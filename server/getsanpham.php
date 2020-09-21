<?php
	include "connect.php";
	$page = $_GET['page'];
	//$idsp = 1;
	$idsp = $_POST['idsanpham'];
	$space = 5;
	$limit =($page-1)* $space;
	$mangsanpham = array();
	$query="SELECT * FROM sanpham WHERE idsanpham = $idsp LIMIT $limit,$space";
	$data=mysqli_query($conn,$query);
	while($row = mysqli_fetch_assoc($data)){
		array_push($mangsanpham,new Sanpham(
			$row['id'],
			$row['tensanpham'],
			$row['giasanpham'],
			$row['hinhsanpham'],
			$row['motesanpham'],
			$row['idsanpham']));	
	}
	echo json_encode($mangsanpham);
	class Sanpham{
		function Sanpham($id,$tensanpham,$giasanpham,$hinhsanpham,$motasanpham,$idsanpham){
			$this->id=$id;
	 		$this->tensanpham=$tensanpham;
	 		$this->giasanpham=$giasanpham;
	 		$this->hinhsanpham=$hinhsanpham;
	 		$this->motasanpham=$motasanpham;
	 		$this->idsanpham=$idsanpham;
		}
	}
?>