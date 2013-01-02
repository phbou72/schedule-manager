<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Schedule Manager</title>
	<c:import url="common.jsp"></c:import>
	<link href="<c:url value="/resources/css/createuser.css" />" rel="stylesheet">
	<script type="text/javascript" src="<c:url value="/resources/js/createuser.js" />" /></script>
</head>
<body>
	<c:import url="header.jsp" />
	<c:url value="/profile/${user.idul}" var="url" />
	<form action="${url}" method="POST" scope="request" commandName="user">
		<div class="container">
			<div class="row-fluid">
				<div class="hero-unit">
					<h1>Édition du profil</h1>
					<h3>&nbsp;&nbsp;&nbsp; - ${user.name}</h3>
				</div>
				<div class="span5">
					<table class="table table-striped">
						<tr>
							<td><b>IDUL : &nbsp;</b></td>
							<td><input type="text" name="idul" scope="request" value="${user.idul}" disabled="disabled"></br></td>
						</tr>
						<tr>
							<td><b>Nom : &nbsp;</b></td>
							<td><input type="text" name="name" scope="request" value="${user.name}"></br></td>
						</tr>
						<tr>
							<td><b>Courriel : &nbsp;</b></td>
							<td><input type="text" name="emailAddress" scope="request" value="${user.emailAddress}"></br></td>
						</tr>
						<tr>
							<td><b>Mot de passe : &nbsp;</b></td>
							<td><input type="password" name="password" scope="request" value="${user.password}"></br></td>
						</tr>
					</table>
					
					<input type="submit" class="btn btn-success pull-right" value="Sauvegarder">
				</div>
			</div>
		</div>
	</form>
	
	<c:import url="footer.jsp" />
</body>
</html>
