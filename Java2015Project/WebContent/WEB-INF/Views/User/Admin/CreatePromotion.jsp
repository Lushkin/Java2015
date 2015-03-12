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
<link rel="stylesheet" href="Content/css/site.css">
</head>
<body>
	<div class="container">
		<div class="header">
			<h2>Création de promotion</h2>
		</div>
		<hr/>
		<form action="/Java2015Project/Admin/CreatePromotion" method="POST">
			<div class="row">
				<label class="col-md-4">Nom de la promotion :</label>
				<div class="col-md-4"><input type="text" placeholder="ING2" id="PromotionName" name="PromotionName" class="form-control"/></div>
				<div class="col-md-4"><input type="submit" value="Ajouter une promotion" class="btn btn-primary"/></div>
			</div>
		</form>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</body>
</html>