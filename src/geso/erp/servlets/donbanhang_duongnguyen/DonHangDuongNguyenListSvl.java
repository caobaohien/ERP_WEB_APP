package geso.erp.servlets.donbanhang_duongnguyen;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import geso.dms.center.util.Utility;
import geso.erp.beans.donbanhang_duongnguyen.IDonHang;
import geso.erp.beans.donbanhang_duongnguyen.IDonHangList;
import geso.erp.beans.donbanhang_duongnguyen.Imp.DonHang;
import geso.erp.beans.donbanhang_duongnguyen.Imp.DonHangList;

/**
 * Servlet implementation class DonHangDuongNguyenListSvl
 */
@WebServlet("/DonHangDuongNguyenListSvl")
public class DonHangDuongNguyenListSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DonHangDuongNguyenListSvl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		Utility util = new Utility();
		String queryString = request.getQueryString();
		/*String userId = util.getUserId(queryString);
		if (userId.length() == 0)
			userId = request.getParameter("userId");*/
		String msg = "";
		IDonHangList dhList = new DonHangList();
		dhList.init("");
		session.setAttribute("donHangList", dhList);
		String nextJSP = "/TrainingGESO/pages/Erp/DonHangListDuongNguyen.jsp";
		response.sendRedirect(nextJSP);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		IDonHangList obj;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		Utility util = new Utility();
		String ma = request.getParameter("ma");
		String trangThai = request.getParameter("trangThai");
		String khachHang = request.getParameter("khachHang");
		String action = request.getParameter("action");
		obj = new DonHangList();
		obj.setMa(ma);
		obj.setTrangThai(trangThai);
		obj.setTenKH(khachHang);
		obj.CreateRsKhachHang();
		HttpSession session = request.getSession();
		//session.setAttribute("donHangList", obj);
		if (action.equals("timkiem")) {
			String query = getSearchQuery(request, obj);
			obj.init(query);
			session.setAttribute("donHangList", obj);
			String nextJSP = "/TrainingGESO/pages/Erp/DonHangListDuongNguyen.jsp";
			response.sendRedirect(nextJSP);
		} else if (action.equals("taomoi")) {
			IDonHang dh = new DonHang();
			dh.CreateRsKhachHang();
			session.setAttribute("dhnew", dh);
			String nextJSP = "/TrainingGESO/pages/Erp/DonHangNewDuongNguyen.jsp";
			response.sendRedirect(nextJSP);
		} else {
			obj.init("");
			session.setAttribute("donHangList", obj);
			String nextJSP = "/TrainingGESO/pages/Erp/DonHangListDuongNguyen.jsp";
			response.sendRedirect(nextJSP);
		}
	}

	private String getSearchQuery(HttpServletRequest request, IDonHangList obj) {
		Utility util = new Utility();

		String query = "";
		query = "select bh.pk_seq,bh.ngaychungtu,kh.ten,bh.trangthai,bh.tongtien,bh.ngaytao,nv.ten as tennv from BANHANG bh "
				+ " left join NHANVIEN nv on bh.nguoitao=nv.pk_seq left join KHACHHANG kh on bh.khachhang_fk = kh.pk_seq where 1=1   ";

		if (obj.getMa().length() > 0) {
			query += " and  bh.pk_seq LIKE '%" + obj.getMa() + "%'";
		}
		if (obj.getTrangThai().length() > 0 && !obj.getTrangThai().equals("-1")) {
			query += " AND bh.trangthai = '" + obj.getTrangThai() + "'";
		}

		if (obj.getTenKH().length() > 0) {
			query += " AND bh.khachhang_fk LIKE '%" + obj.getTenKH() + "%'";
		}
		return query;
	}

}
