<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/base.jsp" %>
<tiles:insertDefinition name="detailTemplate">
<tiles:putAttribute name="detailDataUrl" value="/system/user/detailuserandrole"></tiles:putAttribute>
<tiles:putAttribute name="formBody">
<input type="hidden" name="userId" value="${userId}" />
<input type="hidden" name="loginName" value="${loginName}" />
<input type="hidden" name="loginPwd" value="${loginPwd}" />
<input type="hidden" name="ip" value="${ip}" />
<input type="hidden" name="state" value="${state?state:0}" />
<input type="hidden" name="checkIds" value="${roleIds}" class="checkIds"/>

  <div class="layui-form-item">
    <label class="layui-form-label" >用户编号</label>
    <div class="layui-input-block">
      <input type="text" name="userNo" value="${userNo}"  lay-verify="required" placeholder="请输入编号" class="layui-input">
    </div>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label" >用户名称</label>
    <div class="layui-input-block">
      <input type="text" name="userName" value="${userName}"  lay-verify="required" placeholder="请输入名称" class="layui-input">
    </div>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label" >角色选择</label>
    <div class="layui-input-block">
        <select name="roleIds" xm-select="roleIds">
       
    	</select>
    </div>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label" >证件号码</label>
    <div class="layui-input-block">
      <input type="text" name="userCardId" value="${userCardId}"  lay-verify="required" placeholder="证件号码" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
  	  <sys:SelectTag  name="userType" code="userType" lable="用户类型" value="${userType}"></sys:SelectTag>
   </div>
  
  
  <div class="layui-form-item">
    <label class="layui-form-label">账号有效期</label>
    <div class="layui-input-block">
      <input type="text" name="userDate" value="${userExpDate}"  placeholder="请选择截止日期" class="layui-input" id="userExpDate">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">密码有效期</label>
    <div class="layui-input-block">
      <input type="text" name="pwdDate" value="${pwdExpDate}"  placeholder="请选择截止日期" class="layui-input" id="pwdExpDate">
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
<script id="role_tpl" type="text/html">
	{{# layui.each(d.data,function(index,item){ }}
		<input type="checkbox" name="check" value="{{ item.roleId }}"  lay-filter="check" title="{{ item.roleName }}" lay-skin="primary" {{# if($.inArray(item.roleId,d.roleIdsList) > -1){ }}checked{{# } }}>
	{{# }) }}
</script>
<link rel="stylesheet" href="/static/layui_ext/formSelects/formSelects-v4.css">
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
  });
  laydate.render({
    elem: '#pwdExpDate' //指定元素
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

//加载js
layui.config({
    base: '/static/layui_ext/formSelects/' 
}).extend({
    formSelects: 'formSelects-v4'
});
//加载模块选择表单
layui.use(['jquery', 'formSelects'], function(){
    var formSelects = layui.formSelects;
    layui.formSelects.config('roleIds', {
        searchUrl: '/system/user/getRoles?userId=${userId}'
        ,success: function(id, url, searchVal, result){
        	var data = result.data;
        	if(!isEmpty(data)){
  		  	  for(var i=0;i<data.length;i++){
  		  		  var row = data[i];
  		  		  if(row.selected){
  		  			formSelects.value('roleIds',row.value ); 
  		  		  }
  		  	  }
   		  }
         }
    });
    
    formSelects.on('roleIds', function(id, vals, val, isAdd, isDisabled){
         formSelects.value('roleIds', 'valStr');  
    }, true);
    
    
});


/* layui.use(['form','laytpl'], function(){
	  var form = layui.form,
	  laytpl = layui.laytpl;
	  $ = layui.jquery;
  	  var checkList = [];
  	  
  	  $.ajax({ 
    		url: "/system/role/getrolelist", 
    		type:"POST",
    		success: function(json){
    			console.log(json);
	    		if(json.code=='0'){
	    			json.roleIdsList = '${roleIds}'.split(",");
	    			console.log(json.roleIdsList);
	    			var getTpl = role_tpl.innerHTML,view = document.getElementById("role_area");
	    			laytpl(getTpl).render(json,function(html){
	    				view.innerHTML = html;
	    				form.render('checkbox');
	    			});
	    		}else{
	    			layer.alert(json.desc,{icon: 2,offset:offsetTop});
	    		}
        	}
    	});
  	  
	  updateCheckBox()
	  
	  form.on('checkbox(check)',function(data){
	  	 checkList = [];
	     updateCheckBox()
	  });
	  
	  //更改复选框值
	  function updateCheckBox(){
	  	$("input[name='check']").each(function(){
	     	if($(this).prop("checked")){
	     		checkList.push($(this).val());
	     	}
	     })
	     console.log(checkList);
	     $(".checkIds").val(checkList.join(","));
	  }
}); */
</script>
</tiles:putAttribute>

</tiles:insertDefinition>
