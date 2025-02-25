package geso.erp.servlets.sanpham_kiet06;

import geso.dms.center.util.Utility;
import geso.erp.db.sql.dbutils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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

@WebServlet("/Excel_SanPhamSvl_kiet")
public class Excel_SanPhamSvl_kiet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Excel_SanPhamSvl_kiet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		response.setContentType("application/xlsx");
		response.setHeader("Content-Disposition",
				"attachment; filename=SanPham_kiet.xlsx");
	    
	    HttpSession session = request.getSession();
	    Utility util = new Utility();
	    
	    // get các tham số được truyền từ client bằng phương thức GET
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String action = util.getAction(querystring);
	    String id = util.getId(querystring);
		
	    OutputStream out = response.getOutputStream();
		CreateExcel(out, request, response, id);
		
	}

	private boolean CreateExcel( OutputStream  out,HttpServletRequest request,HttpServletResponse response,String id) throws IOException{
	
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		boolean isFillData = false;
		FileInputStream fstream = null;
		Workbook workbook = new Workbook();
		
		fstream = new FileInputStream(getServletContext().getInitParameter("path_kiet") + "\\SanPham_kiet.xlsx");
		
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
	private boolean FillData(Workbook workbook) {
		
		dbutils db = null;
		try {
			db = new dbutils();

			Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);

			Cells cells = worksheet.getCells();
			Cell cell = null;
			Style style = null;
			// Khai báo font
			Font font = new Font();
			font.setName("Times New Roman");
			font.setSize(10);

			cell = cells.getCell("B2");
			cell.setValue("CÔNG TY GIẢI PHÁP DOANH NGHIỆP TOÀN CẦU GESO 456 ");
			style = cell.getStyle();
			style.setColor(Color.TEAL);
			font.setBold(true);
			font.setSize(13);
			style.setFont(font);
			//------------------------------
			cell = cells.getCell("B3");
			cell.setValue("Address:");
			
			cell = cells.getCell("B4");
			cell.setValue("Numphone:");
			
			cell = cells.getCell("D6");
			cell.setValue("SẢN PHẨM LIVERPOOL FC");
			
			//----------------------------------
			// set borderline
			cell = cells.getCell("B9");
			cell.setValue("STT");
			font.setSize(11);
			font.setBold(true);
			style.setFont(font);
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        style.setHAlignment(TextAlignmentType.CENTER);
	        cell.setStyle(style);
	        
	        cell = cells.getCell("C9");
			cell.setValue("Mã SP");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
	        
	        cell = cells.getCell("D9");
			cell.setValue("Tên SP");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
	        
	        cell = cells.getCell("E9");
			cell.setValue("Đơn Vị");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
	        
	        cell = cells.getCell("F9");
			cell.setValue("Trạng Thái");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
			
	        // lấy dữ liệu và lặp rồi insert vào các cell tương ứng
			String query = "";
			query = "select SP.PK_SEQ, SP.TEN, SP.MA, SP.TRANGTHAI, DV.MA as DonVi "
				+ "from SANPHAM SP left join DONVI DV on DV.PK_SEQ = SP.DONVI_FK";
			
			ResultSet rs=db.get(query); 
			int sott = 1;
			
			while (rs.next()){
				String[] spTitles = {"B", "C", "D", "E", "F"};
				String[] spTitles2 = { sott + "", rs.getString("Ma"),
						rs.getString("Ten"), rs.getString("DonVi"),
						rs.getString("TRANGTHAI"), };
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
				sott++;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			db.shutDown();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
