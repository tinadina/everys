<?php

include 'db_connect.php';

if (mysqli_connect_errno($con))
{
   echo '{"query_result":"ERROR"}';
}

$login = $_GET['login'];
$name = $_GET['name'];
$lastname = $_GET['lastname'];
$password = $_GET['password'];
$fundid = $_GET['fundid'];
$problemid = $_GET['problemid'];
$problem = $_GET['problem'];
$age = $_GET['age'];
$telephone = $_GET['telephone'];
$email = $_GET['email'];
$card = $_GET['card'];
$purpose = $_GET['purpose'];






$vowels = array("/","~","`",",", "'", "|", "*", '"', "+", "^", ":", ";", "!", "#", "$", "%", "&", "(", ")", "[", "]", "{", "}", "?");

$login = str_replace($vowels, "", $login);
$name = str_replace($vowels, "", $name);
$lastname = str_replace($vowels, "", $lastname);
$password = str_replace($vowels, "", $password);
$fundid = str_replace($vowels, "", $fundid);
$problemid = str_replace($vowels, "", $problemid);
$problem = str_replace($vowels, "", $problem);
$age = str_replace($vowels, "", $age);
$telephone = str_replace($vowels, "", $telephone);
$email = str_replace($vowels, "", $email);
$card = str_replace($vowels, "", $card);
$purpose = str_replace($vowels, "", $purpose);

error_reporting(0);

    $result = mysqli_fetch_array(mysqli_query($con,"SELECT * FROM users where login='$login'"));

if($result[0] == ''){ 

    $result = mysqli_query($con,"INSERT INTO users VALUES (NULL,'$login','$name','$lastname',
    '$password','$fundid', '$problemid','$problem','$age','$telephone','$email','$card',$purpose,'','')");
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

