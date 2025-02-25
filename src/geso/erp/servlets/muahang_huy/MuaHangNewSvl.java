package geso.erp.servlets.muahang_huy;

import geso.dms.center.util.Utility;
import geso.erp.beans.donhang_huy.ISanPham;
import geso.erp.beans.donhang_huy.imp.SanPham;
import geso.erp.beans.muahang_huy.IMuaHang;
import geso.erp.beans.muahang_huy.IMuaHangNew;
import geso.erp.beans.muahang_huy.imp.MuaHang;
import geso.erp.beans.muahang_huy.imp.MuaHangNew;
import geso.erp.beans.nhacungcap.INhacungcap;
import geso.erp.beans.nhacungcap.imp.Nhacungcap;
import geso.erp.db.sql.dbutils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aspose.cells.BorderLineType;
import com.aspose.cells.BorderType;
import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.Color;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Font;
import com.aspose.cells.Style;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;

/**
 * Servlet implementation class DonHangNewSvl
 */
public class MuaHangNewSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MuaHangNewSvl() {
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
		System.out.println("dang o di");
	    String msg = "";
	    IMuaHangNew muahangnew=new MuaHangNew();
	    if(querystring.indexOf("update")>=0)
	    {
	    	System.out.println("dang o update");
	    	String id=request.getParameter("update");
	    	muahangnew.setId(id);
	    	muahangnew.init();
	    	muahangnew.CreateRs();
	  	    session.setAttribute("obj",muahangnew);
	  	    String nextJSP = "/TrainingGESO/pages/Erp/MuaHangNew_Huy.jsp";
	  		response.sendRedirect(nextJSP);
	    }
	    else if(querystring.indexOf("display")>=0){
	    	System.out.println("dang o display");
	    	String id=request.getParameter("display");
	    	muahangnew.setId(Id);
	    	muahangnew.init();
	    	muahangnew.CreateRs();
	  	    session.setAttribute("obj",muahangnew);
	  	    String nextJSP = "/TrainingGESO/pages/Erp/MuaHangDisplay_Huy.jsp";
	  		response.sendRedirect(nextJSP);
	    }
	    {
	    	
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		IMuaHangNew obj=new MuaHangNew();
		
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    
	    String action = request.getParameter("action");
	    if (action == null){
	    	action = "";
	    }
	    System.out.println("action gi day : "+action);
	    Utility util = new Utility();
		HttpSession session = request.getSession();
	    String userId = util.antiSQLInspection(request.getParameter("userId"));
	    obj.setUserId(userId);
	    String id = util.antiSQLInspection(request.getParameter("id"));
	  
	   obj.setId(id==null?"":id);
	   System.out.println("id gi day : "+id);
	    String tenncc =util.antiSQLInspection(request.getParameter("tenncc"));
	    System.out.println("tenncc : "+tenncc);
	    obj.setmancc(tenncc);
	    String ngay =util.antiSQLInspection(request.getParameter("ngaymuahang"));
	    System.out.println("ngay : "+ngay);
	    obj.setngay(ngay);
	    Float tongtien =Float.parseFloat(request.getParameter("tongtien").replaceAll(",", ""));
	    System.out.println("tongtien : "+tongtien);
	    obj.settongtien(tongtien);
	     // khoi tao cac mang chua thong tin cac cot 
	     String[] masp=request.getParameterValues("masp");
	     String[] tensp=request.getParameterValues("tensp");
	     String[] donvitinh=request.getParameterValues("dvt");
	     String[] soluong=request.getParameterValues("soluong");
	     String[] idsp=request.getParameterValues("idsp");
	     String[] dongia=request.getParameterValues("dongia");
	     String[] thanhtien=request.getParameterValues("thanhtien");
	     String[] chietkhau=request.getParameterValues("chietkhau");
	     // khoi tao mot mang san pham
	     List<ISanPham> listsp=new ArrayList<ISanPham>();
	     // lay ra danh sach cac ma san pham
	     if(masp!=null)
	     {
	    	 System.out.println("trong if");
	    	 for(int i=0;i<masp.length;i++)
	    	 {
	    		 if(masp[i].length()>0)
	    		 {
	    			 System.out.println("trong if sau");
	    			 ISanPham sp=new SanPham();
	    			 // set cac gia tri cho san pham
	    			 sp.setMasanpham(masp[i]);
	    			 sp.setId(idsp[i]);
	    			 System.out.println("id sp"+sp.getId());
	    			 sp.setTensanpham(tensp[i]);
	    			 sp.setDonvitinh(donvitinh[i]);
	    			 double sl=0;
	    			try
	    			{
	    				sl=Double.parseDouble(soluong[i].replaceAll(",",""));
	    				
	    			}
	    			catch(Exception e)
	    			{
	    			}
	    			sp.setSoluong(sl);
	    			double dg=0;
	    			try{
	    				dg=Double.parseDouble(dongia[i].replaceAll(",",""));
	    				
	    			}
	    		 catch(Exception e)
	    		 {
	    			 
	    		 }
	    			
	    			sp.setDongia(dg);
	    			double ck=0;
	    			try{
	    				ck=Double.parseDouble(chietkhau[i].replaceAll(",",""));
	    				
	    			}
	    		 catch(Exception e)
	    		 {
	    			 
	    		 }
	    			sp.setChietkhau(ck);
	    			sp.setThanhtien((sl*dg)-(sl*dg*(ck/100)));
	    			double tien=sp.getThanhtien();
	    			System.out.println("THANH TIEN"+ tien);
	    			listsp.add(sp);
	    			
	    	 }
	    	 }
	     }
	     
	     
	     System.out.println("danh sach sp"+listsp.size());
	     // goi doi tuong set list san pham
	     obj.setListSp(listsp);
	     System.out.println("actiong: "+action);
	   	java.io.OutputStream out=response.getOutputStream();
	     // khai bao nhacungcap
	     
	     INhacungcap nccnew=new Nhacungcap();
	     String manhacc=request.getParameter("manhacc");
	     String tennhacc=request.getParameter("tennhacc");
	     String diachi=request.getParameter("diachi");
	     String dienthoai=request.getParameter("dienthoai");
	     nccnew.setMa(manhacc);
	     nccnew.setTen(tennhacc);
	     nccnew.setDiachi(diachi);
	     nccnew.setDienthoai(dienthoai);
	    	if(action.equals("save")) 
	    	{ 
	    		if(id.length() ==0)
	    		{
		    		if(!obj.save()){
		    			// lưu không thành công
		    			System.out.println("tai sao o luu");
		    			obj.setId(id);
		    			obj.init();
		    			obj.CreateRs();
		    			session.setAttribute("obj", obj);
				    	response.sendRedirect("/TrainingGESO/pages/Erp/MuaHangNew_Huy.jsp");
		    		}else{
		    				IMuaHang mh=new MuaHang();
		    			
		    				mh.init("");
		    			    session.setAttribute("obj", mh);
		    			    String nextJSP = "/TrainingGESO/pages/Erp/MuaHang_Huy.jsp";
		    				response.sendRedirect(nextJSP);
		    		}
	    		}
	    		else 
	    		{
		    		System.out.println("id: cua edit  "+id);
		    		
		    			if(!obj.edit())
		    			{
		    				// luu o thanh cong
		    				System.out.println("tai sao o luu");
			    			obj.setId(id);
			    			obj.init();
			    			obj.CreateRs();
			    			session.setAttribute("obj", obj);
					    	response.sendRedirect("/TrainingGESO/pages/Erp/MuaHangNew_Huy.jsp");
		    			}
		    			else
		    			{
		    				IMuaHang mh=new MuaHang();
		    				mh.init("");
		    			    session.setAttribute("obj", mh);
		    			    String nextJSP = "/TrainingGESO/pages/Erp/MuaHang_Huy.jsp";
		    				response.sendRedirect(nextJSP);
		    			}
	    		}
	    	}
	    	else if(action.equals("savenhacungcap"))
	    		{
	    			if(!obj.savencc()){
		    			
		    			System.out.println("dang o save nha cc");
		    			obj.setId(id);
		    			obj.init();
		    			obj.CreateRs();
		    			session.setAttribute("obj", obj);
				    	response.sendRedirect("/TrainingGESO/pages/Erp/MuaHangNew_Huy.jsp");
		    		}
	    			else{
		    				
		    			    session.setAttribute("obj", obj);
		    			    String nextJSP = "/TrainingGESO/pages/Erp/MuaHang_Huy.jsp";
		    				response.sendRedirect(nextJSP);
		    		}
	    		}
		    	else if(action.equals("printexcel")){
		    			System.out.println("dang o xuat excel");
		    			response.setContentType("application/xls");
		    			response.setHeader("ContentDisposition","attachment;filename=donmuahang.xls");
		    			if(!Create_Excel(out, response, request,id)){
		    	    		response.setContentType("text/html");
		    			    PrintWriter writer = new PrintWriter(out);
		    			    writer.print("Lỗi xuất excel");
		    			    writer.close();
		    	    	}
	    		}
	    	/*else{
	    		 
		    	obj.CreateRs();
	    		session.setAttribute("obj", obj);
		    	response.sendRedirect("/TrainingGESO/pages/Erp/DonHangNew_Huy.jsp");	*/
	    	//}
	    
		
	}
	public boolean Create_Excel(java.io.OutputStream out,HttpServletResponse response,HttpServletRequest request,String id) throws IOException
	{
		FileInputStream fstream = null;
		Workbook workbook=new Workbook();
		boolean isFillData = false;
		fstream =new FileInputStream(getServletContext().getInitParameter("pathexcel")+"\\muahang.xls");
		workbook.open(fstream);
		workbook.setFileFormatType(FileFormatType.EXCEL97TO2003);
		isFillData = FillData(workbook,id);
		   
		if (!isFillData)
		{
			if(fstream != null)
				fstream.close();
			return false;
		}
		
		workbook.save(out);
		fstream.close();
		return true;
	}
	public boolean FillData(Workbook workbook,String id)
	{
		dbutils db=null;
		try
		{
			NumberFormat formatter_2sole = new DecimalFormat("#,###,###.###");
			 NumberFormat formatter = new DecimalFormat("#,###,###");
			db=new dbutils();
			Worksheets worksheets=workbook.getWorksheets();
			Worksheet worksheet=worksheets.getSheet(0);
			Cells cells=worksheet.getCells();
			Cell cell=null;
			Style style=null;
			Font font = new Font();
			font.setName("Arial");
			font.setSize(10);
			
			// them noi dung tieu de file excel

			cell = cells.getCell("B2");
			cell.setValue("CONG TY GIAI PHAP DOANH NGHIEP TOAN CAU ");
			style = cell.getStyle();
			style.setColor(Color.GREEN);
			style.setFont(font);
			
			cell = cells.getCell("B3");
			cell.setValue("Địa chỉ:");
			
			cell = cells.getCell("B4");
			cell.setValue("Số điện thoại");
			
			cell = cells.getCell("J5");
			cell.setValue("ĐƠN MUA HÀNG");
			
			cell = cells.getCell("B7");
			cell.setValue("TEN NCC");
			cell = cells.getCell("B8");
			cell.setValue("NGAY CHUNG TU");
			// them bang
			cell = cells.getCell("F10");
			cell.setValue("STT");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
	        
	        cell = cells.getCell("G10");
	        cell.setValue("MA");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
	        
	        cell = cells.getCell("H10");
	        cell.setValue("TEN");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
	       
	   
	        cell = cells.getCell("I10");
	        cell.setValue("SOLUONG");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
	        
	        cell = cells.getCell("J10");
	        cell.setValue("DONGIA");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
	        
	        cell = cells.getCell("K10");
	        cell.setValue("THANHTIEN");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
	        
	        // do du lieu vao cac fill
	        String query;
	        String ten="";
	        String ngay="";
	        int stt=1;
	        double tongtien=0;
	        query="select ncc.TEN as ten,mh.NGAYCHUNGTU,sp.MA,sp.TEN,mhsp.SOLUONG,sp.GIABAN,mh.TONGTIEN "
	        		+ "from MUAHANG mh left join NHACUNGCAP ncc on mh.NHACUNGCAP_FK=ncc.PK_SEQ "
	        		+ "left join MUAHANG_SANPHAM mhsp on mh.PK_SEQ=mhsp.MUAHANG_FK "
	        		+ "left join SANPHAM sp on mhsp.SANPHAM_FK=sp.PK_SEQ where mh.PK_SEQ="+id;
	        ResultSet rs=db.get(query);
	        if(rs !=null)
	        {
	        	while(rs.next())
	        	{
	        		String[]spTitle ={"F","G","H","I","J","K"};
	        		   String[] spTitles2 = { "" + stt,
	        		     rs.getString("MA"),rs.getString("TEN"),
	        		     rs.getString("SOLUONG"),rs.getString("GIABAN"),
	        		     formatter.format(rs.getDouble("TONGTIEN")) };
	        		   ten=rs.getString("ten");
	        		   ngay=rs.getString("NGAYCHUNGTU");
	        		    tongtien = tongtien + Math.round(rs.getDouble("TONGTIEN"));
	        		    for (int z = 0; z < spTitles2.length; z++) {
	        		    cell=cells.getCell(spTitle[z]+Integer.toString(10+stt));
	        		    cell.setValue(""+spTitles2[z]+"");
	        		    font.setBold(false);
	        		    font.setSize(12);
	        		    style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        		          style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        		          style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        		          style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        		          cell.setStyle(style);
	        		          style.setColor(null);
	        		         
	        		    
	        		   }
	        		    stt++;
	        	}
	        }
	        cell = cells.getCell("D7");
			cell.setValue(""+ten+"");
			
			cell = cells.getCell("D8");
			cell.setValue(""+ngay+"");
	        return true;
	        
		}
		catch(Exception e)
		{
			return false;
		}
		finally{
			db.shutDown();
		}
	}
	
}
