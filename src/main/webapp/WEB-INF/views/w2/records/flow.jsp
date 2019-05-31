<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/base.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<base href="${basePath}">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" href="/static/layui/css/layui.css" media="all">
<link rel="stylesheet" href="/static/css/app/style.css" media="all">
<script src="/static/js/jquery/jquery-1.8.0.min.js"></script>
<script src="/static/layui/layui.js"></script>
<script src="/static/js/app/common.js"></script>
</head>

<body>
	<div id="app">
		<form class="layui-form" name="ListForm" method="POST" action="">
			<table class="layui-hide" id="listTable" lay-filter="listTableFilter"></table>
		
		</form>
	</div>
	
	<div class="layui-form-item">
	  <div class="layui-input-block-bottom">
	    <button class="layui-btn layui-btn-danger" type="button" id="closeBut"><i class="layui-icon">&#x1006;</i>关闭</button>
	  </div>
	</div>

<script type="text/html" id="xmTpl">
	{{ d.col3 ? d.col3:'' }}
	{{ d.col6 ? ':'+d.col6:'' }}
</script>
<script type="text/html" id="imgTpl">
	{{ d.col5 ? '<span class="show-img layui-tx-green cursor" data-id="'+d.col0+'">查看</span>':'' }}
</script>
<script>
	layui.use(['table','layer','form','laytpl','element'], function(){
		table = layui.table;
		var laypage = layui.laypage;
		var layer = layui.layer;
		var form = layui.form;
		var laytpl = layui.laytpl;
		var element = layui.element;
		$ = layui.jquery;
	
	
	    //展示已知数据
	 	var tableIns = table.render({
		    elem: '#listTable'
		    ,cols: ${page.headerJson} //标题栏
		    ,url:'/w2/records/flowList?ksbh=${ksbh}&ksrq=${ksrq}'
		    ,limit:10000
		    ${page.options}
		});
		
		
		//关闭自身弹出层
		$("#closeBut").click(function(){
			var index= window.parent.layer.getFrameIndex(window.name);
			window.parent.layer.close(index);
		});
	});
	
	$(".layui-form").on("click",".show-img",function(){
		console.log($(this));
		console.log("ok");
		var id = $(this).attr("data-id");
		layer.open({
	    	title:'图片展示',
	    	type: 2,
	    	closeBtn:1,
	        area: ['800px', '500px'],
	        offset: 't',
	        fixed: false, //不固定
	        maxmin: true,
	        scrollbar:false,
	        // moveOut :true,
            anim: 5, //0-6的动画形式，-1不开启
            content: '/w2/records/imagePage?id='+id
	    });
	});




</script>
</body>
</html>
