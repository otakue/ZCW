package com.otaku.mvc.controller.authority;

import com.github.pagehelper.PageInfo;
import com.otaku.pojo.Admin;
import com.otaku.pojo.Role;
import com.otaku.service.AdminService;
import com.otaku.service.RoleService;
import com.otaku.utils.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 * @author Otaku
 * @date 2021/10/22-3:58
 */
@Controller
public class authority {
    @Autowired
    private AdminService adminService;
    @Autowired
    private RoleService roleService;
    @RequestMapping("user/authority.html")
    public ModelAndView getUserPageInfo(@RequestParam(value = "keyword",defaultValue = "") String keyword,
                                        @RequestParam(value = "pageStart",defaultValue = "1") Integer pageStart,
                                        @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        PageInfo<Admin> adminPageInfo = adminService.selectByKeyWord(keyword, pageStart, pageSize);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("adminPageInfo", adminPageInfo);
        modelAndView.setViewName("authority/user-page");
        return modelAndView;
    }
    @RequestMapping("user/singleDel/{adminId}.html")
    @ResponseBody
    public String singleDelUser(@PathVariable("adminId") Integer adminId){
        adminService.removeById(adminId);
        return "删除成功";
    }
    @RequestMapping("user/test.json")
    @ResponseBody
    public ResultEntity save(){
        return ResultEntity.successWithoutData();
    }
    @RequestMapping("user/accreditEdit.html")
    public ModelAndView accreditEdit(@RequestParam("adminId") Integer adminId,ModelAndView modelAndView){
        //查询已存在的角色信息和不存在的角色信息
        List<Role> accredited = roleService.accredited(adminId);
        List<Role> unaccredited = roleService.unaccredited(adminId);
        modelAndView.addObject("accredited", accredited);
        modelAndView.addObject("unaccredited", unaccredited);
        modelAndView.setViewName("authority/accredit-user");
        return modelAndView;
    }
    @RequestMapping("/user/save/accreditEdit.html")
    public String saveAccreditEdit(@RequestParam("adminId")Integer adminId,
                                   @RequestParam("roleIdList") List<Integer> RoleIdList,
                                   @RequestParam("pageStart") Integer pageStart,
                                   @RequestParam(value = "keyword",required = false) String keyword){
        adminService.insertNewRelationship(adminId, RoleIdList);
        return "redirect:/user/to/authority.html?pageStart="+pageStart+"&keyword="+keyword;
    }
}
