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
 * Servlet implementation class SanphamListSvl_kiet05
 */
@WebServlet("/SanphamListSvl_kiet05")
public class SanphamListSvl_kiet05 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SanphamListSvl_kiet05() {
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
	    response.setContentType("text/html; charset=UTF-8");
	    
	    HttpSession session= request.getSession();
	    Utility util= new Utility();
	    
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String msg= "";
	    ISanphamList sp= new SanphamList();
	    sp.init("");
	    
	    session.setAttribute("sanpham", sp);
	    String nextJSP = "/TrainingGESO/pages/Erp/SanphamList_kiet05.jsp";
		response.sendRedirect(nextJSP);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Da lam gi dau");
		
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    
		ISanphamList obj= new SanphamList();
		String Ma=request.getParameter("ma");
	    String ten=request.getParameter("ten");
	    String donvitinh=request.getParameter("donvitinh");
	    String action=request.getParameter("action");
	    
	    /*System.out.println("Ma :"+ Ma);
	    System.out.println("Ten :"+ ten);
	    System.out.println("Dvt :"+ donvitinh);
	    System.out.println("Action :"+ action);*/
	    
	    obj.setMa(Ma);
	    obj.setTen(ten);
	    obj.setDonvitinh(donvitinh);
	    HttpSession session = request.getSession();
	    
	    if(action.equals("timkiem")){
	    	String query=getSearchQuery(request, obj);
	    	obj.init(query);
	    	session.setAttribute("sanpham", obj);
    		String nextJSP = "/TrainingGESO/pages/Erp/SanphamList_kiet05.jsp";
    		response.sendRedirect(nextJSP);
	    }
	    else if (action.equals("taomoi")){
	    	ISanpham spnew= new Sanpham();
	    	spnew.createRs();
	    	session.setAttribute("spnew", spnew);
    		String nextJSP = "/TrainingGESO/pages/Erp/Sanphamnew_kiet05.jsp";
    		response.sendRedirect(nextJSP);
	    }
	    else{
	    	obj.init("");
    		session.setAttribute("sanpham", obj);
    		String nextJSP = "/TrainingGESO/pages/Erp/SanphamList_kiet05.jsp";
    		response.sendRedirect(nextJSP);
	    }
		
	}

	private String getSearchQuery(HttpServletRequest request, ISanphamList obj) {
		Utility util=new Utility();
		String query =   "";
		query="SELECT SP.MA,SP.TEN,SP.TRANGTHAI,SP.PK_SEQ,DV.MA AS DONVI FROM SANPHAM SP "+
				" LEFT JOIN DONVI DV ON DV.PK_SEQ=SP.DONVI_FK where 1=1   ";
				 
		if(obj.getMa().length()>0){
			query+="  and  sp.ma LIKE '%"+ obj.getMa()+"%'";
		}
		if(obj.getTen().length()>0){
			query+="  AND sp.ten LIKE '%"+ obj.getTen()+"%'";
		}
		 
		if(obj.getDonvitinh().length()>0){
			query+=" AND sp.donvi_fk = '"+obj.getDonvitinh()+"'";
		}
		return query;
		
	}
}
