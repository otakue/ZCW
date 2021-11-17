function myAddDiyDemo(treeId,treeNode) {
    //treeId是整个树形结构附着的ul标签的id
    //treeDemo是树形节点的全部数据，包括从后端查到的Menu对象的全部属性
    //zTree生成的id的规则
        //例子：treeDemo_7_ico 为span的id，class控制节点图标
        //解析；ul标签的id_当前的节点序号_功能
        //ul的标签可以通过id_当前的序号通过访问treeNode的tId属性得到
    console.log(treeNode);
    var spanId=treeNode.tId+"_ico";
    //通过控制图标的span标签的id找到这个span标签
    //删除旧的class后添加新的class改变节点图标
    $("#"+spanId)
        .removeClass()
        .addClass(treeNode.icon);
}
//由于一次节点移入可触发多次移入鼠标事件，所以会触发多次函数
function myAddHoverDom(treeId,treeNode) {
    //获取a标签值，在后面添加元素
    var anChorId=treeNode.tId+"_a";
    //添加元素显示的span id  zTree框架自动添加的
    var spanId=treeNode.tId+"_btnGrp";
    //防止多次触发创建多次span标签
    if ($("#"+spanId).length > 0){
        return;
    };
    // 准备各个按钮的 HTML 标签
    var addBtn = "<a id='"+treeNode.id+"' class='btn addBtn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#' title='添加子节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-plus rbg '></i></a>";
    var removeBtn = "<a id='"+treeNode.id+"' class='btn removeBtn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#' title=' 删 除 节 点 '>&nbsp;&nbsp;<i class='fa fa-fw fa-times rbg '></i></a>";
    var editBtn = "<a id='"+treeNode.id+"' class='btn editBtn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#' title=' 修 改 节 点 '>&nbsp;&nbsp;<i class='fa fa-fw fa-edit rbg '></i></a>";
    //通过level来判断当前节点等级
    var level = treeNode.level;
    //如果是根节点，只有添加子节点操作
    var btnHtml="";//用来存储按钮
    if (level == 0){
        btnHtml=addBtn;
    }else if (level == 1){//枝节点可以添加子节点，和修改当前节点，删除当前节点
        var length = treeNode.children.length;
        //如果枝节点没有子节点就可以删除
        if (length == 0){
            btnHtml=addBtn+removeBtn+editBtn;
        }else {
            btnHtml=addBtn+editBtn;
        }
    }else if (level == 2){ //如果为叶节点的话就只有删除和修改操作按钮
        btnHtml=removeBtn+editBtn;
    }
    $("#"+anChorId).after("<span id='"+spanId+"'>"+btnHtml+"</span>");
}
//删除鼠标移动到节点生成的span标签
function myRemoveHoverDom(treeId,treeNode) {
    var spanId=treeNode.tId+"_btnGrp";
    $("#"+spanId).remove();
}
//ajax生成节点
function generateTree(){
    $.ajax({
        url: "menu/get/whole/tree.json",
        dataType: "json",
        type: "post",
        success:function(response) {
            var result=response.result;
            if (result=="SUCCESS"){
                var setting={
                    view: {
                        addDiyDom: myAddDiyDemo,
                        addHoverDom: myAddHoverDom,
                        removeHoverDom: myRemoveHoverDom
                    },
                    data:{
                        key:{
                            url: "Xulr"
                        }
                    }
                };
                var zNodes =response.data;
                $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            }else {
                layer.msg(response.message);
            }
        }
    });
}