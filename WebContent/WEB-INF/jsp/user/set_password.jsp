<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>修改密码</title>
	<link rel="stylesheet" type="text/css" href="../plugins/layui/css/layui.css">
	<link rel="stylesheet" type="text/css" href="../css/query.css">
	<link rel="stylesheet" type="text/css" href="../css/jquery.edittable.css">
	<link  rel="stylesheet" type="text/css" href="../css/main.css">
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
		.add{
			color:#ff3322;
		}
	</style>
</head>
<body>
	<div style="margin: 15px">
		<blockquote class="layui-elem-quote">
			<h2 style="font-size: 20px" class="layui-inline">
				<i class="layui-icon" style="font-size: 30px">&#xe629;</i> 修改密码</h2>
		</blockquote>

		<hr>
		<fieldset class="layui-elem-field">
			<legend>修改密码</legend>
			
      <form class="layui-form layui-form-pane" action="">
        <div class="layui-form-item">
          <label class="layui-form-label">原密码</label>
          <div class="layui-input-block">
            <input type="password" name="title" id="oldPassword" placeholder="请输入原密码"   class="layui-input">
          </div>
		  <span id="oldPasswordAdd" class="add"><span>
        </div>
		 <div class="layui-form-item">
          <label class="layui-form-label">新密码</label>
          <div class="layui-input-block">
            <input type="password"  name="title"  id="password" placeholder="请输入新密码"  class="layui-input">
          </div>
		  <span id="passwordAdd" class="add"><span>
        </div>
		<div class="layui-form-item">
          <label class="layui-form-label">确认密码</label>
          <div class="layui-input-block">
            <input type="password"  name="title"  id="password2" placeholder="请再次输入新密码"  class="layui-input">
          </div>
		  <span id="password2Add" class="add"><span>
        </div>
        <div class="layui-form-item">
          <button class="layui-btn"  lay-filter="formDemoPane"  onclick ="return false;" >确认提交</button>
        </div>
      </form>
 
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
			var $ = layui.jquery;
			$("#date").click(function() {
				layui.laydate({
					elem: this
				});
			});
		});
		var flagO=false,flagP1=false,flagP2=false;
		 //reg=/^[-+]?\d+$/; 
		 reg = /^([A-Z]|[a-z]|[0-9]|[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]){6,20}$/;
		$("#password2").blur(function(){		
		  if($("#password2").val()!=$("#password").val()){
			$("#password2Add").html("两次密码输入不同请检验");
			flagP2=false;
		  } 
		  else{
		  $("#password2Add").html("");
			flagP2=true;
		  }
		});
		$("#oldPassword").blur(function(){			
		  if($("#oldPassword").val()=="") {
		  flagO=false;
		  $("#oldPasswordAdd").html("密码不能为空");
		  }
		  else{
			flagO=true;
			 $("#oldPasswordAdd").html("");
		  }
		});
		$("#password").blur(function(){			
		  if(!reg.test($("#password").val())){
			$("#passwordAdd").html("密码格式不正确，请检查后输入");
			flagP1=false;
			
		  } 
		  else {
		  flagP1=true;
		  $("#passwordAdd").html("");
		  }
		});
		
		$(".layui-btn").click(function(){
		var data={"password":$("#oldPassword").val(),"newPassword1":$("#password").val(),"newPassword2":$("#password2").val()};
			if(flagO&&flagP1&&flagP2){
				$.ajax({
					"url":"set_password",
				   "data":data,
				   "type":"post",
				   "error":function(){
					   layer.msg("服务器未正常响应，请稍后再试", {icon: 5, anim: 0});
				   },
				   "success":function(response){
						 if(response=="1") {
						layer.msg('修改成功', {icon: 6,time: 700}); 
						}
						else {
							layer.msg(response, {icon: 5, anim: 0});
						}
					}
				});
			}
			else{
				layer.msg("您的输入有误，请认真核对", {icon: 5, anim: 0});
			}
	});			
	</script>
	
	<script type="text/javascript">

   </script>
	
</body>
</html>