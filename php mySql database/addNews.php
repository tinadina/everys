<?php

include 'db_connect.php';

if (mysqli_connect_errno($con))
{
   echo '{"query_result":"ERROR"}';
}

$id = $_GET['id'];
$title = $_GET['title'];
$desc = $_GET['desc'];


error_reporting(0);



    $result = mysqli_query($con,"INSERT INTO news VALUES (NULL,$id,'$title','$desc')");
    if($result == true) {
        echo '{"query_result":"SUCCESS"}';
    }
    else{
        echo '{"query_result":"FAILURE"}';
    }
    

mysqli_close($con);
?>

