package geso.erp.servlets.donbanhang_hang;



import geso.dms.center.util.Utility;
import geso.erp.beans.donbanhang_hang.IChiTietDonHang_Hang;
import geso.erp.beans.donbanhang_hang.IDonBanHang_Hang;
import geso.erp.beans.donbanhang_hang.imp.*;

import geso.erp.db.sql.dbutils;

import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.servlet.ServletException;
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
import com.aspose.cells.Font;
import com.aspose.cells.Style;
import com.aspose.cells.TextAlignmentType;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;


public class DonBanHangInExcel_HangSvl extends HttpServlet{
	private static final long serialVersionUID = 1L;

    public DonBanHangInExcel_HangSvl() {
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
	    String nextJSP = "/TrainingGESO/pages/Erp/DonBanHangDisplay_Hang.jsp";
	    
	    //khởi tạo đối tượng
	    IDonBanHang_Hang obj = new DonBanHang_Hang();
	    
	    //Sua lai
	    //obj.setMaDonHang(maDonHang);
	    obj.setId(id);
	    obj.init("");
	    
	    OutputStream out = response.getOutputStream();
	    if(action.equals("excel")){
	    	
	    	response.setContentType("application/xls");
			response.setHeader("Content-Disposition", "attachment; filename=DonBanHangInExcel_Hang.xls");
	    	if(!CreateExcel(out,request, response, obj)){
	    		response.setContentType("text/html");
			    PrintWriter writer = new PrintWriter(out);
			    writer.print("Lỗi xuất excel");
			    writer.close();
	    	}
	    }
	    sesion.setAttribute("objnew", obj);
	    response.sendRedirect(nextJSP);
	    return;
	}
	
