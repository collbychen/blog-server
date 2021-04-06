package cn.coblog.api.imp;


import cn.coblog.api.admin.CategoryApi;
import cn.coblog.common.base.BaseResponse;
import cn.coblog.domain.Category;
import cn.coblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryImp implements CategoryApi {

    @Autowired
    private CategoryService categoryService;

    @Override
    public BaseResponse getAll() {
        return BaseResponse.success("查询成功", categoryService.getAll());
    }

    @Override
    public BaseResponse save(Category category) {
        return categoryService.save(category)? BaseResponse.success("删除成功") : BaseResponse.fail("删除失败");
    }

    @Override
    public BaseResponse update(Category category) {
        return categoryService.updateById(category) ? BaseResponse.success("修改成功"): BaseResponse.fail("修改失败");
    }

    @Override
    public BaseResponse info(Long id) {
        return BaseResponse.success("查询成功", categoryService.getById(id));
    }

    @Override
    public BaseResponse remove(Long id) {
        return categoryService.removeById(id) ? BaseResponse.success("删除成功") : BaseResponse.fail("删除失败");
    }

}
