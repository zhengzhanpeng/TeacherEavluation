<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>学生评教成绩录入</title>
	
	<link type="text/css" rel="stylesheet" href="../css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="../plugins/layui/css/layui.css">
	<link rel="stylesheet" type="text/css" href="../css/query.css">
	<link rel="stylesheet" type="text/css" href="../css/jquery.edittable.css">
	<link rel="stylesheet" type="text/css" href="http://cdn.datatables.net/1.10.15/css/jquery.dataTables.css">
	<script type="text/javascript" charset="utf8" src="../js/xlsx.full.min.js"></script>
	<script type="text/javascript" src="../plugins/layui/layui.js"></script>
	<script type="text/javascript" charset="utf8" src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" charset="utf8" src="http://cdn.datatables.net/1.10.15/js/jquery.dataTables.js"></script>
	<script type="text/javascript" charset="utf8" src="../js/bootstrap.min.js"></script>
	
	<script type="text/javascript" charset="utf8" src="../js/stuRating.js"></script>

</head>
<body>
	<div style="margin: 15px">
		<blockquote class="layui-elem-quote">
			<h2 style="font-size: 20px" class="layui-inline">
				<i class="layui-icon" style="font-size: 30px">&#xe629;</i> 学生评教成绩录入</h2>
		</blockquote>
		
		   
	  	<button id="batch-edit-btn" class="layui-btn">全部编辑</button>
	  	<button id="batch-save-btn" class="layui-btn">全部提交</button>
	  	<button id="batch-compute-btn" class="layui-btn">计算期末成绩</button>
		<div class="layui-box layui-upload-button ">
			<form target="layui-upload-iframe" method="post" key="set-mine" enctype="multipart/form-data" action="">
			<input type="file"  class="layui-upload-file" name="file" id="importData"> 
			<span class="layui-upload-icon">
			<i class="layui-icon"></i>Excel导入
			</span>
		</div>
		<hr>
		<fieldset class="layui-elem-field">
			<legend>数据列表</legend>
			<div class="layui-field-box">
				<table class="layui-table" id="layui-table">
				</table>
		</div>
		</fieldset>
	</div>
	
</body>
</html>