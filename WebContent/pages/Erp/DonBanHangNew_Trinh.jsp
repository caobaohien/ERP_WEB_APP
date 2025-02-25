<%@page import="geso.erp.beans.donbanhang_trinh.IChiTietDonHang_Trinh"%>
<%@page import="geso.erp.beans.donbanhang_trinh.imp.DonBanHang_Trinh"%>
<%@page import="geso.erp.beans.donbanhang_trinh.IDonBanHang_Trinh"%>
<%@page import="geso.dms.center.util.Utility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.Hashtable"%>
<%@ page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<% 
	IDonBanHang_Trinh obj = (IDonBanHang_Trinh) session.getAttribute("objnew"); //bien session truyen giua servelet va jsp
	String userId = (String) session.getAttribute("userId");
	String userTen = (String) session.getAttribute("userTen");
  	ResultSet rsKhachHang = obj.getRsKhachHang();
  	
  	String action = (String) session.getAttribute("action");
  	System.out.println(action);
  	List<IChiTietDonHang_Trinh> danhSachSanPham = obj.getDanhsachSanPham();
  	Utility utility=  new Utility();
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
<script type="text/javascript" src="../scripts/erp-SanPhamList_Trinh.js"></script>


<script type="text/javascript" src="../scripts/dropdowncontent.js"></script>
<script type="text/javascript" src="../scripts/cool_DHTML_tootip.js"></script>
<script type="text/javascript" src="../scripts/jquery.autocomplete.js"></script>
<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
     $(document).ready(function() { $("select").select2();  });
     
</script>

