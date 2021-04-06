package cn.coblog.api.admin;

import cn.coblog.common.base.BaseResponse;
import cn.coblog.domain.Article;
import org.springframework.web.bind.annotation.*;

/**
 * admin文章api
 * @author chens
 * @version 1.0.0
 * @date 2020/8/29 7:30
 */
@RequestMapping("article")
public interface ArticleApi {

    @GetMapping("lists")
    BaseResponse list(Integer current, Integer size, String title, Integer status, Integer type, Integer categoryId, String sort);

    @PostMapping
    BaseResponse save(@RequestBody Article article);

    @PutMapping
    BaseResponse modify(@RequestBody Article article);

    @GetMapping
    BaseResponse getArticle(Long id);

    @DeleteMapping
    BaseResponse remove(Long[] ids);

    /**
     * 查询最新的文章
     */
    @GetMapping("latest")
    BaseResponse latest(int number);
}
