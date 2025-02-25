package geso.erp.servlets.donbanhang_thai;

import geso.dms.center.util.Utility;
import geso.erp.beans.donbanhang_duongnguyen.IDonHang;
import geso.erp.beans.donbanhang_duongnguyen.Imp.DonHang;
import geso.erp.beans.donbanhang_tan.*;
import geso.erp.beans.donbanhang_tan.imp.*;
import geso.erp.beans.donbanhang_thai.IDonbanhang;
import geso.erp.beans.donbanhang_thai.IDonbanhangList;
import geso.erp.beans.donbanhang_thai.ISanpham;
import geso.erp.beans.donbanhang_thai.imp.Donbanhang;
import geso.erp.beans.donbanhang_thai.imp.DonbanhangList;
import geso.erp.beans.donbanhang_thai.imp.Sanpham;

import geso.erp.beans.khachhang.IKhachhangList;
import geso.erp.beans.khachhang.imp.KhachhangList;
import geso.erp.beans.nhacungcap.INhacungcap;
import geso.erp.beans.nhacungcap.INhacungcapList;
import geso.erp.beans.nhacungcap.imp.Nhacungcap;
import geso.erp.beans.nhacungcap.imp.NhacungcapList;
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class NhacungcapNewSvl
 */
@WebServlet("/NhacungcapNewSvl")
public class DonhangNewSvl_thai extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonhangNewSvl_thai() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		Utility util = new Utility();
		String queryString = request.getQueryString();
		String userId = util.getUserId(queryString);
		if (userId.length() == 0)
			userId = util.antiSQLInspection(request.getParameter("userId"));
		String action = util.getAction(queryString);
		String id = util.getId(queryString);
		String msg = "";
		IDonbanhang dh = new Donbanhang();
		dh.setId(id);
		dh.init();
		dh.CreateRs();
		session.setAttribute("dhnew", dh);
		String nextJSP = "/TrainingGESO/pages/Erp/Donbanhang_thai.jsp";
		response.sendRedirect(nextJSP);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
 		IDonbanhang obj=new Donbanhang();
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
	    
	    
	    obj.setUserId(userId);
 	    String id = util.antiSQLInspection(request.getParameter("id"));
	    obj.setId(id==null?"":id);
	    System.out.println("id: "+id);
	    String tongtien = util.antiSQLInspection(request.getParameter("tongtien").replaceAll(",", ""));
	    System.out.println("Tongtien : "+tongtien);
	    obj.setTongtien(tongtien==null?"":tongtien);
 	     String ngay = util.antiSQLInspection(request.getParameter("ngay")); 
	     obj.setNgaychungtu(ngay==null?"0":ngay);
 	     String khachhang = util.antiSQLInspection(request.getParameter("khachhang")); 
	     obj.setKhachhang(khachhang==null?"":khachhang);
 	     System.out.println(id);
	     System.out.println(tongtien);
	     System.out.println(ngay);
	     System.out.println(khachhang);
	     System.out.println("actiong: "+action);
	     
	     String[] idsp =request.getParameterValues("idsp");
	     String[] masp =request.getParameterValues("masp");
	     String[] tensp =request.getParameterValues("tensp");
	     String[] donvitinh =request.getParameterValues("donvitinh");
	     String[] soluong =request.getParameterValues("soluong");
	     String[] dongia =request.getParameterValues("dongia");
	     String[] thanhtien =request.getParameterValues("thanhtien");
	     List<ISanpham> listsp=new ArrayList<ISanpham>();
	     
	     if(masp!=null){
		     for(int  i =0;i<masp.length;i++){
		    	 	if(masp[i].length()>0 ){
		    	 		ISanpham sp =new Sanpham();
		    	 		sp.setMasanpham(masp[i]);
		    	 		sp.setTensanpham(tensp[i]);
		    	 		sp.setId(idsp[i]);
		    	 		sp.setDonvitinh(donvitinh[i]);
		    	 		double dongia_=0;
		    	 		
		    	 		try{
		    	 			dongia_=Double.parseDouble(dongia[i].replaceAll(",", ""));
		    	 			
		    	 		}catch(Exception er){
		    	 			
		    	 		}
		    	 		sp.setDongia(dongia_);
		    	 		
	    	 		double soluong_=0;
		    	 		
		    	 		try{
		    	 			soluong_=Double.parseDouble(soluong[i].replaceAll(",", ""));
		    	 			
		    	 		}catch(Exception er){
		    	 			
		    	 		}
		    	 		sp.setSoluong(soluong_);
		    	 		
		    	 		
		    	 		sp.setMasanpham(masp[i]);
		    	 		listsp.add(sp);
		    	 		
		    	 		
		    	 	}
		    	 	System.out.println("masp"+masp[i]);
		    	 	
		     }
	     }
	     
	     	obj.setListSp(listsp);
	     	
	    	if(action.equals("save")) 
	    	{ 
	    		if(id.length() >0){
	    			if(!obj.update()){
		    			// lưu không thành công
		    			 
		    			obj.CreateRs();
		    			session.setAttribute("dhnew", obj);
		    	    	String nextJSP = "/TrainingGESO/pages/Erp/Donbanhang_thai.jsp";
		    	    	response.sendRedirect(nextJSP);
		    		}else{
		    			IDonbanhangList dh=new DonbanhangList();
		    			dh.init("");
		    		 	dh.CreateRs();
		    		 	session.setAttribute("obj", dh);
		    		    String nextJSP = "/TrainingGESO/pages/Erp/DonbanhangList_thai.jsp";
		    			response.sendRedirect(nextJSP);
		    		}
	    		} else{
		    		if(!obj.save()){
		    			// lưu không thành công
		    			 
		    			obj.CreateRs();
		    			session.setAttribute("dhnew", obj);
		    	    	String nextJSP = "/TrainingGESO/pages/Erp/Donbanhang_thai.jsp";
		    	    	response.sendRedirect(nextJSP);
		    		}else{
		    			IDonbanhangList dh=new DonbanhangList();
		    			dh.init("");
		    		 	dh.CreateRs();
		    		 	session.setAttribute("obj", dh);
		    		    String nextJSP = "/TrainingGESO/pages/Erp/DonbanhangList_thai.jsp";
		    			response.sendRedirect(nextJSP);
		    		}
	    		}
	    	}else{
	    		 
		    	obj.CreateRs();
		    	session.setAttribute("dhnew", obj);
		    	String nextJSP = "/TrainingGESO/pages/Erp/Donbanhang_thai.jsp";	
	    	}
	      
	}
	

}
