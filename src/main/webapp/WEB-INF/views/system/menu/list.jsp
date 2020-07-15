<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/base.jsp" %>
<tiles:insertDefinition name="treeTemplate">
<tiles:putAttribute name="treeName" value="树形菜单" />
<tiles:putAttribute name="queryBody">

	
</tiles:putAttribute>

<tiles:putAttribute name="afterForm">
<script>
//input框调用方法
function col8(obj){
	if(obj.value == ""){
		return true;
	}
	$.ajax({
         url: '/system/menu/editOrder',
         type: 'POST',
         data: {"data":obj.value,"id":obj.data.col0},
         dataType: "json",
         success:function (data){
         	 //保存成果
         	 if(data.code == '0'){
         	 	layer.msg("修改排序成功",{offset:offsetTop}); 
         	 }else{
         		initFunc(false,"修改排序失败："+data.desc);
         	 }
         },
         error:function(XMLHttpRequest, textStatus, errorThrown){
         	initFunc(false,"网络异常!");
         }
      });
}

//增加下级菜单
function addSub(obj){
	layui.use(['layer','table'], function(){
		var table = layui.table;
		var data = obj.data;
		layer.open({
	    	title:'新增下级',
	    	type: 2,
	    	closeBtn:1,
	        area: ['800px', '500px'],
	        offset: 't',
	        fixed: false, //不固定
	        maxmin: true,
	        scrollbar:false,
	        // moveOut :true,
            anim: 5, //0-6的动画形式，-1不开启
            content: '/system/menu/detail?parentId='+data.col0,
            end:function(){
	        	if(window.tools.refresh){
	        		initFunc(true);
	        	}
	        }
	    });
	});
}

function clickNode(obj){
	var nodeId = obj.param.nodeId;
	layui.use(['table','layer'], function(){
		var $ = layui.jquery;
		var table = layui.table;
		/* $("[name='parentId']").val(nodeId);
		whereField.parentId = nodeId; */
		$("#clearBut").click();
		console.log(whereField);
		table.reload("listTable", {
			  where: {parentId:nodeId}
			  ,page: {
			    curr: 1 //重新从第 1 页开始
			  }
			});
	});
}
</script>
</tiles:putAttribute>
</tiles:insertDefinition>
