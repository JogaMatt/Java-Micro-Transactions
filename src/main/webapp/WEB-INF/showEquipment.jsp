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
		<div id="navigation">
			<div class="topNav">
				<div class="left">
					<img class="logo" src="/img/logo.png" alt="" />
				</div>
				<div class="right">
					<a href="/logout"><button type="button" class="btn btn-danger btn-rounded">Logout</button></a>
					<div class="myWallet">
						<div class="wallet">
							<img class="coin" src="/img/coin.gif" alt="" />
							<b><c:out value="${wallet.coins}"/></b>
						</div>
						<div class="wallet">
							<img class="diamond" src="/img/diamond.gif" alt="" />
							<b><c:out value="${wallet.diamonds}"/></b>
						</div>
					</div>
					
				</div>
			</div>
			<div class="bottomNav">
				<a href="/shop">Marketplace</a>
				<a href="/profile">Profile Page</a>
			</div>
		</div>
		<div class="buyEquip">
			<h1>Purchase <c:out value="${equipment.name}"/></h1>
			<img class="buyImage" src="<c:out value='${equipment.image}'/>" alt="<c:out value='${prize.name}'/>" />
			<h4>Item: <c:out value="${equipment.name}"/></h4>
			<h5>Price: <c:out value="${equipment.price}"/> <img class="coinStack" src="/img/coins.png" alt="" /></h5>
			<h5>Gold Generation: <c:out value="${equipment.generationVal}"/> <img class="coinStack" src="/img/coins.png" alt="" />/sec</h5>
			
			<c:choose>
				<c:when test = "${canPurchase == true}">
					<form:form action="/shop/purchase" method="post" modelAttribute="addEquip">
						<form:input type="hidden" path="user" value="${user_id}"/>
						<form:input type="hidden" path="name" value="${equipment.name}"/>
						<form:input type="hidden" path="price" value="${equipment.price}"/>
						<form:input type="hidden" path="sellPrice" value="${equipment.sellPrice}"/>
						<form:input type="hidden" path="generationVal" value="${equipment.generationVal}"/>
						<form:input type="hidden" path="image" value="${equipment.image}"/>
						<form:input type="hidden" path="startTime"/>
						<button type="submit" class="btn btn-success">Purchase!</button>
					</form:form>
				</c:when>
				<c:when test = "${canPurchase == false}">
					<h4 style="color: red;">Not enough coins!</h4>
				</c:when>
			</c:choose>
			
		</div>
		
	</div>
	<script src="/js/main.js"></script>
</body>
</html>