package geso.erp.servlets.chuyenkho_tan;

import geso.dms.center.util.Utility;
import geso.erp.beans.chuyenkho_tan.IChuyenkho_tan;
import geso.erp.beans.chuyenkho_tan.imp.Chuyenkho_tan;
import geso.erp.db.sql.dbutils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aspose.cells.BorderLineType;
import com.aspose.cells.BorderType;
import com.aspose.cells.Cell;
import com.aspose.cells.CellArea;
import com.aspose.cells.Cells;
import com.aspose.cells.Color;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Font;
import com.aspose.cells.Style;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;


public class ChuyenkhoExcelSvl_tan  extends HttpServlet{
	private static final long serialVersionUID = 1L;

    public ChuyenkhoExcelSvl_tan() {
    	super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
	    
	    
	}
	private boolean CreateExcel( OutputStream  out,HttpServletRequest request,HttpServletResponse response) throws IOException{
		boolean isFillData = false;
		FileInputStream fstream = null;
		Workbook workbook = new Workbook();
		
		fstream = new FileInputStream(getServletContext().getInitParameter("path") + "\\chuyenkhoexcel_tan.xlsx");
				
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
	
	private boolean FillData(Workbook workbook){
		
		dbutils db = null;
		try{
			db = new dbutils();
		
		Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);
		
		Cells cells = worksheet.getCells();	
		Cell cell = null;
		Style style = null;
		// Khai báo font
		Font font = new Font();
		font.setName("Arial");
		font.setSize(10);
		
		cell = cells.getCell("B2");
		cell.setValue("CONG TY GIAI PHAP DOANH NGHIEP TOAN CAU ");
		style = cell.getStyle();
		style.setColor(Color.TEAL);
		style.setFont(font);
		
		cell = cells.getCell("B3");
		cell.setValue("Địa chỉ:");
		
		cell = cells.getCell("B4");
		cell.setValue("Số điện thoại");
		
		cell = cells.getCell("D6");
		cell.setValue("Chuyển kho ");
		
		
		// set borderline
		cell = cells.getCell("B9");
		cell.setValue("Mã");
		font.setSize(11);
		font.setBold(true);
		style.setFont(font);
		style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
        cell.setStyle(style);
        
        cell = cells.getCell("C9");
		cell.setValue("Lý do");
		style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
        cell.setStyle(style);
        
        cell = cells.getCell("D9");
		cell.setValue("Trạng thái");
		style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
        cell.setStyle(style);
        
        cell = cells.getCell("E9");
		cell.setValue("Ngày tạo");
		style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
        cell.setStyle(style);
        
        cell = cells.getCell("F9");
		cell.setValue("Người tạo");
		style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
        cell.setStyle(style);
        
        cell = cells.getCell("G9");
		cell.setValue("Ngày sửa");
		style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
        cell.setStyle(style);
        
        cell = cells.getCell("H9");
		cell.setValue("Người sửa");
		style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
        cell.setStyle(style);
        
        // lấy dữ liệu và lặp rồi insert vào các cell tương ứng
		String query = "";
		query = "SELECT CK.PK_SEQ as PK_CHUYENKHO, CK.LYDO, "
				+ "ISNULL(CK.TRANGTHAI,'') AS TRANGTHAI, "
				+ "CK.NGUOITAO, NV.TEN as TENNGUOITAO, CK.NGAYTAO, "
				+ "CK.NGUOISUA, NV2.TEN as TENNGUOISUA, CK.NGAYSUA "
			+ "FROM CHUYENKHO CK "
				+ "LEFT JOIN NHANVIEN NV ON CK.NGUOITAO = NV.PK_SEQ " 
				+ "LEFT JOIN NHANVIEN NV2 ON CK.NGUOISUA = NV.PK_SEQ ";
		 
		ResultSet rs=db.get(query); 
		
		int  sott=1;
		while(rs.next()){
			String[] spTitles = {"B", "C", "D", "E", "F", "G", "H"};
			String[] spTitles2 = {rs.getString("PK_CHUYENKHO"), rs.getString("LYDO"), 
					rs.getString("TRANGTHAI"), rs.getString("TENNGUOITAO"), 
				rs.getString("NGAYTAO"), rs.getString("TENNGUOISUA"), rs.getString("NGAYSUA")};
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
			sott++ ;
		}
        
		return true;
		} catch ( Exception ex){
			ex.printStackTrace();
			return false;
		} finally{
			db.shutDown();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		
	}

	
}
