package geso.erp.servlets.donbanhang_thai;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import geso.dms.center.util.Utility;
import geso.erp.beans.donbanhang_thai.IDonbanhang;
import geso.erp.beans.donbanhang_thai.IDonbanhangList;
import geso.erp.beans.donbanhang_thai.imp.Donbanhang;
import geso.erp.beans.donbanhang_thai.imp.DonbanhangList;
import geso.erp.db.sql.dbutils;

/**
 * Servlet implementation class DonhangListSvl
 */
@WebServlet("/DonhangListSvl_thai")
public class DonbanhangListSvl_thai extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonbanhangListSvl_thai() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		// dùng để điều hướng 
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    
	    HttpSession session = request.getSession();	 
	    Utility util = new Utility();
	    
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId")); 
	    String msg = "";    
	    IDonbanhangList dh=new DonbanhangList();
 
		 	dh.init("");
		 	dh.CreateRs();
		 	session.setAttribute("obj", dh);
		    String nextJSP = "/TrainingGESO/pages/Erp/DonbanhangList_thai.jsp";
			response.sendRedirect(nextJSP);
			/*------------*/
	    
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		IDonbanhangList obj;
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    
	    
	    obj = new DonbanhangList();
	    
	    String MaDH= request.getParameter("MaDH");
	    String trangthai= request.getParameter("trangthai"); // sai chinh ta trangthai
	    String Khachhang= request.getParameter("Khachhang"); 
	    String action = request.getParameter("action");
	    
	    obj.setMaDH(MaDH);
	    obj.setTrangthai(trangthai);
	    obj.setKhachhang(Khachhang);
	    HttpSession session = request.getSession();	
	    if(action.equals("timkiem")){
	    	String query=getSearchQuery(request, obj);
	    	obj.init(query);
	    	session.setAttribute("obj", obj);
	    	String nextJSP = "/TrainingGESO/pages/Erp/DonbanhangList_thai.jsp";
	    	response.sendRedirect(nextJSP);
	    }else if(action.equals("taomoi")){
	    	IDonbanhang dhnew=new Donbanhang();
	    	dhnew.CreateRs();
	    	session.setAttribute("dhnew", dhnew);
	    	String nextJSP = "/TrainingGESO/pages/Erp/Donbanhang_thai.jsp";
	    	response.sendRedirect(nextJSP);
	    	
	    }
	    else {
	    	obj.init("");
	    	session.setAttribute("obj", obj);
	    	String nextJSP = "/TrainingGESO/pages/Erp/DonbanhangList_thai.jsp";
	    	response.sendRedirect(nextJSP);
	    }
 
	} 
	private String getSearchQuery(HttpServletRequest request, IDonbanhangList obj) {
		Utility util=new Utility();
		
		String query = "";
		query= "SELECT DH.PK_SEQ AS MaDH, DH.NGAYCHUNGTU as Ngaychungtu, DH.KHACHHANG_FK as Khachhang, ISNULL(DH.TRANGTHAI,'') AS Trangthai,"
			 + "DH.TONGTIEN  AS Tongtien, DH.NGAYTAO as Ngaytao, DH.NGUOITAO as Nguoitao, KH.TEN as Ten "
			 + "FROM BANHANG DH LEFT JOIN NHANVIEN NV ON NV.PK_SEQ =DH.NGUOITAO "
			 + " LEFT JOIN KHACHHANG KH ON KH.PK_SEQ = DH.KHACHHANG_FK "
			 + "WHERE 1=1";
		if(obj.getMaDH().length()>0){
			query+=" AND cast(DH.PK_SEQ as varchar(10)) like '"+obj.getMaDH().toString()+"%'";
		}
		if(obj.getKhachhang().length()>0){
			query+=" AND DH.KHACHHANG_FK = "+obj.getKhachhang()+"";
		}
		 
		if(obj.getTrangthai().length()>0){
			query+=" AND DH.TRANGTHAI LIKE '"+obj.getTrangthai()+"'";
		}
		return query;
		
	}
}