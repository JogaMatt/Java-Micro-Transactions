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
		
		<div class="buyPrize">
			<h1>Purchase <c:out value="${prize.name}"/></h1>
			<img class="buyImage" src="<c:out value='${prize.image}'/>" alt="<c:out value='${prize.name}'/>" />
			<h4>Coin Price: <c:out value="${prize.coinPrice}"/> <img class="coinStack" src="/img/coins.png" alt="Coin-Stack" /></h4>
			<h4>Diamond Price: <c:out value="${prize.diamondPrice}"/> <img class="coinStack" src="/img/diamond.gif" alt="Diamonds" /></h4>
		
			<c:choose>
				<c:when test = "${canCoinPurchase == true}">
					<form:form action="/shop/prize/coinPurchase" method="post" modelAttribute="addPrize">
						<form:input type="hidden" path="user" value="${user_id}"/>
						<form:input type="hidden" path="name" value="${prize.name}"/>
						<form:input type="hidden" path="coinPrice" value="${prize.coinPrice}"/>
						<form:input type="hidden" path="sellCoinPrice" value="${prize.sellCoinPrice}"/>
						<form:input type="hidden" path="diamondPrice" value="${prize.diamondPrice}"/>
						<form:input type="hidden" path="sellDiamondPrice" value="${prize.sellDiamondPrice}"/>
						<form:input type="hidden" path="image" value="${prize.image}"/>
						<button type="submit" class="btn btn-success">Purchase with Coins!</button>
					</form:form>
				</c:when>
				<c:when test = "${canCoinPurchase == false}">
					<h4 style="color: red;">Not enough coins!</h4>
				</c:when>
			</c:choose>
			
			<c:choose>
				<c:when test = "${canDiamondPurchase == true}">
					<form:form action="/shop/prize/diamondPurchase" method="post" modelAttribute="addPrize">
						<form:input type="hidden" path="user" value="${user_id}"/>
						<form:input type="hidden" path="name" value="${prize.name}"/>
						<form:input type="hidden" path="coinPrice" value="${prize.coinPrice}"/>
						<form:input type="hidden" path="sellCoinPrice" value="${prize.sellCoinPrice}"/>
						<form:input type="hidden" path="diamondPrice" value="${prize.diamondPrice}"/>
						<form:input type="hidden" path="sellDiamondPrice" value="${prize.sellDiamondPrice}"/>
						<form:input type="hidden" path="image" value="${prize.image}"/>
						<button type="submit" class="btn btn-success">Purchase with Diamonds!</button>
					</form:form>
				</c:when>
				<c:when test = "${canDiamondPurchase == false}">
					<h4 style="color: red;">Not enough diamonds!</h4>
				</c:when>
			</c:choose>
		</div>
	</div>
	<script src="/js/main.js"></script>
</body>
</html>