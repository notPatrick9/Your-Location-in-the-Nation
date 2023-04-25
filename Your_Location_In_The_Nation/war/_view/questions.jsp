<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<<<<<<< HEAD
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
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
	
		<form action="${pageContext.servletContext.contextPath}/questions" method="post">
			<table>
				<tr>
					<td class="label">Crime rate:</td>
					<td><input type="text" name="crimeRate" size="12" value="${crimeRate}" /></td>
				</tr>
				<tr>
					<td class="label">Average salary:</td>
					<td><input type="text" name="averageSalary" size="12" value="${averageSalary}" /></td>
				</tr>
				<tr>
					<td class="label">Cost of living:</td>
					<td><input type="text" name="costOfLiving" size="12" value="${costOfLiving}" /></td>
				</tr>
			</table>
			<input type="Submit" name="submit" value="QuestionsDone">
		</form>
	</body>
</html>