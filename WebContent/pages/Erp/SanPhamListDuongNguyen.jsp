
<%@page import="geso.erp.beans.sanpham_duongnguyen.ISanPhamList"%>
<%@page import="geso.dms.center.util.Utility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.Iterator"%>


<%@ page import="java.sql.ResultSet"%>

<%
	String userId = (String) session.getAttribute("userId");
	String userTen = (String) session.getAttribute("userTen");
%>
<%
	ISanPhamList obj = (ISanPhamList) session.getAttribute("sanPhamList");
%>

<%
	ResultSet dataList = (ResultSet) obj.getRsData();
	ResultSet rsDonVi = (ResultSet) obj.getRsDonVi();
%>
<%
	obj.setNextSplittings();
%>

<!DOCTYPE html PUBLIC "-//W3C//Dtd HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<!-- <link href="../css/select2.css" rel="stylesheet" />
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
		 document.forms["spListFormDN"].action.value = "timkiem";
	    document.forms["spListFormDN"].submit();
	 }
	 function taomoi()
	 {   
		 
		document.forms["spListFormDN"].action.value = "taomoi";
	    document.forms["spListFormDN"].submit();
	 }
	 function print_pdf()
	 {   
		document.forms["spListFormDN"].action.value = "print";
	    document.forms["spListFormDN"].submit();
	 }
	 
	 
	 
	 function clearform()
	 {   
		 
		 document.forms["spListFormDN"].action.value = "timkiem";
	    document.forms["spListFormDN"].trangthai.value = "";
	    document.forms["spListFormDN"].ma.value = "";
	    document.forms["spListFormDN"].ten.value = "";
	    document.forms["spListFormDN"].submit();
	 } 
	 function thongbao()
	 {
		 var tt = document.forms["spListFormDN"].msg.value;
	 	 if(tt.length>0)
	     	alert("THONG BAO LOI"+document.forms["spListFormDN"].msg.value);
	 }
	 

	 function processing(id,chuoi)
	 {
	 
 	    document.getElementById(id).innerHTML = "<a href='#'><img src='../images/waiting.gif' width = '20' height = '20' title='cho thuc hien..' border='0' longdesc='cho thuc hien..' style='border-style:outset'> Proc...</a>"; 		  
 	 	document.getElementById(id).href=chuoi;
 	 }
	 
	 function processing_hoantat(id,id_lydo)
	 {
		var lydo=document.getElementById(id_lydo).value;
		document.forms["spListFormDN"].action.value="hoantatphieu";
		
		document.forms["spListFormDN"].lydohoantat_.value =lydo;
		document.forms["spListFormDN"].idhoantat_.value =id;
	    document.forms["spListFormDN"].submit();
 	 }
	 
	</SCRIPT>
</head>
<body>
	<form name="spListFormDN" method="post"
		action="../../SanPhamDuongNguyenListSvl">
		<input type="hidden" name="userId" value="<%=userId%>"> <input
			type="hidden" name="action" value="muonlamgithidanhvaoday"> <input
			type="hidden" name="crrApprSplitting"
			value="<%=obj.getCrrApprSplitting()%>"> <input type="hidden"
			name="nxtApprSplitting" value="<%=obj.getNxtApprSplitting()%>">
		<input type="hidden" name="msg" value='<%=obj.getMsg()%>'>

		<script language="javascript" type="text/javascript">
    thongbao();
