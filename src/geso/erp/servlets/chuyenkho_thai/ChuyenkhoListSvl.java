package geso.erp.servlets.chuyenkho_thai;

import geso.dms.center.util.Utility;
import geso.erp.beans.chuyenkho_thai.IChuyenkho;
import geso.erp.beans.chuyenkho_thai.IChuyenkhoList;
import geso.erp.beans.chuyenkho_thai.imp.Chuyenkho;
import geso.erp.beans.chuyenkho_thai.imp.ChuyenkhoList;
import geso.erp.beans.donbanhang_duongnguyen.IDonHang;
import geso.erp.beans.donbanhang_duongnguyen.Imp.DonHang;
import geso.erp.beans.donbanhang_thai.IDonbanhang;
import geso.erp.beans.donbanhang_thai.IDonbanhangList;
import geso.erp.beans.donbanhang_thai.imp.Donbanhang;
import geso.erp.beans.donbanhang_thai.imp.DonbanhangList;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ChuyenkhoListSvl
 */
public class ChuyenkhoListSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChuyenkhoListSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
 	    HttpSession session = request.getSession();	 
	    Utility util = new Utility();
	    String querystring = request.getQueryString();
	    String UserID = util.getUserId(querystring);
 	    if (UserID.length()==0)
 	    	UserID = util.antiSQLInspection(request.getParameter("UserID")); 
	    String msg = "";    
	    IChuyenkhoList ck=new ChuyenkhoList();
	    
	    ck.init("");
	    ck.CreateRs();
	 	session.setAttribute("chuyenkho", ck);
	    String nextJSP = "/TrainingGESO/pages/Erp/ChuyenkhoList_Thai.jsp";
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
	    
	    
	    IChuyenkhoList ck = new ChuyenkhoList();
	    
	    String Ma= request.getParameter("ma");
	    String Khochuyen= request.getParameter("khochuyen"); 
	    String Khonhan= request.getParameter("khonhan");
	    
	    String Trangthai= request.getParameter("trangthai1");
	    
	    String action = request.getParameter("action"); 
	    System.out.println("Ma: "+Ma+" Kho chuyen: "+Khochuyen+" Kho nhan: "+Khonhan+"Trang thai: "+Trangthai+" action: "+action); 
	    
	    ck.setMa(Ma);
	    ck.setKhochuyen(Khochuyen);
	    ck.setKhonhan(Khonhan);
	    ck.setTrangthai(Trangthai);
	    ck.CreateRs();
	    HttpSession session = request.getSession();
	    if(action.equals("timkiem")){
	    	String query=getSearchQuery(request, ck);
	    	ck.init(query);
	    	session.setAttribute("chuyenkho", ck);
	    	String nextJSP = "/TrainingGESO/pages/Erp/ChuyenkhoList_Thai.jsp";
	    	response.sendRedirect(nextJSP);
	    } else if(action.equals("taomoi")){
	    	IChuyenkho obj = new Chuyenkho();
	    	obj.CreateRs();
			session.setAttribute("chuyenkhonew", obj);
			String nextJSP = "/TrainingGESO/pages/Erp/ChuyenkhoNew_Thai.jsp";
			response.sendRedirect(nextJSP);
	    }
	    else {
	    	ck.init("");
	    	session.setAttribute("chuyenkho", ck);
	    	String nextJSP = "/TrainingGESO/pages/Erp/ChuyenkhoList_Thai.jsp";
	    	response.sendRedirect(nextJSP);
	    }
	    
 
	} 
	private String getSearchQuery(HttpServletRequest request, IChuyenkhoList ck) {
 
		String query="select CK.PK_SEQ as Ma, CK.LYDO as Lydochuyen, NV.TEN as Nguoitao,CK.NGAYTAO as Ngaytao, NV.TEN as Nguoisua, CK.NGAYSUA as Ngaysua,CK.TRANGTHAI as Trangthai"
			 + " from CHUYENKHO as CK left join KHO as K on CK.KHOCHUYEN=K.PK_SEQ and CK.KHONHAN=K.PK_SEQ"
			 + " left join NHANVIEN as NV on CK.NGUOITAO=NV.PK_SEQ and CK.NGUOISUA=NV.PK_SEQ"
			 + " WHERE 1=1";
		 
		if(ck.getMa().length()>0){
			query+=" AND cast(CK.PK_SEQ as varchar(10)) like '"+ck.getMa().toString()+"%'";
		}
		if(ck.getKhochuyen().length()>0){
			query+=" AND CK.KHOCHUYEN = "+ck.getKhochuyen()+"";
		}
		 
		if(ck.getKhonhan().length()>0){
			query+=" AND CK.KHONHAN LIKE '"+ck.getKhonhan()+"'";
		}
		if(ck.getTrangthai().length()>0){
			query+=" AND CK.TRANGTHAI LIKE '"+ck.getTrangthai()+"'";
		}
		return query;
 	}

}
