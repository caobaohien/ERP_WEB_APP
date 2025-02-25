package geso.erp.servlets.chuyenkho_thai;

import geso.dms.center.util.Utility;
import geso.erp.beans.chuyenkho_thai.IChuyenkho;
import geso.erp.beans.chuyenkho_thai.IChuyenkhoList;
import geso.erp.beans.chuyenkho_thai.imp.Chuyenkho;
import geso.erp.beans.chuyenkho_thai.imp.ChuyenkhoList;
 

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Chuyenkho
 */
public class ChuyenkhoSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChuyenkhoSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		Utility util = new Utility();
		String queryString = request.getQueryString();
		String Id = util.getId(queryString);
		String userId = util.getUserId(queryString);
		if (userId.length() == 0)
			userId = util.antiSQLInspection(request.getParameter("userId"));
		String action = util.getAction(queryString);
		
		String msg = "";  
		IChuyenkho ck=new Chuyenkho();
		ck.setId(Id);
		ck.init("");
		ck.CreateRs();
		session.setAttribute("chuyenkhonew", ck);
		String nextJSP = "/TrainingGESO/pages/Erp/ChuyenkhoNew_Thai.jsp";
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
	    Utility util = new Utility();
	    IChuyenkho ck = new Chuyenkho();
	    String Id = util.antiSQLInspection(request.getParameter("id"));
	    String userId = request.getParameter("userId");
	    ck.setId(Id==null?"":Id);
	    String Ngaychuyen= request.getParameter("ngaychuyen");
	    System.out.println("Ngày chứng từ: "+Ngaychuyen);
	    String Khochuyen= request.getParameter("khochuyen"); 
	    System.out.println("Kho chuyển: "+Khochuyen);
	    String Khonhan= request.getParameter("khonhan");
	    System.out.println("Kho nhận: "+Khonhan);
	    String Lydochuyen= request.getParameter("lydochuyen");
	    System.out.println("Lý do chuyển: "+Lydochuyen);
	    String action = request.getParameter("action");
	    System.out.println("Action: "+action);
	    String Trangthai = request.getParameter("trangthai");
	    System.out.println("Trang thai: "+Trangthai);
	    System.out.println("Id: "+Id);
 	    ck.setNgaychuyen(Ngaychuyen);
	    ck.setLydochuyen(Lydochuyen);
	    ck.setKhochuyen(Khochuyen);
	    ck.setKhonhan(Khonhan);
	    ck.setTrangthai(Trangthai);
	    ck.setUserID(userId);
	    
	    ck.CreateRs();
	    HttpSession session = request.getSession();
	    
	    if(action.equals("save")) 
    	{ 
    		if(Id.length() >0){
    			if(!ck.update()){
	    			// lưu không thành công
	    			 
    				ck.CreateRs();
	    			session.setAttribute("chuyenkhonew", ck);
	    	    	String nextJSP = "/TrainingGESO/pages/Erp/ChuyenkhoNew_Thai.jsp";
	    	    	response.sendRedirect(nextJSP);
	    		}else{
	    			IChuyenkhoList dh=new ChuyenkhoList();
	    			dh.init("");
	    		 	dh.CreateRs();
	    		 	session.setAttribute("chuyenkho", dh);
	    		    String nextJSP = "/TrainingGESO/pages/Erp/ChuyenkhoList_Thai.jsp";
	    			response.sendRedirect(nextJSP);
	    		}
    		} else{
	    		if(!ck.save()){
	    			// lưu không thành công
	    			 
	    			ck.CreateRs();
	    			session.setAttribute("chuyenkhonew", ck);
	    	    	String nextJSP = "/TrainingGESO/pages/Erp/ChuyenkhoNew_Thai.jsp";
	    	    	response.sendRedirect(nextJSP);
	    		}else{
	    			IChuyenkhoList dh=new ChuyenkhoList();
	    			dh.init("");
	    		 	dh.CreateRs();
	    		 	session.setAttribute("chuyenkho", dh);
	    		    String nextJSP = "/TrainingGESO/pages/Erp/ChuyenkhoList_Thai.jsp";
	    			response.sendRedirect(nextJSP);
	    		}
    		}
    	}else{
    		 
	    	ck.CreateRs();
	    	session.setAttribute("dhnew", ck);
	    	String nextJSP = "/TrainingGESO/pages/Erp/ChuyenkhoNew_Thai.jsp";	
    	}
	}

}
