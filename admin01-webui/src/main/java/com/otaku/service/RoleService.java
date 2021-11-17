package com.otaku.service;

import com.github.pagehelper.PageInfo;
import com.otaku.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Otaku
 * @date 2021/11/4-22:10
 */
public interface RoleService {
    public List<Role> accredited(Integer adminId);
    public List<Role> unaccredited(Integer amdinId);
    public PageInfo<Role> selectRoleByKeyWord(Integer pageNum,Integer pageSize,String keyword);
    public void muchDeltet(List<Integer> roleIds);
}
