package com.chens.coblog.service.imp;

import com.chens.coblog.common.base.BasePages;
import com.chens.coblog.common.constant.FieldName;
import com.chens.coblog.domain.Article;
import com.chens.coblog.domain.Category;
import com.chens.coblog.domain.enums.ArticleStatusEnum;
import com.chens.coblog.mapper.ArticleMapper;
import com.chens.coblog.service.ArticleService;
import com.chens.coblog.service.ArticleTagService;
import com.chens.coblog.service.CategoryService;
import com.chens.coblog.service.TagService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 文章接口实现
 * @author chens
 * @version 1.0.0
 * @date 2020/8/22
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private ArticleTagService articleTagService;
    @Resource
    private TagService tagService;
    @Resource
    private CategoryService categoryService;

    @Override
    public Article getById(Long id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        if (article == null) { return null; }
        List<Long> tagIds = articleTagService.getArticleBatch(article.getId());
        if (tagIds != null){
            article.setTags(tagService.getByIds(tagIds));
        }
        String cateName = categoryService.getById(article.getCategoryId()).getCateName();
        article.setCateName(cateName);
        return article;
    }
    @Override
    public List<Article> getLatest(int number) {
        return articleMapper.getLatest(number);
    }

    @Override
    public List<Article> getByIds(List<Long> ids) {
        if (ids.size() == 0) {return  null;}
        List<Article> articleList = new ArrayList<>();
        for (Long id: ids){
            articleList.add(getById(id));
        }
        return articleList;
    }

    @Override
    public Article getByUrl(String url) {
        Example example = new Example(Article.class);
        example.createCriteria().andEqualTo(FieldName.URL, url);
        return articleMapper.selectOneByExample(example);
    }

    @Override
    public Article getPreviousById(Long id) { return articleMapper.getPreviousById(id); }

    @Override
    public Article getNextById(Long id) { return articleMapper.getNextById(id); }

    @Override
    public List<Article> getByCondition(String title, Integer status, Integer type, Integer categoryId, String sort) {
        Condition condition = new Condition(Article.class);
        Example.Criteria criteria = condition.createCriteria();
        if (status != null){ criteria.andEqualTo(FieldName.STATUS, status);
        } else { criteria.andNotEqualTo(FieldName.STATUS, ArticleStatusEnum.RECYCLE.getValue()); }
        if (categoryId != null){ criteria.andEqualTo(FieldName.CATEGORY_ID, categoryId); }
        if (type != null){ criteria.andEqualTo(FieldName.TYPE, type); }
        if (title != null){ criteria.andLike(FieldName.TITLE, title); }
        if (!"".equals(sort)){ condition = BasePages.addSort(sort, condition); }
        return articleMapper.selectByCondition(condition);
    }

    @Override
    public Boolean save(Article article) { return articleMapper.insert(article) > 0; }

    @Override
    public Boolean update(Article article) { return articleMapper.updateByPrimaryKeySelective(article) >0; }

    @Override
    public void updateForVisitsById(Long id) {
        Article article = getById(id);
        article.setVisits(article.getVisits()+1);
        update(article);
    }

    @Override
    public Boolean removeByIds(List<Long> asList) { return articleMapper.deleteByIds(StringUtils.strip(asList.toString(),"[]")) > 0; }

    @Override
    public List<Article> getArchiveList() {
        return articleMapper.getArchiveList();
    }

    @Override
    public List<Article> getList() {
        List<Article> articleList = articleMapper.getList();
        for (Article article : articleList) {
            List<Long> tagIds = articleTagService.getArticleBatch(article.getId());
            article.setTags(tagService.getByIds(tagIds));
        }
        return articleList;
    }

    @Override
    public List<Article> getByTagId(Long id) {
        List<Long> articleIds = articleTagService.getTagBatch(id);
        if (articleIds.size() == 0){ return null; }
        return articleMapper.selectByIds(StringUtils.strip(articleIds.toString(),"[]"));
    }

    @Override
    public List<Article> getAll() {
        Condition condition = new Condition(Article.class);
        condition.orderBy(FieldName.CREATE_TIME).desc();
        return articleMapper.selectByCondition(condition);
    }

    @Override
    public Integer getCountByCategoryId(Integer id) {
        return articleMapper.getCountByCategoryId(id);
    }

    @Override
    public List<Article> getByCategoryId(Integer categoryId) {
        Example example = new Example(Article.class);
        example.createCriteria().andEqualTo(FieldName.CATEGORY_ID, categoryId);
        List<Article> articleList = articleMapper.selectByExample(example);
        for (Article article : articleList) {
            Category category = categoryService.getById(article.getCategoryId());
            article.setCateName(category.getCateName());
        }
        return articleList;
    }

    @Override
    public List<Article> getHot() { return articleMapper.getHot(); }

}





