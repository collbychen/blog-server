package cn.coblog.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "menu")
public class Menu implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 菜单名
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 菜单链接
     */
    @Column(name = "url")
    private String url;

    /**
     * 是否在新窗口打开菜单  0：否，1：是
     */
    @Column(name = "is_blank")
    private Boolean isBlank;

    /**
     * Font Awesome图标
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 菜单排序，越小的越靠前
     */
    @Column(name = "sort")
    private Integer sort;

    private static final long serialVersionUID = 1L;
}