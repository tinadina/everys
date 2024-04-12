<?php

include 'db_connect.php';

if (mysqli_connect_errno($con))
{
   echo '{"query_result":"ERROR"}';
}
$login=$_GET['login'];
$id=$_GET['id'];
$sql = "SELECT * FROM users WHERE login='$login' OR id=$id ";
	
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
		$temp['login']=$row['login'];
		$temp['name']=$row['name'];
		$temp['lastname']=$row['lastname'];
		$temp['password']=$row['password'];
		$temp['detail']=$row['detail'];
		$temp['age']=$row['age'];

		$temp['telephone']=$row['telephone'];
		$temp['email']=$row['email'];
		$temp['card']=$row['card'];
		$temp['purpose']=$row['purpose'];
		$temp['earned']=$row['earned'];
		array_push($response/*['users']*/,$temp);
	}
	//displaying the response 
	echo json_encode($response);