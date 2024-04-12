<?php


$HostName = "localhost";

//Define your database username here.
$HostUser = "id12969253_sanamichos";

//Define your database password here.
$HostPass = "t1r8u0f3ll";

//Define your database name here.
$DatabaseName = "id12969253_truffel";

// Create connection
$conn = new mysqli($HostName, $HostUser, $HostPass, $DatabaseName);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 

$Fund=$_GET['fund'];
$Earning=$_GET['earning'];


$sql1 = "SELECT earning FROM funds WHERE fund='$Fund'";
	
	//getting images 
	$resultt = mysqli_query($conn,$sql1);
	//response array 
	$response = array(); 
	//$response['error'] = false; 
	$response/*['users'] */= array(); 
	$row = mysqli_fetch_array($resultt);
	$firstearning = $row['earning'];
	//traversing through all the rows 
    $int = (int)$firstearning;
    $newearning=(int)$Earning;
    $total = $int + $newearning;
    
$sql = "UPDATE funds SET earning='$total'  WHERE fund='$Fund'";

if ($conn->query($sql) === TRUE) {
     echo '{"query_result":"SUCCESS"}';
} else {
     echo '{"query_result":"FAILURE"}';
}

$conn->close();
?>

