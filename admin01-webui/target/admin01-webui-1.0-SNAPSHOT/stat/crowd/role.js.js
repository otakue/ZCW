//生成节点
function generateRoleAuthTree() {
    //发送ajax请求获取数据
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
    //获取响应的数据
    var authList=ajaxReturn.responseJson.data;
    var setting={
        data:{
            simpleData:{
                enable: false
            },
            key:{
                name: "title",
                pIdKey: "categoryId"
            }
        }
    };
    $.fn.zTree.init($("#treeDemo"), setting, authList);
}
