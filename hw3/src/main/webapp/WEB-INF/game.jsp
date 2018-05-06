<head>
	<meta charset="UTF-8">
	<title>Dungeon Crawler</title>
	<link href="app.css" rel="stylesheet" />
</head>
<body>
	<header>
		<h1>Dungeon Runner</h1>
	</header>

	
	<main>
		<div class="story">
			<p>Need Gold? Click the buttton to look for loot and earn Gold.</p>
			<p>You've made a name for yourself as a veteran dungeoneer.</p>
			<p>You've hired an Adventurer to do the looting for you.</p>
		</div>

		<ul></ul>

		<game-counter></game-counter>
		<game-button></game-button>	

		<div class="container">
			<game-generator data-id="0"></game-generator>
			<game-generator data-id="1"></game-generator>
			<game-generator data-id="2"></game-generator>
		</div>
		
	</main>

	<footer><span>&copy; 2018 Neil Manimtim</span></footer>

	<script src='app.js'>
    	window.incrementalGame.state.generators.forEach(g =>
    		var node = document.createElement(li);
    		node.textContent(g.name);
    		document.querySelector(ul).appendChild(node);
    	);
	</script>

</body>
