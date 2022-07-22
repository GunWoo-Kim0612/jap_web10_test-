<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 관리</title>
<link rel="stylesheet" type="text/css" href="css/shopping.css">
</head>
<body>
	<div id="wrap" align="center">
		<h1>상품 리스트 - 관리자 페이지</h1>
		<table>
			<tr>
				<td colspan="5" style="border: white; text-align: right;">
				<a href="productWrite.do">상품 등록</a>
			</tr>
			
			<tr><th>번호</th><th>이름</th><th>가격</th><th>수정</th><th>삭제</th></tr>
			
			<!-- ProductListServlet에서 setAttribute (내장객체) -->
			<c:forEach var="product" items="${productList }">
				<tr class="record">
					<td>${product.code }</td>
					<td>${product.name }</td>
					<td>${product.price }</td>
					<td><a href="productUpdate.do?code=${product.code }">상품수정</a>
					<td><a href="productDelete.do?code=${product.code }">상품삭제</a>
			</c:forEach>
		</table>
	</div>
</body>
</html>