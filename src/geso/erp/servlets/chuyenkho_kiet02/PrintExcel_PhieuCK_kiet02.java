package geso.erp.servlets.chuyenkho_kiet02;

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

@WebServlet("/PrintExcel_PhieuCK_kiet02")
public class PrintExcel_PhieuCK_kiet02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PrintExcel_PhieuCK_kiet02() {
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
				"attachment; filename=PhieuChuyenKho_kiet.xlsx");
		
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
		
		fstream = new FileInputStream(getServletContext().getInitParameter("path_kiet2") + "\\PhieuChuyenKho_kiet.xlsx");
		
		workbook.open(fstream);
		workbook.setFileFormatType(FileFormatType.EXCEL2007);
		
		isFillData = FillData(workbook, request);
		   
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

	private boolean FillData(Workbook workbook, HttpServletRequest request) {
		
		String id = "";
		id = request.getParameter("excel");
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
			cell.setValue("CÔNG TY GIẢI PHÁP DOANH NGHIỆP TOÀN CẦU GESO");
			style = cell.getStyle();
			style.setColor(Color.TEAL);
			style.setFont(font);
			
			cell = cells.getCell("B3");
			cell.setValue("234, Nguyễn Trọng Tuyển, Phú Nhuận");
			
			cell = cells.getCell("D6");
			cell.setValue("PHIẾU CHUYỂN KHO");
			
			String query = "";
			query = "select CK.PK_SEQ as MaCT, CK.NGAYTAO as NgayLap, K.TENKHO as KhoNhan, K1.TENKHO as KhoChuyen, "
				+ " K.DIACHI as DCNhanHang, K.DIACHI as DCGiaoHang, CK.LYDO as LyDo "
				+ " from CHUYENKHO CK left join KHO K on K.PK_SEQ= CK.KHONHAN "
				+ " left join KHO K1 on K1.PK_SEQ= CK.KHOCHUYEN where CK.PK_SEQ= "+ id;
			
			String MaCT = "", NgayLap = "", KhoNhan = "", KhoChuyen = "", DCNhanHang = "",
					DCGiaoHang = "", LyDo = "";
			
			ResultSet resSet = db.get(query);
			while (resSet.next()){
				String[] data = {
						MaCT = resSet.getString("MaCT"),
						NgayLap = resSet.getString("NgayLap"),
						KhoNhan = resSet.getString("KhoNhan"),
						KhoChuyen = resSet.getString("KhoChuyen"),
						DCNhanHang = resSet.getString("DCNhanHang"),
						DCGiaoHang = resSet.getString("DCGiaoHang"),
						LyDo = resSet.getString("LyDo") };
			
			cell = cells.getCell("B8");
			cell.setValue("Mã CT: "+ MaCT);
			
	        cell = cells.getCell("F8");
			cell.setValue("Ngày lập: "+ NgayLap);
	        
	        cell = cells.getCell("B9");
			cell.setValue("Từ kho: "+ KhoChuyen);
	        
	        cell = cells.getCell("F9");
			cell.setValue("Đến kho: "+ KhoNhan);
			
	        cell = cells.getCell("B10");
			cell.setValue("ĐC nhận hàng: "+ DCNhanHang);
	        
	        cell = cells.getCell("F10");
			cell.setValue("ĐC giao hàng: "+ DCGiaoHang);
	        
	        cell = cells.getCell("B11");
			cell.setValue("Lý do chuyển: "+ LyDo);
			}
			
			cell = cells.getCell("D12");
			cell.setValue("THÔNG TIN CHI TIẾT");
			
			cell = cells.getCell("C18");
			cell.setValue("Trưởng Phòng Cung Ứng");
			
			cell = cells.getCell("F18");
			cell.setValue("Người Nhận");
			//----------------------------------
			cell = cells.getCell("B14");
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
			
			cell = cells.getCell("C14");
			cell.setValue("Mã SP");
			font.setSize(11);
			font.setBold(true);
			style.setFont(font);
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        style.setHAlignment(TextAlignmentType.CENTER);
	        cell.setStyle(style);
			
	        cell = cells.getCell("D14");
			cell.setValue("Tên SP");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
			
	        cell = cells.getCell("E14");
			cell.setValue("Đơn Vị");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
			
	        cell = cells.getCell("F14");
			cell.setValue("Số Lượng Chuyển");
			style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
	        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
	        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
	        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
	        cell.setStyle(style);
			
	     // lấy dữ liệu và lặp rồi insert vào các cell tương ứng
			
			query = "select SP.MA as MaSP, SP.TEN as TenSP, DV.MA as DonVi, CK_SP.SOLUONG as SoLuong "
				+ " from ERP_SANPHAM SP left join DONVI DV on DV.PK_SEQ = SP.DONVI_FK "
				+ " left join CHUYENKHO_SANPHAM CK_SP on CK_SP.SANPHAM_FK = SP.PK_SEQ "
				+ " where CK_SP.CHUYENKHO_FK= "+ id;
			ResultSet rs=db.get(query); 
			int sott = 1;
			while (rs.next()) {
				String[] spTitles = { "B", "C", "D", "E", "F"};
				String[] spTitles2 = { sott + "", rs.getString("MaSP"),
						rs.getString("TenSP"), rs.getString("DonVi"),
						rs.getString("SoLuong") };

				for (int z = 0; z < spTitles2.length; z++) {
					cell = cells.getCell(spTitles[z]
							+ Integer.toString(14 + sott));
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
			//----------------------------------------------
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Lỗi: "+ e.getMessage());
			return false;
		} finally{
			db.shutDown();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
