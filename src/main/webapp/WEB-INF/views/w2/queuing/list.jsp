<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/base.jsp" %>
<tiles:insertDefinition name="listTemplate">
<tiles:putAttribute name="queryBody">
</tiles:putAttribute>
<tiles:putAttribute name="afterForm">
<script>
function updateStatus(obj){
		layer.open({
		    	title:'更新考生排队状态',
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
		        content: "/w2/queuing/detailByUpdateStatus?id="+obj.data.col0,
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
