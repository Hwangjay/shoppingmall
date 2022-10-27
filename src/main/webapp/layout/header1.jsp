<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/header1.css">
<style>
ul li {
	list-style-type: none;
	float: left;
	text-align: center;
}
</style>
<title>header1</title>
</head>

<body link="black" vlink="black" alink="black">
	<div class="topmenu">
		&emsp;&emsp;&emsp; <a href="thirdPage.jsp"> 로그인 </a>&emsp; <a
			href="thirdPage.jsp"> 회원가입 </a>&emsp; <a href="thirdPage.jsp">
			장바구니 </a>&emsp; <a href="thirdPage.jsp"> 마이페이지 </a>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
		<a href="thirdPage.jsp"> 공지사항 </a>&emsp; <a href="thirdPage.jsp">
			Q&A </a>&emsp; <i class="fas fa-search">
			<div class="search">
				<form action="searchProduct.do">
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
		</i>
	</div>
	<hr>
	<h1>
		<center>
			<a href="../main.jsp">6조샵</a>
		</center>
	</h1>
	<hr>
</body>
</html>