<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<c:set var="pageTitle" value="Survey Page" />
</head>
<body>
<%@include file="common/header.jsp"%>
<c:url var="submitSurveyUrl" value="/survey" />
<h2>What is Your Favorite Park?</h2>
<form:form action="${submitSurveyUrl }" method="POST" modelAttribute="survey" >
	<div class ="activity-form">
	<label for="parkCode">National Park</label>
	<select name="parkCode" id="parkCode">
	<option value="AABM">Aberration Best Ark Map</option>
	<option value="CVNP">Cuyahoga National Park</option>
	<option value="ENP">Everglades National Park</option>
	<option value=GCNP>Grand Canyon National Park</option>
	<option value="GNP">Glacier National Park</option>
	<option value="GSMNP">Great Smokey Mountains National Park</option>
	<option value="GTNP">Grand Tenton National Park</option>
	<option value="MRNP">Mount Rainier National Park</option>
	<option value="RMNP">Rocky Mountain National Park</option>
	<option value="YNP">Yellowstone National Park</option>
	<option value="YNP2">Yosemite National Park</option>
</select>
</div>
<div class="activity-form">
	<label for="emailAddress">Email Address:</label>
	<form:input path="emailAddress" />
	<form:errors path="emailAddress"/>
</div>
<div class="activity-form">
	<label for="state">State of Residence</label>
	<select name="state" id="state">
		<option value="AA">Ark</option>
		<option value="AL">Alabama</option>
		<option value="AK">Alaska</option>
		<option value="AZ">Arizona</option>
		<option value="AR">Arkansas</option>
		<option value="CA">California</option>
		<option value="CO">Colorado</option>
		<option value="CT">Connecticut</option>
		<option value="DE">Delaware</option>
		<option value="DC">District Of Columbia</option>
		<option value="FL">Florida</option>
		<option value="GA">Georgia</option>
		<option value="HI">Hawaii</option>
		<option value="ID">Idaho</option>
		<option value="IL">Illinois</option>
		<option value="IN">Indiana</option>
		<option value="IA">Iowa</option>
		<option value="KS">Kansas</option>
		<option value="KY">Kentucky</option>
		<option value="LA">Louisiana</option>
		<option value="ME">Maine</option>
		<option value="MD">Maryland</option>
		<option value="MA">Massachusetts</option>
		<option value="MI">Michigan</option>
		<option value="MN">Minnesota</option>
		<option value="MO">Missouri</option>
		<option value="MT">Montana</option>
		<option value="NE">Nebraska</option>
		<option value="NV">Nevada</option>
		<option value="NH">New Hampshire</option>
		<option value="NJ">New Jersey</option>
		<option value="NM">New Mexico</option>
		<option value="NY">New York</option>
		<option value="NC">North Carolina</option>
		<option value="ND">North Dakota</option>
		<option value="OH">Ohio</option>
		<option value="OK">Oklahoma</option>
		<option value="OR">Oregon</option>
		<option value="PA">Pennsylvania</option>
		<option value="RI">Rhode Island</option>
		<option value="SC">South Carolina</option>
		<option value="SD">South Dakota</option>
		<option value="TN">Tennessee</option>
		<option value="TX">Texas</option>
		<option value="UT">Utah</option>
		<option value="VT">Vermont</option>
		<option value="VA">Virginia</option>
		<option value="WA">Washington</option>
		<option value="WV">West Virginia</option>
		<option value="WI">Wisconsin</option>
		<option value="WY">Wyoming</option>		
</select>
</div>
<br>
<div class="activity-form" id="radio-buttons">Activity Level: 
<input type="radio" name="activityLevel" value="Inactive">Inactive
<input type="radio" name="activityLevel" value="Sedentary">Sedentary
<input type="radio" name="activityLevel" value="Moderately Active">Moderately Active
<input type="radio" name="activityLevel" value="Active">Active
<input type="radio" name="activityLevel" value="Extremely Active">Extremely Active
</div>
<br>

<div class="formSubmitButton">
	<input type="submit" value="Submit" />


</div>
</form:form>

<footer>
<%@include file="common/footer.jsp"%>
</footer>
</body>
</html>