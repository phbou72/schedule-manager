<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
				<h1>Cours disponibles</h1>
				<c:if test="${not empty courses}">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>Titre</th>
								<th>Credits</th>
								<th>Cycle</th>
								<th class="centered">D�tails</th>
								<th class="centered">Actions</th>
							</tr>
						</thead>
						<c:url var="courseurl" value="/course/"></c:url>
						<c:forEach var="course" items="${courses}">
							<tr id="${course.acronym}">
								<td><b>${course.acronym} - ${course.title}</b></td>
								<td>${course.credits}</td>
								<td>${course.cycle}</td>
								<td class="centered"><a class="btn btn-info" href="${courseurl}${course.acronym}"><i class="icon-info-sign icon-white"></i></a></td>
								<td class="centered"><a class="btn btn-success" href="addcourse?acronym=${course.acronym}"><i class="icon-plus-sign icon-white"></i></a></td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
			</div>
		</div>
	</div>
	<c:import url="footer.jsp" />
</body>
</html>
