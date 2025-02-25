package geso.erp.servlets.THONGTINHOCSINH;

import java.io.IOException;
import java.sql.ResultSet;


import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import geso.dms.center.util.Utility;
import geso.erp.beans.THONTINHOCSINH.IHoSoList;
import geso.erp.beans.THONTINHOCSINH.IHoSoUpdate;
import geso.erp.beans.THONTINHOCSINH.imp.HoSoList;
import geso.erp.beans.THONTINHOCSINH.imp.HoSoUpdate;
import geso.erp.db.sql.dbutils;


/**
 * Servlet implementation class SanphamListSvl
 */
public class HoSoListSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HoSoListSvl() {
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
		    System.out.println("day la user "+ userId);
		    if (userId.length()==0)
		    	userId = util.antiSQLInspection(request.getParameter("userId"));
		    String action = util.getAction(querystring);
		    System.out.println("day la action "+ action);
		    String Id = util.getId(querystring);
		    System.out.println("day la id "+ Id);
		    String msg = "";
		    IHoSoList hslist=new HoSoList();
		    hslist.setUserId(userId);
		    hslist.init("");
		    if(action.equals("delete"))
		    {
		    	System.out.println("dang o dele");
		    	hslist.setId(Id);
		    	hslist.delete_hs();
		    	hslist.init("");
		    }
		    session.setAttribute("obj",hslist);
		    String nextJSP="/TrainingGESO/pages/Erp/HoSoList.jsp";
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
	    HttpSession session = request.getSession();
	    Utility util = new Utility();
	    String action = request.getParameter("action");
	    String userId = request.getParameter("userId");
	    String litsquocgia=request.getParameter("litsquocgia");
	    String tenhs=util.antiSQLInspection(request.getParameter("tenhs"));
	    String dienthoai=util.antiSQLInspection(request.getParameter("dienthoai"));
	    String trangthaitk=util.antiSQLInspection(request.getParameter("trangthaitk"));
	    System.out.println("ten hs:"+tenhs);
	    System.out.println("ma quoc gia:"+litsquocgia);
	    if (action == null){
	    	action = "";
	    }
	    IHoSoList litshs=new HoSoList();
	    litshs.setPk_seq_quocgia(litsquocgia);
	    litshs.setHoten(tenhs);
	    litshs.setDienthoai(dienthoai);
	    litshs.setTrangthai(trangthaitk);
	    if(action.equals("new"))
    	{	IHoSoUpdate hsnew=new HoSoUpdate();
    		hsnew.CreateRs();
    		 session.setAttribute("obj",hsnew);
 		   String nextJSP="/TrainingGESO/pages/Erp/HoSoUpdate.jsp";
 		   response.sendRedirect(nextJSP);
    	}else if(action.equals("timkiem"))
    	{
    		System.out.println("dang trong tim kiem");
    		litshs.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
			String search = getSearchQuery(request, litshs);
			litshs.init(search);
	     	 session.setAttribute("obj",litshs);
	  		 String nextJSP="/TrainingGESO/pages/Erp/HoSoList.jsp";
	  		 response.sendRedirect(nextJSP);
    	}
    	else if(action.equals("clear"))
    	{
    		litshs.setUserId(userId);
    		litshs.init("");
	     	 session.setAttribute("obj",litshs);
	  		 String nextJSP="/TrainingGESO/pages/Erp/HoSoList.jsp";
	  		 response.sendRedirect(nextJSP);
    	}
	}
	
	private String getSearchQuery(HttpServletRequest request, IHoSoList litshs) {
		Utility util=new Utility();
	
		String query;
		 query=	 " select hs.PK_SEQ,hs.TEN,hs.NGAYSINH,hs.TRINHDOHOCVAN,hs.KHANANGHOCVAN,"
			 		+ "hs.KHANANGTIENGANH,hs.TRANGTHAI,hs.DIENTHOAI from HOSO hs left join "
			 		+ "NHANVIEN nv on nv.PK_SEQ=hs.NGUOITAO "
			 		+ "where 1=1";
		

		if(litshs.getHoten().length()>0){
			query += " AND hs.TEN LIKE '%" + litshs.getHoten()+ "%'";
		}
		if(litshs.getDienthoai().length()>0)
		{
			query += " AND hs.DIENTHOAI LIKE '%" + litshs.getDienthoai()+ "%'";
		}
		if(litshs.getTrangthai().length()>0)
		{
			query += " AND hs.TRANGTHAI LIKE '%" + litshs.getTrangthai()+ "%'";
		}
		return query;
		
	}
}
	

