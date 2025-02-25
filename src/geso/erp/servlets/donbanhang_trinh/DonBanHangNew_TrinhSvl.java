package geso.erp.servlets.donbanhang_trinh;

import geso.dms.center.util.Utility;
import geso.erp.beans.donbanhang_trinh.IDonBanHangList_trinh;
import geso.erp.beans.donbanhang_trinh.IChiTietDonHang_Trinh;
import geso.erp.beans.donbanhang_trinh.IDonBanHang_Trinh;
import geso.erp.beans.donbanhang_trinh.imp.DonBanHangList_trinh;
import geso.erp.beans.donbanhang_trinh.imp.ChiTietDonHang_Trinh;
import geso.erp.beans.donbanhang_trinh.imp.DonBanHang_Trinh;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
/**
 * Servlet implementation class DonBanHangNew_TrinhSvl
 */
@WebServlet("/DonBanHangNew_TrinhSvl")
public class DonBanHangNew_TrinhSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonBanHangNew_TrinhSvl() {
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
	  
	    IDonBanHang_Trinh obj = new DonBanHang_Trinh();

	    
	    if(action.equals("update"))
	    {
	    	obj.setMaDonHang(maDonHang);
	    	obj.init("");
	    	session.setAttribute("action", "update");
	    	obj.LayKhachHang();
		    
		    session.setAttribute("objnew", obj);
		    String nextJSP = "/TrainingGESO/pages/Erp/DonBanHangNew_Trinh.jsp";
			response.sendRedirect(nextJSP);
	    }
	    else if(action.equals("display"))
	    {
	    	obj.setMaDonHang(maDonHang);
	    	obj.init("");
	    	session.setAttribute("action", "display");
	    	obj.LayKhachHang();
		    
		    session.setAttribute("objnew", obj);
		    String nextJSP = "/TrainingGESO/pages/Erp/DonBanHangDisplay_Trinh.jsp";
			response.sendRedirect(nextJSP);
	    }
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		IDonBanHang_Trinh obj = new DonBanHang_Trinh();
		
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    
	    
	    //lay action
	    obj.LayKhachHang();
	    String action = request.getParameter("action");
	    action = (action == null) ? "" : action;
	    
	    Utility util = new Utility();
	    
		HttpSession session = request.getSession();
	    String userId = util.antiSQLInspection(request.getParameter("userId"));
	    obj.setIdUser(userId);
	    String msg = "";
	    
	    String maDonHang = util.antiSQLInspection(request.getParameter("maDonHang"));
	    obj.setMaDonHang(maDonHang == null ? "" : maDonHang);
	    System.out.println(maDonHang);
	  
	    String maKhachHang = util.antiSQLInspection(request.getParameter("maKhachHang"));
	  
	    obj.setMaKhachHang(maKhachHang);
	    
	    String trangThai = util.antiSQLInspection(request.getParameter("trangThai")); 
	    obj.setTrangThai(trangThai == null ? "0" : trangThai);
	    
	    String ngayChungTu = util.antiSQLInspection(request.getParameter("ngayChungTu")); 
	    obj.setNgayChungTu(ngayChungTu == null ? "" : ngayChungTu);
	    
	    String tongTien = util.antiSQLInspection(request.getParameter("tongTien"));     
	    obj.setSoTien(Double.parseDouble(tongTien.replaceAll(",", "")));
	 
	    obj.setMessage(msg);
	    
	    //lay thong tin chi tiet hoa don
	    String[] idSanPham = request.getParameterValues("PK_SEQ");
	    String[] donGia = request.getParameterValues("giaBan");
	    String[] soLuong = request.getParameterValues("soLuong");
	    String[] thanhTien = request.getParameterValues("thanhTien");
	    String[] tenSanPham = request.getParameterValues("tenSanPham");
	    String[] tenDonvi = request.getParameterValues("donViTinh");
	    String[] maSanPham = request.getParameterValues("maSanPham");
	    
	   
	    List<IChiTietDonHang_Trinh> danhsachsanpham = new ArrayList<IChiTietDonHang_Trinh>();
	    if(idSanPham != null){
	    	for(int i= 0; i< idSanPham.length; i++){
	    		if(idSanPham[i].trim().length() > 0){
			    	IChiTietDonHang_Trinh ctdh = new ChiTietDonHang_Trinh();
			    	ctdh.setIdSanPham(idSanPham[i]);
			    	ctdh.setDonGia(Double.parseDouble(donGia[i].replaceAll(",", "")));
			    	ctdh.setSoLuong(Double.parseDouble(soLuong[i].replaceAll(",", "")));
			    	ctdh.setThanhTien(Double.parseDouble(thanhTien[i].replaceAll(",", "")));
			    	ctdh.setMaDonHang(maDonHang);
			    	ctdh.setMaSanPham(maSanPham[i]);
			    	ctdh.setDonViTinh(tenDonvi[i]);
			    	ctdh.setTenSanPham(tenSanPham[i]);
			    	danhsachsanpham.add(ctdh);
	    		}
	    	}
	    	
	    	
	    }
	    obj.setDanhsachSanPham(danhsachsanpham);
	    //neu la update
	    if(action.equals("update")){
	    	if(obj.update()==false)
	    	{
	    		session.setAttribute("action", "update");
	    		obj.LayKhachHang();
	    		session.setAttribute("objnew",obj );
	    		String nextJSP = "/TrainingGESO/pages/Erp/DonBanHangNew_Trinh.jsp";
	    		response.sendRedirect(nextJSP);
	    	}
	    	else{
	    		IDonBanHangList_trinh objlist = new DonBanHangList_trinh();
	    		objlist.init("");
	    		obj.LayKhachHang();
	    		session.setAttribute("obj", objlist);
	    		String nextJSP = "/TrainingGESO/pages/Erp/DonBanHangList_Trinh.jsp";
	    		response.sendRedirect(nextJSP);
	    	}
	    }
	    //neu la insert
	    if(action.equals("insert")){
	    	if(obj.insert()==false)
	    	{
	    		session.setAttribute("action", "insert");
	    		obj.LayKhachHang();
	    		session.setAttribute("objnew",obj );
	    		String nextJSP = "/TrainingGESO/pages/Erp/DonBanHangNew_Trinh.jsp";
	    		response.sendRedirect(nextJSP);
	    	}
	    	else{
	    		IDonBanHangList_trinh objlist = new DonBanHangList_trinh();
	    		objlist.init("");
	    		obj.LayKhachHang();
	    		session.setAttribute("obj", objlist);
	    		String nextJSP = "/TrainingGESO/pages/Erp/DonBanHangList_Trinh.jsp";
	    		response.sendRedirect(nextJSP);
	    	}
	    }
	    if(action.equals("display")){
	    	session.setAttribute("action", "display");
    		obj.LayKhachHang();
    		
    		session.setAttribute("objnew",obj );
    		String nextJSP = "/TrainingGESO/pages/Erp/DonBanHangNew_Trinh.jsp";
    		response.sendRedirect(nextJSP);
	    }
	     
	}

}
