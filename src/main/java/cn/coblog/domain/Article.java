package cn.coblog.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 文章实体类
 * @author chens
 * @version 1.0.0
 * @date 2020/8/10
 */
@Data
@Table(name = "article")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 文章详情页链接
     */
    @Column(name = "url")
    private String url;

    /**
     * 文章标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 文章描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 文章的预览图片
     */
    @Column(name = "image_url")
    private String imageUrl;

    /**
     * 文章内容
     */
    @Column(name = "content")
    private String content;

    /**
     * Markdown格式的文章内容
     */
    @Column(name = "content_md")
    private String contentMd;

    /**
     * 分类ID
     */
    @Column(name = "category_id")
    private Long categoryId;

    /**
     * 发表时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 最后更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 文章是否置顶  0：否  1：是
     */
    @Column(name = "is_top")
    private Boolean isTop;

    /**
     * 是否开启评论 0：关闭 1：开启
     */
    @Column(name = "is_comment")
    private Boolean isComment;

    /**
     * 是否为原创文章 0：转载 1：原创
     */
    @Column(name = "is_original")
    private Boolean isOriginal;

    /**
     * 原文链接，转载文章才需填写
     */
    @Column(name = "source_url")
    private String sourceUrl;

    /**
     * 访问量
     */
    @Column(name = "visits")
    private Integer visits;

    /**
     * 状态 0：草稿 1：已发布 2：回收站
     */
    @Column(name = "`status`")
    private Integer status;

    /**
     * 文章类型 0：普通文章 1：自定义文章
     */
    @Column(name = "`type`")
    private Integer type;

    /**
     * 标签
     */
    @Transient
    private List<Tag> tags;

    /**
     * 类目名
     */
    @Transient
    private String cateName;

}