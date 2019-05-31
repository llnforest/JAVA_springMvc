<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/base.jsp" %>
<tiles:insertDefinition name="detailTemplate">
<tiles:putAttribute name="formBody">
<input type="hidden" name="orgId" value="${orgId}" />
<input type="hidden" name="parentId" value="${parentId}" />

  <div class="layui-form-item">
    <label class="layui-form-label" >机构编码</label>
    <div class="layui-input-block">
      <input type="text" name="orgCode" value="${orgCode}"  lay-verify="required" placeholder="请输入编码" class="layui-input">
    </div>
  </div>

  <div class="layui-form-item">
    <label class="layui-form-label" >机构名称</label>
    <div class="layui-input-block">
      <input type="text" name="orgName" value="${orgName}"  lay-verify="required" placeholder="请输入名称" class="layui-input">
    </div>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label">联系人员</label>
    <div class="layui-input-block">
      <input type="text" name="linkMan" value="${linkMan}"  placeholder="请输入联系人" class="layui-input">
    </div>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label">联系电话</label>
    <div class="layui-input-block">
      <input type="text" name="linkPhone" value="${linkPhone}"  placeholder="请输入联系电话" class="layui-input">
    </div>
  </div>
  
   <div class="layui-form-item">
    <label class="layui-form-label">排序号码</label>
    <div class="layui-input-block">
      <input type="text" name="orgOrder" value="${orgOrder}"  placeholder="请输入排序号" autocomplete="off" class="layui-input">
    </div>
  </div>

  <div class="layui-form-item">
  	  <sys:radioTag lable="机构类型" name="orgType" code="orgType" value="${orgType }"></sys:radioTag>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label">邮政编码</label>
    <div class="layui-input-block">
      <input type="text" name="orgPostcode" value="${orgPostcode}"  placeholder="请输入邮政编码" class="layui-input">
    </div>
  </div>

  <div class="layui-form-item layui-form-text">
    <label class="layui-form-label">机构地址</label>
    <div class="layui-input-block">
      <textarea name="orgAddr" placeholder="请输入机构地址" class="layui-textarea">${orgAddr}</textarea>
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
