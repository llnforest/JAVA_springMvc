<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/base.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tiles:insertDefinition name="detailTemplate">
<tiles:putAttribute name="formBody">
  <input type="hidden" name="valId" value="${valId}" />
  <input type="hidden" name="valColor" value="${valColor}"  placeholder="字体颜色" class="layui-input">
  <div class="layui-form-item">
    <label class="layui-form-label">字典名称</label>
    <div class="layui-input-block">
      <select name="sysDict.dictId" lay-verify="required" lay-search>
        <c:forEach items="${dictList}" var="v">
        	<option value="${v.dictId}" <c:choose><c:when test="${v.dictId == sysDict.dictId}">selected</c:when><c:when test="${v.dictId == dictId and sysDict.dictId == null}">selected</c:when></c:choose> >${v.dictName}</option>
        </c:forEach>
      </select>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label" >字典参数</label>
    <div class="layui-input-block">
      <input type="text" name="valName" value="${valName}"  lay-verify="required" placeholder="请输入参数名称" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label" >字典值</label>
    <div class="layui-input-block">
      <input type="text" name="valCode" value="${valCode}"  lay-verify="required" placeholder="字典值" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label" >字体颜色</label>
    <div class="layui-input-block">
      <div id="valColor"></div>
    </div>
  </div>
  
   <div class="layui-form-item">
    <label class="layui-form-label">排序号码</label>
    <div class="layui-input-block">
      <input type="text" name="valOrder" value="${valOrder}"  placeholder="请输入排序号" autocomplete="off" class="layui-input">
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
<script>
	layui.use('colorpicker', function(){
	  var colorpicker = layui.colorpicker;
	  //渲染
	  colorpicker.render({
	    elem: '#valColor'  //绑定元素
	    ,color: '${valColor}' //默认颜色
	    ,predefine: true // 开启预定义颜色
	    ,done:function(color){
	    	console.log(color);
	    	$("[name='valColor']").val(color);
	    }
	  });
	});
</script>
