package geso.erp.servlets.donbanhang_tho;

import geso.dms.center.util.Utility;
import geso.erp.beans.donbanhang_tho.IDonbanhangListTho;
import geso.erp.beans.donbanhang_tho.IDonbanhangTho;
import geso.erp.beans.donbanhang_tho.imp.DonbanhangListTho;
import geso.erp.beans.donbanhang_tho.imp.DonbanhangTho;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DonbanhangListThoSvl
 */
@WebServlet("/DonbanhangListThoSvl")
public class DonbanhangListThoSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonbanhangListThoSvl() {
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
	    HttpSession session=request.getSession();
	    Utility util= new Utility();
	    String query=request.getQueryString();
	    String userId=util.getUserId(query);
	    if(userId.length()==0)
	    userId=util.antiSQLInspection(request.getParameter("userId"));
	    IDonbanhangListTho dh=new DonbanhangListTho();
	    dh.init("");
	    session.setAttribute("donhang", dh);
	    String nextJSP="/TrainingGESO/pages/Erp/DonbanhangListTho.jsp";
	    response.sendRedirect(nextJSP);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String madh=request.getParameter("madh");
		String khachhang=request.getParameter("khachhang");
		String trangthai=request.getParameter("trangthai");
		String action=request.getParameter("action");
		System.out.print(madh+""+khachhang+""+trangthai+""+action);
		HttpSession session=request.getSession();
		IDonbanhangListTho obj= new DonbanhangListTho();
		obj.setmaDH(madh);
		obj.settenKH(khachhang);
		obj.setTrangthai(trangthai);
		if(action.equals("timkiem"))
		{
			String query=getquerySearch(request, obj);
			obj.init(query);
			session.setAttribute("donhang", obj);
		    String nextJSP="/TrainingGESO/pages/Erp/DonbanhangListTho.jsp";
		    response.sendRedirect(nextJSP);
		}
		else if(action.endsWith("taomoi"))
		{
			IDonbanhangTho dhnew= new DonbanhangTho();
			dhnew.CreateRs();
			session.setAttribute("donhangnew", dhnew);
		    String nextJSP="/TrainingGESO/pages/Erp/DonbanhangTho.jsp";
		    response.sendRedirect(nextJSP);
		}
		else
		{
			obj.init("");
			session.setAttribute("donhang", obj);
		    String nextJSP="/TrainingGESO/pages/Erp/DonbanhangListTho.jsp";
		    response.sendRedirect(nextJSP);
		}
		
	}
	private String getquerySearch(HttpServletRequest request, IDonbanhangListTho obj)
	{
		String query="SELECT BH.PK_SEQ,BH.NGAYCHUNGTU,KH.TEN,BH.TRANGTHAI,BH.TONGTIEN,BH.NGAYTAO,NV.TEN as NGUOITAO from BANHANG BH,NHANVIEN NV,KHACHHANG KH WHERE kh.PK_SEQ=bh.KHACHHANG_FK AND NV.PK_SEQ=bh.NGUOITAO ";
		if(obj.getmaDH().length()>0)
			query=query+"AND BH.PK_SEQ LIKE '%"+obj.getmaDH()+"%'";
		if(obj.gettenKH().length()>0)
			query=query+"AND KH.PK_SEQ LIKE '%"+obj.gettenKH()+"%'";
		if(obj.getTrangthai().length()>0)
			query=query+"AND BH.TRANGTHAI="+obj.getTrangthai();
		return query;
	}

}
