<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="/css/style.css" />
<title>Insert title here</title>
</head>
<body>
	<div id="container">
		<div id="navbar">
			<h1>Welcome</h1>
		</div>
			<div id="mainBody">
				<div class="register">
					
					<h1>New User</h1>
					<form:form action="/register" method="post" modelAttribute="newUser">
						<p>
							First Name:
							<form:input path="first_name"/>
						</p>
						<p>
							Last Name:
							<form:input path="last_name"/>
						</p>
						<p>
							Username:
							<form:input path="username"/>
						</p>
						<p>
							Email:
							<form:input path="email"/>
						</p>
						<p>
							Password:
							<form:input type="password" path="password"/>
						</p>
						<p>
							Confirm Password:
							<form:input type="password" path="confirm"/>
						</p>
						<button type="submit" class="btn btn-primary">Register</button>
						<div id="errorCard">
							<form:errors path="first_name"/>
							<form:errors path="last_name"/>
							<form:errors path="username"/>
							<form:errors path="email"/>
							<form:errors path="password"/>
							<form:errors path="confirm"/>
						</div>
					</form:form>
				</div>
				
				<div class="login">
					<h1>Log in</h1>
					<form:form action="/login" method="post" modelAttribute="newLogin">
						<p>
							Email:
							<form:input path="email"/>
						</p>
						<p>
							Password
							<form:input type="password" path="password"/>
						</p>
						<button type="submit" class="btn btn-success">Login</button>
						<div id="errorCard">
							<form:errors path="email"/>
							<form:errors path="password"/>
						</div>
					</form:form>
				</div>
				
			</div>
	</div>
	<script src="/js/main.js"></script>
</body>
</html>