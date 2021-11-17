package com.otaku.service.impl;

import com.otaku.mapper.MenuMapper;
import com.otaku.pojo.Menu;
import com.otaku.pojo.MenuExample;
import com.otaku.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Otaku
 * @date 2021/10/28-8:29
 */
@Service
public class MenuServiceImpi implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getAll() {
        return menuMapper.selectByExample(new MenuExample());
    }

    @Override
    public void addTree(Menu menu) {
        menuMapper.insert(menu);
    }
}
