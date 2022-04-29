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
		<h1>hello item <c:out value="${equipment.id}"/></h1>
		<img class="icon" src="<c:out value="${equipment.image}"/>" alt="" />
		<form:form action="/equipment/${equipment.id}/cashout" method="post" modelAttribute="updateEquip">
			<input type="hidden" name="_method" value="put">
			<form:input type="hidden" path="user" value="${user_id}"/>
			<form:input type="hidden" path="name" value="${equipment.name}"/>
			<form:input type="hidden" path="price" value="${equipment.price}"/>
			<form:input type="hidden" path="sellPrice" value="${equipment.sellPrice}"/>
			<form:input type="hidden" path="generationVal" value="${equipment.generationVal}"/>
			<form:input type="hidden" path="image" value="${equipment.image}"/>
			<form:input type="hidden" path="startTime"/>
			<button type="submit">Cash Out!</button>
		</form:form>
		<form action="/equipment/${equipment.id}/sell" method="post">
			<input type="hidden" name="_method" value="delete">
    		<button type="submit" class="btn btn-outline-danger">Sell </button>
		</form>
	</div>
	<script src="/js/main.js"></script>
</body>
</html>