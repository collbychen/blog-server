package cn.coblog.service.imp;

import cn.coblog.common.base.BasePages;
import cn.coblog.common.constant.FieldName;
import cn.coblog.domain.Comment;
import cn.coblog.domain.enums.CommentStatusEnum;
import cn.coblog.domain.enums.CommentTargetTypeEnum;
import cn.coblog.mapper.CommentMapper;
import cn.coblog.service.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * 评论接口实现
 * @author chens
 * @version 1.0.0
 * @date 2020/8/10
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Override
    public Integer getCountByArticleId(Long id) {
        Example example = new Example(Comment.class);
        example.createCriteria()
                .andEqualTo(FieldName.ARTICLE_ID, id)
                .andEqualTo(FieldName.STATUS, CommentStatusEnum.PUBLISHED.getValue());
        return commentMapper.selectCountByExample(example);
    }

    @Override
    public List<Comment> getPageByArticleId(Long id) {
        Example example = new Example(Comment.class);
        example.createCriteria()
                .andEqualTo(FieldName.ARTICLE_ID, id)
                .andEqualTo(FieldName.TARGET_TYPE, CommentTargetTypeEnum.ARTICLE.getValue())
                .andEqualTo(FieldName.STATUS, CommentStatusEnum.PUBLISHED.getValue());
        List<Comment> comments = commentMapper.selectByExample(example);
        for (Comment c: comments) {
            Condition condition = new Condition(Comment.class);
            Example.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo(FieldName.PARENT_ID, c.getId())
                    .andEqualTo(FieldName.TARGET_TYPE, CommentTargetTypeEnum.COMMENT.getValue())
                    .andEqualTo(FieldName.ARTICLE_ID, id)
                    .andEqualTo(FieldName.STATUS, CommentStatusEnum.PUBLISHED.getValue());
            condition.orderBy(FieldName.CREATE_TIME).asc();
            List<Comment> result = commentMapper.selectByCondition(condition);
            if (result.size() > 0) {
                c.setChildren(result);
            }
        }
        return comments;
    }

    @Override
    public Boolean updateById(Comment comment) {
        return false;
    }

    @Override
    public Comment getCommentById(Long id) {
        return commentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Comment> getAll() {
        return commentMapper.selectAll();
    }

    @Override
    public Boolean save(Comment comment) {
        return commentMapper.insert(comment) > 0;
    }

    @Override
    public List<Comment> getListByQuery(String keyword, Integer status, String sort) {
        Condition condition = new Condition(Comment.class);
        Example.Criteria criteria = condition.createCriteria();
        if (status != null){ criteria.andEqualTo(FieldName.STATUS, status); }
        if (keyword != null){ criteria.andLike(FieldName.CONTENT, keyword); }
        condition = BasePages.addSort(sort, condition);
        return commentMapper.selectByCondition(condition);
    }

    @Override
    public List<Comment> getLatest(Integer number) {
        return commentMapper.getLatestComments(number);
    }

    @Override
    public Integer getTotalCount() {
        return commentMapper.selectCount(new Comment());
    }

    @Override
    public Integer getOneWeekCount() {
        return commentMapper.getLatest();
    }


}




