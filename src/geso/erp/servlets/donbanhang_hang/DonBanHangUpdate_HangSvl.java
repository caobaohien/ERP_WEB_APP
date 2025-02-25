package geso.erp.servlets.donbanhang_hang;

import geso.dms.center.util.Utility;
import geso.erp.beans.donbanhang_hang.IChiTietDonHang_Hang;
import geso.erp.beans.donbanhang_hang.IDonBanHang_Hang;
import geso.erp.beans.donbanhang_hang.imp.ChiTietDonHang_Hang;
import geso.erp.beans.donbanhang_hang.imp.DonBanHang_Hang;
import geso.erp.beans.donbanhang_hang.IDonBanHangList_Hang;
import geso.erp.beans.donbanhang_hang.imp.DonBanHangList_Hang;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DonBanHangUpdate_HangSvl extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonBanHangUpdate_HangSvl() {
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
	    String maDonHang = util.getId(querystring); //lấy id
	    
	    String msg = "";
	    
	    IDonBanHang_Hang obj = new DonBanHang_Hang();	
	    System.out.println("Action: " + action);
	    
	    if(action.equals("update"))
	    {
	    	session.setAttribute("action", "update");
	    	obj.setId(maDonHang);
	    	obj.setKhachHang("khachHang");
	    	obj.init("");
	    	
	    	obj.getDataKhachHang();	    
		    session.setAttribute("objnew", obj);
		    String nextJSP = "/TrainingGESO/pages/Erp/DonBanHangUpdate_Hang.jsp";
			response.sendRedirect(nextJSP);
	    }
	    else if(action.equals("display"))
	    {
	    	session.setAttribute("action", "display");
	    	obj.setId(maDonHang);
	    	obj.setKhachHang("khachHang");
	    	obj.init("");
	    	    	
	    	obj.getDataKhachHang();	    
		    session.setAttribute("objnew", obj);
		    String nextJSP = "/TrainingGESO/pages/Erp/DonBanHangDisplay_Hang.jsp";
			response.sendRedirect(nextJSP);
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		IDonBanHang_Hang obj = new DonBanHang_Hang();
		
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    
	    
	    //lay action
	    obj.getDataKhachHang();
	    String action = request.getParameter("action");
	    action = (action == null) ? "" : action;	    
	    Utility util = new Utility();
	    
	    HttpSession session = request.getSession();
	    
	    String userId = (String)session.getAttribute("userId");
	    
	    
	    String Id = util.antiSQLInspection(request.getParameter("id"));
	    
	    obj.setId(Id);
	    String msg = "";
	    
	    String maDonHang = util.antiSQLInspection(request.getParameter("maDonHang"));
	    obj.setMaDonHang(maDonHang == null ? "" : maDonHang);
	    System.out.println(maDonHang);
	  
	    String maKhachHang = util.antiSQLInspection(request.getParameter("maKhachHang"));  
	    obj.setKhachHang(maKhachHang);
	    
	    String trangThai = util.antiSQLInspection(request.getParameter("trangThai")); 
	    obj.setTrangThai(trangThai == null ? "0" : trangThai);
	    
	    String ngayChungTu = util.antiSQLInspection(request.getParameter("ngayChungTu")); 
	    obj.setNgayChungTu(ngayChungTu == null ? "" : ngayChungTu);
	    
	    String tongTien = util.antiSQLInspection(request.getParameter("tongTien").replaceAll(",", ""));     
	    obj.setTongTien(Float.parseFloat(tongTien));
	    
	    System.out.println("Ma: " + maKhachHang);
	    System.out.println("Ngay chung tu: " + ngayChungTu);
	    System.out.println("Tong Tien: " + tongTien);
	    System.out.println("Action: "+action);
	    
	    //phải đặt giống Table khung
	    String[] idsp = request.getParameterValues("idsp");
	    String[] maSanPham = request.getParameterValues("maSanPham");
	    String[] tenSanPham = request.getParameterValues("tenSanPham");
	    String[] donViTinh = request.getParameterValues("donViTinh");
	    String[] soLuong = request.getParameterValues("soLuong");
	    String[] soLuongTon = request.getParameterValues("soLuongTon");
	    String[] donGia = request.getParameterValues("donGia");
	    String[] thanhTien = request.getParameterValues("thanhTien");
	    List<IChiTietDonHang_Hang> listsp = new ArrayList<IChiTietDonHang_Hang>();
	    if(maSanPham != null){
		    for(int i = 0; i < maSanPham.length; i++){
		    	if(maSanPham[i].length() > 0){
		    		IChiTietDonHang_Hang sp = new ChiTietDonHang_Hang();
		    		sp.setIdSanPham(idsp[i]);
		    		//sp.setId(idsp[i]);
		    		sp.setMaSanPham(maSanPham[i]);
		    		sp.setTenSanPham(tenSanPham[i]);
		    		sp.setDonViTinh(donViTinh[i]);
		    		
		    		//Số lượng
		    		float soluong_ = 0;
		    		try{
		    			soluong_ = Float.parseFloat(soLuong[i].replaceAll(",", ""));
		    		}catch(Exception e){
		    			
		    		}
		    		sp.setSoLuong(soluong_);
		    		
		    		//Số lượng tồn
		    		float soluongton_ = 0;
		    		try{
		    			soluongton_ = Float.parseFloat(soLuongTon[i].replaceAll(",", ""));
		    		}catch(Exception e){
		    			
		    		}
		    		sp.setSoLuongTon(soluongton_);
		    		
		    		//Đơn giá
		    		float dongia_ = 0;
		    		try{
		    			dongia_ = Float.parseFloat(donGia[i].replaceAll(",", ""));
		    		}catch(Exception e){
		    			
		    		}
		    		sp.setDonGia(dongia_);
		    		
		    		//Thành tiền
		    		float thanhtien_ = 0;
		    		try{
		    			thanhtien_ = Float.parseFloat(thanhTien[i].replaceAll(",", ""));
		    		}catch(Exception e){
		    			
		    		}
		    		sp.setThanhTien(thanhtien_);
		    		
		    		listsp.add(sp);
		    	}
		    	System.out.println("Mã sản phẩm: " + maSanPham[i]);
		    }
	    }
	    
	    obj.setMsg(msg);
	    obj.setListSp(listsp);
	    
	    if(action.equals("save")) { 
	    	obj.setNguoiSua(userId);
		    obj.setNguoiTao(userId);
	    		if(!obj.saveDonHang()){
	    			// lưu không thành công
	    			obj.getDataKhachHang();
	    			session.setAttribute("objnew", obj);
	    		    String nextJSP = "/TrainingGESO/pages/Erp/DonBanHangUpdate_Hang.jsp";
		    		response.sendRedirect(nextJSP);
		    		System.out.println(obj.getMsg());
	    		}
	    		else{
	    			//luu  thanh cong
	    			IDonBanHangList_Hang obj1 = new DonBanHangList_Hang();
	    			obj1.init("");	    		
	    			//gán đối tượng qua httpsession để chuyển lên jsp
	    			session.setAttribute("obj", obj1);
	    			String nextJSP = "/TrainingGESO/pages/Erp/DonBanHang_Hang.jsp";
	    			response.sendRedirect(nextJSP);
	    		}	 
	    } else  if(action.equals("update")) { 
	    	obj.setNguoiSua(userId);
    		if(!obj.updateDonHang()){
    			// lưu không thành công
    			obj.getDataKhachHang();
    			session.setAttribute("objnew", obj);
    		    String nextJSP = "/TrainingGESO/pages/Erp/DonBanHangUpdate_Hang.jsp";
	    		response.sendRedirect(nextJSP);
	    		System.out.println(obj.getMsg());
    		}
    		else{
    			//luu  thanh cong
    			IDonBanHangList_Hang obj1 = new DonBanHangList_Hang();
    			obj1.init("");	    		
    			//gán đối tượng qua httpsession để chuyển lên jsp
    			session.setAttribute("obj", obj1);
    			String nextJSP = "/TrainingGESO/pages/Erp/DonBanHang_Hang.jsp";
    			response.sendRedirect(nextJSP);
    		}	 
    } else{
    	obj.getDataKhachHang();
    	session.setAttribute("objnew", obj);
    	String nextJSP = "/TrainingGESO/pages/Erp/DonBanHangUpdate_Hang.jsp";
    	response.sendRedirect(nextJSP);
    }   	
	}
}
