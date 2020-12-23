package com.chens.coblog.api;


import com.chens.coblog.common.base.BaseResponse;
import com.chens.coblog.common.constant.CodeEnum;
import com.chens.coblog.common.exception.BlogException;
import com.chens.coblog.domain.Article;
import com.chens.coblog.domain.Spider;
import com.chens.coblog.service.SpiderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 爬虫规则管理
 * @author chens
 * @version 1.0.0
 * @date 2020/9/11
 */
@RestController
@RequestMapping("api/spider")
public class ApiSpiderController {

    @Autowired
    private SpiderService spiderService;

    @GetMapping("all")
    public BaseResponse list() {
        return BaseResponse.success("查询成功", spiderService.getAll());
    }

    @PostMapping
    public BaseResponse save(@RequestBody Spider spider) {
//        ValidatorUtils.validate(spider);
        return spiderService.save(spider) ? BaseResponse.success("保存成功"): BaseResponse.fail("保存失败");
    }

    @PutMapping
    public BaseResponse update(@RequestBody Spider spider) {
//        ValidatorUtils.validate(spider);
        return spiderService.updateById(spider) ? BaseResponse.success("修改成功"): BaseResponse.fail("修改失败");
    }

    @GetMapping
    public BaseResponse info(Long id) {
        return BaseResponse.success("查询成功", spiderService.getById(id));
    }

    @DeleteMapping
    public BaseResponse remove(Long id) {
        return spiderService.removeById(id) ? BaseResponse.success("删除成功") : BaseResponse.fail("删除失败");
    }

    @PostMapping("spiderArticle")
    public BaseResponse spiderArticle(Long id, String url) {
        Spider spider = spiderService.getById(id);
        if (StringUtils.isBlank(url)) {
            return BaseResponse.fail("文章链接不能为空");
        }
        if (spider == null) {
            return BaseResponse.fail("规则不能为空");
        }
        Article article;
        try {
            article = spiderService.spiderArticle(spider, url);
        } catch (Exception e) {
            throw new BlogException(CodeEnum.SPIDER_ERROR.code(), "文章抓取失败", e);
        }
        return article != null ? BaseResponse.success("抓取成功", article) : BaseResponse.fail("抓取失败");
    }
}

