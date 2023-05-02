<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
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

<h1>Your Location in the Nation!</h1>
<hr>

<div class="img-container">
<img src="https://suncatcherstudio.com/uploads/patterns/us-maps/svg/us-map-printable-filled-dddddd.png" alt="width:200px;height:150px;">
<h3>Hello! we are here to help you find a place to live.</h3>


<form action="${pageContext.servletContext.contextPath}/index" method="post">
		<div class = "Questions">
		<input type="Submit" name="GotoQuestions" value="Get Started!">
		</div>
		<div class = "PopLocs">
		<input type="Submit" name="GotoPopLocs" value="View Popular Locations!">
		</div>
		<div class = "SavedLocs">
		<input type="Submit" name="GotoSavedLocs" value="View Your Saved Locations!">
		</div>
		
		<div class = "Login">
		<input type="Submit" name="GotoLogin" value="Logout">
		</div>
	</form>





</div>


</body>
</html>