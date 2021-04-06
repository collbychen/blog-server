package cn.coblog.service;

import java.util.List;

/**
 * 文章标签中间接口
 * @author chens
 * @version 1.0.0
 * @date 2020/8/10
 */
public interface ArticleTagService {

    List<Long> getArticleBatch(Long articleId);

    List<Long> getTagBatch(Long tagId);

    Boolean saveBatch(Long articleId, List<Long> tagIds);

    Boolean updateBatch(Long articleId, List<Long> tagIds);

    Boolean deleteBatch(Long articleId);

}




