<%@page import="geso.erp.beans.donbanhang_hang.IChiTietDonHang_Hang"%>
<%@page import="geso.erp.beans.donbanhang_hang.imp.DonBanHang_Hang"%>
<%@page import="geso.erp.beans.donbanhang_hang.IDonBanHang_Hang"%>
<%@page import="geso.dms.center.util.Utility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.Hashtable"%>
<%@ page import="java.util.List"%>
<% 
	IDonBanHang_Hang obj = (IDonBanHang_Hang) session.getAttribute("objnew"); //bien session truyen giua servelet va jsp
	//trang nao cung load de xu ly du lieu ben trong
	String userId = (String) session.getAttribute("userId");
	String userTen = (String) session.getAttribute("userTen");
	String action = (String) session.getAttribute("action");
  	ResultSet rsKhachHang = obj.getRsKhachHang();
  	List<IChiTietDonHang_Hang>listsp = obj.getListSp();
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
<script type="text/javascript" src="../scripts/erp_ListSanPham_Hang.js"></script>
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
	 
	 function savekhachhang()
	 { 		
		 document.forms['dmhForm'].action.value='savekhachhang';
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
	 // SỬA LẠI THÔNG TIN
	 function replaces()
		{	
			var idsp = document.getElementsByName("idsp");
			var masp = document.getElementsByName("maSanPham");
			var tensp = document.getElementsByName("tenSanPham");
			var donvitinh = document.getElementsByName("donViTinh");
			var soluong = document.getElementsByName("soLuong");
			var soluongton = document.getElementsByName("soLuongTon");
			var dongia = document.getElementsByName("donGia");
			var thanhtien = document.getElementsByName("thanhTien");
			 
			var sodong =  masp.length;
			var i;
			for(i = 0; i < sodong; i++)
			{
				if(  masp.item(i).value != ""    )
				{ 
					
					var sp = masp.item(i).value;
					var pos = parseInt(sp.indexOf(" -- "));
					var slg = soluong.item(i).value.replace(/,/g,""); 
					var slgton = soluongton.item(i).value.replace(/,/g,""); 
					
					if(pos > 0)
					{
						masp.item(i).value = sp.substring(0, pos);
						sp = sp.substr(parseInt(sp.indexOf(" -- ")) + 3);
						tensp.item(i).value = sp.substring(0, parseInt(sp.indexOf(" [")));						
						sp = sp.substr(parseInt(sp.indexOf(" [")) + 2);
						
						//Đơn vị tính
						donvitinh.item(i).value = sp.substring(0, parseInt(sp.indexOf("] [")));						
						sp = sp.substr(parseInt(sp.indexOf("] [")) + 3);
						
						//Số lượng tồn
						soluongton.item(i).value = sp.substring(0, parseInt(sp.indexOf("] [")));
						sp = sp.substr(parseInt(sp.indexOf("] [")) + 3);
						
						//Đơn giá
						dongia.item(i).value = sp.substring(0, parseInt(sp.indexOf("] [")));
						sp = sp.substr(parseInt(sp.indexOf("] [")) + 3);
						
						idsp.item(i).value = sp.substring(0, parseInt(sp.indexOf("]")));
						
						//Sau khi cắt xong tự động chuyển qua tab Số Lượng
						soluong.item(i).focus();
						
					}
					//nếu soluong khác rỗng thì tính thành tiền và đơn giá luôn
					if(soluong.item(i).value != "")
					{						
						if(dongia.item(i).value != "")
						{
							var dgia = dongia.item(i).value.replace(/,/g,""); 				 
							var tt = parseFloat(slg) *  parseFloat(dgia);							
							thanhtien.item(i).value =  DinhDangDonGia( Math.round( tt * 1000 ) / 1000 );						 
						}
					}
					 
				}
				else 
				{
				 		
							idsp.item(i).value = "";
							tensp.item(i).value = "";
							donvitinh.item(i).value = "";					 
							soluongton.item(i).value = "";
							dongia.item(i).value = "";
							thanhtien.item(i).value = "";					 
				}
			}
		  
			TinhTien();
			setTimeout(replaces, 500);
		}
	 
	 function TinhTien()
		{
			 
			var soluong = document.getElementsByName("soLuong");
			var dongia = document.getElementsByName("donDia");
			var thanhtien = document.getElementsByName("thanhTien");				
			var tongTien = 0;
				for(var i = 0; i < thanhtien.length; i++)
					{
						//alert('TT: ' + thanhtien.item(i).value);
						if( thanhtien.item(i).value != "" )
						{
							var thanh_tien = thanhtien.item(i).value.replace(/,/g,"");
						 
							tongTien = parseFloat(tongTien) +  parseFloat(thanh_tien); 
							
						}
					}
			  
				//sửa thông tin
				document.getElementById("tongTien").value = DinhDangTien(Math.round(tongTien));
			 
			
		}
	 
	 function DinhDangTien(num) 
	 {
	    num = num.toString().replace(/\$|\,/g,'');
	    if(isNaN(num))
	    num = "0";
	    sign = (num == (num = Math.abs(num)));
	    num = Math.floor(num*100);
	    num = Math.floor(num/100).toString();
	    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
	    num = num.substring(0,num.length-(4*i+3))+','+
	    num.substring(num.length-(4*i+3));
	    return (((sign)?'':'-') + num);
	}
	 
	 function DinhDangDonGia(num) 
		{
			num = num.toString().replace(/\$|\,/g,'');
			 
			var sole = '';
			if(num.indexOf(".") >= 0)
			{
				sole = num.substring(num.indexOf('.'));
			}
			
			if(isNaN(num))
			num = "0";
			sign = (num == (num = Math.abs(num)));
			num = Math.floor(num*100);
			num = Math.floor(num/100).toString();
			for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
			num = num.substring(0,num.length-(4*i+3)) + ',' + num.substring(num.length-(4*i+3));

			var kq;
			if(sole.length >= 0)
			{
				kq = (((sign)?'':'-') + num) + sole;
			}
			else
				kq = (((sign)?'':'-') + num);
			
			//alert(kq);
			return kq;
		}
</script>
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	<form name="dmhForm" method="post"
		action="../../DonBanHangUpdate_HangSvl">
		<input type="hidden" name="userId" value='<%= userId %>'> <input
			type="hidden" name="action" value="<%=action %>" /> <input
			type="hidden" name="id" value='<%= obj.getId() %>'>
		<div id="main" style="width: 100%; padding-left: 2px">
			<div align="left" id="header" style="width: 100%; float: none">
				<div style="width: 60%; padding: 5px; float: left"
					class="tbnavigation">Thông tin sản phẩm</div>
				<div align="right" style="padding: 5px" class="tbnavigation">
					Chào mừng bạn
					<%= userTen %>
				</div>
			</div>
			<div align="left" id="button"
				style="border: 1; width: 100%; height: 32px; padding: 1px; float: none"
				class="tbdarkrow">

				<TABLE border="0" cellpadding="0" cellspacing="0">
					<TR class="tbdarkrow">
						<TD width="40px" align="left"><span id="btnInpdf"> <A
								href="../../DonBanHangInPdf_HangSvl?userId=<%=userId %>&pdf=<%= obj.getId()%>">
									<IMG src="../images/printericon.png" title="In đơn hàng"
									alt="In đơn hàng" border="1px" style="border-style: outset">
							</A>
						</span></TD>

						<!-- obj.getId() tùy trường hợp thay đổi -->
						<TD width="40px" align="left"><span id="btnInExcel"> <A
								href="../../DonBanHangInExcel_HangSvl?userId=<%=userId %>&excel=<%= obj.getId()%>">
									<IMG src="../images/excel.gif" title="In đơn hàng"
									alt="In đơn hàng" border="1px" style="border-style: outset">
							</A>
						</span></TD>
						<TD width="40px" align="left"><span id="btnUndo"> <A
								href="../../DonBanHang_HangSvl?userId=<%=userId%>"> <IMG
									src="../images/Back_undo.png" title="Undo" alt="Undo"
									border="1px" style="border-style: outset"></A>
						</span></TD>

					</TR>
				</TABLE>

			</div>
			<div align="left" style="width: 100%; float: none; clear: left">
				<fieldset>
					<legend class="legendtitle"> Thông báo</legend>
					<textarea name="dataerror" id="Msg" rows="1" readonly="readonly"
						style="height: 50px; width: 100%"><%= obj.getMsg() %></textarea>
				</fieldset>
			</div>
			<div align="left" style="width: 100%; float: none; clear: left">
				<fieldset>
					<legend class="legendtitle"> Thêm Đơn Hàng </legend>
					<div style="float: none; width: 100%" align="left">
						<TABLE width="100%" cellpadding="4" cellspacing="0">
							<TR>
								<TD class="plainlabel">Khách hàng</TD>
								<TD class="plainlabel"><select disabled name="maKhachHang"
									style="width: 200px">
										<option value=""></option>
										<% 
										if (rsKhachHang != null) {
											while (rsKhachHang.next()) {
												if ( rsKhachHang.getString("PK_SEQ").equals(obj.getKhachHang()) ) {
									%>
										<option value="<%= rsKhachHang.getString("PK_SEQ") %>"
											selected="selected">
											<%=rsKhachHang.getString("TEN") %>
										</option>
										<%
												} else {
											%>
										<option value="<%= rsKhachHang.getString("PK_SEQ") %>">
											<%=rsKhachHang.getString("TEN") %>
										</option>
										<% 
												}
											}
										}
									%>
								</select></TD>
							</TR>
							<TR>
								<TD class="plainlabel">Ngày chứng từ</TD>
								<TD class="plainlabel"><input type="text"
									name="ngayChungTu" class="days"
									value="<%= obj.getNgayChungTu()%>" readonly="readonly">
								</TD>
							</TR>
							<TR>
								<TD class="plainlabel">Tổng tiền</TD>
								<TD class="plainlabel"><input type="text" name="tongTien"
									id="tongTien" value="<%= obj.getTongTien() %>"
									readonly="readonly"></TD>
							</TR>
						</TABLE>
						<TABLE class="tabledetail" width="100%" border="0" cellpadding="2"
							cellspacing="2" style="font-size: 14px;">
							<TR class="tbheader">
								<TH>STT</TH>
								<TH>MÃ SẢN PHẨM</TH>
								<TH>TÊN SẢN PHẨM</TH>
								<TH>ĐƠN VỊ TÍNH</TH>
								<TH>SỐ LƯỢNG</TH>
								<TH>SỐ LƯỢNG TỒN</TH>
								<TH>ĐƠN GIÁ</TH>
								<TH>THÀNH TIỀN</TH>
							</TR>
							<%int i = 0; 
							for(i = 0; i<listsp.size();i++){
								IChiTietDonHang_Hang sp = listsp.get(i);
								%>
							<TR>
								<td>
									<%-- <%=(i+1) %> --%> <input type="text" value="<%= i + 1 %>"
									style="text-align: center;" readonly="readonly"> <input
									type="hidden" name="idsp" value="<%=sp.getIdSanPham()%>">
								</td>
								<td><input type="text" name="maSanPham"
									value="<%=sp.getMaSanPham()%>"
									onkeyup="ajax_showOptions(this,'donmuahang',event)"
									AUTOCOMPLETE="off" readonly="readonly"></td>
								<td><input type="text" name="tenSanPham"
									value="<%=sp.getTenSanPham()%>" readonly="readonly"></td>
								<td><input type="text" name="donViTinh"
									value="<%=sp.getDonViTinh()%>" readonly="readonly"></td>
								<td><input type="text" name="soLuong"
									value="<%=sp.getSoLuong()%>" readonly="readonly"></td>
								<td><input type="text" name="soLuongTon"
									value="<%=sp.getSoLuongTon()%>"></td>
								<td><input type="text" name="donGia"
									value="<%=sp.getDonGia()%>" readonly="readonly"></td>
								<td><input type="text" name="thanhTien"
									value="<%=sp.getThanhTien()%> " readonly="readonly"></td>
							</TR>
							<% 
							}%>
						</TABLE>
						<hr>
					</div>
				</fieldset>
			</div>
		</div>
		<%
		obj.closedb();
	%>
	</form>
	<script type="text/javascript">
	replaces();
</script>
</BODY>
</html>