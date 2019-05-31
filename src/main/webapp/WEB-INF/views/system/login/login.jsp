<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<title>${appName}</title>
<link rel="stylesheet" type="text/css" href="static/login/css/style2.0.css">
<link rel="stylesheet" href="static/layui/css/layui.css" media="all">
</head>
<body>
<div class="tyg-div">
	<ul>
    	<li>让</li>
    	<li><div style="margin-left:20px;">数</div></li>
    	<li><div style="margin-left:40px;">据</div></li>
    	<li><div style="margin-left:60px;">改</div></li>
    	<li><div style="margin-left:80px;">变</div></li>
    	<li><div style="margin-left:100px;">生</div></li>
    	<li><div style="margin-left:120px;">活</div></li>
    </ul>
</div> 
<div id="contPar" class="contPar">
	<div id="page1"  style="z-index:1;">
		<div class="title0">${appName}</div>
		<div class="title1">信息报送、寻路导航、排队叫号、法院大数据</div>
		<div class="imgGroug">
			<ul>
				<img alt="" class="img0 png" src="static/login/images/page1_0.png">
				<img alt="" class="img1 png" src="static/login/images/page1_1.png">
				<img alt="" class="img2 png" src="static/login/images/page1_2.png">
			</ul>
		</div>
		<img alt="" class="img3 png" src="static/login/images/page1_3.jpg">
	</div>
</div>
<div class="tyg-div-denglv">
	<div class="tyg-div-form">
		<form action="/login" method="POST">
		<!-- 记住密码 该功能未启动 -->
		<input id="remember" name="remember" value="checked" type="hidden"  />
			<h2>登录</h2><p class="tyg-p">欢迎访问 ${appname}</p>
			<div style="margin:5px 0px;">
				<input name="loginName" type="text" value="${loginName}"  placeholder="请输入账号..."/>
			</div>
			<div style="margin:5px 0px;">
				<input name="passWord" type="password" value="${passWord}" placeholder="请输入密码..."/>
			</div>
			<div style="margin:5px 0px;">
				<input  name="captcha" type="text" value=""  style="width:150px;" placeholder="请输入验证码..."/>
				<img style="vertical-align:bottom;height: 31px"  onclick="this.src='/captcha?'+Math.random()" id="codeImg" alt="点击更换" title="点击更换" src="/captcha" />
			</div>
			<div style="margin:0px 0px;">
				<span style="color: red">${tipsMsg}</span>
			</div>
			<button type="submit" >登<span style="width:20px;"></span>录</button>
		</form>
	</div>
</div>

<script type="text/javascript" src="static/js/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="static/login/js/com.js"></script>

<div style="text-align:center;">
<p></p>
</div>

<script src="static/layui/layui.js"></script>
<script>
var tip = '${tipsMsg}';
layui.use('layer',function(){
	var layer = layui.layer;
	if(tip){
		layer.msg(tip);
	}

})
</script>
</body>
</html>