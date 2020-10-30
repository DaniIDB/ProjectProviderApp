<?php 
$message = '';
$error = $_GET['error'];
/*switch ($error) {
    case 'NOT_IN_DB':
        $message = "You Don´t have access to this page";
        break;
	default:
		if($error != ''){
		   $message = 	"Try later";
		}
        break;
}*/
$message = 	"Try later";
echo $message;

?>