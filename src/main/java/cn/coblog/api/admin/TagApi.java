package cn.coblog.api.admin;

import cn.coblog.common.base.BaseResponse;
import cn.coblog.domain.Tag;
import org.springframework.web.bind.annotation.*;

/**
 * admin标签api
 * @author chens
 * @version 1.0.0/
 * @date 2020/9/11
 */
@RequestMapping("tag")
public interface TagApi {

    @GetMapping("all")
    BaseResponse list();

    @PostMapping
    BaseResponse save(@RequestBody Tag tag);

    @PutMapping
    BaseResponse update(@RequestBody Tag tag);

    @GetMapping
    BaseResponse info(Long id);

    @DeleteMapping
    BaseResponse remove(Long id);

}
