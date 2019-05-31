<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/base.jsp" %>
<tiles:insertDefinition name="detailTemplate">
<tiles:putAttribute name="detailDataUrl" value="/system/user/activeUserExpire"></tiles:putAttribute>
<tiles:putAttribute name="formBody">
<input type="hidden" name="userId" value="${userId}" />
<input type="hidden" name="loginPwd" value="${loginPwd}" />
<input type="hidden" name="state" value="${state?state:0}" />
<input type="hidden" name="checkIds" value="${roleIds}" class="checkIds"/>

	<div class="layui-form-item">
    <label class="layui-form-label" >登录账号</label>
    <div class="layui-input-block">
      <input type="text" name="loginName" value="${loginName}"  lay-verify="required" placeholder="请输入登录账号" class="layui-input layui-disabled" readonly>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label" >用户编号</label>
    <div class="layui-input-block">
      <input type="text" name="userNo" value="${userNo}"  lay-verify="required" placeholder="请输入编号" class="layui-input layui-disabled" readonly>
    </div>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label" >用户名称</label>
    <div class="layui-input-block">
      <input type="text" name="userName" value="${userName}"  lay-verify="required" placeholder="请输入名称" class="layui-input layui-disabled" readonly>
    </div>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label" >角色名称</label>
    <div class="layui-input-block">
      <input type="text" name="roleName" value="${roleName}"  placeholder="角色名称" class="layui-input layui-disabled" readonly>
   	</div>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label" >证件号码</label>
    <div class="layui-input-block">
      <input type="text" name="userCardId" value="${userCardId}"  lay-verify="required" placeholder="证件号码" class="layui-input layui-disabled" readonly>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label" >用户类型</label>
    <div class="layui-input-block">
      <input type="text" name="userType" value="${sys:getDictName('userType',userType)}"  placeholder="用户类型" class="layui-input layui-disabled" readonly>
   	</div>
   </div>
  
  
  <div class="layui-form-item">
    <label class="layui-form-label">账号有效期</label>
    <div class="layui-input-block">
      <input type="text" name="userDate" value="${userExpDate}"  placeholder="请选择截止日期" class="layui-input layui-border-color-green" readonly id="userExpDate" readonly>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">密码有效期</label>
    <div class="layui-input-block">
      <input type="text" name="pwdDate" value="${pwdExpDate}"  placeholder="请选择截止日期" class="layui-input layui-disabled" readonly id="pwdExpDate">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">登录时间</label>
    <div class="layui-input-block">
      <input type="text" name="limitStart" value="${limitStart}"  placeholder="每日开始时间" class="layui-input detail-input-short layui-disabled" readonly id="limitStart">
     <span class="detail-input-text">至</span>
      <input type="text" name="limitEnd" value="${limitEnd}"  placeholder="每日结束时间" class="layui-input detail-input-short layui-disabled" readonly id="limitEnd">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">允许IP</label>
    <div class="layui-input-block">
      <input type="text" name="ip" value="${ip}"  placeholder="请输入IP" autocomplete="off" class="layui-input layui-disabled" readonly>
    </div>
  </div>
  
   <div class="layui-form-item">
    <label class="layui-form-label">排序号码</label>
    <div class="layui-input-block">
      <input type="text" name="userOrder" value="${userOrder}"  autocomplete="off" class="layui-input layui-disabled" readonly>
    </div>
   </div>
  
  <div class="layui-form-item layui-form-text">
    <label class="layui-form-label">备注说明</label>
    <div class="layui-input-block">
      <textarea name="remark"  class="layui-textarea layui-disabled" readonly>${remark}</textarea>
    </div>
  </div> 
  
</tiles:putAttribute>


<tiles:putAttribute name="afterForm">
<script>



//时间和日期
layui.use('laydate', function(){
  var laydate = layui.laydate;
  laydate.render({
    elem: '#userExpDate' //指定元素
   ,min:'${nowDate}'
  });
});

</script>
</tiles:putAttribute>

</tiles:insertDefinition>
