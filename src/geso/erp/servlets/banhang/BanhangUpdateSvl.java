package geso.erp.servlets.banhang;

import geso.erp.db.sql.dbutils;
import geso.dms.distributor.util.Utility;
import geso.erp.beans.banhang.IBanhang;
import geso.erp.beans.banhang.ISanpham;
import geso.erp.beans.banhang.imp.Banhang;
import geso.erp.beans.banhang.imp.Sanpham;
import geso.erp.beans.doctien.DocTien;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class BanhangUpdateSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public BanhangUpdateSvl()
	{
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
			String nextJSP;
			HttpSession session = request.getSession();
			String userId = (String) session.getAttribute("userId");
			String userTen = (String) session.getAttribute("userTen");
			String sum = (String) session.getAttribute("sum");
			geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
	 		session.setMaxInactiveInterval(30000);

			Utility util = new Utility();
			String querystring = request.getQueryString();
			userId = util.getUserId(querystring);

			if (userId.length() == 0)
				userId = util.antiSQLInspection(request.getParameter("userId"));
			
			String id = util.getId(querystring);
			String action=util.getAction(querystring);
			
			IBanhang dmhBean = new Banhang(id);
			
			dmhBean.setUserId(userId); // phai co UserId truoc khi Init
			
			String task = request.getParameter("task");
			if(task == null)
				task = "";
			
			 
			
			if(task.equals("print"))
			{
				Create_PO_PDF(response, request,id);
			}
			else
			{
				dmhBean.init();
				dmhBean.createRs();
		  		dmhBean.setchungtutimkiem(dmhBean.getSochungtu());
		  		System.out.println("task: "+task);
		  		if(action.equals("update")){
		  			dmhBean.setIsCapNhat("1");
		  		}else {
		  			dmhBean.setIsCapNhat("0");
		  		}
				session.setAttribute("dmhBean", dmhBean);
				nextJSP = "/TrainingGESO/pages/Erp/BanhangNew.jsp";
				response.sendRedirect(nextJSP);
			}
		//}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userTen = (String) session.getAttribute("userTen");
		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		 
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			session.setMaxInactiveInterval(30000);
			
			IBanhang dmhBean;
			Utility util = new Utility();
			String id = util.antiSQLInspection(request.getParameter("id_dh"));
			System.out.println("ID" + id);
			if (id == null)
			{
				id="";
				dmhBean = new Banhang("");
			}
			else
			{
				dmhBean = new Banhang(id);
			} 
			dmhBean.setUserId(userId);
			
			 
			String ngaygd = util.antiSQLInspection(request.getParameter("ngaymuahang"));
			if (ngaygd == null || ngaygd == "")
				ngaygd = this.getDateTime();
			dmhBean.setNgaymuahang(ngaygd);
			
			String ghichu = util.antiSQLInspection(request.getParameter("ghichu"));
			if (ghichu == null || ngaygd == "")
				ghichu="";
			dmhBean.setGhiChu(ghichu);
			
			
			 	
			String khachangid = util.antiSQLInspection(request.getParameter("khachangid"));
			if (khachangid == null)
				khachangid = "";
			
			dmhBean.setKhId(khachangid);

			
			String tenkh = util.antiSQLInspection(request.getParameter("tenkh"));
			if (tenkh == null)
				tenkh = "";
			dmhBean.setKhten(tenkh);
			
			String makh = util.antiSQLInspection(request.getParameter("makh"));
			if (makh == null)
				makh = "";
			
			dmhBean.setKhMa(makh);
			
			String diachikh = util.antiSQLInspection(request.getParameter("diachikh"));
			if (diachikh == null)
				diachikh = "";
			
			dmhBean.setKhDiachi(diachikh);
			
			String dienthoaikh = util.antiSQLInspection(request.getParameter("dienthoaikh"));
			if (dienthoaikh == null)
				dienthoaikh = "";
			dmhBean.setKhDienThoai(dienthoaikh);
			
			String isthanhtoanhet = util.antiSQLInspection(request.getParameter("isthanhtoanhet"));
			if (isthanhtoanhet == null)
				isthanhtoanhet = "0";
			dmhBean.setIsThanhToanHet(isthanhtoanhet);
			
			double tongtientruocvat=0;
			try{
 		      tongtientruocvat = Double.parseDouble(request.getParameter("tongtientruocvat").replaceAll(",",""));
			}catch(Exception er){}
			dmhBean.setTongtienchuaVat(tongtientruocvat);
			
			
			
			double tienthanhtoan=0;
			try{
				tienthanhtoan = Double.parseDouble(request.getParameter("tienthanhtoan").replaceAll(",",""));
			}catch(Exception er){}
			dmhBean.setTienThanhToan(tienthanhtoan);
			
			    
			String[] idsp = request.getParameterValues("idsp");
			String[] masp = request.getParameterValues("masp");
			String[] tensp = request.getParameterValues("tensp");
			String[] sl = request.getParameterValues("soluong");
			String[] donvitinh = request.getParameterValues("donvitinh");
			String[] dongia = request.getParameterValues("dongia");
			String[] chietkhau = request.getParameterValues("chietkhau");
			String[] thanhtien = request.getParameterValues("thanhtien");
			
		 	String action = request.getParameter("action");
		 	
			List<ISanpham> spList = new ArrayList<ISanpham>();
			 
			
			ISanpham sp = null; String tenhd = "";
			for(int i = 0; i < masp.length; i++)
			{
				//Loại hàng hóa chi phí dịch vụ (2) -> không cần nhập mã nên chỉ cần kiểm tra tên
				if((  tensp[i].trim().length() > 0) || masp[i].trim().length() > 0)
				{
					 
					sp = new Sanpham();
					sp.setPK_SEQ(idsp[i]);
					
					sp.setMasanpham(masp[i]);
					sp.setTensanpham(tensp[i]);
					
					double soluong=0;
					try{
						soluong=Double.parseDouble(sl[i].replaceAll(",", ""));
					}catch(Exception er){}
					sp.setSoluong(soluong);
					
					sp.setDonvitinh(donvitinh[i]);
					double dongia_=0;
					try{
						dongia_=Double.parseDouble(dongia[i].replaceAll(",", ""));
					}catch(Exception er){}
					
					sp.setDongia(dongia_);
					
					double chietkhau_=0;
					try{
						chietkhau_=Double.parseDouble(chietkhau[i].replaceAll(",", ""));
					}catch(Exception er){}
					
					sp.setChietkhau(chietkhau_);
					
					double thanhtien_=0;
					try{
						thanhtien_=Double.parseDouble(thanhtien[i].replaceAll(",", ""));
					}catch(Exception er){}
					
					sp.setThanhtien(thanhtien_);
					 
					//Tùy chọn tên sản phẩm in hóa đơn
					spList.add(sp);
				}
			}
			dmhBean.setSpList(spList);
			
			
			if (action.equals("save") )
			{
				dmhBean.setTrangthai("0");
				if (id.length() == 0) // tao moi
				{
					  	if(!dmhBean.createDmh()){
							dmhBean.createRs();
							dmhBean.setIsCapNhat("1");
							session.setAttribute("dmhBean", dmhBean);
							String nextJSP = "/TrainingGESO/pages/Erp/BanhangNew.jsp";
							response.sendRedirect(nextJSP);
							return;
					  	}else{
					  		
					  		dmhBean.createRs();
					  		dmhBean.setchungtutimkiem(dmhBean.getSochungtu());
					  		
							dmhBean.setIsCapNhat("0");
							session.setAttribute("dmhBean", dmhBean);
							String nextJSP = "/TrainingGESO/pages/Erp/BanhangNew.jsp";
							response.sendRedirect(nextJSP);
							return;
					  	}
					 
					 
				}
				else
				{
					if (!dmhBean.updateDmh())
					{
						dmhBean.createRs();
						dmhBean.setIsCapNhat("1");
						session.setAttribute("dmhBean", dmhBean);
						String nextJSP = "/TrainingGESO/pages/Erp/BanhangNew.jsp";
						response.sendRedirect(nextJSP);
						return;
					}else{
						dmhBean.createRs();
				  		dmhBean.setchungtutimkiem(dmhBean.getSochungtu());
						dmhBean.setIsCapNhat("0");
						session.setAttribute("dmhBean", dmhBean);
						String nextJSP = "/TrainingGESO/pages/Erp/BanhangNew.jsp";
						response.sendRedirect(nextJSP);
						return;
					}
					 
				}
			}else if(action.equals("themmoi")){
				dmhBean = new Banhang("");
		    	dmhBean.setUserId(userId);
		    	dmhBean.setIsCapNhat("1");
		    	dmhBean.setIsThanhToanHet("1");
		    	dmhBean.createRs();
		    	session.setAttribute("dmhBean", dmhBean);
	    		String nextJSP = "/TrainingGESO/pages/Erp/BanhangNew.jsp";
	    		response.sendRedirect(nextJSP);
	    		
			}else if(action.equals("quaylaiInit")){
					dmhBean = new Banhang(id);
			    	dmhBean.setUserId(userId);
			    	dmhBean.init();
			    	dmhBean.setIsCapNhat("0");
			    	dmhBean.createRs();
			    	session.setAttribute("dmhBean", dmhBean);
		    		String nextJSP = "/TrainingGESO/pages/Erp/BanhangNew.jsp";
		    		response.sendRedirect(nextJSP);
				
			}else if(action.equals("chinhsua")){
				dmhBean = new Banhang(id);
		    	dmhBean.setUserId(userId);
		    	dmhBean.init();
		    	dmhBean.setIsCapNhat("1");
		    	dmhBean.createRs();
		    	session.setAttribute("dmhBean", dmhBean);
	    		String nextJSP = "/TrainingGESO/pages/Erp/BanhangNew.jsp";
	    		response.sendRedirect(nextJSP);
			}else if(action.equals("Xoa_banghi")){
				dmhBean = new Banhang(id);
				
		    	dmhBean.setUserId(userId);
		    	
		    	dmhBean.Delete_dh();
		    	dmhBean.init();
		    	dmhBean.setIsCapNhat("0");
		    	dmhBean.createRs();
		    	session.setAttribute("dmhBean", dmhBean);
	    		String nextJSP = "/TrainingGESO/pages/Erp/BanhangNew.jsp";
	    		response.sendRedirect(nextJSP);
			}
			else if(action.equals("timkiem") || action.equals("VeDau") || action.equals("VeCuoi") || action.equals("DichuyenToi") || action.equals("DichuyenLui")   ){
				
				String chungtutimkiem= request.getParameter("chungtutimkiem");
				if(chungtutimkiem==null){
					chungtutimkiem="";
				}
				
				
				dmhBean = new Banhang(id);
				dmhBean.setUserId(userId);
				dmhBean.setchungtutimkiem(chungtutimkiem);
				dmhBean.Init_getIdToMove(action);
		    	dmhBean.init();
		    	dmhBean.setIsCapNhat("0");
		    	dmhBean.createRs();
		    	session.setAttribute("dmhBean", dmhBean);
	    		String nextJSP = "/TrainingGESO/pages/Erp/BanhangNew.jsp";
	    		response.sendRedirect(nextJSP);
			}else if(action.equals("savekhachhang")){
				// tao moi khach hang 
				dmhBean.saveKhachhang();
				String Idkh=dmhBean.getKhId();
				dmhBean =new Banhang("");
				dmhBean.setUserId(userId);
				dmhBean.setKhId(Idkh);
				dmhBean.createRs();
				dmhBean.setIsCapNhat("1");
				session.setAttribute("dmhBean", dmhBean);
				String nextJSP = "/TrainingGESO/pages/Erp/BanhangNew.jsp";
				response.sendRedirect(nextJSP);
				return;
				
			}
			 
		
	}
	
	private void Create_PO_PDF(HttpServletResponse response, HttpServletRequest request,String id) 
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
			 
			this.CreatePO_Training(document, outstream, response, request, db,id);
			db.shutDown();
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			System.out.println("___Exception PO Print: " + e.getMessage());
		}
	}
	
	private void CreatePO_Training(Document document, ServletOutputStream outstream, HttpServletResponse response, HttpServletRequest request, dbutils db,String id) 
	{
		HttpSession session = request.getSession();
		try
		{
			IBanhang obj=new Banhang(id);
			obj.init();
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
			
			PdfPCell cell = new PdfPCell(new Paragraph("CONG TY GIAI PHAP DOANH NGHIEP TOAN CAU ", font11_bold));
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

			
			
			Paragraph pxk = new Paragraph("ĐƠN BÁN HÀNG", new Font(bf, 14, Font.BOLD));
			
			pxk.setSpacingAfter(2);
			pxk.setAlignment(Element.ALIGN_CENTER);
			 
			document.add(pxk);
			 
			
			// khoi tao 1 bang ,bao nhieu cot thì phai go trước
			
			PdfPTable tbl_traning = new PdfPTable(2);
			tbl_traning.setSpacingBefore(10);
			tbl_traning.setWidthPercentage(100);
			tbl_traning.setHorizontalAlignment(Element.ALIGN_LEFT);
			float[] crtbl_traning = {7f,25f};
			
			tbl_traning.setWidths(crtbl_traning);
			
			
			// đối tượng CELL 
			  cell = new PdfPCell(new Paragraph("Tên: ", font11_bold));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
		/*	cell.setBorderWidth(1);
			cell.setBorderWidthTop(1);
			cell.setBorderWidthBottom(1);*/
			tbl_traning.addCell(cell);
			
			 cell = new PdfPCell(new Paragraph(obj.getKhten(), font10));
				cell.setPaddingLeft(5.0f);
				cell.setBorder(0);
				/*cell.setBorderWidth(1);
				 * 
				cell.setBorderWidthTop(0);
				cell.setBorderWidthBottom(0);*/
				tbl_traning.addCell(cell);
				
				  cell = new PdfPCell(new Paragraph("Địa chỉ: ", font11_bold));
				cell.setPaddingLeft(5.0f);
				cell.setBorder(0);
			/*	cell.setBorderWidth(1);
				cell.setBorderWidthTop(1);
				cell.setBorderWidthBottom(1);*/
				tbl_traning.addCell(cell);
				
				 	cell = new PdfPCell(new Paragraph(obj.getKhDiachi(), font10));
					cell.setPaddingLeft(5.0f);
					cell.setBorder(0);
					/*cell.setBorderWidth(1);
					cell.setBorderWidthTop(0);
					cell.setBorderWidthBottom(0);*/
					tbl_traning.addCell(cell);
				 
					tbl_traning.setSpacingAfter(10);
		 
					document.add(tbl_traning);
			
			
			
			PdfPTable tbHeader = new PdfPTable(1);
			tbHeader.setWidthPercentage(100);
			tbHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
			float[] crHeader = {400f};
			// thuộc tính 
			tbHeader.setWidths(crHeader);
			tbHeader.getDefaultCell().setBorder(0);
			
			 
		 
			document.add(tbHeader);
			
			

			float[] crSanpham = {1.2f * CONVERT, 2.0f * CONVERT, 2.9f * CONVERT, 1.8f * CONVERT, 2.0f * CONVERT,2.0f * CONVERT,2.0f * CONVERT, 2.8f * CONVERT}; 
 
			PdfPTable tbSanPham = new PdfPTable(crSanpham.length);
			tbSanPham.setWidthPercentage(100);
			tbSanPham.setHorizontalAlignment(Element.ALIGN_LEFT);			
			tbSanPham.setWidths(crSanpham);
			tbSanPham.getDefaultCell().setBorder(0);
			tbSanPham.setSpacingAfter(8.0f);
			
			String[] spTitles = {"STT", "Mã","Tên", "Đơn vị", "Số lượng", "Đơn giá","Chiết khấu", "Thành tiền"};
			
			for(int z = 0; z < spTitles.length; z++) {
				cell = new PdfPCell(new Paragraph(spTitles[z], font11_bold));
				cell.setPadding(3.0f);
				cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				tbSanPham.addCell(cell);
			}
	 
			 
			List<ISanpham>listsp=obj.getSpList();
			
 			int  sott=1;
			for(int i=0;i<listsp.size();i++){
				ISanpham sp=listsp.get(i);
				
				String[] spTitles2 = {""+sott, sp.getMasanpham(), sp.getTensanpham(),sp.getDonvitinh(),formatter_2sole.format(sp.getSoluong()) 
						, formatter_2sole.format(sp.getDongia()),formatter_2sole.format(sp.getChietkhau()),formatter_2sole.format(sp.getThanhtien()) } ;
				
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
				cell.setColspan(7); 
				cell.setPadding(3.0f);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				tbSanPham.addCell(cell);
			
				cell = new PdfPCell(new Paragraph(formatter.format(obj.getTongtienchuaVat()), font11_bold));
				cell.setPadding(3.0f);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				tbSanPham.addCell(cell);
				
				
				
				
			
				document.add(tbSanPham);
			
			  pxk = new Paragraph("Số tiền bằng chữ: " +DocTien.docTien(Math.round(obj.getTongtienchuaVat()))+" .", new Font(bf, 12, Font.ITALIC));
				
				pxk.setSpacingAfter(2);
				pxk.setAlignment(Element.ALIGN_LEFT);
				 
				document.add(pxk);
				
			
			/*// khởi tạo với số cột trên hàng.
			
			
			
			
			*//********************** INFO CONGTY *******************************//*
			
			//các đối tượng để đưa vào : 
			//table PdfPTable
			
			
			
			*//********************** END INFO CONGTY *******************************//*
			
			
			*//********************** INFO NHA CUNG CAP *******************************//*
			
			PdfPTable tbNCC = new PdfPTable(2);
			tbNCC.setWidthPercentage(100);
			tbNCC.setHorizontalAlignment(Element.ALIGN_LEFT);
			float[] crNcc = {200.0f, 150.0f};
			tbNCC.setWidths(crNcc);
			tbNCC.getDefaultCell().setBorder(0);
			tbNCC.setSpacingBefore(10.0f);
			
			 
			
			*//********************END INFO NCC ***********************//*
			
			
			
			*//********************INFO SAN PHAM **********************//*
			
 
			*/
			
			document.close(); 
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception In PDF: " + e.getMessage());
		}
		
	}
		
 
	
	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	private String getEnDateTime(String date) {
		if(date.length() == 10) {
			String ngay = date.substring(8, 10);
			String thang = date.substring(5, 7);
			String nam = date.substring(0, 4);
			
			thang = thang.equals("01") ? "Jan" : thang.equals("02") ? "Feb" : thang.equals("03") ? "Mar" : thang.equals("04") ? "Apr" : thang.equals("05") ? "May" : thang.equals("06") ? "Jun" : thang.equals("07") ? "Jul" : thang.equals("08") ? "Aug" : thang.equals("09") ? "Sep" : thang.equals("10") ? "Oct" : thang.equals("11") ? "Nov" : thang.equals("12") ? "Dec" : " ";
			return thang + " " + ngay + ", " + nam;
		} else {
			return date;
		} 
	}
	
	private String getVnDateTime(String date) {
		if(date.length() == 10) {
			String ngay = date.substring(8, 10);
			String thang = date.substring(5, 7);
			String nam = date.substring(0, 4);
			return ngay + "-" + thang + "-" + nam;
		} else {
			return date;
		}
	}
	
	public static void main(String[] arg)
	{
		 
	}
	
	public String removeFONT(String text)
	{
		String kq = "<font size=\"2\">Pha 2-GĐ TKTD kết thúc ngày 21/8/2014.<br></font>awdakd askjndfka<font size=\"2\" style='color:red' >TEST DONG THU 2</font>";
		
		String[] arr = kq.split("</font>");
		for(int pos = 0; pos < arr.length; pos ++)
		{
			//TIM VI TRI BAT DAU CUA FONT
			int index = arr[pos].indexOf("<font ");
			
			//TIM VI TRI KET THUC CUA FONT
			if(index >= 0)
			{
				int index_END = 0;
				for(int i = index; i < arr[pos].length() - 1; i++)
				{
					if( arr[pos].substring(i, i + 1).equals(">") )
					{
						index_END = i;
						break;
					}
				}
				
				//TIM KY TU </font>
				//int index_END2 = kq.indexOf("</font>");
				//kq = kq.substring(index_END + 1, index_END2).trim();
				
				System.out.println("---KQ TRUOC: " + arr[pos] );
				
				if(index > 0)
					arr[pos] = arr[pos].substring(index_END + 1, arr[pos].length());
				else
					arr[pos] = arr[pos].substring(0, index) +  arr[pos].substring(index_END + 1, arr[pos].length());
				
				System.out.println("---KQ CHO NAY: " + arr[pos] );
				
			}
		}
		
		kq = "";
		for(int pos = 0; pos < arr.length; pos ++)
		{
			kq += arr[pos];
		}
		
		
		
		System.out.println("Ket qua la: " + kq);
		return kq;
	}
	
	
}
