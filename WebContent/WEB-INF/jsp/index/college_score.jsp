<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>邯郸学院教职工测评成绩查询</title>
  
  <link href="../css/docs.min.css" rel="stylesheet">
  <link href="../css/main_index.css" rel="stylesheet">
  <link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />
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
		<div class="center">
    <div class="panel panel-primary" >
       <div class="panel-heading text-center" >
          <h3 class="panel-title">${collegeName}</h3>
       </div> 
     <div class="panel">
        <div class="panel-heading">
       	  <ul class="nav nav-tabs">
        	<li id="l1" class="active"><a href="#semester1" data-toggle="tab">第一学期</a></li>
            <li id="l2" ><a href="#semester2" data-toggle="tab" >第二学期</a></li>
            <li id="l3" ><a href="#all" data-toggle="tab">综合成绩</a></li>         
          </ul>
        </div>
		<a href="index"><button id="returnIndex">返回首页</button></a> 
      </div> 
           
      
       
     <div class="tab-content" style="margin-top:-20px">
       <div id="semester1" class="tab-pane active">
          <table class="table table-bordered table-striped table-hover">
          </table>
        </div>
        <div id="semester2" class="tab-pane active">
            <table class="table table-bordered table-striped table-hover">
          </table>
        </div>       
        <div id="all" class="tab-pane active">
            <table class="table table-bordered table-striped table-hover">
          </table>
        </div>
    </div>	  
       
  </div>
  </div>
  <div class="footer">
  <p>版权所有 © <a href="http://www.hdc.edu.cn">邯郸学院</a> 2016-2017</P>
  <p> 冀ICP备05002795号 地址：河北省邯郸市邯山区学院北路530号 邮政编码：056005　 </P>
 
  </div>
  <link rel="stylesheet" type="text/css" href="http://cdn.datatables.net/1.10.15/css/jquery.dataTables.css">
  <script type="text/javascript" charset="utf8" src="http://cdn.datatables.net/1.10.15/js/jquery.dataTables.js"></script>
  <script type="text/javascript" src="../plugins/layui/layui.js"></script>
  <script type="text/javascript">
  function lx() {
		layer.open({
	        type: 1
	        ,title: false //不显示标题栏
	        ,closeBtn: false
	        ,area: '300px;'
	        ,shade: 0.8
	        ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
	        ,btn: ['确定', '取消']
	        ,moveType: 1 //拖拽模式，0或者1
	        ,content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">${wisdom}<br/>恭喜各位老师，您已挤入前八名，再接再厉哦！</div>'
	        ,success: function(layero){
	          var btn = layero.find('.layui-layer-btn');
	          btn.css('text-align', 'center');
	        }
	      });
	}
		layui.use(['layer'], function() {
			var layer = layui.layer;
			
			setTimeout("lx()", 1000);
		});
	</script>
  <script>
  function c() {
		$("#semester2").attr("class", "tab-pane active hidden");
		$("#all").attr("class", "tab-pane active hidden");
	}

	$(document).ready(function(){
		setTimeout("c()", 800);
	$("#l1").click(function () {
		$("#semester2").attr("class", "tab-pane active hidden");
		$("#all").attr("class", "tab-pane active hidden");
	})
	$("#l2").click(function () {
		$("#semester2").attr("class", "tab-pane active show");
		$("#all").attr("class", "tab-pane active hidden");
	})
	$("#l3").click(function () {
		$("#semester2").attr("class", "tab-pane active hidden");
		$("#all").attr("class", "tab-pane active show");
	})
		var collegeId=${collegeId};
		
		 var table1 = $('#semester1 table').DataTable({
           "ajax": {
               "url": "get_semester1s",
               "dataSrc": "data",//默认为data
               "data": {"collegeId":collegeId},
               "type": "post",
               "error":function(){layer.msg("服务器繁忙，请稍后再试", {icon: 5, anim: 0});}
           },
           "columns": [
               { "data": "name", "title":"姓名","defaultContent":""},
			   { "data": "position", "title":"职称","defaultContent":""},
               { "data": "superviseScore", "title":"督导成绩","defaultContent":""},
               { "data": "peerScore", "title":"同行成绩","defaultContent":""},
			   { "data": "studentScore", "title":"学生评教成绩","defaultContent":""},
			   { "data": "endScore", "title":"第一学期期末成绩","defaultContent":""}
           ],
           "order": [[ 5, "desc" ]],
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
	   var table2 = $('#semester2 table').DataTable({
           "ajax": {
               "url": "get_semester2s",
               "dataSrc": "data2",//默认为data
               "data": {"collegeId":collegeId},
               "type": "post",
               "error":function(){layer.msg("服务器繁忙，请稍后再试", {icon: 5, anim: 0});}
           },
           "order": [[ 6, "desc" ]],
           "columns": [
              { "data": "name", "title":"姓名","defaultContent":""},
			   { "data": "position", "title":"职称","defaultContent":""},
               { "data": "superviseScore", "title":"督导成绩","defaultContent":""},
               { "data": "peerScore", "title":"同行成绩","defaultContent":""},
			   { "data": "studentScore", "title":"学生评教成绩","defaultContent":""},
			   { "data": "teachScore", "title":"教学业绩考核成绩","defaultContent":""},
			   { "data": "endScore", "title":"第二学期期末成绩","defaultContent":""}
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
	   var table3 = $('#all table').DataTable({
           "ajax": {
               "url": "get_end_scores",
               "dataSrc": "data3",//默认为data
               "data": {"collegeId":collegeId},
               "type": "post",
               "error":function(){layer.msg("服务器繁忙，请稍后再试", {icon: 5, anim: 0});}
           },
           "order": [[ 4, "desc" ]],
           "columns": [
               { "data": "name", "title":"姓名","defaultContent":""},
			   { "data": "position", "title":"职称","defaultContent":""},
               { "data": "m1", "title":"学年折合成绩","defaultContent":""},
               { "data": "m2", "title":"业绩折合成绩","defaultContent":""},
			   { "data": "all1", "title":"学年质量考核成绩","defaultContent":""},
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
			  
			  });
		

  </script>
</body>
</html>
