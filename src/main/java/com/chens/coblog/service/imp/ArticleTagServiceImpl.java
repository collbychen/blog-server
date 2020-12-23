package com.chens.coblog.service.imp;

import com.chens.coblog.domain.ArticleTag;
import com.chens.coblog.mapper.ArticleTagMapper;
import com.chens.coblog.service.ArticleTagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文章标签中间实现
 * @author chens
 * @version 1.0.0
 * @date 2020/8/10
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ArticleTagServiceImpl implements ArticleTagService {

    @Resource
    private ArticleTagMapper articleTagMapper;

    @Override
    public List<Long> getArticleBatch(Long articleId) {
        List<ArticleTag> articleTags = articleTagMapper.getArticleBatch(articleId);
        if (articleTags == null){ return null;}
        return articleTags.stream().map(ArticleTag::getTagId).collect(Collectors.toList());
    }

    @Override
    public List<Long> getTagBatch(Long tagId) {
        List<ArticleTag> articleTags = articleTagMapper.getTagBatch(tagId);
        return articleTags.stream().map(ArticleTag::getArticleId).collect(Collectors.toList());
    }

    @Override
    public Boolean saveBatch(Long articleId, List<Long> tagIds) {
        for(Long tagId : tagIds){
            articleTagMapper.insert(new ArticleTag(null,articleId,tagId));
        }
        return true;
    }

    @Override
    public Boolean updateBatch(Long articleId, List<Long> tagIds) {
        boolean res = deleteBatch(articleId);
        return saveBatch(articleId, tagIds);
    }

    @Override
    public Boolean deleteBatch(Long articleId) {
        Example example = new Example(ArticleTag.class);
        example.createCriteria().andEqualTo("articleId", articleId);
        return articleTagMapper.deleteByExample(example) > 0;
    }

}




