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
		<div id="marketBody">
			<div class="riddleBody">
				<c:choose>
					<c:when test = "${answered == false}">
				    	<h4><c:out value="${riddle}"/></h4>
						<form action="/jackpot" method="post">
							<input type="hidden" name="code" value="<c:out value='${answer}'/>" />
							<input type="text" class="jackpotInput" name="jackpot"/>
							<button type="submit" class="btn btn-info">Did I win?</button>
						</form>
				    </c:when>
				    <c:when test = "${answered == true}">
				    	
				    	<h4 style="color: green;"><img class="confetti" src="/img/confetti.gif" alt="" />Congratulations! You won the jackpot!<img class="confetti" src="/img/confetti.gif" alt="" /></h4>
				    	
				    	<p><b>Be sure to stop by next time for another chance to win!</b></p>
				    </c:when>
				</c:choose>
			</div>
			<div class="buyDiamondsBody">
				<c:choose>
					<c:when test = "${lowBalance == false}">
						<h4 style="color:green;">You have more than 10,000 coins!</h4>
						<h6>You will get 1 diamond for each 10,000 coins that you spend!</h6>
						<div class="buyButtons">
							<form action="/shop/buy1Diamond" method="post">
								<input type="hidden" name="myCoins" value="${wallet.coins}" />
								<button type="submit" class="btn btn-rounded btn-success">Buy 1 Diamond Now!</button>
							</form>
							<form action="/shop/buy5Diamonds" method="post">
								<input type="hidden" name="myCoins" value="${wallet.coins}" />
								<button type="submit" class="btn btn-rounded btn-success">Buy 5 Diamonds Now!</button>
							</form>
							<form action="/shop/buy10Diamonds" method="post">
								<input type="hidden" name="myCoins" value="${wallet.coins}" />
								<button type="submit" class="btn btn-rounded btn-success">Buy 10 Diamonds Now!</button>
							</form>
							<form action="/shop/buy25Diamonds" method="post">
								<input type="hidden" name="myCoins" value="${wallet.coins}" />
								<button type="submit" class="btn btn-rounded btn-success">Buy 25 Diamonds Now!</button>
							</form>
							<form action="/shop/buy50Diamonds" method="post">
								<input type="hidden" name="myCoins" value="${wallet.coins}" />
								<button type="submit" class="btn btn-rounded btn-success">Buy 50 Diamonds Now!</button>
							</form>
							<form action="/shop/buy100Diamonds" method="post">
								<input type="hidden" name="myCoins" value="${wallet.coins}" />
								<button type="submit" class="btn btn-rounded btn-success">Buy 100 Diamonds Now!</button>
							</form>
						</div>
						
					</c:when>
					<c:when test = "${lowBalance == true}">
						<h4 style="color:red;">You have less than 10,000 coins!</h4>
						<h6>When you have more than 10,000 coins, you will be able to purchase diamonds!</h6>
					</c:when>
				</c:choose>
			</div>
			
		
			<br />
			<br />
			<h1>Buy Equipment!</h1>
			<div id="equipBody">
				<c:forEach var="equipment" items="${equipment}">
					<div class="itemCard">
						<a href="/shop/equipment/<c:out value='${equipment.id}'/>"><img class="prizeCard" src="<c:out value='${equipment.image}'/>" alt="Gold_Mine" /></a>
						<p><b><c:out value="${equipment.name}"/></b></p>
					</div>
				</c:forEach>
			</div>
			<br />
			<br />
			<h1>Buy Prizes!</h1>
			<div id="prizeBody">
				
				<c:forEach var="prizes" items="${prizes}">
					<div class="itemCard">
						<a href="/shop/prizes/<c:out value='${prizes.id}'/>"><img class="prizeCard" src="<c:out value='${prizes.image}'/>" alt="Gold_Mine" /></a>
						<p><b><c:out value="${prizes.name}"/></b></p>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<script src="/js/main.js"></script>
</body>
</html>