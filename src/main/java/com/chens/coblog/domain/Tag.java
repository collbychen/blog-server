package com.chens.coblog.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 标签实体类
 * @author chens
 * @version 1.0.0
 * @date 2020/8/22
 */
@Data
@Table(name = "tag")
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 标签名
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 标签链接
     */
    @Column(name = "url")
    private String url;

    /**
     * 标签颜色
     */
    @Column(name="color")
    private String color;

    /**
     * 文章数量
     */
    @Transient
    private Integer count;

}