	private boolean CreateExcel( OutputStream  out,HttpServletRequest request,HttpServletResponse response,IDonBanHang_Hang obj) throws IOException{
		boolean isFillData = false;
		FileInputStream fstream = null;
		Workbook workbook = new Workbook();
		
		fstream = new FileInputStream(getServletContext().getInitParameter("pathHang") + "\\DonBanHangInExcel_Hang.xls");
				
		workbook.open(fstream);
		workbook.setFileFormatType(FileFormatType.EXCEL97TO2003);

		
		isFillData = FillData(workbook, obj);
   
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
	
	private boolean FillData(Workbook workbook, IDonBanHang_Hang obj){
		dbutils db = null;
		try {
			db = new dbutils();
			
			//khởi tạo 1 worksheet
			Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0); //tạo 1 worksheet ở vị trí 0
			
			//khỏi tạo 1 cell
			Cells cells = worksheet.getCells();
			Cell cell = null;
			Style styletieude = null;
			
			Style style = null; //size cho các cột
			
			
			//khai báo font
			Font font = new Font();
			font.setName("Arial");
			font.setSize(11);

			cells.merge(1, 1, 1, 7);
			cell = cells.getCell("B2");
			cell.setValue("CÔNG TY GIẢI PHÁP DOANH NGHIỆP TOÀN CẦU");
			styletieude = cell.getStyle();
			styletieude.setHAlignment(TextAlignmentType.LEFT);
			//style.setVAlignment(TextAlignAttribute.CENTER);
			Font fonttieude = new Font();
			fonttieude.setName("Arial");
			fonttieude.setSize(14);
			fonttieude.setColor(Color.BLACK);
			fonttieude.setBold(true);
			styletieude.setFont(fonttieude);
			cell.setStyle(styletieude);
			
			
			cell = cells.getCell("B3");
			cell.setValue("Địa chỉ:");
			cell = cells.getCell("B4");
			cell.setValue("Điện thoại:");
			obj.getKhachHangTheoMa();
			cells.merge(2, 2, 2, 7);
			cell = cells.getCell("C3");
			cell.setValue("Nguyễn Bỉnh Khiêm");
			
			cells.merge(3, 2, 3, 7);
			cell = cells.getCell("C4");
			cell.setValue("0965.025.376");
			
			
			
			cells.merge(6, 3, 6, 5);
			cell = cells.getCell("D7");
			cell.setValue("ĐƠN BÁN HÀNG");
			Style styledonbanhang = null;
			styledonbanhang = cell.getStyle();
			styledonbanhang.setHAlignment(TextAlignmentType.CENTER);
			Font fontdonbanhang = new Font();
			fontdonbanhang.setName("Arial");
			fontdonbanhang.setSize(13);
			fontdonbanhang.setBold(true);
			fontdonbanhang.setColor(Color.BLACK);
			styledonbanhang.setFont(fontdonbanhang);
			cell.setStyle(styledonbanhang);
						
			
			cell = cells.getCell("B9");
			cell.setValue("Tên:");
			cell = cells.getCell("B10");
			cell.setValue("Địa chỉ:");
			cell = cells.getCell("B11");
			cell.setValue("Số điện thoại:");
			
			
			obj.getKhachHangTheoMa();
			ResultSet rskh = obj.getRsKhachHang();
			if(rskh.next()){
				cells.merge(8, 2, 8, 7);
				cell = cells.getCell("C9");
				cell.setValue(rskh.getString("TEN"));
				cells.merge(9, 2, 9, 7);
				cell = cells.getCell("C10");
				cell.setValue(rskh.getString("DIACHI"));
				cells.merge(10, 2, 10, 7);
				cell = cells.getCell("C11");
				cell.setValue(rskh.getString("DIENTHOAI"));
				
				System.out.println("Tên: " + rskh.getString("TEN"));
				System.out.println("Địa chỉ: " + rskh.getString("DIACHI"));
				System.out.println("Số điện thoại: " + rskh.getString("DIENTHOAI"));
			}
					
			cell = cells.getCell("B13");
			cell.setValue("STT");
			style = cell.getStyle();
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
			
			
	        cell = cells.getCell("C13");
			cell.setValue("Mã Sản Phẩm");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
	        
	        cell = cells.getCell("D13");
			cell.setValue("Tên Sản Phẩm");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
	        
	        cell = cells.getCell("E13");
			cell.setValue("Đon vị tính");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
	        
	        
	        cell = cells.getCell("F13");
			cell.setValue("Số lượng");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
	        
	        cell = cells.getCell("G13");
			cell.setValue("Số lượng tồn");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
	        
	        cell = cells.getCell("H13");
			cell.setValue("Đơn giá");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
	        
	        cell = cells.getCell("I13");
			cell.setValue("Thành tiền");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
	        
	        
	        List<IChiTietDonHang_Hang> ctdh = obj.getListSp();
	        int index = 14;
	        for(int i= 0; i< ctdh.size(); i++ ){
	        	//STT
	        	
	        	cells.setColumnWidth(1, 13.0f);
	        	fillChiTietDonHang(index, "B", String.valueOf(i+1), cell, cells, style);
	        	
	        	//ma sản phẩm
	        	cells.setColumnWidth(2, 13.0f);
	        	fillChiTietDonHang(index, "C", ctdh.get(i).getMaSanPham(), cell, cells, style);	     
	        	
	        	//tên sản phẩm
	        	cells.setColumnWidth(3, 15.0f);
	        	fillChiTietDonHang(index, "D", ctdh.get(i).getTenSanPham(), cell, cells, style);
	        	
	        	//đơn vị tính
	        	cells.setColumnWidth(4, 18.0f);
	        	fillChiTietDonHang(index, "E", ctdh.get(i).getDonViTinh(), cell, cells, style);
	        		        	
	        	//số lượng
	        	cells.setColumnWidth(6, 18.0f);
	        	fillChiTietDonHang(index, "F", String.valueOf(ctdh.get(i).getSoLuong()), cell, cells, style);
	        	
	        	//Số lượng tồn
	        	cells.setColumnWidth(7, 18.0f);
	        	fillChiTietDonHang(index, "G", String.valueOf(ctdh.get(i).getSoLuongTon()), cell, cells, style);
	        	
	        	//đơn giá
	        	cells.setColumnWidth(5, 18.0f);
	        	fillChiTietDonHang(index, "H", String.valueOf(ctdh.get(i).getDonGia()), cell, cells, style);
	        	
	        	//thành tiền
	        	cells.setColumnWidth(8, 18.0f);
	        	fillChiTietDonHang(index, "I", String.valueOf(ctdh.get(i).getThanhTien()), cell, cells, style);
	        	
	        	index ++;
	        	
	        	System.out.println("Xuất ra i = " + i);
	        	System.out.println("Mã sản phẩm: " + ctdh.get(i).getMaSanPham());
	        	System.out.println("Tên sản phẩm: " + ctdh.get(i).getTenSanPham());
	        	System.out.println("Đơn vị tính: " + ctdh.get(i).getDonViTinh());
	        	System.out.println("Số lượng: " + String.valueOf(ctdh.get(i).getSoLuong()));
	        	System.out.println("Số lượng tồn: " + String.valueOf(ctdh.get(i).getSoLuongTon()));
	        	System.out.println("Đơn giá: " + String.valueOf(ctdh.get(i).getDonGia()));
	        	System.out.println("Thành tiền: " + String.valueOf(ctdh.get(i).getThanhTien()));
	        }
	        
	        //làm ô tổng tiền
	        cells.merge(index-1, 1, index-1, 7);
	        cell = cells.getCell("B" + index);
			cell.setValue("Tổng tiền");
			
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        style.setHAlignment(TextAlignmentType.CENTER);
	        font.setBold(true);
	        style.setFont(font);
	        cell.setStyle(style);
	        cell = cells.getCell("C" + index);
	        cell.setStyle(style);
	        cell = cells.getCell("D" + index);
	        cell.setStyle(style);
	        cell = cells.getCell("E" + index);
	        cell.setStyle(style);
	        cell = cells.getCell("F" + index);
	        cell.setStyle(style);
	        cell = cells.getCell("G" + index);
	        cell.setStyle(style);
	        cell = cells.getCell("H" + index);
	        cell.setStyle(style);
	        
	        //giá trị
	        NumberFormat formatter_2sole = new DecimalFormat("#,###,###.###");
	        //Sửa giá trị thành tiền
	        cell = cells.getCell("I" + index);
			cell.setValue(formatter_2sole.format(obj.getTongTien()));
		//	cells.merge(index, 2, index, 7);
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        
	        style.setHAlignment(TextAlignmentType.RIGHT);
	       // style.setColor(Color.RED);
	        font.setBold(true);
	        style.setFont(font);
	        cell.setStyle(style);
	        
	        System.out.println("Tổng tiền: "+ formatter_2sole.format(obj.getTongTien()));
	        
		} catch ( Exception ex) {
			ex.printStackTrace();
			return false;
			// TODO: handle exception
		} finally{
			db.shutDown();
		}
		
			return true;
	}
	
	//định dạng các ô trong bảng
	private void fillChiTietDonHang(int chiso, String chucai, String noidung, Cell cell, Cells cells, Style style){
		NumberFormat formatter_2sole = new DecimalFormat("#,###,###.###");
		cell = cells.getCell(chucai + String.valueOf(chiso));
		if(chucai.equals("F") || chucai.equals("H") || chucai.equals("I"))
		{
			cell.setValue(formatter_2sole.format(Double.parseDouble(noidung)));
			style.setHAlignment(TextAlignmentType.RIGHT);
		}
		else{
			cell.setValue(noidung);
			style.setHAlignment(TextAlignmentType.LEFT);
		}
		style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
       
        cell.setStyle(style);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
	}

}

