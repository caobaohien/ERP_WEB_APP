package geso.erp.servlets.muahang_hieu;

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
import com.sun.corba.se.impl.javax.rmi.CORBA.Util;

import geso.dms.center.util.Utility;
import geso.erp.beans.banhang.IBanhang;
import geso.erp.beans.banhang.imp.Banhang;
import geso.erp.beans.banhang.imp.BanhangList;
import geso.erp.beans.donbanhang_hieu.IDonbanhang;
import geso.erp.beans.donbanhang_hieu.IThongtindonbanhang;
import geso.erp.beans.donbanhang_hieu.imp.Donbanhang;
import geso.erp.beans.donbanhang_hieu.imp.Thongtindonbanhang;
import geso.erp.beans.muahang_hieu.IMuahang;
import geso.erp.beans.muahang_hieu.IThongtinmuahang;
import geso.erp.beans.muahang_hieu.imp.Donmuahang;
import geso.erp.beans.muahang_hieu.imp.Thongtindonmuahang;
import geso.erp.beans.nhacungcap.INhacungcap;
import geso.erp.beans.nhacungcap.INhacungcapList;
import geso.erp.beans.nhacungcap.imp.Nhacungcap;
import geso.erp.beans.nhacungcap.imp.NhacungcapList;
import geso.erp.beans.sanpham_tan.ISanpham;
import geso.erp.beans.sanpham_tan.IThongtinsanpham;
import geso.erp.beans.sanpham_tan.imp.Sanpham;
import geso.erp.beans.sanpham_tan.imp.Thongtinsanpham;
import geso.erp.db.sql.dbutils;

/**
 * Servlet implementation class SanphamListSvl
 */
@WebServlet("/DonmuahangListSvl")
public class DonmuahangListSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DonmuahangListSvl() {
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

		IMuahang dmh = new Donmuahang();
		// khởi tạo ra 1 session

		// cách lấy action (hành động truyền vào, và id truyền vào)

		System.out.println("querystring : " + querystring);
		String action = util.getAction(querystring);
		String action_getbangparameter = request.getParameter("update");

		System.out.println("action_getbangparameter : "
				+ action_getbangparameter);
		String dmhId = util.getId(querystring);

		if (action.equals("delete")) {
			dmh.setId(dmhId);
			dmh.Delete_dh();
			dmh.init("");

		} else if (action.equals("chot")) {
			dmh.setId(dmhId);
			dmh.Chot_dh();
			dmh.init("");

		}
		dmh.init("");
		session.setAttribute("obj", dmh);
		String nextJSP = "/TrainingGESO/pages/Erp/DonmuahangList_hieu.jsp";
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
		IMuahang obj;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		//
		String action = request.getParameter("action");
		if (action == null) {
			action = "";
		}
		obj = new Donmuahang();
		System.out.println("action gi day : " + action);

		String ma = util.antiSQLInspection(request.getParameter("ma"));
		if (ma == null) {
			ma = "";
		}
		System.out.println("ma gi day : " + ma);

		String nhacungcap = util.antiSQLInspection(request
				.getParameter("nhacungcap"));
		if (nhacungcap == null) {
			nhacungcap = "";
		}

		String trangthai = request.getParameter("trangthai");
		if (trangthai == null) {
			trangthai = "";
		}
		obj.setTrangthai(trangthai);
		System.out.println("trang thai gi : " + nhacungcap);

		HttpSession session = request.getSession();
		String userId = util.antiSQLInspection(request.getParameter("userId"));

		obj.setMa(ma);
		obj.setNhacungcap(nhacungcap);

		obj.setTrangthai(trangthai);

		if (action.equals("timkiem")) {

			obj.setNxtApprSplitting(Integer.parseInt(request
					.getParameter("nxtApprSplitting")));

			String search = getSearchQuery(request, obj);

			obj.init(search);

			session.setAttribute("obj", obj);
			response.sendRedirect("/TrainingGESO/pages/Erp/DonmuahangList_hieu.jsp");

		} else if (action.equals("taomoi")) {

			IThongtinmuahang dbh = new Thongtindonmuahang();
			session.setAttribute("obj", dbh);
			session.setAttribute("action", "save");
			dbh.CreateRs();
			String nextJSP = "/TrainingGESO/pages/Erp/DonmuahangNew_hieu.jsp";
			response.sendRedirect(nextJSP);
		} else if (action.equals("print")) {
			Create_PO_PDF(response, request);
		} else {
			obj = new Donmuahang();
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
			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf",
					BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			// khỏi tạo font chữ
			Font font10 = new Font(bf, 10, Font.NORMAL);

			Font font11_bold = new Font(bf, 11, Font.BOLD);

			PdfPTable tbl_header = new PdfPTable(1);

			tbl_header.setWidthPercentage(100);
			tbl_header.setHorizontalAlignment(Element.ALIGN_LEFT);
			float[] crtbl_header = { 7f };
			tbl_header.setWidths(crtbl_header);

			PdfPCell cell = new PdfPCell(new Paragraph("HÓA ĐƠN MUA HÀNG ",
					font11_bold));
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
					2.0f * CONVERT, 2.9f * CONVERT, 1.8f * CONVERT,
					2.0f * CONVERT };

