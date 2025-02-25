package geso.erp.servlets.reports;

import geso.erp.db.sql.dbutils;
import geso.erp.servlets.reports.ReportAPI;
import geso.dms.distributor.util.Utility;
import geso.erp.beans.baocao.IBaocao;
import geso.erp.beans.baocao.imp.Baocao;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

public class ErpBCHangxuatSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    public ErpBCHangxuatSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IBaocao obj;
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    	    
	    HttpSession session = request.getSession();	    

	    Utility util = new Utility();
	    	    
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    obj = new Baocao();
	    obj.setUserId(userId);
	    
		session.setAttribute("obj", obj);
				
		String nextJSP = "/TrainingGESO/pages/Erp/ErpBCHangXuatKho.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		OutputStream out = response.getOutputStream(); 
		IBaocao obj;
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    	    
	    HttpSession session = request.getSession();	    
	    
	    String userTen = (String) session.getAttribute("userTen");
	    String userId = request.getParameter("userId");

	    obj = new Baocao();
	    obj.setUserId(userId);
	    obj.setUserTen(userTen);
	    
	    String tungay = request.getParameter("tungay");
	    if(tungay == null)
	    	tungay = "";
	    obj.setTuNgay(tungay);
	    
	    String denngay = request.getParameter("denngay");
	    if(denngay == null)
	    	denngay = "";
	    obj.setDenNgay(denngay);
	   
	    String action = request.getParameter("action");
	    System.out.println("Action nhan duoc: " + action);
	    
	    if(action.equals("search"))
	    {
	    	 
	    	session.setAttribute("obj", obj);
	    	
			
			String nextJSP = "/TrainingGESO/pages/Erp/ErpBCHangXuatKho.jsp";
			response.sendRedirect(nextJSP);
	    }
	    else
	    {
	    	response.setContentType("application/xlsm");
			response.setHeader("Content-Disposition", "attachment; filename=HangXuatKhod.xlsm");
			
	    	boolean isTrue = false;
			try 
			{
				isTrue = CreatePivotTable(out, obj, "");	
			} 
			catch (Exception e) 
			
			{
				e.printStackTrace();
				isTrue = false;
			}
			
			if(!isTrue)
			{
				 
				session.setAttribute("obj", obj);
				obj.setMsg("Không thể tạo báo cáo");
				
				String nextJSP = "/TrainingGESO/pages/Erp/ErpBCHangXuatKho.jsp";
				response.sendRedirect(nextJSP);
			}
	    }
	}
	

	private boolean CreatePivotTable(OutputStream out, IBaocao obj, String condition) throws Exception
    {   
		FileInputStream fstream = null;
		Workbook workbook = new Workbook();		
		 
		fstream = new FileInputStream(getServletContext().getInitParameter("path") + "\\ErpBCHangxuatKho.xlsm");

		workbook.open(fstream);
		workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
		
	     CreateStaticHeader(workbook, obj );	     
	     boolean isTrue = CreateStaticData(workbook, obj, condition);
	     if(!isTrue){
	    	 return false;
	     }
	     workbook.save(out);
			
		fstream.close();
	     return true;
   }
	
	private void CreateStaticHeader(Workbook workbook,IBaocao  obj) 
	{
		 String dateFrom= obj.getTuNgay(); String dateTo =obj.getDenNgay(); String UserName=obj.getUserTen();
 
		Worksheets worksheets = workbook.getWorksheets();
	    Worksheet worksheet = worksheets.getSheet(0);
	   	   
	    worksheet.setName("Sheet1");
	    
	    Cells cells = worksheet.getCells();
		
	    Style style;
	    Font font = new Font();
	    font.setColor(Color.RED);//mau chu
	    font.setSize(16);// size chu
	   	font.setBold(true);
	   	
	    cells.setRowHeight(0, 20.0f);
	    Cell cell = cells.getCell("A1");
	    style = cell.getStyle();
	    style.setFont(font); 
	    style.setHAlignment(TextAlignmentType.LEFT);// canh le cho chu 	  
	    
	    String tieude = "BÁO CÁO HÀNG XUẤT KHO";
	    ReportAPI.getCellStyle(cell,Color.RED, true, 14, tieude);
	           
	    cells.setRowHeight(4, 18.0f);
	    cell = cells.getCell("A2");
	    ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "Từ ngày: " + dateFrom);
	    
	    cells.setRowHeight(4, 18.0f);
	    cell = cells.getCell("B2");
	    ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "Đến ngày: " + dateTo);
	    
	    cells.setRowHeight(4, 18.0f);
	    cell = cells.getCell("A3");
	    ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "Ngày báo cáo: " + ReportAPI.NOW("yyyy-MM-dd"));
	    
	    cells.setRowHeight(5, 18.0f);
	    cell = cells.getCell("A4");
	    ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "Được tạo bởi:  " + UserName);
	     
	    
			cell = cells.getCell("FA1");cell.setValue("KhoXuat");ReportAPI.setCellHeader(cell);
			cell = cells.getCell("FB1");cell.setValue("KhoNhan");ReportAPI.setCellHeader(cell);
			 
			cell = cells.getCell("FC1");cell.setValue("SoChungTu");ReportAPI.setCellHeader(cell);
			cell = cells.getCell("FD1");cell.setValue("NgayChungTu");ReportAPI.setCellHeader(cell);
			 
			cell = cells.getCell("FE1");cell.setValue("MaSanPham");ReportAPI.setCellHeader(cell);
			cell = cells.getCell("FF1");cell.setValue("TenSanPham");ReportAPI.setCellHeader(cell);
 
			cell = cells.getCell("FG1");cell.setValue("SoLuong");ReportAPI.setCellHeader(cell);
		 
	    	
	}
	
	private boolean CreateStaticData(Workbook workbook, IBaocao obj, String condition) throws Exception
	{
		dbutils db = new dbutils();
		Worksheets worksheets = workbook.getWorksheets();
	    Worksheet worksheet = worksheets.getSheet(0);
	    Cells cells = worksheet.getCells();
		
	    String[] param = new String[8];
		param[0] = obj.getTuNgay().equals("") ? null : obj.getTuNgay();
		param[1] = obj.getDenNgay().equals("") ? null : obj.getDenNgay();
		param[2] = obj.getUserId().equals("") ? null : obj.getUserId();
		String	query=" SELECT CK.PK_SEQ AS SOCHUNGTU, CK.NGAYCHUYENKHO,CKSP.SOLUONG,SP.MA,SP.TEN " +
				" ,KC.TENKHO AS KHOCHUYEN, KN.TENKHO AS KHONHAN "+
			" FROM CHUYENKHO  CK   "+
			" INNER JOIN CHUYENKHO_sANPHAM  CKSP ON CK.PK_sEQ=CKSP.CHUYENKHO_FK  "+
			" INNER JOIN ERP_SANPHAM SP ON SP.PK_sEQ=CKSP.SANPHAM_FK "+
			" INNER JOIN KHO KC ON KC.PK_SEQ=CK.KHOCHUYEN  "+
			" INNER JOIN KHO KN ON KN.PK_SEQ=CK.KHONHAN "+ 
			" WHERE CK.NGAYCHUYENKHO >='"+obj.getTuNgay()+"' and  CK.NGAYCHUYENKHO <='"+obj.getDenNgay()+"'";
		ResultSet rs = db.get(query);
		
		int i = 2;
		  
			try
			{
				cells.setColumnWidth(0, 15.0f);
				cells.setColumnWidth(1, 15.0f);
				cells.setColumnWidth(2, 15.0f);
				cells.setColumnWidth(3, 15.0f);
				cells.setColumnWidth(4, 15.0f);
				cells.setColumnWidth(5, 15.0f);
				cells.setColumnWidth(6, 15.0f);
				cells.setColumnWidth(7, 15.0f);
				cells.setColumnWidth(8, 15.0f);

				Cell cell = null;
				while (rs.next())
				{
						cell = cells.getCell("FA" + Integer.toString(i));cell.setValue(rs.getString("KHOCHUYEN"));
						cell = cells.getCell("FB" + Integer.toString(i));cell.setValue(rs.getString("KhoNHAN"));
						
						cell = cells.getCell("FC" + Integer.toString(i));cell.setValue(rs.getString("SOCHUNGTU"));
						cell = cells.getCell("FD" + Integer.toString(i));cell.setValue(rs.getString("NGAYCHUYENKHO"));
						cell = cells.getCell("FE" + Integer.toString(i));cell.setValue(rs.getString("MA"));
						cell = cells.getCell("FF" + Integer.toString(i));cell.setValue(rs.getString("ten") );
						cell = cells.getCell("FG" + Integer.toString(i));cell.setValue(rs.getDouble("soluong"));
 					i++;
				}
				if (rs != null)
					rs.close();
				if (db != null)
					db.shutDown();
				if (i == 2)
				{
					throw new Exception("Khong co bao cao trong thoi gian nay...");
				}
			} catch (Exception e)
			{
				System.out.println("115.Exception: " + e.getMessage());
				e.printStackTrace();
				throw new Exception(e.getMessage());
			}
 		if (db != null)
			db.shutDown();
		return true;	
	}
}
