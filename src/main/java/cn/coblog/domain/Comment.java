package cn.coblog.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 评价实体类
 * @author chens
 * @version 1.0.0
 * @date 2020/8/22
 */
@Data
@Table(name = "`comment`")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 评论者使用的浏览器
     */
    @Column(name = "user_agent")
    private String userAgent;

    /**
     * 评论内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 评论者的ip地址
     */
    @Column(name = "ip")
    private String ip;

    /**
     * 父级评论
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 评论者Id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 评论者名称
     */
    @Column(name = "username")
    private String username;

    /**
     * 评论者头像
     */
    @Column(name = "avatar")
    private String avatar;

    /**
     * 回复的人Id
     */
    @Column(name = "reply_id")
    private Long replyId;

    /**
     * 回复的人名称
     */
    @Column(name = "replier")
    private String replier;

    /**
     * 目标类型 1：文章 2：评论
     */
    @Column(name = "target_type")
    private Integer targetType;

    /**
     * 评论的文章
     */
    @Column(name = "article_id")
    private Integer articleId;

    /**
     * 评论时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 评论状态 0：待审核 1：已发布 2：已删除
     */
    @Column(name = "`status`")
    private Integer status;

    /**
     * 二级评论
     */
    @Transient
    private List<Comment> children;

}