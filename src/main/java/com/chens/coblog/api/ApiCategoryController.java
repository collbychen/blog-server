package com.chens.coblog.api;


import com.chens.coblog.common.base.BaseResponse;
import com.chens.coblog.domain.Category;
import com.chens.coblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 分类管理
 * @author chens
 * @version 1.0.0
 * @date 2020/8/29 7:30
 */
@RestController
@RequestMapping("api/category")
public class ApiCategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("all")
    public BaseResponse getAll() {
        return BaseResponse.success("查询成功", categoryService.getAll());
    }

    @PostMapping
    public BaseResponse save(@RequestBody Category category) {
        return categoryService.save(category)? BaseResponse.success("删除成功") : BaseResponse.fail("删除失败");
    }

    @PutMapping
    public BaseResponse update(@RequestBody Category category) {
        return categoryService.updateById(category) ? BaseResponse.success("修改成功"): BaseResponse.fail("修改失败");
    }

    @GetMapping
    public BaseResponse info(Long id) {
        return BaseResponse.success("查询成功", categoryService.getById(id));
    }

    @DeleteMapping
    public BaseResponse remove(Long id) {
        return categoryService.removeById(id) ? BaseResponse.success("删除成功") : BaseResponse.fail("删除失败");
    }
}
