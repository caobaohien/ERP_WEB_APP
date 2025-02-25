<%@page import="geso.erp.util.UtilityKho_Vy"%>
<%@page import="geso.erp.beans.chuyenkho_vy.IPhieuChuyenKhoList_Vy"%>
<%@page import="geso.erp.beans.donbanhang_vy.*"%>
<%@page import="geso.dms.center.util.Utility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="geso.erp.beans.donbanhang_vy.*"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.text.NumberFormat"%>

<%
	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	

	IPhieuChuyenKhoList_Vy obj = (IPhieuChuyenKhoList_Vy) session.getAttribute("phieuChuyenKhoList");
	
	
	ResultSet resultSetKho = obj.getUtilityKho().createResultSetKho();
	
	ResultSet resultSetPhieuChuyenKho = obj.getUtilityKho().getResultSetKho();
	
	obj.setNextSplittings(); 
	
	NumberFormat numberFormat = new DecimalFormat("#,###,###");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SalesUp - Project</title>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" media="print"
	href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<LINK rel="stylesheet" href="../css/datepicker.css" type="text/css">

<script language="javascript" src="../scripts/datepicker.js"></script>
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../scripts/Timepicker/jquery.min.js"></script>
<script type="text/javascript"
	src="../scripts/Timepicker/jquery-ui.min.js"></script>
<script type="text/javascript" src="../scripts/phanTrang.js"></script>


<script type="text/javascript" src="../scripts/jquery.min.js"></script>
<script type="text/javascript" src="../scripts/speechbubbles.js"></script>
<script type="text/javascript" src="../scripts/dropdowncontent.js"></script>
<script type="text/javascript" src="../scripts/jquery.autocomplete.js"></script>


<script type="text/javascript" src="../scripts/jquery.js"></script>

<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
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

<script type="text/javascript" src="..scripts/jquery-1.js"></script>
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<script type="text/javascript">
        $(document).ready(function(){
            $(".button").hover(function(){
                $(".button img")
                .animate({top:"-10px"}, 200).animate({top:"-4px"}, 200) // first jump
                .animate({top:"-7px"}, 100).animate({top:"-4px"}, 100) // second jump
                .animate({top:"-6px"}, 100).animate({top:"-4px"}, 100); // the last jump
            });
        }); 
		$(document).ready(function(){
            $(".button2").hover(function(){
                $(".button2 img")
                .animate({top:"-10px"}, 200).animate({top:"-4px"}, 200) // first jump
                .animate({top:"-7px"}, 100).animate({top:"-4px"}, 100) // second jump
                .animate({top:"-6px"}, 100).animate({top:"-4px"}, 100); // the last jump
            });
        }); 
		$(document).ready(function(){
            $(".button3").hover(function(){
                $(".button3 img")
                .animate({top:"-10px"}, 200).animate({top:"-4px"}, 200) // first jump
                .animate({top:"-7px"}, 100).animate({top:"-4px"}, 100) // second jump
                .animate({top:"-6px"}, 100).animate({top:"-4px"}, 100); // the last jump
            });
        }); 
    </script>
<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
	     $(document).ready(function() { $("select").select2();  });	     
	</script>

<SCRIPT language="javascript" type="text/javascript">
	 function confirmLogout()
	 {
	    if(confirm("Bạn có muốn đăng xuất?"))
	    {
			top.location.href = "home.jsp";
	    }
	    return
	 }
	 function submitform()
	 {  
		document.forms["erpDmhForm"].action.value = "timkiem";
	    document.forms["erpDmhForm"].submit();
	 }
	 function taomoi()
	 {   
		 
		document.forms["erpDmhForm"].action.value = "taomoi";
	    document.forms["erpDmhForm"].submit();
	 }
	 
	 function chotphieu()
	 {
		document.forms["erpDmhForm"].action.value = "chot";
	    document.forms["erpDmhForm"].submit();
	 }
	 
	 function print_pdf()
	 {   
		document.forms["erpDmhForm"].action.value = "print";
	    document.forms["erpDmhForm"].submit();
	 }
	 
	 
	 
	 function clearform()
	 {   
		/* View('erpDmhForm', 1, 'view');  */
		View('erpDmhForm', 1, 'timkiem');
		
	    document.forms["erpDmhForm"].trangThai.value = "";
	    document.forms["erpDmhForm"].maPhieuChuyenKho.value = "";
	    document.forms["erpDmhForm"].maKhoNhan.value = "";
	    document.forms["erpDmhForm"].maKhoChuyen.value = "";
	    document.forms["erpDmhForm"].submit();
	 } 
	 function thongbao()
	 {
		 var tt = document.forms["erpDmhForm"].msg.value;
	 	 if(tt.length>0)
	     	alert(document.forms["erpDmhForm"].msg.value);
	 }
	 

	 function processing(id,chuoi)
	 {
	 
 	    document.getElementById(id).innerHTML = "<a href='#'><img src='../images/waiting.gif' width = '20' height = '20' title='cho thuc hien..' border='0' longdesc='cho thuc hien..' style='border-style:outset'> Proc...</a>"; 		  
 	 	document.getElementById(id).href=chuoi;
 	 }
	 
	 function processing_hoantat(id,id_lydo)
	 {
		var lydo=document.getElementById(id_lydo).value;
		document.forms["erpDmhForm"].action.value="hoantatphieu";
		
		document.forms["erpDmhForm"].lydohoantat_.value =lydo;
		document.forms["erpDmhForm"].idhoantat_.value =id;
	    document.forms["erpDmhForm"].submit();
 	 }
	 
	</SCRIPT>