</script>

		<div id="main" style="width: 100%; padding-left: 2px">
			<div align="left" id="header" style="width: 100%; float: none">
				<div style="width: 70%; padding: 5px; float: left"
					class="tbnavigation">Danh sách san pham</div>
				<div align="right" style="padding: 5px" class="tbnavigation">
					Chào mừng Bạn
					<%=userTen%>
					&nbsp;
				</div>
			</div>
			<div align="left" id="button"
				style="border: 1; width: 100%; height: 32px; padding: 1px; float: none"
				class="tbdarkrow">

				<table border="0" cellpadding="0" cellspacing="0">
					<TR class="tbdarkrow">

						<td width="40px" align="left"><span id="btnthem"> <A
								href="javascript:taomoi()"> <IMG src="../images/add32.png"
									title="Tìm kiếm" alt="Tìm kiếm" border="1px"
									style="border-style: outset; width: 25px; height: 25px"></A>
						</span></td>


						<td width="40px" align="left"><span id="btnthem"> <A
								href="javascript:submitform()"> <IMG
									src="../images/timkiem_e.png" title="Tìm kiếm" alt="Tìm kiếm"
									border="1px"
									style="border-style: outset; width: 25px; height: 25px"></A>
						</span></td>

						<td width="30px" align="left"><span id="btnUndo"> <A
								href="javascript:clearform()"> <IMG
									src="../images/convert.gif" title="Undo" alt="Undo"
									border="1px"
									style="border-style: outset; width: 25px; height: 25px"></A>
						</span></td>
					</TR>
				</table>


			</div>
			<div></div>
			<div id="cotent" style="width: 100%; float: none">
				<div align="left"
					style="width: 100%; float: none; clear: left; font-size: 0.7em">
					<fieldset>
						<legend>
							<span class="legendtitle"> Sản phẩm </span>&nbsp;&nbsp;
						</legend>
						<LEGEND class="legendtitle">Tiêu chí tìm kiếm &nbsp;</LEGEND>
						<table width="100%" cellpadding="6" cellspacing="0">
							<tr>
								<td class="plainlabel">Mã</td>
								<td class="plainlabel"><input type="text" name="ma"
									style="width: 200px" onchange="submitform()"
									value="<%=obj.getMa()%>"></td>
							</tr>
							<tr>
								<td class="plainlabel">Tên</td>
								<td class="plainlabel"><input type="text" name="ten"
									style="width: 200px" onchange="submitform()"
									value="<%=obj.getTen()%>"></td>
							</tr>
							<tr>
								<td class="plainlabel">Đơn vị tính</td>
								<td class="plainlabel"><select name="donViTinh"
									onchange="submitform()">
										<option></option>
										<%
											if (rsDonVi != null)
												while (rsDonVi.next()) {
													if (rsDonVi.getString("pk_seq").equals(obj.getDonViTinh())) {
										%>
										<option selected="selected"
											value="<%=rsDonVi.getString("pk_seq")%>">
											<%=rsDonVi.getString("ma")%>
										</option>
										<%
											} else {
										%>
										<option value="<%=rsDonVi.getString("pk_seq")%>"><%=rsDonVi.getString("ma")%></option>
										<%
											}
												}
										%>
								</select></td>
							</tr>
						</table>
						<table width="100%" border="1" cellspacing="1" cellpadding="4">
							<tr class="tbheader">
								<!-- <TH align="center" width="4%">Số đề nghị mua hàng</TH> -->
								<th align="center" width="3%">Số TT</th>
								<th align="center" width="10%">Mã</th>
								<th align="center" width="25">Tên</th>
								<th align="center" width="25">Đơn vị</th>
								<th align="center" width="10%">Trạng thái</th>
								<th align="center" width="5%">Tác vụ</th>
							</tr>
							<%
								int count = 1;
								if (dataList != null) {
									while (dataList.next()) {
										if ((count % 2) == 0) {
							%>
							<tr class='tbdarkrow'>
								<%
									} else {
								%>
							
							<tr class='tblightrow'>
								<%
									}
								%>

								<th><%=count%></th>
								<th><%=dataList.getString("pk_seq")%></th>
								<th><%=dataList.getString("ten")%></th>
								<th><%=dataList.getString("donvi")%></th>
								<th><%=dataList.getString("trangthai").equals("1") ? "Hoạt Động" : "Ngưng Hoạt Động"%></th>
								<th><a
									href="../../SanPhamDuongNguyenNewSvl?userId=<%=userId%>
									&update=<%=dataList.getString("pk_seq")%>"><img
										src="../images/edit32.png" width="20" height="20"
										alt="Cap nhat" title="Cập nhật" border=0></a>&nbsp;
							</tr>
							<%
								count++;
									}
								}
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