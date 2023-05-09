<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		
		
		<title>Zip for area</title>
		
		
		
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
		
		
		.Zips{
			position : absolute;
			top: 70%;
			left: 12%;
			
		
		}
	
		.error {
			position: relative;
			left: 110%;
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

	
		<form action="${pageContext.servletContext.contextPath}/viewzips" method="post">
			
				<div class = "searchDiv">
				
				<input class = "searchbar" type="text" name="AreaName" size="75" value="${AreaName}" />
				
				<br>
				
				<input class = "Enter" type="Submit" name="submit" value="Search">
				
				</div>
				
			<div class="header">
				<p align="center">Search for an area name to get zipcodes for the area!</p>
			
				<input class = "index" type="Submit" name="index" value="Index">
			
			
				<hr  color="black">
		
		
			</div>
				
				
			</form>
				
			
				
		<div class = "Zips">
			
			<c:if test="${! empty errorMessage}">
					<div class="error">${errorMessage}</div>
				
				</c:if>
			
			
			<c:if test="${! empty Zips}">
					<c:forEach items="${Zips}" var="Zips">
			      <tr class="Row">
			           <td class="nameCol">${Zips}</td>
			            		            
			        </tr>
			    </c:forEach>
			</c:if>
			
			
		</div>
			
		
	</body>
</html>