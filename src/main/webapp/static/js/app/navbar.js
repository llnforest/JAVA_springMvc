layui.define(['element','tab'],function(exports){
	 var $ = layui.jquery,
	 layer = parent.layer === undefined ? layui.layer : parent.layer,
	 element = layui.element,
	 Navbar = function (){
		  this.config = {
		   openNav: true //默认展开二级菜单
	      }
	 };
  	 var tab = layui.tab();
  	
  	 
	/**
	 * 配置Navbar
	 * @author wangzhen
	 */
    Navbar.prototype.set = function (options) {
        var that = this;
        //that.config.data = undefined;
        $.extend(true, that.config, options);
        return that;
    };
 	
	/**
	 * 根据获取的topMenu数据生成top菜单html
	 * @author wangzhen
	 */
	  Navbar.prototype.loadNav = function(){
		 var that = this;
		 $.get("/index/topMenu", function(data) {
			 	$.each(data,function(index,obj){
			 		var ulHtml = '';
			 		ulHtml += '<li name="topNav" id="'+obj.menuId+'" class="layui-nav-item">'
			 		+'<a href="javascript:;">'
			 		+ obj.icon
			 		+ '&nbsp;&nbsp;'+obj.title
			 		+'</a>'
			 		+'</li>';
			 		//将topNav追加到html
			 		$("#topNav").append(ulHtml);
			 		//绑定当前topNav点击事件
			 		$("#"+obj.menuId).click(function(){
			 			that.loadLeftNav(obj.menuId);		
			 		});
			 	});
			 	element.init();
			 	that.loadLeftNav(data[0].menuId);
			});
	 }

	  
	  /**
	   * 根据top菜单的id加载左侧二三级菜单
	   * @author wangzhen
	   */
	  Navbar.prototype.loadLeftNav = function (id){
		 var that = this;
	  	// 1.更改样式
	  	$("[name=topNav]").removeClass("layui-this");
	  	$("#"+id).addClass("layui-this");
	  	
	  	// 2加载左侧菜单数据
	  	$.get("/index/leftMenu/"+id, function(data) {
	  		var ulHtml = '';
	  		$.each(data,function(index,obj){
	  			ulHtml += '<li class="layui-nav-item " id = '+obj.menuId+' lay-tips='+obj.title+'>';
	  			ulHtml += '<a href="javascript:;"  data-url="'+obj.url+'"  data-name="form" >';
	  			 if (obj.icon.indexOf('fa-') !== -1) {
	                   ulHtml += '<i class="fa ' + obj.icon + '" aria-hidden="true" data-icon="' + obj.icon + '"></i>';
	               } else {
	                   ulHtml += '<i class="layui-icon" data-icon="' + obj.icon + '">' + obj.icon + '</i>';
	               }
	  			 ulHtml += '<span>&nbsp;&nbsp;'+obj.title+'</span>'
	  			 ulHtml += '</a>'
	  				 //判断是否有三级菜单
	  				 if(obj.children != undefined && obj.children != null && obj.children.length > 0){
	  					//三级菜单 
	  		  			ulHtml += '<dl class="layui-nav-child">';
	  					for(var j=0;j<obj.children.length;j++){
	  						ulHtml += '<dd><li class="layui-nav-item" lay-tips='+obj.title+'><a style="padding-left:15%;" href="javascript:;" id = '+obj.children[j].menuId+' data-url="'+obj.children[j].url+'">';
	  						if (obj.children[j].icon !== undefined && obj.children[j].icon !== '') {
	  							ulHtml += obj.children[j].icon;
	  						}
	  						ulHtml += '<span>&nbsp;&nbsp;'+obj.children[j].title+'</span></a><li></dd>';
	  					}
	  					ulHtml += "</dl>"
	  				 }
	  			 ulHtml += '</li>';
	  		});
	  		$("#leftNav").html(ulHtml);
	  		element.init();
	  		
	  	    that.showNav(that.config.openNav);
	  	    that.clickNav();
	  	});
	  }
	  
		
	  /**
	   * 展开或收缩二级菜单
	   * @author wangzhen
	   */
	  Navbar.prototype.showNav = function(openNav){
		  	var that = this;
			if(openNav){
				//0.设置折叠区域宽度
				$(".layui-side-fold").animate({width:'200px'});
				//1.设置 layui-side-plat左侧菜单div宽度
				$(".layui-side-plat").animate({width:'200px'});
				//2.设置layui-nav-item宽度
				$("#leftNav .layui-nav-item").animate({width:'200px'});
				//3.菜单名称显示
				$("#leftNav .layui-nav-item span").show();
				//4.设置body内容宽度样式
				$('#container').animate({ left:'200'});
				//5.接触layui-nav-item的hover事件
				$("#leftNav .layui-nav-item").unbind('mouseenter').unbind('mouseleave');
			}else{
				//0.设置折叠区域宽度
				$(".layui-side-fold").animate({width:'50px'});
				//1.设置 layui-side-plat左侧菜单div宽度
				$(".layui-side-plat").animate({width:'50px'});
				//2.设置layui-nav-item宽度
				$("#leftNav .layui-nav-item").animate({width:'50px'});
				//3.菜单名称隐藏
				$("#leftNav .layui-nav-item span").hide();
				//4.设置body内容宽度样式
				$('#container').animate({ left:'50'});
				//5.给layui-nav-item添加hover事件
			    $("#leftNav .layui-nav-item").hover(function(){
			     tipsi = layer.tips($(this).attr("lay-tips"),this,{anim: 5});
			   },function(){
					layer.close(tipsi);
			   });
			}
			
	  }
	 
	  /**
	   * 绑定左侧菜单事件定事件
	   * @author wangzhen
	   */
	 
	  Navbar.prototype.clickNav = function (){
		  var that = this;
		  $("#leftNav .layui-nav-item a").bind("click",function(){
			    var menuId = $(this).parent().attr("id");
				var title  = $(this).children('span').text();
	    		var href   = $(this).data('url');
	    		var icon   = $(this).children('i:first').data('icon');
	    		var data   = {href: href,icon: icon,title: title,menuId:menuId};
	    		tab.addTab(data,false);
		  });
	  }

  var navbar = new Navbar();
  exports('navbar', function(options){
	  return navbar.set(options);
  });
}); 

	
   