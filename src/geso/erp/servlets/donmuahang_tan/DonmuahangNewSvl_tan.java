package geso.erp.servlets.donmuahang_tan;

import java.io.IOException;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

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
import geso.erp.beans.doctien.DocTien;
import geso.erp.beans.donmuahang_tan.*;
import geso.erp.beans.donmuahang_tan.imp.*;
import geso.erp.db.sql.dbutils;

/**
 * Servlet implementation class DonmuahangNewSvl_tan
 */
@WebServlet("/DonmuahangNewSvl_tan")
public class DonmuahangNewSvl_tan extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonmuahangNewSvl_tan() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// dùng để lấy dữ liệu
		request.setCharacterEncoding("UTF-8");
		
		// dung để điều hướng 
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    
	    HttpSession session = request.getSession();	 
	    Utility util = new Utility();
	    
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String action = util.getAction(querystring);
	    System.out.println("-------->action is strong :  " + action);
	    String Id = util.getId(querystring);
	    
	    String msg = "";
	    
	    IDonmuahangNew_tan bhn=new DonmuahangNew_tan("");
	    bhn.setUserId(userId); // phai co UserId truoc khi Init
	    
	    bhn.setId(Id);
		bhn.init();
		bhn.CreateRs();
		//Ham load gia tri can update
		if(querystring.indexOf("update")>=0){
			String id = request.getParameter("update");
			System.out.println("-------->i'm strong :  " + id);
		    bhn.init();
		}else if(querystring.indexOf("display")>=0){
			String id = request.getParameter("display");
			System.out.println("-------->đại khái:  " + id);
			bhn.Display_dmh();
		    bhn.init();
		}

		session.setAttribute("obj", bhn);
		String nextJSP = "/TrainingGESO/pages/Erp/DonmuahangNew_tan.jsp";
		response.sendRedirect(nextJSP);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		IDonmuahangNew_tan obj;
		
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		//String userId = util.antiSQLInspection(request.getParameter("userId"));
		String userTen = (String) session.getAttribute("userTen");
		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		 
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		session.setMaxInactiveInterval(30000);
	    	    
	    Utility util = new Utility();
	    
	    // trường hợp tạo mới ID sẽ không có ,bằng khoảng trắng
	    String id = util.antiSQLInspection(request.getParameter("id_dh"));
	    System.out.println("ID" + id);
	     
	    if (id == null)
		{
			id="";
			obj = new DonmuahangNew_tan("");
		}
		else
		{
			obj = new DonmuahangNew_tan(id);
		} 
	    obj.setUserId(userId);
	    
	    String ngaychungtu = util.antiSQLInspection(request.getParameter("txtNgaychungtu")); 
	    obj.setNgaychungtu(ngaychungtu==null?"0":ngaychungtu);
	     
	     
	    String nhacungcap = util.antiSQLInspection(request.getParameter("cbNhacungcap")); 
	    obj.setNhacungcap(nhacungcap==null?"":nhacungcap);
	     
	     
	    double tongtien=0;
			try{
				tongtien = Double.parseDouble(request.getParameter("txtTongtien").replaceAll(",",""));
			}catch(Exception er){}
			obj.setTongtien(tongtien);
			
			
	      String[] masp=request.getParameterValues("txtMasp");
	      String[] idsp=request.getParameterValues("idsp");
	      
	      String[] tensp=request.getParameterValues("txtTensp");
	      String[] donvitinh=request.getParameterValues("txtDvt");
	      String[] dongia=request.getParameterValues("txtDongia");
	      String[] soluong=request.getParameterValues("txtSoluong");
	      String[] thanhtien=request.getParameterValues("txtThanhtien");
	      

	      String action = request.getParameter("action");
	      if (action == null){
	    	  action = "";
	    	  
	      }	      
	      // lấy được 1 mảng mã sản phẩm
	      List<ISanpham_tan> listsp=new ArrayList<ISanpham_tan>();	      
	      if(masp!=null){
	 	      for(int i=0;i <masp.length;i++){
		    	  if(masp[i].length() >0){
		    		  ISanpham_tan sp=new Sanpham_tan();
		    		  sp.setMasanpham(masp[i]);
		    		  sp.setId(idsp[i]);
		    		  sp.setTensanpham(tensp[i]);
		    		  sp.setDonvitinh(donvitinh[i]);
		    		  double sl=0;
		    		  try{
		    			  sl=Double.parseDouble(soluong[i].replaceAll(",", ""));
		    			  
		    		  }catch(Exception er){
		    			  
		    		  }
		    		  sp.setSoluong(sl);
		    		  
		    		  double dg=0;
		    		  try{
		    			  dg=Double.parseDouble(dongia[i].replaceAll(",", ""));
		    			  
		    		  }catch(Exception er){
		    			  
		    		  }
		    		  sp.setDongia(dg);
		    		  
		    		  sp.setThanhtien(sl*dg);
		    		  listsp.add(sp);
		    		  
		    	  }
		      }
	      }
	      obj.setListSp(listsp);
	      
	     
	     System.out.println("actiong: "+action);
	     if(action.equals("save")) 
	     { 
	    	if(!obj.save()){
	    	// lưu không thành công
	    		 
	    		obj.CreateRs();
	    		obj.setIsCapNhat("1");
	    		session.setAttribute("obj", obj);
			    response.sendRedirect("/TrainingGESO/pages/Erp/DonmuahangNew_tan.jsp");
	    	}else{
	    		IDonmuahangList_tan dh=new DonmuahangList_tan();
	    	    dh.init(""); 
	    	    // khởi tạo ra 1 session
	    	   // tất cả mọi chỗ gọi trang DonbanhangList.jsp  thì cái  tên đối tượng, và đối tượng truyền vào phải giống nhau
	    	    obj.setIsCapNhat("0");
	    	    session.setAttribute("obj", dh);
	    	    String nextJSP = "/TrainingGESO/pages/Erp/DonmuahangList_tan.jsp";
	    		response.sendRedirect(nextJSP);
	    	}
	    		
	    }else if (action.equals("print")){
	    	Create_PO_PDF(response,request);
	    }else{
	       	obj.CreateRs();
	    	session.setAttribute("obj", obj);
		   	response.sendRedirect("/TrainingGESO/pages/Erp/DonmuahangNew_tan.jsp");	
	    }
		
	}
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
			PdfPTable tbl_header = new PdfPTable(1);
			
			tbl_header.setWidthPercentage(100);
			tbl_header.setHorizontalAlignment(Element.ALIGN_LEFT);	//định vị trí
			float[] crtbl_header = {7f};
			tbl_header.setWidths(crtbl_header);
			//-- > Paragraph ---
			PdfPCell cell = new PdfPCell(new Paragraph("CT TNHH GIAI PHAP DOANH NGHIEP TOAN CAU, 441/19 NGUYEN BINH KHIEM P.1 Q.GO VAP, HCM ", font11_bold));
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
			
			Paragraph pxk = new Paragraph("BÁO CÁO ĐƠN MUA HÀNG TRONG KỲ", new Font(bf, 14, Font.BOLD));
			
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
			
			String[] spTitles = {"SỐ TT","SỐ CT", "MÃ NCC","TÊN NCC", "NGÀY ĐƠN HÀNG",  "Thành tiền"};
			
			for(int z = 0; z < spTitles.length; z++) {
				cell = new PdfPCell(new Paragraph(spTitles[z], font11_bold));
				cell.setPadding(3.0f);
				cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				tbSanPham.addCell(cell);
			}
	 
			
			 
			
			 
			  
			String query =   "";
			
			 
		 
			
		 	
			query=   " SELECT MH.PK_SEQ AS MAMH, MH.NGAYCHUNGTU, MH.TRANGTHAI, MH.NGAYTAO, " +
			" TONGTIEN, NCC.MA, NCC.TEN AS TEN_NCC, NV.TEN AS NGUOITAO "+
			" FROM MUAHANG MH "+
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
}
