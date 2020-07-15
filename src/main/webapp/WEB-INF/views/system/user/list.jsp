<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/base.jsp" %>
<tiles:insertDefinition name="listTemplate">
<tiles:putAttribute name="queryBody">

</tiles:putAttribute>
<tiles:putAttribute name="afterForm">
<script type="text/html" id="statusTpl">
    <input type="checkbox" name="dataFlag" value="{{d.col0}}" lay-skin="switch" lay-text="正常|禁用" lay-filter="dataFlag" {{ d.col6 == 'Y' ? 'checked' : '' }} >
</script>
<script type="text/html" id="allowTimeTpl">
    {{d.col10}}-{{d.col11}}
</script>
<script type="text/html" id="limitTextTpl">
    <span class="layui-tx-red">
	{{# if(d.col8 < '${nowDate}'){ }}账号过期锁定，{{# } }}
	{{# if(d.col9 < '${nowDate}'){ }}密码过期失效,{{# } }}
	{{# if(d.col7 == 1){ }}重置密码{{# } }}
	</span>
	
</script>
<script>
	function detailByUser(obj){
		if(jQuery.isEmptyObject(radioData)){
			layer.msg("请选择要激活账号的用户");
			return;
		}else if(radioData.data.col8 >= '${nowDate}'){
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
