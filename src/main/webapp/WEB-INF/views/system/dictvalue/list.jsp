<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/base.jsp" %>
<tiles:insertDefinition name="listTemplate">
<tiles:putAttribute name="tabBody">
<!-- <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
  <ul class="layui-tab-title">
    <li onclick="javascript:window.location.href='/system/dict/list';">字典类别</li>
    <li class="layui-this">字典参数</li>
  </ul>
</div> --> 
</tiles:putAttribute>
<tiles:putAttribute name="queryBody">
	<div class="layui-inline">
		<label class="layui-form-label">字典参数</label>
		<div class="layui-input-inline">
			<input name="obj.valName" value="${valName}" autocomplete="off" class="layui-input" type="text">
		</div>
	</div>
	<div class="layui-inline">
		<sys:SelectTag inline="inline" laySearch="true" name="obj.sysDict.dictId" lable="字典名称" value="${dictId}" sql="select dictName,dictId from SysDict order by dictOrder asc"></sys:SelectTag>
	</div>
</tiles:putAttribute>
<tiles:putAttribute name="afterForm">
<script type="text/html" id="colorTpl">
	{{# if(d.col4 != ""){ }}
		<span style="color:{{ d.col4 }}">{{ d.col2 }}</span>
	{{# } }}
</script>
</tiles:putAttribute>
</tiles:insertDefinition>
