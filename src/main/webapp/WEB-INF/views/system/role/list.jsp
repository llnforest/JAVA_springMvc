<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/base.jsp" %>
<tiles:insertDefinition name="listTemplate">
<tiles:putAttribute name="queryBody">
	<div class="layui-inline">
		<label class="layui-form-label">角色名称</label>
		<div class="layui-input-inline">
			<input name="roleName" value="${roleName}" autocomplete="off" class="layui-input" type="text">
		</div>
	</div>
	<div class="layui-inline">
		<label class="layui-form-label">分组名称</label>
		<div class="layui-input-inline">
			<input name="roleGroup" value="${roleGroup}" autocomplete="off" class="layui-input" type="text">
		</div>
	</div>
</tiles:putAttribute>
<tiles:putAttribute name="afterForm">
	<script>
	//权限
	function auth(obj){
		layui.use('layer',function(){
			var data = obj.data;
			layer.open({
		    	title:'角色权限',
		    	type: 2,
		    	closeBtn:1,
		        area: ['800px', '500px'],
		        offset: 't',
		        fixed: false, //不固定
		        maxmin: true,
		        scrollbar:false,
		        // moveOut :true,
	            anim: 5, //0-6的动画形式，-1不开启
	            content: '/system/role/auth?roleId='+data.col0
		    });
		});
	} 
	</script>

</tiles:putAttribute>

</tiles:insertDefinition>
