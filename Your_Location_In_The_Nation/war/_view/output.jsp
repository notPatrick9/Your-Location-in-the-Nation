<!DOCTYPE html>
<html>
<head>
<<<<<<< HEAD
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
<title>Florida</title>
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

 
 <a href="welcomeG.html">
 <p>Home</p>
 </a>
<h1>Results!</h1>
<hr>

<h2>Based on the data you entered, Here are a list of information!</h2>


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
					<td class="label">County: :</td>
					<td>${County}</td>
				
				</tr>
				<tr> 
					<td class="label">Fun things to do: </td>
					<td>${FunThingsToDo}</td>
				</tr>
			</table>





</body>
</html> 
