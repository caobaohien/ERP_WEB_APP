<%@page import="geso.erp.beans.donmuahang_tan.*"%>
<%@page import="geso.erp.beans.sanpham_tan.IThongtinsanpham"%>
<%@page import="geso.erp.beans.nhacungcap.INhacungcap"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="geso.dms.center.util.Utility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.Hashtable"%>
<%@ page import="java.util.List"%>
<%
	IDonmuahangNew_tan obj = (IDonmuahangNew_tan) session.getAttribute("obj");
	String userId = (String) session.getAttribute("userId");
	String userTen = (String) session.getAttribute("userTen");
	Utility util = (Utility) session.getAttribute("util");
	List<ISanpham_tan> listsp =obj.getListSp();
	ResultSet dataListNCC=obj.getRsncc();

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
	filter: progid:       DXImageTransform.Microsoft.Shadow(       color=
		    
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

	 function print_pdf(){
		 document.forms["erpDmhForm"].action.value = "print";
		 document.forms["erpDmhForm"].submit();
	 }
	 function checkSanPham()
	 {
		 var masp = document.getElementsByName("txtMasp");
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
	 
	 
	 
	 
	 function savenhacungcap()
	 { 		
		 document.forms['dmhForm'].action.value='savenhacungcap';
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
	 
 
	 
	 
 
	 function goBack()
	 {
	  	window.history.back();
	 }
	 function replaces()
		{
			 
			// lay mot mang
			var idsp = document.getElementsByName("idsp");
			var masp = document.getElementsByName("txtMasp");
			var tensp = document.getElementsByName("txtTensp");
			var donvitinh = document.getElementsByName("txtDvt");
			var soluong = document.getElementsByName("txtSoluong");
			var dongia = document.getElementsByName("txtDongia");
			 
			var thanhtien = document.getElementsByName("txtThanhtien");
			 
			var sodong =  masp.length;
			console.log(sodong);
			
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
						console.log(pos);
						
						masp.item(i).value = sp.substring(0, pos);
						
						// substr ham nay truyen vao vi tri bat dau lay cua chuoi toi het chuoi
						//vd  ( VT10 -- VIETTEL10 [] [20,000] [0] [100010] )   lay tu vi tri 7: VIETTEL10 [] [20,000] [0] [100010]
						sp = sp.substr(parseInt(sp.indexOf(" -- ")) + 3);
						tensp.item(i).value = sp.substring(0, parseInt(sp.indexOf(" [")));
						
						sp = sp.substr(parseInt(sp.indexOf(" [")) + 2);
						donvitinh.item(i).value = sp.substring(0, parseInt(sp.indexOf("] [")));
						
						sp = sp.substr(parseInt(sp.indexOf("] [")) + 3);
						
						dongia.item(i).value = sp.substring(0, parseInt(sp.indexOf("] [")));

						sp = sp.substr(parseInt(sp.indexOf("] [")) + 3);
						 
						sp = sp.substr(parseInt(sp.indexOf("] [")) + 3);
						
						idsp.item(i).value = sp.substring(0, parseInt(sp.indexOf("]")));
						
						soluong.item(i).focus();
						
					}
					
					if(soluong.item(i).value != ""){
						if(dongia.item(i).value != ""){
							var dgia = dongia.item(i).value.replace(/,/g,""); 
							var tt = parseFloat(slg) *  parseFloat(dgia)  ;
							thanhtien.item(i).value =  tt ;
							
							 
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
			var soluong = document.getElementsByName("txtSoluong");
			var dongia = document.getElementsByName("txtDongia");
			var thanhtien = document.getElementsByName("txtThanhtien");	
			
			var tongtien = 0;
			
			for(var i = 0; i < thanhtien.length; i++){
				//alert('TT: ' + thanhtien.item(i).value);
				if( thanhtien.item(i).value != "" ){
					var thanh_tien = thanhtien.item(i).value.replace(/,/g,"");
					tongtien = parseFloat(tongtien) +  parseFloat(thanh_tien);
					
				}
				
			}
			document.getElementById("txtTongtien").value = DinhDangTien(Math.round(tongtien)); 
			
		}
		
		
		//------------------------
		
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
	<form name="dmhForm" method="post" action="../../DonmuahangNewSvl_tan">
		<input type="hidden" name="userId" value='<%=userId%>'> <input
			type="hidden" name="action" value="1" /> <input type="hidden"
			name="id_dh" value='<%=obj.getId()%>'> <input type="hidden"
			id="IsCapNhat" name="IsCapNhat" value='<%=obj.getIsCapNhat()%>'>

		<div id="main" style="width: 100%; padding-left: 2px">
			<div align="left" id="header" style="width: 100%; float: none">
				<div style="width: 60%; padding: 5px; float: left"
					class="tbnavigation">THÔNG TIN ĐƠN MUA HÀNG</div>
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
						<%if(obj.getId().length()>0){ %>
						<TD width="50px" align="left"><A
							href="../../DonmuahangInExcelSvl_tan?userId=<%=userId %>&excel=<%= obj.getId()%>">
								<IMG src="../images/Excel2013.png" title="Xuất file Excel"
								alt="Xuất file Excel" border="1px" style="border-style: outset">
						</A></TD>
						<%}%>
						<td width="30px" align="left"><A
							href="javascript:print_pdf()"> <IMG
								src="../images/pdf2013.png" title="In báo cáo PDF"
								alt="In báo cáo PDF" border="1px"
								style="border-style: outset; width: 30px; height: 30px">
						</A></td>
						<TD width="30px" align="left"><span id="btnUndo"> <A
								href="../../DonmuahangListSvl_tan?userId=<%=userId%>"> <IMG
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
					<legend class="legendtitle"> THÊM HÓA ĐƠN MUA HÀNG </legend>
					<div style="float: none; width: 100%" align="left">
						<TABLE width="100%" cellpadding="4" cellspacing="0">

							<TR>
								<TD class="plainlabel">Tổng tiền</TD>
								<TD class="plainlabel"><input type="text"
									name="txtTongtien" id="txtTongtien"
									value="<%=formatter.format(obj.getTongtien())%>"
									style="text-align: right; font-size: 14px; font-weight: bold;">
								</TD>

							</TR>
							<TR>
								<TD class="plainlabel">Ngày chứng từ</TD>
								<TD class="plainlabel"><input type="text" class="days"
									name="txtNgaychungtu" value="<%=obj.getNgaychungtu()%>">
								</TD>

							</TR>

							<tr>
								<TD class="plainlabel">Tên nhà cung cấp</TD>
								<TD class="plainlabel"><select name="cbNhacungcap"
									style="width: 200px">
										<option></option>
										<%
											if (dataListNCC != null) {
												while (dataListNCC.next()) {
													if (dataListNCC.getString("pk_seq").equals(obj.getNhacungcap())) {
										%>
										<option value="<%=dataListNCC.getString("pk_seq")%>"
											selected="selected"><%=dataListNCC.getString("TEN")%>
										</option>
										<%
											} else {
										%>
										<option value="<%=dataListNCC.getString("pk_seq")%>"><%=dataListNCC.getString("TEN")%>
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
									<TH align="center" width="2%">STT</TH>

									<TH align="center" width="10%">Mã sản phẩm</TH>
									<TH align="center" width="15%">Tên sản phẩm</TH>

									<TH align="center" width="5%">ĐVT</TH>
									<TH align="center" width="5%">Số lượng</TH>
									<TH align="center" width="10%">Đơn giá</TH>

									<TH align="center" width="10%">Thành tiền</TH>

								</TR>

								<% 
							
							int i=0 ;
							for(  i=0;i<listsp.size();i++) {
								ISanpham_tan sp=listsp.get(i);
								
								%>
								<TR>
									<Td align="center"><%=i%></Td>

									<Td align="center" width="10%"><input type="hidden"
										name="idsp" value="<%=sp.getId()%>"> <input
										type="text" name="txtMasp" value="<%=sp.getMasanpham()%>"
										onkeyup="ajax_showOptions(this,'donmuahang',event)"
										AUTOCOMPLETE="off" <%=readonly%>></Td>
									<Td align="center" width="15%"><input type="text"
										name="txtTensp" value="<%=sp.getTensanpham()%>"></Td>

									<Td align="center" width="5%"><input type="text"
										name="txtDvt" value="<%=sp.getDonvitinh()%>"></Td>
									<Td align="center" width="5%"><input type="text"
										name="txtSoluong"
										value="<%=formatter.format(sp.getSoluong())%>"></Td>
									<Td align="center" width="10%"><input type="text"
										name="txtDongia" value="<%=formatter.format(sp.getDongia())%>"></Td>

									<Td align="center" width="10%"><input type="text"
										name="txtThanhtien"
										value="<%=formatter.format(sp.getThanhtien())%>"></Td>

								</TR>

								<%
								}
								%>


								<!-- trường hợp list không có danh sách thì khởi tạo 1 list trống -->
								<%
							 
							 for(i=listsp.size();i<listsp.size()+20 ;i++) {
								 
								
								%>
								<TR>
									<Td align="center" width="30px"><%=i %></Td>

									<Td align="center" width="10%"><input type="hidden"
										name="idsp" value=""> <input type="text"
										name="txtMasp" value=""
										onkeyup="ajax_showOptions(this,'donmuahang',event)"
										AUTOCOMPLETE="off"></Td>
									<Td align="center" width="15%"><input type="text"
										name="txtTensp" value=""></Td>

									<Td align="center" width="5%"><input type="text"
										name="txtDvt" value=""></Td>
									<Td align="center" width="5%"><input type="text"
										name="txtSoluong" value=""></Td>
									<Td align="center" width="10%"><input type="text"
										name="txtDongia" value=""></Td>

									<Td align="center" width="10%"><input type="text"
										name="txtThanhtien" value=""></Td>

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
		</script>
	</form>
</BODY>
</html>