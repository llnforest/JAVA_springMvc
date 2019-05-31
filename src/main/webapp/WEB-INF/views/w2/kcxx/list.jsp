<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/base.jsp" %>
<tiles:insertDefinition name="listTemplate">
<tiles:putAttribute name="queryBody">
	<div class="layui-inline">
		<label class="layui-form-label">车牌号</label>
		<div class="layui-input-inline">
			<input name="obj.cph_like" value="${cph}" autocomplete="off" class="layui-input" type="text">
		</div>
	</div>
	<div class="layui-inline">
		<label class="layui-form-label">考车型号</label>
		<div class="layui-input-inline">
			<input name="obj.kcmc_like" value="${kcmc}" autocomplete="off" class="layui-input" type="text">
		</div>
	</div>
	<div class="layui-inline">
		<sys:SelectTag inline="inline" isDefault="true"  name="obj.kscx" code="carType" lable="考试车型" value="${kscx}"></sys:SelectTag>
	</div>
	<div class="layui-inline">
		<sys:SelectTag inline="inline" isDefault="true"  name="obj.zt" code="carStatus" lable="考车状态" value="${zt}"></sys:SelectTag>
	</div>
</tiles:putAttribute>
<tiles:putAttribute name="afterForm">

</tiles:putAttribute>

</tiles:insertDefinition>
