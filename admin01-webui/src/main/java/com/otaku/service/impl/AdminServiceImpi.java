package com.otaku.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.otaku.mapper.AdminMapper;
import com.otaku.pojo.Admin;
import com.otaku.pojo.AdminExample;
import com.otaku.service.AdminService;
import com.otaku.utils.MD5Util;
import com.otaku.utils.loginfailexception;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author Otaku
 * @date 2021/10/16-10:01
 */
@Service
public class AdminServiceImpi implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public int saveAdmin(Admin admin) {
        int i = adminMapper.insert(admin);
        return i;
    }

    @Override
    public List<Admin> getAll() {
        List<Admin> adminList = adminMapper.selectByExample(new AdminExample());
        return adminList;
    }

    @Override
    public Admin getAdminByLoginAcct(String userName, String password) {
        //创建adminExample对象
        AdminExample adminExample = new AdminExample();
        //添加条件
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andLoginAcctEqualTo(userName);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        //判断通过账号是否可以查询到信息
        if (admins.size() == 0){
            throw new loginfailexception("账号输入错误");
        }
        //如果出现了多个账号，数据库数据出现了问题
        if (admins.size()>2){
            throw new loginfailexception("系统出现异常,联系管理员");
        }
        //账号密码对比
        Admin admin = admins.get(0);
        String userPswd = admin.getUserPswd();
        String md5UerPswd = MD5Util.getMd5(password);
        if (! Objects.equals(userPswd, md5UerPswd)){
            throw new loginfailexception("密码输入错误，请重新输入");
        }
        return admin;
    }

    @Override
    public PageInfo<Admin> selectByKeyWord(String keyword,int pageStart,int pageSize) {
        PageHelper.startPage(pageStart, pageSize);
        List<Admin> admins = adminMapper.selectByKeyWord(keyword);
        PageInfo<Admin> adminPageInfo = new PageInfo<>(admins);
        return adminPageInfo;
    }

    @Override
    public void removeById(Integer adminId) {
        adminMapper.deleteByPrimaryKey(adminId);
    }

    @Override
    public void insertNewRelationship(Integer adminId, List<Integer> roleIdList) {
        adminMapper.deleteOLdRelationship(adminId);
        adminMapper.insertNewRelationship(adminId, roleIdList);
    }
}
