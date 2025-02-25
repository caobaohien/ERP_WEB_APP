
<%@page import="geso.erp.beans.chuyenkho_thai.IChuyenkhoList"%>
<%@page import="geso.dms.center.util.Utility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="geso.erp.beans.chuyenkho_thai.*"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.text.NumberFormat"%>
<%
	String userId = (String) session.getAttribute("userId"); 
	String userTen = (String) session.getAttribute("userTen"); 
 
	  %>
<% IChuyenkhoList obj = (IChuyenkhoList)session.getAttribute("chuyenkho"); %>

<%
	ResultSet dataList = (ResultSet)obj.getDataList();
	ResultSet getListKC = (ResultSet) obj.getListKC();
	ResultSet getListKN = (ResultSet) obj.getListKN(); 
	ResultSet rstrangthai = obj.rstrangthai();
%>
<% obj.setNextSplittings(); %>

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
		 document.forms["chuyenkho"].action.value = "timkiem";
	    document.forms["chuyenkho"].submit();
	 }
	 function taomoi()
	 {   
		 
		document.forms["chuyenkho"].action.value = "taomoi";
	    document.forms["chuyenkho"].submit();
	 }
	 function print_pdf()
	 {   
		document.forms["chuyenkho"].action.value = "print";
	    document.forms["chuyenkho"].submit();
	 }
	 
	 
	 
	 function clearform()
	 {   
		 View('chuyenkho', 1, 'refresh');
	   
	    document.forms["chuyenkho"].trangthai.value = "";
	    document.forms["chuyenkho"].ma.value = "";
	    document.forms["chuyenkho"].khochuyen.value = "";
	    document.forms["chuyenkho"].khonhan.value = "";

	    document.forms["chuyenkho"].submit();
	 } 
	 function thongbao()
	 {
		 var tt = document.forms["chuyenkho"].msg.value;
	 	 if(tt.length>0)
	     	alert(document.forms["chuyenkho"].msg.value);
	 }
	 

	 function processing(id,chuoi)
	 {
	 
 	    document.getElementById(id).innerHTML = "<a href='#'><img src='../images/waiting.gif' width = '20' height = '20' title='cho thuc hien..' border='0' longdesc='cho thuc hien..' style='border-style:outset'> Proc...</a>"; 		  
 	 	document.getElementById(id).href=chuoi;
 	 }
	 
	 function processing_hoantat(id,id_lydo)
	 {
		var lydo=document.getElementById(id_lydo).value;
		document.forms["chuyenkho"].action.value="hoantatphieu";
		
		document.forms["chuyenkho"].lydohoantat_.value =lydo;
		document.forms["chuyenkho"].idhoantat_.value =id;
	    document.forms["chuyenkho"].submit();
 	 }
	 
	</SCRIPT>
</head>
<body>
	<form name="chuyenkho" method="post" action="../../ChuyenkhoListSvl">
		<input type="hidden" name="userId" value="<%=userId %>"> <input
			type="hidden" name="action" value="1"> <input type="hidden"
			name="crrApprSplitting" value="<%= obj.getCrrApprSplitting() %>">
		<input type="hidden" name="nxtApprSplitting"
			value="<%= obj.getNxtApprSplitting() %>">

		<script language="javascript" type="text/javascript">
    thongbao();
