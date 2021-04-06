package cn.coblog.api.imp;

import cn.coblog.api.admin.ConfigApi;
import cn.coblog.common.base.BaseResponse;
import cn.coblog.domain.Config;
import cn.coblog.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigImp implements ConfigApi {

    @Autowired
    private ConfigService configService;

    @Override
    public BaseResponse list() {
        return BaseResponse.success("保存成功",configService.list());
    }

    @Override
    public BaseResponse save(Config config) {
        return configService.save(config) ? BaseResponse.success("保存成功"): BaseResponse.fail("保存失败");
    }

    @Override
    public BaseResponse update(Config config) {
        return configService.updateById(config) ? BaseResponse.success("修改成功"): BaseResponse.fail("修改失败");

    }

    @Override
    public BaseResponse info(Long id) {
        return BaseResponse.success("查询成功", configService.getById(id));
    }

    @Override
    public BaseResponse remove(Long id) {
        return configService.removeById(id) ? BaseResponse.success("删除成功"): BaseResponse.fail("删除失败");
    }

    @Override
    public BaseResponse global() {
        return BaseResponse.success("查询成功", configService.getAllGlobal());
    }

}
