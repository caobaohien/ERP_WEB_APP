package geso.training.servlets.donhang;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import geso.dms.center.util.Utility;
import geso.training.bean.donhang.IDonHangList;
import geso.training.bean.donhang.imp.DonHangList;
import geso.training.bean.donhang.IDonHang;
import geso.training.bean.donhang.imp.DonHang;
/**
 * Servlet implementation class Donhanglistsvl
 */
@WebServlet("/Donhanglistsvl")
public class Donhanglistsvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Donhanglistsvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// lay thong tin nguoi dung dang nhap
		HttpSession session=request.getSession();
		String userId=(String)session.getAttribute("userId");
		String userTen=(String)session.getAttribute("userTen");
		// khoi tao don hang
		IDonHangList obj=new DonHangList();
		obj.setUserId(userId);
		obj.setUserTen(userTen);
		//String search="";
		//obj.init(search);
		String searchQuery = getSearchQuery(request, obj);
		obj.init(searchQuery);
		session.setAttribute("ob", obj);
		String nextjsp="/TrainingGESO/pages/Erp/donhanglist.jsp";
		response.sendRedirect(nextjsp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html, charset=UTF-8");
		
		HttpSession session=request.getSession();
		String userId=(String)session.getAttribute("userId");
		String userTen=(String)session.getAttribute("userTen");
		String action=request.getParameter("action");
		
		IDonHangList donhanglist =new DonHangList();
		String madh = request.getParameter("sodonhang");
		String tenkh = request.getParameter("khId");
		
		madh = (madh == null) ? "" : madh;
		tenkh = (tenkh == null) ? "" : tenkh;
		donhanglist.setMadh(madh);
		donhanglist.setKhachhang(tenkh);
		System.out.println(tenkh);
		if(action==null){
			action="";
		}
		if(action.equals("timkiem")){
			donhanglist.init("");
			session.setAttribute("ob", donhanglist);
			String nextjsp="/TrainingGESO/pages/Erp/donhanglist.jsp";
			response.sendRedirect(nextjsp);
		}
		else if(action.equals("search")) {
			//donHangList_Vy.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
			
			String searchQuery = getSearchQuery(request, donhanglist);
			donhanglist.init(searchQuery);
			
	    	session.setAttribute("ob", donhanglist);
	    	response.sendRedirect("/TrainingGESO/pages/Erp/donhanglist.jsp");
		}else if(action.equals("moi")){
			IDonHang dh= new DonHang();
			dh.setUserId(userId);
			session.setAttribute("donhang", dh);
			String nextjsp="/TrainingGESO/pages/Erp/donhangnew.jsp";
			response.sendRedirect(nextjsp);
		}else{
			/*String makhachhang=request.getParameter("khId");
			IDonHangList obj =new DonHangList();
			obj.setKhachhang(makhachhang);
			obj.init("");*/
			donhanglist.init("");
			session.setAttribute("ob", donhanglist);
			String nextjsp="/TrainingGESO/pages/Erp/donhanglist.jsp";
			response.sendRedirect(nextjsp);
		}
	}
	private String getSearchQuery(HttpServletRequest request, IDonHangList obj) {
		Utility utility = new Utility();
		
		String query = "SELECT BH.PK_SEQ, " +
				"KH.TEN, " +
				"ISNULL(BH.TONGGIATRI,'')TONGGIATRI, " +
				"ISNULL(BH.TRANGTHAI, '')TRANGTHAI, " +
				"BH.NGAYNHAP, " +
				"BH.NGAYTAO, " +
				"NV.TEN AS NGUOITAO, " +
				"BH.NGAYSUA, "+
				"NV1.TEN AS NGUOISUA "+
				"FROM DONHANG BH " +
				"LEFT JOIN KHACHHANG KH ON BH.KHACHHANG_FK = KH.PK_SEQ " +
				"LEFT JOIN NHANVIEN NV ON BH.NGUOITAO = NV.PK_SEQ " +
				"LEFT JOIN NHANVIEN NV1 ON BH.NGUOISUA = NV1.PK_SEQ " +
				"WHERE 1=1";
		
		if (obj.getMadh().length() > 0) {
			query += " AND BH.PK_SEQ LIKE '%" + obj.getMadh() + "%'";
		}
		if (obj.getKhachhang().length() > 0) {
			System.out.println(obj.getKhachhang());
			query += " AND KH.PK_SEQ LIKE '%" + obj.getKhachhang() + "%'";
		}
		/*if (obj.getTrangThai().length() > 0) {
			query += " AND BH.TRANGTHAI = " + obj.getTrangThai();
		}*/
		return query;
	}
}

