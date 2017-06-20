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
	<link  rel="stylesheet" type="text/css" href="../css/main.css">
</head>
<body>
	<div style="margin: 15px">
		<blockquote class="layui-elem-quote">
			<h2 style="font-size: 20px" class="layui-inline">
				<i class="layui-icon" style="font-size: 30px">&#xe629;</i> 学院信息</h2>
		</blockquote>
		
			
		<button id="addRow" class="layui-btn">添加学院</button>
	  	<button id="batch-edit-btn" class="layui-btn">全部编辑</button>
	  	<button id="batch-save-btn" class="layui-btn">全部保存</button>
		<hr>
		<fieldset class="layui-elem-field">
			<legend>数据列表</legend>
			<div class="layui-field-box">
				<table class="layui-table" id="layui-table">
				  <colgroup>
				    <col width="400">
				    <col>
				    <col>
				  </colgroup>
				</table>
		</div>
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
	<script type="text/javascript">
		layui.use(['laydate', 'jquery', 'form'], function() {
			var $ = layui.jquery;
			$("#date").click(function() {
				layui.laydate({
					elem: this
				});
			});
		});
	</script>
	
	<script type="text/javascript">
  $(function(){
       var table = $('#layui-table').DataTable({
           "ajax": {
               "url": "get_colleges",
               "dataSrc": "data",//默认为data
               "type": "post",
               "error":function(){alert("服务器未正常响应，请重试");}
           },
           "columns": [
               { "data": "collegeName", "title":"学院名称","defaultContent":""},
			   { "data": "phone", "title":"电话","defaultContent":""},
               { "data": null, "title":"操作","defaultContent": "<button class='edit-btn layui-btn layui-btn-normal' type='button'>编辑</button>  <button class='layui-btn layui-btn-warm' type='button'>删除</button>"}
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
 
       $("#layui-table tbody").on("click",".edit-btn",function(){
           var tds=$(this).parents("tr").children();
           $.each(tds, function(i,val){
               var jqob=$(val);
               if(i < 0 || jqob.has('button').length ){return true;}//跳过第1项 序号,按钮
               var txt=jqob.text();
               var put=$("<input type='text'>");
               put.val(txt);
               jqob.html(put);
           });
           $(this).html("保存");
           $(this).toggleClass("edit-btn layui-btn layui-btn-normal");
           $(this).toggleClass("save-btn layui-btn ");
		   $('#layui-table tbody input').addClass("form-control");
       });
	   
	   
	   //删除单行
	   $("#layui-table tbody").on("click",".layui-btn-warm",function(){
           var nRow=$(this).parents('tr')[0];
		   var rowData = table.row(nRow).data();
		   var id = rowData.id;
			var itemName = rowData.collegeName;
			delete rowData["date"];
			if(!id){
				layer.confirm("确定删除未保存的新增学院吗 ？ ", {icon: 3, title:'确认删除操作', anim: 6}, function(index){
					layer.close(index);
		            layer.msg('删除成功', {icon: 6,time: 700});
					table.row(nRow).remove().draw(false);
				});
			}else{
				layer.confirm("确定删除教师   " + itemName+"   吗?", {icon: 3, title:'确认删除操作', anim: 6}, function(index){
					layer.close(index);
					$.ajax({
		              "url":"delete_college",
		              "data":{"collegeId":id},
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
		           });			
		});}
       });
 
	   $("#layui-table tbody").on("click",".save-btn",function(){
           var row=table.row($(this).parents("tr"));
           var thisObj=$(this);
		    var tds=$(this).parents("tr").children();
			$.each(tds, function(i,val){
				var jqob=$(val);
				//把input变为字符串
				if(!jqob.has('button').length){
				var txt=jqob.children("input").val();
				table.cell(jqob).data(txt);//修改DataTables对象的数据
							   
				}
			});
           var data=row.data();
		   if (!data.id) 	data.id="";
           delete data["date"];
           $.ajax({
              "url":"save_college",
               "data":data,
               "type":"post",
               "error":function(){
            	   $.each(tds, function(i,val){
					   var jqob=$(val);
					   if(i < 0 || jqob.has('button').length ){return true;}//跳过第1项 序号,按钮
					   var txt=jqob.text();
					   var put=$("<input type='text' class='form-control'>");
					   put.val(txt);
					   jqob.html(put);
				   });
            	   layer.msg("您的输入有误，请认真核对", {icon: 5, anim: 0});
               },
               "success":function(data1){
            	   var arr= new Array();
            	   arr = data1.split("-");
            	   response = arr[0];
            	   var savedId = arr[1];
                   if(response=="1") {
						
						$.each(tds, function(i,val){
						var jqob=$(val);
						//把input变为字符串
							if(!jqob.has('button').length){
								var txt=jqob.children("input").val();
								jqob.html(txt);		   
							}
						});
						thisObj.html("编辑");
					    thisObj.toggleClass("edit-btn layui-btn layui-btn-normal");
						thisObj.toggleClass("save-btn layui-btn ");
						data.id=savedId;
						layer.msg('保存成功', {icon: 6,time: 700}); 
				   }
				   else {
						$.each(tds, function(i,val){
					   var jqob=$(val);
					   if(i < 0 || jqob.has('button').length ){return true;}//跳过第1项 序号,按钮
					   var txt=jqob.text();
					   var put=$("<input type='text' class='form-control'>");
					   put.val(txt);
					   jqob.html(put);
				   });
					   layer.msg(response, {icon: 5, anim: 0});
				   }
               }
           });
       });



 
       //批量点击编辑按钮
       $("#batch-edit-btn").click(function(){
           $(".edit-btn").click();
       });
       $("#batch-save-btn").click(function(){
           $(".save-btn").click();
       });
	  $('#addRow').on( 'click', function () {
        var temp=table.row.add({});	
		   temp.draw();
		   $(temp.node()).find(".edit-btn").click();
    } );	
   });
    
   </script>
	
</body>
</html>