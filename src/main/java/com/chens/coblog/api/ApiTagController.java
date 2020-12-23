package com.chens.coblog.api;


import com.chens.coblog.common.base.BaseResponse;
import com.chens.coblog.domain.Tag;
import com.chens.coblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * 标签管理
 * @author chens
 * @version 1.0.0/
 * @date 2020/9/11
 */
@RestController
@RequestMapping("api/tag")
public class ApiTagController {

    @Autowired
    private TagService tagService;

    @GetMapping("all")
    public BaseResponse list() {
        return BaseResponse.success("查询成功", tagService.getAll());
    }

    @PostMapping
    public BaseResponse save(@RequestBody Tag tag) {
//        ValidatorUtils.validate(tag);
        boolean res = tagService.save(tag);
        return res ? BaseResponse.success("保存成功") : BaseResponse.fail("保存失败");
    }

    @PutMapping
    public BaseResponse update(@RequestBody Tag tag) {
//        ValidatorUtils.validate(tag);
        if (Objects.isNull(tag.getId())) {
            return BaseResponse.fail("ID不能为空");
        }
        boolean res = tagService.updateById(tag);
        return res ? BaseResponse.success("修改成功", tag) : BaseResponse.fail("修改失败");
    }

    @GetMapping
    public BaseResponse info(Long id) {
        return BaseResponse.success("查询成功", tagService.getById(id));
    }

    @DeleteMapping
    public BaseResponse remove(Long id) {
        boolean res = tagService.removeById(id);
        return res ? BaseResponse.success("删除成功") : BaseResponse.fail("删除失败");
    }
}
