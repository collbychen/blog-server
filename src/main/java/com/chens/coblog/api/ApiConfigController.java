package com.chens.coblog.api;

import com.chens.coblog.common.base.BaseResponse;
import com.chens.coblog.domain.Config;
import com.chens.coblog.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 配置管理
 * @author chens
 * @version 1.0.0
 * @date 2020/8/29 7:30
 */
@RestController
@RequestMapping("api/config")
public class ApiConfigController {

    @Autowired
    private ConfigService configService;

    @GetMapping("all")
    public BaseResponse list() {
        return BaseResponse.success("保存成功",configService.list());
    }

    @PostMapping
    public BaseResponse save(@RequestBody Config config) {
        return configService.save(config) ? BaseResponse.success("保存成功"): BaseResponse.fail("保存失败");
    }


    @PutMapping
    public BaseResponse update(@RequestBody Config config) {
        return configService.updateById(config) ? BaseResponse.success("修改成功"): BaseResponse.fail("修改失败");

    }

    @GetMapping
    public BaseResponse info(Long id) {
        return BaseResponse.success("查询成功", configService.getById(id));
    }

    @DeleteMapping
    public BaseResponse remove(Long id) {
        return configService.removeById(id) ? BaseResponse.success("删除成功"): BaseResponse.fail("删除失败");
    }

    @GetMapping("global")
    public BaseResponse global() {
        return BaseResponse.success("查询成功", configService.getAllGlobal());
    }
}
