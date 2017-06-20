<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>学院信息</title>
	<link rel="stylesheet" type="text/css" href="../plugins/layui/css/layui.css">
	<link rel="stylesheet" type="text/css" href="../css/query.css">
	<link rel="stylesheet" type="text/css" href="../css/jquery.edittable.css">
	<link rel="stylesheet" type="text/css" href="../css/main.css">
	<style>
		.layui-form-label{
		width:112px  !important; 
		}
		.layui-form-item{
		margin-left:20px  !important;
		}
		.layui-input-block{
		width:250px  !important;
		}
	</style>
</head>
<body>
	<div style="margin: 15px">
		<blockquote class="layui-elem-quote">
			<h2 style="font-size: 20px" class="layui-inline">
				<i class="layui-icon" style="font-size: 30px">&#xe629;</i> 学院信息</h2>
		</blockquote>

		<hr>
		<fieldset class="layui-elem-field">
			<legend>学院信息</legend>
			
      <form class="layui-form layui-form-pane" action=""  onsubmit="return false;">
        <div class="layui-form-item">
          <label class="layui-form-label">学院名称</label>
          <div class="layui-input-block">
            <input type="text" name="title" disabled="disabled" value="${college.collegeName }"  class="layui-input">
          </div>
        </div>
		 <div class="layui-form-item">
          <label class="layui-form-label">联系电话</label>
          <div class="layui-input-block">
            <input id="phone" type="text" name="title" placeholder="请输联系方式" value="${college.phone }" class="layui-input">
          </div>
        </div>
        <div class="layui-form-item">
          <button id="save" class="layui-btn" lay-submit="" lay-filter="formDemoPane">保存修改</button>
        </div>
      </form>
 
		</fieldset>
		
	</div>
	<link rel="stylesheet" type="text/css" href="../css/dataTables.css">
 
<!-- jQuery -->
<script type="text/javascript" charset="utf8" src="../js/jquery-1.12.3.min.js"></script>
 
<!-- DataTables -->
<script type="text/javascript" charset="utf8" src="../js/dataTables.js"></script>
<link type="text/css" rel="stylesheet" href="../css/bootstrap.min.css">
<script type="text/javascript" charset="utf8" src="../js/bootstrap.min.js"></script>
	<script type="text/javascript" src="../plugins/layui/layui.js"></script>
	<script>
	layui.use(['layer', 'form'], function() {
		var layer = layui.layer,
			$ = layui.jquery,
			form = layui.form();
		$("#save").click(function () {
			if(flagP1){
				$.post("set_phone", {phone: $("#phone").val()}, function (data, staus) {
		   			if(data == 1) {
		   				layer.msg("保存成功");
		   			} else {
		   				layer.msg(data);
		   			}
		   		})
			}
			else{
				layer.msg("您的输入有误，请认真核对", {icon: 5, anim: 0});
			}
	   		
	   	});
	});
	
	
	var flagP1=false;
	reg = /^[0-9]*$/;
	$("#phone").blur(function(){		
		if(!reg.test($("#phone").val())){
			layer.msg("您的输入有误，请认真核对", {icon: 5, anim: 0});
			flagP1=false;
		  }
		else{
			flagP1=true;
		}
	  } );
	</script>
</body>
</html>