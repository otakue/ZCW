//生成节点
function generateRoleAuthTree() {
    //发送ajax请求获取ztree数据
    var ajaxReturn=$.ajax({
        url: "user/role/getAllAuth/page.json",
        //data: roleId,
        dataType: "json",
        type: "post",
        async: false
    });
    //如果响应数据是不200，说明出错，没必要继续执行
    if(ajaxReturn.status != 200){
        alert("请求处理出错，响应状态码是："+ajaxReturn.status+"-问题是："+ajaxReturn.statusText);
        return;
    }
    console.log(ajaxReturn);
    //获取响应的数据
    var authList=ajaxReturn.responseJSON.data;
    //发送ajax请求获取已拥有的数据权限
    var ajaxReturnPossessAuth=$.ajax({
        url: "user/role/getPossessAuth/page.json",
        data: {
            "roleId":window.roleId
        },
        dataType: "json",
        type: "post",
        async: false
    });
    console.log(ajaxReturnPossessAuth);
    var possessAuthRoleId = ajaxReturnPossessAuth.responseJSON.data;
    //如果响应数据是不200，说明出错，没必要继续执行
    if(ajaxReturnPossessAuth.status != 200){
        alert("请求处理出错，响应状态码是："+ajaxReturnPossessAuth.status+"-问题是："+ajaxReturnPossessAuth.statusText);
        return;
    }
    var setting={
        data:{
            simpleData:{
                enable: true,
                pIdKey: "categoryId"
            },
            key:{
                name: "title"
            }
        },
        check: {
            enable: true,
            chkStyle: "checkbox"
        }
    };
    $.fn.zTree.init($("#treeDemo"), setting, authList);
    var roleTree=$.fn.zTree.getZTreeObj("treeDemo");
    for (var i=0;i<possessAuthRoleId.length;i++){
        var treeNode=roleTree.getNodeByParam("id",possessAuthRoleId[i]);
        roleTree.checkNode(treeNode,true,false);
    }
    roleTree.expandAll(true);

}
