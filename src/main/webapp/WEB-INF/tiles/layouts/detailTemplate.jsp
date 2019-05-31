<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/base.jsp" %>
<html>
<head>
<title><tiles:getAsString name="title"/></title>
<tiles:insertAttribute name="headTemplate" />
<tiles:insertAttribute name="head" ignore="true" />
</head>

<body>
<form class="layui-form layui-form-plat" name="detailForm" method="POST" action="">
  	<input type="hidden" name="createId" value="${createId==null?sessionUser.user.userId:createId}"/>
  	<input type="hidden" name="modifyId" value="${sessionUser.user.userId}"/>
  	<!-- 传入指定的页面提交地址 -->
  	<input type="hidden" name="action" id="action" value="<tiles:getAsString name="action"/>"/>
  	<!-- 页面提交后是否需要跳转页面 -->
  	<input type="hidden" name="isJump" id="isJump" value="<tiles:getAsString ignore="true" defaultValue="true" name="isJump"/>"/>
   	<tiles:insertAttribute name="formBody" ignore="true" />
     <div class="layui-form-item">
	    <div class="layui-input-block-bottom">
	      <button class="layui-btn ${readonly!=null?'layui-hide':''}" lay-submit lay-filter="submitForm"><i class="layui-icon">&#xe605;</i>提交</button>
	      <button class="layui-btn ${id!=null?'layui-hide':''}" type="reset" > <i class="layui-icon">&#xe621;</i>重置</button>
	      <button class="layui-btn layui-btn-danger" type="button" id="closeBut"><i class="layui-icon">&#x1006;</i>关闭</button>
	    </div>
  	</div>
</form>

<script>
layui.use(['form','layer'], function(){
	  var form = layui.form;
	  var layer = layui.layer;
	  var $ = layui.jquery;
	  //判断当前是什么操作
	  var action = '<tiles:getAsString  name="detailDataUrl" defaultValue="${sys:romoveUrlPara(id!=null?servletURI.replace('detail', 'update'):servletURI.replace('detail', 'save'))}" ignore="true"/>';
	  var rep = '${id!=null?"update":"save"}';
	  action = action.replace('detail',rep);
	  
	  if(!isEmpty($("#action").val())){
		  action =  $("#action").val();
	  }
	  //监听提交
	  form.on('submit(submitForm)', function(data){
		  submitForm(action,data,layer);
		  return false;
	  });
	  
	//关闭自身弹出层
	  $("#closeBut").click(function(){
	  	  var index= window.parent.layer.getFrameIndex(window.name);
	  	  window.parent.layer.close(index);
	  });
});



//提交表单
function submitForm(action,data,layer){
	var offsetTop = "180px";
    $.ajax({
        url:action,
        type:'POST',
        data: data.field,//表单数据
        beforeSend:function(){
        	layer.load(2,{offset:offsetTop});
        },
        complete:function(){
        	layer.closeAll('loading');
        },
        success:function(d){
        	if(d.code=='0'){
	        	layer.msg('数据保存成功！',{time:500,offset:offsetTop},function(){
	        		//需要刷新跳转
	        		if($("#isJump").val()=="true"){
	        			//window.parent.location.reload();
	        			parent.layer.close(parent.layer.getFrameIndex(window.name));
	        		}
	        		parent.tools.refresh = true;//设置页面刷新
	        	});
        	}else{
        		//提交出错，记录错误日志
        		layer.msg('提交出错：'+d.desc,{offset:offsetTop});
        	}
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
        	layer.msg("网络异常",{offset:offsetTop});
        }
    });
}

</script>
<tiles:insertAttribute name="afterForm" ignore="true" />
</body>
</html>
