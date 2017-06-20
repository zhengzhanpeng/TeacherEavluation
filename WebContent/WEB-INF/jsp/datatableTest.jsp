<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">  
<link rel="stylesheet" type="text/css" href="plugins/layui/css/layui.css">
	<link rel="stylesheet" type="text/css" href="css/query.css">
	<link rel="stylesheet" type="text/css" href="css/jquery.edittable.css">
<title>Insert title here</title>  
</head>  
<body>  
       <table id="tb" class="display">  
            <thead>  
                <tr>  
                    <th>col1</th>  
                    <th>col2</th>  
                </tr>  
            </thead>  
            <tbody>  
            </tbody>  
        </table>  
        <link rel="stylesheet" type="text/css" href="http://cdn.datatables.net/1.10.15/css/jquery.dataTables.css">
 
<!-- jQuery -->
<script type="text/javascript" charset="utf8" src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
 
<!-- DataTables -->
<script type="text/javascript" charset="utf8" src="http://cdn.datatables.net/1.10.15/js/jquery.dataTables.js"></script>
<link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css">
<script type="text/javascript" charset="utf8" src="/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="plugins/layui/layui.js"></script>
        <script type="text/javascript">  
        $(document).ready(function() {  
            $("#tb").dataTable({    
                "bProcessing": false, // 是否显示取数据时的那个等待提示  
                "bServerSide": true,//这个用来指明是通过服务端来取数据  
                "sAjaxSource": "${ctx}datatable/getAlltable",//这个是请求的地址  
                "fnServerData": retrieveData // 获取数据的处理函数  
            });  
        });  
           
        // 3个参数的名字可以随便命名,但必须是3个参数,少一个都不行  
        function retrieveData( sSource111,aoData111, fnCallback111) {  
            $.ajax({  
                url : sSource111,//这个就是请求地址对应sAjaxSource  
                data : {"aoData":JSON.stringify(aoData111)},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数  
                type : 'post',  
                dataType : 'json',  
                async : false,  
                success : function(result) {  
                    fnCallback111(result);//把返回的数据传给这个方法就可以了,datatable会自动绑定数据的  
                },  
                error : function(msg) {  
                }  
            });  
        }  
    </script>  
</body>  
</html>  