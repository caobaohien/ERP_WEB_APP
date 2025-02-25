package geso.erp.servlets.THONGTINHOCSINH;

import geso.dms.center.util.Utility;

import geso.erp.db.sql.dbutils;
import geso.erp.beans.THONTINHOCSINH.IHoSoList;
import geso.erp.beans.THONTINHOCSINH.IHoSoTuVanUpdate;
import geso.erp.beans.THONTINHOCSINH.IHoSoUpdate;
import geso.erp.beans.THONTINHOCSINH.imp.HoSoList;
import geso.erp.beans.THONTINHOCSINH.imp.HoSoTuVanUpdate;
import geso.erp.beans.THONTINHOCSINH.imp.HoSoUpdate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DonHangNewSvl
 */
public class HoSoUpdateSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HoSoUpdateSvl() {
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
	    IHoSoUpdate hsnew=new HoSoUpdate();
	    if(action.equals("display"))
	    {
	    	hsnew.setId(Id);
	    	hsnew.CreateRs();
	    	hsnew.init();
	    	session.setAttribute("obj",hsnew);
		   	response.sendRedirect("/TrainingGESO/pages/Erp/HoSoDisplay.jsp");
	    }else if(action.equals("update"))
	    {
	    	
	    	hsnew.setId(Id);																																			
	    	hsnew.CreateRs();
	    	hsnew.init();
	    	session.setAttribute("obj",hsnew);
		   	response.sendRedirect("/TrainingGESO/pages/Erp/HoSoUpdate.jsp");
	    }else if(action.equals("delete"))
	    {
	    	
	    }else  if(action.equals("tuvan")){
	    	
	    	hsnew.setId(Id);
	    	hsnew.CreateRs();
	    	hsnew.init();
	    	
	    	
	    	session.setAttribute("objhsnew",hsnew);
	    	
	    	
	    	IHoSoTuVanUpdate hstv=new HoSoTuVanUpdate();
	    	hstv.setMahoso(Id);
	    	hstv.CreateRs();
	    	session.setAttribute("obj",hstv);
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
	    Utility util = new Utility();
	    HttpSession session = request.getSession();
	    String action = request.getParameter("action");
	    if (action == null){
	    	action = "";
	    }
	    // tao doi tuong
	    IHoSoUpdate obj=new HoSoUpdate();
	    // lay cac gia tri tu the input
	    String userId = util.antiSQLInspection(request.getParameter("userId")); 
	    System.out.println("user "+userId);
		String id = util.antiSQLInspection(request.getParameter("id"));
		String maquocgia=util.antiSQLInspection(request.getParameter("maquocgia"));
		String tenquocgia=util.antiSQLInspection(request.getParameter("tenquocgia"));
		String hotenhs=util.antiSQLInspection(request.getParameter("hotenhs"));
		System.out.println("ten hs "+hotenhs);
		String ngaysinhhs=util.antiSQLInspection(request.getParameter("ngaysinhhs"));
		System.out.println("ngay hs "+ngaysinhhs);
		String tdhocvan=util.antiSQLInspection(request.getParameter("tdhocvan"));
		System.out.println("hoc van hs "+tdhocvan);
		String knhocvan=util.antiSQLInspection(request.getParameter("knhocvan"));
		
		String kntienganh=util.antiSQLInspection(request.getParameter("kntienganh"));
		//String litsquocgia=util.antiSQLInspection(request.getParameter("litsquocgia"));
		String []arrqg;
		arrqg = request.getParameterValues("litsquocgia");
		
		List<String> arrayqg = new ArrayList<String>();
		if( arrayqg !=null){
			if(arrqg!=null)
			{
				for(int i=0; i< arrqg.length; i++){
					arrayqg.add(arrqg[i]);
				}
			}
		}
		 obj.set_pk_seqquocgiahoc(arrayqg);
			
		//System.out.println("chieu dai:"+arrqg.length);
		String nguyenvong=util.antiSQLInspection(request.getParameter("nguyenvong")); if(nguyenvong==null)
	    {
			nguyenvong="";
	    }
		String nguon=util.antiSQLInspection(request.getParameter("nguon"));
		String diachihs=util.antiSQLInspection(request.getParameter("diachihs"));
		String dienthoaihs=util.antiSQLInspection(request.getParameter("dienthoaihs"));
		String ngaytaohoso=util.antiSQLInspection(request.getParameter("ngaytaohoso"));
		String manv=util.antiSQLInspection(request.getParameter("manv"));
		String emailhs=util.antiSQLInspection(request.getParameter("emailhs"));
		String facebookhs=util.antiSQLInspection(request.getParameter("facebookhs"));
		
		String hotencha=util.antiSQLInspection(request.getParameter("hotencha"));
		String ngaysinhcha=util.antiSQLInspection(request.getParameter("ngaysinhcha"));
		String diachicha=util.antiSQLInspection(request.getParameter("diachicha"));
		String dienthoaicha=util.antiSQLInspection(request.getParameter("dienthoaicha"));
		String nguyenvongkhac=util.antiSQLInspection(request.getParameter("nguyenvongkhac"));
		String nghenghiepcha=util.antiSQLInspection(request.getParameter("nghenghiepcha"));
		String hotenme=util.antiSQLInspection(request.getParameter("hotenme"));
		String ngaysinhme=util.antiSQLInspection(request.getParameter("ngaysinhme"));
		String diachime=util.antiSQLInspection(request.getParameter("diachime"));
		
		String dienthoaime=util.antiSQLInspection(request.getParameter("dienthoaime"));
		String nghenghiepme=util.antiSQLInspection(request.getParameter("nghenghiepme"));
		String thunhap=util.antiSQLInspection(request.getParameter("thunhap"));
		//Float thunhap =Float.parseFloat(request.getParameter("thunhap").replaceAll(",", ""));
		String emailkhac=util.antiSQLInspection(request.getParameter("emailkhac"));
		String facebookkhac=util.antiSQLInspection(request.getParameter("facebookkhac"));
		// set du lieu vao doi tuong
		obj.setId(id);
		obj.setUserId(userId);
		obj.setHoten(hotenhs);
		obj.setNgaysinh(ngaysinhhs);
		obj.setTrinhdohocvan(tdhocvan);
		obj.setKhananghocvan(knhocvan);
		obj.setKhanangtienganh(kntienganh);
		
		obj.setNguyenvong(nguyenvong);
	    obj.setNguon(nguon);
	    obj.setDiachi(diachihs);
	    obj.setDienthoai(dienthoaihs);
	    obj.setEmail(emailhs);
	    obj.setfacebook(facebookhs);
	    obj.setHoten_cha(hotencha);
	    obj.setNgaysinh_cha(ngaysinhcha);
	    obj.setDiachi_cha(diachicha);
	    obj.setDienthoai_cha(dienthoaicha);
	    obj.setNghenghiep_cha(nghenghiepcha);
	    obj.setHoten_me(hotenme);
	    obj.setNgaysinh_me(ngaysinhme);
	    obj.setDiachi_me(diachime);
	    obj.setDienthoai_me(dienthoaime);
	    obj.setNghenghiep_me(nghenghiepme);
	    obj.setThunhap_GD(thunhap);
	    obj.setEmail_khac(emailkhac);
	    obj.setfacebook_khac(facebookkhac);
	    obj.setManhanvien(manv);
	    obj.setNgaytaohoso(ngaytaohoso);
	    obj.setNguyenvongkhac(nguyenvongkhac);
		// xu ly action save
	    if(action.equals("save"))
	    {	
	    		System.out.println("dang o save");
	    		System.out.println("id la bao nhieu"+id);
	    	if(id.length() ==0)	// save cua tao moi
	    	{
	    		if(!obj.save())	// khong thuc hien duoc ham save
	    		{
	    			System.out.println("dang o save tren");
	    			System.out.println("khong save");
	    			obj.init();
	    			obj.CreateRs();
	    			session.setAttribute("obj", obj);
	    			response.sendRedirect("/TrainingGESO/pages/Erp/HoSoUpdate.jsp");
	    		}
	    		else	// save thanh cong
	    		{
	    			System.out.println("vao save");
	    			IHoSoList lisths=new HoSoList();
	    			lisths.setUserId(userId);
	    			System.out.println(" save");
	    			lisths.init("");
	    			session.setAttribute("obj", lisths);
	    			response.sendRedirect("/TrainingGESO/pages/Erp/HoSoList.jsp");
	    		}
	    	}
	    	else 
	    	{
	    		if(!obj.edit_hs())
    			{
    				// luu o thanh cong
	    			System.out.println("da vao edit");
    				System.out.println("tai sao o luu");
	    			obj.init();
	    			obj.CreateRs();
	    			session.setAttribute("obj", obj);
			    	response.sendRedirect("/TrainingGESO/pages/Erp/HoSoUpdate.jsp");
    			}
    			else
    			{
    				
    				System.out.println("  o edit");
    				IHoSoList lisths=new HoSoList();
    				lisths.setUserId(userId);
	    			lisths.init("");
	    			session.setAttribute("obj", lisths);
	    			response.sendRedirect("/TrainingGESO/pages/Erp/HoSoList.jsp");
    			}
	    	}
	    	
	    }else if(action.equals("loadqg"))
	    {
	    	System.out.println("dang o load qg");
	    	obj.CreateRs();
	    	session.setAttribute("obj", obj);
	    	String nextJSP = "/TrainingGESO/pages/Erp/HoSoUpdate.jsp";
	    	 response.sendRedirect(nextJSP);
	    } else if(action.equals("savequocgia"))
	    {
	    	obj.setMaquocgia(maquocgia);
	    	obj.settenquocgia(tenquocgia);
	    	obj.savequocgia();
	    	//obj.CreateRs();
	    	session.setAttribute("obj", obj);
	    	String nextJSP = "/TrainingGESO/pages/Erp/HoSoUpdate.jsp";
	    	 response.sendRedirect(nextJSP);
	    }else if(action.equals("submit"))
	    {
	    	
	    	System.out.println("dang o submit");
	    	obj.CreateRs();
	    	session.setAttribute("obj", obj);
	    	String nextJSP = "/TrainingGESO/pages/Erp/HoSoUpdate.jsp";
	    	response.sendRedirect(nextJSP);
	    }
	    
	}
}
