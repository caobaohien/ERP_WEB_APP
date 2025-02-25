<%@page import="geso.erp.beans.donbanhang_kiet02.IDonHangList"%>
<%@page import="geso.erp.beans.donbanhang_kiet02.*"%>
<%@page import="geso.dms.center.util.Utility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="geso.erp.beans.donbanhang_kiet02.*"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.text.NumberFormat"%>

<%
	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	

	IDonHangList obj = (IDonHangList) session.getAttribute("obj");

	ResultSet dataList = (ResultSet)obj.getDataList();
	ResultSet rsKhachHang = (ResultSet) obj.getListKhachHang();

	obj.setNextSplittings(); 
	
	NumberFormat numberFormat = new DecimalFormat("#,###,###đ");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SalesUp - Project</title>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" media="print"
	href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<LINK rel="stylesheet" href="../css/datepicker.css" type="text/css">

<script language="javascript" src="../scripts/datepicker.js"></script>
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../scripts/Timepicker/jquery.min.js"></script>
<script type="text/javascript"
	src="../scripts/Timepicker/jquery-ui.min.js"></script>
<script type="text/javascript" src="../scripts/phanTrang.js"></script>


<script type="text/javascript" src="../scripts/jquery.min.js"></script>
<script type="text/javascript" src="../scripts/speechbubbles.js"></script>
<script type="text/javascript" src="../scripts/dropdowncontent.js"></script>
<script type="text/javascript" src="../scripts/jquery.autocomplete.js"></script>


<script type="text/javascript" src="../scripts/jquery.js"></script>

<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.datepicker.js"
	type="text/javascript"></script>
<script type="text/javascript">
		$(document).ready(function() {		
				$( ".days" ).datepicker({			    
						changeMonth: true,
						changeYear: true				
				});            
	        }); 		
			
	</script>

<script type="text/javascript" src="..scripts/jquery-1.js"></script>
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<script type="text/javascript">
        $(document).ready(function(){
            $(".button").hover(function(){
                $(".button img")
                .animate({top:"-10px"}, 200).animate({top:"-4px"}, 200) // first jump
                .animate({top:"-7px"}, 100).animate({top:"-4px"}, 100) // second jump
                .animate({top:"-6px"}, 100).animate({top:"-4px"}, 100); // the last jump
            });
        }); 
		$(document).ready(function(){
            $(".button2").hover(function(){
                $(".button2 img")
                .animate({top:"-10px"}, 200).animate({top:"-4px"}, 200) // first jump
                .animate({top:"-7px"}, 100).animate({top:"-4px"}, 100) // second jump
                .animate({top:"-6px"}, 100).animate({top:"-4px"}, 100); // the last jump
            });
        }); 
		$(document).ready(function(){
            $(".button3").hover(function(){
                $(".button3 img")
                .animate({top:"-10px"}, 200).animate({top:"-4px"}, 200) // first jump
                .animate({top:"-7px"}, 100).animate({top:"-4px"}, 100) // second jump
                .animate({top:"-6px"}, 100).animate({top:"-4px"}, 100); // the last jump
            });
        }); 
    </script>
<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
	     $(document).ready(function() { $("select").select2();  });	     
	</script>

