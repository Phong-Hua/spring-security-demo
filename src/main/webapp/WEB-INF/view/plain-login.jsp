<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Custom Login Page</title>
</head>
<body>
	<!-- We use form:form, so Spring provide security against CSRF threat -->
	<form:form method="POST"
		action="${pageContext.request.contextPath}/authenticateTheUser">
		<!-- pageContext.request.contextPath give us access to context path dynamically, helps to keep links REALATIVE to application context path -->

		<!-- Spring Security defines DEFAULT names for login form fields such as: username, password -->
		<p>
			User name: <input type="text" name="username" />
		</p>
		<p>
			Password: <input type="password" name="password" />
		</p>
		<input type="submit" value="Login" />

	</form:form>
</body>
</html>