<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="geso.dms.center.util.*"%>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
 %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
</head>


<frameset rows="50,*" frameborder="0" border="0" framespacing="0">
	<frame name="top" src="pages/Center/menu_bar.jsp" scrolling="no">
	<frame name="content" src="pages/Center/home.jsp" marginheight="0"
		marginwidth="0" scrolling="auto" noresize>

	<noframes>

	</noframes>

</frameset>
</frameset>
</html>
