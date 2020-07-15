<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/base.jsp" %>
<tiles:insertDefinition name="detailTemplate">
<tiles:putAttribute name="formBody">
 <input type="hidden" name="tableId" value="${tableId}" />
 <input type="hidden" name="dataFlag" value="${dataFlag==null?'Y':dataFlag}" />
 <input type="hidden" name="buttonCss" value="${buttonCss}" />
 <div class="layui-form-item">
        <sys:SelectTag name="tableName"  code="tableName" lable="功能模块" value="${tableName}" isDefault="false"></sys:SelectTag>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label" >字段备注</label>
    <div class="layui-input-block">
      <input type="text" name="remarkName" value="${remarkName}" placeholder="请输入字段备注" class="layui-input" lay-verify="required">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label" >字段名称</label>
    <div class="layui-input-block">
      <input type="text" name="columnName" value="${columnName}" placeholder="请输入字段名称" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">数据字典</label>
    <div class="layui-input-block">
      <input type="text" name="dictCode" value="${dictCode}" placeholder="请输入数据字典" class="layui-input">
    </div>
  </div>
  
  
  <div class="layui-form-item">
   	<sys:SelectTag lable="是否备注" name="isLog" inline="true" code="isTrue" isDefault="false" value="${isLog}"></sys:SelectTag>
  </div>
  <div id="logDom" class="layui-form-item ${isLog == 0?'layui-hide':''}" style="padding-left:130px;">
    <div class="layui-inline">
        <sys:SelectTag defaultText="头部固定" name="isFixedLog" inline="true"  code="isTrue" isDefault="true" value="${isFixedLog == null?'0':isFixedLog}" style="width: 120px;"></sys:SelectTag>
    </div>
    <div class="layui-inline" >
      <div class="layui-input-inline" style="width: 120px;">
         <input type="text" name="logOrder" value="${logOrder}"  placeholder="备注排序" autocomplete="off" class="layui-input">
      </div>
    </div>
  </div>
  
  <div class="layui-form-item">
   	<sys:SelectTag lable="是否查询" name="isSearch" inline="true" code="isTrue" isDefault="false" value="${isSearch==null?0:isSearch}"></sys:SelectTag>
  </div>
  <div id="searchDom" class="layui-form-item ${isSearch != 1?'layui-hide':''}" style="padding-left:130px;">
    <div class="layui-inline" >
      <div class="layui-input-inline" style="width: 120px;">
         <input type="text" name="searchOrder" value="${searchOrder}"  placeholder="查询排序" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline" >
      <div class="layui-input-inline" style="width: 480px;">
         <input type="text" name="searchSql" value="${searchSql}"  placeholder="查询语句" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline" >
      <div class="layui-input-inline" style="width: 120px;">
         <input type="text" name="searchName" value="${searchName}"  placeholder="查询别名" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline">
     	<sys:SelectTag defaultText="查询类型" name="searchType" inline="true" code="searchType" isDefault="true" value="${searchType}" style="width: 120px;"></sys:SelectTag>
    </div>
    <div class="layui-inline">
     	<sys:SelectTag defaultText="时间格式" name="searchTime" inline="true" code="dateFormate" isDefault="true" value="${searchTime}" style="width: 120px;"></sys:SelectTag>
    </div>
  </div>
  
  <div class="layui-form-item">
   	<sys:SelectTag lable="是否列表" name="isList" inline="true" code="isTrue" isDefault="false" value="${isList}"></sys:SelectTag>
  </div>
  <div id="listDom" class="layui-form-item ${isList == 0?'layui-hide':''}" style="padding-left:130px;">
    <div class="layui-inline" >
      <div class="layui-input-inline" style="width: 120px;">
         <input type="text" name="listOrder" value="${listOrder}"  placeholder="列排序" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline" >
      <div class="layui-input-inline" style="width: 120px;">
         <input type="text" name="listWidth" value="${listWidth}"  placeholder="列宽度" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline" >
      <div class="layui-input-inline" style="width: 120px;">
         <input type="text" name="listTpl" value="${listTpl}"  placeholder="列模板" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline" >
      <div class="layui-input-inline" style="width: 120px;">
         <input type="text" name="listJsfunc" value="${listJsfunc}"  placeholder="列方法" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline" >
      <div class="layui-input-inline" style="width: 120px;">
         <input type="text" name="listName" value="${listName}"  placeholder="列别名" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline" >
      <div class="layui-input-inline" style="width: 480px;">
         <input type="text" name="listStyle" value="${listStyle}"  placeholder="列样式" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline">
     	<sys:SelectTag defaultText="列隐藏" name="listHide" inline="true" code="isTrue" isDefault="true" value="${listHide==null?0:listHide}" style="width: 120px;"></sys:SelectTag>
    </div>
    <div class="layui-inline">
     	<sys:SelectTag defaultText="列输入" name="listInput" inline="true" code="isTrue" isDefault="true" value="${listInput==null?0:listInput}" style="width: 120px;"></sys:SelectTag>
    </div>
    <div class="layui-inline">
     	<sys:SelectTag defaultText="列开关" name="listSwitch" inline="true" code="isTrue" isDefault="true" value="${listSwitch==null?0:listSwitch}" style="width: 120px;"></sys:SelectTag>
    </div>
  </div>
  
</tiles:putAttribute>

<tiles:putAttribute name="afterForm">
<script>
layui.use(['form'], function(){
	  var form = layui.form,
	   $ = layui.jquery;
	  
	  //监听是否日志
	  form.on('select(isLog)', function(data){
		  //如果是日志
		  if(data.value=='1'){
			 $("#logDom").removeClass("layui-hide");
		  }else{
		  	 $("#logDom").addClass("layui-hide");
			 $('[name="logOrder"]').val('');
			 $('[name="isFixedLog"]').val('0');
			 form.render("select");
		  }
	  });  
	  
	  
	   //监听是否查询
	  form.on('select(isSearch)', function(data){
		  //如果是查询
		  if(data.value=='1'){
			 $("#searchDom").removeClass("layui-hide");
		  }else{
		  	 $("#searchDom").addClass("layui-hide");
			 $('[name="searchOrder"]').val('');
			 $('[name="searchSql"]').val('');
			 $('[name="searchName"]').val('');
			 $('[name="searchType"]').val('');
			 $('[name="searchTime"]').val('');
			 form.render("select");
		  }
	  }); 
	  
	   //监听是否列表
	  form.on('select(isList)', function(data){
		  //如果是列表
		  if(data.value=='1'){
			 $("#listDom").removeClass("layui-hide");
		  }else{
		  	 $("#listDom").addClass("layui-hide");
			 $('[name="listOrder"]').val('');
			 $('[name="listWidth"]').val('');
			 $('[name="listTpl"]').val('');
			 $('[name="listJsfunc"]').val('');
			 $('[name="listName"]').val('');
			 $('[name="listStyle"]').val('');
			 $('[name="listHide"]').val('0');
			 $('[name="listInput"]').val('0');
			 $('[name="listSwitch"]').val('0');
			 form.render("select");
		  }
	  }); 
});


</script>

</tiles:putAttribute>


</tiles:insertDefinition>
