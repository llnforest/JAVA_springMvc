<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/base.jsp" %>
<tiles:insertDefinition name="detailTemplate">
<tiles:putAttribute name="formBody">
 <input type="hidden" name="menuId" value="${menuId}" />
 <input type="hidden" name="parentId" value="${parentId}" />
 <input type="hidden" name="dataFlag" value="${dataFlag==null?'Y':dataFlag}" />
 <input type="hidden" name="buttonCss" value="${buttonCss}" />
   <div class="layui-form-item">
      <sys:radioTag name="menuType" code="menuType" value="${menuType==null?'M':menuType}" ></sys:radioTag>
   </div>
 
  <div class="layui-form-item">
    <label class="layui-form-label" >菜单图标</label>
    <div class="layui-input-block">
      <input type="text" name="menuIcon" value="${menuIcon}" placeholder="请选择图标" class="layui-input">
    </div>
  </div>
  <!-- 后续集成权限
  <div class="layui-form-item">
    <label class="layui-form-label">菜单编码</label>
    <div class="layui-input-block">
      <input type="text" name="menuCode" value="${menuCode}"  placeholder="请输入编码" class="layui-input">
    </div>
  </div>
  --> 
  <div class="layui-form-item">
    <label class="layui-form-label">菜单名称</label>
    <div class="layui-input-block">
      <input type="text" name="menuTitle" value="${menuTitle}" required  lay-verify="required" placeholder="请输入名称" class="layui-input">
    </div>
  </div>
  
  
   <div class="layui-form-item">
    <label class="layui-form-label">菜单URL</label>
    <div class="layui-input-block">
      <input type="text" name="menuUrl" value="${menuUrl}" placeholder="请输入URL" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
        <sys:SelectTag name="businessType"  code="businessType" lable="业务类别" value="${businessType}" isDefault="false"></sys:SelectTag>
  </div>
  <div id="buttonTypeDocment" class="layui-form-item layui-hide">
  	<div class="layui-inline">
     	<sys:SelectTag lable="按钮事件" name="buttonType" inline = "true" code="buttonType" isDefault="false" value="${buttonType}" style="width: 120px;"></sys:SelectTag>
    </div>
    <div class="layui-inline">
        <sys:SelectTag name="buttonFunctionS" inline = "true"  code="buttonFunction" isDefault="false" value="${buttonFunction}" style="width: 120px;"></sys:SelectTag>
    </div>
    <div class="layui-inline" >
      <div class="layui-input-inline" style="width: 120px;">
         <input type="text" name="buttonFunction" value="${buttonFunction}"  placeholder="JS调用事件名称" autocomplete="off" class="layui-input">
      </div>
    </div>
     <div class="layui-inline" >
      <div id="buttonCss"></div>
    </div>
  </div>
  
   <div class="layui-form-item">
  	<div class="layui-inline">
     	<sys:SelectTag lable="日志级别" name="logLevel" code="logLevel" isDefault="false" inline = "true"  value="${logLevel}" ></sys:SelectTag>
    </div>
    <div class="layui-inline" >
        <label class="layui-form-label">排序号码</label>
	    <div class="layui-input-inline">
	      <input type="text" name="menuOrder" value="${menuOrder}" required  lay-verify="required" placeholder="请输入排序号" autocomplete="off" class="layui-input">
	    </div>
    </div>
  </div>
  
  <div class="layui-form-item layui-form-text">
    <label class="layui-form-label">备注说明</label>
    <div class="layui-input-block">
      <textarea name="remark" placeholder="请输入备注说明" class="layui-textarea">${remark}</textarea>
    </div>
  </div>
  
</tiles:putAttribute>

<tiles:putAttribute name="afterForm">
<script>
layui.use(['form','colorpicker'], function(){
	  var form = layui.form,
	   colorpicker = layui.colorpicker,
	   $ = layui.jquery;
	
	  
	  $(function(){
		  var mv = '${menuType==null?'M':menuType}';
		  if(mv=='B'){
		  	  $('[name="menuIcon"]').parent().parent('.layui-form-item').removeClass('layui-hide');
			  $('#buttonTypeDocment').removeClass('layui-hide');
		  }else{
			  $('#buttonTypeDocment').addClass('layui-hide');
			  $('[name="buttonCss"]').val('');
			  $('[name="buttonType"]').val('');
			  if(mv == 'T' || mv == 'G'){
		  		$('[name="menuIcon"]').parent().parent('.layui-form-item').addClass('layui-hide');
		  	  }else{
		  	  	$('[name="menuIcon"]').parent().parent('.layui-form-item').removeClass('layui-hide');
		  	  }
		  }
		  //初始化按钮颜色选择器
		  renderColorpicker('${buttonCss==null||buttonCss==''?'#009688':buttonCss}');
		  
	  });
	  
	  //监听菜单类型单选按钮
	  form.on('radio(menuType)', function(data){
		  //如果是菜单
		  if(data.value=='B'){
		 	 $('[name="menuIcon"]').parent().parent('.layui-form-item').removeClass('layui-hide');
			 $('#buttonTypeDocment').removeClass('layui-hide');
		  }else{
			 $('#buttonTypeDocment').addClass('layui-hide');
			 $('[name="buttonCss"]').val('');
			 $('[name="buttonType"]').val('');
			 if(data.value == 'T' || data.value == 'G'){
		  		$('[name="menuIcon"]').parent().parent('.layui-form-item').addClass('layui-hide');
		  		$('[name="menuIcon"]').val('');
		  	  }else{
		  	  	$('[name="menuIcon"]').parent().parent('.layui-form-item').removeClass('layui-hide');
		  	  }
		  }
		  
		});  
	  
	  //监听按钮事件
	  form.on('select(buttonFunctionS)', function(data){
		  //非自定义事件
		  if(data.value!='custom'){
			  $('[name="buttonFunction"]').val(data.value);
			  $('[name="buttonFunction"]').addClass('layui-disabled');
			  if(data.value=='add'||data.value=='edit'||data.value=='show'){
				  renderColorpicker('#009688');//默认墨绿色
				  $('[name="buttonCss"]').val('');
			  }
			  if(data.value=='del'){
				  renderColorpicker('#FF5722');
				  $('[name="buttonCss"]').val('#FF5722');
			  }
			  
		  }else{
			  $('[name="buttonFunction"]').val('');
			  $('[name="buttonFunction"]').removeClass('layui-disabled');
		  }
		  
		}); 
	  
	  
	  
	  function renderColorpicker(dColor){
		  colorpicker.render({
			    elem: '#buttonCss'
			    ,color: dColor //设置默认色
			    ,predefine: true // 开启预定义颜色
			    ,done: function(color){
			    	$('[name="buttonCss"]').val(color);
			    }
		  });
	  }
});


</script>

</tiles:putAttribute>


</tiles:insertDefinition>
