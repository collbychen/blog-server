package cn.coblog.mapper;

import cn.coblog.common.utils.TkMapper;
import cn.coblog.domain.ArticleTag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文章标签Mapper
 * @author chens
 * @version 1.0.0
 * @date 2020/8/10
 */
public interface ArticleTagMapper extends TkMapper<ArticleTag> {

    List<ArticleTag> getTagBatch(@Param("tagId") Long tagId);

    List<ArticleTag> getArticleBatch(@Param("articleId") Long articleId);
}