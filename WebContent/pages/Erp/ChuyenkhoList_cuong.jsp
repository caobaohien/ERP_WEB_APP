<%@page import="java.sql.ResultSet"%>
<%@page import="geso.erp.beans.chuyenkho_cuong.imp.ChuyenkhoList_cuong"%>
<%@page import="geso.erp.beans.chuyenkho_cuong.IChuyenkhoList_cuong"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%String userId=(String)session.getAttribute("userId");
    String userTen=(String)session.getAttribute("userTen");
    %>
<% IChuyenkhoList_cuong obj = (IChuyenkhoList_cuong)session.getAttribute("chuyenkholist"); %>
<%
    	ResultSet dataList = (ResultSet)obj.getRsData();
    	ResultSet rsKhochuyen=(ResultSet)obj.getRsKhoChuyen();
    	ResultSet rsKhonhan = (ResultSet)obj.getRsKhoNhan();
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
<!--  <link href="../css/select2.css" rel="stylesheet" />
	<script src="../scripts/select2.js"></script>
	<script>
	     $(document).ready(function() { $("select").select2();  });	     
	</script> -->

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
		 //javascrip:
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
		 
		 document.forms["chuyenkho"].action.value = "timkiem";
	    document.forms["chuyenkho"].trangthai.value = "";
	    document.forms["chuyenkho"].ma.value = "";
	    document.forms["chuyenkho"].ten.value = "";
	    document.forms["chuyenkho"].submit();
	 } 
	 function thongbao()
	 {
		 var tt = document.forms["chuyenkho"].msg.value;
	 	 if(tt.length>0)
	     	alert("THONG BAO LOI"+document.forms["chuyenkho"].msg.value);
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
	<form name="chuyenkho" method="post"
		action="../../ChuyenkhoListSvl_cuong">
		<input type="hidden" name="userId" value="<%=userId %>"> <input
			type="hidden" name="action" value=""> <input type="hidden"
			name="crrApprSplitting" value="<%= obj.getCrrApprSplitting() %>">
		<input type="hidden" name="nxtApprSplitting"
			value="<%= obj.getNxtApprSplitting() %>">

		<script language="javascript" type="text/javascript">
    thongbao();
</script>

		<div id="main" style="width: 100%; padding-left: 2px">
			<div align="left" id="header" style="width: 100%; float: none">
				<div style="width: 70%; padding: 5px; float: left"
					class="tbnavigation">Danh sách chuyển kho</div>
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
										src="../images/add32.png" title="Tạo mới" alt="Tạo mới" border="1px" style="border-style: outset;width: 25px;height: 25px"></A> 
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
										  <A  href="javascript:clearform()"> <IMG  src="../images/convert.gif" title="Undo" alt="Undo" border="1px" style="border-style: outset;width: 25px;height: 25px"></A>
									</span>
									</TD>								 
								</TR>
								</TABLE>
				   			
					 
			</div> -->
			<div></div>
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
									value="<%= obj.getMa()%>"></TD>
							</tr>
							<tr>
								<TD class="plainlabel">Kho chuyển</TD>
								<TD class="plainlabel"><select name="khochuyen"
									onchange="submitform()">
										<option></option>
										<% 
								    				  if(rsKhochuyen!=null)
								    				  while(rsKhochuyen.next()) { 
								    				  
								    					  	if(rsKhochuyen.getString("pk_seq").equals(obj.getKhoChuyen())){
								    					 	%>
										<option selected="selected"
											value="<%=rsKhochuyen.getString("pk_Seq")%>">
											<%=rsKhochuyen.getString("TENKHO")%>
										</option>
										<%  		
								    					  	}else{
								    				  %>

										<option value="<%=rsKhochuyen.getString("pk_Seq")%>">
											<%=rsKhochuyen.getString("TENKHO")%>
										</option>

										<%  }
								    		   		} %>

								</select></TD>
							</tr>
							<tr>
								<TD class="plainlabel">Kho nhận</TD>
								<TD class="plainlabel"><select name="khonhan"
									onchange="submitform()">
										<option></option>
										<% 
								    				  if(rsKhonhan!=null)
								    				  while(rsKhonhan.next()) { 
								    				  
								    					  	if(rsKhonhan.getString("pk_seq").equals(obj.getKhoNhan())){
								    					 	%>
										<option selected="selected"
											value="<%=rsKhonhan.getString("pk_Seq")%>">
											<%=rsKhonhan.getString("TENKHO")%>
										</option>
										<%  		
								    					  	}else{
								    				  %>

										<option value="<%=rsKhonhan.getString("pk_Seq")%>">
											<%=rsKhonhan.getString("TENKHO")%>
										</option>

										<%  }
								    		   		} %>

								</select></TD>
							</tr>
							<tr>
								<TD class="plainlabel">Trạng thái</TD>
								<TD class="plainlabel"><select name="trangThai"
									onchange="submitform()">
										<% if(obj.getTrangThai().equals("")) {%>
										<option value="" selected="selected"></option>
										<option value="0">Chưa chốt</option>
										<option value="1">Đã chốt</option>
										<option value="2">Đã xóa</option>
										<%} else if(obj.getTrangThai().equals("0")) {%>
										<option value="" selected="selected"></option>
										<option value="0" selected="selected">Chưa chốt</option>
										<option value="1">Đã chốt</option>
										<option value="2">Đã xóa</option>
										<%}else if(obj.getTrangThai().equals("1")) { %>
										<option value="" selected="selected"></option>
										<option value="0">Chưa chốt</option>
										<option value="1" selected="selected">Đã chốt</option>
										<option value="2">Đã xóa</option>
										<%}else  if(obj.getTrangThai().equals("2"))  { %>
										<option value="" selected="selected"></option>
										<option value="0">Chưa chốt</option>
										<option value="1">Đã chốt</option>
										<option value="2" selected="selected">Đã xóa</option>
										<%}%>
								</select></TD>
							</tr>
							<tr>
								<TD width="30px" align="left" class="plainlabel"><span
									id="btnUndo"> <a href="../../ChuyenkhoListSvl_cuong"><img
											src="../images/button.png" width="20" height="20" alt="reset"
											title="Nhập lại" border=0>Nhập lại</a>
								</span></TD>
							</tr>
						</TABLE>
						<legend>
							<span class="legendtitle">Chuyển kho <span id="btnNew">
									<span id="btnthem"> <A href="javascript:taomoi()"> <IMG
											src="../images/New.png" title="Tạo mới" alt="Tạo mới"
											border="1px"
											style="border-style: outset; width: 25px; height: 25px">Tạo
											mới
									</A>
								</span>
							</span></span>&nbsp;&nbsp;

						</legend>
						<TABLE width="100%" border="1" cellspacing="1" cellpadding="4">
							<TR class="tbheader">
								<!-- <TH align="center" width="4%">Số đề nghị mua hàng</TH> -->
								<TH align="center" width="3%">Mã</TH>
								<TH align="center" width="5%">Lý do chuyển</TH>
								<TH align="center" width="6%">Người tạo</TH>
								<TH align="center" width="8%">Ngày tạo</TH>
								<TH align="center" width="8%">Người sửa</TH>
								<TH align="center" width="16%">Ngày sửa</TH>
								<TH align="center" width="5%">Tác vụ</TH>
							</TR>
							<%
				                int dem=1;
				                if(dataList!=null)
				                	while(dataList.next())
				                	{
				                		if((dem % 2 ) == 0) { %>
							<TR class='tbdarkrow'>
								<%}else{ %>
							
							<TR class='tblightrow'>
								<%} %>
								<td align="center"><%= dataList.getString("pk_seq") %></td>
								<td align="center"><%= dataList.getString("LYDO") %></td>
								<td align="center"><%= dataList.getString("NGUOITAO") %></td>
								<td align="center"><%= dataList.getString("NGAYTAO") %></td>
								<td align="center"><%= dataList.getString("NGUOISUA") %></td>
								<td align="center"><%= dataList.getString("NGAYSUA") %></td>
								<th><a
									href="../../ChuyenkhoNewSvl_cuong?userId=<%=userId%>
									&update=<%=dataList.getString("pk_seq")%>"><img
										src="../images/edit32.png" width="20" height="20"
										alt="Cap nhat" title="Cập nhật" border=0></a>&nbsp;
							</tr>
							<% dem++;}
				                %>
						</table>


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