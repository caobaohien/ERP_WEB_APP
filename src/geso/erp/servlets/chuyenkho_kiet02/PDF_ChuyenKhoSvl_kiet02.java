package geso.erp.servlets.chuyenkho_kiet02;

import geso.dms.center.util.Utility;
import geso.erp.db.sql.dbutils;

import java.io.IOException;
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

@WebServlet("/PDF_ChuyenKhoSvl_kiet02")
public class PDF_ChuyenKhoSvl_kiet02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PDF_ChuyenKhoSvl_kiet02() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		Utility util = new Utility();
		String action = request.getParameter("action");
		if (action == null) {
			action = "";
		}
		System.out.println("Action : " + action);

		String ma = util.antiSQLInspection(request.getParameter("ma"));
		if (ma == null) {
			ma = "";
		}
		
		Create_PO_PDF(response, request);
	}

	private void Create_PO_PDF(HttpServletResponse response,
			HttpServletRequest request) {
		
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition"," inline; filename=ChuyenKho_kiet02.pdf");
		
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

	private void CreatePO_Training(Document document,
			ServletOutputStream outstream, HttpServletResponse response,
			HttpServletRequest request, dbutils db) {
		
		HttpSession session = request.getSession();
		NumberFormat formatter_2sole = new DecimalFormat("#,###,###.##");
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
			
			PdfPCell cell = new PdfPCell(new Paragraph("CÔNG TY GIẢI PHÁP DOANH NGHIỆP TOÀN CẦU", font11_bold));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			tbl_header.addCell(cell);
			
			cell = new PdfPCell(new Paragraph("Address: 234, Nguyễn Trọng Tuyển, Phú Nhuận", font11_bold));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			tbl_header.addCell(cell);
			
			cell = new PdfPCell(new Paragraph("Number Phone: 0962 9999 19", font11_bold));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			tbl_header.addCell(cell);
			document.add(tbl_header);
			//------------------------------------------------------------
			
			String tungay = request.getParameter("tungay");
			if(tungay == null)
				tungay = "";
			 
			
			String denngay = request.getParameter("denngay");
			if(denngay == null)
				denngay = "";
			
			Paragraph pxk = new Paragraph("PHIẾU CHUYỂN KHO", new Font(bf, 14, Font.BOLD));
			pxk.setSpacingBefore(3);
			pxk.setSpacingAfter(3);
			pxk.setAlignment(Element.ALIGN_CENTER);
			 
			document.add(pxk);
			
			
			pxk = new Paragraph("Từ ngày: "+tungay +". Đến ngày:"+denngay, new Font(bf, 12, Font.BOLD));
			
			pxk.setSpacingAfter(15);
			pxk.setAlignment(Element.ALIGN_CENTER);
			document.add(pxk);
			
			//--------------------------------------------------------------------------------
			float[] crSanpham = {1.0f * CONVERT,2.0f * CONVERT, 2.9f * CONVERT, 1.8f * CONVERT, 2.0f * CONVERT,
					2.0f * CONVERT, 2.0f * CONVERT, 2.0f * CONVERT, 2.0f * CONVERT, 2.0f * CONVERT}; 
			 
			PdfPTable tbSanPham = new PdfPTable(crSanpham.length);
			tbSanPham.setWidthPercentage(100);
			tbSanPham.setHorizontalAlignment(Element.ALIGN_LEFT);			
			tbSanPham.setWidths(crSanpham);
			tbSanPham.getDefaultCell().setBorder(0);
			tbSanPham.setSpacingAfter(8.0f);
			
			String[] spTitles = { "STT", "Mã CK", "Kho Chuyển", "Kho Nhận",
					"Lý Do", "Người Tạo", "Ngày Tạo", "Người Sửa", "Ngày Sửa", "Trạng Thái" };

			for (int z = 0; z < spTitles.length; z++) {
				cell = new PdfPCell(new Paragraph(spTitles[z], font11_bold));
				cell.setPadding(3.0f);
				cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				tbSanPham.addCell(cell);
			}
			String query = "";
			query = " SELECT ISNULL(ck.TRANGTHAI,'') AS trangthai,ck.PK_SEQ as ma, "
					+ " k.tenkho as khonhan,k1.tenkho as khochuyen,ck.NGAYTAO as ngaytao ,ck.NGAYSUA as ngaysua, "
					+ " NV.TEN as nguoitao,NV1.TEN as nguoisua,ck.lydo as lydo "
					+ " FROM chuyenkho ck LEFT JOIN KHO k ON CK.KHONHAN=K.PK_SEQ LEFT JOIN KHO k1 ON CK.KHOchuyen=K1.PK_SEQ"
					+ " LEFT JOIN NHANVIEN NV ON CK.NGUOITAO=NV.PK_SEQ LEFT JOIN NHANVIEN NV1 ON CK.NGUOISUA=NV1.PK_SEQ ";
			
			ResultSet rs = db.get(query);
			int sott = 1;
			while (rs.next()){
				String[] spTitles2 = {sott+"", rs.getString("ma"), rs.getString("khochuyen"), rs.getString("khonhan"),
						rs.getString("lydo"), rs.getString("nguoitao"), rs.getString("ngaytao"),
						rs.getString("nguoisua"), rs.getString("ngaysua"), rs.getString("trangthai")};
				
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
			cell = new PdfPCell(new Paragraph("You'll never walk alone!", font11_bold));
			cell.setColspan(9);
			cell.setPadding(3.0f);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tbSanPham.addCell(cell);
			
			cell = new PdfPCell(new Paragraph("Liverpool",
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
