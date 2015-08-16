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
	 
	$r=mysql_query("SELECT A.ID, A.FID_TIM1, A.FID_TIM2, B.NAZIV TIM1, C.NAZIV TIM2, A.FID_STADION, D.NAZIV STADION, A.GOL_TIM1, A.GOL_TIM2 
				FROM UTAKMICA A, TIM B, TIM C, STADION D
				WHERE B.ID = A.FID_TIM1
				AND C.ID = A.FID_TIM2
				AND D.ID = A.FID_STADION"); 
	while($row=mysql_fetch_array($r))
	{
		$row_array["ID"]=$row[0];
		$row_array["STADION"]=$row["STADION"];
		$row_array["TIM1"]=$row["TIM1"];
		$row_array["TIM2"]=$row["TIM2"];
		$row_array["GOL1"]=$row["GOL_TIM1"];
		$row_array["GOL2"]=$row["GOL_TIM2 "];
		
		array_push($return_arr,$row_array);
		
		
	}
	print(json_encode($return_arr)); 
	
	mysql_close($con);

?>