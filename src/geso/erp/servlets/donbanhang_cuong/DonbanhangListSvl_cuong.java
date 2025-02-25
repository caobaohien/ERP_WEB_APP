package geso.erp.servlets.donbanhang_cuong;

import geso.dms.center.util.Utility;
import geso.erp.beans.donbanhang_cuong.IDonbanhangList_cuong;
import geso.erp.beans.donbanhang_cuong.IDonbanhangNew_cuong;
import geso.erp.beans.donbanhang_cuong.imp.DonbanhangList_cuong;
import geso.erp.beans.donbanhang_cuong.imp.DonbanhangNew_cuong;
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
 * Servlet implementation class DonbanhangListSvl_cuong
 */
@WebServlet("/DonbanhangListSvl_cuong")
public class DonbanhangListSvl_cuong extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonbanhangListSvl_cuong() {
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
	  
	    IDonbanhangList_cuong dh=new DonbanhangList_cuong();
	    dh.init("");
	    session.setAttribute("donbanhang", dh);
	    String nextJSP="/TrainingGESO/pages/Erp/DonbanhangList_cuong.jsp";
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
	    Utility util = new Utility();
	    String MaDH=request.getParameter("madh");
	    String khachhang=request.getParameter("khachhang");
	    String trangthai=request.getParameter("trangthai");
	    IDonbanhangList_cuong obj =new DonbanhangList_cuong();
	    obj.setMa(MaDH);
	    obj.setKhachhang(khachhang);
	    obj.setTrangThai(trangthai);
	    obj.CreateRsKhachHang();
	    String action=request.getParameter("action");
	    HttpSession session = request.getSession();	 
	    session.setAttribute("donbanhang", obj);
		 if(action.equals("timkiem")){
		    	String query=getSearchQuery(request, obj);
		    	obj.init(query);
		    	session.setAttribute("donbanhang", obj);
	    		String nextJSP = "/TrainingGESO/pages/Erp/DonbanhangList_cuong.jsp";
	    		response.sendRedirect(nextJSP);
		    				
		    }
		 	else if(action.equals("taomoi"))
			{
				IDonbanhangNew_cuong dhnew= new DonbanhangNew_cuong();
				dhnew.CreatRS();
				session.setAttribute("donbanhangNew", dhnew);
			    String nextJSP="/TrainingGESO/pages/Erp/DonbanhangNew_cuong.jsp";
			    response.sendRedirect(nextJSP);
			}
		    else{
					obj.init("");
					session.setAttribute("donbanhang", obj);
		    		String nextJSP = "/TrainingGESO/pages/Erp/DonbanhangList_cuong.jsp";
		    		response.sendRedirect(nextJSP);
		    }}
			private String getSearchQuery(HttpServletRequest request, IDonbanhangList_cuong obj) {
				Utility util=new Utility();
				
				  
				
				String query =  "";
				query="select bh.pk_seq,bh.ngaychungtu,kh.ten,bh.trangthai,bh.tongtien,bh.ngaytao,nv.TEN as TenNV from BANHANG bh"+
				" left join KHACHHANG kh on bh.khachhang_fk = kh.pk_seq"+
				" left join nhanvien nv on bh.NGUOITAO=nv.PK_SEQ where 1=1";
						 
				if(obj.getMa().length()>0){
					query+="  and  bh.pk_seq LIKE '%"+ obj.getMa()+"%'";
				}
				if(obj.getkhachhang().length()>0){
					query+="  AND bh.khachhang_fk LIKE '%"+ obj.getkhachhang()+"%'";
				}
				 
				if(obj.getTrangThai().length()>0){
					query+=" AND bh.trangthai = '"+obj.getTrangThai()+"'";
				}
				return query;
	}
	
}
