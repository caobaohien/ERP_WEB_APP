package geso.erp.servlets.chuyenkho_kiet02;

import geso.dms.center.util.Utility;
import geso.erp.beans.chuyenkho_kiet02.IChuyenKhoNew;
import geso.erp.beans.chuyenkho_kiet02.imp.ChuyenKhoNew;
import geso.erp.db.sql.dbutils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

@WebServlet("/PrintPDF_PhieuCK_kiet02")
public class PrintPDF_PhieuCK_kiet02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PrintPDF_PhieuCK_kiet02() {
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
		//------------------------------
		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		
		Create_PO_PDF(response, request);
	}

	private void Create_PO_PDF(HttpServletResponse response,
			HttpServletRequest request) throws UnsupportedEncodingException {
		
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition"," inline; filename=PrintChuyenKho_kiet02.pdf");
		
		float CONVERT = 28.346457f;// 1 cm
		float PAGE_LEFT = 2.0f * CONVERT, PAGE_RIGHT = 1.5f * CONVERT,
				PAGE_TOP = 0.5f * CONVERT, PAGE_BOTTOM = 0.0f * CONVERT;
		Document document = new Document(PageSize.A4, PAGE_LEFT, PAGE_RIGHT,
				PAGE_TOP, PAGE_BOTTOM);
		ServletOutputStream outstream;
		
		try {
			outstream = response.getOutputStream();
			dbutils db = new dbutils();
			CreatePO_Training(document, outstream, response, request, db);
			db.shutDown();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception PO Print: " + e.getMessage());
		}
		
	}
	
	private void CreatePO_Training(Document document,
			ServletOutputStream outstream, HttpServletResponse response,
			HttpServletRequest request, dbutils db) throws UnsupportedEncodingException {
		
		HttpSession session = request.getSession();
		Utility util = new Utility();
		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		String action = util.getAction(querystring);
		if (userId.length() == 0)
			userId = util.antiSQLInspection(request.getParameter("userId"));

		String id = request.getParameter("pdf");
		
		
		//----------------------------------------------
		
		NumberFormat formatter_2sole = new DecimalFormat("#,###,###.##");
		NumberFormat formatter = new DecimalFormat("#,###,###");
		
		try {
			PdfWriter.getInstance(document, outstream);
			document.open();
			
			float CONVERT = 28.346457f; // = 1cm
			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			
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
			
			cell = new PdfPCell(new Paragraph("234, Nguyễn Trọng Tuyển, Phú Nhuận", font11_bold));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			tbl_header.addCell(cell);
			document.add(tbl_header);
			
			Paragraph pxk = new Paragraph("PHIẾU CHUYỂN KHO", new Font(bf, 14, Font.BOLD));
			pxk.setSpacingBefore(3);
			pxk.setSpacingAfter(10);
			pxk.setAlignment(Element.ALIGN_CENTER);
			
			document.add(pxk);
			
			String query = "";
			query = "select CK.PK_SEQ as MaCT, CK.NGAYTAO as NgayLap, K.TENKHO as KhoNhan, K1.TENKHO as KhoChuyen, "
				+ " K.DIACHI as DCNhanHang, K.DIACHI as DCGiaoHang, CK.LYDO as LyDo "
				+ " from CHUYENKHO CK left join KHO K on K.PK_SEQ= CK.KHONHAN "
				+ " left join KHO K1 on K1.PK_SEQ= CK.KHOCHUYEN where CK.PK_SEQ= "+ id;
			
			String MaCT = "", NgayLap = "", KhoNhan = "", KhoChuyen = "", DCNhanHang = "",
					DCGiaoHang = "", LyDo = "";
			ResultSet resSet= db.get(query);
			while (resSet.next()){
				String[] data = {
						MaCT = resSet.getString("MaCT"),
						NgayLap = resSet.getString("NgayLap"),
						KhoNhan = resSet.getString("KhoNhan"),
						KhoChuyen = resSet.getString("KhoChuyen"),
						DCNhanHang = resSet.getString("DCNhanHang"),
						DCGiaoHang = resSet.getString("DCGiaoHang"),
						LyDo = resSet.getString("LyDo") };
			
			pxk = new Paragraph("Mã chứng từ: "+ MaCT+ "                                               "+ "Ngày lập: "+ NgayLap, new Font(bf, 12, Font.BOLD));
			pxk.setSpacingAfter(5);
			pxk.setAlignment(Element.ALIGN_LEFT);
			document.add(pxk);
			
			pxk = new Paragraph("Từ kho: "+ KhoChuyen+ "                                                         "+ "Đến kho: "+ KhoNhan, new Font(bf, 12, Font.BOLD));
			pxk.setSpacingAfter(5);
			pxk.setAlignment(Element.ALIGN_LEFT);
			document.add(pxk);
			
			pxk = new Paragraph("ĐC nhận hàng: "+ DCNhanHang+ "                                   "+ "ĐC giao hàng: "+ DCGiaoHang, new Font(bf, 12, Font.BOLD));
			pxk.setSpacingAfter(5);
			pxk.setAlignment(Element.ALIGN_LEFT);
			document.add(pxk);
			
			pxk = new Paragraph("Lý do chuyển: "+ LyDo, new Font(bf, 12, Font.BOLD));
			pxk.setSpacingAfter(15);
			pxk.setAlignment(Element.ALIGN_LEFT);
			document.add(pxk);
			//--------------------------------------------------------------------------------
			float[] crSanpham1 = {1.0f * CONVERT, 2.0f * CONVERT, 2.0f * CONVERT, 2.0f * CONVERT, 2.0f * CONVERT}; 
			 
			PdfPTable tbSanPham1 = new PdfPTable(crSanpham1.length);
			tbSanPham1.setWidthPercentage(100);
			tbSanPham1.setHorizontalAlignment(Element.ALIGN_LEFT);			
			tbSanPham1.setWidths(crSanpham1);
			tbSanPham1.getDefaultCell().setBorder(0);
			tbSanPham1.setSpacingAfter(8.0f);
			
			String[] spTitles = {"STT", "Mã SP", "Tên SP", "Đơn vị", "SL Chuyển"};
			for (int z = 0; z < spTitles.length; z++) {
				cell = new PdfPCell(new Paragraph(spTitles[z], font11_bold));
				cell.setPadding(3.0f);
				cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				tbSanPham1.addCell(cell);
			}
			query= "";
			query = "select SP.MA as MaSP, SP.TEN as TenSP, DV.MA as DonVi, CK_SP.SOLUONG as SoLuong "
				+ " from ERP_SANPHAM SP left join DONVI DV on DV.PK_SEQ = SP.DONVI_FK "
				+ " left join CHUYENKHO_SANPHAM CK_SP on CK_SP.SANPHAM_FK = SP.PK_SEQ "
				+ " where CK_SP.CHUYENKHO_FK= "+ id;
			
			ResultSet rs = db.get(query);
			int sott = 1;
			while (rs.next()){
				String[] spTitles2 = {sott+"", rs.getString("MaSP"), rs.getString("TenSP"),
						rs.getString("DonVi"),
						rs.getString("SoLuong")};
				
				for (int z = 0; z < spTitles2.length; z++) {
					cell = new PdfPCell(new Paragraph(spTitles2[z], font10));
					cell.setPadding(3.0f);
					if (z == 4 || z == 5 || z == 7) {
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					} else {
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					}
					tbSanPham1.addCell(cell);
				}
				sott++;
			}
			
			document.add(tbSanPham1);
			pxk = new Paragraph("Trưởng phòng cung ứng"+ "                                                  "+ "Người nhận", new Font(bf, 12, Font.BOLD));
			pxk.setSpacingAfter(15);
			pxk.setAlignment(Element.ALIGN_LEFT);
			document.add(pxk);
			document.close(); 
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println("Exception print PDF: "+ e.getMessage());
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
