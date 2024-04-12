<?php

include 'db_connect.php';

if (mysqli_connect_errno($con))
{
   echo '{"query_result":"ERROR"}';
}
$login = $_GET['login'];
$tovar = $_GET['tovar'];

$sql = "SELECT * FROM rynok WHERE login='$login' AND tovar='$tovar' ORDER BY login asc";
	
	//getting images 
	$result = mysqli_query($con,$sql);
	
	//response array 
	$response = array(); 
	
	//traversing through all the rows 
	while($row = mysqli_fetch_array($result)){
		$temp = array(); 
		$temp['id']=$row['id'];
		$temp['tovar']=$row['tovar'];
		$temp['price']=$row['price'];
		$temp['login']=$row['login'];
		$temp['phonee']=$row['phone'];
		$temp['description']=$row['description'];
	
		array_push($response/*['users']*/,$temp);
	}
	//displaying the response 
	echo json_encode($response);