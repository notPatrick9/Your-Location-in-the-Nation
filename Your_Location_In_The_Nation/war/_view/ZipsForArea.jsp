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
				
				<input type="Submit" name="submit" value="Get Zipcodes!">
				
			</form>
				
				
				
				<!-- These are just placeholders for now. The names in the Location model class will be different than these-->
			<c:if test="${! empty Zips}">
					<c:forEach items="${Zips}" var="Zips">
			      <tr class="Row">
			           <td class="nameCol">${Zips}</td>
			            		            
			        </tr>
			    </c:forEach>
			</c:if>
				
			
		
	</body>
</html>