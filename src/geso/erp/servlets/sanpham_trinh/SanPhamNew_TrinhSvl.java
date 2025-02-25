package geso.erp.servlets.sanpham_trinh;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cete.dynamicpdf.ob;

import geso.dms.center.util.Utility;
import geso.erp.beans.donbanhang_trinh.IDonBanHang_Trinh;
import geso.erp.beans.donbanhang_trinh.imp.DonBanHang_Trinh;
import geso.erp.beans.sanpham_trinh.ISanPhamList_Trinh;
import geso.erp.beans.sanpham_trinh.ISanPham_Trinh;
import geso.erp.beans.sanpham_trinh.imp.SanPhamList_Trinh;
import geso.erp.beans.sanpham_trinh.imp.SanPham_Trinh;

public class SanPhamNew_TrinhSvl extends HttpServlet  {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SanPhamNew_TrinhSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//định dạng 
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		Utility utility = new Utility();
		String queryString = request.getQueryString(); //lấy chuỗi truyền
		
		
		//tạo session để chứa thông tin của người sử dụng
		HttpSession httpSession = request.getSession();
		
		//lấy thông tin action
		String action = utility.getAction(queryString);
		//lấy userid
		
		String userId = utility.getUserId(queryString);
		if(userId.length() == 0) 
			userId = utility.antiSQLInspection(request.getParameter("userId"));
		
		String maSanPham = utility.getId(queryString);
		
		ISanPham_Trinh obj = new SanPham_Trinh();
		
		obj.setMaSanPham(maSanPham);
		obj.init("");
		obj.layDonVi();
		HttpSession session = request.getSession();
		session.setAttribute("objnew", obj);
		String nextJSP = "/TrainingGESO/pages/Erp/SanPhamNew_Trinh.jsp";
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
		//khởi tạo đối tượng sản phẩm
		ISanPham_Trinh obj = new SanPham_Trinh();
		
		//lấy thông tin action
		String action = request.getParameter("action");
		action = action == null? "" : action;		
		//nếu ấn nút save lại
		
		
		
		//lấy thông tin user và sản phẩm
		Utility utility = new Utility();
		String queryString = request.getQueryString(); //lấy chuỗi truyền
		
		String userId = utility.antiSQLInspection(request.getParameter("userId"));
		userId = userId == null? "" : userId; 
		obj.setUserId(userId);
		
		String maSanPham = utility.antiSQLInspection(request.getParameter("id"));
		maSanPham = maSanPham == null? "" : maSanPham;
		obj.setMaSanPham(maSanPham);
		
		String maSanPhamAo = utility.antiSQLInspection(request.getParameter("maSanPham"));
		maSanPhamAo = maSanPhamAo == null? "" : maSanPhamAo;
		obj.setMaSanPhamAo(maSanPhamAo);
		
		String tenSanPham = utility.antiSQLInspection(request.getParameter("tenSanPham"));
		tenSanPham = tenSanPham == null? "" : tenSanPham;
		obj.setTenSanPham(tenSanPham);
		
		String maDonVi = utility.antiSQLInspection(request.getParameter("donVi"));
		maDonVi = maDonVi == null? "" : maDonVi;
		obj.setMaDonVi(maDonVi);
		
		String trangThai = utility.antiSQLInspection(request.getParameter("trangThai"));
		trangThai = trangThai == null? "0" : trangThai;
		obj.setTrangThai(trangThai);
		
		String soLuong = utility.antiSQLInspection(request.getParameter("soLuong"));
		soLuong = soLuong == null? "0" : soLuong;
		
		//kiểm tra xem người dùng nhập đúng số ko
		if(kiemtraso(soLuong)==true){ 
			obj.setSoLuong(Double.parseDouble(soLuong));
			
			
			String giaBan = utility.antiSQLInspection(request.getParameter("giaBan"));
			giaBan = giaBan == null? "0" : giaBan;
			if(kiemtraso(giaBan)==true){
				obj.setGiaBan(Double.parseDouble(giaBan));
				
				
				if(maSanPhamAo != ""){
					if(action.equals("save")){
						//nếu save lại thất bại
						if(obj.save() == false){
							obj.layDonVi();
			    			session.setAttribute("objnew", obj);
				    		String nextJSP = "/TrainingGESO/pages/Erp/SanPhamNew_Trinh.jsp";
				    		response.sendRedirect(nextJSP);
				    		System.out.println(obj.getMessage());
						}
						else{
						//save thanh cong
							ISanPhamList_Trinh objnew = new SanPhamList_Trinh();
							objnew.init("");
							objnew.layDonvi();
					    	session.setAttribute("obj", objnew);
					    	String nextJSP = "/TrainingGESO/pages/Erp/SanPhamList_Trinh.jsp";
					    	response.sendRedirect(nextJSP);
						}
					}
					else{
						//nếu trở về trang trước
						ISanPham_Trinh objnew = new SanPham_Trinh();
						objnew.init("");
						
						session.setAttribute("obj", objnew);
			    		String nextJSP = "/TrainingGESO/pages/Erp/SanPhamList_Trinh.jsp";
			    		response.sendRedirect(nextJSP);
			    	
					}
				}
				else{
					obj.layDonVi();
					obj.setMessage("BẠN PHẢI NHẬP MÃ SẢN PHẨM");
			    	session.setAttribute("objnew", obj);
			    	String nextJSP = "/TrainingGESO/pages/Erp/SanPhamNew_Trinh.jsp";
			    	response.sendRedirect(nextJSP);
				}
				
				
			}
			else {
				obj.layDonVi();
				obj.setMessage("Giá bán phải là số phải là số");
		    	session.setAttribute("objnew", obj);
		    	String nextJSP = "/TrainingGESO/pages/Erp/SanPhamNew_Trinh.jsp";
		    	response.sendRedirect(nextJSP);
			}
		}
		else {
			obj.layDonVi();
			obj.setMessage("Số lượng phải là số");
	    	session.setAttribute("objnew", obj);
	    	String nextJSP = "/TrainingGESO/pages/Erp/SanPhamNew_Trinh.jsp";
	    	response.sendRedirect(nextJSP);
		}
	
	}
	
	//hàm kiểm tra số nhập vào có là số hay không
	public boolean kiemtraso(String text){
		try {
			double so = Double.parseDouble(text);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}
