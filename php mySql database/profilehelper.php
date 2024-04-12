<?php

include 'db_connect.php';

if (mysqli_connect_errno($con))
{
   echo '{"query_result":"ERROR"}';
}
$login=$_GET['login'];
$sql = "SELECT * FROM helpers WHERE login='$login' ";
	
	//getting images 
	$result = mysqli_query($con,$sql);
	

	$response = array(); 

	$response/*['users'] */= array(); 
	
	//traversing through all the rows 
	while($row = mysqli_fetch_array($result)){
		$temp = array(); 
		
		$temp['age']=$row['age'];
		$temp['telephone']=$row['telephone'];
		$temp['email']=$row['email'];
		$temp['earned']=$row['earned'];
		$temp['city']=$row['city'];
		$temp['name']=$row['name'];
	
		array_push($response/*['users']*/,$temp);
	}
	//displaying the response 
	echo json_encode($response);