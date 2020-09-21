<?php
	include "connect.php";
	$query="SELECT * FROM sanpham";
	$data=mysqli_query($conn,$query);
	$mangspmoinhat = array();
	while($row = mysqli_fetch_assoc($data)){
		array_push($mangspmoinhat,new Sanphammoinhat(
			$row['id'],
			$row['tensanpham'],
			$row['giasanpham'],
			$row['hinhsanpham'],
			$row['motesanpham'],
			$row['idsanpham']));	
	}
	echo json_encode($mangspmoinhat);
	 class Sanphammoinhat{
	 	function Sanphammoinhat($id,$tensanpham,$giasanpham,$hinhsanpham,$motasanpham,$idsanpham){
	 		$this->id=$id;
	 		$this->tensanpham=$tensanpham;
	 		$this->giasanpham=$giasanpham;
	 		$this->hinhsanpham=$hinhsanpham;
	 		$this->motasanpham=$motasanpham;
	 		$this->idsanpham=$idsanpham;

	 	}
	 }
?>