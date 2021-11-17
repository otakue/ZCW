<%--
  Created by IntelliJ IDEA.
  User: otaku
  Date: 2021/10/18
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
    <%--    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">--%>
    <%
        String base = request.getContextPath()+"/";
        String url = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+base;
    %>
    <base href=<%=url%>>
    <link rel="stylesheet" href="stat/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="stat/css/font-awesome.min.css">
    <link rel="stylesheet" href="stat/css/login.css">
    <script src="stat/jquery/jquery-2.1.1.min.js"></script>
    <script src="stat/bootstrap/js/bootstrap.min.js"></script>
    <script>
        $(function () {
            $("#back").click(function () {
                window.history.back();
            })
        })
    </script>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div><a class="navbar-brand" href="index.html" style="font-size:32px;">尚筹网-创意产品众筹平台</a></div>
        </div>
    </div>
</nav>
<div class="container" style="text-align: center">
        <h2>出现了错误请稍后再试！😊</h2>
        <div class="checkbox" style="text-align:center;margin-top: 50px;margin-bottom: 50px"><a href="reg.html">${requestScope.exception.message}</a></div>
        <button id="back" style="width: 150px;margin: auto" class="btn btn-lg btn-success btn-block">返回上一步</button>
</div>
</body>
</html>
