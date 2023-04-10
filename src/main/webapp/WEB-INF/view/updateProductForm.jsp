<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Product Form - ${product.getName()}</title>
</head>
<body>
	<h3 style="background-color:red;color:white;">${exceptionMessage}</h3>
	<h1>Update ${product.getName()}:</h1>
	<c:url value="/product" var="path" />
	<form action="${path}?action=update" method="post">
		<input type="hidden" name="id" value="${product.getId()}"> 
		<label for="name">Name:</label>
		<input id="name" type="text" placeholder="Product's Name" name="name" value="${product.getName()}">
		<label for="price">Price:</label>
		<input id="price" type="text" placeholder="0.00" name="price" min="0" value="${product.getPrice().toString()}">
		<button>Send</button>
	</form>
</body>
</html>