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
	padding-top: 10px;
	padding-bottom: 40px;
	background-color: #f5f5f5;
}

.row {
	padding-top: 10px;
	padding-bottom: 10px;
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
		<div class="row">
			<div class="span12">
				<%@ include file="/jspf/navbar.jspf"%>
			</div>
		</div>

		<div class="row">
			<div class="span2">
				<ul class="nav nav-tabs nav-stacked">
					<li class="active"><a href="#">General info</a></li>
					<li><a href="/OpinionPoll/surveys-unpublished.jsp">Questions</a></li>
				</ul>
			</div>
			<div class="span10">
				<%
					if (request.getAttribute("successMessage") != null) {
				%>
				<div class="alert alert-success">${successMessage}</div>
				<%
					}
				%>
				<form action="/OpinionPoll/SurveysController" method="post">
					<legend> Survey information </legend>
					<%
						Retrievable ret = JDInstanceService.getInstance();
					SurveyInstance survey;
						if (request.getParameter("id") != null) {
							Integer id = Integer.parseInt(request.getParameter("id"));
							 survey = ret.getSurvey(id);
						} else{
							 survey = (SurveyInstance) request.getAttribute("survey");
						}
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
						type="hidden" name="id" value="<%=survey.getId()%>">
					<%
						if (request.getParameter("from") != null) {
					%>
					<input type="hidden" name="from"
						value="<%=request.getParameter("from")%>">
					<%
						}
					%>
					<div class="controls">
						<button class="btn  button-wide btn-success" type="submit">Save</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
