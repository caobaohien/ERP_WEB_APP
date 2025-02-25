<%@page import="geso.erp.beans.donbanhang_duongnguyen.ISanPham"%>
<%@page import="geso.erp.beans.donbanhang_duongnguyen.IDonHang"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="geso.dms.center.util.Utility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.Hashtable"%>
<%@ page import="java.util.List"%>
<%
	IDonHang dhBean = (IDonHang) session.getAttribute("dhnew");
	String userId = (String) session.getAttribute("userId");
	System.out.println(userId);
	String userTen = (String) session.getAttribute("userTen");
	Utility util = (Utility) session.getAttribute("util");
	ResultSet rsKhachHang = dhBean.getRsKhachHang();
	List<ISanPham> listSP = dhBean.getListSP();
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
<script type="text/javascript" src="../scripts/erp-spList-DN.js"></script>

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
		document.forms['dhFormDN'].action.value = 'save';
		document.forms['dhFormDN'].submit();
	}

	function checkSanPham() {
		var masp = document.getElementsByName("maSP");
		for (var hh = 0; hh < masp.length; hh++) {
			if (masp.item(hh).value != "") {
				return true;
			}
		}
		return false;
	}

	function submitform() {
		document.forms['dhFormDN'].action.value = 'submit';
		document.forms["dhFormDN"].submit();
	}

	function themmoi() {
		document.forms['dhFormDN'].action.value = 'themmoi';
		document.forms['dhFormDN'].IsCapNhat.value = '1';
		document.forms["dhFormDN"].submit();
	}

	function quaylaiInit() {
		document.forms['dhFormDN'].action.value = 'quaylaiInit';
		document.forms['dhFormDN'].IsCapNhat.value = '0';
		document.forms["dhFormDN"].submit();
	}

	function savekhachhang() {
		document.forms['dhFormDN'].action.value = 'savekhachhang';
		document.forms['dhFormDN'].IsCapNhat.value = '0';
		document.forms["dhFormDN"].submit();
	}

	function Chinhsua() {
		document.forms['dhFormDN'].action.value = 'chinhsua';
		document.forms['dhFormDN'].IsCapNhat.value = '0';
		var id_dh = document.forms['dhFormDN'].id_dh.value;
		if (id_dh == "") {
			alert('Không xác định số chứng từ sửa,vui lòng kiểm tra lại');
			return;
		}

		document.forms["dhFormDN"].submit();
	}

	function goBack() {
		window.history.back();
	}
	function replaces() {

		var idSP = document.getElementsByName("idSP");
		//lay ra 1 mang tren trang của một input : tuong tu nhu reques.getprametervalue

		var maSP = document.getElementsByName("maSP");
		var tenSP = document.getElementsByName("tenSP");
		var donViTinh = document.getElementsByName("donViTinh");
		var soLuong = document.getElementsByName("soLuong");
		var donGia = document.getElementsByName("donGia");
		var thanhTien = document.getElementsByName("thanhTien");
		var soDong = maSP.length;
		var i;
		for (i = 0; i < soDong; i++) {
			if (maSP.item(i).value != "") {
				var sp = maSP.item(i).value;
				var pos = parseInt(sp.indexOf(" -- "));
				var slg = soLuong.item(i).value.replace(/,/g, "");
				if (pos > 0) {
					maSP.item(i).value = sp.substring(0, pos);
					sp = sp.substr(parseInt(sp.indexOf(" -- ")) + 3);
					tenSP.item(i).value = sp.substring(0, parseInt(sp
							.indexOf(" [")));
					sp = sp.substr(parseInt(sp.indexOf(" [")) + 2);
					donViTinh.item(i).value = sp.substring(0, parseInt(sp
							.indexOf("] [")));
					sp = sp.substr(parseInt(sp.indexOf("] [")) + 3);
					donGia.item(i).value = sp.substring(0, parseInt(sp
							.indexOf("] [")));
					sp = sp.substr(parseInt(sp.indexOf("] [")) + 3);
					idSP.item(i).value = sp.substring(0, parseInt(sp
							.indexOf("]")));
					soLuong.item(i).focus();
				}

				if (soLuong.item(i).value != "") {
					if (donGia.item(i).value != "") {
						var dgia = donGia.item(i).value.replace(/,/g, "");
						var tt = parseFloat(slg) * parseFloat(dgia);
						thanhTien.item(i).value = DinhDangDonGia(Math
								.round(tt * 1000) / 1000);
					}
				}
			} else {
				idSP.item(i).value = "";
				tenSP.item(i).value = "";
				donViTinh.item(i).value = "";
				soLuong.item(i).value = "";
				donGia.item(i).value = "";
				thanhTien.item(i).value = "";
			}
		}
		TinhTien();
		setTimeout(replaces, 500);
	}
	function DinhDangDonGia(num) {
		num = num.toString().replace(/\$|\,/g, '');

		var sole = '';
		if (num.indexOf(".") >= 0) {
			sole = num.substring(num.indexOf('.'));
		}

		if (isNaN(num))
			num = "0";
		sign = (num == (num = Math.abs(num)));
		num = Math.floor(num * 100);
		num = Math.floor(num / 100).toString();
		for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
			num = num.substring(0, num.length - (4 * i + 3)) + ','
					+ num.substring(num.length - (4 * i + 3));
		var kq;
		if (sole.length >= 0) {
			kq = (((sign) ? '' : '-') + num) + sole;
		} else
			kq = (((sign) ? '' : '-') + num);
		return kq;
	}
	function TinhTien() {
		var soLuong = document.getElementsByName("soLuong");
		var donGia = document.getElementsByName("donGia");
		var thanhTien = document.getElementsByName("thanhTien");
		var tongTien = 0;
		for (var i = 0; i < thanhTien.length; i++) {
			if (thanhTien.item(i).value != "") {
				var thanh_tien = thanhTien.item(i).value.replace(/,/g, "");
				tongTien = parseFloat(tongTien) + parseFloat(thanh_tien);
			}
		}
		document.getElementById("tongTien").value = DinhDangTien(Math
				.round(tongTien));
	}
	function DinhDangTien(num) {
		num = num.toString().replace(/\$|\,/g, '');
		if (isNaN(num))
			num = "0";
		sign = (num == (num = Math.abs(num)));
		num = Math.floor(num * 100);
		num = Math.floor(num / 100).toString();
		for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
			num = num.substring(0, num.length - (4 * i + 3)) + ','
					+ num.substring(num.length - (4 * i + 3));
		return (((sign) ? '' : '-') + num);
	}
