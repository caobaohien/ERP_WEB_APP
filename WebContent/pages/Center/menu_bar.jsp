<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String userId = (String) session.getAttribute("userId");
	String userTen = (String) session.getAttribute("userTen");
	%>
<!doctype html>
<html lang=''>
<head>
<meta charset='utf-8'>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="styles.css">
<script src="http://code.jquery.com/jquery-latest.min.js"
	type="text/javascript"></script>
<!-- <script src="../scripts/jquery-latest.min.js" type="text/javascript"></script> -->

<script src="../scripts/script.js"></script>
<title>CSS MenuMaker</title>

</head>
<body>

	<div id='cssmenu'>
		<ul>
			<li><a
				href="../../BanhangSvl?userId=<%=userId%>&userTen=<%=userTen%>"
				target="content">Bảng kê bán hàng </a></li>
			<li><a
				href="../../BanhangSvl?userId=<%=userId%>&userTen=<%=userTen%>&action=taomoi"
				target="content"
				style="height: 40px; padding-top: 10px; width: 30px; padding-left: 0px">
					<IMG src="../images/add32.png" alt="Hiển thị" title="Hiển thị"
					border=0 style="padding: 0; height: 30px">
			</a></li>

			<li><a
				href="../../MuahangSvl?userId=<%=userId%>&userTen=<%=userTen%>"
				target="content">Bảng kê mua hàng </a></li>
			<li><a
				href="../../MuahangSvl?userId=<%=userId%>&userTen=<%=userTen%>&action=taomoi"
				target="content"
				style="height: 40px; padding-top: 10px; width: 30px; padding-left: 0px">
					<IMG src="../images/add32.png" alt="Hiển thị" title="Hiển thị"
					border=0 style="padding: 0; height: 30px">
			</a></li>

			<li><a href="../../ Svl?userId=<%=userId%>&userTen=<%=userTen%>"
				target="content">Danh mục sản phẩm</a></li>
			<li><a
				href="../../KhachhangSvl?userId=<%=userId%>&userTen=<%=userTen%>"
				target="content">Khách hàng </a></li>

			<li><a href="../../ Svl?userId=<%=userId%>&userTen=<%=userTen%>"
				target="content">Nhà cung cấp </a></li>

			<li class='active has-sub'><a href='#'><span>Products</span></a>
				<ul>
					<li class='has-sub'><a href='#'><span>Product 1</span></a>
						<ul>
							<li><a href='#'><span>Sub Product</span></a></li>
							<li class='last'><a href='#'><span>Sub Product</span></a></li>
						</ul></li>
					<li class='has-sub'><a href='#'><span>Product 2</span></a>
						<ul>
							<li><a href='#'><span>Sub Product</span></a></li>
							<li class='last'><a href='#'><span>Sub Product</span></a></li>
						</ul></li>
				</ul></li>
			<li><a href='#'><span>About</span></a></li>
			<li class='last'><a href='#'><span>Contact</span></a></li>
			<li class='last'><a href="../../LogoutSvl" target="_parent"><span>LOGOUT</span></a></li>
		</ul>
	</div>

</body>