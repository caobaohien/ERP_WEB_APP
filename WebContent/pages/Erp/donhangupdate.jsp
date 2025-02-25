<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"
		import="java.util.ArrayList"
		import="java.sql.ResultSet"
		import="geso.training.bean.donhang.IDonHang"
		import="geso.training.bean.donhang.imp.DonHang"
		import="geso.training.bean.sanpham.ISanPham"
		import="geso.training.bean.sanpham.imp.SanPham" %>
<%@page import="java.util.Iterator" %>
<%
	//IDonHang obj =(IDonHang)session.getAttribute("donhangupdate");
	ISanPham sp =(ISanPham)session.getAttribute("donhangupdate");
    String userId=(String)session.getAttribute("userId");
	String userTen=(String)session.getAttribute("userTen");
	//String userId=obj.getUserId();
	//String action=(String)session.getAttribute("action");
	List<ISanPham> listSp =sp.getListSp();
	//List<IDonHang> ds_sanpham = obj.getDs_sanpham();
	//ResultSet khRs=obj.getKhRs();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Best-Project</title>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">

<LINK rel="stylesheet"type="text/css" media="print" href="../css/impression.css">
<LINK rel="stylesheet"type="text/css" href="../css/main.css">
<link rel="stylesheet"type="text/css" href="../css/mybutton.css">
<link rel="stylesheet"type="text/css" href="../css/style.css"/>
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
	position: absolute; /* never change this one */
	width: auto; /* Width of box */
	height: 200px; /* Height of box */
	overflow: auto; /*Scrolling features */
	border: 1px solid #317082; /* Dark green border */
	background-color: #C5E8CD; /* White background color */
	color: black;
	text-align: left;
	font-size: 1.0em;
	z-index: 100;
}

#ajax_listOfOptions div {
	/* General rule for both .optionDiv and .optionDivSelected */
	margin: 1px;
	padding: 1px;
	cursor: pointer;
	font-size: 1.0em;
}

#ajax_listOfOptions .optionDiv { /* Div for each item in list */
}

#ajax_listOfOptions .optionDivSelected { 
	/* Selected item in the list */
	background-color: #317082; /*color as move */
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
	/*Remove below line to remove shadow. Below line should always appear last
within this css */
	filter: progrid:DXImageTransform.Microsoft.Shadow(color=gray, direction=135);
}

#dhtmlpointer {
	position: absolute;
	left: -300px;
	z-index: 101;
	visibility: hidden;
}
</style>
<link rel="stylesheet" type="text/css" href="../css/speechbubbles.css" />

<script type="text/javascript" src="../scripts/jquery.min.js"></script>
<script type="text/javascript" src="../scripts/speechbubbles.js"></script>
<script type="text/javascript">
	jQuery(function($){
		$(".addspeech").speechbubble();
	});
</script>

<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
<link rel="stylesheet" type="text/css" href="../css/jquery-ui.css" />
<script type="text/javascript" src="../scripts/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="../scripts/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="../scripts/ui/jquery.ui.datepicker.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
		$(".days").datepicker({
			changeMonth:true,
			changeYear:true
		});
	});
</script>

<script type="text/javascript" src="../scripts/ajax.js"></script>
<script type="text/javascript" src="../scripts/ajax-dynamic-list.js"></script>
<script type="text/javascript" src="../scripts/dropdowncontent.js"></script>
<script type="text/javascript" src="../scripts/cool_DHTML_tootip.js"></script>
<script type="text/javascript" src="../scripts/jquery.autocomplete.js"></script>
<!--<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
     $(document).ready(function() { $("select").select2();
     });
     
