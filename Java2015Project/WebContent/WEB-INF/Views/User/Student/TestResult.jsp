<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Résultats</title>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="../Content/css/site.css">
</head>
<body>
	<div class="container">
		<div class="header row">
			<div class="col-md-10">
				<h3>Espace étudiant > Résultats du test</h3>
			</div>
			<div class="col-md-2" style="text-align: right;">
				<a class="logout" role="button" href="/Java2015Project/">Déconnexion</a>
			</div>
		</div>
		<hr />
		
	<h1>${Test.getTitle()}</h1>
			<c:forEach items="${Questions}" var="q" varStatus="i">
				<div class="form-group">
					<h4>${i.index + 1}-${q.getQuestion().getContent()} (${q.getQuestion().getPonderation().setScale(2)} pts)</h4>
					<c:forEach items="${q.getAnswers()}" var="a">
						<label class="form-label"> 
							<input type="checkbox" ${a.getIsChecked() == 1 ? "checked" : ""} disabled />
							<span class="${a.getAnswers().getIsCorrect() == 1 ? "correct-answer" : "false-answer"}">${a.getAnswers().getValue()}</span>
						</label>
						<br />
					</c:forEach>
				</div>
			</c:forEach>
			<hr/>
			<div class="form-group">
				<div class="col-md-2 col-md-offset-8">
					<a class="btn btn-default" role="button"
						href="/Java2015Project/Student" style="width: 100% !important;">Retour</a>
				</div>
			</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</body>
</html>