</head>
<body>
	<form name="erpDmhForm" method="post"
		action="../../ChuyenKhoListSvl_Vy">
		<input type="hidden" name="userId" value="<%= userId %>"> <input
			type="hidden" name="action" value=""> <input type="hidden"
			name="crrApprSplitting" value="<%= obj.getCrrApprSplitting() %>">

		<input type="hidden" name="nxtApprSplitting"
			value="<%= obj.getNxtApprSplitting() %>"> <input
			type="hidden" name="message" value="<%=  obj.getMessage() %>">
		<script language="javascript" type="text/javascript">
    thongbao();
</script>

		<div id="main" style="width: 100%; padding-left: 2px">
			<div align="left" id="header" style="width: 100%; float: none">
				<div style="width: 70%; padding: 5px; float: left"
					class="tbnavigation">Danh sách phiếu chuyển kho</div>
				<div align="right" style="padding: 5px" class="tbnavigation">
					Chào mừng Bạn
					<%= userTen %>
					&nbsp;
				</div>
			</div>
			<div align="left" id="button"
				style="border: 1; width: 100%; height: 32px; padding: 1px; float: none"
				class="tbdarkrow";>

				<TABLE border="0" cellpadding="0" cellspacing="0">
					<TR class="tbdarkrow">

						<TD width="40px" align="left"><span id="btnthem"> <A
								href="javascript:taomoi()"> <IMG src="../images/add32.png"
									title="Tạo mới" alt="Tạo mới" border="1px"
									style="border-style: outset; width: 25px; height: 25px">
							</A>
						</span></TD>


						<TD width="40px" align="left"><span id="btnthem"> <A
								href="javascript:submitform()"> <IMG
									src="../images/timkiem_e.png" title="Tìm kiếm" alt="Tìm kiếm"
									border="1px"
									style="border-style: outset; width: 25px; height: 25px">
							</A>
						</span></TD>

						<TD width="30px" align="left"><span id="btnUndo"> <A
								href="javascript:clearform()"> <IMG
									src="../images/convert.gif" title="Undo" alt="Undo"
									border="1px"
									style="border-style: outset; width: 25px; height: 25px">
							</A>
						</span></TD>



					</TR>
				</TABLE>


			</div>
			<div></div>
			<div id="cotent" style="width: 100%; float: none">


				<div align="left"
					style="width: 100%; float: none; clear: left; font-size: 0.95em">
					<fieldset>
						<legend>
							<span class="legendtitle"> Phiếu chuyển kho </span>&nbsp;&nbsp;

						</legend>

						<LEGEND class="legendtitle">Tiêu chí tìm kiếm &nbsp;</LEGEND>
						<TABLE width="100%" cellpadding="6" cellspacing="0">
							<tr>
								<TD class="plainlabel" width="15%">Mã phiếu chuyển kho</TD>
								<TD class="plainlabel" width="90%"><input type="text"
									name="maPhieuChuyenKho" onchange="submitform()"
									value="<%=obj.getPK_SEQ()%>" placeholder="Ex: 100000">
								</TD>
							</tr>
							<tr>
								<TD class="plainlabel">Kho chuyển</TD>
								<td class="plainlabel"><select name="maKhoChuyen"
									style="width: 200px">
										<option value="">Toàn bộ</option>
										<%
										String selected = "";
										//selected=\"selected\"
				                        while(resultSetKho.next()){
				                        	selected = (resultSetKho.getString("PK_SEQ").equals(obj.getKhoChuyen().getPK_SEQ())) ? "selected=\"selected\"" : "";
				                        
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
							</TR>

							<tr>
								<TD class="plainlabel">Kho nhận</TD>
								<td class="plainlabel"><select name="maKhoNhan"
									style="width: 200px">
										<option value="" selected="selected">Toàn bộ</option>
										<%
										selected = "";
										//selected=\"selected\"
				                        while(resultSetKho.next()){
				                        	selected = (resultSetKho.getString("PK_SEQ").equals(obj.getKhoNhan().getPK_SEQ())) ? "selected=\"selected\"" : "";
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
							</TR>

							<TR>
								<TD class="plainlabel">Trạng thái</TD>
								<TD class="plainlabel"><select name="trangThai"
									style="width: 200px">
										<option value="">Toàn bộ</option>
										<%
					                    	String mangMaTrangThai[] = {"0", "1", "2"};
				                    		String mangTenTrangThai[] = {"Chưa chốt", "Đã chốt", "Đã hủy"};
				                    		for(int i = 0; i < mangMaTrangThai.length; i++){
											 if(obj.getTrangThai().equals(mangMaTrangThai[i])){
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


								</select></TD>

							</TR>

						</TABLE>
						<TABLE width="100%" border="0" cellspacing="1" cellpadding="4"
							style="border: 2px solid green;">
							<TR class="tbheader">
								<!-- <TH align="center" width="4%">Số đề nghị mua hàng</TH> -->
								<TH align="left" width="8%">Mã Phiếu Chuyển</TH>
								<TH align="left" width="8%">Lý do chuyển</TH>
								<TH align="left" width="13%">Người Tạo</TH>
								<TH align="left" width="5%">Ngày Tạo</TH>
								<TH align="left" width="10%">Người Sửa</TH>
								<TH align="left" width="5%">Ngày Sửa</TH>
								<TH align="left" width="9%">Tác Vụ</TH>
							</TR>

							<%
							int countRow = 0;
							String rowFormat = "";
							if (resultSetPhieuChuyenKho != null) {
								while ( resultSetPhieuChuyenKho.next() ) {
									String maTrangThai = resultSetPhieuChuyenKho.getString("TRANGTHAI");
									String maPhieuChuyenKho = resultSetPhieuChuyenKho.getString("PK_SEQ");
									String ngaySua = resultSetPhieuChuyenKho.getString("NGAYSUA");
									String nguoiSua = resultSetPhieuChuyenKho.getString("NGUOISUA");
						%>
							<TR
								class=<%= rowFormat = (countRow % 2 == 0) ? "'tbdarkrow'" : "'tblightrow'"%>>

								<TD align="left"><%= maPhieuChuyenKho %></TD>

								<TD align="left"><%= resultSetPhieuChuyenKho.getString("LYDO") %></TD>

								<TD align="left"><%= resultSetPhieuChuyenKho.getString("NGUOITAO") %></TD>

								<TD align="left"><%= resultSetPhieuChuyenKho.getString("NGAYTAO") %></TD>

								<TD align="left"><%= nguoiSua == null ? "Chưa" : nguoiSua %></TD>

								<TD align="left"><%= ngaySua == null ? "Chưa" : ngaySua %></TD>

								<TD align="center"><A
									href="../../ChuyenKhoNewSvl_Vy?userId=<%=userId%>&display=<%= maPhieuChuyenKho %>">
										<IMG src="../images/Display20.png" alt="Hiển thị"
										title="Hiển thị" border=0>
								</A>&nbsp; <% 
				                    if ( maTrangThai.equals("0") ) {
				                %> <A
									href="../../ChuyenKhoListSvl_Vy?userId=<%=userId%>&chot=<%= maPhieuChuyenKho%>">
										<IMG src="../images/Chot.png" width="20" height="20"
										alt="Chot don hang" title="Chốt" border=0>
								</A>&nbsp; <A
									href="../../ChuyenKhoNewSvl_Vy?userId=<%=userId%>&update=<%= maPhieuChuyenKho %>">
										<IMG src="../images/edit32.png" width="20" height="20"
										alt="Cap nhat" title="Cập nhật" border=0>
								</A>&nbsp; <A
									href="../../ChuyenKhoListSvl_Vy?userId=<%=userId%>&delete=<%= maPhieuChuyenKho%>">
										<IMG src="../images/DeleteRed_32.png" width="20" height="20"
										alt="Xoa don hang" title="Xóa" border=0>
								</A>&nbsp;</TD>
							</TR>
							<%}  %>
							<% 		
									countRow++;
								}
							resultSetPhieuChuyenKho.close();
							} %>

						</TABLE>

					</fieldset>
				</div>
			</div>
		</div>
	</form>
</body>
</html>

<%
	obj.getUtilityKho().getDbutils().shutDown();
%>
