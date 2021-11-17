<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: otaku
  Date: 2021/10/17
  Time: 6:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Success</title>
</head>
<body>
<h1>success</h1>
<c:forEach items="${adminList}" var="item">
    <td>
        <tr>${item}</tr>
    </td>
</c:forEach>
</body>
</html>
