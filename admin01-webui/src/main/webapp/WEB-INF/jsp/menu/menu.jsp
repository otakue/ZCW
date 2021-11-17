<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<html>
<%@include file="/WEB-INF/jsp/includ/includ-head.jsp" %>
<link rel="stylesheet" href="stat/ztree/zTreeStyle.css"/>
<script type="text/javascript" src="stat/ztree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="stat/crowd/menu.js"></script>
<body>
    <script type="text/javascript">
    $(function() {
        generateTree();
        $("#treeDemo").on("click",".addBtn",function() {
            //当前的枝节点是添加的叶节点的父元素
            window.pid=this.id;
             $('#menuAddModal').modal('show');
             return false;
        });
        //点击保存按钮添加事件
        $("#menuSaveBtn").click(function() {
            //获取要保存的节点名称
            var name=$.trim($("#menuAddModal [name=name]").val());
            //获取要保存的路径
            var url=$.trim($("#menuAddModal [name=url]").val());
            //获取保存的自定义控件
            var icon=$.trim($("#menuAddModal [name=icon]:checked").val());
            //发送添加ajax请求
            $.ajax({
                url: "menu/get/whole/AddTree.json",
                type: "post",
                data: {
                    "pid": window.pid,
                    "name": name,
                    "url": url,
                    "icon": icon
                 },
                dataType: "json",
                success:function(response) {
                    var result=response.result;
                    if (result == "SUCCESS"){
                        layer.msg("保存成功");
                        generateTree();
                    }if (result == "FAIL"){
                        layer.msg("保存失败"+response.message);
                     }
                  },
                error:function(response) {
                    layer.msg(response.status+""+response.statusText);
                }
             });
         $('#menuAddModal').modal('hide');
        });
        //更新要显示更新的name，url，自定义控件
        //通过点击编辑按钮就获取id，再通过zTree方法 var treeObj=$.fn.zTree.getZTreeObj("treeDemo");treeDemo是容器，使用的是ul
        //通过var node = treeObj.getNodeByParam("id", 1, null);获取到节点对象 .name .url .icon获取值
        //radion按钮选择器通过方法 $("menuEditModal [name=icon]").val([node.icon]);
        //点击保存通过mapper方法updateByPrimaryKeySelective();通过id更新 pid不传入

    });
    </script>
<%@include file="/WEB-INF/jsp/includ/includ-nav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/jsp/includ/includ-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
             <div class="panel panel-default">
                <div class="panel-heading">
                     <i class="glyphicon glyphicon-th-list"></i>
                    权限菜单列表
                    <div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal">
                    <i class="glyphicon glyphicon-question-sign"></i>
                    </div>
                </div>
            <div class="panel-body">
                <ul id="treeDemo" class="ztree"></ul>
            </div>
            </div>
        </div>
    </div>
</div>
    <%@include file="/WEB-INF/jsp/menu/modal-menu-add.jsp"%>
    <%@include file="/WEB-INF/jsp/menu/modal-menu-edit.jsp"%>
    <%@include file="/WEB-INF/jsp/menu/modal-menu-confirm.jsp"%>
    </body>
</html>