package geso.erp.servlets.donbanhang_tho;

import geso.dms.center.util.Utility;
import geso.erp.beans.donbanhang_tho.IDonbanhangListTho;
import geso.erp.beans.donbanhang_tho.IDonbanhangTho;
import geso.erp.beans.donbanhang_tho.imp.DonbanhangListTho;
import geso.erp.beans.donbanhang_tho.imp.DonbanhangTho;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

/**
 * Servlet implementation class DonbanhangNewThoSvl
 */
@WebServlet("/DonbanhangNewThoSvl")
public class DonbanhangNewThoSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonbanhangNewThoSvl() {
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
	    HttpSession session= request.getSession();
	   // Utility util=new Utility();
	    //String querystring=request.getQueryString();
	   // String userId=util.getUserId(querystring);
	   // String action=util.getAction(querystring);
	    IDonbanhangTho dhnew= new DonbanhangTho();
	    String id=request.getParameter("update");
	    dhnew.setId(id);
	    dhnew.init("");
	    session.setAttribute("donhangnew", dhnew);
		String nextJSP="/TrainingGESO/pages/Erp/DonbanhangTho.jsp";
	    response.sendRedirect(nextJSP);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		// dung để điều hướng 
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session= request.getSession();
		String userId=request.getParameter("userId");
		String tongtien=request.getParameter("tongtien");
		String ngaychungtu=request.getParameter("ngaychungtu");
		String khachhang=request.getParameter("khachhang");
		String action=request.getParameter("action");
		String id=request.getParameter("id");
		IDonbanhangTho dhnew=new DonbanhangTho();
		dhnew.setId(id);
		dhnew.setuserId(userId);
		dhnew.setTongtien(tongtien);
		dhnew.setngayCT(ngaychungtu);
		dhnew.settenKH(khachhang);
		if(action.equals("save"))
		{		
			if(id.length()>0)
			{	if(!dhnew.update())
				{
					session.setAttribute("donhangnew", dhnew);
					String nextJSP="/TrainingGESO/pages/Erp/DonbanhangTho.jsp";
			    	response.sendRedirect(nextJSP);
				}else
				{
					IDonbanhangListTho dh=new DonbanhangListTho();
			    	dh.init("");
			    	session.setAttribute("donhang", dh);
			    	String nextJSP="/TrainingGESO/pages/Erp/DonbanhangListTho.jsp";
			    	response.sendRedirect(nextJSP);
				}
			}else
			{
				if(!dhnew.save())
				{
					session.setAttribute("donhangnew", dhnew);
					String nextJSP="/TrainingGESO/pages/Erp/DonbanhangTho.jsp";
			    	response.sendRedirect(nextJSP);
				}else
				{
					IDonbanhangListTho dh=new DonbanhangListTho();
			    	dh.init("");
			    	session.setAttribute("donhang", dh);
			    	String nextJSP="/TrainingGESO/pages/Erp/DonbanhangListTho.jsp";
			    	response.sendRedirect(nextJSP);
				}
			}
				
		}
		else
		{
			session.setAttribute("donhangnew", dhnew);
			String nextJSP="/TrainingGESO/pages/Erp/DonbanhangTho.jsp";
		}
		
		
	}

}
