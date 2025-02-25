
<%@page import="geso.erp.beans.report.Ireport"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="geso.erp.beans.baocao.*"%>
<%@ page import="java.sql.ResultSet"%>

<%
	Ireport obj = (Ireport) session.getAttribute("obj");
%>

<%
	String userId = (String) session.getAttribute("userId");
%>
<%
	String userTen = (String) session.getAttribute("userTen");
%>
<%
	ResultSet rsSanPham = (ResultSet) obj.getRsSanPham();
	ResultSet rsNhaPhanPhoi = (ResultSet) obj.getRsNhaPhanPhoi();
	ResultSet rsKenhBanHang = (ResultSet) obj.getRsKenhBanHang();
	ResultSet rsNhomSanPham = (ResultSet) obj.getRsNhomSanPham();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Diageo - Project</title>
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
<script type="text/javascript" src="../scripts/dropdowncontent.js"></script>

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
		 document.forms["erpDmhForm"].action.value = "submit";
	     document.forms["erpDmhForm"].submit();
	 }
	 
	 function locsanpham()
	 {   
		 document.forms["erpDmhForm"].action.value = "search";
	     document.forms["erpDmhForm"].submit();
	 }
	
	 function thongbao()
	 {
		 var tt = document.forms["erpDmhForm"].msg.value;
	 	 if(tt.length>0)
	     	alert(document.forms["erpDmhForm"].msg.value);
	 }
	 
	 function sellectAll()
	 {
		 var chkAll = document.getElementById("chkAll");
		 var spIds = document.getElementsByName("spIds");
		 
		 if(chkAll.checked)
		 {
			 for(i = 0; i < spIds.length; i++)
			 {
				 spIds.item(i).checked = true;
			 }
		 }
		 else
		 {
			 for(i = 0; i < spIds.length; i++)
			 {
				 spIds.item(i).checked = false;
			 }
		 }
	 }
	 
	 function sellectAll2()
	 {
		 var chkAll = document.getElementById("chkAll2");
		 var spIds = document.getElementsByName("clIds");
		 
		 if(chkAll.checked)
		 {
			 for(i = 0; i < spIds.length; i++)
			 {
				 spIds.item(i).checked = true;
			 }
		 }
		 else
		 {
			 for(i = 0; i < spIds.length; i++)
			 {
				 spIds.item(i).checked = false;
			 }
		 }
	 }
	 function sellectAll3()
	 {
		 var chkAll = document.getElementById("chkAll3");
		 var spIds = document.getElementsByName("ndxIds");
		 
		 if(chkAll.checked)
		 {
			 for(i = 0; i < spIds.length; i++)
			 {
				 spIds.item(i).checked = true;
			 }
		 }
		 else
		 {
			 for(i = 0; i < spIds.length; i++)
			 {
				 spIds.item(i).checked = false;
			 }
		 }
	 }
	</SCRIPT>