<SCRIPT language="javascript" type="text/javascript">
	 function confirmLogout()
	 {
	    if(confirm("Bạn có muốn đăng xuất?"))
	    {
			top.location.href = "home.jsp";
	    }
	    return
	 }
	 function submitform()
	 {  
		document.forms["erpDmhForm"].action.value = "timkiem";
	    document.forms["erpDmhForm"].submit();
	 }
	 function taomoi()
	 {   
		 
		document.forms["erpDmhForm"].action.value = "taomoi";
	    document.forms["erpDmhForm"].submit();
	 }
	 
	 function chotphieu()
	 {
		document.forms["erpDmhForm"].action.value = "chot";
	    document.forms["erpDmhForm"].submit();
	 }
	 
	 function print_pdf()
	 {   
		document.forms["erpDmhForm"].action.value = "print";
	    document.forms["erpDmhForm"].submit();
	 }
	 
	 
	 
	 function clearform()
	 {   
		/* View('erpDmhForm', 1, 'view');  */
		View('erpDmhForm', 1, 'timkiem');
		
	    document.forms["erpDmhForm"].trangThai.value = "";
	    document.forms["erpDmhForm"].maDonHang.value = "";
	    document.forms["erpDmhForm"].khachHang.value = "";
	    document.forms["erpDmhForm"].submit();
	 } 
	 function thongbao()
	 {
		 var tt = document.forms["erpDmhForm"].msg.value;
	 	 if(tt.length>0)
	     	alert(document.forms["erpDmhForm"].msg.value);
	 }
	 

	 function processing(id,chuoi)
	 {
	 
 	    document.getElementById(id).innerHTML = "<a href='#'><img src='../images/waiting.gif' width = '20' height = '20' title='cho thuc hien..' border='0' longdesc='cho thuc hien..' style='border-style:outset'> Proc...</a>"; 		  
 	 	document.getElementById(id).href=chuoi;
 	 }
	 
	 function processing_hoantat(id,id_lydo)
	 {
		var lydo=document.getElementById(id_lydo).value;
		document.forms["erpDmhForm"].action.value="hoantatphieu";
		
		document.forms["erpDmhForm"].lydohoantat_.value =lydo;
		document.forms["erpDmhForm"].idhoantat_.value =id;
	    document.forms["erpDmhForm"].submit();
 	 }
	 
	</SCRIPT>
</head>
<body>
	<form name="erpDmhForm" method="post"
		action="../../DonHangListSvl_Kiet02">
		<input type="hidden" name="userId" value="<%= userId %>"> <input
			type="hidden" name="action" value=""> <input type="hidden"
			name="crrApprSplitting" value="<%= obj.getCrrApprSplitting() %>">
		<input type="hidden" name="nxtApprSplitting"
			value="<%= obj.getNxtApprSplitting() %>"> <input
			type="hidden" name="msg" value='<%= obj.getMsg() %>'>
		<script language="javascript" type="text/javascript">
    thongbao();
