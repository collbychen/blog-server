package cn.coblog.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 链接实体类
 * @author chens
 * @version 1.0.0
 * @date 2020/8/22
 */
@Data
@Table(name = "link")
public class Link implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 链接名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 链接地址
     */
    @Column(name = "url")
    private String url;

    /**
     * 链接类型 1：友情链接 2：个人链接
     */
    @Column(name = "`type`")
    private Byte type;

}