</head>
<body>
	<form name="erpDmhForm" method="post" action="../../ErpBCViengThamSvl">
		<input type="hidden" name="userId" value="<%=userId%>"> <input
			type="hidden" name="userTen" value="<%=userTen%>"> <input
			type="hidden" name="action" value="1"> <input type="hidden"
			name="khoTen" id="khoTen" value="">

		<div id="main" style="width: 100%; padding-left: 2px">
			<div align="left" id="header" style="width: 100%; float: none">
				<div style="width: 60%; padding: 5px; float: left"
					class="tbnavigation">Quản lý cung ứng > Báo cáo > Hàng xuất
					kho</div>
				<div align="right" style="padding: 5px" class="tbnavigation">
					Chào mừng Bạn
					<%=userTen%>
					&nbsp;
				</div>
			</div>
			<div align="left" style="width: 100%; float: none; clear: left">
				<fieldset>
					<legend class="legendtitle"> Thông báo</legend>
					<textarea name="dataerror" style="width: 100%; color: red"
						readonly="readonly" rows="1" readonly="readonly"><%=obj.getMsg()%></textarea>
					<%
						obj.setMsg("");
					%>
				</fieldset>
			</div>
			<div id="cotent" style="width: 100%; float: none">
				<div align="left"
					style="width: 100%; float: none; clear: left; font-size: 0.7em">
					<fieldset style="margin-top: 5px">
						<legend class="legendtitle"> Tiêu chí tìm kiếm</legend>
						<TABLE width="100%" cellpadding="6px" cellspacing="0px"
							style="margin-top: 5px">
							<!-- 								                          
                    <TR >
                        <TD class="plainlabel" width="15%">Từ ngày </TD>
                        <TD class="plainlabel" >
                            <input type="text" class="days" 
                                   id="tungay" name="tungay" value="<%//obj.gettungay()%>" maxlength="10" />
                        </TD>
                    </TR>
                     <TR >
                        <TD class="plainlabel" width="15%" >Đến ngày </TD>
                        <TD class="plainlabel" >
                            <input type="text" class="days" 
                                   id="denngay" name="denngay" value="<%//obj.getdenngay()%>" maxlength="10" />
                        </TD>
                    </TR> -->
							<TR>
								<TD class="plainlabel" width="15%">Tháng</TD>
								<td class="plainlabel"><select name="month"">
										<option value="-1"></option>
										<%
										for(int i=1;i<=12;i++){
									%>
										<option value="<%=i%>"><%=i %></option>
										<%} %>
								
							</TR>
							<TR>
								<TD class="plainlabel" width="15%">Năm</TD>
								<td class="plainlabel"><select name="year"">
										<option value="-1"></option>
										<%
										for(int i=2000;i<=2030;i++){
									%>
										<option value="<%=i%>"><%=i %></option>
										<%} %>
								
							</TR>
							<%-- 	<tr>
								<td class="plainlabel">Sản Phảm</td>
								<td class="plainlabel"><select name="sanPham">
										<option></option>
										<%
											if (rsSanPham != null)
												while (rsSanPham.next()) {
													if (rsSanPham.getString("pk_seq").equals(obj.getSanPham())) {
										%>
										<option selected="selected"
											value="<%=rsSanPham.getString("pk_seq")%>">
											<%=rsSanPham.getString("ten")%>
										</option>
										<%
											} else {
										%>
										<option value="<%=rsSanPham.getString("pk_seq")%>"><%=rsSanPham.getString("ten")%></option>
										<%
											}
												}
										%>
								</select></td>
							</tr> --%>
							<tr>
								<td class="plainlabel">Nhà Phân Phối</td>
								<td class="plainlabel"><select name="nhaPhanPhoi">
										<option></option>
										<%
											if (rsNhaPhanPhoi != null)
												while (rsNhaPhanPhoi.next()) {
													if (rsNhaPhanPhoi.getString("pk_seq").equals(obj.getNhaPhanPhoi())) {
										%>
										<option selected="selected"
											value="<%=rsNhaPhanPhoi.getString("pk_seq")%>">
											<%=rsNhaPhanPhoi.getString("ten")%>
										</option>
										<%
											} else {
										%>
										<option value="<%=rsNhaPhanPhoi.getString("pk_seq")%>"><%=rsNhaPhanPhoi.getString("ten")%></option>
										<%
											}
												}
										%>
								</select></td>
							</tr>
							<tr>
								<td class="plainlabel">Kênh Bán Hàng</td>
								<td class="plainlabel"><select name="kenhBanHang">
										<option></option>
										<%
											if (rsKenhBanHang != null)
												while (rsKenhBanHang.next()) {
													if (rsKenhBanHang.getString("pk_seq").equals(obj.getKenhBanHang())) {
										%>
										<option selected="selected"
											value="<%=rsKenhBanHang.getString("pk_seq")%>">
											<%=rsKenhBanHang.getString("diengiai")%>
										</option>
										<%
											} else {
										%>
										<option value="<%=rsKenhBanHang.getString("pk_seq")%>"><%=rsKenhBanHang.getString("diengiai")%></option>
										<%
											}
												}
										%>
								</select></td>
							</tr>
							<%-- <tr>
								<td class="plainlabel">Nhóm Sản Phẩm</td>
								<td class="plainlabel"><select name="nhomSanPham">
										<option></option>
										<%
											if (rsNhomSanPham != null)
												while (rsNhomSanPham.next()) {
													if (rsNhomSanPham.getString("pk_seq").equals(obj.getNhomSanPham())) {
										%>
										<option selected="selected"
											value="<%=rsNhomSanPham.getString("pk_seq")%>">
											<%=rsNhomSanPham.getString("diengiai")%>
										</option>
										<%
											} else {
										%>
										<option value="<%=rsNhomSanPham.getString("pk_seq")%>"><%=rsNhomSanPham.getString("diengiai")%></option>
										<%
											}
												}
										%>
								</select></td>
							</tr> --%>



							<tr>
								<td colspan="2" class="plainlabel"><a class="button"
									href="javascript:submitform();"> <img style="top: -4px;"
										src="../images/button.png" alt=""> Tạo báo cáo
								</a></td>
							</tr>

						</TABLE>
				</div>
	</form>
</body>
<script type="text/javascript">
	dropdowncontent.init('spId', "right-bottom", 300, "click");
	dropdowncontent.init('clId', "right-bottom", 300, "click");
	dropdowncontent.init('ndxId', "right-bottom", 300, "click");
</script>
</html>

<%
	try {

		session.setAttribute("obj", null);
	} catch (Exception er) {

	} finally {

	}
%>
