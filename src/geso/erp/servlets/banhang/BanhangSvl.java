package geso.erp.servlets.banhang;

import geso.erp.beans.banhang.IBanhang;
import geso.erp.beans.banhang.IBanhangList;
import geso.erp.beans.banhang.imp.Banhang;
import geso.erp.beans.banhang.imp.BanhangList;
import geso.erp.db.sql.dbutils;
import geso.dms.center.util.Utility;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class BanhangSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public BanhangSvl() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IBanhangList obj;
		request.setCharacterEncoding("UTF-8");
		
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    	    
	    HttpSession session = request.getSession();	    

	    Utility util = new Utility();
	    	    
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String action = util.getAction(querystring);
	    
	    
	    String dmhId = util.getId(querystring);
	    
	    String msg = "";
	    if (action.equals("delete"))
	    {	
	    	IBanhang bh=new Banhang(dmhId);
	    	bh.Delete_dh();
	    }
	    else
	    { 
	    		if(action.equals("chot"))
		    	{
		    		dbutils db = new dbutils();
		    		db.update(" update donhang set trangthai = '1' where pk_seq = '" + dmhId + "'");
		    		db.shutDown();
		    	}
	    		
	    	 
	    }
	    String task=request.getParameter("action");
	    
	    if(task!=null && task.equals("taomoi")){
	    	IBanhang dmhBean = new Banhang("");
	    	dmhBean.setUserId(userId);
	    	dmhBean.createRs();
    		 
	    	session.setAttribute("dmhBean", dmhBean);
    		String nextJSP = "/TrainingGESO/pages/Erp/BanhangNew.jsp";
    		response.sendRedirect(nextJSP);
	    	return;
	    }

	    obj = new BanhangList();
	    obj.setUserId(userId);
	    
	    if(msg.length() > 0) obj.setmsg(msg);
	    
	    obj.init("");
	     
	    
		session.setAttribute("obj", obj);
				
		String nextJSP = "/TrainingGESO/pages/Erp/BanhangList.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IBanhangList obj;
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    
	    String action = request.getParameter("action");
	    if (action == null){
	    	action = "";
	    }
	    
	    Utility util = new Utility();
	    
		HttpSession session = request.getSession();
	    String userId = util.antiSQLInspection(request.getParameter("userId")); 
	    
	    if(action.equals("Tao moi"))
	    {
	    	IBanhang dmhBean = new Banhang("");
	    	dmhBean.setUserId(userId);
	    	dmhBean.createRs();
    		 
	    	session.setAttribute("dmhBean", dmhBean);
    		String nextJSP = "/TrainingGESO/pages/Erp/BanhangNew.jsp";
    		response.sendRedirect(nextJSP);
	    }
	     
	    	else if(action.equals("print")) 
	    	{ 
	    		Create_PO_PDF(response,request);
	    	}else{
	    		obj = new BanhangList();
	    		obj.setUserId(userId);
	    		
		    	
	    		obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));

	    		String search = getSearchQuery(request, obj);
		    	
		    	obj.init(search);

		    	session.setAttribute("obj", obj);
		    	response.sendRedirect("/TrainingGESO/pages/Erp/BanhangList.jsp");	
	    	}
	
	     
	    
	}

	private void Create_PO_PDF(HttpServletResponse response, HttpServletRequest request) 
	{
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition"," inline; filename=DonHang.pdf");
		
		float CONVERT = 28.346457f;//1 cm 
		float PAGE_LEFT = 2.0f*CONVERT, PAGE_RIGHT = 1.5f*CONVERT, PAGE_TOP = 0.5f*CONVERT, PAGE_BOTTOM = 0.0f*CONVERT; //cm
		Document document = new Document(PageSize.A4, PAGE_LEFT, PAGE_RIGHT, PAGE_TOP, PAGE_BOTTOM);
		ServletOutputStream outstream;
		try 
		{
			 outstream = response.getOutputStream();
			 dbutils db = new dbutils();
			 
			this.CreatePO_Training(document, outstream, response, request, db);
			db.shutDown();
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			System.out.println("___Exception PO Print: " + e.getMessage());
		}
	}
	
	private void CreatePO_Training(Document document, ServletOutputStream outstream, HttpServletResponse response, HttpServletRequest request, dbutils db) 
	{
		HttpSession session = request.getSession();
		try
		{
			
			
			NumberFormat formatter_2sole = new DecimalFormat("#,###,###.###");
			NumberFormat formatter = new DecimalFormat("#,###,###");
			
			PdfWriter.getInstance(document, outstream);
			document.open();
			//document.setPageSize(new Rectangle(210.0f, 297.0f));

			float CONVERT = 28.346457f; // = 1cm
			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			 // khỏi tạo font chữ 
			Font font10 = new Font(bf, 10, Font.NORMAL);
			 
			Font font11_bold= new Font(bf, 11, Font.BOLD);
			
			
			PdfPTable tbl_header = new PdfPTable(1);
			
			tbl_header.setWidthPercentage(100);
			tbl_header.setHorizontalAlignment(Element.ALIGN_LEFT);
			float[] crtbl_header = {7f};
			tbl_header.setWidths(crtbl_header);
			
			PdfPCell cell = new PdfPCell(new Paragraph("CHƯA HÀNG SIM CAT DIEN THOAI HA ", font11_bold));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			tbl_header.addCell(cell);
			
			  cell = new PdfPCell(new Paragraph("Địa chỉ:  ", font11_bold));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			tbl_header.addCell(cell);
			 cell = new PdfPCell(new Paragraph("Điện thoại:  ", font11_bold));
				cell.setPaddingLeft(5.0f);
				cell.setBorder(0);
				tbl_header.addCell(cell);
			
			 
			document.add(tbl_header);

			String tungay = request.getParameter("tungay");
			if(tungay == null)
				tungay = "";
			 
			
			String denngay = request.getParameter("denngay");
			if(denngay == null)
				denngay = "";
			
			Paragraph pxk = new Paragraph("BÁO CÁO ĐƠN BÁN HÀNG TRONG KỲ", new Font(bf, 14, Font.BOLD));
			
			pxk.setSpacingAfter(2);
			pxk.setAlignment(Element.ALIGN_CENTER);
			 
			document.add(pxk);
			
			
			pxk = new Paragraph("Từ ngày: "+tungay +" .Đến ngày:"+denngay, new Font(bf, 12, Font.BOLD));
			
			pxk.setSpacingAfter(2);
			pxk.setAlignment(Element.ALIGN_CENTER);
			 
			document.add(pxk);
			
			
			  
			float[] crSanpham = {0.7f * CONVERT, 2.0f * CONVERT,2.0f * CONVERT, 2.9f * CONVERT, 1.8f * CONVERT, 2.0f * CONVERT }; 
 
			PdfPTable tbSanPham = new PdfPTable(crSanpham.length);
			tbSanPham.setWidthPercentage(100);
			tbSanPham.setHorizontalAlignment(Element.ALIGN_LEFT);			
			tbSanPham.setWidths(crSanpham);
			tbSanPham.getDefaultCell().setBorder(0);
			tbSanPham.setSpacingAfter(8.0f);
			
			String[] spTitles = {"SỐ TT","SỐ CT", "MÃ KH","TÊN KH","NGÀY ĐƠN HÀNG",  "Thành tiền"};
			
			for(int z = 0; z < spTitles.length; z++) {
				cell = new PdfPCell(new Paragraph(spTitles[z], font11_bold));
				cell.setPadding(3.0f);
				cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				tbSanPham.addCell(cell);
			}
	 
			
			 
			
			 
			  
			String query =   "";
			
			 
		 
			
		 	
			query=   " SELECT DH.PK_SEQ AS SOCHUNGTU,DH.NGAYCHUNGTU,DH.TRANGTHAI,DH.NGAYTAO, " +
			" TONGTIENtruocVAT as tongtien, KH.MA, ISNULL(KH.TEN,'') AS TEN,NV.TEN AS NGUOITAO "+
			" FROM DONHANG DH "+
			" LEFT JOIN KHACHHANG KH ON KH.PK_SEQ=DH.KHACHHANG_FK "+
			" LEFT JOIN NHANVIEN NV ON NV.PK_SEQ=DH.NGUOITAO where 1=1 ";
			
			if(tungay.length() > 0)
				query += " and dh.ngaychungtu >= '" + tungay + "'";
			
			if(denngay.length() > 0)
				query += " and dh.ngaychungtu <= '" + denngay + "'";
			 
			
			ResultSet rs=db.get(query); 
			
 			int  sott=1;
 			double tongtien=0;
			while(rs.next()){
				
				
				String[] spTitles2 = {""+sott, rs.getString("SOCHUNGTU"),rs.getString("MA"),rs.getString("TEN") 
						,rs.getString("NGAYCHUNGTU")
						, formatter.format(rs.getDouble("tongtien"))} ;
				
				tongtien =tongtien+ Math.round(rs.getDouble("tongtien"));
				
				for(int z = 0; z < spTitles2.length; z++) {
					cell = new PdfPCell(new Paragraph(spTitles2[z], font10));
					cell.setPadding(3.0f);
					if(z==4||z==5||z==7){
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);	
					}else{
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					}
					tbSanPham.addCell(cell);
				}
				sott++ ;
			}
			
			  
			  
			  
			
			 
				cell = new PdfPCell(new Paragraph("Tổng cộng", font11_bold));
				cell.setColspan(5); 
				cell.setPadding(3.0f);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				tbSanPham.addCell(cell);
			
				cell = new PdfPCell(new Paragraph(formatter.format(tongtien), font11_bold));
				cell.setPadding(3.0f);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				tbSanPham.addCell(cell);
				
				
				
				
			
				document.add(tbSanPham);
			
			 
			document.close(); 
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception In PDF: " + e.getMessage());
		}
		
	}
		
 
	
	private String getSearchQuery(HttpServletRequest request, IBanhangList obj)
	{
		 
		String tungay = request.getParameter("tungay");
		if(tungay == null)
			tungay = "";
		obj.setTungay(tungay);
		
		String denngay = request.getParameter("denngay");
		if(denngay == null)
			denngay = "";
		obj.setDenngay(denngay);
		
		String khachangid = request.getParameter("khachangid");
		if(khachangid == null)
			khachangid = "";
		obj.setKh(khachangid);
		 
		
		
		String sodonmuahang = request.getParameter("sochungtu");
		if(sodonmuahang == null)
			sodonmuahang = "";
		obj.setSochungtu(sodonmuahang);
		 
	 
		String trangthai = request.getParameter("trangthai");
		if(trangthai == null)
			trangthai = "";
		obj.setTrangthai(trangthai);
		
		 
		String query =   "";
		
		 
	 
		
	 	
		query=   " SELECT DH.PK_SEQ AS SOCHUNGTU,DH.NGAYCHUNGTU,DH.TRANGTHAI,DH.NGAYTAO, " +
		" TONGTIENtruocVAT as tongtien, ISNULL(KH.TEN,'') AS TEN,NV.TEN AS NGUOITAO "+
		" FROM DONHANG DH "+
		" LEFT JOIN KHACHHANG KH ON KH.PK_SEQ=DH.KHACHHANG_FK "+
		" LEFT JOIN NHANVIEN NV ON NV.PK_SEQ=DH.NGUOITAO where  dh.shop_fk = (select shop_fk from nhanvien where pk_seq="+obj.getUserId()+")";
		
		if(tungay.length() > 0)
			query += " and dh.ngaychungtu >= '" + tungay + "'";
		
		if(denngay.length() > 0)
			query += " and dh.ngaychungtu <= '" + denngay + "'";
		if(trangthai.length() >0){
			query += " and dh.trangthai = '" + trangthai + "'";
		}
		if(khachangid.length() >0){
			query += " and dh.khachhang_fk  IN (SELECT PK_SEQ FROM KHACHHANG WHERE TIMKIEM LIKE '%"+khachangid+"%' " +
					 " AND SHOP_FK=(SELECT SHOP_FK FROM NHANVIEN WHERE PK_sEQ="+obj.getUserId()+" ) ) ";
		}
		
		if(sodonmuahang.length() >0){
			query += " and cast(dh.pk_seq as varchar(10)) like '%" +sodonmuahang+"%'";
		}
		
	 
		 		System.out.println("cau search "+query);

		return query;
	}

	 
}
