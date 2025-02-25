<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="geso.dms.center.util.*"%>
<%
	String userId = (String) session.getAttribute("userId");
	String userTen = (String) session.getAttribute("userTen");
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if (!util.check(userId, userTen, sum)) {
		response.sendRedirect("/TrainingGESO/index.jsp");
	} else {
		//IMenu obj = (IMenu)session.getAttribute("obj");
		//int[] mang = obj.getMang(); 
		int mang[] = util.quyen_ungdung(userId);
		System.out.println("mang :" + mang[1]);
		String chuoi = (String)session.getValue("chuoi");
		if (chuoi.equals("")) {
			chuoi = "../css/style1024.css";
		}
		
%>
<html>
<head>
<LINK id="sitetheme" rel="stylesheet" href="<%=chuoi%>" type="text/css">
<style type="text/css">
body {
	font-family: "Times New Roman", Georgia, Serif;
	font-size: 1em;
	margin: 1px;
	background-color: #ffffff;
}
</style>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>jQuery Accordion Example</title>
<style type="text/css">
/* A few IE bug fixes */
* {
	margin: 0;
	padding: 0;
}

* html ul ul li a {
	height: 80%;
}

* html ul li a {
	height: 80%;
}

* html ul ul li {
	margin-bottom: -1px;
}

body {
	padding-left: 0em;
	font-family: Arial, Helvetica, sans-serif;
}

#theMenu {
	width: 0px;
	height: 0px;
	margin: 0px;
	padding: 0;
}

/* Some list and link styling */
ul li {
	width: 210px;
	line-height: 20pt;
	margin-bottom: 0;
	border-left-width: 0px;
	border-left-style: none;
	border-left-color: #FFFFFF;
	background-color: #80CB9B;
	border-bottom-style: solid;
	border-bottom-color: #FFFFFF;
	border-bottom-width: thin;
	margin-left: 0px;
	padding-top: 0;
	padding-right: 0;
	padding-bottom: 0;
	padding-left: 5px;
	text-indent: 5px;
}

ul li a {
	color: #000000;
	font-size: 10pt;
	background-color: #80CB9B;
	width: 210px;
}

ul li a:hover {
	display: block;
	color: #fff;
	background-color: #80CB9B;
	font-size: small;
	width: 210px;
}

ul ul li {
	width: 210px;
	margin-left: 0px;
	background-position: left center;
	line-height: 18pt;
	border-top-style: none;
	border-right-style: none;
	border-bottom-style: none;
	border-left-style: none;
	border-top-color: #FFFFFF;
	border-right-color: #FFFFFF;
	border-bottom-color: #FFFFFF;
	border-left-color: #FFFFFF;
	background-color: #C5E8CD;
	border-bottom-width: thin;
	padding-left: 0;
	font-family: Arial, Helvetica, sans-serif;
}

ul ul li a {
	display: marker;
	color: #fff;
	padding: 0px;
	font-size: small;
	width: 210px;
}

ul ul li a:hover {
	display: block;
	color: #369;
	padding: 0px;
	font-size: small;
	width: 210px;
}

/* For the xtra menu */
ul ul li a.selected {
	display: marker;
	color: #369;
	padding: 0px;
	font-size: small;
}

ul ul ul li {
	padding: 0;
	width: 210px;
	margin-left: 0px;
	background-position: left center;
	line-height: 20pt;
	border-top-style: none;
	border-right-style: none;
	border-bottom-style: none;
	border-left-style: none;
	border-top-color: #FFFFFF;
	border-right-color: #FFFFFF;
	border-bottom-color: #FFFFFF;
	border-left-color: #FFFFFF;
	background-color: #FFFFFF;
	border-bottom-width: thin;
}

ul ul ul li a {
	display: block;
	color: #fff;
	padding: 0px;
	font-size: small;
	background-color: #FFFFFF;
}

ul ul ul li a:hover {
	display: block;
	color: #369;
	background-color: #CEF6F5;
	padding: 0px;
	font-size: small;
}

ul ul ul li a.selected {
	display: block;
	color: #369;
	background-color: #CEF6F5;
	padding: 0px;
	font-size: small;
}

/* For the sub menu */
ul ul ul ul li {
	border-left: none;
	border-bottom: none;
	padding: 0;
	width: 210px;
	margin-bottom: 0;
	margin-left: 5px;
	background-color: #F4F4F0;
}

ul ul ul ul li a {
	display: block;
	color: #fff;
	padding: 0px;
	font-size: small;
	background-color: #CCCCCC;
}

ul ul ul ul li a:hover {
	display: block;
	color: #369;
	background-color: #CEF6F5;
	padding: 3px 8px;
	font-size: small;
}

ul ul ul ul li a.selected {
	display: block;
	color: #369;
	background-color: #CEF6F5;
	padding: 3px 8px;
	font-size: small;
}

li {
	list-style-type: none;
}

h2 {
	margin-top: 0em;
}
</style>
</head>
<script type="text/javascript" src="../scripts/jquery.js"></script>
<script type="text/javascript" src="../scripts/accordion.js"></script>
<script type="text/javascript">
	

	jQuery().ready(function()
	{
		jQuery("a").click(function(e){
			jQuery("a").css("color", "black");
			jQuery(this).css("color", "red");
		});
		
		// applying the settings
		jQuery('#theMenu').Accordion(
		{
			active : 'h3.selected',
			header : 'h3.head',
			alwaysOpen : false,
			animated : true,
			showSpeed : 400,
			hideSpeed : 800
		});
		jQuery('#xtraMenu1').Accordion(
		{
			active : 'h4.selected',
			header : 'h4.head',
			alwaysOpen : false,
			animated : true,
			showSpeed : 400,
			hideSpeed : 800
		});

		jQuery('#xtraMenu2').Accordion(
		{
			active : 'h4.selected',
			header : 'h4.head',
			alwaysOpen : false,
			animated : true,
			showSpeed : 400,
			hideSpeed : 800
		});

		jQuery('#xtraMenu3').Accordion(
		{
			active : 'h4.selected',
			header : 'h4.head',
			alwaysOpen : false,
			animated : true,
			showSpeed : 400,
			hideSpeed : 800
		});

		jQuery('#xtraMenu4').Accordion(
		{
			active : 'h4.selected',
			header : 'h4.head',
			alwaysOpen : false,
			animated : true,
			showSpeed : 400,
			hideSpeed : 800
		});

		jQuery('#xtraMenu5').Accordion(
		{
			active : 'h4.selected',
			header : 'h4.head',
			alwaysOpen : false,
			animated : true,
			showSpeed : 400,
			hideSpeed : 800
		});

		jQuery('#xtraMenu6').Accordion(
		{
			active : 'h4.selected',
			header : 'h4.head',
			alwaysOpen : false,
			animated : true,
			showSpeed : 400,
			hideSpeed : 800
		});
		
		jQuery('#xtraMenu7').Accordion(
		{
			active : 'h4.selected',
			header : 'h4.head',
			alwaysOpen : false,
			animated : true,
			showSpeed : 400,
			hideSpeed : 800
		});
		
		jQuery('#xtraMenu8').Accordion(
		{
			active : 'h4.selected',
			header : 'h4.head',
			alwaysOpen : false,
			animated : true,
			showSpeed : 400,
			hideSpeed : 800
		});
		
		jQuery('#xtraMenu9').Accordion(
		{
			active : 'h4.selected',
			header : 'h4.head',
			alwaysOpen : false,
			animated : true,
			showSpeed : 400,
			hideSpeed : 800
		});
		
		jQuery('#xtraMenu10').Accordion(
		{
			active : 'h4.selected',
			header : 'h4.head',
			alwaysOpen : false,
			animated : true,
			showSpeed : 400,
			hideSpeed : 800
		});

		jQuery('#subMenu').Accordion(
		{
			active : 'h6.selected',
			header : 'h6.head',
			alwaysOpen : false,
			animated : true,
			showSpeed : 400,
			hideSpeed : 800
		});
		
	});