<script language="javascript" type="text/javascript">

	function replaces(){
		var idsp = document.getElementsByName("PK_SEQ");
		var masp = document.getElementsByName("maSanPham");
		var tensp = document.getElementsByName("tenSanPham");
		var donvitinh = document.getElementsByName("donViTinh");
		var soluong = document.getElementsByName("soLuong");
		var dongia = document.getElementsByName("giaBan");
		var thanhtien = document.getElementsByName("thanhTien");
		 
		var sodong =  masp.length;
		console.log(sodong);
		console.log(donvitinh);
		var i;
		for(i = 0; i < sodong; i++)
		{
			if(  masp.item(i).value != ""    )
			{ 
				console.log(masp);
				var sp = masp.item(i).value;
				var pos = parseInt(sp.indexOf(" -- "));
				var slg = soluong.item(i).value.replace(/,/g,""); 
				
				if(pos > 0)
				{
					masp.item(i).value = sp.substring(0, pos);
					sp = sp.substr(parseInt(sp.indexOf(" -- ")) + 3);
					tensp.item(i).value = sp.substring(0, parseInt(sp.indexOf(" ["))); //lấy giá trị ở chuỗi và gán vào
					
					sp = sp.substr(parseInt(sp.indexOf(" [")) + 2);
					donvitinh.item(i).value = sp.substring(0, parseInt(sp.indexOf("] [")));
					
					sp = sp.substr(parseInt(sp.indexOf("] [")) + 3);
					
					dongia.item(i).value = sp.substring(0, parseInt(sp.indexOf("] [")));
	
					sp = sp.substr(parseInt(sp.indexOf("] [")) + 3);
					//vì bỏ qua chiếc khấu nên cắt 2 lần
					sp = sp.substr(parseInt(sp.indexOf("] [")) + 3);
					
					idsp.item(i).value = sp.substring(0, parseInt(sp.indexOf("]")));
					
					
					
					soluong.item(i).focus();
				}
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
				 
						soluong.item(i).value = "";
						dongia.item(i).value = "";
						thanhtien.item(i).value = "";

			}
		}
	  
		TinhTongTien();
		
		setTimeout(replaces, 500); //khoảng nữa giây hàm replace được gọi lại một lần
		}
	
	function TinhTongTien(){
		var soluong = document.getElementsByName("soLuong");
		var dongia = document.getElementsByName("giaBan");
		/* var chietkhau = document.getElementsByName("chietkhau"); */
		var thanhtien = document.getElementsByName("thanhTien");	
		
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
		  
			
			 document.getElementById("tongTien").value = DinhDangTien(Math.round(tongtien));  
		
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
</script>
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	<form name="dmhForm" method="post"
		action="../../DonBanHangNew_TrinhSvl">
		<input type="hidden" name="userId" value='<%= userId %>'> <input
			type="hidden" name="action" value="<%=action%>" /> <input
			type="hidden" name="maDonHang" value='<%= obj.getMaDonHang() %>'>
		<div id="main" style="width: 100%; padding-left: 2px">
			<div align="left" id="header" style="width: 100%; float: none">
				<div style="width: 60%; padding: 5px; float: left"
					class="tbnavigation">Thong tin san pham</div>
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
						<TD width="30px" align="left"><A href="javascript:saveform()">
								<IMG src="../images/1455966997_save.png" title="Save" alt="Save"
								border="1px" style="border-style: outset">
						</A></TD>
						<TD width="30px" align="left"><span id="btnUndo"> <A
								href="../../DonBanHang_TrinhSvl?userId=<%=userId%>"> <IMG
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
						style="height: 50px; width: 100%"><%= obj.getMessage() %></textarea>
				</fieldset>
			</div>
			<div align="left" style="width: 100%; float: none; clear: left">
				<fieldset>
					<legend class="legendtitle"> Thêm Đơn Hàng </legend>
					<div style="float: none; width: 100%" align="left">
						<TABLE width="100%" cellpadding="4" cellspacing="0">
							<TR>
								<TD class="plainlabel">Khách hàng</TD>
								<TD class="plainlabel"><select name="maKhachHang"
									style="width: 200px">
										<option value=""></option>
										<% 
										if (rsKhachHang != null) {
											while (rsKhachHang.next()) {
												if ( rsKhachHang.getString("MAKHACHHANG").equals(obj.getMaKhachHang()) ) {
									%>
										<option value="<%= rsKhachHang.getString("MAKHACHHANG") %>"
											selected="selected">
											<%=rsKhachHang.getString("TENKHACHHANG") %>
										</option>
										<%
												} else {
											%>
										<option value="<%= rsKhachHang.getString("MAKHACHHANG") %>">
											<%=rsKhachHang.getString("TENKHACHHANG") %>
										</option>
										<% 
												}
											}
										}
									%>
								</TD>

							</TR>

							<TR>
								<TD class="plainlabel">Ngày chứng từ</TD>
								<TD class="plainlabel"><input type="text"
									name="ngayChungTu" class="days"
									value="<%= obj.getNgayChungTu() %>"></TD>
							</TR>

							<TR>
								<TD class="plainlabel">Tổng tiền</TD>
								<TD class="plainlabel"><input type="text" name="tongTien"
									id="tongTien" readonly="readonly"
									value="<%= obj.getSoTien() %>"></TD>
								<% System.out.println(obj.getSoTien()); %>
							</TR>
						</TABLE>
					</div>
					<hr>
					<!-- Chi tiết đơn hàng  -->


					<div align="left" style="width: 100%; float: none; clear: none;">
						<table class="tabledetail" width="100%" border="0" cellpadding="5"
							style="font-size: 15px;">
							<tr class="tbheader">
								<th align="left" width="2%">STT</th>
								<th align="left" width="8%">Mã Sản Phẩm</th>
								<th align="left" width="15%">Tên Sản Phẩm</th>
								<th align="left" width="13%">Đơn vị Tính</th>
								<th align="left" width="3%">Số lương</th>
								<th align="left" width="10%">Đơn giá</th>
								<th align="left" width="7%">Thành tiền</th>
							</tr>

							<% 
								int soluongSanPham =0;
							if(danhSachSanPham.size() > 0){
								for(int i=0; i< danhSachSanPham.size(); i++){
									IChiTietDonHang_Trinh ctdh = danhSachSanPham.get(i);
									soluongSanPham++;
								%>
							<TR>

								<td align="center" width="2%"><input type="text"
									value="<%= soluongSanPham%>" style="text-align: center;"
									readonly="readonly"> <input type="hidden"
									value="<%=ctdh.getIdSanPham()%>" name="PK_SEQ"
									style="width: 100%" readonly="readonly"></td>

								<TD align="left" width="8%"><input type="text"
									name="maSanPham" value="<%= ctdh.getMaSanPham() %>"
									style="text-align: center;"></TD>

								<TD align="left" width="10%"><input type="text"
									name="tenSanPham" readonly="readonly"
									value="<%= ctdh.getTenSanPham() %>" style="text-align: center;"></TD>

								<TD align="left" width="6%"><input type="text"
									name="donViTinh" readonly="readonly"
									value="<%= ctdh.getDonViTinh() %>" style="text-align: center;"></TD>

								<TD align="right" width="3%"><input type="text"
									name="soLuong" value="<%= ctdh.getSoLuong() %>"
									style="text-align: center;"></TD>

								<TD align="right" width="8%"><input type="text"
									name="giaBan" readonly="readonly"
									value="<%= ctdh.getDonGia() %>" style="text-align: center;"></TD>

								<TD align="right" width="8%"><input type="text"
									name="thanhTien" readonly="readonly"
									value="<%= ctdh.getThanhTien() %>" style="text-align: center;"></TD>
							</TR>
							<% 	
								}
							}%>
							<% for(int i = soluongSanPham; i < danhSachSanPham.size() + 20; i++)
								{%>

							<tr>
								<td align="center" width="2%"><input type="text"
									value="<%= i%>" style="text-align: center;" readonly="readonly">
									<input type="hidden" value="" name="PK_SEQ" style="width: 100%"
									readonly="readonly"></td>

								<TD align="left" width="8%"><input type="text"
									name="maSanPham" style="width: 100%" value=""
									onkeyup="ajax_showOptions(this,'donHangMoi',event)"
									AUTOCOMPLETE="off">
								<!-- chưa hiểu onkeyup --></TD>

								<TD align="left" width="10%"><input type="text" value=""
									readonly="readonly" name="tenSanPham" style="width: 100%">
								</TD>

								<TD align="center" width="6%"><input type="text" value=""
									readonly="readonly" name="donViTinh" style="width: 100%">
								</TD>

								<TD align="right" width="7%"><input type="text" value=""
									name="soLuong" style="width: 100%; text-align: right;"
									onkeyup="FormatNumber(this)"></TD>

								<TD align="right" width="8%"><input type="text" value=""
									name="giaBan" style="width: 100%; text-align: right;"
									readonly="readonly" onkeyup="FormatNumber(this)"></TD>

								<TD align="right" width="8%"><input type="text" value=""
									name="thanhTien" style="width: 100%; text-align: right;"
									readonly="readonly"></TD>
							</tr>
							<%} %>

							<tr class="tbfooter">
								<td colspan="12">&nbsp;</td>
							</tr>
						</table>

					</div>

				</fieldset>
			</div>
		</div>
		<%
		obj.closedb();
	%>
		<!-- gọi hàm replace để hoạt động -->
		<script type="text/javascript">
				replaces();
		</script>
	</form>
</BODY>
</html>