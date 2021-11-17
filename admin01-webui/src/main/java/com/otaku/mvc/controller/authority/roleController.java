package com.otaku.mvc.controller.authority;

import com.github.pagehelper.PageInfo;
import com.otaku.pojo.Auth;
import com.otaku.pojo.Role;
import com.otaku.service.AuthService;
import com.otaku.service.RoleService;
import com.otaku.utils.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @author Otaku
 * @date 2021/11/10-13:57
 */
@Controller
public class roleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthService authService;

    @RequestMapping("user/role/page.html")
    public ModelAndView skipRolePage(@RequestParam(value = "keyword",required = false,defaultValue = "") String keyword,
                                     @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize){
        PageInfo<Role> rolePageInfo = roleService.selectRoleByKeyWord(pageNum, pageSize, keyword);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("rolePageInfo",rolePageInfo);
        modelAndView.setViewName("authority/role-page");
        return modelAndView;
    }
    @RequestMapping("user/role/MuchDelete/page.json")
    @ResponseBody
    public ResultEntity muchDelete(@RequestBody List<Integer> roleIds){
        roleService.muchDeltet(roleIds);
        return ResultEntity.successWithoutData();
    }
    @RequestMapping("user/role/getAllAuth/page.json")
    @ResponseBody
    public ResultEntity getAllAuthForRole(){
        List<Auth> allAuth = authService.getAllAuth();
        return ResultEntity.successWithoutData(allAuth);
    }
    @RequestMapping("user/role/getPossessAuth/page.json")
    @ResponseBody
    public ResultEntity getPossessAuth(@RequestParam("roleId") Integer roleId){
        List<Integer> possessAuthByRoleId = authService.getPossessAuthByRoleId(roleId);
        return ResultEntity.successWithoutData(possessAuthByRoleId);
    }
    @RequestMapping("user/role/saveUpdate/page.json")
    @ResponseBody
    public ResultEntity saveUpdateAuth(@RequestBody Map<String,List<Integer>> map){
        authService.saveUpdate(map);
        return ResultEntity.successWithoutData();
    }

}
