package geso.erp.servlets.donbanhang_hieu;


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

import geso.erp.beans.donbanhang_hieu.IDonbanhang;
import geso.erp.beans.donbanhang_hieu.IThongtindonbanhang;
import geso.erp.beans.donbanhang_hieu.imp.Donbanhang;
import geso.erp.beans.donbanhang_hieu.imp.Thongtindonbanhang;
import geso.erp.db.sql.dbutils;




/**
 * Servlet implementation class SanphamListSvl
 */
@WebServlet("/DonbanhangListSvl")
public class DonbanhangListSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DonbanhangListSvl() {
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
		 
		IDonbanhang dbh = new Donbanhang();
		
		// khởi tạo ra 1 session

		// cách lấy action (hành động truyền vào, và id truyền vào)
		
		System.out.println("querystring : "+querystring);
		String action = util.getAction(querystring);
		String action_getbangparameter= request.getParameter("update");
		
		System.out.println("action_getbangparameter : "+action_getbangparameter);
		String dmhId = util.getId(querystring);
		
		 if (action.equals("delete"))
		    {	
			 	dbh.setId(dmhId);
				 dbh.Delete_dh();
				 dbh.init("");
		     
				
		    }
		    else if(action.equals("chot"))
			    	{
		    	dbh.setId(dmhId);
				 dbh.Chot_dh();
				 dbh.init("");
			    		 
			    		
			    	}
		 	dbh.init("");
			session.setAttribute("obj", dbh);
			String nextJSP = "/TrainingGESO/pages/Erp/DonbanhangList.jsp";
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
		IDonbanhang obj;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		//
		String action = request.getParameter("action");
		if (action == null) {
			action = "";
		}
		obj = new Donbanhang();
		System.out.println("action gi day : " + action);

		String ma = util.antiSQLInspection(request.getParameter("ma"));
		if (ma == null) {
			ma = "";
		}
		System.out.println("ma gi day : " + ma);

		String khachhang =util.antiSQLInspection( request.getParameter("khachhang"));
		if (khachhang == null) {
			khachhang = "";
		}
		System.out.println("ten kh: " + khachhang);
		String trangthai = request.getParameter("trangthai");
		if (trangthai == null) {
			trangthai = "";
		}
		obj.setTrangthai(trangthai);
		System.out.println("trang thai gi : " + khachhang);
		
		
		
		
		HttpSession session = request.getSession();
		
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		obj.setMa(ma);
		obj.setKhachhang(khachhang);
		
		
		obj.setTrangthai(trangthai);

