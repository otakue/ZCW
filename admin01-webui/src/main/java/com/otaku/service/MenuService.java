package com.otaku.service;

import com.otaku.pojo.Menu;

import java.util.List;

/**
 * @author Otaku
 * @date 2021/10/28-8:29
 */
public interface MenuService {
    public List<Menu> getAll();

    public void addTree(Menu menu);
}
