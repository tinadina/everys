<?php

include 'db_connect.php';

if (mysqli_connect_errno($con))
{
   echo '{"query_result":"ERROR"}';
}
$sql = "SELECT * FROM sharing ORDER BY login asc";
	
	//getting images 
	$result = mysqli_query($con,$sql);
	
	//response array 
	$response = array(); 
	
	//traversing through all the rows 
	while($row = mysqli_fetch_array($result)){
		$temp = array(); 
		
		$temp['aim']=$row['aim'];
		$temp['price']=$row['price'];
		$temp['login']=$row['login'];
		$temp['card']=$row['card'];
		$temp['phonee']=$row['phone'];
		$temp['who']=$row['who'];
	
		array_push($response/*['users']*/,$temp);
	}
	//displaying the response 
	echo json_encode($response);