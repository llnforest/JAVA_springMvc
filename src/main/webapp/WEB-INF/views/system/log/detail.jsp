<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/base.jsp" %>
<tiles:insertDefinition name="detailTemplate">
<tiles:putAttribute name="formBody">

  <div class="layui-form-item">
	  <div class="layui-inline">
	    <label class="layui-form-label" >日志级别</label>
	    <div class="layui-input-inline">
	      <input type="text" value="${sys:getDictName('logLevel',logLevel)}" class="layui-input layui-disabled" readonly="readonly">
	    </div>
     </div>
     <div class="layui-inline">
	    <label class="layui-form-label" >操作类别</label>
	    <div class="layui-input-inline">
	      <input type="text" value="${operateName}" class="layui-input layui-disabled" readonly="readonly">
	    </div>
     </div>
  </div>
  <div class="layui-form-item">
	  <div class="layui-inline">
	    <label class="layui-form-label" >操作类型</label>
	    <div class="layui-input-inline">
	      <input type="text" value="${operateType}" class="layui-input layui-disabled" readonly="readonly">
	    </div>
     </div>
     <div class="layui-inline">
	    <label class="layui-form-label" >结果结果</label>
	    <div class="layui-input-inline">
	      <input type="text" value="${sys:getDictName('operateResult',operateResult)}" class="layui-input layui-disabled" readonly="readonly">
	    </div>
     </div>
  </div>

  <div class="layui-form-item">
    <label class="layui-form-label" >访问地址</label>
    <div class="layui-input-block">
      <input type="text" value="${appUrl}" class="layui-input layui-disabled" readonly="readonly">
    </div>
  </div>
  <div class="layui-form-item">
	  <div class="layui-inline">
		    <label class="layui-form-label" >操作用户</label>
		    <div class="layui-input-inline">
		      <input type="text" value="${userName}" class="layui-input layui-disabled" readonly="readonly">
		    </div>
	    </div>
	    <div class="layui-inline">
		    <label class="layui-form-label" >操作时间</label>
		    <div class="layui-input-inline">
		      <input type="text" value="${createTime}" class="layui-input layui-disabled" readonly="readonly">
		    </div>
	    </div>
  </div>
  
    <div class="layui-form-item">
	  <div class="layui-inline">
		    <label class="layui-form-label" >操作系统</label>
		    <div class="layui-input-inline">
		      <input type="text" value="${clientOs}" class="layui-input layui-disabled" readonly="readonly">
		    </div>
	    </div>
	     <div class="layui-inline">
		    <label class="layui-form-label" >机器名称</label>
		    <div class="layui-input-inline">
		      <input type="text" value="${clientName}" class="layui-input layui-disabled" readonly="readonly">
		    </div>
	    </div>
  </div>
  
  <div class="layui-form-item">
	  <div class="layui-inline">
		    <label class="layui-form-label" >浏览器内核</label>
		    <div class="layui-input-inline">
		      <input type="text" value="${clientBrowser}" class="layui-input layui-disabled" readonly="readonly">
		    </div>
	    </div>
	    <div class="layui-inline">
		    <label class="layui-form-label" >IP地址</label>
		    <div class="layui-input-inline">
		      <input type="text" value="${clientIp}" class="layui-input layui-disabled" readonly="readonly">
		    </div>
	    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label" >MAC地址</label>
    <div class="layui-input-block">
      <input type="text" value="${clientMac}" class="layui-input layui-disabled" readonly="readonly">
    </div>
  </div>
 
  <div class="layui-form-item layui-form-text">
    <label class="layui-form-label">备注说明</label>
    <div class="layui-input-block">
      <textarea class="layui-textarea layui-disabled" readonly="readonly">${remark}</textarea>
    </div>
  </div>
  
  <div class="layui-form-item layui-form-text">
    <label class="layui-form-label">请求数据</label>
    <div class="layui-input-block">
      <textarea class="layui-textarea layui-disabled" readonly="readonly">${httpData}</textarea>
    </div>
  </div>
  
</tiles:putAttribute>

</tiles:insertDefinition>
