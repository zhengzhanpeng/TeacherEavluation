<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>邯郸学院教职工测评成绩查询</title>
  
  <link href="../css/docs.min.css" rel="stylesheet">
  <link href="../css/main_index.css" rel="stylesheet">
  <script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
  <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

</head> 
<body>
  <div  class="header">
    </div>
    
    <div class="center" >
			<div class="center-title">
				<p>学院部门</p>
				<a href="../user/login"><button>教师登陆入口</button></a>
			</div>
			<div class="college">
			<ul>
			<c:forEach items="${list}" var="c"> 
				<li>
					<a href="college_score?collegeId=${c.id }">
						<span class="glyphicon glyphicon-home" aria-hidden="true"></span>${c.collegeName }
					</a>
				</li>
			</c:forEach>
			</ul>
			</div>
        
  </div>
  <div class="footer">
  <p>版权所有 © <a href="http://www.hdc.edu.cn">邯郸学院</a> 2016-2017</P>
  <p> 冀ICP备05002795号 地址：河北省邯郸市邯山区学院北路530号 邮政编码：056005　 </P>
 
  </div>
  <script>
	$(".center-title button").click(function(){
	//window.location.href = "";
	});
  </script>
</body>
</html>

