<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
	<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>第二学期教师成绩录入</title>
	<link rel="stylesheet" type="text/css" href="../plugins/layui/css/layui.css">
	<link rel="stylesheet" type="text/css" href="../css/query.css">
	<link rel="stylesheet" type="text/css" href="../css/jquery.edittable.css">
	<link rel="stylesheet" type="text/css" href="../css/docs.min.css" rel="stylesheet">
	<link  rel="stylesheet" type="text/css" href="../css/main.css">
</head>
<style>
.panel-primary>.panel-heading{
    color: #fff;
    background-color: #009688 !important;
    border-color: #009688 !important;
}
.nav-tabs a{
	color:#009688 ;
}
.panel-primary {
	border-color:#009688 !important;
}
</style>
<body>
	<div style="margin: 15px">
		<blockquote class="layui-elem-quote">
			<h2 style="font-size: 20px" class="layui-inline">
				<i class="layui-icon" style="font-size: 30px">&#xe629;</i> 成绩展示</h2>
		</blockquote>
		<hr>
		<fieldset class="layui-elem-field">
			<legend>数据列表</legend>
				<div class="panel panel-primary" >
				   <div class="panel-heading text-center" >
				   </div> 
				 <div class="panel">
					<div class="panel-heading">
					  <ul class="nav nav-tabs">
						<li id="l1" class="active"><a href="#semester1" data-toggle="tab">第一学期</a></li>
						<li id="l2" ><a href="#semester2" data-toggle="tab" >第二学期</a></li>
						<li id="l3" ><a href="#all" data-toggle="tab">学年考核成绩</a></li>         
					  </ul>
					</div>
				  </div> 
				  
				 <div class="tab-content" style="margin-top:-20px">
				   <div id="semester1" class="tab-pane active">
					  <table class="table table-bordered table-striped table-hover">
					  </table>
					</div>
					<div id="semester2" class="tab-pane active">
						<table class="table table-bordered table-striped table-hover">
					  </table>
					</div>       
					<div id="all" class="tab-pane active">
						<table class="table table-bordered table-striped table-hover">
					  </table>
					</div>
				</div>	  
       
				</div>
		</fieldset>
	</div>
	<link rel="stylesheet" type="text/css" href="http://cdn.datatables.net/1.10.15/css/jquery.dataTables.css">
 
<!-- jQuery -->
<script type="text/javascript" charset="utf8" src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
 
