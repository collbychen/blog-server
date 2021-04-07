package cn.coblog.api.imp;

import cn.coblog.api.front.CommentsApi;
import cn.coblog.common.base.BaseResponse;
import cn.coblog.common.utils.IPUtils;
import cn.coblog.domain.Comment;
import cn.coblog.domain.enums.CommentStatusEnum;
import cn.coblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
public class CommentsImp implements CommentsApi {

    @Autowired
    private CommentService commentService;

    @Override
    public BaseResponse info(Long id) {
        HashMap<String, Object> result = new HashMap<>(2);
        //查询当前文章的第一页评论
        List<Comment> commentPage = commentService.getPageByArticleId(id);
        result.put("comments", commentPage);
        Integer commentCount = commentService.getCountByArticleId(id);
        result.put("count", commentCount);
        return BaseResponse.success("查询成功", result);
    }
    @Override
    public BaseResponse commit(HttpServletRequest request, Comment comment) {
        comment.setUserAgent(request.getHeader("user-agent"));
        comment.setIp(IPUtils.getIpAddr(request));
        comment.setStatus(CommentStatusEnum.PUBLISHED.getValue());
        return commentService.save(comment) ? BaseResponse.success("添加成功"): BaseResponse.success("添加失败");
    }

}
