<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>用户信息</title>
	<link rel="stylesheet" type="text/css" href="../plugins/layui/css/layui.css">
	<link rel="stylesheet" type="text/css" href="../css/query.css">
	<link rel="stylesheet" type="text/css" href="../css/jquery.edittable.css">
	<link  rel="stylesheet" type="text/css" href="../css/main.css">
</head>
<body>
	<div style="margin: 15px">
		<blockquote class="layui-elem-quote">
			<h2 style="font-size: 20px" class="layui-inline">
				<i class="layui-icon" style="font-size: 30px">&#xe629;</i> 用户信息</h2>
		</blockquote>		
		<button id="addRow" class="layui-btn">添加用户</button>
		<fieldset class="layui-elem-field">
			<legend>数据列表</legend>
			<div class="layui-field-box">
				<table class="layui-table" id="layui-table">
				</table>
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
       var table = $('#layui-table').DataTable({
           "ajax": {
               "url": "get_users",
               "dataSrc": "data",//默认为data
               "type": "post",
               "error":function(){alert("服务器未正常响应，请重试");}
           },
           "columns": [
               { "data": "collegeName", "title":"所属学院","defaultContent":""},
			   { "data": "userName", "title":"用户名","defaultContent":""},
			   { "data": "phone", "title":"联系电话","defaultContent":""},
               { "data": null, "title":"操作","defaultContent": "<button class='resave-btn layui-btn layui-btn-normal' type='button'>重置密码</button>  <button class='layui-btn layui-btn-warm' type='button'>删除</button>"}
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
	   //删除单行
	   $("#layui-table tbody").on("click",".layui-btn-warm",function(){
           var nRow=$(this).parents('tr')[0];
		   var rowData = table.row(nRow).data();
			var itemName = rowData.userName;
				layer.confirm("确定删除用户   " + itemName+"   吗?", {icon: 3, title:'确认删除操作', anim: 6}, function(index){
					layer.close(index);
					$.ajax({
		              "url":"delete_user",
		              "data":{"userName":itemName},
		               "type":"post",
		               "error":function(){
		            	   layer.msg("服务器繁忙，请稍后再试", {icon: 5, anim: 0});
		               },
		               "success":function(response){
		                   if(response=="1") {
		                	   layer.msg('删除成功', {icon: 6,time: 700});
					           table.row(nRow).remove().draw(false);
		                   } else {
		                	   layer.msg(response, {icon: 5, anim: 0});
		                   }
						}			
					});})
       });
	   
	   //修改密码
	   $("#layui-table tbody").on("click",".resave-btn",function(){
           var nRow=$(this).parents('tr')[0];
		   var rowData = table.row(nRow).data();
			var itemName = rowData.userName;
			layer.open({
		        type: 1
		        ,title: ['重置密码', 'padding:none !important ;text-align:center'] //不显示标题栏
		        ,closeBtn: false
		        ,area: '500px;'
		        ,shade: 0.8
		        ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
		        ,btn: ['确定', '取消']
		        ,moveType: 1 //拖拽模式，0或者1
		        ,content: '<div  style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;"><form  class="add-user" action=""><div class="layui-form-item"><label class="layui-form-label">新密码</label><div class="layui-input-block"><input type="text" id="newPassword" name="title" value="123456"  class="layui-input"></div></div></form></div>'
				,success: function(layero){
		          var btn = layero.find('.layui-layer-btn');
		          btn.css('text-align', 'center');
		        },yes: function(index, layero){
				var newPassword=$("#newPassword").val();
					$.ajax({
		              "url":"set_user_password",
		              "data":{"userName":itemName,"password":newPassword},
		               "type":"post",
		               "error":function(){
		            	   layer.msg("服务器繁忙，请稍后再试", {icon: 5, anim: 0});
		               },
		               "success":function(response){
		                   if(response=="1") {
		                	   layer.msg('修改成功', {icon: 6,time: 700});
		                	   layer.close(index);
		                   } else {
		                	   layer.msg(response, {icon: 5, anim: 0});
		                   }

		               }
		           });	
				  }
				  ,btn2: function(index, layero){
				  }

		      });		
       });

	   
       //添加用户
	  $('#addRow').on( 'click', function () {
	  
				layer.open({
		        type: 2	
		        ,title: ['添加用户', 'padding:none !important ;text-align:center'] //不显示标题栏
		        ,closeBtn: false
		        ,area: ['500px','350px']
		        ,shade: 0.8
		        ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
		        ,btn: ['确定', '取消']
		        ,moveType: 1 //拖拽模式，0或者1
		        ,content: ['add_user_model', 'no']
				//,content:'<div  style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;"><form class="layui-form" action=""><select name="modules" lay-verify="required" lay-search=""><option value="">直接选择或搜索选择</option><option value="1">layer</option><option value="2">form</option><option value="3">layim</option><option value="4">element</option><option value="5">laytpl</option><option value="6">upload</option><option value="7">laydate</option><option value="8">laypage</option><option value="9">flow</option><option value="10">util</option><option value="11">code</option><option value="12">tree</option><option value="13">layedit</option><option value="14">nav</option><option value="15">tab</option><option value="16">table</option><option value="17">select</option><option value="18">checkbox</option><option value="19">switch</option><option value="20">radio</option></select></form></div>'
		        ,success: function(layero){
		          var btn = layero.find('.layui-layer-btn');
		          btn.css('text-align', 'center');
		        },yes: function(index, layero){
		        	var userName = layer.getChildFrame("#userName").val();
		        	var collegeId = layer.getChildFrame("#college").val();
		        	var password = layer.getChildFrame("#password").val();
					$.ajax({
		              "url":"add_user",
		              "data":{"userName":userName, "collegeId":collegeId, "password": password},
		               "type":"post",
		               "error":function(){
		            	   layer.msg("服务器繁忙，请稍后再试", {icon: 5, anim: 0});
		               },
		               "success":function(data1){
		            	   var arr = new Array();
		            	   arr = data1.split("-");
		            	   var response = arr[0];
		                   if(response=="1") {
		                	   layer.close(index);
		                	   layer.msg('添加成功', {icon: 6,time: 700});
							   var temp=table.row.add({"userName":userName, "collegeName": arr[2], "phone": arr[1]});	
								temp.draw();
		                   } else {
		                	   layer.msg(response, {icon: 5, anim: 0});
		                   }

		               }
		           });	
					
				  }
				  ,btn2: function(index, layero){
				  }
		      });
    } );		
   </script>


</body>
</html>