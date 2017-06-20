<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<head>
<meta charset="utf-8">
<title>邯郸学院教师考评系统</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="../css/index.css" media="all" />
<link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="../css/global.css" media="all" />
<link rel="stylesheet" type="text/css"
	href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
</head>

<body>
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header header header-demo">
			<div class="layui-main">
				<div class="admin-login-box">
					<a class="logo" style="left: 0;" href="#"> <span
						style="font-size: 22px;"><img src="../images/logo.png"  style="width:40px;height:40px;margin:0 5px 5px 0">教师考评系统</span>
					</a>
					<div class="admin-side-toggle">
						<i class="fa fa-bars" aria-hidden="true"></i>
					</div>
				</div>
				<ul class="layui-nav admin-header-item">
					<li class="layui-nav-item" id="semester"><a
						href="javascript:;"></a></li>

					<li class="layui-nav-item"><a href="javascript:;">浏览网站</a></li>
					<li class="layui-nav-item"><a href="javascript:;"
						class="admin-header-user"> <img src="../images/0.jpg" /> <span>邯郸学院</span>
					</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;"><i class="fa fa-user-circle"
									aria-hidden="true"></i> 学院信息</a>
							</dd>
							<dd>
								<a href="javascript:;"><i class="fa fa-gear"
									aria-hidden="true"></i> 设置</a>
							</dd>
							<dd id="lock">
								<a href="javascript:;"> <i class="fa fa-lock"
									aria-hidden="true"
									style="padding-right: 3px; padding-left: 1px;"></i> 锁屏 (Alt+L)
								</a>
							</dd>
							<dd>
								<a href="exit"><i class="fa fa-sign-out"
									aria-hidden="true"></i> 注销</a>
							</dd>
						</dl></li>
				</ul>
				<ul class="layui-nav admin-header-item-mobile">
					<li class="layui-nav-item"><a href="exit"><i
							class="fa fa-sign-out" aria-hidden="true"></i> 注销</a></li>
				</ul>
			</div>
		</div>
		<div class="layui-side layui-bg-black" id="admin-side">
			<div class="layui-side-scroll" id="admin-navbar-side"
				lay-filter="side">
				
				</div>
		</div>
		<div class="layui-body"
			style="bottom: 0; border-left: solid 2px #1AA094;" id="admin-body">
			<div class="layui-tab admin-nav-card layui-tab-brief"
				lay-filter="admin-tab">
				<ul class="layui-tab-title">
					<li class="layui-this"><i class="fa fa-dashboard"
						aria-hidden="true"></i> <cite>面板主页</cite></li>
				</ul>
				<div class="layui-tab-content"
					style="min-height: 150px; padding: 5px 0 0 0;">
					<div class="layui-tab-item layui-show">
						<iframe src="../html/main.html"></iframe>
					</div>
				</div>
			</div>
		</div>
		<div class="layui-footer footer footer-demo" id="admin-footer">
			<div class="layui-main">
				<p>
					<p id="time">copyRight  ©<a href="www.hdc.edu.cn">邯郸学院  </a>  2016-</p>
				</p>
			</div>
		</div>
		<div class="site-tree-mobile layui-hide">
			<i class="layui-icon">&#xe602;</i>
		</div>
		<div class="site-mobile-shade"></div>

		<!--锁屏模板 start-->
		<script type="text/template" id="lock-temp">
				<div class="admin-header-lock" id="lock-box">
					<div class="admin-header-lock-img">
						<img src="../images/0.jpg"/>
					</div>
					<div class="admin-header-lock-name" id="lockUserName">邯郸学院</div>
					<input type="text" class="admin-header-lock-input" value="输入密码解锁.." name="lockPwd" id="lockPwd" />
					<button class="layui-btn layui-btn-small" id="unlock">解锁</button>
				</div>
			</script>
		<!--锁屏模板 end -->

		<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
		<script type="text/javascript" src="../js/jquery.tabledit.min.js"></script>
		<script type="text/javascript" src="../plugins/layui/layui.js"></script>
		<script type="text/javascript" src="../datas/nav_admin.js"></script>

		<script type="text/javascript" src="../js/index_admin.js"></script>
		<script>
		var time = new Date( ); //获得当前时间
	     var year = time.getFullYear();
	     $("#time").append(year);
			</script>
	</div>
</body>
<script type="text/javascript"> $(document).ready(function(){ });</script>
</html>