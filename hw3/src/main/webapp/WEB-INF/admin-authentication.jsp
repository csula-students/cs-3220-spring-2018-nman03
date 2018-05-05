<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel='stylesheet' type='text/css' href="<c:url value='/app.css' />" />
<h1>Incremental Game Framework</h1>
<form class='login' method='POST'>
	<label for='username'>Username:</label><br>
	<input name='username' id='username' type='text' /><br>
	<label for='password'>Password:</label><br>
	<input name='password' id='password' type='text' /><br>
	<button>Log in</button>
</form>