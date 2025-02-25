package geso.erp.servlets.donmuahang_tan;

import geso.dms.center.util.Utility;
import geso.erp.beans.donmuahang_tan.IDonmuahangNew_tan;
import geso.erp.beans.donmuahang_tan.imp.DonmuahangNew_tan;
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


public class DonmuahangInExcelSvl_tan  extends HttpServlet{
	private static final long serialVersionUID = 1L;

    public DonmuahangInExcelSvl_tan() {
    	super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
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
	    String DonmuahangId = util.getId(querystring);
	    String nextJSP = "/TrainingGESO/pages/Erp/DonmuahangNew_tan.jsp";
	    IDonmuahangNew_tan obj=new DonmuahangNew_tan(DonmuahangId);
	    obj.init();
	    obj.CreateRs();
	    OutputStream out = response.getOutputStream();
	    obj.setIsCapNhat("0");
  
		session.setAttribute("obj", obj);
		
	    if(action.equals("excel")){
	    	
	    	response.setContentType("application/xlsx");
			response.setHeader("Content-Disposition", "attachment; filename=TESTMUAHANG.xlsx");
	    	if(!CreateExcel(out, request, response, DonmuahangId)){
	    		response.setContentType("text/html");
			    PrintWriter writer = new PrintWriter(out);
			    writer.print("Lỗi xuất excel");
			    writer.close();
	    	}
	    }else {
	    	// tuỳ theo các action khác mà sử dụng
	    	session.setAttribute("obj", obj);
			response.sendRedirect(nextJSP);
			return;
	    }
	    session.setAttribute("obj", obj);
		response.sendRedirect(nextJSP);
		return;
	    
	}
	private boolean CreateExcel( OutputStream  out,HttpServletRequest request,HttpServletResponse response,String id) throws IOException{
		boolean isFillData = false;
		FileInputStream fstream = null;
		Workbook workbook = new Workbook();
		
		fstream = new FileInputStream(getServletContext().getInitParameter("path") + "\\donmuahangexcel_tan.xlsx");
				
		workbook.open(fstream);
		workbook.setFileFormatType(FileFormatType.EXCEL2007);
		
		isFillData = FillData(workbook, id);
   
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
	
	private boolean FillData(Workbook workbook, String id){
		
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
		cell.setValue("ĐƠN MUA HÀNG ");
		cell = cells.getCell("C7");
		cell.setValue("Mã mua hàng: " + id);
		
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
        cell.setStyle(style);
        
        cell = cells.getCell("C9");
		cell.setValue("Mã sản phẩm");
		style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
        cell.setStyle(style);
        
        cell = cells.getCell("D9");
		cell.setValue("Tên sản phẩm");
		style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
        cell.setStyle(style);
        
        cell = cells.getCell("E9");
		cell.setValue("ĐVT");
		style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
        cell.setStyle(style);
        
        cell = cells.getCell("F9");
		cell.setValue("Số lượng");
		style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
        cell.setStyle(style);
        
        cell = cells.getCell("G9");
		cell.setValue("Đơn giá");
		style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
        cell.setStyle(style);
        
        cell = cells.getCell("H9");
		cell.setValue("Thành tiền");
		style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
        cell.setStyle(style);
        
        // lấy dữ liệu và lặp rồi insert vào các cell tương ứng
		String query = "";
		query = "SELECT MHSP.MUAHANG_FK as MAMH, SP.MA as MASP, SP.TEN as TENSP, DV.TEN as DVT," 
					+ "MHSP.SOLUONG, MHSP.DONGIA, MHSP.THANHTIEN "
				+ "FROM MUAHANG_SANPHAM MHSP "
					+ "LEFT JOIN SANPHAM SP ON SP.PK_SEQ = MHSP.SANPHAM_FK "
					+ "LEFT JOIN DONVI DV ON DV.PK_SEQ = SP.DONVI_FK "
				+ "WHERE MHSP.MUAHANG_FK = "+id+"";
		 
		ResultSet rs=db.get(query); 
		
		int  sott=1;
		float tongtien=0f;
		while(rs.next()){
			String[] spTitles = {"B", "C", "D", "E", "F", "G", "H"};
			String[] spTitles2 = {""+sott+"", rs.getString("MASP"), rs.getString("TENSP"), rs.getString("DVT"), 
				rs.getString("SOLUONG"), rs.getString("DONGIA"), rs.getString("THANHTIEN")};
			for(int z = 0; z < spTitles2.length; z++) {
				cell = cells.getCell(spTitles[z]+Integer.toString(9+sott));
				if(checkNumber(spTitles2[z])==true && z!=0){
					cell.setValue(Float.parseFloat(spTitles2[z]));
				}else{
					cell.setValue(spTitles2[z]);
				}
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
			tongtien += rs.getFloat("THANHTIEN");
			sott++ ;
		}

		font.setSize(12);
		font.setBold(true);
		style.setFont(font);
		style.setColor(Color.SILVER);
		style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
        
        cell=cells.getCell("B"+Integer.toString(9+sott));
		cell.setValue("TỔNG TIỀN ");
	    cell.setStyle(style);
        worksheet.getCells().merge(8+sott, 1, 8+sott, 6);
	    cell=cells.getCell("C"+Integer.toString(9+sott));
	    cell.setStyle(style);
	    cell=cells.getCell("D"+Integer.toString(9+sott));
	    cell.setStyle(style);
	    cell=cells.getCell("E"+Integer.toString(9+sott));
	    cell.setStyle(style);
	    cell=cells.getCell("F"+Integer.toString(9+sott));
	    cell.setStyle(style);
	    cell=cells.getCell("G"+Integer.toString(9+sott));
	    cell.setStyle(style);
        
        cell = cells.getCell("H"+Integer.toString(9+sott));
		cell.setValue(tongtien);
        cell.setStyle(style);
        
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

	private boolean checkNumber(String _str){
		char[] number = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
		boolean b = false;
        char a[] = _str.toCharArray();//tách chuỗi string ra thành các kí tự

        for(int i = 0; i< a.length; i++) {
            label:
            for (int j = 0; j < number.length; j++) {
                if (a[i] == number[j] || a[i] == '.') {
                    b = true;
                    break label;
                }else if(j == number.length-1){
                    return false;
                }
            }
        }
        return b;
	}
}
