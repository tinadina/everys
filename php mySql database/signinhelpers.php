<?php

include 'db_connect.php';


if (mysqli_connect_errno($con))
{
   echo '{"query_result":"ERROR"}';
}

$login = $_GET['login'];
$password = $_GET['password'];


$vowels = array("/","~","`",",", "'", "|", "*", '"', "+", "^", ":", ";", "!", "#", "$", "%", "&", "(", ")", "[", "]", "{", "}", "?");

$login = str_replace($vowels, "", $login);
$password = str_replace($vowels, "", $password);


error_reporting(0);

$query = mysqli_query($con,"SELECT * FROM helpers WHERE login='$login'  LIMIT 1");
				$user_data=mysqli_fetch_array($query);

				if($user_data['password']==$password){
				   
	    			echo '{"query_result":"SUCCESS"}';

				 } 
				 else{
					echo '{"query_result":"FAILURE"}';
					}


mysqli_close($con);
?>