<!-- DataTables -->
<script type="text/javascript" charset="utf8" src="http://cdn.datatables.net/1.10.15/js/jquery.dataTables.js"></script>
<link type="text/css" rel="stylesheet" href="../css/bootstrap.min.css">
<script type="text/javascript" charset="utf8" src="../js/bootstrap.min.js"></script>
	<script type="text/javascript" src="../plugins/layui/layui.js"></script>
	
	
	<script>
		function c() {
			$("#semester2").attr("class", "tab-pane active hidden");
			$("#all").attr("class", "tab-pane active hidden");
		}
	
		$(document).ready(function(){
			setTimeout("c()", 500);
		$("#l1").click(function () {
			$("#semester2").attr("class", "tab-pane active hidden");
			$("#all").attr("class", "tab-pane active hidden");
		})
		$("#l2").click(function () {
			$("#semester2").attr("class", "tab-pane active show");
			$("#all").attr("class", "tab-pane active hidden");
		})
		$("#l3").click(function () {
			$("#semester2").attr("class", "tab-pane active hidden");
			$("#all").attr("class", "tab-pane active show");
		})
		
		 var table1 = $('#semester1 table').DataTable({
           "ajax": {
               "url": "get_semester1s",
               "dataSrc": "data",//默认为data
               "type": "post",
               "error":function(){layer.msg("服务器繁忙，请稍后再试", {icon: 5, anim: 0});}
           },
           "columns": [
               { "data": "name", "title":"姓名","defaultContent":""},
			   { "data": "position", "title":"职称","defaultContent":""},
               { "data": "superviseScore", "title":"督导成绩","defaultContent":""},
               { "data": "peerScore", "title":"同行成绩","defaultContent":""},
			   { "data": "studentScore", "title":"学生评教成绩","defaultContent":""},
			   { "data": "endScore", "title":"第一学期期末成绩","defaultContent":""}
           ],
           "language": {
               "sProcessing": "处理中...",
               "sLengthMenu": "显示 _MENU_ 项结果",
               "sZeroRecords": "没有匹配结果",
               "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
               "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
               "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
               "sInfoPostFix": "",
               "sSearch": "搜索:",
               "sUrl": "",
               "sEmptyTable": "表中数据为空",
               "sLoadingRecords": "载入中...",
               "sInfoThousands": ",",
               "oPaginate": {
                   "sFirst": "首页",
                   "sPrevious": "上页",
                   "sNext": "下页",
                   "sLast": "末页"
               },
               "oAria": {
                   "sSortAscending": ": 以升序排列此列",
                   "sSortDescending": ": 以降序排列此列"
               }
           }
       });
	   var table2 = $('#semester2 table').DataTable({
           "ajax": {
               "url": "get_semester2s",
               "dataSrc": "data",//默认为data
               "type": "post",
               "error":function(){layer.msg("服务器繁忙，请稍后再试", {icon: 5, anim: 0});}
           },
           "columns": [
              { "data": "name", "title":"姓名","defaultContent":""},
			   { "data": "position", "title":"职称","defaultContent":""},
               { "data": "superviseScore", "title":"督导成绩","defaultContent":""},
               { "data": "peerScore", "title":"同行成绩","defaultContent":""},
			   { "data": "studentScore", "title":"学生评教成绩","defaultContent":""},
			   { "data": "teachScore", "title":"教学业绩考核成绩","defaultContent":""},
			   { "data": "endScore", "title":"第二学期期末成绩","defaultContent":""}
           ],
           "language": {
               "sProcessing": "处理中...",
               "sLengthMenu": "显示 _MENU_ 项结果",
               "sZeroRecords": "没有匹配结果",
               "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
               "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
               "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
               "sInfoPostFix": "",
               "sSearch": "搜索:",
               "sUrl": "",
               "sEmptyTable": "表中数据为空",
               "sLoadingRecords": "载入中...",
               "sInfoThousands": ",",
               "oPaginate": {
                   "sFirst": "首页",
                   "sPrevious": "上页",
                   "sNext": "下页",
                   "sLast": "末页"
               },
               "oAria": {
                   "sSortAscending": ": 以升序排列此列",
                   "sSortDescending": ": 以降序排列此列"
               }
           }
       });
	   var table3 = $('#all table').DataTable({
           "ajax": {
               "url": "get_end_scores",
               "dataSrc": "data",//默认为data
               "type": "post",
               "error":function(){layer.msg("服务器繁忙，请稍后再试", {icon: 5, anim: 0});}
           },
           "columns": [
               { "data": "name", "title":"姓名","defaultContent":""},
			   { "data": "position", "title":"职称","defaultContent":""},
               { "data": "m1", "title":"学年折合成绩","defaultContent":""},
               { "data": "m2", "title":"业绩折合成绩","defaultContent":""},
			   { "data": "all1", "title":"学年质量考核成绩","defaultContent":""},
           ],
           "language": {
               "sProcessing": "处理中...",
               "sLengthMenu": "显示 _MENU_ 项结果",
               "sZeroRecords": "没有匹配结果",
               "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
               "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
               "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
               "sInfoPostFix": "",
               "sSearch": "搜索:",
               "sUrl": "",
               "sEmptyTable": "表中数据为空",
               "sLoadingRecords": "载入中...",
               "sInfoThousands": ",",
               "oPaginate": {
                   "sFirst": "首页",
                   "sPrevious": "上页",
                   "sNext": "下页",
                   "sLast": "末页"
               },
               "oAria": {
                   "sSortAscending": ": 以升序排列此列",
                   "sSortDescending": ": 以降序排列此列"
               }
           }
       });
			  
			  });
		

  </script>
	
</body>
</html>