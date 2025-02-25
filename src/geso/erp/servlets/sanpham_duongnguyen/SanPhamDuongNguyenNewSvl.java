package geso.erp.servlets.sanpham_duongnguyen;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import geso.dms.center.util.Utility;
import geso.erp.beans.sanpham_duongnguyen.ISanPham;
import geso.erp.beans.sanpham_duongnguyen.ISanPhamList;
import geso.erp.beans.sanpham_duongnguyen.imp.SanPham;
import geso.erp.beans.sanpham_duongnguyen.imp.SanPhamList;

/**
 * Servlet implementation class SanPhamDuonNguyenNewSvl
 */
@WebServlet("/SanPhamDuongNguyenNewSvl")
public class SanPhamDuongNguyenNewSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SanPhamDuongNguyenNewSvl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		Utility util = new Utility();
		String queryString = request.getQueryString();
		String userId = util.getUserId(queryString);
		if (userId.length() == 0)
			userId = util.antiSQLInspection(request.getParameter("userId"));
		String action = util.getAction(queryString);
		String id = util.getId(queryString);
		String msg = "";
		ISanPham sp = new SanPham();
		sp.setId(id);
		sp.init();
		sp.createRs();
		session.setAttribute("spnew", sp);
		String nextJSP = "/TrainingGESO/pages/Erp/SanPhamNewDuongNguyen.jsp";
		response.sendRedirect(nextJSP);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Khi nào gọi doPost khi nào gọi doGet
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		ISanPham obj = new SanPham();
		String action = request.getParameter("action");
		if (action == null) {
			action = "";
		}
		Utility util = new Utility();
		HttpSession session = request.getSession();
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		String id = util.antiSQLInspection(request.getParameter("id"));
		String ma = util.antiSQLInspection(request.getParameter("ma"));
		String ten = util.antiSQLInspection(request.getParameter("ten"));
		String tinhTrang = util.antiSQLInspection(request.getParameter("tinhTrang"));
		String donViTinh = util.antiSQLInspection(request.getParameter("donViTinh"));
		obj.setUserId(userId);
		obj.setId(id == null ? "" : id);
		obj.setMa(ma == null ? "" : ma);
		obj.setTen(ten == null ? "" : ten);
		obj.setTrangThai(tinhTrang == null ? "0" : tinhTrang);
		obj.setDonViTinh(donViTinh == null ? "" : donViTinh);
		if (action.equals("save")) {
			if (id.length() > 0) {
				if (!obj.update()) {
					session.setAttribute("spnew", obj);
					String nextJSP = "/TrainingGESO/pages/Erp/SanPhamNewDuongNguyen.jsp";
					response.sendRedirect(nextJSP);
				} else {
					ISanPhamList spList = new SanPhamList();
					spList.init("");
					session.setAttribute("sanPhamList", spList);
					String nextJSP = "/TrainingGESO/pages/Erp/SanPhamListDuongNguyen.jsp";
					response.sendRedirect(nextJSP);
				}
			} else {
				if (!obj.save()) {
					session.setAttribute("spnew", obj);
					String nextJSP = "/TrainingGESO/pages/Erp/SanPhamNewDuongNguyen.jsp";
					response.sendRedirect(nextJSP);
				} else {
					ISanPhamList spList = new SanPhamList();
					spList.init("");
					session.setAttribute("sanPhamList", spList);
					String nextJSP = "/TrainingGESO/pages/Erp/SanPhamListDuongNguyen.jsp";
					response.sendRedirect(nextJSP);
				}
			}
		} else {
			session.setAttribute("spnew", obj);
			String nextJSP = "/TrainingGESO/pages/Erp/SanPhamNewDuongNguyen.jsp";
		}
	}

}
