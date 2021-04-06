package cn.coblog.api.imp;

import cn.coblog.api.admin.TagApi;
import cn.coblog.common.base.BaseResponse;
import cn.coblog.domain.Tag;
import cn.coblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class TagImp implements TagApi {

    @Autowired
    private TagService tagService;

    @Override
    public BaseResponse list() {
        return BaseResponse.success("查询成功", tagService.getAll());
    }

    @Override
    public BaseResponse save(Tag tag) {
//        ValidatorUtils.validate(tag);
        boolean res = tagService.save(tag);
        return res ? BaseResponse.success("保存成功") : BaseResponse.fail("保存失败");
    }

    @Override
    public BaseResponse update(Tag tag) {
//        ValidatorUtils.validate(tag);
        if (Objects.isNull(tag.getId())) {
            return BaseResponse.fail("ID不能为空");
        }
        boolean res = tagService.updateById(tag);
        return res ? BaseResponse.success("修改成功", tag) : BaseResponse.fail("修改失败");
    }

    @Override
    public BaseResponse info(Long id) {
        return BaseResponse.success("查询成功", tagService.getById(id));
    }

    @Override
    public BaseResponse remove(Long id) {
        boolean res = tagService.removeById(id);
        return res ? BaseResponse.success("删除成功") : BaseResponse.fail("删除失败");
    }

}
