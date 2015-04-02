<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin index</title>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="../Content/css/site.css">
</head>
<body>
	<div class="container">
		<div class="header row">
			<div class="col-md-10">
				<h3>Espace administrateur > Création de professeurs</h3>
			</div>
			<div class="col-md-2" style="text-align:right;">
				<a class="logout" role="button" href="/Java2015Project/">Déconnexion</a>
			</div>
		</div>
		<hr/>
		<form action="/Java2015Project/Admin/CreateProf" method="POST" class="form-horizontal">
		
			<div class="form-group">
			    <label for="Nom" class="col-sm-2 control-label">Nom </label>
			    <div class="col-sm-10">
			      <input type="text" placeholder="nom" id="Nom" name="Nom" class="form-control"/>
			    </div>
			</div>

			<div class="form-group">
			    <label for="Prenom" class="col-sm-2 control-label">Prenom </label>
			    <div class="col-sm-10">
			      <input type="text" placeholder="prenom" id="Prenom" name="Prenom" class="form-control"/>
			    </div>
			</div>
			
			<div class="form-group">
			    <label for="DateNaissance" class="col-sm-2 control-label">Date de naissance </label>
			    <div class="col-sm-10">
			      <input type="date" id="DateNaissance" name="DateNaissance" class="form-control"/>
			    </div>
			</div>
			
			<div class="form-group">
			    <label for="Email" class="col-sm-2 control-label">Email </label>
			    <div class="col-sm-10">
			      <input type="text" id="Email" name="Email" class="form-control" />
			    </div>
			</div>
			
			<div class="form-group">
			    <label for="Password" class="col-sm-2 control-label">Mot de passe </label>
			    <div class="col-sm-10">
			      <input type="password" id="Password" name="Password" class="form-control"/>
			    </div>
			</div>
			<hr/>
		  	<div class="form-group">
		  		<div class="col-md-2 col-md-offset-8">
		  			<a class="btn btn-default" role="button" href="/Java2015Project/Admin" style="width:100%!important;">Retours</a>
		  		</div>
		  		<div class="col-md-2">
		  			<input type="submit" value="Enregistrer" class="btn btn-primary" style="width:100%!important;"/>
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