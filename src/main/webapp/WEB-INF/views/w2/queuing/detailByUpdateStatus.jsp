<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/base.jsp" %>
<tiles:insertDefinition name="detailTemplate">
<tiles:putAttribute name="detailDataUrl" value="/w2/queuing/updateStatus"></tiles:putAttribute>
<tiles:putAttribute name="formBody">
  <input type="hidden" name="id" value="${id}" />
  <div class="layui-form-item">
    <label class="layui-form-label" >考生编号</label>
    <div class="layui-input-block">
      <input type="text" value="${ksbh}"  lay-verify="required" placeholder="请输入考生编号" class="layui-input layui-disabled" readonly>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">考生姓名</label>
    <div class="layui-input-block">
      <input type="text" value="${xm}"  lay-verify="required" placeholder="请输入考生姓名" class="layui-input layui-disabled" readonly>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">考生证件号码</label>
    <div class="layui-input-block">
      <input type="text" value="${zjhm}"  lay-verify="required" placeholder="请输入考生证件号码" class="layui-input layui-disabled" readonly>
    </div>
  </div>
  <div class="layui-form-item">
  	  <sys:SelectTag  name="zt" isDefault="false" code="queuingStatus" lable="排队状态" value="${zt}"></sys:SelectTag>
  </div>
  <div class="layui-form-item">
  	  <sys:SelectTag  name="djc" isDefault="false" code="examTimes" lable="考试次数" value="${djc}"></sys:SelectTag>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label" >分配项目</label>
    <div class="layui-input-block">
        <select name="ksxm" xm-select="ksxm">
       
    	</select>
    </div>
  </div>
  
  
</tiles:putAttribute>
<tiles:putAttribute name="afterForm">
<link rel="stylesheet" href="/static/layui_ext/formSelects/formSelects-v4.css">
<script>
//加载js
layui.config({
    base: '/static/layui_ext/formSelects/' 
}).extend({
    formSelects: 'formSelects-v4'
});
//加载模块选择表单
layui.use(['jquery', 'formSelects'], function(){
    var formSelects = layui.formSelects;
    layui.formSelects.config('ksxm', {
        searchUrl: '/w2/queuing/getProject?pids=${ksxm}'
        ,success: function(id, url, searchVal, result){
        	var data = result.data;
        	if(!isEmpty(data)){
  		  	  for(var i=0;i<data.length;i++){
  		  		  var row = data[i];
  		  		  if(row.selected){
  		  			formSelects.value('ksxm',row.value ); 
  		  		  }
  		  	  }
   		  }
         }
    });
    
    formSelects.on('ksxm', function(id, vals, val, isAdd, isDisabled){
         formSelects.value('ksxm', 'valStr');  
    }, true);
    
    
});
</script>
</tiles:putAttribute>
</tiles:insertDefinition>
