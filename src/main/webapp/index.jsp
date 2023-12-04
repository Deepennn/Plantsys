<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();//获得当前的项目根目录路径赋值给path
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>园林植物管理平台</title>
</head>
<body>
<jsp:forward page="/WEB-INF/view/auth/login.jsp"></jsp:forward>
</body>
</html>
