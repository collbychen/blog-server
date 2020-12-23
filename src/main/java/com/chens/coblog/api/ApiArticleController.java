package com.chens.coblog.api;

import com.chens.coblog.common.base.BasePages;
import com.chens.coblog.common.base.BaseResponse;
import com.chens.coblog.domain.Article;
import com.chens.coblog.domain.Tag;
import com.chens.coblog.service.ArticleService;
import com.chens.coblog.service.ArticleTagService;
import com.chens.coblog.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文章管理
 * @author chens
 * @version 1.0.0
 * @date 2020/8/29 7:30
 */
@RestController
@RequestMapping("api/article")
public class ApiArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleTagService articleTagService;
    @Autowired
    private TagService tagService;

    @GetMapping("list")
    public BaseResponse list(Integer current,Integer size, String title, Integer status, Integer type, Integer categoryId, String sort) {
        PageHelper.startPage(current, size);
        List<Article> list = articleService.getByCondition(title, status, type, categoryId, sort);
        PageInfo<Article> pageInfo = new PageInfo<>(list);
        List<Article> articleList = pageInfo.getList();
        for (Article article : articleList) {
            List<Long> tagIds = articleTagService.getArticleBatch(article.getId());
            article.setTags(tagService.getByIds(tagIds));
        }
        return BaseResponse.success("查询成功", BasePages.getPagesMap(articleList, pageInfo.getTotal()));
    }

    @PostMapping
    public BaseResponse save(@RequestBody Article article) {
        if (article.getId() != null){
            return modify(article);
        }
        if (StringUtils.isBlank(article.getUrl())) {
            article.setUrl(article.getTitle());
        }
        article.setCreateTime(new Date());
        article.setUpdateTime(article.getCreateTime());
        boolean res = articleService.save(article);
        if (!res) {
            return BaseResponse.fail("保存失败");
        }
        List<Tag> tags = article.getTags();
        if (!CollectionUtils.isEmpty(tags)) {
            Long articleId = article.getId();
            List<Long> tagIds = tags.stream().map(Tag::getId).collect(Collectors.toList());
            res = articleTagService.saveBatch(articleId, tagIds);
            if (!res) {
                return BaseResponse.fail("文章已成功保存，但关联标签保存失败");
            }
        }
        return BaseResponse.success("保存成功", article);
    }

    @PutMapping
    public BaseResponse modify(@RequestBody Article article) {
        article.setUpdateTime(new Date());
        Boolean res = articleService.update(article);
        if (!res) {
            return BaseResponse.fail("保存失败");
        }
        if (article.getTags() != null){
            List<Long> tags = article.getTags().stream().map(Tag::getId).collect(Collectors.toList());
            res = articleTagService.updateBatch(article.getId(), tags);
            if (!res) {
                return BaseResponse.fail("关联标签保存成功失败,文章id:"+ article.getId());
            }
        }
        return BaseResponse.success("保存成功", article);
    }

    @GetMapping
    public BaseResponse info(Long id) {
        return BaseResponse.success("查询成功", articleService.getById(id));
    }

    @DeleteMapping
    public BaseResponse remove(Long[] ids) {
        for (Long id : ids){
            articleTagService.deleteBatch(id);
        }
        return articleService.removeByIds(Arrays.asList(ids)) ? BaseResponse.success("删除成功") : BaseResponse.fail("删除失败");
    }

    /**
     * 查询最新的文章
     */
    @GetMapping("latest")
    public BaseResponse latest(int number) {
        return BaseResponse.success("查询成功", articleService.getLatest(number));
    }

}
