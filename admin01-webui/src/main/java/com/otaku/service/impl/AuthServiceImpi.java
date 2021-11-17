package com.otaku.service.impl;

import com.otaku.mapper.AuthMapper;
import com.otaku.pojo.Auth;
import com.otaku.pojo.AuthExample;
import com.otaku.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.util.List;
import java.util.Map;

/**
 * @author Otaku
 * @date 2021/11/14-21:07
 */
@Service
public class AuthServiceImpi implements AuthService {
    @Autowired
    private AuthMapper authMapper;
    @Override
    public List<Auth> getAllAuth() {
        List<Auth> authList = authMapper.selectByExample(new AuthExample());
        return authList;
    }
    @Override
    public List<Integer> getPossessAuthByRoleId(Integer roleId) {
        List<Integer> authByRoleId = authMapper.getPossessAuthByRoleId(roleId);
        return authByRoleId;
    }

    @Override
    public void saveUpdate(Map<String, List<Integer>> map) {
        //从map中获取roleId
        List<Integer> roleIdList = map.get("roleId");
        Integer roleId=roleIdList.get(0);
        //保存之前先删除之前的数据
        int i = deleteAuthById(roleId);
        //保存auth权限数据
        List<Integer> authIdList = map.get("authIdList");
        int saveAuthList = authMapper.saveAuthList(roleId, authIdList);
    }

    @Override
    public int deleteAuthById(Integer id) {
        int i = authMapper.deleteShipRealation(id);
        return i;
    }
}
