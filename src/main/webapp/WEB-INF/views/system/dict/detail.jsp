<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/base.jsp" %>
<tiles:insertDefinition name="detailTemplate">
<tiles:putAttribute name="formBody">
<input type="hidden" name="dictId" value="${dictId}" />
<input type="hidden" name="parentId" value="${parentId}" />
  <div class="layui-form-item">
    <label class="layui-form-label">字典名称</label>
    <div class="layui-input-block">
      <input type="text" name="dictName" value="${dictName}"  lay-verify="required" placeholder="请输入名称" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label" >字典编码</label>
    <div class="layui-input-block">
      <input type="text" name="dictCode" value="${dictCode}"  lay-verify="required" placeholder="请输入编码" class="layui-input">
    </div>
  </div>
  
  
   <div class="layui-form-item">
     <sys:SelectTag lable="字典类型" isDefault="false" name="dictType" code="dataStruct" value="${dictType}"></sys:SelectTag>
   </div>
  
   <div class="layui-form-item">
    <label class="layui-form-label">排序号码</label>
    <div class="layui-input-block">
      <input type="text" name="dictOrder" value="${dictOrder}"  placeholder="请输入排序号" autocomplete="off" class="layui-input">
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
