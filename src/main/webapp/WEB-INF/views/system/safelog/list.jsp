<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/base.jsp" %>
<tiles:insertDefinition name="listTemplate">
<tiles:putAttribute name="queryBody">
	<div class="layui-inline">
		<label class="layui-form-label">操作人员</label>
		<div class="layui-input-inline">
			<input name="obj.userName_like" value="${userName}" autocomplete="on" class="layui-input" type="text">
		</div>
	</div>
	<div class="layui-inline">
		<label class="layui-form-label">操作类别</label>
		<div class="layui-input-inline">
			<input name="obj.operateName_like" value="${operateName}" autocomplete="on" class="layui-input" type="text">
		</div>
	</div>
	<div class="layui-inline">
		<label class="layui-form-label">操作类型</label>
		<div class="layui-input-inline">
			<input name="obj.opreateType_like" value="${opreateType}" autocomplete="on" class="layui-input" type="text">
		</div>
	</div>
	<div class="layui-inline">
		<label class="layui-form-label">操作时间</label>
		<div class="layui-input-inline">
			<input name="obj.createTime_>=_time" id="beginTime" value="${beginTime}" autocomplete="off" class="layui-input" type="text">
		</div>
		<div class="layui-input-inline">
			<input name="obj.createTime_<=_time" id="endTime" value="${endTime}" autocomplete="off" class="layui-input" type="text">
		</div>
	</div>
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
