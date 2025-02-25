package geso.erp.servlets.chuyenkho_kiet02;

import geso.dms.center.util.Utility;
import geso.erp.beans.chuyenkho_kiet02.IChuyenKhoList;
import geso.erp.beans.chuyenkho_kiet02.imp.ChuyenKhoList;
import geso.erp.db.sql.dbutils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
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

@WebServlet("/Excel_ChuyenKhoSvl_kiet02")
public class Excel_ChuyenKhoSvl_kiet02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Excel_ChuyenKhoSvl_kiet02() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		Utility util = new Utility();

		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);

		if (userId.length() == 0)
			userId = util.antiSQLInspection(request.getParameter("userId"));
		
		response.setContentType("application/xlsx");
		response.setHeader("Content-Disposition",
				"attachment; filename=ChuyenKho_kiet.xlsx");
		
		OutputStream out = response.getOutputStream();
		CreateExcel(out, request, response);
		
	}
	
	private boolean CreateExcel(OutputStream out, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		boolean isFillData = false;
		FileInputStream fstream = null;
		Workbook workbook = new Workbook();
		
		fstream = new FileInputStream(getServletContext().getInitParameter("path_kiet") + "\\ChuyenKho_kiet.xlsx");
		
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
			cell.setValue("CÔNG TY GIẢI PHÁP DOANH NGHIỆP TOÀN CẦU GESO 666 ");
			style = cell.getStyle();
			style.setColor(Color.TEAL);
			style.setFont(font);
			
			cell = cells.getCell("B3");
			cell.setValue("Địa chỉ:");
			
			cell = cells.getCell("B4");
			cell.setValue("Số điện thoại:");
			
			cell = cells.getCell("F6");
			cell.setValue("CHUYỂN KHO LIVERPOOL FC");
			//----------------------------------
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
			cell.setValue("Mã CK");
			font.setSize(11);
			font.setBold(true);
			style.setFont(font);
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        style.setHAlignment(TextAlignmentType.CENTER);
	        cell.setStyle(style);
			
	        cell = cells.getCell("D9");
			cell.setValue("Kho Chuyển");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
			
	        cell = cells.getCell("E9");
			cell.setValue("Kho Nhận");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
			
	        cell = cells.getCell("F9");
			cell.setValue("Lý Do Chuyển");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
			
	        cell = cells.getCell("G9");
			cell.setValue("Người Tạo");
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
			cell.setValue("Người Sửa");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
			
	        cell = cells.getCell("J9");
			cell.setValue("Ngày Sửa");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
			
	        cell = cells.getCell("K9");
			cell.setValue("Trạng Thái");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
			
	     // lấy dữ liệu và lặp rồi insert vào các cell tương ứng
			String query = "";
			query = " SELECT ISNULL(ck.TRANGTHAI,'') AS trangthai,ck.PK_SEQ as ma , "
					+ " k.tenkho as khonhan,k1.tenkho as khochuyen,ck.NGAYTAO as ngaytao ,ck.NGAYSUA as ngaysua, "
					+ " NV.TEN as nguoitao,NV1.TEN as nguoisua,ck.lydo as lydo "
					+ " FROM chuyenkho ck LEFT JOIN KHO k ON CK.KHONHAN=K.PK_SEQ LEFT JOIN KHO k1 ON CK.KHOchuyen=K1.PK_SEQ"
					+ " LEFT JOIN NHANVIEN NV ON CK.NGUOITAO=NV.PK_SEQ LEFT JOIN NHANVIEN NV1 ON CK.NGUOISUA=NV1.PK_SEQ ";
			
			ResultSet rs=db.get(query); 
			int sott = 1;
			while (rs.next()) {
				String[] spTitles = { "B", "C", "D", "E", "F", "G", "H", "I",
						"J", "K" };
				String[] spTitles2 = { sott + "", rs.getString("ma"),
						rs.getString("khochuyen"), rs.getString("khonhan"),
						rs.getString("lydo"), rs.getString("nguoitao"),
						rs.getString("ngaytao"), rs.getString("nguoisua"),
						rs.getString("ngaysua"), rs.getString("trangthai") };

				for (int z = 0; z < spTitles2.length; z++) {
					cell = cells.getCell(spTitles[z]
							+ Integer.toString(11 + sott));
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
