<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>

<title>Results</title>
<style>
.img-container{
    text-align: center;
}
line{
  border: 1px solid black;
  margin: auto;  
  padding: 0.1px;
  
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
=======
<title>Results</title>
<style>
.img-container{
    text-align: center;
}
line{
  border: 1px solid black;
  margin: auto;  
  padding: 0.1px;
  
>>>>>>> refs/remotes/Ryan/master
}
</style>
</head>
<body>

 
 
<h1>Results!</h1>
<hr>

<h2>Based on the data you entered, Here are a list of information!</h2>


<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
</c:if>


<table>
				
				
				
				
				
				<tr>
					<td class="label">Crime rate:</td>
					<td>${CrimeRate}</td>
				</tr>
				<tr>
					<td class="label">Average salary: </td>
					<td>${AvgSalary}</td>
				</tr>
				<tr>
					<td class="label">Cost of living:</td>
					<td>${CostOfLiving}</td>
					
				</tr>
				<tr>
					<td class="label">County: </td>
					<td>${County}</td>
				
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
			<input type="Submit" name="submitquestions" value="Back to Questions">
			<input type="Submit" name="SaveLocation" value="Save this location">
		</form>




</body>
</html> 
