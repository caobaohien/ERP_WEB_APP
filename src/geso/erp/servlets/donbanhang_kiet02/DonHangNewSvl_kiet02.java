package geso.erp.servlets.donbanhang_kiet02;

import geso.dms.center.util.Utility;
import geso.erp.beans.donbanhang_kiet02.IDonHang;
import geso.erp.beans.donbanhang_kiet02.IDonHangList;
import geso.erp.beans.donbanhang_kiet02.ISanPham;
import geso.erp.beans.donbanhang_kiet02.imp.DonHang;
import geso.erp.beans.donbanhang_kiet02.imp.DonHangList;
import geso.erp.beans.donbanhang_kiet02.imp.SanPham;

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
 * Servlet implementation class DonHangNewSvl_kiet02
 */
@WebServlet("/DonHangNewSvl_kiet02")
public class DonHangNewSvl_kiet02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonHangNewSvl_kiet02() {
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
	    String userId = util.getUserId(querystring);
	    
	    if (userId.length() == 0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String action = util.getAction(querystring);
	    String maDonHang = util.getId(querystring);
	    String msg = "";
	    
	    IDonHang iDonHang = new DonHang();
	    iDonHang.setMaDonHang(maDonHang);
	    iDonHang.init("");
	    iDonHang.CreateRsKH();

	    
	    //Load gia tri da co san 
	    if(querystring.indexOf("update") >= 0){
	    	
	    	String id = request.getParameter("update");
	    	iDonHang.init("");
	    }
	    String maKhachHang = util.antiSQLInspection(request.getParameter("maKhachHang"));
	    System.out.println("Makhachhang " + maKhachHang);
	        
	    session.setAttribute("donHangMoi", iDonHang);
	    String nextJSP = "/TrainingGESO/pages/Erp/DonHangNew_kiet03.jsp";
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
	    Utility util = new Utility();
	    
		HttpSession session = request.getSession();
	    String userId = util.antiSQLInspection(request.getParameter("userId"));
	    String action = request.getParameter("action");
	    action = (action == null) ? "" : action;
		
	    IDonHang donHang = new DonHang();
	    donHang.CreateRsKH();
	    donHang.setUserId(userId);
	    String msg = "";
	    
	    String maDonHang = util.antiSQLInspection(request.getParameter("maDonHang"));
	    donHang.setMaDonHang(maDonHang == null ? "" : maDonHang);
	    //System.out.println(maDonHang);
	  
	    String maKhachHang = util.antiSQLInspection(request.getParameter("maKhachHang"));
	    donHang.setMaKhachHang(maKhachHang);
	    
	    String trangThai = util.antiSQLInspection(request.getParameter("trangThai")); 
	    donHang.setTrangThai(trangThai == null ? "0" : trangThai);
	    
	    String ngayChungTu = util.antiSQLInspection(request.getParameter("ngayChungTu")); 
	    donHang.setNgayChungTu(ngayChungTu == null ? "" : ngayChungTu);
	    
	    String tongTien = util.antiSQLInspection(request.getParameter("tongTien"));     
	    donHang.setTongTien(Float.parseFloat(tongTien.replaceAll(",", "")));
	    
	    donHang.setMsg(msg);
	    //-------------------------------------------------
	    String[] PK_SEQ = request.getParameterValues("PK_SEQ");
		String[] maSanPham = request.getParameterValues("maSanPham");
		String[] tenSanPham = request.getParameterValues("tenSanPham");
		String[] soLuong = request.getParameterValues("soLuong");
		String[] donViTinh = request.getParameterValues("donViTinh");
		String[] giaBan = request.getParameterValues("giaBan");
		String[] thanhtien = request.getParameterValues("thanhTien");
		
		List<ISanPham> danhSachSanPham = new ArrayList<ISanPham>();
		if (maSanPham != null) {
			for (int i = 0; i < maSanPham.length; i++) {
				if (tenSanPham[i].trim().length() > 0
						|| maSanPham[i].trim().length() > 0) {
					ISanPham sanPham = new SanPham(PK_SEQ[i], 
												maSanPham[i],
												donViTinh[i], 
												tenSanPham[i],
												Integer.parseInt(soLuong[i]),
												Float.parseFloat((giaBan[i]).replaceAll(",", "")));
					danhSachSanPham.add(sanPham);
				}
			}
		}
		donHang.setDanhSachSanPham(danhSachSanPham);
		
		if (action.equals("taomoi")) {
			session.setAttribute("readonly", "");
			System.out.println("Day la action tao moi");
		}

		if (action.equals("save")) {
			if (!donHang.save()) {
				// lưu không thành công
				donHang.CreateRsKH();
				session.setAttribute("donHangMoi", donHang);
				String nextJSP = "/TrainingGESO/pages/Erp/DonHangNew_kiet03.jsp";
				response.sendRedirect(nextJSP);
				System.out.println(donHang.getMsg());
			} else {
				IDonHangList donHangList = new DonHangList();
				donHangList.init("");
				// khởi tạo ra 1 session
				session.setAttribute("obj", donHangList);
				String nextJSP = "/TrainingGESO/pages/Erp/DonHangList_kiet03.jsp";
				response.sendRedirect(nextJSP);
			}
		} else {
			donHang.CreateRsKH();
			session.setAttribute("donHangMoi", donHang);
			String nextJSP = "/TrainingGESO/pages/Erp/DonHangNew_kiet03.jsp";
		}
	}
}
		
