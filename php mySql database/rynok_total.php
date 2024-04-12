<?php

include 'db_connect.php';

if (mysqli_connect_errno($con))
{
   echo '{"query_result":"ERROR"}';
}
$sql = "SELECT image FROM images ORDER BY tags Asc";
$sql1 = "SELECT *  FROM rynok ORDER BY id Asc";
	
	//getting images 
	$result = mysqli_query($con,$sql);
	$results = mysqli_query($con,$sql1);
	//response array 
	$response = array(); 
	//$response['error'] = false; 
	$response/*['users'] */= array(); 
	
	//traversing through all the rows 
	while($row = mysqli_fetch_array($result) and $row1 = mysqli_fetch_array($results) ){
		$temp = array(); 
		
		$temp['image']=$row['image'];
		$temp['id']=$row1['id'];
		$temp['tovar']=$row1['tovar'];
		$temp['price']=$row1['price'];
		$temp['login']=$row1['login'];
		$temp['phonee']=$row1['phone'];
		$temp['description']=$row1['description'];
	
		array_push($response/*['users']*/,$temp);
	}
	//displaying the response 
	echo json_encode($response);