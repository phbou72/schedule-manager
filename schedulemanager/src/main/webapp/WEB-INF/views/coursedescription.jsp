<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Schedule Manager</title>
	<c:import url="common.jsp"></c:import>
</head>
<body>
	<c:import url="header.jsp" />
	<div class="container">
		<div class="row-fluid">
			<div class="span12">
				<div class="hero-unit">
					<h1>Cours ${acronym}</h1>
					<br />
					<h3>- ${title}</h3>
				</div>
				<h3>Cycle : <a class="btn btn-large btn-primary disabled">${cycle}</a></h3>
				
				<h3>Description :</h3>
				<p>${description}</p>
				
				<h3>Pr�alables :</h3>
				<c:url var="courseurl" value="/course/"></c:url>
				<c:set var="count" value="0" scope="page" />
				<c:set var="courseCount" value="0" scope="page" />
				<c:forEach var="prerequisite" items="${prerequisites}">
					<p>
						<c:set var="acronyms" value="${prerequisite.acronyms}" />
						<c:set var="count" value="${count + 1}" scope="page" />
						<h4 class="inline">(</h4>
						<c:forEach var="course" items="${acronyms}">
							<c:set var="courseCount" value="${courseCount + 1}" scope="page" />
							<h4 class="inline">
								<a class="muted" href="${courseurl}${course}" target="_blank">${course}</a>
							</h4>
							<c:if test="${courseCount < fn:length(acronyms)}">
								<h4 class="inline text-info">&nbsp;OU</h4>
							</c:if>
						</c:forEach>
						<h4 class="inline">)</h4>
						<c:if test="${prerequisite.isConcomitant}">
							<span class="text-warning"><b>&nbsp; - Concomitant</b></span>
						</c:if>
						<c:if test="${count != fn:length(prerequisites)}">
							<h4 class="text-success">&nbsp;ET</h4>
						</c:if>
					</p>
				</c:forEach>
				
				<h3>Temps consacr� :</h3>
				<label class="control-label">Heures en classe :</label> <input type="text" disabled="disabled" class="input-small" value="${timeInClass}" />
				<label class="control-label" for="hours_labo">Heures en labo/travail dirig� : </label> <input type="text" disabled="disabled" class="input-small" value="${timeInLab}" />
				<label class="control-label" for="hours_home">Heures de travail personnel : </label> <input type="text" disabled="disabled" class="input-small" value="${timeAtHome}" />
			</div>
		</div>
	</div>
	<c:import url="footer.jsp" />
</body>
</html>
