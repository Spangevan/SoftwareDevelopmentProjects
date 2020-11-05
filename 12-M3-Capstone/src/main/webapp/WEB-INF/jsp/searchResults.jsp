<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:import url="/WEB-INF/jsp/common/header.jsp" />

<main class="content column"> 
	<c:forEach var="park" items="${park}">
	<div class="homepagePark">
		<div class="homepageImage">
			<c:url var="parkCodeURL" value="/parkDetailPage">
				<c:param name="parkCode" value="${park.parkCode}" />
			</c:url>

			<c:url var="image" value="/img/parks/${park.parkCode.toLowerCase()}.jpg" />
			<a href="${parkCodeURL}"><img src="${image}"
				class=homepageParkImage></a>
		</div>
		<div>
			<div class="homepageParkName">
				<p>
					<b><a href="${parkCodeURL}"><c:out value="${park.parkName}" /></a></b>
				</p>
			</div>
			<div class="homepageParkDescription">
				<c:out value="${park.parkDescription}" />

				<div>
					<p>State: ${park.state} | Fee: $${park.entryFee}0 | Total
						Miles Of Trail: ${park.milesOfTrail} | Total Campsites:
						${park.numberOfCampsites}</p>
				</div>
			</div>
		</div>
	</div>
</c:forEach> </main>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />