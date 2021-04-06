package cn.coblog.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 日志实体类
 * @author chens
 * @version 1.0.0
 * @date 2020/8/22
 */
@Data
@Table(name = "log")
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * IP地址
     */
    @Column(name = "ip")
    private String ip;

    /**
     * 所在城市
     */
    @Column(name = "city")
    private String city;

    /**
     * 访问链接
     */
    @Column(name = "url")
    private String url;

    /**
     * 来源
     */
    @Column(name = "referer")
    private String referer;

    /**
     * 浏览器类型
     */
    @Column(name = "user_agent")
    private String userAgent;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 接口响应时长（单位：毫秒）
     */
    @Column(name = "duration")
    private Integer duration;

    /**
     * 访问类型
     */
    @Column(name = "`type`")
    private String type;

    /**
     * 访问参数
     */
    @Column(name = "params")
    private String params;

    /**
     * 返回结果
     */
    @Column(name = "`result`")
    private String result;

    /**
     * 执行的方法
     */
    @Column(name = "`method`")
    private String method;

    /**
     * 请求是否正常 1：正常 0：异常
     */
    @Column(name = "is_normal")
    private Boolean isNormal;

    /**
     * 浏览器
     */
    @Column(name = "browser")
    private String browser;

    /**
     * 操作系统
     */
    @Column(name = "operating_system")
    private String operatingSystem;

}