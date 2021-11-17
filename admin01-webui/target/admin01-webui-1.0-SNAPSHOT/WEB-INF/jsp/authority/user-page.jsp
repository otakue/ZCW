<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<%@include file="/WEB-INF/jsp/includ/includ-head.jsp" %>
<link rel="stylesheet" href="stat/css/pagination.css">
<script type="text/javascript" src="stat/jquery/jquery.pagination.js"></script>
<script type="text/javascript">
    $(function () {
        //分页导航条
       initPagination();
       //删除单个员工
       singleDel();
       accredit_edit();
    });
    //分页导航条使用pagination框架
    function initPagination(){
        //获取总记录数
        var totalRecord=${requestScope.adminPageInfo.total};
        //声明 Pagination 设置属性的 JSON 对象
        var properties={
            num_edge_entries: 1, //边缘页数
            num_display_entries: 4, //主体页数
            current_page:${requestScope.adminPageInfo.pageNum-1},
            callback: pageselectCallback,
            items_per_page:${requestScope.adminPageInfo.pageSize}, //每页显示多少条数据
            prev_text:"上一页", //"前一页”分页按钮上显示的文字
            next_text:"下一页"  //“下一页”分页按钮上显示的文字
        };
        $("#Pagination").pagination(totalRecord,properties);
    };
    //回调函数，点击分页导航条上的分页按钮时跳转到指定分页
    function pageselectCallback(page_index, jq) {
        //获取当前的page页面num
        var currentPage=page_index+1;
        //跳转链接
        window.location.href="user/authority.html?pageStart="+currentPage+"&keyword=${param.keyword}";
        return false;
    };
    //删除单个员工的事件
    function singleDel() {
        $(".singleDel").click(function () {
            var userId=$(this).parents("tr").find("td:eq(1)").text();
            $.ajax({
                url: "user/singleDel/"+userId+".html",
                type: "post",
                success:function () {
                    alert("删除成功");
                    window.location.reload();
                },
                error:function () {
                    alert("删除失败，出现了错误！")
                }
            })
        })
    };
    //点击角色用户修改
    function accredit_edit() {
        $("#accredit_edit").click(function () {
            var adminId=$(this).parents("tr").find("td:eq(1)").text();
            window.location.href="user/accreditEdit.html?adminId="+adminId;
        });
    }

</script>
<body>
<%@include file="/WEB-INF/jsp/includ/includ-nav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/jsp/includ/includ-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form action="user/authority.html" class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input name="keyword" class="form-control has-success" type="text" placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button type="submit" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
                    </form>
                    <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
                    <button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='user/add.html'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input type="checkbox"></th>
                                <th>账号</th>
                                <th>名称</th>
                                <th>邮箱地址</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${empty requestScope.adminPageInfo.list}">
                                <tr>
                                    <td colspan="6" align="center">抱歉，没有查到用户的信息！</td>
                                </tr>
                            </c:if>
                            <c:if test="${!empty requestScope.adminPageInfo.list}">
                                <c:forEach items="${requestScope.adminPageInfo.list}" var="admin" varStatus="myStatus">
                                    <tr>
                                        <td>${myStatus.count}</td>
                                        <td style="display:none">${admin.id}</td>
                                        <td><input type="checkbox"></td>
                                        <td>${admin.loginAcct}</td>
                                        <td>${admin.userName}</td>
                                        <td>${admin.email}</td>
                                        <td>
                                            <button type="button" id="accredit_edit" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>
                                            <button type="button" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>
                                            <button type="button" class="singleDel btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>
                                        </td>
                                    </tr>
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
</body>
</html>