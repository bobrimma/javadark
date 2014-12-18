<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="/jspf/link.jspf"%>
<title>Registration</title>
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
	width: 205px;
}
</style>
<body>
<div class="container">
		<form action = "/OpinionPoll/RegistrationController" class="form-register" method="post">
			<legend>
				Registration form
			</legend>
			<label class="control-label" for="firstName">First name</label>
			<div class="controls">
				<input type="text" name="firstName" placeholder="first name">
			</div>
			<label class="control-label" for="lastName">Last name</label>
			<div class="controls">
				<input type="text" name="lastName" placeholder="last name">
			</div>
			<label class="control-label" for="email">E-mail</label>
			<div class="controls">
				<input type="text" name="email" placeholder="e-mail@somewhere.com">
			</div>
			<label class="control-label" for="username">Username</label>
			<div class="controls">
				<input type="text" name="username" placeholder="username">
			</div>
			<label class="control-label" for="password1">Password</label>
			<div class="controls">
				<input type="password" name="password1" placeholder="password">
			</div>
			<label class="control-label" for="password2">Repeat password</label>
			<div class="controls">
				<input type="password" name="password2" placeholder="password">
			</div>
			<div class="controls">
				<a href="index.jsp"  class="btn" type="button" style="width: 50px;">Back</a>
				<button class="btn  button-wide btn-success" type="submit">Register</button>
			</div>
		</form>
	</div>
</body>
</html>
