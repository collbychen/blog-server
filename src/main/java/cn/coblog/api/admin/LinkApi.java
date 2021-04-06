package cn.coblog.api.admin;

import cn.coblog.common.base.BaseResponse;
import cn.coblog.domain.Link;
import org.springframework.web.bind.annotation.*;

/**
 * 链接api
 * @author chens
 * @version 1.0.0
 * @date 2020/8/10
 */
@RequestMapping("link")
public interface LinkApi {

    @GetMapping("all")
    BaseResponse list();

    @PostMapping
    BaseResponse save(@RequestBody Link link);

    @PutMapping
    BaseResponse update(@RequestBody Link link);

    @GetMapping
    BaseResponse info(Long id);

    @DeleteMapping
    BaseResponse remove(Long id);
}
