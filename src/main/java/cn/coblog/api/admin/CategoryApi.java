package cn.coblog.api.admin;

import cn.coblog.common.base.BaseResponse;
import cn.coblog.domain.Category;
import org.springframework.web.bind.annotation.*;

/**
 * admin分类api
 * @author chens
 * @version 1.0.0
 * @date 2020/8/29 7:30
 */
@RequestMapping("category")
public interface CategoryApi {

    @GetMapping("all")
    BaseResponse getAll();

    @PostMapping
    BaseResponse save(@RequestBody Category category);

    @PutMapping
    BaseResponse update(@RequestBody Category category);

    @GetMapping
    BaseResponse info(Long id);

    @DeleteMapping
    BaseResponse remove(Long id);

}
