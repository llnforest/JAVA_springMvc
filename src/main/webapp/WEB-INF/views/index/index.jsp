<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>${appName}</title>
    <link rel="stylesheet" href="static/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="static/css/app/main.css" media="all">
    <link rel="stylesheet" type="text/css" href="static/css/font-awesome/css/font-awesome.min.css">
</head>
<body class="layui-layout-body">
<!-- header begin -->
<div class="layui-layout layui-layout-admin layui-layout-plat">
  <div class="layui-header" style="background-color: ${sessionUser.userConfig.topColor};">
    <div class="layui-logo layui-logo-plat">${appName}</div>
    <!-- 一级导航菜单 -->
    <ul class="layui-nav layui-layout-left" id="topNav">
     <li class="layui-nav-item" title="左侧收缩" lay-unselect><a id="shrink" href="javascript:;"><i class="fa fa-outdent" aria-hidden="true"></i></a></li>
    </ul>
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item" title="消息" lay-unselect><a id="notice" data-id="${sessionUser.user.userId}" href="javascript:;"><i class="layui-icon layui-icon-notice" aria-hidden="true"></i><span class="layui-badge-dot"></span></a></li>
      <li class="layui-nav-item" title="主题" lay-unselect><a id="theme" data-id="${sessionUser.user.userId}" href="javascript:;"><i class="layui-icon layui-icon-theme" aria-hidden="true"></i></a></li>
      <li class="layui-nav-item" title="全屏" lay-unselect><a id="fullscreen" href="javascript:;"><i class="fa fa-arrows-alt" aria-hidden="true"></i></a></li>
      <li class="layui-nav-item">
        <a href="javascript:;">
          <img src="/static/images/app/default_photo.jpg" class="layui-nav-img"/>
           ${sessionUser.user.userName}
        </a>
        <dl class="layui-nav-child">
          <dd><a id="selfInfo" data-id="${sessionUser.user.userId}" href="javascript:;"><i class="fa fa-user-circle" aria-hidden="true"></i> 基本资料</a></dd>
          <dd><a id="changePass" data-id="${sessionUser.user.userId}" href="javascript:;"><i class="fa fa-key" aria-hidden="true"></i> 修改密码</a></dd>
          <dd><a id="lockScreen" data-id="${sessionUser.user.loginPwd}" href="javascript:;"><i class="fa fa-lock" aria-hidden="true"></i> 锁屏（ALT+L）</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item"><a href="/logout"><i class="fa fa-sign-out" aria-hidden="true"></i> 注销</a></li>
    </ul>
  </div>
<!-- header end -->
  
<!-- leftNav begin -->
<div class="layui-side layui-side-plat" style="opacity:0.85;background-color: ${sessionUser.userConfig.leftColor};">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree layui-nav-tree-plat"  id="leftNav" style="background-color: ${sessionUser.userConfig.leftColor};">
      </ul>
    </div>
  </div>
<!-- leftNav end -->

<div class="layui-body" id="container" >
         <!-- tab 标签 -->
         <div class="layui-tab layui-tab-plat" lay-filter="tabFilter" >
					<ul class="layui-tab-title layui-tab-title-plat">
						<li class="layui-this" lay-id="index">
							<i class="layui-icon layui-icon-home"></i>
							<cite>控制面板</cite>
						</li>
					</ul>
					
					<div class="layui-tab-content" style="min-height: 150px; padding: 0 0 0 0;">
						<div class="layui-tab-item layui-show">
							<iframe src="/index/console"></iframe>
						</div>
					</div>
	    </div>
</div>


  
</div>


<script src="static/layui/layui.js"></script>
<script src="static/js/app/index.js"></script>
<script src="static/js/util/md5.js"></script>
</body>
</html>