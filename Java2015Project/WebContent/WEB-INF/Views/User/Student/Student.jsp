<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Student Index</title>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="Content/css/site.css">
</head>
<body>
	<div class="container">
	<div class="header row">
			<div class="col-md-10">
				<h3>Espace étudiant > Tests</h3>
			</div>
			<div class="col-md-2" style="text-align: right;">
				<a class="logout" role="button" href="/Java2015Project/">Déconnexion</a>
			</div>
		</div>
		<hr />
		
		<div id="pendingTests">
			<h3>Tests à faire :</h3>
			<br />
			<table class="table table-hover">
				<tr>
					<th>Titre</th>
					<th>Sujet</th>
					<th>Début</th>
					<th>Fin</th>
				</tr>
				<c:forEach items="${PendingTests}" var="t">
					<tr onclick="pendingClicked(${t.getTests().getId()})">
						<td>${t.getTests().getTitle()}</td>
						<td>${t.getTests().getSubjects().getName()}</td>
						<td><fmt:formatDate pattern="dd/MM/yyyy"
								value="${t.getTests().getStartDate()}" /></td>
						<td><fmt:formatDate pattern="dd/MM/yyyy"
								value="${t.getTests().getEndDate()}" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<br />
		<div id="completedTests">
			<h3>Tests terminés :</h3>
			<br />
			<table class="table table-hover">
				<tr>
					<th>Titre</th>
					<th>Sujet</th>
					<th>Début</th>
					<th>Fin</th>
					<th>Note</th>
				</tr>
				<c:forEach items="${FinishedTests}" var="t">
					<tr onclick="finishedClicked(${t.getTests().getId()})">
						<td>${t.getTests().getTitle()}</td>
						<td>${t.getTests().getSubjects().getName()}</td>
						<td><fmt:formatDate pattern="dd/MM/yyyy"
								value="${t.getTests().getStartDate()}" /></td>
						<td><fmt:formatDate pattern="dd/MM/yyyy"
								value="${t.getTests().getEndDate()}" /></td>
						<td><fmt:formatNumber value="${t.getMark()}" maxFractionDigits="2"/>/20</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		function pendingClicked(id){
			window.location.href = "/Java2015Project/Student/DoTest?id=" + id;
		}
		function finishedClicked(id){
			window.location.href = "/Java2015Project/Student/TestResult?id=" + id;
		}
	</script>
</body>
</html>