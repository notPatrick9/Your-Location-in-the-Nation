<!DOCTYPE html>
<html>
<head>
  <title>Questions</title>
</head>
<body>

<h1>location in the nation </h1>
<hr>
<h3>Please answer each question.</h3>

<p>Which type of area do you prefer?:</p>

<form method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>">
  <input type="radio" id="city" name="AreaType" value="City">
  <label for="city">City<br>
  <input type="radio" id="rural" name="AreaType" value="Rural">
  <label for="rural">Rural</label><br>
  <input type="radio" id="sub" name="AreaType" value="Suburban">
  <label for="sub">Suburban</label>



<p>What type of climate would you like to live in?:</p>


  <input type="radio" id="trop" name="climate" value="Tropical">
  <label for="trop">Tropical<br>
  <input type="radio" id="polar" name="climate" value="Polar">
  <label for="polar">Polar</label><br>
  <input type="radio" id="temp" name="climate" value="Temperate">
  <label for="temp">Temperate</label>

<p>How important are the following on a scale of 1-10?</p>

  <label for="Crime">Crime Rate (between 1 and 10):</label>
  <input type="number" id="crime" name="Crime" min="1" max="10"><br><br>

  <label for="Cost">Cost of living (between 1 and 10):</label>
  <input type="number" id="cost" name="Cost" min="1" max="10"><br><br>

  <label for="salary">Avg. Salary (between 1 and 10):</label>
  <input type="number" id="salary" name="salary" min="1" max="10"><br><br>

<input type="submit" name="submit" value="Submit">

</form>

<?php

//dont know what to put here for server and request method
if ($_SERVER["REQUEST_METHOD"] == "POST") {
  
  $crime = $_POST['crime'];
  $cost = $_POST['CostOfLiving'];
  $salary = $_POST['AvgSalary'];
  


  header("Location: output.php");
  exit();
}
?>

</body>
</html>