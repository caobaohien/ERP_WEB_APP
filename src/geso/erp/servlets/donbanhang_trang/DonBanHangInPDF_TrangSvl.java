package geso.erp.servlets.donbanhang_trang;

import geso.dms.center.util.Utility;
import geso.erp.beans.doctien.DocTien;
import geso.erp.beans.donbanhang_trang.IChiTietDonHang_Trang;
import geso.erp.beans.donbanhang_trang.IDonBanHang_Trang;
import geso.erp.beans.donbanhang_trang.imp.DonBanHang_Trang;

import geso.erp.db.sql.dbutils;

import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import java.util.List;

import java.io.IOException;
import java.io.OutputStream;


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
import com.itextpdf.text.pdf.fonts.*;
import com.lowagie.text.pdf.PdfCell;


public class DonBanHangInPDF_TrangSvl extends HttpServlet{
	private static final long serialVersionUID = 1L;

    public DonBanHangInPDF_TrangSvl() {
    	super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		// set characterEncoding là utf-8 cho đối tượng request
		request.setCharacterEncoding("UTF-8");
		// set characterEncoding là utf-8 cho đối tượng request
	    response.setCharacterEncoding("UTF-8");
	    
	    //lấy sesion
	    HttpSession sesion = request.getSession();
	    
	 // khai báo lớp tiện ích Utility
	    Utility util = new Utility();
	    
	    // get các tham số được truyền từ client bằng phương thức GET
	    String querystring = request.getQueryString();
	    
	    String action = util.getAction(querystring);
	    String id = util.getId(querystring);
	    String nextJSP = "/TrainingGESO/pages/Erp/DonBanHangDisplay_Trang.jsp";
	    
	    //khởi tạo đối tượng
	    IDonBanHang_Trang obj = new DonBanHang_Trang();
	    /*obj.setMaDonHang(maDonHang);*/
	    obj.setId(id);
	    obj.init("");
	    
	    OutputStream out = response.getOutputStream();
	    if(action.equals("pdf")){
	    	
	    	CreatePDF(request, response, obj);
	    }
	    sesion.setAttribute("objnew", obj);
	    response.sendRedirect(nextJSP);
	    return;
	}
	
	//Bắt đầu xây dựng: IN FILE PDF
	private void CreatePDF( HttpServletRequest request,HttpServletResponse response,IDonBanHang_Trang obj) {
		
		//BƯỚC 0: 	ĐỊNH NGHĨA KIỂU DỮ LIỆU TRẢ VỀ RESPONSE CHO CLIENT LÀ KIỀU PDF
		//			SETHEADER CƠ BẢN CHO TRANG PDF CẦN HIỂN THỊ
		
			//Định nghĩa kiểu trả về cho response là pdf
		response.setContentType("application/pdf");
			//Đặt tên cho file của bạn
		response.setHeader("Content-Disposition"," inline; filename=DonBanHangInPdf_Trang.pdf");
			//setHeader cho trang pdf cần hiển thị
		float CONVERT = 28.346457f;// CONVERT tương đương với 1 cm 
		
		//Chuyển về cm
		float PAGE_LEFT = 2.0f*CONVERT;
		float PAGE_RIGHT = 1.5f*CONVERT;
		float PAGE_TOP = 0.5f*CONVERT ;
		float PAGE_BOTTOM = 0.0f*CONVERT; 
		
		
		//BƯỚC 1: KHỞI TẠO ĐỐI TƯỢNG TÀI LIỆU
		//		Định dạng khổ giấy
		Document document = new Document(PageSize.A4, PAGE_LEFT, PAGE_RIGHT, PAGE_TOP, PAGE_BOTTOM);
		ServletOutputStream outstream;
		try {
			outstream = response.getOutputStream();
			dbutils db = new dbutils();
			 
			this.FillData(document, outstream, response, request, db,obj);
			db.shutDown();
		
		} catch (IOException e){
			e.printStackTrace();
			System.out.println("___Exception Print: " + e.getMessage());
		}
	}
	
