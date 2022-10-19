<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
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

	<!-- Display user name and role -->

	<p>
		<!-- Display username -->
		User:
		<security:authentication property="principal.username" />
		<br> <br>
		<!-- Display roles, authorities = roles -->
		<!-- By default, Spring Security use "ROLE_" prefix, such as ROLE_EMPLOYEE, etc... -->
		Role(s):
		<security:authentication property="principal.authorities" />
	</p>

	<security:authorize access="hasRole('MANAGER')">
		<!-- Add a link to /leaders ... this is for the managers -->
		<p>
			<a href="${pageContext.request.contextPath}/leaders">Leadership
				Meeting</a> (Only for Manager peeps)
		</p>
	</security:authorize>

	<security:authorize access="hasRole('ADMIN')">
		<!-- Add a link to /systems ... this is for the admins -->
		<p>
			<a href="${pageContext.request.contextPath}/systems">IT Systems
				Meeting</a> (Only for Admin peeps)
		</p>
	</security:authorize>

	<security:authorize access="hasAnyRole('ADMIN', 'MANAGER')">
		<!-- Add a link to /vip ... this is for the admins or managers -->
		<p>
			<a href="${pageContext.request.contextPath}/vip">VIP
				Meeting</a> (Only for Admin or Manager peeps)
		</p>
	</security:authorize>
	
	<hr>
	
	<!-- We need form tag for logout, because logout is part of the form -->
	<form:form method="POST"
		action="${pageContext.request.contextPath}/logout">
		<input type="submit" value="Logout" />
	</form:form>
</body>
</html>