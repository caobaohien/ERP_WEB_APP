<%@page import="geso.erp.beans.chuyenkho_hieu.ISanpham"%>
<%@page import="geso.erp.beans.chuyenkho_hieu.IChuyenkhoUpdate_hieu"%>


<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="geso.dms.center.util.Utility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.Hashtable"%>
<%@ page import="java.util.List"%>
<%
	IChuyenkhoUpdate_hieu obj = (IChuyenkhoUpdate_hieu) session.getAttribute("obj");
	String userId = (String) session.getAttribute("userId");
	String userTen = (String) session.getAttribute("userTen");
	String action1=(String)session.getAttribute("action");
	Utility util = (Utility) session.getAttribute("util");
	List<ISanpham> listsp =obj.getListSp();
	ResultSet dataListkh=obj.getRsdvk();
	NumberFormat formatter = new DecimalFormat("#,###,###.###");
	ResultSet dataListck = (ResultSet) obj.getRsck();
	ResultSet dataListck1 = (ResultSet) obj.getRsck1();
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
		action="../../ChuyenkhoUpdateSvl_hieu">
		<input type="hidden" name="userId" value='<%=userId%>'> <input
			type="hidden" name="action" value="<%=action1%>" /> <input
			type="hidden" name="id" value='<%=obj.getId()%>'>
		<div id="main" style="width: 100%; padding-left: 2px">
			<div align="left" id="header" style="width: 100%; float: none">
				<div style="width: 60%; padding: 5px; float: left"
					class="tbnavigation">Thông tin chuyển kho</div>
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
								href="../../ChuyenkhoListSvl_hieu?userId=<%=userId%>"> <IMG
									src="../images/Back_undo.png" title="Undo" alt="Undo"
									border="1px" style="border-style: outset">
							</A>
						</span></TD>

						<td <%
 							if (obj.getId().length()>0) {%> width="30px"
							align="left"><A href="javascript:print_excel()"> <IMG
								src="../images/Excel.jpg" title="Xuất chứng từ"
								alt="Xuất chứng từ" border="1px"
								style="border-style: outset; width: 30px; height: 30px">
						</A> <% } %></td>
						<td <%
 							if (obj.getId().length()>0) {%> width="30px"
							align="left"><A href="javascript:print_pdf()"> <IMG
								src="../images/pdf.jpg" title="Xuất chứng từ"
								alt="Xuất chứng từ" border="1px"
								style="border-style: outset; width: 30px; height: 30px">
						</A> <% } %></td>




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
					<legend class="legendtitle"> Thêm danh sách chuyển kho </legend>
					<div style="float: none; width: 100%" align="left">
						<TABLE width="100%" cellpadding="4" cellspacing="0">


							<TR>
								<TD class="plainlabel">Ngày ghi nhận</TD>
								<TD class="plainlabel"><input type="text" class="days"
									name="ngayghinhan" value="<%=obj.getNgaychuyenkho()%>">
								</TD>

							</TR>
							<tr>
								<TD class="plainlabel">Lý do</TD>
								<TD class="plainlabel"><input type="text"
									value="<%=obj.getLydochuyen()%>" name="lydo"
									style="width: 200px"></TD>
							</tr>
							<tr>
								<TD class="plainlabel">Tên kho chuyển</TD>
								<TD class="plainlabel"><select name="khochuyen"
									onchange="loadsanpham()" style="width: 200px"
									style="width: 200px">
										<option></option>
										<%
											if (dataListck != null) {
												while (dataListck.next()) {
													if (dataListck.getString("pk_seq").equals(
															obj.getKhochuyen())) {
										%>
										<option value="<%=dataListck.getString("pk_seq")%>"
											selected="selected"><%=dataListck.getString("TENKHO")%>
										</option>
										<%
											} else {
										%>
										<option value="<%=dataListck.getString("pk_seq")%>"><%=dataListck.getString("TENKHO")%>
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
							<tr>
								<TD class="plainlabel">Tên kho nhận</TD>
								<TD class="plainlabel"><select name="khonhan"
									style="width: 200px" style="width: 200px">
										<option></option>
										<%
											if (dataListck1 != null) {
												while (dataListck1.next()) {
													if (dataListck1.getString("pk_seq").equals(obj.getKhonhan())) {
										%>
										<option value="<%=dataListck1.getString("pk_seq")%>"
											selected="selected"><%=dataListck1.getString("TENKHO")%>
										</option>
										<%
											} else {
										%>
										<option value="<%=dataListck1.getString("pk_seq")%>"><%=dataListck1.getString("TENKHO")%>
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

						<div align="left" style="width: 100%; float: none; clear: none;">
							<TABLE class="tabledetail" width="100%" border="0"
								cellpadding="2" cellspacing="2" style="font-size: 14px;">
								<TR class="tbheader">
									<TH align="center" width="5%">STT</TH>

									<TH align="center" width="20%">Mã sản phẩm</TH>
									<TH align="center" width="35%">Tên sản phẩm</TH>

									<TH align="center" width="10%">ĐVT</TH>
									<TH align="center" width="10%">Số lượng tồn</TH>
									<TH align="center" width="20%">Số lượng chuyển</TH>
								</TR>

								<% 
							
							int i=0 ;
							for(  i=0;i<listsp.size();i++) {
								ISanpham sp=listsp.get(i);
								
								%>
								<TR>
									<Td align="center"><%=i%></Td>

									<Td align="center" width="10%"><input type="hidden"
										name="idsp" value="<%=sp.getId()%>"> <input
										type="text" name="masp" value="<%=sp.getMasanpham()%>"
										onkeyup="ajax_showOptions(this,'chuyenkho',event)"
										AUTOCOMPLETE="off"></Td>
									<Td align="center" width="15%"><input type="text"
										name="tensp" value="<%=sp.getTensanpham()%>"></Td>
									<Td align="center" width="5%"><input type="text"
										name="dvt" value="<%=sp.getDonvitinh()%>"></Td>
									<Td align="center" width="5%"><input type="text"
										name="slton" value="<%=sp.getSoluongton()%>"></Td>
									<Td align="center" width="5%"><input type="text"
										name="slchuyen" value="<%=sp.getSoluongchuyen()%>"></Td>
								</TR>

								<%
								}
								%>


								<!-- trường hợp list không có danh sách thì khởi tạo 1 list trống -->
								<%
							 
							 for(  i=listsp.size();i<listsp.size()+20 ;i++) {
								 
								
								%>
								<TR>
									<Td align="center" width="30px"><%=i %></Td>

									<Td align="center" width="10%"><input type="hidden"
										name="idsp" value=""> <input type="text" name="masp"
										value="" onkeyup="ajax_showOptions(this,'chuyenkho',event)"
										AUTOCOMPLETE="off"></Td>
									<Td align="center" width="15%"><input type="text"
										name="tensp" value=""></Td>

									<Td align="center" width="5%"><input type="text"
										name="dvt" value=""></Td>
									<Td align="center" width="5%"><input type="text"
										name="slton" value=""></Td>
									<Td align="center" width="5%"><input type="text"
										name="slchuyen" value=""></Td>

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