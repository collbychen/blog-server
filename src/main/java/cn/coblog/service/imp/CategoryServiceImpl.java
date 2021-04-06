package cn.coblog.service.imp;

import cn.coblog.domain.Article;
import cn.coblog.domain.Category;
import cn.coblog.domain.enums.ArticleStatusEnum;
import cn.coblog.mapper.CategoryMapper;
import cn.coblog.service.ArticleService;
import cn.coblog.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * 类目接口实现
 * @author chens
 * @version 1.0.0
 * @date 2020/8/10
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private ArticleService articleService;

    @Override
    public Category getById(Long id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Category> getAll() { return categoryMapper.selectAll(); }

    @Override
    public List<Article> getArticleById(Integer id) {
        if (id == null){ return articleService.getAll(); }
        return articleService.getByCondition(null, ArticleStatusEnum.PUBLISHED.getValue(),null, id,null);
    }

    @Override
    public Boolean save(Category category) { return categoryMapper.insert(category) > 0; }

    @Override
    public Boolean updateById(Category category) {
        return categoryMapper.updateByPrimaryKeySelective(category) > 0;
    }

    @Override
    public Boolean removeById(Long id) {
        return categoryMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public List<Category> getAllInfo() {
        List<Category> categoryList = categoryMapper.selectAll();
        for (Category category: categoryList){
            category.setCount(articleService.getCountByCategoryId(category.getId()));
        }
        return categoryMapper.selectAll();
    }

    @Override
    public Integer getByUrl(String categoryUrl) {
        Example example = new Example(Category.class);
        example.createCriteria().andEqualTo("url",categoryUrl);
        Category category = categoryMapper.selectOneByExample(example);
        if (category == null) { return -1; }
        return category.getId();
    }

}




