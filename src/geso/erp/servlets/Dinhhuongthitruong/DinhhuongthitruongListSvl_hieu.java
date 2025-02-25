package geso.erp.servlets.Dinhhuongthitruong;


import java.io.IOException;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import geso.dms.center.util.Utility;
import geso.erp.beans.Dinhhuongthitruong_hieu.IDinhhuongthitruongList_hieu;
import geso.erp.beans.Dinhhuongthitruong_hieu.IDinhhuongthitruongUpdate_hieu;
import geso.erp.beans.Dinhhuongthitruong_hieu.imp.DinhhuongthitruongList_hieu;
import geso.erp.beans.Dinhhuongthitruong_hieu.imp.DinhhuongthitruongUpdate_hieu;





/**
 * Servlet implementation class SanphamListSvl
 */
@WebServlet("/DinhhuongthitruongListSvl_hieu")
public class DinhhuongthitruongListSvl_hieu extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DinhhuongthitruongListSvl_hieu() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		// dung để điều hướng
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		HttpSession session = request.getSession();
		Utility util = new Utility();

		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);

		if (userId.length() == 0)
			userId = util.antiSQLInspection(request.getParameter("userId"));


		String msg = "";
		 
		IDinhhuongthitruongList_hieu ck = new DinhhuongthitruongList_hieu();
		
		// khởi tạo ra 1 session

		// cách lấy action (hành động truyền vào, và id truyền vào)
		
		System.out.println("querystring : "+querystring);
		String action = util.getAction(querystring);
		String action_getbangparameter= request.getParameter("update");
		
		System.out.println("action_getbangparameter : "+action_getbangparameter);
		String ckId = util.getId(querystring);
		
		 if (action.equals("delete"))
		    {	
			 	ck.setId(ckId);
				 ck.Delete_dh();
				 ck.init("");
		     
				
		    }
		    else if(action.equals("chot"))
			    	{
		    	ck.setId(ckId);
				 ck.Chot_dh();
				 ck.init("");
			    		 
			    		
			    }
		 	ck.init("");
			session.setAttribute("obj",ck);
			String nextJSP = "/TrainingGESO/pages/Erp/DinhhuongthitruongList_hieu.jsp";
			response.sendRedirect(nextJSP);
		    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Utility util = new Utility();
		IDinhhuongthitruongList_hieu obj=new DinhhuongthitruongList_hieu();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		//
		String action = request.getParameter("action");
		if (action == null) {
			action = "";
		}
		
		System.out.println("action gi day : " + action);

		String ma = util.antiSQLInspection(request.getParameter("ma"));
		if (ma == null) {
			ma = "";
		}
		System.out.println("ma gi day : " + ma);
		int thang=0;
		
		 String temp = util.antiSQLInspection( request.getParameter("thang"));
		 
		 try{
			 thang=Integer.parseInt(temp);
			 
		 }catch(Exception er){}
		int nam=0;
			
		 String temp1 = util.antiSQLInspection( request.getParameter("nam"));
		 
		 try{
			 nam=Integer.parseInt(temp1);
			 
		 }catch(Exception er){}
		 
		 int thangdinhhuong=0;
			
		 String temp2 = util.antiSQLInspection( request.getParameter("thangdinhhuong"));
		 
		 try{
			 thangdinhhuong=Integer.parseInt(temp2);
			 
		 }catch(Exception er){}
		
		 
		 int quydinhhuong=0;
			
		 String temp3 = util.antiSQLInspection( request.getParameter("quydinhhuong"));
		 
		 try{
			 quydinhhuong=Integer.parseInt(temp3);
			 
		 }catch(Exception er){}
		
		String trangthai = request.getParameter("trangthai");
		if (trangthai == null) {
			trangthai = "";
		}
		
		
		
		
		
		HttpSession session = request.getSession();
		
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		obj.setId(ma);
		obj.setThang(thang);
		obj.setNam(nam);
		obj.setThangnhandinh(thangdinhhuong);
		obj.setQuynhandinh(quydinhhuong);

		obj.setTrangthai(trangthai);

		if (action.equals("timkiem")) {

			obj.setNxtApprSplitting(Integer.parseInt(request
					.getParameter("nxtApprSplitting")));

			String search = getSearchQuery(request, obj);

			obj.init(search);

			session.setAttribute("obj", obj);
			response.sendRedirect("/TrainingGESO/pages/Erp/DinhhuongthitruongList_hieu.jsp");

		}else if (action.equals("taomoi")) {

			IDinhhuongthitruongUpdate_hieu cku = new DinhhuongthitruongUpdate_hieu();
			session.setAttribute("obj", cku);
			session.setAttribute("action", "save");
			cku.CreateRs();
			String nextJSP = "/TrainingGESO/pages/Erp/DinhhuongthitruongUpdate_hieu.jsp";
			response.sendRedirect(nextJSP);
			
		}
		else {
			obj = new DinhhuongthitruongList_hieu();
			obj.setUserId(userId);

			obj.setNxtApprSplitting(Integer.parseInt(request
					.getParameter("nxtApprSplitting")));

			String search = getSearchQuery(request, obj);

			obj.init(search);

			session.setAttribute("obj", obj);
			response.sendRedirect("/TrainingGESO/pages/Erp/ChuyenkhoList_hieu.jsp");
		}

	}

	private String getSearchQuery(HttpServletRequest request, IDinhhuongthitruongList_hieu obj) {
		Utility util = new Utility();

		String query = "";
		query =" select khdh.pk_seq as ma,thang,nam,TINHTRANGTHITRUONG,THANG_NHANDINH,QUY_NHANDINH,KEHOACHHANHDONG,NGAYTAO,nv.TEN as nguoitao,khdh.TRANGTHAI as trangthai "+
			   " from KEHOACHDINHHUONG khdh left join NHANVIEN nv on khdh.NGUOITAO=nv.PK_SEQ where "+
				"1=1";
		if (obj.getId().length() > 0) {
			query += " AND  khdh.pk_seq LIKE '%" + obj.getId() + "%'";
		}
		if (obj.getThang() > 0) {
			query += " AND thang = " + obj.getThang() ;
		}
		if (obj.getNam() > 0) {
			query += " AND nam = " + obj.getNam() ;
		}
		if (obj.getThangnhandinh() > 0) {
			query += " AND THANG_NHANDINH = " + obj.getThangnhandinh() ;
		}
		if (obj.getQuynhandinh() > 0) {
			query += " AND QUY_NHANDINH = " + obj.getQuynhandinh();
		}

		if (obj.getTrangthai().length() > 0) {
			query += " AND khdh.TRANGTHAI LIKE '" + obj.getTrangthai() + "'";
		}
		return query;

	}


}
