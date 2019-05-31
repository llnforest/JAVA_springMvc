<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/base.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<tiles:insertDefinition name="detailTemplate">
<tiles:putAttribute name="detailDataUrl" value="/system/user/detailuserandrole"></tiles:putAttribute>
<tiles:putAttribute name="formBody">
<input type="hidden" name="userId" value="${userId}" />
<input type="hidden" name="ip" value="${ip}" />
<c:if test="${id != null}">
<input type="hidden" name="loginPwd" value="${loginPwd}" />
<input type="hidden" name="loginName" value="${loginPwd}" />
</c:if>
<input type="hidden" name="state" value="${state?state:0}" />
<input type="hidden" name="dataFlag" value="${dataFlag?dataFlag:'Y'}" />
<input type="hidden" name="checkIds" value="${roleIds}" class="checkIds"/>

  <div class="layui-form-item">
    <label class="layui-form-label" >登录账号</label>
    <div class="layui-input-block">
      <input type="text" name="${id?'':'loginName'}" value="${loginName}"  lay-verify="required" placeholder="请输入登录账号" class="layui-input ${id!=null?'layui-disabled':''}"  ${id!=null?'readonly':''}>
    </div>
  </div>
  <c:if test="${id == null}">
  <div class="layui-form-item">
    <label class="layui-form-label" >登录密码</label>
    <div class="layui-input-block">
      <input type="password" name="loginPwd" value="${loginPwd}"  lay-verify="required|password" placeholder="请输入登录密码（必须包含数字、字母和特殊符号的8位英文字符）" class="layui-input">
    </div>
  </div>
  </c:if>
  <div class="layui-form-item">
    <label class="layui-form-label" >用户编号</label>
    <div class="layui-input-block">
      <input type="text" name="userNo" value="${userNo}"  lay-verify="required" placeholder="请输入用戶或警员编号" class="layui-input">
    </div>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label" >用户姓名</label>
    <div class="layui-input-block">
      <input type="text" name="userName" value="${userName}"  lay-verify="required" placeholder="请输入姓名" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <sys:SelectTag  name="roleIds" isDefault="false" code="roleIds" lable="角色选择" value="${ roleId }" sql="select roleName,roleId from SysRole order by roleOrder asc"></sys:SelectTag>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label" >证件号码</label>
    <div class="layui-input-block">
      <input type="text" name="userCardId" value="${userCardId}"  lay-verify="required|identity" placeholder="请输入身份证号码" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
  	  <sys:SelectTag  name="userType" isDefault="false" code="userType" lable="用户类型" value="${userType}"></sys:SelectTag>
   </div>
  
  
  <div class="layui-form-item">
    <label class="layui-form-label">账号有效期</label>
    <div class="layui-input-block">
      <input type="text" name="userDate" value="${userExpDate}"  placeholder="请选择截止日期" class="layui-input" id="userExpDate" readonly>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">密码有效期</label>
    <div class="layui-input-block">
      <input type="text" name="pwdDate" value="${pwdExpDate}"  placeholder="请选择截止日期" class="layui-input" id="pwdExpDate" readonly>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">登录时间</label>
    <div class="layui-input-block">
      <input type="text" name="limitStart" value="${limitStart}"  placeholder="每日开始时间" class="layui-input detail-input-short" id="limitStart">
     <span class="detail-input-text">至</span>
      <input type="text" name="limitEnd" value="${limitEnd}"  placeholder="每日结束时间" class="layui-input detail-input-short" id="limitEnd">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">允许IP</label>
    <div class="layui-input-block ip-area" id="ip_area">
    
    </div>
  </div>
  
   <div class="layui-form-item">
    <label class="layui-form-label">排序号码</label>
    <div class="layui-input-block">
      <input type="text" name="userOrder" value="${userOrder}"  placeholder="请输入排序号" autocomplete="off" class="layui-input">
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

<script id="ip_tpl" type="text/html">
	{{# if(d.length != 0){ }}
	{{# layui.each(d,function(index,item){ }}
		<div class="detail-input-list">
	      <input type="text" name="ips[]" value="{{ item }}"  placeholder="请输入允许登录IP" autocomplete="off" class="layui-input detail-input-middle">
	      {{# if(index == 0){ }}	
		  <span class="layui-btn layui-btn-sm detail-btn btn-add"><i class="layui-icon layui-icon-add-1"></i></span>
		  {{# }else{ }}
			<span class="layui-btn layui-btn-sm layui-btn-primary detail-btn btn-del"><i class="layui-icon layui-icon-delete"></i></span>
		  {{# } }}
    	</div>
	{{# })}}
	{{# }else{ }}
		<div class="detail-input-list">
	      <input type="text" name="ips[]" value=""  placeholder="请输入允许登录IP" autocomplete="off" class="layui-input detail-input-middle">
		  <span class="layui-btn layui-btn-sm detail-btn btn-add"><i class="layui-icon layui-icon-add-1"></i></span>
    	</div>
	{{# } }}
</script>
<script>

//ip控制
layui.use(['jquery','laytpl'],function(){
	var laytpl = layui.laytpl;
	var getTpl = ip_tpl.innerHTML,view = document.getElementById("ip_area");
	var ips = "${ip}".split(",");
	
	laytpl(getTpl).render(ips,function(html){
		view.innerHTML = html;
	});
	//添加
	$(".btn-add").bind("click",function(){
		var addHtml = '<div class="detail-input-list">'
				      + '<input type="text" name="ips[]" value=""  placeholder="请输入允许登录IP" autocomplete="off" class="layui-input detail-input-middle">'
				      + '<span class="layui-btn layui-btn-sm layui-btn-primary detail-btn btn-del"><i class="layui-icon layui-icon-delete"></i></span>'
			    	+ '</div>'; 
		$(".ip-area").append(addHtml);
	});
	//删除
	$(".ip-area").on("click",".btn-del",function(){
		$(this).parent().remove();
		renderIp();
	});
	//失去焦点事件
	$(".ip-area").on("blur",".detail-input-middle",function(){
		renderIp();
	})
	
	//渲染ip
	function renderIp(){
		var ipList = [];
		$(".detail-input-middle").each(function(){
			var ip = $(this).val().trim();
			if($.inArray(ip,ipList) == -1 && ip) ipList.push(ip);
		})
		$("[name='ip']").val(ipList.join(","));
	}
});


//时间和日期
layui.use('laydate', function(){
  var laydate = layui.laydate;

  laydate.render({
    elem: '#userExpDate' //指定元素
    ,min:'${nowDate}'
  });
  laydate.render({
    elem: '#pwdExpDate' //指定元素
    ,min:'${nowDate}'
  });
  laydate.render({
    elem: '#limitStart',
    type: 'time',
    format:'HH:mm'
  });
  laydate.render({
    elem: '#limitEnd',
    type: 'time',
    format:'HH:mm'
  });
});

</script>
</tiles:putAttribute>

</tiles:insertDefinition>
