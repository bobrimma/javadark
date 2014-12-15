<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="/jspf/link.jspf"%>
<title>Surveys</title>
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

</style>
<body>
	<div class="container">

		<div class="row">
			<div class="span12">
				<%@ include file="/jspf/navbar.jspf"%>
			</div>
		</div>
		<div class="row">
			<div class="span12 bordered">
				<div class="tabbable tabs-left">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#tab1" data-toggle="tab">Search</a></li>
						<li><a href="#tab2" data-toggle="tab">Puplished</a></li>
						<li><a href="#tab3" data-toggle="tab">Unpubliched</a></li>
						<li><a href="#tab4" data-toggle="tab">Create new</a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="tab1">
							<p>Я первая секция.</p>
						</div>
						<div class="tab-pane" id="tab2">
							<p>Привет, я 2-я секция.</p>
						</div>
						<div class="tab-pane" id="tab3">
							<p>Привет, я 3-я секция.</p>
						</div>
						<div class="tab-pane" id="tab4">
							<p>Привет, я 4-я секция.</p>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
</body>
</html>