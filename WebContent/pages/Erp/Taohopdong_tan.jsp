<%@page import="geso.erp.beans.hopdong_tan.*"%>
<%@page import="geso.erp.beans.hopdong_tan.imp.*"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="geso.dms.center.util.Utility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.Hashtable"%>
<%@ page import="java.util.List"%>
<%
	ITaohopdong_tan obj = (Taohopdong_tan) session.getAttribute("obj");
	String userId = (String) session.getAttribute("userId");
	String userTen = (String) session.getAttribute("userTen");
	String action1=(String)session.getAttribute("action");
	Utility util = (Utility) session.getAttribute("util");
	

	ResultSet rsListHopdong = (ResultSet)obj.getRsHD();
	ResultSet rsListHoso = (ResultSet)obj.getRsHS();
	ResultSet rsqg=obj.getRsQG();

	String readonly="readonly";
	if(obj.getIsCapNhat().equals("1") ){
		readonly="";
	}
	
	String disabled="disabled";
	if(obj.getIsCapNhat().equals("1") ){
		disabled="";
	}
	NumberFormat formatter = new DecimalFormat("#,###,###.###");
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
	filter: progid: DXImageTransform.Microsoft.Shadow(       color=
		    
		 gray, direction=       135);
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
<script type="text/javascript" src="../scripts/erp_tanlist.js"></script>

<script type="text/javascript" src="../scripts/dropdowncontent.js"></script>
<script type="text/javascript" src="../scripts/cool_DHTML_tootip.js"></script>
<script type="text/javascript" src="../scripts/jquery.autocomplete.js"></script>
<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
     $(document).ready(function() { $("select").select2();  });
     
</script>

