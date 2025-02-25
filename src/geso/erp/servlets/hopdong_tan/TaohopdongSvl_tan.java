package geso.erp.servlets.hopdong_tan;

import java.io.IOException;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import geso.dms.center.util.Utility;
import geso.erp.beans.hopdong_tan.*;
import geso.erp.beans.hopdong_tan.imp.*;
import geso.erp.db.sql.dbutils;

/**
 * Servlet implementation class TaohopdongSvl_tan
 */
@WebServlet("/TaohopdongSvl_tan")
public class TaohopdongSvl_tan extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
    */
    public TaohopdongSvl_tan() {
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
	    System.out.println("-------->action is strong :  " + action);
	    String idSohopdong = util.getId(querystring);
	    
	    String msg = "";
	    
	    ITaohopdong_tan hd=new Taohopdong_tan("");
	    hd.setUserId(userId); // phai co UserId truoc khi Init
	    
	    hd.setIdSohopdong(idSohopdong);
		hd.init();
		hd.createRs();
		//Ham load gia tri can update
		if(querystring.indexOf("update")>=0){
		    hd.init();
		}else if(querystring.indexOf("display")>=0){
		    hd.setIsCapNhat("0");
			hd.init();
		}

		session.setAttribute("obj", hd);
		String nextJSP = "/TrainingGESO/pages/Erp/Taohopdong_tan.jsp";
		response.sendRedirect(nextJSP);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ITaohopdong_tan obj;
	    
		Utility util = new Utility();
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		//String userId = util.antiSQLInspection(request.getParameter("userId"));
		 
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		session.setMaxInactiveInterval(30000);
	    
	    // trường hợp tạo mới ID sẽ không có ,bằng khoảng trắng
	    String id = util.antiSQLInspection(request.getParameter("id_dh"));
	    System.out.println("ID" + id);
	     
	    if (id == null){
			id="";
			obj = new Taohopdong_tan("");
		}else{
			obj = new Taohopdong_tan(id);
		} 
	    obj.setUserId(userId);
	    
	    String hoso = util.antiSQLInspection(request.getParameter("cbHoso")); 
	    obj.setHoso_fk(hoso==null?"0":hoso);

	    String kyhopdong = util.antiSQLInspection(request.getParameter("rdHopdong"));
	    System.out.println("ky hop dong://///"+kyhopdong);
		obj.setKyhopdong(kyhopdong);
		
		String sohopdong = util.antiSQLInspection(request.getParameter("txtSohopdong")); 
	    obj.setSohopdong(sohopdong==null?"":sohopdong);
	    
	    String lydo = util.antiSQLInspection(request.getParameter("txtLydo"));
		obj.setLydo(lydo==null?"":lydo);
		
	    double giatrihopdong = 0;
	    try{
	    	giatrihopdong = Double.parseDouble(util.antiSQLInspection(request.getParameter("txtGiatrihopdong")).replaceAll(",",""));
	    }catch(Exception er){
	    	
	    }
		obj.setGiatrihopdong(giatrihopdong);
	    
	    
	    String action = request.getParameter("action");
	    if (action == null){
	    	action = "";
	    }
	    System.out.println("action: "+action);
	    if(action.equals("save")){ 
	    	if(!obj.save()){
	    	// lưu không thành công
	    		obj.createRs();
	    		obj.setIsCapNhat("1");
	    		session.setAttribute("obj", obj);
			    response.sendRedirect("/TrainingGESO/pages/Erp/Taohopdong_tan.jsp");
	    	}else{
	    		IHopdong_tan hd=new Hopdong_tan();
	    	    hd.init(""); 
	    	    // khởi tạo ra 1 session
	    	    obj.setIsCapNhat("0");
	    	    session.setAttribute("obj", hd);
	    	    String nextJSP = "/TrainingGESO/pages/Erp/Hopdong_tan.jsp";
	    		response.sendRedirect(nextJSP);
	    	}
	     }
		
	}
	
}
