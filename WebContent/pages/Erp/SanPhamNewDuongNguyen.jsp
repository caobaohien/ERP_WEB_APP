<%@page import="geso.erp.beans.sanpham_duongnguyen.ISanPham"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="geso.dms.center.util.Utility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.Hashtable"%>
<%@ page import="java.util.List"%>
<%
	ISanPham spBean = (ISanPham) session.getAttribute("spnew");
	String userId = (String) session.getAttribute("userId");
	String userTen = (String) session.getAttribute("userTen");
	Utility util = (Utility) session.getAttribute("util");
	ResultSet rsDonViTinh = spBean.getRsDonViTinh();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>SalesUp - Project</TITLE>
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
	filter: progid:DXImageTransform.Microsoft.Shadow(color=gray, direction=135);
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
	jQuery(function($) {
		$('.addspeech').speechbubble();
	});
</script>

<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.datepicker.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".days").datepicker({
			changeMonth : true,
			changeYear : true
		});
	});
</script>

<script type="text/javascript" src="../scripts/ajax.js"></script>
<script type="text/javascript" src="../scripts/erp-spList.js"></script>

<script type="text/javascript" src="../scripts/dropdowncontent.js"></script>
<script type="text/javascript" src="../scripts/cool_DHTML_tootip.js"></script>
<script type="text/javascript" src="../scripts/jquery.autocomplete.js"></script>
<!-- <link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
     $(document).ready(function() { $("select").select2();  });
     
</script> -->

<script language="javascript" type="text/javascript">
	function saveform() {
		document.forms['spFormDN'].action.value = 'save';
		document.forms['spFormDN'].submit();
	}

	function checkSanPham() {
		var masp = document.getElementsByName("masp");
		for (var hh = 0; hh < masp.length; hh++) {
			if (masp.item(hh).value != "") {
				return true;
			}
		}
		return false;
	}

	function submitform() {
		document.forms['spFormDN'].action.value = 'submit';
		document.forms["spFormDN"].submit();
	}

	function themmoi() {
		document.forms['spFormDN'].action.value = 'themmoi';
		document.forms['spFormDN'].IsCapNhat.value = '1';
		document.forms["spFormDN"].submit();
	}

	function quaylaiInit() {
		document.forms['spFormDN'].action.value = 'quaylaiInit';
		document.forms['spFormDN'].IsCapNhat.value = '0';
		document.forms["spFormDN"].submit();
	}

	function savekhachhang() {
		document.forms['spFormDN'].action.value = 'savekhachhang';
		document.forms['spFormDN'].IsCapNhat.value = '0';
		document.forms["spFormDN"].submit();
	}

	function Chinhsua() {
		document.forms['spFormDN'].action.value = 'chinhsua';
		document.forms['spFormDN'].IsCapNhat.value = '0';
		var id_dh = document.forms['spFormDN'].id_dh.value;
		if (id_dh == "") {
			alert('Không xác định số chứng từ sửa,vui lòng kiểm tra lại');
			return;
		}

		document.forms["spFormDN"].submit();
	}

	function goBack() {
		window.history.back();
	}
</script>
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	<form name="spFormDN" method="post"
		action="../../SanPhamDuongNguyenNewSvl">
		<input type="hidden" name="userId" value='<%=userId%>'> <input
			type="hidden" name="action" value="1" /> <input type="hidden"
			name="id" value='<%=spBean.getId()%>'>
		<div id="main" style="width: 100%; padding-left: 2px">
			<div align="left" id="header" style="width: 100%; float: none">
				<div style="width: 60%; padding: 5px; float: left"
					class="tbnavigation">Thong tin sản phẩm</div>
				<div align="right" style="padding: 5px" class="tbnavigation">
					Chào mừng bạn
					<%=userTen%>
				</div>
			</div>
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
								href="../../SanphamListSvl?userId=<%=userId%>"> <IMG
									src="../images/Back_undo.png" title="Undo" alt="Undo"
									border="1px" style="border-style: outset"></A>
						</span></TD>


					</TR>
				</table>


			</div>
			<div align="left" style="width: 100%; float: none; clear: left">
				<fieldset>
					<legend class="legendtitle"> Thông báo</legend>
					<textarea name="dataerror" id="Msg" rows="1" readonly="readonly"
						style="width: 100%"><%=spBean.getMsg()%></textarea>
				</fieldset>
			</div>
			<div align="left" style="width: 100%; float: none; clear: left">
				<fieldset>
					<legend class="legendtitle"> Thêm sản phẩm </legend>
					<div style="float: none; width: 100%" align="left">
						<table width="100%" cellpadding="4" cellspacing="0">

							<TR>
								<TD class="plainlabel">Mã</TD>
								<TD class="plainlabel"><input type="text" name="ma"
									value="<%=spBean.getMa()%>"></TD>

							</TR>
							<TR>
								<TD class="plainlabel">Tên</TD>
								<TD class="plainlabel"><input type="text" name="ten"
									value="<%=spBean.getTen()%>"></TD>

							</TR>
							<TR>
								<TD class="plainlabel">Đơn vi</TD>
								<TD class="plainlabel"><select name="donViTinh"
									style="width: 500px">
										<option></option>
										<%
											if (rsDonViTinh != null) {
												while (rsDonViTinh.next()) {
													if (rsDonViTinh.getString("pk_seq").equals(spBean.getDonViTinh())) {
										%>
										<option value="<%=rsDonViTinh.getString("pk_seq")%>"
											selected="selected"><%=rsDonViTinh.getString("ma")%>
										</option>
										<%
											} else {
										%>
										<option value="<%=rsDonViTinh.getString("pk_seq")%>"><%=rsDonViTinh.getString("ma")%>
										</option>
										<%
											}
												}
										%>
										<%
											}
										%>

								</select></TD>

							</TR>
							<TR>
								<TD class="plainlabel">Trạng thái</TD>
								<TD class="plainlabel">
									<%
										if (spBean.getTrangThai().equals("1")) {
									%> <input type="checkbox" name="tinhTrang" checked="checked"
									value="1"> <%
 	} else {
 %> <input type="checkbox" name="tinhTrang" value="1"> <%
 	}
 %>
								</TD>

							</TR>
						</table>
						<hr>
					</div>
				</fieldset>
			</div>
		</div>


		<%
			spBean.dbClose();
		%>
	</form>
</body>
</html>