<?php

include 'db_connect.php';

if (mysqli_connect_errno($con))
{
   echo '{"query_result":"ERROR"}';
}

$tovar = $_GET['tovar'];
$price = $_GET['price'];
$description = $_GET['description'];
$phone = $_GET['phone'];
$login = $_GET['login'];



$vowels = array("/","~","`",",", "'", "|", "*", '"', "+", "^", ":", ";", "!", "#", "$", "%", "&", "(", ")", "[", "]", "{", "}", "?");

$tovar = str_replace($vowels, "", $tovar);
$price = str_replace($vowels, "", $price);
$description = str_replace($vowels, "", $description);
$phone = str_replace($vowels, "", $phone);
$login = str_replace($vowels, "", $login);

error_reporting(0);


    $result = mysqli_query($con,"INSERT INTO rynok VALUES (NULL,'$tovar','$price', '$phone', '$description','$login')");
    if($result == true) {
        echo '{"query_result":"SUCCESS"}';
    }
    else{
        echo '{"query_result":"FAILURE"}';
    }
    

mysqli_close($con);
?>
