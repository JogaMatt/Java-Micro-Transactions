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
		<div id="profileBody">
			<div class="equipment">
			
				<c:forEach var="equipment" items="${equipment}">
					<div class="card">
	  					<div class="bg-image hover-overlay ripple" data-mdb-ripple-color="light">
	    					<img class="icon" src="<c:out value='${equipment.image}'/>" alt="" />
	  					</div>
						<div class="card-body">
						    <h5 class="card-title"><c:out value="${equipment.name}"/></h5>
						    <form:form action="/equipment/${equipment.id}/cashout" method="post" modelAttribute="updateEquip">
								<input type="hidden" name="_method" value="put">
								<form:input type="hidden" path="user" value="${user_id}"/>
								<form:input type="hidden" path="name" value="${equipment.name}"/>
								<form:input type="hidden" path="price" value="${equipment.price}"/>
								<form:input type="hidden" path="sellPrice" value="${equipment.sellPrice}"/>
								<form:input type="hidden" path="generationVal" value="${equipment.generationVal}"/>
								<form:input type="hidden" path="image" value="${equipment.image}"/>
								<form:input type="hidden" path="startTime"/>
								<button type="submit" class="btn btn-outline-success">Cash Out!</button>
							</form:form>
							<form class="sellForm" action="/equipment/${equipment.id}/sell" method="post">
								<input type="hidden" name="_method" value="delete">
	    						<button type="submit" class="btn btn-outline-danger">Sell </button>
	    						<p class="sellPrice"><b>for <c:out value="${equipment.sellPrice}"/></b> <img class="coinStack" src="/img/coins.png" alt="" /></p>
							</form>
						</div>
					</div>
				</c:forEach>
			</div>			
			
			<div class="prizes">
				<c:forEach var="prizes" items="${prizes}">
					<div class="itemCard">
						<img class="prizeCard" src="<c:out value='${prizes.image}'/>" alt="" />
						<p><b><c:out value="${prizes.name}"/></b></p>
						<form class="sellForm" action="/prize/${prizes.id}/coinSell" method="post">
							<input type="hidden" name="_method" value="delete">
	    					<button type="submit" class="btn btn-outline-danger">Sell </button>
	    					<p class="sellPrice"><b>for <c:out value="${prizes.sellCoinPrice}"/></b> <img class="coinStack" src="/img/coins.png" alt="" /></p>
						</form>
						<form class="sellForm" action="/prize/${prizes.id}/diamondSell" method="post">
							<input type="hidden" name="_method" value="delete">
	    					<button type="submit" class="btn btn-outline-danger">Sell </button>
	    					<p class="sellPrice"><b>for <c:out value="${prizes.sellDiamondPrice}"/></b> <img class="coinStack" src="/img/diamond.gif" alt="" /></p>
						</form>
					</div>
				</c:forEach>
			</div>
			
			
		</div>
	</div>
	<script src="/js/main.js"></script>
</body>
</html>