package com.chens.coblog.api.front;

import com.chens.coblog.common.base.BasePages;
import com.chens.coblog.common.base.BaseResponse;
import com.chens.coblog.domain.Article;
import com.chens.coblog.service.ArticleService;
import com.chens.coblog.service.CategoryService;
import com.chens.coblog.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 前台文章控制器
 * @author chens
 * @version 1.0.0
 * @date 2020/8/12
 */
@RestController
@RequestMapping("articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;


    @GetMapping("list")
    public BaseResponse getList(Integer page, String categoryUrl, String tagUrl){
        if (page == null){ return BaseResponse.success("参数页数错误"); }
        HashMap<String, Object> result = new HashMap<>(2);
        result.put("list", null);
        result.put("total", 0);
        List<Article> articleList =null;
        if (tagUrl != null){
            Long tagId = tagService.getByUrl(tagUrl);
            if (tagId == -1L) { return BaseResponse.fail("参数错误",result); }
            result.put("total", tagService.getArticleCountById(tagId));
            PageHelper.startPage(page, BasePages.PAGE_SIZE);
            articleList = tagService.getByTagId(tagId);
        }else if (categoryUrl != null) {
            Integer categoryId = categoryService.getByUrl(categoryUrl);
            if (categoryId == -1) { return BaseResponse.fail("参数错误",result); }
            result.put("total", articleService.getCountByCategoryId(categoryId));
            PageHelper.startPage(page, BasePages.PAGE_SIZE);
            articleList = articleService.getByCategoryId(categoryId);
        }else {
            PageHelper.startPage(page, BasePages.PAGE_SIZE);
            articleList = articleService.getAll();
        }
        if (articleList == null){ return BaseResponse.success("暂时没有文章",result); }
        PageInfo<Article> pageInfo = new PageInfo<>(articleList);
        result.put("list", pageInfo.getList());
        if (0 == (Integer)result.get("total")){ result.put("total",pageInfo.getTotal()); }
        return BaseResponse.success("获取文章列表成功",result);
    }

    @GetMapping("archiveList")
    public BaseResponse getArchiveList(){
        HashMap<String, Object> result = new HashMap<>(2);
        Calendar c = Calendar.getInstance();
        List<Article> archiveList = articleService.getArchiveList();
        List<Map<String, Object>> dataList = new ArrayList<>();
        String year = "";
        List<Article> lists = null;
        for (Article a : archiveList){
            Map<String, Object> dataMap = new HashMap<>();
            boolean b = true;
            c.setTime(a.getCreateTime());
            if(!year.equals(c.get(Calendar.YEAR)+"")){
                year = c.get(Calendar.YEAR)+"";
                dataMap.put("year", year);
                lists = new ArrayList<>();
                b = false;
            }
            if ( lists != null){  lists.add(a); }
            if (!b){
                dataMap.put("list", lists);
                dataList.add(dataMap);
            }
        }
        result.put("list", dataList);
        result.put("count", archiveList.size());
        return BaseResponse.success("获取文章列表成功",result);
    }

    @GetMapping("hot")
    public BaseResponse getHot() {
        return BaseResponse.success("获取热门文章成功", articleService.getHot());
    }

    /**
     * 文章详情页
     */
    @GetMapping("/{id}")
    public BaseResponse info(@PathVariable(value = "id")Long id) {
        HashMap<String, Object> result = new HashMap<>(3);
        Article info = articleService.getById(id);
        if (null == info) { return BaseResponse.fail("找不到文章"); }
        articleService.updateForVisitsById(info.getId());
        result.put("info", info);
        //上一篇和下一篇
        Article previousArticle = articleService.getPreviousById(id);
        result.put("pre", previousArticle);
        Article nextArticle = articleService.getNextById(id);
        result.put("next", nextArticle);
        return BaseResponse.success("查询成功", result);
    }

    @GetMapping("category")
    public BaseResponse getCategoryAllInfo(){ return BaseResponse.success(categoryService.getAllInfo()); }

    @GetMapping("tag")
    public BaseResponse getTagAllInfo(){ return BaseResponse.success(tagService.getAllInfo()); }

}