		if (action.equals("timkiem")) {

			obj.setNxtApprSplitting(Integer.parseInt(request
					.getParameter("nxtApprSplitting")));

			String search = getSearchQuery(request, obj);

			obj.init(search);

			session.setAttribute("obj", obj);
			response.sendRedirect("/TrainingGESO/pages/Erp/DonbanhangList.jsp");

		} else if (action.equals("taomoi")) {

			IThongtindonbanhang dbh = new Thongtindonbanhang();
			session.setAttribute("obj", dbh);
			dbh.CreateRs();
			String nextJSP = "/TrainingGESO/pages/Erp/Donbanhangnew.jsp";
			response.sendRedirect(nextJSP);
			
		}else if (action.equals("print")) {
			Create_PO_PDF(response, request);
		}
		else {
			obj = new Donbanhang();
			obj.setUserId(userId);

			obj.setNxtApprSplitting(Integer.parseInt(request
					.getParameter("nxtApprSplitting")));

			String search = getSearchQuery(request, obj);

			obj.init(search);

			session.setAttribute("obj", obj);
			response.sendRedirect("/TrainingGESO/pages/Erp/DonmuahangList_hieu.jsp");
		}

	}

	// Xử lý xuất file pdf
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
			
			com.itextpdf.text.Image img=com.itextpdf.text.Image.getInstance( getServletContext().getInitParameter("path")+"/images/logo.png");
			img.setAlignment(Element.ALIGN_RIGHT);
			document.add(img);
			////////////////////////////////////////
			//PdfPTable tbl_header = new PdfPTable(1);
			tbl_header.setWidthPercentage(50);
			tbl_header.setHorizontalAlignment(Element.ALIGN_LEFT);
			PdfPCell cell = new PdfPCell(new Paragraph("HOANG VAN LUONG ",font11_bold));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			tbl_header.addCell(cell);

			cell = new PdfPCell(new Paragraph("ID/Passport:164440030", font11_bold));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			tbl_header.addCell(cell);
			cell = new PdfPCell(new Paragraph("CT TNHH GIAI PHAP DOANH NGHIEP TOAN CAU,441/19 NGUYEN BINH KHIEM P1 Q.GOVAP,HCM", font11_bold));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			tbl_header.addCell(cell);
			
			cell = new PdfPCell(new Paragraph("DIEN THOAI :+084909865115", font11_bold));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			tbl_header.addCell(cell);
			document.add(tbl_header);

			String tungay = request.getParameter("tungay");
			if (tungay == null)
				tungay = "";

			String denngay = request.getParameter("denngay");
			if (denngay == null)
				denngay = "";

			Paragraph pxk = new Paragraph("BÁO CÁO ĐƠN BÁN HÀNG TRONG KỲ",
					new Font(bf, 14, Font.BOLD));

			pxk.setSpacingAfter(2);
			pxk.setAlignment(Element.ALIGN_CENTER);

			document.add(pxk);

			pxk = new Paragraph("Từ ngày: " + tungay + " .Đến ngày:" + denngay,
					new Font(bf, 12, Font.BOLD));

			pxk.setSpacingAfter(2);
			pxk.setAlignment(Element.ALIGN_CENTER);

			document.add(pxk);

			float[] crSanpham = { 0.7f * CONVERT, 2.0f * CONVERT,
					2.0f * CONVERT, 1.0f * CONVERT, 1.8f * CONVERT,1.9f * CONVERT,
					2.0f * CONVERT };

			PdfPTable tbSanPham = new PdfPTable(crSanpham.length);
			tbSanPham.setWidthPercentage(100);
			tbSanPham.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbSanPham.setWidths(crSanpham);
			tbSanPham.getDefaultCell().setBorder(0);
			tbSanPham.setSpacingAfter(8.0f);

			String[] spTitles = { "SỐ TT", "MÃ", "TÊN SẢN PHẨM", "TÊN ĐƠN VỊ",
					"SỐ LƯỢNG", "ĐƠN GIÁ","THÀNH TIỀN" };

			for (int z = 0; z < spTitles.length; z++) {
				cell = new PdfPCell(new Paragraph(spTitles[z], font11_bold));
				cell.setPadding(3.0f);
				cell.setBorder(0);
				cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				tbSanPham.addCell(cell);
			}

			String query = "";

			query = " SELECT bh.PK_SEQ as SOCHUNGTU,sp.TEN as TENSP,dv.TEN as dvt," +
					"dhsp.SOLUONG as SOLUONG,dhsp.DONGIA AS DONGIA,dhsp.THANHTIEN as thanhtien"
					+" FROM BANHANG bh left join DONHANG_SANPHAM dhsp on bh.PK_SEQ=dhsp.DONHANG_FK"
					+" left join SANPHAM sp on dhsp.SANPHAM_FK=sp.PK_SEQ"
					+" left join DONVI dv on sp.DONVI_FK=dv.PK_SEQ" ;
					

			ResultSet rs = db.get(query);

			int sott = 1;
			double tongtien = 0;
			while (rs.next()) {

				String[] spTitles2 = { "" + sott, rs.getString("SOCHUNGTU"),
						rs.getString("TENSP"), rs.getString("dvt"),
						rs.getString("SOLUONG"),
						rs.getString("DONGIA"),
						formatter.format(rs.getDouble("thanhtien")) };

				tongtien = tongtien + Math.round(rs.getDouble("thanhtien"));

				for (int z = 0; z < spTitles2.length; z++) {
					cell = new PdfPCell(new Paragraph(spTitles2[z], font10));
					cell.setPadding(3.0f);
					cell.setBorder(0);
					if (z == 4 || z == 5 || z == 7) {
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					} else {
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					}
					tbSanPham.addCell(cell);
				}
				sott++;
			}

			cell = new PdfPCell(new Paragraph("Tổng cộng", font11_bold));
			cell.setBorder(0);
			cell.setColspan(6);
			cell.setPadding(3.0f);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tbSanPham.addCell(cell);

			cell = new PdfPCell(new Paragraph(formatter.format(tongtien),
					font11_bold));
			cell.setPadding(3.0f);
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tbSanPham.addCell(cell);

			document.add(tbSanPham);
			pxk = new Paragraph("I confirmed that I have received the Card(s) and Account Number in good condtition " +
					"of sealed envenled which directly hand deliver to my address stated-here above",
					new Font(bf, 12, Font.ITALIC));

			pxk.setSpacingAfter(2);
			pxk.setAlignment(Element.ALIGN_LEFT);
			
			document.add(pxk);
		
			pxk = new Paragraph("Data/ngày:......./......./......... ",
					new Font(bf, 12));

			pxk.setSpacingAfter(2);
			pxk.setAlignment(Element.ALIGN_LEFT);
			document.add(pxk);
			pxk =new Paragraph("\n\n\n\n\n");
			document.add(pxk);
			pxk = new Paragraph("Ký ghi rõ họ tên ",
					new Font(bf, 12));

			pxk.setSpacingAfter(2);
			pxk.setAlignment(Element.ALIGN_LEFT);
			
			document.add(pxk);
			pxk = new Paragraph("Full name/Họ và Tên :",
					new Font(bf, 12));

			pxk.setSpacingAfter(2);
			pxk.setAlignment(Element.ALIGN_LEFT);
			
			document.add(pxk);
			
			
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