<script language="javascript" type="text/javascript">
	function checkInput(){
		/*$('#rdHopdong').click(function (){
			var checkedradio = $('[name="rdHopdong"]:radio:checked').val();
		});*/
		var r_value = "";
	    if (document.dmhForm.rdHopdong.checked){
	    	r_value += (r_value?',':'')+document.dmhForm.rdHopdong.value;
	    }
	    document.dmhForm.hidenHopdong.value=r_value;
	}
	
	function saveform(){
		document.forms['dmhForm'].action.value='save';
	    document.forms['dmhForm'].submit();
	}

	function print_pdf(){
		document.forms["erpDmhForm"].action.value = "print";
		document.forms["erpDmhForm"].submit();
	}
	function checkSanPham(){
		 var masp = document.getElementsByName("txtMasp");
		 for(var hh = 0; hh < masp.length; hh++)
		 {
			if(masp.item(hh).value != ""){
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
	 function loadhoso() {
			document.forms['dmhForm'].action.value = 'hoso';
			document.forms["dmhForm"].submit();
	 }
	 function loadquocgia() {
			document.forms['dmhForm'].action.value = 'quocgia';
			document.forms["dmhForm"].submit();
	 }
	 
	 function themmoi(){ 		
		 document.forms['dmhForm'].action.value='themmoi';
		 document.forms['dmhForm'].IsCapNhat.value='1';
	     document.forms["dmhForm"].submit();
	 }
	 
	 function Chinhsua()
	 {
		 document.forms['dmhForm'].action.value='chinhsua';
		 document.forms['dmhForm'].IsCapNhat.value='0';
		 var  id_dh= document.forms['dmhForm'].id_dh.value;
		 if(id_dh==""){
			 alert('Không xác định số chứng từ sửa,vui lòng kiểm tra lại');
			 return;
		 }
		 
	     document.forms["dmhForm"].submit();
	 }

	 function goBack()
	 {
	  	window.history.back();
	 }
	
	function keypress(e) //Hàm dùng để ngăn người dùng nhập các kí tự khác ký tự số vào TextBox
	{    
		var keypressed = null;
		if (window.event)
			keypressed = window.event.keyCode; //IE
		else
			keypressed = e.which; //NON-IE, Standard
		
		if (keypressed < 48 || keypressed > 57)
		{ 
			if (keypressed == 8 || keypressed == 127 || keypressed == 37 || keypressed == 39 || keypressed == 0 || keypressed == 46)
			{//Phím Delete và Phím Back
				return;
			}
			return false;
		}
	}
		
		//--------------
		
 
</script>
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	<form id="dmhForm" name="dmhForm" method="post"
		action="../../TaohopdongSvl_tan">
		<input type="hidden" name="userId" value='<%=userId%>'> <input
			type="hidden" name="action" value="<%=action1%>" /> <input
			type="hidden" name="id_ck" value='<%=obj.getIdSohopdong()%>'>

		<input type="hidden" id="IsCapNhat" name="IsCapNhat"
			value='<%=obj.getIsCapNhat()%>'>

		<div id="main" style="width: 100%; padding-left: 2px">
			<div align="left" id="header" style="width: 100%; float: none">
				<div style="width: 60%; padding: 5px; float: left"
					class="tbnavigation">Trang tạo hợp đồng</div>
				<div align="right" style="padding: 5px" class="tbnavigation">
					Chào mừng bạn
					<%=userTen%>
				</div>
			</div>
			<div align="left" id="button"
				style="border: 1; width: 100%; height: 32px; padding: 1px; float: none"
				class="tbdarkrow">

				<TABLE border="0" cellpadding="0" cellspacing="0">
					<TR class="tbdarkrow">
						<%if(obj.getIsCapNhat().equals("1")){%>
						<TD width="30px" align="left"><A href="javascript:saveform()">
								<IMG src="../images/1455966997_save.png" title="Save" alt="Save"
								border="1px" style="border-style: outset">
						</A></TD>
						<%}%>
						<TD width="30px" align="left"><span id="btnUndo"> <A
								href="../../HopdongSvl_tan?userId=<%=userId%>"> <IMG
									src="../images/Back_undo.png" title="Undo" alt="Undo"
									border="1px" style="border-style: outset">
							</A>
						</span></TD>
					</TR>
				</TABLE>
			</div>
			<div align="left" style="width: 100%; float: none; clear: left">
				<fieldset>
					<legend class="legendtitle"> Thông báo</legend>
					<textarea name="dataerror" id="Msg" rows="1" readonly="readonly"
						style="width: 100%"><%=obj.getMsg()%></textarea>
				</fieldset>
			</div>
			<div align="left" style="width: 100%; float: none; clear: left">
				<fieldset>
					<legend class="legendtitle"> Thông tin HỢP ĐỒNG </legend>
					<TABLE width="100%" cellpadding="6px" cellspacing="0px"
						style="margin-top: 5px">
						<TR>
							<%
                        System.out.println("Kysssss hợp đồng: " + obj.getKyhopdong());
                        if(obj.getKyhopdong().equals("1")==true){%>
							<TD class="plainlabel" width="15%">Ký hợp đồng <input
								type="radio" name="rdHopdong" id="rdHopdong" value="1"
								checked="checked" />
							</TD>
							<TD class="plainlabel">Không ký hợp đồng <input type="radio"
								name="rdHopdong" id="rdHopdong" value="0" />
							</TD>
							<%}else{ %>
							<TD class="plainlabel" width="15%">Ký hợp đồng <input
								type="radio" name="rdHopdong" id="rdHopdong" value="1" />
							</TD>
							<TD class="plainlabel">Không ký hợp đồng <input type="radio"
								name="rdHopdong" id="rdHopdong" value="0" checked="checked" />
							</TD>
							<%} %>

							<TD class="plainlabel" width="15%"></td>
							<td class="plainlabel"></td>
						</TR>
						<tr>
							<TD class="plainlabel" width="15%">Lý do</td>
							<td class="plainlabel"><input type="text" name="txtLydo"
								id="txtLydo" value="<%=obj.getLydo()%>"
								style="text-align: right; font-size: 14px; font-weight: bold;">
							</td>
							<TD class="plainlabel" width="15%"></td>
							<td class="plainlabel"></td>
						</tr>
						<TR>
							<TD class="plainlabel" width="15%">Hồ sơ</TD>
							<TD class="plainlabel"><select name="cbHoso"
								style="width: 200px">
									<option></option>
									<%
											if (rsListHoso != null) {
												while (rsListHoso.next()) {
													if (rsListHoso.getString("PK_SEQ").equals(obj.getHoso_fk())) {
										%>
									<option value="<%=rsListHoso.getString("PK_SEQ")%>"
										selected="selected"><%=rsListHoso.getString("TEN")%>
									</option>
									<%
											} else {
										%>
									<option value="<%=rsListHoso.getString("PK_SEQ")%>"><%=rsListHoso.getString("TEN")%>
									</option>
									<%
											}
												}
										%>
									<%
											}
										%>
							</select></TD>
							<TD class="plainlabel" width="15%"></TD>
							<TD class="plainlabel"></TD>

						</TR>
						<TR>
							<TD class="plainlabel" width="15%">Giá trị hợp đồng</TD>
							<TD class="plainlabel"><input type="text"
								name="txtGiatrihopdong" id="txtGiatrihopdong"
								value="<%=obj.getGiatrihopdong()%>" /></TD>
							<TD class="plainlabel" width="15%"></td>
							<td class="plainlabel"></td>
						</TR>
						<TR>
							<TD class="plainlabel" width="15%">Số hợp đồng</TD>
							<TD class="plainlabel"><input type="text"
								name="txtSohopdong" id="txtSohopdong"
								value="<%=obj.getSohopdong()%>"
								style="text-align: right; font-size: 14px; font-weight: bold;">
							</TD>
							<TD class="plainlabel" width="15%"></TD>
							<TD class="plainlabel"></TD>
						</TR>
					</TABLE>
				</fieldset>
			</div>
		</div>
		<%
	obj.dbClose();
%>
		<script type="text/javascript">
	replaces();
</script>
	</form>
</BODY>
</html>