package geso.erp.servlets.donbanhang_trang;

import geso.dms.center.util.Utility;
import geso.erp.beans.donbanhang_trang.IChiTietDonHang_Trang;
import geso.erp.beans.donbanhang_trang.IDonBanHang_Trang;
import geso.erp.beans.donbanhang_trang.imp.ChiTietDonHang_Trang;
import geso.erp.beans.donbanhang_trang.imp.DonBanHang_Trang;
import geso.erp.beans.donbanhang_trang.IDonBanHangList_Trang;
import geso.erp.beans.donbanhang_trang.imp.DonBanHangList_Trang;
import geso.erp.beans.donbanhang_trinh.IChiTietDonHang_Trinh;
import geso.erp.beans.donbanhang_trinh.imp.ChiTietDonHang_Trinh;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DonBanHangUpdate_TrangSvl extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonBanHangUpdate_TrangSvl() {
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
	    
	    IDonBanHang_Trang obj = new DonBanHang_Trang();	    
	    if(action.equals("update"))
	    {
	    	obj.setId(maDonHang);
	    	obj.init("");
	    	session.setAttribute("action", "update");
	    	obj.getDataKhachHang();
	    	
	    	session.setAttribute("objnew", obj);
		    String nextJSP = "/TrainingGESO/pages/Erp/DonBanHangUpdate_Trang.jsp";
			response.sendRedirect(nextJSP);
	    }
	    
	    else if(action.equals("display")){
	    	obj.setId(maDonHang);
	    	obj.init("");
	    	session.setAttribute("action", "display");
	    	obj.getDataKhachHang();
	    	
	    	session.setAttribute("objnew", obj);
		    String nextJSP = "/TrainingGESO/pages/Erp/DonBanHangDisplay_Trang.jsp";
			response.sendRedirect(nextJSP);
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		IDonBanHang_Trang obj = new DonBanHang_Trang();
		
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
	    
	    //Lấy thông tin của CHI TIẾT HOÁ ĐƠN
	    String[] idsp = request.getParameterValues("idsp");
	    String[] masp = request.getParameterValues("masp");
	    String[] tensp = request.getParameterValues("tensp");
	    String[] donvitinh = request.getParameterValues("donvitinh");
	    String[] soluong = request.getParameterValues("soluong");
	    String[] soluongton = request.getParameterValues("soluongton");
	    String[] dongia = request.getParameterValues("dongia");
	    String[] thanhtien = request.getParameterValues("thanhtien");
	    
	    List<IChiTietDonHang_Trang> list = new ArrayList<IChiTietDonHang_Trang>();
	    if(masp != null){
	    	for (int i = 0; i < masp.length; i++) {
				if(masp[i].length() > 0){
					IChiTietDonHang_Trang sp = new ChiTietDonHang_Trang();
					sp.setSanPhamPK_SEQ(idsp[i]);
					sp.setTenSanPham(tensp[i]);
					sp.setDonViTinh(donvitinh[i]);
					
					//Hàm kiểm trả giá trị nhập vào của DONGIA có hợp lệ hay không
					double dongia_temp = 0;
					try {
						dongia_temp = Double.parseDouble(dongia[i].replaceAll(",", ""));
					} catch (Exception e) {
						// TODO: handle exception
					}
					sp.setDonGia(dongia_temp);
					
					//Hàm kiểm tra giá trị nhập vào của SOLUONG có hợp lệ hay không
					double soluong_temp = 0;
					try {
						soluong_temp = Double.parseDouble(soluong[i].replaceAll(",", ""));
					} catch (Exception e) {
						// TODO: handle exception
					}
					sp.setSoLuong(soluong_temp);
					
					//Hàm kiểm tra giá trị nhập vào của SOLUONGTON có hợp lệ hay không
					double soluongton_temp = 0;
					try {
						soluongton_temp = Double.parseDouble(soluongton[i].replaceAll(",", ""));
					} catch (Exception e) {
						// TODO: handle exception
					}
					sp.setSoLuongTon(soluongton_temp);
					
					//Hàm kiểm tra giá trị nhập vào của THANHTIEN có hợp lệ hay không
					double thanhtien_temp = 0;
					try {
						thanhtien_temp = Double.parseDouble(thanhtien[i].replaceAll(",", ""));
					} catch (Exception e) {
						// TODO: handle exception
					}
					sp.setThanhTien(thanhtien_temp);
					list.add(sp);
				}
				System.out.println("masp: " + masp[i]);
			}
	    }
	    obj.setDanhSachSanPham(list);
	    
	    obj.setMsg(msg);
	   
	    if(action.equals("save")) { 
	    	obj.setNguoiSua(userId);
		    obj.setNguoiTao(userId);
	    		if(!obj.saveDonHang()){
	    			//Nếu save không thành công
	    			obj.getDataKhachHang();
	    			session.setAttribute("objnew", obj);
	    		    String nextJSP = "/TrainingGESO/pages/Erp/DonBanHangUpdate_Trang.jsp";
		    		response.sendRedirect(nextJSP);
		    		System.out.println(obj.getMsg());
	    		}
	    		else{
	    			IDonBanHangList_Trang obj1 = new DonBanHangList_Trang();
	    			obj1.init("");	    		
	    			//gán đối tượng qua httpsession để chuyển lên jsp
	    			session.setAttribute("obj", obj1);
	    			String nextJSP = "/TrainingGESO/pages/Erp/DonBanHang_Trang.jsp";
	    			response.sendRedirect(nextJSP);
	    		}	 
	    } else  if(action.equals("update")) { 
	    	obj.setNguoiSua(userId);
    		if(!obj.updateDonHang()){
    			//Update không thành công
    			obj.getDataKhachHang();
    			session.setAttribute("objnew", obj);
    		    String nextJSP = "/TrainingGESO/pages/Erp/DonBanHangUpdate_Trang.jsp";
	    		response.sendRedirect(nextJSP);
	    		System.out.println(obj.getMsg());
    		}
    		else{
    			//Update thành công
    			IDonBanHangList_Trang obj1 = new DonBanHangList_Trang();
    			obj1.init("");	    		
    			//Gán đối tượng qua httpsession để chuyển lên jsp
    			session.setAttribute("obj", obj1);
    			String nextJSP = "/TrainingGESO/pages/Erp/DonBanHang_Trang.jsp";
    			response.sendRedirect(nextJSP);
    		}	 
    } else{
    	obj.getDataKhachHang();
    	session.setAttribute("objnew", obj);
    	String nextJSP = "/TrainingGESO/pages/Erp/DonBanHangUpdate_Trang.jsp";
    	response.sendRedirect(nextJSP);
    } 	
	}
}
