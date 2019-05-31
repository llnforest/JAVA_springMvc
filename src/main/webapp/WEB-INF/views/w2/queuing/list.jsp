<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/base.jsp" %>
<tiles:insertDefinition name="listTemplate">
<tiles:putAttribute name="queryBody">
	<div class="layui-inline">
		<label class="layui-form-label">考生编号</label>
		<div class="layui-input-inline">
			<input name="obj.ksbh_like" value="${ksbh}" autocomplete="off" class="layui-input" type="text">
		</div>
	</div>
	<div class="layui-inline">
		<label class="layui-form-label">考生姓名</label>
		<div class="layui-input-inline">
			<input name="obj.xm_like" value="${xm}" autocomplete="off" class="layui-input" type="text">
		</div>
	</div>
	<div class="layui-inline">
		<label class="layui-form-label">证件号码</label>
		<div class="layui-input-inline">
			<input name="obj.zjhm_like" value="${zjhm}" autocomplete="off" class="layui-input" type="text">
		</div>
	</div>
	<div class="layui-inline">
		<label class="layui-form-label">考试车号</label>
		<div class="layui-input-inline">
			<input name="obj.kcbh_like" value="${kcbh}" autocomplete="off" class="layui-input" type="text">
		</div>
	</div>
	<div class="layui-inline">
		<label class="layui-form-label">车牌号</label>
		<div class="layui-input-inline">
			<input name="obj.kchp_like" value="${kchp}" autocomplete="off" class="layui-input" type="text">
		</div>
	</div>
	<div class="layui-inline">
		<label class="layui-form-label">考官姓名</label>
		<div class="layui-input-inline">
			<input name="obj.kgname_like" value="${kgname}" autocomplete="off" class="layui-input" type="text">
		</div>
	</div>
	<div class="layui-inline">
		<label class="layui-form-label">考官证件</label>
		<div class="layui-input-inline">
			<input name="obj.kg_like" value="${kg}" autocomplete="off" class="layui-input" type="text">
		</div>
	</div>
	<div class="layui-inline">
		<sys:SelectTag inline="inline" isDefault="true"  name="obj.kscx" code="carType" lable="考试车型" value="${kscx}"></sys:SelectTag>
	</div>
	<div class="layui-inline">
		<sys:SelectTag inline="inline" isDefault="true"  name="obj.zt" code="queuingStatus" lable="排队状态" value="${zt}"></sys:SelectTag>
	</div>
</tiles:putAttribute>
<tiles:putAttribute name="afterForm">
<script>
function updateStatus(obj){
		layer.open({
		    	title:'更新考生排队状态',
		    	type: 2,
		    	closeBtn:1,
		        area: ['800px', '500px'],
		        offset: 't',
		        fixed: false, //不固定
		        maxmin: true,
		        scrollbar:false,
		        moveOut :true,
		        anim: 4, //0-6的动画形式，-1不开启
		        shade: 0,
		        content: "/w2/queuing/detailByUpdateStatus?id="+obj.data.col0,
		        end:function(){
		        	if(window.tools.refresh){
		        		initFunc(true);
		        	}
		        },
		        error:function(XMLHttpRequest, textStatus, errorThrown){
		         	initFunc(false,"网络异常!");
		         }
		    });
	}
</script>
</tiles:putAttribute>

</tiles:insertDefinition>
