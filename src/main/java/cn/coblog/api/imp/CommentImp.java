package cn.coblog.api.imp;

import cn.coblog.api.admin.CommentApi;
import cn.coblog.common.base.BasePages;
import cn.coblog.common.base.BaseResponse;
import cn.coblog.common.constant.CodeEnum;
import cn.coblog.common.constant.ConfigConst;
import cn.coblog.common.utils.IPUtils;
import cn.coblog.domain.Comment;
import cn.coblog.domain.User;
import cn.coblog.domain.enums.CommentStatusEnum;
import cn.coblog.domain.enums.CommentTargetTypeEnum;
import cn.coblog.service.CommentService;
import cn.coblog.service.ConfigService;
import cn.coblog.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class CommentImp implements CommentApi {

    @Autowired
    private CommentService commentService;
    @Autowired
    private ConfigService configService;
    @Autowired
    private UserService userService;

    @Override
    public BaseResponse list(Integer current) {
        PageHelper.startPage(current, BasePages.PAGE_SIZE);
        List<Comment> list = commentService.getAll();
        PageInfo<Comment> pageInfo = new PageInfo<>(list);
        return BaseResponse.success("查询成功", BasePages.getPagesMap(pageInfo.getList(), pageInfo.getTotal()));
    }

    @Override
    public BaseResponse save(Comment comment, HttpServletRequest request) {
        if (comment.getTargetType().equals(CommentTargetTypeEnum.COMMENT.getValue())) {
            if (Objects.isNull(comment.getParentId())) {
                return BaseResponse.fail(CodeEnum.VALIDATION_ERROR.code(), "父级评论不能为空");
            }
            if (Objects.isNull(comment.getReplier())) {
                return BaseResponse.fail(CodeEnum.VALIDATION_ERROR.code(), "回复的人不能为空");
            }
        }
        User currentUser = userService.getCurrentUser();
        comment.setUserId(currentUser.getId());
        if (currentUser.getIsAdmin()) {
            comment.setStatus(CommentStatusEnum.PUBLISHED.getValue());
        } else {
            String value = configService.getConfigByName(ConfigConst.COMMENT_CHECK);
            comment.setStatus(StringUtil.isNotEmpty(value) ? CommentStatusEnum.CHECKING.getValue() : CommentStatusEnum.PUBLISHED.getValue());
        }
        comment.setUserAgent(request.getHeader("user-agent"));
        comment.setIp(IPUtils.getIpAddr(request));
        return commentService.save(comment) ? BaseResponse.success("添加成功"): BaseResponse.success("添加失败");
    }

    @Override
    public BaseResponse getComment(Long id) {
        Comment commentById = commentService.getCommentById(id);
        return BaseResponse.success("查询成功", commentById);
    }

    @Override
    public BaseResponse commentCount() {
        Map<String, Integer> data = new HashMap<>();
        int total = commentService.getTotalCount();
        data.put("totalComment", total);
        int latest =commentService.getOneWeekCount();
        data.put("latestComment", latest);
        return BaseResponse.success("获取成功", data);
    }

    @Override
    public BaseResponse latest(int number) {
        List<Comment> commentList = commentService.getLatest(number);
        return BaseResponse.success("查询成功", null);
    }

    @Override
    public BaseResponse getAll() {
        return BaseResponse.success("查询成功", commentService.getAll());
    }

}