	private void FillData(Document document, ServletOutputStream outstream, 
			HttpServletResponse response, HttpServletRequest request, dbutils db,IDonBanHang_Trang obj){
		try
		{
			//Format dữ liệu (số) theo tiêu chí nào đó: 
			//		Tiêu chí 1: 
			NumberFormat formatter_2sole = new DecimalFormat("#,###,###.###");
			//		Tiêu chí 2: 
			NumberFormat formatter = new DecimalFormat("#,###,###");
			
			//BƯỚC 2: KHỞI TẠO 1 PDFWRITE
			PdfWriter.getInstance(document, outstream); 
			//Mở document
			document.open(); 
			
			//BƯỚC 3: THIẾT LẬP FONT CHỮ
			float CONVERT = 28.346457f; // = 1cm
			BaseFont bf = BaseFont.createFont("c:\\windows\\Fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			 // khỏi tạo font chữ 
			Font font10 = new Font(bf, 10, Font.NORMAL);			//font10: bình thường
			Font font11_bold= new Font(bf, 11, Font.BOLD);			//font11: in đậm
			Font font12_italic = new Font(bf, 11, Font.ITALIC);		//font12: in nghiên
			
			
			//BƯỚC 4: CHUẨN BỊ NỘI DUNG TỪ CƠ SỞ DỮ LIỆU: LẤY DỮ LIỆU THÔNG QUA QUERY
			
			//BƯỚC 5: TRÌNH BÀY DỮ LIỆU
			// Khởi tạo 1 table
			PdfPTable tb1_header = new PdfPTable(1);
			tb1_header.setWidthPercentage(100);
			tb1_header.setHorizontalAlignment(Element.ALIGN_LEFT);
			
			float[] crb1_header = {7f};
			tb1_header.setWidths(crb1_header);
			
			PdfPCell cell = new PdfPCell(new Paragraph("CÔNG TY GIẢI PHÁP DOANH NGHIỆP TOÀN CẦU", font11_bold));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			tb1_header.addCell(cell);
			
			cell = new PdfPCell(new Paragraph("Địa chỉ: Nguyễn Bỉnh Khiêm", font11_bold));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			tb1_header.addCell(cell);
			
			cell = new PdfPCell(new Paragraph("Số điện thoại: 01234567891", font11_bold));
			cell.setPaddingLeft(5.0f);
			cell.setBorder(0);
			tb1_header.addCell(cell);
			
			document.add(tb1_header);
			Paragraph pxk = new Paragraph("ĐƠN BÁN HÀNG", new Font(bf, 16, Font.BOLD));	
			pxk.setSpacingAfter(Element.ALIGN_CENTER);
			document.add(pxk);	
			
			//KHỞI TẠO 1 TABLE, BAO NHIÊU CỘT THÌ TA PHẢI KHAI BÁO TRƯỚC
			//tạo thông tin khách hàng
			obj.getMaDonHangPDF();
			ResultSet rskh = obj.getRsKhachHang();
			if(rskh != null)
				if(rskh.next()){
					PdfPTable tblkhachhang = new PdfPTable(2);
					tblkhachhang.setSpacingBefore(10);
					tblkhachhang.setWidthPercentage(100);
					tblkhachhang.setHorizontalAlignment(Element.ALIGN_LEFT);
					float[] crtbl_traning = {7f,25f};
					
					tblkhachhang.setWidths(crtbl_traning);
					
					//đối tượng CELL
					cell = new PdfPCell(new Paragraph("Tên khách hàng: ", font10));
					//định dạng những thứ linh tinh
					cell.setPaddingLeft(5.0f);
					cell.setBorder(0);
					//add cell vào 
					tblkhachhang.addCell(cell);
					
					cell = new PdfPCell(new Paragraph(rskh.getString("TEN"), font10));
					cell.setPaddingLeft(5.0f);
					cell.setBorder(0);
					tblkhachhang.addCell(cell);
						
					cell = new PdfPCell(new Paragraph("Địa chỉ: ", font10));
					cell.setPaddingLeft(5.0f);
					cell.setBorder(0);
					tblkhachhang.addCell(cell);
						
					cell = new PdfPCell(new Paragraph(rskh.getString("DIACHI"), font10));
					cell.setPaddingLeft(5.0f);
					cell.setBorder(0);
					tblkhachhang.addCell(cell);
					
					cell = new PdfPCell(new Paragraph("Điện thoại: ", font10));
					cell.setPaddingLeft(5.0f);
					cell.setBorder(0);
					tblkhachhang.addCell(cell);
						
					cell = new PdfPCell(new Paragraph(rskh.getString("DIENTHOAI"), font10));
					cell.setPaddingLeft(5.0f);
					cell.setBorder(0);
					tblkhachhang.addCell(cell);
					
					tblkhachhang.setSpacingAfter(10);
					document.add(tblkhachhang);
				}
			
			//tạo lưới các sản phẩm
			
			
			//table chứa header
			PdfPTable tbHeader = new PdfPTable(1);
			tbHeader.setWidthPercentage(100);
			tbHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
			float[] crHeader = {400f};
			//thuộc tính 
			tbHeader.setWidths(crHeader);
			tbHeader.getDefaultCell().setBorder(0);
			 	 
			document.add(tbHeader);
			
			//khai báo độ dài từng cột cho bản chi tiết
			float[] crSanpham = {1.2f * CONVERT, 2.0f * CONVERT, 2.9f * CONVERT, 1.8f * CONVERT, 2.0f * CONVERT,
					2.0f * CONVERT, 2.8f * CONVERT, 2.8f * CONVERT}; 
			
			//table chứa sản phẩm
			PdfPTable tbSanPham = new PdfPTable(crSanpham.length);
			tbSanPham.setWidthPercentage(100);
			tbSanPham.setHorizontalAlignment(Element.ALIGN_LEFT);			
			tbSanPham.setWidths(crSanpham);
			tbSanPham.getDefaultCell().setBorder(0);
			tbSanPham.setSpacingAfter(8.0f);
			
			String[] spTitles = {"STT", "Mã","Tên", "Đơn vị", "Số lượng", "Số lượng tồn" , "Đơn giá", "Thành tiền"};
			
			for(int z = 0; z < spTitles.length; z++) {
				cell = new PdfPCell(new Paragraph(spTitles[z], font11_bold));
				cell.setPadding(3.0f);
				cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				tbSanPham.addCell(cell);
			}
	 
			List<IChiTietDonHang_Trang>listsp = obj.getDanhSachSanPham();
			
 			int  sott=1;
			for(int i=0;i<listsp.size();i++){
				IChiTietDonHang_Trang ctdh = listsp.get(i);
				
				String[] spTitles2 = {""+sott, ctdh.getMaSanPham(), ctdh.getTenSanPham(), ctdh.getDonViTinh(),
						formatter_2sole.format(ctdh.getSoLuong()) , formatter_2sole.format(ctdh.getSoLuongTon()), 
						formatter_2sole.format(ctdh.getDonGia()),
						formatter_2sole.format(ctdh.getThanhTien()) } ;
				System.out.println("Thanh tien  PDF " + formatter_2sole.format(ctdh.getThanhTien()));
				
				for(int z = 0; z < spTitles2.length; z++) {
					cell = new PdfPCell(new Paragraph(spTitles2[z], font10));
					cell.setPadding(3.0f);
					if(z==4||z==5||z==6){
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);	
					}else{
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					}
					tbSanPham.addCell(cell);
				}
				sott++ ;
			}
			 
			cell = new PdfPCell(new Paragraph("Tổng cộng", font11_bold));
			cell.setColspan(7); //gộp cột lại với nhau
			cell.setPadding(3.0f);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tbSanPham.addCell(cell);
			
			cell = new PdfPCell(new Paragraph(formatter.format(obj.getTongTien()), font11_bold));
			cell.setPadding(3.0f);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tbSanPham.addCell(cell);
				
			document.add(tbSanPham);
			
			pxk = new Paragraph("Số tiền bằng chữ: " +DocTien.docTien(Math.round(obj.getTongTien()))+" .", 
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

