<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Saved</title>
    <style>
 .img-container{
            text-align: center;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%,-50%);
width: 380px;
padding: 20px 30px;
background:#fff;
box-shadow: 2px 2px 5px 5px rgba(0,0,0,0.15);
border-radius: 10px;

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
h1 {
  color: #4CAF50;
  text-align: center;
   font-family: Arial, Helvetica, sans-serif;
}

h2 {
  color: #4CAF50;
   font-family: Arial, Helvetica, sans-serif;
}

.button {
    background-color: #4CAF50; 
  border: none;
  color: white;
  padding: 16px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  transition-duration: 0.4s;
  cursor: pointer;
  border-radius: 10px;
}
.button1 {
  background-color:  #4CAF50;
  color: black;
  border: 2px solid #4CAF50;
}

.button1:hover {
  background-color: lightblue;
  color: white;
}

.button2 {
  background-color: #4CAF50;
  color: black;
  border: 2px solid #4CAF50;
}

.button2:hover {
  background-color: lightblue;
  color: white;
}

table, th, td {
  border: 1px solid;
  width: 100%;
color: #4CAF50;
}
    
        </style>
</head>
<body>
    
 
<h1>Here are your saved locations: </h1>
<hr>
<body>
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
	<div class="img-container">
		<table>
			 <tr>
       			<td class="nameColHeading">Zipcode</td>
       			     				
			   </tr>
			        
			   <c:forEach items="${SavedLocations}" var="SavedLocations">
			      <tr class="PopularLocationsRow">
			           <td class="nameCol">${SavedLocations}</td>
			            		            
			        </tr>
			    </c:forEach>
			</table>
		<form action="${pageContext.servletContext.contextPath}/PopularLocations" method="post">
			<input class= "button button1"type="Submit" name="submithome" value="Home">
		</form>
  </div>

 




  <style>
    body{
      background-color: lightblue;
    }
  </style>
</body>
</html>