<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello World</h1>
	<h1>BYE!</h1>
	<table>
		<tr>
			<td>ID</td>
			<td>TITLE</td>
			<td>CONTENT</td>
			<td>WRITER</td>
		</tr>
		<c:forEach items="${list}" var="item">
			<tr>
				<td>${item.bId}</td>
				<td>${item.bTitle}</td>
				<td>${item.bContent}</td>
				<td>${item.bWriter}</td>
			</tr>
		
		</c:forEach>
	</table>
</body>
</html>