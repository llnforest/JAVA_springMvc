<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/base.jsp" %>
<tiles:insertDefinition name="detailTemplate">
<tiles:putAttribute name="formBody">
	<input type="hidden" name="configId" value="${configId}" />
  <div class="layui-form-item">
    <label class="layui-form-label" >配置编码</label>
    <div class="layui-input-block">
      <input type="text" name="configCode" value="${configCode}"  lay-verify="required" placeholder="请输入编码" class="layui-input">
    </div>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label">配置名称</label>
    <div class="layui-input-block">
      <input type="text" name="configName" value="${configName}"  lay-verify="required" placeholder="请输入名称" class="layui-input">
    </div>
  </div>
  
   <div class="layui-form-item">
    <label class="layui-form-label">配置数值</label>
    <div class="layui-input-block">
      <input type="text" name="configValue" value="${configValue}"  lay-verify="required" placeholder="请输入数值" class="layui-input">
    </div>
  </div>
   <div class="layui-form-item">
    <label class="layui-form-label">单位名称</label>
    <div class="layui-input-block">
      <input type="text" name="units" value="${units}"  lay-verify="required" placeholder="请输入单位" class="layui-input">
    </div>
  </div>
  
   <div class="layui-form-item">
    <label class="layui-form-label">排序号码</label>
    <div class="layui-input-block">
      <input type="text" name="configOrder" value="${configOrder}"  placeholder="请输入排序号" autocomplete="off" class="layui-input">
    </div>
  </div>

  <div class="layui-form-item layui-form-text">
    <label class="layui-form-label">备注说明</label>
    <div class="layui-input-block">
      <textarea name="remark" placeholder="请输入备注说明" class="layui-textarea">${remark}</textarea>
    </div>
  </div> 
  
</tiles:putAttribute>

</tiles:insertDefinition>
