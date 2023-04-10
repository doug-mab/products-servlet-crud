<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Product</title>
</head>
<body>
<h3 style="background-color:red;color:white;">${exceptionMessage}</h3>
	<h1>New Product Form:</h1>
	<c:url value="/product" var="path" />
	<form action="${path}?action=new" method="post">
		<label for="name">Name:</label> <input id="name" type="text"
			placeholder="Product's Name" name="name"> <label for="price">Price:</label>
		<input id="price" type="text" placeholder="0.00" min="0"
			name="price">
		<button>Send</button>
	</form>
</body>
</html>