</script>

		<div id="main" style="width: 100%; padding-left: 2px">
			<div align="left" id="header" style="width: 100%; float: none">
				<div style="width: 70%; padding: 5px; float: left"
					class="tbnavigation">Chuyển kho</div>
				<div align="right" style="padding: 5px" class="tbnavigation">
					Chào mừng Bạn
					<%= userTen %>
					&nbsp;
				</div>
			</div>
			<!-- <div align="left" id="button" style=" border:1;width: 100%; height: 32px; padding: 1px; float: none"  class="tbdarkrow">
 			<TABLE  border="0" cellpadding="0" cellspacing="0">
								<TR class = "tbdarkrow">
									 
									 <TD width="40px" align="left">
									
									<span id="btnthem">	
										<A href="javascript:taomoi()"> <IMG
										src="../images/add32.png" title="Tìm kiếm" alt="Tìm kiếm" border="1px" style="border-style: outset;width: 25px;height: 25px"></A> 
									</span>
									
									</TD>
									
									
									<TD width="40px" align="left">
									
									<span id="btnthem">	
										<A href="javascript:submitform()"> <IMG
										src="../images/timkiem_e.png" title="Tìm kiếm" alt="Tìm kiếm" border="1px" style="border-style: outset;width: 25px;height: 25px"></A> 
									</span>
									
									</TD>
									 
									<TD width="30px" align="left">
									<span id="btnUndo">	
										  <A href="javascript:clearform()"> <IMG  
										  src="../images/convert.gif" title="Undo" alt="Undo" border="1px" style="border-style: outset;width: 25px;height: 25px"></A>
									</span>
									</TD>
									
									  
								 
								</TR>
								</TABLE>
				   			
					 
			</div> -->
			<div id="cotent" style="width: 100%; float: none">
				<div align="left"
					style="width: 100%; float: none; clear: left; font-size: 0.7em">
					<fieldset>
						<LEGEND class="legendtitle">Tiêu chí tìm kiếm &nbsp;</LEGEND>
						<TABLE width="100%" cellpadding="6" cellspacing="0">
							<tr>
								<TD class="plainlabel">Mã</TD>
								<TD class="plainlabel"><input type="text" name="ma"
									style="width: 200px" onchange="submitform()"
									value="<%=obj.getMa()%>"></TD>
							</tr>
							<tr>
								<td class="plainlabel">Kho chuyển</td>
								<td class="plainlabel"><select name="khochuyen"
									onchange="submitform()" style="width: 200px">
										<option></option>
										<%if(getListKC != null)
											  	while(getListKC.next()){
											  		if(obj.getKhochuyen().equals(getListKC.getString("Khochuyen"))){%>
										<option selected="selected"
											value="<%=getListKC.getString("Khochuyen")%>"><%=getListKC.getString("TENKHO") %></option>
										<%}else{ %>
										<option value="<%=getListKC.getString("Khochuyen")%>"><%=getListKC.getString("TENKHO")%></option>
										<%}} %>
								</select></td>
							</tr>

							<tr>
								<TD class="plainlabel">Kho nhận</TD>
								<td class="plainlabel"><select name="khonhan"
									onchange="submitform()" style="width: 200px">
										<option value=""></option>
										<%if(getListKN!=null)
									  		  while(getListKN.next()){ 
					                        	if(obj.getKhonhan().equals(getListKN.getString("Khonhan"))){%>
										<option selected="selected"
											value="<%=getListKN.getString("Khonhan")%>">
											<%=getListKN.getString("TENKHO") %>
										</option>
										<%
													}else{
													%>
										<option value="<%=getListKN.getString("Khonhan")%>">
											<%=getListKN.getString("TENKHO")%>
										</option>
										<%
					                        	   }
 					                           }
					                    	%>
								</select></td>
							</tr>

							<tr>
								<TD class="plainlabel">Trạng thái</TD>
								<TD class="plainlabel"><select name="trangthai"
									onchange="submitform()" style="width: 200px">
										<option></option>
										<% if(obj.getTrangthai().equals("")) {%>
										<option value="0">Chưa chốt</option>
										<option value="1">Đã chốt</option>
										<option value="2">Đã xóa</option>
										<%} else if(obj.getTrangthai().equals("0")) {%>
										<option value="0" selected="selected">Chưa chốt</option>
										<option value="1">Đã chốt</option>
										<option value="2">Đã xóa</option>
										<%}else if(obj.getTrangthai().equals("1")) { %>
										<option value="0">Chưa chốt</option>
										<option value="1" selected="selected">Đã chốt</option>
										<option value="2">Đã xóa</option>
										<%}else  if(obj.getTrangthai().equals("2"))  { %>
										<option value="0">Chưa chốt</option>
										<option value="1">Đã chốt</option>
										<option value="2" selected="selected">Đã xóa</option>
										<%}%>


								</select></TD>
							</tr>

						</TABLE>
						<TABLE width="100%" cellpadding="6" cellspacing="0">
							<tr>
								<td class="plainlabel" style="width: 5%" align="right"><span
									id="btnUndo"> <A href="javascript:clearform()"> <IMG
											src="../images/button.png" title="Undo" alt="Undo"width: 35px;height: 35px"></A>
								</span></td>
								<TD class="plainlabel" style="width: 10%" align="left">Nhập
									lại</TD>
								<td class="plainlabel" style="width: 85%"></td>
							</tr>
						</table>
					</fieldset>
					<fieldset>
						<LEGEND class="legendtitle">
							<table width="100%" cellspacing="1" cellpadding="4">
								<tr>
									<td>Chuyển kho</td>
									<td><span id="btnUndo"> <A
											href="javascript:taomoi()"> <IMG
												src="../images/New30.png" title="Undo" alt="Undo"width: 35px;height: 35px"></A>
											Tạo mới
									</span></td>
								</tr>
							</table>
						</LEGEND>
						<TABLE width="100%" border="1" cellspacing="1" cellpadding="4">
							<TR class="tbheader">
								<!-- <TH align="center" width="4%">Số đề nghị mua hàng</TH> -->
								<TH align="center" width="3%">STT</TH>
								<TH align="center" width="5%">Mã</TH>
								<TH align="center" width="8%">Lý do chuyển</TH>
								<TH align="center" width="10%">Người tạo</TH>
								<TH align="center" width="10%">Ngày tạo</TH>
								<TH align="center" width="10%">Người sửa</TH>
								<TH align="center" width="8%">Ngày sửa</TH>
								<TH align="center" width="10%">Tác vụ</TH>
							</TR>
							<%  int count=1;
				             		if(dataList != null)
				             			while(dataList.next()){
 				                		if((count % 2 ) == 0) { %>
							<TR class='tbdarkrow'>
								<%}else{ %>
							
							<TR class='tblightrow'>
								<%} %>
								<th><%=count %></th>
								<th><%=dataList.getString("Ma") %></th>
								<th><%=dataList.getString("Lydochuyen") %></th>
								<th><%=dataList.getString("Nguoitao") %></th>
								<th><%=dataList.getString("Ngaytao") %></th>
								<th><%=dataList.getString("Nguoisua") %></th>
								<th><%=dataList.getString("Ngaysua") %></th>
								<%String tt = dataList.getString("Trangthai");%>
								<th>
									<%if( tt.equals("0")){ %> <A
									href="../../ChuyenkhoSvl?userId=<%=userId%>&update=<%= dataList.getString("Ma")%>">
										<IMG src="../images/edit32.png" width="20" height="20"
										alt="Cap nhat" title="Cập nhật" border=0>
								</A>&nbsp; <A
									href="../../ChuyenkhoList_Thai?userId=<%=userId%>&delete=<%= dataList.getString("Ma")%>">
										<img src="../images/DeleteRed_32.png" width="20" height="20"
										border=0 alt="Xóa" title="Xóa"
										onclick="if(!confirm('Bạn muốn xóa đơn hàng này?')) return false;">
								</A>&nbsp; <A
									href="../../ChuyenkhoList_Thai?userId=<%=userId%>&delete=<%= dataList.getString("Ma") %>">
										<img src="../images/Chot.png" width="20" height="20" border=0
										alt="Chốt" title="Chốt">
								</A>&nbsp; <A
									href="../../ChuyenkhoList_ThaiuserId=<%=userId%>&delete=<%= dataList.getString("Ma") %>">
										<img src="../images/Display.png" width="20" height="20"
										border=0 alt="Hiển thị" title="Hiển thị">
								</A>&nbsp; <%}else if( tt.equals("1")){ %> <A
									href="../../ChuyenkhoList_Thai?userId=<%=userId%>&delete=<%= dataList.getString("Ma") %>">
										<img src="../images/Display.png" width="20" height="20"
										border=0 alt="Hiển thị" title="Hiển thị">
								</A>&nbsp; <%} %>
								</th>

							</tr>
							<% count++;
				                } %>
						</TABLE>
					</fieldset>
				</div>
			</div>
		</div>
	</form>
</body>
</html>

<% obj.DBclose();%>