<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@page import ="geso.training.bean.hocsinh.IHocSinh"%>
<%@ page import="java.sql.ResultSet"%>
<%
// lay gia tri session
	IHocSinh obj = (IHocSinh)session.getAttribute("hocsinh");
	String userId = (String)session.getAttribute("userId");
	String userTen = (String)session.getAttribute("userTen");
// lay danh sach lop
	ResultSet RsLop = obj.getRsLop();
	//ResultSet RsHocSinhList = obj.getRsHocSinhList();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>SalesUp - Project</title>
	<META http-equiv="Content-Type" content="text/html; charset=utf-8">
	<META http-equiv="Content-Style-Type" content="text/css">
	
	<link rel="stylesheet" type="text/css" href="../css/main.css">
	<script type="text/javascript" src="../scripts/jquery.min.js"></script>
	<link href="../css/select2.css" rel="stylesheet"/>
	<script src="../scripts/select2.js"></script>
	
	<script>
		$(document).ready(function() { $("select").select2(); });
	</script>
	
	<!--<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
    <LINK rel="stylesheet" href="../css/main.css" type="text/css">
   <link type="text/css" rel="stylesheet" href="../css/mybutton.css">
   
	<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
    <link href="../css/jquery-ui.css" rel="stylesheet" type="text.css" />
	<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
	<script src="../scripts/ui/jquery.ui.core.widget.js" type="text/javascript"></script>
	<script src="../scripts/ui/jquery.ui.core.datepicker.js" type="text/javascript"></script>
	<link href="../css/select2.js" rel="stylesheet"/>
	<script src="../scripts/select2.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$(".days").datepicker({
				changeMonth: true, changeYear:true
			});
		});
	</script>
	
	<script>
		$(document).ready(function() { 
			$("select:not(.notuseselect2)").select2({width:'resolve'});
			});
	</script>-->
	
	<Script type="text/javascript">
	function saveform(){
		document.forms["hocsinhlist"].action.value = "luumoi";
		document.forms["hocsinhlist"].submit();
	}
	</Script>
</head>
<body>
<form name="hocsinhlist" method="post" action="../../HocsinhUpdatesvl">
<input type="hidden" name="action" value="1"/>
<div id="main" style="width:99%; padding-left:2px">
  <div align="left" id="header" style="width:100%; float:none">
	<div style="width:100%; padding:5px; float:left" class="tbnavigation">
		Quản lý học sinh
	</div>
	<div align="right" style="width:100%; padding:5px" class="tbnavigation">
		Hello guys <%=userTen%></div>
	</div>
 </div>
<div id="cotent" style="width:100%; float:none">
	<div align="left" style="width:100%; float:none; clear:left; font-size:0.7em">
	<fieldset>
	<LEGEND class="legendtitle">Tiêu chí tìm kiếm &nbsp;</LEGEND>
	<a href="javascript:saveform()">
	<img src="../images/1455966997_save.png" title="Save" alt="Save" border="1px" style="border-style:outset">
	</a>
	<TABLE width="100%" cellpadding="6" cellspacing="0">
		<tr>
			<TD class="plainlabel">Tên lớp </TD>
			<TD class="plainlabel">
			<select name="lop" id="lop" style="width:206px">
				<option value="">Chọn Lớp</option>
			<%
			  if(RsLop!=null){
				  while(RsLop.next()){
					  if(obj.getMaLop().equals(RsLop.getString("PK_SEQ")))
					  {
					  %>
					   <option selected = "selected" value="<%=RsLop.getString("PK_SEQ")%>">
					   <%=RsLop.getString("TENLOP")%>
					   </option>
					  <%
					  }
					  else
					  {
						  %>
						  <option value="<%=RsLop.getString("PK_SEQ")%>">
						  <%=RsLop.getString("TENLOP")%>
						  </option>
						  <%
					  }
				  }
			  }
			%>
			</select>
		</TD>
	   </tr>
	   <tr style="font-size:10px">
	   		<td class="plainlabel"> Tên học sinh </td>
	   		<td class="plainlabel">
	   		<input type="text"name="tenhocsinh"id="tenhocsinh"style="width:200px"value="<%=obj.getTenHS()%>">
	   		</td>
	   </tr>
	   
	   <tr style="font-size:10px">
	   		<td class="plainlabel"> Mã học sinh </td>
	   		<td class="plainlabel">
	   		<input type="text"name="mahocsinh"id="mahocsinh"style="width:200px"value="<%=obj.getMaHS()%>">
	   		</td>
	   </tr>
</TABLE>
</fieldset>
</div>
</div>

</form>
</body>
</html>

<% obj.DBClose(); %>
