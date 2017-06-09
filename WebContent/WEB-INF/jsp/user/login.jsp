<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<title>登录</title>
		<link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="../css/login.css" />
	</head>

	<body class="beg-login-bg">
		<div class="beg-login-box">
			<header>
				<h1>邯郸学院教师考评系统</h1>
			</header>
			<div class="beg-login-main">
				<form class="layui-form" onsubmit="return false;">
					<div class="layui-form-item">
						<label class="beg-login-icon">
                        <i class="layui-icon">&#xe612;</i>
                    </label>
						<input type="text" id="user-name" name="userName" lay-verify="userName" autocomplete="off" placeholder="这里输入您的账号" class="layui-input">
					</div>
					<div class="layui-form-item">
						<label class="beg-login-icon">
                        <i class="layui-icon">&#xe642;</i>
                    </label>
						<input type="password" id="password" name="password" lay-verify="password" autocomplete="off" placeholder="这里输入您的密码" class="layui-input">
					</div>
					<div class="layui-form-item">
					</div>
						<div class="beg-pull-left beg-login-remember">
							<label>记住帐号？</label>
							<input type="checkbox" name="rememberMe" value="true" lay-skin="switch" checked title="记住帐号">
						</div>
						<div class="beg-pull-right">
							<button id="login" class="layui-btn layui-btn-primary" lay-submit>
                            <i class="layui-icon">&#xe650;</i> 登录
                        </button>
						</div>
						<div class="beg-clear"></div>
				</form>
			</div>
			<footer>
				<p id="time">copyRight  ©<a href="www.hdc.edu.cn">邯郸学院  </a>  2016-</p>
			</footer>
		</div>
		<script type="text/javascript" src="../plugins/layui/layui.js"></script>
		<script>
			var semester;
			
			function toIndex() {
				window.location.href="index";
			}
			layui.use(['layer', 'form'], function() {
				var layer = layui.layer,
					$ = layui.jquery,
					form = layui.form();
				 var time = new Date( ); //获得当前时间
			     var year = time.getFullYear();
			     $("#time").append(year);
				$("#login").click(function () {
					$.post("login", {
						"userName": $("#user-name").val()
						, "password": $("#password").val()
					}, function (data, status) {
						if(data == "1" || data == "2") {
							semester = data;
							layer.msg("登录成功。", {icon: 6, anim: 0});
							setTimeout("toIndex()", 500);
							
						} else {
							layer.msg(data, {icon: 5, anim: 6,offset: '100px'});
						}
					}); 
					
				})
				
			});
		</script>
	</body>

</html>