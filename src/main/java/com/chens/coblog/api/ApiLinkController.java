package com.chens.coblog.api;


import com.chens.coblog.common.base.BaseResponse;
import com.chens.coblog.domain.Link;
import com.chens.coblog.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 链接管理
 * @author chens
 * @version 1.0.0
 * @date 2020/8/10
 */
@RestController
@RequestMapping("api/link")
public class ApiLinkController {

    @Autowired
    private LinkService linkService;

    @GetMapping("all")
    public BaseResponse list() {
        return BaseResponse.success("获取成功", linkService.getAll());
    }

    @PostMapping
    public BaseResponse save(@RequestBody Link link) {
        return linkService.save(link) ? BaseResponse.success("保存成功") : BaseResponse.fail("保存失败");
    }

    @PutMapping
    public BaseResponse update(@RequestBody Link link) {
        return linkService.updateById(link) ? BaseResponse.success("修改成功", link) : BaseResponse.fail("修改失败");
    }

    @GetMapping
    public BaseResponse info(Long id) {
        return BaseResponse.success("查询成功", linkService.getById(id));
    }

    @DeleteMapping
    public BaseResponse remove(Long id) {
        return linkService.removeById(id) ? BaseResponse.success("删除成功") : BaseResponse.fail("删除失败");
    }
}
