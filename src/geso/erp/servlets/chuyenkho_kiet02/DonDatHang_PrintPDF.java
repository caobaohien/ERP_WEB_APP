package geso.erp.servlets.chuyenkho_kiet02;

import geso.dms.center.util.Utility;
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
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@WebServlet("/DonDatHang_PrintPDF")
public class DonDatHang_PrintPDF extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DonDatHang_PrintPDF() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		Utility util = new Utility();
		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		
		String action = request.getParameter("action");
		if (action == null) {
			action = "";
		}
		System.out.println("Action = " + action);
		Create_PO_PDF(response, request);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	private void Create_PO_PDF(HttpServletResponse response,
			HttpServletRequest request) throws UnsupportedEncodingException {
		
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition"," inline; filename=Traphaco_DonDatHang.pdf");
		
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
		//___________________________________________
		
		NumberFormat formatter_2sole = new DecimalFormat("#,###,###.##");
		NumberFormat formatter = new DecimalFormat("#,###,###");
		
		try {
			PdfWriter.getInstance(document, outstream);
			document.open();
			
			float CONVERT = 28.346457f; // = 1cm
			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf",
					BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			
			Font font10 = new Font(bf, 10, Font.NORMAL);
			Font font10_bold = new Font(bf, 10, Font.BOLD);
			Font font11_bold= new Font(bf, 11, Font.BOLD);
			Font font11_italic = new Font(bf, 11, Font.ITALIC);
			
			PdfPTable tab_Header = new PdfPTable(1);
			tab_Header.setWidthPercentage(100);
			tab_Header.setHorizontalAlignment(Element.ALIGN_RIGHT);
			float[] crtbl_header = {7f};
			tab_Header.setWidths(crtbl_header);
			
			//Header 1
			PdfPCell cell = new PdfPCell(new Paragraph("BM29/09", font11_italic));
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setBorder(0);
			tab_Header.addCell(cell);
			
			String date = "17/06/2016";
			cell = new PdfPCell(new Paragraph("BH/SĐ: " + date, font11_italic));
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setBorder(0);
			tab_Header.addCell(cell);
			document.add(tab_Header);
			//Header 2
			
			PdfPTable table = new PdfPTable(2);
			table.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table.setWidths(new int[]{ 4, 4});
	        table.setSpacingAfter(5.0f);
	        PdfPCell cell2;
	        cell2 = new PdfPCell(new Phrase("", font11_bold));
	        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell2.setColspan(6);
	        cell2.setPadding(1.0f);
	        cell2.setBorder(0);
	        table.addCell(cell2);
	        
	        //insert logo traphaco
	        Image img = Image.getInstance("logo.png");
	        img.scalePercent(10);
	        
	        cell2 = new PdfPCell();
	        cell2.addElement(new Chunk(img, 5, -5));
	        table.addCell(cell2);
	        
	        /*cell2 = new PdfPCell(new Phrase("TRAPHACO", font10_bold));
	        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell2.setBorder(0);
	        table.addCell(cell2);*/
	        
	        cell2 = new PdfPCell(new Phrase("CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM", font10_bold));
	        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell2.setBorder(0);
	        table.addCell(cell2);
	        
	        cell2 = new PdfPCell(new Phrase("CÔNG TY CP TRAPHACO", font10_bold));
	        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell2.setPadding(1.0f);
	        cell2.setBorder(0);
	        table.addCell(cell2);
	        
	        cell2 = new PdfPCell(new Phrase("Độc lập - Tự do - Hạnh phúc", font10));
	        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell2.setBorder(0);
	        table.addCell(cell2);
	        
	        cell2 = new PdfPCell(new Phrase("Số: ... ĐĐH/ TRAPHACO - ..../ 201...", font10));
	        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell2.setBorder(0);
	        table.addCell(cell2);
	        
	        cell2 = new PdfPCell(new Phrase("Hà Nội, Ngày        Tháng        Năm       ", font10));
	        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell2.setBorder(0);
	        table.addCell(cell2);
	        
	        document.add(table);
			
			//Header 3
			Paragraph donDH = new Paragraph("ĐƠN ĐẶT HÀNG", new Font(bf, 14, Font.BOLD));
			donDH.setSpacingBefore(10);
			donDH.setSpacingAfter(1);
			donDH.setAlignment(Element.ALIGN_CENTER);
			document.add(donDH);
			
			Paragraph nam = new Paragraph("Năm ....", new Font(bf, 11, Font.BOLD));
			nam.setSpacingBefore(0);
			nam.setSpacingAfter(10);
			nam.setAlignment(Element.ALIGN_CENTER);
			document.add(nam);
			
			String DiaChi = "37 Nguyễn Bỉnh Khiêm", DienThoai = "0987654321", Fax = "0909 777", MaSoThue = "MST001-002";
			Paragraph para = new Paragraph("Kính gửi:", new Font(bf, 11, Font.BOLD));
			para.setSpacingBefore(10);
			para.setSpacingAfter(3);
			para.setAlignment(Element.ALIGN_LEFT);
			document.add(para);
			
			Paragraph para1 = new Paragraph("Đơn vị đặt hàng:       CÔNG TY CỔ PHẦN TRAPHACO", new Font(bf, 11, Font.BOLD));
			para1.setSpacingBefore(3);
			para1.setSpacingAfter(3);
			para1.setAlignment(Element.ALIGN_LEFT);
			document.add(para1);
			
			Paragraph para2 = new Paragraph("Địa chỉ:                  " + DiaChi, new Font(bf, 12, Font.NORMAL));
			para2.setSpacingBefore(3);
			para2.setSpacingAfter(3);
			para2.setAlignment(Element.ALIGN_LEFT);
			document.add(para2);
			
			Paragraph para3 = new Paragraph("Số điện thoại:         " + DienThoai + "                                   Fax: " + Fax, new Font(bf, 12, Font.NORMAL));
			para3.setSpacingBefore(3);
			para3.setSpacingAfter(3);
			para3.setAlignment(Element.ALIGN_LEFT);
			document.add(para3);
			
			Paragraph para4 = new Paragraph("Mã số thuế:            " + MaSoThue, new Font(bf, 12, Font.NORMAL));
			para4.setSpacingBefore(3);
			para4.setSpacingAfter(3);
			para4.setAlignment(Element.ALIGN_LEFT);
			document.add(para4);
			
			Paragraph para5 = new Paragraph("I. Nội dung đặt hàng: ", new Font(bf, 12, Font.BOLD));
			para5.setSpacingBefore(3);
			para5.setSpacingAfter(10);
			para5.setAlignment(Element.ALIGN_LEFT);
			document.add(para5);
			
			//Cau query lay data do vao table
			String query = "";
			query = "";
			String TenHang = "", TCKT = "", SoLuong = "", DonGia = "", VAT = "",
			ThanhTien = "", ThoiGianGH = "", DiaDiemNH = "";
			/*while (rs.next()) {
				String[] data = {
						TenHang = rs.getString("TenHang"),
						TCKT = rs.getString("TCKT"),
						SoLuong = rs.getString("SoLuong"),
						DonGia = rs.getString("DonGia"),
						VAT = rs.getString("VAT"),
						ThanhTien = rs.getString("ThanhTien"),
						ThoiGianGH = rs.getString("ThoiGianGH"),
						DiaDiemNH = rs.getString("DiaDiemNH")
				};
			}*/
			//table
			float[] crDonHang = { 1.0f * CONVERT, 2.0f * CONVERT,
					1.0f * CONVERT, 1.0f * CONVERT, 1.0f * CONVERT,
					1.0f * CONVERT, 1.0f * CONVERT, 1.0f * CONVERT,
					1.0f * CONVERT, 1.0f * CONVERT };
			
			PdfPTable tab_DonHang = new PdfPTable(crDonHang.length);
			tab_DonHang.setWidthPercentage(100);
			tab_DonHang.setHorizontalAlignment(Element.ALIGN_LEFT);			
			tab_DonHang.setWidths(crDonHang);
			tab_DonHang.getDefaultCell().setBorder(0);
			tab_DonHang.setSpacingAfter(8.0f);
			
			String[] spTitles = { "TT", "Tên hàng, qui cách", "TCKT", "Đ.vị",
					"S.lượng", "Đ.giá (VND)", "VAT", "Thành tiền (VND)", "Thời gian giao hàng",
					"Địa điểm nhận hàng" };
			
			for (int z = 0; z < spTitles.length; z++) {
				cell = new PdfPCell(new Paragraph(spTitles[z], font11_bold));
				cell.setPadding(3.0f);
				cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				tab_DonHang.addCell(cell);
			}
			
			query = ""; //cau query do data vao table
			
			ResultSet rs = db.get(query);
			int sott = 1;
			if (rs != null) {
				while (rs.next()) {
					String[] spTitles2 = { sott + "", rs.getString("TenHang"),
							rs.getString("TCKT"), rs.getString("DonVi"),
							rs.getString("SoLuong"), rs.getString("DonGia"),
							rs.getString("VAT"), rs.getString("ThanhTien"),
							rs.getString("ThoiGianGH"),
							rs.getString("DiaDiemNH") };

					for (int z = 0; z < spTitles2.length; z++) {
						cell = new PdfPCell(new Paragraph(spTitles2[z], font10));
						cell.setPadding(3.0f);
						if (z == 4 || z == 5 || z == 7) {
							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						} else {
							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						}
						tab_DonHang.addCell(cell);
					}
					sott++;
				}
			}
			cell = new PdfPCell(new Paragraph("Tổng cộng", font11_bold));
			cell.setColspan(2);
			cell.setPadding(3.0f);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tab_DonHang.addCell(cell);
			//tinh tong gia tri cho cac cot trong table
			cell = new PdfPCell(new Paragraph(formatter.format(1000),
					font10_bold));
			cell.setPadding(3.0f);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tab_DonHang.addCell(cell);
			//.....
			document.add(tab_DonHang);
			
			//tong tien bang chu
			Paragraph para7 = new Paragraph("Tổng số tiền viết bằng chữ: Đọc tiền theo nguyên tệ gốc", new Font(bf, 12, Font.BOLD));
			para7.setSpacingBefore(3);
			para7.setSpacingAfter(5);
			para7.setAlignment(Element.ALIGN_LEFT);
			document.add(para7);
			
	        /*
	        PdfPTable table3 = new PdfPTable(2);
			table3.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.setWidths(new int[]{ 6, 6});
			table3.setSpacingAfter(5.0f);
	        PdfPCell cell4;
	        cell4 = new PdfPCell(new Phrase("", font11_bold));
	        cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell4.setColspan(8);
	        cell4.setPadding(1.0f);
	        cell4.setBorder(0);
	        table3.addCell(cell4);
	        
	        cell4 = new PdfPCell(new Phrase("Tổng tiền viết bằng chữ: ", font11_bold));
	        cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
	        cell4.setBorder(0);
	        table3.addCell(cell4);
	        
	        cell4 = new PdfPCell(new Phrase("Đọc theo nguyên tệ gốc", new Font(bf, 11, Font.NORMAL)));
	        cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
	        cell4.setBorder(0);
	        table3.addCell(cell4);
	        document.add(table3);*/
	        //___________________
	        
			Paragraph para6 = new Paragraph("II. Hình thức thanh toán:   Chuyển khoản hoặc tiền mặt", new Font(bf, 12, Font.BOLD));
			para6.setSpacingBefore(3);
			para6.setSpacingAfter(10);
			para6.setAlignment(Element.ALIGN_LEFT);
			document.add(para6);
			
			
			Paragraph pxk = new Paragraph("            XÁC NHẬN CỦA BÊN BÁN"+ "                                    "+ "TRƯỞNG PHÒNG XNK-CƯVT", new Font(bf, 12, Font.BOLD));
			pxk.setSpacingAfter(15);
			pxk.setAlignment(Element.ALIGN_LEFT);
			document.add(pxk);
			document.close(); 
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception print PDF: "+ e.getMessage());
		}
	}

}
