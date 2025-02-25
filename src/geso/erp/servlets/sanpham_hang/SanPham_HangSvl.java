package geso.erp.servlets.sanpham_hang;

import geso.dms.center.util.Utility;
import geso.erp.beans.sanpham_hang.ISanPhamList_Hang;
import geso.erp.beans.sanpham_hang.ISanPham_Hang;
import geso.erp.beans.sanpham_hang.imp.SanPhamList_Hang;
import geso.erp.beans.sanpham_hang.imp.SanPham_Hang;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SanPham_HangSvl extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SanPham_HangSvl() {
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
		response.setContentType("text/html; charset=UTF-8");//kieu tra ve la 1 trang html
		
		//tạo session để chứa thông tin của người sử dụng
		HttpSession httpSession = request.getSession();
		Utility utility = new Utility();
		String queryString = request.getQueryString(); //lấy chuỗi truyền
		
		//Lay thong tin cua mot user trong session
		String userId = utility.getUserId(queryString);
	    if (userId.length()==0) {
	    	userId = utility.antiSQLInspection(request.getParameter("userId")); 
		
	    }

		//khởi tạo đối tượng
		ISanPhamList_Hang obj = new SanPhamList_Hang();
		String action = utility.getAction(queryString); // lây tên của action
		action = (action == null) ? "" : action;
		    //lay ma
		String maSanPham = utility.getId(queryString);
		    
		ISanPham_Hang objnew = new SanPham_Hang();
	    objnew.setMaSanPham(maSanPham);		   	    		
		 if(action.equals("delete")){
		    	
		   objnew.deteleSanPham();
		 }    
		obj.init("");
		
		//gán đối tượng qua httpsession để chuyển lên jsp
		httpSession.setAttribute("obj", obj);
		String nextJSP = "/TrainingGESO/pages/Erp/SanPhamList_Hang.jsp";
		response.sendRedirect(nextJSP);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8"); //dinh dang UNICODE
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		ISanPhamList_Hang obj = new SanPhamList_Hang();
		String action = request.getParameter("action");
		
		String maSanPham = request.getParameter("maSanPham"); //tim kiem
		String tenSanPham = request.getParameter("tenSanPham");//tim kiem
		String donViTinh = request.getParameter("donViTinh");//tim kiem
		
		
		action = (action == null) ? "" : action;
		maSanPham = (maSanPham == null) ? "" : maSanPham;
		tenSanPham = (tenSanPham == null) ? "" : tenSanPham;
		donViTinh = (donViTinh == null) ? "" : donViTinh;
		
		System.out.println("action: " + action);
		System.out.println("Mã sản Phẩm: " + maSanPham);
		System.out.println("Tên sản phẩm: " + tenSanPham);
		System.out.println("Đơn vị tính: " + donViTinh);
		
		//để giữ giá trị lại trên input khi tìm kiếm
		HttpSession session = request.getSession();
		obj.setMaSanPham(maSanPham);
		obj.setTenSanPham(tenSanPham);
		obj.setDonViTinh(donViTinh);
		
		System.out.println("action " + action);		
		if (action.equals("timkiem")) {
			obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
			
			String searchQuery = this.getSearchQuery(request, obj);
			System.out.println(searchQuery);
			obj.init(searchQuery);
			
	    	session.setAttribute("obj", obj);	    	
	    	response.sendRedirect("/TrainingGESO/pages/Erp/SanPhamList_Hang.jsp");
	    	
		}else if (action.equals("taomoi")) { //khi nhấn vào dấu cộng bên trên
		    ISanPham_Hang objnew = new SanPham_Hang();
		    objnew.init("");
		    objnew.getDataDonVi();
		    session.setAttribute("action", "save");
		    session.setAttribute("objnew", objnew);
		    String nextJSP = "/TrainingGESO/pages/Erp/SanPhamUpdate_Hang.jsp";
			response.sendRedirect(nextJSP);
		}
		else{
			obj.init("");
			session.setAttribute("objnew", obj);
			String nextJSP = "/TrainingGESO/pages/Erp/SanPhamList_Hang.jsp";
	    	response.sendRedirect(nextJSP);
		}
	}
	public String getSearchQuery(HttpServletRequest request, ISanPhamList_Hang obj) {
		
		String query = "select sp.PK_SEQ as MASANPHAM, sp.TEN as TENSANPHAM, dv.TEN as DONVI, " +
		" sp.SOLUONG as SOLUONG, sp.GIABAN as GIABAN, sp.TRANGTHAI as TRANGTHAI " +
		" from SANPHAM as sp inner join DONVI as dv " + 
		" on sp.DONVI_FK = dv.PK_SEQ  where 1=1";
		
		if (obj.getMaSanPham().length() > 0) {
			query += " AND  sp.PK_SEQ LIKE '%" + obj.getMaSanPham() + "%'";
		}
		if (obj.getTenSanPham().length() > 0) {
			query += " AND  sp.TEN LIKE '%" + obj.getTenSanPham() +"%'";
		}
		if (obj.getDonViTinh().length() > 0) {
			query += " AND  dv.pk_seq = '" + obj.getDonViTinh() +"'";
		}		
		return query;
	}
}