			PdfPTable tbSanPham = new PdfPTable(crSanpham.length);
			tbSanPham.setWidthPercentage(100);
			tbSanPham.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbSanPham.setWidths(crSanpham);
			tbSanPham.getDefaultCell().setBorder(0);
			tbSanPham.setSpacingAfter(8.0f);

			String[] spTitles = { "SỐ TT", "SỐ CT", "MÃ CC", "TÊN CC",
					"NGÀY MUA HÀNG", "TỔNG TIỀN" };

			for (int z = 0; z < spTitles.length; z++) {
				cell = new PdfPCell(new Paragraph(spTitles[z], font11_bold));
				cell.setPadding(3.0f);
				cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				tbSanPham.addCell(cell);
			}

			String query = "";

			query = " SELECT mh.PK_SEQ as SOCHUNGTU,mh.NHACUNGCAP_FK AS MA,"
					+ "mh.NGAYCHUNGTU as NGAYCHUNGTU,ncc.TEN as TEN,mh.TONGTIEN as tongtien,"
					+ " mh.NGAYTAO as ngaytao,mh.NGUOITAO as nguoitao"
					+ " FROM MUAHANG mh LEFT "
					+ "	JOIN NHACUNGCAP ncc ON mh.NHACUNGCAP_FK=ncc.PK_SEQ ";
					
			if (tungay.length() > 0)
				query += " and dh.ngaychungtu >= '" + tungay + "'";

			if (denngay.length() > 0)
				query += " and dh.ngaychungtu <= '" + denngay + "'";

			ResultSet rs = db.get(query);

			int sott = 1;
			double tongtien = 0;
			while (rs.next()) {

				String[] spTitles2 = { "" + sott, rs.getString("SOCHUNGTU"),
						rs.getString("MA"), rs.getString("TEN"),
						rs.getString("NGAYCHUNGTU"),
						formatter.format(rs.getDouble("tongtien")) };

				tongtien = tongtien + Math.round(rs.getDouble("tongtien"));

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

			cell = new PdfPCell(new Paragraph("Tổng cộng", font11_bold));
			cell.setColspan(5);
			cell.setPadding(3.0f);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tbSanPham.addCell(cell);

			cell = new PdfPCell(new Paragraph(formatter.format(tongtien),
					font11_bold));
			cell.setPadding(3.0f);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tbSanPham.addCell(cell);

			document.add(tbSanPham);

			document.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception In PDF: " + e.getMessage());
		}

	}

	private String getSearchQuery(HttpServletRequest request, IMuahang obj) {
		Utility util = new Utility();

		String query = "";
		query = " SELECT  ISNULL(mh.TRANGTHAI,'') AS trangthai,mh.PK_SEQ as ma,mh.NGAYCHUNGTU as ngay,ncc.TEN as nhacungcap,mh.TONGTIEN as tongtien,"
				+ " mh.NGAYTAO as ngaytao,mh.NGUOITAO as nguoitao "
				+ " FROM MUAHANG mh LEFT "
				+ " "
				+ "	JOIN NHACUNGCAP ncc ON mh.NHACUNGCAP_FK=ncc.PK_SEQ "
				+"where 1=1";
		if (obj.getMa().length() > 0) {
			query += " AND  mh.PK_SEQ LIKE '%" + obj.getMa() + "%'";
		}
		if (obj.getNhacungcap().length() > 0) {
			query += " AND mh.NHACUNGCAP_FK = '" + obj.getNhacungcap() + "'";
		}

		if (obj.getTrangthai().length() > 0) {
			query += " AND mh.TRANGTHAI LIKE '" + obj.getTrangthai() + "'";
		}
		return query;

	}

}
