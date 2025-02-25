package geso.erp.servlets.donbanhang_kiet02;

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

@WebServlet("/Excel_DonHangSvl_kiet02")
public class Excel_DonHangSvl_kiet02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Excel_DonHangSvl_kiet02() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();
	    Utility util = new Utility();
	    
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
		
	    //lấy action được truyền vào
	    String action = request.getParameter("action");
		if (action == null) {
			action = "";
		}
		
		Create_EXCEL(request, response);
	}

	private void Create_EXCEL(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		response.setContentType("application/xlsx");
		response.setHeader("Content-Disposition",
				"attachment; filename=DonHang_kiet02.xlsx");
		OutputStream out = response.getOutputStream();
		
		CreateExcel(out, request, response);
	}
	
	private boolean CreateExcel(OutputStream out, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		boolean isFillData = false;
		FileInputStream fstream = null;
		Workbook workbook = new Workbook();
		
		fstream = new FileInputStream(getServletContext().getInitParameter("path_kiet") + "\\DonHang_kiet02.xlsx");
		
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
		
		dbutils db = new dbutils();
		try {
			Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);
			
			Cells cells = worksheet.getCells();
			Cell cell = null;
			Style style = null;
			
			Font font = new Font();
			font.setName("Arial");
			font.setSize(10);
			
			cell = cells.getCell("B2");
			cell.setValue("CÔNG TY GIẢI PHÁP DOANH NGHIỆP TOÀN CẦU GESO 123 ");
			style = cell.getStyle();
			style.setColor(Color.TEAL);
			style.setFont(font);
			
			cell = cells.getCell("B3");
			cell.setValue("Địa chỉ:");
			
			cell = cells.getCell("B4");
			cell.setValue("Số điện thoại:");
			
			cell = cells.getCell("D6");
			cell.setValue("ĐƠN HÀNG LIVERPOOL FC");
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
			cell.setValue("Mã ĐH");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
	        
	        cell = cells.getCell("D9");
			cell.setValue("Ngày CT");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
	        
	        cell = cells.getCell("E9");
			cell.setValue("Khách Hàng");
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
	        
	        cell = cells.getCell("G9");
			cell.setValue("Tổng Tiền");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
	        
	        cell = cells.getCell("H9");
			cell.setValue("Ngày Tạo");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
	        
	        cell = cells.getCell("I9");
			cell.setValue("Người Tạo");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
			
	     // lấy dữ liệu và lặp rồi insert vào các cell tương ứng
			String query = "";
			query = "SELECT BH.PK_SEQ AS MADONHANG, "
					+ "BH.NGAYCHUNGTU AS NGAYCHUNGTU, "
					+ "KH.TEN AS TENKHACHHANG, "
					+ "ISNULL(BH.TRANGTHAI, '') AS TRANGTHAI, "
					+ "BH.TONGTIEN, " + "BH.NGAYTAO, " +

					"NV.TEN AS TENNGUOITAO " + "FROM BANHANG BH "
					+ "LEFT JOIN KHACHHANG KH ON BH.KHACHHANG_FK = KH.PK_SEQ "
					+ "LEFT JOIN NHANVIEN NV ON BH.NGUOITAO = NV.PK_SEQ "
					+ "ORDER BY MADONHANG ASC";
			 
			ResultSet rs=db.get(query); 
			
			int sott = 1;
			while (rs.next()){
				String[] spTitles = { "B", "C", "D", "E", "F", "G", "H", "I" };
				String[] spTitles2 = { sott + "", rs.getString("MADONHANG"),
						rs.getString("NGAYCHUNGTU"),
						rs.getString("TENKHACHHANG"),
						rs.getString("TRANGTHAI"), rs.getString("TONGTIEN"),
						rs.getString("NGAYTAO"), rs.getString("TENNGUOITAO") };
				
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
		} finally{
			db.shutDown();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
