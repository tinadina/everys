<?php

include 'db_connect.php';

if (mysqli_connect_errno($con))
{
   echo '{"query_result":"ERROR"}';
}

$aim = $_GET['aim'];
$price = $_GET['price'];
$description = $_GET['description'];
$phone = $_GET['phone'];
$card = $_GET['card'];
$login = $_GET['login'];



$vowels = array("/","~","`",",", "'", "|", "*", '"', "+", "^", ":", ";", "!", "#", "$", "%", "&", "(", ")", "[", "]", "{", "}", "?");

$aim = str_replace($vowels, "", $aim);
$price = str_replace($vowels, "", $price);
$description = str_replace($vowels, "", $description);
$phone = str_replace($vowels, "", $phone);
$card = str_replace($vowels, "", $card);
$login = str_replace($vowels, "", $login);

error_reporting(0);


    $result = mysqli_query($con,"INSERT INTO sharing VALUES (NULL,'$aim','$price', '$description', '$phone', '$card','$login')");
    if($result == true) {
        echo '{"query_result":"SUCCESS"}';
    }
    else{
        echo '{"query_result":"FAILURE"}';
    }
    

mysqli_close($con);
?>
