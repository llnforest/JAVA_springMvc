<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/sysTag.tld" prefix="sys"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach items="${listQuery}" var="v">
<div class="layui-inline">
	<c:choose>
		<c:when test="${v.searchType == 'time'}">
		<label class="layui-form-label">${v.remarkName}</label>
		<div class="layui-input-inline">
			<input name="${v.searchName}_>=_${v.searchTime}" id="beginTime" value="${listQueryParams[v.columnName]}" autocomplete="off" class="layui-input" type="text" placeholder="选择开始${v.searchTime == 'time'?'时间':'日期'}" readonly>
		</div>
		<div class="layui-input-inline">
			<input name="${v.searchName}_<=_${v.searchTime}" id="endTime" value="${listQueryParams[v.columnName]}" autocomplete="off" class="layui-input" type="text" placeholder="选择结束${v.searchTime == 'time'?'时间':'日期'}" readonly>
		</div>
		</c:when>
		
		<c:when test="${v.searchType == 'select'}">
		<sys:SelectTag inline="inline" isDefault="true"  name="${v.searchName}" code="${v.dictCode}" sql="${v.searchSql}" lable="${v.remarkName}" value="${listQueryParams[v.columnName]}"></sys:SelectTag>
		</c:when>
		
		<c:otherwise>
		<label class="layui-form-label">${v.remarkName}</label>
		<div class="layui-input-inline">
			<input name="${v.searchName}" value="${listQueryParams[v.columnName]}" autocomplete="off" class="layui-input" type="text" placeholder="请输入${v.remarkName}">
		</div>
		</c:otherwise>
	</c:choose>
</div>
</c:forEach>