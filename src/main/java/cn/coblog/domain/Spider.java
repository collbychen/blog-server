package cn.coblog.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 爬虫实体类
 * @author chens
 * @version 1.0.0
 * @date 2020/8/22
 */
@Data
@Table(name = "spider")
public class Spider implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 爬虫标识名
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 标题爬取规则
     */
    @Column(name = "title_rule")
    private String titleRule;

    /**
     * 文章内容爬取规则
     */
    @Column(name = "content_rule")
    private String contentRule;

}