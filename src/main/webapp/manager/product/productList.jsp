<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Admin new product</title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/product1.css">
</head>
<body>
	<div id="root">
		<header id="header">
			<div id="header_box">
				<%@ include file="/../layout/managerHeader.jsp"%>
			</div>
		</header>

		<nav id="nav">
			<div id="nav_box">
				<%@ include file="/../layout/nav.jsp"%>
			</div>
		</nav>

		<section id="container">
			<aside>
				<%@ include file="/../layout/aside.jsp"%>
			</aside>

			<div align="center">
				<h2>상품 리스트</h2>
				<a href="writeProduct.do">상품 등록</a>

				<%-- 	${productList} --%>
				<table border="1">
					<tr>
						<th>코드</th>
						<th>이름</th>
						<th>가격</th>
						<th>등록일자</th>
						<th>상세</th>
						<th>수정</th>
						<th>삭제</th>
					</tr>
					<c:forEach var="product" items="${productList}">
						<tr align="center">
							<td>${product.code}</td>
							<td>${product.name}</td>
							<td>${product.price}</td>
							<td>${product.reg_date}</td>
							<td><a href="productDetail.do?code=${product.code}">상품상세</a></td>
							<td><a href="updateProduct.do?code=${product.code}">상품수정</a></td>
							<td><a href="deleteProduct.do?code=${product.code}">상품삭제</a></td>
						</tr>
					</c:forEach>
				</table>
				<div class="search">
					<form action="searchProductM.do">
						<select name="column">
							<option ${(param.column=="code")?"selected":""} value="code">상품코드</option>
							<option ${(param.column=="name")?"selected":""} value="name">이름</option>
							<option ${(param.column=="price")?"selected":""} value="price">가격</option>
							<option ${(param.column=="reg_date")?"selected":""}
								value="reg_date">등록날짜</option>
						</select> <input type="text" name="keyword" value="${param.keyword}">
						<input type="submit" value="검색">
					</form>
				</div>
				<%-- ${message} --%>
			</div>
		</section>
	</div>
</body>
</html>