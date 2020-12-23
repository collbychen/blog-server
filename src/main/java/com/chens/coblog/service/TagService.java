package com.chens.coblog.service;

import com.chens.coblog.domain.Article;
import com.chens.coblog.domain.Tag;

import java.util.List;

/**
 * 标签接口
 * @author chens
 * @version 1.0.0
 * @date 2020/8/10
 */
public interface TagService {

    boolean save(Tag tag);

    boolean updateById(Tag tag);

    Tag getById(Long id);

    boolean removeById(Long id);

    List<Tag> getAll();

    List<Article> getByTagId(Long id);

    List<Tag> getByIds(List<Long> tagIds);

    List<Tag> getAllInfo();

    Integer getArticleCountById(Long tagId);

    Long getByUrl(String tagUrl);
}




