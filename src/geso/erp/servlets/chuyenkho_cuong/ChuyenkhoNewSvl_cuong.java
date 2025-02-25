package geso.erp.servlets.chuyenkho_cuong;

import geso.dms.center.util.Utility;
import geso.erp.beans.chuyenkho_cuong.IChuyenkhoList_cuong;
import geso.erp.beans.chuyenkho_cuong.IChuyenkhoNew_cuong;
import geso.erp.beans.chuyenkho_cuong.imp.ChuyenkhoList_cuong;
import geso.erp.beans.chuyenkho_cuong.imp.ChuyenkhoNew_cuong;
import geso.erp.beans.donbanhang_cuong.IDonbanhangList_cuong;
import geso.erp.beans.donbanhang_cuong.imp.DonbanhangList_cuong;
import geso.erp.beans.donbanhang_duongnguyen.IDonHangList;
import geso.erp.beans.donbanhang_duongnguyen.Imp.DonHangList;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ChuyenkhoNewSvl_cuong
 */
@WebServlet("/ChuyenkhoNewSvl_cuong")
public class ChuyenkhoNewSvl_cuong extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChuyenkhoNewSvl_cuong() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    HttpSession session=request.getSession();
	    Utility util = new Utility();
		String queryString = request.getQueryString();
		String userId = util.getUserId(queryString);
		if (userId.length() == 0)
			userId = util.antiSQLInspection(request.getParameter("userId"));
		String action = util.getAction(queryString);
		String msg = "";
		String id=request.getParameter("update");
		IChuyenkhoNew_cuong chuyenkhonew= new ChuyenkhoNew_cuong();
		chuyenkhonew.init("");
		session.setAttribute("chuyenkhonew", chuyenkhonew);
		String nextJSP="/TrainingGESO/pages/Erp/ChuyenkhoNew_cuong.jsp";
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
		String userId = request.getParameter("userId");
		HttpSession session = request.getSession();
		String ngayghinhan = request.getParameter("ngayghinhan");
		String lydo = request.getParameter("lydo");
		String khochuyen = request.getParameter("khochuyen");
		String khonhan = request.getParameter("khonhan");
		String id = request.getParameter("id");
		String action = request.getParameter("action");
		System.out.println("khochuyen:"+khochuyen+"Kho nhan:"+khonhan+"ngay: "+ngayghinhan+"lydo"+lydo);
		IChuyenkhoNew_cuong chuyenkhonew=new ChuyenkhoNew_cuong();
		chuyenkhonew.setUserId(userId == null ? "" : userId );
		chuyenkhonew.setId(id == null ? "" : id );
		chuyenkhonew.setNgayChuyen(ngayghinhan == null ? "" : ngayghinhan );
		chuyenkhonew.setLyDo(lydo == null ? "" :lydo );
		chuyenkhonew.setKhoChuyen(khochuyen == null ? "" : khochuyen );
		chuyenkhonew.setKhoNhan(khonhan == null ? "" : khonhan );
		if (action.equals("save")) {
			if (id.length() > 0) {
				if (!chuyenkhonew.save()) {
					session.setAttribute("chuyenkhonew", chuyenkhonew);
					String nextJSP = "/TrainingGESO/pages/Erp/ChuyenkhoNew_cuong.jsp";
					response.sendRedirect(nextJSP);
				} else {
					IChuyenkhoList_cuong chuyenkholist = new ChuyenkhoList_cuong();
					chuyenkholist.init("");
					session.setAttribute("chuyenkholist", chuyenkholist);
					String nextJSP = "/TrainingGESO/pages/Erp/ChuyenkhoList_cuong.jsp";
					response.sendRedirect(nextJSP);
				}
			}
		} else {
			session.setAttribute("chuyenkhonew", chuyenkhonew);
			String nextJSP = "/TrainingGESO/pages/Erp/ChuyenkhoNew_cuong.jsp";
		}
		
	}

}
