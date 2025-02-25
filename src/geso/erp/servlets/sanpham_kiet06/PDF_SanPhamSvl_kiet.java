package geso.erp.servlets.sanpham_kiet06;

import geso.dms.center.util.Utility;
import geso.erp.beans.sanpham_kiet06.ISanPham;
import geso.erp.beans.sanpham_kiet06.imp.SanPham;
import geso.erp.db.sql.dbutils;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@WebServlet("/PDF_SanPhamSvl_kiet")
public class PDF_SanPhamSvl_kiet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PDF_SanPhamSvl_kiet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
		
	    HttpSession session = request.getSession();
	    Utility util = new Utility();
	    // get các tham số được truyền từ client bằng phương thức GET
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String action = util.getAction(querystring);
	    String DonHangId = util.getId(querystring);
	    try {
			CreatePDF(request, response,DonHangId);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	private void CreatePDF(HttpServletRequest request,
			HttpServletResponse response, String id) throws SQLException {
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition"," inline; filename=SanPham_kiet.pdf");
		
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
			
			CreatePO_Training(document, outstream, response, request, db, id);
			db.shutDown();
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("___Exception Print: " + e.getMessage());
		}
	}
	
	private void CreatePO_Training(Document document, ServletOutputStream outstream, 
			HttpServletResponse response, HttpServletRequest request, dbutils db, String id) throws IOException, SQLException{
		
		NumberFormat formatter_2sole = new DecimalFormat("#,###,###.##"); //1,000,000.48
		NumberFormat formatter = new DecimalFormat("#,###,###");
		
		try {
			PdfWriter.getInstance(document, outstream);
			document.open();

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
			
			PdfPCell cell = new PdfPCell(new Paragraph("CÔNG TY GIẢI PHÁP DOANH NGHIỆP TOÀN CẦU GESO SP ", font11_bold));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			tbl_header.addCell(cell);
			
			cell = new PdfPCell(new Paragraph("Address:  ", font11_bold));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			tbl_header.addCell(cell);
			cell = new PdfPCell(new Paragraph("Number phone:  ", font11_bold));
		    cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			tbl_header.addCell(cell);
			
			document.add(tbl_header);
			//--------------------------------------------------------------------
			Paragraph pxk = new Paragraph("SẢN PHẨM KIỆT", new Font(bf, 14, Font.BOLD));
			
			pxk.setSpacingAfter(2);
			pxk.setAlignment(Element.ALIGN_CENTER); 
			document.add(pxk);
			//--------------------------------------------------------------------------------
			float[] crSanpham = {1.0f * CONVERT, 2.0f * CONVERT,2.0f * CONVERT, 2.0f * CONVERT, 2.0f * CONVERT }; 
			 
			PdfPTable tbSanPham = new PdfPTable(crSanpham.length);
			tbSanPham.setWidthPercentage(100);
			tbSanPham.setHorizontalAlignment(Element.ALIGN_LEFT);			
			tbSanPham.setWidths(crSanpham);
			tbSanPham.getDefaultCell().setBorder(0);
			tbSanPham.setSpacingAfter(8.0f);
			
			String[] spTitles = { "STT", "MÃ SẢN PHẨM", "TÊN SẢN PHẨM", "ĐƠN VỊ",
					"TRẠNG THÁI"};
			
			for (int z = 0; z < spTitles.length; z++) {
				cell = new PdfPCell(new Paragraph(spTitles[z], font11_bold));
				cell.setPadding(3.0f);
				cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				tbSanPham.addCell(cell);
			}
			String query = "";
			query = "select SP.PK_SEQ, SP.TEN, SP.MA, SP.TRANGTHAI, DV.MA as DonVi "
				+ "from SANPHAM SP left join DONVI DV on DV.PK_SEQ = SP.DONVI_FK";
			
			ResultSet rs = db.get(query);
			int sott = 1;
			while (rs.next()) {

				String[] spTitles2 = { "" + sott,
						rs.getString("MA"), rs.getString("TEN"),
						rs.getString("DONVI"),
						rs.getString("TRANGTHAI") };

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
			
			document.add(tbSanPham);
			document.close(); 
			
			
		} catch (DocumentException e) {
			e.printStackTrace();
			System.out.println("Exception In PDF: " + e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
