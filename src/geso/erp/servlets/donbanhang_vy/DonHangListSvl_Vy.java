package geso.erp.servlets.donbanhang_vy;

import geso.dms.center.util.Utility;
import geso.erp.beans.donbanhang_vy.IDonHangList_Vy;
import geso.erp.beans.donbanhang_vy.IDonHang_Vy;
import geso.erp.beans.donbanhang_vy.imp.DonHangList_Vy;
import geso.erp.beans.donbanhang_vy.imp.DonHang_Vy;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class DonHangSvl_Vy
 */
public class DonHangListSvl_Vy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonHangListSvl_Vy() {
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
	    
	    IDonHangList_Vy iDonHangList_Vy = new DonHangList_Vy();
	    iDonHangList_Vy.init("");
	  
	    
		String maDonHang = utility.getId(queryString);
		String action = utility.getAction(queryString);
		
		System.out.println(maDonHang);
		System.out.println("action: " +action);

		IDonHang_Vy iDonHang_Vy = new DonHang_Vy();
		iDonHang_Vy.setMaDonHang(maDonHang);
		
		if (action.equals("delete")) {
			iDonHang_Vy.deleteDonHang();
		/*	iDonHangList_Vy.init("");*/
		} else if (action.equals("chot")) {
			iDonHang_Vy.chotDonHang();
		}
		iDonHangList_Vy.init("");
		
		httpSession.setAttribute("obj", iDonHangList_Vy);
	    String nextJSP = "/TrainingGESO/pages/Erp/DonHangList_Vy.jsp";
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
		
		IDonHangList_Vy donHangList_Vy = new DonHangList_Vy();
		String action = request.getParameter("action");
		
		String maDonHang = request.getParameter("maDonHang");
		String tenKhachHang = request.getParameter("khachHang");
		String trangThai = request.getParameter("trangThai");
		System.out.println(donHangList_Vy.getTrangThai());
		
		action = (action == null) ? "" : action;
		maDonHang = (maDonHang == null) ? "" : maDonHang;
		tenKhachHang = (tenKhachHang == null) ? "" : tenKhachHang;
		trangThai = (trangThai == null) ? "" : trangThai;
		
		HttpSession session = request.getSession();
		donHangList_Vy.setMaDonHang(maDonHang);
		donHangList_Vy.setMaKhachHang(tenKhachHang);
		donHangList_Vy.setTrangThai(trangThai);
		
		System.out.println(action);
		System.out.println(donHangList_Vy.getTrangThai());
		
		
		if (action.equals("timkiem")) {
			donHangList_Vy.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
			
			String searchQuery = getSearchQuery(request, donHangList_Vy);
			donHangList_Vy.init(searchQuery);
			
	    	session.setAttribute("obj", donHangList_Vy);
	    	response.sendRedirect("/TrainingGESO/pages/Erp/DonHangList_Vy.jsp");
		}else if (action.equals("taomoi")) {
		    IDonHang_Vy iDonHang_Vy = new DonHang_Vy();
		    iDonHang_Vy.CreateRsKH();
		    session.setAttribute("donHangMoi", iDonHang_Vy);	    
		    String nextJSP = "/TrainingGESO/pages/Erp/DonHangNew_Vy.jsp";
			response.sendRedirect(nextJSP);
		}
		
	}

	
	private String getSearchQuery(HttpServletRequest request, IDonHangList_Vy obj) {
		Utility utility = new Utility();
		
		String query = "SELECT BH.PK_SEQ AS MADONHANG, " +
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
						"LEFT JOIN NHANVIEN NV ON BH.NGUOITAO = NV.PK_SEQ " +
						"WHERE 1=1";
		
		if (obj.getMaDonHang().length() > 0) {
			query += " AND BH.PK_SEQ LIKE '%" + obj.getMaDonHang() + "%'";
		}
		if (obj.getMaKhachHang().length() > 0) {
			System.out.println(obj.getMaKhachHang());
			query += " AND KH.PK_SEQ LIKE '%" + obj.getMaKhachHang() + "%'";
		}
		if (obj.getTrangThai().length() > 0) {
			query += " AND BH.TRANGTHAI = " + obj.getTrangThai();
		}
		return query;
	}
}
