<%@page import="geso.erp.beans.THONTINHOCSINH.IHoSoTuVanUpdate"%>
<%@page import="geso.erp.beans.THONTINHOCSINH.IHoSoUpdate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="geso.erp.beans.baocao.*"%>
<%@ page import="java.sql.ResultSet"%>
<% String userId = (String) session.getAttribute("userId");  %>
<% String userTen = (String) session.getAttribute("userTen");  %>
<%
	IHoSoUpdate obj =(IHoSoUpdate)session.getAttribute("obj"); 
	IHoSoTuVanUpdate objhstvnew =(IHoSoTuVanUpdate)session.getAttribute("objhstvnew"); 
	ResultSet rsqgtv=objhstvnew.getDataLisQuocgiat();
	ResultSet rsqg=obj.getDataLisQuocgiat();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Diageo - Project</title>
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
<script type="text/javascript" src="../scripts/dropdowncontent.js"></script>

<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.datepicker.js"
	type="text/javascript"></script>
<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script type="text/javascript">
		$(document).ready(function() {		
				$( ".days" ).datepicker({			    
						changeMonth: true,
						changeYear: true				
				});            
	        }); 		
		$(document).ready(function()
				{
				 $("#khId").select2();
				 $("#nccId").select2();
				 $("#ddkdId").select2();
				 $("#spId").select2();  
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
<SCRIPT language="javascript" type="text/javascript">
	 function confirmLogout()
	 {
	    if(confirm("Bạn có muốn đăng xuất?"))
	    {
			top.location.href = "home.jsp";
	    }
	    return
	 }
	 function savequocgia()
	 {
		 document.forms["erpDmhForm"].action.value = "savequocgia";
	     document.forms["erpDmhForm"].submit();
	 }
	 function checktiemnang(id) {
		    for (var i = 1;i <= 2; i++)
		    {
		        document.getElementById("check" + i).checked = false;
		    }
		    document.getElementById(id).checked = true;
		    var x=document.getElementById("check"+i).value();
		}
	 function submitform()
	 { 
		 document.forms["erpDmhForm"].action.value = "submit";
	     document.forms["erpDmhForm"].submit();
	 }
	 function loadquocgia()
	 { 
		 document.forms["erpDmhForm"].action.value = "loadqg";
	     document.forms["erpDmhForm"].submit();
	 }
	 function saveform()
	 { 
		 document.forms["erpDmhForm"].action.value = "save";
	     document.forms["erpDmhForm"].submit();
	 }
	 function locsanpham()
	 {   
		 document.forms["erpDmhForm"].action.value = "search";
	     document.forms["erpDmhForm"].submit();
	 }
	
	 function thongbao()
	 {
		 var tt = document.forms["erpDmhForm"].msg.value;
	 	 if(tt.length>0)
	     	alert(document.forms["erpDmhForm"].msg.value);
	 }
	 
	 function sellectAll()
	 {
		 var chkAll = document.getElementById("chkAll");
		 var spIds = document.getElementsByName("spIds");
		 
		 if(chkAll.checked)
		 {
			 for(i = 0; i < spIds.length; i++)
			 {
				 spIds.item(i).checked = true;
			 }
		 }
		 else
		 {
			 for(i = 0; i < spIds.length; i++)
			 {
				 spIds.item(i).checked = false;
			 }
		 }
	 }
	 
	 function sellectAll2()
	 {
		 var chkAll = document.getElementById("chkAll2");
		 var spIds = document.getElementsByName("clIds");
		 
		 if(chkAll.checked)
		 {
			 for(i = 0; i < spIds.length; i++)
			 {
				 spIds.item(i).checked = true;
			 }
		 }
		 else
		 {
			 for(i = 0; i < spIds.length; i++)
			 {
				 spIds.item(i).checked = false;
			 }
		 }
	 }
	 function sellectAll3()
	 {
		 var chkAll = document.getElementById("chkAll3");
		 var spIds = document.getElementsByName("ndxIds");
		 
		 if(chkAll.checked)
		 {
			 for(i = 0; i < spIds.length; i++)
			 {
				 spIds.item(i).checked = true;
			 }
		 }
		 else
		 {
			 for(i = 0; i < spIds.length; i++)
			 {
				 spIds.item(i).checked = false;
			 }
		 }
	 }
	</SCRIPT>
</head>
<body>
	<form name="erpDmhForm" method="post" action="../../HoSoTuVanListSvl">
		<input type="hidden" name="userId" value="<%= userId %>"> <input
			type="hidden" name="userTen" value="<%= userTen %>"> <input
			type="hidden" name="action" value="1"> <input type="hidden"
			name="id" value="<%=obj.getId() %>">

		<div align="left" id="header" style="width: 100%; float: none">
			<div style="width: 60%; padding: 5px; float: left"
				class="tbnavigation">Quản lý cung ứng</div>
			<div align="right" style="padding: 5px" class="tbnavigation">
				Chào mừng Bạn
				<%= userTen %>
				&nbsp;
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
							href="../../HoSoTuVanListSvl?userId=<%=userId%>"> <IMG
								src="../images/Back_undo.png" title="Undo" alt="Undo"
								border="1px" style="border-style: outset"></A>
					</span>
				</TR>
			</TABLE>

		</div>
		<div class="plainlabel">
			<fieldset style="margin-top: 5px">
				<TABLE width="100%" cellpadding="6px" cellspacing="0px">
					<tr>
						<td class="plainlabel"><label class="plainlabel">Ngày
								Tạo Hồ Sơ:</label> <input class="plainlabel" readonly type="text"
							name="ngaytuvan" value="<%=obj.getNgaytaohoso()%>" /></td>
						<td class="plainlabel"><label class="plainlabel">Ngày
								Tư vấn:</label> <input class="plainlabel" readonly type="text"
							name="ngaytuvan" value="<%=objhstvnew.getNgaytuvan() %>" /></td>
					</tr>

				</TABLE>
		</div>

		<div id="cotent" style="width: 100%; float: none">
			<div align="left"
				style="width: 100%; float: none; clear: left; font-size: 0.7em">
				<fieldset style="margin-top: 5px">

					<legend>
						<span style="color: red" class="legendtitle">Thông tin học
							sinh</span>&nbsp;&nbsp;
					</legend>
					<TABLE width="100%" cellpadding="6px" cellspacing="0px"
						style="margin-top: 5px">

						<TR>
							<TD class="plainlabel" width="15%">Họ Tên</TD>
							<TD class="plainlabel"><input type="text" readonly
								name="hotenhs" value="<%=obj.getHoten() %>" /></TD>
							<TD class="plainlabel" width="15%">Ngày sinh</TD>
							<TD class="plainlabel"><input type="text" maxlength="10"
								readonly name="ngaysinhhs" value="<%=obj.getNgaysinh() %>" /></TD>
						</TR>
						<TR>
							<TD class="plainlabel" width="15%">Trình độ học vấn</TD>
							<TD class="plainlabel">
								<%	String arraytdhvnum[]={"0","1","2","3","4","5","6"};
                            	String arraytdhv[]={"THCS","THPT","Cao Đẳng-Đại Học năm nhất","Cao Đẳng-Đại Học năm 2","Cao Đẳng-Đại Học năm 3","Cao Đẳng-Đại Học năm 4","Cao Đẳng-Đại Học năm cuối"};
                            	%> <select name="tdhocvan"
								onchange="submitform()" disabled>
									<option value=""></option>
									<%
				         		 		for(int i=0;i<arraytdhvnum.length;i++)
				         		 		{
				         		 			if(obj.getTrinhdohocvan().equals(arraytdhvnum[i]))
				         		 			{
				         		 			%>
									<option selected="selected" value="<%=arraytdhvnum[i]%>"><%=arraytdhv[i]%></option>
									<%} 
				         		 			else
				         		 			{
				         		 			%>
									<option value="<%=arraytdhvnum[i]%>"><%=arraytdhv[i]%></option>
									<%} %>
									<%} %>
							</select>
							</TD>
							<TD class="plainlabel" width="15%">Khả năng tiếng anh</TD>
							<TD class="plainlabel">
								<%		String arraykntanum[]={"0","1","2","3","4"};
                            	String arrayknta[]={"ILTS","TOEFL","Trung Bình","Khá","Giỏi"};
                            	%> <select name="kntienganh"
								onchange="submitform()" disabled>
									<option value=""></option>
									<%
				         		 		for(int i=0;i<arraykntanum.length;i++)
				         		 		{
				         		 			if(obj.getKhanangtienganh().equals(arraykntanum[i]))
				         		 			{
				         		 			%>
									<option selected="selected" value="<%=arraykntanum[i]%>"><%=arrayknta[i]%></option>
									<%} 
				         		 			else
				         		 			{
				         		 			%>
									<option value="<%=arraykntanum[i]%>"><%=arrayknta[i]%></option>
									<%} %>
									<%} %>

							</select>
							</TD>

						</TR>

						<TR>
							<TD class="plainlabel" width="15%">Nguyện vọng đi học</TD>
							<TD class="plainlabel">
								<%		String arraynv1[]={"0","1","2","3"};
                            	String arraynv[]={"Ở với người thân","Tự do","Học bổng","khác"};
                            	%> <select name="nguyenvong"
								onchange="submitform()" disabled>

									<option value=""></option>
									<%
				         		 		for(int i=0;i<arraynv1.length;i++)
				         		 		{
				         		 			if(obj.getNguyenvong().equals(arraynv1[i]))
				         		 			{
				         		 			%>
									<option selected="selected" value="<%=arraynv1[i]%>"><%=arraynv[i]%></option>

									<%} else 
				         		 		{
				         		 		%>
									<option value="<%=arraynv1[i]%>"><%=arraynv[i]%></option>

									<%} %>
									<%} %>

							</select> <%
                    		if(obj.getNguyenvong().equals("3"))
                    		{
                    	%> <input type="text" readonly
								name="nguyenvongkhac" value="" maxlength="10" /> <%} %>
							</TD>
							<TD class="plainlabel" width="15%">Quốc gia học</TD>
							<TD class="plainlabel"><select class="select2"
								name="litsquocgia" id="spId" style="width: 200px;"
								multiple='multiple' disabled onchange="submitform()">
									<option value=""></option>
									<%
						         
						          if (rsqg != null)
						          {
							           while (rsqg.next()) 
							           {	
							        	   String maqgtemp=rsqg.getString("PK_SEQ");
							        	   boolean check = false;
							        	   for(int i=0;i<obj.get_pk_seqquocgiahoc().size();i++)
							        	   {
							        		   
									            if (maqgtemp.equals(obj.get_pk_seqquocgiahoc().get(i)))
									            {
										            %>
									<option value="<%=rsqg.getString("PK_SEQ")%>" selected><%=rsqg.getString("TEN")%></option>
									<% check = true; 
										           	  break; 
									           }}
							        	   if(check == false){ %>
									<option value="<%=rsqg.getString("PK_SEQ")%>"><%=rsqg.getString("TEN")%></option>
									<%}
									     }
						          } %>

							</select></TD>
						</TR>

						<TR>
							<TD class="plainlabel" width="15%">Địa chỉ</TD>
							<TD class="plainlabel"><input type="text" readonly
								name="diachihs" maxlength="10" value="<%=obj.getDiachi() %>" />
							</TD>
							<TD class="plainlabel" width="15%">Điện thoại</TD>
							<TD class="plainlabel"><input type="text" readonly
								maxlength="10" name="dienthoaihs"
								value="<%=obj.getDienthoai() %>" /></TD>
						</TR>
						<tr>
							<td colspan="4" style="background-color: #C5E8CD"><a
								class="plainlabel" href="" id="noidungGhiChu" rel="ndGhiChu">&nbsp;
									<img alt="chi tiet hs" src="../images/vitriluu.png">Chi
									Tiết Học Sinh
							</a>
								<DIV id="ndGhiChu"
									style="position: absolute; visibility: hidden; border: 9px solid #80CB9B; background-color: white; width: 1000px; min-height: 150px; overflow: auto; padding: 2px;">
									<TABLE width="100%" cellpadding="6px" cellspacing="0px"
										style="margin-top: 5px">
										<TR>
											<TD style="color: red" align="center" class="plainlabel"
												width="15%" colspan="4">THÔNG TIN CHI TIẾT</TD>
										</TR>
										<TR>
											<TD class="plainlabel" width="15%">Email Học Sinh</TD>
											<TD class="plainlabel"><input type="text" readonly
												maxlength="10" name="emailhs" value="<%=obj.getEmail() %>" />
											</TD>
											<TD class="plainlabel" width="15%">FaceBook</TD>
											<TD class="plainlabel"><input type="text" readonly
												maxlength="10" name="facebookhs"
												value="<%=obj.getfacebook()%>" /></TD>
										</TR>
										<tr>
											<TD class="plainlabel" width="15%">Khả năng học vấn</TD>
											<TD class="plainlabel">
												<%		String arrayknhvnum[]={"0","1","2","3"};
                            	String arrayknhv[]={"yếu","Trung Bình","Khá","Giỏi"};
                            	%> <select name="knhocvan"
												onchange="submitform()" disabled>

													<option value=""></option>
													<%
				         		 		for(int i=0;i<arrayknhvnum.length;i++)
				         		 		{
				         		 			if(obj.getKhananghocvan().equals(arrayknhvnum[i]))
				         		 			{
				         		 			%>
													<option selected="selected" value="<%=arrayknhvnum[i]%>"><%=arrayknhv[i]%></option>
													<%} 
				         		 			else
				         		 			{
				         		 			%>
													<option value="<%=arrayknhvnum[i]%>"><%=arrayknhv[i]%></option>
													<%} %>
													<%} %>

											</select>
											</TD>
											<TD class="plainlabel" width="15%">Nguồn</TD>
											<TD class="plainlabel">
												<%		
                            	String arraynguon[]={"Truyền miệng","Báo chí","Website","Fanface","Khác"};
                            	%> <select name="nguon" disabled>

													<option value=""></option>
													<%
				         		 		for(int i=0;i<arraynguon.length;i++)
				         		 		{
				         		 			%>
													<option value=""><%=arraynguon[i]%></option>
													<%} %>

											</select>
											</TD>
										</tr>
										<TR>
											<TD class="plainlabel" width="15%">Họ Tên cha</TD>
											<TD class="plainlabel"><input type="text" readonly
												maxlength="10" name="hotencha"
												value="<%=obj.getHoten_cha()%>" /></TD>
											<TD class="plainlabel" width="15%">Họ Tên mẹ</TD>
											<TD class="plainlabel"><input type="text" readonly
												maxlength="10" name="hotenme"
												value="<%=obj.getHoten_me() %>" /></TD>
										</TR>
										<TR>
											<TD class="plainlabel" width="15%">Sinh Ngày</TD>
											<TD class="plainlabel"><input type="text" readonly
												maxlength="10" name="ngaysinhcha"
												value="<%=obj.getNgaysinh_cha() %>" /></TD>
											<TD class="plainlabel" width="15%">Sinh Ngày</TD>
											<TD class="plainlabel"><input type="text" readonly
												maxlength="10" name="ngaysinhme"
												value="<%=obj.getNgaysinh_me() %>" /></TD>
										</TR>
										<TR>
											<TD class="plainlabel" width="15%">Địa chỉ (Nếu
												khác ở trên)</TD>
											<TD class="plainlabel"><input type="text" readonly
												name="diachicha" maxlength="10"
												value="<%=obj.getDiachi_cha() %>" /></TD>
											<TD class="plainlabel" width="15%">Địa chỉ (Nếu
												khác ở trên)</TD>
											<TD class="plainlabel"><input type="text" readonly
												name="diachime" maxlength="10"
												value="<%=obj.getDiachi_me() %>" /></TD>
										</TR>
										<TR>
											<TD class="plainlabel" width="15%">Số Điện thoại</TD>
											<TD class="plainlabel"><input type="text" readonly
												maxlength="10" name="dienthoaicha"
												value="<%=obj.getDienthoai_cha()%>" /></TD>
											<TD class="plainlabel" width="15%">Số Điện thoại</TD>
											<TD class="plainlabel"><input type="text" readonly
												maxlength="10" name="dienthoaime"
												value="<%=obj.getDienthoai_me()%>" /></TD>
										</TR>

										<TR>
											<TD class="plainlabel" width="15%">Nghề Nghiệp</TD>
											<TD class="plainlabel"><input type="text" readonly
												maxlength="10" name="nghenghiepcha"
												value="<%=obj.getNghenghiep_cha() %>" /></TD>
											<TD class="plainlabel" width="15%">Nghề Nghiệp</TD>
											<TD class="plainlabel"><input type="text" readonly
												maxlength="10" name="nghenghiepme"
												value="<%=obj.getNghenghiep_me()%>" /></TD>
										</TR>
										<TR>
											<TD class="plainlabel" width="25%">Thu nhập hàng
												tháng của gia đình</TD>
											<TD class="plainlabel"><input type="text" readonly
												maxlength="10" name="thunhap"
												value="<%=obj.getThunhap_GD()%>" /></TD>
											<TD class="plainlabel" width="25%">Email Khác(Nếu
												có)</TD>
											<TD class="plainlabel"><input type="text" readonly
												maxlength="10" name="emailkhac"
												value="<%=obj.getEmail_khac() %>" /></TD>
										</TR>
									</TABLE>
								</div></td>
						</tr>

					</TABLE>
			</div>

			<div align="left"
				style="width: 100%; float: none; clear: left; font-size: 0.7em">
				<fieldset style="margin-top: 5px">
					<legend>
						<span style="color: red" class="legendtitle">Thông tin tư
							vấn</span>&nbsp;&nbsp;
					</legend>
					<TABLE width="100%" cellpadding="6px" cellspacing="0px"
						style="margin-top: 5px; background-color: red">

						<TR>
							<TD class="plainlabel" width="15%">Quốc gia theo học</TD>
							<TD class="plainlabel"><select name="litsquocgia" disabled
								onchange="submitform()" style="width: 200px">
									<option value=""></option>
									<%
                     
                        if(rsqgtv!= null)
                        {
                        
                        	 
                         while(rsqgtv.next())
                         {
	                          if(rsqgtv.getString("PK_SEQ").equals(objhstvnew.getQuocgia_fk()))
	                          {
	                         %>
									<option selected="selected"
										value="<%= rsqgtv.getString("PK_SEQ") %>"><%= rsqgtv.getString("TEN") %></option>
									<%} else%>
									<%{ %>
									<option value="<%= rsqgtv.getString("PK_SEQ") %>"><%= rsqgtv.getString("TEN") %></option>
									<%} %>
									<%} %>
									<% }%>

							</select></TD>
						</TR>
						<TR>
							<TD class="plainlabel" width="15%">Diễn giải</TD>
							<TD class="plainlabel"><textarea style="width: 400px"
									rows="2" cols="10" name="diengiai" disabled><%=objhstvnew.getDiengiai() %></textarea>
							</TD>
						</TR>
						<TR>
							<TD class="plainlabel" width="15%">Nhận xét</TD>
							<TD class="plainlabel"><textarea style="width: 400px"
									rows="2" cols="5" name="nhanxet" disabled><%=objhstvnew.getNhanxet() %></textarea>
							</TD>
						</TR>
						<TR>
							<TD class="plainlabel" width="5%">Tiềm năng</TD>
							<TD class="plainlabel">
								<%if(objhstvnew.getTiemnang().equals("0")) 
                        {%> <input type="radio" checked="checked"
								maxlength="10" id="check1" name="tiemnang" value="0" />Có <input
								type="radio" maxlength="10" name="tiemnang" id="check2"
								value="1" />Không <%}else if(objhstvnew.getTiemnang().equals("1")){ %>
								<input type="radio" maxlength="10" id="check1" name="tiemnang"
								value="0" />Có <input type="radio" checked="checked"
								maxlength="10" name="tiemnang" id="check2" value="1" />Không <%} else{%>
								<input type="radio" checked="checked" maxlength="10" id="check1"
								name="tiemnang" value="0" />Có <input type="radio"
								maxlength="10" name="tiemnang" id="check2" value="1" />Không <%} %>
							</TD>
						</TR>

					</TABLE>
			</div>
	</form>
</body>
<script type="text/javascript">
dropdowncontent.init("noidungGhiChu", "right-botom", 500, "click");
</script>
</html>


