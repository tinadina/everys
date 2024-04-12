<?php

include 'db_connect.php';


if (mysqli_connect_errno($con))
{
   echo '{"query_result":"ERROR"}';
}

$login = $_GET['Login'];
$vowels = array("/","~","`",",", "'", "|", "*", '"', "+", "^", ":", ";", "!", "#", "$", "%", "&", "(", ")", "[", "]", "{", "}", "?");

$login = str_replace($vowels, "", $login);

error_reporting(0);

$heroes = array(); 

if ($login=='all'){
    $tablez = 'lotery';
     $sql = "SELECT `id`,`price`,`stavka`,`vsego`,`uzhe`,`name`,`company` FROM `$tablez` ORDER BY id Desc";
    
    $stmt = $con->prepare($sql);
    $stmt->execute();
     
    //binding results for that statment 
    $stmt->bind_result($id, $price, $stavka, $vsego, $uzhe, $name,$company);
     
    //looping through all the records
    while($stmt->fetch()){
     
     //pushing fetched data in an array 
     $temp = [
       'id'=>$id,
     'price'=>$price,
     'stavka'=>$stavka,
     'vsego'=>$vsego,
     'uzhe'=>$uzhe,
     'name'=>$name,
     'company'=>$company
     ];
     
     //pushing the array inside the hero array 
     array_push($heroes, $temp);
    }
//displaying the data in json format 
echo json_encode($heroes, JSON_UNESCAPED_UNICODE);
}else {
     $tablez = 'lotery';
     $sql = "SELECT `id`,`price`,`stavka`,`vsego`,`uzhe`,`name`,`company` FROM `$tablez` WHERE name = '$login' ORDER BY id Desc";
    
    $stmt = $con->prepare($sql);
    $stmt->execute();
     
    //binding results for that statment 
    $stmt->bind_result($id, $price, $stavka, $vsego, $uzhe, $name,$company);
     
    //looping through all the records
    while($stmt->fetch()){
     
     //pushing fetched data in an array 
     $temp = [
       'id'=>$id,
     'price'=>$price,
     'stavka'=>$stavka,
     'vsego'=>$vsego,
     'uzhe'=>$uzhe,
     'name'=>$name,
     'company'=>$company
     ];
     
     //pushing the array inside the hero array 
     array_push($heroes, $temp);
    }
//displaying the data in json format 
echo json_encode($heroes, JSON_UNESCAPED_UNICODE);
}
mysqli_close($con);
?>