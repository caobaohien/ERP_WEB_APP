<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import ="geso.training.bean.donhang.IDonHangList"
		import ="geso.training.bean.donhang.imp.DonHangList"
		import ="java.sql.ResultSet" %>

<%
	//lay gia tri session
	IDonHangList ob =(IDonHangList)session.getAttribute("ob");
	String userId=(String)session.getAttribute("userId");
	String userTen=(String)session.getAttribute("userTen");
	//lay danh sach khach hang va don hang
	ResultSet khRs=ob.getKhRs();
	ResultSet dhRs=ob.getDhRs();
	//String userId=obj.getUserId();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Best-Project</title>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">

<script type="text/javascript" src="../scripts/jquery.min.js"></script>
<script type="text/javascript" src="../scripts/speechbubbles.js"></script>
<script type="text/javascript" src="../scripts/dropdowncontent.js"></script>
<script type="text/javascript" src="../scripts/jquery.autocomplete.js"></script>

<LINK rel="stylesheet"type="text/css" media="print" href="../css/impression.css">
<LINK rel="stylesheet"type="text/css" href="../css/main.css">
<link rel="stylesheet"type="text/css" href="../css/mybutton.css">
<script type="text/javascript" src="..scripts/jquery-1.js"></script>

<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
<link rel="stylesheet"type="text/css" href="../css/jquery-ui.css">
<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.datepicker.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$(".days").datepicker({
			changeMonth:true,
			changeYear:true
		});
	});
</script>
<link rel="stylesheet" href="../css/select2.css">
<script src="../scripts/select2.js"></script>
<script>
	$(document).ready(function(){
		$("select").select2();
	});
</script>
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
<script language="javascript"type="text/javascript">
	function submitform()
	{  
		document.forms["donhanglist"].action.value = "timkiem";
	   	document.forms["donhanglist"].submit();
	}
	function searchform()
	{  
		document.forms["donhanglist"].action.value = "search";
	   	document.forms["donhanglist"].submit();
	}
	function newform(){
		document.forms["donhanglist"].action.value="moi";
		document.forms["donhanglist"].submit();
	}
	function clearform()
	 {   
		/* View('donhanglist', 1, 'view');  */
		//View('donhanglist', 1, 'view');
		
	   
	    document.forms["donhanglist"].sodonhang.value = "";
	    //document.forms["donhanglist"].khId.value = "";
	    document.forms["donhanglist"].submit();
	 } 
