
<%@page
	import="geso.erp.beans.Tienhanhhopdongdaky_hieu.imp.TienhanhhopdongdakyUpdate"%>
<%@page
	import="geso.erp.beans.Tienhanhhopdongdaky_hieu.ITienhanhhopdongdakyUpdate"%>
<%@page
	import="geso.erp.beans.Tuvanthongtinhopdongdaky_hieu.imp.TuvanthongtinhopdongdakyUpdate"%>
<%@page
	import="geso.erp.beans.Tuvanthongtinhopdongdaky_hieu.ITuvanthongtinhopdongdakyUpdate"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="geso.dms.center.util.Utility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.Hashtable"%>
<%@ page import="java.util.List"%>
<%
	ITienhanhhopdongdakyUpdate obj = (TienhanhhopdongdakyUpdate) session.getAttribute("obj");
	String userId = (String) session.getAttribute("userId");
	String userTen = (String) session.getAttribute("userTen");
	String action1=(String)session.getAttribute("action");
	Utility util = (Utility) session.getAttribute("util");
	ResultSet dataListkh=obj.getDataList();
	NumberFormat formatter = new DecimalFormat("#,###,###.###");
	ResultSet dataListck = (ResultSet) obj.getRsck();
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
	filter: progid:               DXImageTransform.Microsoft.Shadow( 
		         
		   color=    
		    
		     gray, direction=               135);
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
<script type="text/javascript" src="../scripts/erp-spList_hieu.js"></script>

<script type="text/javascript" src="../scripts/dropdowncontent.js"></script>
<script type="text/javascript" src="../scripts/cool_DHTML_tootip.js"></script>
<script type="text/javascript" src="../scripts/jquery.autocomplete.js"></script>
<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
	$(document).ready(function() {
		$("select").select2();
	});
</script>

<script language="javascript" type="text/javascript">
	function saveform() {
		
		document.forms['dmhForm'].submit();
	}

	function submitform() {
		document.forms['dmhForm'].action.value = 'submit';
		document.forms["dmhForm"].submit();
	}
	function loadthongtin() {
		document.forms['dmhForm'].action.value = 'hopdong';
		document.forms["dmhForm"].submit();
	}

	function themmoi() {
		document.forms['dmhForm'].action.value = 'themmoi';
		document.forms["dmhForm"].submit();
	}

	function quaylaiInit() {
		document.forms['dmhForm'].action.value = 'quaylaiInit';
		document.forms['dmhForm'].IsCapNhat.value = '0';
		document.forms["dmhForm"].submit();
	}

	function print_excel() {
		document.forms["dmhForm"].action.value = "printexcel";
		document.forms["dmhForm"].submit();
	}
	function print_pdf()
	 {   
		document.forms["dmhForm"].action.value = "printpdf";
	    document.forms["dmhForm"].submit();
	 }
	function goBack() {
		window.history.back();
	}
	function replaces() {

		// lay mot mang
		var idsp = document.getElementsByName("idsp");
		var masp = document.getElementsByName("masp");
		var tensp = document.getElementsByName("tensp");
		var donvitinh = document.getElementsByName("dvt");
		var soluongton = document.getElementsByName("slton");

		var sodong = masp.length;
		console.log(sodong);

		var i;
		for (i = 0; i < sodong; i++) {
			if (masp.item(i).value != "") {

				var sp = masp.item(i).value;
				var pos = parseInt(sp.indexOf(" -- "));

				if (pos > 0) {
					console.log(pos);

					masp.item(i).value = sp.substring(0, pos);

					// substr ham nay truyen vao vi tri bat dau lay cua chuoi toi het chuoi
					//vd  ( VT10 -- VIETTEL10 [] [20,000] [0] [100010] )   lay tu vi tri 7: VIETTEL10 [] [20,000] [0] [100010]
					sp = sp.substr(parseInt(sp.indexOf(" -- ")) + 3);
					tensp.item(i).value = sp.substring(0, parseInt(sp
							.indexOf(" [")));

					sp = sp.substr(parseInt(sp.indexOf(" [")) + 2);
					donvitinh.item(i).value = sp.substring(0, parseInt(sp
							.indexOf("] [")));

					sp = sp.substr(parseInt(sp.indexOf("] [")) + 3);

					soluongton.item(i).value = sp.substring(0, parseInt(sp
							.indexOf("] [")));

					sp = sp.substr(parseInt(sp.indexOf("] [")) + 3);

					idsp.item(i).value = sp.substring(0, parseInt(sp
							.indexOf("]")));

				}

			} else {
				idsp.item(i).value = "";
				tensp.item(i).value = "";
				donvitinh.item(i).value = "";
				soluongton.item(i).value = "";
				
			}
		}

		setTimeout(replaces, 500);
	}
