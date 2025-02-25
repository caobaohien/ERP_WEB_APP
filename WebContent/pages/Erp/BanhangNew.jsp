<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="geso.dms.center.util.Utility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="geso.erp.beans.banhang.*"%>
<%@ page import="geso.erp.beans.banhang.imp.*"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.Hashtable"%>
<%@ page import="java.util.List"%>
<%
	IBanhang dmhBean = (IBanhang) session.getAttribute("dmhBean");
 	List<ISanpham> spList = dmhBean.getSpList();
	 String userId = (String) session.getAttribute("userId");
	String userTen = (String) session.getAttribute("userTen");
	 ResultSet khrs=dmhBean.getRsKh();
	Utility util = (Utility) session.getAttribute("util");
	
	String readonly="readonly";
	if(dmhBean.getIsCapNhat().equals("1") ){
		readonly="";
	}
	
	String disabled="disabled";
	if(dmhBean.getIsCapNhat().equals("1") ){
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

	function replaces()
	{
		 
		
		var idsp = document.getElementsByName("idsp");
		var masp = document.getElementsByName("masp");
		var tensp = document.getElementsByName("tensp");
		var donvitinh = document.getElementsByName("donvitinh");
		var soluong = document.getElementsByName("soluong");
		var dongia = document.getElementsByName("dongia");
		var chietkhau = document.getElementsByName("chietkhau");
		var thanhtien = document.getElementsByName("thanhtien");
		 
		var sodong =  masp.length;
		var i;
		for(i = 0; i < sodong; i++)
		{
			if(  masp.item(i).value != ""    )
			{ 
				
				var sp = masp.item(i).value;
				var pos = parseInt(sp.indexOf(" -- "));
				var slg = soluong.item(i).value.replace(/,/g,""); 
				
				if(pos > 0)
				{
					masp.item(i).value = sp.substring(0, pos);
					sp = sp.substr(parseInt(sp.indexOf(" -- ")) + 3);
					tensp.item(i).value = sp.substring(0, parseInt(sp.indexOf(" [")));
					
					sp = sp.substr(parseInt(sp.indexOf(" [")) + 2);
					donvitinh.item(i).value = sp.substring(0, parseInt(sp.indexOf("] [")));
					
					sp = sp.substr(parseInt(sp.indexOf("] [")) + 3);
					
					dongia.item(i).value = sp.substring(0, parseInt(sp.indexOf("] [")));

					sp = sp.substr(parseInt(sp.indexOf("] [")) + 3);
					
					chietkhau.item(i).value = sp.substring(0, parseInt(sp.indexOf("] [")));

					sp = sp.substr(parseInt(sp.indexOf("] [")) + 3);
					
					idsp.item(i).value = sp.substring(0, parseInt(sp.indexOf("]")));
					
					soluong.item(i).focus();
					
				}
				
				if(soluong.item(i).value != "")
				{
					
					if(dongia.item(i).value != "")
					{
						var dgia = dongia.item(i).value.replace(/,/g,""); 
						  
						var ck=chietkhau.item(i).value.replace(/,/g,"");
						 
						var tt = parseFloat(slg) * ( parseFloat(dgia) - parseFloat(dgia) *parseFloat(ck) / 100 )  ;
						
						thanhtien.item(i).value =  DinhDangDonGia( Math.round( tt * 1000 ) / 1000 );
						
						 
					}
				}
				 
			}
			else 
			{
			 		
						idsp.item(i).value = "";
						tensp.item(i).value = "";
						donvitinh.item(i).value = "";
				 
						soluong.item(i).value = "";
						dongia.item(i).value = "";
						thanhtien.item(i).value = "";
						 
				 
			}
		}
	  
		TinhTien();
		setTimeout(replaces, 500);
	}
	
	function TinhTien()
	{
		 
		var soluong = document.getElementsByName("soluong");
		var dongia = document.getElementsByName("dongia");
		var chietkhau = document.getElementsByName("chietkhau");
		var thanhtien = document.getElementsByName("thanhtien");	
		
		var tongtien = 0;
		
		 
		 
			 
				for(var i = 0; i < thanhtien.length; i++)
				{
					//alert('TT: ' + thanhtien.item(i).value);
					if( thanhtien.item(i).value != "" )
					{
						var thanh_tien = thanhtien.item(i).value.replace(/,/g,"");
						 
							tongtien = parseFloat(tongtien) +  parseFloat(thanh_tien); 
						
					}
				}
		  
			
			document.getElementById("tongtientruocvat").value = DinhDangTien(Math.round(tongtien));
		 
		
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
	
	function FormatNumber(e)
	{
		e.value = DinhDangDonGia(e.value);
	}
	
	function roundNumber(num, dec) 
	{
		var result = Math.round(num*Math.pow(10,dec))/Math.pow(10,dec);
		return result;
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
	
	function keypress(e) //Hàm dùng d? ngan ngu?i dùng nh?p các ký t? khác ký t? s? vào TextBox
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
	 
	 
	  function timkiem()
	 { 		
		 document.forms['dmhForm'].action.value='timkiem';
		 document.forms['dmhForm'].IsCapNhat.value='0';
	     document.forms["dmhForm"].submit();
	 }
	 
	 function VeCuoi()
	 { 		
		 document.forms['dmhForm'].action.value='VeCuoi';
		 document.forms['dmhForm'].IsCapNhat.value='0';
	     document.forms["dmhForm"].submit();
	 }
	 function VeDau()
	 { 		
		 document.forms['dmhForm'].action.value='VeDau';
		 document.forms['dmhForm'].IsCapNhat.value='0';
	     document.forms["dmhForm"].submit();
	 }
	 function DichuyenLui()
	 { 		
		 document.forms['dmhForm'].action.value='DichuyenLui';
		 document.forms['dmhForm'].IsCapNhat.value='0';
	     document.forms["dmhForm"].submit();
	 }
	 
	 function DichuyenToi()
	 { 		
		 document.forms['dmhForm'].action.value='DichuyenToi';
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
			 alert('Không xác định số chứng từ sửa,vui lòng kiểm tra lại');
			 return;
		 }
		 
	     document.forms["dmhForm"].submit();
	 }
	 

	 function Xoa_banghi()
	 { 		
		 
		 
		 
		 document.forms['dmhForm'].action.value='Xoa_banghi';
		 document.forms['dmhForm'].IsCapNhat.value='0';
		 var  id_dh= document.forms['dmhForm'].id_dh.value;
		 if(id_dh==""){
			 alert('Không xác định số chứng từ xóa,vui lòng kiểm tra lại');
			 return;
		 }
		 if(!confirm('Bạn có chắc chắn muốn xóa chứng từ : '+id_dh+' này không?'))
		 {
		 	return false;
		 }
		 
	     document.forms["dmhForm"].submit();
	 }
	 
	 
	 
	 
	 
	 function changeNoiDung()
	 {
		 document.forms['dmhForm'].action.value='changeSP';
	     document.forms["dmhForm"].submit();
	 }
 
	 function goBack()
	 {
	  	window.history.back();
	 }
	 
	 function changeNoiDia()
	 {
		document.forms['dmhForm'].nccId.value= "";
		document.forms['dmhForm'].action.value='checkedNoiDia';
		document.forms["dmhForm"].submit();
		
	 } 
	function addRow(pos)
	{
		var table = $('#nn_'+pos);
		table.append(
	    	'<tr>'+
	       		'<td> <input type="text" name="sub_ngaynhan_'+pos+'" class="days"> </td>'+
				'<td> <input type="text" name="sub_soluong_'+pos+'"> </td>'+
	    	'</tr>'
	   			);
	}
	
	function ThemNgay(pos)
	{
		 var sl = window.prompt("Nhấp số lượng ngày muốn thêm", '');
		 if(isNaN(sl) == false && sl < 30)
		 {
			 for(var i=0; i < sl ; i++)
				 addRow(pos);
			 $( ".days" ).datepicker({			    
					changeMonth: true,
					changeYear: true				
			});
		 }
		 else
		 {
			 alert('Số lượng bạn nhập không hợp lệ. Mọi lần bạn chỉ được thêm tối đa ngày');
			 return;
		 }
	 }
</script>
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	<form name="dmhForm" method="post" action="../../BanhangUpdateSvl">
		<input type="hidden" name="userId" value='<%=userId%>'> <input
			type="hidden" name="action" value="1" /> <input type="hidden"
			name="id_dh" value='<%=dmhBean.getSochungtu()%>'> <input
			type="hidden" id="IsCapNhat" name="IsCapNhat"
			value='<%=dmhBean.getIsCapNhat()%>'>

		<div id="main" style="width: 100%; padding-left: 2px">
			<div align="left" id="header" style="width: 100%; float: none">
				<div style="width: 60%; padding: 5px; float: left"
					class="tbnavigation">Quản lý đơn hàng</div>
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
						<%if(!dmhBean.getIsCapNhat().equals("1")){ %>
						<TD width="40px" align="left"><span id="btnthem"> <A
								href="javascript:themmoi()"> <IMG src="../images/add32.png"
									title="Thêm mới" alt="Luu lai" border="1px"
									style="border-style: outset"></A>
						</span></TD>
						<%if(dmhBean.getTrangthai().equals("0") && dmhBean.getSochungtu().length()>0 && dmhBean.getIsCapNhat().equals("0")){ %>

						<TD width="50px" align="left"><A href="javascript:Chinhsua()">
								<IMG src="../images/edit32.png" title="Chỉnh sửa" alt="Luu lai"
								border="1px" style="border-style: outset">
						</A></TD>

						<TD width="50px" align="left"><A
							href="javascript:Xoa_banghi()"> <IMG
								src="../images/DeleteRed_32.png" title="Xóa" alt="Luu lai"
								border="1px" style="border-style: outset"></A></TD>

						<%} %>

						<%if(dmhBean.getSochungtu().length()>0){ %>
						<TD width="50px" align="left"><A
							href="../../DonHangInPdfSvl?userId=<%=userId %>&print=<%= dmhBean.getSochungtu()%>">
								<IMG src="../images/printericon.png" title="In Chúng từ"
								alt="In Chúng từ" border="1px" style="border-style: outset">
						</A></TD>
						<%} %>

						<%if(dmhBean.getSochungtu().length()>0){ %>
						<TD width="50px" align="left"><A
							href="../../DonHangInExcelSvl?userId=<%=userId %>&excel=<%= dmhBean.getSochungtu()%>">
								<IMG src="../images/excel.gif" title="In Chúng từ"
								alt="In Chúng từ" border="1px" style="border-style: outset">
						</A></TD>
						<%} %>


						<%} %>
						<%if(  dmhBean.getIsCapNhat().equals("1")){ %>
						<TD width="30px" align="left"><A href="javascript:saveform()">
								<IMG src="../images/1455966997_save.png" title="Save" alt="Save"
								border="1px" style="border-style: outset">
						</A></TD>
						<TD width="30px" align="left"><span id="btnUndo"> <A
								href="javascript:quaylaiInit()"> <IMG
									src="../images/Back_undo.png" title="Undo" alt="Undo"
									border="1px" style="border-style: outset"></A>
						</span></TD>

						<%} %>
						<TD width="50px" align="left"></TD>
						<td width="30px" align="left"><A href="javascript:VeCuoi()">
								<IMG src="../images/back_first.png" title="Về cuối"
								alt="Về cuối" border="1px" style="border-style: outset">
						</A></td>
						<td width="30px" align="left"><A
							href="javascript:DichuyenLui()"> <IMG
								src="../images/back_1.png" title="Lùi" alt="Lùi" border="1px"
								style="border-style: outset"></A></td>
						<td width="30px" align="left"><input
							style="width: 100px; height: 30px; border-style: outset"
							type="text" name="chungtutimkiem"
							value="<%=dmhBean.getchungtutimkiem()%>" onchange="timkiem()">
						</td>
						<td width="30px" align="left"><A
							href="javascript:DichuyenToi()"> <IMG
								src="../images/next_1.png" title="Lùi" alt="Tới" border="1px"
								style="border-style: outset"></A></td>
						<td width="30px" align="left"><A href="javascript:VeDau()">
								<IMG src="../images/next_e.png" title="Lùi" alt="Về Đầu"
								border="1px" style="border-style: outset">
						</A></td>
						<td width="30px" align="left"><A href="javascript:timkiem()">
								<IMG src="../images/timkiem_e.png" title="Tìm kiếm"
								alt="Tìm kiếm" border="1px" style="border-style: outset">
						</A></td>
						<td>
							<% 
										String trangthai_="";
										if(dmhBean.getTrangthai().equals("0")){
											trangthai_="CHƯA CHỐT";
										}else if(dmhBean.getTrangthai().equals("1")){
											trangthai_="ĐÃ CHỐT";
										}else if(dmhBean.getTrangthai().equals("2")){
											trangthai_="ĐÃ XÓA";
										}
										%>
							<div style="font-size: 14px; font-weight: bold; color: red;">
								SỐ CHỨNG TỪ:
								<%=dmhBean.getSochungtu()%>
								. Trạng thái:
								<%=trangthai_ %></div>
						</td>
					</TR>
				</TABLE>


			</div>
			<div align="left" style="width: 100%; float: none; clear: left">
				<fieldset>
					<legend class="legendtitle"> Thông báo</legend>
					<textarea name="dataerror" id="Msg" rows="1" readonly="readonly"
						style="width: 100%"><%=dmhBean.getMsg()%></textarea>
				</fieldset>
			</div>
			<div align="left" style="width: 100%; float: none; clear: left">
				<fieldset>
					<legend class="legendtitle"> Đơn bán hàng</legend>
					<div style="float: none; width: 100%" align="left">
						<TABLE width="100%" cellpadding="4" cellspacing="0">

							<TR>
								<TD class="plainlabel">Khách hàng</TD>
								<TD class="plainlabel"><select <%=disabled%>
									name="khachangid" style="width: 500px">
										<option></option>
										<%if(khrs!=null) {
										while(khrs.next()) {
											if(khrs.getString("pk_seq").equals(dmhBean.getKhId())){
												%>
										<option value="<%=khrs.getString("pk_seq")%>"
											selected="selected"><%=khrs.getString("ten") %>
										</option>
										<%
											}else{
												 %>
										<option value="<%=khrs.getString("pk_seq")%>"><%=khrs.getString("ten") %>
										</option>
										<%
											}
										}
									 	
										 %>
										<% }%>

								</select> <a href="" id="noidungGhiChu" rel="ndGhiChu"> &nbsp; <img
										alt="Thêm khách hàng" src="../images/vitriluu.png">Thêm
										khách hàng
								</a>
									<DIV id="ndGhiChu"
										style="position: absolute; visibility: hidden; border: 9px solid #80CB9B; background-color: white; width: 500px; min-height: 150px; overflow: auto; padding: 4px;">
										<table width="100%" align="center">
											<tr>
												<th colspan="2" width="500px"
													style="font-size: 14px; font-weight: bold;">THÊM KHÁCH
													HÀNG</th>

											</tr>

											<tr>
												<td width="20%">Mã</td>
												<td width="80%"><input style="width: 100%" name="makh"
													type="text" value="<%=dmhBean.getKhMa() %>"></td>
											</tr>

											<tr>
												<td width="300px">Tên</td>
												<td width="300px"><input style="width: 100%"
													name="tenkh" type="text" value="<%=dmhBean.getKhten() %>">
												</td>
											</tr>

											<tr>
												<td width="300px">Địa chỉ</td>
												<td width="300px"><input style="width: 100%"
													type="text" name="diachikh"
													value="<%=dmhBean.getKhDiachi() %>"></td>
											</tr>

											<tr>
												<td width="300px">Điện thoại</td>
												<td width="300px"><input style="width: 100%"
													type="text" name="dienthoaikh"
													value="<%=dmhBean.getKhDienThoai() %>"></td>
											</tr>

											<tr>
												<td></td>
												<td width="300px"><A href="javascript:savekhachhang()">
														<IMG src="../images/1455966997_save.png" title="Save"
														alt="Save" border="1px" style="border-style: outset">
														Lưu khách hàng
												</A> <a href="javascript:dropdowncontent.hidediv('ndGhiChu')">
														<IMG src="../images/exit_1.png" title="Close" alt="Close"
														border="1px" style="border-style: outset"> Đóng tab
												</a></td>
											</tr>

										</table>

									</DIV></TD>

								<TD class="plainlabel" valign="top" width="150px">Ngày
									chứng từ</TD>
								<TD class="plainlabel" valign="top"><input type="text"
									class="days" id="ngaymuahang" name="ngaymuahang"
									value="<%= dmhBean.getNgaymuahang() %>" maxlength="10"
									<%=readonly%> /></TD>

							</TR>







							<TR>
								<TD class="plainlabel">Ghi chú</TD>
								<TD class="plainlabel" colspan="3"><input type="text"
									name="ghichu" style="width: 500px"
									value="<%=dmhBean.getGhiChu()%>" <%=readonly%>></TD>



							</TR>

							<TR>
								<TD class="plainlabel">Thành tiền</TD>
								<TD class="plainlabel"><input type="text"
									name="tongtientruocvat" id="tongtientruocvat"
									value="<%=dmhBean.getTongtienchuaVat()%>"
									style="text-align: right; font-size: 14px; font-weight: bold;"
									readonly="readonly"></TD>

								<TD class="plainlabel">Thanh toán</TD>
								<TD class="plainlabel">
									<% if(dmhBean.getIsThanhToanHet().equals("1") ) { %> <input
									type="text" name="tienthanhtoan" id="tienthanhtoan"
									value="<%= formatter.format(dmhBean.getTienThanhToan())%>"
									style="text-align: right; font-size: 14px; font-weight: bold;"
									readonly="readonly"> <%}else{ %> <input type="text"
									name="tienthanhtoan" id="tienthanhtoan"
									value="<%= formatter.format(dmhBean.getTienThanhToan())%>"
									style="text-align: right; font-size: 14px; font-weight: bold;">
									<%} %> <% if(dmhBean.getIsThanhToanHet().equals("1") ) { %> <input
									type="checkbox" name="isthanhtoanhet" value="1"
									checked="checked"> Thanh toán hết <%}else{ %> <input
									type="checkbox" name="isthanhtoanhet" value="1"> Thanh
									toán hết <%} %>

								</TD>

							</TR>

						</TABLE>
						<hr>
					</div>

					<div align="left" style="width: 100%; float: none; clear: none;">
						<TABLE class="tabledetail" width="100%" border="0" cellpadding="2"
							cellspacing="2" style="font-size: 14px;">
							<TR class="tbheader">
								<TH align="center" width="30px">STT</TH>

								<TH align="center" width="10%">Mã hàng mua</TH>
								<TH align="center" width="15%">Tên hàng mua</TH>

								<TH align="center" width="5%">ĐVT</TH>
								<TH align="center" width="5%">Số lượng</TH>
								<TH align="center" width="10%">Đơn giá</TH>
								<TH align="center" width="10%">Chiết khấu</TH>
								<TH align="center" width="10%">Thành tiền</TH>

							</TR>

							<% int count = 0; 
								if(spList.size() > 0) { 
									for(int i = 0; i < spList.size(); i++) { 
										ISanpham sp = spList.get(i);
										%>

							<tr>
								<TD align="center" width="2%"><input type="text"
									value="<%= i + 1 %>" style="text-align: center;"
									readonly="readonly"> <input type="hidden"
									value="<%= sp.getPK_SEQ() %>" name="idsp" style="width: 100%"
									readonly="readonly"></TD>

								<TD align="center" width="8%"><input type="text"
									name="masp" style="width: 100%"
									value="<%= sp.getMasanpham() %>"
									onkeyup="ajax_showOptions(this,'donmuahang',event)"
									AUTOCOMPLETE="off" <%=readonly%>></TD>
								<TD align="left" width="10%"><input type="text"
									value="<%= sp.getTensanpham() %>" name="tensp"
									style="width: 100%" readonly="readonly"></TD>
								<TD align="center" width="6%"><input type="text"
									value="<%= sp.getDonvitinh() %>" name="donvitinh"
									style="width: 100%"></TD>
								<TD align="center" width="7%"><input type="text"
									value="<%= formatter.format(sp.getSoluong()) %>" name="soluong"
									style="width: 100%; text-align: right;" <%=readonly%>>
								</TD>
								<TD align="center" width="8%"><input type="text"
									value="<%=formatter.format(sp.getDongia()) %>" name="dongia"
									style="width: 100%; text-align: right;" <%=readonly%>>
								</TD>
								<TD align="center" width="8%"><input type="text"
									value="<%= sp.getChietkhau() %>" name="chietkhau"
									style="width: 100%; text-align: right;" <%=readonly%>>
								</TD>

								<TD align="center" width="8%"><input type="text"
									value="<%= formatter.format(sp.getThanhtien()) %>"
									name="thanhtien" style="width: 100%; text-align: right;"
									readonly="readonly"></TD>
							</tr>

							<% count++; }
								} %>

							<% for(int i = count; i < 20; i++) { %>
							<tr>
								<TD align="center" width="2%"><input type="text"
									value="<%= i + 1 %>" style="text-align: center;"
									readonly="readonly"> <input type="hidden" value=""
									name="idsp" style="width: 100%" readonly="readonly"></TD>

								<TD align="center" width="8%"><input type="text"
									name="masp" style="width: 100%" value=""
									onkeyup="ajax_showOptions(this,'donmuahang',event)"
									AUTOCOMPLETE="off" <%=readonly%>></TD>
								<TD align="left" width="10%"><input type="text" value=""
									name="tensp" style="width: 100%" readonly="readonly">

								</TD>
								<TD align="center" width="6%"><input type="text" value=""
									name="donvitinh" style="width: 100%"></TD>
								<TD align="center" width="7%"><input type="text" value=""
									name="soluong" style="width: 100%; text-align: right;"
									onkeyup="FormatNumber(this)" <%=readonly%>></TD>
								<TD align="center" width="8%"><input type="text" value=""
									name="dongia" style="width: 100%; text-align: right;"
									onkeyup="FormatNumber(this)" <%=readonly%>></TD>
								<TD align="center" width="8%"><input type="text" value=""
									name="chietkhau" style="width: 100%; text-align: right;"
									onkeyup="FormatNumber(this)" <%=readonly%>></TD>

								<TD align="center" width="8%"><input type="text" value=""
									name="thanhtien" style="width: 100%; text-align: right;"
									readonly="readonly"></TD>
							</tr>


							<% } %>


							<tr class="tbfooter">
								<td colspan="12">&nbsp;</td>
							</tr>
						</TABLE>
					</div>



				</fieldset>
			</div>
		</div>
		<script type="text/javascript">
	replaces();
	dropdowncontent.init("noidungGhiChu", "right-botom", 500, "click");
 
</script>

		<%
		if(spList!=null){
			spList.clear();
		}
 		 
 		session.setAttribute("dmhBean",null);
		dmhBean.close();
	%>
	</form>
</BODY>
</html>