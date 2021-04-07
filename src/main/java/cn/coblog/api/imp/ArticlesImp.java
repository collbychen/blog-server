package cn.coblog.api.imp;

import cn.coblog.api.front.ArticlesApi;
import cn.coblog.common.base.BasePages;
import cn.coblog.common.base.BaseResponse;
import cn.coblog.domain.Article;
import cn.coblog.service.ArticleService;
import cn.coblog.service.CategoryService;
import cn.coblog.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class ArticlesImp implements ArticlesApi {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private TagService tagService;
    @Autowired
    private CategoryService categoryService;

    @Override
    public BaseResponse info(Long id) {
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

    @Override
    public BaseResponse getList(Integer page, String categoryUrl, String tagUrl){
        String orderBy = "create_time desc";
        if (page == null){ return BaseResponse.success("参数页数错误"); }
        HashMap<String, Object> result = new HashMap<>(2);
        result.put("list", null);
        result.put("total", 0);
        List<Article> articleList =null;
        if (tagUrl != null){
            Long tagId = tagService.getByUrl(tagUrl);
            if (tagId == -1L) { return BaseResponse.fail("参数错误",result); }
            result.put("total", tagService.getArticleCountById(tagId));
            PageHelper.startPage(page, BasePages.PAGE_SIZE, orderBy);
            articleList = tagService.getByTagId(tagId);
        }else if (categoryUrl != null) {
            Integer categoryId = categoryService.getByUrl(categoryUrl);
            if (categoryId == -1) { return BaseResponse.fail("参数错误",result); }
            result.put("total", articleService.getCountByCategoryId(categoryId));
            PageHelper.startPage(page, BasePages.PAGE_SIZE, orderBy);
            articleList = articleService.getByCategoryId(categoryId);
        }else {
            PageHelper.startPage(page, BasePages.PAGE_SIZE, orderBy);
            articleList = articleService.getAll();
        }
        if (articleList == null){ return BaseResponse.success("暂时没有文章",result); }
        PageInfo<Article> pageInfo = new PageInfo<>(articleList);
        result.put("list", pageInfo.getList());
        if (0 == (Integer)result.get("total")){ result.put("total",pageInfo.getTotal()); }
        return BaseResponse.success("获取文章列表成功",result);
    }

    @Override
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

    @Override
    public BaseResponse getHot() {
        return BaseResponse.success("获取热门文章成功", articleService.getHot());
    }

    @Override
    public BaseResponse getCategoryAllInfo(){ return BaseResponse.success(categoryService.getAllInfo()); }

    @Override
    public BaseResponse getTagAllInfo(){ return BaseResponse.success(tagService.getAllInfo()); }

    @Override
    public BaseResponse search(String wd) { return BaseResponse.success(articleService.searchByKeyword(wd)); }
}
