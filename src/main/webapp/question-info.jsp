<%@page import="domain.AnswerInstance"%>
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
<title>Question</title>
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
form {
	margin: 0 0 0;
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
			<div class="span12">

				<div class="tabbable tabs-left">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#tab1" data-toggle="tab">General
								info</a></li>
						<li><a href="#tab2" data-toggle="tab">Answers</a></li>
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
								<legend> Question information </legend>
								<%
									Retrievable ret = JDInstanceService.getInstance();
									QuestionInstance question;
									if (request.getParameter("qwid") != null) {
										Integer id = Integer.parseInt(request.getParameter("qwid"));
										question = ret.getQuestion(id);
									} else {
										question = (QuestionInstance) request.getAttribute("question");
									}
								%>
								<label class="control-label" for="name">Name</label>
								<div class="controls">
									<textarea rows="2" name="name"><%=question.getName()%></textarea>
								</div>
								<label class="control-label" for="description">Description</label>
								<div class="controls">
									<textarea rows="5" name="description"><%=question.getDescription()%></textarea>
								</div>
								
								<input type="hidden" name="formname" value="edit"> <input
									type="hidden" name="id" value="<%=question.getId()%>">
								<%
									if (request.getParameter("from") != null) {
								%>
								<input type="hidden" name="from"
									value="<%=request.getParameter("from")%>">
								<%
									}
								%>
								<div class="controls">
								<a href="survey-info.jsp?id=<%=question.getSurvey().getId()%>" class="btn"
								type="button" style="width: 50px;">Back</a>
									<button class="btn  button-wide btn-success inactive" type="submit">Save</button>
								</div>
							</form>

						</div>
						<div class="tab-pane" id="tab2">
						<form method="post"
									action="/OpinionPoll/answer-new.jsp">
									<input type="hidden" name="from" value="search"> <input
										type="hidden" name="qwid" value=<%=question.getId()%>>
									<button type="submit" class="btn btn-link">Add new answer</button>
								</form>
							<%
								List<AnswerInstance> answerList = ret.getAnswers(question.getId());
								if (answerList.isEmpty()) {
							%>
							<div class="alert alert-error">
								<button type="button" class="close" data-dismiss="alert">&times;</button>

								There are no answers yet!
							</div>
							<%
								} else {
							%>
							<table class="table table-hover table-condensed">

								<thead>
									<tr>
										<th>Description</th>
										<th>Correct</th>
									</tr>
								</thead>
								<tbody>
								
									<%
										for (AnswerInstance answer : answerList) {
											String status = null;
											if (answer.isCorrect())
												status = "correct";
											else
												status = "incorrect";
									%>
									
									<tr <%if (status.equals("correct")) {%> class="success"
							<%} else {%> class="error" <%}%>>
										<td><%=answer.getAnswerDescription()%></td>
										<td><%=status%></td>
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
