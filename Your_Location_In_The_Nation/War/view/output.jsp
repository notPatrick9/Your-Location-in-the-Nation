<!DOCTYPE html>
<html>
<head>
<title>Florida</title>
<style>
.img-container{
    text-align: center;
}
line{
  border: 1px solid black;
  margin: auto;  
  padding: 0.1px;
  
}
</style>
</head>
<body>

 
 <a href="/Lab_1/welcomeG.html">
 <p>Home</p>
 </a>
<h1>Results!</h1>
<hr>

<h2>Based on the data you entered, Here are a list of information!</h2>

<?php

$crime = $_POST["crime"];
$cost = $_POST["CostOfLiving"];
$salary = $_POST["AvgSalary"];

//dont know the database info to fill in "select" and "from"
$sql = "SELECT  FROM  WHERE crime >= $crime AND CostOfliving >= $CostOfLiving AND AvgSalary >= $AvgSalary";
$result = $conn->query($sql);


if ($result->num_rows > 0) {
    echo "<ul>";
  
    while($row = $result->fetch_assoc()) {
        echo "<li>" . $row["crime"] . "</li>";
        echo "<li>" . $row["CostOfLiving"] . "</li>";
        echo "<li>" . $row["AvgSalary"] . "</li>";
    }
    echo "</ul>";
} else {
    echo "No data found.";
}

?>

</body>
</html> 
