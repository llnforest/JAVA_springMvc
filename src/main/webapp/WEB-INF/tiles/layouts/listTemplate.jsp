<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/base.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<title><tiles:getAsString name="title"/></title>
<tiles:insertAttribute name="headTemplate" />
<tiles:insertAttribute name="head" ignore="true" />
</head>

<body>
<div id="app">
	<c:if test="${listTabs != '[]'}">
	<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief" v-if="listTabs.length > 0" id="tabBody">
	  <ul class="layui-tab-title">
	  	<template v-for="tab in listTabs">
	    	<li class="layui-this" v-on:click="tabEvent(tab.url)" v-if="tab.url.indexOf('?') == -1 && tab.url == '${ sys:romoveUrlPara(servletURI)}'">{{ tab.name }}</li>
	    	<li class="layui-this" v-on:click="tabEvent(tab.url)" v-else-if="tab.url.indexOf('?') != -1 && tab.url == '${ servletURI}'">{{ tab.name }}</li>
	    	<li v-on:click="tabEvent(tab.url)" v-else>{{ tab.name }}</li>
	    </template>
	  </ul>
	</div>
	</c:if>
	<form class="layui-form" name="ListForm" method="POST" action="">
	  <div class="layui-form-item plat-list-query">
	  	<tiles:insertAttribute name="queryBody" ignore="true" />
			<div class="layui-form-item plat-list-query-button" id = "listBut">
				<button v-show="${page.showQueryBut}" class="layui-btn layui-btn-sm" id="queryBut" type="button"  lay-submit  lay-filter="formQuery"><i class="layui-icon layui-icon-search"></i>查询</button>
				<button v-show="${page.showQueryBut}" class="layui-btn layui-btn-sm" id="clearBut" type="reset"><i class="layui-icon layui-icon-file"></i>重置</button>
				<button v-show="${page.showExportBut}" class="layui-btn layui-btn-sm" id="exportBut" type="button"><i class="layui-icon layui-icon-table"></i>导出</button>
				<list-button v-for="but in listButs" :but="but" :key="but.id" v-on:event="butEvent(but)" /> 
			</div>
	  </div>
	<table class="layui-hide" id="listTable" lay-filter="listTableFilter"></table>
	
	<tiles:insertAttribute name="afterTable" ignore="true" />
	</form>
</div>

<!-- 通过注入tabSkin设置tab样式 layui-tab-brief（简洁风格;layui-tab-card(卡片风格) -->
<div class="layui-tab layui-tab-plat <tiles:getAsString ignore="true" defaultValue="layui-hide" name="showTab"/>  <tiles:getAsString ignore="true" name="tabSkin"/>"  lay-allowClose="<tiles:getAsString ignore="true" defaultValue="" name="allowClose"/>" lay-filter="tabFilter">
  <ul class="layui-tab-title layui-tab-title-plat">
  	<tiles:insertAttribute name="tabTitle" ignore="true" />
  </ul>
  <div  class="layui-tab-content layui-tab-content-plat">
    <tiles:insertAttribute name="tabContent" ignore="true" />
  </div>
</div>

