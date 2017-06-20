<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>录入权限管理</title>
	<link rel="stylesheet" type="text/css" href="../plugins/layui/css/layui.css">
	<link rel="stylesheet" type="text/css" href="../css/query.css">
	<link rel="stylesheet" type="text/css" href="../css/jquery.edittable.css">
	<link  rel="stylesheet" type="text/css" href="../css/main.css">
</head>
<body>
	<div style="margin: 15px">
		<blockquote class="layui-elem-quote">
			<h2 style="font-size: 20px" class="layui-inline">
				<i class="layui-icon" style="font-size: 30px">&#xe629;</i> 录入权限管理</h2>
		</blockquote>		
		<button id="openAll" class="layui-btn">全部开启</button>
		<button id="closeAll" class="layui-btn layui-btn-warm">全部关闭</button>
		<button id="setTime" class="layui-btn">设置学期</button>
		<div id="showTime" class="layui-btn layui-btn-primary"></div>
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
               "url": "get_states",
               "dataSrc": "data",//默认为data
               "type": "post",
               "error":function(){alert("服务器未正常响应，请重试")},
               },
           "columns": [
               { "data": "collegeName", "title":"学院名称","defaultContent":""},
			   { "data": "state", "title":"当前状态","defaultContent":""},
               { "data": null, "title":"操作","defaultContent": "<button class='open-btn layui-btn layui-btn-normal' type='button'>开启权限</button>"}
           ],
           "fnInitComplete": function (oSettings, json) {

        	   $(".open-btn").parent().each(function(){
               	   if($(this).prev().text()=="待录入"){
               		$(this).html("<button class='close-btn layui-btn layui-btn-warm' type='button'>关闭权限</button>");
               	   }
                  });
        	   $("a").click(function(){
        		   $(".open-btn").parent().each(function(){
                   	   if($(this).prev().text()=="待录入"){
                   		$(this).html("<button class='close-btn layui-btn layui-btn-warm' type='button'>关闭权限</button>");
                   	   }
                      });
        	   })

           },
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
       
       
          $.ready(function(){
        	 
          });
       var year2 = ${year};
	   var semester2 = ${semester};
	   var bigArr2;
	   if(semester2==1) bigArr2="一";
		else if(semester2==2) bigArr2="二";
		
		var semesterStr="当前学期 ： "+year2+"学年  第"+bigArr2+"学期";
		$("#showTime").text(semesterStr);
	   //开启单个权限
	   $("#layui-table tbody").on("click",".open-btn",function(){
		   var temp=$(this);
           var nRow=$(this).parents('tr')[0];
		   var rowData = table.row(nRow).data();
			var itemName = rowData.id;
			var collegeName = rowData.collegeName;
			 layer.confirm("确定开启" + collegeName + "的录入权限吗？", {icon: 1, title:'确认开启权限', anim: 1}, function (index) {
					$.ajax({
		              "url":"open_college_state",
		              "data":{"collegeId":itemName},
		               "type":"post",
		               "error":function(){
		            	   layer.msg("服务器繁忙，请稍后再试", {icon: 5, anim: 0});
		               },
		               "success":function(response){
		                   if(response=="1") {
		                	 layer.msg('开启权限成功', {icon: 6,time: 700});
		                	 temp.html("关闭权限");
		                	 temp.toggleClass("open-btn layui-btn layui-btn-warm");
		                	 temp.toggleClass("close-btn layui-btn layui-btn-normal");
		                	 temp.parent().prev().text("待录入");
		                   } else {
		                	   layer.msg(response, {icon: 5, anim: 0});
		                   }

		               }
					});
				}
		      );		
       });
	   
	   //关闭单个权限
	   $("#layui-table tbody").on("click",".close-btn",function(){
           var nRow=$(this).parents('tr')[0];
		   var rowData = table.row(nRow).data();
			var itemName = rowData.id;
			var temp=$(this);
			var collegeName = rowData.collegeName;
			layer.confirm("确定关闭" + collegeName + "的录入权限吗？", {icon: 2, title:'确认关闭权限', anim: 1}, function (index) {
					$.ajax({
		              "url":"close_college_state",
		              "data":{"collegeId":itemName},
		               "type":"post",
		               "error":function(){
		            	   layer.msg("服务器繁忙，请稍后再试", {icon: 5, anim: 0});
		               },
		               "success":function(response){
		                   if(response=="1") {
		                	 layer.msg('关闭权限成功', {icon: 6,time: 700});
		                	 temp.html("开启权限");
		                	 temp.toggleClass("open-btn layui-btn layui-btn-warm");
		                	 temp.toggleClass("close-btn layui-btn layui-btn-normal");
		                	 temp.parent().prev().text("未开启");
		                   } else {
		                	   layer.msg(response, {icon: 5, anim: 0});
		                   }

		               }
		      });	
			}
			);
       });
	   
       //设置学期
	  $('#setTime').on( 'click', function () {
	  
				layer.open({
		        type: 1
		        ,title: ['设置学期', 'padding:none !important ;text-align:center'] //不显示标题栏
		        ,closeBtn: false
		        ,area: '500px;'
		        ,shade: 0.8
		        ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
		        ,btn: ['确定', '取消']
		        ,moveType: 1 //拖拽模式，0或者1
		        ,content: '<div  style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;"><form  class="add-user" action=""><div class="layui-form-item"><label class="layui-form-label">当前学年</label><div class="layui-input-block"><select id="year"  lay-verify="required"><option value="-1">2016</option><option value="0">2017</option><option value="1">2018</option><option value="2">2019</option><option value="3">2020</option><option value="4">2021</option><option value="5">2022</option><option value="6">2023</option><option value="7">2024</option><option value="8">2025</option><option value="9">2026</option><option value="10">2027</option><option value="11">2028</option><option value="12">2029</option><option value="13">2030</option><option value="14">2031</option><option value="15">2032</option><option value="16">2033</option><option value="17">2034</option><option value="18">2035</option><option value="19">2036</option><option value="20">2037</option><option value="21">2038</option><option value="22">2039</option><option value="23">2040</option><option value="24">2041</option><option value="25">2042</option><option value="26">2043</option><option value="27">2044</option><option value="28">2045</option><option value="29">2046</option><option value="30">2047</option><option value="31">2048</option><option value="32">2049</option><option value="33">2050</option><option value="34">2051</option><option value="35">2052</option><option value="36">2053</option><option value="37">2054</option><option value="38">2055</option><option value="39">2056</option><option value="40">2057</option><option value="41">2058</option><option value="42">2059</option><option value="43">2060</option><option value="44">2061</option><option value="45">2062</option><option value="46">2063</option><option value="47">2064</option><option value="48">2065</option><option value="49">2066</option><option value="50">2067</option></select></div></div><div class="layui-form-item"><label class="layui-form-label">当前学期</label><div class="layui-input-block"><select id="semester"  lay-verify="required"><option value="1">第一学期</option><option value="2">第二学期</option></select></div></div></form></div>'
				//,content:'<div  style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;"><form class="layui-form" action=""><select name="modules" lay-verify="required" lay-search=""><option value="">直接选择或搜索选择</option><option value="1">layer</option><option value="2">form</option><option value="3">layim</option><option value="4">element</option><option value="5">laytpl</option><option value="6">upload</option><option value="7">laydate</option><option value="8">laypage</option><option value="9">flow</option><option value="10">util</option><option value="11">code</option><option value="12">tree</option><option value="13">layedit</option><option value="14">nav</option><option value="15">tab</option><option value="16">table</option><option value="17">select</option><option value="18">checkbox</option><option value="19">switch</option><option value="20">radio</option></select></form></div>'
		        ,success: function(layero){
		          var btn = layero.find('.layui-layer-btn');
		          btn.css('text-align', 'center');
		        },yes: function(index, layero){
				layer.close(index);
				var year=parseInt($("#year").val())+2017;
				var semester=$("#semester").val();
				var bigArr;
				if(semester==1) bigArr="一";
				else if(semester==2) bigArr="二";
				
				var semesterStr="当前学期 ： "+year+"学年  第"+bigArr+"学期";
				
						$.ajax({
		              "url":"set_semester",
		              "data":{"year":year, "semester":semester},
		               "type":"post",
		               "error":function(){
		            	   layer.msg("服务器繁忙，请稍后再试", {icon: 5, anim: 0});
		               },
		               "success":function(response){
		                   if(response=="1") {
		                	   $("#showTime").text(semesterStr);
		                	   layer.msg('设置成功', {icon: 6,time: 700});
		                   } else {
		                	   layer.msg(response, {icon: 5, anim: 0});
		                   }

		               }
		           });	
					
				  }
				  ,btn2: function(index, layero){
				  }
		      });
	  
			;
    } );	
		//开启全部学院权限
		 $('#openAll').on( 'click', function () {
				layer.confirm("确定开启全部学院的录入权限吗?", {icon: 3, title:'确认录入权限', anim: 0}, function(index){
					layer.close(index);
					$.ajax({
		              "url":"open_all_college_state",
		               "type":"get",
		               "error":function(){
		            	   layer.msg("服务器繁忙，请稍后再试", {icon: 5, anim: 0});
		               },
		               "success":function(response){
		                   if(response=="1") {
		                	   layer.msg('开启成功', {icon: 6,time: 700});
							   setTimeout("location.reload()",800);
		                   } else {
		                	   layer.msg(response, {icon: 5, anim: 0});
		                   }
						}			
					});
				});
		 });
		 
		 
		 
		 //关闭全部学院权限
		 $('#closeAll').on( 'click', function () {
				layer.confirm("确定开启全部学院的录入权限吗?", {icon: 3, title:'确认录入权限', anim: 6}, function(index){
					layer.close(index);
					$.ajax({
		              "url":"close_all_college_state",
		               "type":"get",
		               "error":function(){
		            	   layer.msg("服务器繁忙，请稍后再试", {icon: 5, anim: 0});
		               },
		               "success":function(response){
		                   if(response=="1") {
		                	   layer.msg('关闭成功', {icon: 6,time: 700});
							   setTimeout("location.reload()",800);
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