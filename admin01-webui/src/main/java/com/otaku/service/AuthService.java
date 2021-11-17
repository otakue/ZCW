package com.otaku.service;

import com.otaku.pojo.Auth;

import java.util.List;
import java.util.Map;

/**
 * @author Otaku
 * @date 2021/11/14-21:07
 */
public interface AuthService {
    public List<Auth> getAllAuth();
    public List<Integer> getPossessAuthByRoleId(Integer roleId);
    public void saveUpdate(Map<String,List<Integer>> map);
    public int deleteAuthById(Integer id);
}
