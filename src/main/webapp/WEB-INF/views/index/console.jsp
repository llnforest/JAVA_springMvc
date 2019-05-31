<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta charset="utf-8">
  <title>控制台</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="/static/layui/css/layui.css" media="all">
  <style>
  	.content{
  		padding:5% 50px;
  		font-size:16px;
  	}
  	.content-list{
  		margin-bottom:40px;
  	}
  	.content-span{
  		color:#009688;
  		margin:0 5px;
  		font-weight:700;
  	}
  	.record-list{
  		margin-bottom:10px;
  	}
  </style>
</head>
  <body>
	<div class="content">
		<div class="content-list">欢迎进入${ appName }！${sessionScope.sessionUser.getUser().getState() == 1?'<span class="content-span">账号激活后必须修改密码重新登录才能正常使用系统</span>':''}</div>
		<div class="content-list">您好，<span class="content-span">${ userName }</span>！您的账号有效期还剩<span class="content-span">${ userDate }</span>天，密码有效期还剩<span class="content-span">${ pwdDate}</span>天，
			每日登陆时间段为<span class="content-span">${ limitStart }至${limitEnd}</span>;
			</div>
		<div class="content-list">您本次登录时间为<span class="content-span">${ firstTime.getCreateTime() }</span>，登录的IP是<span class="content-span">${ firstTime.getClientIp() }</span></div>
		<c:if test="${ secondTime != null }">
		<div class="content-list">您上次成功登录时间为<span class="content-span">${ secondTime.getCreateTime() }</span>，登录的IP是<span class="content-span">${ secondTime.getClientIp() }</span></div>
		<div class="content-list">
			<p class="record-list">您最近登录失败<span class="content-span">${ failList.size()}</span>次</p>
			<c:forEach items="${ failList }" var="v">
			<p class="record-list">${ v.getCreateTime() }在${ v.getClientOs() }登录失败：${ v.getRemark()}，IP：${ v.getClientIp() }</p>
			</c:forEach>
		</div>
		</c:if>
	</div>
</body>
<c:if test="${sessionScope.sessionUser.getUser().getState() == 1}">
<script src="/static/layui/layui.js"></script>
<script>
	var userState = ${sessionScope.sessionUser.getUser().getState()};
	var userId = "${sessionScope.sessionUser.getUserId()}";
</script>
<script src="/static/js/app/console.js"></script>
</c:if>
</html>