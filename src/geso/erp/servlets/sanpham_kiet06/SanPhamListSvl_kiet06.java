package geso.erp.servlets.sanpham_kiet06;

import geso.dms.center.util.Utility;
import geso.erp.beans.sanpham_kiet06.ISanPham;
import geso.erp.beans.sanpham_kiet06.ISanPhamList;
import geso.erp.beans.sanpham_kiet06.imp.SanPham;
import geso.erp.beans.sanpham_kiet06.imp.SanPhamList;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SanPhamListSvl_kiet06
 */
@WebServlet("/SanPhamListSvl_kiet06")
public class SanPhamListSvl_kiet06 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SanPhamListSvl_kiet06() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		HttpSession session = request.getSession();
		Utility util = new Utility();

		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		if (userId.length() > 0) {
			userId = util.antiSQLInspection(request.getParameter("userId"));
		}
		String action = request.getParameter("action");
		String msg = "";
		
		ISanPhamList spList = new SanPhamList();
		spList.init("");
		session.setAttribute("spList", spList);
		String nextJSP = "/TrainingGESO/pages/Erp/SanPhamList_kiet06.jsp";
		response.sendRedirect(nextJSP);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset= UTF-8");
		
		String ma = request.getParameter("ma");
		String ten = request.getParameter("ten");
		String donViTinh = request.getParameter("donvitinh");
		String action = request.getParameter("action");
		
		ISanPhamList obj = new SanPhamList();
		obj.setMa(ma);
		obj.setTen(ten);
		obj.setDonViTinh(donViTinh);
		
		HttpSession session = request.getSession();
		if (action.equals("timkiem")){
			String query = getQuerySearch(request, obj);
			obj.init(query);
			session.setAttribute("spList", obj);
			String nextJSP = "/TrainingGESO/pages/Erp/SanPhamList_kiet06.jsp";
			response.sendRedirect(nextJSP);
		} else if (action.equals("taomoi")){
			ISanPham sp = new SanPham();
			sp.createRsDvt();
			session.setAttribute("SanPhamMoi", sp);
			String nextJSP = "/TrainingGESO/pages/Erp/SanPhamNew_kiet06.jsp";
			response.sendRedirect(nextJSP);
		} else {
			obj.init("");
			session.setAttribute("SanPhamList", obj);
			String nextJSP = "/TrainingGESO/pages/Erp/SanPhamList_kiet06.jsp";
			response.sendRedirect(nextJSP);
		}
	}

	protected String getQuerySearch(HttpServletRequest request, ISanPhamList spList){
		
		Utility util = new Utility();
		String query = "";
		query = "select SP.PK_SEQ, SP.MA, SP.TEN, SP.TRANGTHAI, DV.MA as DONVI from SANPHAM SP "
				+ "left join DONVI DV on DV.PK_SEQ = SP.DONVI_FK where 1 = 1 ";

		if (spList.getMa().length() > 0) {
			query += "and SP.MA like '%" + spList.getMa() + "%'";
		}
		if (spList.getTen().length() > 0) {
			query += " and SP.TEN like '%" + spList.getTen() + "%'";
		}
		if (spList.getDonViTinh().length() > 0) {
			query += " and SP.DONVI_FK like '%" + spList.getDonViTinh() + "%'";
		}
		return query;
	}
	
	
}
