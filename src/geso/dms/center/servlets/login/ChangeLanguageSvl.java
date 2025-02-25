package geso.dms.center.servlets.login;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import geso.dms.center.util.*;

 public class ChangeLanguageSvl extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet 
 {
    static final long serialVersionUID = 1L;
    
    public ChangeLanguageSvl() 
    {
		super();
	}   	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession(true);
		Utility util = new Utility();
   		String nnId = util.antiSQLInspection(request.getParameter("Id"));
   		System.out.println("nnId:" + nnId);
   		session.setAttribute("nnId", nnId );
   		response.sendRedirect("/TrainingGESO/Center/mainpage.jsp");
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}
	
	private String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}
}