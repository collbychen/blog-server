package cn.coblog.service.imp;

import cn.coblog.domain.Article;
import cn.coblog.domain.Tag;
import cn.coblog.mapper.TagMapper;
import cn.coblog.service.ArticleService;
import cn.coblog.service.ArticleTagService;
import cn.coblog.service.TagService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * 标签接口实现
 * @author chens
 * @version 1.0.0
 * @date 2020/8/10
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TagServiceImpl implements TagService {

    @Resource
    private TagMapper tagMapper;
    @Resource
    private ArticleService articleService;
    @Resource
    private ArticleTagService articleTagService;

    @Override
    public boolean save(Tag tag) {
        return tagMapper.insert(tag) > 0;
    }

    @Override
    public boolean updateById(Tag tag) {
        return tagMapper.updateByPrimaryKeySelective(tag) > 0;
    }

    @Override
    public Tag getById(Long id) {
        return tagMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean removeById(Long id) {
        return tagMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public List<Tag> getAll() {
        return tagMapper.selectAll();
    }

    @Override
    public List<Article> getByTagId(Long id) {
        return tagMapper.getByTagId(id);
    }

    @Override
    public List<Tag> getByIds(List<Long> tagIds) {
        if (tagIds.size() == 0){ return null; }
        return tagMapper.selectByIds(StringUtils.strip(tagIds.toString(),"[]"));
    }

    @Override
    public List<Tag> getAllInfo() {
        List<Tag> tags = tagMapper.selectAll();
        for (Tag tag: tags){
            tag.setCount(getArticleCountById(tag.getId()));
        }
        return tags;
    }

    @Override
    public Integer getArticleCountById(Long tagId) {
        List<Article> articleList = getByTagId(tagId);
        if (articleList==null){
            return 0;
        }else{
            return articleList.size();
        }
    }

    @Override
    public Long getByUrl(String tagUrl) {
        Example example = new Example(Tag.class);
        example.createCriteria().andEqualTo("url",tagUrl);
        Tag tag = tagMapper.selectOneByExample(example);
        if (tag == null) { return -1L; }
        return tag.getId();
    }
}



