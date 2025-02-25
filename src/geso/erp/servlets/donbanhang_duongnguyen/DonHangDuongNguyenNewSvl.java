package geso.erp.servlets.donbanhang_duongnguyen;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import geso.dms.center.util.Utility;
import geso.erp.beans.donbanhang_duongnguyen.IDonHang;
import geso.erp.beans.donbanhang_duongnguyen.IDonHangList;
import geso.erp.beans.donbanhang_duongnguyen.ISanPham;
import geso.erp.beans.donbanhang_duongnguyen.Imp.DonHang;
import geso.erp.beans.donbanhang_duongnguyen.Imp.DonHangList;
import geso.erp.beans.donbanhang_duongnguyen.Imp.SanPham;

/**
 * Servlet implementation class DonHangDuongNguyenNewSvl
 */
@WebServlet("/DonHangDuongNguyenNewSvl")
public class DonHangDuongNguyenNewSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DonHangDuongNguyenNewSvl() {
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
		String userId = util.getUserId(queryString);
		if (userId.length() == 0)
			userId = util.antiSQLInspection(request.getParameter("userId"));
		String action = util.getAction(queryString);
		String id = util.getId(queryString);
		String msg = "";
		IDonHang dh = new DonHang();
		dh.setId(id);
		dh.init();
		dh.CreateRsKhachHang();
		session.setAttribute("dhnew", dh);
		String nextJSP = "/TrainingGESO/pages/Erp/DonHangNewDuongNguyen.jsp";
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
		response.setContentType("text/html; charset=UTF-8");

		IDonHang obj = new DonHang();
		String action = request.getParameter("action");
		if (action == null) {
			action = "";
		}
		Utility util = new Utility();
		HttpSession session = request.getSession();
		String userId = request.getParameter("userId");
		String id = request.getParameter("id");
		String khachHang = util.antiSQLInspection(request.getParameter("khachHang"));
		String ngayCT = util.antiSQLInspection(request.getParameter("ngayCT"));
		float tongTien = Float.parseFloat(util.antiSQLInspection(request.getParameter("tongTien")));
		String trangThai = util.antiSQLInspection(request.getParameter("trangThai"));
		obj.setUserId(userId);
		obj.setId(id);
		obj.setTenKH(khachHang == null ? "" : khachHang);
		obj.setNgayCT(ngayCT == null ? "" : ngayCT);
		obj.setTongTien(tongTien == 0 ? 0 : tongTien);
		obj.setTrangThai(trangThai == null ? "0" : trangThai);
		String[] idSP = request.getParameterValues("idSP");
		String[] maSP = request.getParameterValues("maSP");
		String[] tenSP = request.getParameterValues("tenSP");
		String[] donViTinh = request.getParameterValues("donViTinh");
		String[] soLuong = request.getParameterValues("soLuong");
		String[] donGia = request.getParameterValues("donGia");
		String[] thanhTien = request.getParameterValues("thanhTien");
		List<ISanPham> listSP = new ArrayList<ISanPham>();
		if (maSP != null) {
			for (int i = 0; i < maSP.length; i++) {
				if (maSP[i].length() > 0) {
					ISanPham sp = new SanPham();
					sp.setMaSP(maSP[i]);
					sp.setTenSP(tenSP[i]);
					sp.setDVT(donViTinh[i]);
					sp.setId(idSP[i]);
					double dongia = 0;
					try {
						dongia = Double.parseDouble(donGia[i].replaceAll(",", ""));
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					sp.setDonGia(dongia);
					double soluong = 0;
					try {
						soluong = Double.parseDouble(soLuong[i].replaceAll(",", ""));
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					sp.setSoLuong(soluong);
					listSP.add(sp);
				}
			}
		}
		obj.setListSP(listSP);
		if (action.equals("save")) {
			if (id.length() > 0) {
				if (!obj.update()) {
					session.setAttribute("dhnew", obj);
					String nextJSP = "/TrainingGESO/pages/Erp/DonHangNewDuongNguyen.jsp";
					response.sendRedirect(nextJSP);
				} else {
					IDonHangList dhList = new DonHangList();
					dhList.init("");
					session.setAttribute("donHangList", dhList);
					String nextJSP = "/TrainingGESO/pages/Erp/DonHangListDuongNguyen.jsp";
					response.sendRedirect(nextJSP);
				}
			} else {
				if (userId != null)
					obj.setNguoiTao(userId);
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				obj.setNgayTao(dateFormat.format(date));
				if (!obj.save()) {
					session.setAttribute("dhnew", obj);
					String nextJSP = "/TrainingGESO/pages/Erp/DonHangNewDuongNguyen.jsp";
					response.sendRedirect(nextJSP);
				} else {
					IDonHangList dhList = new DonHangList();
					dhList.init("");
					session.setAttribute("donHangList", dhList);
					String nextJSP = "/TrainingGESO/pages/Erp/DonHangListDuongNguyen.jsp";
					response.sendRedirect(nextJSP);
				}
			}
		} else {
			session.setAttribute("dhnew", obj);
			String nextJSP = "/TrainingGESO/pages/Erp/DonHangNewDuongNguyen.jsp";
		}

	}
}
