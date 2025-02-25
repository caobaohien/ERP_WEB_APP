package geso.erp.servlets.sanpham_nhom2;

import geso.dms.center.util.Utility;
import geso.erp.beans.sanpham_nhom2.ISanpham;
import geso.erp.beans.sanpham_nhom2.ISanphamList;
import geso.erp.beans.sanpham_nhom2.imp.Sanpham;
import geso.erp.beans.sanpham_nhom2.imp.SanphamList;
 
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SanphamNhom2ListSvl
 */
public class SanphamNhom2ListSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SanphamNhom2ListSvl() {
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
	    
	    HttpSession session = request.getSession();	 
	    Utility util = new Utility();
	    
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	/*    String action = util.getAction(querystring);
	    
	    
	    String Id = util.getId(querystring);*/
	    
	    String msg = "";
	    
	    ISanphamList sp=new SanphamList();
	    sp.init(""); 
	    // khởi tạo ra 1 session
	    
	    session.setAttribute("sanpham", sp);
	    String nextJSP = "/TrainingGESO/pages/Erp/SanphamListNhom2.jsp";
		response.sendRedirect(nextJSP);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("CHUA LAM GIF HET");

		ISanphamList obj;
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    
	    String Ma=request.getParameter("ma");
	    String ten=request.getParameter("ten");
	    String donvitinh=request.getParameter("donvitinh");
	    String action=request.getParameter("action");
	    obj =new SanphamList();
	    obj.setMa(Ma);
	    obj.setTen(ten);
	    obj.setDonvitinh(donvitinh);
	    HttpSession session = request.getSession();	 
	    System.out.println("ma "+Ma +" Ten : "+ten + "donvitinh : "+donvitinh+" action :"+action);

	    
	    if(action.equals("timkiem")){
	    	String query=getSearchQuery(request, obj);
	    	obj.init(query);
	    	session.setAttribute("sanpham", obj);
    		String nextJSP = "/TrainingGESO/pages/Erp/SanphamListNhom2.jsp";
    		response.sendRedirect(nextJSP);
	    				
	    }else if(action.equals("taomoi")){
	    		ISanpham spnew=new Sanpham();
	    		spnew.CreateRs();
	    	 	session.setAttribute("spnew", spnew);
	    		String nextJSP = "/TrainingGESO/pages/Erp/Sanphamnewnhom2.jsp";
	    		response.sendRedirect(nextJSP);
	    		
	    		
	    }
	    else{
				obj.init("");
	    		session.setAttribute("sanpham", obj);
	    		String nextJSP = "/TrainingGESO/pages/Erp/SanphamListNhom2.jsp";
	    		response.sendRedirect(nextJSP);
	    }
	    
	   /* // 
	    String action = request.getParameter("action");
	    if (action == null){
	    	action = "";
	    }
	    obj = new SanphamList();
	    System.out.println("action gi day : "+action);
	    
	    String ma= request.getParameter("ma");
	    if (ma == null){
	    	ma = "";
	    }
	    System.out.println("mma gi day : "+ma);
	    
	    String ten= request.getParameter("ten");
	    if (ten == null){
	    	ten = "";
	    }
	    
	    String trangthai = request.getParameter("trangthai");
	    if(trangthai==null){
	    	trangthai="";
	    }
	    obj.setTrangthai(trangthai);
	    
	    System.out.println("ten gi day : "+ten);
	    HttpSession session = request.getSession();	 
	    Utility util = new Utility();
	   
	    obj.setMa(ma);
	    obj.setTen(ten);
	    
	   if(action.equals("timkiem")){
		   
		   		obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));

   				String search = getSearchQuery(request, obj);
	    	
	    	obj.init(search);

	    	session.setAttribute("obj", obj);
	    	response.sendRedirect("/TrainingGESO/pages/Erp/Sanphamlist.jsp");
	    	
	   }else if(action.equals("taomoi")) {
		   
			    IThongtinsanpham sp=new Thongtinsanpham();
			    session.setAttribute("obj", sp);
			    sp.CreateRs();
			    String nextJSP = "/TrainingGESO/pages/Erp/sanphamnew.jsp";
				response.sendRedirect(nextJSP);
	   }*/
		
		
		
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
