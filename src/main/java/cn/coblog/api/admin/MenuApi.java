package cn.coblog.api.admin;

import cn.coblog.common.base.BaseResponse;
import cn.coblog.domain.Menu;
import org.springframework.web.bind.annotation.*;

/**
 * 目录管理
 * @author chens
 * @version 1.0.0
 * @date 2020/9/11
 */
@RequestMapping("menu")
public interface MenuApi {

    @GetMapping("all")
    BaseResponse list();

    @PostMapping
    BaseResponse save(@RequestBody Menu menu);

    @PutMapping
    BaseResponse update(@RequestBody Menu menu);

    @GetMapping
    BaseResponse info(Long id);

    @DeleteMapping
    BaseResponse remove(Long id);

}
