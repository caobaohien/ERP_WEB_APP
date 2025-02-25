package geso.erp.servlets.donbanhang_trang;

import geso.dms.center.util.Utility;
import geso.erp.beans.donbanhang_trang.IDonBanHangList_Trang;
import geso.erp.beans.donbanhang_trang.IDonBanHang_Trang;
import geso.erp.beans.donbanhang_trang.imp.DonBanHangList_Trang;
import geso.erp.beans.donbanhang_trang.imp.DonBanHang_Trang;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DonBanHang_TrangSvl extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonBanHang_TrangSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//định dạng 
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");//kieu tra ve la 1 trang html
		
		//tạo session để chứa thông tin của người sử dụng
		HttpSession httpSession = request.getSession();
		Utility utility = new Utility();
		String queryString = request.getQueryString(); //lấy chuỗi truyền
		
		//Lay thong tin cua mot user trong session
		String userId = utility.getUserId(queryString);
	    if (userId.length()==0) {
	    	userId = utility.antiSQLInspection(request.getParameter("userId")); 
		
	    }

		//khởi tạo đối tượng
		IDonBanHangList_Trang obj = new DonBanHangList_Trang();
		String action = utility.getAction(queryString); // lây tên của action
		action = (action == null) ? "" : action;
		    //lay ma
		String maDonHang = utility.getId(queryString);
		    
		IDonBanHang_Trang objnew = new DonBanHang_Trang();
	    objnew.setMaDonHang(maDonHang);		   
		if(action.equals("chot")){ //truong hop la chot don hang   	
		   objnew.chotDonHang();		    	
		 }		    		
		 if(action.equals("delete")){
		   objnew.deteleDonHang();
		 }    
		obj.init("");
		
		//gán đối tượng qua httpsession để chuyển lên jsp
		httpSession.setAttribute("obj", obj);
		String nextJSP = "/TrainingGESO/pages/Erp/DonBanHang_Trang.jsp";
		response.sendRedirect(nextJSP);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8"); //dinh dang UNICODE
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		IDonBanHangList_Trang obj = new DonBanHangList_Trang();
		String action = request.getParameter("action");
		
		String maDonHang = request.getParameter("maDonHang"); //tim kiem
		String trangThai = request.getParameter("trangThai");//tim kiem
		String khachHang = request.getParameter("khachHang");//tim kiem
		
		
		action = (action == null) ? "" : action;
		maDonHang = (maDonHang == null) ? "" : maDonHang;
		trangThai = (trangThai == null) ? "" : trangThai;
		khachHang = (khachHang == null) ? "" : khachHang;
		
		
		//để giữ giá trị lại trên input khi tìm kiếm
		HttpSession session = request.getSession();
		obj.setMaDonHang(maDonHang);
		obj.setTrangThai(trangThai);
		obj.setKhachHang(khachHang);
				
		System.out.print("KH:"+obj.getKhachHang());
		System.out.print("tt"+obj.getTrangThai());
		if (action.equals("timkiem")) {
			obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
			
			String searchQuery = this.getSearchQuery(request, obj);
			System.out.println(searchQuery);
			obj.init(searchQuery);
			
	    	session.setAttribute("obj", obj);	    	
	    	response.sendRedirect("/TrainingGESO/pages/Erp/DonBanHang_Trang.jsp");
		}else if (action.equals("taomoi")) { //khi nhấn vào dấu cộng bên trên
		    IDonBanHang_Trang objnew = new DonBanHang_Trang();
		    objnew.init("");
		    objnew.getDataKhachHang();
		    session.setAttribute("action", "save");
		    session.setAttribute("objnew", objnew);
		    String nextJSP = "/TrainingGESO/pages/Erp/DonBanHangUpdate_Trang.jsp";
			response.sendRedirect(nextJSP);
		}
		else{
			obj.init("");
			session.setAttribute("objnew", obj);
			String nextJSP = "/TrainingGESO/pages/Erp/DonBanHang_Trang.jsp";
	    	response.sendRedirect(nextJSP);
		}
	}
	public String getSearchQuery(HttpServletRequest request, IDonBanHangList_Trang obj) {
		
		String query = "select bh.PK_SEQ as MADONHANG, bh.NGAYCHUNGTU AS NGAYCHUNGTU, kh.PK_SEQ AS MAKHACHHANG, kh.TEN as KHACHHANG, " +
		" bh.TRANGTHAI, nv.TEN as NGUOITAO, bh.NGAYTAO, bh.TONGTIEN " +
		" from BANHANG as bh inner join KHACHHANG kh on kh.PK_SEQ = bh.KHACHHANG_FK" +
		" left join NHANVIEN nv on nv.PK_SEQ = bh.NGUOITAO " +
		" left join NHANVIEN nv1 on nv1.PK_SEQ = bh.NGUOISUA  where 1=1";
		
		if (obj.getMaDonHang().length() > 0) {
			//query += " AND  BH.PK_SEQ LIKE '%" + obj.getMaDonHang() + "%'";
			query += " AND  bh.PK_SEQ LIKE '%" + obj.getMaDonHang() + "%'";
		}
		if (obj.getTrangThai().length() > 0) {
			//query += " AND  BH.TRANGTHAI LIKE '%" + obj.getTrangThai() +"%'";
			query += " AND  dbo.ftBoDau(bh.TRANGTHAI) LIKE dbo.ftBoDau('%" + obj.getTrangThai() +"%')";
		}
		if (obj.getKhachHang().length() > 0) {
			
			//thêm hàm bỏ dấu vào để so sánh có dấu vào không dấu.
			query += " AND  kh.PK_SEQ LIKE '%" + obj.getKhachHang() +"%'";
		}
		return query;
	}
}
