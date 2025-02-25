<%@page import="geso.erp.util.UtilityKho_Vy"%>
<%@page import="geso.erp.beans.chuyenkho_vy.IPhieuChuyenKho_Vy"%>
<%@page import="java.util.Iterator"%>
<%@page import="geso.erp.beans.sanpham_vy.ISanPham_Vy"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.Hashtable"%>
<%@ page import="java.util.List"%>
<% 
	IPhieuChuyenKho_Vy phieuChuyenKho = (IPhieuChuyenKho_Vy) session.getAttribute("phieuChuyenKho");
	String userId = (String) session.getAttribute("userId");
	String userTen = (String) session.getAttribute("userTen");
	UtilityKho_Vy utilityKho = new UtilityKho_Vy();
  	ResultSet resultSetKho = utilityKho.createResultSetKho();
  	List<ISanPham_Vy> danhSachSanPhamPhieuChuyenKho = phieuChuyenKho.getDanhSachSanPham();
   	String readOnly = "";
   	String disabled = "";
  	if (phieuChuyenKho.isHienThi() == true) {
  		readOnly = "readonly";
  		disabled = "disabled";
  	} 

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
	filter: progid: DXImageTransform.Microsoft.Shadow( color= gray,
		direction= 135);
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
<script type="text/javascript" src="../scripts/erp-SanPhamKhoList_Vy.js"></script>

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
	//Lay ra mot mang giong nhu getParameterValue
	var idsp = document.getElementsByName("PK_SEQ");
	var masp = document.getElementsByName("maSanPham");
	var tensp = document.getElementsByName("tenSanPham");
	var donvitinh = document.getElementsByName("donViTinh");
	var soluongton = document.getElementsByName("soLuongTon");
	 
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
			
			if(pos > 0)
			{
				masp.item(i).value = sp.substring(0, pos);
				sp = sp.substr(parseInt(sp.indexOf(" -- ")) + 3);
				tensp.item(i).value = sp.substring(0, parseInt(sp.indexOf(" [")));
				
				sp = sp.substr(parseInt(sp.indexOf(" [")) + 2);
				donvitinh.item(i).value = sp.substring(0, parseInt(sp.indexOf("] [")));
				
				sp = sp.substr(parseInt(sp.indexOf("] [")) + 3);
				
				soluongton.item(i).value = sp.substring(0, parseInt(sp.indexOf("] [")));
				
				sp = sp.substr(parseInt(sp.indexOf("] [")) + 3);
				
				idsp.item(i).value = sp.substring(0, parseInt(sp.indexOf("]")));
/* 
				sp = sp.substr(parseInt(sp.indexOf("] [")) + 3);
				
				soluongton.item(i).value = sp.substring(0, parseInt(sp.indexOf("] ["))); */
				
				/* chietkhau.item(i).value = sp.substring(0, parseInt(sp.indexOf("] ["))); */
				
			}
			
			 
		}
		else 
		{
		 		
					idsp.item(i).value = "";
					tensp.item(i).value = "";
					donvitinh.item(i).value = "";
			 
					soluongton.item(i).value = "";
					 
			 
		}
	}
  
		setTimeout(replaces, 500);
}

	 function saveform()
	 {	
	 	 document.forms['dmhForm'].action.value='save';
	     document.forms['dmhForm'].submit();
	 }

	 function checkSanPham()
	 {
		 var masp = document.getElementsByName("maSanPham");
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
	<form name="dmhForm" method="post" action="../../ChuyenKhoNewSvl_Vy">
		<input type="hidden" name="userId" value='<%= userId %>'> <input
			type="hidden" name="action" value="" /> <input type="hidden"
			name="maPhieuChuyenKho" value='<%= phieuChuyenKho.getPK_SEQ() %>' />

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
								href="../../ChuyenKhoListSvl_Vy?userId=<%=userId%>"> <IMG
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
						style="height: 50px; width: 100%"><%= phieuChuyenKho.getMessage() %></textarea>
				</fieldset>
			</div>
			<div align="left" style="width: 100%; float: none; clear: left">
				<fieldset>
					<legend class="legendtitle"> Thêm Đơn Hàng </legend>
					<div style="float: none; width: 100%" align="left">
						<TABLE width="100%" cellpadding="4" cellspacing="0">
							<tr>

								<td class="plainlabel">Ngày ghi nhận</td>
								<TD class="plainlabel"><input type="text" name="ngayTao"
									class="days" value="<%=phieuChuyenKho.getNgayTao() %>"
									maxlength="10" <%=disabled%>></td>
							</tr>


							<tr>
								<td class="plainlabel">Kho chuyển</td>
								<td class="plainlabel"><select name="maKhoChuyen"
									onchange="submitform()" <%=disabled%> style="width: 200px">
										<option value="" selected="selected">Toàn bộ</option>
										<%
										String selected = "";
										//selected=\"selected\"
				                        while(resultSetKho.next()){
				                        	selected = (resultSetKho.getString("PK_SEQ").equals(phieuChuyenKho.getKhoChuyen().getPK_SEQ())) ? "selected=\"selected\"" : "";
				                        
				                    %>
										<option <%= selected %>
											value="<%=resultSetKho.getString("PK_SEQ")%>">
											<%=resultSetKho.getString("TENKHO") %>
										</option>
										<%
										} 
				                        resultSetKho.beforeFirst();
				                        
									%>

								</select></td>
							</tr>

							<tr>
								<td class="plainlabel">Kho nhận</td>
								<td class="plainlabel"><select name="maKhoNhan"
									<%=disabled%> style="width: 200px">
										<option value="" selected="selected">Toàn bộ</option>
										<%
										selected = "";
										//selected=\"selected\"
				                        while(resultSetKho.next()){
				                        	selected = (resultSetKho.getString("PK_SEQ").equals(phieuChuyenKho.getKhoNhan().getPK_SEQ())) ? "selected=\"selected\"" : "";
				                        	System.out.println(resultSetKho.getString("MAKHO"));
				                    %>
										<option <%= selected %>
											value="<%=resultSetKho.getString("PK_SEQ")%>">
											<%=resultSetKho.getString("TENKHO") %>
										</option>
										<%
										} 
									%>

								</select></td>
							</tr>

							<tr>
								<td class="plainlabel" width="10%">Lý do</td>
								<TD class="plainlabel" width="90%"><input type="text"
									<%= readOnly %> name="lyDo" placeholder="Kho X hết hàng"
									value="<%=phieuChuyenKho.getLyDo()%>"></td>

							</tr>
						</TABLE>
						<hr>
					</div>

					<!-- Chi tiết đơn hàng  -->

					<div align="left" style="width: 100%; float: none; clear: none;">
						<TABLE class="tabledetail" width="100%" border="0" cellpadding="5"
							style="font-size: 15px;">
							<!-- Tiêu đề -->
							<TR class="tbheader">
								<TH align="left" width="2%">STT</TH>

								<TH align="left" width="8%">Mã sản phẩm</TH>
								<TH align="left" width="15%">Sản phẩm</TH>
								<TH align="left" width="10%">Đơn vị tính</TH>
								<TH align="left" width="10%">Số lượng tồn</TH>
								<TH align="left" width="10%">Số lượng chuyển</TH>


							</TR>

							<!-- Lưới sản phẩm -->
							<!-- In sản phẩm -->
							<% 
						int countSanPhamRow = 0;
						if (danhSachSanPhamPhieuChuyenKho.size() > 0) {
							Iterator<ISanPham_Vy> iteratorSanPham = danhSachSanPhamPhieuChuyenKho.iterator();
							while (iteratorSanPham.hasNext()) {
								ISanPham_Vy elementSanPham = iteratorSanPham.next();
								int soLuongTonSanPham = phieuChuyenKho.getKhoChuyen().getSoLuongTon(elementSanPham);
								int i = 0;
							
						%>
							<tr>
								<TD align="center" width="2%"><input type="text"
									value="<%= countSanPhamRow + 1 %>" style="text-align: center;"
									readonly="readonly"> <input type="hidden"
									value=<%=elementSanPham.getPk_seq() %> name="PK_SEQ"
									style="width: 100%" readonly="readonly"></TD>
								<TD align="left" width="8%"><input type="text"
									name="maSanPham" style="width: 100%"
									value="<%=elementSanPham.getMaSanPham() %>" <%=readOnly%>
									onkeyup="ajax_showOptions(this,'phieuChuyenKho',event)"
									AUTOCOMPLETE="off"></TD>
								<TD align="left" width="15%"><input type="text"
									value="<%=elementSanPham.getTenSanPham() %>"
									readonly="readonly" name="tenSanPham" style="width: 100%">

								</TD>
								<TD align="center" width="10%"><input type="text"
									value="<%=elementSanPham.getmaDonViTinh() %>"
									readonly="readonly" name="donViTinh" style="width: 100%">

								</TD>

								<TD align="right" width="10%"><input type="text"
									value="<%= soLuongTonSanPham %>" name="soLuongTon"
									style="width: 100%; text-align: right;" readonly="readonly">
								</TD>

								<TD align="right" width="10%"><input type="number"
									<%=readOnly%> value="<%= elementSanPham.getSoLuong() %>"
									name="soLuongChuyen" style="width: 100%; text-align: right;"
									onkeyup="FormatNumber(this)"></TD>
							</tr>

							<% 
						countSanPhamRow++;} }%>
							<% for(int i = countSanPhamRow; i < 20; i++) { %>
							<tr>
								<TD align="center" width="2%"><input type="text"
									value="<%= i + 1 %>" style="text-align: center;"
									readonly="readonly"> <input type="hidden" value=""
									name="PK_SEQ" style="width: 100%" readonly="readonly">
								</TD>
								<TD align="left" width="8%"><input type="text"
									name="maSanPham" style="width: 100%" <%=readOnly%>
									onkeyup="ajax_showOptions(this,'phieuChuyenKho',event)"
									AUTOCOMPLETE="off"></TD>
								<TD align="left" width="15%"><input type="text"
									readonly="readonly" name="tenSanPham" style="width: 100%">

								</TD>
								<TD align="center" width="10%"><input type="text"
									readonly="readonly" name="donViTinh" style="width: 100%">

								</TD>

								<TD align="right" width="10%"><input type="text"
									name="soLuongTon" style="width: 100%; text-align: right;"
									readonly="readonly"></TD>

								<TD align="right" width="10%"><input type="number"
									<%=readOnly%> name="soLuongChuyen"
									style="width: 100%; text-align: right;"
									onkeyup="FormatNumber(this)"></TD>
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



		<%
		utilityKho.getDbutils().shutDown();
	%>
		<!-- Goi ham java script trong chuong trinh -->
		<script type="text/javascript">
				replaces();
		</script>
	</form>
</BODY>
</html>