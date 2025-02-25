package geso.erp.servlets.sanpham_hang;

import geso.dms.center.util.Utility;
import geso.erp.beans.sanpham_hang.ISanPham_Hang;
import geso.erp.beans.sanpham_hang.imp.SanPham_Hang;
import geso.erp.beans.sanpham_hang.ISanPhamList_Hang;
import geso.erp.beans.sanpham_hang.imp.SanPhamList_Hang;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SanPhamUpdate_HangSvl extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SanPhamUpdate_HangSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    
	    HttpSession session = request.getSession();	 
	    Utility util = new Utility();	    
	    String querystring = request.getQueryString();	    
	    String userId = util.getUserId(querystring); //lấy thông tin của user
	    
	    if (userId.length() == 0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String action = util.getAction(querystring);
	    String maSanPham = util.getId(querystring); //lấy id
	    
	    String msg = "";
	    
	    ISanPham_Hang obj = new SanPham_Hang();	    
	 
		 if(action.equals("update"))
		  {
			 	session.setAttribute("action", "update");
		    	obj.setId(maSanPham);
		    	obj.setDonViTinh("donViTinh");
		    	obj.init("");
		    	
		    	obj.getDataDonVi();	    
		 	    session.setAttribute("objnew", obj);
		 	    String nextJSP = "/TrainingGESO/pages/Erp/SanPhamUpdate_Hang.jsp";
		 		response.sendRedirect(nextJSP);
		    }
		    else if(action.equals("display"))
		    {
		    	session.setAttribute("action", "display");
		    	obj.setId(maSanPham);
		    	obj.setDonViTinh("donViTinh");
		    	obj.init("");
		    	
		    	obj.getDataDonVi();	    
		 	    session.setAttribute("objnew", obj);
		 	    String nextJSP = "/TrainingGESO/pages/Erp/SanPhamDisplay_Hang.jsp";
		 		response.sendRedirect(nextJSP);
		    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ISanPham_Hang obj = new SanPham_Hang();
		
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    
	    
	    //lay action
	    obj.getDataDonVi();
	    String action = request.getParameter("action");
	    action = (action == null) ? "" : action;	    
	    Utility util = new Utility();
	    
	    HttpSession session = request.getSession();
	    
	    String userId = (String)session.getAttribute("userId");
	    String Id = util.antiSQLInspection(request.getParameter("id"));
	    obj.setId(Id);
	    String msg = "";
	    
	    String maSanPham = util.antiSQLInspection(request.getParameter("maSanPham"));
	    obj.setMaSanPham(maSanPham == null ? "" : maSanPham);
	    
	    String tenSanPham = util.antiSQLInspection(request.getParameter("tenSanPham"));
	    obj.setTenSanPham(tenSanPham == null ? "" : tenSanPham);
	  
	    String donViTinh = util.antiSQLInspection(request.getParameter("donViTinh"));  
	    obj.setDonViTinh(donViTinh == null ? "" : donViTinh );
	    
	    String soLuong = util.antiSQLInspection(request.getParameter("soLuong")); 
	    obj.setSoLuong(Float.parseFloat(soLuong));
	    
	    String giaBan = util.antiSQLInspection(request.getParameter("giaBan"));     
	    obj.setGiaBan(Float.parseFloat(giaBan));
	    
	
	    
	    System.out.println("Tên sản phẩm: " + tenSanPham);
	    System.out.println("Đơn vị tính: " + donViTinh);
	    System.out.println("Số lượng: " + soLuong);
	    System.out.println("Giá bán: " + giaBan);
	    System.out.println("Action: "+action);
	    
	    obj.setMsg(msg);
	   
	    if(action.equals("save")) { 
	    	obj.setNguoiSua(userId);
		    obj.setNguoiTao(userId);
	    		if(!obj.saveSanPham()){
	    			// lưu không thành công
	    			obj.getDataDonVi();
	    			session.setAttribute("objnew", obj);
	    		    String nextJSP = "/TrainingGESO/pages/Erp/SanPhamUpdate_Hang.jsp";
		    		response.sendRedirect(nextJSP);
		    		System.out.println(obj.getMsg());
	    		}
	    		else{
	    			//luu  thanh cong
	    			ISanPhamList_Hang obj1 = new SanPhamList_Hang();
	    			obj1.init("");	    		
	    			//gán đối tượng qua httpsession để chuyển lên jsp
	    			session.setAttribute("obj", obj1);
	    			String nextJSP = "/TrainingGESO/pages/Erp/SanPhamList_Hang.jsp";
	    			response.sendRedirect(nextJSP);
	    		}	 
	    } else  if(action.equals("update")) { 
	    	obj.setNguoiSua(userId);
    		if(!obj.updateSanPham()){
    			// lưu không thành công
    			obj.getDataDonVi();
    			session.setAttribute("objnew", obj);
    		    String nextJSP = "/TrainingGESO/pages/Erp/SanPhamUpdate_Hang.jsp";
	    		response.sendRedirect(nextJSP);
	    		System.out.println(obj.getMsg());
    		}
    		else{
    			//luu  thanh cong
    			ISanPhamList_Hang obj1 = new SanPhamList_Hang();
    			obj1.init("");	    		
    			//gán đối tượng qua httpsession để chuyển lên jsp
    			session.setAttribute("obj", obj1);
    			String nextJSP = "/TrainingGESO/pages/Erp/SanPhamList_Hang.jsp";
    			response.sendRedirect(nextJSP);
    		}	 
    } else{
    	obj.getDataDonVi();
    	session.setAttribute("objnew", obj);
    	String nextJSP = "/TrainingGESO/pages/Erp/SanPhamUpdate_Hang.jsp";
    	response.sendRedirect(nextJSP);
    }
	}
	
}
