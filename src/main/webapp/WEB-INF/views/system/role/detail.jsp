<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/base.jsp" %>
<tiles:insertDefinition name="detailTemplate">
<tiles:putAttribute name="formBody">
<input type="hidden" name="roleId" value="${roleId}" />
  <div class="layui-form-item">
    <label class="layui-form-label" >角色名称</label>
    <div class="layui-input-block">
      <input type="text" name="roleName" value="${roleName}"  lay-verify="required" placeholder="请输入名称" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
        <sys:SelectTag name="roleGroup"  code="roleGroup" lable="角色归属" value="${roleGroup}" isDefault="false"></sys:SelectTag>
  </div>
   <div class="layui-form-item">
    <label class="layui-form-label">排序号码</label>
    <div class="layui-input-block">
      <input type="text" name="roleOrder" value="${roleOrder}"  placeholder="请输入排序号" autocomplete="off" class="layui-input">
    </div>
  </div>

  <div class="layui-form-item">
  	<sys:checkboxTag lable="角色状态" checkValue = "Y" skin="switch" text="启用|禁用" name="dataFlag" value="${dataFlag }" ></sys:checkboxTag>
  </div>

  <div class="layui-form-item layui-form-text">
    <label class="layui-form-label">备注说明</label>
    <div class="layui-input-block">
      <textarea name="remark" placeholder="请输入备注说明" class="layui-textarea">${remark}</textarea>
    </div>
  </div> 
  
</tiles:putAttribute>

</tiles:insertDefinition>
