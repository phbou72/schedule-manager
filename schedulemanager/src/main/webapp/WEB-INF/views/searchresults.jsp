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
					<h1>Résultats de recherche</h1>
				</div>
				<h4>
					${fn:length(courses)} résultat
					<c:if test="${fn:length(courses)} > 1">s</c:if>
					de recherche pour "${keywords}".
				</h4>
				<c:url var="courseurl" value="/course/"></c:url>
				<c:forEach var="course" items="${courses}">
					<h4>
						<a href="${courseurl}${course.acronym}"><b>${course.acronym} - ${course.title}</b></a>
					</h4>
					<div class="span12">${course.description}</div>
					<div class="span12"></div>
				</c:forEach>
			</div>
		</div>
	</div>
	<c:import url="footer.jsp" />
</body>
</html>
