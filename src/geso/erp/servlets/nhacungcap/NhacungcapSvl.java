package geso.erp.servlets.nhacungcap;

import geso.dms.center.util.Utility;
import geso.erp.beans.khachhang.IKhachhangList;
import geso.erp.beans.khachhang.imp.KhachhangList;
import geso.erp.beans.nhacungcap.INhacungcap;
import geso.erp.beans.nhacungcap.INhacungcapList;
import geso.erp.beans.nhacungcap.imp.Nhacungcap;
import geso.erp.beans.nhacungcap.imp.NhacungcapList;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class NhacungcapSvl
 */
@WebServlet("/NhacungcapSvl")
public class NhacungcapSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NhacungcapSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// dùng để lấy dữ liệu
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
	    
	    String action = util.getAction(querystring);
	    
	    
	    String Id = util.getId(querystring);
	    
	    String msg = "";
	    
	    INhacungcapList ncc=new NhacungcapList();
	    ncc.init(""); 
	    session.setAttribute("obj", ncc);
	    String nextJSP = "/TrainingGESO/pages/Erp/Nhacungcaplist.jsp";
		response.sendRedirect(nextJSP);
	     
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		INhacungcapList obj;
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    
	    String action = request.getParameter("action");
	    if (action == null){
	    	action = "";
	    }
	    
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
	    		
		    	
	    		obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));

	    		String search = getSearchQuery(request, obj);
		    	
		    	obj.init(search);

		    	session.setAttribute("obj", obj);
		    	response.sendRedirect("/TrainingGESO/pages/Erp/Nhacungcaplist.jsp");	
	    	}
	
	     
	    
	
		
	}
	private String getSearchQuery(HttpServletRequest request, INhacungcapList obj)
	{
		Utility util=new Utility();
		
		String trangthai = request.getParameter("trangthai");
		if(trangthai == null)
			trangthai = "";
		obj.setTrangthai(trangthai);
		

		String ma =util.antiSQLInspection(request.getParameter("ma"));
		if(ma == null)
			ma = "";
		obj.setMa(ma);
		
		String ten = request.getParameter("ten");
		if(ten == null)
			ten = "";
		obj.setTen(ten);
		
		
		 
		
		String query =   "";
		  	
		query= " SELECT  KH.PK_SEQ AS SOCHUNGTU, KH.MA   " +
	    " ,KH.NGAYTAO,ISNULL(KH.TEN,'') AS TEN,NV.TEN AS NGUOITAO , ISNULL(KH.TRANGTHAI,'') AS TRANGTHAI  "+
		" FROM "+
		" NHACUNGCAP KH   "+
		" LEFT JOIN NHANVIEN NV ON NV.PK_SEQ=KH.NGUOITAO  where " +
				" 1=1";
		if(ma.length()>0){
			query+=" AND KH.timkiem LIKE '%"+util.replaceAEIOU(obj.getMa())+"%'";
		}
		 
		if(trangthai.length()>0){
			query+=" AND KH.TRANGTHAI LIKE '"+trangthai+"'";
		}
		return query;
		
	}
 
}
