<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login or Register</title>
</head>
<body>
	<h3 style="background-color: red; color: white;">${exceptionMessage}</h3>
	<h2>Login:</h2>
	<c:url value="/login" var="path" />
	<form action="${path}?action=login" method="post">
		<label for="username">Username:</label> <input id="username"
			type="text" placeholder="Username here..." name="username"> <label
			for="password">Password:</label> <input id="password" type="password"
			placeholder="Password" name="password">
		<button>Send</button>
	</form>
	
	<br><br>
	
	<h2>Register:</h2>
	<c:url value="/login" var="path" />
	<form action="${path}?action=register" method="post">
		<label for="username">Username:</label> <input id="username"
			type="text" placeholder="Username here..." name="username"> <label
			for="password">Password:</label> <input id="password" type="password"
			placeholder="Password" name="password">
		<button>Send</button>
	</form>
</body>
</html>