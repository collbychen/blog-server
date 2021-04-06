package cn.coblog.service;

import cn.coblog.domain.Article;

import java.util.List;

/**
 * 文章接口
 * @author chens
 * @version 1.0.0
 * @date 2020/8/10
 */
public interface ArticleService {

    Article getById(Long id);

    List<Article> getLatest(int number);

    List<Article> getByIds(List<Long> articleList);

    Article getByUrl(String url);

    Article getPreviousById(Long id);

    Article getNextById(Long id);

    List<Article> getByCondition(String title, Integer status, Integer type, Integer categoryId, String sort);

    Boolean save(Article article);

    Boolean update(Article article);

    void updateForVisitsById(Long id);

    Boolean removeByIds(List<Long> ids);

    List<Article> getHot();

    List<Article> getArchiveList();

    List<Article> getList();

    List<Article> getByTagId(Long id);

    List<Article> getAll();

    Integer getCountByCategoryId(Integer id);

    List<Article> getByCategoryId(Integer categoryId);

    List<Article> searchByKeyword(String wd);
}





