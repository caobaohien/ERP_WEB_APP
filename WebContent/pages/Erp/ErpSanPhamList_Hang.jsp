<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="geso.erp.db.sql.dbutils"%>
<%@ page import="geso.dms.center.util.*"%>
<%@ page import="java.net.URLDecoder"%>


<%
	dbutils db = new dbutils();
	try
	{
		 
		String userId = (String) session.getAttribute("userId");
		
		String command = "";
		
		request.setCharacterEncoding("UTF-8");	
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		String query = (String)request.getQueryString(); 
	 	System.out.println(query);
		String view = query;
	   	query = new String(query.substring(query.indexOf("&letters=") + 9, query.length()).getBytes("UTF-8"), "UTF-8");
	   	query = URLDecoder.decode(query, "UTF-8").replace("+", " ");
	   	// query se la cai ky tu ma nguoi dung danh vao o tim kiem
	   	Utility Ult = new Utility();
	   	query = Ult.replaceAEIOU(query);
	   	System.out.println(query);
	   //sửa lại câu truy vấn
	   	  command=" SELECT SP.PK_SEQ,SP.MA,SP.TEN,ISNULL(DV.TEN,'') AS DONVI, SP.SOLUONG AS SOLUONGTON,ISNULL(GIABAN,0) AS GIABAN, 0 AS CHIETKHAU "+
	   		 	  " FROM SANPHAM SP LEFT JOIN DONVI DV ON DV.PK_SEQ=SP.DONVI_FK "+
	   		 	  " WHERE SP.TIMKIEM LIKE '%"+query+"%'";
	   			NumberFormat formatter = new DecimalFormat("#,###,###.###");
				System.out.println("Lay san pham / vat tu / tai san: " + command);
				ResultSet sp = db.get(command);
				int j = 0;
				if(sp != null)
				{ 
						while(sp.next())
						{							
							String tenSP = Ult.replaceAEIOU(sp.getString("ten"));
							String ma = Ult.replaceAEIOU(sp.getString("ma"));
							String tensp = sp.getString("ten");
							//luu y: bat buoc phai co dau phan cach '|' o cuoi moi dong'
							out.print("###" + sp.getString("ma") + " -- " + tensp + " [" + sp.getString("donvi")  + "] [" + formatter.format(sp.getFloat("soluongton")) + "] [" + formatter.format(sp.getFloat("giaban"))  + "] [" + sp.getString("pk_seq") + "]|");	
						}	
						sp.close();					
				}
	    
		db.shutDown();		
	}
	catch(Exception ex){ System.out.println("Xay ra exception roi ban..."); }
%>

