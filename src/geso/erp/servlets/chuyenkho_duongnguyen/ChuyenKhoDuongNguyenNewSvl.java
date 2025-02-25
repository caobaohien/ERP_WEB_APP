package geso.erp.servlets.chuyenkho_duongnguyen;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import geso.dms.center.util.Utility;
import geso.erp.beans.chuyenkho_duongnguyen.IChuyenKho;
import geso.erp.beans.chuyenkho_duongnguyen.IChuyenKhoList;
import geso.erp.beans.chuyenkho_duongnguyen.imp.ChuyenKho;
import geso.erp.beans.chuyenkho_duongnguyen.imp.ChuyenKhoList;

/**
 * Servlet implementation class ChuyenKhoDuongNguyenNewSvl
 */
@WebServlet("/ChuyenKhoDuongNguyenNewSvl")
public class ChuyenKhoDuongNguyenNewSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChuyenKhoDuongNguyenNewSvl() {
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
		response.setCharacterEncoding("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		Utility util = new Utility();
		String queryString = request.getQueryString();
		String userId = util.getUserId(queryString);
		if (userId.length() == 0)
			userId = util.antiSQLInspection(request.getParameter("userId"));
		String action = util.getAction(queryString);
		String id = util.getId(queryString);
		IChuyenKho ck = new ChuyenKho();
		ck.setId(id);
		ck.init();
		session.setAttribute("chuyenKhoNew", ck);
		String nextJSP = "/TrainingGESO/pages/Erp/ChuyenKhoNewDuongNguyen.jsp";
		response.sendRedirect(nextJSP);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		IChuyenKho ck = new ChuyenKho();
		if (action == null)
			action = "";
		Utility util = new Utility();
		String userId = request.getParameter("userId");
		String id = request.getParameter("id");
		String ngayChuyenKho = util.antiSQLInspection(request.getParameter("ngayChuyenKho"));
		String lyDo = util.antiSQLInspection(request.getParameter("lyDo"));
		String khoChuyen = util.antiSQLInspection(request.getParameter("khoChuyen"));
		String khoNhan = util.antiSQLInspection(request.getParameter("khoNhan"));
		
		ck.setId(id);
		ck.setLyDo(lyDo == null ? "" : lyDo);
		ck.setNgayChuyenKho(ngayChuyenKho == null ? "" : ngayChuyenKho);
		ck.setKhoChuyen(khoChuyen == null ? "" : khoChuyen);
		ck.setKhoNhan(khoNhan == null ? "" : khoNhan);
		if (action.equals("save")) {
			if (id.length() > 0) {
				if (userId != null)
					ck.setNguoiSua(userId);
				if (!ck.update()) {
					ck.CreateRsKhoChuyen();
					ck.CreateRsKhoNhan();
					session.setAttribute("chuyenKhoNew", ck);
					String nextJSP = "/TrainingGESO/pages/Erp/ChuyenKhoNewDuongNguyen.jsp";
					response.sendRedirect(nextJSP);
				} else {
					IChuyenKhoList ckList = new ChuyenKhoList();
					ckList.init("");
					session.setAttribute("chuyenKhoList", ckList);
					String nextJSP = "/TrainingGESO/pages/Erp/ChuyenKhoListDuongNguyen.jsp";
					response.sendRedirect(nextJSP);
				}
			} else {
				System.out.println("userId:" + userId);
				ck.setNguoiTao(userId);
				if (!ck.save()) {
					ck.CreateRsKhoChuyen();
					ck.CreateRsKhoNhan();
					session.setAttribute("chuyenKhoNew", ck);
					String nextJSP = "/TrainingGESO/pages/Erp/ChuyenKhoNewDuongNguyen.jsp";
					response.sendRedirect(nextJSP);
				} else {
					IChuyenKhoList ckList = new ChuyenKhoList();
					ckList.init("");
					session.setAttribute("chuyenKhoList", ckList);
					String nextJSP = "/TrainingGESO/pages/Erp/ChuyenKhoListDuongNguyen.jsp";
					response.sendRedirect(nextJSP);
				}
			}
		}
	}

}
