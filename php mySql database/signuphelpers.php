<?php

include 'db_connect.php';

if (mysqli_connect_errno($con))
{
   echo '{"query_result":"ERROR"}';
}

$login = $_GET['login'];
$password = $_GET['password'];
$age = $_GET['age'];
$telephone = $_GET['telephone'];
$email = $_GET['email'];
$address = $_GET['address'];
$name = $_GET['name'];



$vowels = array("/","~","`",",", "'", "|", "*", '"', "+", "^", ":", ";", "!", "#", "$", "%", "&", "(", ")", "[", "]", "{", "}", "?");

$login = str_replace($vowels, "", $login);
$password = str_replace($vowels, "", $password);
$age = str_replace($vowels, "", $age);
$telephone = str_replace($vowels, "", $telephone);
$email = str_replace($vowels, "", $email);
$address = str_replace($vowels, "", $address);

$name = str_replace($vowels, "", $name);
error_reporting(0);

    $result = mysqli_fetch_array(mysqli_query($con,"SELECT * FROM helpers where login='$login'"));

if($result[0] == ''){ 

    $result = mysqli_query($con,"INSERT INTO helpers VALUES (NULL,'$login',
    '$password','$age','$telephone','$email', '0', '$address', '$name')");
    if($result == true) {
        echo '{"query_result":"SUCCESS"}';
    }
    else{
        echo '{"query_result":"FAILURE"}';
    }
    
} else {
    echo '{"query_result":"HAVELOG"}';
}
mysqli_close($con);
?>

