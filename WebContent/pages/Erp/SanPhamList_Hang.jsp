<%@page import="geso.erp.beans.sanpham_hang.ISanPhamList_Hang"%>
<%@page import="geso.erp.beans.sanpham_hang.*"%>
<%@page import="geso.dms.center.util.Utility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="geso.erp.beans.sanpham_hang.*"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.text.NumberFormat"%>

<%
	//Lay user va ten thong qua cac session
	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	ISanPhamList_Hang obj = (ISanPhamList_Hang) session.getAttribute("obj"); //doi tuong truyen vao phai giong voi doi tuong ben
	ResultSet dataList = (ResultSet)obj.getRsDataList();
	ResultSet rsDonViTinh = (ResultSet) obj.getRsDonvi();

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
	 
	 
	 
	 
	 
	 
	 function clearform()
	 {   
		/* View('erpDmhForm', 1, 'view');  */
		View('erpDmhForm', 1, 'timkiem');
		
	    document.forms["erpDmhForm"].maDonHang.value = "";
	    document.forms["erpDmhForm"].trangThai.value = "";
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
	 
	</SCRIPT>
</head>
<body>
	<form name="erpDmhForm" method="post" action="../../SanPham_HangSvl">
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
					class="tbnavigation">Danh sách đơn bán hàng</div>
				<div align="right" style="padding: 5px" class="tbnavigation">
					Chào mừng Bạn
					<%= userTen %>
					&nbsp;
				</div>
			</div>
			<div align="left" id="button"
				style="border: 1; width: 100%; height: 32px; padding: 1px; float: none"
				class="tbdarkrow">

				<TABLE border="0" cellpadding="0" cellspacing="0">
					<TR class="tbdarkrow">

						<TD width="40px" align="left"><span id="btnthem"> <A
								href="javascript:taomoi()"> <IMG src="../images/add32.png"
									title="Tạo mới" alt="Tạo mới" border="1px"
									style="border-style: outset; width: 25px; height: 25px"></A>
						</span></TD>


						<TD width="40px" align="left"><span id="btnthem"> <A
								href="javascript:submitform()"> <IMG
									src="../images/timkiem_e.png" title="Tìm kiếm" alt="Tìm kiếm"
									border="1px"
									style="border-style: outset; width: 25px; height: 25px"></A>
						</span></TD>

						<TD width="30px" align="left"><span id="btnUndo"> <A
								href="javascript:clearform()"> <IMG
									src="../images/convert.gif" title="Undo" alt="Undo"
									border="1px"
									style="border-style: outset; width: 25px; height: 25px"></A>
						</span></TD>
					</TR>
				</TABLE>


			</div>
			<div></div>
			<div id="cotent" style="width: 100%; float: none">


				<div align="left"
					style="width: 100%; float: none; clear: left; font-size: 0.7em">
					<fieldset>
						<legend>
							<span class="legendtitle">Mã đơn hàng</span>&nbsp;&nbsp;
						</legend>
						<LEGEND class="legendtitle">Tìm kiếm &nbsp;</LEGEND>
						<TABLE width="100%" cellpadding="6" cellspacing="0">
							<tr>
								<TD class="plainlabel">Mã sản phẩm</TD>
								<TD class="plainlabel"><input type="text" name="maSanPham"
									onchange="submitform()" value="<%=obj.getMaSanPham()%>"
									placeholder="Ex: 100000"></TD>
							</tr>

							<tr>
								<TD class="plainlabel">Tên sản phẩm</TD>
								<TD class="plainlabel"><input type="text" name="tenSanPham"
									onchange="submitform()" value="<%=obj.getTenSanPham()%>"
									placeholder="Ex: 100000"></TD>
							</tr>


							<tr>
								<!-- lay du lieu tu method getDataDonVi() -->
								<TD class="plainlabel">Đơn Vị Tính</TD>
								<TD class="plainlabel"><select name="donViTinh"
									onchange="submitform()" style="width: 200px">
										<option value="" selected="selected">Toàn bộ</option>
										<%
				                        while(rsDonViTinh.next()){
				                        	if(rsDonViTinh.getString("PK_SEQ").equals(obj.getDonViTinh())){
												%>
										<option selected="selected"
											value="<%=rsDonViTinh.getString("PK_SEQ")%>">
											<%=rsDonViTinh.getString("TEN") %>
										</option>
										<%
												}else{
												%>
										<option value="<%=rsDonViTinh.getString("PK_SEQ")%>">
											<%=rsDonViTinh.getString("TEN")%>
										</option>
										<%
				                        	   }
											}
				                    	%>
								</select></TD>
							</tr>
						</TABLE>

						<TABLE width="100%" border="0" cellspacing="1" cellpadding="4"
							style="border: 2px solid green;">
							<TR class="tbheader">
								<!-- <TH align="center" width="4%">Số đề nghị mua hàng</TH> -->
								<TH align="left" width="2%">STT</TH>
								<TH align="left" width="8%">Mã Sản Phẩm</TH>
								<TH align="left" width="8%">Tên Sản Phẩm</TH>
								<TH align="left" width="13%">Đơn Vị</TH>
								<TH align="left" width="7%">Số lượng</TH>
								<TH align="left" width="5%">Giá bán</TH>
								<TH align="left" width="7%">Trạng thái</TH>

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
								<%-- <TD align="left"><%= dataList.getString("PK_SEQ") %></TD> --%>
								<TD align="left"><%= dataList.getString("MASANPHAM") %></TD>
								<TD align="left"><%= dataList.getString("TENSANPHAM") %></TD>
								<TD align="left"><%= dataList.getString("DONVI") %></TD>
								<TD align="right"><%= numberFormat.format(dataList.getFloat("SOLUONG")) %></TD>
								<TD align="right"><%= numberFormat.format(dataList.getFloat("GIABAN")) %></TD>
								<%
					                    String maTrangThai = dataList.getString("TRANGTHAI");
					                    String tenTrangThai = "";
					                    String styleTrangThai = "";
		                    		 	if (maTrangThai.equals("1")) {
		                    		 		tenTrangThai = "hoạt động";
		                    		 	}	 		
		                    		 	else {		
		                    		 		tenTrangThai = "không hoạt động";
		                    		 		styleTrangThai = "style=\"color: blue\"";
		                    		 	}
		                    		 	
				                   	%>
								<TD align="left" <%= styleTrangThai %>><%= tenTrangThai %>
								</TD>
								<TD align="center"><A
									href="../../SanPhamUpdate_HangSvl?userId=<%=userId%>&display=<%= dataList.getString("MASANPHAM") %>">
										<IMG src="../images/Display20.png" alt="Hiển thị"
										title="Hiển thị" border=0>
								</A>&nbsp; <% if (maTrangThai.equals("1")) {%> <A
									href="../../SanPhamUpdate_HangSvl?userId=<%=userId%>&update=<%= dataList.getString("MASANPHAM") %>">
										<IMG src="../images/edit32.png" width="20" height="20"
										alt="Cap nhat" title="Cập nhật" border=0>
								</A> <A
									href="../../SanPham_HangSvl?userId=<%=userId%>&delete=<%= dataList.getString("MASANPHAM")%>">
										<IMG src="../images/DeleteRed_32.png" width="20" height="20"
										alt="Xoa don hang" title="Xóa" border=0>
								</A> <% } %></TD>
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
	obj.closedb();
%>
