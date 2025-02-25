package geso.erp.servlets.donbanhang_thu.imp;

import geso.dms.center.util.Utility;
import geso.erp.beans.donbanhang_thu.IDonBanHangList_Thu;
import geso.erp.beans.donbanhang_thu.IDonBanHang_Thu;
import geso.erp.beans.donbanhang_thu.imp.DonBanHangList_Thu;
import geso.erp.beans.donbanhang_thu.imp.DonBanHang_Thu;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DonBanHang_ThuSvl extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DonBanHang_ThuSvl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Dinh dang unicode
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		// Tao session de chua thong tin cua nguoi tao/nguoi sua
		HttpSession httpSession = request.getSession();

		Utility utility = new Utility();

		String queryString = request.getQueryString();

		// Lay thong tin cua mot user trong session
		String userId = utility.getUserId(queryString);
		if (userId.length() == 0) {
			userId = utility.antiSQLInspection(request.getParameter("userId"));
		}

		IDonBanHangList_Thu obj = new DonBanHangList_Thu();
		String maDonHang = utility.getId(queryString);
		String action = utility.getAction(queryString);

		System.out.println(maDonHang);
		System.out.println("action: " + action);
		obj.setMaDonHang(maDonHang);
		obj.setUserId(userId);

		if (action.equals("delete")) {
			obj.deleteDonHang();

		}
		if (action.equals("chot")) {
			obj.chotDonHang();

		}
		
		
		
		// xét các action

		obj.init("");
		httpSession.setAttribute("obj", obj);
		String nextJSP = "/TrainingGESO/pages/Erp/DonBanHang_Thu.jsp";
		response.sendRedirect(nextJSP);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Dinh dang unicode
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		IDonBanHangList_Thu obj = new DonBanHangList_Thu();
		String action = request.getParameter("action");

		String maDonHang = request.getParameter("maDonHang");
		String tenKhachHang = request.getParameter("khachHang");
		String trangThai = request.getParameter("trangThai");
		System.out.println(obj.getTrangThai());

		action = (action == null) ? "" : action;
		maDonHang = (maDonHang == null) ? "" : maDonHang;
		tenKhachHang = (tenKhachHang == null) ? "" : tenKhachHang;
		trangThai = (trangThai == null) ? "" : trangThai;

		HttpSession session = request.getSession();
		obj.setMaDonHang(maDonHang);
		obj.setKhachHang(tenKhachHang);
		obj.setTrangThai(trangThai);

		System.out.println(action);
		System.out.println(obj.getTrangThai());

		// thực hiện các điều khiển
		if (action.equals("timkiem")) {
			obj.setNxtApprSplitting(Integer.parseInt(request
					.getParameter("nxtApprSplitting")));

			String searchQuery = getSearchQuery(request, obj);

			// System.out.print("tk"+ searchQuery);
			// khởi tạo
			obj.init(searchQuery);

			// đưa đối tượng vào session đưa đi đến jsp
			session.setAttribute("obj", obj);
			response.sendRedirect("/TrainingGESO/pages/Erp/DonBanHang_Thu.jsp");

		} else if (action.equals("taomoi")) {
			IDonBanHang_Thu obj1 = new DonBanHang_Thu();
			obj1.layKhachHang();
			session.setAttribute("action", "taomoi");
			session.setAttribute("obj", obj1);
			String nextJSP = "/TrainingGESO/pages/Erp/DonHangNew_Thu.jsp";
			response.sendRedirect(nextJSP);
		}

	}

	private String getSearchQuery(HttpServletRequest request,
			IDonBanHangList_Thu obj) {

		Utility utility = new Utility();

		String query = "select BH.PK_SEQ as MADONHANG, isnull(BH.NGAYCHUNGTU,'') as NGAYCHUNGTU,"
				+ " KH.TEN as TEN,BH.TRANGTHAI as TRANGTHAI ,"
				+ " BH.TONGTIEN as TONGTIEN,BH.NGAYTAO as NGAYTAO,"
				+ " BH.NGUOITAO as NGUOITAO"
				+ " from BANHANG BH left join  KHACHHANG KH on KH.PK_SEQ = BH.KHACHHANG_FK "
				+ " WHERE 1=1 ";

		// tìm theo mã đơn hàng
		if (obj.getMaDonHang().length() > 0) {
			query += " AND BH.PK_SEQ = '" + obj.getMaDonHang() + "'";
		}

		// tìm theo mã khách hàng
		if (obj.getKhachHang().length() > 0) {
			System.out.println(obj.getKhachHang());
			query += " AND KH.MA = '" + obj.getKhachHang() + "'";
		}

		// tìm theo trang thái
		if (obj.getTrangThai().length() > 0) {
			query += " AND BH.TRANGTHAI = " + obj.getTrangThai();
		}

		System.out.print("câu truy vấn tìm kiếm: " + query);
		return query;
	}

}
