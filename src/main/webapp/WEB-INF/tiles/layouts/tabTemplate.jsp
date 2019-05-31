<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/base.jsp" %>
<html>
<head>
<title><tiles:getAsString name="title"/></title>
<tiles:insertAttribute name="headTemplate" />
<tiles:insertAttribute name="head" ignore="true" />
</head>
<body>

<!-- 通过注入tabSkin设置tab样式 layui-tab-brief（简洁风格;layui-tab-card(卡片风格) -->
<div class="layui-tab layui-tab-plat <tiles:getAsString ignore="true" name="tabSkin"/>"  lay-allowClose="<tiles:getAsString ignore="true" defaultValue="" name="allowClose"/>" lay-filter="tabFilter">
  <ul class="layui-tab-title layui-tab-title-plat">
  	<tiles:insertAttribute name="tabTitle" ignore="true" />
  </ul>
  <div  class="layui-tab-content layui-tab-content-plat">
    <tiles:insertAttribute name="tabContent" ignore="true" />
  </div>
</div>

<script>
layui.use(['element'], function(){
	var element = layui.element;
	
	 //iframe自适应
	$(window).on('resize', function () {
	    var $content = $('.layui-tab-content');
	    $content.height($(this).height()-51);
	    $content.find('iframe').each(function () {
	        $(this).height($content.height());
	    });
	}).resize();
	 
});

</script>
</body>
</html>
