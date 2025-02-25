package geso.dms.center.servlets.login;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import geso.erp.db.sql.*;
import geso.dms.center.util.*;

 public class LoginSvl extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet 
 {
    static final long serialVersionUID = 1L;
    
    public LoginSvl() 
    {
		super();
	}   	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
    	String userId = (String)request.getSession(false).getAttribute("userId");  
    	String userTen = (String)request.getSession(false).getAttribute("userTen");
    	String sum = (String)request.getSession(false).getAttribute("sum");
    	String site = (String)request.getSession(false).getAttribute("site");
    	Utility util = (Utility)request.getSession(false).getAttribute("util");
    	if(!util.check(userId, userTen, sum)){
    		response.sendRedirect("/TrainingGESO/index.jsp");
    	}else{
    		response.sendRedirect("/TrainingGESO/ChangePassword.jsp");
    	}
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession(true);
		String userId = ""; 
		String userTen = ""; 
		
		String congtyId = "";
		String congtyTen = "";
			
		Utility util = new Utility();
	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    dbutils db = new dbutils();
	    
		String name = util.ValidateParam(util.antiSQLInspection(request.getParameter("username")));
		String pass = util.ValidateParam(util.antiSQLInspection(request.getParameter("password")));
		
		
		
	 
		
	    String login = util.antiSQLInspection(request.getParameter("login"));
	    if (login.equals("1"))
	    {
	    	String strcheck=CheckQuyen(request, name, pass, "", congtyId);
	    	if(!strcheck.equals("")){
	    		session.setAttribute("msg", strcheck);
    			response.sendRedirect("/TrainingGESO/index.jsp");
	    	}else
	    	{
		    	String result = createSession(request, name, pass,"", congtyId); 
		    	
		    	
		    	if (result.equals("2"))
		    	{			
		    		 
		    			response.sendRedirect("/TrainingGESO/Center/mainpage.jsp");
		    		 
		    	}
		    	else
		    	{
		    		 
		    			session.setAttribute("msg", "Tai khoan khong hop le hoac tai khoan da duoc dang nhap");
		    			response.sendRedirect("/TrainingGESO/index.jsp");
		    		
		    	}
		    }
	    }
	    else
	    {
	    	userId = (String)request.getSession(false).getAttribute("userId");  
	    	userTen = (String)request.getSession(false).getAttribute("userTen");
	    	String sum = (String)request.getSession(false).getAttribute("sum");
	    	
	    	util = (Utility)request.getSession(false).getAttribute("util");
	    	
	    	if(!util.check(userId, userTen, sum))
	    	{
	    		response.sendRedirect("/TrainingGESO/index.jsp");
	    	}
	    	else
	    	{ 
	    		String oldpass = util.ValidateParam(util.antiSQLInspection(request.getParameter("oldpass")));
	    		String newpass = util.ValidateParam(util.antiSQLInspection(request.getParameter("newpass1")));
	    		String query = "SELECT pwdcompare ('" + oldpass + "', (select matkhau from nhanvien where pk_seq='" + userId + "')) as num";
	    		
//	    		String query = "select count(pk_seq) as num from nhanvien where matkhau='" + oldpass + "' and pk_seq='" + userId + "'";
	    		ResultSet rs = db.get(query);
	    		try
	    		{
	    			rs.next();
	    			if (rs.getString("num").equals("0"))
	    			{
	    				session.setAttribute("msg", "Mat khau khong hop le");
		    			response.sendRedirect("/TrainingGESO/ChangePassword.jsp");
	    			}
	    			else
	    			{
	    				if(newpass.length() > 5 & !newpass.contains("12345")){
	    					query = "update nhanvien set matkhau= pwdencrypt('" + newpass + "') , sessionId = '" + getDate() + "'  where pk_seq='" + userId + "'";
	    					db.update(query);
	    					
	    					 
	    						response.sendRedirect("/TrainingGESO/Center/mainpage.jsp");
	    					
	    				}
	    				else
	    				{
	    					if (newpass.length() <= 5){
	    						session.setAttribute("msg", "Mat khau phai dai tren 5 ky tu");
	    						response.sendRedirect("/TrainingGESO/ChangePassword.jsp");	    					
	    					}else{
	    					if(newpass.contains("12345")){
	    						session.setAttribute("msg", "Mat khau qua de doan");
	    						response.sendRedirect("/TrainingGESO/ChangePassword.jsp");	    					   						
	    					}
	    					}
	    				}
	    			}
	    			rs.close();
	    			db.shutDown();
	    		}
	    		catch(Exception e){
	    			e.printStackTrace();
	    		}
	    		
	    	}
	    }		
	}
	
    private String CheckQuyen(HttpServletRequest request, String name,
			String pass, String site, String congtyId) {
    
    	String chuoi="";
    	 
		return chuoi;
	}

	private String createSession(HttpServletRequest request, String name, String pass, String site1, String congty_fk)
    {
		Utility util = new Utility();
		dbutils db = new dbutils();
		String userId = "";
		String userTen = "";
		String query;
		String result="";
		
		int i = name.indexOf("or");
		int j = pass.indexOf("or");
		
		if ((i >= 0 ) || (j >= 0))
			return "0";
		
		 
		query = "SELECT isnull(pwdcompare ('" + pass + "', (select matkhau from nhanvien where dangnhap='" + name + "')),'0') as num";
		 
		ResultSet rs = db.get(query);
		try{
			if(rs.next())
			{
				if(rs.getString("num").equals("1"))
				{
					rs.close();
					
					query = "select pk_seq, ten from nhanvien where dangnhap='" + name + "'   and trangthai='1'";

					rs = db.get(query);
		
					if(rs.next())
					{			
						userId = rs.getString("pk_seq"); 
						userTen = rs.getString("ten");
						System.out.println("UserId 1:" + userId);
						rs.close();				
					}
					else
					{
						userId =  "";
						userTen = "";
						System.out.println("UserId 1:" + userId);
					}
					result="2";
				}
			}
		
		rs.close();
		
		 
		
		

		HttpSession session = request.getSession(true);

		if(result.equals("1") || result.equals("2"))
		{
			session.setAttribute("userId", userId);	
			session.setAttribute("userTen", userTen);
			session.setAttribute("sum", util.calSum(userId, userTen));
			session.setAttribute("util", util);
			 	
			session.setMaxInactiveInterval(120000);
			
		 	
			
		}
		
		if(rs != null)
			rs.close();
		}
		
		catch (java.sql.SQLException e)
		{
			return "0";
		}
		db.shutDown();
		return result;
    }
    
	private String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}
}