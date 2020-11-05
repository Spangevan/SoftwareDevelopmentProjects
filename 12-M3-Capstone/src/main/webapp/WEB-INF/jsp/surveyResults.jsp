<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<c:set var="pageTitle" value="Survey Page"/>
<c:url var="parkDetailUrl" value="/parkDetailPage" >
<c:param name="parkcode" value="${park.parkCode}" />
</c:url>
</head>
<body>
<%@include file="common/header.jsp" %>

<h1>Thank You for Submitting!</h1>
<h2>America's Favorite National Parks: </h2>

	<c:forEach items="${favoriteParks}" var="park">
	<div class="favoriteContainer">
	<div class="favoriteImage"><a href="${parkDetailUrl }${park.parkCode}"><img src="img/parks/${park.parkCode.toLowerCase()}.jpg"/></a></div>
	<div class="favoriteText">
		<h2><c:out value="${park.parkName }"/></h2>
		<h3>(<c:out value="${park.count}"/>)Votes</h3>
	</div>
	</div>
	</c:forEach>
	
<footer>
<%@include file="common/footer.jsp"%>
</footer>
</body>
</html>