package cn.coblog.mapper;


import cn.coblog.common.utils.TkMapper;
import cn.coblog.domain.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文章Mapper
 * @author chens
 * @version 1.0.0
 * @date 2020/8/10
 */
public interface ArticleMapper extends TkMapper<Article> {

    /**
     * 获取最新的文章
     * @param number 数量
     * @return list 文章数组
     */
    List<Article> getLatest(@Param("number") int number);

    /**
     * 获取上一篇文章
     * @param id id
     * @return article 文章
     */
    Article getPreviousById(@Param("id")Long id);

    /**
     * 获取下一篇文章
     * @param id id
     * @return article 文章
     */
    Article getNextById(@Param("id")Long id);

    List<Article> getHot();

    List<Article> getArchiveList();

    List<Article> getList();

    Integer getCountByCategoryId(@Param("categoryId")Integer id);

    List<Article> selectByKeyword(@Param("wd")String wd);
}