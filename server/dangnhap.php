<?php
	include("connect.php");
	//$taikhoan="thien@gmail.com";
	//$matkhau="123456789";
	$taikhoan=$_POST['taikhoan'];
	$matkhau=$_POST['matkhau'];
	
	$query="SELECT * FROM taikhoan WHERE taikhoan = '$taikhoan' AND matkhau = '$matkhau' ";
	$data=mysqli_query($conn,$query);
	
	if( mysqli_num_rows($data)>0 ){
		echo "success";
	}else{
		echo "error";
	}
?>