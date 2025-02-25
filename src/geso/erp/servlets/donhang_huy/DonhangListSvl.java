package geso.erp.servlets.donhang_huy;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import geso.dms.center.util.Utility;
import geso.erp.beans.donhang_huy.IDonHangList;
import geso.erp.beans.donhang_huy.IDonHangNew;
import geso.erp.beans.donhang_huy.imp.DonHangList;
import geso.erp.beans.donhang_huy.imp.DonHangNew;


/**
 * Servlet implementation class SanphamListSvl
 */
public class DonhangListSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonhangListSvl() {
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
	    String action = util.getAction(querystring);
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    String Id=util.getId(querystring);
	    //
	    IDonHangList listdh=new DonHangList();
	    listdh.setUserId(userId);
	 
	    
	    // xu ly cac action
	    if(action.equals("chot"))
	    {
	    	listdh.setId(Id);
	    	listdh.chot();
	    }else if(action.equals("delete"))
	    {
	    	listdh.setId(Id);
	    	listdh.delete();
	    }
	    listdh.init("");
	    session.setAttribute("obj",listdh);
	    String nextJSP="/TrainingGESO/pages/Erp/DonHangList_Huy.jsp";
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
	    String action = request.getParameter("action");
	    if (action == null){
	    	action = "";
	    }
	    Utility util = new Utility();
		HttpSession session = request.getSession();
		// lay gia tri tu cac input 
	    String userId = util.antiSQLInspection(request.getParameter("userId")); 
	    String id = util.antiSQLInspection(request.getParameter("id"));
	    String madh=util.antiSQLInspection(request.getParameter("madh"));if(madh==null){madh="";}
	    String trangthai=util.antiSQLInspection(request.getParameter("trangthai"));if(trangthai==null){madh="";}
	    String tenkh=util.antiSQLInspection(request.getParameter("tenkh"));if(tenkh==null){tenkh="";}
	    // KHOI TAO DOI TUONG
	    IDonHangList dhlist=new DonHangList();
	    // set cac thuoc tinh vua lay vao DonHangList
	    dhlist.setUserId(userId);
	    dhlist.setId(id);
	    dhlist.setMaDH(madh);
	    dhlist.setTrangthai(trangthai);
	    dhlist.settenKH(tenkh);
	    // kiem tra action
	    if(action.equals("timkiem"))
	    {
	    	dhlist.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
	    	String search=getSearchQuery(request, dhlist);
	    	dhlist.init(search);
	    	session.setAttribute("obj",dhlist);
	    	 String nextJSP="/TrainingGESO/pages/Erp/DonHangList_Huy.jsp";
	  	    response.sendRedirect(nextJSP);
	    }
	    else if(action.equals("clear"))
	    {
	    	dhlist.init("");
	    	session.setAttribute("obj",dhlist);
	    	 String nextJSP="/TrainingGESO/pages/Erp/DonHangList_Huy.jsp";
	  	    response.sendRedirect(nextJSP);
	    }
	    else if(action.equals("new"))
	    {
	    	IDonHangNew dhnew=new DonHangNew();
	    	dhnew.CreateRs();
	    	session.setAttribute("obj", dhnew);
	    	 String nextJSP="/TrainingGESO/pages/Erp/DonHangNew_Huy.jsp";
		  	  response.sendRedirect(nextJSP);
	    }
	}
	// ham xu ly tim kiem\
	private String getSearchQuery(HttpServletRequest request,IDonHangList dhlist)
	{
		Utility util=new Utility();
		String query;
		query="select dh.PK_SEQ as madh,dh.NGAYCHUNGTU,kh.TEN,dh.TRANGTHAI,"
				+ " dh.TONGTIENTRUOCVAT as tongtien,dh.NGAYTAO,nv.TEN"
				+" from DONHANG dh left join KHACHHANG kh on dh.KHACHHANG_FK=kh.PK_SEQ "
				+ "left join NHANVIEN nv on dh.NGUOITAO=nv.PK_SEQ where 1=1";
		if(dhlist.getmaDH().length()>0)
		{
			query+="and dh.PK_SEQ like '%"+dhlist.getmaDH()+"%'";
		}
		if(dhlist.getTrangthai().length()>0)
		{
			query+="and dh.TRANGTHAI like'%"+dhlist.getTrangthai()+"%'";
		}
		if(dhlist.getTenKH().length()>0)
		{
			query+="and kh.TEN like '%"+dhlist.getTenKH()+"%'";
		}
			return query;
	}
	
}

