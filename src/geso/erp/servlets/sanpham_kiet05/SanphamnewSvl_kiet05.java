package geso.erp.servlets.sanpham_kiet05;

import geso.dms.center.util.Utility;
import geso.erp.beans.sanpham_kiet05.ISanpham;
import geso.erp.beans.sanpham_kiet05.ISanphamList;
import geso.erp.beans.sanpham_kiet05.imp.Sanpham;
import geso.erp.beans.sanpham_kiet05.imp.SanphamList;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SanphamnewSvl_kiet05
 */
@WebServlet("/SanphamnewSvl_kiet05")
public class SanphamnewSvl_kiet05 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SanphamnewSvl_kiet05() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		HttpSession session = request.getSession();
		Utility util = new Utility();

		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		if (userId.length() == 0)
			userId = util.antiSQLInspection(request.getParameter("userId"));

		String action = util.getAction(querystring);
		String id = util.getId(querystring);
		String msg = "";
		
		//update
		ISanpham sp= new Sanpham();
		sp.setId(id);
		sp.init();
		sp.createRs();
		session.setAttribute("spnew", sp);
		String nextJSP= "/TrainingGESO/pages/Erp/Sanphamnew_kiet05.jsp";
		response.sendRedirect(nextJSP);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		ISanpham obj = new Sanpham();
		String action = request.getParameter("action");
		if (action == null)
			action = "";
		
		Utility util= new Utility();
		HttpSession session= request.getSession();
		String userId= util.antiSQLInspection(request.getParameter("userId"));
		obj.setUserId(userId);
		
		String id = util.antiSQLInspection(request.getParameter("id"));
		obj.setId(id == null ? "" : id);

		String ma = util.antiSQLInspection(request.getParameter("ma"));
		obj.setMa(ma == null ? "" : ma);

		String ten = util.antiSQLInspection(request.getParameter("ten"));
		obj.setTen(ten == null ? "" : ten);

		String tinhtrang = util.antiSQLInspection(request
				.getParameter("tinhtrang"));
		obj.setTrangthai(tinhtrang == null ? "" : tinhtrang);

		String dvt = util.antiSQLInspection(request.getParameter("donvi"));
		obj.setDonvitinh(dvt == null ? "" : dvt);

		System.out.println("ma  : " + ma);
		System.out.println("ten : " + ten);
		System.out.println("don vi: " + dvt);
		System.out.println("trang thai " + tinhtrang);
		System.out.println("actiong: " + action);
		
		if (action.equals("save")) {
			if(id.length()>0){
				System.out.println("Update is here!");
				if (!obj.update()) {
					session.setAttribute("spnew", obj);
					String nextJSP = "/TrainingGESO/pages/Erp/Sanphamnew_kiet05.jsp";
					response.sendRedirect(nextJSP);
				} else {
					ISanphamList sp = new SanphamList();
					sp.init("");
					session.setAttribute("sanpham", sp);
					String nextJSP = "/TrainingGESO/pages/Erp/SanphamList_kiet05.jsp";
					response.sendRedirect(nextJSP);
				}
			}
			else{
			if (!obj.save()) {
				session.setAttribute("spnew", obj);
				String nextJSP = "/TrainingGESO/pages/Erp/Sanphamnew_kiet05.jsp";
				response.sendRedirect(nextJSP);
			} else {
				ISanphamList sp = new SanphamList();
				sp.init("");
				session.setAttribute("sanpham", sp);
				String nextJSP = "/TrainingGESO/pages/Erp/SanphamList_kiet05.jsp";
				response.sendRedirect(nextJSP);
			}
			}
		} else {
			session.setAttribute("spnew", obj);
			String nextJSP = "/TrainingGESO/pages/Erp/Sanphamnew_kiet05.jsp";
		}

	}

}
