package geso.erp.servlets.chuyenkho_tan;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;

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

import geso.dms.center.util.Utility;
import geso.erp.beans.chuyenkho_tan.*;
import geso.erp.beans.chuyenkho_tan.imp.*;
import geso.erp.db.sql.dbutils;

public class ChuyenkhoSvl_tan extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    public ChuyenkhoSvl_tan() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    HttpSession session = request.getSession();	    
	    Utility util = new Utility();
	    //Lấy giá trị idUser
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    //Lấy giá trị action được truyền vào và idChuyenkho ứng với mỗi action đó
	    String action = util.getAction(querystring);
	    IChuyenkho_tan obj=new Chuyenkho_tan();
	    obj.setId(util.getId(querystring));
	 	if (action.equals("delete"))
		{
	 		obj.set_pk_chuyenkho(obj.getId());
	 		obj.Delete_Chuyenkho();
	 		obj.init("");
			
		}else if(action.equals("chot")){
			obj.set_pk_chuyenkho(obj.getId());
	 		obj.Chot_Chuyenkho();
	 		obj.init("");
			
		}
	    String _msg = "";
	    obj.setUserId(userId);
	    if(_msg.length() > 0) obj.setMsg(_msg);
	    obj.init("");	    
		session.setAttribute("obj", obj);
				
		String nextJSP = "/TrainingGESO/pages/Erp/Chuyenkho_tan.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IChuyenkho_tan obj = new Chuyenkho_tan();
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");

	    //lấy action được truyền vào
	    String action = request.getParameter("action");
	    if (action == null){
	    	action = "";
	    }
	    //--- begin: Lấy giá trị nhập vào từ giao diện 
	    String maCK= request.getParameter("txtMaCK");
	    if (maCK == null){
	    	maCK = "";
	    }
	    obj.set_pk_chuyenkho(maCK);
	    String khonhan= request.getParameter("cbKhonhan");
	    if (khonhan == null){
	    	khonhan = "";
	    }
	    obj.setKhonhan(khonhan);
	    String khochuyen= request.getParameter("cbKhochuyen");
	    if (khochuyen == null){
	    	khochuyen = "";
	    }
	    obj.setKhochuyen(khochuyen);
	    String trangthai= request.getParameter("cbTrangthai");
	    if (trangthai == null){
	    	trangthai = "";
	    }
	    obj.setTrangthai(trangthai);
	    //---End!
	    Utility util = new Utility();
		HttpSession session = request.getSession();
	    String userId = util.antiSQLInspection(request.getParameter("userId"));
	    System.out.println("Hanh dong duoc truyen vao la: "+action);
	    //xét hành động truyền vào
	    if(action.equals("timkiem")){
		   obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
		   String search = getSearchQuery(request, obj);		   
		   obj.init(search);
		   session.setAttribute("obj", obj);
		   response.sendRedirect("/TrainingGESO/pages/Erp/Chuyenkho_tan.jsp");
	    	
	    }else if(action.equals("taomoi")){
	    	IChuyenkhoNew_tan CKBean = new ChuyenkhoNew_tan("");
	    	session.setAttribute("obj", CKBean);
	    	session.setAttribute("action", "save");
	    	CKBean.setUserId(userId);
	    	CKBean.CreateRs();
    		String nextJSP = "/TrainingGESO/pages/Erp/ChuyenkhoNew_tan.jsp";
    		response.sendRedirect(nextJSP);
	   }else if(action.equals("refresh")){
		   IChuyenkho_tan ckBean=new Chuyenkho_tan();
		   ckBean.init("");
		   // khởi tạo ra 1 session
		   session.setAttribute("obj", ckBean);
		   String nextJSP = "/TrainingGESO/pages/Erp/Chuyenkho_tan.jsp";
		   response.sendRedirect(nextJSP);
	   }else if(action.equals("excel")){
		   Create_EXCEL(request, response);
	   }
	}
	
	private String getSearchQuery(HttpServletRequest request, IChuyenkho_tan obj){
		String query = "SELECT CK.PK_SEQ as PK_CHUYENKHO, CK.LYDO, "
				+ "ISNULL(CK.TRANGTHAI,'') AS TRANGTHAI, "
				+ "CK.NGUOITAO, NV.TEN as TENNGUOITAO, CK.NGAYTAO, "
				+ "CK.NGUOISUA, NV2.TEN as TENNGUOISUA, CK.NGAYSUA "
			+ "FROM CHUYENKHO CK "
				+ "LEFT JOIN NHANVIEN NV ON CK.NGUOITAO = NV.PK_SEQ " 
				+ "LEFT JOIN NHANVIEN NV2 ON CK.NGUOISUA = NV.PK_SEQ "
			+ "WHERE 1=1 ";
		if(obj.get_pk_chuyenkho().length()>0){
			query +=" AND cast(CK.PK_SEQ as varchar(10)) like '"+obj.get_pk_chuyenkho().toString()+"%'";
		}
		if(obj.getKhochuyen().length()>0){
			query+=" AND CK.KHOCHUYEN = "+obj.getKhochuyen()+"";
		}
		if(obj.getKhonhan().length()>0){
			query+=" AND CK.KHONHAN = "+obj.getKhonhan()+"";
		}
		if(obj.getTrangthai().length()>0){
			query+=" AND CK.TRANGTHAI LIKE '"+obj.getTrangthai()+"'";
		}
		return query;
	}
	
	private void Create_EXCEL(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
	    HttpSession session = request.getSession();
	    Utility util = new Utility();
	    
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
		
	    //lấy action được truyền vào
	    String action = request.getParameter("action");
	    if (action == null){
	    	action = "";
	    }
	    String nextJSP = "/TrainingGESO/pages/Erp/Chuyenkho_tan.jsp";
	    IChuyenkho_tan obj=new Chuyenkho_tan();
	    obj.init("");
	    obj.CreateRs();
	    OutputStream out = response.getOutputStream();
	    //obj.setIsCapNhat("0");
  
		session.setAttribute("obj", obj);
		
	    if(action.equals("excel")){
	    	
	    	response.setContentType("application/xlsx");
			response.setHeader("Content-Disposition", "attachment; filename=TESTCHUYENKHO.xlsx");
	    	if(!CreateExcel(out, request, response)){
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
}
