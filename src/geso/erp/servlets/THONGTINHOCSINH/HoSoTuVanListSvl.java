package geso.erp.servlets.THONGTINHOCSINH;

import java.io.IOException;
import java.sql.ResultSet;


import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import geso.dms.center.util.Utility;
import geso.erp.beans.THONTINHOCSINH.IHoSoTuVanList;
import geso.erp.beans.THONTINHOCSINH.IHoSoList;
import geso.erp.beans.THONTINHOCSINH.IHoSoUpdate;
import geso.erp.beans.THONTINHOCSINH.imp.HoSoTuVanList;
import geso.erp.beans.THONTINHOCSINH.imp.HoSoList;
import geso.erp.beans.THONTINHOCSINH.imp.HoSoUpdate;
import geso.erp.db.sql.dbutils;


/**
 * Servlet implementation class SanphamListSvl
 */
public class HoSoTuVanListSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HoSoTuVanListSvl() {
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
		    IHoSoTuVanList hstvlist=new HoSoTuVanList();
		    hstvlist.setUserId(userId);
		    hstvlist.init("");
		    if(action.equals("delete"))
		    {
		    	 System.out.println("dang o delete ");
		    	 hstvlist.setId(Id);
		    	 hstvlist.delete_hs();
		    	  hstvlist.init("");
		    }
		   
			session.setAttribute("obj",hstvlist);
		   	response.sendRedirect("/TrainingGESO/pages/Erp/HoSoTuVanList.jsp");
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
	    String mahs=request.getParameter("mahs");
	    String tiemnangseach=request.getParameter("tiemnangseach");
	    
	   
	    String trangthaitk=util.antiSQLInspection(request.getParameter("trangthaitk"));
	    String querystring = request.getQueryString();
	    String Id = util.getId(querystring);
	    if (action == null){
	    	action = "";
	    }
	    IHoSoTuVanList hstv=new HoSoTuVanList();
	    hstv.setMahoso(mahs);
	    hstv.setTiemnang(tiemnangseach);
	    hstv.setTrangthai(trangthaitk);
	    if(action.equals("save"))
    	{	
	    	if(!hstv.save())	// khong thuc hien duoc ham save
    		{
	    		IHoSoUpdate hsnew=new HoSoUpdate();
    			System.out.println("dang o save tren");
    			System.out.println("khong save");
    			hsnew.setId(Id);
		    	hsnew.CreateRs();
		    	hsnew.init();
    			session.setAttribute("obj", hsnew);
    			response.sendRedirect("/TrainingGESO/pages/Erp/HoSoTuVanSvl.jsp");
    		}
    		else	// save thanh cong
    		{
    			System.out.println("vao save");
    			IHoSoList lisths=new HoSoList();
    			lisths.setUserId(userId);
    			System.out.println(" save");
    			lisths.init("");
    			session.setAttribute("obj", lisths);
    			response.sendRedirect("/TrainingGESO/pages/Erp/HoSoTuVanList.jsp");
    		}
    	}else if(action.equals("timkiem"))
    	{
    		hstv.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
    		String search = getSearchQuery(request, hstv);
    		hstv.init(search);
	     	 session.setAttribute("obj",hstv);
	  		 String nextJSP="/TrainingGESO/pages/Erp/HoSoTuVanList.jsp";
	  		 response.sendRedirect(nextJSP);
    	}else if(action.equals("clear"))
    	{
    		hstv.setUserId(userId);
    		hstv.init("");
	     	 session.setAttribute("obj",hstv);
	  		 String nextJSP="/TrainingGESO/pages/Erp/HoSoTuVanList.jsp";
	  		 response.sendRedirect(nextJSP);
    	}
    	
	}
	private String getSearchQuery(HttpServletRequest request, IHoSoTuVanList hstv) {
		Utility util=new Utility();
		System.out.println("vao seah");
		String query;
		 query=	 "select tv.PK_SEQ,tv.HOSO_FK,tv.NGAYTAO,nv.TEN,tv.TIEMNANG,tv.NHANXET,tv.TRANGTHAI "
			 		+ "from TUVAN tv left join NHANVIEN nv on tv.NGUOITAO=nv.PK_SEQ "
			 		+ "where 1=1";
		

		if(hstv.getMahoso().length()>0){
			query += " AND tv.HOSO_FK LIKE '%" + hstv.getMahoso()+ "%'";
		}
		if(hstv.getTiemnang().length()>0)
		{
			query += " AND tv.TIEMNANG LIKE '%" + hstv.getTiemnang()+ "%'";
		}
		if(hstv.getTrangthai().length()>0)
		{
			query += " AND tv.TRANGTHAI LIKE '%" + hstv.getTrangthai()+ "%'";
		}
		return query;
		
	}
	
}
	

