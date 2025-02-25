package geso.erp.servlets.donbanhang_trinh;

import geso.dms.center.util.Utility;
import geso.erp.beans.donbanhang_trinh.IDonBanHangList_trinh;
import geso.erp.beans.donbanhang_trinh.IDonBanHang_Trinh;
import geso.erp.beans.donbanhang_trinh.imp.DonBanHangList_trinh;
import geso.erp.beans.donbanhang_trinh.imp.DonBanHang_Trinh;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DonBanHang_TrinhSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonBanHang_TrinhSvl() {
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
		
		String queryString = request.getQueryString(); //lấy chuỗi truyền
		
		//Lay thong tin cua mot user trong session
		String userId = utility.getUserId(queryString);
	    if (userId.length()==0) {
	    	userId = utility.antiSQLInspection(request.getParameter("userId")); 
	    }
	    
	    IDonBanHangList_trinh obj = new DonBanHangList_trinh();
	    
	    
	    //lay lenh tu action
	    String action = utility.getAction(queryString); // lây tên của action
	    action = (action == null) ? "" : action;
	    //lay ma
	    String maDonHang = utility.getId(queryString);
	    
	    IDonBanHang_Trinh objchot = new DonBanHang_Trinh();
    	objchot.setMaDonHang(maDonHang);
	   
	    if(action.equals("chot")){ //truong hop la chot don hang
	    	
	    	objchot.chotDonHang();
	    	
	    }
	    		
	    if(action.equals("delete")){
	    	
	    	objchot.deleteDonHang();
	    }
	   
	    
	   // objchot.init("");	
	    
	    obj.init("");
		httpSession.setAttribute("obj", obj);
	    String nextJSP = "/TrainingGESO/pages/Erp/DonBanHangList_Trinh.jsp";
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
		
		IDonBanHangList_trinh obj = new DonBanHangList_trinh();
		String action = request.getParameter("action");
		
		String maDonHang = request.getParameter("maDonHang");
		String tenKhachHang = request.getParameter("khachHang");
		String trangThai = request.getParameter("trangThai");
	//System.out.println(donHangList_Vy.getTrangThai());
		
		action = (action == null) ? "" : action;
		maDonHang = (maDonHang == null) ? "" : maDonHang;
		tenKhachHang = (tenKhachHang == null) ? "" : tenKhachHang;
		trangThai = (trangThai == null) ? "" : trangThai;
		
		//để giữ giá trị lại trên input khi tìm kiếm
		HttpSession session = request.getSession();
		obj.setMaDonHang(maDonHang);
		obj.setMaKhachHang(tenKhachHang);
		obj.setTrangThai(trangThai);
		
		System.out.println(action + ", " + obj.getMaDonHang() + "," + obj.getMaKhachHang() + ", " + obj.getTrangThai());
	
		
		
		if (action.equals("timkiem")) {
			obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
			
			String searchQuery = getSearchQuery(request, obj);
			obj.init(searchQuery);
			
	    	session.setAttribute("obj", obj);
	    	
	    	response.sendRedirect("/TrainingGESO/pages/Erp/DonBanHangList_Trinh.jsp");
		}else if (action.equals("taomoi")) {
		    IDonBanHang_Trinh objnew = new DonBanHang_Trinh();
		    objnew.LayKhachHang();
		    session.setAttribute("action", "insert");
		    session.setAttribute("objnew", objnew);	    
		    String nextJSP = "/TrainingGESO/pages/Erp/DonBanHangNew_Trinh.jsp";
			response.sendRedirect(nextJSP);
		}
		
	}

	
	public String getSearchQuery(HttpServletRequest request, IDonBanHangList_trinh obj) {
		
		
		String query="SELECT BH.PK_SEQ AS MADONHANG, " +
				"BH.NGAYCHUNGTU AS NGAYCHUNGTU, " +
				"BH.KHACHHANG_FK AS MAKHACHHANG, " +
				"KH.TEN AS TENKHACHHANG, " +
				"ISNULL(BH.TRANGTHAI, '') AS TRANGTHAI, " +
				"BH.TONGTIEN, " +
				"BH.NGAYTAO, " +
				"BH.NGUOITAO AS MANGUOITAO, " +
				"NV.TEN AS TENNGUOITAO " +
				"FROM BANHANG BH " +
				"LEFT JOIN KHACHHANG KH ON BH.KHACHHANG_FK = KH.PK_SEQ " +
				"LEFT JOIN NHANVIEN NV ON BH.NGUOITAO = NV.PK_SEQ WHERE 1=1";
		
		if (obj.getMaDonHang().length() > 0) {
			query += " AND BH.PK_SEQ LIKE '%" + obj.getMaDonHang() + "%'";
		}
		if (obj.getMaKhachHang().length() > 0) {
			System.out.println(obj.getMaKhachHang());
			query += " AND KH.PK_SEQ = " + obj.getMaKhachHang();
		}
		if (obj.getTrangThai().length() > 0) {
			query += " AND BH.TRANGTHAI = " + obj.getTrangThai();
		}
		return query;
	}
}
