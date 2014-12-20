<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/jspf/link.jspf"%>
<title>Index</title>
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
	padding-bottom: 40px;
	border-left:0;
	border: 1px solid #dddddd;
	border-collapse: separate;
	*border-collapse: collapse;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	border-radius: 4px;
}

#submit{
	margin-left: 20px;
}


</style>
<body>
	<div class="container">
		<div class="row">
			<div class="span12">
				<%@ include file="/jspf/indexnavbar.jspf"%>
			</div>
			<div id = "submit" class="alert alert-success" >${submitMessage}</div>
		</div>
		
		<div class="row">
			<div class="span4">
						<%@ include file="/jspf/loginform.jspf"%>
			</div>
			<div class="span8">
				
			</div>
		</div>
	</div>
</body>
</html>