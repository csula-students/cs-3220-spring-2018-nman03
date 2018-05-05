<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
	<meta charset="UTF-8">
	<title>Dungeon Crawler</title>
	<link rel='stylesheet' type='text/css' href="<c:url value='/app.css' />" /></head>
<body>
	<header>
		<h1>Dungeon Runner</h1>
	</header>
	
	<main>
		<div class="story">
			<c:forEach items="${events}" var="event">
				<p>${event.name}</p>
			</c:forEach>
		</div>

		<label class="resource">Gold:<span id="resourceNum">0</span></label>
		<button class="clicker">Look For Loot</button>

		<div class="gen-container">
			<c:forEach items="${generators}" var="generator">
				<div class="generators">
					<p class="generator-name">${generator.name}<span class="generator-count">0</span></p>
					<p class="generator-description">${generator.description}</p> 
					<span class="rate">${generator.rate}</span><button>${generator.baseCost}</button>
				</div>
			</c:forEach>
		</div>
	</main>
	<script>
    	window.incrementalGame = {
	        state: {
	            counter: 0
	          
	        }
		}
    	window.incrementalGame.state = ${jsonString}; // where state is passed from Controller as JSON string
	</script>
	
	<script src="<c:url value='/app.js' />"></script>


	<footer><span>&copy; 2018 Neil Manimtim</span></footer>
	
</body>