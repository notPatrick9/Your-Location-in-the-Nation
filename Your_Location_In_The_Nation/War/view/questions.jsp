<!DOCTYPE html>
<html>
<head>
  <title>Questions</title>
</head>
<body>

<h1>location in the nation </h1>
<hr>
<h3>Please answer each question.</h3>

	<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
	
		<form method="post" action="${pageContext.request.contextPath}/questions">
    <label for="CrimeRateFactor">On a scale from 1-10, how important is the Crime Rate of an area?:</label>
    <input type="number" name="crimeRateFactor" id="crimeRateFactor" min="0" max="10" required>
    <br><br>
    <label for="AveragesalaryFactor">On a scale from 1-10, how important is the average salary per household of an area?:</label>
    <input type="number" name="averageSalaryFactor" id="averageSalaryFactor" min="0" max="10" required>
    <br><br>
    <label for="CostOfLivingFactor">On a scale from 1-10, how important is the cost of living of an area?:</label>
    <input type="number" name="CostOfLivingFactor" id="CostOfLivingFactor" min="0" max="10" required>
    <br><br>
    <input type="submit" value="Submit">
</form>
		

</body>
</html>