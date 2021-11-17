<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<%@include file="/WEB-INF/jsp/includ/includ-head.jsp" %>

<body>
<%@include file="/WEB-INF/jsp/includ/includ-nav.jsp" %>
<script>
    $(function () {
        //点击往右别添加按钮事件
        $("#toright").click(function () {
            $("select:eq(0)>option:selected").appendTo("select:eq(1)");
        });
        //点击向左边添加按钮事件
        $("#toleft").click(function () {
            $("select:eq(1)>option:selected").appendTo("select:eq(0)");
        });
        //点击保存按钮时，把要保存的角色选中
        $("#submitBtn").click(function () {
            $("select:eq(1)>option").prop("selected","selected");
        });
    });
</script>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/jsp/includ/includ-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="#">首页</a></li>
                <li><a href="#">数据列表</a></li>
                <li class="active">分配角色</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form action="user/save/accreditEdit.html" role="form" class="form-inline">
                        <input type="hidden" value="${param.adminId}" name="adminId">
                        <input type="hidden" value="${param.pageStart}" name="pageStart">
                        <input type="hidden" value="${param.keyword}" name="keyword">
                        <div class="form-group">
                            <label for="exampleInputPassword1">未分配角色列表</label><br>
                            <select class="form-control" multiple="" size="10" style="width:100px;overflow-y:auto;">
                                <c:forEach items="${requestScope.unaccredited}" var="role">
                                    <option value="${role.id}">${role.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <ul>
                                <li id="toright" class="btn btn-default glyphicon glyphicon-chevron-right"></li>
                                <br>
                                <li id="toleft" class="btn btn-default glyphicon glyphicon-chevron-left" style="margin-top:20px;"></li>
                            </ul>
                        </div>
                        <div class="form-group" style="margin-left:40px;">
                            <label for="exampleInputPassword1">已分配角色列表</label><br>
                            <select name="roleIdList" class="form-control" multiple="" size="10" style="width:100px;overflow-y:auto;">
                                <c:forEach items="${requestScope.accredited}" var="role">
                                    <option value="${role.id}">${role.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <button type="submit" id="submitBtn" style="display: block;margin-left: 260px;margin-top: 15px" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i>保存</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>