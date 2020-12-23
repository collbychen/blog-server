package com.chens.coblog.service;

import com.chens.coblog.domain.Menu;

import java.util.List;

/**
 * 菜单接口
 * @author chens
 * @version 1.0.0
 * @date 2020/8/10
 */
public interface MenuService {

    boolean save(Menu menu);

    boolean updateById(Menu menu);

    boolean removeById(Long id);

    Menu getById(Long id);

    List<Menu> getAll();

}




