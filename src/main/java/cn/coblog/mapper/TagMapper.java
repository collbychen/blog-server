package cn.coblog.mapper;

import cn.coblog.common.utils.TkMapper;
import cn.coblog.domain.Article;
import cn.coblog.domain.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 标签Mapper
 * @author chens
 * @version 1.0.0
 * @date 2020/8/10
 */
public interface TagMapper extends TkMapper<Tag> {

    List<Article> getByTagId(@Param("id")Long id);
}