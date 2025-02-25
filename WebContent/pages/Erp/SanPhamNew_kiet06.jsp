<%@page import="java.sql.ResultSet"%>
<%@page import="geso.erp.beans.sanpham_kiet06.ISanPham"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<% 
	String userId = (String) session.getAttribute("userId");
	String userTen = (String) session.getAttribute("userTen");
	ISanPham sp = (ISanPham) session.getAttribute("SanPhamMoi");
	ResultSet rsDonVi = sp.getRsDonViTinh();
%>

<html>
<head>
<title>SalesUp - Project</title>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" media="print"
	href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<LINK rel="stylesheet" type="text/css" href="../css/style.css" />

<style type="text/css">
#mainContainer {
	width: 660px;
	margin: 0 auto;
	text-align: left;
	height: 100%;
	border-left: 3px double #000;
	border-right: 3px double #000;
}

#formContent {
	padding: 5px;
}
/* END CSS ONLY NEEDED IN DEMO */

/* Big box with list of options */
#ajax_listOfOptions {
	position: absolute; /* Never change this one */
	width: auto; /* Width of box */
	height: 200px; /* Height of box */
	overflow: auto; /* Scrolling features */
	border: 1px solid #317082; /* Dark green border */
	background-color: #C5E8CD; /* White background color */
	color: black;
	text-align: left;
	font-size: 1.0em;
	z-index: 100;
}

#ajax_listOfOptions div {re
	/* General rule for both .optionDiv and .optionDivSelected */ margin:1px;
	padding: 1px;
	cursor: pointer;
	font-size: 1.0em;
}

#ajax_listOfOptions .optionDiv { /* Div for each item in list */
	
}

#ajax_listOfOptions .optionDivSelected { /* Selected item in the list */
	background-color: #317082; /*mau khi di chuyen */
	color: #FFF;
}

#ajax_listOfOptions_iframe {
	background-color: #F00;
	position: absolute;
	z-index: 5;
}

form {
	display: inline;
}

#dhtmltooltip {
	position: absolute;
	left: -300px;
	width: 150px;
	border: 1px solid black;
	padding: 2px;
	background-color: lightyellow;
	visibility: hidden;
	z-index: 100;
	/*Remove below line to remove shadow. Below line should always appear last within this CSS*/
	filter: progid: DXImageTransform.Microsoft.Shadow( color= gray,
		direction= 135);
}

#dhtmlpointer {
	position: absolute;
	left: -300px;
	z-index: 101;
	visibility: hidden;
}
}
</style>
<link rel="stylesheet" type="text/css" href="../css/speechbubbles.css" />
<script type="text/javascript" src="../scripts/jquery.min.js"></script>
<script type="text/javascript" src="../scripts/speechbubbles.js"></script>
<script type="text/javascript">
			jQuery(function($){ 
				 $('.addspeech').speechbubble();});
		</script>

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

<script type="text/javascript" src="../scripts/ajax.js"></script>
<script type="text/javascript" src="../scripts/erp-spList.js"></script>

<script type="text/javascript" src="../scripts/dropdowncontent.js"></script>
<script type="text/javascript" src="../scripts/cool_DHTML_tootip.js"></script>
<script type="text/javascript" src="../scripts/jquery.autocomplete.js"></script>
<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
		     $(document).ready(function() { $("select").select2();  });
		     
		</script>

<script language="javascript" type="text/javascript">
			 function saveform()
			 {	
			 	 document.forms['dmhForm'].action.value='save';
			     document.forms['dmhForm'].submit();
			 }
		
			 function checkSanPham()
			 {
				 var masp = document.getElementsByName("masp");
				 for(var hh = 0; hh < masp.length; hh++)
				 {
					if(masp.item(hh).value != "")
					{
						return true;
					}
				 }
				 return false;
			 }
			 
			 function submitform()
			 { 		
				 document.forms['dmhForm'].action.value='submit';
			     document.forms["dmhForm"].submit();
			 }
			 
			 function themmoi()
			 { 		
				 document.forms['dmhForm'].action.value='themmoi';
				 document.forms['dmhForm'].IsCapNhat.value='1';
			     document.forms["dmhForm"].submit();
			 }
			 
			 function quaylaiInit()
			 { 		
				 document.forms['dmhForm'].action.value='quaylaiInit';
				 document.forms['dmhForm'].IsCapNhat.value='0';
			     document.forms["dmhForm"].submit();
			 }
			 
			 function savesanpham()
			 { 		
				 document.forms['dmhForm'].action.value='savesanpham';
				 document.forms['dmhForm'].IsCapNhat.value='0';
			     document.forms["dmhForm"].submit();
			 }
			  
			 function Chinhsua()
			 { 		
				 document.forms['dmhForm'].action.value='chinhsua';
				 document.forms['dmhForm'].IsCapNhat.value='0';
				 var  id_dh= document.forms['dmhForm'].id_dh.value;
				 if(id_dh==""){
					 alert('Không xác định số chứng từ sửa, vui lòng kiểm tra lại');
					 return;
				 }
				 
			     document.forms["dmhForm"].submit();
			 }
		
			 function goBack()
			 {
			  	window.history.back();
			 }
		</script>
