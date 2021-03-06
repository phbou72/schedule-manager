<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Schedule Manager</title>
	<c:import url="common.jsp"></c:import>
	<script type="text/javascript" src="<c:url value="/resources/js/acceptschedule.js" />" /></script>
</head>
<body>
	<c:import url="header.jsp" />
	<div class="container">
		<div class="hero-unit">
			<h1>Visualiser un horaire</h1>
			<p>Choisissez l'horaire � visualiser :</p>
		</div>
		<c:choose>
			<c:when test="${ empty error }"></c:when>
			<c:when test="${error != 'success'}">
				<div class="alert alert-error">
					<button type="button" class="close" data-dismiss="alert">�</button>
					<strong>Erreur!</strong> Une erreur est survenue, veuillez r�essayer - ${error}.
				</div>
			</c:when>
			<c:when test="${ error == 'success'}">
				<div class="alert alert-success">
					<button type="button" class="close" data-dismiss="alert">�</button>
					<strong>Succ�s!</strong> La requ�te s'est effectu�e avec succ�s.
				</div>
			</c:when>
		</c:choose>
		<c:if test="${not empty schedules}">
			<c:url var="scheduleurl" value="schedule"></c:url>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Horaires :</th>
						<th></th>
						<th></th>
						<th></th>
						<sec:authorize access="hasRole('ROLE_Enseignant')">
							<th class="centered">Accepter</th>
							<th class="centered">Refuser</th>
							<th class="centered">Votre choix</th>
						</sec:authorize>
						<sec:authorize access="hasRole('ROLE_Responsable')">
							<th></th>
							<th class="centered">Choix</th>
						</sec:authorize>
					</tr>
				</thead>
				<c:forEach var="schedule" items="${schedules}">
					<tr id="${schedule.id}">
						<td class="span12">[${schedule.semester} ${schedule.year}] ${schedule.id}</td>
						<td class="centered"><c:choose>
								<c:when test="${schedule.score <= 300}">
									<span class="badge badge-success">${schedule.score}</span>
								</c:when>
								<c:when test="${schedule.score <= 600 && schedule.score > 300}">
									<span class="badge badge-warning">${schedule.score}</span>
								</c:when>
								<c:otherwise>
									<span class="badge badge-important">${schedule.score}</span>
								</c:otherwise>
							</c:choose></td>
						<td class="centered"><a class="btn btn-label" href="<c:url value="/${scheduleurl}/${schedule.id}/list" />"> <i class="icon-list"></i> Liste
						</a></td>
						<td class="centered"><a class="btn btn-label" href="<c:url value="/${scheduleurl}/${schedule.id}/calendar" />"> <i class="icon-calendar"></i> Calendrier
						</a></td>
						<sec:authorize access="hasRole('ROLE_Responsable')">
							<input type="hidden" id="approvedUsers" name="approvedUsers" value="${schedule.approvedUsers}" />
							<input type="hidden" id="refusedUsers" name="refusedUsers" value="${schedule.refusedUsers}" />
							<td class="centered"><a class="btn btn-danger" href="<c:url value="/${scheduleurl}/delete/${schedule.id}" />"> <i class="icon-trash icon-white"></i>
							</a></td>
							<td><a class="btn btn-info btnSeeAccepted"><i class="icon-comment icon-white"></i></a></td>
						</sec:authorize>
						<sec:authorize access="hasRole('ROLE_Enseignant')">
							<td><a class="badge badge-success btnAccept" href="#" title="Accepter l'horaire"><i class="icon icon-ok icon-white"></i></a></td>
							<td><a class="badge badge-important btnRefuse" href="#" title="Refuser l'horaire"><i class="icon icon-remove icon-white"></i></a></td>
							<td class="centered choice"><c:choose>
									<c:when test="${statuses[schedule.id] == 'Accepted'}">
										<b><span class="badge badge-success"><i class="icon icon-ok icon-white"></i></span></b>
									</c:when>
									<c:when test="${statuses[schedule.id] == 'Refused'}">
										<b><span class="badge badge-important"><i class="icon icon-remove icon-white"></i></span></b>
									</c:when>
									<c:otherwise>
										<b>?</b>
									</c:otherwise>
								</c:choose>
						</sec:authorize>
					</tr>
				</c:forEach>
			</table>

			<p>L�gende des scores d'horaires</p>
			Bon : <span class="badge badge-success">0-300</span>
	      	Moyen : <span class="badge badge-warning">301-600</span>
	      	Pauvre : <span class="badge badge-important">601++</span>
		</c:if>
	</div>
	<c:import url="footer.jsp" />
</body>
</html>
