package com.chens.coblog.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 配置实体类
 * @author chens
 * @version 1.0.0
 * @date 2020/8/22
 */
@Data
@Table(name = "config")
public class Config implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 参数名
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 参数值
     */
    @Column(name = "`value`")
    private String value;

    /**
     * 参数类型 1：全局变量 2：系统配置
     */
    @Column(name = "`type`")
    private Byte type;

    /**
     * 描述
     */
    @Column(name = "description")
    private String description;

}