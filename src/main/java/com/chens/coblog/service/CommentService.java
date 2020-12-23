package com.chens.coblog.service;

import com.chens.coblog.domain.Comment;

import java.util.List;

/**
 * 类目接口
 * @author chens
 * @version 1.0.0
 * @date 2020/8/10
 */
public interface CommentService {

    Integer getCountByArticleId(Long id);

    List<Comment> getPageByArticleId(Long id);

    Boolean updateById(Comment comment);

    Comment getCommentById(Long id);

    List<Comment> getAll();

    Boolean save(Comment comment);

    List<Comment> getListByQuery(String keyword, Integer status, String sort);

    List<Comment> getLatest(Integer number);

    Integer getTotalCount();

    Integer getOneWeekCount();

}




