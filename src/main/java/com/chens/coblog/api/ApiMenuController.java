package com.chens.coblog.api;


import com.chens.coblog.common.base.BaseResponse;
import com.chens.coblog.domain.Menu;
import com.chens.coblog.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 目录管理
 * @author chens
 * @version 1.0.0
 * @date 2020/9/11
 */
@RestController
@RequestMapping("api/menu")
public class ApiMenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("all")
    public BaseResponse list() {
        return BaseResponse.success("查询成功", menuService.getAll());
    }

    @PostMapping
    public BaseResponse save(@RequestBody Menu menu) {
//        ValidatorUtils.validate(menu);
        return menuService.save(menu) ? BaseResponse.success("保存成功"): BaseResponse.fail("保存失败");
    }

    @PutMapping
    public BaseResponse update(@RequestBody Menu menu) {
//        ValidatorUtils.validate(menu);
        return menuService.updateById(menu) ? BaseResponse.success("修改成功"): BaseResponse.fail("修改失败");
    }

    @GetMapping
    public BaseResponse info(Long id) {
        return BaseResponse.success("查询成功", menuService.getById(id));
    }

    @DeleteMapping
    public BaseResponse remove(Long id) {
        return menuService.removeById(id) ? BaseResponse.success("删除成功") : BaseResponse.fail("删除失败");
    }
}
