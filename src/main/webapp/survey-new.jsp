<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/jspf/link.jspf"%>
<title>Creating new survey</title>
</head>
<style type="text/css">
body {
	padding-top: 10px;
	padding-bottom: 40px;
	background-color: #f5f5f5;
}

.row {
	padding-top: 10px;
	padding-bottom: 10px;
}
.bordered {
	padding-top: 10px;
	padding-bottom: 10px;
	border: 1px solid #dddddd;
	border-collapse: separate;
	*border-collapse: collapse;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	border-radius: 4px;
}

textarea {
	width: 500px;
}

form {
	margin: 0 0 0;
}
</style>
<body>
	<div class="container">
		<div class="row">
			<div class="span12">
				<%@ include file="/jspf/navbar.jspf"%>
			</div>
		</div>
		<div class="row">
			<div class="span12">
			<div>
			<%
				if (request.getAttribute("errorMessage") != null) {
			%>
			<div class="alert alert-error">${errorMessage}</div>
			<%
				}
			%>
		</div>
				<form action="/OpinionPoll/SurveysController" method="post"
					class="form">
					<legend> New survey</legend>
					<div class="control-group">
						<label class="control-label" for="name">Name *</label>
						<div class="controls">
							<textarea rows="1" name="name" placeholder="survey name"
								class="ntSaveForms"></textarea>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="description">Description *</label>
						<div class="controls">
							<textarea rows="3" name="description"
								placeholder="enter description here..." class="ntSaveForms"></textarea>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="status">Status</label>
						<div class="controls">
							<label class="radio inline"> <input type="radio"
								name="status" value="unpublished" checked> Unpublished
							</label> 
							<label class="radio inline"> <input type="radio"
								name="status" value="published">Published
							</label>
						</div>
					</div>
					<input type="hidden" name="formname" value="new">
					<lable>Fields marked with * are required</lable>
					<div class="control-group">
						<div class="controls">
							<button class="btn  button-wide btn-success" type="submit">Save</button>
						</div>
					</div>
				</form>

			</div>
		</div>
	</div>
</body>
</html>