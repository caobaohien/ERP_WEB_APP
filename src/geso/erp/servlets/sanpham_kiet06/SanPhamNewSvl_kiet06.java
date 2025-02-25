package geso.erp.servlets.sanpham_kiet06;

import geso.dms.center.util.Utility;
import geso.erp.beans.sanpham_kiet06.ISanPham;
import geso.erp.beans.sanpham_kiet06.ISanPhamList;
import geso.erp.beans.sanpham_kiet06.imp.SanPham;
import geso.erp.beans.sanpham_kiet06.imp.SanPhamList;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/SanPhamNewSvl_kiet06")
public class SanPhamNewSvl_kiet06 extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public SanPhamNewSvl_kiet06() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();
		Utility util = new Utility();
		
		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		if (userId.length() > 0){
			userId = util.antiSQLInspection(request.getParameter(userId));
		}
		String id = util.getId(querystring);
		String action = util.getAction(querystring);
		String msg = "";
		
		ISanPham sp = new SanPham();
		sp.setPk_seq(id);
		sp.init();
		/*System.out.println("ID: "+ sp.getPk_seq());
		System.out.println("Ma: "+ sp.getMa());
		System.out.println("Ten: "+ sp.getTen());
		System.out.println("Don vi: "+ sp.getDonViTinh());
		System.out.println("Trang thai: "+ sp.getTrangThai());*/
		sp.createRsDvt();
		session.setAttribute("SanPhamMoi", sp);
		String nextJSP = "/TrainingGESO/pages/Erp/SanPhamNew_kiet06.jsp";
		response.sendRedirect(nextJSP);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();
		Utility util = new Utility();
		
		String action = request.getParameter("action");
		System.out.println(action == null? "Action: null": "Action: "+ action);
		
		ISanPham sp = new SanPham();
		sp.createRsDvt();
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		sp.setUserId(userId);
		String id = util.antiSQLInspection(request.getParameter("id"));
		sp.setPk_seq((id == null) ? "" : id);

		String maSP = util.antiSQLInspection(request.getParameter("maSanPham"));
		sp.setMa((maSP == null) ? "" : maSP);
		String tenSP = util.antiSQLInspection(request.getParameter("tenSanPham"));
		sp.setTen((tenSP == null) ? "" : tenSP);
		String dvt = util.antiSQLInspection(request.getParameter("maDonVi"));
		sp.setDonViTinh((dvt == null) ? "" : dvt);
		String tt = util.antiSQLInspection(request.getParameter("trangthai"));
		sp.setTrangThai((tt == null) ? "" : tt);
		String msg= "";
		sp.setMsg(msg);

		if (action.equals("save")) {
			if (id.length() > 0) {
				if (!sp.update()) {
					session.setAttribute("SanPhamMoi", sp);
					String nextJSP = "/TrainingGESO/pages/Erp/SanPhamNew_kiet06.jsp";
					response.sendRedirect(nextJSP);
				} else {
					System.out.println("Update is here!");
					ISanPhamList obj = new SanPhamList();
					obj.init("");
					session.setAttribute("spList", obj);
					String nextJSP = "/TrainingGESO/pages/Erp/SanPhamList_kiet06.jsp";
					response.sendRedirect(nextJSP);
				}
			} else {
				
				if (!sp.save()) {
					session.setAttribute("SanPhamMoi", sp);
					String nextJSP = "/TrainingGESO/pages/Erp/SanPhamNew_kiet06.jsp";
					response.sendRedirect(nextJSP);
				} else {
					System.out.println("Save is here!");
					ISanPhamList obj = new SanPhamList();
					obj.init("");
					session.setAttribute("spList", obj);
					String nextJSP = "/TrainingGESO/pages/Erp/SanPhamList_kiet06.jsp";
					response.sendRedirect(nextJSP);
				}
			}
		}/* else {
			sp.createRsDvt();
			session.setAttribute("spList", sp);
			String nextJSP = "/TrainingGESO/pages/Erp/SanPhamList_kiet06.jsp";
			response.sendRedirect(nextJSP);
		}*/
	}
}
