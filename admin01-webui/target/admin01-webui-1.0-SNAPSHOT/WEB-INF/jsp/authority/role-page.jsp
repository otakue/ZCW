<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<%@include file="/WEB-INF/jsp/includ/includ-head.jsp" %>

<body>
<%@include file="/WEB-INF/jsp/includ/includ-nav.jsp" %>
<link rel="stylesheet" href="stat/css/pagination.css">
<script type="text/javascript" src="stat/jquery/jquery.pagination.js"></script>
<link rel="stylesheet" href="stat/ztree/zTreeStyle.css"/>
<script type="text/javascript" src="stat/ztree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="stat/crowd/role.js"></script>
<script>
    //分页导航栏
    $(function () {
        //总共有都少条数据
        var totalNumber=${requestScope.rolePageInfo.total};
        $("#Pagination").pagination(totalNumber,{
            items_per_page: ${requestScope.rolePageInfo.pageSize},
            num_display_entries: 4,
            current_page: ${requestScope.rolePageInfo.pageNum-1},
            num_edge_entries: 3,
            prev_text: "上一页",
            next_text: "下一页",
            callback : pageselectCallback
        });
        function pageselectCallback(pageNum,jq) {
            var currentPage=pageNum+1;
            window.location.href="user/role/page.html?pageNum="+currentPage+"&keyword=${param.keyword}"
            return false;
        };
        //点击全部全中按钮时，td按钮全部选中
        $("#checkAll").click(function () {
                var prop = $("#checkAll").is(':checked');
                if (prop == true){
                    $(".tdCheck").prop("checked",true);
                }
                if (prop == false){
                    $(".tdCheck").prop("checked",false);
                }
            });
        //当角色中的checkbox 全部选中时，首行中的checkbox选中,反之
        $(".tdCheck").click(function () {
            var checkSelectLeng=$(".tdCheck:checked").length;
            var pageSize=${requestScope.rolePageInfo.size};
            if (checkSelectLeng == pageSize){
                $("#checkAll").prop("checked",true);
            }
            //如果选中的tdcheked没有全选，就取消checkAll按钮
            if (checkSelectLeng < pageSize){
                $("#checkAll").prop("checked",false);
            }
        });
        //点击最上方的删除按钮，遍历checkbox选中的，取出隐藏的role id,发送给后端进行删除
        var checkedRoleIds=[];
        $("#delBtn").click(function () {
            $(".tdCheck").each(function () {
                if ($(this).is(":checked") == true){
                    var id = $(this).parents("tr").find("td:eq(1)").text();
                    checkedRoleIds.push(id);
                }
            });
            var roleIds = JSON.stringify(checkedRoleIds);
            $.ajax({
                url: "user/role/MuchDelete/page.json",
                type: "post",
                data: roleIds,
                contentType: "application/json;charset=UTF-8",
                dataType: "json",
                success:function (response) {
                    var result=response.result;
                    if (result == "SUCCESS"){
                        alert("删除成功");
                        window.location.href="user/role/page.html?pageNum=${param.pageNum}&keyword=${param.keyword}";
                    }
                    if (result == "FAIL"){
                        alert("删除失败,请稍后重试");
                    }
                },
                error: function () {
                    alert("未知错误！");
                }
            })
        });
        //点击role编辑按钮弹出模态框
        $(".role_edit").click(function () {
            window.roleId=$(this).parents("tr").find("td:eq(1)").text();
            $('#menuEditModal').modal('show');
            generateRoleAuthTree();
        });
        //点击更新按钮后，提交保存
        $("#menuEditBtn").click(function () {
            //保存role id的数组
            var roleIds=[];
            var roleTree=$.fn.zTree.getZTreeObj("treeDemo");
            var nodes = roleTree.getCheckedNodes(true);
            for (var i=0; i<nodes.length;i++){
                roleIds.push(nodes[i].id);
            }
            var requestBody={
                "roleId":[window.roleId],
                "authIdList":roleIds
            };
            var requestBody = JSON.stringify(requestBody);
            $.ajax({
                url: "user/role/saveUpdate/page.json",
                data: requestBody,
                dataType: "json",
                contentType: "application/json;charset=UTF-8",
                type: "post",
                success:function (response) {
                    var result=response.result;
                    if (result == "SUCCESS"){
                        alert("保存成功");
                    }
                    if (result == "FAIL"){
                        alert("保存失败,请稍后重试");
                    }
                },
                error: function () {
                    alert("未知错误！");
                }
            });
        });
    });

</script>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/jsp/includ/includ-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form action="user/role/page.html" method="post" class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input name="keyword" class="form-control has-success" type="text" placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button type="submit" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
                    </form>
                    <button type="button" id="delBtn" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
                    <button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='user/add.html'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input id="checkAll" type="checkbox"></th>
                                <th>名称</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${empty requestScope.rolePageInfo.list}">
                                <tr>
                                    <td colspan="6" align="center">抱歉，没有查到用户的信息！</td>
                                </tr>
                            </c:if>
                            <c:if test="${!empty requestScope.rolePageInfo.list}">
                                <c:forEach items="${requestScope.rolePageInfo.list}" var="role" varStatus="myStatus">
                                   <c:if test="${myStatus.count % 2==1}">
                                       <tr style="background-color: #8D8D8D">
                                           <td>${myStatus.count}</td>
                                           <td style="display:none">${role.id}</td>
                                           <td><input class="tdCheck" type="checkbox"></td>
                                           <td>${role.name}</td>
                                           <td>
                                               <button type="button"  class="btn role_edit btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>
                                               <button type="button" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>
                                               <button type="button" class="singleDel btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>
                                           </td>
                                       </tr>
                                   </c:if>
                                    <c:if test="${myStatus.count % 2==0}">
                                        <tr>
                                            <td>${myStatus.count}</td>
                                            <td style="display:none">${role.id}</td>
                                            <td><input class="tdCheck" type="checkbox"></td>
                                            <td>${role.name}</td>
                                            <td>
                                                <button type="button" class="btn role_edit btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>
                                                <button type="button" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>
                                                <button type="button" class="singleDel btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>
                                            </td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                            </c:if>

                            </tbody>
                            <tfoot>
                            <tr>
                                <td colspan="6" align="center">
                                    <div id="Pagination" class="pagination"><!-- 这里显示分页 --></div>
                                </td>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="role_edit.jsp"%>
</body>
</html>