</script>
</head>
<body>
<form name="donhanglist" method="post" action="../../Donhanglistsvl">
<input type="hidden" name="userId" value="<%= userId %>"> 
<input type="hidden" name="action" value="1">
	<table width="100%"height="100%"cellspacing="0"cellpadding="0"border="0">
	<tr>
		<td colspan="4"align='left'valign='top'bgcolor="#FFFFFF"><!-- begin body Dossier-->
			<table width="100%" cellpadding="0"border="0">
				<tbody>
				<tr>
					<td align="left"class="tbnavigation">
					<table width="100%"cellpadding="0"cellspacing="0" border="0">
					<tr height="22">
						<td colspan="2" align="left"class="tbnavigation">&nbsp;Xử lý đơn hàng > Đơn hàng bán
						</td>
					</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td>
					<fieldset>
						<LEGEND class="legendtitle">&nbsp;Tiêu chí tìm kiếm</LEGEND>
						<table width="100%"cellpadding="6"cellspacing="0">
							<tr>
								<td class="plainlabel">Từ ngày</td>
								<td class="plainlabel">
								<input type="text"class="days"size="11"id="tungay"name="tungay"maxlength="10"
								readonly/></td>
							</tr>
							<tr>
								<td class="plainlabel">Đến ngày</td>
								<td class="plainlabel">
								<input type="text"class="days"size="11"id="denngay"
								name="denngay"maxlength="10"readonly/></td>
							</tr>
							<tr>
								<td class="plainlabel">Mã đơn hàng</td>
								<td class="plainlabel">
								<input type="text"name="sodonhang"value="<%=ob.getMadh()%>"size="11"placeholder="Ex: 100000"></td>
							</tr>
							<tr>
								<td class="plainlabel">Mã/tên khách hàng</td>
								<td class="plainlabel">
								<select onchange="submitform()"name="khId"id="khId"style="width:206px">
									<option value="">Toàn bộ</option>
									<%
									if(khRs!=null){
										try{while(khRs.next()){
											if(khRs.getString("PK_SEQ").equals(ob.getKhachhang())){
											%>
											<option selected="selected"
											value="<%=khRs.getString("PK_SEQ")%>"><%=khRs.getString("TEN") %>
										    </option>
										    <%
										}else{
									
											%>
											<option value="<%=khRs.getString("PK_SEQ")%>"><%=khRs.getString("TEN")%></option>
											<%
										}
										}
										}catch(SQLException e){
											e.printStackTrace();
										}
									}
									%>
								</select></td>
							</tr>
							<tr>
								<td class="plainlabel"colspan="3">&nbsp;
								<a class="button2" href="javascript:searchform()">
								<img style="top:-4px;"src="../images/Search30.png"alt="">Tìm kiếm</a>
								&nbsp;&nbsp;&nbsp;
								<a class="button2" href="javascript:clearform()">
								<img style="top:-4px;"src="../images/button.png"title="Undo"alt="Undo">Nhập lại</a>
								&nbsp;&nbsp;&nbsp;
								</td>
							</tr>
						</table>
					</fieldset>
					</td>
				</tr>
				<tr>
					<td>
					<fieldset>
					<LEGEND class="legendtitle">Đơn hàng bán&nbsp;&nbsp;&nbsp;
					<a class="button3" onclick="newform()">
					<img style="top:-4px;" src="../images/New.png" alt="">Tạo mới</a>
					</LEGEND>
						<table class=""width="100%">
							<tr>
								<td width="98%">
								<table width="100%" cellspacing="1px" cellpadding="4px" border="0">
									<tr class="tbheader">
										<th width="6%" align="center">Mã DH</th>
										<th width="10%" align="center">Khách hàng</th>
										<th width="10%" align="center">Tổng tiền</th>
										<th width="9%" align="center">Trạng thái</th>
										<th width="10%" align="center">Ngày đơn hàng</th>
										<th width="8%" align="center">Ngày tạo</th>
										<th width="10%" align="center">Người tạo</th>
										<th width="8%" align="center">Ngày sửa</th>
										<th width="15%" align="center">Người sửa</th>
										<th width="10%" align="center">Tác vụ</th>
									</tr>
										<%
											if(dhRs!=null){
												while(dhRs.next()){
												%>
												<tr style="font-size:14px">
													<td><%=dhRs.getString("PK_SEQ")%></td>
													<td><%=dhRs.getString("TEN")%></td>
													<td><%=dhRs.getString("TONGGIATRI")%></td>
													<% String trangthai="";
													if(dhRs.getString("TRANGTHAI").equals("0"))
														trangthai="Chưa chốt";
													else if(dhRs.getString("TRANGTHAI").equals("1"))
														trangthai="Đã chốt";
													else
														trangthai="Đã hủy";%>
													<td><%=trangthai%></td>
													<td><%=dhRs.getString("NGAYNHAP")%></td>
													<td><%=dhRs.getString("NGAYTAO")%></td>
													<td><%=dhRs.getString("NGUOITAO")%></td>
													<td><%=dhRs.getString("NGAYSUA")%></td>
													<td><%=dhRs.getString("NGUOISUA")%></td>
													<td><a href="../../DonhangUpdatesvl?userId=<%=userId%>&display=<%=dhRs.getString("PK_SEQ")%>">
													<img src="../images/Display20.png"width="25"height="25"alt="Hien thi"title="hien thi"border=0></a>&nbsp;
													<a href="../../DonhangUpdatesvl?userId=<%=userId%>&update=<%=
													dhRs.getString("PK_SEQ")%>"><img src="../images/Edit20.png"width="25"height="25"
													alt="Cập nhật"title="Cập nhật"border="0"></a>&nbsp;
													<a href="../../DonhangUpdatesvl?userId=<%=userId%>&delete=<%=dhRs.getString("PK_SEQ")%>">
													<img src="../images/Delete20.png"width="25"height="25"alt="Xóa"title="Xóa"border="0"
													onclick="if(!confirm('Bạn có muốn xóa đơn hàng này không?'))return false;">
													</a>&nbsp;
													</td>
												</tr>
												<%
												}
											}
										%>
								</table>
								</td>
							</tr>
						</table>
					</fieldset>
					</td>
				</tr>
				</tbody>
			</table>
		</td>
	</tr>			
	</table>
</form>
</body>
</html>
<% 
if(khRs!=null)
	khRs.close();
if(dhRs!=null)
	dhRs.close();
session.setAttribute("ob", null);
ob.DBClose(); 
%>