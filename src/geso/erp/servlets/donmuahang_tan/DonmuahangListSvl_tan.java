package geso.erp.servlets.donmuahang_tan;

import java.io.IOException;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import java.awt.Toolkit;
import com.itextpdf.text.Image;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
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

import geso.dms.center.util.Utility;
import geso.erp.beans.donmuahang_tan.*;
import geso.erp.beans.donmuahang_tan.imp.*;
import geso.erp.db.sql.dbutils;


/**
 * Servlet implementation class DonmuahangListSvl_tan
 */
@WebServlet("/DonmuahangListSvl_tan")
public class DonmuahangListSvl_tan extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonmuahangListSvl_tan() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		// dùng để điều hướng 
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    HttpSession session = request.getSession();	 
	    Utility util = new Utility();
	    
	    //-->lấy giá giá trị iduser
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    //-->end
	    
	    String msg = "";    
	    IDonmuahangList_tan dmh=new DonmuahangList_tan();
	    
	    /*-----------*/
		// khởi tạo 1 session
		// lấy action (hành động truyền vào, và id truyền vào)
		String action = util.getAction(querystring); // lấy hành động truyền vào
		String action_getbangparameter= request.getParameter("update"); // hành động truyền vào là update
		
		System.out.println("action_getbangparameter : "+action_getbangparameter);
		String dmhId = util.getId(querystring);	//lấy cái id truyền vào
		
		if (action.equals("delete"))
		{
			dmh.setId(dmhId);
			dmh.Delete_dh();
			dmh.init("");
			
		}else if(action.equals("chot")){
			dbutils db = new dbutils();
			db.update(" update muahang set trangthai = '1' where pk_seq = '" + dmhId + "'");
			db.shutDown();
			
		}
		dmh.init("");
	 	session.setAttribute("obj", dmh);
	    String nextJSP = "/TrainingGESO/pages/Erp/DonmuahangList_tan.jsp";
		response.sendRedirect(nextJSP);
			/*------------*/
	    
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		IDonmuahangList_tan obj;
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    
	    // kiểm tra action  
	    String action = request.getParameter("action");
	    if (action == null){
	    	action = "";
	    }
	    obj = new DonmuahangList_tan();
	    System.out.println("action gi day : "+action);
	    
	    //--- begin: Lấy giá trị nhập vào từ giao diện 
	    String mamh= request.getParameter("txtMaMH");
	    if (mamh == null){
	    	mamh = "";
	    }
	    obj.setMamuahang(mamh);
	    System.out.println(mamh);
	    
	    String nhacungcap= request.getParameter("cbNhacungcap");
	    if (nhacungcap == null){
	    	nhacungcap = "";
	    }
	    obj.setNhacungcap(nhacungcap);
	    System.out.println(nhacungcap);
	    
	    String trangthai = request.getParameter("cbTrangthai");
	    if(trangthai==null){
	    	trangthai="";
	    }
	    obj.setTrangthai(trangthai);
	    //---finish.
	    HttpSession session = request.getSession();	 
	    Utility util = new Utility();
	    //xét hành động truyền vào
	    if(action.equals("timkiem")){
		   obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
		   
		   String search = getSearchQuery(request, obj);		   
		   obj.init(search);
		   session.setAttribute("obj", obj);
		   response.sendRedirect("/TrainingGESO/pages/Erp/DonmuahangList_tan.jsp");
	    	
	    }else if(action.equals("taomoi")){
	    	IDonmuahangNew_tan dmh=new DonmuahangNew_tan("");
	    	session.setAttribute("obj", dmh);
	    	dmh.CreateRs();
	    	String nextJSP = "/TrainingGESO/pages/Erp/DonmuahangNew_tan.jsp";
	    	response.sendRedirect(nextJSP);
	   }
	   else if(action.equals("refresh")){
		   IDonmuahangList_tan dmh=new DonmuahangList_tan();
		   dmh.init("");
		   // khởi tạo ra 1 session
		    
		   session.setAttribute("obj", dmh);
		   String nextJSP = "/TrainingGESO/pages/Erp/DonmuahangList_tan.jsp";
		   response.sendRedirect(nextJSP);
	   }
	   else if(action.equals("print")){
		   Create_PO_PDF(response,request);
	   }
	   
	}
	//------ In báo cáo -------
	private void Create_PO_PDF(HttpServletResponse response, HttpServletRequest request){
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition"," inline; filename=TEST_HOADONMUAHANG.pdf");
		
		float CONVERT = 28.346457f;//1 cm 
		float PAGE_LEFT = 2.0f*CONVERT, PAGE_RIGHT = 1.5f*CONVERT, PAGE_TOP = 0.5f*CONVERT, PAGE_BOTTOM = 0.0f*CONVERT; //cm
		Document document = new Document(PageSize.A4, PAGE_LEFT, PAGE_RIGHT, PAGE_TOP, PAGE_BOTTOM);
		ServletOutputStream outstream;
		try{
			 outstream = response.getOutputStream();
			 dbutils db = new dbutils();
			 
			this.CreatePO_Training(document, outstream, response, request, db);
			db.shutDown();
			
		}catch (IOException e){
			e.printStackTrace();
			System.out.println("___Exception PO Print: " + e.getMessage());
		}
	}
	private void CreatePO_Training(Document document, ServletOutputStream outstream, 
			HttpServletResponse response, HttpServletRequest request, dbutils db){
		//---------------
		try{
			NumberFormat formatter_2sole = new DecimalFormat("#,###,###.###");
			NumberFormat formatter = new DecimalFormat("#,###,###");
			
			// khai báo trình viết pdf
			PdfWriter.getInstance(document, outstream);
			//mở documment
			document.open();

			float CONVERT = 28.346457f; // = 1cm
			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			// khỏi tạo font chữ 
			Font font10 = new Font(bf, 10, Font.NORMAL);
			Font font11_bold= new Font(bf, 11, Font.BOLD);
			PdfPTable tbl_header = new PdfPTable(3);
			
			tbl_header.setWidthPercentage(100);
			tbl_header.setTotalWidth(100);
			tbl_header.setHorizontalAlignment(Element.ALIGN_LEFT);	//định vị trí
			float[] crtbl_header = {7f, 3f, 4f};
			tbl_header.setWidths(crtbl_header);
			
			//-- > Chèn giá trị vào các cột trong bảng tbl_header ---
			PdfPCell cell = new PdfPCell(new Paragraph("CT TNHH GIAI PHAP DOANH NGHIEP TOAN CAU, 441/19 NGUYEN BINH KHIEM P.1 Q.GO VAP, HCM \n (+84) 909 865 115 ", font11_bold));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_header.addCell(cell);
			
			cell.addElement(new Paragraph(""));
			cell.setBorder(0);
			tbl_header.addCell(cell);
			
			com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance(getServletContext().getInitParameter("ImagePath") + "\\logo.png");
			cell.addElement(img);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setBorder(0);
			tbl_header.addCell(cell);
			// Thêm bảng tbl_header vào file document
			document.add(tbl_header);
			
			
			String tungay = request.getParameter("tungay");
			if(tungay == null)
				tungay = "";
			String denngay = request.getParameter("denngay");
			if(denngay == null)
				denngay = "";
			
			Paragraph pxk = new Paragraph("BÁO CÁO ĐƠN MUA HÀNG TRONG KỲ", new Font(bf, 14, Font.BOLD));
			pxk.setSpacingAfter(2);
			pxk.setAlignment(Element.ALIGN_CENTER);
			document.add(pxk);
						
			pxk = new Paragraph("Từ ngày: "+tungay +".          Đến ngày: "+denngay+"\n\n", new Font(bf, 12, Font.BOLD));
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
			
			String[] spTitles = {"SỐ TT","SỐ CT", "MÃ NCC","TÊN NCC", "NGÀY ĐƠN HÀNG",  "Thành tiền"};
			
			for(int z = 0; z < spTitles.length; z++) {
				cell = new PdfPCell(new Paragraph(spTitles[z], font11_bold));
				cell.setPadding(3.0f);
				cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				tbSanPham.addCell(cell);
			}

			String query = "";
			query="SELECT MH.PK_SEQ AS MAMH, MH.NGAYCHUNGTU, MH.TRANGTHAI, MH.NGAYTAO, " +
					" TONGTIEN, NCC.MA, NCC.TEN AS TEN_NCC, NV.TEN AS NGUOITAO "+
				  "FROM MUAHANG MH "+
					" LEFT JOIN NHACUNGCAP NCC ON NCC.PK_SEQ=MH.NHACUNGCAP_FK "+
					" LEFT JOIN NHANVIEN NV ON NV.PK_SEQ=MH.NGUOITAO where 1=1 ";
			
			if(tungay.length() > 0)
				query += " and mh.ngaychungtu >= '" + tungay + "'";
			
			if(denngay.length() > 0)
				query += " and mh.ngaychungtu <= '" + denngay + "'";
			ResultSet rs=db.get(query); 
			
 			int  sott=1;
 			double tongtien=0;
			while(rs.next()){
				String[] spTitles2 = {""+sott, rs.getString("MAMH"),rs.getString("MA"), rs.getString("TEN_NCC"), 
									rs.getString("NGAYCHUNGTU"), formatter.format(rs.getDouble("tongtien"))} ;				
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
	//-------------------------

	private String getSearchQuery(HttpServletRequest request, IDonmuahangList_tan obj) {
		Utility util=new Utility();
		
		String query = "";
		query = "SELECT MH.PK_SEQ AS MAMH, MH.NGAYCHUNGTU, MH.NHACUNGCAP_FK AS NHACUNGCAP, NCC.TEN AS TEN,"
				+ " ISNULL(MH.TRANGTHAI,'') AS TRANGTHAI, MH.TONGTIEN, MH.NGAYTAO, MH.NGUOITAO "
				+ "FROM MUAHANG MH LEFT JOIN NHANVIEN NV ON MH.NGUOITAO = NV.PK_SEQ  "
				+ "LEFT JOIN NHACUNGCAP NCC ON NCC.PK_SEQ = MH.NHACUNGCAP_FK "
				+ "WHERE 1=1";
		if(obj.getMamuahang().length()>0){
			query +=" AND cast(MH.PK_SEQ as varchar(10)) like '"+obj.getMamuahang().toString()+"%'";
		}
		if(obj.getNhacungcap().length()>0){
			query+=" AND MH.NHACUNGCAP_FK = "+obj.getNhacungcap()+"";
		}
		 
		if(obj.getTrangthai().length()>0){
			query+=" AND MH.TRANGTHAI LIKE '"+obj.getTrangthai()+"'";
		}
		return query;
		
	}
}
