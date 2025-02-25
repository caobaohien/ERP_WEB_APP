package geso.erp.servlets.sanpham_trinh;



import geso.dms.center.util.Utility;
import geso.erp.beans.banhang.IBanhang;
import geso.erp.beans.banhang.imp.Banhang;
import geso.erp.beans.donbanhang_trinh.IChiTietDonHang_Trinh;
import geso.erp.beans.donbanhang_trinh.IDonBanHang_Trinh;
import geso.erp.beans.donbanhang_trinh.imp.*;

import geso.erp.db.sql.dbutils;
import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TryCatchFinally;

import com.aspose.cells.BorderLineType;
import com.aspose.cells.BorderType;
import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.Color;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Font;
import com.aspose.cells.Style;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;

public class DonBanHangInExcel_TrinhSvl  extends HttpServlet{
	private static final long serialVersionUID = 1L;

    public DonBanHangInExcel_TrinhSvl() {
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
	    String maDonHang = util.getId(querystring);
	    String nextJSP = "/TrainingGESO/pages/Erp/DonBanHangDisplay_Trinh.jsp";
	    
	    //khởi tạo đối tượng
	    IDonBanHang_Trinh obj = new DonBanHang_Trinh();
	    obj.setMaDonHang(maDonHang);
	    obj.init("");
	    
	    OutputStream out = response.getOutputStream();
	    if(action.equals("excel")){
	    	
	    	response.setContentType("application/xls");
			response.setHeader("Content-Disposition", "attachment; filename=DonBanHangInExcel_Trinh.xls");
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
	
	private boolean CreateExcel( OutputStream  out,HttpServletRequest request,HttpServletResponse response,IDonBanHang_Trinh obj) throws IOException{
		boolean isFillData = false;
		FileInputStream fstream = null;
		Workbook workbook = new Workbook();
		
		fstream = new FileInputStream(getServletContext().getInitParameter("path") + "\\DonBanHangInExcel_Trinh.xls");
				
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
	
	private boolean FillData(Workbook workbook, IDonBanHang_Trinh obj){
		dbutils db = null;
		try {
			db = new dbutils();
			
			//khởi tạo 1 worksheet
			Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0); //tạo 1 worksheet ở vị trí 0
			
			//khỏi tạo 1 cell
			Cells cells = worksheet.getCells();
			Cell cell = null;
			Style style = null;
			//khai báo font
			Font font = new Font();
			font.setName("Arial");
			font.setSize(11);
			
			cell = cells.getCell("B2");
			cell.setValue("CÔNG TY GIẢI PHÁP DOANH NGHIỆP TOÀN CẦU");
			style = cell.getStyle();
			style.setColor(Color.RED);
			style.setFont(font);
			
			
			cell = cells.getCell("B5");
			cell.setValue("Tên:");
			cell = cells.getCell("B6");
			cell.setValue("Địa chỉ:");
			cell = cells.getCell("B7");
			cell.setValue("Số điện thoại:");
			
			cell = cells.getCell("D9");
			cell.setValue("ĐƠN BÁN HÀNG");
			
			cell = cells.getCell("B10");
			cell.setValue("STT");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
			
			
	        cell = cells.getCell("C10");
			cell.setValue("Mã SP");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
	        
	        cell = cells.getCell("D10");
			cell.setValue("Tên SP");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
	        
	        cell = cells.getCell("E10");
			cell.setValue("Đon vị tính");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
	        
	        
	        cell = cells.getCell("F10");
			cell.setValue("Đơn giá");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
	        
	        
	        cell = cells.getCell("G10");
			cell.setValue("Số lượng");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
	        
	        
	        cell = cells.getCell("H10");
			cell.setValue("Thành tiền");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
	        
	        
	        List<IChiTietDonHang_Trinh> ctdh = obj.getDanhsachSanPham();
	        int index = 11;
	        for(int i= 0; i< ctdh.size(); i++ ){
	        	//STT
	        	fillChiTietDonHang(index, "B", String.valueOf(i+1), cell, cells, style);
	        	//ma sản phẩm
	        	fillChiTietDonHang(index, "C", ctdh.get(i).getMaSanPham(), cell, cells, style);	        	
	        	//tên sản phẩm
	        	fillChiTietDonHang(index, "D", ctdh.get(i).getTenSanPham(), cell, cells, style);
	        	//đơn vị tính
	        	fillChiTietDonHang(index, "E", ctdh.get(i).getDonViTinh(), cell, cells, style);
	        	//đơn giá
	        	fillChiTietDonHang(index, "F", String.valueOf(ctdh.get(i).getDonGia()), cell, cells, style);	        	
	        	//số lượng
	        	fillChiTietDonHang(index, "G", String.valueOf(ctdh.get(i).getSoLuong()), cell, cells, style);
	        	//thành tiền
	        	fillChiTietDonHang(index, "H", String.valueOf(ctdh.get(i).getThanhTien()), cell, cells, style);
	        	index ++;
	        }
	        
	        
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
		cell = cells.getCell(chucai + String.valueOf(chiso));
		cell.setValue(noidung);
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

