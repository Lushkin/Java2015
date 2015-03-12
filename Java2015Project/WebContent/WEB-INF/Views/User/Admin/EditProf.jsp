<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.text.SimpleDateFormat,java.text.DateFormat;" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
		<form action="/Java2015Project/Admin/EditProf?id=${Etudiant.getId()}" method="POST">
		
			<label>Nom :</label>
			<input type="text" placeholder="nom" id="Nom" name="Nom" class="form-control" value="${Etudiant.getLastName()}"/>
			<label>Prenom :</label>
			<input type="text" placeholder="prenom" id="Prenom" name="Prenom" class="form-control" value="${Etudiant.getFirstName()}"/>
			<label>Date de naissance :</label>
			<input type="date" id="DateNaissance" name="DateNaissance" class="form-control" value="<fmt:formatDate value="${Etudiant.getBirthDate()}" pattern="yyyy-MM-dd" />"/>
			<label>Email</label>
			<input type="text" id="Email" name="Email" class="form-control" value="${Etudiant.getEmail()}"/>
			<label>Password</label>
			<input type="password" id="Password" name="Password" class="form-control" value="${Etudiant.getPassword()}"/>
			<input type="submit" value="Modifier un prof" class="btn btn-primary"/>
			
		</form>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</body>
</html>