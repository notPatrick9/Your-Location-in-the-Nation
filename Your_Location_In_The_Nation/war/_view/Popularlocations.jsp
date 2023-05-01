<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Popular Locations</title>
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
    
 
<h1>Here are some Popular Locations:</h1>
<body>
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
	
		<table>
			 <tr>
       			<td class="nameColHeading">Zipcode</td>
       			<td class="nameColHeading">Number of saves</td>       				
			   </tr>
			        
			   <c:forEach items="${PopularLocations}" var="PopularLocations">
			      <tr class="PopularLocationsRow">
			           <td class="nameCol">${PopularLocations.zipcode}</td>
			            <td class="nameCol">${PopularLocations.numberOfSaves}</td>			            
			        </tr>
			    </c:forEach>
			</table>
		<form action="${pageContext.servletContext.contextPath}/PopularLocations" method="post">
			<input type="Submit" name="submithome" value="Home">
		</form>
	</body>
</html>