<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Luv2code Home Page</title>
</head>
<body>
	<h2>Luv2code Home Page</h2>
	<hr>
		Welcome to luv2code home page
	<hr>
	<!-- We need form tag for logout, because logout is part of the form -->
	<form:form 
		method="POST" 
		action="${pageContext.request.contextPath}/logout"
	>
		<input type="submit" value="Logout"/>
	</form:form>
</body>
</html>