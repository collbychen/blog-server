package com.chens.coblog.api.front;

import com.chens.coblog.common.base.BaseResponse;
import com.chens.coblog.common.utils.IPUtils;
import com.chens.coblog.domain.Comment;
import com.chens.coblog.domain.enums.CommentStatusEnum;
import com.chens.coblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * 前台评论控制器
 * @author chens
 * @version 1.0.0
 * @date 2020/8/12
 */
@RestController
@RequestMapping("comments")
public class CommentsController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/{id}")
    public BaseResponse info(@PathVariable(value = "id") Long id) {
        HashMap<String, Object> result = new HashMap<>(2);
        //查询当前文章的第一页评论
        List<Comment> commentPage = commentService.getPageByArticleId(id);
        result.put("comments", commentPage);
        Integer commentCount = commentService.getCountByArticleId(id);
        result.put("count", commentCount);
        return BaseResponse.success("查询成功", result);
    }

    @PostMapping
    public BaseResponse commit(HttpServletRequest request,@RequestBody Comment comment){
//        if (comment.getTargetType().equals(CommentTargetTypeEnum.COMMENT.getValue())) {
//            if (Objects.isNull(comment.getParentId())) {
//                return BaseResponse.fail(CodeEnum.VALIDATION_ERROR.code(), "父级评论不能为空");
//            }
//            if (Objects.isNull(comment.getReplier())) {
//                return BaseResponse.fail(CodeEnum.VALIDATION_ERROR.code(), "回复的人不能为空");
//            }
//        }
        comment.setUserAgent(request.getHeader("user-agent"));
        comment.setIp(IPUtils.getIpAddr(request));
        comment.setStatus(CommentStatusEnum.PUBLISHED.getValue());
        return commentService.save(comment) ? BaseResponse.success("添加成功"): BaseResponse.success("添加失败");
    }
}
