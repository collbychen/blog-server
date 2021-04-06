package cn.coblog.api.front;

import cn.coblog.common.base.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 前台文章api
 * @author chens
 * @version 1.0.0
 * @date 2020/8/12
 */
@RequestMapping("articles")
public interface ArticlesApi {

    @GetMapping("list")
    BaseResponse getList(Integer page, String categoryUrl, String tagUrl);

    @GetMapping("archiveList")
    BaseResponse getArchiveList();

    @GetMapping("hot")
    BaseResponse getHot();

    /**
     * 文章详情页
     */
    @GetMapping("{id}")
    BaseResponse info(@PathVariable(value = "id")Long id);

    @GetMapping("category")
    BaseResponse getCategoryAllInfo();

    @GetMapping("tag")
    BaseResponse getTagAllInfo();

    /**
     * 搜索页分页
     * @param wd  关键字
     */
    @GetMapping("search")
    BaseResponse search(String wd);

}
