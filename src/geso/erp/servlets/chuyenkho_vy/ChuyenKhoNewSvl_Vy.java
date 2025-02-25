package geso.erp.servlets.chuyenkho_vy;

import geso.dms.center.util.Utility;
import geso.erp.beans.chuyenkho_vy.IPhieuChuyenKhoList_Vy;
import geso.erp.beans.chuyenkho_vy.IPhieuChuyenKho_Vy;
import geso.erp.beans.chuyenkho_vy.imp.PhieuChuyenKhoList_Vy;
import geso.erp.beans.chuyenkho_vy.imp.PhieuChuyenKho_Vy;
import geso.erp.beans.kho_vy.IKho_Vy;
import geso.erp.beans.kho_vy.imp.Kho_Vy;
import geso.erp.beans.sanpham_vy.ISanPham_Vy;
import geso.erp.beans.sanpham_vy.imp.SanPham_Vy;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ChuyenKhoNewSvl_Vy
 */
@WebServlet("/ChuyenKhoNewSvl_Vy")
public class ChuyenKhoNewSvl_Vy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChuyenKhoNewSvl_Vy() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ChuyenKhoNewSvl_Vy doGet");
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    HttpSession session = request.getSession();
	    
	    Utility util = new Utility();
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    if (userId.length() == 0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    String maPhieuChuyenKho = util.getId(querystring);
	    
	    IPhieuChuyenKho_Vy phieuChuyenKhoCu = new PhieuChuyenKho_Vy(maPhieuChuyenKho);
	    
	    
	    String action = util.getAction(querystring);
	    PrintWriter out = response.getWriter();
	    phieuChuyenKhoCu.init();
	    
	    if (action.equals("update")){
	    	//System.out.println("Update Phieu chuyen kho so: "+ phieuChuyenKhoCu.getPK_SEQ());
	    	//Co the bo phieuChuyenKhoCu.getKhoChuyen().loadThongTinKHO_SANPHAM();
	    	phieuChuyenKhoCu.setNguoiSua(userId);
	    	phieuChuyenKhoCu.setCapNhat(true);
	    	//session.setAttribute("phieuChuyenKhoCu", phieuChuyenKhoCu);
	    } else if (action.equals("display")) {
	    	System.out.println("Hien thi Phieu chuyen kho so: "+ phieuChuyenKhoCu.getPK_SEQ());
	    	//Co the bo phieuChuyenKhoCu.getKhoChuyen().loadThongTinKHO_SANPHAM();
	    	phieuChuyenKhoCu.setHienThi(true);
	    }
	    
    	session.setAttribute("phieuChuyenKho", phieuChuyenKhoCu);
		String nextJSP = "/TrainingGESO/pages/Erp/ChuyenKhoNew_Vy.jsp";
		response.sendRedirect(nextJSP);
	   
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Di vao doPost");
		request.setCharacterEncoding("UTF-8");	
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
	    String action = request.getParameter("action");
	    action = (action == null) ? "" : action;
	    
	    System.out.println("Action post: "+action);
	    HttpSession session = request.getSession();
	    
	    Utility util = new Utility();
	    
	    String userId = util.antiSQLInspection(request.getParameter("userId"));
	    //Lay cac thong so duoc gui tu file jsp
	    IPhieuChuyenKho_Vy phieuChuyenKho_Vy = getParameterToPhieuChuyenKho(request, userId, util);
	    if (action.equals("save")) {
	    	//IPhieuChuyenKho_Vy phieuChuyenKhoCu = (IPhieuChuyenKho_Vy) session.getAttribute("phieuChuyenKhoCu");
	    	if (phieuChuyenKho_Vy.save()){
		    	System.out.println("Save thành công");
		    	IPhieuChuyenKhoList_Vy phieuChuyenKhoList = new PhieuChuyenKhoList_Vy();
		    	phieuChuyenKhoList.initListPhieuChuyenKho();
	    		session.setAttribute("phieuChuyenKhoList", phieuChuyenKhoList);
		    	String nextJSP = "/TrainingGESO/pages/Erp/ChuyenKhoList_Vy.jsp";
				response.sendRedirect(nextJSP);
	    		
	    	} else {
	    		System.out.println("Save thất bại");
	    		session.setAttribute("phieuChuyenKho", phieuChuyenKho_Vy);
	    		String nextJSP = "/TrainingGESO/pages/Erp/ChuyenKhoNew_Vy.jsp";
	    		response.sendRedirect(nextJSP);
	    	}
	    
	    } else if (action.equals("submit")){
	    	System.out.println("Nhan cai khac cu the la " +action);
	    	System.out.println(phieuChuyenKho_Vy.getPK_SEQ());
	    	phieuChuyenKho_Vy.getDanhSachSanPham().clear();
	    	session.setAttribute("phieuChuyenKho", phieuChuyenKho_Vy);
	    	String nextJSP = "/TrainingGESO/pages/Erp/ChuyenKhoNew_Vy.jsp";
    		response.sendRedirect(nextJSP);
	    } 
	}
	
	private IPhieuChuyenKho_Vy getParameterToPhieuChuyenKho(HttpServletRequest request, String userId, Utility util) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
	    //String maPhieuChuyenKho = util.antiSQLInspection(request.getParameter("maPhieuChuyenKho"));
		String maPhieuChuyenKho = request.getParameter("maPhieuChuyenKho");
		String ngayTao = request.getParameter("ngayTao");
	    String maKhoNhan = request.getParameter("maKhoNhan");
	    String maKhoChuyen = request.getParameter("maKhoChuyen");
	    String lyDo = request.getParameter("lyDo");
	    IKho_Vy khoChuyen = new Kho_Vy(maKhoChuyen);
	    IPhieuChuyenKho_Vy phieuChuyenKho_Vy = new PhieuChuyenKho_Vy();
	    phieuChuyenKho_Vy.setPK_SEQ(maPhieuChuyenKho == null ? "" : maPhieuChuyenKho);
	    phieuChuyenKho_Vy.setNgayTao(ngayTao);
	    phieuChuyenKho_Vy.setKhoNhan(new Kho_Vy(maKhoNhan));
	    phieuChuyenKho_Vy.setKhoChuyen(khoChuyen);
	    phieuChuyenKho_Vy.setLyDo(lyDo);
	    phieuChuyenKho_Vy.setUserId(userId);
	    
	    String[] PK_SEQ = request.getParameterValues("PK_SEQ");
		String[] maSanPham = request.getParameterValues("maSanPham");
		String[] tenSanPham = request.getParameterValues("tenSanPham");
		/*String[] soLuongTon = request.getParameterValues("soLuongTon");*/
		String[] soLuongChuyen = request.getParameterValues("soLuongChuyen");
		String[] donViTinh = request.getParameterValues("donViTinh");
		
		List<ISanPham_Vy> danhSachSanPham = new ArrayList<ISanPham_Vy>();
		/*int soLuongChuyen2[] = new int[soLuongTon.length];*/
		//Neu mang ma asn pham khac rong thi vao vong lap
		//Co can phai kiem tra dieu kien nay khong?
		if (maSanPham != null) {
			for (int i = 0; i < maSanPham.length; i++) {
				//Neu mang ma asn pham co ki tu thi moi lay gia tri
				if (tenSanPham[i].trim().length() > 0
						|| maSanPham[i].trim().length() > 0) {
					int /*slt = 0,*/ slc = 0;
					try {
						/*slt = Integer.parseInt(soLuongTon[i]);*/
						slc = Integer.parseInt(soLuongChuyen[i]);
					} catch (NumberFormatException e){
						/*slt = 0;*/ slc = 0;
						e.printStackTrace();
					}
					/*ISanPham_Vy sanPham_Vy = new SanPham_Vy(PK_SEQ[i], 
												maSanPham[i],
												donViTinh[i], 
												tenSanPham[i],
												slt);*/
					ISanPham_Vy sanPham_Vy = new SanPham_Vy(PK_SEQ[i], 
															maSanPham[i],
															donViTinh[i], 
															tenSanPham[i],
															slc);
					/*soLuongChuyen2[i] = slc;*/
					danhSachSanPham.add(sanPham_Vy);
				}
			}
		}
		phieuChuyenKho_Vy.setDanhSachSanPham(danhSachSanPham);
		/*phieuChuyenKho_Vy.setSoLuongChuyen(soLuongChuyen2);*/
	    
		return phieuChuyenKho_Vy;
		
	}
}
