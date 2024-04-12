<?php

include 'db_connect.php';

if (mysqli_connect_errno($con))
{
   echo '{"query_result":"ERROR"}';
}

$login = $_GET['login'];


$vowels = array("/","~","`",",", "'", "|", "*", '"', "+", "^", ":", ";", "!", "#", "$", "%", "&", "(", ")", "[", "]", "{", "}", "?");

$login = str_replace($vowels, "", $login);

error_reporting(0);

$query = mysqli_query($con,"SELECT * FROM users WHERE login='$login' ");
				$user_data=mysqli_fetch_array($query);

$heroes = array(); 

    $tablez = 'users';
    
    $sql = "SELECT `login`,`name`,`lastname`,`problem`,`age`,`telephone`,`email`,`card` FROM `$tablez` WHERE  login='$login' ";

    
    $stmt = $con->prepare($sql);
    $stmt->execute();
     
    //binding results for that statment 
    $stmt->bind_result($login, $name, $lastname, $problem, $age, $telephone, $email, $card);
     
     
    //looping through all the records
    while($stmt->fetch()){
     
     $temp = [
     'login'=>$login,
     'name'=>$name,
     'lastname'=>$lastname,
     'problem'=>$problem,
      'age'=>$age,
     'telephone'=>$telephone,
     'email'=>$email,
     'card'=>$card,
     ];
    }
     //pushing the array inside the hero array 
     array_push($heroes, $temp);



//displaying the data in json format 
echo json_encode($heroes, JSON_UNESCAPED_UNICODE);


mysqli_close($con);
?>
