package cn.coblog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 文章标签关系类
 * @author chens
 * @version 1.0.0
 * @date 2020/8/10
 */
@Data
@AllArgsConstructor
@Table(name = "article_tag")
public class ArticleTag implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 文章ID
     */
    @Column(name = "article_id")
    private Long articleId;

    /**
     * 标签ID
     */
    @Column(name = "tag_id")
    private Long tagId;

}