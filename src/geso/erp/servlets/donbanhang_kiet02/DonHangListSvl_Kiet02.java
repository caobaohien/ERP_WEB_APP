package geso.erp.servlets.donbanhang_kiet02;

import geso.dms.center.util.Utility;
import geso.erp.beans.donbanhang_kiet02.IDonHang;
import geso.erp.beans.donbanhang_kiet02.IDonHangList;
import geso.erp.beans.donbanhang_kiet02.imp.DonHang;
import geso.erp.beans.donbanhang_kiet02.imp.DonHangList;
import geso.erp.beans.donbanhang_vy.IDonHang_Vy;
import geso.erp.beans.donbanhang_vy.imp.DonHang_Vy;
import geso.erp.db.sql.dbutils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;

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
//import com.aspose.cells.Font;
import com.aspose.cells.Style;
import com.aspose.cells.TextAlignmentType;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@WebServlet("/DonHangListSvl_Kiet02")
public class DonHangListSvl_Kiet02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DonHangListSvl_Kiet02() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession httpSession = request.getSession();
		Utility utility = new Utility();
		String queryString = request.getQueryString();
		
		String userId = utility.getUserId(queryString);
	    if (userId.length()==0) {
	    	userId = utility.antiSQLInspection(request.getParameter("userId"));
	    }
	    
	    IDonHangList iDonHangList= new DonHangList();
	    iDonHangList.init("");
	    String maDonHang = utility.getId(queryString);
		String action = utility.getAction(queryString);
		
		System.out.println("Ma DH: "+ maDonHang);
		System.out.println("action: " +action);

	    IDonHang iDonHang= new DonHang();
	    iDonHang.setMaDonHang(maDonHang);
	    
	    if (action.equals("delete")) {
			iDonHang.deleteDonHang();
		/*	iDonHangList_Vy.init("");*/
		} else if (action.equals("chot")) {
			iDonHang.chotDonHang();
		}
		iDonHangList.init("");
		
		httpSession.setAttribute("obj", iDonHangList);
	    String nextJSP = "/TrainingGESO/pages/Erp/DonHangList_kiet03.jsp";
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
		
		String action = request.getParameter("action");

		String maDonHang = request.getParameter("maDonHang");
		String tenKhachHang = request.getParameter("khachHang");
		String trangThai = request.getParameter("trangThai");

		action = (action == null) ? "" : action;
		maDonHang = (maDonHang == null) ? "" : maDonHang;
		tenKhachHang = (tenKhachHang == null) ? "" : tenKhachHang;
		trangThai = (trangThai == null) ? "" : trangThai;

		HttpSession session = request.getSession();
		IDonHangList donHangList = new DonHangList();
		donHangList.setMaDonHang(maDonHang);
		donHangList.setMaKhachHang(tenKhachHang);
		donHangList.setTrangThai(trangThai);

		System.out.println(action);
		System.out.println(donHangList.getTrangThai());
		
		
		if (action.equals("timkiem")) {
			donHangList.setNxtApprSplitting(Integer.parseInt(request
					.getParameter("nxtApprSplitting")));

			String searchQuery = getSearchQuery(request, donHangList);
			donHangList.init(searchQuery);

			session.setAttribute("obj", donHangList);
			response.sendRedirect("/TrainingGESO/pages/Erp/DonHangList_kiet03.jsp");
		} else if (action.equals("taomoi")) {
			IDonHang iDonHang = new DonHang();
			iDonHang.CreateRsKH();
			session.setAttribute("donHangMoi", iDonHang);
			String nextJSP = "/TrainingGESO/pages/Erp/DonHangNew_kiet03.jsp";
			response.sendRedirect(nextJSP);
		} /*else if (action.equals("print")){
			Create_PO_PDF(response, request);
		} else if (action.equals("excel")){
			//Create_EXCEL(request, response);
		}*/
	}

	/*private void Create_EXCEL(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();
	    Utility util = new Utility();
	    
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
		
	    //lấy action được truyền vào
	    String action = request.getParameter("action");
	    if (action == null){
	    	action = "";
	    }
	    String nextJSP = "/TrainingGESO/pages/Erp/DonHangList_kiet03.jsp";
	    IDonHangList obj = new DonHangList();
	    obj.init("");
	    obj.CreateRsKH();
	    
	    OutputStream out = response.getOutputStream();
	    session.setAttribute("obj", obj);
	    

		if (action.equals("excel")) {

			response.setContentType("application/xlsx");
			response.setHeader("Content-Disposition",
					"attachment; filename=TESTCHUYENKHO.xlsx");
			if (!CreateExcel(out, request, response)) {
				response.setContentType("text/html");
				PrintWriter writer = new PrintWriter(out);
				writer.print("Lỗi xuất excel");
				writer.close();
			}
		} else {
			// tuỳ theo các action khác mà sử dụng
			session.setAttribute("obj", obj);
			response.sendRedirect(nextJSP);
			return;
		}
		session.setAttribute("obj", obj);
		response.sendRedirect(nextJSP);
		return;
	}

	private boolean CreateExcel(OutputStream out, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		boolean isFillData = false;
		FileInputStream fstream = null;
		Workbook workbook = new Workbook();
		
		fstream = new FileInputStream(getServletContext().getInitParameter("path_kiet") + "\\DonHang_kiet02.xlsx");
		
		workbook.open(fstream);
		workbook.setFileFormatType(FileFormatType.EXCEL2007);
		
		isFillData = FillData(workbook);
   
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

	private boolean FillData(Workbook workbook) {
		
		dbutils db = new dbutils();
		try {
			Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);
			
			Cells cells = worksheet.getCells();
			Cell cell = null;
			Style style = null;
			
			Font font = new Font();
			font.setName("Arial");
			font.setSize(10);
			
			cell = cells.getCell("B2");
			cell.setValue("CÔNG TY GIẢI PHÁP DOANH NGHIỆP TOÀN CẦU GESO 123 ");
			style = cell.getStyle();
			style.setColor(Color.TEAL);
			style.setFont(font);
			
			cell = cells.getCell("B3");
			cell.setValue("Địa chỉ:");
			
			cell = cells.getCell("B4");
			cell.setValue("Số điện thoại:");
			
			cell = cells.getCell("D6");
			cell.setValue("ĐƠN HÀNG LIVERPOOL FC");
			//----------------------------------
			// set borderline
			cell = cells.getCell("B9");
			cell.setValue("Mã ĐH");
			font.setSize(11);
			font.setBold(true);
			style.setFont(font);
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        style.setHAlignment(TextAlignmentType.CENTER);
	        cell.setStyle(style);
	        
	        cell = cells.getCell("C9");
			cell.setValue("Ngày CT");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
	        
	        cell = cells.getCell("D9");
			cell.setValue("Khách Hàng");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
	        
	        cell = cells.getCell("E9");
			cell.setValue("Trạng Thái");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
	        
	        cell = cells.getCell("F9");
			cell.setValue("Tổng Tiền");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
	        
	        cell = cells.getCell("G9");
			cell.setValue("Ngày Tạo");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
	        
	        cell = cells.getCell("H9");
			cell.setValue("Người Tạo");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
			
	     // lấy dữ liệu và lặp rồi insert vào các cell tương ứng
			String query = "";
			query = "SELECT BH.PK_SEQ AS MADONHANG, "
					+ "BH.NGAYCHUNGTU AS NGAYCHUNGTU, "
					+ "KH.TEN AS TENKHACHHANG, "
					+ "ISNULL(BH.TRANGTHAI, '') AS TRANGTHAI, "
					+ "BH.TONGTIEN, " + "BH.NGAYTAO, " +

					"NV.TEN AS TENNGUOITAO " + "FROM BANHANG BH "
					+ "LEFT JOIN KHACHHANG KH ON BH.KHACHHANG_FK = KH.PK_SEQ "
					+ "LEFT JOIN NHANVIEN NV ON BH.NGUOITAO = NV.PK_SEQ "
					+ "ORDER BY MADONHANG ASC";
			 
			ResultSet rs=db.get(query); 
			
			int sott = 1;
			while (rs.next()){
				String[] spTitles = {"B", "C", "D", "E", "F", "G", "H"};
				String[] spTitles2 = {rs.getString("MADONHANG"), rs.getString("NGAYCHUNGTU"),
						rs.getString("TENKHACHHANG"), rs.getString("TRANGTHAI"),
						rs.getString("TONGTIEN"), rs.getString("NGAYTAO"),
						rs.getString("TENNGUOITAO")};
				
				for(int z = 0; z < spTitles2.length; z++) {
					cell = cells.getCell(spTitles[z]+Integer.toString(9+sott));
					cell.setValue(spTitles2[z]);
					
					font.setSize(11);
					font.setBold(false);
					style.setFont(font);
					style.setColor(Color.WHITE);
					style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
			        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
			        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
			        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
			        cell.setStyle(style);
				}
				sott++;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally{
			db.shutDown();
		}
	}*/

	protected String getSearchQuery(HttpServletRequest request, IDonHangList obj) {
		Utility utility = new Utility();
		
		String query = "SELECT BH.PK_SEQ AS MADONHANG, " +
						"BH.NGAYCHUNGTU AS NGAYCHUNGTU, " +
						"BH.KHACHHANG_FK AS MAKHACHHANG, " +
						"KH.TEN AS TENKHACHHANG, " +
						"ISNULL(BH.TRANGTHAI, '') AS TRANGTHAI, " +
						"BH.TONGTIEN, " +
						"BH.NGAYTAO, " +
						"BH.NGUOITAO AS MANGUOITAO, " +
						"NV.TEN AS TENNGUOITAO " +
						"FROM BANHANG BH " +
						"LEFT JOIN KHACHHANG KH ON BH.KHACHHANG_FK = KH.PK_SEQ " +
						"LEFT JOIN NHANVIEN NV ON BH.NGUOITAO = NV.PK_SEQ " +
						"WHERE 1=1";
		
		if (obj.getMaDonHang().length() > 0) {
			query += " AND BH.PK_SEQ LIKE '%" + obj.getMaDonHang() + "%'";
		}
		if (obj.getMaKhachHang().length() > 0) {
			System.out.println(obj.getMaKhachHang());
			query += " AND KH.PK_SEQ LIKE '%" + obj.getMaKhachHang() + "%'";
		}
		if (obj.getTrangThai().length() > 0) {
			query += " AND BH.TRANGTHAI = " + obj.getTrangThai();
		}
		return query;
	}
	
	private void Create_PO_PDF(HttpServletResponse response, HttpServletRequest request){
		
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition"," inline; filename=DonHang_kiet02.pdf");
		
		float CONVERT = 28.346457f;//1 cm 
		float PAGE_LEFT = 2.0f*CONVERT, PAGE_RIGHT = 1.5f*CONVERT, PAGE_TOP = 0.5f*CONVERT, PAGE_BOTTOM = 0.0f*CONVERT; //cm
		Document document = new Document(PageSize.A4, PAGE_LEFT, PAGE_RIGHT, PAGE_TOP, PAGE_BOTTOM);
		ServletOutputStream outstream;
		
		try 
		{
			 outstream = response.getOutputStream();
			 dbutils db = new dbutils();
			 
			this.CreatePO_Training(document, outstream, response, request, db);
			db.shutDown();
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			System.out.println("___Exception PO Print: " + e.getMessage());
		}
	}
	
	private void CreatePO_Training(Document document, ServletOutputStream outstream, HttpServletResponse response, HttpServletRequest request, dbutils db){
		
		HttpSession session = request.getSession();
		NumberFormat formatter_2sole = new DecimalFormat("#,###,###.###");
		NumberFormat formatter = new DecimalFormat("#,###,###");
		
		try {
			PdfWriter.getInstance(document, outstream);
			document.open();
			//document.setPageSize(new Rectangle(210.0f, 297.0f));

			float CONVERT = 28.346457f; // = 1cm
			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			 // khỏi tạo font chữ 
			Font font10 = new Font(bf, 10, Font.NORMAL);
			 
			Font font11_bold= new Font(bf, 11, Font.BOLD);
			
			
			PdfPTable tbl_header = new PdfPTable(1);
			
			tbl_header.setWidthPercentage(100);
			tbl_header.setHorizontalAlignment(Element.ALIGN_LEFT);
			float[] crtbl_header = {7f};
			tbl_header.setWidths(crtbl_header);
			
			PdfPCell cell = new PdfPCell(new Paragraph("CÔNG TY GIẢI PHÁP TOÀN CẦU GESO ", font11_bold));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			tbl_header.addCell(cell);
			
			cell = new PdfPCell(new Paragraph("Địa chỉ:  ", font11_bold));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			tbl_header.addCell(cell);
			cell = new PdfPCell(new Paragraph("Điện thoại:  ", font11_bold));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			tbl_header.addCell(cell);
			//------------------------------------------------------------
			document.add(tbl_header);

			String tungay = request.getParameter("tungay");
			if(tungay == null)
				tungay = "";
			 
			
			String denngay = request.getParameter("denngay");
			if(denngay == null)
				denngay = "";
			
			Paragraph pxk = new Paragraph("BÁO CÁO ĐƠN BÁN HÀNG KIỆT TRONG KỲ", new Font(bf, 14, Font.BOLD));
			
			pxk.setSpacingAfter(2);
			pxk.setAlignment(Element.ALIGN_CENTER);
			 
			document.add(pxk);
			
			
			pxk = new Paragraph("Từ ngày: "+tungay +" .Đến ngày:"+denngay, new Font(bf, 12, Font.BOLD));
			
			pxk.setSpacingAfter(2);
			pxk.setAlignment(Element.ALIGN_CENTER);
			document.add(pxk);
			//--------------------------------------------------------------------------------
			float[] crSanpham = {0.7f * CONVERT, 2.0f * CONVERT,2.0f * CONVERT, 2.9f * CONVERT, 1.8f * CONVERT, 2.0f * CONVERT }; 
			 
			PdfPTable tbSanPham = new PdfPTable(crSanpham.length);
			tbSanPham.setWidthPercentage(100);
			tbSanPham.setHorizontalAlignment(Element.ALIGN_LEFT);			
			tbSanPham.setWidths(crSanpham);
			tbSanPham.getDefaultCell().setBorder(0);
			tbSanPham.setSpacingAfter(8.0f);
			

			String[] spTitles = { "SỐ TT", "SỐ CT", "MÃ KH", "TÊN KH",
					"NGÀY ĐƠN HÀNG", "Thành tiền" };

			for (int z = 0; z < spTitles.length; z++) {
				cell = new PdfPCell(new Paragraph(spTitles[z], font11_bold));
				cell.setPadding(3.0f);
				cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				tbSanPham.addCell(cell);
			}
			
			String query = "";

			query = " SELECT DH.PK_SEQ AS SOCHUNGTU,DH.NGAYCHUNGTU,DH.TRANGTHAI,DH.NGAYTAO, "
					+ " TONGTIENtruocVAT as tongtien, KH.MA, ISNULL(KH.TEN,'') AS TEN,NV.TEN AS NGUOITAO "
					+ " FROM DONHANG DH "
					+ " LEFT JOIN KHACHHANG KH ON KH.PK_SEQ=DH.KHACHHANG_FK "
					+ " LEFT JOIN NHANVIEN NV ON NV.PK_SEQ=DH.NGUOITAO where 1=1 ";

			if (tungay.length() > 0)
				query += " and dh.ngaychungtu >= '" + tungay + "'";

			if (denngay.length() > 0)
				query += " and dh.ngaychungtu <= '" + denngay + "'";
			
			ResultSet rs = db.get(query);

			int sott = 1;
			double tongtien = 0;
			while (rs.next()) {

				String[] spTitles2 = { "" + sott, rs.getString("SOCHUNGTU"),
						rs.getString("MA"), rs.getString("TEN"),
						rs.getString("NGAYCHUNGTU"),
						formatter.format(rs.getDouble("tongtien")) };

				tongtien = tongtien + Math.round(rs.getDouble("tongtien"));

				for (int z = 0; z < spTitles2.length; z++) {
					cell = new PdfPCell(new Paragraph(spTitles2[z], font10));
					cell.setPadding(3.0f);
					if (z == 4 || z == 5 || z == 7) {
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					} else {
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					}
					tbSanPham.addCell(cell);
				}
				sott++;
			}
			// --------------------------------------------------------------
			cell = new PdfPCell(new Paragraph("Tổng cộng", font11_bold));
			cell.setColspan(5);
			cell.setPadding(3.0f);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tbSanPham.addCell(cell);

			cell = new PdfPCell(new Paragraph(formatter.format(tongtien),
					font11_bold));
			cell.setPadding(3.0f);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tbSanPham.addCell(cell);

			document.add(tbSanPham);
			document.close(); 
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception In PDF: " + e.getMessage());
		}
	}
}
