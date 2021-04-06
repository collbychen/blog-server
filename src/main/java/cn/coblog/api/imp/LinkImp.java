package cn.coblog.api.imp;

import cn.coblog.api.admin.LinkApi;
import cn.coblog.common.base.BaseResponse;
import cn.coblog.domain.Link;
import cn.coblog.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LinkImp implements LinkApi {

    @Autowired
    private LinkService linkService;

    @Override
    public BaseResponse list() {
        return BaseResponse.success("获取成功", linkService.getAll());
    }

    @Override
    public BaseResponse save(Link link) {
        return linkService.save(link) ? BaseResponse.success("保存成功") : BaseResponse.fail("保存失败");
    }

    @Override
    public BaseResponse update(Link link) {
        return linkService.updateById(link) ? BaseResponse.success("修改成功", link) : BaseResponse.fail("修改失败");
    }

    @Override
    public BaseResponse info(Long id) {
        return BaseResponse.success("查询成功", linkService.getById(id));
    }

    @Override
    public BaseResponse remove(Long id) {
        return linkService.removeById(id) ? BaseResponse.success("删除成功") : BaseResponse.fail("删除失败");
    }

}
