<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="../plugins/layui/css/layui.css">
	<link rel="stylesheet" type="text/css" href="../css/query.css">
	<link rel="stylesheet" type="text/css" href="../css/jquery.edittable.css">
	<link  rel="stylesheet" type="text/css" href="../css/main.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<div  style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">
		<form  class="add-user" action="">
			<div class="layui-form-item">
				<label class="layui-form-label">所属学院</label>
				<div class="layui-input-block">
					<select id="college" name="modules" lay-verify="required" lay-search="">
						<c:forEach items="${list}" var="c">  
							<option value="${c.id }">${c.collegeName }</option>
						</c:forEach>
					</select>
				</div>
			</div>
		<div class="layui-form-item">
			<label class="layui-form-label">用户名</label>
			<div class="layui-input-block">
				<input id="userName" type="text" name="title" value=""  class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">密码</label>
			<div class="layui-input-block">
				<input id="password" type="text" name="title" value="123456" class="layui-input">
			</div>
		</div>
		</form>
	</div>
</body>
<!-- jQuery -->
<script type="text/javascript" charset="utf8" src="../js/jquery-1.12.3.min.js"></script>
 
<!-- DataTables -->
<script type="text/javascript" charset="utf8" src="../js/dataTables.js"></script>
<link type="text/css" rel="stylesheet" href="../css/bootstrap.min.css">
<script type="text/javascript" charset="utf8" src="../js/bootstrap.min.js"></script>
	<script type="text/javascript" src="../plugins/layui/layui.js"></script>
</html>