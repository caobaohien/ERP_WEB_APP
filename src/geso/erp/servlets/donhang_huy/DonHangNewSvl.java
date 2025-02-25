package geso.erp.servlets.donhang_huy;

import geso.dms.center.util.Utility;
import geso.erp.beans.donhang_huy.IDonHangList;
import geso.erp.beans.donhang_huy.IDonHangNew;
import geso.erp.beans.donhang_huy.imp.DonHangList;
import geso.erp.beans.donhang_huy.imp.DonHangNew;
import geso.erp.beans.donhang_huy.imp.SanPham;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DonHangNewSvl
 */
public class DonHangNewSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonHangNewSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// dùng để lấy dữ liệu
		request.setCharacterEncoding("UTF-8");
		
		// dung để điều hướng 
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    
	    HttpSession session = request.getSession();	 
	    Utility util = new Utility();
	    
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    String action = util.getAction(querystring);
	    String Id = util.getId(querystring);
	    // tao doi tuong
		IDonHangNew dhnew=new DonHangNew();
	    if(action.equals("display"))
	    {
	  
	    	dhnew.setId(Id);
	    	dhnew.init();
	    	dhnew.CreateRs();
	    	 session.setAttribute("obj", dhnew);
		   	    response.sendRedirect("/TrainingGESO/pages/Erp/DonHangDisplay_Huy.jsp");
	    }else if(action.equals("update"))
	    {
	    	dhnew.setId(Id);
	    	dhnew.init();
	    	dhnew.CreateRs();
	    	 session.setAttribute("obj", dhnew);
	   	    response.sendRedirect("/TrainingGESO/pages/Erp/DonHangNew_Huy.jsp");
	    }
	  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    Utility util = new Utility();
	    HttpSession session = request.getSession();
	    String action = request.getParameter("action");
	    if (action == null){
	    	action = "";
	    }
	    // tao doi tuong
	    IDonHangNew obj=new DonHangNew();
	    // lay cac gia tri tu the input
	    String userId = util.antiSQLInspection(request.getParameter("userId"));
	    String Id = util.antiSQLInspection(request.getParameter("id"));
	    String makh=util.antiSQLInspection(request.getParameter("makh"));
	    String ngay=util.antiSQLInspection(request.getParameter("ngay"));
	    double tongtien=Double.parseDouble(request.getParameter("tongtien").replaceAll(",", ""));
	    obj.setUserId(userId);
	    obj.setId(Id);
	    System.out.println("id = " +Id);
	    obj.setMaKH(makh);
	    obj.setNgaychungtu(ngay);
	    obj.setTongtien(tongtien);
		// xu ly action save
	    if(action.equals("save"))
	    {		IDonHangList dhlist=new DonHangList();
	    		System.out.println("dang o save");
	    	if(Id.length()==0)	// save cua tao moi
	    	{
	    		if(!obj.save())	// khong thuc hien duoc ham save
	    		{
	    			System.out.println("khong save");
	    			obj.init();
	    			obj.CreateRs();
	    			session.setAttribute("obj", obj);
	    			response.sendRedirect("/TrainingGESO/pages/Erp/DonHangNew_Huy.jsp");
	    		}
	    		else	// save thanh cong
	    		{
	    			
	    			dhlist.init("");
	    			session.setAttribute("obj", dhlist);
	    			response.sendRedirect("/TrainingGESO/pages/Erp/DonHangList_Huy.jsp");
	    		}
	    	}
	    	else	// save cua edit
	    	{
	    		System.out.println("dang o  edit");
	    	}
	    	dhlist.init("");
			session.setAttribute("obj", dhlist);
			response.sendRedirect("/TrainingGESO/pages/Erp/DonHangList_Huy.jsp");
	    }
	}
	
}