</head>
<body leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	<form name="dmhForm" method="post" action="../../SanPhamNewSvl_kiet06">

		<input type="hidden" name="userId" value='<%= userId %>'> <input
			type="hidden" name="action" value="1" /> <input type="hidden"
			name="id" value='<%=sp.getPk_seq() %>'>
		<div id="main" style="width: 100%; padding-left: 2px">
			<div align="left" id="header" style="width: 100%; float: none">
				<div style="width: 60%; padding: 5px; float: left"
					class="tbnavigation">Thông Tin Sản Phẩm (Kiệt 06)</div>
				<div align="right" style="padding: 5px" class="tbnavigation">
					Chào mừng bạn
					<%=userTen%>
				</div>
			</div>
			<!-- --------------------------------------------------------- -->
			<div align="left" id="button"
				style="border: 1; width: 100%; height: 32px; padding: 1px; float: none"
				class="tbdarkrow">

				<table border="0" cellpadding="0" cellspacing="0">
					<TR class="tbdarkrow">
						<TD width="30px" align="left"><A href="javascript:saveform()">
								<IMG src="../images/1455966997_save.png" title="Save" alt="Save"
								border="1px" style="border-style: outset">
						</A></TD>
						<TD width="30px" align="left"><span id="btnUndo"> <A
								href="../../SanPhamListSvl_kiet06?userId=<%=userId%>"> <IMG
									src="../images/Back_undo.png" title="Undo" alt="Undo"
									border="1px" style="border-style: outset">
							</A>
						</span></TD>
					</TR>
				</table>
			</div>
			<!-- --------------------------------------------------------- -->

			<div align="left" style="width: 100%; float: none; clear: left">
				<fieldset>
					<legend class="legendtitle">Thông Báo</legend>
					<textarea name="dataerror" id="Msg" rows="2" readonly="readonly"
						style="height: 50px; width: 100%">
						<%= sp.getMsg() %>
					</textarea>
				</fieldset>
			</div>

			<div align="left" style="width: 100%; float: none; clear: left">
				<fieldset>
					<legend class="legendtitle">Thêm Sản Phẩm Mới</legend>
					<div style="float: none; width: 100%" align="left"></div>
					<table width=100% cellpadding="4" cellspacing="0">

						<tr>
							<td class="plainlabel">Mã sản phẩm</td>
							<td class="plainlabel"><input type="text" name="maSanPham"
								value="<%= sp.getMa()%>" placeholder="Ex: VT100"></td>
						</tr>

						<tr>
							<td class="plainlabel">Tên sản phẩm</td>
							<td class="plainlabel"><input type="text" name="tenSanPham"
								value="<%= sp.getTen() %>" placeholder="Ex: VIETTEL100">
							</td>
						</tr>

						<tr>
							<td class="plainlabel">Đơn vị tính</td>
							<td class="plainlabel"><select name="maDonVi"
								style="width: 200px">
									<option value=""></option>
									<%	if (rsDonVi != null){
										while (rsDonVi.next()){
											if (rsDonVi.getString("pk_seq").equals(sp.getDonViTinh())){
												%>
									<option selected="selected"
										value="<%= rsDonVi.getString("pk_seq")%>">
										<%= rsDonVi.getString("MA") %>
									</option>
									<%} else { %>
									<option value="<%= rsDonVi.getString("pk_seq")%>">
										<%= rsDonVi.getString("MA") %>
									</option>
									<% 	
													}
															}
														}	
										%>
							</select></td>
						</tr>

						<tr>
							<td class="plainlabel">Trạng thái</td>
							<td class="plainlabel">
								<% if (sp.getTrangThai().equals("1")) {%> <input type="checkbox"
								name="trangthai" checked="checked" value="1"> <% } else {%>
								<input type="checkbox" name="trangthai" value="1"> <% }%>
							</td>
						</tr>

					</table>


				</fieldset>
			</div>
		</div>
		<% sp.dbClose(); %>
	</form>
</body>
</html>















