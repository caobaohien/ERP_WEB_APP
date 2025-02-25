package geso.erp.servlets.sanpham_duongnguyen;

import java.io.IOException;
import geso.dms.center.util.Utility;
import geso.erp.beans.sanpham_duongnguyen.ISanPham;
import geso.erp.beans.sanpham_duongnguyen.ISanPhamList;
import geso.erp.beans.sanpham_duongnguyen.imp.SanPham;
import geso.erp.beans.sanpham_duongnguyen.imp.SanPhamList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SanPhamDuongNguyenListSvl
 */
// @WebServlet("/SanPhamDuongNguyenListSvl")
public class SanPhamDuongNguyenListSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SanPhamDuongNguyenListSvl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

		/*
		 * String action = util.getAction(querystring);
		 * 
		 * 
		 * String Id = util.getId(querystring);
		 */

		String msg = "";

		ISanPhamList spList = new SanPhamList();
		spList.init("");
		// khởi tạo ra 1 session

		session.setAttribute("sanPhamList", spList);
		String nextJSP = "/TrainingGESO/pages/Erp/SanPhamListDuongNguyen.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ISanPhamList obj;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String ma = request.getParameter("ma");
		String ten = request.getParameter("ten");
		String donViTinh = request.getParameter("donViTinh");
		String action = request.getParameter("action");
		obj = new SanPhamList();
		obj.setMa(ma);
		obj.setTen(ten);
		obj.setDonViTinh(donViTinh);
		// tại sao phải tạo CreateRsDonVi
		obj.CreateRsDonVi();
		HttpSession session = request.getSession();
		session.setAttribute("sanPhamList", obj);

		if (action.equals("timkiem")) {
			String query = getSearchQuery(request, obj);
			obj.init(query);
			session.setAttribute("sanPhamList", obj);
			String nextJSP = "/TrainingGESO/pages/Erp/SanPhamListDuongNguyen.jsp";
			response.sendRedirect(nextJSP);
		} else if (action.equals("taomoi")) {
			ISanPham sp = new SanPham();
			sp.createRs();
			session.setAttribute("spnew", sp);
			String nextJSP = "/TrainingGESO/pages/Erp/SanPhamNewDuongNguyen.jsp";
			response.sendRedirect(nextJSP);
		} else {
			obj.init("");
			session.setAttribute("sanPhamList", obj);
			String nextJSP = "/TrainingGESO/pages/Erp/SanPhamListDuongNguyen.jsp";
			response.sendRedirect(nextJSP);
		}
	}

	private String getSearchQuery(HttpServletRequest request, ISanPhamList obj) {
		Utility util = new Utility();

		String query = "";
		query = "SELECT SP.MA,SP.TEN,SP.TRANGTHAI,SP.PK_SEQ,DV.MA AS DONVI FROM SANPHAM SP "
				+ " LEFT JOIN DONVI DV ON DV.PK_SEQ=SP.DONVI_FK where 1=1   ";

		if (obj.getMa().length() > 0) {
			query += "  and  sp.ma LIKE '%" + obj.getMa() + "%'";
		}
		if (obj.getTen().length() > 0) {
			query += "  AND sp.ten LIKE '%" + obj.getTen() + "%'";
		}

		if (obj.getDonViTinh().length() > 0) {
			query += " AND sp.donvi_fk = '" + obj.getDonViTinh() + "'";
		}
		return query;
	}

}
