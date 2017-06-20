<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>设置公式</title>
	<link rel="stylesheet" type="text/css" href="../plugins/layui/css/layui.css">
	<link rel="stylesheet" type="text/css" href="../css/query.css">
	<link rel="stylesheet" type="text/css" href="../css/jquery.edittable.css">
	<link  rel="stylesheet" type="text/css" href="../css/main.css">
</head>
<body>
	<div style="margin: 15px">
		<blockquote class="layui-elem-quote">
			<h2 style="font-size: 20px" class="layui-inline">
				<i class="layui-icon" style="font-size: 30px">&#xe629;</i> 设置公式</h2>
		</blockquote>
		<hr>
		<fieldset class="layui-elem-field" style="padding:10px 30px">
			<legend>修改计算公式</legend>
			<div class="row">
			  <div class="col-md-11"><div class="input-group">
					  <input type="text" class="form-control" value="第一学期期末成绩" disabled=" disabled" aria-describedby="basic-addon1">
					  <span class="input-group-addon" id="basic-addon1">=</span>
					  <input type="text" class="form-control" value="督导成绩" disabled=" disabled" aria-describedby="basic-addon1">
					  <span class="input-group-addon" id="basic-addon2">*</span>
					  <input type="text" class="form-control  superviseScoreN" placeholder="督导成绩系数" aria-describedby="basic-addon1">
					  <span class="input-group-addon" id="basic-addon1">+</span>
					  <input type="text" class="form-control" value="同行成绩" disabled=" disabled" aria-describedby="basic-addon1">
					  <span class="input-group-addon" id="basic-addon3">*</span>
					  <input type="text" class="form-control   peerScoreN" placeholder="同行成绩系数" aria-describedby="basic-addon1">
					  <span class="input-group-addon" id="basic-addon4">+</span>
					  <input type="text" class="form-control" value="学生评教成绩" disabled=" disabled" aria-describedby="basic-addon1">
					  <span class="input-group-addon" id="basic-addon1">*</span>
					  <input type="text" class="form-control studentScoreN" placeholder="学生评教成绩系数" aria-describedby="basic-addon1"> 
					</div></div>
			  <div class="col-md-1"><button id="btn-semester1" class=' layui-btn layui-btn-normal' type='button'>确认修改</button></div>
			</div>
			<div class="row">
			  <div class="col-md-11"><div class="input-group">
					  <input type="text" class="form-control" value="第二学期期末成绩" disabled=" disabled" aria-describedby="basic-addon1">
					  <span class="input-group-addon" id="basic-addon1">=</span>
					  <input type="text" class="form-control" value="督导成绩" disabled=" disabled" aria-describedby="basic-addon1">
					  <span class="input-group-addon" id="basic-addon2">*</span>
					  <input type="text" class="form-control  superviseScoreN" placeholder="督导成绩系数" aria-describedby="basic-addon1">
					  <span class="input-group-addon" id="basic-addon1">+</span>
					  <input type="text" class="form-control" value="同行成绩" disabled=" disabled" aria-describedby="basic-addon1">
					  <span class="input-group-addon" id="basic-addon3">*</span>
					  <input type="text" class="form-control   peerScoreN" placeholder="同行成绩系数" aria-describedby="basic-addon1">
					  <span class="input-group-addon" id="basic-addon4">+</span>
					  <input type="text" class="form-control" value="学生评教成绩" disabled=" disabled" aria-describedby="basic-addon1">
					  <span class="input-group-addon" id="basic-addon1">*</span>
					  <input type="text" class="form-control studentScoreN" placeholder="学生评教成绩系数" aria-describedby="basic-addon1"> 
					</div></div>
			  <div class="col-md-1"><button id="btn-semester2" class=' layui-btn layui-btn-normal' type='button'>确认修改</button></div>
			</div>
			<div class="row">
			  <div class="col-md-11"><div class="input-group">
					  <input type="text" class="form-control" value="学年折合成绩" disabled=" disabled" aria-describedby="basic-addon1">
					  <span class="input-group-addon" id="basic-addon1">=</span>
					  <input type="text" class="form-control" value="第一学期期末成绩" disabled=" disabled" aria-describedby="basic-addon1">
					  <span class="input-group-addon" id="basic-addon2">*</span>
					  <input type="text" class="form-control  semester1N" placeholder="第一学期期末成绩系数" aria-describedby="basic-addon1">
					  <span class="input-group-addon" id="basic-addon1">+</span>
					  <input type="text" class="form-control" value="第二学期期末成绩" disabled=" disabled" aria-describedby="basic-addon1">
					  <span class="input-group-addon" id="basic-addon3">*</span>
					  <input type="text" class="form-control     semester2N" placeholder="第二学期期末成绩系数" aria-describedby="basic-addon1">
					</div></div>
			  <div class="col-md-1"><button id="btn-year" class=' layui-btn layui-btn-normal' type='button'>确认修改</button></div>
			</div>
			<div class="row">
			  <div class="col-md-11"><div class="input-group">
					  <input type="text" class="form-control" value="业绩折合成绩" disabled=" disabled" aria-describedby="basic-addon1">
					  <span class="input-group-addon" id="basic-addon1">=</span>
					  <input type="text" class="form-control" value="学生评教成绩" disabled=" disabled" aria-describedby="basic-addon1">
					  <span class="input-group-addon" id="basic-addon2">*</span>
					  <input type="text" class="form-control  studentScoreN" placeholder="学生评教成绩系数" aria-describedby="basic-addon1">
					</div></div>
			  <div class="col-md-1"><button id="btn-work" class=' layui-btn layui-btn-normal' type='button'>确认修改</button></div>
			</div>
			<div class="row">
			  <div class="col-md-11"><div class="input-group">
					  <input type="text" class="form-control" value="学年质量考核成绩" disabled=" disabled" aria-describedby="basic-addon1">
					  <span class="input-group-addon" id="basic-addon1">=</span>
					  <input type="text" class="form-control" value="学年折合成绩" disabled=" disabled" aria-describedby="basic-addon1">
					  <span class="input-group-addon" id="basic-addon2">*</span>
					  <input type="text" class="form-control  M1" placeholder="学年折合成绩系数" aria-describedby="basic-addon1">
					  <span class="input-group-addon" id="basic-addon1">+</span>
					  <input type="text" class="form-control" value="业绩折合成绩" disabled=" disabled" aria-describedby="basic-addon1">
					  <span class="input-group-addon" id="basic-addon3">*</span>
					  <input type="text" class="form-control      M2" placeholder="业绩折合成绩系数" aria-describedby="basic-addon1">
					</div></div>
			  <div class="col-md-1"><button id="btn-all" class=' layui-btn layui-btn-normal' type='button'>确认修改</button></div>
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
	<script type="text/javascript">
		layui.use(['laydate', 'jquery', 'form'], function() {
		var form = layui.form();
			var $ = layui.jquery;
			$("#date").click(function() {
				layui.laydate({
					elem: this
				});
			});
		});
	</script>
	
	<script type="text/javascript"> 
	
	//第一学期期末成绩	ajax
       $("#btn-semester1").click(function(){
			var tempT=$(this);
			var superviseScoreN=tempT.parents(".row").eq(0).find(".superviseScoreN").val();
			var peerScoreN=tempT.parents(".row").eq(0).find(".peerScoreN").val();
			var studentScoreN=tempT.parents(".row").eq(0).find(".studentScoreN").val();
			layer.confirm("确认修改第一学期期末成绩公式为：</br>督导成绩  *  " + superviseScoreN+"  +  同行成绩  *  "+peerScoreN+"  +  学生评教成绩  *  "+studentScoreN+"  </br>吗？", {icon: 1, title:'确认修改操作'}, function(index){
			layer.close(index);
			$.ajax({
		              "url":"change_end_score1",
		              "data":{"v1":superviseScoreN,"v2":peerScoreN,"v3":studentScoreN},
		               "type":"post",
		               "error":function(){
		            	   layer.msg("服务器繁忙，请稍后再试", {icon: 5, anim: 0});
		               },
		               "success":function(response){
		                   if(response=="1") {
		                	   layer.msg('修改成功', {icon: 6,time: 700});
		                   } else {
		                	   layer.msg(response, {icon: 5, anim: 0});
		                   }

		               }
		           });		
			});
	   });
	   
	   
	   //第二学期期末成绩	ajax
       $("#btn-semester2").click(function(){
			var tempT=$(this);
			var superviseScoreN=tempT.parents(".row").eq(0).find(".superviseScoreN").val();
			var peerScoreN=tempT.parents(".row").eq(0).find(".peerScoreN").val();
			var studentScoreN=tempT.parents(".row").eq(0).find(".studentScoreN").val();
			layer.confirm("确认修改第二学期期末成绩公式为：</br>督导成绩  *  " + superviseScoreN+"  +  同行成绩  *  "+peerScoreN+"  +  学生评教成绩  *  "+studentScoreN+"  </br>吗？", {icon: 1, title:'确认修改操作'}, function(index){
			layer.close(index);
			$.ajax({
		              "url":"change_end_score2",
		              "data":{"v1":superviseScoreN,"v2":peerScoreN,"v3":studentScoreN},
		               "type":"post",
		               "error":function(){
		            	   layer.msg("服务器繁忙，请稍后再试", {icon: 5, anim: 0});
		               },
		               "success":function(response){
		                   if(response=="1") {
		                	   layer.msg('修改成功', {icon: 6,time: 700});
		                   } else {
		                	   layer.msg(response, {icon: 5, anim: 0});
		                   }

		               }
		           });		
			});
	   });
	   
	   //学年折合成绩	ajax
       $("#btn-year").click(function(){
			var tempT=$(this);
			var semester1N=tempT.parents(".row").eq(0).find(".semester1N").val();
			var semester2N=tempT.parents(".row").eq(0).find(".semester2N").val();
			layer.confirm("确认修改学年折合成绩公式为</br>第一学期期末成绩  *  " + semester1N+"  +  第二学期期末成绩  *  "+semester2N+"  </br>吗？", {icon: 1, title:'确认修改操作'}, function(index){
			layer.close(index);
			$.ajax({
		              "url":"change_m1",
		              "data":{"v1":semester1N,"v2":semester2N},
		               "type":"post",
		               "error":function(){
		            	   layer.msg("服务器繁忙，请稍后再试", {icon: 5, anim: 0});
		               },
		               "success":function(response){
		                   if(response=="1") {
		                	   layer.msg('修改成功', {icon: 6,time: 700});
		                   } else {
		                	   layer.msg(response, {icon: 5, anim: 0});
		                   }

		               }
		           });		
			});
	   });
	   
	   //业绩折合成绩	ajax
       $("#btn-work").click(function(){
			var tempT=$(this);
			var studentScoreN=tempT.parents(".row").eq(0).find(".studentScoreN").val();
			layer.confirm("确认修改业绩折合成绩公式为：</br>学生评教成绩  *  " + studentScoreN+"  </br>吗？", {icon: 1, title:'确认修改操作'}, function(index){
			layer.close(index);
			$.ajax({
		              "url":"change_m2",
		              "data":{"v1":studentScoreN},
		               "type":"post",
		               "error":function(){
		            	   layer.msg("服务器繁忙，请稍后再试", {icon: 5, anim: 0});
		               },
		               "success":function(response){
		                   if(response=="1") {
		                	   layer.msg('修改成功', {icon: 6,time: 700});
		                   } else {
		                	   layer.msg(response, {icon: 5, anim: 0});
		                   }

		               }
		           });		
			});
	   });
	   
	   
	   //学年质量考核成绩	ajax
       $("#btn-all").click(function(){
			var tempT=$(this);
			var M1=tempT.parents(".row").eq(0).find(".M1").val();
			var M2=tempT.parents(".row").eq(0).find(".M2").val();
			layer.confirm("确认修改学年质量考核成绩公式为</br>学年折合成绩  *  " +  M1+"  +  业绩折合成绩  *  "+M2+"  </br>吗？", {icon: 1, title:'确认修改操作'}, function(index){
			layer.close(index);
			$.ajax({
		              "url":"change_all",
		              "data":{"v1":M1,"v2":M2},
		               "type":"post",
		               "error":function(){
		            	   layer.msg("服务器繁忙，请稍后再试", {icon: 5, anim: 0});
		               },
		               "success":function(response){
		                   if(response=="1") {
		                	   layer.msg('修改成功', {icon: 6,time: 700});
		                   } else {
		                	   layer.msg(response, {icon: 5, anim: 0});
		                   }

		               }
		           });		
			});
	   });
   </script>
   

</body>
</html>