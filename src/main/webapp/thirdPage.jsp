<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>delete product</title>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/product1.css">
<script type="text/javascript">
	function fnPay() {
		alert("결제 기능을 지원하지 않습니다.");
	}

	function fnGo() {
		location.href = "main.do";
	}
</script>


</head>
<body>
	<div id="root">
		<link rel="stylesheet" href="./css/NewFile2.css">
		</head>
		<body>
			<%@ include file="html/header1.jsp"%>
			<%@ include file="html/header2.jsp"%>
			<br>
			<br>
			<br>
			<br>
			<div>
				<ul class="best_product">
					<c:forEach var="product" items="${productList}">
						<li><a href="thirdPage.jsp"> <img
								src="upload/${product.pictureurl}" width="280px" height="340px">
						</a>
							<div>
								품명 : ${product.name} <br> 가격 : ${product.price} 원 <br>
								<p onclick="fnCart()">
									<a class="btn btn-primary"
										href="cartDetail.do?code=${product.code} ">장바구니 담기</a>
								</p>
							</div></li>
					</c:forEach>
				</ul>
			</div>
			&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;



			<section id="container">

				<div align="center">
					<h2>상품상세</h2>
					<div id="container_box">
						<form action="productDetailMain.do" method="post">
							<table border="1">
								<tr>
									<td align="center"><c:choose>
											<c:when test="${empty product.pictureurl}}">
												<img src="images/2.jpg">
											</c:when>
											<c:otherwise>
												<img src="upload/${product.pictureurl}">
											</c:otherwise>
										</c:choose></td>
									<td>
										<table border="1">
											<tr>
												<th style="width: 100px">상품명</th>
												<td style="width: 200px" align="center">${product.name}</td>
											</tr>
											<tr>
												<th>가 격</th>
												<td align="center">${product.price}</td>
											</tr>
											<tr>
												<th>설 명</th>
												<td align="center">${product.description}</td>
											</tr>
											<tr>
												<th>등록 일자</th>
												<td align="center">${product.reg_date}</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>


							<div>
								<p onclick="fnCart()">
									<a class="btn btn-primary"
										href="cartDetail.do?code=${product.code} ">장바구니 담기</a>
								</p>
							</div>
							<div class="aa" style="text-align: center;">
								<br> <input type="button" value="상품 주문하기" onclick="fnPay()">

								<input type="button" value="계속 쇼핑하기" onclick="fnGo()">
							</div>
							<br>

						</form>
			</section>
	</div>

	<%@ include file="html/footer.jsp"%>
</body>
</html>