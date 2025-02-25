package geso.erp.servlets.chuyenkho_cuong;

import geso.dms.center.util.Utility;
import geso.erp.beans.chuyenkho_cuong.IChuyenkhoList_cuong;
import geso.erp.beans.chuyenkho_cuong.IChuyenkhoNew_cuong;
import geso.erp.beans.chuyenkho_cuong.imp.ChuyenkhoList_cuong;
import geso.erp.beans.chuyenkho_cuong.imp.ChuyenkhoNew_cuong;
import geso.erp.beans.donbanhang_cuong.IDonbanhangNew_cuong;
import geso.erp.beans.donbanhang_cuong.imp.DonbanhangNew_cuong;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ChuyenkhoListSvl_cuong
 */
@WebServlet("/ChuyenkhoListSvl_cuong")
public class ChuyenkhoListSvl_cuong extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChuyenkhoListSvl_cuong() {
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
	    IChuyenkhoList_cuong ck=new ChuyenkhoList_cuong();
	    ck.init("");
	    session.setAttribute("chuyenkholist", ck);
	    String nextJSP="/TrainingGESO/pages/Erp/ChuyenkhoList_cuong.jsp";
	    //dữ liệu phản hồi trả về trang theo đường dẫn của nextJSP
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
	    String ma=request.getParameter("ma");
	    String khochuyen=request.getParameter("khochuyen");
	    String khonhan=request.getParameter("khonhan");
	    String trangthai=request.getParameter("trangThai");
	    System.out.println("Ma: "+ma+"khochuyen:"+khochuyen+"Kho nhan:"+khonhan+"trang thai: "+trangthai);
	    IChuyenkhoList_cuong ck = new ChuyenkhoList_cuong();
	    ck.setMa(ma);
	    ck.setKhoChuyen(khochuyen);
	    ck.setKhoNhan(khonhan);
	    ck.setTrangThai(trangthai);
	    String action=request.getParameter("action");
	    HttpSession session = request.getSession();	 
	    session.setAttribute("chuyenkholist", ck);
	    if(action.equals("timkiem")){
	    	//nếu giá trị action là timkiem, thì search theo giá trị input người dùng nhập vào để tìm
	    	String query=getSearchQuery(request, ck);
	    	//load lại danh sách theo câu truy vấn query
	    	ck.init(query);
	    	//bỏ ck vào hộp session tên "chuyenkholist" để qua trang jsp hiện lên bảng
	    	session.setAttribute("chuyenkholist", ck);
    		String nextJSP = "/TrainingGESO/pages/Erp/ChuyenkhoList_cuong.jsp";
    		response.sendRedirect(nextJSP);
	    				
	    }
	 	else if(action.equals("taomoi"))
		{
			IChuyenkhoNew_cuong chuyenkhonew= new ChuyenkhoNew_cuong();
			chuyenkhonew.CreateRsKhoChuyen();
			chuyenkhonew.CreateRsKhoNhan();
			session.setAttribute("chuyenkhonew", chuyenkhonew);
		    String nextJSP="/TrainingGESO/pages/Erp/ChuyenkhoNew_cuong.jsp";
		    response.sendRedirect(nextJSP);
		}
	 	 else{
				ck.init("");
				session.setAttribute("chuyenkholist", ck);
	    		String nextJSP = "/TrainingGESO/pages/Erp/ChuyenkhoList_cuong.jsp";
	    		response.sendRedirect(nextJSP);
	    }
	}
	private String getSearchQuery(HttpServletRequest request, IChuyenkhoList_cuong ck) {
		String query =  "";
		query="	select  ck.PK_SEQ, LYDO, nv.TEN as NGUOITAO ,NGAYTAO,nv.TEN AS NGUOISUA, NGAYSUA from CHUYENKHO ck, NHANVIEN nv where nv.PK_SEQ=ck.NGUOITAO and  nv.PK_SEQ=ck.NGUOISUA and 1=1";
				 
		if(ck.getMa().length()>0){
			query+="  and  ck.PK_SEQ LIKE '%"+ ck.getMa()+"%'";
		}
		if(ck.getKhoChuyen().length()>0){
			query+="  AND ck.KHOCHUYEN LIKE '%"+ ck.getKhoChuyen()+"%'";
		}
		if(ck.getKhoNhan().length()>0){
			query+="  AND ck.KHONHAN LIKE '%"+ ck.getKhoNhan()+"%'";
		}
		
		if(ck.getTrangThai().length()>0){
			query+=" AND ck.TRANGTHAI = '"+ck.getTrangThai()+"'";
		}
		return query;
}
}
