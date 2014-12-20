<%@page import="domain.AnswerInstance"%>
<%@page import="domain.QuestionInstance"%>
<%@page import="domain.SurveyInstance"%>
<%@page import="service.Retrievable"%>
<%@page import="service.JDInstanceService"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="/jspf/link.jspf"%>
<title>New question</title>
</head>
<style type="text/css">
body {
	padding-top: 10px;
	padding-bottom: 40px;
}

.button-wide {
	width: 205px;
}

textarea {
	width: 500px;
}
</style>
<body>
	<div class="container">
		<%
			Integer createdQuestionId = (Integer) request
					.getAttribute("createdQuestionId");
		
		Integer survId = (Integer) request
				.getAttribute("idSurv");
		%>
		<form action="/OpinionPoll/QuestionController" class="form"
			method="post">
			<legend> Question</legend>
			<div class="control-group">
				<label class="control-label" for="name">Name</label>
				<div class="controls">
					<textarea rows="2" name="name" placeholder="can be empty"></textarea>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="description">Description</label>
				<div class="controls">
					<textarea rows="5" name="description"
						placeholder="question description here..."></textarea>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="status">Question type</label>
				<div class="controls">
					<label class="radio inline"> <input type="radio"
						name="questiontype" value="multi"> multianswer
					</label> <label class="radio inline"> <input type="radio"
						name="questiontype" value="one" checked>one answer
					</label>
				</div>
			</div>
			<input type="hidden" name="formname" value="newquestion"> <input
				type="hidden" name="survId"
				value="<%=survId%>">
			<div class="control-group">
				<div class="controls">
					<a href="questions.jsp?idSurv=<%=survId%>"
						class="btn ntSaveFormsSubmit" type="button" style="width: 50px;">Back</a>
					<button class="btn  button-wide btn-success" type="submit">Save</button>
				</div>
			</div>
		</form>
		<div class="control-group">
			<h4>Answer's list:</h4>
			<%
				if (createdQuestionId != null) {
			%>
			<a href="#myModal" class="btn" data-toggle="modal">(add new)</a>




			<%
				Retrievable ret = JDInstanceService.getInstance();
					List<AnswerInstance> answersList = ret
							.getAnswers(createdQuestionId);
					if (answersList != null) {
						if (0 == answersList.size()) {
			%>
			<div class="alert alert-error">There're no any answers yet.</div>
			<%
				} else {
			%>
			<table class="table table-hover table-condensed">
				<thead>
					<tr>
						<th>Description</th>
						<th>Correct</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<%
						for (AnswerInstance answer : answersList) {
					%>
					<tr>
						<td><%=answer.getAnswerDescription()%>></td>
						<td>
							<%
								boolean correct = answer.isCorrect();
												if (correct)
													out.print("YES");
												else
													out.print("NO");
							%>
						</td>
						<td><a href="#">delete</a></td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
			<%
				}
					}
				}
			%>
		</div>
	</div>
	<div class="modal" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"
		style="display: none;">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">x</button>
			<h4 id="myModalLabel">New answer</h4>
		</div>
		<form action="/OpinionPoll/AnswerController" class="form"
			method="post">
			<div class="modal-body">
				<p></p>
			</div>
			<div class="modal-footer">
				<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
				<button class="btn btn-primary">Save changes</button>
			</div>

		</form>

	</div>
	<script type="text/javascript"
		src="/OpinionPoll/resources/js/ntsaveforms.js"></script>
</body>
</html>
