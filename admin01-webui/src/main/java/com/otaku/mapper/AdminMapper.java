package com.otaku.mapper;

import com.otaku.pojo.Admin;
import com.otaku.pojo.AdminExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {
    long countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
    List<Admin> selectByKeyWord(String keyword);
    int deleteOLdRelationship(@Param("adminId")Integer adminId);
    int insertNewRelationship(@Param("adminId")Integer adminId,@Param("RoleIdList") List<Integer> RoleIdList);
}