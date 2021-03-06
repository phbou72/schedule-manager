<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Schedule Manager</title>
	<c:import url="common.jsp"></c:import>
	<link rel="stylesheet" href='<c:url value="/resources/css/jquery-ui.css" />'" />
	<link href="<c:url value="/resources/css/createuser.css" />" rel="stylesheet">
	<script type="text/javascript" src="<c:url value="/resources/js/edituser.js" />" /></script>
	<script type="text/javascript" src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
</head>
<body>
	<c:import url="header.jsp" />
	<div class="container">
		<div class="row-fluid">
			<div class="hero-unit">
				<h1>Modification des r�les d'un usager</h1>
			</div>
			<c:choose>
				<c:when test="${ empty error }"></c:when>
				<c:when test="${ error == 'success'}">
					<div class="alert alert-success">
						<button type="button" class="close" data-dismiss="alert">�</button>
						<strong>Succ�s!</strong> La requ�te s'est effectu�e avec succ�s.
					</div>
				</c:when>
			</c:choose>
			<form action="/schedulemanager/changeroles/" method=POST scope="request" commandName="user">
				<div>
					<select id='userToChange' name='userToChange'>
						<option value="null">Usagers:</option>
						<c:forEach var="user" items="${users}">
							<option value="${user.idul}">${user.name}</option>
						</c:forEach>
					</select>
				</div>
				<div id="rolesCheckBoxes"></div>
			</form>
		</div>
	</div>
	<c:import url="footer.jsp" />
</body>
</html>