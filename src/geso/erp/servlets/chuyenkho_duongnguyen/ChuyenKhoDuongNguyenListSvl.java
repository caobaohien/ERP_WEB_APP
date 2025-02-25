package geso.erp.servlets.chuyenkho_duongnguyen;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import geso.dms.center.util.Utility;
import geso.erp.beans.chuyenkho_duongnguyen.IChuyenKho;
import geso.erp.beans.chuyenkho_duongnguyen.IChuyenKhoList;
import geso.erp.beans.chuyenkho_duongnguyen.imp.ChuyenKho;
import geso.erp.beans.chuyenkho_duongnguyen.imp.ChuyenKhoList;

/**
 * Servlet implementation class ChuyenKhoDuongNguyenListSvl
 */
@WebServlet("/ChuyenKhoDuongNguyenListSvl")
public class ChuyenKhoDuongNguyenListSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChuyenKhoDuongNguyenListSvl() {
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
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		Utility util = new Utility();
		String queryString=request.getQueryString();
		String userId =util.getUserId(queryString);
		if(userId.length() ==0)
			userId=util.antiSQLInspection(request.getParameter("userId"));
		String msg = "";
		IChuyenKhoList ckList = new ChuyenKhoList();
		ckList.init("");
		session.setAttribute("chuyenKhoList", ckList);
		String nextJSP ="/TrainingGESO/pages/Erp/ChuyenKhoListDuongNguyen.jsp";
		response.sendRedirect(nextJSP);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		IChuyenKhoList obj = new ChuyenKhoList();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		Utility util = new Utility();
		String ma = request.getParameter("ma");
		String khoChuyen = request.getParameter("khoChuyen");
		String khoNhan = request.getParameter("khoNhan");
		String trangThai = request.getParameter("trangThai");
		obj.setMa(ma);
		obj.setKhoChuyen(khoChuyen);
		obj.setKhoNhan(khoNhan);
		obj.setTrangThai(trangThai);
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		if(action.equals("timkiem")){
			String query = getSearchQuery(request,obj);
			obj.init(query);
			session.setAttribute("chuyenKhoList", obj);
			String nextJSP = "/TrainingGESO/pages/Erp/ChuyenKhoListDuongNguyen.jsp";
			response.sendRedirect(nextJSP);
		}else if (action.equals("taomoi")){
			IChuyenKho ck = new ChuyenKho();
			ck.CreateRsKhoChuyen();
			ck.CreateRsKhoNhan();
			session.setAttribute("chuyenKhoNew", ck);
			String nextJSP = "/TrainingGESO/pages/Erp/ChuyenKhoNewDuongNguyen.jsp";
			response.sendRedirect(nextJSP);
		}else{
			obj.init("");
			session.setAttribute("chuyenKhoList", obj);
			String nextJSP="/TrainingGESO/pages/Erp/ChuyenKhoListDuongNguyen.jsp";
			response.sendRedirect(nextJSP);
		}
	}
	private String getSearchQuery(HttpServletRequest request, IChuyenKhoList obj) {
		Utility util = new Utility();

		String query = "";
		query = "SELECT CK.PK_SEQ ,CK.LYDO,NVTAO.TEN AS NHANVIENTAO,CK.NGAYTAO,NVSUA.TEN AS NHANVIENSUA,CK.NGAYSUA FROM CHUYENKHO CK"
				+" LEFT JOIN KHO KN ON CK.KHONHAN=KN.PK_SEQ" 
				+" LEFT JOIN KHO KC ON CK.KHOCHUYEN=KC.PK_SEQ" 
				+" LEFT JOIN NHANVIEN NVTAO ON CK.NGUOITAO=NVTAO.PK_SEQ"
				+" LEFT JOIN NHANVIEN NVSUA ON CK.NGUOISUA=NVSUA.PK_SEQ WHERE 1=1";
		if (obj.getMa().length() > 0) {
			query += " and  CK.PK_SEQ LIKE '%" + obj.getMa() + "%'";
		}
		if (obj.getKhoChuyen().length() > 0) {
			query += " AND CK.KHOCHUYEN = '" + obj.getKhoChuyen() + "'";
		}
		if (obj.getKhoNhan().length() > 0) {
			query += " AND CK.KHONHAN = '" + obj.getKhoNhan() + "'";
		}
		if (obj.getTrangThai().length() > 0 && !obj.getTrangThai().equals("-1")) {
			query += " AND CK.TRANGTHAI = '" + obj.getTrangThai() + "'";
		}
		return query;
	}

}
