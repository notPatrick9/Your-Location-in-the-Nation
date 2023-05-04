<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Zip Info</title>
		<style type="text/css">
		.error {
			color: red;
		}
		
		td.label {
			text-align: right;
		}
		</style>
	</head>

	<body>
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
	
		<form action="${pageContext.servletContext.contextPath}/searchzip" method="post">
			
				<tr>
					<td class="label">Enter the Zipcode that you would like more information about! </td>
					<td><input type="text" name="Zipcode" size="12" value="${Zipcode}" /></td>
				</tr>
				
				<input type="Submit" name="submit" value="View Zipcode Info!">
				
			</form>
				
				
				
				<!-- These are just placeholders for now. The names in the Location model class will be different than these-->
			<c:if test="${! empty Location}">
					<tr>
					<td class="label">Here is some information:</td>
					<td class="label">Location Name: </td>
					<td>${Location.city}</td>
					<td class="label">county: </td>
					<td>${Location.county}</td>
					<td class="label">State: </td>
					<td>${Location.state}</td>
					<td class="label">Zipcode: </td>
					<td>${Location.zipcode}</td>
					<td class="label">Income: </td>
					<td>${avgsal}</td>
					<td class="label">Rent: </td>
					<td>${Location.costOfLivingRent}</td>
					<td class="label">CostOfLivingOwnWithMortgage: </td>
					<td>${Location.costOfLivingOwnWithMortgage}</td>
					<td class="label">No mortgage: </td>
					<td>${Location.costOfLivingOwnNoMortgage}</td>
					<td class="label">CrimeRate: </td>
					<td>${Location.crimeRate}</td>
					<td class="label">Region: </td>
					<td>${Location.region}</td>
					<td class="label">Population: </td>
					<td>${Location.population}</td>
				</tr>
			</c:if>
				
			
		
	</body>
</html>