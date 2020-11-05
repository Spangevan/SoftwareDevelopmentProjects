<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<c:set var="title" value="National Park Geek" />
<title>${title}${param.pageTitle}</title>
<c:url value="/css/site.css" var="cssHref" />
<link rel="stylesheet" href="${cssHref}">
<link href="https://fonts.googleapis.com/css?family=Palanquin+Dark&display=swap" rel="stylesheet">
</head>

<div class="container">
	<body>

		<header class="header column">
			<c:url value="/" var="homePageHref" />
			<c:url value="/img/ark.jpg" var="logoSrc" />
			<c:url value="/survey" var="survey" />
			<c:url value="/searchResults" var="search" />

			<a href="${homePageHref}"> <img src="${logoSrc}"
				alt="National ARK Geek logo" />
			</a>
			<nav>
				<div class="topnav">
					<a class="active" href="${homePageHref}">Home</a> <a
						href="${survey}">Survey</a>
					<div class="search-container">
						<form action="${search}" method="GET">
							<input type="text" placeholder="Search.." name="search">
							<button id="searchButton" type="submit">
								Submit <i class="fa fa-search"></i>
							</button>
						</form>
					</div>
				</div>
			</nav>
		</header>