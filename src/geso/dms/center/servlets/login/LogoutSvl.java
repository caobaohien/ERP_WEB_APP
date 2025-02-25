package geso.dms.center.servlets.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LogoutSvl() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*    	String userId = (String)request.getSession(false).getAttribute("userId");  
    	String userTen = (String)request.getSession(false).getAttribute("userTen");
    	String sum = (String)request.getSession(false).getAttribute("sum");
    	String site = (String)request.getSession(false).getAttribute("site");
    	Utility util = (Utility)request.getSession(false).getAttribute("util");
    	if(util.check(userId, userTen, sum)){*/
/*    		dbutils db = new dbutils();
    		String query = "update nhanvien set islogin='0', sessionid='0' where pk_seq='" + userId + "'";
    		db.update(query);
    		db.shutDown();*/
			HttpSession session = request.getSession();
			session.setAttribute("userId", "");  
			session.setAttribute("userTen", "");
			session.setAttribute("congtyId", "");	
			session.setAttribute("congtyTen", "");
    		response.sendRedirect("index.jsp");
//    	}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 /*   	String userId = (String)request.getSession(false).getAttribute("userId");  
    	String userTen = (String)request.getSession(false).getAttribute("userTen");
    	String sum = (String)request.getSession(false).getAttribute("sum");
    	String site = (String)request.getSession(false).getAttribute("site");
    	Utility util = (Utility)request.getSession(false).getAttribute("util");
    	if(util.check(userId, userTen, sum)){*/
/*    		dbutils db = new dbutils();
    		String query = "update nhanvien set islogin='0', sessionid='0' where pk_seq='" + userId + "'";
    		db.update(query);
    		db.shutDown();*/
			HttpSession session = request.getSession();
			session.setAttribute("userId", "");  
			session.setAttribute("userTen", "");
			session.setAttribute("congtyId", "");	
			session.setAttribute("congtyTen", "");
    		response.sendRedirect("index.jsp");
 //   	}
	}

}
