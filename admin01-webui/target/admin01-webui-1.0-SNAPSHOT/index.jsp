<html>
<%@page contentType="text/html; charset=utf-8" language="java" isELIgnored="true" %>
<head>
    <%
        String base = request.getContextPath()+"/";
        String url = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+base;
    %>
    <base href=<%=url%>>
    <script type="text/javascript" src="stat/jquery/jquery-2.1.1.min.js"></script>
</head>
<body>
<h2>Hello World!</h2>
<script type="text/javascript">
    $(function () {
        $("#btn1").click(function () {
            var array=[5,8,12];
            var stringify = JSON.stringify(array);
            $.ajax({
                "url": "send/array.html",
                "type": "post",
                "data": stringify,
                "contentType": "application/json;charset=UTF-8",
                "dataType": "text",
                "success": function (response) {
                    alert(response)
                },
                "error": function (response) {

                }
            })
        })
    })
</script>
<button id="btn1">send [5,8,13] one</button>
<a href="test/ssm.html">测试链接</a>
<br/>
<a href="error.html">测试错误页面</a>
<br/>
<a href="skip/admin/login.html">跳转到登入页面</a>
<br/>
<a href="user/test.json">fenhui</a>
</body>
</html>
