package geso.erp.servlets.sanpham_tan;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import geso.dms.center.util.Utility;
import geso.erp.beans.sanpham_tan.ISanpham;
import geso.erp.beans.sanpham_tan.IThongtinsanpham;
import geso.erp.beans.sanpham_tan.imp.Sanpham;
import geso.erp.beans.sanpham_tan.imp.Thongtinsanpham;

/**
 * Servlet implementation class SanphamListSvl
 */
@WebServlet("/SanphamListSvl")
public class SanphamListSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SanphamListSvl() {
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
	    
	    ISanpham sp=new Sanpham();
	    sp.init(""); 
	    // khởi tạo ra 1 session
	    
	    session.setAttribute("obj", sp);
	    String nextJSP = "/TrainingGESO/pages/Erp/Sanphamlist.jsp";
		response.sendRedirect(nextJSP);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub

		ISanpham obj;
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    // 
	    String action = request.getParameter("action");
	    if (action == null){
	    	action = "";
	    }
	    obj = new Sanpham();
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
	   }
		
	    
	   /* 
	    Utility util = new Utility();
	    
		HttpSession session = request.getSession();
	    String userId = util.antiSQLInspection(request.getParameter("userId")); 
	    
		    if(action.equals("taomoi"))
		    {
		    	INhacungcap nccBean = new Nhacungcap();
		    	nccBean.setUserId(userId);
		    	
	    		 
		    	session.setAttribute("obj", nccBean);
	    		String nextJSP = "/TrainingGESO/pages/Erp/NhacungcapNew.jsp";
	    		response.sendRedirect(nextJSP);
		    }
	     
	    	else if(action.equals("savenew")) 
	    	{ 
	    	 
	    	}else{
	    		obj = new   NhacungcapList();
	    		obj.setUserId(userId);
	    		
		    	
	    			
	    	}
	
	     
	    */
	
		
	
		
	}

	private String getSearchQuery(HttpServletRequest request, ISanpham obj) {
		Utility util=new Utility();
		
		  
		
		String query =   "";
		 query= " SELECT ISNULL(SP.TRANGTHAI,'') AS TRANGTHAI ,SP.PK_SEQ,SP.MA,SP.TEN,SP.NGAYTAO ,DV.MA AS DONVI FROM SANPHAM SP LEFT "+
				" JOIN NHANVIEN NV ON NV.PK_SEQ =SP.NGUOITAO  "+
				" LEFT JOIN DONVI DV ON DV.PK_SEQ=SP.DONVI_FK  where "+
				" 1=1";
		if(obj.getMa().length()>0){
			query+=" AND sp.ma LIKE '%"+ obj.getMa()+"%'";
		}
		if(obj.getTen().length()>0){
			query+=" AND sp.ten LIKE '%"+ obj.getTen()+"%'";
		}
		 
		if(obj.getTrangthai().length()>0){
			query+=" AND sp.TRANGTHAI LIKE '"+obj.getTrangthai()+"'";
		}
		return query;
		
	}

}