</script>
<body>
	<ul id="theMenu">

		<li style="position: static;">
			<h3 class="head">
				<a href="" class="head">Quản lý học sinh</a>
			</h3>
			<ul style="display: block;">
				<li>
					<ul id="xtraMenu3">

						<li>
							<h4 class="head">
								<a href="">Cơ bản </a>
							</h4>
							<ul style="display: none;">
							<li>
									<%if (mang[1]==1)
									{%>
									<h5 class="head">
										<a href="../../ErpCongTySvl?userId=<%=userId%>"
											target="content">Công ty</a>
									</h5> <%}%>
							</li>
							<li>
									<h5 class="head">
										<a href="../../HocSinhsvl?userId=<%=userId%>"
											target="content">Học Sinh</a>
									</h5>
							</li>
						</ul>
						</li>
					</ul>
				</li>
			</ul>
		<li style="position: static;">
			<h3 class="head">
				<a href="" class="head">Dữ liệu nền </a>
			</h3>
			<ul style="display: block;">
				<li>
					<ul id="xtraMenu1">
					
						<li>
							<h4 class="head">
								<a href="">Cơ bản </a>
							</h4>
							<ul style="display: none;">
								<li>
									<%if (mang[1] == 1) {%>
									<h5 class="head">
										<a href="../../ErpCongTySvl?userId=<%=userId%>"
											target="content">Công ty</a>
									</h5> <%}%>
								</li>
								<li>

									<h5 class="head">
										<a href="../../ErpBCViengThamSvl?userId=<%=userId%>"
											target="content">Bao Cao Vieng Tham</a>
									</h5>
								</li>
								<li>

									<h5 class="head">
										<a href="../../DoanhSoTheoTinhSvl?userId=<%=userId%>"
											target="content">Doanh Số Theo Tỉnh</a>
									</h5>
								</li>

								<li>
								<li>

									<h5 class="head">
										<a href="../../ErpBCSoLuongSvl_cuong?userId=<%=userId%>"
											target="content">Báo cáo số lượng</a>
									</h5>
								</li>

								<li>
									<h5 class="head">

										<a href="../../ChuyenkhoListSvl?userId=<%=userId%>"
											target="content">Chuyển kho - Thái</a>

									</h5>
								</li>
								<li>
									<h5 class="head">
										<a href="../../Donhanglistsvl?userId=<%=userId%>"
											target="content">Đơn Hàng</a>
									</h5>
								</li>
								<li>
									<%
									if (mang[2] == 1) {
								%>
									<h5 class="head" id="dvthId">
										<a
											href="../../Erp_DonViThucHienSvl?userId=<%=userId%>;userTen=<%=userTen%>"
											target="content">Phòng ban</a>
									</h5> <% } %>
								</li>




								<li>
									<%
									if (mang[3] == 1) {
								%>
									<h5 class="head" id="cdId">
										<a
											href="../../Erp_ChucdanhSvl?userId=<%=userId%>;userTen=<%=userTen%>"
											target="content">Chức danh</a>
									</h5> <% } %>
								</li>



								<%-- <li>
					        <%
					         if (mang[176] == 1) {
					        %>
					        <h5 class="head" id="kbhId">
					         <a href="../../ErpCauhinhSMSSvl?userId=<%=userId%>&userTen=<%=userTen%>" target="content">Tin nhắn</a>
					        </h5> <% } %>
					       </li> --%>

							</ul>
						</li>

						<li>
							<h4 class="head" id="dlnspId">
								<a href="">Kế toán</a>
							</h4>
							<ul style="display: none;">
								<li>
									<%
									if (mang[4] == 1) {
								%>
									<h5 class="head" id="kbhId">
										<a
											href="../../ErpNganHangSvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Ngân hàng</a>
									</h5> <% } %>
								</li>
								<li>
									<%
									if (mang[5] == 1) {
								%>
									<h5 class="head" id="kbhId">
										<a
											href="../../Erp_ChiNhanhSvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Chi nhánh</a>
									</h5> <% } %>
								</li>
								<li>
									<%
									if (mang[7] == 1) {
								%>
									<h5 class="head" id="kbhId">
										<a
											href="../../ErpLoaiTaiKhoanSvl?userId=<%=userId%>;userTen=<%=userTen%>"
											target="content">Loại tài khoản</a>
									</h5> <%} %>
								</li>

								<li>
									<%
									if (mang[7] == 1) {
								%>
									<h5 class="head" id="kbhId">
										<a
											href="../../ErpDoiTuongKyQuySvl?userId=<%=userId%>;userTen=<%=userTen%>"
											target="content">Đối tượng ký quỹ</a>
									</h5> <%} %>
								</li>

								<li>
									<%
									if (mang[8] == 1) {
								%>
									<h5 class="head" id="kbhId">
										<a
											href="../../TaikhoanketoanSvl?userId=<%=userId%>;userTen=<%=userTen%>"
											target="content">Tài khoản kế toán</a>
									</h5> <% } %>
								</li>

								<li>
									<%
									if (mang[8] == 1) {
								%>
									<h5 class="head" id="kbhId">
										<a
											href="../../ErpCauhinhchiphiSvl?userId=<%=userId%>;userTen=<%=userTen%>"
											target="content">Nhóm tài khoản</a>
									</h5> <% } %>
								</li>

								<li>
									<%
									if (mang[9] == 1) {
								%>
									<h5 class="head" id="clId">
										<a href="../../ErpLoaiSanPhamSvl?userId=<%=userId%>"
											target="content">Loại sản phẩm </a>
									</h5> <%}%>
								</li>

								<%-- <li>
								<%
									if (mang[10] == 1) {
								%>
								<h5 class="head" id="dvdlspId">
									<a href="../../MasanphamktSvl?userId=<%=userId%>" target="content">Mã sản phẩm kế toán </a>
								</h5> <% } %>
						    </li> --%>


								<li>
									<%
									if (mang[11] == 1) {
								%>
									<h5 class="head" id="kbhId">
										<a
											href="../../ErpNganHangCongTySvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Tài khoản ngân hàng công ty</a>
									</h5> <% } %>
								</li>



								<li>
									<%
					         if (mang[12] == 1) {
					        %>
									<h5 class="head" id="kbhId">
										<a
											href="../../Erp_TrungTamChiPhiSvl?userId=<%=userId%>;userTen=<%=userTen%>"
											target="content">Trung tâm chi phí</a>
									</h5> <% } %>
								</li>

								<li>
									<%
					         if (mang[13] == 1) {
					        %>
									<h5 class="head" id="kbhId">
										<a
											href="../../Erp_ChiphiSvl?userId=<%=userId%>;userTen=<%=userTen%>"
											target="content">Khoản mục chi phí</a>
									</h5> <% } %>
								</li>

								<li>
									<%
					         if (mang[14] == 1) {
					        %>
									<h5 class="head" id="kbhId">
										<a
											href="../../Erp_TrungTamDoanhThuSvl?userId=<%=userId%>;userTen=<%=userTen%>"
											target="content">Trung tâm doanh thu</a>
									</h5> <% } %>
								</li>

								<li>
									<%
					         if (mang[15] == 1) {
					        %>
									<h5 class="head" id="kbhId">
										<a
											href="../../Erp_MucdoanhthuSvl?userId=<%=userId%>;userTen=<%=userTen%>"
											target="content">Khoản mục doanh thu</a>
									</h5> <% } %>
								</li>

								<li>
									<%
					         if (mang[15] == 1) {
					        %>
									<h5 class="head" id="kbhId">
										<a
											href="../../Erp_KhoanmucchietkhauSvl?userId=<%=userId%>;userTen=<%=userTen%>"
											target="content">Khoản mục chiết khấu</a>
									</h5> <% } %>
								</li>

								<li>
									<%
				              if (mang[16] == 1) {
				             %>
									<h5 class="head" id="kbhId">
										<a
											href="../../NhanvienSvl?userId=<%=userId%>;userTen=<%=userTen%>"
											target="content">Nhân viên công ty</a>
									</h5> <% } %>
								</li>

								<li>
									<h5 class="head" id="dlthId">
										<a
											href="../../Erp_DulieutonghopSvl?userId=<%=userId%>;userTen=<%=userTen%>"
											target="content">Dữ liệu tổng hợp</a>
									</h5>
								</li>


								<li>
									<%
									if (mang[4] == 1) {
								%>
									<h5 class="head" id="kbhId">
										<a
											href="../../ErpCoPhieuSvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Danh mục cổ phiếu</a>
									</h5> <% } %>
								</li>

							</ul>
						</li>

						<li>
							<h4 class="head" id="muahangId">
								<a href="">Mua hàng</a>
							</h4>
							<ul style="display: none;">
								<li>
									<%
									if (mang[17] == 1) {
								%>
									<h5 class="head" id="nccId">
										<a
											href="../../ErpLoaiNhaCungCapSvl?userId=<%=userId%>;userTen=<%=userTen%>"
											target="content">Loại nhà Cung Cấp</a>
									</h5> <% } %>
								</li>

								<li>
									<%
									if (mang[18] == 1) {
								%>
									<h5 class="head" id="nccId">
										<a
											href="../../NhomnhacungcapcnSvl?userId=<%=userId%>;userTen=<%=userTen%>"
											target="content">Nhóm nhà Cung Cấp</a>
									</h5> <% } %>
								</li>

								<li>
									<%
									if (mang[19] == 1) {
								%>
									<h5 class="head" id="nccId">
										<a
											href="../../ErpNhaCungCapSvl?userId=<%=userId%>;userTen=<%=userTen%>"
											target="content">Nhà Cung Cấp</a>
									</h5> <% } %>
								</li>

								<li>
									<%
									if (mang[20] == 1) {
								%>
									<h5 class="head" id="mcpId">
										<a
											href="../../Erp_mucchiphiSvl?userId=<%=userId%>;userTen=<%=userTen%>"
											target="content">Mức chi phí</a>
									</h5> <% } %>
								</li>

								<li>
									<%
									if (mang[21] == 1) {
								%>
									<h5 class="head" id="dmhId">
										<a
											href="../../Erp_ChinhsachduyetmuahangSvl?userId=<%=userId%>;userTen=<%=userTen%>"
											target="content">Chính sách duyệt mua hàng</a>
									</h5> <% } %>
								</li>

								<li>
									<%
									if (mang[22] == 1) {
								%>
									<h5 class="head" id="kbhId">
										<a
											href="../../Erp_TienTeSvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Tiền tệ</a>
									</h5> <% } %>
								</li>
								<li>
									<%
									if (mang[23] == 1) {
								%>
									<h5 class="head" id="kbhId">
										<a
											href="../../TigiaSvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Tỉ giá</a>
									</h5> <% } %>
								</li>

							</ul>
						</li>

						<li>
							<h4 class="head">
								<a href="">Sản xuất</a>
							</h4>
							<ul style="display: none;">

								<li>
									<%
									if (mang[24] == 1) {
								%>
									<h5 class="head">
										<a href="../../ErpNhamaySvl?userId=<%= userId %>"
											target="content">Nhà máy</a>
									</h5> <% }  %>
								</li>

								<li>
									<%
									if (mang[25] == 1) {
								%>
									<h5 class="head">
										<a href="../../ErpBomSvl?userId=<%= userId %>"
											target="content">Danh mục vật tư ( BOM )</a>
									</h5> <% }  %>
								</li>

								<li>
									<%
									if (mang[25] == 1) {
								%>
									<h5 class="head">
										<a href="../../ErpDuyetbomSvl?userId=<%= userId %>"
											target="content">Duyệt BOM</a>
									</h5> <% }  %>
								</li>

								<li>
									<%
									if (mang[26] == 1) {
								%>
									<h5 class="head">
										<a href="../../ErpCongDoanSanXuatGiaySvl?userId=<%=userId%>"
											target="content">Công đoạn sản xuất</a>
									</h5> <%}%>
								</li>

								<li>
									<%
									if (mang[27] == 1) {
								%>
									<h5 class="head">
										<a href="../../ErpKichBanSanXuatGiaySvl?userId=<%=userId%>"
											target="content">Kịch bản sản xuất</a>
									</h5> <%}%>
								</li>

							</ul>
						</li>

						<li>
							<h4 class="head" id="khoId">
								<a href="">Kho vận</a>
							</h4>
							<ul style="display: none;">

								<li>
									<%if (mang[29] == 1) {%>
									<h5 class="head">
										<a
											href="../../ErpKhoTTSvl?userId=<%=userId%>;userTen=<%=userTen%>"
											target="content">Khai báo kho</a>
									</h5> <%}%>
								</li>

								<li>
									<%if (mang[29] == 1) {%>
									<h5 class="head">
										<a
											href="../../ErpKhuVucKhoSvl?userId=<%=userId%>;userTen=<%=userTen%>"
											target="content">Khai báo khu vực kho</a>
									</h5> <%}%>
								</li>

								<%-- <li>
								<%
									if (mang[30] == 1) {
								%>
								<h5 class="head" id="dvdlspId">
									<a href="../../ErpNguongocSvl?userId=<%=userId%>" target="content">Nguồn gốc sản phẩm</a>
								</h5> <% } %>
							</li> --%>

								<%-- <li>
								<%
									if (mang[31] == 1) {
								%>
								<h5 class="head" >
									<a href="../../MausacSvl?userId=<%=userId%>" target="content">Màu sắc</a>
								</h5> <% }  %>
							</li>
							<li>
								<%
									if (mang[181] == 1) {
								%>
								<h5 class="head" >
									<a href="../../MauinSvl?userId=<%=userId%>" target="content">Mẫu in</a>
								</h5> <% }  %>
							</li> --%>
								<li>
									<%
									if (mang[32] == 1) {
								%>
									<h5 class="head" id="dvdlspId">
										<a href="../../DonvidoluongSvl?userId=<%=userId%>"
											target="content">Đơn vị đo lường </a>
									</h5> <% } %>
								</li>

								<li>
									<%
									if (mang[33] == 1) {
								%>

									<h5 class="head" id="ttspId">
										<a href="../../ThongtinsanphamgiaySvl?userId=<%=userId%>"
											target="content">Sản phẩm </a>

									</h5> <% } %>
								</li>

								<li>
									<%
									if (mang[34] == 1) {
								%>
									<h5 class="head" id="nspId">
										<a href="../../NhomsanphamSvl?userId=<%=userId%>"
											target="content">Nhóm sản phẩm</a>
									</h5> <% } %>
								</li>

								<li>
									<!--  xem lai quyen --> <%
									if (mang[33] == 1) {
								%>
									<h5 class="head" id="cbspId">
										<a href="../../CongbosanphamSvl?userId=<%=userId %>"
											target="content">Công bố sản phẩm </a>
									</h5> <%} %>
								</li>

								<li>
									<%
									if (mang[34] == 1) {
								%>
									<h5 class="head" id="nspId">
										<a href="../../ErpTonkhoantoanUpdateSvl?userId=<%=userId%>"
											target="content">Tồn kho an toàn</a>
									</h5> <% } %>
								</li>

							</ul>
						</li>

						<li>
							<h4 class="head">
								<a href="">Kinh doanh </a>
							</h4>
							<ul style="display: none;">

								<li>
									<%
									if (mang[35] == 1) {
								%>
									<h5 class="head">
										<a href="../../DonvikinhdoanhSvl?userId=<%=userId%>"
											target="content">Đơn vị Kinh doanh</a>
									</h5> <% }  %>
								</li>




								<li>
									<%
									if (mang[36] == 1) {
								%>
									<h5 class="head">
										<a
											href="../../KenhbanhangSvl?userId=<%=userId%>;userTen=<%=userTen%>"
											target="content">Kênh bán hàng</a>
									</h5> <% } %>
								</li>

								<li>
									<%
									if (mang[37] == 1) {
								%>

									<h5 class="head">
										<a
											href="../../VungmienSvl?userId=<%=userId%>;userTen=<%=userTen%>"
											target="content">Vùng miền</a>


									</h5> <% } %>
								</li>
								<li>
									<%
									if (mang[38] == 1) {
								%>
									<h5 class="head">
										<a
											href="../../KhuvucSvl?userId=<%=userId%>;userTen=<%=userTen%>"
											target="content">Khu vực</a>
									</h5> <% } %>
								</li>

								<li>
									<%
									if (mang[39] == 1) {
								%>
									<h5 class="head">
										<a
											href="../../NhomkhachhangTTSvl?userId=<%=userId%>;userTen=<%=userTen%>"
											target="content">Nhóm khách hàng</a>
									</h5> <% } %>
								</li>

								<li>
									<%
									if (mang[40] == 1) {
								%>
									<h5 class="head">
										<a
											href="../../LoaikhachhangSvl?userId=<%=userId%>;userTen=<%=userTen%>"
											target="content">Loại khách hàng</a>
									</h5> <% } %>
								</li>

								<li>
									<%
									if (mang[40] == 1) {
								%>
									<h5 class="head">
										<a
											href="../../ErpKhachHangSvl?userId=<%=userId%>;userTen=<%=userTen%>"
											target="content">Khách hàng</a>
									</h5> <% } %>
								</li>

								<li>
									<%
									if (mang[41] == 1) {
								%>
									<h5 class="head">
										<a href="../../ErpBanggiabanGiaySvl?userId=<%=userId%>"
											target="content">Định giá bán</a>
									</h5> <% } %>
								</li>

								<li>
									<%
									if (mang[42] == 1) {
								%>
									<h5 class="head">
										<a href="../../ErpSuagiabanGiaySvl?userId=<%=userId%>"
											target="content">Duyệt giá bán</a>
									</h5> <% } %>
								</li>

								<li>
									<%
									if (mang[43] == 1) {
								%>

									<h5 class="head" id="nhId">
										<a href="../../NhanhangSvl?userId=<%=userId%>"
											target="content">Nhãn hàng</a>
									</h5> <% } %>
								</li>
								<li>
									<%
									if (mang[44] == 1) {
								%>
									<h5 class="head" id="clId">
										<a href="../../ChungloaiSvl?userId=<%=userId%>"
											target="content">Chủng loại</a>
									</h5> <% } %>
								</li>

							</ul>
						</li>

					</ul>
				</li>

			</ul>
		</li>


		<li style="position: static;">
			<h3 class="head">
				<a href="" class="head">Quản lý chỉ tiêu </a>
			</h3>
			<ul style="display: block;">
				<li>
					<ul id="xtraMenu2">

						<li>
							<h4 class="head">
								<a href="">Khai báo </a>
							</h4>
							<ul>
								<%-- <li>
								<%
									if (mang[23] == 1) {
								%>
								<h5 class="head" id="ddkdId">
									<a href="../../chitieuttchovungSvl?userId=<%=userId%>&userTen=<%=userTen%>" target="content">Chỉ tiêu cho khu vực</a>
								</h5> <% } %>
							</li>
							<li>
								<%
									if (mang[24] == 1) {
								%>
								<h5 class="head" id="ddkdId">
									<a href="../../ChiTieuSvl?userId=<%=userId%>&userTen=<%=userTen%>" target="content">Chỉ tiêu cho NPP</a>
								</h5> <% } %>
							</li> --%>

								<li>
									<%
									if (mang[200] == 1) {
								%>
									<h5 class="head" id="ddkdId">
										<a
											href="../../ChitieuthangSvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Chỉ tiêu tháng</a>
									</h5> <% } %>
								</li>

								<li>
									<%
									if (mang[201] == 1) {
								%>
									<h5 class="head" id="ddkdId">
										<a
											href="../../TieuchithuongTLSvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Công thức thưởng</a>
									</h5> <% } %>
								</li>

								<li>
									<%
									if (mang[202] == 1) {
								%>
									<h5 class="head" id="ddkdId">
										<a
											href="../../ChietkhauthangSvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Chiết khấu thương mại</a>
									</h5> <% } %>
								</li>

							</ul>
						</li>


						<li>
							<h4 class="head">
								<a href="">Báo cáo</a>
							</h4>
							<ul style="display: none;">
								<li>
									<%
									if (mang[200] == 1) {
								%>
									<h5 class="head">
										<a href="../../DailySalesSvl?userId=<%=userId%>"
											target="content">Thực đạt & chỉ tiêu NPP</a>
									</h5> <% } %>
								</li>
								<li>
									<%
									if (mang[200] == 1) {
								%>
									<h5 class="head">
										<a href="../../ErpBCThuongKhachHang?userId=<%=userId%>"
											target="content">BC Thưởng khách hàng</a>
									</h5> <% } %>
								</li>

								<li>
									<%
									if (mang[200] == 1) {
								%>
									<h5 class="head">
										<a href="../../ErpBcThuongCKThuongMai?userId=<%=userId%>"
											target="content">Thưởng chiết khấu thương mại</a>
									</h5> <% } %>
								</li>
							</ul>
						</li>

					</ul>
				</li>
			</ul>
		</li>


		<li style="position: static;">
			<h3 class="head">
				<a href="" class="head">Quản lý khuyến mại </a>
			</h3>
			<ul style="display: block;">
				<li>
					<ul id="xtraMenu3">

						<li>
							<h4 class="head">
								<a href="">Khai báo </a>
							</h4>
							<ul style="display: none;">
								<li>
									<%
									if (mang[38] == 1) {
								%>
									<h5 class="head">
										<a href="../../NhomkhuyenmaiSvl?userId=<%=userId%>"
											target="content">Nhóm khuyến mãi</a>
									</h5> <% } %>
								</li>
								<li>
									<%
									if (mang[39] == 1) {
								%>
									<h5 class="head">
										<a href="../../nhomkhachhangkmSvl?userId=<%=userId%>"
											target="content">Nhóm khách hàng</a>
									</h5> <% } %>
								</li>
								<li>
									<%
									if (mang[40] == 1) {
								%>
									<h5 class="head">
										<a href="../../DieukienkhuyenmaiSvl?userId=<%=userId%>"
											target="content">Điều kiện khuyến mãi</a>
									</h5> <% } %>
								</li>
								<li>
									<%
									if (mang[208] == 1) {
								%>
									<h5 class="head">
										<a href="../../TrakhuyenmaiSvl?userId=<%=userId%>"
											target="content">Trả khuyến mãi</a>
									</h5> <% } %>
								</li>
								<li>
									<%
									if (mang[209] == 1) {
								%>
									<h5 class="head">
										<a href="../../CtkhuyenmaiSvl?userId=<%=userId%>"
											target="content">Chương trình khuyến mãi</a>
									</h5> <% } %>
								</li>
								<li>
									<%
									if (mang[43] == 1) {
								%>
									<h5 class="head">
										<a href="../../PhanbokhuyenmaiSvl?userId=<%=userId%>"
											target="content">Phân bổ khuyến mãi</a>
									</h5> <% } %>
								</li>
							</ul>
						</li>

						<li>
							<h4 class="head">
								<a href="">Báo cáo</a>
							</h4>
							<ul style="display: none;">
								<li>
									<%
									if (mang[44] == 1) {
								%>
									<h5 class="head">
										<a href="../../PromotionReportTTSvl?userId=<%=userId%>"
											target="content">Xuất khuyến mãi</a>
									</h5> <% } %>
								</li>
								<li>
									<%
									if (mang[45] == 1) {
								%>
									<h5 class="head">
										<a href="../../AccumulatedPromotion?userId=<%=userId%>"
											target="content">Trả khuyến mãi tích lũy </a>
									</h5> <% } %>
								</li>
							</ul>
						</li>

					</ul>
				</li>
			</ul>
		</li>


		<li style="position: static;">
			<h3 class="head">
				<a href="" class="head">Quản lý cung ứng </a>
			</h3>
			<ul style="display: block;">
				<li>
					<ul id="xtraMenu4">

						<li>
							<h4 class="head">
								<a href="">Lập kế hoạch </a>
							</h4>
							<ul style="display: none;">
								<li>
									<%
                                           if (mang[45] == 1) {
                                     %>
									<h5 class="head">
										<a href="../../DuBao_GiaySvl?userId=<%=userId%>"
											target="content">Dự báo kinh doanh</a>
									</h5> <% } %>
								</li>

								<li>
									<%
                                           if (mang[46] == 1) {
                                     %>
									<h5 class="head" id="bgmnppId">
										<a href="../../YeuCauSvl?userId=<%=userId%>" target="content">Yêu
											cầu cung ứng</a>
									</h5> <% } %>
								</li>

								<li>
									<%
                                           if (mang[47] == 1) {
                                     %>
									<h5 class="head" id="bgmnppId">
										<a href="../../ErpLenhsanxuatdukienSvl?userId=<%=userId%>"
											target="content">Đề nghị sản xuất</a>
									</h5> <%}%>
								</li>

								<li>
									<%
                                           if (mang[48] == 1) {
                                     %>
									<h5 class="head" id="bgmnppId">
										<a href="../../ErpDenghingungsanxuatSvl?userId=<%=userId%>"
											target="content">Đề nghị ngưng sản xuất</a>
									</h5> <%}%>
								</li>
								<li>
									<%
                                           if (mang[51] == 1) {
                                     %>
									<h5 class="head">
										<a href="../../ErpXemkehoachUpdateSvl?userId=<%=userId%>"
											target="content">Kiểm tra kế hoạch</a>
									</h5> <% } %>
								</li>
								<li>
									<%
                                           if (mang[49] == 1) {
                                     %>
									<h5 class="head" id="bgmnppId">
										<a href="../../ErpLenhmuahangdukienSvl?userId=<%=userId%>"
											target="content">Đề nghị mua nguyên liệu</a>
									</h5> <%}%>
								</li>

								<li>
									<%
                                           if (mang[50] == 1) {
                                     %>
									<h5 class="head" id="bgmnppId">
										<a href="../../ErpDenghihuymuaSvl?userId=<%=userId%>"
											target="content">Đề nghị hủy mua</a>
									</h5> <%}%>
								</li>




								<li style="display: none">
									<%
                                           if (mang[52] == 1) {
                                     %>
									<h5 class="head" id="bgmnppId">
										<a href="../../ErpCanhbaothieuhangSvl?userId=<%=userId%>"
											target="content">Cảnh báo thiếu hàng</a>
									</h5> <%}%>
								</li>

								<li>
									<%
                                           if (mang[53] == 1) {
                                     %>
									<h5 class="head" id="bgmnppId">
										<a
											href="../../ErpCanhbaothieunguyenlieuSvl?userId=<%=userId%>"
											target="content">Cảnh báo thiếu nguyên liệu</a>
									</h5> <%}%>
								</li>

								<li>
									<%
                                           if (mang[54] == 1) {
                                     %>
									<h5 class="head" id="bgmnppId">
										<a href="../../ErpKehoachnhanNVLSvl?userId=<%=userId%>"
											target="content">Kế hoạch nhận NVL</a>
									</h5> <%}%>
								</li>

								<li style="display: none">
									<%
                                           if (mang[55] == 1) {
                                     %>
									<h5 class="head" id="bgmnppId">
										<a href="../../ErpKehoachgiaohangSvl?userId=<%=userId%>"
											target="content">Kế hoạch giao hàng</a>
									</h5> <%}%>
								</li>

							</ul>
						</li>

						<li>
							<h4 class="head">
								<a href="">Quản lý mua hàng </a>
							</h4>
							<ul style="display: none;">
								<%-- <li>
								<% if (mang[56] == 1) {%>
								<h5 class="head">
									<a href="../../ErpDenghimuahangList_Svl?userId=<%=userId%>&userTen=<%=userTen%>" target="content">Đề nghị mua hàng </a>
								</h5> <%}%>
							</li> --%>
								<li>
									<% if (mang[57] == 1) {%>
									<h5 class="head">
										<a
											href="../../ErpDonMuaHangSvl_Mi?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Đơn mua hàng Mi </a>
									</h5> <%}%>
								</li>
								<li>
									<% if (mang[57] == 1) {%>
									<h5 class="head">
										<a
											href="../../ErpYeuCauChuyenKhoSvl_Mi?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Yêu cầu chuyển kho Mi </a>
									</h5> <%}%>
								</li>

								<li>
									<% if (mang[57] == 1) {%>
									<h5 class="head">
										<a
											href="../../ErpReportFromStoreProcSvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content"> Báo cáo Training </a>
									</h5> <%}%>
								</li>

								<li>
									<% if (mang[57] == 1) {%>
									<h5 class="head">
										<a
											href="../../ErpDonMuaHangSvl_Sang?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Đơn mua hàng Sang </a>
									</h5> <%}%>
								</li>

								<li>
									<% if (mang[57] == 1) {%>
									<h5 class="head">
										<a
											href="../../ErpDonmuahangList_GiaySvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Đơn mua hàng </a>
									</h5> <%}%>
								</li>

								<li>
									<% if (mang[58] == 1) {%>
									<h5 class="head">
										<a
											href="../../Erp_DuyetmuahangSvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Duyệt đơn mua hàng </a>
									</h5> <%}%>
								</li>

								<li>
									<% if (mang[59] == 1) {%>
									<h5 class="head">
										<a
											href="../../ErpNhanhang_GiaySvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Nhận hàng </a>
									</h5> <%}%>
								</li>

								<li>
									<% if (mang[99] == 1) {%>
									<h5 class="head">
										<a
											href="../../ErpKiemdinhchatluong_NhGiaySvl?userId=<%=userId%>"
											target="content">Kiểm định chất lượng </a>
									</h5> <%}%>
								</li>

								<li>
									<% if (mang[60] == 1) {%>
									<h5 class="head">
										<a
											href="../../ErpCongtytrahangSvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Đơn trả hàng NCC</a>
									</h5> <%}%>
								</li>

								<li>
									<% if (mang[61] == 1) {%>
									<h5 class="head">
										<a
											href="../../ErpPhieuxuatkhoTSCDSvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Xuất trả TSCĐ</a>
									</h5> <%}%>
								</li>
								<li>
									<% if (mang[186] == 1) {%>
									<h5 class="head">
										<a
											href="../../ErpPhieuThanhToanSvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Đề nghị thanh toán </a>
									</h5> <%}%>
								</li>
								<li>
									<% if (mang[187] == 1) {%>
									<h5 class="head">
										<a
											href="../../Erp_DuyetthanhtoanSvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Duyệt đề nghị thanh toán </a>
									</h5> <%}%>
								</li>

								<li>
									<% if (mang[62] == 1) {%>
									<h5 class="head">
										<a
											href="../../ErpHuychungtuMhSvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Hủy chứng từ </a>
									</h5> <%}%>
								</li>

								<%-- <li>
								<% if (mang[62] == 1) {%>
								<h5 class="head">
									<a href="../../ErpHuychungtudenghithanhtoanSvl?userId=<%=userId%>&userTen=<%=userTen%>" target="content">Hủy đề nghị thanh toán </a>
								</h5> <%}%>
							</li> --%>

								<li>
									<% if (mang[63] == 1) { %>
									<h5 class="head">
										<a
											href="../../ErpBCDonmuahangSvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Theo dõi đơn mua</a>
									</h5> <% } %>
								</li>

								<li>
									<% if (mang[64] == 1) { %>
									<h5 class="head">
										<a
											href="../../Erp_TheoDoiGiaMuaNCC?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Theo dõi giá mua</a>
									</h5> <% } %>
								</li>

								<%-- <li>
								<% if (mang[62] == 1) {%>
								<h5 class="head">
									<a href="../../ErpHuychungtudenghithanhtoanSvl?userId=<%=userId%>&userTen=<%=userTen%>" target="content">Hủy đề nghị thanh toán</a>
								</h5> <%}%>
							</li> --%>

							</ul>
						</li>

						<li>
							<h4 class="head">
								<a href="">Quản lý bán hàng</a>
							</h4>
							<ul style="display: none;">

								<div style="overflow: auto; max-height: 300px; max-width: 82%;">
									<li>
										<h5 class="head">

											<a href="../../SanphamListSvl_kiet05?userId=<%=userId%>"
												target="content">Sản Phẩm (Kiệt)</a>

										</h5>
									</li>

									<li>
										<h5 class="head">

											<a href="../../SanPhamListSvl_kiet06?userId=<%=userId%>"
												target="content">Sản Phẩm (Kiệt 06)</a>

										</h5>
									</li>

									<li>
										<h5 class="head">

											<a href="../../DonHangListSvl_Kiet02?userId=<%=userId%>"
												target="content">Đơn Hàng (Kiệt)</a>

										</h5>
									</li>

									<li>
										<h5 class="head">

											<a href="../../ChuyenKhoListSvl_kiet02?userId=<%=userId%>"
												target="content">Chuyển Kho (Kiệt 02)</a>

										</h5>
									</li>

									<li>
										<h5 class="head">

											<a href="../../SanPham_HangSvl?userId=<%=userId%>"
												target="content">Sản Phẩm Hằng</a>

										</h5>
									</li>
									<li>
										<h5 class="head">

											<a href="../../DonBanHang_HangSvl?userId=<%=userId%>"
												target="content">Đơn bán hàng Hằng</a>

										</h5>
									</li>
									<li>
										<h5 class="head">

											<a href="../../SanPhamList_TrangSvl?userId=<%=userId%>"
												target="content">Sản Phẩm_Trang</a>

										</h5>
									</li>
									<li>
										<h5 class="head">

											<a href="../../DonBanHang_TrangSvl?userId=<%=userId%>"
												target="content">Đơn bán hàng_Trang</a>

										</h5>
									</li>
									<li>
										<h5 class="head">

											<a href="../../DonbanhangListSvl_cuong?userId=<%=userId%>"
												target="content">Đơn bán hàng_cuong</a>

										</h5>
									</li>
									<li>
										<h5 class="head">

											<a href="../../DonHangListSvl_Vy?userId=<%=userId%>"
												target="content">Đơn hàng (Vy)</a>

										</h5>
									</li>



									<li>
										<h5 class="head">

											<a href="../../DonBanHang_ThuSvl?userId=<%=userId%>"
												target="content">Đơn hàng (Thư)</a>

										</h5>
									</li>
									<li>
										<h5 class="head">

											<a href="../../SanPhamSvl_Thu?userId=<%=userId%>"
												target="content">Sản Phẩm (Thư)</a>

										</h5>
									</li>


									<li>
										<h5 class="head">

											<a href="../../DonBanHang_TrinhSvl?userId=<%=userId%>"
												target="content">Đơn bán hàng (Trinh)</a>

										</h5>
									</li>
									<li>

										<h5 class="head">

											<a href="../../SanPham_TrinhSvl?userId=<%=userId%>"
												target="content">Sản phẩm (Trinh)</a>

										</h5>
									</li>
									<li>
										<h5 class="head">

											<a href="../../DonhangListSvl?userId=<%=userId%>"
												target="content">Đơn Ban Hang Huy</a>

										</h5>
									</li>
									<li>
										<h5 class="head">

											<a href="../../MuaHangSvl?userId=<%=userId%>"
												target="content">Đơn Mua Hang Huy</a>

										</h5>
									</li>
									<li>
										<h5 class="head">

											<a href="../../DonbanhangListSvl_thai?userId=<%=userId%>"
												target="content">Đơn bán hàng - Thái</a>

										</h5>
									</li>

									<li>
										<h5 class="head">

											<a href="../../SanphamNhom2ListSvl?userId=<%=userId%>"
												target="content">Bài mẫu nhóm 2</a>

										</h5>
									</li>
									<li>
										<h5 class="head">

											<a href="../../DonbanhangListThoSvl?userId=<%=userId%>"
												target="content">Đơn Hàng Thọ</a>

										</h5>
									</li>
									<li>
										<h5 class="head">

											<a href="../../SanPhamDuongNguyenListSvl?userId=<%=userId%>"
												target="content">Sản Phẩm DuongNguyen</a>

										</h5>
									</li>
									<li>
										<h5 class="head">

											<a href="../../DonHangDuongNguyenListSvl?userId=<%=userId%>"
												target="content">Đơn Hàng DuongNguyen</a>

										</h5>
									</li>


									<li>
										<h5 class="head">

											<a href="../../KhachhangSvl?userId=<%=userId%>"
												target="content">Khách hàng</a>

										</h5>
									</li>
									<li>
										<h5 class="head">

											<a href="../../DonbanhangListSvl?userId=<%=userId%>"
												target="content">Đơn bán hàng -Hiếu</a>

										</h5>
									</li>
									<li>
										<h5 class="head">

											<a href="../../DonmuahangListSvl?userId=<%=userId%>"
												target="content">Đơn mua hàng -Hiếu</a>

										</h5>
									</li>

									<li>
										<h5 class="head">

											<a href="../../SanphamListSvl?userId=<%=userId%>"
												target="content">Sản phẩm</a>

										</h5>
									</li>
									<li>
										<h5 class="head">

											<a href="../../DonhangListSvl_tan?userId=<%=userId%>"
												target="content">Đơn Bán Hàng_Tân</a>

										</h5>
									</li>
									<li>
										<h5 class="head">

											<a href="../../DonmuahangListSvl_tan?userId=<%=userId%>"
												target="content">TÂN - ĐƠN MUA HÀNG</a>

										</h5>
									</li>
									<li>
										<h5 class="head">

											<a href="../../ChuyenkhoSvl_tan?userId=<%=userId%>"
												target="content">TÂN - CHUYỂN KHO</a>

										</h5>
									</li>

									<li>
										<h5 class="head">

											<a href="../../NhacungcapSvl?userId=<%=userId%>"
												target="content">Nhà cung cấp</a>

										</h5>
									</li>
									<li>
										<h5 class="head">

											<a href="../../BanhangSvl?userId=<%=userId%>"
												target="content">Đơn hàng bán</a>

										</h5>
									</li>
									<%-- <li>
								<h5 class="head" >
									<%
										if (mang[67] == 1) {
									%>
									<a href="../../Erp_DonbanhangKmSvl?userId=<%=userId%>" target="content">Đơn hàng khuyến mại</a>
									<% } %>
								</h5></li> --%>

									<li>
										<h5 class="head">
											<%
										if (mang[69] == 1) {
									%>
											<a href="../../Erp_DuyetDonBanHangSvl?userId=<%=userId%>"
												target="content">Duyệt đơn hàng </a>
											<% } %>
										</h5>
									</li>

									<li>
										<h5 class="head">
											<%
										if (mang[188] == 1) {
									%>
											<a href="../../Erp_DuyetDonBanHangGDSvl?userId=<%=userId%>"
												target="content">GD Duyệt đơn hàng </a>
											<% } %>
										</h5>
									</li>

									<li>
										<h5 class="head">
											<%
										if (mang[70] == 1) {
									%>
											<a href="../../Erp_dontrahangSvl?userId=<%=userId%>"
												target="content">Đơn trả hàng </a>
											<% } %>
										</h5>
									</li>
									<li>
										<% if (mang[71] == 1) {%>
										<h5 class="head">
											<a
												href="../../ErpNhanhang_DonTHSvl?userId=<%=userId%>&userTen=<%=userTen%>"
												target="content">Nhận hàng </a>
										</h5> <%}%>
									</li>
									<li>
										<%
									if (mang[72] == 1) {
								%>
										<h5 class="head">
											<a
												href="../../ErpPhieuxuatkhoSvl?userId=<%=userId%>&userTen=<%=userTen%>"
												target="content">Xuất kho </a>
										</h5> <% } %>
									</li>
									<%-- 							<li>
								<%
								if (mang[73] == 1)  {
								%>
								<h5 class="head">
									<a href="../../ErpHoaDonSvl?userId=<%=userId%>&userTen=<%=userTen%>" target="content">Xuất hóa đơn tài chính </a>
								</h5> <% } %>
							</li> --%>

									<li>
										<%
								if (mang[73] == 1)  {
								%>
										<h5 class="head">
											<a
												href="../../ErpHoaDonTaiChinhSvl?userId=<%=userId%>&userTen=<%=userTen%>"
												target="content">Xuất HĐTC </a>
										</h5> <% } %>
									</li>

									<li>
										<%
								if (mang[73] == 1)  {
								%>
										<h5 class="head">
											<a
												href="../../ErpHoaDonTaiChinhKMSvl?userId=<%=userId%>&userTen=<%=userTen%>"
												target="content">Xuất HĐKM </a>
										</h5> <% } %>
									</li>

									<li>
										<%
									if (mang[143] == 1) {
								%>
										<h5 class="head">
											<a
												href="../../ErpHoadonphelieuSvl?userId=<%=userId%>&userTen=<%=userTen%>"
												target="content">Xuất HĐ khác</a>
										</h5> <% } %>
									</li>

									<li>
										<%if(mang[74]==1){ %>
										<h5 class="head">
											<a
												href="../../ErpHoaDonTraveNccSvl?userId=<%=userId %>&userTen=<%= userTen %>"
												target="content">Xuất hóa đơn trả về NCC </a>
										</h5> <%} %>
									</li>

									<li>
										<%
								if (mang[75] == 1)  {
								%>
										<h5 class="head">
											<a
												href="../../ErpHdTraHangSvl?userId=<%=userId%>&userTen=<%=userTen%>"
												target="content">Hóa đơn hàng trả về</a>
										</h5> <% } %>
									</li>

									<li>
										<%if(mang[185]==1){ %>
										<h5 class="head">
											<a
												href="../../HuyhoadontaichinhSvl?userId=<%=userId %>&userTen=<%= userTen %>"
												target="content">Hủy hóa đơn tài chính </a>
										</h5> <%} %>
									</li>

									<li>
									<li>
										<%
									if (mang[76] == 1) {
								%>
										<h5 class="head">
											<a
												href="../../ErpHuychungtuBhSvl?userId=<%=userId%>&userTen=<%=userTen%>"
												target="content">Hủy chứng từ </a>
										</h5> <% } %>
									</li>


									<li>
										<%
									if (mang[77] == 1) {
								%>
										<h5 class="head" id="kbkId">
											<a href="../../Erp_DonHangBanTrongKy?userId=<%=userId%>"
												target="content">Đơn hàng trong kỳ</a>
										</h5> <% } %>
									</li>

									<li>
										<%
									if (mang[78] == 1) {
								%>
										<h5 class="head" id="kbkId">
											<a href="../../DailyPrimarySales?userId=<%=userId%>"
												target="content">Báo cáo doanh số(HĐ)</a>
										</h5> <% } %>
									</li>

									<li>
										<%
									if (mang[184] == 1) {
								%>
										<h5 class="head" id="kbkId">
											<a href="../../BcdoanhthuspmSvl?userId=<%=userId%>"
												target="content">BC Doanh thu (Canfoco)</a>
										</h5> <% } %>
									</li>

									<li>
										<%
									if (mang[223] == 1) {
								%>
										<h5 class="head" id="kbkId">
											<a href="../../BCbanhangTHSvl?userId=<%=userId%>"
												target="content">BC bán hàng tổng hợp</a>
										</h5> <% } %>
									</li>

									<li>
										<%
									if (mang[227] == 1) {
								%>
										<h5 class="head" id="kbkId">
											<a href="../../BCbanhangkhachhangSvl?userId=<%=userId%>"
												target="content">BC bán hàng theo khách hàng</a>
										</h5> <% } %>
									</li>


									<li>
										<%
									if (mang[225] == 1) {
								%>
										<h5 class="head" id="kbkId">
											<a href="../../BCgiaohangSvl?userId=<%=userId%>"
												target="content">BC Giao hàng theo HĐ</a>
										</h5> <% } %>
									</li>


									<li>
										<%
									if (mang[79] == 1) {
								%>
										<h5 class="head" id="kbkId">
											<a href="../../Erp_TheoDoiGiaBanKH?userId=<%=userId%>"
												target="content">Báo cáo theo dõi giá</a>
										</h5> <% } %>
									</li>


									<%-- <li>
								<%
									if (mang[80] == 1) {
								%>
									<h5 class="head" id="kbkId">
										<a href="../../ErpTheoDoiHopDong?userId=<%=userId%>" target="content">Báo cáo theo dõi hợp đồng</a>
									</h5> <% } %>
							</li> --%>
								</div>
							</ul>
						</li>


						<li>
							<h4 class="head">
								<a href="">Sản xuất</a>
							</h4>
							<ul style="display: none;">
								<div style="overflow: auto; max-height: 300px; max-width: 95%;">
									<li>
										<%
									if (mang[81] == 1) {
								%>
										<h5 class="head" id="bgmnppId">
											<a href="../../ErpLenhsanxuatgiaySvl?userId=<%=userId%>"
												target="content">Lệnh sản xuất</a>
										</h5> <%}%>
									</li>
									<%--  <li>
								<% if (mang[82] == 1) {%>
								<h5 class="head" >
									<a href="../../ErpPhieuyeucauSvl?userId=<%=userId%>&task=0" target="content">Yêu cầu nguyên liệu</a>
								</h5> <%}%>
							</li>
							<li>
								<%
									if (mang[83] == 1) {
								%>
								<h5 class="head" >
									<a href="../../ErpYeucaunguyenlieugiaySvl" target="content">Nhận nguyên liệu</a>
								</h5> <% }  %>
							</li> --%>

									<li>
										<%
									if (mang[83] == 1) {
								%>
										<h5 class="head">
											<a
												href="../../ErpChuyenkhoSXSvl?userId=<%=userId%>&task=LSX&isnhanHang=1"
												target="content">Nhận hàng</a>
										</h5> <% }  %>
									</li>
									<li>
										<% if (mang[85] == 1) {%>
										<h5 class="head">
											<a href="../../ErpNhapkhoLsxSvl?userId=<%=userId%>"
												target="content">Nhập kho </a>
										</h5> <%}%>
									</li>

									<li>
										<% if (mang[86] == 1) {%>
										<h5 class="head">
											<a href="../../ErpKiemdinhchatluongSvl?userId=<%=userId%>"
												target="content">Kiểm định chất lượng </a>
										</h5> <%}%>
									</li>

									<li>
										<%
									if (mang[84] == 1) {
								%>
										<h5 class="head">
											<a href="../../ErpTieuhaonguyenlieugiaySvl" target="content">Tiêu
												hao nguyên liệu</a>
										</h5> <% }  %>
									</li>



									<li>
										<%
									if (mang[85] == 1) {
								%>
										<h5 class="head">
											<a href="../../PhanBoLoHoiSvl?userId=<%=userId%>"
												target="content">Phân bổ lò hơi</a>
										</h5> <%}%>
									</li>


									<li>
										<% if (mang[179] == 1) {%>
										<h5 class="head">
											<a href="../../ErpHuylenhsanxuatSvl?userId=<%=userId%>"
												target="content">Hủy chứng từ </a>
										</h5> <%}%>
									</li>


									<li>
										<%
                                       if (mang[88] == 1) {
                                 %>
										<h5 class="head">
											<a href="../../ErpTinhGiaDongLucListSvl" target="content">Tính
												giá động lực</a>
										</h5> <% }  %>
									</li>
									<li>
										<%
                                       if (mang[88] == 1) {
                                 %>
										<h5 class="head">
											<a href="../../ErpGiabaoquanSvl" target="content">Giá bảo
												quản/ cấp đông</a>
										</h5> <% }  %>
									</li>

									<li>
										<%
                                       if (mang[88] == 1) {
                                 %>
										<h5 class="head">
											<a href="../../ErpTinhGiaThanhSvl" target="content">Tính
												giá thành</a>
										</h5> <% }  %>
									</li>

									<li>
										<%
									if (mang[89] == 1) {
								%>
										<h5 class="head">
											<a href="../../BosanphamSvl?userId=<%=userId%>"
												target="content">Bó sản phẩm</a>
										</h5> <%}%>
									</li>

									<%--  <li>
								<%
									if (mang[90] == 1) {
								%>
								<h5 class="head" >
									<a href="../../Erp_Chitietnguyenvatlieu?userId=<%=userId%>" target="content">Chi tiết nguyên vật liệu</a>
								</h5> <%}%>
							</li> --%>
									<li>
										<% 	if (mang[91] == 1) { %>
										<h5 class="head">
											<a href="../../BCTieuHaoNL?userId=<%=userId%>"
												target="content">Báo cáo tiêu hao NL</a>
										</h5> <% } %>
									</li>
								</div>
							</ul>
						</li>

						<li>
							<h4 class="head">
								<a href="">Quản lý tồn kho</a>
							</h4>
							<ul style="display: none;">

								<div style="overflow: auto; max-height: 300px; max-width: 95%;">
									<li>
										<h5 class="head">

											<a href="../../ChuyenKhoListSvl?userId=<%=userId%>"
												target="content">Chuyen Kho Huy</a>

										</h5>
									</li>
									<li>
									<li>
										<h5 class="head">

											<a href="../../HoSoListSvl?userId=<%=userId%>"
												target="content">Thông tin hồ sơ</a>

										</h5>
									</li>
									<li>
										<h5 class="head">

											<a href="../../HoSoTuVanListSvl?userId=<%=userId%>"
												target="content">Thông tin tư vấn</a>

										</h5>
									</li>
									<li>
										<h5 class="head">

											<a href="../../ErpBCTuvanduhocSvl?userId=<%=userId%>"
												target="content">Báo cáo 7</a>

										</h5>
									</li>
									<li>
										<% if (mang[92] == 1) {%>
										<h5 class="head">
											<a
												href="../../ErpYeuCauChuyenKhoSvl?userId=<%=userId%>&task=LSX"
												target="content">Phiếu yêu cầu</a>
										</h5> <%}%>
									</li>
									<li>
										<h5 class="head">

											<a href="../../ChuyenkhoListSvl_hieu?userId=<%=userId%>"
												target="content">Chuyển kho_hiếu</a>

										</h5>
									<li>
										<% 	if (mang[91] == 1) { %>
										<h5 class="head">
											<a href="../../BCTieuHaoNL?userId=<%=userId%>"
												target="content">Báo cáo tiêu hao NL</a>
										</h5> <% } %>
									</li>
								</div>
							</ul>
						</li>

						<li>
							<h4 class="head">
								<a href="">Quản lý tồn kho</a>
							</h4>
							<ul style="display: none;">

								<div style="overflow: auto; max-height: 300px; max-width: 95%;">
									<li>
										<h5 class="head">

											<a href="../../ChuyenKhoListSvl?userId=<%=userId%>"
												target="content">Chuyen Kho Huy</a>

										</h5>
									</li>
									<li>
									<li>
										<h5 class="head">

											<a href="../../HocSinhListSvl?userId=<%=userId%>"
												target="content">Thong tin hoc sinh</a>

										</h5>
									</li>
									<li>
										<% if (mang[92] == 1) {%>
										<h5 class="head">
											<a
												href="../../ErpYeuCauChuyenKhoSvl?userId=<%=userId%>&task=LSX"
												target="content">Phiếu yêu cầu</a>
										</h5> <%}%>
									</li>
									<li>
										<h5 class="head">

											<a href="../../ChuyenkhoListSvl_hieu?userId=<%=userId%>"
												target="content">Chuyển kho_hiếu</a>

										</h5>
									<li>
										<% 	if (mang[91] == 1) { %>
										<h5 class="head">
											<a href="../../BCTieuHaoNL?userId=<%=userId%>"
												target="content">Báo cáo tiêu hao NL</a>
										</h5> <% } %>
									</li>
								</div>
							</ul>
						</li>

						<li>
							<h4 class="head">
								<a href="">Quản lý tồn kho</a>
							</h4>
							<ul style="display: none;">

								<div style="overflow: auto; max-height: 300px; max-width: 95%;">
									<li>

										<h5 class="head">
											<a href="../../BCDoanhSoTheoTinhSvl?userId=<%=userId%>"
												target="content">Báo cáo doanh số theo tỉnh - Thái</a>

										</h5>

										<h5 class="head">

											<a
												href="../../TuvanthongtinhopdongdakyListSvl_hieu?userId=<%=userId%>"
												target="content">Bước 4-tư vấn hợp đồng đã ký</a>

										</h5>

										<h5 class="head">

											<a
												href="../../TienhanhhopdongdakyListSvl_hieu?userId=<%=userId%>"
												target="content">Bước 5-Tiến hành hợp đồng đã ký</a>

										</h5>

									</li>
									<li>
										<% if (mang[93] == 1) {%>
										<h5 class="head">
											<a href="../../ErpChuyenkhoSXSvl?userId=<%=userId%>&task=LSX"
												target="content">Chuyển nguyên liệu</a>
										</h5> <%}%>
									</li>
									<li>
										<% if (mang[177] == 1) {%>
										<h5 class="head">
											<a
												href="../../ErpYeuCauChuyenKhoSvl?userId=<%=userId%>&task=chuyenNL"
												target="content">Yêu cầu chuyển kho</a>
											<%--  <a href="../../ErpYeuCauChuyenKhoSvl?userId=<%=userId%>&task=LSX" target="content">Yêu cầu chuyển kho</a> --%>
										</h5> <%}%>
									</li>
									<li>
										<%

									
							 {%>
										<h5 class="head">
											<a href="../../ErpChuyenkhoSXSvl?userId=<%=userId%>&task=LSX"
												target="content">Chuyển nguyên liệu</a>
										</h5> <%}%>
									</li>
									<li>
										<% if (mang[177] == 1) {%>
										<h5 class="head">
											<a
												href="../../ErpYeuCauChuyenKhoSvl?userId=<%=userId%>&task=chuyenNL"
												target="content">Yêu cầu chuyển kho</a>
											<%--  <a href="../../ErpYeuCauChuyenKhoSvl?userId=<%=userId%>&task=LSX" target="content">Yêu cầu chuyển kho</a> --%>
										</h5> <%}%>
									</li>

									<li>
										<h5 class="head">

											<a href="../../HocSinhListSvl?userId=<%=userId%>"
												target="content">Thong tin hoc sinh</a>

										</h5>
									</li>

									<li>
										<h5 class="head">

											<a href="../../ChuyenkhoListSvl_hieu?userId=<%=userId%>"
												target="content">Chuyển kho_hiếu</a>

										</h5>
										<h5 class="head">
											<a href="../../HopdongSvl_tan?userId=<%=userId%>"
												target="content">Bước 3</a>
										</h5>
										<h5 class="head">

											<a
												href="../../DinhhuongthitruongListSvl_hieu?userId=<%=userId%>"
												target="content">Định hướng thị trường</a>

										</h5>

										<h5 class="head">

											<a href="../../ErpBCHangxuatSvl?userId=<%=userId%>"
												target="content">Xuất báo cáo</a>

										</h5>
									</li>
									<li>
										<% if (mang[93] == 1) {%>
										<h5 class="head">
											<a href="../../ErpChuyenkhoSXSvl?userId=<%=userId%>&task=LSX"
												target="content">Chuyển nguyên liệu</a>
										</h5> <%}%>
									</li>
									<li>
										<% if (mang[177] == 1) {%>
										<h5 class="head">
											<a
												href="../../ErpYeuCauChuyenKhoSvl?userId=<%=userId%>&task=chuyenNL"
												target="content">Yêu cầu chuyển kho</a>
											<%--  <a href="../../ErpYeuCauChuyenKhoSvl?userId=<%=userId%>&task=LSX" target="content">Yêu cầu chuyển kho</a> --%>
										</h5> <%}%>
									</li>
									<li>
										<%

									if (mang[94] == 1) {
								%>
										<h5 class="head">
											<a
												href="../../ErpChuyenkhoSXSvl?userId=<%=userId%>&task=chuyenNL"
												target="content">Chuyển kho</a>
										</h5> <% }  %>
									</li>

									<%-- 	<li>
								<%
									if (mang[94] == 1) {
								%>
								<h5 class="head" >
									<a href="../../ChuyenKhuVucKhoSvl?userId=<%=userId%>" target="content">Chuyển khu vực kho</a>
								</h5> 
								<% }  %>
							</li> --%>

									<li>
										<%
									if (mang[97] == 1) {
								%>
										<h5 class="head">
											<a
												href="../../ErpChuyenkhoSXSvl?userId=<%=userId%>&task=chuyenNL&isnhanHang=1"
												target="content">Nhận hàng</a>
										</h5> <% }  %>
									</li>

									<li>
										<%
									if (mang[98] == 1) {
								%>
										<h5 class="head">
											<a
												href="../../ErpChuyenkhoSXSvl?userId=<%=userId%>&task=chuyenNL&isnhanHang=2"
												target="content">Xuất kho</a>
										</h5> <% }  %>
									</li>
									<li>
										<% if (mang[180] == 1) {%>
										<h5 class="head">
											<a href="../../ErpHuyChuyenKhoSvl?userId=<%=userId%>"
												target="content">Hủy chứng từ </a>
										</h5> <%}%>
									</li>

									<li>
										<%
									if (mang[95] == 1) {
								%>
										<h5 class="head">
											<a href="../../ErpKiemKhoSvl?userId=<%=userId%>"
												target="content">Kiểm kho</a>
										</h5> <% }  %>
									</li>

									<li>
										<%
									if (mang[96] == 1) {
								%>
										<h5 class="head">
											<a href="../../ErpKiemKhoExportSvl?userId=<%=userId%>"
												target="content">In bảng kê kho</a>
										</h5> <% }  %>
									</li>

									<li>
										<%
									if (mang[96] == 1) {
								%>
										<h5 class="head">
											<a
												href="../../ErpKiemdinhchatluong_khoSvl?userId=<%=userId%>"
												target="content">Kiểm định chất lượng</a>
										</h5> <% }  %>
									</li>


									<%-- 	
							<li>
								<% if (mang[99] == 1) {%>
								<h5 class="head" >
									<a href="../../ErpKiemdinhchatluong_NhGiaySvl?userId=<%=userId%>" target="content">Kiểm định chất lượng </a>
								</h5> <%}%>
							</li> --%>
									<li>
										<% if (mang[100] == 1) { %>
										<h5 class="head">
											<a href="../../DoiquycachSvl" target="content">Đổi quy
												cách</a>
										</h5> <%} %>
									</li>

									<li>
										<% if (mang[101] == 1) { %>
										<h5 class="head">
											<a href="../../ErpDieuChinhTonKhoTTSvl?userId=<%=userId%>"
												target="content">Điều chỉnh tồn kho TT </a>
										</h5> <% } %>
									</li>

									<li>
										<% if (mang[101] == 1) { %>
										<h5 class="head">
											<a href="../../ErpDieuChinhSoLoKhoTTSvl?userId=<%=userId%>"
												target="content">Điều chỉnh số lô </a>
										</h5> <% } %>
									</li>

									<li>
										<% if (mang[102] == 1) { %>
										<h5 class="head">
											<a href="../../ErpCapnhatgianhapkhoSvl?userId=<%=userId%>"
												target="content">Cập nhật giá nhập kho</a>
										</h5> <% } %>
									</li>
									<li>
										<% if (mang[103] == 1) { %>
										<h5 class="head">
											<a href="../../ErpTinhgiaxuatSvl?userId=<%=userId%>"
												target="content">Tính giá xuất kho</a>
										</h5> <% } %>
									</li>
									<li>
										<% if (mang[104] == 1) { %>
										<h5 class="head">
											<a href="../../ErpKhoasothangSvl?userId=<%=userId%>"
												target="content">Khóa sổ tháng</a>
										</h5> <% } %>
									</li>
								</div>
							</ul>
						</li>

						<li>
							<h4 class="head">
								<a href="">Báo cáo</a>
							</h4>
							<ul style="display: none;">


								<!-- báo cáo thư -->
								<li>
									<h5 class="head" id="tkttId">
										<a href="../../BaoCao1_ThưSvl?userId=<%=userId%>"
											target="content">Thư Báo Cáo 1</a>
									</h5>
								</li>



								<li>
									<% if (mang[105] == 1) { %>
									<h5 class="head" id="tkttId">
										<a href="../../ErpTonkhotrungtamSvl?userId=<%=userId%>"
											target="content">Tồn kho trung tâm</a>
									</h5> <% } %>
								</li>

								<li>
									<% if (mang[106] == 1) { %>
									<h5 class="head" id="tkttId">
										<a href="../../ErpXuatnhaptonTTSvl?userId=<%=userId%>"
											target="content">Xuất nhập tồn TT</a>
									</h5> <% } %>
								</li>
								<li>
									<% if (mang[234] == 1) { %>
									<h5 class="head" id="tkttId">
										<a href="../../ErpReportFromStoreProcSvl?userId=<%=userId%>"
											target="content">Tuổi hàng tồn kho</a>
									</h5> <% } %>
								</li>

								<li>
									<% if (mang[107] == 1) { %>
									<h5 class="head">
										<a
											href="../../ErpBCThekhoSvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Thẻ kho</a>
									</h5> <% } %>
								</li>

								<li>
									<% if (mang[109] == 1) {%>
									<h5 class="head" id="tkttId">
										<a href="../../ErpBCTheodoihangnhapSvl?userId=<%=userId%>"
											target="content">Theo dõi hàng nhập</a>
									</h5> <%}%>
								</li>

								<li>
									<% if (mang[226] == 1) { %>
									<h5 class="head">
										<a href="../../ErpBCHansudungSvl?userId=<%=userId%>"
											target="content">BC Hạn sử dụng</a>
									</h5> <% } %>
								</li>


								<li>
									<% if (mang[108] == 1) { %>
									<h5 class="head" id="tkttId">
										<a href="../../ErpBcLichSuGdKhoSvl?userId=<%=userId%>"
											target="content">Lịch sử giao dịch kho </a>
									</h5> <% } %>
								</li>
								<li>
									<% if (mang[109] == 1) {%>
									<h5 class="head" id="tkttId">
										<a href="../../ErpBCHangnhapSvl?userId=<%=userId%>"
											target="content">Hàng nhập kho</a>
									</h5> <%}%>
								</li>

								<li>
									<% if (mang[110] == 1) {%>
									<h5 class="head" id="tkttId">
										<a href="../../ErpBCHangxuatSvl?userId=<%=userId%>"
											target="content">Hàng xuất kho</a>
									</h5> <%}%>
								</li>

								<li style="display: none">
									<% if (mang[111] == 1) {%>
									<h5 class="head" id="tkttId">
										<a href="../../ErpBCHansudungSvl?userId=<%=userId%>"
											target="content">Hạn sử dụng</a>
									</h5> <%}%>
								</li>

								<li style="display: none">
									<% if (mang[112] == 1) {%>
									<h5 class="head" id="tkttId">
										<a href="../../ErpBCHangluanchuyenSvl?userId=<%=userId%>"
											target="content">Hàng chậm luân chuyển</a>
									</h5> <%}%>
								</li>
								<li style="display: none">
									<% 	if (mang[113] == 1) { %>
									<h5 class="head" id="kbkId">
										<a href="../../StockAlarm?userId=<%=userId%>" target="content">Cảnh
											báo hàng tồn kho</a>
									</h5> <% } %>
								</li>

								<li>
									<% if (mang[114] == 1) { %>
									<h5 class="head" id="tkttId">
										<a href="../../ErpXuatnhaptonTTTHSvl?userId=<%=userId%>"
											target="content">Xuất nhập tồn TT tổng hợp</a>
									</h5> <% } %>
								</li>

								<li>
							</ul>
						</li>

					</ul>
				</li>
			</ul>
		</li>


		<li style="position: static;">
			<h3 class="head">
				<a href="" class="head">Quản lý công nợ </a>
			</h3>
			<ul style="display: block;">
				<li>
					<ul id="xtraMenu5">

						<li>
							<h4 class="head">
								<a href="">Công nợ phải thu </a>
							</h4>
							<ul style="display: none;">
								<li>
									<%
									if (mang[114] == 1) {
								%>
									<h5 class="head">
										<a
											href="../../ErpThutienSvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Thu tiền </a>
									</h5> <% } %>
								</li>

								<li>
									<%
									if (mang[228] == 1) {
								%>
									<h5 class="head">
										<a
											href="../../ErpDuyetthanhtoanhoadonSvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Duyệt Phiếu thu</a>
									</h5> <% } %>
								</li>

								<li>
									<%
									if (mang[115] == 1) {
								%>
									<h5 class="head">
										<a
											href="../../ErpXoakhachhangttSvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Xóa nợ khách hàng</a>
									</h5> <% } %>
								</li>


								<li>
									<%
                                        if (mang[191] == 1) {
                                  %>
									<h5 class="head">
										<a href="../../ButrucongnoSvl?userId=<%=userId%>"
											target="content">Bù trừ công nợ</a>
									</h5> <% } %>
								</li>

								<li>
									<%
									if (mang[217] == 1) {
								%>
									<h5 class="head">
										<a
											href="../../ErpHuythutienSvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Hủy chứng từ</a>
									</h5> <% } %>
								</li>

							</ul>
						</li>

						<li>
							<h4 class="head">
								<a href="">Công nợ phải trả </a>
							</h4>
							<ul style="display: none;">
								<li>
									<%
									if (mang[116] == 1) {
								%>
									<h5 class="head">
										<a href="../../ErpTamUngSvl?userId=<%=userId%>"
											target="content">Đề nghị tạm ứng</a>
									</h5> <% } %>
								</li>

								<li>
									<%
                                        if (mang[117] == 1) {
                                  %>
									<h5 class="head">
										<a href="../../ErpCanTruCongNoSvl?userId=<%=userId%>"
											target="content">Cấn trừ công nợ</a>
									</h5> <% } %>
								</li>



								<li>
									<%
									if (mang[118] == 1) {
								%>
									<h5 class="head">
										<a href="../../ErpChiphinhapkhauSvl?userId=<%=userId%>"
											target="content">Chi phí nhận hàng</a>
									</h5> <% } %>
								</li>

								<li>
									<%
                                        if (mang[119] == 1) {
                                  %>
									<h5 class="head">
										<a href="../../ErpThuenhapkhauSvl?userId=<%=userId%>"
											target="content">Thuế nhập khẩu</a>
									</h5> <% } %>
								</li>

								<li>
									<%
                                        if (mang[120] == 1) {
                                  %>
									<h5 class="head">
										<a href="../../ErpChiphikhacSvl?userId=<%=userId%>"
											target="content">Chi phí khác</a>
									</h5> <% } %>
								</li>

								<li>
									<%
									if (mang[121] == 1) {
								%>
									<h5 class="head">
										<a href="../../ErpParkHoadonSvl?userId=<%=userId%>"
											target="content">Hóa đơn NCC</a>
									</h5> <% } %>
								</li>
								<%-- <li>
								<%
									if (mang[122] == 1) {
								%>
								<h5 class="head">
									<a href="../../ErpParkHoadonduyetSvl?userId=<%=userId%>" target="content">Duyệt hóa đơn NCC</a>
								</h5> <% } %>
							</li> --%>

								<%-- 	<li>
								<%
									if (mang[229] == 1) {
								%>
								<h5 class="head">
									<a href="../../ErpDenghithanhtoanNCCSvl?userId=<%=userId%>" target="content">Đề nghị thanh toán NCC</a>
								</h5> <% } %>
							</li> --%>

								<li>
									<%
									if (mang[230] == 1) {
								%>
									<h5 class="head">
										<a href="../../Erp_GanmachiphiSvl?userId=<%=userId%>"
											target="content">Gán mã chi phí</a>
									</h5> <% } %>
								</li>

								<li>
									<%
									if (mang[231] == 1) {
								%>
									<h5 class="head">
										<a href="../../ErpDenghithanhtoanNCCSvl?userId=<%=userId%>"
											target="content">Đề nghị thanh toán NCC</a>
									</h5> <% } %>
								</li>

								<li>
									<%
									if (mang[123] == 1) {
								%>
									<h5 class="head">
										<a
											href="../../ErpThanhtoanhoadonSvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Phiếu chi</a>
									</h5> <% } %>
								</li>

								<li>
									<%
									if (mang[190] == 1) {
								%>
									<h5 class="head">
										<a
											href="../../ErpUynhiemchiSvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Ủy nhiệm chi</a>
									</h5> <% } %>
								</li>



								<li>
									<%
									if (mang[124] == 1) {
								%>
									<h5 class="head">
										<a
											href="../../ErpXoaNoNCCSvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Xóa nợ NCC</a>
									</h5> <% } %>
								</li>

								<li>
									<%
									if (mang[218] == 1) {
								%>
									<h5 class="head">
										<a
											href="../../ErpHuyphieuchiSvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Hủy chứng từ</a>
									</h5> <% } %>
								</li>



							</ul>
						</li>

					</ul>
				</li>
			</ul>
		</li>

		<li style="position: static;">
			<h3 class="head">
				<a href="" class="head">Quản lý tài sản </a>
			</h3>
			<ul style="display: block;">
				<li>
					<ul>

						<li>
							<%
							if (mang[125] == 1) {
						%>
							<h5 class="head" id="kbhId">
								<a
									href="../../ErpCongDungSvl?userId=<%=userId%>&userTen=<%=userTen%>"
									target="content">Công dụng tài sản</a>
							</h5> <% } %>
						</li>

						<li>
							<%
							if (mang[126] == 1) {
						%>
							<h5 class="head" id="kbhId">
								<a
									href="../../Erp_LoaiTaiSanSvl?userId=<%=userId%>;userTen=<%=userTen%>"
									target="content">Loại tài sản</a>
							</h5> <% } %>
						</li>

						<li>
							<%
							if (mang[127] == 1) {
						%>
							<h5 class="head" id="kbhId">
								<a
									href="../../Erp_NhomTaiSanSvl?userId=<%=userId%>;userTen=<%=userTen%>"
									target="content">Nhóm tài sản</a>
							</h5> <% } %>
						</li>
						<li>
							<%
							if (mang[128] == 1) {
						%>
							<h5 class="head" id="kbhId">
								<a
									href="../../Erp_TaiSanCoDinhSvl?userId=<%=userId%>;userTen=<%=userTen%>"
									target="content">Tài sản </a>
							</h5> <% } %>
						</li>

						<li>
							<%
                                   if (mang[129] == 1) {
                          %>
							<h5 class="head">
								<a
									href="../../ErpKhauhaotaisanSvl?userId=<%=userId%>&userTen=<%=userTen%>"
									target="content">Khấu hao tài sản</a>
							</h5> <% } %>
						</li>

						<li>
							<%                      
                                if (mang[130] == 1) {
                          %>
							<h5 class="head">
								<a
									href="../../ErpThanhlytaisanSvl?userId=<%=userId%>&userTen=<%=userTen%>"
									target="content">Thanh lý tài sản</a>
							</h5> <% } %>
						</li>

						<li>
							<%                      
                                if (mang[131] == 1) {
                          %>
							<h5 class="head">
								<a
									href="../../ThetaisancodinhSvl?userId=<%=userId%>&userTen=<%=userTen%>"
									target="content">Thẻ tài sản</a>
							</h5> <% } %>
						</li>

						<li>
							<%                      
                                if (mang[132] == 1) {
                          %>
							<h5 class="head">
								<a
									href="../../TheodoitaisancodinhSvl?userId=<%=userId%>&userTen=<%=userTen%>"
									target="content">Theo dõi tài sản</a>
							</h5> <% } %>
						</li>

						<li>
							<%                      
                                if (mang[132] == 1) {
                          %>
							<h5 class="head">
								<a
									href="../../BangketaisanSvl?userId=<%=userId%>&userTen=<%=userTen%>"
									target="content">Bảng kê tài sản</a>
							</h5> <% } %>
						</li>
						<li>
							<%
                                if (mang[132] == 1) {
                          %>
							<h5 class="head">
								<a
									href="../../ErpBCTaiSanCoDinhTongHopSvl?userId=<%=userId%>&userTen=<%=userTen%>"
									target="content">BC Tài sản cố định tổng hợp</a>
							</h5> <% } %>
						</li>
					</ul> <!-- <ul id="xtraMenu10">
					<li>
		                  <h4 class="head" > <a href="">Báo cáo </a> </h4>
		                  <ul style="display: none;">
		                  
		                                                                
		                  </ul>
		            </li>
            	</ul> -->
			</ul>
		</li>

		<li style="position: static;">
			<h3 class="head">
				<a href="" class="head">Quản lý công cụ dụng cụ </a>
			</h3>
			<ul style="display: block;">
				<li>
					<ul>
						<li>
							<%
	       if (mang[133] == 1) {
	      %>
							<h5 class="head" id="kbhId">
								<a
									href="../../ErpCongDungCCDCSvl?userId=<%=userId%>&userTen=<%=userTen%>"
									target="content">Công dụng</a>
							</h5> <% } %>
						</li>

						<li>
							<%
	       if (mang[134] == 1) {
	      %>
							<h5 class="head" id="kbhId">
								<a
									href="../../Erp_LoaiCCDCSvl?userId=<%=userId%>;userTen=<%=userTen%>"
									target="content">Loại công cụ dụng cụ</a>
							</h5> <% } %>
						</li>

						<li>
							<%
	       if (mang[135] == 1) {
	      %>
							<h5 class="head" id="kbhId">
								<a
									href="../../Erp_NhomCCDCSvl?userId=<%=userId%>;userTen=<%=userTen%>"
									target="content">Nhóm công cụ dụng cụ</a>
							</h5> <% } %>
						</li>


						<li>
							<%
	       if (mang[136] == 1) {
	      %>
							<h5 class="head" id="kbhId">
								<a
									href="../../Erp_CongCuDungCuSvl?userId=<%=userId%>;userTen=<%=userTen%>"
									target="content">Công cụ dụng cụ </a>
							</h5> <% } %>
						</li>

						<li>
							<%
                if (mang[137] == 1) {
               %>
							<h5 class="head">
								<a
									href="../../ErpKhauhaocongcudungcuSvl?userId=<%=userId%>&userTen=<%=userTen%>"
									target="content">Khấu hao </a>
							</h5> <% } %>
						</li>
					</ul>
				</li>
			</ul>
		</li>

		<li style="position: static;">
			<h3 class="head">
				<a href="" class="head">Quản lý kế toán </a>
			</h3>
			<ul style="display: block;">
				<li>
					<ul>
						<li>
							<%                      
                                if (mang[138] == 1) {
                          %>
							<h5 class="head">
								<a
									href="../../HopdongvaySvl?userId=<%=userId%>&userTen=<%=userTen%>"
									target="content">Hợp đồng vay vốn</a>
							</h5> <% } %>
						</li>

						<li>
							<%                      
                                if (mang[139] == 1) {
                          %>
							<h5 class="head">
								<a
									href="../../NhantienvaySvl?userId=<%=userId%>&userTen=<%=userTen%>"
									target="content">Giải ngân</a>
							</h5> <% } %>
						</li>

						<li>
							<%                      
                                if (mang[140] == 1) {
                          %>
							<h5 class="head">
								<a
									href="../../ThanhtoannovaySvl?userId=<%=userId%>&userTen=<%=userTen%>"
									target="content">Thanh toán nợ vay</a>
							</h5> <% } %>
						</li>
						<li>
							<%                      
                                if (mang[183] == 1) {
                          %>
							<h5 class="head">
								<a
									href="../../ErpBCVayTienSvl?userId=<%=userId%>&userTen=<%=userTen%>"
									target="content">Báo cáo theo dõi vay tiền</a>
							</h5> <% } %>
						</li>


						<li>
							<%
							if (mang[141] == 1) {
						%>
							<h5 class="head">
								<a
									href="../../ErpGiamgiahangmuaSvl?userId=<%=userId%>&userTen=<%=userTen%>"
									target="content">Giảm giá hàng mua</a>
							</h5> <% } %>
						</li>
						<%-- 	<li>
						<%
							if (mang[142] == 1) {
						%>
						<h5 class="head">
							<a href="../../ErpGiamgiahangbanSvl?userId=<%=userId%>&userTen=<%=userTen%>" target="content">Giảm/Tăng giá hàng bán </a>
						</h5> <% } %>
					</li> --%>

						<li>
							<%
							if (mang[144] == 1) {
						%>
							<h5 class="head">
								<a
									href="../../ErpButToanTongHopSvl?userId=<%=userId%>&userTen=<%=userTen%>"
									target="content">Bút toán tổng hợp</a>
							</h5> <% } %>
						</li>
						<li>
							<%
                                if (mang[145] == 1) {
                          %>
							<h5 class="head">
								<a
									href="../../Erp_danhgiatigiaSvl?userId=<%=userId%>&userTen=<%=userTen%>"
									target="content">Đánh giá tỉ giá</a>
							</h5> <% } %>
						</li>
						<li>
							<%
                                if (mang[178] == 1) {
                          %>
							<h5 class="head">
								<a
									href="../../ErpDieuchuyentienSvl?userId=<%=userId%>&userTen=<%=userTen%>"
									target="content">Điều chuyển tiền</a>
							</h5> <% } %>
						</li>

						<li>
							<%
							if (mang[146] == 1) {
						%>
							<h5 class="head">
								<a href="../../ErpKhoasothangSvl?userId=<%=userId%>&task=kskt"
									target="content">Khóa sổ kế toán</a>
							</h5> <% } %>
						</li>
					</ul>
				</li>
			</ul>
		</li>

		<li style="position: static;">
			<h3 class="head">
				<a href="" class="head">Quản lý ngân sách </a>
			</h3>
			<ul style="display: block;">
				<li>
					<ul id="xtraMenu6">

						<li>
							<h4 class="head">
								<a href="">Dữ liệu nền </a>
							</h4>
							<ul style="display: none;">
								<li>
									<%
                           if (mang[147] == 1) {
                     %>
									<h5 class="head">
										<a
											href="../../ErpLNSBanggiabanSvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Bảng giá bán</a>
									</h5> <% } %>
								</li>

								<li>
									<%
                           if (mang[148] == 1) {
                     %>
									<h5 class="head">
										<a
											href="../../ErpLNSBanggianguyenlieuSvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Bảng giá nguyên liệu</a>
									</h5> <% } %>
								</li>
							</ul>
							<h4 class="head">
								<a href="">Lập ngân sách </a>
							</h4>
							<ul style="display: none;">
								<li>
									<%
                         if (mang[149] == 1) {
                   %>
									<h5 class="head">
										<a
											href="../../KhoitaongansachSvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Khởi tạo ngân sách </a>
									</h5> <% } %>
								</li>

								<li>
									<%
                         if (mang[150] == 1) {
                   %>
									<h5 class="head">
										<a
											href="../../DubaoNam_GiaySvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Dự báo kinh doanh</a>
									</h5> <% } %>
								</li>

								<li>
									<%
                         if (mang[151] == 1) {
                   %>
									<h5 class="head">
										<a
											href="../../LapngansachCPUpdateSvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Dự toán chi phí</a>
									</h5> <% } %>
								</li>


								<li>
									<%
                         if (mang[152] == 1) {
                   %>
									<h5 class="head">
										<a
											href="../../LapngansachTSUpdateSvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Dự toán tài sản</a>
									</h5> <% } %>
								</li>

								<li>
									<%
                         if (mang[153] == 1) {
                   %>
									<h5 class="head">
										<a
											href="../../LapngansachCDvaKHTSUpdateSvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Khấu hao và phân bổ</a>
									</h5> <% } %>
								</li>

								<li>
									<%
                         if (mang[154] == 1) {
                   %>
									<h5 class="head">
										<a
											href="../../LapngansachDTKUpdateSvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Dự toán doanh thu khác</a>
									</h5> <% } %>
								</li>


							</ul>
						</li>

						<li>
							<h4 class="head">
								<a href="">Theo dõi ngân sách </a>
							</h4>
							<ul style="display: none;">
								<li>
									<%
                             if (mang[155] == 1) {
                       %>
									<h5 class="head">
										<a
											href="../../DuyetngansachSvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Duyệt ngân sách</a>
									</h5> <% } %>
								</li>
								<li>
									<%
		             if (mang[156] == 1) { %>
									<h5 class="head">
										<a
											href="../../BCTheoDoiNganSachChiPhi?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Theo dõi chi phí</a>
									</h5> <% } %>
								</li>

								<li>
									<%
		             if (mang[157] == 1) { %>
									<h5 class="head">
										<a
											href="../../TheodoitrungtamchiphiSvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Theo dõi TTCP</a>
									</h5> <% } %>
								</li>


								<li>
									<%
                    if (mang[158] == 1) { %>
									<h5 class="head">
										<a
											href="../../TheoDoiNganSachLaiLo?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Theo dõi lãi lỗ</a>
									</h5> <% } %>
								</li>
							</ul>
						</li>

					</ul>
				</li>
			</ul>
		</li>

		<li style="position: static;">
			<h3 class="head">
				<a href="" class="head">Báo cáo quản trị </a>
			</h3>
			<ul style="display: block;">
				<li>
					<ul id="xtraMenu7">

						<li>
							<h4 class="head">
								<a href="">Công nợ </a>
							</h4>
							<ul style="display: none;">
								<li>
									<%
 								 if (mang[159] == 1) {
                                      %>
									<h5 class="head">
										<a
											href="../../ErpBCCongNoTongHopNCC?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">BC Công nợ tổng hợp NCC</a>
									</h5> <% } %>
								</li>
								<li>
									<%
                                            if (mang[160] == 1) {
                                      %>
									<h5 class="head">
										<a
											href="../../ErpBCChiTietCongNoNCC?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">BC Công nợ chi tiết NCC</a>
									</h5> <% } %>
								</li>
								<li>
									<%
                                            if (mang[161] == 1) {
                                      %>
									<h5 class="head">
										<a
											href="../../ErpBCCongNoTongHopKH?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Công nợ tổng hợp KH</a>
									</h5> <% } %>
								</li>
								<li>
									<%
                                            if (mang[162] == 1) {
                                      %>
									<h5 class="head">
										<a
											href="../../ErpBCChiTietCongNoKH?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Công nợ chi tiết KH</a>
									</h5> <% } %>
								</li>

								<%-- <li>
								<%
									if (mang[218] == 1) {
								%>
								<h5 class="head">
									<a href="../../ErpBCTheodoitamungNV?userId=<%=userId%>&userTen=<%=userTen%>" target="content">Theo dõi tạm ứng NV</a>
								</h5> <% } %>
							</li> --%>
								<li>
								<li>
									<%
                                            if (mang[163] == 1) {
                                      %>
									<h5 class="head">
										<a
											href="../../BCTongHopTuoiNo_MucHanNo?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Tuổi nợ</a>
									</h5> <% } %>
								</li>
								<li>
									<%
									if (mang[235] == 1) {
								%>
									<h5 class="head">
										<a
											href="../../ErpBCTheodoitamungNV?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Theo dõi tạm ứng NV</a>
									</h5> <% } %>
								</li>

								<li>
									<%
									if (mang[233] == 1) {
								%>
									<h5 class="head" id="kbkId">
										<a href="../../BctinhhinhsudunghdSvl?userId=<%=userId%>"
											target="content">Báo cáo tình hình sử dụng HĐ</a>
									</h5> <% } %>
								</li>

							</ul>
						</li>

						<li>
							<h4 class="head">
								<a href="">Kế toán - Tài chính </a>
							</h4>
							<ul style="display: none;">

								<li>
									<%
									if (mang[164] == 1) {
								%>
									<h5 class="head">
										<a
											href="../../BCChiTietTKKeToan?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Tổng hợp tài khoản</a>
									</h5> <% } %>
								</li>
								<li>
									<%
									if (mang[165] == 1) {
								%>
									<h5 class="head">
										<a
											href="../../BoBaoCaoTongHopSvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Bộ báo cáo tài chính</a>
									</h5> <% } %>
								</li>

								<li>
									<%
									if (mang[166] == 1) {
								%>
									<h5 class="head">
										<a
											href="../../BangcandoiphatsinhSvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Cân đối phát sinh</a>
									</h5> <% } %>
								</li>

								<li>
									<%
									if (mang[167] == 1) {
								%>
									<h5 class="head">
										<a
											href="../../NhatkytaikhoanSvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Nhật ký tài khoản</a>
									</h5> <% } %>
								</li>
								<li>
									<%
									if (mang[168] == 1) {
								%>
									<h5 class="head">
										<a
											href="../../BangchitiettaikhoanSvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Chi tiết tài khoản</a>
									</h5> <% } %>
								</li>

								<li>
									<%
									if (mang[169] == 1) {
								%>
									<h5 class="head">
										<a
											href="../../SoquytienmatSvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Sổ quỹ tiền mặt</a>
									</h5> <% } %>
								</li>

								<li>
									<%
									if (mang[170] == 1) {
								%>
									<h5 class="head">
										<a
											href="../../SotienguinganhangSvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Sổ tiền gửi ngân hàng</a>
									</h5> <% } %>
								</li>
								<li>
									<%
									if (mang[170] == 1) {
								%>
									<h5 class="head">
										<a
											href="../../BCChartSvl?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Báo cáo Chart</a>
									</h5> <% } %>
								</li>


							</ul>
						</li>

						<li>
							<h4 class="head">
								<a href="">Thuế </a>
							</h4>
							<ul style="display: none;">

								<li>
									<%
									if (mang[171] == 1) {
								%>
									<h5 class="head">
										<a
											href="../../BcBangKeMuaVao?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Hóa đơn mua vào</a>
									</h5> <% } %>
								</li>
								<li>
									<%
									if (mang[172] == 1) {
								%>
									<h5 class="head">
										<a
											href="../../BcBangKeBanRa?userId=<%=userId%>&userTen=<%=userTen%>"
											target="content">Hóa đơn bán ra</a>
									</h5> <% } %>
								</li>
							</ul>
						</li>

					</ul>
				</li>
			</ul>
		</li>

		<li style="position: static;">
			<h3 class="head">
				<a href="" class="head">Quản trị hệ thống</a>
			</h3>
			<ul style="display: block;">
				<li>
					<ul>

						<li>
							<%
							if (mang[173] == 1 || userId.equals("system")) {
						%>
							<h5 class="head">
								<a href="../../DanhmucquyenSvl?userId=<%=userId%>"
									target="content">Cập nhật quyền</a>
							</h5> <% } %>
						</li>

						<li>
							<%
							if (mang[174] == 1 || userId.equals("system")) {
						%>
							<h5 class="head" id="kbkId">
								<a href="../../CapnhatnhanvienSvl?userId=<%=userId%>"
									target="content">Cập nhật nhân viên</a>
							</h5> <% } %>
						</li>

						<li>
							<%
							if (mang[174] == 1 || userId.equals("system")) {
						%>
							<h5 class="head" id="kbkId">
								<a href="../../UploadPhanQuyenNvSvl?userId=<%=userId%>"
									target="content">UpLoad phân quyền nhân viên</a>
							</h5> <% } %>
						</li>

						<li>
							<%
							if (mang[175] == 1 || userId.equals("system")) {
						%>
							<h5 class="head" id="kbkId">
								<a href="../../ErpCauHinhInHoaDonSvl?userId=<%=userId%>"
									target="content">Cấu hình in hóa đơn</a>
							</h5> <% } %>
						</li>

						<li>
							<%
							if (mang[175] == 1 || userId.equals("system")) {
						%>
							<h5 class="head" id="kbkId">
								<a href="../../KhoasodonhangSvl?userId=<%=userId%>"
									target="content">Khóa ngày đơn hàng</a>
							</h5> <% } %>
						</li>

					</ul>
				</li>
			</ul>
		</li>



	</ul>

</body>
</html>
<%
	}
%>
