<%@page import="domain.SurveyInstance"%>
<%@page import="service.JDInstanceService"%>
<%@page import="service.Retrievable"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ include file="/jspf/link.jspf"%>
<title>Survey</title>
</head>
<style type="text/css">
body {
	padding-top: 100px;
	padding-bottom: 40px;
	background-color: #f5f5f5;
}

.button-wide {
	width: 205px;
}

textarea {
	width: 270px;
}
</style>
<body>
	<div class="container">
		<form action="/OpinionPoll/SurveysController" class="form-register"
			method="post">
			<legend> Survey information </legend>
			<%
				Retrievable ret = JDInstanceService.getInstance();
				Integer survId = Integer.parseInt(request.getParameter("id"));
				SurveyInstance survey = ret.getSurvey(survId);
			%>
			<label class="control-label" for="name">Name</label>
			<div class="controls">
				<textarea rows="2" name="name"><%=survey.getName()%></textarea>
			</div>
			<label class="control-label" for="description">Description</label>
			<div class="controls">
				<textarea rows="5" name="description"><%=survey.getDescription()%></textarea>
			</div>
			<label class="control-label" for="status">Status</label>
			<div class="controls">
				<label class="radio"> <input type="radio" name="status"
					value="unpublished" <%if (!survey.isPublished()) {%> checked
					<%}%>> Unpublished
				</label> <label class="radio"> <input type="radio" name="status"
					value="published" <%if (survey.isPublished()) {%> checked <%}%>>Published
				</label>
			</div>
			<input type="hidden" name="formname" value="edit"> <input
				type="hidden" name="id" value="<%=request.getParameter("id")%>">
			<input type="hidden" name="from"
				value="<%=request.getParameter("from")%>">

			<div class="controls">
				<a href="surveys.jsp" class="btn" type="button" style="width: 50px;">Back</a>
				<button class="btn  button-wide btn-success" type="submit">Save</button>
			</div>
		</form>
	</div>
</body>
</html>
