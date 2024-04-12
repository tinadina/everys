<?php

include 'db_connect.php';

if (mysqli_connect_errno($con))
{
   echo '{"query_result":"ERROR"}';
}
$sql = "SELECT fund FROM funds ORDER BY id_fund Asc";
$sql1 = "SELECT problem  FROM problems ORDER BY id_problem Asc";
	
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
		
		$temp['fund']=$row['fund'];
		$temp['problem']=$row1['problem'];
	
		array_push($response/*['users']*/,$temp);
	}
	//displaying the response 
	echo json_encode($response);