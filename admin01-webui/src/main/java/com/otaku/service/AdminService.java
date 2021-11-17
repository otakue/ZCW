package com.otaku.service;

import com.github.pagehelper.PageInfo;
import com.otaku.pojo.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Otaku
 * @date 2021/10/16-10:00
 */
public interface AdminService {
    public int saveAdmin(Admin admin);
    public List<Admin> getAll();
    public Admin getAdminByLoginAcct(String userName,String password);
    public PageInfo<Admin> selectByKeyWord(String keyword,int pageStart,int pageSize);
    public void removeById(Integer adminId);
    public void insertNewRelationship(@Param("adminId") Integer adminId, @Param("roleIdList") List<Integer> roleIdList);
}
