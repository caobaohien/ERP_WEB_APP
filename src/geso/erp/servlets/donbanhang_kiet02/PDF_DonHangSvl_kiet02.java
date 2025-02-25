package geso.erp.servlets.donbanhang_kiet02;

import geso.dms.center.util.Utility;
import geso.erp.beans.doctien.DocTien;
import geso.erp.beans.donbanhang_kiet02.IDonHang;
import geso.erp.beans.donbanhang_kiet02.ISanPham;
import geso.erp.beans.donbanhang_kiet02.imp.DonHang;
import geso.erp.db.sql.dbutils;

import java.io.IOException;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

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
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@WebServlet("/PDF_DonHangSvl_kiet02")
public class PDF_DonHangSvl_kiet02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public PDF_DonHangSvl_kiet02() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    
	    HttpSession session = request.getSession();
	    Utility util = new Utility();
	    
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
		
		String action = util.getAction(querystring);
		String id = util.getId(querystring);

		CreatePDF(request, response, id);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	protected void CreatePDF(HttpServletRequest request, HttpServletResponse response, String id){
		
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition"," inline; filename=DonHang_kiet02.pdf");
		
		float CONVERT = 28.346457f;//1 cm 
		
		float PAGE_LEFT = 2.0f*CONVERT;
		float PAGE_RIGHT = 1.5f*CONVERT;
		float PAGE_TOP = 0.5f*CONVERT ;
		float PAGE_BOTTOM = 0.0f*CONVERT; 
		
		Document document = new Document(PageSize.A4, PAGE_LEFT, PAGE_RIGHT, PAGE_TOP, PAGE_BOTTOM);
		ServletOutputStream outstream;
		
		try {
			outstream = response.getOutputStream();
			dbutils db = new dbutils();
			
			this.CreatePO_Training(document, outstream, request, response, db, id);
			db.shutDown();
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception print: "+ e.getMessage());
		}
		
	}
	
	protected void CreatePO_Training(Document document, ServletOutputStream outstream,
			HttpServletRequest request, HttpServletResponse response, dbutils db, String id) throws IOException{
		
		HttpSession session = request.getSession();
		IDonHang obj = new DonHang(id);
		obj.init("");
		
		NumberFormat formatter_2sole = new DecimalFormat("#,###,###.##"); //1,000,000.48
		NumberFormat formatter = new DecimalFormat("#,###,###"); // 1,000,000
		
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
