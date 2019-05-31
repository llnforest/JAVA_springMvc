<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/base.jsp" %>
<tiles:insertDefinition name="listTemplate">
<tiles:putAttribute name="queryBody">

	<div class="layui-inline">
		<label class="layui-form-label">用户编号</label>
		<div class="layui-input-inline">
			<input name="obj.userNo_like" value="${userNo}" autocomplete="off" class="layui-input" type="text">
		</div>
	</div>
	
	<div class="layui-inline">
		<label class="layui-form-label">用户名称</label>
		<div class="layui-input-inline">
			<input name="obj.userName_like" value="${ userName }" autocomplete="off" class="layui-input" type="text">
		</div>
	</div>
	
	<div class="layui-inline">
		<label class="layui-form-label">绑定Ip</label>
		<div class="layui-input-inline">
			<input name="obj.ip_like" value="${ ip }" autocomplete="off" class="layui-input" type="text">
		</div>
	</div>
	
	<div class="layui-inline">
		<sys:SelectTag inline="inline" isDefault="true"  name="userRole.sysRole.roleId"  lable="用户角色" value="" sql="select roleName,roleId from SysRole order by roleOrder asc"></sys:SelectTag>
	</div>

	<div class="layui-inline">
		<sys:SelectTag inline="inline" isDefault="true"  name="obj.userType" code="userType" lable="用户类型" value="${userType}"></sys:SelectTag>
	</div>
	
	<div class="layui-inline">
		<sys:SelectTag inline="inline" isDefault="true"  name="userExpType" code="userExpType" lable="账号过期" value="${userExpDate}"></sys:SelectTag>
	</div>
	
	<div class="layui-inline">
		<sys:SelectTag inline="inline" isDefault="true"  name="pwdExpType" code="pwdExpType" lable="密码过期" value="${pwdExpDate}"></sys:SelectTag>
	</div>
	
	<div class="layui-inline">
		<sys:SelectTag inline="inline" isDefault="true"  name="obj.state" code="userState" lable="用户状态" value="${state}"></sys:SelectTag>
	</div>
</tiles:putAttribute>
<tiles:putAttribute name="afterForm">
<script type="text/html" id="statusTpl">
    <input type="checkbox" name="dataFlag" value="{{d.col0}}" lay-skin="switch" lay-text="禁用|启用" lay-filter="dataFlag" {{ d.col12 == 'Y' ? 'checked' : '' }} >
</script>
<script type="text/html" id="allowTimeTpl">
    {{d.col9}}-{{d.col10}}
</script>
<script type="text/html" id="limitTextTpl">
    <span class="layui-tx-red">
	{{# if(d.col7 < '${nowDate}'){ }}账号过期锁定，{{# } }}
	{{# if(d.col8 < '${nowDate}'){ }}密码过期失效,{{# } }}
	{{# if(d.col11 == 1){ }}重置密码{{# } }}
	</span>
	
</script>
<script>
	function detailByUser(obj){
		if(jQuery.isEmptyObject(radioData)){
			layer.msg("请选择要激活账号的用户");
			return;
		}else if(radioData.data.col7 >= '${nowDate}'){
			layer.msg("该用户账号暂未锁定，请重新选择");
			return;
		}
		layer.open({
		    	title:'过期账号激活',
		    	type: 2,
		    	closeBtn:1,
		        area: ['800px', '500px'],
		        offset: 't',
		        fixed: false, //不固定
		        maxmin: true,
		        scrollbar:false,
		        moveOut :true,
		        anim: 4, //0-6的动画形式，-1不开启
		        shade: 0,
		        content: "/system/user/detailByUser?id="+radioData.data.col0,
		        end:function(){
		        	if(window.tools.refresh){
		        		initFunc(true);
		        	}
		        },
		        error:function(XMLHttpRequest, textStatus, errorThrown){
		         	initFunc(false,"网络异常!");
		         }
		    });
	}
	
	function detailByPwd(obj){
		if(jQuery.isEmptyObject(radioData)){
			layer.msg("请选择要激活账号的用户");
			return;
		}
		layer.open({
		    	title:'过期账号激活',
		    	type: 2,
		    	closeBtn:1,
		        area: ['800px', '500px'],
		        offset: 't',
		        fixed: false, //不固定
		        maxmin: true,
		        scrollbar:false,
		        moveOut :true,
		        anim: 4, //0-6的动画形式，-1不开启
		        shade: 0,
		        content: "/system/user/detailByPwd?id="+radioData.data.col0,
		        end:function(){
		        	if(window.tools.refresh){
		        		initFunc(true);
		        	}
		        },
		        error:function(XMLHttpRequest, textStatus, errorThrown){
		         	initFunc(false,"网络异常!");
		         }
		    });
	}
</script>
</tiles:putAttribute>
</tiles:insertDefinition>
