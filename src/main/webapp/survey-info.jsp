<%@page import="domain.SurveyInstance"%>
<%@page import="service.JDInstanceService"%>
<%@page import="service.Retrievable"%>
<%@page import="domain.QuestionInstance"%>
<%@page import="java.util.List"%>

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

				<div class="tabbable tabs-left">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#tab1" data-toggle="tab">General
								info</a></li>
						<li><a href="#tab2" data-toggle="tab">Questions</a></li>
					</ul>

					<div class="tab-content">
						<div class="tab-pane  active" id="tab1">
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
									} else {
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
									</label> <label class="radio"> <input type="radio"
										name="status" value="published"
										<%if (survey.isPublished()) {%> checked <%}%>>Published
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
						<div class="tab-pane" id="tab2">
						<form method="post"
									action="/OpinionPoll/question-new.jsp">
									<input type="hidden" name="from" value="search"> <input
										type="hidden" name="survid" value=<%=survey.getId()%>>
									<button type="submit" class="btn btn-link">Add new question</button>
								</form>
							<%
								List<QuestionInstance> questionsList = ret.getQuestions(survey
										.getId());
								if (questionsList.isEmpty()) {
							%>
							<div class="alert alert-error">
								<button type="button" class="close" data-dismiss="alert">&times;</button>

								There are no questions yet!
							</div>
							<%
								} else {
							%>
							<table class="table table-hover table-condensed">

								<thead>
									<tr>
										<th>Name</th>
										<th>Description</th>
										<th>Type</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<%
										for (QuestionInstance question : questionsList) {
									%>
									<tr>
										<td><%=question.getName()%></td>
										<td><%=question.getDescription()%></td>
										<td>
											<%
												boolean multi = question.isAllowMultipleAnswers();
														if (multi)
															out.print("multianswer");
														else
															out.print("one answer");
											%>
										</td>
										<td><form method="post"
									action="/OpinionPoll/question-info.jsp">
									<input type="hidden" name="from" value="search"> <input
										type="hidden" name="qwid" value=<%=question.getId()%>>
									<button type="submit" class="btn btn-link">edit</button>
								</form></td>
									</tr>
									<%
										}
									%>
								</tbody>
							</table>
							<%
								}
							%>


						</div>
					</div>


				</div>






			</div>
		</div>
	</div>
</body>
</html>
