package geso.erp.servlets.sanpham_nhom2;

import geso.dms.center.util.Utility;
import geso.erp.beans.banhang.IBanhang;
import geso.erp.beans.banhang.imp.Banhang;
import geso.erp.db.sql.dbutils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

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
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;

public class DonHangInExcelSvl  extends HttpServlet{
	private static final long serialVersionUID = 1L;

    public DonHangInExcelSvl() {
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
	    String nextJSP = "/TrainingGESO/pages/Erp/BanhangNew.jsp";
	    IBanhang obj=new Banhang(DonHangId);
	    obj.init();
	    obj.createRs();
	    OutputStream out = response.getOutputStream();
	    
  		obj.setchungtutimkiem(obj.getSochungtu());	
  		obj.setIsCapNhat("0");
  
		session.setAttribute("dmhBean", obj);
		
	    if(action.equals("excel")){
	    	
	    	response.setContentType("application/xls");
			response.setHeader("Content-Disposition", "attachment; filename=DonMuaHang.xls");
	    	if(!CreateExcel(out,request, response,DonHangId)){
	    		response.setContentType("text/html");
			    PrintWriter writer = new PrintWriter(out);
			    writer.print("Lỗi xuất excel");
			    writer.close();
	    	}
	    }else {
	    	// tuỳ theo các action khác mà sử dụng
	    	session.setAttribute("dmhBean", obj);
			response.sendRedirect(nextJSP);
			return;
	    }
	    session.setAttribute("dmhBean", obj);
		response.sendRedirect(nextJSP);
		return;
	    
	}
	private boolean CreateExcel( OutputStream  out,HttpServletRequest request,HttpServletResponse response,String id) throws IOException{
		boolean isFillData = false;
		FileInputStream fstream = null;
		Workbook workbook = new Workbook();
		
		fstream = new FileInputStream(getServletContext().getInitParameter("pathTrinh") + "\\donhangexcel.xls");
				
		workbook.open(fstream);
		workbook.setFileFormatType(FileFormatType.EXCEL97TO2003);

		
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
		style.setColor(Color.GREEN);
		style.setFont(font);
		
		cell = cells.getCell("B3");
		cell.setValue("Địa chỉ:");
		
		cell = cells.getCell("B4");
		cell.setValue("Số điện thoại");
		
		
		cell = cells.getCell("D7");
		cell.setValue("ĐƠN BÁN HÀNG");
		
		
		// set borderline 
		cell = cells.getCell("B9");
		cell.setValue("HEADER1");
		style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
        cell.setStyle(style);
        
        cell = cells.getCell("C9");
		cell.setValue("HEADER2");
		style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
        cell.setStyle(style);
        
        cell = cells.getCell("D9");
		cell.setValue("HEADER3");
		style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
        cell.setStyle(style);
        
        cell = cells.getCell("E9");
		cell.setValue("HEADER4");
		style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
        cell.setStyle(style);
        
        cell = cells.getCell("F9");
		cell.setValue("HEADER4");
		style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
        cell.setStyle(style);
        // lấy dữ liệu và lặp rồi insert vào các cell tương ứng
        
        int index = 17;
        
        for(int i=0; i<10;i++){
        	//sfđsf
        	 cell = cells.getCell("E"+index);
        	index++;
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
