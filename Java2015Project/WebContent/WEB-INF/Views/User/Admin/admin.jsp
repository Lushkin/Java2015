<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin index</title>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link rel="stylesheet" href="Content/css/site.css">
</head>
<body>
	<div class="container">
		<div class="header">
			<h2>Dashboard !</h2>
		</div>
		<hr/>
		<div>
			<ul class="nav nav-tabs">
				<li role="presentation" class="active nav" id="teacher"><a>Profs</a></li>
				<li role="presentation" class="nav" id="promotion"><a>Promotions</a></li>
				<li role="presentation" class="nav" id="student"><a>Etudiants</a></li>
			</ul>
		</div>
		<div class="content" id="teacher-content">
			<table class="table table-hover">
				<tr>
					<th>Prénom</th>
					<th>Nom</th>
					<th>Email</th>
					<th>Date de naissance</th>
					<th>Modifier</th>
					<th>Supprimer</th>
				</tr>
				<c:forEach items="${Teachers}" var="t">
					<tr>
						<td>${t.getFirstName()}</td>
						<td>${t.getLastName()}</td>
						<td>${t.getEmail()}</td>
						<td><fmt:formatDate pattern="dd/MM/yyyy"
								value="${t.getBirthDate()}" /></td>
						<td><a role="button" class="btn btn-default"
							href="/Java2015Project/Admin/EditProf?id=${t.getId()}"> <span class="glyphicon glyphicon-pencil"
								aria-hidden="true"></span>
						</a></td>
						<td><a role="button" class="btn btn-default"
							href="/Java2015Project/Admin/DeleteProf?id=${t.getId()}"> <span class="glyphicon glyphicon-trash"
								aria-hidden="true"></span>
						</a></td>
					</tr>
				</c:forEach>
			</table>
			<a class="btn btn-default" role="button" href="">Ajouter un prof</a>
		</div>
		<div class="content" id="promotion-content" style="display: none">
			<table class="table table-hover">
				<tr>
					<th>Nom</th>
					<th>Modifier</th>
					<th>Supprimer</th>
				</tr>
				<c:forEach items="${Promotions}" var="p">
					<tr>
						<td>${p.getName()}</td>
						<td><a role="button" class="btn btn-default"
							href="/Java2015Project/Admin/EditPromotion?id=${p.getId()}"> <span class="glyphicon glyphicon-pencil"
								aria-hidden="true"></span>
						</a></td>
						<td><a role="button" class="btn btn-default"
							href="/Java2015Project/Admin/DeletePromotion?id=${p.getId()}"> <span class="glyphicon glyphicon-trash"
								aria-hidden="true"></span>
						</a></td>
					</tr>
				</c:forEach>
			</table>
			<a class="btn btn-default" role="button"
				href="/Java2015Project/Admin/CreatePromotion">Ajouter une
				promotion</a>
		</div>
		<div class="content" id="student-content" style="display: none">
			<table class="table table-hover">
				<tr>
					<th>Prénom</th>
					<th>Nom</th>
					<th>Email</th>
					<th>Date de naissance</th>
					<th>Modifier</th>
					<th>Supprimer</th>
				</tr>
				<c:forEach items="${Students}" var="s">
					<tr>
						<td>${s.getFirstName()}</td>
						<td>${s.getLastName()}</td>
						<td>${s.getEmail()}</td>
						<td><fmt:formatDate pattern="dd/MM/yyyy"
								value="${s.getBirthDate()}" /></td>
						<td><a role="button" class="btn btn-default"
							href="/Java2015Project/Admin/EditEtudiant?id=${s.getId()}"><span class="glyphicon glyphicon-pencil"
								aria-hidden="true"></span>
						</a></td>
						<td><a role="button" class="btn btn-default"
							href="/Java2015Project/Admin/DeleteEtudiant?id=${s.getId()}"><span class="glyphicon glyphicon-trash"
								aria-hidden="true"></span>
						</a></td>
					</tr>
				</c:forEach>
			</table>
			<a class="btn btn-default" role="button" href="/Java2015Project/Admin/CreateEtudiant">Ajouter un étudiant</a>
		</div>
		<a class="btn btn-info" role="button" href="/Java2015Project/Admin/StudentsToPromotion">Attribuer Etudiant au Promotion</a>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$('.nav').click(function(e) {
				$('.content').hide();
				$('#' + $(this).attr('id') + '-content').show();
				$('.nav').removeClass('active');
				$(this).addClass('active');
				e.stopImmediatePropagation();
			});
		});
	</script>
</body>
</html>