<?php

	header('Content-Type: text/html; charset=utf-8');
	$host="dance.hr";
	$uname="dancehr_sidrun";
	$pwd="dancehr_sidrun";
	$db="dancehr_sidrun";

	$return_arr = array();
	$con = mysql_connect($host,$uname,$pwd) or die("connection failed");
	mysql_query("SET NAMES 'utf8'");
	mysql_query("SET CHARACTER SET utf8");
	mysql_query("SET SESSION collation_connection = 'utf8_unicode_ci'");
	
	mysql_select_db($db,$con) or die("db selection failed");
	 
	// $id=$_REQUEST['id'];
	 
	$r=mysql_query("SELECT A.ID, A.FID_TIM, A.PREZIME, A.IME, B.NAZIV TIM_NAZIV FROM IGRAC A, TIM B WHERE B.ID = A.FID_TIM"); 
	while($row=mysql_fetch_array($r))
	{
		$row_array["ID"]=$row[0];
		$row_array["PREZIME"]=$row["PREZIME"];
		$row_array["IME"]=$row["IME"];
		$row_array["TIM"]=$row["TIM_NAZIV "];
		
		array_push($return_arr,$row_array);
		
		
	}
	print(json_encode($return_arr)); 
	
	mysql_close($con);

?>