</script>
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	<form name="dhFormDN" method="post"
		action="../../DonHangDuongNguyenNewSvl">
		<input type="hidden" name="userId" value='<%=userId%>'> <input
			type="hidden" name="action" value="1" /> <input type="hidden"
			name="id" value='<%=dhBean.getId()%>'>
		<div id="main" style="width: 100%; padding-left: 2px">
			<div align="left" id="header" style="width: 100%; float: none">
				<div style="width: 60%; padding: 5px; float: left"
					class="tbnavigation">Thong tin Đơn Hàng</div>
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
								href="../../DonHangDuongNguyenListSvl?userId=<%=userId%>"> <IMG
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
						style="width: 100%"><%=dhBean.getMsg()%></textarea>
				</fieldset>
			</div>
			<div align="left" style="width: 100%; float: none; clear: left">
				<fieldset>
					<legend class="legendtitle"> Thêm đơn hàng </legend>
					<div style="float: none; width: 100%" align="left">
						<table width="100%" cellpadding="4" cellspacing="0">

							<tr>
								<td class="plainlabel">Khách Hàng</td>
								<td class="plainlabel"><select name="khachHang"
									style="width: 500px">
										<%
											if (rsKhachHang != null) {
												while (rsKhachHang.next()) {
													if (rsKhachHang.getString("pk_seq").equals(dhBean.getTenKH())) {
										%>
										<option value="<%=rsKhachHang.getString("pk_seq")%>"
											selected="selected"><%=rsKhachHang.getString("ten")%>
										</option>
										<%
											} else {
										%>
										<option value="<%=rsKhachHang.getString("pk_seq")%>"><%=rsKhachHang.getString("ten")%>
										</option>
										<%
											}
												}
										%>
										<%
											}
										%>

								</select></td>

							</tr>
							<tr>
								<td class="plainlabel">Ngày CT</td>
								<td class="plainlabel"><input type="date" name="ngayCT"
									value="<%=dhBean.getNgayCT()%>"></td>

							</tr>
							<tr>
								<td class="plainlabel">Tổng tiền</td>
								<td class="plainlabel"><input type="text" name="tongTien"
									id="tongTien" value="<%=dhBean.getTongTien()%>"></td>
							</tr>
							<tr>
								<td class="plainlabel">Trạng thái</td>
								<td class="plainlabel">
									<%
										if (dhBean.getTrangThai().equals("1")) {
									%> <input type="checkbox" name="trangThai" checked="checked"
									value="1"> (Đã chốt) <%
 	} else {
 %> <input type="checkbox" name="trangThai" value="1"> (Đã chốt)
									<%
 	}
 %>
								</td>

							</tr>
						</table>
						<table class="tabledetail" width="100%" border="0" cellpadding="2"
							cellspacing="2" style="font-size: 14px;">
							<tr class="tbheader">
								<th>Số TT</th>
								<th>Mã SP</th>
								<th>Tên SP</th>
								<th>DVT</th>
								<th>Số Lượng</th>
								<th>Đơn giá</th>
								<th>Thành tiền</th>
							</tr>
							<%
								int i = 0;
								for (i = 0; i < listSP.size(); i++) {
									if ((i % 2) == 0) {
										%>
							<tr class='tbdarkrow'>
								<%
												} else {
											%>
							
							<tr class='tblightrow'>
								<%
												}
									ISanPham sp = listSP.get(i);
							%>
								<td align="center" width="2%"><input type="text"
									value="<%=i + 1%>" style="text-align: center;"
									readonly="readonly"> <input type="hidden"
									value="<%=sp.getId()%>" name="idSP" style="width: 100%"
									readonly="readonly"></td>
								<td align="center" width="8%"><input type="text"
									name="maSP" style="width: 100%" value="<%=sp.getMaSP()%>"
									onkeyup="ajax_showOptions(this,'donmuahang',event)"
									AUTOCOMPLETE="off"></td>
								<td align="left" width="10%"><input type="text"
									value="<%=sp.getTenSP()%>" name="tenSP" style="width: 100%"
									readonly="readonly"></td>
								<td align="center" width="6%"><input type="text"
									value="<%=sp.getDVT()%>" name="donViTinh" style="width: 100%"></td>
								<td align="center" width="7%"><input type="number"
									value="<%=sp.getSoLuong()%>" name="soLuong"
									style="width: 100%; text-align: right;"
									onkeyup="FormatNumber(this)"></td>
								<td align="center" width="8%"><input type="text"
									value="<%=sp.getDonGia()%>" name="donGia"
									style="width: 100%; text-align: right;"
									onkeyup="FormatNumber(this)"></td>
								<td align="center" width="8%"><input type="text"
									value="<%=sp.getThanhTien()%>" name="thanhTien"
									style="width: 100%; text-align: right;" readonly="readonly"></td>
							</tr>
							<%
								}
								while (i < 20 + listSP.size()) {
									if ((i % 2) == 0) {
										%>
							<tr class='tbdarkrow'>
								<%
												} else {
											%>
							
							<tr class='tblightrow'>
								<%
												}
							%>
								<td align="center" width="2%"><input type="text"
									value="<%=i + 1%>" style="text-align: center;"
									readonly="readonly"> <input type="hidden" value=""
									name="idSP" style="width: 100%" readonly="readonly"></td>

								<td align="center" width="8%"><input type="text"
									name="maSP" style="width: 100%" value=""
									onkeyup="ajax_showOptions(this,'donmuahang',event)"
									AUTOCOMPLETE="off"></td>
								<td align="left" width="10%"><input type="text" value=""
									name="tenSP" style="width: 100%" readonly="readonly"></td>
								<td align="center" width="6%"><input type="text" value=""
									name="donViTinh" style="width: 100%"></td>
								<td align="center" width="7%"><input type="number" value=""
									name="soLuong" style="width: 100%; text-align: right;"
									onkeyup="FormatNumber(this)"></td>
								<td align="center" width="8%"><input type="text" value=""
									name="donGia" style="width: 100%; text-align: right;"
									onkeyup="FormatNumber(this)"></td>
								<td align="center" width="8%"><input type="text" value=""
									name="thanhTien" style="width: 100%; text-align: right;"
									readonly="readonly"></td>
							</tr>
							<%
								i++;
								}
							%>
						</table>
						<hr>
					</div>
				</fieldset>
			</div>
		</div>


		<%
			dhBean.DBclose();
		%>
	</form>
	<script type="text/javascript">
		replaces();
	</script>
</body>
</html>