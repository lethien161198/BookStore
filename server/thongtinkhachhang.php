<?php
	include "connect.php";
	//$tenkhachhang= "thien";
	//$sodienthoai="0354592277";
	//$email="thien@gmail.com";
	$tenkhachhang= $_POST['tenkhachhang'];
	$sodienthoai=$_POST['sodienthoai'];
	$email=$_POST['email'];
	if(strlen($tenkhachhang)>0&&strlen($email)>0&&strlen($sodienthoai)>0){
		$query="INSERT INTO donhang(id,tenkhachhang,sodienthoai,email) VALUES (null,'$tenkhachhang','$sodienthoai','$email')";
		if(mysqli_query($conn,$query)){
			$iddonhang= $conn->insert_id;
			echo $iddonhang; 
		}else{
			echo"Failed";
		}
	}else{
		echo "Kiểm tra lại dữ liệu";
	}
?>