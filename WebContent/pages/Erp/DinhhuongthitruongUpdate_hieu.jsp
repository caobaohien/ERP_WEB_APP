
<%@page
	import="geso.erp.beans.Dinhhuongthitruong_hieu.IDinhhuongthitruongUpdate_hieu"%>
<%@page
	import="geso.erp.beans.Dinhhuongthitruong_hieu.IDinhhuong_hoatdong"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="geso.dms.center.util.Utility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.Hashtable"%>
<%@ page import="java.util.List"%>
<%
	IDinhhuongthitruongUpdate_hieu obj = (IDinhhuongthitruongUpdate_hieu) session.getAttribute("obj");
	String userId = (String) session.getAttribute("userId");
	String userTen = (String) session.getAttribute("userTen");
	String action1=(String)session.getAttribute("action");
	Utility util = (Utility) session.getAttribute("util");
	List<IDinhhuong_hoatdong> listhd =obj.getListkhhd();
	ResultSet dataListkh=obj.getDataList();
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
	function loadsanpham() {
		document.forms['dmhForm'].action.value = 'chuyenkho';
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
		action="../../DinhhuongthitruongUpdateSvl_hieu">
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
								href="../../DinhhuongthitruongListSvl_hieu?userId=<%=userId%>">
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
					<legend class="legendtitle"> Thêm danh sách định hướng
						hoạt động </legend>
					<div style="float: none; width: 100%" align="left">
						<TABLE width="100%" cellpadding="4" cellspacing="0">

							<tr>
								<TD class="plainlabel">Tình trạng thị trường</TD>
								<TD class="plainlabel"><input type="text" name="tinhtrang"
									style="width: 400px" value="<%=obj.getTinhtrangthitruong()%>"></TD>
							</tr>
							<tr>
								<TD class="plainlabel">Chọn Tháng</TD>
								<td class="plainlabel">
									<%
										int mang[] = new int[]{1,2,3,4,5,6,7,8,9,10,11,12};
										String mangten[] = new String[]{"Tháng một", "Tháng hai", "Tháng ba","Tháng bốn", "Tháng năm", "Tháng sáu","Tháng bảy", "Tháng tám", "Tháng chín","Tháng mười", "Tháng mười một", "Tháng mười hai"};
									%> <select name="thang" style="width: 200px">
										<option value=""></option>
										<%
											for (int j = 0; j < mang.length; j++) {
												if (obj.getThang()==(mang[j])) {
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
							<tr>
								<TD class="plainlabel">Chọn Năm</TD>
								<td class="plainlabel">
									<%
										 int mang1[] = new int[]{2016,2017,2018,2019,2020};
										String mangten1[] = new String[]{"năm 2016", "Năm 2017", "Năm 2018","Năm 2019", "Năm 2020"};
									%> <select name="nam" style="width: 200px">
										<option value=""></option>
										<%
											for (int j = 0; j < mang1.length; j++) {
												if (obj.getNam()==(mang1[j])) {
													%>
										<option selected="selected" value="<%=mang1[j]%>">
											<%=mangten1[j]%>
										</option>
										<%
														} else {
											
										%>
										<option value="<%=mang1[j]%>">
											<%=mangten1[j]%>
										</option>
										<%
														}
											}
										%>
								</select>
								</td>
							</TR>

							<tr>
								<TD class="plainlabel">Chọn Tháng Nhận Định</TD>
								<td class="plainlabel">
									<%
									int mang2[] = new int[]{1,2,3,4,5,6,7,8,9,10,11,12};
									String mangten2[] = new String[]{"Tháng một", "Tháng hai", "Tháng ba","Tháng bốn", "Tháng năm", "Tháng sáu","Tháng bảy", "Tháng tám", "Tháng chín","Tháng mười", "Tháng mười một", "Tháng mười hai"};
									%> <select name="thangdinhhuong" style="width: 200px">
										<option value=""></option>
										<%
											for (int j = 0; j < mang2.length; j++) {
												if (obj.getNhandinhthoigiantheothang()==(mang2[j])) {
													%>
										<option selected="selected" value="<%=mang2[j]%>">
											<%=mangten2[j]%>
										</option>
										<%
														} else {
										%>
										<option value="<%=mang2[j]%>">
											<%=mangten2[j]%>
										</option>
										<%
														}
											}
										%>
								</select>
								</td>
							</TR>

							<tr>
								<TD class="plainlabel">Chọn Quý Nhận Định</TD>
								<td class="plainlabel">
									<%
										int mang3[] = new int[]{1,2,3,4};
										String mangten3[] = new String[]{"Quý I", "Quý II", "Quý III","Quý IV"};
									%> <select name="quydinhhuong" style="width: 200px">
										<option value=""></option>
										<%
											for (int j = 0; j < mang3.length; j++) {
												if (obj.getNhandinhthoigiantheoquy()==(mang3[j])) {
													%>
										<option selected="selected" value="<%=mang3[j]%>">
											<%=mangten3[j]%>
										</option>
										<%
														} else {
										
										%>
										<option value="<%=mang3[j]%>">
											<%=mangten3[j]%>
										</option>
										<%
														}
											
											}
										%>
								</select>
								</td>
							</TR>



						</TABLE>

						<div align="left" style="width: 100%; float: none; clear: none;">
							<TABLE class="tabledetail" width="100%" border="0"
								cellpadding="2" cellspacing="2" style="font-size: 14px;">
								<TR class="tbheader">
									<TH align="center" width="5%">STT</TH>
									<TH align="center" width="15%">Tên hoạt động</TH>
									<TH align="center" width="30%">Mô tả</TH>
									<TH align="center" width="10%">Kinh phí</TH>
									<TH align="center" width="10%">Từ ngày</TH>
									<TH align="center" width="10%">Đến ngày</TH>
									<TH align="center" width="10%">Số lượng KH tiềm năng</TH>
									<TH align="center" width="10%">Số lượng hành động</TH>
								</TR>

								<% 
							
							int i=0 ;
							for(  i=0;i<listhd.size();i++) {
								IDinhhuong_hoatdong hd=listhd.get(i);
								
								%>
								<TR>
									<Td align="center"><%=i%></Td>

									<Td align="center" width="15%"><input type="hidden"
										name="idhd" value="<%=hd.getId()%>"> <input
										type="text" name="tenhd" value="<%=hd.getTenhoatdong()%>">
									</Td>
									<Td align="center" width="30%"><input type="text"
										name="mota" value="<%=hd.getMota()%>"></Td>
									<Td align="center" width="10%"><input type="text"
										name="kinhphi" value="<%=hd.getKinhphi()%>"></Td>
									<Td align="center" width="10%"><input class="days"
										type="text" name="tungay" value="<%=hd.getTungay()%>">
									</Td>
									<Td align="center" width="10%"><input class="days"
										type="text" name="denngay" value="<%=hd.getDenngay()%>">
									</Td>
									<Td align="center" width="10%"><input type="text"
										name="slkh" value="<%=hd.getSoluongkh()%>"></Td>
									<Td align="center" width="10%"><input type="text"
										name="slhd" value="<%=hd.getSoluonghd()%>"></Td>
								</TR>

								<%
								}
								%>


								<!-- trường hợp list không có danh sách thì khởi tạo 1 list trống -->
								<%
							 for(i=listhd.size();i<listhd.size()+10 ;i++) {
								 
								
								%>
								<TR>
									<Td align="center" width="30px"><%=i %></Td>


									<Td align="center" width="15%"><input type="text"
										name="tenhd" value=""></Td>
									<Td align="center" width="30%"><input type="text"
										name="mota" value=""></Td>

									<Td align="center" width="5%"><input type="text"
										name="kinhphi" value=""></Td>
									<Td align="center" width="5%"><input class="days"
										type="text" name="tungay" value=""></Td>
									<Td align="center" width="5%"><input class="days"
										type="text" name="denngay" value=""></Td>
									<Td align="center" width="5%"><input type="text"
										name="slkh" value=""></Td>
									<Td align="center" width="5%"><input type="text"
										name="slhd" value=""></Td>


								</TR>

								<%
								}
								%>

							</TABLE>
						</div>
						<hr>
					</div>

				</fieldset>
			</div>
		</div>


		<%
			obj.DBclose();
		%>
		<script type="text/javascript">
			replaces();
			dropdowncontent.init("noidungGhiChu", "right-botom", 500, "click");
		</script>
		<%
		
	%>
	</form>
</BODY>
</html>