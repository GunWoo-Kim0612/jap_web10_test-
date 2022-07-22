<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>삼품 관리</title>
<link rel="stylesheet" type="text/css" href="css/shopping.css">
</head>
<body>
<!-- 상품목록에서 삭제 >> ProductDeleteServlet에서 do 실행 (해당 code로 조회) >> 현재 페이지( ProductDelete.jsp)  -->
<div id="wrap" align="center">
	<h1>상품 삭제 - 관리자 페이지</h1>
	<!-- submit 이후 ProductDeleteServlet이동 post > 삭제메소드 이후 페이지이동(productList.do) -->
	<form action="productDelete.do" method="post">
		<table>
			<tr>
				<td>
					<c:choose>
						<c:when test="${empty product.pictureUrl }">
							<img src="upload/noimage.gif">
						</c:when>
						<c:otherwise>
							<img alt="" src="upload/${product.pictureUrl }">
						</c:otherwise>
					</c:choose>
				</td>
				<td>
				<table>
					<tr>
						<th style="width: 80px">상 품 명</th>
						<td>${product.name }</td>
					</tr>
					<tr>
						<th> 가  격 </th>
						<td> ${product.price }</td>
					</tr>
					<tr>
						<th> 설  명 </th>
						<td> 
							<div style="height: 220px; width: 100%">
							${product.description }
							</div>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		<input type="hidden" name="code" value="${product.code }">
		<input type="submit" value="삭제">
		<input type="button" value="목록" onclick="location.href='productList.do'"> 
	</form>
</div>
</body>
</html>