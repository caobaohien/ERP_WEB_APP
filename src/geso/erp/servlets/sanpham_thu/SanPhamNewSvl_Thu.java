package geso.erp.servlets.sanpham_thu;

import geso.dms.center.util.Utility;
import geso.erp.beans.donbanhang_thu.IDonBanHangList_Thu;
import geso.erp.beans.donbanhang_thu.IDonBanHang_Thu;
import geso.erp.beans.donbanhang_thu.imp.DonBanHangList_Thu;
import geso.erp.beans.donbanhang_thu.imp.DonBanHang_Thu;
import geso.erp.beans.sanpham_thu.ISanPhamList_Thu;
import geso.erp.beans.sanpham_thu.ISanPham_Thu;
import geso.erp.beans.sanpham_thu.imp.SanPhamList_Thu;
import geso.erp.beans.sanpham_thu.imp.SanPham_Thu;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SanPhamNewSvl_Thu extends HttpServlet{
	 public SanPhamNewSvl_Thu() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
	 //dùng để chuyển trang
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//dịnh dạng 	
		 request.setCharacterEncoding("UTF-8");
		    response.setCharacterEncoding("UTF-8");
		    response.setContentType("text/html; charset=UTF-8");
		
		// lấy session    
		    HttpSession session = request.getSession();	 
		    Utility util = new Utility();
		//lấy thông tin từ session
		    String querystring = request.getQueryString();
		    String userId = util.getUserId(querystring);
		    
		    if (userId.length() == 0)
		    	userId = util.antiSQLInspection(request.getParameter("userId"));
		    
		    String action = util.getAction(querystring);
		    String maSanPham = util.getId(querystring);
		   
		    
		    String msg = "";
		    
		    //lấy sản phẩm
		    ISanPham_Thu obj = new SanPham_Thu();
		    obj.setMaSanPham(maSanPham);
		    obj.init("");
		    obj.CreateDonVi();

		    //Load gia tri da co san 
		    //đưa đối tượng vào biến obj nhờ session đưa đi đến jsp
		    session.setAttribute("obj", obj);
		    String nextJSP = "/TrainingGESO/pages/Erp/SanPhamNew_Thu.jsp";
			response.sendRedirect(nextJSP);
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			 	
				//định dạng
				request.setCharacterEncoding("UTF-8");
			    response.setCharacterEncoding("UTF-8");
			    response.setContentType("text/html; charset=UTF-8");
			    
			    //tạo đơn hàng mới
			    ISanPham_Thu obj = new SanPham_Thu();
			    obj.CreateDonVi();
			    String action = request.getParameter("action");
			    action = (action == null) ? "" : action;
			    
			    System.out.print("hành động: "+ action);
			    
			    Utility util = new Utility();
			    
			    //gọi session
				HttpSession session = request.getSession();
				
				//lấy các thuộc tính gán cho obj
			    String userId = util.antiSQLInspection(request.getParameter("userId"));
			    obj.setUserId(userId);
			    String msg = "";
			    
			    String tenSanPham = util.antiSQLInspection(request.getParameter("tenSanPham"));
			    obj.setTenSanPham(tenSanPham == null ? "" : tenSanPham);
			    
			    System.out.println( " MA SAN PHAM:"+ tenSanPham);
			    
			  
			    String maDonVi = util.antiSQLInspection(request.getParameter("maDonVi"));
			    obj.setMaDoVi(maDonVi);
			    
			    String soLuong = util.antiSQLInspection(request.getParameter("soLuong")); 
			    obj.setSoLương(Float.parseFloat(soLuong) );
			   
			    
			    String giaBan = util.antiSQLInspection(request.getParameter("giaBan"));     
			    obj.setGiaBan(Float.parseFloat(giaBan));
			    
			    obj.setMsg(msg);
			   
			    
			    // nhận hành động
			     if(action.equals("save")) { 
			    	if(!obj.saveSanPham()){
			    			// lưu không thành công thì tạo đối tượng mới chuyển trang sản phẩm mới	
			    			obj.CreateDonVi();
			    			session.setAttribute("obj", obj);
				    		String nextJSP = "/TrainingGESO/pages/Erp/SanPhamNew_Thu.jsp";
				    		response.sendRedirect(nextJSP);
				    		System.out.println(obj.getMsg());
			    	}else{
			    		//nếu lưu xong thì hiển thị lại trang tổng
			    			  	ISanPhamList_Thu obj1 = new SanPhamList_Thu();
			    			  	obj1.init("");
			    			    // khởi tạo ra 1 session
			    			    session.setAttribute("obj", obj1);
			    			    String nextJSP = "/TrainingGESO/pages/Erp/SanPhamList_Thu.jsp";
			    				response.sendRedirect(nextJSP);
			    		}
			    }else{
			    	
			    	obj.CreateDonVi();
			    	session.setAttribute("obj", obj);
			    	String nextJSP = "/TrainingGESO/pages/Erp/SanPhamNew_Thu.jsp";
			    }
			     
		}
	
}
