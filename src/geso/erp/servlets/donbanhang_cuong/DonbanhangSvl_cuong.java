package geso.erp.servlets.donbanhang_cuong;

import geso.dms.center.util.Utility;
import geso.erp.beans.donbanhang_cuong.IDonbanhangList_cuong;
import geso.erp.beans.donbanhang_cuong.IDonbanhangNew_cuong;
import geso.erp.beans.donbanhang_cuong.imp.DonbanhangList_cuong;
import geso.erp.beans.donbanhang_cuong.imp.DonbanhangNew_cuong;
import geso.erp.beans.sanpham_nhom2.ISanpham;
import geso.erp.beans.sanpham_nhom2.ISanphamList;
import geso.erp.beans.sanpham_nhom2.imp.Sanpham;
import geso.erp.beans.sanpham_nhom2.imp.SanphamList;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DonbanhangSvl_cuong
 */
@WebServlet("/DonbanhangSvl_cuong")
public class DonbanhangSvl_cuong extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonbanhangSvl_cuong() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		// dung để điều hướng 
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    HttpSession session=request.getSession();
	  
	    IDonbanhangNew_cuong dbhnew=new DonbanhangNew_cuong();
	    dbhnew.init("");
	    session.setAttribute("donbanhangNew", dbhnew);
	    String nextJSP="/TrainingGESO/pages/Erp/DonbanhangNew_cuong.jsp";
	    response.sendRedirect(nextJSP);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		HttpSession session = request.getSession();
		//
		String userid = request.getParameter("userId");
		String khachhang = request.getParameter("khachhang");
		String ngaychungtu = request.getParameter("ngaychungtu");
		String tongtien = request.getParameter("tongtien");
		String action = request.getParameter("action");
		String id = request.getParameter("id");
		IDonbanhangNew_cuong dbhnew = new DonbanhangNew_cuong();
		dbhnew.setId(id);
		dbhnew.setUserId(userid);
		dbhnew.setKhachHang(khachhang);
		dbhnew.setNgay(ngaychungtu);
		dbhnew.setTongTien(tongtien);

		if (action.equals("save")) {
			if (!dbhnew.save()) {
				// khong luu dc
				dbhnew.getrskhachhang();
				session.setAttribute("donbanhangNew", dbhnew);
				String nextJSP = "/TrainingGESO/pages/Erp/DonbanhangNew_cuong.jsp";
				response.sendRedirect(nextJSP);
			} else {
				// luu dc
				IDonbanhangList_cuong dbh = new DonbanhangList_cuong();
				dbh.init("");
				session.setAttribute("donbanhang", dbh);
				String nextJSP = "/TrainingGESO/pages/Erp/DonbanhangList_cuong.jsp";
				response.sendRedirect(nextJSP);
			}

		} else {
			session.setAttribute("donbanhangNew", dbhnew);
			String nextJSP = "/TrainingGESO/pages/Erp/DonbanhangNew_cuong.jsp";
		}

	}

}
