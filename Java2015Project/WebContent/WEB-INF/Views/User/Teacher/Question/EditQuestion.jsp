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
<link rel="stylesheet" href="../Content/css/site.css">
</head>
<body>
	<div class="container">
		<div class="header row">
			<div class="col-md-10">
				<h3>Espace enseignant > Edition de question</h3>
			</div>
			<div class="col-md-2" style="text-align: right;">
				<a class="logout" role="button" href="/Java2015Project/">Déconnexion</a>
			</div>
		</div>
		<hr />

		<form action="/Java2015Project/Teacher/EditQuestion?id=${Question.getId()}" method="POST"
			class="form-horizontal">

			<div class="form-group">
				<label for="Question" class="col-sm-2 control-label">Question
				</label>
				<div class="col-sm-10">
					<input type="text" placeholder="question" id="Question"
						name="Question" class="form-control"
						value="${Question.getContent()}" />
				</div>
			</div>

			<div class="form-group">
				<label for="Categorie" class="col-sm-2 control-label">Catégorie
				</label>
				<div class="col-sm-10">
					<select id="Categorie" name="Categorie" class="form-control">
						<option value="0">Selectionner une catégorie</option>
						<c:forEach items="${Categories}" var="c">
							<c:choose>
								<c:when test="${c.getId() == Question.getCategories().getId()}">
									<option selected="true" value="${c.getId()}">${c.getName()}</option>
								</c:when>
								<c:otherwise>
									<option value="${c.getId()}">${c.getName()}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="form-group">
				<label for="Points" class="col-sm-2 control-label">Points </label>
				<div class="col-sm-10">
					<input type="text" placeholder="points" id="Points" name="Points"
						class="form-control" value="${Question.getPonderation() }" />
				</div>
			</div>

			<p>Réponses :</p>
			<div id="answers">
				<c:forEach items="${Question.getQuestionAnswerses()}" var="a" varStatus="i">
					<div class="form-group">
						<label for="Answer${i.index}" class="col-sm-2 control-label">Réponse
						</label>
						<div class="col-sm-8">
							<input type="text" placeholder="réponse" id="Answer${i.index}"
								name="Answer${i.index}" class="form-control" value="${a.getAnswers().getValue()}"/>
						</div>
						<div class="col-sm-2 checkbox">
							<label> 
								<input type="checkbox" id="AnswerCb${i.index}" name="AnswerCb${i.index}" ${a.getAnswers().getIsCorrect() == 1 ? "checked" : "" }> Correcte
							</label>
						</div>
					</div>
				</c:forEach>
			</div>
			<button class="btn btn-default center-block" type="button"
				onclick="addAnswer()">+</button>

			<hr />
			<div class="form-group">
				<div class="col-md-2 col-md-offset-8">
					<a class="btn btn-default" role="button"
						href="/Java2015Project/Teacher" style="width: 100% !important;">Retour</a>
				</div>
				<div class="col-md-2">
					<input type="submit" value="Enregistrer" class="btn btn-primary"
						style="width: 100% !important;" />
				</div>
			</div>
		</form>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		function addAnswer() {
			var answersNb = $('#answers').children().length;

			$("#answers")
					.append(
							'<div class="form-group">'
									+ '<label for="Answer'+ answersNb +'" class="col-sm-2 control-label">Réponse'
									+ '</label>'
									+ '<div class="col-sm-8">'
									+ '<input type="text" placeholder="réponse" id="Answer'+ answersNb +'" name="Answer'+ answersNb +'" class="form-control" />'
									+ '</div>'
									+ '<div class="col-sm-2 checkbox">'
									+ '<label>'
									+ '<input type="checkbox" id="AnswerCb'+ answersNb +'" name="AnswerCb'+ answersNb +'"> Correcte'
									+ '</label>' + '</div>' + '</div>');
		}
	</script>
</body>
</html>