</script>-->
<script language="javascript" type="text/javascript">
function TinhTien(){
	var thanhtien=document.getElementsByName("thanhtien1");
	var tongtien = 0;
	for(var i=0;i<thanhtien.length;i++){
		var thanh_tien=thanhtien.item(i).value;
		while(thanh_tien.match(","))
		{
			thanh_tien=thanh_tien.replace(",","");
		}
		if(thanh_tien != "")
		{
			tongtien=parseFloat(tongtien) + parseFloat(thanh_tien);
		}
	}
	var tienchuaVAT = tongtien;
	document.getElementById("sotienchuaVAT").value = DinhDangTien(tienchuaVAT);
	
	var vat=document.getElementById("VAT").value;
	if(vat==""){
		vat= "0";
	}
	var tongtiencoVAT = tienchuaVAT+(parseFloat(vat))/100*tienchuaVAT;
	document.getElementById("sotiencoVAT").value=DinhDangTien(Math.round(tongtiencoVAT));
}
function roundNumber(num,dec){
	var result=Math.round(num*Math.pow(10, dec))/Math.pow(10,dec);
	return result;
}
function DinhDangTien(num){
	num=num.toString().replace(/\$|\,/g,'');
	if(isNaN(num)){
		num= "0";
		sign=(num==(num=Math.abs(num)));
		num=Math.floor(num*100+0.50000000001);
		num=Math.floor(num/100).toString();
	}
	for(var i=0;i<Math.floor((num.length-(1+i))/3);i++){
		num=num.substring(0, num.length-(4*i+3))+","+
		num.substring(num.length-(4*i+3));
	}
	return (((sign)?'':'-')+num);
}
function replaces(){
	var khTen=document.getElementsByName("khTen");
	var smartId=document.getElementsByName("smartId");
	for(var i=0;i<smartId.length;i++){
		var tem=smartId.item(0).value;
		if(tem==""){
			khTen.item(0).value = "";
			document.getElementById("khId").value = "";
			break;
		}
		if(parseInt(tem.indexOf("-->"))>0){
			var tmp=tem.substring(0,parseInt(tem.indexOf("-->[")));
			document.getElementById("khId").value=tmp.substring(0,parseInt(tem.indexOf("-")));
			smartId.item(0).value=tmp.substring(parseInt(tem.indexOf("-"))+1,tmp.length);
			khTen.item(0).value=tem.substring(parseInt(tem.indexOf("-->["))+4, parseInt(tem.indexOf("]")));
			
			break;
		}
	}
	var idsp=document.getElementsByName("spId");
	var masp=document.getElementsByName("masp");
	var tensp=document.getElementsByName("tensp1");
	var donvitinh=document.getElementsByName("donvitinh1");
	var dongia=document.getElementsByName("dongia1");
	var soluong=document.getElementsByName("soluong");
	var chietkhau=document.getElementsByName("chietkhau1");
	var thanhtien=document.getElementsByName("thanhtien1");
	/*
	for(var i=0;i<masp.length;i++){
		var tem=masp.item(i).value;
		if(tem==""){
			tensp.item(i).value="";
			dongia.item(i).value = "";
			document.getElementById("spId").value = "";
			break;
		}
		if(parseInt(tem.indexOf("-->"))>0){
			var tmp=tem.substring(0,parseInt(tem.indexOf("-->")));
			document.getElementById("spId").value=tmp.substring(parseInt(tmp.indexOf("-"))+1,tmp.length);
			masp.item(i).value=tmp.substring(0,parseInt(tem.indexOf("-")));
			//idsp.item(i).value=tmp.substring(parseInt(tem.indexOf("-"))+1, tmp.length);
			var tkp=tem.substring(parseInt(tem.indexOf("-->"))+3, tem.length);
			tensp.item(i).value=tkp.substring(0,parseInt(tkp.indexOf("-")));
			dongia.item(i).value=tkp.substring(parseInt(tkp.indexOf("-"))+1, tkp.length);
			
			break;
		}
	}*/
	for(var i=0;i<masp.length;i++){
		if(masp.item(i).value!= ""){
			var sp=masp.item(i).value;
			if((parseInt(sp.indexOf("-->")))>0){
				var tmp=sp.substring(0,parseInt(sp.indexOf("-->")));
				document.getElementById("spId").value=tmp.substring(parseInt(tmp.indexOf("-"))+1,tmp.length);
				masp.item(i).value=tmp.substring(0,parseInt(sp.indexOf("-")));
				idsp.item(i).value=tmp.substring(parseInt(tem.indexOf("-"))+1, tmp.length);
				var tkp=sp.substring(parseInt(sp.indexOf("-->"))+3, sp.length);
				tensp.item(i).value=tkp.substring(0,parseInt(tkp.indexOf("-")));
				dongia.item(i).value=tkp.substring(parseInt(tkp.indexOf("-"))+1, tkp.length);
				
				//break;
			}
			/*
			var pos=parseInt(sp.indexOf(" - "));
			if(pos>0){
				masp.item(i).value=sp.substring(0, pos);
				sp=sp.substr(parseInt(sp.indexOf(" - "))+3);
				tensp.item(i).value=sp.substring(0,parseInt(sp.indexOf(" [")));
				sp=sp.substr(parseInt(sp.indexOf(" ["))+2);
				donvitinh.item(i).value=sp.substring(0,parseInt(sp.indexOf("] [")));
				sp=sp.substr(parseInt(sp.indexOf("] ["))+3);
				dongia.item(i).value=sp.substring(0,parseInt(sp.indexOf("] [")));
				sp=sp.substr(parseInt(sp.indexOf("] ["))+3);
				chietkhau.item(i).value=sp.substring(0,parseInt(sp.indexOf("] [")));
				sp=sp.substr(parseInt(sp.indexOf("] ["))+3);
				idsp.item(i).value=sp.substring(0, parseInt(sp.indexOf("]")));
				soluong.item(i).focus();
			}*/
			var so_luong=soluong.item(i).value;
			while(so_luong.match(",")){
				so_luong=so_luong.replace(",","");
			}
			if(parseInt(so_luong)>0){
				var don_gia=dongia.item(i).value;
				if(don_gia >0){
					while(don_gia.match(",")){
						don_gia=don_gia.replace(",","");
					}
					var chiet_khau=chietkhau.item(i).value;
					while(chiet_khau.match(",")){
						chiet_khau=chiet_chietkhau.replace(",","");
					}
					var tt=parseInt(so_luong)*(parseFloat(don_gia)-parseFloat(don_gia)*parseFloat(chiet_khau) / 100);
					thanhtien.item(i).value=DinhDangTien(roundNumber(tt, 2));
					TinhTien();
				}
			}else{
				thanhtien.item(i).value = "";
			}
		}else{
			idsp.item(i).value="";
			tensp.item(i).value="";
			donvitinh.item(i).value="";
			dongia.item(i).value="";
			soluong.item(i).value="";
			chietkhau.item(i).value="";
			thanhtien.item(i).value="";
		}
	}
	setTimeout(replaces, 100);
}
function updateform(){
	document.forms["donhanglist"].action.value="hien";
	document.forms["donhanglist"].submit();
}
function saveform(){
	document.forms["donhanglist"].action.value="luusua";
	document.forms["donhanglist"].submit();
}
function goBack()
{
 	window.history.back();
}
function checkSanpham(){
	var masp = document.getElementsByName("masp");
	for(var hh=0;hh<masp.length;hh++){
		if(masp.item(hh).value !=""){
			return true;
		}
	}
	return false;
}
</script>
</head>
<body leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="donhanglist" method="post" action="../../DonhangUpdatesvl">
<input type="hidden" name="action" value="1">
<input type="hidden" id="khId" name="khId" value="">
<input type="hidden" id="spId" name="spId" value="">
<input type="hidden" name="userId" value="<%=userId%>">
<input type="hidden"name="madonhang" value="<%=sp.getMadh() %>">
	<table width="100%" height="100%" cellspacing="0" cellpadding="0" border="0">
		<tr>
			<td colspan="4" align="left" valign="top" bgcolor="#FFFFFF">
				<table width="100%" cellspacing="0" cellpadding="2" border="0">
					<tbody>
						<tr height="22">
							<td align="left">
								<table width="100%"cellspacing="0"cellpadding="0">
									<tr>
										<td align="left">
											<table width="100%"cellpadding="0"cellspacing="0"border="0">
											<tr height="22">
											<td class="tbnavigation"align="left">&nbsp;Xử lý đơn hàng > Đơn hàng bán > Tạo mới</td>
											<td class="tbnavigation"align="right">Chào mừng nhà phân phối</td>
											</tr>
										    </table>
										</td>
									</tr>
								</table>
								<table width="100%"cellspacing="0"cellpadding="1"border="0">
									<tr>
									<td>
									<table width="100%" cellspacing="0"cellpadding="0"border="0">
									<tr class="tbdarkrow">
									<td align="left">
									<a href="../../Donhanglistsvl?userId=<%=userId%>"><img src="../images/Back30.png" alt="Quay về" title="Quay về"
									longdesc="Quay về"border="1"style="border-style: outset"></a>
									
									<a href="javascript:saveform()"><img src="../images/Save30.png" alt="Save"
									title="Save"longdesc="Save"border="1"style="border-style: outset"></a>
									
									<a href="javascript:updateform()"><img src="../images/Edit30.png" alt="Update"
									title="Update"border="0"></a>
									</td>
									</tr>
									</table>
									</td>
									</tr>
								</table>
								<table width="100%"cellspacing="0"cellpadding="0"border="0">
									<tr>
									<td class="legendtitle" colspan="4"align="left">
									<fieldset>
									<LEGEND class="legendtitle">Báo lỗi nhập liệu</LEGEND>
									<textarea name="dataerror"id="Msg"
									style="width:99.5%; color:#F00; font-weight:bold"
									readonly="readonly" rows="2"><%=sp.getMsg() %></textarea>
									</fieldset>
									</td>
									</tr>
									</table>
								<table width="100%"cellspacing="0"cellpadding="0"border="0">
									<tr>
									<td>
									<fieldset>
									<LEGEND class="legendtitle">Đơn hàng bán</LEGEND>
									<table width="100%"cellspacing="0"cellpadding="3"border="0">
										<tr>
										<td width="170px"class="plainlabel">Ngày giao dịch</td>
										<td class="plainlabel">
										<input type="text"class="days"size="11"name="ngaydonhang"id="ngaydonhang"value="<%=sp.getNgaydh()%>"maxlength="10"readonly /></td>
										<td class="plainlabel"><a href="" id="noidungGhiChu" rel="ndGhiChu"><img
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
													type="text" value="<%=sp.getMakh() %>"></td>
											</tr>

											<tr>
												<td width="300px">Tên</td>
												<td width="300px"><input style="width: 100%"
													name="tenkh" type="text" value="<%=sp.getTenkh() %>">
												</td>
											</tr>

											<tr>
												<td width="300px">Địa chỉ</td>
												<td width="300px"><input style="width: 100%"
													type="text" name="diachikh"
													value="<%=sp.getKhDiachi() %>"></td>
											</tr>

											<tr>
												<td width="300px">Điện thoại</td>
												<td width="300px"><input style="width: 100%"
													type="text" name="dienthoaikh"
													value="<%=sp.getKhDienthoai() %>"></td>
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

									</DIV></td>
										<%--chú thích: xuất lên cmp khách hàng  --%>
										<td class="plainlabel">Trạng thái</td>
										<td class="plainlabel"><select name="trangthai"
											onchange="submitform()" style="width: 200px">
												<option value="<%=sp.getTrangThai()%>">Toàn bộ</option>
												<%
							                    	String mangMaTrangThai[] = {"0", "1", "2"};
						                    		String mangTenTrangThai[] = {"Chưa chốt", "Đã chốt", "Đã hủy"};
						                    		for(int i = 0; i < mangMaTrangThai.length; i++){
													 if(sp.getTrangThai().equals(mangMaTrangThai[i])){
													 %>
												<option selected="selected" value="<%=mangMaTrangThai[i]%>">
													<%= mangTenTrangThai[i] %>
												</option>
													<%
													 }else{
													 %>
												<option value="<%= mangMaTrangThai[i] %>">
													<%= mangTenTrangThai[i] %>
												</option>
												<% 
													 }
												 }
						                    	%>
										</select></td>
										<td colspan="3" class="plainlabel"></td>
										</tr>
										<tr>
										<td class="plainlabel">Mã khách hàng</td>
										<td class="plainlabel" width="250px">
										<input type="text"name="smartId"id="smartId"style="width:200px"value="<%=sp.getMakh()%>"/>
										</td>
										<td class="plainlabel"width="190px">Tên khách hàng - Địa chỉ</td>
										<td class="plainlabel"colspan="5">
										<input type="text"name="khTen"id="khTen"style="width:100%"value="<%=sp.getTenkh()%>"readonly /></td>
										</tr>
										<tr class="tblightrow">
										<td class="plainlabel">VAT(%)</td>
										<td class="plainlabel">
										<input type="text"name="VAT"id="VAT"value="<%=sp.getVat()%>"onkeypress="return keypress(event);">%</td>
										<td class="plainlabel">Tổng số tiền</td>
										<td class="plainlabel"colspan="5">
										<input type="text"name="sotienchuaVAT"id="sotienchuaVAT"value="<%=sp.getTongtientruocVat()%>"readonly>VND</td>
										</tr>
										<tr class="tblightrow">
										<td class="plainlabel">Tổng số tiền(sau VAT)</td>
										<td class="plainlabel">
										<input type="text"name="sotiencoVAT"id="sotiencoVAT"value="<%=sp.getTongtiensauVat()%>"readonly>VND</td>
										<td class="plainlabel">Mã đơn hàng</TD>
								   		<td class="plainlabel"colspan="5"><input type="text"
										name="madonhang"value="<%=sp.getMadh()%>" placeholder="Ex: 100000">
										</td>
										</tr>
									</table>
									<hr>
									<table width="100%"cellpadding="5"border="0"style="font-size: 15px">
										<tbody id="san_pham">
											<tr class="tbheader">
											<TH align="left" width="2%">STT</TH>
											<th align="center"width="8%"height="20">Mã sản phẩm</th>
											<th align="center"width="15%">Tên sản phẩm</th>
											<th align="center"width="6%">Số lượng</th>
											<th align="center"width="6%">DVT</th>
											<th align="center"width="8%">Đơn giá</th>
											<th align="center"width="6%">Chiết khấu</th>
											<th align="center"width="10%">Thành tiền</th>
											</tr>
													<%
													int j=0;
													if(listSp.size()>0){
														Iterator<ISanPham> iteratorSanPham = listSp.iterator();
														while(iteratorSanPham.hasNext()){
															ISanPham elementSanPham = iteratorSanPham.next();
															j++;
														%>
														<tr>
														<td align="center" width="2%"><input type="text"
                                                        value="<%=j%>" style="width:100%;text-align: center;"readonly="readonly">
														<input type="hidden"value="<%=elementSanPham.getPK_SEQ() %>"name="spId"id="spId"
														style="width:100%"readonly="readonly"></td>
														
														<td align="left" width="8%">				
														<input type="text"name="masp"id="masp"value="<%=elementSanPham.getMaSP() %>"onkeyup="ajax_showOptions(this,'donhangsua',event)"
														autocomplete="on"style="width:100%"></td>
														
														<td align="left"width="15%">
														<input type="text"name="tensp1"id="tensp1"value="<%=elementSanPham.getTenSP() %>"
														readonly="readonly"style="width:100%"></td>
														
														<td align="center"width="6%">
														<input type="number"name="soluong"value="<%=elementSanPham.getSoLuong() %>"
														style="width:100%;text-align:center;"></td>
														
														<td align="right"width="6%">
														<input type="text"name="donvitinh1"value="<%=elementSanPham.getDVT() %>"
														style="width:100%;text-align:center;color:black;"></td>
														
														<td align="right"width="8%">
														<input type="text"name="dongia1"id="dongia1"value="<%=elementSanPham.getDonGia() %>"
														style="width:100%;text-align:center;color:black;"readonly="readonly"></td>
														
														<td align="right" width="6%">
														<input type="text"name="chietkhau1"value="<%=elementSanPham.getChietkhau() %>"
														style="width:100%;text-align:center;color:black;"></td>
										                
														<td align="right"width="10%">
														<input type="text"name="thanhtien1"value="<%=elementSanPham.getThanhTien() %>"
														style="width:100%;text-align:center;color:black;"readonly="readonly"></td>
														</tr>
														
													<%}}
													%>
											<%
													for(int i=j;i<listSp.size()+20;i++){
											%>
													<tr>
														<td align="center" width="2%"><input type="text"
                                                        value="<%=i+1%>" style="width:100%;text-align: center;"
														readonly="readonly"><input type="hidden" value=""
														name="spId"id="spId" style="width: 100%" readonly="readonly"></td>
														
														<td align="left"width="8%">
														<input type="text"name="masp"id="masp"value=""onkeyup="ajax_showOptions(this,'donhangsua',event)"
														autocomplete="on"style="width:100%"></td>
														
														<td align="left"width="15%">
														<input type="text"name="tensp1"id="tensp1"value=""style="width:100%"></td>
														
														<td align="center"width="6%">
														<input type="number"name="soluong"value=""style="width:100%;text-align:center;color:black;"></td>
														
														<td align="right"width="6%">
														<input type="text"name="donvitinh1"value=""style="width:100%;text-align:center;color:black;"></td>
														
														<td align="right"width="8%">
														<input type="text"name="dongia1"id="dongia1"value=""style="width:100%;text-align:center;color:black;"></td>
														
														<td align="center" width="6%">
														<input type="text"name="chietkhau1" value=""style="width:100%;text-align:center;color:black;"></td>
														
														<td align="right"width="10%">
														<input type="text"name="thanhtien1"value=""style="width:100%;text-align:center;color:black;"
														readonly="readonly"></td>
													</tr>
													<%
														}
													%>
												<tr class="tbfooter">
														<td colspan="12">&nbsp;</td>
												</tr>
										</tbody>									
									</table>
									</fieldset>
									</td>
									</tr>
								</table>
							</td>
						</tr>
					</tbody>
			</table>
		</td>
	</tr>
</table>
	<% sp.DBClose(); %>
<script type="text/javascript">
	replaces();
	dropdowncontent.init("noidungGhiChu", "right-botom", 500, "click");
	jQuery(function(){
		$("#smartId").autocomplete("khachhanglistAjax.jsp");
	});
</script>
</form>
</body>
</html>