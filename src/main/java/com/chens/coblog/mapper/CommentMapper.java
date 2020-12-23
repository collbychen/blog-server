package com.chens.coblog.mapper;

import com.chens.coblog.common.utils.TkMapper;
import com.chens.coblog.domain.Comment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 评论Mapper
 * @author chens
 * @version 1.0.0
 * @date 2020/8/10
 */
public interface CommentMapper extends TkMapper<Comment> {

    /**
     * 获取最新的评论
     * @param number 数量
     * @return list 评论数组
     */
    @Select("SELECT * FROM comment GROUP BY id ORDER BY create_time DESC LIMIT #{number}")
    List<Comment> getLatestComments(@Param("number") int number);

    /**
     * 获取一周内的评论数量
     * @return Integer 评论数量
     */
    @Select("SELECT count(*) FROM `comment` where DATE_SUB(CURDATE(), INTERVAL 1 WEEK) <= date(create_time);")
    Integer getLatest();
}