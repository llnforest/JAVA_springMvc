<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/base.jsp" %>
<tiles:insertDefinition name="detailTemplate">
<tiles:putAttribute name="formBody">
	<input type="hidden" name="id" value="${id}" />
  <div class="layui-form-item">
    <label class="layui-form-label" >考车编号</label>
    <div class="layui-input-block">
      <input type="text" name="kch" value="${kch}"  lay-verify="required" placeholder="请输入考车编号" class="layui-input">
    </div>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label">考车牌号</label>
    <div class="layui-input-block">
      <input type="text" name="cph" value="${cph}"  lay-verify="required" placeholder="请输入考车牌号" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">考车型号</label>
    <div class="layui-input-block">
      <input type="text" name="kcmc" value="${kcmc}"  lay-verify="required" placeholder="请输入考车型号" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
  	  <sys:SelectTag  name="kscx" isDefault="false" code="carType" lable="准考车型" value="${kscx}"></sys:SelectTag>
  </div>
  <div class="layui-form-item">
  	  <sys:SelectTag  name="zt" isDefault="false" code="carStatus" lable="考车状态" value="${zt}"></sys:SelectTag>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">项目序号</label>
    <div class="layui-input-block">
      <input type="text" name="xmxh" value="${xmxh}"  lay-verify="required" placeholder="请输入项目序号，,号隔开" class="layui-input">
    </div>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label">车载视频IP</label>
    <div class="layui-input-block">
      <input type="text" name="czip" value="${czip}"  lay-verify="required"  placeholder="请输入车载视频IP" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">车载视频端口</label>
    <div class="layui-input-block">
      <input type="text" name="czport" value="${czport}"  lay-verify="required"  placeholder="请输入车载视频端口" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">车载视频通道</label>
    <div class="layui-input-block">
      <input type="text" name="czchannel" value="${czchannel}"  lay-verify="required"  placeholder="请输入车载视频通道" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">车载用户名</label>
    <div class="layui-input-block">
      <input type="text" name="cuser" value="${cuser}"  lay-verify="required"  placeholder="请输入车载用户名" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">车载密码</label>
    <div class="layui-input-block">
      <input type="text" name="cpwd" value="${cpwd}"  lay-verify="required"  placeholder="请输入车载密码" autocomplete="off" class="layui-input">
    </div>
  </div>

<div class="layui-form-item">
    <label class="layui-form-label">中心视频IP</label>
    <div class="layui-input-block">
      <input type="text" name="zxip" value="${zxip}"  lay-verify="required"  placeholder="请输入中心视频IP" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">中心视频端口</label>
    <div class="layui-input-block">
      <input type="text" name="zxport" value="${zxport}"  lay-verify="required"  placeholder="请输入中心视频端口" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">中心视频通道</label>
    <div class="layui-input-block">
      <input type="text" name="zxchannel" value="${zxchannel}"  lay-verify="required"  placeholder="请输入中心视频通道" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">中心用户名</label>
    <div class="layui-input-block">
      <input type="text" name="zuser" value="${zuser}"  lay-verify="required"  placeholder="请输入中心用户名" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">中心密码</label>
    <div class="layui-input-block">
      <input type="text" name="zpwd" value="${zpwd}"  lay-verify="required"  placeholder="请输入中心密码" autocomplete="off" class="layui-input">
    </div>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label">四合一视频IP</label>
    <div class="layui-input-block">
      <input type="text" name="fourip" value="${fourip}"  lay-verify="required"  placeholder="请输入四合一视频IP" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">四合一视频端口</label>
    <div class="layui-input-block">
      <input type="text" name="fourport" value="${fourport}"  lay-verify="required"  placeholder="请输入四合一视频端口" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">四合一视频通道</label>
    <div class="layui-input-block">
      <input type="text" name="fourchannel" value="${fourchannel}"  lay-verify="required"  placeholder="请输入四合一视频通道" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">四合一用户名</label>
    <div class="layui-input-block">
      <input type="text" name="fouruser" value="${fouruser}"  lay-verify="required"  placeholder="请输入四合一用户名" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">四合一密码</label>
    <div class="layui-input-block">
      <input type="text" name="fourpwd" value="${fourpwd}"  lay-verify="required"  placeholder="请输入四合一密码" autocomplete="off" class="layui-input">
    </div>
  </div>
  
  
</tiles:putAttribute>

</tiles:insertDefinition>
