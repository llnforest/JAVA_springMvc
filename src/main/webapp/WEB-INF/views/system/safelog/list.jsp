<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/base.jsp" %>
<tiles:insertDefinition name="listTemplate">
<tiles:putAttribute name="queryBody">
</tiles:putAttribute>
<tiles:putAttribute name="afterForm">
<script>
	layui.use('laydate', function(){
		var laydate = layui.laydate;
		  laydate.render({
			    elem: '#beginTime' //开始时间
			    ,type: 'datetime'
		  });
		  laydate.render({
			    elem: '#endTime' //结束时间
			    ,type: 'datetime'
		  });
	});
</script>
</tiles:putAttribute>
</tiles:insertDefinition>
