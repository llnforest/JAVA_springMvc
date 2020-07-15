<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/base.jsp" %>
<tiles:insertDefinition name="listTemplate">
<tiles:putAttribute name="queryBody">
</tiles:putAttribute>

<tiles:putAttribute name="afterForm">
<script>
//字典参数按钮
function showValue(obj){
	var data = obj.data;
	window.location.href="/system/dictvalue/showValue?dictId="+data.col0;
}
</script>
</tiles:putAttribute>
</tiles:insertDefinition>
