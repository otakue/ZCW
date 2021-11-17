package com.otaku.mvc.controller.Menu;

import com.otaku.pojo.Menu;
import com.otaku.service.MenuService;
import com.otaku.utils.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Otaku
 * @date 2021/10/28-14:43
 */
@Controller
public class MenuController {
    @Autowired
    private MenuService menuService;
    @RequestMapping("menu/get/whole/tree.json")
    @ResponseBody
    public ResultEntity<Menu> getWholeTreeNew(){
        //查询全部menu对象
        List<Menu> menuList = menuService.getAll();
        //设置根节点
        Menu root=null;
        //创建Map来存储id和menu对象的对应关系便于查找父节点
        Map<Integer,Menu> menuMap=new HashMap<>();
        //遍历menuList填充menuMap
        for (Menu menu : menuList){
            Integer id=menu.getId();
            menuMap.put(id, menu);
        }
        //再次遍历menuList查找父节点，组装父子节点
        for (Menu menu : menuList){
            //判断当前menu pid属性值
            Integer pid = menu.getPid();
            //如果pid为null的话，就是根节点
            if (pid == null){
                root=menu;
                //如果是根节点，就没有父节点，及跳出当前本次循环
                continue;
            }
            //如果pid不为null，说明当前节点有父节点，那么可以根据 pid 找到menMap中的对应的menu对象
            Menu father=menuMap.get(pid);
            //将当前子节点存入父节点Children集合
            father.getChildren().add(menu);
        }
        return ResultEntity.successWithoutData(root);

    }
    @RequestMapping("menu/get/whole/AddTree.json")
    @ResponseBody
    public ResultEntity addTree(Menu menu){
        menuService.addTree(menu);
        return ResultEntity.successWithoutData();
    }
}
