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

.button {
    background-color: #000000; 
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
}
.button1 {
  background-color: white;
  color: black;
  border: 2px solid #555555;
}

.button1:hover {
  background-color: #555555;
  color: white;
}

        </style>
</head>
<body>

<h1>Your Location In The Nation!</h1>
<hr>

<div class="img-container">
<img src="https://suncatcherstudio.com/uploads/patterns/us-maps/svg/us-map-printable-filled-dddddd.png" alt="width:200px;height:150px;">
<h3>Hello! we are here to help you find a place to live.</h3>
<button class="button button1" onclick="document.location='questions.html'">Get Started!</button>
</div>


</body>
</html>