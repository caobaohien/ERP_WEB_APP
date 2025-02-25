package geso.erp.servlets.donbanhang_tan;

import geso.dms.center.util.Utility;
import geso.erp.beans.donbanhang_tan.*;
import geso.erp.beans.donbanhang_tan.imp.*;

import geso.erp.beans.khachhang.IKhachhangList;
import geso.erp.beans.khachhang.imp.KhachhangList;
import geso.erp.beans.nhacungcap.INhacungcap;
import geso.erp.beans.nhacungcap.INhacungcapList;
import geso.erp.beans.nhacungcap.imp.Nhacungcap;
import geso.erp.beans.nhacungcap.imp.NhacungcapList;
 
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
 * Servlet implementation class NhacungcapNewSvl
 */
@WebServlet("/NhacungcapNewSvl")
public class DonhangNewSvl_tan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonhangNewSvl_tan() {
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
	    
	    String msg = "";
	    
	    IDonhangNew_tan bhn=new DonhangNew_tan();
	    bhn.setId(Id);
	    bhn.init();
	    bhn.CreateRs();
	    //Ham load gia tri can update
	    if(querystring.indexOf("update")>=0){
	    	
	    	String id = request.getParameter("update");
	    	bhn.init();
	    }

	    session.setAttribute("obj", bhn);
	    String nextJSP = "/TrainingGESO/pages/Erp/DonbanhangNew_tan.jsp";
		response.sendRedirect(nextJSP);
	     
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		IDonhangNew_tan obj=new DonhangNew_tan();
		
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    
	    String action = request.getParameter("action");
	    if (action == null){
	    	action = "";
	    }
	    
	    Utility util = new Utility();
	    
		HttpSession session = request.getSession();
	    String userId = util.antiSQLInspection(request.getParameter("userId"));
	    obj.setUserId(userId);
	    
	    // trường hợp tạo mới ID sẽ không có ,bằng khoảng trắng
	    String id = util.antiSQLInspection(request.getParameter("id"));
	    obj.setId(id==null?"":id);
	    System.out.println("id->>>>"+id);
	    String tongtien = util.antiSQLInspection(request.getParameter("tongtien"));
	    System.out.println("tongtien : "+tongtien);
	    obj.setTongtien(tongtien==null?"":tongtien);
	    
	     
	     String ngay = util.antiSQLInspection(request.getParameter("ngay")); 
	     obj.setNgaychungtu(ngay==null?"0":ngay);
	     
	     
	     String khachhang = util.antiSQLInspection(request.getParameter("khachhang")); 
	     obj.setKhachhang(khachhang==null?"":khachhang);
	     /*
	      String[] masp=request.getParameterValues("masp");
	      String[] idsp=request.getParameterValues("idsp");
	      
	      String[] tensp=request.getParameterValues("tensp");
	      String[] donvitinh=request.getParameterValues("dvt");
	      String[] dongia=request.getParameterValues("dongia");
	      String[] soluong=request.getParameterValues("soluong");
	      String[] thanhtien=request.getParameterValues("thanhtien");
	      // lấy được 1 mảng mã sản phẩm
	      List<Isanpham> listsp=new ArrayList<Isanpham>();
	      if(masp!=null){
	 	      for(int i=0;i <masp.length;i++){
		    	  if(masp[i].length() >0){
		    		  Isanpham sp=new Sanpham();
		    		  sp.setMasanpham(masp[i]);
		    		  sp.setId(idsp[i]);
		    		  sp.setTensanpham(tensp[i]);
		    		  sp.setDonvitinh(donvitinh[i]);
		    		  double sl=0;
		    		  try{
		    			  sl=Double.parseDouble(soluong[i].replaceAll(",", ""));
		    			  
		    		  }catch(Exception er){
		    			  
		    		  }
		    		  sp.setSoluong(sl);
		    		  
		    		  double dg=0;
		    		  try{
		    			  dg=Double.parseDouble(dongia[i].replaceAll(",", ""));
		    			  
		    		  }catch(Exception er){
		    			  
		    		  }
		    		  sp.setDongia(dg);
		    		  
		    		  sp.setThanhtien(sl*dg);
		    		  listsp.add(sp);
		    		  
		    	  }
		      }
	      }
	      
	      obj.setListSp(listsp);
	      
	     */
	     System.out.println("actiong: "+action);
	    	if(action.equals("save")) 
	    	{ 
	    		if(!obj.save()){
	    			// lưu không thành công
	    			 
	    			obj.CreateRs();
	    			session.setAttribute("obj", obj);
			    	response.sendRedirect("/TrainingGESO/pages/Erp/DonbanhangNew_tan.jsp");
	    		}else{
	    				IDonhangList_tan dh=new DonhangList_tan();
	    			    dh.init(""); 
	    			    // khởi tạo ra 1 session
	    			   // tất cả mọi chỗ gọi trang DonbanhangList.jsp  thì cái  tên đối tượng, và đối tượng truyền vào phải giống nhau
	    			    session.setAttribute("obj", dh);
	    			    String nextJSP = "/TrainingGESO/pages/Erp/DonbanhangList_Tan.jsp";
	    				response.sendRedirect(nextJSP);
	    		}
	    		
	    	}else{
	    		 
		    	obj.CreateRs();
	    		session.setAttribute("obj", obj);
		    	response.sendRedirect("/TrainingGESO/pages/Erp/DonbanhangNew_tan.jsp");	
	    	}
	
	     
	    
	
		
	}
	
 
}