<script type="text/html" id="listBar"></script>
<script type="text/html" id="listBarT">
	{{#  layui.each(d, function(index, item){ }}
  		<a class="layui-btn layui-btn-xs" style="background-color:{{ item.css }}" lay-event="{{ item.event }}"> {{ item.icon }}{{ item.name }}</a>
    {{#  }); }}
</script>


<script>
new Vue({ 
el: '#app',
	data:{
		listButs:${listButs},
		listTabs:${listTabs}
	},
	methods: {
	 	butEvent: function (obj) {
		    var eventFunc = obj.event;
		    eval(eventFunc + "(obj);");
	   },
	   tabEvent:function(url){
	   		window.location.href = url;
	   }
	}
});


var	checkIds = [];//复选框选中的ids
var	whereField = {};//查询区域字段对象
var radioData = {};//单选按钮值对象
var offsetTop = ($(window).height()-154)/2 + 'px';;//定义弹框位置

window.tools = {
	refresh : false //列表是否刷新
}
layui.use(['table','layer','form','laytpl','element'], function(){
  table = layui.table;
  var laypage = layui.laypage;
  var layer = layui.layer;
  var form = layui.form;
  var laytpl = layui.laytpl;
  var element = layui.element;
  var $ = layui.jquery;
  var title = $(parent.document).find(".layui-tab-title .layui-this cite").text();

  //生成一个操作工具条bar模板
  var getTpl = listBarT.innerHTML;
  laytpl(getTpl).render(${barButs}, function(html){
	  //将模板复制到空的模板listBar
	  listBar.innerHTML=html;
	  //如果需要自定义模板内容则需要更改pageUtil中bar对应的id值
	});

    //展示已知数据
 	var tableIns = table.render({
	    elem: '#listTable'
	    ,title: $.trim(title)
	    ,cols: ${page.headerJson} //标题栏
	    ,url:'<tiles:getAsString  name="listDataUrl" defaultValue="${servletURI.replace('list', 'listData')}" ignore="true"/>'
	    ${page.options}
	  });
 
  	//监听input框
  	table.on('edit(listTableFilter)',function(obj){
		eval(obj.field+"(obj);")
	})
	
 	//监听单击行事件
 	table.on('radio(listTableFilter)', function(obj){
	   eval("onRadio(obj);");//执行event函数
	});
  
 	table.on('checkbox(listTableFilter)', function(obj){
		  //console.log(obj.checked); //当前是否选中状态
		  //console.log(obj.data); //选中行的相关数据
		  //console.log(obj.type); //如果触发的是全选，则为：all，如果触发的是单选，则为：one
		  var checkStatus = table.checkStatus('listTable');
		  var data = checkStatus.data;
	      //layer.alert(JSON.stringify(data));
	      eval("onCheck(data);");//执行event函数
	});
	
    //监听工具条
    table.on('tool(listTableFilter)', function(obj){
	    var data = obj.data;//获取行数据
	    var eventFunc = obj.event; //获得 lay-event 对应的值
	    var tr = obj.tr; //获得当前行 tr 的DOM对象
	    eval(eventFunc + "(obj);");//执行event函数
    });
  	
  	$("#exportBut").click(function(){
  		console.log(table);
  		console.log(tableIns.config.data);
  		table.exportFile(tableIns.config.id, table.cache.listTable,'xls')
  	});
  
	//回车搜索
    $('.plat-list-query input').bind('keydown', function (event) {
        var event = window.event || arguments.callee.caller.arguments[0];
        if (event.keyCode == 13){
            $("#queryBut").click();
        }
    });
  
  	//监听提交
	 form.on('submit(formQuery)', function(data){
	 	  checkIds = [];
	 	  radioData = {};
	 	  whereField = data.field
		  tableIns.reload({
			  where: whereField
			  ,page: {
			    curr: 1 //重新从第 1 页开始
			  }
			});
	    return false;
	  });
	  
	  //监听状态操作
	  form.on('switch(dataFlag)', function(obj){
	     $.ajax({
	         url: '<tiles:getAsString  name="addUrl" defaultValue="${servletURI.replace('list', 'setDataFlag')}" ignore="true"/>',
	         type: 'POST',
	         data: {"flag":obj.elem.checked,"id":this.value},
	         dataType: "json",
	         success:function (data){
	         	 //保存成果
	         	 if(data.code=='0'){
	         		 layer.msg("状态设置成功！",{offset:offsetTop}); 
	         	 }else{
	         		 initFunc(false,"状态设置成功！"+data.desc);
	         	 }
	         },
	         error:function(XMLHttpRequest, textStatus, errorThrown){
	         	initFunc(false,"网络异常！");
	         }
	      });
	  });
		  
});

//通用的新增方法
function add(obj){
	layui.use('layer', function(){
		layer.open({
	    	title:'新增',
	    	type: 2,
	    	closeBtn:1,
	        area: ['800px', '500px'],
	        offset: 't',
	        fixed: false, //不固定
	        maxmin: false,
	        scrollbar:false,
	        moveOut :true,
	        anim: 5, //0-6的动画形式，-1不开启
	        shade: 0,
	        content: '<tiles:getAsString  name="addUrl" defaultValue="${servletURI.replace('list', 'detail')}" ignore="true"/>',
	        end:function(){
	        	if(window.tools.refresh){
	        		initFunc(true);
	        	}
	        },
	        error:function(XMLHttpRequest, textStatus, errorThrown){
        		initFunc(false,"网络异常!");
        	}
	    });
	});
}

//通用的修改方法
function edit(obj){
	layui.use('layer', function(){
		var data = obj.data;
		var url =  '${sys:convertUrl(servletURI.replace("list", "detail"),"id=")}'+data.col0;
		layer.open({
	    	title:'编辑',
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
	        content: url,
	        end:function(){
	        	if(window.tools.refresh){
	        		initFunc(true);
	        	}
	        },
	        error:function(XMLHttpRequest, textStatus, errorThrown){
	        	initFunc(false,"网络异常!");
	        }
	    });
	});
}

//通用的查看方法，如日志
function show(obj){
	layui.use('layer', function(){
		var data = obj.data;
		var url =  '${sys:convertUrl(servletURI.replace("list", "detail"),"readonly=true&id=")}'+data.col0;
		layer.open({
	    	title:'查看',
	    	type: 2,
	    	closeBtn:1,
	        area: ['800px', '500px'],
	        offset: 't',
	        fixed: false, //不固定
	        maxmin: true,
	        scrollbar:false,
	        moveOut :true,
	        anim: 3, //0-6的动画形式，-1不开启
	        shade: 0,
	        content: url
	    });
	});
}

//通用的删除方法
function del(obj){
	layui.use('layer', function(){
		var $ = layui.jquery;
		var data = obj.data;
		var url =  '${sys:romoveUrlPara(servletURI.replace("list", "delete"))}/'+data.col0;
	    var index = layer.confirm('您是否要删除该条记录？', {offset: offsetTop},function(index){
	    	$.ajax({ 
	    		url: url, 
	    		type:"POST",
	    		success: function(d){
		    		if(d.code=='0'){
		    			layer.msg("删除成功",{offset:offsetTop});
		    			obj.del();
		    		}else{
		    			layer.alert(d.desc,{icon: 2,offset:offsetTop});
		    		}
	    			layer.close(index);
	        	},
	        	error:function(XMLHttpRequest, textStatus, errorThrown){
	        		initFunc(false,"网络异常!");
	        	}
	    	});
	     });
	});
}

function onRadio(obj){
	radioData = obj;
	return obj;
}

function onCheck(data){
	checkIds = [];
	for(var i=0;i<data.length;i++){
		checkIds.push(data[i].col0);
	}
}

//初始化函数
//refresh 刷新是否重置
//msg     是否有弹框
function initFunc(refresh,msg){
	var refresh = refresh || false;
	if(refresh){
		window.tools.refresh = false;
		checkIds = [];
		radioData = {};
	    table.reload('listTable',{where:whereField});
	}
    msg ? layer.msg(msg,{offset:offsetTop}) : ''; 
}
</script>
<tiles:insertAttribute name="afterForm" ignore="true" />
</body>
</html>
