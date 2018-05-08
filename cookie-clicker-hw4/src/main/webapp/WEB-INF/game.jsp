<head>
	<meta charset="UTF-8">
	<title>Dungeon Crawler</title>
	<link href="app.css" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Kavivanar" rel="stylesheet">
	<script src="/app.bundle.js"></script>

	<script type="text/javascript">
		window.incrementalGame.state: ${state};
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

	<footer><span>&copy; 2018 Neil Manimtim</span></footer>
	<script src="/app.bundle.js"></script>

</body>