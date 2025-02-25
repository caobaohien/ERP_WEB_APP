package geso.erp.servlets.donbanhang_vy;

import geso.dms.center.util.Utility;
import geso.erp.beans.donbanhang_vy.IDonHangList_Vy;
import geso.erp.beans.donbanhang_vy.IDonHang_Vy;
import geso.erp.beans.donbanhang_vy.imp.DonHangList_Vy;
import geso.erp.beans.donbanhang_vy.imp.DonHang_Vy;
import geso.erp.beans.sanpham_vy.*;
import geso.erp.beans.sanpham_vy.imp.SanPham_Vy;
import geso.erp.db.sql.dbutils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DonHangNewSvl_Vy
 */
public class DonHangNewSvl_Vy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonHangNewSvl_Vy() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    
	    HttpSession session = request.getSession();	 
	    Utility util = new Utility();
	    
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    if (userId.length() == 0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String action = util.getAction(querystring);
	    String maDonHang = util.getId(querystring);
	    
	    String msg = "";
	    
	    IDonHang_Vy iDonHang_Vy = new DonHang_Vy();
	    iDonHang_Vy.setMaDonHang(maDonHang);
	    iDonHang_Vy.init("");
	    iDonHang_Vy.CreateRsKH();

	    
	    //Load gia tri da co san 
	    if(querystring.indexOf("update") >= 0){
	    	String id = request.getParameter("update");
	    	iDonHang_Vy.init("");

	    }
	    String maKhachHang = util.antiSQLInspection(request.getParameter("maKhachHang"));
	    System.out.println("Makhachhang " + maKhachHang);
	        
	    session.setAttribute("donHangMoi", iDonHang_Vy);
	    String nextJSP = "/TrainingGESO/pages/Erp/DonHangNew_Vy.jsp";
		response.sendRedirect(nextJSP);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 	IDonHang_Vy donHang_Vy = new DonHang_Vy();
			
			request.setCharacterEncoding("UTF-8");
		    response.setCharacterEncoding("UTF-8");
		    response.setContentType("text/html; charset=UTF-8");
		    donHang_Vy.CreateRsKH();
		    String action = request.getParameter("action");
		    action = (action == null) ? "" : action;
		    
		    Utility util = new Utility();
		    
			HttpSession session = request.getSession();
		    String userId = util.antiSQLInspection(request.getParameter("userId"));
		    donHang_Vy.setUserId(userId);
		    String msg = "";
		    
		    String maDonHang = util.antiSQLInspection(request.getParameter("maDonHang"));
		    donHang_Vy.setMaDonHang(maDonHang == null ? "" : maDonHang);
		    System.out.println(maDonHang);
		  
		    String maKhachHang = util.antiSQLInspection(request.getParameter("maKhachHang"));
		  
		    donHang_Vy.setMaKhachHang(maKhachHang);
		    
		    String trangThai = util.antiSQLInspection(request.getParameter("trangThai")); 
		    donHang_Vy.setTrangThai(trangThai == null ? "0" : trangThai);
		    
		    String ngayChungTu = util.antiSQLInspection(request.getParameter("ngayChungTu")); 
		    donHang_Vy.setNgayChungTu(ngayChungTu == null ? "" : ngayChungTu);
		    
		    String tongTien = util.antiSQLInspection(request.getParameter("tongTien"));     
		    donHang_Vy.setTongTien(Float.parseFloat(tongTien.replaceAll(",", "")));
		    
		    donHang_Vy.setMsg(msg);
		    
		    //Lay thong tin san pham
		    
			/*Object[][] bangSanPham = {
					request.getParameterValues("PK_SEQ"),
					request.getParameterValues("maSanPham"),
					request.getParameterValues("tenSanPham"),
					request.getParameterValues("donViTinh"),
					request.getParameterValues("soLuong"),
					request.getParameterValues("donGia"),
					request.getParameterValues("thanhTien"),
			};*/
		    
			String[] PK_SEQ = request.getParameterValues("PK_SEQ");
			String[] maSanPham = request.getParameterValues("maSanPham");
			String[] tenSanPham = request.getParameterValues("tenSanPham");
			String[] soLuong = request.getParameterValues("soLuong");
			String[] donViTinh = request.getParameterValues("donViTinh");
			String[] giaBan = request.getParameterValues("giaBan");
			String[] thanhtien = request.getParameterValues("thanhTien");
			
			List<ISanPham_Vy> danhSachSanPham = new ArrayList<ISanPham_Vy>();
			//Neu mang ma asn pham khac rong thi vao vong lap
			//Co can phai kiem tra dieu kien nay khong?
			if (maSanPham != null) {
				for (int i = 0; i < maSanPham.length; i++) {
					//Neu mang ma asn pham co ki tu thi moi lay gia tri
					if (tenSanPham[i].trim().length() > 0
							|| maSanPham[i].trim().length() > 0) {
						//Khong dung try catch trong ep kieu vi ta da ngan nguoi dung nhap thong tin rac
						ISanPham_Vy sanPham_Vy = new SanPham_Vy(PK_SEQ[i], 
													maSanPham[i],
													donViTinh[i], 
													tenSanPham[i],
													Integer.parseInt(soLuong[i]),
													Float.parseFloat((giaBan[i]).replaceAll(",", "")));
						danhSachSanPham.add(sanPham_Vy);
					}
				}
			}
			donHang_Vy.setDanhSachSanPham(danhSachSanPham);
			
			if (action.equals("taomoi")) {
				session.setAttribute("readonly", "");
				System.out.println("Day la action tao moi");
			}
		   
		     if(action.equals("save")) { 
		    	if(!donHang_Vy.save()){
		    			// lưu không thành công
		    			donHang_Vy.CreateRsKH();
		    			session.setAttribute("donHangMoi", donHang_Vy);
			    		String nextJSP = "/TrainingGESO/pages/Erp/DonHangNew_Vy.jsp";
			    		response.sendRedirect(nextJSP);
			    		System.out.println(donHang_Vy.getMsg());
		    	}else{
		    			  	IDonHangList_Vy donHangList_Vy = new DonHangList_Vy();
		    			  	donHangList_Vy.init("");
		    			    // khởi tạo ra 1 session
		    			    session.setAttribute("obj", donHangList_Vy);
		    			    String nextJSP = "/TrainingGESO/pages/Erp/DonHangList_Vy.jsp";
		    				response.sendRedirect(nextJSP);
		    		}
		    }else{
		    	donHang_Vy.CreateRsKH();
		    	session.setAttribute("donHangMoi", donHang_Vy);
		    	String nextJSP = "/TrainingGESO/pages/Erp/DonHangNew_Vy.jsp";
		    }
		     
	}

}
