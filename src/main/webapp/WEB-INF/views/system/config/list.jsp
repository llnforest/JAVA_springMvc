<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/base.jsp" %>
<tiles:insertDefinition name="listTemplate">
<tiles:putAttribute name="queryBody">
</tiles:putAttribute>
<tiles:putAttribute name="afterForm">
<script>

//input框调用方法
function col3(obj){
	if(obj.value == ""){
		return true;
	}
	$.ajax({
         url: '/system/config/editValue',
         type: 'POST',
         data: {"data":obj.value,"id":obj.data.col0},
         dataType: "json",
         success:function (data){
         	 //保存成果
         	 if(data.code == '0'){
         	 	layer.msg("修改配置数值成功",{offset:offsetTop}); 
         	 }else{
         		initFunc(false,"修改配置数值失败："+data.desc);
         	 }
         },
         error:function(XMLHttpRequest, textStatus, errorThrown){
         	initFunc(false,"网络异常！");
         }
      });
}
</script>
</tiles:putAttribute>

</tiles:insertDefinition>
