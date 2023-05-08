
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>

<title>Results</title>
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
.middle{
            text-align: center;
        
        }
</style>
</head>
<body>

 
 
<h1>Results!</h1>
<hr>
<div class="img-container">
<h2>Based on the data you entered, Here are a list of information!</h2>


<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
</c:if>


<table>
				
				<tr>
					<td class="label">Name:</td>
					<td>${bestLoc.locationName}</td>
				</tr>
				<tr>
					<td class="label">County:</td>
					<td>${bestLoc.county}</td>
				</tr>
				
				<tr>
					<td class="label">State:</td>
					<td>${bestLoc.state}</td>
				</tr>
				<tr>
					<td class="label">Zipcode:</td>
					<td>${bestLoc.zipcode}</td>
				</tr>
				
				<tr>
					<td class="label">Average salary: </td>
					<td>${AvgSalary}</td>
				</tr>
				<tr>
					<td class="label">Cost of Living(Rent): </td>
					<td>${bestLoc.costOfLivingRent}</td>
				</tr>
				
				<tr>
					<td class="label">Cost of Living(Mortgage): </td>
					<td>${bestLoc.costOfLivingOwnWithMortgage}</td>
				</tr>
				
				<tr>
					<td class="label">Cost of Living(No Mortgage): </td>
					<td>${bestLoc.costOfLivingOwnNoMortgage}</td>
				</tr>
				
				<tr>
					<td class="label">Crime rate:</td>
					<td>${bestLoc.crimeRate}</td>
				</tr>
				
				<tr>
					<td class="label">Region:</td>
					<td>${bestLoc.region}</td>
				</tr>
				
				<tr>
					<td class="label">Population:</td>
					<td>${bestLoc.population}</td>
				</tr>
				
				<tr> 
					<td class="label">Fun things to do: </td>
					<td>${FunThingsToDo}</td>
				</tr>
			</table>
			
			<c:if test="${! empty success}">
			<div class="success">${success}</div>
			</c:if>
			 
			
		 
    
	<form action="${pageContext.servletContext.contextPath}/output" method="post">
			<input class="button button1"type="Submit" name="submitquestions" value="Back to Questions">
			<input class="button button2" type="Submit" name="SaveLocation" value="Save this location">
		</form>
</div>	

<style>
  body{
  background-color: lightblue;
  }
</style>



</body>
</html> 

