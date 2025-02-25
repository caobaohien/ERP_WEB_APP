
<%@page import="geso.erp.beans.donbanhang_cuong.IDonbanhangList_cuong"%>
<%@page import="geso.dms.center.util.Utility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.Iterator"%>


<%@ page import="java.sql.ResultSet"%>

<%
	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
 
	  %>
<% IDonbanhangList_cuong obj = (IDonbanhangList_cuong)session.getAttribute("donbanhang"); %>

<%  ResultSet datalist = (ResultSet)obj.getRsData();  

ResultSet RsKH= (ResultSet)obj.getRsKhachHang();
 
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
		 document.forms["donhang"].action.value = "timkiem";
	    document.forms["donhang"].submit();
	 }
	 function taomoi()
	 {   
		 
		document.forms["donhang"].action.value = "taomoi";
	    document.forms["donhang"].submit();
	 }
	 function print_pdf()
	 {   
		document.forms["donhang"].action.value = "print";
	    document.forms["donhang"].submit();
	 }
	 
	 
	 
	 function clearform()
	 {   
		 
		 document.forms["donhang"].action.value = "timkiem";
	    document.forms["donhang"].trangthai.value = "";
	    document.forms["donhang"].ma.value = "";
	    document.forms["donhang"].ten.value = "";
	    document.forms["donhang"].submit();
	 } 
	 function thongbao()
	 {
		 var tt = document.forms["donhang"].msg.value;
	 	 if(tt.length>0)
	     	alert("THONG BAO LOI"+document.forms["donhang"].msg.value);
	 }
	 

	 function processing(id,chuoi)
	 {
	 
 	    document.getElementById(id).innerHTML = "<a href='#'><img src='../images/waiting.gif' width = '20' height = '20' title='cho thuc hien..' border='0' longdesc='cho thuc hien..' style='border-style:outset'> Proc...</a>"; 		  
 	 	document.getElementById(id).href=chuoi;
 	 }
	 
	 function processing_hoantat(id,id_lydo)
	 {
		var lydo=document.getElementById(id_lydo).value;
		document.forms["donhang"].action.value="hoantatphieu";
		
		document.forms["donhang"].lydohoantat_.value =lydo;
		document.forms["donhang"].idhoantat_.value =id;
	    document.forms["donhang"].submit();
 	 }
	 
	</SCRIPT>
</head>
<body>
	<form name="donhang" method="post"
		action="../../DonbanhangListSvl_cuong">
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
					class="tbnavigation">Danh sách san pham</div>
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
							<span class="legendtitle">Đơn Bán Hàng </span>&nbsp;&nbsp;

						</legend>

						<LEGEND class="legendtitle">Tiêu chí tìm kiếm &nbsp;</LEGEND>
						<TABLE width="100%" cellpadding="6" cellspacing="0">
							<tr>
								<TD class="plainlabel">Mã</TD>
								<TD class="plainlabel"><input type="text" name="madh"
									style="width: 200px" onchange="submitform()"
									value="<%= obj.getMa()%>"></TD>
							</tr>
							<tr>
								<TD class="plainlabel">Khách hàng</TD>
								<TD class="plainlabel"><select name="khachhang"
									onchange="submitform()">
										<option></option>
										<% 
								    				  if(RsKH!=null)
								    				  while(RsKH.next()) { 
								    				  
								    					  	if(RsKH.getString("pk_seq").equals(obj.getkhachhang())){
								    					 	%>
										<option selected="selected"
											value="<%=RsKH.getString("pk_Seq")%>">
											<%=RsKH.getString("ten")%>
										</option>
										<%  		
								    					  	}else{
								    				  %>

										<option value="<%=RsKH.getString("pk_Seq")%>">
											<%=RsKH.getString("ten")%>
										</option>

										<%  }
								    		   		} %>

								</select></TD>
							</tr>
							<tr>
								<TD class="plainlabel">Trạng thái</TD>
								<TD class="plainlabel"><select name="trangthai"
									onchange="submitform()">
										<option value=""></option>
										<%
								    		  			if(obj.getTrangThai()!=null)
								    		  			{
								    		  				if(obj.getTrangThai().equals("1"))
								    		  				{%>
										<option selected="selected" value="1">Đã chốt</option>
										<option value="0">Chưa chốt</option>

										<%}
								    		  				else if(obj.getTrangThai().equals("0"))
								    		  				{%>
										<option selected="selected" value="0">Chưa chốt</option>
										<option value="1">Đã chốt</option>

										<%}
								    		  				else if(obj.getTrangThai().equals(""))
								    		  				{%>
										<option value="1">Đã chốt</option>
										<option value="0">Chưa chốt</option>
										<%}
								    		  			}
								    		  			else
								    		  			{%>
										<option value="1">Đã chốt</option>
										<option value="0">Chưa chốt</option>
										<%}
								    		  		%>
								</select></TD>
							</tr>
						</TABLE>
						<TABLE width="100%" border="1" cellspacing="1" cellpadding="4">
							<TR class="tbheader">
								<!-- <TH align="center" width="4%">Số đề nghị mua hàng</TH> -->
								<TH align="center" width="3%">STT</TH>
								<TH align="center" width="5%">Mã ĐH</TH>
								<TH align="center" width="6%">Ngày chứng từ</TH>
								<TH align="center" width="8%">Khách hàng</TH>
								<TH align="center" width="8%">Trạng thái</TH>
								<TH align="center" width="16%">Tổng tiền</TH>
								<TH align="center" width="8%">Ngày tạo</TH>
								<TH align="center" width="10%">Người tạo</TH>
								<TH align="center" width="5%">Tác vụ</TH>
							</TR>
							<%
				                int dem=1;
				                if(datalist!=null)
				                	while(datalist.next())
				                	{
				                		if((dem % 2 ) == 0) { %>
							<TR class='tbdarkrow'>
								<%}else{ %>
							
							<TR class='tblightrow'>
								<%} %>
								<td align="center"><%= dem %></td>
								<td align="center"><%= datalist.getString("pk_seq") %></td>
								<td align="center"><%= datalist.getString("ngaychungtu") %></td>
								<td align="center"><%= datalist.getString("ten") %></td>
								<td align="center"><%= datalist.getString("trangthai").equals("1") ? "Đã chốt" : "Chưa Chốt" %></td>
								<td align="center"><%= datalist.getString("tongtien") %></td>
								<td align="center"><%= datalist.getString("ngaytao") %></td>
								<td align="center"><%= datalist.getString("tennv") %></td>
								<th><a
									href="../../DonbanhangSvl_cuong?userId=<%=userId%>
									&update=<%=datalist.getString("pk_seq")%>"><img
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