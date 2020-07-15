<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/base.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<tiles:insertDefinition name="listTemplate">
<tiles:putAttribute name="queryBody">
</tiles:putAttribute>
<tiles:putAttribute name="afterForm">
<script type="text/html" id="listBarWith">
	<c:forEach items="${barButs}" var="item">
		<c:choose>
		<c:when test="${item.name == '解锁'}">
		{{# if(d.col4 == "<span style='color:#ff5722'>已锁定</span>"){ }}
		<a class="layui-btn layui-btn-xs" style="background-color:${item.css}" lay-event="${item.event}"> ${item.icon}${item.name}</a>
		{{# } }}
		</c:when>
		<c:otherwise>
		<a class="layui-btn layui-btn-xs" style="background-color:${item.css}" lay-event="${item.event}"> ${item.icon}${item.name}</a>
		</c:otherwise>
		</c:choose>
	</c:forEach>
</script>
<script type="text/html" id="statusTpl">
    {{# if(d.col4 == 'N'){ }}
    <input type="checkbox" name="dataFlag" value="{{d.col0}}" lay-skin="switch" lay-text="锁定|解锁" lay-filter="dataFlag" {{ d.col4 == 'Y' ? 'checked' : '' }} >
	{{# }else{ }}
	<span class="layui-tx-green">已解锁</span>
	{{# } }}
</script>
<script>
	layui.use('laydate', function(){
		var laydate = layui.laydate;
		  laydate.render({
			    elem: '#beginTime' //开始时间
			    ,type: 'datetime'
		  });
		  laydate.render({
			    elem: '#endTime' //结束时间
			    ,type: 'datetime'
		  });
	});
	
	//解锁
	function setDataFlag(obj){
		 console.log(obj);
	     $.ajax({
	         url: '/system/blank/setDataFlag',
	         type: 'POST',
	         data: {"flag":true,"id":obj.data.col0},
	         dataType: "json",
	         success:function (data){
	         	 //保存成果
	         	 if(data.code=='0'){
	         		 layer.msg("状态设置成功！");
	         		 checkIds = [];
	         		 table.reload('listTable',{where:whereField});
	         	 }else{
	         		 layer.msg(data.desc); 
	         	 }
	         },
	         error:function(XMLHttpRequest, textStatus, errorThrown){
	         	layer.msg("网络异常！"); 
	         }
	      });
	  }
	
	//批量解锁
	function batchUnlock(obj){
		if(checkIds.length == 0){
			layer.msg("请勾选要解锁的黑名单");
			return;
		}
		$.ajax({
	         url: '/system/blank/batchUnlock',
	         type: 'POST',
	         data: {"ids":checkIds.join(",")},
	         dataType: "json",
	         success:function (data){
	         	 //保存成果
	         	 layer.msg(data.desc); 
	         	 if(data.code == '0'){
	         		 table.reload('listTable',{where:whereField});
	         	 }
	         },
	         error:function(XMLHttpRequest, textStatus, errorThrown){
	         	layer.msg("网络异常！"); 
	         }
	      });
	}
	
</script>
</tiles:putAttribute>
</tiles:insertDefinition>
