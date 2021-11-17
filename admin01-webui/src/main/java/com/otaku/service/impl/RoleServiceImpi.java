package com.otaku.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.otaku.mapper.RoleMapper;
import com.otaku.pojo.Auth;
import com.otaku.pojo.Role;
import com.otaku.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Otaku
 * @date 2021/11/4-22:10
 */
@Service
public class RoleServiceImpi implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> accredited(Integer adminId) {
        List<Role> accredited = roleMapper.accredited(adminId);
        return accredited;
    }

    @Override
    public List<Role> unaccredited(Integer amdinId) {
        List<Role> unaccredited = roleMapper.unaccredited(amdinId);
        return unaccredited;
    }

    @Override
    public PageInfo<Role> selectRoleByKeyWord(Integer pageNum, Integer pageSize, String keyword) {
        PageHelper.startPage(pageNum, pageSize);
        List<Role> roleList = roleMapper.selectRoleByWord(keyword);
        PageInfo<Role> pageInfo = new PageInfo<>(roleList);
        return pageInfo;
    }

    @Override
    public void muchDeltet(List<Integer> roleIds) {
        roleMapper.deleteMuchRole(roleIds);
    }


}
