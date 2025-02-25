package geso.erp.servlets.reports;

import geso.erp.db.sql.dbutils;
import geso.erp.servlets.reports.ReportAPI;
import geso.dms.distributor.util.Utility;
import geso.erp.beans.report.Ireport;
import geso.erp.beans.report.imp.Report;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.Color;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Font;
import com.aspose.cells.Style;
import com.aspose.cells.TextAlignmentType;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;

public class ErpBCViengThamSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    public ErpBCViengThamSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Ireport obj;
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    HttpSession session = request.getSession();	    
	    Utility util = new Utility();  
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    obj = new Report();
	    obj.setuserId(userId);
	    obj.setRsSanPham();
	    obj.setRsKenhBanHang();
	    obj.setRsNhaPhanPhoi();
	    obj.setRsNhomSanPham();
		session.setAttribute("obj", obj);
		String nextJSP = "/TrainingGESO/pages/Erp/ErpBCViengTham.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		OutputStream out = response.getOutputStream(); 
		Ireport obj;
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    	    
	    HttpSession session = request.getSession();	    
	    
	    String userTen = (String) session.getAttribute("userTen");
	    String userId = request.getParameter("userId");
	    obj = new Report();
	    obj.setuserId(userId);
	    obj.setuserTen(userTen);

	    String month = request.getParameter("month");
	    if(month == null)
	    	month = "";
	    obj.setMonth(month);
	    
	    String year = request.getParameter("year");
	    if(year == null)
	    	year = "";
	    obj.setYear(year);
	    
	    String sanPham = request.getParameter("sanPham");
	    if(sanPham == null)
	    	sanPham = "";
	    obj.setSanPham(sanPham);
	    
	    String nhaPhanPhoi = request.getParameter("nhaPhanPhoi");
	    if(nhaPhanPhoi == null)
	    	nhaPhanPhoi = "";
	    obj.setNhaPhanPhoi(nhaPhanPhoi);
	    
	    String kenhBanHang = request.getParameter("kenhBanHang");
	    if(kenhBanHang == null)
	    	kenhBanHang = "";
	    obj.setKenhBanHang(kenhBanHang);
	    
	    String nhomSanPham = request.getParameter("nhomSanPham");
	    if(nhomSanPham == null)
	    	nhomSanPham = "";
	    obj.setNhomSanPham(nhomSanPham);
	    
	    
	   
	    String action = request.getParameter("action");
	    if(action.equals("search"))
	    {
	    	session.setAttribute("obj", obj);
			String nextJSP = "/TrainingGESO/pages/Erp/ErpBCViengTham.jsp";
			response.sendRedirect(nextJSP);
	    }
	    else
	    {
	    	response.setContentType("application/xlsm");
			response.setHeader("Content-Disposition", "attachment; filename=BCViengTham.xlsm");
	    	boolean isTrue = false;
			try 
			{
				isTrue = CreatePivotTable(out, obj, "");	
			} 
			catch (Exception e) 
			
			{
				e.printStackTrace();
				isTrue = false;
			}
			
			if(!isTrue)
			{
				 
				session.setAttribute("obj", obj);
				obj.setMsg("Không thể tạo báo cáo");
				
				String nextJSP = "/TrainingGESO/pages/Erp/ErpBCViengTham.jsp";
				response.sendRedirect(nextJSP);
			}
	    }
	}
	

	private boolean CreatePivotTable(OutputStream out, Ireport obj, String condition) throws Exception
    {   
		FileInputStream fstream = null;
		Workbook workbook = new Workbook();		
		 // copy nguyen cai template da co san trong he thong
		fstream = new FileInputStream(getServletContext().getInitParameter("path") + "\\ErpBCViengTham.xlsm");

		workbook.open(fstream);
		workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
		
	     CreateStaticHeader(workbook, obj );	     
	     boolean isTrue = CreateStaticData(workbook, obj, condition);
	    /* if(!isTrue){
	    	 return false;
	     }*/
	     workbook.save(out);
			
		fstream.close();
	     return true;
   }
	
	private void CreateStaticHeader(Workbook workbook,Ireport  obj) 
	{
		String UserName=obj.getuserTen();
		String month= obj.getMonth();
		String year =obj.getYear();
		String sanPham = obj.getSanPham();
		String nhaPhanPhoi = obj.getNhaPhanPhoi();
		String kenhBanHang = obj.getKenhBanHang();
		String nhomSanPham = obj.getNhomSanPham();
 
		Worksheets worksheets = workbook.getWorksheets();
	    Worksheet worksheet = worksheets.getSheet(0);
	   	   
	    worksheet.setName("BCViengTham");
	    
	    Cells cells = worksheet.getCells();
		
	    Style style ;
	    Font font = new Font();
	    font.setColor(Color.RED);//mau chu
	    font.setSize(16);// size chu
	   	font.setBold(true);
	   	
	    cells.setRowHeight(0, 20.0f);
	    Cell cell = cells.getCell("A1");
	    style = cell.getStyle();
	    style.setFont(font); 
	    style.setHAlignment(TextAlignmentType.LEFT);// canh le cho chu 	  
	    
	    String tieuDe = "BÁO CÁO VIẾNG THĂM";
	    ReportAPI.getCellStyle(cell,Color.RED, true, 14, tieuDe);
	           
	    cells.setRowHeight(4, 18.0f);
	    cell = cells.getCell("A2");
	    ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "Tháng " + month);
	    
	    cells.setRowHeight(4, 18.0f);
	    cell = cells.getCell("B2");
	    ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "Năm " + year);
	    
	    cells.setRowHeight(4, 18.0f);
	    cell = cells.getCell("A3");
	    ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "Sản phẩm " + sanPham);
	    
	    cells.setRowHeight(4, 18.0f);
	    cell = cells.getCell("C3");
	    ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "Nhà phân phối " + nhaPhanPhoi);
	    
	    cells.setRowHeight(4, 18.0f);
	    cell = cells.getCell("A4");
	    ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "Kênh bán hàng " + kenhBanHang);
	    
	    cells.setRowHeight(4, 18.0f);
	    cell = cells.getCell("C4");
	    ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "Nhóm sản phẩm " + nhomSanPham);
	    
	    cells.setRowHeight(4, 18.0f);
	    cell = cells.getCell("A5");
	    ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "Ngày báo cáo: " + ReportAPI.NOW("yyyy-MM-dd"));
	    
	    cells.setRowHeight(5, 18.0f);
	    cell = cells.getCell("A6");
	    ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "Được tạo bởi:  " + UserName);
	     
	    
		/*	cell = cells.getCell("A10");cell.setValue("Vùng");ReportAPI.setCellHeader(cell);
			cell = cells.getCell("B10");cell.setValue("Khu vực");ReportAPI.setCellHeader(cell);
			cell = cells.getCell("C10");cell.setValue("Tỉnh thành");ReportAPI.setCellHeader(cell);
			cell = cells.getCell("D10");cell.setValue("TDV");ReportAPI.setCellHeader(cell);
			cell = cells.getCell("E10");cell.setValue("Mã KH");ReportAPI.setCellHeader(cell);
			cell = cells.getCell("F10");cell.setValue("Tên KH");ReportAPI.setCellHeader(cell);
			cell = cells.getCell("G10");cell.setValue("Địa chỉ");ReportAPI.setCellHeader(cell);
			cell = cells.getCell("H10");cell.setValue("Loại KH");ReportAPI.setCellHeader(cell);
			cell = cells.getCell("I10");cell.setValue("Tần suất VT");ReportAPI.setCellHeader(cell);
			cell = cells.getCell("J10");cell.setValue("Đếm số lần đã VT");ReportAPI.setCellHeader(cell);
			cell = cells.getCell("K10");cell.setValue("Số lần VT/\n tổng số lần phẩn VT");ReportAPI.setCellHeader(cell);
			cell = cells.getCell("L10");cell.setValue("SoLanViengTham");ReportAPI.setCellHeader(cell);
			cell = cells.getCell("M10");cell.setValue("NgayID");ReportAPI.setCellHeader(cell);
			cell = cells.getCell("N10");cell.setValue("ThanhTien");ReportAPI.setCellHeader(cell);*/
		 
	    	
	}
	
	private boolean CreateStaticData(Workbook workbook, Ireport obj, String condition) throws Exception
	{
		dbutils db = new dbutils();
		Worksheets worksheets = workbook.getWorksheets();
	    Worksheet worksheet = worksheets.getSheet(0);
	    Cells cells = worksheet.getCells();
		
	    String[] param = new String[50];
		param[0] = obj.getMonth().equals("") ? null : obj.getMonth();
		param[1] = obj.getYear().equals("") ? null : obj.getYear();
		
		
		param[2] = obj.getuserId().equals("") ? null : obj.getuserId();
		param[3] = obj.getSanPham().equals("") ? null : obj.getSanPham();
		param[4] = obj.getNhaPhanPhoi().equals("") ? null : obj.getNhaPhanPhoi();
		param[5] = obj.getKenhBanHang().equals("") ? null : obj.getKenhBanHang();
		param[6] = obj.getNhomSanPham().equals("") ? null : obj.getNhomSanPham();
		
		String tungay=obj.getYear()+"-"+(obj.getMonth().length()>1? obj.getMonth():"0"+obj.getMonth()) +"-01";
		String denngay=obj.getYear()+"-"+(obj.getMonth().length()>1? obj.getMonth():"0"+obj.getMonth()) +"-31";
		
		String[] mangngayct= this.ngayidcuoithang(obj,db);
		String sql_loc="";
		if(obj.getKenhBanHang().length()>0){
			sql_loc=" and DH.KBH_FK="+obj.getKenhBanHang() ;	
		}
		
		String	query="  select   VUNG.TEN as vung,  kv.ten as khuvuc, tt.pk_Seq as tinhthanhid,tt.ten as tinhthanh, "+ 
					 "  ddkd.ten,tuyen.ddkd_fk,tuyen.khachhang_fk,kh.mafast as makhachhang,kh.ten as tenkhachhang, kh.diachi,lch.DIENGIAI as loaikh,"+ 
					 " tuyen.tanso  ,isnull(solanviengtham ,0)  as solanviengtham,tuyen.ngayid,"+ 
					 " isnull( doanhso.thanhtien,0) as thanhtien_nk , isnull(thanhtien_sx,0) as   thanhtien_sx   " +
					 " ,isnull(doanhso_tong.thanhtien_tong,0) as thanhtien_tong "+ 
					 " "+  
					 " from ("+ 
					 " "+ 
					 "  "+ 
					 " 			select a.ddkd_fk,a.khachhang_fk,a.tanso , "+ 
					 " 			("+ 
					 " "+ 
					 " 			select   cast(b.ngayid as varchar(10)) + ', ' AS [text()]  		 "+ 
					 " 			from( "+ 
					 " 			select distinct tuyen.ddkd_fk,tbh_kh.khachhang_fk,tbh_kh.tanso,tuyen.ngayid"+ 
					 " 			from  tuyenbanhang tuyen"+ 
					 " 			inner join  KHACHHANG_TUYENBH  tbh_kh   on tuyen.pk_seq=tbh_kh.tbh_fk"+ 
					 " 			)  b "+ 
					 " 			where a.ddkd_fk=b.ddkd_fk and  a.khachhang_fk = b.khachhang_fk and a.tanso= b.tanso"+ 
					 " 			For XML PATH ('')  "+ 
					 " 			)  as ngayid "+ 
					 " "+ 
					 " 			from ("+ 
					 " 			select  distinct tuyen.ddkd_fk,tbh_kh.khachhang_fk,tbh_kh.tanso   from  tuyenbanhang tuyen"+ 
					 " 			inner join  KHACHHANG_TUYENBH  tbh_kh   on tuyen.pk_seq=tbh_kh.tbh_fk "+ 
					 " 			) a "+ 
					 " 	 "+ 
					 " ) as tuyen "+ 
					 " inner join daidienkinhdoanh ddkd on ddkd.pk_Seq=tuyen.ddkd_fk"+ 
					 " left join tinhthanh tt on tt.pk_Seq=ddkd.tinhthanh_fk"+ 
					 " inner join khachhang kh on kh.pk_Seq=tuyen.khachhang_fk"+ 
					 " inner join loaicuahang lch on lch.pk_seq=kh.lch_fk"+ 
					 " left join "+ 
					 " (	"+ 
					 " 	select tinhthanh_fk,max(khuvuc_fk) as khuvuc_fk"+ 
					 " 	from khuvuc_tinhthanh  "+ 
					 " 	group by tinhthanh_fk"+ 
					 " ) thkv on thkv.tinhthanh_fk=tt.pk_seq"+ 
					 " left join khuvuc kv on kv.pk_Seq=thkv.khuvuc_fk " +
					 " left join vung on vung.pk_seq=kv.vung_fk "+ 
					 " left join "+ 
					 " ("+ 
					 " 	select ddkd_fk,khachhang_fk ,count( distinct CONVERT(char(10),thoidiem,126)) as solanviengtham "+ 
					 " 	from ddkd_khachhang where thoidiem >='"+tungay+"'"+ 
					 " 	and  thoidiem <='"+denngay+"'"+ 
					 " 	group by  ddkd_fk,khachhang_fk"+ 
					 " "+ 
					 " )viengtham on viengtham.ddkd_fk =tuyen.ddkd_fk and viengtham.khachhang_fk=tuyen.khachhang_fk"+ 
					 " left join "+ 
					 " ("+ 
					 " 	select ddkd_fk,khachhang_fk ,sum(thanhtien) as thanhtien from ("+ 
					 " "+ 
					 " 		SELECT ddkd_fk,khachhang_fk ,SUM(SOLUONG*GIAMUA) AS THANHTIEN"+ 
					 " 		FROM donhang DH "+ 
					 " 		INNER JOIN DONHANG_SANPHAM DHSP ON  DH.PK_SEQ=DHSP.DONHANG_FK "+ 
					 " 		WHERE"+ 
					 " 		  DH.TRANGTHAI <>2 AND DH.NGAYNHAP  >='"+tungay+"' and DH.NGAYNHAP  <='"+denngay+"'  " +
					 		"  and SANPHAM_FK in (SELECT SANPHAM_FK FROM NHOMHANG_SANPHAM WHERE NhomHang_FK= 100000) "+ sql_loc+
					 " 		GROUP BY ddkd_fk,khachhang_fk "+ 
					 " "+ 
					 
					 " 			union all "+ 
					 " 		SELECT ddkd_fk,khachhang_fk ,(-1)*SUM(SOLUONG*DonGia) AS THANHTIEN"+ 
					 " 		FROM Erp_HangTraLaiNpp DH "+ 
					 " 		INNER JOIN Erp_HangTraLaiNpp_SanPham DHSP ON  DH.PK_SEQ=DHSP.HangTraLai_fk "+ 
					 " 		WHERE  dh.doituong=1  and dh.trangthai <> 2  AND"+ 
					 " 		DH.ngaytra  >='"+tungay+"' and DH.ngaytra  <='"+denngay+"'  " +
					 		" and SANPHAM_FK in (SELECT SANPHAM_FK FROM NHOMHANG_SANPHAM WHERE NhomHang_FK= 100000) "+ sql_loc+ 
					 " 		GROUP BY ddkd_fk,khachhang_fk "+ 
					 " 	)  a "+ 
					 " 	group by ddkd_fk,khachhang_fk"+ 
					 " "+ 
					 " )doanhso on doanhso.ddkd_fk =tuyen.ddkd_fk and doanhso.khachhang_fk=tuyen.khachhang_fk  " +
					 "  left join  "+ 
					 "  ( "+ 
					 "  	select ddkd_fk,khachhang_fk ,sum(thanhtien) as thanhtien_sx from ( "+ 
					 "   "+ 
					 "  		SELECT ddkd_fk,khachhang_fk ,SUM(SOLUONG*GIAMUA) AS THANHTIEN "+ 
					 "  		FROM donhang DH  "+ 
					 "  		INNER JOIN DONHANG_SANPHAM DHSP ON  DH.PK_SEQ=DHSP.DONHANG_FK  "+ 
					 "  		WHERE "+ 
					 "  		DH.TRANGTHAI <>2 AND  DH.NGAYNHAP  >='"+tungay+"' and DH.NGAYNHAP  <='"+denngay+"' "+ 
					 "  		and SANPHAM_FK in (SELECT SANPHAM_FK FROM NHOMHANG_SANPHAM WHERE NhomHang_FK= 100001) "+ sql_loc+
					 "  		GROUP BY ddkd_fk,khachhang_fk  "+ 
					 "   "+ 
					 "  			union all  "+ 
					 "  		SELECT ddkd_fk,khachhang_fk ,(-1)*SUM(SOLUONG*DonGia) AS THANHTIEN "+ 
					 "  		FROM Erp_HangTraLaiNpp DH  "+ 
					 "  		INNER JOIN Erp_HangTraLaiNpp_SanPham DHSP ON  DH.PK_SEQ=DHSP.HangTraLai_fk  "+ 
					 "  		WHERE  dh.doituong=1  and dh.trangthai <> 2  AND "+ 
					 "  		DH.ngaytra  >='"+tungay+"' and DH.ngaytra  <='"+denngay+"' "+ 
					 "  		and SANPHAM_FK in (SELECT SANPHAM_FK FROM NHOMHANG_SANPHAM WHERE NhomHang_FK= 100001) "+ sql_loc+
					 "  		GROUP BY ddkd_fk,khachhang_fk  "+ 
					 "  	)  a  "+ 
					 "  	group by ddkd_fk,khachhang_fk "+ 
					 "   "+ 
					 "  )doanhso_sx on doanhso_sx.ddkd_fk =tuyen.ddkd_fk and doanhso_sx.khachhang_fk=tuyen.khachhang_fk "+
					 "  left join  "+ 
					 "  ( "+ 
					 "  	select ddkd_fk,khachhang_fk ,sum(thanhtien) as thanhtien_tong from ( "+ 
					 "   "+ 
					 "  		SELECT ddkd_fk,khachhang_fk ,SUM(SOLUONG*GIAMUA) AS THANHTIEN "+ 
					 "  		FROM donhang DH  "+ 
					 "  		INNER JOIN DONHANG_SANPHAM DHSP ON  DH.PK_SEQ=DHSP.DONHANG_FK  "+ 
					 "  		WHERE "+ 
					 "  		DH.TRANGTHAI <>2 AND  DH.NGAYNHAP  >='"+tungay+"' and DH.NGAYNHAP  <='"+denngay+"' "+ sql_loc+
				 
					 "  		GROUP BY ddkd_fk,khachhang_fk  "+ 
					 "   "+ 
					 "  			union all  "+ 
					 "  		SELECT ddkd_fk,khachhang_fk , (-1)*SUM(SOLUONG*DonGia) AS THANHTIEN "+ 
					 "  		FROM Erp_HangTraLaiNpp DH  "+ 
					 "  		INNER JOIN Erp_HangTraLaiNpp_SanPham DHSP ON  DH.PK_SEQ=DHSP.HangTraLai_fk  "+ 
					 "  		WHERE  dh.doituong=1  and dh.trangthai <> 2  AND "+ 
					 "  		DH.ngaytra  >='"+tungay+"' and DH.ngaytra  <='"+denngay+"' "+ sql_loc+
					 
					 "  		GROUP BY ddkd_fk,khachhang_fk  "+ 
					 "  	)  a  "+ 
					 "  	group by ddkd_fk,khachhang_fk "+ 
					 "   "+ 
					 "  )doanhso_tong on doanhso_tong.ddkd_fk =tuyen.ddkd_fk and doanhso_tong.khachhang_fk=tuyen.khachhang_fk " +
					 " where 1=1   ";
		
		if(obj.getNhaPhanPhoi().length() >0){
			//lấy nhũng ddkd trong bảng  DAIDIENKINHDOANH_NPP
			query+= " and  ddkd.pk_seq in ( select  ddkd_fk from     DAIDIENKINHDOANH_NPP WHERE npp_fk="+obj.getNhaPhanPhoi()+")";
		}
		
		 
		
		System.out.println(query);
		
		ResultSet rs = db.get(query);
		
		int i = 12;
		  
			try
			{
				cells.setColumnWidth(0, 15.0f);
				cells.setColumnWidth(1, 15.0f);
				cells.setColumnWidth(2, 15.0f);
				cells.setColumnWidth(3, 15.0f);
				cells.setColumnWidth(4, 15.0f);
				cells.setColumnWidth(5, 15.0f);
				cells.setColumnWidth(6, 15.0f);
				cells.setColumnWidth(7, 15.0f);
				cells.setColumnWidth(8, 15.0f);

				Cell cell = null;
				if(rs!=null){
				while (rs.next())
				{
					
					cell = cells.getCell("A" + Integer.toString(i));cell.setValue(rs.getString("Vung"));
					
					cell = cells.getCell("B" + Integer.toString(i));cell.setValue(rs.getString("KHUVUC"));	
					cell = cells.getCell("C" + Integer.toString(i));cell.setValue(rs.getString("TINHTHANH"));
					
					cell = cells.getCell("D" + Integer.toString(i));cell.setValue(rs.getString("TEN"));
					
					cell = cells.getCell("E" + Integer.toString(i));cell.setValue(rs.getString("MAKHACHHANG"));
					cell = cells.getCell("F" + Integer.toString(i));cell.setValue(rs.getString("TENKHACHHANG") );
					 
					cell = cells.getCell("G" + Integer.toString(i));cell.setValue(rs.getString("DIACHI") );
					cell = cells.getCell("H" + Integer.toString(i));cell.setValue(rs.getString("LOAIKH"));
					
					cell = cells.getCell("I" + Integer.toString(i));cell.setValue(rs.getString("TANSO"));
					cell = cells.getCell("J" + Integer.toString(i));cell.setValue(rs.getDouble("SOLANVIENGTHAM") );
					
					double tongsolanviengtham=getsolanviengtham(rs.getString("TANSO")) + this.getsolanvt_ngayle( rs.getString("ngayid"),mangngayct,rs.getString("TANSO"));
					
					cell = cells.getCell("K" + Integer.toString(i));cell.setValue(rs.getInt("SOLANVIENGTHAM")+"/"+tongsolanviengtham );
					 
					cell = cells.getCell("L" + Integer.toString(i));cell.setValue(rs.getDouble("thanhtien_nk"));
					cell = cells.getCell("M" + Integer.toString(i));cell.setValue(rs.getDouble("thanhtien_sx"));
					cell = cells.getCell("N" + Integer.toString(i));cell.setValue(rs.getDouble("thanhtien_tong") );
					i++;
				}
				}
				if (rs != null)
					rs.close();
				if (db != null)
					db.shutDown();
				/*if (i == 2)
				{
					throw new Exception("Khong co bao cao trong thoi gian nay...");
				}*/
			} catch (Exception e)
			{
				System.out.println("115.Exception: " + e.getMessage());
				e.printStackTrace();
				throw new Exception(e.getMessage());
			}
		 

		if (db != null)
			db.shutDown();
		return true;	
	}
	private String[] ngayidcuoithang(Ireport obj,dbutils db){
 
		String ngayid_="100";
		String tungay=obj.getYear()+"-"+(obj.getMonth().length()>1? obj.getMonth():"0"+obj.getMonth());
		
		try{
			String query=" SELECT   DATEPART( DAY, DATEADD(d,-1, DATEADD(mm, DATEDIFF(mm, 0 , '"+tungay+"-01' )+1, 0))) as day1 ";
				
			System.out.println("query  : "+query);
			ResultSet rs=db.get(query);
			String query1="";
			if(rs.next()){
				int ngaycuoithang= rs.getInt("day1");
				for(int i=29;i<=ngaycuoithang;i++){
					if(i==29){
					query1 ="    SELECT DATEPART(dw,'"+tungay+"-"+i+"')  as ngayid";
					}else{
						query1+=" union  SELECT DATEPART(dw,'"+tungay+"-"+i+"') as ngayid";
					}
					
				}
				System.out.println("querry1 : "+query1);
				if(query1.length()>0){
					ResultSet rsday=db.get(query1);
					
					while(rsday.next()){
						ngayid_+=";"+rsday.getString("ngayid");
					}
					rsday.close();
				}
				
				
				
			}
			
		}catch(Exception er){
			er.printStackTrace();
		}
		return   ngayid_.split(";");
	}
	private int getsolanvt_ngayle(String ngayid, String[] mangngayct,String tanso) {
		
		if(tanso==null){
			tanso="";
		}
		if(tanso.length()>1){
			if(tanso.indexOf("F12") >=0){
				tanso=tanso.substring(0,3);
			}else{
				tanso=tanso.substring(0,2);
			}
			System.out.println(tanso);
		}
		
		
		// TODO Auto-generated method stub
		int bien=0;
		if(!tanso.equals("F1") && !tanso.equals("F2") ){
			for(int i=0;i<mangngayct.length;i++ ){
				if(ngayid.indexOf(mangngayct[i])>=0){
					bien++;
				}
			}
		}
		return bien;
	}

	private int getsolanviengtham(String tanso) {
		// TODO Auto-generated method stub
		int solanvt=0;
		if(tanso==null){
			tanso="";
		}
		if(tanso.length()>1){
			if(tanso.indexOf("F12") >=0){
				tanso=tanso.substring(0,3);
			}else{
				tanso=tanso.substring(0,2);
			}
			System.out.println(tanso);
		}
		 if(tanso.equals("F1")){
			 solanvt=1;
		 }else if(tanso.equals("F2")){
			 solanvt=2;
		 }else if(tanso.equals("F4")){
			 solanvt=4;
		 }else if(tanso.equals("F8")){
			 solanvt=8;
		 }else if(tanso.equals("F12")){
			 solanvt=12;
		 }
		return solanvt;
	}
}
