package cn.coblog.api.imp;

import cn.coblog.api.admin.MenuApi;
import cn.coblog.common.base.BaseResponse;
import cn.coblog.domain.Menu;
import cn.coblog.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuImp implements MenuApi {

    @Autowired
    private MenuService menuService;

    @Override
    public BaseResponse list() {
        return BaseResponse.success("查询成功", menuService.getAll());
    }

    @Override
    public BaseResponse save(Menu menu) {
        return menuService.save(menu) ? BaseResponse.success("保存成功"): BaseResponse.fail("保存失败");
    }

    @Override
    public BaseResponse update(Menu menu) {
        return menuService.updateById(menu) ? BaseResponse.success("修改成功"): BaseResponse.fail("修改失败");
    }

    @Override
    public BaseResponse info(Long id) {
        return BaseResponse.success("查询成功", menuService.getById(id));
    }

    @Override
    public BaseResponse remove(Long id) {
        return menuService.removeById(id) ? BaseResponse.success("删除成功") : BaseResponse.fail("删除失败");
    }

}
