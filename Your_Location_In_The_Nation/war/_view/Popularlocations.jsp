<!DOCTYPE html>
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
    
 <a href="welcomeG.html">
    <p>Home</p>
    </a>
<h1>Here are some Popular Locations:</h1>
<hr>
<c:forEach items="${popularLocations}" var="location">
            <tr>
         (needs editing probably)       <td>${location.name}</td>
        (needs editing probably)        <td>${location.numSaves}</td>
            </tr>
        </c:forEach>

</body>
</html>