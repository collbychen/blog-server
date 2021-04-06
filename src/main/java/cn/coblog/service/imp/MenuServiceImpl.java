package cn.coblog.service.imp;

import cn.coblog.domain.Menu;
import cn.coblog.mapper.MenuMapper;
import cn.coblog.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单接口实现
 * @author chens
 * @version 1.0.0
 * @date 2020/8/10
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    @Override
    public boolean save(Menu menu) {
        return menuMapper.insert(menu) > 0;
    }

    @Override
    public boolean updateById(Menu menu) {
        return menuMapper.updateByPrimaryKeySelective(menu) > 0;
    }

    @Override
    public boolean removeById(Long id) {
        return menuMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public Menu getById(Long id) {
        return menuMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Menu> getAll() {
        return menuMapper.selectAll();
    }
}




