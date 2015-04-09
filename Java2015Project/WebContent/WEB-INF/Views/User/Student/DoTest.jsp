<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Test</title>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
	<h1>${Test.getTitle()}</h1>
		<form action="/Java2015Project/Student/DoTest?id=${Test.getId()}" method="POST"
			class="form-horizontal">
			<c:forEach items="${Questions}" var="q" varStatus="i">
				<div class="form-group">
					<h4>${i.index + 1}-${q.getContent()}</h4>
					<c:forEach items="${q.getQuestionAnswerses()}" var="a">
						<label class="form-label"> <input type="checkbox"
							id="Answer-${a.getAnswers().getId()}"
							name="Answer-${a.getAnswers().getId()}">
							${a.getAnswers().getValue()}
						</label>
						<br />
					</c:forEach>
				</div>
			</c:forEach>
			<div class="form-group">
				<div class="col-md-2 col-md-offset-8">
					<a class="btn btn-default" role="button"
						href="/Java2015Project/Student" style="width: 100% !important;">Annuler</a>
				</div>
				<div class="col-md-2">
					<input type="submit" value="Envoyer" class="btn btn-primary"
						style="width: 100% !important;" />
				</div>
			</div>
		</form>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</body>
</html>