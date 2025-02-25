package geso.erp.servlets.chuyenkho_hieu;


import java.io.IOException;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.xmlbeans.impl.store.Query;

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

import geso.dms.center.util.Utility;

import geso.erp.beans.chuyenkho_hieu.IChuyenkhoList_hieu;
import geso.erp.beans.chuyenkho_hieu.IChuyenkhoUpdate_hieu;
import geso.erp.beans.chuyenkho_hieu.imp.ChuyenkhoList_hieu;
import geso.erp.beans.chuyenkho_hieu.imp.ChuyenkhoUpdate_hieu;
import geso.erp.beans.donbanhang_hieu.IDonbanhang;


import geso.erp.db.sql.dbutils;




/**
 * Servlet implementation class SanphamListSvl
 */
@WebServlet("/ChuyenkhoListSvl_hieu")
public class ChuyenkhoListSvl_hieu extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChuyenkhoListSvl_hieu() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		// dung để điều hướng
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		HttpSession session = request.getSession();
		Utility util = new Utility();

		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);

		if (userId.length() == 0)
			userId = util.antiSQLInspection(request.getParameter("userId"));


		String msg = "";
		 
		IChuyenkhoList_hieu ck = new ChuyenkhoList_hieu();
		
		// khởi tạo ra 1 session

		// cách lấy action (hành động truyền vào, và id truyền vào)
		
		System.out.println("querystring : "+querystring);
		String action = util.getAction(querystring);
		String action_getbangparameter= request.getParameter("update");
		
		System.out.println("action_getbangparameter : "+action_getbangparameter);
		String ckId = util.getId(querystring);
		
		 if (action.equals("delete"))
		    {	
			 	ck.setId(ckId);
				 ck.Delete_dh();
				 ck.init("");
		     
				
		    }
		    else if(action.equals("chot"))
			    	{
		    	ck.setId(ckId);
				 ck.Chot_dh();
				 ck.init("");
			    		 
			    		
			    }
		 	ck.init("");
			session.setAttribute("obj",ck);
			String nextJSP = "/TrainingGESO/pages/Erp/ChuyenkhoList_hieu.jsp";
			response.sendRedirect(nextJSP);
		    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Utility util = new Utility();
		IChuyenkhoList_hieu obj=new ChuyenkhoList_hieu();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		//
		String action = request.getParameter("action");
		if (action == null) {
			action = "";
		}
		
		System.out.println("action gi day : " + action);

		String ma = util.antiSQLInspection(request.getParameter("ma"));
		if (ma == null) {
			ma = "";
		}
		System.out.println("ma gi day : " + ma);

		String khochuyen =util.antiSQLInspection( request.getParameter("khochuyen"));
		if (khochuyen == null) {
			khochuyen = "";
		}
		String khonhan =util.antiSQLInspection( request.getParameter("khonhan"));
		if (khonhan == null) {
			khonhan = "";
		}
		String trangthai = request.getParameter("trangthai");
		if (trangthai == null) {
			trangthai = "";
		}
		obj.setTrangthai(trangthai);
		System.out.println("ma kho chuyen gi : " + khochuyen);
		System.out.println("ma kho nhan gi : " + khonhan);
		
		
		
		HttpSession session = request.getSession();
		
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		obj.setMa(ma);
		obj.setKhochuyen(khochuyen);
		obj.setKhonhan(khonhan);

		obj.setTrangthai(trangthai);

		if (action.equals("timkiem")) {

			obj.setNxtApprSplitting(Integer.parseInt(request
					.getParameter("nxtApprSplitting")));

			String search = getSearchQuery(request, obj);

			obj.init(search);

			session.setAttribute("obj", obj);
			response.sendRedirect("/TrainingGESO/pages/Erp/ChuyenkhoList_hieu.jsp");

		}else if (action.equals("taomoi")) {

			IChuyenkhoUpdate_hieu cku = new ChuyenkhoUpdate_hieu();
			session.setAttribute("obj", cku);
			session.setAttribute("action", "save");
			cku.CreateRs();
			String nextJSP = "/TrainingGESO/pages/Erp/ChuyenkhoUpdate_hieu.jsp";
			response.sendRedirect(nextJSP);
			
		}
		else {
			obj = new ChuyenkhoList_hieu();
			obj.setUserId(userId);

			obj.setNxtApprSplitting(Integer.parseInt(request
					.getParameter("nxtApprSplitting")));

			String search = getSearchQuery(request, obj);

			obj.init(search);

			session.setAttribute("obj", obj);
			response.sendRedirect("/TrainingGESO/pages/Erp/ChuyenkhoList_hieu.jsp");
		}

	}

	private String getSearchQuery(HttpServletRequest request, IChuyenkhoList_hieu obj) {
		Utility util = new Utility();

		String query = "";
		query = " SELECT ISNULL(ck.TRANGTHAI,'') AS trangthai,ck.PK_SEQ as ma ,ck.NGAYCHUYENKHO as ngaychuyenkho, "+
		" k.tenkho as khonhan,k1.tenkho as khochuyen,ck.NGAYTAO as ngaytao ,ck.NGAYSUA as ngaysua, "+
		" NV.TEN as nguoitao,NV1.TEN as nguoisua,ck.lydo as lydo "+
		" FROM chuyenkho ck LEFT JOIN KHO k ON CK.KHONHAN=K.PK_SEQ LEFT JOIN KHO k1 ON CK.KHOchuyen=K1.PK_SEQ" +
		" LEFT JOIN NHANVIEN NV ON CK.NGUOITAO=NV.PK_SEQ LEFT JOIN NHANVIEN NV1 ON CK.NGUOISUA=NV1.PK_SEQ  where "+
				"1=1";
		if (obj.getMa().length() > 0) {
			query += " AND  ck.PK_SEQ LIKE '%" + obj.getMa() + "%'";
		}
		if (obj.getKhochuyen().length() > 0) {
			query += " AND CK.KHOCHUYEN = '" + obj.getKhochuyen() + "'";
		}
		if (obj.getKhonhan().length() > 0) {
			query += " AND CK.KHONHAN = '" + obj.getKhonhan() + "'";
		}

		if (obj.getTrangthai().length() > 0) {
			query += " AND ck.TRANGTHAI LIKE '" + obj.getTrangthai() + "'";
		}
		return query;

	}
	private void Create_PO_PDF(HttpServletResponse response,
			HttpServletRequest request) {
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition",
				" inline; filename=DonMuaHang.pdf");

		float CONVERT = 28.346457f;// 1 cm
		float PAGE_LEFT = 2.0f * CONVERT, PAGE_RIGHT = 1.5f * CONVERT, PAGE_TOP = 0.5f * CONVERT, PAGE_BOTTOM = 0.0f * CONVERT; // cm
		Document document = new Document(PageSize.A4, PAGE_LEFT, PAGE_RIGHT,
				PAGE_TOP, PAGE_BOTTOM);
		ServletOutputStream outstream;
		try {
			outstream = response.getOutputStream();
			dbutils db = new dbutils();

			this.CreatePO_Training(document, outstream, response, request, db);
			db.shutDown();

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("___Exception PO Print: " + e.getMessage());
		}
	}

	private void CreatePO_Training(Document document,
			ServletOutputStream outstream, HttpServletResponse response,
			HttpServletRequest request, dbutils db) {
			HttpSession session = request.getSession();
		try {

			NumberFormat formatter_2sole = new DecimalFormat("#,###,###.###");
			NumberFormat formatter = new DecimalFormat("#,###,###");

			PdfWriter.getInstance(document, outstream);
			document.open();
			// document.setPageSize(new Rectangle(210.0f, 297.0f));

			float CONVERT = 28.346457f; // = 1cm
			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf",BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			// khỏi tạo font chữ
			Font font10 = new Font(bf, 10, Font.NORMAL);

			Font font11_bold = new Font(bf, 11, Font.BOLD);
			//////////////////////////////////////////////
			
			PdfPTable ima=new PdfPTable(1);
			ima.setWidthPercentage(50);
			ima.setHorizontalAlignment(Element.ALIGN_RIGHT);
			PdfPTable tbl_header = new PdfPTable(1); 
			
			float[] crtbl_header = { 7f };
			tbl_header.setWidths(crtbl_header);
			/*
			com.itextpdf.text.Image img=com.itextpdf.text.Image.getInstance( getServletContext().getInitParameter("path")+"/images/logo.png");
			img.setAlignment(Element.ALIGN_RIGHT);
			document.add(img);*/
			////////////////////////////////////////
			//PdfPTable tbl_header = new PdfPTable(1);
			tbl_header.setWidthPercentage(60);
			tbl_header.setHorizontalAlignment(Element.ALIGN_LEFT);
			
			PdfPCell cell = new PdfPCell(new Paragraph("CÔNG TY GIẢI PHÁP DOANH NGHIỆP TOÀN CẦU ",font11_bold));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			tbl_header.addCell(cell);

			cell = new PdfPCell(new Paragraph("234 Nguyễn Trọng Tuyển,Phú Nhuận,HCM", font11_bold));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			tbl_header.addCell(cell);
			document.add(tbl_header);
			Paragraph pxk1 =new Paragraph("\n");
			document.add(pxk1);
			String mact="",ngaylap="",tukho="",denkho="",lydochuyen="",diachigiaohang="",diachi="";
			

			Paragraph pxk = new Paragraph("PHIẾU CHUYỂN KHO",
					new Font(bf, 14, Font.BOLD));
			
			String query = "";

			query = " SELECT ck.PK_SEQ as machungtu,SP.MA as masp ,SP.TEN as tensp,ISNULL(DV.TEN,'') AS DONVI,"+
					" cksp.SOLUONG as soluongchuyen,k.TENKHO as khonhan,k1.TENKHO as khochuyen,ck.LYDO as lydo,"+
					" ck.NGAYCHUYENKHO as ngaychuyenkho,k.DIACHI as diachikhonhan,k1.DIACHI as diachikhochuyen "+
					" FROM CHUYENKHO_SANPHAM cksp LEFT JOIN CHUYENKHO ck ON ck.PK_SEQ=cksp.CHUYENKHO_FK "+
					" LEFT JOIN KHO k ON CK.KHONHAN=K.PK_SEQ LEFT JOIN KHO k1 ON CK.KHOchuyen=K1.PK_SEQ "+
					" left join KHO_SANPHAM ksp on ksp.kho_fk=k.PK_SEQ "+
					" left join erp_SANPHAM sp on ksp.SANPHAM_FK=sp.PK_SEQ "+
					" left join erp_DONVI dv on sp.DONVI_FK=dv.PK_SEQ ";

					

			ResultSet rs = db.get(query);
			while (rs.next()) {
				mact=rs.getString("machungtu");
				ngaylap=rs.getString("ngaychuyenkho");
				tukho=rs.getString("khochuyen");
				denkho=rs.getString("khonhan");
				lydochuyen=rs.getString("lydo");
				diachigiaohang=rs.getString("diachikhonhan");
				diachi=rs.getString("diachikhochuyen");
			}

			pxk.setSpacingAfter(2);
			pxk.setAlignment(Element.ALIGN_CENTER);
			document.add(pxk);
			document.add(pxk1);
			PdfPTable ct=new PdfPTable(2);
			cell = new PdfPCell(new Paragraph("Mã chứng từ :"+mact, font11_bold));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			ct.addCell(cell);
			
			cell = new PdfPCell(new Paragraph("Ngày lập :"+ngaylap, font11_bold));
			cell.setPaddingRight(5.0f);
			cell.setBorder(0);
			ct.addCell(cell);
			
			cell = new PdfPCell(new Paragraph("Từ kho :"+tukho, font11_bold));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			ct.addCell(cell);
			
			cell = new PdfPCell(new Paragraph("Đến kho :"+denkho, font11_bold));
			cell.setPaddingRight(5.0f);
			cell.setBorder(0);
			ct.addCell(cell);
			
			cell = new PdfPCell(new Paragraph("Địa chỉ :"+diachi, font11_bold));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			ct.addCell(cell);
			
			cell = new PdfPCell(new Paragraph("Địa chỉ giao hàng :"+diachigiaohang, font11_bold));
			cell.setPaddingRight(5.0f);
			cell.setBorder(0);
			ct.addCell(cell);
			
			cell = new PdfPCell(new Paragraph("Lý do chuyển :"+lydochuyen, font11_bold));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			ct.addCell(cell);
			
			cell = new PdfPCell(new Paragraph("", font11_bold));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			ct.addCell(cell);
			
			document.add(ct);
			document.add(pxk1);
			
			float[] crSanpham = { 1f * CONVERT, 2.0f * CONVERT,
					4.0f * CONVERT, 2.0f * CONVERT,2.0f * CONVERT };

			PdfPTable tbSanPham = new PdfPTable(crSanpham.length);
			tbSanPham.setWidthPercentage(100);
			tbSanPham.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbSanPham.setWidths(crSanpham);
			tbSanPham.getDefaultCell().setBorder(0);
			tbSanPham.setSpacingAfter(8.0f);

			String[] spTitles = { "SỐ TT", "MÃ SẢN PHẨM", "TÊN SẢN PHẨM", "ĐƠN VỊ",
					"SỐ LƯỢNG CHUYỂN "};

			for (int z = 0; z < spTitles.length; z++) {
				cell = new PdfPCell(new Paragraph(spTitles[z], font11_bold));
				cell.setPadding(3.0f);
				
				cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				tbSanPham.addCell(cell);
			}

			

			int sott = 1;
			ResultSet rs1 = db.get(query);
			while (rs1.next()) {

				String[] spTitles2 = { "" + sott, rs1.getString("masp"),
						rs1.getString("tensp"), rs1.getString("DONVI"),
						rs1.getString("soluongchuyen")};
						

				for (int z = 0; z < spTitles2.length; z++) {
					cell = new PdfPCell(new Paragraph(spTitles2[z], font10));
					cell.setPadding(3.0f);
					
					if (z == 4 || z == 5 || z == 7) {
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					} else {
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					}
					tbSanPham.addCell(cell);
				}
				sott++;
			}
			document.add(tbSanPham);
			PdfPTable kt=new PdfPTable(2);
			cell = new PdfPCell(new Paragraph("Trưởng phòng cung ứng:", font11_bold));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			kt.addCell(cell);
			
			cell = new PdfPCell(new Paragraph("Người nhận :", font11_bold));
			cell.setPaddingRight(10.0f);
			cell.setBorder(0);
			kt.addCell(cell);
			document.add(kt);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception In PDF: " + e.getMessage());
		}

	}
	
	

	private String getSearchQuery(HttpServletRequest request, IDonbanhang obj) {
		Utility util = new Utility();

		String query = "";
		query = " SELECT ISNULL(bh.TRANGTHAI,'') AS trangthai,bh.PK_SEQ as ma,bh.NGAYCHUNGTU as ngay,kh.TEN as khachhang,bh.TONGTIEN as tongtien," +
				" bh.NGAYTAO as ngaytao,bh.NGUOITAO as nguoitao " +
				" FROM BANHANG bh LEFT "+" "
				+ "	JOIN KHACHHANG kh ON bh.KHACHHANG_FK=kh.PK_SEQ "
			+ "LEFT"+" JOIN DONHANG_SANPHAM dhsp ON dhsp.DONHANG_FK=bh.PK_SEQ where "+
			"1=1";
		if (obj.getMa().length() > 0) {
			query += " AND  bh.PK_SEQ LIKE '%" + obj.getMa() + "%'";
		}
		if (obj.getKhachhang().length() > 0) {
			query += " AND kh.pk_seq = '" + obj.getKhachhang() + "'";
		}

		if (obj.getTrangthai().length() > 0) {
			query += " AND bh.TRANGTHAI LIKE '" + obj.getTrangthai() + "'";
		}
		return query;

	}


}