</script>
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	<form name="dmhForm" method="post"
		action="../../TienhanhhopdongdakyUpdateSvl_hieu">
		<input type="hidden" name="userId" value='<%=userId%>'> <input
			type="hidden" name="action" value="<%=action1%>" /> <input
			type="hidden" name="id" value='<%=obj.getId()%>'>
		<div id="main" style="width: 100%; padding-left: 2px">
			<div align="left" id="header" style="width: 100%; float: none">
				<div style="width: 60%; padding: 5px; float: left"
					class="tbnavigation">Thông tin các kế hoạch định hướng thị
					trường</div>
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


						<TD width="30px" align="left"><A href="javascript:saveform()">
								<IMG src="../images/1455966997_save.png" title="Save" alt="Save"
								border="1px" style="border-style: outset">
						</A></TD>
						<TD width="30px" align="left"><span id="btnUndo"> <A
								href="../../TienhanhhopdongdakyListSvl_hieu?userId=<%=userId%>">
									<IMG src="../images/Back_undo.png" title="Undo" alt="Undo"
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
						style="width: 100%"><%=obj.getmsg()%></textarea>
				</fieldset>
			</div>
			<div align="left" style="width: 100%; float: none; clear: left">
				<fieldset>
					<legend class="legendtitle"> Trang tư vấn hợp đồng </legend>
					<div style="float: none; width: 100%" align="left">
						<TABLE width="100%" cellpadding="4" cellspacing="0">
							<tr>
								<TD class="plainlabel" width="15%">Số hợp đồng</TD>
								<TD class="plainlabel"><select name="mahopdong"
									onchange="loadthongtin()" style="width: 200px">
										<option></option>
										<%
											if (dataListck != null) {
												while (dataListck.next()) {
													if (dataListck.getString("pk_seq").equals(
															obj.getMahopdong())) {
										%>
										<option value="<%=dataListck.getString("pk_seq")%>"
											selected="selected"><%=dataListck.getString("sohopdong")%>
										</option>
										<%
											} else {
										%>
										<option value="<%=dataListck.getString("pk_seq")%>"><%=dataListck.getString("sohopdong")%>
										</option>
										<%
											}
												}
										%>
										<%
											}
										%>

								</select></TD>

							</tr>

						</TABLE>
					</div>


					<fieldset>
						<legend class="legendtitle"> Thông Tin Cá Nhân </legend>
						<div style="float: none; width: 100%" align="left">
							<TABLE width="100%" cellpadding="4" cellspacing="0">
								<TR>
									<TD class="plainlabel" width="15%">Họ Tên</TD>
									<TD class="plainlabel"><input type="text" readonly
										name="hotenhs" value="<%=obj.getHotencanhan()%>" /></TD>
									<TD class="plainlabel" width="15%">Ngày sinh</TD>
									<TD class="plainlabel"><input type="text" maxlength="10"
										readonly name="ngaysinhhs" value="<%=obj.getNgaysinh()%>" />
									</TD>
								</TR>
								<TR>
									<TD class="plainlabel" width="15%">Trình độ học vấn</TD>
									<TD class="plainlabel">
										<%
											String arraytdhvnum[] = {"0", "1", "2", "3", "4", "5", "6"};
											String arraytdhv[] = {"THCS", "THPT",
													"Cao Đẳng-Đại Học năm nhất",
													"Cao Đẳng-Đại Học năm 2", "Cao Đẳng-Đại Học năm 3",
													"Cao Đẳng-Đại Học năm 4",
													"Cao Đẳng-Đại Học năm cuối"};
										%> <select readonly="readonly" name="trinhdohocvan">
											<option value=""></option>
											<%
												for (int i = 0; i < arraytdhvnum.length; i++) {
													if (obj.getTrinhdohocvan().equals(arraytdhvnum[i])) {
											%>
											<option selected="selected" value="<%=arraytdhvnum[i]%>"><%=arraytdhv[i]%></option>
											<%
												} else {
											%>
											<option value="<%=arraytdhvnum[i]%>"><%=arraytdhv[i]%></option>
											<%
												}
											%>
											<%
												}
											%>
									</select>
									</TD>
									<TD class="plainlabel" width="15%">Khả năng tiếng anh</TD>
									<TD class="plainlabel">
										<%
											String arraykntanum[] = {"0", "1", "2", "3", "4"};
											String arrayknta[] = {"ILTS", "TOEFL", "Trung Bình", "Khá",
													"Giỏi"};
										%> <select readonly="readonly" name="khanangtienganh">
											<option value=""></option>
											<%
												for (int i = 0; i < arraykntanum.length; i++) {
													if (obj.getkhanangtienganh().equals(arraykntanum[i])) {
											%>
											<option selected="selected" value="<%=arraykntanum[i]%>"><%=arrayknta[i]%></option>
											<%
												} else {
											%>
											<option value="<%=arraykntanum[i]%>"><%=arrayknta[i]%></option>
											<%
												}
											%>
											<%
												}
											%>

									</select>
									</TD>
								</TR>

								<TR>
									<TD class="plainlabel" width="15%">Nguyện vọng đi học</TD>
									<TD class="plainlabel">
										<%
											String arraynv1[] = {"0", "1", "2", "3"};
											String arraynv[] = {"Ở với người thân", "Tự do", "Học bổng",
													"khác"};
										%> <select readonly="readonly" name="nguyenvongdihoc"
										onchange="submitform()">

											<option value=""></option>
											<%
												for (int i = 0; i < arraynv1.length; i++) {
													if (obj.getNguyenvong().equals(arraynv1[i])) {
											%>
											<option selected="selected" value="<%=arraynv1[i]%>"><%=arraynv[i]%></option>

											<%
												} else {
											%>
											<option value="<%=arraynv1[i]%>"><%=arraynv[i]%></option>

											<%
												}
											%>
											<%
												}
											%>

									</select> <%
 	if (obj.getNguyenvong().equals("3")) {
 %> <input type="text" name="nguyenvongkhac" value="" maxlength="10" />
										<%
											}
										%>
									</TD>
									<TD class="plainlabel" width="15%">Khả năng học vấn</TD>
									<TD class="plainlabel">
										<%
											String arrayknhvnum[] = {"0", "1", "2", "3"};
											String arrayknhv[] = {"yếu", "Trung Bình", "Khá", "Giỏi"};
										%> <select readonly="readonly" name="khananghocvan">

											<option value=""></option>
											<%
												for (int i = 0; i < arrayknhvnum.length; i++) {
													if (obj.getKhananghocvan().equals(arrayknhvnum[i])) {
											%>
											<option selected="selected" value="<%=arrayknhvnum[i]%>"><%=arrayknhv[i]%></option>
											<%
												} else {
											%>
											<option value="<%=arrayknhvnum[i]%>"><%=arrayknhv[i]%></option>
											<%
												}
											%>
											<%
												}
											%>

									</select>
									</TD>
								</TR>

							</TABLE>
						</div>
					</fieldset>

					<fieldset>
						<legend class="legendtitle"> Thông tin hồ sơ cá nhân</legend>
						<div style="float: none; width: 100%" align="left">
							<TABLE width="100%" cellpadding="4" cellspacing="0">
								<TR>
									<TD class="plainlabel" width="15%">Số hợp đồng</TD>
									<TD class="plainlabel"><input type="text" readonly
										name="sohopdong" value="<%=obj.getSohopdong()%>" /></TD>
									<TD class="plainlabel" width="15%">Ngày ký hợp đồng</TD>
									<TD class="plainlabel"><input type="text" maxlength="10"
										readonly name="ngaykyhopdong"
										value="<%=obj.getNgaykyhopdong()%>" /></TD>
								</TR>
								<TR>
									<TD class="plainlabel" width="15%">Quốc gia đã chọn</TD>
									<TD class="plainlabel"><input type="text" readonly
										name="quocgia" value="<%=obj.getQuocgia()%>" /></TD>
									<TD class="plainlabel" width="15%">Giá trị hợp đồng</TD>
									<TD class="plainlabel"><input type="text" maxlength="10"
										readonly name="giatrihopdong"
										value="<%=obj.getGiatrihopdong()%>" /></TD>
								</TR>

							</TABLE>
						</div>
					</fieldset>
					<fieldset>
						<legend class="legendtitle"> Tiến hành hợp đồng</legend>
						<div style="float: none; width: 100%" align="left">
							<TABLE width="100%" cellpadding="4" cellspacing="0">
								<TR>
									<TD class="plainlabel" width="15%">Chuẩn bị</TD>
									<TD class="plainlabel"><textarea style="width: 400px"
											rows="4" cols="5" name="nhanxet"> <%= obj.getChuanbi() %></textarea>
									</TD>
								</TR>
								<TR>
									<TD class="plainlabel" width="15%">Ngày phỏng vấn</TD>
									<TD class="plainlabel"><input class="days" type="text"
										name="ngayphongvan" value="<%=obj.getNgayphongvan()%>" /></TD>
								</TR>
								<TR>
									<TD class="plainlabel" width="15%">Giờ phỏng vấn</TD>
									<TD class="plainlabel"><input type="text"
										name="giophongvan" value="<%=obj.getGiophongvan()%>" /></TD>
								</TR>
								<TR>
									<TD class="plainlabel" width="15%">Ngày nhập học dự kiến</TD>
									<TD class="plainlabel"><input class="days" type="text"
										name="ngaynhaphocdukien"
										value="<%=obj.getNgaynhaphocdukien()%>" /></TD>
								</TR>
								<tr>
									<TD class="plainlabel">Kết quả phỏng vấn</TD>
									<td class="plainlabel">
										<%
										String mang[] = new String[]{"0", "1"};
										String mangten[] = new String[]{"Đậu", "Rớt"};
									%> <select name="ketquaphongvan" onchange="submitform()"
										style="width: 80px">
											<option value=""></option>
											<%
											for (int j = 0; j < mang.length; j++) {
												if (obj.getKetquaphongvan().trim().equals(mang[j])) {
										%>
											<option selected="selected" value="<%=mang[j]%>">
												<%=mangten[j]%>
											</option>
											<%
											} else {
										%>
											<option value="<%=mang[j]%>">
												<%=mangten[j]%>
											</option>
											<%
											}
											}
										%>
									</select>
									</td>
								</TR>

							</TABLE>
						</div>
					</fieldset>
			</div>



			<%
				obj.DBclose();
			%>
			<script type="text/javascript">
				replaces();
				dropdowncontent.init("noidungGhiChu", "right-botom", 500,
						"click");
			</script>
			<%%>
		
	</form>
</BODY>
</html>