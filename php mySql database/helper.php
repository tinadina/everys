<?php

include 'db_connect.php';

if (mysqli_connect_errno($con))
{
   echo '{"query_result":"ERROR"}';
}
$sql = "SELECT * FROM photos ORDER BY name";
	
	//getting images 
	$result = mysqli_query($con,$sql);
	
	//response array 
	$response = array(); 
	//$response['error'] = false; 
	$response/*['users'] */= array(); 
	
	//traversing through all the rows 
	while($row = mysqli_fetch_array($result)){
		$temp = array(); 
		
		$temp['id']=$row['id'];
		$temp['name']=$row['name'];
		$temp['url']=$row['url'];

		array_push($response/*['users']*/,$temp);
	}
	//displaying the response 
	echo json_encode($response);