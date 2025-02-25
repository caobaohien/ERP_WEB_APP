package geso.erp.servlets.muahang_huy;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.concurrent.Phaser;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfTable;

import geso.dms.center.util.Utility;
import geso.erp.beans.muahang_huy.IMuaHang;
import geso.erp.beans.muahang_huy.IMuaHangNew;
import geso.erp.beans.muahang_huy.imp.MuaHang;
import geso.erp.beans.muahang_huy.imp.MuaHangNew;
import geso.erp.beans.nhacungcap.INhacungcap;
import geso.erp.beans.nhacungcap.INhacungcapList;
import geso.erp.beans.nhacungcap.imp.Nhacungcap;
import geso.erp.beans.nhacungcap.imp.NhacungcapList;
import geso.erp.beans.sanpham_tan.ISanpham;
import geso.erp.beans.sanpham_tan.imp.Sanpham;
import geso.erp.db.sql.dbutils;

/**
 * Servlet implementation class SanphamListSvl
 */
@WebServlet("/MuaHangSvl")
public class MuaHangSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MuaHangSvl() {
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
	    IMuaHang listdhmua=new MuaHang();
	    listdhmua.init("");
	    String Id = util.getId(querystring);
	    System.out.println("day la id "+ Id);
	    String msg = "";
	    //xu ly cac action tren trang danh sach don mua hang (display,chot,delete,edit)
	    if(action.equals("delete"))
	    {
	    	// set id cua dong hien tai
	    	listdhmua.setId(Id);
	    	listdhmua.delete_dhmua();
	    	listdhmua.init("");
	    }
	    else if(action.equals("chot"))
	    {
	    	listdhmua.setId(Id);
	    	listdhmua.chot_dhmua();
	    	listdhmua.init("");
	    }
	    // khởi tạo ra 1 se'ssion
	    session.setAttribute("obj",listdhmua);
	    String nextJSP = "/TrainingGESO/pages/Erp/MuaHang_Huy.jsp";
		response.sendRedirect(nextJSP);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		IMuaHang obj;
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    // 
	    Utility util = new Utility();
	    String action = request.getParameter("action");
	    if (action == null){
	    	action = "";
	    }
	    obj=new MuaHang();
	    System.out.println("action gi day : "+action);
	    String userId = util.antiSQLInspection(request.getParameter("userId"));
	    System.out.println("userid gi day: "+userId);
	    obj.setUserId(userId);
	    String id = util.antiSQLInspection(request.getParameter("id"));
	   obj.setId(id==null?"":id);
	    // lay ten tu input cua MuHang_Huy.jsp
	    String mamh= util.antiSQLInspection(request.getParameter("mamh"));
	    if(mamh==null)
	    {
	    	mamh="";
	    }
	    System.out.println("ma mh: "+mamh);
	    String trangthai= util.antiSQLInspection(request.getParameter("trangthai"));
	    if(trangthai==null)
	    {
	    	trangthai="";
	    }
	    System.out.println("trang thai : "+trangthai);
	    String tenncc= util.antiSQLInspection(request.getParameter("tenncc"));
		  if(tenncc==null)
		  {
			  tenncc="";
		  }
	    System.out.println("ten nha cung cap : "+tenncc);
	    // tao session
	    HttpSession session = request.getSession();	 
	    // gan cac truong du lieu lay duoc vao doi tuong mua hang
	    obj.setmamh(mamh);
	    obj.setTrangthai(trangthai);
	    obj.settenncc(tenncc);
	   if(action.equals("timkiem")){
		   		obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
   				String search = getSearchQuery(request, obj);
	    	obj.init(search);
	    	session.setAttribute("obj", obj);
	    	response.sendRedirect("/TrainingGESO/pages/Erp/MuaHang_Huy.jsp");
	    	
	    	// nó chạy vào hàm tạo mới nên chỗ breakpoint nênlà chỗ này, nếu nó không lỗi nữa thì đặt bên trang jsp
	   }else if(action.equals("new")){
		   System.out.println("vao new");
		   IMuaHangNew mhnew=new MuaHangNew();
		   mhnew.CreateRs();
	    	session.setAttribute("obj",mhnew);
	    	response.sendRedirect("/TrainingGESO/pages/Erp/MuaHangNew_Huy.jsp");
		   
	   }else if(action.equals("clear"))
	   {
		   	obj.init("");
	    	session.setAttribute("obj",obj);
	    	response.sendRedirect("/TrainingGESO/pages/Erp/MuaHang_Huy.jsp");
	   }
	   else if(action.equals("print")){
		   
		   Create_Pdf(response, request);
			}
	   }
	   {	    
	   
	}
	   
	   
	private void Create_Pdf(HttpServletResponse response,HttpServletRequest request)
	{
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition"," inline; filename=DonHang.pdf");
		float CONVERT = 28.346457f;//1 cm 
		float PAGE_LEFT = 2.0f*CONVERT, PAGE_RIGHT = 1.5f*CONVERT, PAGE_TOP = 0.5f*CONVERT, PAGE_BOTTOM = 0.0f*CONVERT; //cm
		// tao doi tuong document
		Document document=new Document(PageSize.A4, PAGE_LEFT, PAGE_RIGHT, PAGE_TOP, PAGE_BOTTOM);
		// ten tai lieu
		ServletOutputStream outstream;	
		try
		{
			outstream=response.getOutputStream();
			 dbutils db = new dbutils();
			 this.Create_Po_Pdf(document, outstream, response, request, db);
			 db.shutDown();
		}
		catch(Exception e)
		{}
	}
	private void Create_Po_Pdf(Document document,ServletOutputStream outstream,HttpServletResponse response,HttpServletRequest request,dbutils db)
	{
		HttpSession session	=request.getSession();
		try
		{
			NumberFormat formatter_2sole = new DecimalFormat("#,###,###.###");
			NumberFormat formatter = new DecimalFormat("#,###,###");
			PdfWriter.getInstance(document, outstream);
			document.open();

			float CONVERT = 28.346457f; // = 1cm
			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			 // khỏi tạo font chữ 
			Font font10 = new Font(bf, 10, Font.NORMAL);
			 
			Font font11_bold= new Font(bf, 11, Font.BOLD);
			// tao table header
			PdfPTable tbl_header=new PdfPTable(1);
			tbl_header.setWidthPercentage(100);
			tbl_header.setHorizontalAlignment(Element.ALIGN_LEFT);
			float[] crtbl_header = {7f};
			tbl_header.setWidths(crtbl_header);
			// tao cell ten cong ty
			PdfPCell cell=new PdfPCell(new Paragraph("CONG TY GIAI PHAP DOANH NGHIEP TOAN CAU", font11_bold));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			tbl_header.addCell(cell);
			// tao cell dia chi
			cell=new PdfPCell(new Paragraph("Dia Chi",font11_bold ));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			tbl_header.addCell(cell);
			// tao cell dien thoai
			cell=new PdfPCell(new Paragraph("Dien Thoai",font11_bold ));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			tbl_header.addCell(cell);
			document.add(tbl_header);
			PdfPTable tbl_img=new PdfPTable(1);
			Image img=Image.getInstance(getServletContext().getInitParameter("pathpdf")+"/images/logo.png");
			img.setAlignment(Element.ALIGN_RIGHT);
			document.add(img);
			Paragraph pxk = new Paragraph("DON BAN HANG", new Font(bf, 14, Font.BOLD));
			pxk.setSpacingAfter(2);
			pxk.setAlignment(Element.ALIGN_CENTER);
			document.add(pxk);
			Paragraph tenkhachhang=new Paragraph("Ten KH:",new Font(bf,10));
			tenkhachhang.setSpacingAfter(2);
			document.add(tenkhachhang);
			Paragraph diachikh=new Paragraph("Dia Chi KH:",new Font(bf,10));
			diachikh.setSpacingAfter(2);
			document.add(diachikh);
		
			float[] crSanpham = {0.7f * CONVERT, 2.0f * CONVERT,2.0f * CONVERT, 2.9f * CONVERT, 1.8f * CONVERT, 2.0f * CONVERT }; 
			PdfPTable tbSanPham = new PdfPTable(crSanpham.length);
			tbSanPham.setWidthPercentage(100);
			tbSanPham.setHorizontalAlignment(Element.ALIGN_LEFT);			
			tbSanPham.setWidths(crSanpham);
			tbSanPham.getDefaultCell().setBorder(0);
			tbSanPham.setSpacingAfter(8.0f);
			String[] spTitles = {"SỐ TT","Ma DH", "Ten NCC","So Luong", "Don Gia","Thanh Tien"};
			for(int z = 0; z < spTitles.length; z++) {
				cell = new PdfPCell(new Paragraph(spTitles[z], font11_bold));
				cell.setPadding(3.0f);
				cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				tbSanPham.addCell(cell);
			}
			// thuc hien load du lieu
			String query="";
			query=" select mh.PK_SEQ AS MUAHANG_FK,mh.TONGTIEN as TONGTIEN, ISNULL(NCC.TEN,'') AS TEN,mhsp.SOLUONG as SOLUONG,mhsp.DONGIA as DONGIA"
	+" from MUAHANG mh left join NHACUNGCAP NCC ON NCC.PK_SEQ= MH.NHACUNGCAP_FK  "
	+ "LEFT JOIN MUAHANG_SANPHAM mhsp ON mhsp.MUAHANG_FK=MH.PK_SEQ";
			ResultSet rs=db.get(query); 
			int sott=1;
			double tongtien=0;
			while(rs.next())
			{
				String[] spdetail={""+sott,rs.getString("MUAHANG_FK"),rs.getString("TEN"),
						formatter.format(rs.getFloat("SOLUONG")),
						formatter.format(rs.getFloat("DONGIA")),formatter.format(rs.getFloat("TONGTIEN"))};
				tongtien =tongtien+ Math.round(rs.getDouble("tongtien"));
				for(int z = 0; z < spdetail.length; z++) {
					cell = new PdfPCell(new Paragraph(spdetail[z], font10));
					cell.setPadding(3.0f);
					if(z==4||z==5||z==7){
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);	
					}else{
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					}
					tbSanPham.addCell(cell);
				}
				sott++ ;
			}
			cell = new PdfPCell(new Paragraph("Tổng cộng", font11_bold));
			cell.setColspan(5); 
			cell.setPadding(3.0f);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tbSanPham.addCell(cell);
		
			cell = new PdfPCell(new Paragraph(formatter.format(tongtien), font11_bold));
			cell.setPadding(3.0f);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tbSanPham.addCell(cell);
			document.add(tbSanPham);
			
			Paragraph tongtienchu=new Paragraph("So tien bang chu",new Font(bf,10));
			document.add(tongtienchu);
			
			//tao image logo congty
			document.close(); 
		}
		catch(Exception e)
		{}
	}
	private String getSearchQuery(HttpServletRequest request, IMuaHang obj) {
		Utility util=new Utility();
	
		String query;
		 query=" SELECT ISNULL(mh.TRANGTHAI,'') AS TRANGTHAI,mhsp.MUAHANG_FK,"
			 		+ "mh.NGAYCHUNGTU,ncc.TEN,mh.TONGTIEN,mh.NGAYTAO,mh.NGUOITAO FROM MUAHANG mh LEFT"
			 		+ " JOIN NHACUNGCAP ncc ON mh.NHACUNGCAP_FK=ncc.PK_SEQ LEFT "
			 		+ "JOIN MUAHANG_SANPHAM mhsp ON mhsp.MUAHANG_FK=mh.PK_SEQ where 1=1";
		
		if(obj.getmamh().length()>0){
			query+=" AND mhsp.MUAHANG_FK LIKE '%"+ obj.getmamh()+"%'";
		}
		if(obj.gettenncc().length()>0){
			query += " AND ncc.TEN LIKE '%" + obj.gettenncc() + "%'";
		}
		 
		if(obj.getTrangthai().length()>0){
			query+=" AND mh.TRANGTHAI LIKE '"+obj.getTrangthai()+"'";
		}
		return query;
		
	}

}

