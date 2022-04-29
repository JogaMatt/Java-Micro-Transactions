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
		<c:choose>
			<c:when test = "${wrongAnswer == true}">
				<h1>I'm sorry, better luck next time!</h1>
				<a href="/profile">Go to your profile</a>
			</c:when>
			<c:when test = "${wrongAnswer == false}">
				<h1>Congrats! You won the jackpot!</h1>
				<a href="/profile">Go to your profile</a>
			</c:when>
		</c:choose>
	</div>
	<script src="/js/main.js"></script>
</body>
</html>