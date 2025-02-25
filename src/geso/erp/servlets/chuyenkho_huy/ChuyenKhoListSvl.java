package geso.erp.servlets.chuyenkho_huy;

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
import geso.erp.beans.chuyenkho_huy.IChuyenKhoUpdate;
import geso.erp.beans.chuyenkho_huy.IListChuyenKho;
import geso.erp.beans.chuyenkho_huy.imp.ChuyenKhoUpdate;
import geso.erp.beans.chuyenkho_huy.imp.ListChuyenKho;
import geso.erp.beans.donhang_huy.IDonHangList;
import geso.erp.beans.donhang_huy.IDonHangNew;
import geso.erp.beans.donhang_huy.imp.DonHangList;
import geso.erp.beans.donhang_huy.imp.DonHangNew;
import geso.erp.beans.muahang_huy.IMuaHang;
import geso.erp.beans.muahang_huy.imp.MuaHang;
import geso.erp.db.sql.dbutils;


/**
 * Servlet implementation class SanphamListSvl
 */
public class ChuyenKhoListSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChuyenKhoListSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		    System.out.println("day la action "+ action);
		    IListChuyenKho listkho=new ListChuyenKho();
		    String Id = util.getId(querystring);
		    System.out.println("day la id "+ Id);
		    String msg = "";
		    
	    // xu ly cac action
		    listkho.init("");
		    listkho.CreateRs();
	    session.setAttribute("obj",listkho);
	    String nextJSP="/TrainingGESO/pages/Erp/ChuyenKhoList_Huy.jsp";
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
	    String action = request.getParameter("action");
	    if (action == null){
	    	action = "";
	    }
	    Utility util = new Utility();
	    
	    IListChuyenKho obj=new ListChuyenKho();
	    // lay cac input tu trang ChuyenKhoList_Huy
	    String userId = util.antiSQLInspection(request.getParameter("userId"));
	    String id = util.antiSQLInspection(request.getParameter("id"));
	    String makho=util.antiSQLInspection(request.getParameter("makho"));
	    String khochuyen=util.antiSQLInspection(request.getParameter("khochuyen"));
	    String khonhan=util.antiSQLInspection(request.getParameter("khonhan"));
	    String trangthai=util.antiSQLInspection(request.getParameter("trangthai"));
		HttpSession session = request.getSession();
	 // set cac thuoc tinh vua lay vao ChuyenKhoList
	    obj.setUserId(userId);
	    obj.setId(id==null?"":id);
	    obj.setPK_SEQ(makho);
	    obj.setKhochuyen(khochuyen);
	    obj.setKhonhan(khonhan);
	    obj.setTrangthai(trangthai);
		
		    if(action.equals("timkiem"))
		    {
		    	obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
		    	String search=getSearchQuery(request, obj);
		    	obj.init(search);
		    	obj.CreateRs();
		    	session.setAttribute("obj",obj);
		    	 String nextJSP="/TrainingGESO/pages/Erp/ChuyenKhoList_Huy.jsp";
		  	    response.sendRedirect(nextJSP);
		    }
		    else if(action.equals("clear"))
		    {
		    	obj.init("");
		    	obj.CreateRs();
		    	session.setAttribute("obj",obj);
		    	 String nextJSP="/TrainingGESO/pages/Erp/ChuyenKhoList_Huy.jsp";
		  	    response.sendRedirect(nextJSP);
		    }
		    else if(action.equals("new"))
		    {
		    	System.out.println("dang o new");
		    	IChuyenKhoUpdate chuyenkhonew=new ChuyenKhoUpdate();
		    	chuyenkhonew.CreateRs();
		    	session.setAttribute("obj", chuyenkhonew);
		    	 String nextJSP="/TrainingGESO/pages/Erp/ChuyenKhoUpdate_Huy.jsp";
			  	  response.sendRedirect(nextJSP);
		    }
		}
	
	
	
	
		// ham xu ly tim kiem\
		private String getSearchQuery(HttpServletRequest request,IListChuyenKho obj)
		{
			Utility util=new Utility();
			String query;
			query="select ISNULL(ck.PK_SEQ,'') as PK_SEQ,ck.LYDO,ISNULL(ck.trangthai,'') as trangthai,nv.TEN,ck.NGAYTAO,nv.TEN,ck.NGAYSUA "
					+ "from CHUYENKHO ck left join NHANVIEN nv on ck.NGUOITAO=nv.PK_SEQ where 1=1";
			if(obj.getPK_SEQ().length()>0){
				query+=" AND ck.PK_SEQ LIKE '%"+ obj.getPK_SEQ()+"%'";
			}
			if(obj.getKhochuyen().length()>0)
			{
				query+=" and ck.KHOCHUYEN Like '%"+obj.getKhochuyen()+"%'";
			}
			if(obj.getKhonhan().length()>0)
			{
				query+=" and ck.KHONHAN Like '%"+obj.getKhonhan()+"%'";
			}
			if(obj.getTrangthai().length()>0){
				query+=" AND ck.trangthai LIKE '%"+obj.getTrangthai()+"%'";
			}
				return query;
		}
		
	// ham xu ly tim kiem\
	}
	
	

