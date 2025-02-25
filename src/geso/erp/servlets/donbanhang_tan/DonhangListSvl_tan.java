package geso.erp.servlets.donbanhang_tan;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import geso.dms.center.util.Utility;
import geso.erp.beans.donbanhang_tan.IDonhangList_tan;
import geso.erp.beans.donbanhang_tan.IDonhangNew_tan;
import geso.erp.beans.donbanhang_tan.imp.DonhangList_tan;
import geso.erp.beans.donbanhang_tan.imp.DonhangNew_tan;
import geso.erp.db.sql.dbutils;

/**
 * Servlet implementation class DonhangListSvl
 */
@WebServlet("/DonhangListSvl_tan")
public class DonhangListSvl_tan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonhangListSvl_tan() {
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
	    
	/*    String action = util.getAction(querystring);
	    
	    
	    String Id = util.getId(querystring);*/
	    
	    String msg = "";    
	    IDonhangList_tan dh=new DonhangList_tan();
	    
	    /*-----------*/
		// khởi tạo ra 1 session

		// cách lấy action (hành động truyền vào, và id truyền vào)
		
		System.out.println("querystring : "+querystring);
		String action = util.getAction(querystring);
		String action_getbangparameter= request.getParameter("update");
		
		System.out.println("action_getbangparameter : "+action_getbangparameter);
		String dmhId = util.getId(querystring);
		
		 if (action.equals("delete"))
		    {	
			 	dh.setId(dmhId);
				dh.Delete_dh();
				dh.init("");
		    }
		    /*else  	if(action.equals("chot"))
			    	{
			    		dbutils db = new dbutils();
			    		db.update(" update donhang set trangthai = '1' where pk_seq = '" + dmhId + "'");
			    		db.shutDown();
			    		 
			    		
			    	}*/
		 	dh.init("");
		 	session.setAttribute("obj", dh);
		    String nextJSP = "/TrainingGESO/pages/Erp/DonbanhangList_Tan.jsp";
			response.sendRedirect(nextJSP);
			/*------------*/
	    
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		IDonhangList_tan obj;
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    // 
	    String action = request.getParameter("action");
	    if (action == null){
	    	action = "";
	    }
	    obj = new DonhangList_tan();
	    System.out.println("action gi day : "+action);
	    
	    String MaDH= request.getParameter("MaDH");
	    if (MaDH == null){
	    	MaDH = "";
	    }
	    obj.setMaDH(MaDH);
	    System.out.println(MaDH);
	    
	    
	    String Khachhang= request.getParameter("Khachhang");
	    if (Khachhang == null){
	    	Khachhang = "";
	    }
	    obj.setKhachhang(Khachhang);
	    System.out.println(Khachhang);
	    String Trangthai = request.getParameter("Trangthai");
	    if(Trangthai==null){
	    	Trangthai="";
	    }
	    obj.setTrangthai(Trangthai);
	    
	    
	    HttpSession session = request.getSession();	 
	    Utility util = new Utility();
	   
	    
	   if(action.equals("timkiem")){
		   obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
		   
		   String search = getSearchQuery(request, obj);		   
		   obj.init(search);
		   session.setAttribute("obj", obj);
		   response.sendRedirect("/TrainingGESO/pages/Erp/DonbanhangList_Tan.jsp");
	    	
	   }else if(action.equals("taomoi")) {
		   
			    IDonhangNew_tan dh=new DonhangNew_tan();
			    session.setAttribute("obj", dh);
			    dh.CreateRs();
			    String nextJSP = "/TrainingGESO/pages/Erp/DonbanhangNew_tan.jsp";
				response.sendRedirect(nextJSP);
				
	   }
	   else if(action.equals("refresh")){
		   IDonhangList_tan dh=new DonhangList_tan();
		    dh.init(""); 
		    // khởi tạo ra 1 session
		    
		    session.setAttribute("obj", dh);
		    String nextJSP = "/TrainingGESO/pages/Erp/DonbanhangList_Tan.jsp";
			response.sendRedirect(nextJSP);
	   }
	   
	}

	private String getSearchQuery(HttpServletRequest request, IDonhangList_tan obj) {
		Utility util=new Utility();
		
		String query = "";
		query= "SELECT DH.PK_SEQ AS MaDH, DH.NGAYCHUNGTU as Ngaychungtu, DH.KHACHHANG_FK as Khachhang, ISNULL(DH.TRANGTHAI,'') AS TRANGTHAI, "
				 + "DH.TONGTIENTRUOCVAT AS TongtientruocVAT, DH.NGAYTAO, DH.NGUOITAO, KH.TEN as Ten "
				 + "FROM DONHANG DH LEFT JOIN NHANVIEN NV ON NV.PK_SEQ =DH.NGUOITAO "
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
