<?php

include 'db_connect.php';

if (mysqli_connect_errno($con))
{
   echo '{"query_result":"ERROR"}';
}

$id = $_GET['id'];


$vowels = array("/","~","`",",", "'", "|", "*", '"', "+", "^", ":", ";", "!", "#", "$", "%", "&", "(", ")", "[", "]", "{", "}", "?");

$id = str_replace($vowels, "", $id);


error_reporting(0);

$query = mysqli_query($con,"SELECT `user_id`,`title`,`description` FROM news WHERE user_id=$id  ");
				$user_data=mysqli_fetch_array($query);

$heroes = array(); 


    $tablez = 'news';
    
    $sql = "SELECT `user_id`,`title`,`description` FROM `$tablez` WHERE user_id=$id";
    
    $stmt = $con->prepare($sql);
    $stmt->execute();
     
    //binding results for that statment 
    $stmt->bind_result($id, $title, $description);
     
    //looping through all the records
    while($stmt->fetch()){
     
     //pushing fetched data in an array 
     $temp = [
     'id'=>$id,
     'title'=>$title,
     'description'=>$description
     ];
     
     //pushing the array inside the hero array 
     array_push($heroes, $temp);
    }

 
 
//displaying the data in json format 
echo json_encode($heroes, JSON_UNESCAPED_UNICODE);


mysqli_close($con);
?>
