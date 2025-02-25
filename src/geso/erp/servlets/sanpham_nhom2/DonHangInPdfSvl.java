package geso.erp.servlets.sanpham_nhom2;

import geso.dms.center.util.Utility;
import geso.erp.beans.banhang.IBanhang;
import geso.erp.beans.banhang.ISanpham;
import geso.erp.beans.banhang.imp.Banhang;
import geso.erp.beans.doctien.DocTien;
import geso.erp.db.sql.dbutils;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
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

public class DonHangInPdfSvl extends HttpServlet{
	private static final long serialVersionUID = 1L;

    public DonHangInPdfSvl() {
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		// set characterEncoding là utf-8 cho đối tượng request
		request.setCharacterEncoding("UTF-8");
		// set characterEncoding là utf-8 cho đối tượng request
	    response.setCharacterEncoding("UTF-8");
	    
	    // get đối tượng session từ request	    
	    HttpSession session = request.getSession();
	    
	    // khai báo lớp tiện ích Utility
	    Utility util = new Utility();
	    // get các tham số được truyền từ client bằng phương thức GET
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String action = util.getAction(querystring);
	    String DonHangId = util.getId(querystring);
	    
	    if(action.equals("print")){
	    	CreatePDF(request, response,DonHangId);
	    }else {
	    	// tuỳ theo các action khác mà sử dụng
	    }
	    
	}
	private void CreatePDF( HttpServletRequest request,HttpServletResponse response,String id) {
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition"," inline; filename=DonHang.pdf");
		
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
			 
			this.CreatePO_Training(document, outstream, response, request, db,id);
			db.shutDown();
		
		} catch (IOException e){
			e.printStackTrace();
			System.out.println("___Exception Print: " + e.getMessage());
		}
	}
	
	private void CreatePO_Training(Document document, ServletOutputStream outstream, 
			HttpServletResponse response, HttpServletRequest request, dbutils db,String id){
		try
		{
			IBanhang obj=new Banhang(id);
			obj.init();
			
			NumberFormat formatter_2sole = new DecimalFormat("#,###,###.###");
			NumberFormat formatter = new DecimalFormat("#,###,###");
			
			PdfWriter.getInstance(document, outstream);
			document.open();

			float CONVERT = 28.346457f; // = 1cm
			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			 // khỏi tạo font chữ 
			Font font10 = new Font(bf, 10, Font.NORMAL);
			Font font11_bold= new Font(bf, 11, Font.BOLD);
			
			
			// khởi tạo 1 table
			PdfPTable tbl_header = new PdfPTable(1);
			
			tbl_header.setWidthPercentage(100);
			tbl_header.setHorizontalAlignment(Element.ALIGN_LEFT);
			
			float[] crtbl_header = {7f};
			tbl_header.setWidths(crtbl_header);
			
			PdfPCell cell = new PdfPCell(new Paragraph("CONG TY GIAI PHAP DOANH NGHIEP TOAN CAU ", font11_bold));
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
			
			 
			document.add(tbl_header);

			
			Paragraph pxk = new Paragraph("ĐƠN BÁN HÀNG", new Font(bf, 14, Font.BOLD));
			
			pxk.setSpacingAfter(2);
			pxk.setAlignment(Element.ALIGN_CENTER); 
			document.add(pxk);
			 
			
			//khoi tao 1 bang ,bao nhieu cot thì phai go trước
			PdfPTable tbl_traning = new PdfPTable(2);
			tbl_traning.setSpacingBefore(10);
			tbl_traning.setWidthPercentage(100);
			tbl_traning.setHorizontalAlignment(Element.ALIGN_LEFT);
			float[] crtbl_traning = {7f,25f};
			
			tbl_traning.setWidths(crtbl_traning);
			
			//đối tượng CELL 
			cell = new PdfPCell(new Paragraph("Tên: ", font11_bold));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			tbl_traning.addCell(cell);
			
			cell = new PdfPCell(new Paragraph(obj.getKhten(), font10));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			tbl_traning.addCell(cell);
				
			cell = new PdfPCell(new Paragraph("Địa chỉ: ", font11_bold));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			tbl_traning.addCell(cell);
				
			cell = new PdfPCell(new Paragraph(obj.getKhDiachi(), font10));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			tbl_traning.addCell(cell);
			tbl_traning.setSpacingAfter(10);
			document.add(tbl_traning);
			
			PdfPTable tbHeader = new PdfPTable(1);
			tbHeader.setWidthPercentage(100);
			tbHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
			float[] crHeader = {400f};
			//thuộc tính 
			tbHeader.setWidths(crHeader);
			tbHeader.getDefaultCell().setBorder(0);
			 	 
			document.add(tbHeader);

			float[] crSanpham = {1.2f * CONVERT, 2.0f * CONVERT, 2.9f * CONVERT, 1.8f * CONVERT, 2.0f * CONVERT,
					2.0f * CONVERT,2.0f * CONVERT, 2.8f * CONVERT}; 
 
			PdfPTable tbSanPham = new PdfPTable(crSanpham.length);
			tbSanPham.setWidthPercentage(100);
			tbSanPham.setHorizontalAlignment(Element.ALIGN_LEFT);			
			tbSanPham.setWidths(crSanpham);
			tbSanPham.getDefaultCell().setBorder(0);
			tbSanPham.setSpacingAfter(8.0f);
			
			String[] spTitles = {"STT", "Mã","Tên", "Đơn vị", "Số lượng", "Đơn giá","Chiết khấu", "Thành tiền"};
			
			for(int z = 0; z < spTitles.length; z++) {
				cell = new PdfPCell(new Paragraph(spTitles[z], font11_bold));
				cell.setPadding(3.0f);
				cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				tbSanPham.addCell(cell);
			}
	 
			List<ISanpham>listsp=obj.getSpList();
			
 			int  sott=1;
			for(int i=0;i<listsp.size();i++){
				ISanpham sp=listsp.get(i);
				
				String[] spTitles2 = {""+sott, sp.getMasanpham(), sp.getTensanpham(),sp.getDonvitinh(),
						formatter_2sole.format(sp.getSoluong()) , formatter_2sole.format(sp.getDongia()),
						formatter_2sole.format(sp.getChietkhau()),formatter_2sole.format(sp.getThanhtien()) } ;
				
				for(int z = 0; z < spTitles2.length; z++) {
					cell = new PdfPCell(new Paragraph(spTitles2[z], font10));
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
			cell.setColspan(7); 
			cell.setPadding(3.0f);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tbSanPham.addCell(cell);
			
			cell = new PdfPCell(new Paragraph(formatter.format(obj.getTongtienchuaVat()), font11_bold));
			cell.setPadding(3.0f);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tbSanPham.addCell(cell);
				
			document.add(tbSanPham);
			
			pxk = new Paragraph("Số tiền bằng chữ: " +DocTien.docTien(Math.round(obj.getTongtienchuaVat()))+" .", 
					new Font(bf, 12, Font.ITALIC));
			pxk.setSpacingAfter(2);
			pxk.setAlignment(Element.ALIGN_LEFT);
			
			document.add(pxk);
			document.close(); 
	
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Exception In PDF: " + e.getMessage());
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
	}

}
