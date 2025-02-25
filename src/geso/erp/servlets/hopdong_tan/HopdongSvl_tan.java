package geso.erp.servlets.hopdong_tan;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

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
import geso.erp.beans.hopdong_tan.*;
import geso.erp.beans.hopdong_tan.imp.*;
import geso.erp.db.sql.dbutils;

public class HopdongSvl_tan extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    public HopdongSvl_tan() {
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
	    IHopdong_tan obj=new Hopdong_tan();
	    obj.setId(util.getId(querystring));
	 	if (action.equals("delete"))
		{
	 		obj.setPk_hopdong(obj.getId());
	 		obj.Delete();
	 		obj.init("");
			
		}else if(action.equals("chot")){
			obj.setPk_hopdong(obj.getId());
	 		obj.Chot();
	 		obj.init("");
			
		}
	    String _msg = "";
	    obj.setUserId(userId);
	    if(_msg.length() > 0) obj.setMsg(_msg);
	    obj.init("");
		session.setAttribute("obj", obj);
				
		String nextJSP = "/TrainingGESO/pages/Erp/Hopdong_tan.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IHopdong_tan obj = new Hopdong_tan();
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");

	    //lấy action được truyền vào
	    String action = request.getParameter("action");
	    if (action == null){
	    	action = "";
	    }
	    //--- begin: Lấy giá trị nhập vào từ giao diện 
	    String maHoso= request.getParameter("cbHoso");
	    if (maHoso == null){
	    	maHoso = "";
	    }
	    obj.setPk_hoso(maHoso);
	    String maHD= request.getParameter("txtMaHD");
	    if (maHD == null){
	    	maHD = "";
	    }
	    obj.setPk_hopdong(maHD);
	    //---End!
	    Utility util = new Utility();
		HttpSession session = request.getSession();
	    String userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    //xét hành động truyền vào
	    if(action.equals("timkiem")){
		   obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
		   String search = getSearchQuery(request, obj);		   
		   obj.init(search);
		   session.setAttribute("obj", obj);
		   response.sendRedirect("/TrainingGESO/pages/Erp/Hopdong_tan.jsp");
	    	
	    }else if(action.equals("taomoi")){
	    	ITaohopdong_tan HDBean = new Taohopdong_tan("");
	    	session.setAttribute("obj", HDBean);
	    	session.setAttribute("action", "save");
	    	HDBean.setUserId(userId);
	    	HDBean.createRs();
    		String nextJSP = "/TrainingGESO/pages/Erp/Taohopdong_tan.jsp";
    		response.sendRedirect(nextJSP);
	   }else if(action.equals("refresh")){
		   IHopdong_tan HDBean=new Hopdong_tan();
		   HDBean.init("");
		   // khởi tạo ra 1 session
		   session.setAttribute("obj", HDBean);
		   String nextJSP = "/TrainingGESO/pages/Erp/Hopdong_tan.jsp";
		   response.sendRedirect(nextJSP);
	   }
	}
	
	private String getSearchQuery(HttpServletRequest request, IHopdong_tan obj){
		String query = "select HD.PK_SEQ as MAHD, HD.SOHOPDONG as SOHD, HD.HOSO_FK as MAHOSO, "
						+ "TV.PK_SEQ as MATV, TV.NHANXET, TV.QUOCGIA_FK as MAQUOCGIA, "
						+ "QG.TEN as TENQUOCGIA, HD.TRANGTHAI as TRANGTHAIHOPDONG "
					+"from HOPDONG HD INNER JOIN  "
						+"HOSO ON HOSO.PK_SEQ = HD.HOSO_FK INNER JOIN  "
						+"TUVAN TV ON TV.HOSO_FK = HOSO.PK_SEQ LEFT JOIN  "
						+"QUOCGIA QG ON QG.PK_SEQ = TV.QUOCGIA_FK   "
					+ "where 1=1 ";
		
		if(obj.getPk_hoso().length()>0){
			query+=" AND HOSO.PK_SEQ = "+Integer.parseInt(obj.getPk_hoso())+"";
		}
		if(obj.getPk_hopdong().length()>0){
			query +=" AND cast(HD.PK_SEQ as varchar(10)) like '"+obj.getPk_hopdong().toString()+"%'";
		}
		return query;
	}
	
}
