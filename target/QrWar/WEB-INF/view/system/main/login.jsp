<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="loginHtml">
<head>
	<meta charset="utf-8">
	<title>登录-图书管理系统</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="icon" href="${alfred}/resources/favicon.ico">
	<link rel="stylesheet" href="${alfred}/resources/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="${alfred}/resources/css/public.css" media="all" />
</head>
<body class="loginBody">
<br><br>
<h1 align="center"  style="color:LightSteelBlue ; font-size: 50px">图书管理系统</h1>
	<br><br>
	<form class="layui-form" id="loginFrm" method="post" action="${alfred }/login/login.action" style="height: 380px">
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
			<legend>登录</legend>
		</fieldset>
		<div class="layui-form-item input-item">
			<label>身份</label>
			<select name="type">
				<option value="" selected="selected">请选择身份</option>
				<option value="1">管理员</option>
				<option value="2">工作人员</option>
				<option value="3">读者</option>
			</select>
		</div>
		<div class="layui-form-item input-item">
			<label for="loginname">用户名</label>
			<input type="text" placeholder="请输入用户名" autocomplete="off" name="loginname" id="loginname" class="layui-input" lay-verify="required">
		</div>
		<div class="layui-form-item input-item">
			<label for="pwd">密码</label>
			<input type="password" placeholder="请输入密码" autocomplete="off" name="pwd" id="pwd" class="layui-input" lay-verify="required">
		</div>
		<div class="layui-form-item">
			<button class="layui-btn layui-block" lay-filter="login" lay-submit>登录</button>
			<br/>
			<a class="layui-btn layui-block magt3" lay-filter="register" href="${alfred}/login/toRegister.action">注册</a>
		</div>
		<div class="layui-form-item layui-row" style="text-align: right;color: blue;">
			<a style="text-align: right " href="${alfred}/login/toRestPwd.action">重置密码</a>
		</div>
		<div class="layui-form-item layui-row" style="text-align: center;color: red;">
			${error}
		</div>
	</form>
	<script type="text/javascript" src="${alfred}/resources/layui/layui.js"></script>
	<script type="text/javascript" src="${alfred}/resources/js/cache.js"></script>
	<script type="text/javascript">
	layui.use(['form','layer','jquery'],function(){
	    var form = layui.form,
	        layer = parent.layer === undefined ? layui.layer : top.layer
	        $ = layui.jquery;
	    //登录按钮
	    form.on("submit(login)",function(data){
	        $(this).text("登录中...").attr("disabled","disabled").addClass("layui-disabled");
	        setTimeout(function(){
	           $("#loginFrm").submit();
	        },1000);
	        return false;
	    })

	    //表单输入效果
	    $(".loginBody .input-item").click(function(e){
	        e.stopPropagation();
	        $(this).addClass("layui-input-focus").find(".layui-input").focus();
	    })
	    $(".loginBody .layui-form-item .layui-input").focus(function(){
	        $(this).parent().addClass("layui-input-focus");
	    })
	    $(".loginBody .layui-form-item .layui-input").blur(function(){
	        $(this).parent().removeClass("layui-input-focus");
	        if($(this).val() != ''){
	            $(this).parent().addClass("layui-input-active");
	        }else{
	            $(this).parent().removeClass("layui-input-active");
	        }
	    })
		$(".magt3").click(function(e){
		<%--<a href="${alfred}/login/toLogin.action" class="signOut"><i class="seraph icon-tuichu"></i><cite>退出</cite></a>--%>
		})
	})

	</script>
</body>
</html>
