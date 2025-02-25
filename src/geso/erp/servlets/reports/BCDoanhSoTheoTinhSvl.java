package geso.erp.servlets.reports;

import geso.erp.beans.report.Ireport;
import geso.erp.beans.report.imp.Report;
import geso.erp.db.sql.dbutils;
import geso.erp.servlets.reports.ReportAPI;
import geso.dms.distributor.util.Utility; 
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

public class BCDoanhSoTheoTinhSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    public BCDoanhSoTheoTinhSvl() {
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
	    
		session.setAttribute("obj", obj);
				
		String nextJSP = "/TrainingGESO/pages/Erp/BCDoanhSoTheoTinh_Thai.jsp";
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
	    
	    String tungay = request.getParameter("tungay");
	    if(tungay == null)
	    	tungay = "";
	    obj.settungay(tungay);
	    
	    String denngay = request.getParameter("denngay");
	    if(denngay == null)
	    	denngay = "";
	    obj.setdenngay(denngay);
	   
	    String action = request.getParameter("action");
	    System.out.println("Tu ngay: " + tungay);
	    System.out.println("Den ngay: " + denngay);
	    System.out.println("Action nhan duoc: " + action);
	    
	    if(action.equals("search"))
	    {
 	    	session.setAttribute("obj", obj);
 			String nextJSP = "/TrainingGESO/pages/Erp/BCDoanhSoTheoTinh_Thai.jsp";
			response.sendRedirect(nextJSP);
	    }
	    else
	    {
	    	response.setContentType("application/xlsm");
			response.setHeader("Content-Disposition", "attachment; filename=BCDoanhSoTheoTinh_Thai.xlsm");
			
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
 				String nextJSP = "/TrainingGESO/pages/Erp/BCDoanhSoTheoTinh_Thai.jsp";
				response.sendRedirect(nextJSP);
			}
	    }
	}
 	private boolean CreatePivotTable(OutputStream out, Ireport obj, String condition) throws Exception
    {   
		FileInputStream fstream = null;
		Workbook workbook = new Workbook();		
		 
		fstream = new FileInputStream(getServletContext().getInitParameter("path") + "\\BCDoanhSoTheoTinh_Thai.xlsm");

		workbook.open(fstream);
		workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
		
	     CreateStaticHeader(workbook, obj );	     
	     boolean isTrue = CreateStaticData(workbook, obj, condition);
	     if(!isTrue){
	    	 return false;
	     }
	     workbook.save(out);
 		fstream.close();
	     return true;
   }
 	private void CreateStaticHeader(Workbook workbook,Ireport  obj) 
	{
		 String dateFrom= obj.gettungay(); 
		 String dateTo =obj.getdenngay(); 
		 String UserName=obj.getuserTen();
 
		Worksheets worksheets = workbook.getWorksheets();
	    Worksheet worksheet = worksheets.getSheet(0);
	   	   
	    worksheet.setName("Sheet1");
	    
	    Cells cells = worksheet.getCells();
		
	    Style style;
	    Font font = new Font();
	    font.setColor(Color.RED);//mau chu
	    font.setSize(16);// size chu
	   	font.setBold(true);
 	    cells.setRowHeight(0, 20.0f);
 	    
 	    
	    Cell cell = cells.getCell("A1");
	    style = cell.getStyle();
	    style.setFont(font); 
	    style.setHAlignment(TextAlignmentType.LEFT);// canh le cho chu 	  
 	    String tieude = "DOANH SỐ BÁN CỦA MẶT HÀNG THEO TỈNH VÀ KHÁCH HÀNG";
	    ReportAPI.getCellStyle(cell,Color.RED, true, 14, tieude);
 	    cells.setRowHeight(4, 18.0f);
 	    
 	    
	    cell = cells.getCell("A2");
	    ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "Từ ngày: " + dateFrom);
 	    cells.setRowHeight(4, 18.0f);
 	    
 	    
	    cell = cells.getCell("C2");
	    ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "Đến ngày: " + dateTo);
 	    cells.setRowHeight(4, 18.0f);
 	    
 	    
	    cell = cells.getCell("A3");
	    ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "Ngày báo cáo: " + ReportAPI.NOW("yyyy-MM-dd"));
 	    cells.setRowHeight(5, 18.0f);
 	    
 	    
	    cell = cells.getCell("A4");
	    ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "Được tạo bởi:  " + UserName);
    
		cell = cells.getCell("A7");cell.setValue("STT");ReportAPI.setCellHeader(cell);
		cell = cells.getCell("B7");cell.setValue("Mã");ReportAPI.setCellHeader(cell);
		cell = cells.getCell("C7");cell.setValue("Tỉnh/Địa bàn");ReportAPI.setCellHeader(cell);
		cell = cells.getCell("D7");cell.setValue("Tháng 1");ReportAPI.setCellHeader(cell);
		cell = cells.getCell("E7");cell.setValue("Tháng 2");ReportAPI.setCellHeader(cell);
		cell = cells.getCell("F7");cell.setValue("Tháng 3");ReportAPI.setCellHeader(cell);
		cell = cells.getCell("G7");cell.setValue("Tháng 4");ReportAPI.setCellHeader(cell);
		cell = cells.getCell("H7");cell.setValue("Tháng 5");ReportAPI.setCellHeader(cell);
		cell = cells.getCell("I7");cell.setValue("Tháng 6");ReportAPI.setCellHeader(cell);
		cell = cells.getCell("J7");cell.setValue("Tháng 7");ReportAPI.setCellHeader(cell);
		cell = cells.getCell("K7");cell.setValue("Tháng 8");ReportAPI.setCellHeader(cell);
		cell = cells.getCell("L7");cell.setValue("Tháng 9");ReportAPI.setCellHeader(cell);
		cell = cells.getCell("M7");cell.setValue("Tháng 10");ReportAPI.setCellHeader(cell);
		cell = cells.getCell("N7");cell.setValue("Tháng 11");ReportAPI.setCellHeader(cell);
		cell = cells.getCell("O7");cell.setValue("Tháng 12");ReportAPI.setCellHeader(cell);
		cell = cells.getCell("P7");cell.setValue("Tổng");ReportAPI.setCellHeader(cell);
  	}
 	private boolean CreateStaticData(Workbook workbook, Ireport obj, String condition) throws Exception
	{
		dbutils db = new dbutils();
		Worksheets worksheets = workbook.getWorksheets();
	    Worksheet worksheet = worksheets.getSheet(0);
	    Cells cells = worksheet.getCells();
		
	    String[] param = new String[15];
		param[0] = obj.gettungay().equals("") ? null : obj.gettungay();
		param[1] = obj.getdenngay().equals("") ? null : obj.getdenngay();
		param[2] = obj.getuserId().equals("") ? null : obj.getuserId();
		String	query="	select  isnull(TT.PK_SEQ,0)  AS MATINH , isnull(TT.TEN,'')  AS TINHTHANH , NPP.MA as Ma ,NPP.TEN as Ten ,DH.* from( "+
					  " select a.npp_fk, CAST(a.thang AS INT) AS THANG,sum(a.thanhtien ) as thanhtien from ("+
 					  " select   dh.NPP_DAT_FK AS npp_fk  , substring(dh.NgayDonHang,6,2)  as thang ,SUM( dhsp.soluong*dhsp.dongia ) as thanhtien "+ 
 					  " from ERP_DONDATHANGNPP  dh "+
 					  " inner join ERP_DONDATHANGNPP_SANPHAM dhsp on dh.PK_SEQ=dhsp.dondathang_fk "+
 					  " where dh.TRANGTHAI!=3 and dh.NgayDonHang >='"+obj.gettungay()+"' and dh.NgayDonHang <='"+obj.getdenngay()+"'"+
 					 " group by dh.NPP_DAT_FK ,substring(dh.NgayDonHang,6,2)  "+
  					 " union all  "+ 
 					 " SELECT dh.NppTra_FK ,substring(dh.NGAYTRA,6,2)  , (-1) *SUM( DHSP.SOLUONG*DHSP.DONGIA ) AS THANHTIEN  "+
 					 " FROM ERP_HANGTRALAINPP  DH "+
 					 " INNER JOIN ERP_HANGTRALAINPP_SANPHAM DHSP ON DH.PK_SEQ=DHSP.HANGTRALAI_FK "+
 					 " WHERE DH.TRANGTHAI=1 AND DH.DOITUONG=0  AND DH.NGAYTRA >='"+obj.gettungay()+"' AND DH.NGAYTRA <='"+obj.getdenngay()+"'"+
 					 " GROUP BY DH.NppTra_FK,substring(dh.NGAYTRA,6,2)  "+
					
 					 " )  a "+
 					 " group by npp_fk,thang "+
 					 " ) as data "+
 					 "  PIVOT "+
 					 " ( "+
 					 " SUM(thanhtien)  "+
 					 " FOR THANG IN  "+
 					 " ([1],[2],[3],[4],[5],[6],[7],[8] ,[9] ,[10] ,[11] ,[12] )  "+
 					 " ) AS DH "+
 					 " INNER JOIN NHAPHANPHOI NPP ON NPP.PK_sEQ=DH.NPP_FK  "+
 					 " LEFT JOIN TINHTHANH TT ON TT.PK_SEQ=NPP.TINHTHANH_FK "+
 					 " ORDER BY TT.TEN"; 
		ResultSet rs = db.get(query);
		System.out.println("RS: " );
 		int i = 8;
 			try
			{
				cells.setColumnWidth(0, 15.0f);
				cells.setColumnWidth(1, 15.0f);
				cells.setColumnWidth(2, 70.0f);
				cells.setColumnWidth(3, 15.0f);
				cells.setColumnWidth(4, 15.0f);
				cells.setColumnWidth(5, 15.0f);
				cells.setColumnWidth(6, 15.0f);
				cells.setColumnWidth(7, 15.0f);
				cells.setColumnWidth(8, 15.0f);
				cells.setColumnWidth(9, 15.0f);
				cells.setColumnWidth(10, 15.0f);
				cells.setColumnWidth(11, 15.0f);
				cells.setColumnWidth(12, 15.0f); 
				
				Cell cell = null;
				String a= "a";
				int j=i;
				int sott=1; 
				while (rs.next())
				{   
						//So sánh mã tỉnh hiện tại với mã tỉnh trước đó, 
						//nếu KHÁC nhau thì tạo 1 dòng TỈNH MỚI phía trên các dòng NHÀ BÁN LẺ thuộc TỈNH đó
						if(i==8){
								a= rs.getString("MATINH");
								cell = cells.getCell("B" + Integer.toString(i));cell.setValue(rs.getString("MATINH"));
								cell = cells.getCell("C" + Integer.toString(i));cell.setValue(rs.getString("TINHTHANH"));
								i++;
								  
						}
						if(!a.equals(rs.getString("MATINH"))) 
						{   
					 
							System.out.println(a);
							a = rs.getString("MATINH");
							//cell = cells.getCell("A" + Integer.toString(i));cell.setValue(i-7);
							cell = cells.getCell("B" + Integer.toString(i));cell.setValue(rs.getString("MATINH"));
	 						cell = cells.getCell("C" + Integer.toString(i));cell.setValue(rs.getString("TINHTHANH"));
	 						 
							//Tính tổng theo tỉnh
							cell = cells.getCell("D" + Integer.toString(j));cell.setFormula("=SUM(D"+(j+1)+":D"+(i-1)+")");
							cell = cells.getCell("E" + Integer.toString(j));cell.setFormula("=SUM(E"+(j+1)+":E"+(i-1)+")");
							cell = cells.getCell("F" + Integer.toString(j));cell.setFormula("=SUM(F"+(j+1)+":F"+(i-1)+")");
							cell = cells.getCell("G" + Integer.toString(j));cell.setFormula("=SUM(G"+(j+1)+":G"+(i-1)+")");
							cell = cells.getCell("H" + Integer.toString(j));cell.setFormula("=SUM(H"+(j+1)+":H"+(i-1)+")");
							cell = cells.getCell("I" + Integer.toString(j));cell.setFormula("=SUM(I"+(j+1)+":I"+(i-1)+")");
							cell = cells.getCell("J" + Integer.toString(j));cell.setFormula("=SUM(J"+(j+1)+":J"+(i-1)+")");
							cell = cells.getCell("K" + Integer.toString(j));cell.setFormula("=SUM(K"+(j+1)+":K"+(i-1)+")");
							cell = cells.getCell("L" + Integer.toString(j));cell.setFormula("=SUM(L"+(j+1)+":L"+(i-1)+")");
							cell = cells.getCell("M" + Integer.toString(j));cell.setFormula("=SUM(M"+(j+1)+":M"+(i-1)+")");
							cell = cells.getCell("N" + Integer.toString(j));cell.setFormula("=SUM(N"+(j+1)+":N"+(i-1)+")");
							cell = cells.getCell("O" + Integer.toString(j));cell.setFormula("=SUM(O"+(j+1)+":O"+(i-1)+")");
							cell = cells.getCell("P" + Integer.toString(j));cell.setFormula("=SUM(P"+(j+1)+":P"+(i-1)+")");
							
							j = i;
							 i++;
						}
						//đổ dữ liệu không cần xét điều kiện If nên để ngoài If cho dòng code đơn giản
							cell = cells.getCell("A" + Integer.toString(i));cell.setValue(sott);
							cell = cells.getCell("B" + Integer.toString(i));cell.setValue(rs.getString("Ma"));
 							cell = cells.getCell("C" + Integer.toString(i));cell.setValue(rs.getString("Ten"));
 						// Đổ dữ liệu
 							cell = cells.getCell("D" + Integer.toString(i));cell.setValue(rs.getDouble("1"));
							cell = cells.getCell("E" + Integer.toString(i));cell.setValue(rs.getDouble("2"));
							cell = cells.getCell("F" + Integer.toString(i));cell.setValue(rs.getDouble("3"));
							cell = cells.getCell("G" + Integer.toString(i));cell.setValue(rs.getDouble("4"));
							cell = cells.getCell("H" + Integer.toString(i));cell.setValue(rs.getDouble("5") );
							cell = cells.getCell("I" + Integer.toString(i));cell.setValue(rs.getDouble("6"));
							cell = cells.getCell("J" + Integer.toString(i));cell.setValue(rs.getDouble("7"));
							cell = cells.getCell("K" + Integer.toString(i));cell.setValue(rs.getDouble("8") );
							cell = cells.getCell("L" + Integer.toString(i));cell.setValue(rs.getDouble("9"));
							cell = cells.getCell("M" + Integer.toString(i));cell.setValue(rs.getDouble("10"));
							cell = cells.getCell("N" + Integer.toString(i));cell.setValue(rs.getDouble("11") );
							cell = cells.getCell("O" + Integer.toString(i));cell.setValue(rs.getDouble("12"));
							cell = cells.getCell("P" + Integer.toString(i));cell.setFormula("=SUM(D"+(i)+":O"+(i)+")");
							
							sott ++;
							i++;  
 								
 								
				} 
				cell = cells.getCell("D" + Integer.toString(j));cell.setFormula("=SUM(D"+(j+1)+":D"+(i-1)+")");
				cell = cells.getCell("E" + Integer.toString(j));cell.setFormula("=SUM(E"+(j+1)+":E"+(i-1)+")");
				cell = cells.getCell("F" + Integer.toString(j));cell.setFormula("=SUM(F"+(j+1)+":F"+(i-1)+")");
				cell = cells.getCell("G" + Integer.toString(j));cell.setFormula("=SUM(G"+(j+1)+":G"+(i-1)+")");
				cell = cells.getCell("H" + Integer.toString(j));cell.setFormula("=SUM(H"+(j+1)+":H"+(i-1)+")");
				cell = cells.getCell("I" + Integer.toString(j));cell.setFormula("=SUM(I"+(j+1)+":I"+(i-1)+")");
				cell = cells.getCell("J" + Integer.toString(j));cell.setFormula("=SUM(J"+(j+1)+":J"+(i-1)+")");
				cell = cells.getCell("K" + Integer.toString(j));cell.setFormula("=SUM(K"+(j+1)+":K"+(i-1)+")");
				cell = cells.getCell("L" + Integer.toString(j));cell.setFormula("=SUM(L"+(j+1)+":L"+(i-1)+")");
				cell = cells.getCell("M" + Integer.toString(j));cell.setFormula("=SUM(M"+(j+1)+":M"+(i-1)+")");
				cell = cells.getCell("N" + Integer.toString(j));cell.setFormula("=SUM(N"+(j+1)+":N"+(i-1)+")");
				cell = cells.getCell("O" + Integer.toString(j));cell.setFormula("=SUM(O"+(j+1)+":O"+(i-1)+")");
				cell = cells.getCell("P" + Integer.toString(j));cell.setFormula("=SUM(P"+(j+1)+":P"+(i-1)+")");
				
				cell = cells.getCell("C"+(i+1));
			    ReportAPI.getCellStyle(cell, Color.NAVY, true, 20, "Tổng doanh thu: ");
		 	    cells.setRowHeight(10, 18.0f);
		 	    
				cell = cells.getCell("D" + Integer.toString(i+1));cell.setFormula("=(SUM(D"+8+":D"+(i-1)+"))/2");
				cell = cells.getCell("E" + Integer.toString(i+1));cell.setFormula("=(SUM(E"+8+":E"+(i-1)+"))/2");
				cell = cells.getCell("F" + Integer.toString(i+1));cell.setFormula("=(SUM(F"+8+":F"+(i-1)+"))/2");
				cell = cells.getCell("G" + Integer.toString(i+1));cell.setFormula("=(SUM(G"+8+":G"+(i-1)+"))/2");
				cell = cells.getCell("H" + Integer.toString(i+1));cell.setFormula("=(SUM(H"+8+":H"+(i-1)+"))/2");
				cell = cells.getCell("I" + Integer.toString(i+1));cell.setFormula("=(SUM(I"+8+":I"+(i-1)+"))/2");
				cell = cells.getCell("J" + Integer.toString(i+1));cell.setFormula("=(SUM(J"+8+":J"+(i-1)+"))/2");
				cell = cells.getCell("K" + Integer.toString(i+1));cell.setFormula("=(SUM(K"+8+":K"+(i-1)+"))/2");
				cell = cells.getCell("L" + Integer.toString(i+1));cell.setFormula("=(SUM(L"+8+":L"+(i-1)+"))/2");
				cell = cells.getCell("M" + Integer.toString(i+1));cell.setFormula("=(SUM(M"+8+":M"+(i-1)+"))/2");
				cell = cells.getCell("N" + Integer.toString(i+1));cell.setFormula("=(SUM(N"+8+":N"+(i-1)+"))/2");
				cell = cells.getCell("O" + Integer.toString(i+1));cell.setFormula("=(SUM(O"+8+":O"+(i-1)+"))/2");
				cell = cells.getCell("P" + Integer.toString(i+1));cell.setFormula("=(SUM(P"+8+":P"+(i-1)+"))/2");
				
				if (rs != null)
					rs.close();
				if (db != null)
					db.shutDown();
				if (i == 8)
				{
					throw new Exception("Khong co bao cao trong thoi gian nay...");
				}
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
}
