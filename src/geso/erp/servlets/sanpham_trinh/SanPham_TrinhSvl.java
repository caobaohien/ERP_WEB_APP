package geso.erp.servlets.sanpham_trinh;

import geso.dms.center.util.Utility;
import geso.erp.beans.donbanhang_trinh.IDonBanHangList_trinh;
import geso.erp.beans.donbanhang_trinh.IDonBanHang_Trinh;
import geso.erp.beans.donbanhang_trinh.imp.DonBanHangList_trinh;
import geso.erp.beans.donbanhang_trinh.imp.DonBanHang_Trinh;
import geso.erp.beans.sanpham_trinh.ISanPhamList_Trinh;
import geso.erp.beans.sanpham_trinh.ISanPham_Trinh;
import geso.erp.beans.sanpham_trinh.imp.SanPhamList_Trinh;
import geso.erp.beans.sanpham_trinh.imp.SanPham_Trinh;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SanPham_TrinhSvl
 */
@WebServlet("/SanPham_TrinhSvl")
public class SanPham_TrinhSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SanPham_TrinhSvl() {
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
		
		//Lay thong tin cua mot user trong session
		String userId = utility.getUserId(queryString);
	    if (userId.length()==0) {
	    	userId = utility.antiSQLInspection(request.getParameter("userId")); 
		
	    }
		
		
		
		//tạo session để chứa thông tin của người sử dụng
		HttpSession httpSession = request.getSession();
		
		//nếu như update
		//lấy action
		String action = utility.getAction(queryString);
		 action = (action == null) ? "" : action;
		if(action.equals("delete")){
			
			//lấy id
			String maSanPham = utility.getId(queryString);
			
			ISanPham_Trinh objd = new SanPham_Trinh();
			objd.setMaSanPham(maSanPham);
			objd.setUserId(userId);
			
			objd.DeleteSP();
		}
		
		//khởi tạo đối tượng
		ISanPhamList_Trinh obj = new SanPhamList_Trinh();
		obj.init("");
		obj.layDonvi();
		
		//gán đối tượng qua httpsession để chuyển lên jsp
		httpSession.setAttribute("obj", obj);
		String nextJSP = "/TrainingGESO/pages/Erp/SanPhamList_Trinh.jsp";
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
		
		ISanPhamList_Trinh obj = new SanPhamList_Trinh();
		String action = request.getParameter("action");
		
		String maSanPham = request.getParameter("maSanPham");
		String tenSanPham = request.getParameter("tenSanPham");
		String donVi = request.getParameter("donViTinh");
	
		
		action = (action == null) ? "" : action;
		maSanPham = (maSanPham == null) ? "" : maSanPham;
		tenSanPham = (tenSanPham == null) ? "" : tenSanPham;
		donVi = (donVi == null) ? "" : donVi;
		
		//để giữ giá trị lại trên input khi tìm kiếm
		HttpSession session = request.getSession();
		obj.setMaSnaPham(maSanPham);
		obj.setTenSanPham(tenSanPham);
		obj.setMaDonVi(donVi);
		
		System.out.println(action + ", " + obj.getMaSnaPham() + "," + obj.getTenSanPham() + ", " + obj.getMaDonVi());

		if (action.equals("timkiem")) {
			obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
			
			String searchQuery = this.getSearchQuery(request, obj);
			System.out.println(searchQuery);
			obj.init(searchQuery);
			
	    	session.setAttribute("obj", obj);
	    	
	    	response.sendRedirect("/TrainingGESO/pages/Erp/SanPhamList_Trinh.jsp");
		}else if (action.equals("taomoi")) { //khi nhấn vào dấu cộng bên trên
		    ISanPham_Trinh objnew = new SanPham_Trinh();
		    objnew.layDonVi();
		    session.setAttribute("objnew", objnew);	    
		    String nextJSP = "/TrainingGESO/pages/Erp/SanPhamNew_Trinh.jsp";
			response.sendRedirect(nextJSP);
		}
	}
	public String getSearchQuery(HttpServletRequest request, ISanPhamList_Trinh obj) {
		
		String query="SELECT SANPHAM.PK_SEQ AS MASANPHAM, SANPHAM.MA AS MA, SANPHAM.TEN AS TENSANPHAM, " +
						"DONVI.TEN AS TENDONVI, ISNULL(SANPHAM.TRANGTHAI,'1') AS TRANGTHAI, SANPHAM.SOLUONG, " +
						"NHANVIEN.TEN as TENNGUOITAO, SANPHAM.NGAYTAO as NGAYTAO, SANPHAM.GIABAN AS GIABAN " +
						"FROM SANPHAM LEFT JOIN DONVI ON SANPHAM.DONVI_FK = DONVI.PK_SEQ " +
						"LEFT JOIN NHANVIEN ON SANPHAM.NGUOITAO = NHANVIEN.PK_SEQ WHERE 1=1";
		
		if (obj.getMaSnaPham().length() > 0) {
			query += " AND SANPHAM.MA LIKE '%" + obj.getMaSnaPham() + "%'";
		}
		if (obj.getTenSanPham().length() > 0) {
			
			//thêm hàm bỏ dấu vào để so sánh có dấu vào không dấu.
			query += " AND dbo.ftBoDau(SANPHAM.TEN) LIKE  dbo.ftBoDau('%" + obj.getTenSanPham() +"%')";
		}
		if (obj.getMaDonVi().length() > 0) {
			query += " AND SANPHAM.DONVI_FK  LIKE '%" + obj.getMaDonVi() +"%'";
		}
		return query;
	}
}
