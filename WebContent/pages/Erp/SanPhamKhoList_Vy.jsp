<%@page import="geso.erp.beans.chuyenkho_vy.IPhieuChuyenKho_Vy"%>
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
		IPhieuChuyenKho_Vy phieuChuyenKho = (IPhieuChuyenKho_Vy) session.getAttribute("phieuChuyenKho");
		String maKhoChuyen = phieuChuyenKho.getKhoChuyen().getPK_SEQ();
		System.out.println(phieuChuyenKho.getKhoChuyen().getPK_SEQ() + "o kholistjsp");
	 	System.out.println(query);
		String view = query;
	   	query = new String(query.substring(query.indexOf("&letters=") + 9, query.length()).getBytes("UTF-8"), "UTF-8");
	   	query = URLDecoder.decode(query, "UTF-8").replace("+", " ");
	   	// query se la cai ky tu ma nguoi dung danh vao o tim kiem
	   	Utility Ult = new Utility();
	   	query = Ult.replaceAEIOU(query);
	   	//Lay cai chu ma minh danh trong input
	   	System.out.println("query trong SanPhamKhoList: " + query);
	   
	   	  command=" SELECT SP.PK_SEQ, SP.MA, SP.TEN, ISNULL(DV.TEN,'') AS DONVI, KS.SOLUONG AS SOLUONGTON"+
	   	  			" FROM ERP_SANPHAM SP"+
	   	  			" INNER JOIN ERP_DONVI DV ON DV.PK_SEQ = SP.DONVI_FK"+
	   	  			" INNER JOIN KHO_SANPHAM KS ON SP.PK_SEQ = KS.SANPHAM_FK"+
	   		 	  " WHERE KS.KHO_FK = '"+maKhoChuyen+"' AND SP.MA LIKE '%"+query+"%'";
	   			NumberFormat formatter = new DecimalFormat("#,###,###.###");
				System.out.println("Lay san pham / vat tu / tai san: " + command);
				ResultSet sp = db.get(command);
				int j = 0;
				if(sp != null)
				{ 
						while(sp.next())
						{
							
							String tenSP = Ult.replaceAEIOU(sp.getString("TEN"));
							String ma = Ult.replaceAEIOU(sp.getString("MA"));
							String tensp = sp.getString("TEN");
							out.print("###" + sp.getString("MA") + " -- " + tensp + " [" + sp.getString("DONVI") + "] [" + sp.getInt("SOLUONGTON") + "] [" + sp.getString("PK_SEQ") + "]|"); //luu y: bat buoc phai co dau phan cach '|' o cuoi moi dong'
													}	
						sp.close();
					
				}
	    
		db.shutDown();
		session.removeAttribute("maKhoChuyen");
	}
	catch(Exception ex){ System.out.println("Xay ra exception roi ban..."); }
%>
