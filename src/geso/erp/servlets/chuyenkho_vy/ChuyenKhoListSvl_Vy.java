package geso.erp.servlets.chuyenkho_vy;

import geso.dms.center.util.Utility;
import geso.erp.beans.chuyenkho_vy.IPhieuChuyenKhoList_Vy;
import geso.erp.beans.chuyenkho_vy.IPhieuChuyenKho_Vy;
import geso.erp.beans.chuyenkho_vy.imp.PhieuChuyenKhoList_Vy;
import geso.erp.beans.chuyenkho_vy.imp.PhieuChuyenKho_Vy;
import geso.erp.beans.kho_vy.imp.Kho_Vy;
import geso.erp.util.UtilityKho_Vy;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ChuyenKhoListSvl_Vy
 */
public class ChuyenKhoListSvl_Vy extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Dinh dang unicode
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		// Tao session de chua thong tin cua nguoi tao/nguoi sua
		HttpSession httpSession = request.getSession();
		Utility utility = new Utility();
		// Lay thong tin cua mot user trong session
		String queryString = request.getQueryString();
		String userId = utility.getUserId(queryString);

		if (userId.length() == 0) {
			userId = utility.antiSQLInspection(request.getParameter("userId"));
		}
		String PK_SEQ = utility.getId(queryString);
		String action = utility.getAction(queryString);

		IPhieuChuyenKho_Vy phieuChuyenKhoCu = new PhieuChuyenKho_Vy(PK_SEQ);
		phieuChuyenKhoCu.setNguoiSua(userId);
		if (action.equals("chot")) {
			phieuChuyenKhoCu.chotPhieu();
		} else if (action.equals("delete")) {
			phieuChuyenKhoCu.huyPhieu();
		}

		// Tao danh sach cac phieu chuyen kho
		IPhieuChuyenKhoList_Vy phieuChuyenKhoList = new PhieuChuyenKhoList_Vy();
		phieuChuyenKhoList.initListPhieuChuyenKho();

		httpSession.setAttribute("phieuChuyenKhoList", phieuChuyenKhoList);
		String nextJSP = "/TrainingGESO/pages/Erp/ChuyenKhoList_Vy.jsp";
		response.sendRedirect(nextJSP);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Dinh dang unicode
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		HttpSession session = request.getSession();
		UtilityKho_Vy utilityKho = new UtilityKho_Vy();

		String action = request.getParameter("action");
		action = (action == null) ? "" : action;

		String maPhieuChuyenKho = request.getParameter("maPhieuChuyenKho");
		String maKhoNhan = request.getParameter("maKhoNhan");
		String maKhoChuyen = request.getParameter("maKhoChuyen");
		String maTrangThai = request.getParameter("trangThai");

		maPhieuChuyenKho = (maPhieuChuyenKho == null) ? "" : maPhieuChuyenKho;
		maKhoNhan = (maKhoNhan == null) ? "" : maKhoNhan;
		maKhoChuyen = (maKhoChuyen == null) ? "" : maKhoChuyen;
		maTrangThai = (maTrangThai == null) ? "" : maTrangThai;

		IPhieuChuyenKho_Vy phieuChuyenKho = new PhieuChuyenKho_Vy(maPhieuChuyenKho, 
																	new Kho_Vy(maKhoNhan), 
																	new Kho_Vy(maKhoChuyen), 
																	maTrangThai);
		IPhieuChuyenKhoList_Vy phieuChuyenKhoList = new PhieuChuyenKhoList_Vy(phieuChuyenKho);

		if (action.equals("timkiem")) {
			System.out.println("Đang tim kiếm dữ liệu");
			phieuChuyenKhoList.searchPhieuChuyenKho();
			session.setAttribute("phieuChuyenKhoList", phieuChuyenKhoList);
			response.sendRedirect("/TrainingGESO/pages/Erp/ChuyenKhoList_Vy.jsp");

		} else if (action.equals("taomoi")) {
			IPhieuChuyenKho_Vy phieuChuyenKhoMoi = new PhieuChuyenKho_Vy();
			phieuChuyenKhoMoi.setKhoChuyen(new Kho_Vy("100000"));
			phieuChuyenKhoMoi.setTrangThai("0");
			phieuChuyenKhoMoi.getUtilityKho().createResultSetKho();
			session.setAttribute("phieuChuyenKho", phieuChuyenKhoMoi);
			String nextJSP = "/TrainingGESO/pages/Erp/ChuyenKhoNew_Vy.jsp";
			response.sendRedirect(nextJSP);
		}
	}
}

