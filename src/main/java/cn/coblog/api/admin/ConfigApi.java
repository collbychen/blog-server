package cn.coblog.api.admin;

import cn.coblog.common.base.BaseResponse;
import cn.coblog.domain.Config;
import org.springframework.web.bind.annotation.*;

/**
 * admin配置api
 * @author chens
 * @version 1.0.0
 * @date 2020/8/29 7:30
 */
@RequestMapping("config")
public interface ConfigApi {

    @GetMapping("all")
    BaseResponse list();

    @PostMapping
    BaseResponse save(@RequestBody Config config);


    @PutMapping
    BaseResponse update(@RequestBody Config config);

    @GetMapping
    BaseResponse info(Long id);

    @DeleteMapping
    BaseResponse remove(Long id);

    @GetMapping("global")
    BaseResponse global();

}
