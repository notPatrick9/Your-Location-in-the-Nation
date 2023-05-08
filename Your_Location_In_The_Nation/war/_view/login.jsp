<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


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
.button-pop{
	width:60%;
	height: 30px;
	border: none;
	outline: none;
	font-size: 15px;
	background:#222;
	color:#f5f5f5;
border-radius: 10px;
cursor: pointer;

	
}

.img-container .form .form-element input[type="text"]{
 margin-top: 5px;
	display:block;
	width: 100%;
	padding: 10px;
	outline: none;
	border: 1px solid #aaa;
	border-radius: 5px;
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
<html>
	<head>
		<title>Your_Location_In_The_Nation Login</title>
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
	<div class="img-container">
		<h2>Login</h2>
		<form action="${pageContext.servletContext.contextPath}/Login" method="post">
			
			<table>
				
					<tr>
					<td class="label">User Name:</td>
					<td><input type="text" name="username" size="12" value="${Username}" /></td>

				</tr>
				

				<tr>
					<td class="label">Password:</td>
					<td><input type="text" name="password" size="12" value="${Password}" /></td>
				</tr>
			</table>
			
			<input class="button-pop" type="Submit" name="login" value="Login">
			<input class="button-pop" type="Submit" name="createuser" value="Create Account">
		
		</form>
	</div>
	<style>
		body{
		background-color: lightblue;
		}
		</style>
	</body>
</html>