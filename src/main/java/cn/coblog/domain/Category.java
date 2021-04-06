package cn.coblog.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 类目实体类
 * @author chens
 * @version 1.0.0
 * @date 2020/8/22
 */
@Data
@Table(name = "category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "cateName")
    private String cateName;

    @Column(name = "url")
    private String url;

    @Transient
    private Integer count;
}