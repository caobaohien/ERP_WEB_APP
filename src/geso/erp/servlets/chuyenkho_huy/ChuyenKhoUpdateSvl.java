package geso.erp.servlets.chuyenkho_huy;

import geso.dms.center.util.Utility;
import geso.erp.beans.chuyenkho_huy.IChuyenKhoUpdate;
import geso.erp.beans.chuyenkho_huy.IListChuyenKho;
import geso.erp.beans.chuyenkho_huy.imp.ChuyenKhoUpdate;
import geso.erp.beans.chuyenkho_huy.imp.ListChuyenKho;
import geso.erp.beans.chuyenkho_huy.imp.SanPham;
import geso.erp.db.sql.dbutils;
import geso.erp.beans.chuyenkho_huy.ISanPham;

import java.io.FileInputStream;
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
import com.aspose.cells.Style;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Servlet implementation class DonHangNewSvl
 */
public class ChuyenKhoUpdateSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChuyenKhoUpdateSvl() {
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
	    IChuyenKhoUpdate chuyenkhonew=new ChuyenKhoUpdate();
	    String[] soluong=request.getParameterValues("soluongton");
	    String [] booked=request.getParameterValues("soluongchuyen");
	    System.out.println("sl trong chot"+soluong);
		System.out.println("bok trong chot"+booked);
	   
	 
	    if(action.equals("display"))
	    {
	    	chuyenkhonew.setId(Id);
	    	chuyenkhonew.init();
	    	chuyenkhonew.CreateRs();
	    	 session.setAttribute("obj", chuyenkhonew);
		   	    response.sendRedirect("/TrainingGESO/pages/Erp/ChuyenKhoDisplay_Huy.jsp");
	    }else if(action.equals("update"))
	    {
	    	chuyenkhonew.setId(Id);
	    	chuyenkhonew.init();
	    	chuyenkhonew.CreateRs();
	    	 session.setAttribute("obj", chuyenkhonew);
	   	    response.sendRedirect("/TrainingGESO/pages/Erp/ChuyenKhoUpdate_Huy.jsp");
	    } else if(action.equals("chot")){
	    	IListChuyenKho listck=new ListChuyenKho();
	    	chuyenkhonew.setId(Id);
	    	System.out.println("id chot "+Id);
	    	chuyenkhonew.chot();
	    	listck.init("");
	    	 session.setAttribute("obj", listck);
		   	    response.sendRedirect("/TrainingGESO/pages/Erp/ChuyenKhoList_Huy.jsp");
	    }else if(action.equals("delete"))
	    {
	    	IListChuyenKho listck=new ListChuyenKho();
	    	chuyenkhonew.setId(Id);
	    	System.out.println("id delete "+Id);
	    	chuyenkhonew.delete();
	    	listck.init("");
	    	 session.setAttribute("obj", listck);
		   	    response.sendRedirect("/TrainingGESO/pages/Erp/ChuyenKhoList_Huy.jsp");
	    }
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
	    HttpSession session = request.getSession();
	    String action = request.getParameter("action");
	    if (action == null){
	    	action = "";
	    }
	    // tao doi tuong
	    IChuyenKhoUpdate obj=new ChuyenKhoUpdate();
	    // lay cac gia tri tu the input
	    String userId = util.antiSQLInspection(request.getParameter("userId")); 
	    
		String id = util.antiSQLInspection(request.getParameter("id"));
		
		String ngayghinhan=util.antiSQLInspection(request.getParameter("ngayghinhan"));
		if(ngayghinhan==null){ngayghinhan="";}
		String lydo=util.antiSQLInspection(request.getParameter("lydo"));if(lydo==null){lydo="";}
		String khochuyen=util.antiSQLInspection(request.getParameter("khochuyen"));if(khochuyen==null){khochuyen="";}
		System.out.println("kho chuyen"+khochuyen);
		String khonhan=util.antiSQLInspection(request.getParameter("khonhan"));if(khonhan==null){khonhan="";}
		System.out.println("kho nhan"+khonhan);
		obj.setUserId(userId);
		obj.setId(id==null?"":id);
		System.out.println("id la bao nhieu"+id);
		obj.setNgayghinhan(ngayghinhan);
		obj.setLydo(lydo);
		obj.setKhochuyen(khochuyen);
		System.out.println("ma kho chuyen can lay"+khochuyen);
		
