package geso.training.servlets.donhang;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import geso.dms.center.util.Utility;
import geso.erp.db.sql.dbutils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import geso.training.bean.donhang.IDonHang;
import geso.training.bean.donhang.imp.DonHang;
import geso.training.bean.sanpham.ISanPham;
import geso.training.bean.sanpham.imp.SanPham;
import geso.training.bean.donhang.IDonHangList;
import geso.training.bean.donhang.imp.DonHangList;
/**
 * Servlet implementation class DonhangUpdatesvl
 */
@WebServlet("/DonhangUpdatesvl")
public class DonhangUpdatesvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	dbutils db;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonhangUpdatesvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		//khoi tao session
		HttpSession session= request.getSession();
		Utility util = new Utility();
		
		String querystring =request.getQueryString();
		String madh=util.getId(querystring);
		String userId=util.getUserId(querystring);
		String action=util.getAction(querystring);
		System.out.println("hien thi: " +querystring);
		
		IDonHang ob=new DonHang();
		ob.setUserId(userId);
		ob.setMadh(madh);
		
		if (querystring.indexOf("update")>=0){
			String id = request.getParameter("update");
			session.setAttribute("action", "update");
		}
		if(action.equals("display")){
			ob.init("");
			//ob.initsp();
			session.setAttribute("donhangsua", ob);
			String nextjsp="/TrainingGESO/pages/Erp/donhangsua.jsp";
			response.sendRedirect(nextjsp);
		}else if(action.equals("delete")){
			if(!ob.Delete()){
				System.out.println("xoa khong thanh cong");
				//truyen gia trị den trang view
			}else{
				String userid = (String) session.getAttribute("userId");
				String userTen = (String) session.getAttribute("userTen");
				IDonHangList obj=new DonHangList();
				obj.setUserId(userid);
				obj.setUserTen(userTen);
				obj.init("");
				//khoi tao 1 session va send toi trang jsp
				session.setAttribute("ob", obj);
				String nextjsp="/TrainingGESO/pages/Erp/donhanglist.jsp";
				response.sendRedirect(nextjsp);
			}
		}else if(action.equals("update")){
			ISanPham spp = new SanPham();
			spp.setMadh(madh);
			spp.setUserId(userId);
			spp.init();
			//sanpham.initsp();
			//sanpham.setListSp(sanpham.getListSp());
			/*String[] idsp=request.getParameterValues("spId");
			String[] masp=request.getParameterValues("masp");
			String[] tensp=request.getParameterValues("tensp1");
			String[] soluong=request.getParameterValues("soluong");
			String[] donvitinh=request.getParameterValues("donvitinh1");
			String[] dongia=request.getParameterValues("dongia1");
			String[] chietkhau=request.getParameterValues("chietkhau1");
			String[] thanhtien=request.getParameterValues("thanhtien1");
			
			//add vao danh sach san pham
			List<ISanPham> listSp=new ArrayList<ISanPham>();
			if(masp!=null){
				for(int i=0;i<masp.length;i++){
					if(tensp[i].trim().length()>0 && masp[i].trim().length()>0 && soluong[i]!="" && thanhtien[i]!=""){
						ISanPham sp = new SanPham();
						sp.setPK_SEQ(idsp[i]);
						sp.setMaSP(masp[i]);
						sp.setTenSP(tensp[i]);
						sp.setSoLuong(Integer.parseInt(soluong[i]));
						sp.setDVT(donvitinh[i]);
						sp.setDonGia(Double.parseDouble((dongia[i]).replaceAll(",", "")));
						sp.setChietkhau(Double.parseDouble((chietkhau[i]).replaceAll(",", "")));
						sp.setThanhTien(Double.parseDouble((thanhtien[i]).replaceAll(",", "")));
						listSp.add(sp);
					}
				}
			}
			sanpham.setListSp(listSp);*/
			session.setAttribute("donhangupdate", spp);
			response.sendRedirect("/TrainingGESO/pages/Erp/donhangupdate.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session=request.getSession();
		//tao don hang moi
		IDonHang dh=new DonHang();
		String action = request.getParameter("action");
		action = (action==null)? "":action ;
		Utility util=new Utility();
		//lay cac thuoc tinh gan cho doi tuong dh
		String userId=util.antiSQLInspection(request.getParameter("userId"));
		dh.setUserId(userId);
		String userTen=util.antiSQLInspection(request.getParameter("userTen"));
		dh.setUserTen(userTen);
		
		String Madh=request.getParameter("madonhang");
		dh.setMadh(Madh);
		
		String Makh=util.antiSQLInspection(request.getParameter("smartId"));
		dh.setMakh(Makh);
		
		String khten=util.antiSQLInspection(request.getParameter("khTen"));
		dh.setTenkh(khten);
		
		String trangthai=util.antiSQLInspection(request.getParameter("trangthai"));
		dh.setTrangThai(trangthai == null ? "0" : trangthai);
		
		String ngaydh=util.antiSQLInspection(request.getParameter("ngaydonhang"));
		dh.setNgaydh(ngaydh == null ? "" : ngaydh);
		
		String Vat=util.antiSQLInspection(request.getParameter("VAT"));
		dh.setVat(Double.parseDouble(Vat.replace(",", "")));
		
		String tongtientruocvat=util.antiSQLInspection(request.getParameter("sotienchuaVAT"));
		dh.setTongtientruocVat(Double.parseDouble(tongtientruocvat.replace(",", "")));
		
		String tongtien=util.antiSQLInspection(request.getParameter("sotiencoVAT"));
		dh.setTongtiensauVat(Double.parseDouble(tongtien.replace(",", "")));
		
		String msg="";
		dh.setMsg(msg);
		
		//lay chi tiet don hang (la cac san pham cua don hang)
		String[] idsp=request.getParameterValues("spId");
		String[] masp=request.getParameterValues("masp");
		String[] tensp=request.getParameterValues("tensp1");
		String[] soluong=request.getParameterValues("soluong");
		String[] donvitinh=request.getParameterValues("donvitinh1");
		String[] dongia=request.getParameterValues("dongia1");
		String[] chietkhau=request.getParameterValues("chietkhau1");
		String[] thanhtien=request.getParameterValues("thanhtien1");
		
		//add vao danh sach san pham
		List<ISanPham> listSp=new ArrayList<ISanPham>();
		if(masp!=null){
			for(int i=0;i<masp.length;i++){
				if(tensp[i].trim().length()>0 && masp[i].trim().length()>0 && soluong[i]!="" && thanhtien[i]!=""){
					ISanPham sp = new SanPham();
					sp.setPK_SEQ(idsp[i]);
					sp.setMaSP(masp[i]);
					sp.setTenSP(tensp[i]);
					sp.setSoLuong(Integer.parseInt(soluong[i]));
					sp.setDVT(donvitinh[i]);
					sp.setDonGia(Double.parseDouble((dongia[i]).replaceAll(",", "")));
					sp.setChietkhau(Double.parseDouble((chietkhau[i]).replaceAll(",", "")));
					sp.setThanhTien(Double.parseDouble((thanhtien[i]).replaceAll(",", "")));
					listSp.add(sp);
				}
			}
		}
		dh.setListSp(listSp);
		if(action.equals("hien")){
			//ISanPham spp = new SanPham();
			//spp.setMadh(Madh);
			//sanpham.setMakh(Makh);
			//sanpham.setTenkh(khten);
			//String Madh1=request.getParameter("madonhang");
			//sanpham.setMadh(Madh1);
			//spp.init();
			//sanpham.initsp();
			/*String Madh1=request.getParameter("madonhang");
			spp.setMadh(Madh1);
			
			String Makh1=util.antiSQLInspection(request.getParameter("smartId"));
			spp.setMakh(Makh1);
			
			String khten1=util.antiSQLInspection(request.getParameter("khTen"));
			spp.setTenkh(khten1);
			
			String trangthai1=util.antiSQLInspection(request.getParameter("trangthai"));
			spp.setTrangThai(trangthai1 == null ? "0" : trangthai1);
			
			String ngaydh1=util.antiSQLInspection(request.getParameter("ngaydonhang"));
			spp.setNgaydh(ngaydh1 == null ? "" : ngaydh1);
			
			String Vat1=util.antiSQLInspection(request.getParameter("VAT"));
			spp.setVat(Double.parseDouble(Vat1.replace(",", "")));
			
			String tongtientruocvat1=util.antiSQLInspection(request.getParameter("sotienchuaVAT"));
			spp.setTongtientruocVat(Double.parseDouble(tongtientruocvat1.replace(",", "")));
			
			String tongtien1=util.antiSQLInspection(request.getParameter("sotiencoVAT"));
			spp.setTongtiensauVat(Double.parseDouble(tongtien1.replace(",", "")));
			
			String[] idsp1=request.getParameterValues("spId");
			String[] masp1=request.getParameterValues("masp");
			String[] tensp1=request.getParameterValues("tensp1");
			String[] soluong1=request.getParameterValues("soluong");
			String[] donvitinh1=request.getParameterValues("donvitinh1");
			String[] dongia1=request.getParameterValues("dongia1");
			String[] chietkhau1=request.getParameterValues("chietkhau1");
			String[] thanhtien1=request.getParameterValues("thanhtien1");
			
			//add vao danh sach san pham
			List<ISanPham> listSp1=new ArrayList<ISanPham>();
			if(masp1!=null){
				for(int i=0;i<masp1.length;i++){
					if(tensp1[i].trim().length()>0 && masp1[i].trim().length()>0 && soluong1[i]!="" && thanhtien1[i]!=""){
						ISanPham sp = new SanPham();
						sp.setPK_SEQ(idsp1[i]);
						sp.setMaSP(masp1[i]);
						sp.setTenSP(tensp1[i]);
						sp.setSoLuong(Integer.parseInt(soluong1[i]));
						sp.setDVT(donvitinh1[i]);
						sp.setDonGia(Double.parseDouble((dongia1[i]).replaceAll(",", "")));
						sp.setChietkhau(Double.parseDouble((chietkhau1[i]).replaceAll(",", "")));
						sp.setThanhTien(Double.parseDouble((thanhtien1[i]).replaceAll(",", "")));
						listSp1.add(sp);
					}
				}
			}
			spp.setListSp(listSp1);*/
			session.setAttribute("donhangsua", dh);
			response.sendRedirect("/TrainingGESO/pages/Erp/donhangsua.jsp");
		}
		if(action.equals("luumoi")){
			if(!dh.insert()){
				
				session.setAttribute("donhang", dh);
				String nextjsp="/TrainingGESO/pages/Erp/donhangnew.jsp";
				response.sendRedirect(nextjsp);
				System.out.println(dh.getMsg());
			}else{
				IDonHangList obj=new DonHangList();
				obj.init("");
				//khoi tao 1 session
				session.setAttribute("ob", obj);
				String nextjsp="/TrainingGESO/pages/Erp/donhanglist.jsp";
				response.sendRedirect(nextjsp);
			}
		}
		if(action.equals("luusua")){
			if(!dh.Update()){
				session.setAttribute("donhangsua", dh);
				String nextjsp="/TrainingGESO/pages/Erp/donhangsua.jsp";
				response.sendRedirect(nextjsp);
				System.out.println(dh.getMsg());
			}else{
				IDonHangList obj=new DonHangList();
				obj.init("");
				//khoi tao 1 session va send toi trang jsp
				session.setAttribute("ob", obj);
				String nextjsp="/TrainingGESO/pages/Erp/donhanglist.jsp";
				response.sendRedirect(nextjsp);
			}
		}
		if(action.equals("savekhachhang")){
			String khid=request.getParameter("makh");
			String tenkh=request.getParameter("tenkh");
			String diachi=request.getParameter("diachikh");
			String trangthaikh=request.getParameter("dienthoaikh");
			dh.setMakh(khid);
			dh.setTenkh(tenkh);
			dh.setKhDiachi(diachi);
			dh.setKhDienthoai(trangthaikh);
			dh.savekhachhang();
			session.setAttribute("donhang", dh);
			String nextjsp="/TrainingGESO/pages/Erp/donhangnew.jsp";
			response.sendRedirect(nextjsp);
			System.out.println(dh.getMsg());
		}
	}

}
