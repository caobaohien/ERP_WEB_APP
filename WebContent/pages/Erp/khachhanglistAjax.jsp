<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="geso.erp.db.sql.dbutils" %>
<%@page import="java.sql.SQLException" %>
<%@page import="java.util.Iterator" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.net.URLDecoder" %>
<%
	try{
		dbutils db=new dbutils();
		//String smartId="";
		String khId="";
		String khTen="";
		String command="";
		//String sanpham="";
		//String masp="";
		//String tensp="";
		//String dongia="";
		//String madonhang="";
		request.setCharacterEncoding("UTF-8");
		String query=(String)request.getQueryString();
		query=new String(query.substring(query.indexOf("q=")+2, query.indexOf("&limit=")).getBytes("UTF-8"),"UTF-8");
		query=URLDecoder.decode(query, "UTF-8").replace("+", "");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		
		command="select distinct top(30) smartid, pk_seq as khId, ten as khTen, diachi ";
		command+="from khachhang where trangthai = '1' ";
		command+="and ( smartid = '"+ query + "%' or ten like (N'%" + query + "%')) ";
		command+="order by khId, smartid, khTen";
		System.out.println("KHACHHANG : "+command);
		ResultSet kh=db.get(command);
		if(kh!=null){
			int m=0;
			String khb;
			while(kh.next()){
				khb=kh.getString("smartid");
				khId=kh.getString("khId") + "-" + khb;
				khTen=kh.getString("khTen") + "-" + kh.getString("diachi");
				if(khTen.toUpperCase().startsWith(query.toUpperCase())||khTen.toUpperCase().indexOf(query.toUpperCase())>=0
						||khId.toUpperCase().startsWith(query.toUpperCase())||khId.toUpperCase().indexOf(query.toUpperCase())>=0)
				{
					String khachhang=khId + "-->[" + khTen + "]";
					out.println(khachhang + "\n");
					m++;
					if(m>=50)
						break;
				}
			}
			kh.close();
		}
		/*
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
					out.println(SanPham + "\n");
					n++;
					if(n>=50)
						break;
				}
			}
			sp.close();
		}*/
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