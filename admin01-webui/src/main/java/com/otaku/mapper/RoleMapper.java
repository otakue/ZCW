package com.otaku.mapper;

import com.otaku.pojo.Auth;
import com.otaku.pojo.Role;
import com.otaku.pojo.RoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    long countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    List<Role> accredited(Integer adminId);
    List<Role> unaccredited(Integer adminId);
    List<Role> selectRoleByWord(String keyword);
    int deleteMuchRole(List<Integer> roleIds);
}