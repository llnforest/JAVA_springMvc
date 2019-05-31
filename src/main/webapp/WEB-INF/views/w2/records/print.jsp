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
<style>
.print-table{
	width:100%;
	border:1px solid #e6e6e6;
	table-layout:fixed
}
.print-table td{
	border:1px solid #e6e6e6;
	padding:8px;
}
.print-table .min-height{
	min-height:100px;
}
</style>
</head>

<body>
	<div id="app">
		<table cellspacing="0" cellpadding="0" border="0" class="print-table" width="100%">
		<tr>
			<td>姓名</td>
			<td colspan="2"></td>
			<td>学习驾驶证明</td>
			<td></td>
			<td rowspan="4"></td>
		</tr>
		<tr>
			<td>身份证号码</td>
			<td colspan="2"></td>
			<td>报考车型</td>
			<td></td>
		</tr>
		<tr>
			<td>业务类型</td>
			<td colspan="4"></td>
		</tr>
		<tr>
			<td>考试日期</td>
			<td colspan="2"></td>
			<td>预约次数</td>
			<td></td>
		</tr>
		<tr>
			<td colspan="6"  align="center">科目二考试</td>
		</tr>
		<tr>
			<td>考试时间</td>
			<td colspan="2"></td>
			<td>考试成绩</td>
			<td colspan="2"></td>
		</tr>
		<tr>
			<td class="min-height">扣分项</td>
			<td class="min-height" colspan="5"></td>
		</tr>
		<tr>
			<td>考试员签名</td>
			<td colspan="2"></td>
			<td>考生签名</td>
			<td colspan="2"></td>
		</tr>
		<tr>
			<td>考试视频照片（3张）</td>
			<td colspan="5"></td>
		</tr>
		
		<tr>
			<td colspan="6" align="center">科目二补考</td>
		</tr>
		<tr>
			<td>考试时间</td>
			<td colspan="2"></td>
			<td>考试成绩</td>
			<td colspan="2"></td>
		</tr>
		<tr  class="min-height">
			<td>扣分项</td>
			<td colspan="5"></td>
		</tr>
		<tr>
			<td>考试员签名</td>
			<td colspan="2"></td>
			<td>考生签名</td>
			<td colspan="2"></td>
		</tr>
		<tr>
			<td>考试视频照片（3张）</td>
			<td colspan="5"></td>
		</tr>
		</table>
	
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
