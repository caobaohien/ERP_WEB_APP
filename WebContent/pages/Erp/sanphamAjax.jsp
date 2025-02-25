<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="geso.erp.db.sql.dbutils" %>
<%@page import="java.sql.SQLException" %>
<%@page import="java.util.Iterator" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.net.URLDecoder" %>
<%@page import="geso.dms.center.util.*" %>
<%
	try{
		dbutils db=new dbutils();
		//String smartId="";
		String sanpham="";
		request.setCharacterEncoding("UTF-8");
		String query=(String)request.getQueryString();
		System.out.println(query);
		//lay ki tu duoc nhap vao o tim kiem trong query
		query=new String(query.substring(query.indexOf("&letters=")+9, query.length()).getBytes("UTF-8"),"UTF-8");
		query=URLDecoder.decode(query, "UTF-8").replace("+", "");
		Utility Ult = new Utility();
		//ki tu duoc nhap vao se chuyen thanh chu khong dau neu no co dau
		query = Ult.replaceAEIOU(query);
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		System.out.println("query trong sanphamAjax: " +query);

		sanpham="select distinct top(30) pk_seq, ma, ten, giaban ";
		sanpham+="from sanpham where trangthai='1' ";
		sanpham+="order by pk_seq,ma,ten,giaban";
		System.out.println("sanpham: "+sanpham);
		ResultSet sp=db.get(sanpham);
		String spId="";
		String spgia="";
		if(sp!=null){
			int n=0;
			while(sp.next()){
				spId=sp.getString("ma") +"-"+ sp.getString("pk_seq");
				spgia=sp.getString("ten") +"-"+sp.getString("giaban");
				if(spgia.toUpperCase().startsWith(query.toUpperCase())||spgia.toUpperCase().indexOf(query.toUpperCase())>=0
						||spId.toUpperCase().startsWith(query.toUpperCase())||spId.toUpperCase().indexOf(query.toUpperCase())>=0)
				{
					String SanPham=spId + "-->" + spgia;
					out.println(SanPham + "\n|"); // luu y: bat buoc phai co dau phan cach '|' o cuoi moi dong
					n++;
					if(n>=50)
						break;
				}
			}
			sp.close();
		}
		db.shutDown();
		db=null;
	}catch(SQLException e){
		e.printStackTrace();
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Best - Project</title>
</head>
<body>
</body>
</html>