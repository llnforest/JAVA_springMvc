<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/base.jsp" %>
<tiles:insertDefinition name="detailTemplate">
<tiles:putAttribute name="formBody">
  <div class="layui-form-item">
    <label class="layui-form-label" >考生编号</label>
    <div class="layui-input-block">
      <input type="text" name="ksbh" value="${ksbh}"  lay-verify="required" placeholder="请输入考生编号" class="layui-input layui-disabled" readonly>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">考生姓名</label>
    <div class="layui-input-block">
      <input type="text" name="xm" value="${xm}"  lay-verify="required" placeholder="请输入考生姓名" class="layui-input layui-disabled" readonly>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">考生证件号码</label>
    <div class="layui-input-block">
      <input type="text" name="zjhm" value="${zjhm}"  lay-verify="required" placeholder="请输入考生证件号码" class="layui-input layui-disabled" readonly>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">考试车型</label>
    <div class="layui-input-block">
      <input type="text" name="kscx" value="${kscx}"  lay-verify="required" placeholder="请输入考试车型" class="layui-input layui-disabled" readonly>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">考试车号</label>
    <div class="layui-input-block">
      <input type="text" name="kcbh" value="${kcbh}"  lay-verify="required" placeholder="请输入考试车号" class="layui-input layui-disabled" readonly>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">车牌号</label>
    <div class="layui-input-block">
      <input type="text" name="kchp" value="${kchp}"  lay-verify="required" placeholder="请输入车牌号" class="layui-input layui-disabled" readonly>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">考试次数</label>
    <div class="layui-input-block">
      <input type="text" name="djc" value="${sys:getDictName('examTimes',djc)}"  lay-verify="required" placeholder="请输入考试次数" class="layui-input layui-disabled" readonly>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">排队状态</label>
    <div class="layui-input-block">
      <input type="text" name="zt" value="${sys:getDictName('queuingStatus',zt)}"  lay-verify="required" placeholder="请输入排队状态" class="layui-input layui-disabled" readonly>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">分配项目</label>
    <div class="layui-input-block">
      <input type="text" name="ksxm" value="${ksxm}"  lay-verify="required" placeholder="请输入项目序号，,号隔开" class="layui-input layui-disabled" readonly>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">未完成项目</label>
    <div class="layui-input-block">
      <input type="text" name="wcxm" value="${wcxm}"  lay-verify="required" placeholder="请输入未完成项目" class="layui-input layui-disabled" readonly>
    </div>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label">考官姓名</label>
    <div class="layui-input-block">
      <input type="text" name="kgname" value="${kgname}"  lay-verify="required"  placeholder="请输入考官姓名" autocomplete="off" class="layui-input layui-disabled" readonly>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">考官证件号码</label>
    <div class="layui-input-block">
      <input type="text" name="kg" value="${kg}"  lay-verify="required"  placeholder="请输入考官证件号码" autocomplete="off" class="layui-input layui-disabled" readonly>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">缴费</label>
    <div class="layui-input-block">
      <input type="text" name="sfyk" value="${sfyk}"  lay-verify="required"  placeholder="请输入缴费" autocomplete="off" class="layui-input layui-disabled" readonly>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">成绩</label>
    <div class="layui-input-block">
      <input type="text" name="score" value="${score}"  lay-verify="required"  placeholder="请输入成绩" autocomplete="off" class="layui-input layui-disabled" readonly>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">交管分车</label>
    <div class="layui-input-block">
      <input type="text" name="sign" value="${sys:getDictName('queuingSign',sign)}"  lay-verify="required"  placeholder="请输入交管分车" autocomplete="off" class="layui-input layui-disabled" readonly>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">线路</label>
    <div class="layui-input-block">
      <input type="text" name="rLine" value="${rLine}"  lay-verify="required"  placeholder="请输入线路" autocomplete="off" class="layui-input layui-disabled" readonly>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">驾校名称</label>
    <div class="layui-input-block">
      <input type="text" name="jxmc" value="${jxmc}"  lay-verify="required"  placeholder="请输入驾校名称" autocomplete="off" class="layui-input layui-disabled" readonly>
    </div>
  </div>
  
  
  
</tiles:putAttribute>

</tiles:insertDefinition>
