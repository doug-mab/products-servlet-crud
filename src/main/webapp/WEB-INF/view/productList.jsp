<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Products List</title>
</head>
<body>
	<h3>Logged User: ${loggedUser.getUsername()}</h3>
	<c:url value="logout" var="logout" />
	<a href="${logout}">Logout</a>
	<h1>Product List:</h1>
	<ul>
		<c:forEach items="${products}" var="product">
			<c:url value="/product" var="path" />
			<li><strong>${product.getName()}</strong> -
				$${product.getPrice().toString()} <c:if
					test="${product.getPoster().getUsername().equals(loggedUser.getUsername())}">
					<a href="${path}?action=update&id=${product.getId()}">edit</a>
					<a href="${path}?action=delete&id=${product.getId()}">delete</a>
				</c:if> <br> <sup><strong>Poster:</strong>
					${product.getPoster().getUsername()}</sup></li>
		</c:forEach>
	</ul>
	<c:url value="product?action=new" var="path" />
	<a href="${path}">New product</a>
</body>
</html>