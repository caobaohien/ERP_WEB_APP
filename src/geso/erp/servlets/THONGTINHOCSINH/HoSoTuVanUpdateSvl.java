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
import geso.erp.beans.THONTINHOCSINH.IHoSoTuVanUpdate;
import geso.erp.beans.THONTINHOCSINH.IHoSoList;
import geso.erp.beans.THONTINHOCSINH.IHoSoUpdate;
import geso.erp.beans.THONTINHOCSINH.imp.HoSoTuVanList;
import geso.erp.beans.THONTINHOCSINH.imp.HoSoTuVanUpdate;
import geso.erp.beans.THONTINHOCSINH.imp.HoSoList;
import geso.erp.beans.THONTINHOCSINH.imp.HoSoUpdate;
import geso.erp.db.sql.dbutils;


/**
 * Servlet implementation class SanphamListSvl
 */
public class HoSoTuVanUpdateSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HoSoTuVanUpdateSvl() {
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
		    
		    IHoSoTuVanUpdate hstvnew=new HoSoTuVanUpdate();
		    hstvnew.setUserId(userId);
		    if(action.equals("display"))
		    {
		    	 System.out.println("dang o diiplay ");
		    	hstvnew.setId(Id);
		    	hstvnew.CreateRs();
			    hstvnew.init();
			    session.setAttribute("objhstvnew",hstvnew);
			    String mahoso=hstvnew.getMahoso();
		    	IHoSoUpdate hsnew=new HoSoUpdate();
		    	hsnew.setId(mahoso);
		    	hsnew.CreateRs();
		    	hsnew.init();
		    	session.setAttribute("obj",hsnew);
		    	
			   	response.sendRedirect("/TrainingGESO/pages/Erp/HoSoTuVanDisplay.jsp");
		    }else if(action.equals("update"))
		    {
		    	hstvnew.setId(Id);
		    	hstvnew.CreateRs();
		    	hstvnew.init();
		    	session.setAttribute("obj",hstvnew);
		        String mahoso=hstvnew.getMahoso();
		    	IHoSoUpdate hsnew=new HoSoUpdate();
		    	hsnew.setId(mahoso);
		    	hsnew.CreateRs();
		    	hsnew.init();
		    	session.setAttribute("objhsnew",hsnew);
			   	response.sendRedirect("/TrainingGESO/pages/Erp/HoSoTuVanUpdate.jsp");
		    }
		   
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
	    System.out.println("id trong tu van up: "+userId);
	    
		String idmahoso=util.antiSQLInspection(request.getParameter("idmahoso"));
		  System.out.println("id ma ho so: "+idmahoso);
		String maquocgia=util.antiSQLInspection(request.getParameter("maquocgia"));
		String tenquocgia=util.antiSQLInspection(request.getParameter("tenquocgia"));
	    String litsquocgia=request.getParameter("litsquocgia");
	    String ngaytuvan=request.getParameter("ngaytuvan");
	    String diengiai=request.getParameter("diengiai");
	    String nhanxet=util.antiSQLInspection(request.getParameter("nhanxet"));
	    String tiemnang=util.antiSQLInspection(request.getParameter("tiemnang"));
	    
	    System.out.println("ra"+tiemnang);
	    
	    String querystring = request.getQueryString();
	    String id = util.getId(querystring);
	    System.out.println("id trong tu van up: "+id);
	    if (action == null){
	    	action = "";
	    }
	    IHoSoTuVanUpdate hstv=new HoSoTuVanUpdate();
	    hstv.setUserId(userId);
	    hstv.setMahoso(idmahoso);
	    hstv.setQuocgia_fk(litsquocgia);
	    hstv.setNgaytuvan(ngaytuvan);
	    hstv.setDiengiai(diengiai);
	    hstv.setNhanxet(nhanxet);
	    hstv.setTiemnang(tiemnang);
	    if(action.equals("save"))
	    {	
	   
	    	if(id.length() ==0)	// save cua tao moi
	    	{
	    		if(!hstv.save())	// khong thuc hien duoc ham save
	    		{
	    			hstv.init();
	    			hstv.CreateRs();
	    			session.setAttribute("obj", hstv);
	    			response.sendRedirect("/TrainingGESO/pages/Erp/HoSoTuVanUpdate.jsp");
	    		}
	    		else	// save thanh cong
	    		{
	    			IHoSoTuVanList listhstv=new HoSoTuVanList();
	    			listhstv.setUserId(userId);
	    			listhstv.init("");
	    			session.setAttribute("obj", listhstv);
	    			response.sendRedirect("/TrainingGESO/pages/Erp/HoSoTuVanList.jsp");
	    		}
	    	}
	    	else 
	    	{
	    		if(!hstv.edit_tuvan())
    			{
	    			hstv.init();
	    			hstv.CreateRs();
	    			session.setAttribute("obj", hstv);
	    			response.sendRedirect("/TrainingGESO/pages/Erp/HoSoTuVanUpdate.jsp");
    			}
    			else
    			{
    				
    				IHoSoTuVanList listhstv=new HoSoTuVanList();
	    			listhstv.setUserId(userId);
	    			listhstv.init("");
	    			session.setAttribute("obj", listhstv);
	    			response.sendRedirect("/TrainingGESO/pages/Erp/HoSoTuVanList.jsp");
    			}
	    	}
	    	
	    }else if(action.equals("clear"))
    	{
    		hstv.setUserId(userId);
    		hstv.init();
	     	 session.setAttribute("obj",hstv);
	  		 String nextJSP="/TrainingGESO/pages/Erp/HoSoTuVanList.jsp";
	  		 response.sendRedirect(nextJSP);
    	}else if(action.equals("submit"))
	    {
	    	//IHoSoUpdate hsnew=new HoSoUpdate();
    		//hsnew.setId(idmahoso);
	    	//hsnew.CreateRs();
	    	//hsnew.init();
	    	//session.setAttribute("objhsnew",hsnew);
	    	hstv.CreateRs();
	    	session.setAttribute("obj",hstv);
		   	response.sendRedirect("/TrainingGESO/pages/Erp/HoSoTuVanUpdate.jsp");
	    }else if(action.equals("savequocgia"))
	    {
	    	hstv.setMaquocgia(maquocgia);
	    	hstv.settenquocgia(tenquocgia);
	    	hstv.savequocgia();
	    	hstv.CreateRs();
	    	session.setAttribute("obj", hstv);
	    	String nextJSP = "/TrainingGESO/pages/Erp/HoSoTuVanUpdate.jsp";
	    	 response.sendRedirect(nextJSP);
	    }
    	
	}
	
}
	

