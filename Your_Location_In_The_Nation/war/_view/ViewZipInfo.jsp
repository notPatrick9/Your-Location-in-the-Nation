<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		
		
		<title>Zip Info</title>
		
		
		
		<style type="text/css">
		
		* {background-color: lightblue}
		
		
		
		
		.header {
			color: #4CAF50;
  			text-align: center;
   			font-family: Arial, Helvetica, sans-serif;		
		}
		
		.index {
			position: relative;
			left: -45%;
			bottom: 60%;
			background-color:  #4CAF50;
  			color: black;
  			border: 2px solid #4CAF50;
			height: 50px;
			width: 100px;
			
		}
		
		
		.LocationTable{
			position : absolute;
			top: 70%;
			left: 12%;
			border: 1px solid;
		
		}
		tr, th {
			border: 1px solid;
		}
	
		.error {
			position: relative;
			left: 130%;
			color: red;
			
		
		}
		
		.searchbar {
			text-align: center;
			height: 25px;
			width: 450px;
			
			
		}
		.Enter {
			background-color:  #4CAF50;
  			color: black;
  			border: 2px solid #4CAF50;
			position: relative;
			height: 75px;
			width: 150px;
			top: 10%;
		}
		.searchDiv {
			
  			margin: auto;
  			position: absolute;
  			text-align: center;
  			top: 40%;
  			bottom: 50%;
  			left: 25%;
  			
  			width: 50%;  
			
		}
		</style>
		
		
		
		
	
	
	
	
	</head>

	<body>

	
		<form action="${pageContext.servletContext.contextPath}/searchzip" method="post">
			
				<div class = "searchDiv">
				
				<input class = "searchbar" type="text" name="Zipcode" size="75" value="${Zipcode}" />
				
				<br>
				
				<input class = "Enter" type="Submit" name="submit" value="Search">
				
				</div>
				
			<div class="header">
				<p align="center">Enter the Zipcode that you would like more information about!</p>
			
				<input class = "index" type="Submit" name="index" value="Index">
			
			
				<hr  color="black">
		
		
			</div>
				
				
			</form>
				
				
				
				
			
					
			<div class = "LocationTable">
					
				
				<c:if test="${! empty errorMessage}">
					<div class="error">${errorMessage}</div>
				
				</c:if>
				
				
				
				
					
					
				<c:if test="${! empty Location}">
				
				
				<table>
					
					<tr>
						<th>Name</th>
						<th>County</th>
						<th>State</th>
						<th>Zipcode</th>
						<th>Income</th>
						<th>Cost of Living(with rent)</th>
						<th>Cost of Living(with mortgage)</th>
						<th>Cost of Living(with no mortgage)</th>
						<th>Crime Rate</th>
						<th>Region</th>
						<th>Population</th>
					
					
					
					</tr>
					
					
					<tr>
					
						<th>${Location.locationName}</th>
						<th>${Location.county}</th>
						<th>${Location.state}</th>
						<th>${Location.zipcode}</th>
						<th>${avgsal}</th>
						<th>${Location.costOfLivingRent}</th>
						<th>${Location.costOfLivingOwnWithMortgage}</th>
						<th>${Location.costOfLivingOwnNoMortgage}</th>
						<th>${Location.crimeRate}</th>
						<th>${Location.region}</th>
						<th>${Location.population}</th>
					
					
					
					
					</tr>
					
					
					
					
					</table>
				
				</c:if>
				
				
				</div>
					
		
				
			
		
	</body>
</html>