<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="/jspf/link.jspf"%>
<title>Password recovery</title>
</head>
<style type="text/css">
body {
	padding-top: 100px;
	padding-bottom: 40px;
	background-color: #f5f5f5;
}

</style>
<body>
	<div class="container">
		<form  action="/OpinionPoll/PasswordRecoveryController" class="form-signin" method="post">
			<legend> Forgot password? Don't worry! </legend>
			
			<label class="control-label" for="email">Enter e-mail you
				registered for</label>
			<div class="controls">
				<input type="text" name="email" placeholder="email">
			</div>
			<div class="controls">
				<a href="index.jsp"  class="btn" type="button" style="width: 40px;">Back</a>
				<button class="btn btn-primary" type="submit">Recover password</button>
			</div>
		</form>
	</div>
</body>
</html>