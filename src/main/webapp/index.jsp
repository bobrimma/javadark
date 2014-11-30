<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="/jspf/link.jspf"%>
<title>Sign in</title>
</head>
<style type="text/css">
body {
	padding-top: 100px;
	padding-bottom: 40px;
	background-color: #f5f5f5;
}
input{
  width: 270px;
}
.button-wide{
	width: 165px;
}
</style>
<body>
	<div class="container">
		<form class="form-signin">
			<legend>
				Please sign in
			</legend>
			<label class="control-label" for="inputUsername">Username</label>
			<div class="controls">
				<input type="text" id="inputUsername" placeholder="username">
			</div>
			<label class="control-label" for="inputPassword">Password <a href="passwordrecovery.jsp"> (forgot password)</a></label>
			<div class="controls">
				<input type="password" id="inputPassword" placeholder="password">
			</div>
			<div class="controls">
				<button class="btn btn-primary" type="submit">Sign in</button> or 
				<a href="registration.jsp"  class="btn button-wide btn-success" type="button">Register</a>
			</div>
		</form>
	</div>
</body>
</html>