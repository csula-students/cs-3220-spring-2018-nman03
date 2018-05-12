<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
	<meta charset="UTF-8">
	<title>Dungeon Crawler</title>
	<link href='<c:url value="/app.css" />' rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Kavivanar" rel="stylesheet">
	<script type="text/javascript">
		window.incrementalGame = {};
		window.incrementalGame.state = ${state};
		window.incrementalGame.state.generators.forEach(g =>
			g.quantity = 0
		);
		window.incrementalGame.state.story.forEach(s =>
			s.state = 'hidden'
		);
	</script>

</head>
<body>
	<header>
		<h1>Dungeon Runner</h1>
	</header>

	
	<main>
		<div class="story">
			<game-story-book></game-story-book>
		</div>

		<game-counter></game-counter>
		<game-button></game-button>	

		<div class="container">
			<game-generator data-id="0"></game-generator>
			<game-generator data-id="1"></game-generator>
			<game-generator data-id="2"></game-generator>
		</div>
	</main>

	<script src='<c:url value="/app.bundle.js" />'></script>

	<footer><span>&copy; 2018 Neil Manimtim</span></footer>
</body>