<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="../Content/css/site.css">
<title>Questions</title>
</head>
<body>
	<div class="container">
		<div class="header row">
			<div class="col-md-10">
				<h3>Espace enseignant > Liste des questions</h3>
			</div>
			<div class="col-md-2" style="text-align: right;">
				<a class="logout" role="button" href="/Java2015Project/">Déconnexion</a>
			</div>
		</div>
		<table class="table table-hover">
			<tr>
				<th>Question</th>
				<th>Catégorie</th>
				<th>Points</th>
				<th>Modifier</th>
				<th>Supprimer</th>
			</tr>
			<c:forEach items="${Questions}" var="q">
				<tr>
					<td>${q.getContent()}</td>
					<td>
						${q.getCategories().getName()}
					</td>
					<td>${q.getPonderation().setScale(2)}</td>
					<td><a role="button" class="btn btn-default"
						href="/Java2015Project/Teacher/EditQuestion?id=${q.getId()}">
							<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
					</a></td>
					<td><a role="button" class="btn btn-default"
						href="/Java2015Project/Teacher/DeleteQuestion?id=${q.getId()}">
							<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
					</a></td>
				</tr>
			</c:forEach>
		</table>
		<hr/>
		<a class="btn btn-default" role="button" href="/Java2015Project/Teacher">Retours</a>
		<a class="btn btn-default" role="button" href="/Java2015Project/Teacher/CreateQuestion">Ajouter une question</a>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</body>
</html>