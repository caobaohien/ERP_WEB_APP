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

public class SanPhamSvl_Thu extends HttpServlet {

	//servlet
private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SanPhamSvl_Thu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Dinh dang unicode
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//Tao session de chua thong tin cua nguoi tao/nguoi sua
		HttpSession httpSession = request.getSession();
		Utility utility = new Utility();
		String queryString = request.getQueryString();
		
		//Lay thong tin cua mot user trong session
		String userId = utility.getUserId(queryString);
	    if (userId.length()==0) {
	    	userId = utility.antiSQLInspection(request.getParameter("userId"));
	    }
	    
	    //tạo đối tượng sản phẩm
	    ISanPhamList_Thu obj = new SanPhamList_Thu();
	 
	    //lấy mã sản phẩm và control
		String maSanPham = utility.getId(queryString);
		String action = utility.getAction(queryString);
		
		System.out.println(maSanPham);
		System.out.println("action: " +action);
		
		//lấy mã sản phẩm
	    obj.setMaSanPham(maSanPham);
	  
	    
	    
	    if (action.equals("delete")) {
			obj.deleteSanPham();
			//obj.init("");
		} 
	    
	    //chuyển đi
	    obj.init("");
	    httpSession.setAttribute("obj",obj);
	    String nextJSP = "/TrainingGESO/pages/Erp/SanPhamList_Thu.jsp";
		response.sendRedirect(nextJSP);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Dinh dang unicode
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		//tạo đối tượng
		ISanPhamList_Thu obj = new SanPhamList_Thu();
		String action = request.getParameter("action");
		
		//NHẬN CÁC THÔNG TIN TỪ GIAO DIỆN JSP 
		//NÀY LÀ CỦA TEXT INPUT (NHỚ ĐẶT TÊN GIỐNG TÊN CỦA TEXTBOX)
		String maSanPham = request.getParameter("maSanPham");
		String tenSanPham = request.getParameter("tenSanPham");
		String tenDonVi = request.getParameter("tenDonVi");
		
		
		
		//chuyển action nếu null thì rỗng
		action = (action == null) ? "" : action;
		maSanPham = (maSanPham == null) ? "" : maSanPham;
		tenSanPham = (tenSanPham == null) ? "" : tenSanPham;
		tenDonVi = (tenDonVi == null) ? "" : tenDonVi;
		
		//gán session 
		HttpSession session = request.getSession();
		obj.setMaSanPham(maSanPham);
		obj.setTenSanPham(tenSanPham);
		obj.setMaDoVi(tenDonVi);
		obj.setTenSanPham(tenSanPham);
		
		
		//in ra coi chơi
		System.out.println(action);
	
		
		//thực hiện các điều khiển
		if (action.equals("timkiem")) {
			
			obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
			//lấy yêu cầu
			String searchQuery = getSearchQuery(request, obj);
			obj.init(searchQuery);
			//đưa đối tượng vào session đưa đi đến jsp
	    	session.setAttribute("obj", obj);
	    	response.sendRedirect("/TrainingGESO/pages/Erp/SanPhamList_Thu.jsp");
	    	
	    	
		}else if (action.equals("taomoi")) {
		    ISanPham_Thu obj1 = new SanPham_Thu();
		    obj1.CreateDonVi();
		    session.setAttribute("obj", obj1);	    
		    String nextJSP = "/TrainingGESO/pages/Erp/SanPhamNew_Thu.jsp";
			response.sendRedirect(nextJSP);
		}
		
			
		
	}

	
	private String getSearchQuery(HttpServletRequest request, ISanPhamList_Thu obj) {
		
		Utility utility = new Utility();
		
		String query = 	"SELECT SP.PK_SEQ AS MASANPHAM,"+
						"SP.TEN AS TENSANPHAM, "+
						"DV.TEN AS TENDONVI, SP.TRANGTHAI AS TRANGTHAI, "+
						"SP.SOLUONG AS SOLUONG, SP.GIABAN AS GIABAN "+
						"from SANPHAM SP LEFT JOIN DONVI DV ON SP.DONVI_FK=DV.PK_SEQ "+
						" WHERE 1=1 ";

		//tìm theo mã đơn hàng
		if (obj.getMaSanPham().length() > 0) {
			query += " AND SP.PK_SEQ = '" + obj.getMaSanPham() + "'";
		}
		
		//tìm theo mã khách hàng
		if (obj.getTenSanPham().length() > 0) {
			System.out.println(obj.getTenSanPham());
			query += " AND SP.TEN like '%" + obj.getTenSanPham() + "%'";
		}
	
		//tìm theo trang thái
		if (obj.getMaDoVi().length() > 0) {
			query += " AND DV.PK_SEQ = " + obj.getMaDoVi();
		}
				
		System.out.print("câu truy tim kiem: " +query);
		return query;
	}

	
	
	
}
