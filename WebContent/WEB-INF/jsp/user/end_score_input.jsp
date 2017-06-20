<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>单学期成绩补录</title>
	<link type="text/css" rel="stylesheet" href="../css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="../plugins/layui/css/layui.css">
	<link rel="stylesheet" type="text/css" href="../css/query.css">
	<link rel="stylesheet" type="text/css" href="../css/jquery.edittable.css">
	<link rel="stylesheet" type="text/css" href="http://cdn.datatables.net/1.10.15/css/jquery.dataTables.css">
</head>
<body>
	<div style="margin: 15px">
		<blockquote class="layui-elem-quote">
			<h2 style="font-size: 20px" class="layui-inline">
				<i class="layui-icon" style="font-size: 30px">&#xe629;</i> 单学期成绩补录</h2>
		</blockquote>  
	  	<button id="batch-edit-btn" class="layui-btn">全部编辑</button>
		<hr>
		<fieldset class="layui-elem-field">
			<legend>数据列表</legend>
			<div class="layui-field-box">
				<table class="layui-table" id="layui-table">
				</table>
		</div>
		</fieldset>
	</div>
	<script type="text/javascript" charset="utf8" src="../js/xlsx.full.min.js"></script>
	<script type="text/javascript" src="../plugins/layui/layui.js"></script>
	<script type="text/javascript" charset="utf8" src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" charset="utf8" src="http://cdn.datatables.net/1.10.15/js/jquery.dataTables.js"></script>
	<script type="text/javascript" charset="utf8" src="../js/bootstrap.min.js"></script>
	
	<script>

	$(function(){
		layui.use(['layer', 'form'], function(){
		  var layer = layui.layer
		  ,form = layui.form();
		});

		var table = $('#layui-table').DataTable({
		   "ajax": {
			   "url": "end_score_input",
			   "dataSrc": "data",//默认为data
			   "type": "post",
			   "error":function(){layer.msg("服务器繁忙，请稍后再试", {icon: 5, anim: 0});}
		   },
		   "columns": [
				{ "data": "jobNumber", "title":"工号","defaultContent":""},
				{ "data": "name", "title":"姓名","defaultContent":""},
				{ "data": "semester", "title":"学期","defaultContent":""},
				{ "data": "endScore", "title":"期末成绩","defaultContent":""},
			   { "data": null, "title":"操作","defaultContent": "<button class='edit-btn layui-btn layui-btn-normal' type='button'>编辑</button> "}
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
		//点击编辑按钮
	   $("#layui-table tbody").on("click",".edit-btn",function(){
			var preBrother = $(this).parent().prev();
			var txt =  preBrother.text().trim();
			if(txt=='')txt = 0;
			var html="<input type='text' value='"+txt+"'>";
			preBrother.html(html);

			$(this).html("保存");
			$(this).toggleClass("edit-btn layui-btn layui-btn-normal");
			$(this).toggleClass("save-btn layui-btn ");
			$('#layui-table tbody input').addClass("form-control");
	   });
	   
		//点击保存按钮
	   $("#layui-table tbody").on("click",".save-btn",function(){
		   var thisObj=$(this);
		   var row=table.row($(this).parents("tr"));
		   var tds=$(this).parents("tr").children("td");
		   var jobNumber = tds.eq(0).text().trim();
		   var itemName=tds.eq(1).text().trim();
		   var  semester=tds.eq(2).text().trim();
		   var str = semester == 1? "一" : "二";
		   var endScore = tds.eq(3).children("input").val();
		  
		   
		   /*$.each(tds, function(i,val){
			   var jqob=$(val);
			   //把input变为字符串
			   if(!jqob.has('button').length){
				   var txt=jqob.children("input").val();
				   jqob.html(txt);
				   table.cell(jqob).data(txt);//修改DataTables对象的数据
			   }
		   });*/
		   //var data=row.data();
		   var jsonData={"jobNumber":jobNumber, "name": itemName, "semester":semester,"endScore":endScore};
		   layer.confirm("确定将教师 <span style='color:#FF5722'>" + itemName+"</span>的第"+ str +"学期期末成绩补录为<span style='color:#FF5722'>"+ endScore + "</span>分吗?（确认后无法修改或二次补录）", {icon: 3, title:'确认补录操作', anim: 6}, function(index){
				layer.close(index);
				$.ajax({
			   "url":"save_end_socre",
			   "data":jsonData,
			   "type":"post",
			   "error":function(){
	        		   var preBrother = thisObj.parent().prev();
	        			var txt =  preBrother.text().trim();
	        			if(txt=='')txt = 0;
	        			var html="<input type='text' value='"+txt+"'>";
	        			preBrother.html(html);

	        	   layer.msg("您的输入有误，请认真核对", {icon: 5, anim: 0});
	           },
	           "success":function(response){
	               if(response=="1") {
	            	   table.row(thisObj.parents("tr")).remove().draw(false);
						layer.msg('录入成功', {icon: 6,time: 700}); 
				   }
				   else {
					   var preBrother = thisObj.parent().prev();
						var txt =  preBrother.text().trim();
						if(txt=='')txt = 0;
						var html="<input type='text' value='"+txt+"'>";
						preBrother.html(html);
					   layer.msg(response, {icon: 5, anim: 0});
				   }
	           }
		   });
			});
		   
	   });

	   //批量点击编辑按钮
	   $("#batch-edit-btn").click(function(){
		   $(".edit-btn").click();
	   });
	});  

	</script>
</body>
</html>