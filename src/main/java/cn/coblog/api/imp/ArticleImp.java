package cn.coblog.api.imp;

import cn.coblog.api.admin.ArticleApi;
import cn.coblog.common.base.BasePages;
import cn.coblog.common.base.BaseResponse;
import cn.coblog.common.constant.Const;
import cn.coblog.common.utils.ElasticSearchUtil;
import cn.coblog.domain.Article;
import cn.coblog.domain.Tag;
import cn.coblog.service.ArticleService;
import cn.coblog.service.ArticleTagService;
import cn.coblog.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ArticleImp implements ArticleApi {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private TagService tagService;
    @Autowired
    private ArticleTagService articleTagService;
    @Autowired
    private RestHighLevelClient client;

    @Override
    public BaseResponse list(Integer current, Integer size, String title, Integer status, Integer type, Integer categoryId, String sort) {
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

    @Override
    public BaseResponse save(Article article) {
        if (article.getId() != null){
            return modify(article);
        }
        if (StringUtils.isBlank(article.getUrl())) {
            article.setUrl(article.getTitle());
        }
        article.setUpdateTime(new Date());
        return modify(article);
    }

    @Override
    public BaseResponse modify(Article article) {
        article.setCreateTime(new Date());
        boolean res = articleService.save(article);
        if (!res) {
            return BaseResponse.fail("创建失败");
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
        boolean es_result = ElasticSearchUtil.index(client, Const.BLOG, article.getId(), article);
        if ( !es_result ){
            return BaseResponse.fail("文章已成功保存，但ES保存失败");
        }
        return BaseResponse.success("保存成功", article);
    }

    @Override
    public BaseResponse getArticle(Long id) {
        return BaseResponse.success("查询成功", articleService.getById(id));
    }

    @Override
    public BaseResponse remove(Long[] ids) {
        for (Long id : ids){
            articleTagService.deleteBatch(id);
        }
        return articleService.removeByIds(Arrays.asList(ids)) ? BaseResponse.success("删除成功") : BaseResponse.fail("删除失败");
    }

    @Override
    public BaseResponse latest(int number) {
        return BaseResponse.success("查询成功", articleService.getLatest(number));
    }

}
