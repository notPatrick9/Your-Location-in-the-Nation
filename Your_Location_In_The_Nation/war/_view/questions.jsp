<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
  <title>Questions</title>
  
     <style>
        .img-container{
            text-align: center;
        
        }
        
body{
animation: transition 0.75s;
}
@keyframes transition {
    from{
opacity: 0;
transform: translate(-10px);

    }
  to{
    opacity: 1;
    transform: translate(0);
  }  
}

        </style>
  
</head>
<body>

<h1>Your Location In Then Nation </h1>
<hr>
<h3>Please answer each question.</h3>

	
		<form  action="${pageContext.request.contextPath}/questions" method="post">
		
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