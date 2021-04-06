package cn.coblog.service;

import cn.coblog.domain.Article;
import cn.coblog.domain.Category;

import java.util.List;

/**
 * 类目接口
 * @author chens
 * @version 1.0.0
 * @date 2020/8/10
 */
public interface CategoryService {

    Category getById(Long id);

    List<Category> getAll();

    List<Article> getArticleById(Integer id);

    Boolean save(Category category);

    Boolean updateById(Category category);

    Boolean removeById(Long id);

    List<Category> getAllInfo();

    Integer getByUrl(String categoryUrl);
}