		obj.setKhonhan(khonhan);
		
		 // khoi tao cac mang chua thong tin cac cot 
	     String[] masp=request.getParameterValues("masp");
	     String[] tensp=request.getParameterValues("tensp");
	     String[] donvitinh=request.getParameterValues("dvt");
	     String[] soluong=request.getParameterValues("soluongton");
	     String[] idsp=request.getParameterValues("idsp");
	     String [] booked=request.getParameterValues("soluongchuyen");
	     // khoi tao mot mang san pham
	     List<ISanPham> listsp=new ArrayList<ISanPham>();
	     
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
	    			 System.out.println("ma sp"+sp.getMasanpham());
	    			 sp.setId(idsp[i]);
	    			 System.out.println("id sp"+sp.getId());
	    			 sp.setTensanpham(tensp[i]);
	    			 System.out.println("ma sp"+sp.getTensanpham());
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
	    			 System.out.println("sl sp"+sp.getSoluong());
	    			double bk=0;
	    			try
	    			{
	    				bk=Double.parseDouble(booked[i].replaceAll(",",""));
	    				
	    			}
	    			catch(Exception e)
	    			{
	    			}
	    			sp.setBooked(bk);
	    			 System.out.println("sl book sp"+sp.getBooked());
	    			listsp.add(sp);
	    			
	    	 }
	    	}
	     }
	     
	     
	     System.out.println("danh sach sp"+listsp.size());
	     // goi doi tuong set list san pham
	     obj.setListSp(listsp);
	    java.io.OutputStream out=response.getOutputStream();
		// xu ly action save
	    if(action.equals("save"))
	    {		IListChuyenKho  listck=new ListChuyenKho();
	    		System.out.println("dang o save");
	    		System.out.println("id la bao nhieu"+id);
	    	if(id.length() ==0)	// save cua tao moi
	    	{
	    		if(!obj.save())	// khong thuc hien duoc ham save
	    		{
	    			System.out.println("khong save");
	    			obj.init();
	    			obj.CreateRs();
	    			session.setAttribute("obj", obj);
	    			response.sendRedirect("/TrainingGESO/pages/Erp/ChuyenKhoUpdate_Huy.jsp");
	    		}
	    		else	// save thanh cong
	    		{
	    			System.out.println(" save");
	    			listck.init("");
	    			session.setAttribute("obj", listck);
	    			response.sendRedirect("/TrainingGESO/pages/Erp/ChuyenKhoList_Huy.jsp");
	    		}
	    	}
	    	else 
	    	{
	    		if(!obj.edit())
    			{
    				// luu o thanh cong
	    			System.out.println("da vao edit");
    				System.out.println("tai sao o luu");
	    			obj.init();
	    			obj.CreateRs();
	    			session.setAttribute("obj", obj);
			    	response.sendRedirect("/TrainingGESO/pages/Erp/ChuyenKhoUpdate_Huy.jsp");
    			}
    			else
    			{
    				
    				System.out.println("  o edit");
    				listck.init("");
    			    session.setAttribute("obj", listck);
    			    String nextJSP = "/TrainingGESO/pages/Erp/ChuyenKhoList_Huy.jsp";
    				response.sendRedirect(nextJSP);
    			}
	    	}
	    	
	    }
	    else if(action.equals("print_pdf"))
	    {
	    	System.out.println("dang o in pdf");
	    	Print_Pdf(response, request,id);
	    }
	    else if(action.equals("print_excel"))
	    {
	    	System.out.println("dang o xuat excel");
			response.setContentType("application/xls");
			response.setHeader("ContentDisposition","attachment;filename=phieuchuyenkho.xls");
			if(!Create_Excel(out, response, request,id)){
	    		response.setContentType("text/html");
			    PrintWriter writer = new PrintWriter(out);
			    writer.print("Lỗi xuất excel");
			    writer.close();
			}
	    }else if(action.equals("khochuyen"))
		{
	    	session.setAttribute("kho", khochuyen);
	    	//obj.init();
	    	listsp.clear();
			obj.CreateRs();
			session.setAttribute("obj", obj);
			response.sendRedirect("/TrainingGESO/pages/Erp/ChuyenKhoUpdate_Huy.jsp");
		}
	    	
	}
	
	// xuat pdf
	private void Print_Pdf(HttpServletResponse response,HttpServletRequest request,String id)
	{
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition"," inline; filename=PhieuChuyenKho.pdf");
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
			 this.Create_Pdf(document, outstream, response, request, db,id);
			 db.shutDown();
		}
		catch(Exception e)
		{}
	}
	private void Create_Pdf(Document document,ServletOutputStream outstream,HttpServletResponse response,HttpServletRequest request,dbutils db,String id)
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
			PdfPCell cell=new PdfPCell(new Paragraph("CÔNG TY GIẢI PHÁP DOANH NGHIỆP TOÀN CẦU", font11_bold));
			cell.setPaddingLeft(2.0f);
			cell.setBorder(0);
			tbl_header.addCell(cell);
			// tao cell dia chi
			cell=new PdfPCell(new Paragraph("234 Nguyễn Trọng Tuyển, Phú Nhuận, HCM",font11_bold ));
			cell.setPaddingLeft(2.0f);
			cell.setBorder(0);
			tbl_header.addCell(cell);
			document.add(tbl_header);
			
			Image img=Image.getInstance(getServletContext().getInitParameter("ImagePath")+"/images/GESO.png");
			img.setAlignment(Element.ALIGN_RIGHT);
			
			document.add(img);
			
			Paragraph pxuongdong=new Paragraph("\n");
			document.add(pxuongdong);
			
			Paragraph pxk = new Paragraph("PHIẾU CHUYỂN KHO", new Font(bf, 14, Font.BOLD));
			pxk.setSpacingAfter(2);
			pxk.setAlignment(Element.ALIGN_CENTER);
			document.add(pxk);
			
			 pxuongdong=new Paragraph("\n");
			document.add(pxuongdong);
			
			String query;
			query="select ck.PK_SEQ,ck.NGAYCHUYENKHO,khonhan.TENKHO as khonhan,khochuyen.TENKHO as khochuyen,"
					+ "khonhan.DIACHI as dckhonhan,khochuyen.DIACHI as dckhochuyen,ck.LYDO "
					+ "from CHUYENKHO ck left join KHO khonhan on ck.KHONHAN=khonhan.PK_SEQ "
					+ "left join KHO khochuyen on khochuyen.PK_SEQ=ck.KHOCHUYEN where ck.PK_SEQ="+id+"";
			ResultSet rsnd=db.get(query); 
			
			String mact="";
			String ngay="";
			String tukho="";
			String denkho="";
			String dcnhan="";
			String dcgiao="";
			String lydo="";
			
			while(rsnd.next())
			{
				 mact=rsnd.getString("PK_SEQ");
				 ngay=rsnd.getString("NGAYCHUYENKHO");
				 tukho=rsnd.getString("khochuyen");
				 denkho=rsnd.getString("khonhan");
				 dcnhan=rsnd.getString("dckhonhan");
				 dcgiao=rsnd.getString("dckhochuyen");
				 lydo=rsnd.getString("LYDO");
				
			}
			
			PdfPTable tdl_noidung=new PdfPTable(2);
			
			cell=new PdfPCell(new Paragraph("Ma Chung Tu: "+mact, font11_bold));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			tdl_noidung.addCell(cell);
			
			cell=new PdfPCell(new Paragraph("Ngay Lap:"+ngay, font11_bold));
			cell.setPaddingLeft(80.0f);
			//cell.setPaddingRight(25.0f);
			cell.setBorder(0);
			tdl_noidung.addCell(cell);
		
			
			cell=new PdfPCell(new Paragraph("Tu Kho:"+tukho, font11_bold));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			tdl_noidung.addCell(cell);
			
			cell=new PdfPCell(new Paragraph("Den Kho:"+denkho, font11_bold));
			cell.setPaddingLeft(80.0f);
			cell.setBorder(0);
			tdl_noidung.addCell(cell);
		
			
			cell=new PdfPCell(new Paragraph("Dia Chi:"+dcgiao, font11_bold));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			tdl_noidung.addCell(cell);
			
			cell=new PdfPCell(new Paragraph("Dia Chi Giao hang:"+dcnhan, font11_bold));
			cell.setPaddingLeft(80.0f);
			cell.setBorder(0);
			tdl_noidung.addCell(cell);
			
			cell=new PdfPCell(new Paragraph("Ly do::"+lydo, font11_bold));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			tdl_noidung.addCell(cell);
			document.add(tdl_noidung);
			 pxuongdong=new Paragraph("\n\n");
				document.add(pxuongdong);
			
			float[] crSanpham = {0.7f * CONVERT, 2.0f * CONVERT,2.0f * CONVERT, 2.9f * CONVERT, 1.8f * CONVERT }; 
			PdfPTable tbSanPham = new PdfPTable(crSanpham.length);
			tbSanPham.setWidthPercentage(100);
			tbSanPham.setHorizontalAlignment(Element.ALIGN_LEFT);			
			tbSanPham.setWidths(crSanpham);
			tbSanPham.getDefaultCell().setBorder(0);
			tbSanPham.setSpacingAfter(8.0f);
			String[] spTitles = {"SỐ TT","Ma San pham", "Ten San Pham","Don Vi", "So Luong Chuyen"};
			for(int z = 0; z < spTitles.length; z++) {
				cell = new PdfPCell(new Paragraph(spTitles[z], font11_bold));
				cell.setPadding(3.0f);
				cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				tbSanPham.addCell(cell);
			}
			// thuc hien load du lieu
			
			query=" select sp.MA,sp.TEN as tensp,dv.TEN as tendv,cksp.SOLUONG "
					+ "from ERP_SANPHAM sp left join ERP_DONVI dv on sp.DONVI_FK=dv.PK_SEQ "
					+ "left join CHUYENKHO_SANPHAM cksp on cksp.SANPHAM_FK=sp.PK_SEQ "
					+ "where cksp.CHUYENKHO_FK="+mact+"";
			ResultSet rs=db.get(query); 
			int sott=1;
			while(rs.next())
			{
				String[] spdetail={""+sott,rs.getString("MA"),rs.getString("tensp"),rs.getString("tendv"),formatter.format(rs.getFloat("SOLUONG"))};
				
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
		
			document.add(tbSanPham);
		
			document.close(); 
		}
		catch(Exception e)
		{}
	}
	//xuat excel
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
			com.aspose.cells.Font font = new com.aspose.cells.Font();
			font.setName("Arial");
			font.setSize(10);
			
			// them noi dung tieu de file excel

			cell = cells.getCell("B2");
			cell.setValue("CONG TY GIAI PHAP DOANH NGHIEP TOAN CAU ");
			style = cell.getStyle();
			style.setColor(Color.GREEN);
			style.setFont(font);
			
			cell = cells.getCell("B3");
			cell.setValue("234 Nguyen Trong Tuyen, Phu Nhuan ,HCM");
			
			cell = cells.getCell("J5");
			cell.setValue("PHIEU CHUYEN KHO");
			
			cell = cells.getCell("G6");
			cell.setValue("MA CHUNG TU");
			cell = cells.getCell("G7");
			cell.setValue("TU KHO");
			
			cell = cells.getCell("G8");
			cell.setValue("Dia Chi");
			cell = cells.getCell("G9");
			cell.setValue("Ly Do Chuyen");
			
			cell = cells.getCell("L6");
			cell.setValue("ngay lap");
			
			cell = cells.getCell("L7");
			cell.setValue("Den Kho");
			cell = cells.getCell("L8");
			cell.setValue("dia chi giao");
			
			// them bang
			cell = cells.getCell("F10");
			cell.setValue("STT");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
	        
	        cell = cells.getCell("G10");
	        cell.setValue("MA SAN PHAM");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
	        
	        cell = cells.getCell("H10");
	        cell.setValue("TEN SAN PHAM");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
	       
	   
	        cell = cells.getCell("I10");
	        cell.setValue("DON VI");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
	        
	        cell = cells.getCell("J10");
	        cell.setValue("SO LUONG CHUYEN");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
	        
	        // do du lieu vao cac fill
	    	String query;
			query="select ck.PK_SEQ,ck.NGAYCHUYENKHO,khonhan.TENKHO as khonhan,khochuyen.TENKHO as khochuyen,"
					+ "khonhan.DIACHI as dckhonhan,khochuyen.DIACHI as dckhochuyen,ck.LYDO "
					+ "from CHUYENKHO ck left join KHO khonhan on ck.KHONHAN=khonhan.PK_SEQ "
					+ "left join KHO khochuyen on khochuyen.PK_SEQ=ck.KHOCHUYEN where ck.PK_SEQ="+id+"";
			ResultSet rsnd=db.get(query); 
			
			String mact="";
			String ngay="";
			String tukho="";
			String denkho="";
			String dcnhan="";
			String dcgiao="";
			String lydo="";
			
			while(rsnd.next())
			{
				 mact=rsnd.getString("PK_SEQ");
				 ngay=rsnd.getString("NGAYCHUYENKHO");
				 tukho=rsnd.getString("khochuyen");
				 denkho=rsnd.getString("khonhan");
				 dcnhan=rsnd.getString("dckhonhan");
				 dcgiao=rsnd.getString("dckhochuyen");
				 lydo=rsnd.getString("LYDO");
				
			}
			cell = cells.getCell("H6");
			cell.setValue(mact);
			cell = cells.getCell("H7");
			cell.setValue(tukho);
			
			cell = cells.getCell("H8");
			cell.setValue(dcnhan);
			cell = cells.getCell("H9");
			cell.setValue(lydo);
			
			cell = cells.getCell("M6");
			cell.setValue(ngay);
			
			cell = cells.getCell("M7");
			cell.setValue(denkho);
			cell = cells.getCell("M8");
			cell.setValue(dcgiao);
	        
	        int stt=1;
	        
	      
	        query=" select sp.MA,sp.TEN as tensp,dv.TEN as tendv,cksp.SOLUONG "
					+ "from ERP_SANPHAM sp left join ERP_DONVI dv on sp.DONVI_FK=dv.PK_SEQ "
					+ "left join CHUYENKHO_SANPHAM cksp on cksp.SANPHAM_FK=sp.PK_SEQ "
					+ "where cksp.CHUYENKHO_FK="+mact+"";
	        ResultSet rs=db.get(query);
	        if(rs !=null)
	        {
	        	while(rs.next())
	        	{
	        		String[]spTitle ={"F","G","H","I","J"};
	        		   String[] spTitles2 = { "" + stt,
	        		     rs.getString("MA"),rs.getString("tensp"),
	        		     rs.getString("tendv"),
	        		     formatter.format(rs.getDouble("SOLUONG")) };
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
	        cell = cells.getCell("G16");
			cell.setValue("Truong Phong Cung Ung");
			
			cell = cells.getCell("L16");
			cell.setValue("Nguoi Nhan");
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