</script>

		<div id="main" style="width: 100%; padding-left: 2px">
			<div align="left" id="header" style="width: 100%; float: none">
				<div style="width: 70%; padding: 5px; float: left"
					class="tbnavigation">Danh sách đơn bán hàng Kiet</div>
				<div align="right" style="padding: 5px" class="tbnavigation">
					Chào mừng bạn
					<%= userTen %>
					&nbsp;
				</div>
			</div>
			<div align="left" id="button"
				style="border: 1; width: 100%; height: 32px; padding: 1px; float: none"
				class="tbdarkrow";>

				<TABLE border="0" cellpadding="0" cellspacing="0">
					<TR class="tbdarkrow">

						<TD width="40px" align="left"><span id="btnthem"> <A
								href="javascript:taomoi()"> <IMG src="../images/add32.png"
									title="Tạo mới" alt="Tạo mới" border="1px"
									style="border-style: outset; width: 25px; height: 25px">
							</A>
						</span></TD>


						<TD width="40px" align="left"><span id="btnthem"> <A
								href="javascript:submitform()"> <IMG
									src="../images/timkiem_e.png" title="Tìm kiếm" alt="Tìm kiếm"
									border="1px"
									style="border-style: outset; width: 25px; height: 25px">
							</A>
						</span></TD>

						<TD width="30px" align="left"><span id="btnUndo"> <A
								href="javascript:clearform()"> <IMG
									src="../images/convert.gif" title="Undo" alt="Undo"
									border="1px"
									style="border-style: outset; width: 25px; height: 25px">
							</A>
						</span></TD>



					</TR>
				</TABLE>


			</div>
			<div></div>
			<div id="cotent" style="width: 100%; float: none">


				<div align="left"
					style="width: 100%; float: none; clear: left; font-size: 0.95em">
					<fieldset>
						<legend>
							<span class="legendtitle"> Đơn bán hàng </span>&nbsp;&nbsp;

						</legend>

						<LEGEND class="legendtitle">Tiêu chí tìm kiếm &nbsp;</LEGEND>
						<TABLE width="100%" cellpadding="6" cellspacing="0">
							<tr>
								<TD class="plainlabel" width="10%">Mã đơn hàng</TD>
								<TD class="plainlabel" width="90%"><input type="text"
									name="maDonHang" onchange="submitform()"
									value="<%=obj.getMaDonHang()%>" placeholder="Ex: 100000">
								</TD>
							</tr>
							<tr>
								<TD class="plainlabel">Khách hàng</TD>
								<td class="plainlabel"><select name="khachHang"
									onchange="submitform()" style="width: 200px">
										<option value="" selected="selected">Toàn bộ</option>
										<%
				                        while(rsKhachHang.next()){
				                        	if(rsKhachHang.getString("MAKHACHHANG").equals(obj.getMaKhachHang())){
												%>
										<option selected="selected"
											value="<%=rsKhachHang.getString("MAKHACHHANG")%>">
											<%=rsKhachHang.getString("TENKHACHHANG") %>
										</option>
										<%
												}else{
												%>
										<option value="<%=rsKhachHang.getString("MAKHACHHANG")%>">
											<%=rsKhachHang.getString("TENKHACHHANG")%>
										</option>
										<%
				                        	   }
											}
				                    	%>
								</select></td>
							</TR>
							<TR>
								<TD class="plainlabel">Trạng thái</TD>
								<TD class="plainlabel"><select name="trangThai"
									onchange="submitform()" style="width: 200px">
										<option value="">Toàn bộ</option>
										<%
					                    	String mangMaTrangThai[] = {"0", "1", "2"};
				                    		String mangTenTrangThai[] = {"Chưa chốt", "Đã chốt", "Đã hủy"};
				                    		for(int i = 0; i < mangMaTrangThai.length; i++){
											 if(obj.getTrangThai().equals(mangMaTrangThai[i])){
											 %>
										<option selected="selected" value="<%=mangMaTrangThai[i]%>">
											<%= mangTenTrangThai[i] %>
										</option>
										<%
											 }else{
											 %>
										<option value="<%= mangMaTrangThai[i] %>">
											<%= mangTenTrangThai[i] %>
										</option>
										<% 
											 }
										 }
				                    	%>


								</select></TD>

							</TR>

						</TABLE>

						<TABLE width="100%" border="0" cellspacing="1" cellpadding="4"
							style="border: 2px solid green;">
							<TR class="tbheader">
								<!-- <TH align="center" width="4%">Số đề nghị mua hàng</TH> -->
								<TH align="left" width="2%">STT</TH>
								<TH align="left" width="8%">Mã Đơn Hàng</TH>
								<TH align="left" width="8%">Ngày Chứng Từ</TH>
								<TH align="left" width="13%">Khách Hàng</TH>
								<TH align="left" width="5%">Ngày Tạo</TH>
								<TH align="left" width="10%">Người Tạo</TH>

								<TH align="left" width="7%">Tổng Tiền</TH>
								<TH align="left" width="7%">Trạng Thái</TH>
								<TH align="left" width="9%">Tác Vụ</TH>
							</TR>
							<%
						int countRow = 0;
						
                   		if(dataList != null) {
                   			while(dataList.next()) {
                   				String style="";
                   				if((countRow % 2 ) == 0) { 
                   	%>
							<TR class='tbdarkrow'>
								<%	}
                   				else { 
                   			%>
							
							<TR class='tblightrow'>
								<%
		                    	} 
		                    %>
								<TD align="right"><%= (countRow+1) %></TD>

								<TD align="left"><%= dataList.getString("MADONHANG") %></TD>

								<TD align="left"><%= dataList.getString("NGAYCHUNGTU") %></TD>

								<TD align="left"><%= dataList.getString("TENKHACHHANG") %></TD>
								<%
					                    String maTrangThai = dataList.getString("TRANGTHAI");
					                    String tenTrangThai = "";
					                    String styleTrangThai = "";
		                    		 	if (maTrangThai.equals("0")) {
		                    		 		tenTrangThai = "Chưa chốt";
		                    		 	}	 		
		                    		 	else if (maTrangThai.equals("1")) {		
		                    		 		tenTrangThai = "Đã chốt";
		                    		 		styleTrangThai = "style=\"color: blue\"";
		                    		 	}
		                    		 	else if (maTrangThai.equals("2")) {
		                    		 		tenTrangThai = "Đã hủy";
		                    		 		styleTrangThai = "style=\"color: red\"";
		                    		 	}
		                    		 	else {
		                    		 		tenTrangThai = "Chưa có";
		                    		 	}
				                   	%>

								<TD align="left"><%= dataList.getString("NGAYTAO") %></TD>

								<TD align="left"><%= dataList.getString("TENNGUOITAO") %></TD>

								<TD align="right"><%= numberFormat.format(dataList.getFloat("TONGTIEN")) %></TD>

								<TD align="left" <%= styleTrangThai %>><%= tenTrangThai %>
								</TD>

								<TD align="center"><A
									href="../../DonHangNewSvl_Kiet02?userId=<%=userId%>&display=<%= dataList.getString("MADONHANG") %>">
										<IMG src="../images/Display20.png" alt="Hiển thị"
										title="Hiển thị" border=0>
								</A>&nbsp; <% 
				                    	if (!maTrangThai.equals("2")) {
				                    %> <% if (!maTrangThai.equals("1")) {%> <A
									href="../../DonHangListSvl_Kiet02?userId=<%=userId%>&chot=<%= dataList.getString("MADONHANG")%>">
										<IMG src="../images/Chot.png" width="20" height="20"
										alt="Chot don hang" title="Chốt" border=0>
								</A>&nbsp; <A
									href="../../DonHangNewSvl_Kiet02?userId=<%=userId%>&update=<%= dataList.getString("MADONHANG") %>">
										<IMG src="../images/edit32.png" width="20" height="20"
										alt="Cap nhat" title="Cập nhật" border=0>
								</A>&nbsp; <% } %> <A
									href="../../DonHangListSvl_Kiet02?userId=<%=userId%>&delete=<%= dataList.getString("MADONHANG")%>">
										<IMG src="../images/DeleteRed_32.png" width="20" height="20"
										alt="Xoa don hang" title="Xóa" border=0>
								</A>&nbsp; <% 
										} 
									%></TD>
							</TR>
							<% 
		                	countRow++; } 
                   			dataList.close(); } 
		            %>

							<tr class="tbfooter">
								<TD align="center" valign="middle" colspan="13" class="tbfooter">
									<%if(obj.getNxtApprSplitting() >1) {%> <img alt="Trang Dau"
									src="../images/first.gif" style="cursor: pointer;"
									onclick="View('erpDmhForm', 1, 'view')"> &nbsp; <%}else {%>
									<img alt="Trang Dau" src="../images/first.gif"> &nbsp; <%} %>
									<% if(obj.getNxtApprSplitting() > 1){ %> <img alt="Trang Truoc"
									src="../images/prev.gif" style="cursor: pointer;"
									onclick="Prev('erpDmhForm', 'prev')"> &nbsp; <%}else{ %> <img
									alt="Trang Truoc" src="../images/prev_d.gif"> &nbsp; <%} %>

									<%
								int[] listPage = obj.getNextSplittings();
							 	if(listPage!=null){
									for(int i = 0; i < listPage.length; i++){
							%> <% 							
							
							if(listPage[i] == obj.getNxtApprSplitting()){ %> <a
									style="color: white;"><%= listPage[i] %>/ <%=obj.getTheLastSplitting() %></a>
									<%}else{ %> <a
									href="javascript:View('erpDmhForm', <%= listPage[i] %>, 'view')"><%= listPage[i] %></a>
									<%} %> <input type="hidden" name="list"
									value="<%= listPage[i] %>" /> &nbsp; <%}
								}%> <% if(obj.getNxtApprSplitting() < obj.getTheLastSplitting()){ %>
									&nbsp; <img alt="Trang Tiep" src="../images/next.gif"
									style="cursor: pointer;" onclick="Next('erpDmhForm', 'next')">
									&nbsp; <%}else{ %> &nbsp; <img alt="Trang Tiep"
									src="../images/next_d.gif"> &nbsp; <%} %> <%if(obj.getNxtApprSplitting() == obj.getTheLastSplitting()) {%>
									<img alt="Trang Cuoi" src="../images/last.gif"> &nbsp; <%}else{ %>
									<img alt="Trang Cuoi" src="../images/last.gif"
									style="cursor: pointer;"
									onclick="View('erpDmhForm', -1, 'view')"> &nbsp; <%} %>
								</TD>
							</tr>
						</TABLE>

					</fieldset>
				</div>
			</div>
		</div>
	</form>
</body>
</html>

<%
	obj.DBclose();
%>
