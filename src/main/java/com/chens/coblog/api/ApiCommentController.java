package com.chens.coblog.api;

import com.chens.coblog.common.base.BasePages;
import com.chens.coblog.common.base.BaseResponse;
import com.chens.coblog.common.constant.CodeEnum;
import com.chens.coblog.common.constant.ConfigConst;
import com.chens.coblog.common.utils.IPUtils;
import com.chens.coblog.domain.Comment;
import com.chens.coblog.domain.User;
import com.chens.coblog.domain.enums.CommentStatusEnum;
import com.chens.coblog.domain.enums.CommentTargetTypeEnum;
import com.chens.coblog.service.CommentService;
import com.chens.coblog.service.ConfigService;
import com.chens.coblog.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 评论管理
 * @author chens
 * @version 1.0.0
 * @date 2020/8/29 7:30
 */
@RestController
@RequestMapping("api/comment")
public class ApiCommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private ConfigService configService;
    @Autowired
    private UserService userService;

    @GetMapping("list")
    public BaseResponse list(Integer current){
        PageHelper.startPage(current, BasePages.PAGE_SIZE);
        List<Comment> list = commentService.getAll();
        PageInfo<Comment> pageInfo = new PageInfo<>(list);
        return BaseResponse.success("查询成功", BasePages.getPagesMap(pageInfo.getList(), pageInfo.getTotal()));
    }

    @PostMapping("save")
    public BaseResponse save(@RequestBody Comment comment,HttpServletRequest request) {
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

//    @PutMapping
//    public BaseResponse update(@RequestBody Comment comment) {
//        if (Objects.isNull(comment.getId())) {
//            return BaseResponse.fail(CodeEnum.VALIDATION_ERROR.code(), "评论ID不能为空");
//        }
//        boolean res = commentService.updateById(comment);
//        return res ? BaseResponse.success("修改成功") : BaseResponse.fail("修改失败");
//    }

    @GetMapping
    public BaseResponse info(Long id) {
        Comment commentById = commentService.getCommentById(id);
        return BaseResponse.success("查询成功", commentById);
    }

    /**
     * 获得最近一周评论数和总评论数
     */
    @GetMapping("commentCount")
    public BaseResponse commentCount() {
        Map<String, Integer> data = new HashMap<>();
        int total = commentService.getTotalCount();
        data.put("totalComment", total);
        int latest =commentService.getOneWeekCount();
        data.put("latestComment", latest);
        return BaseResponse.success("获取成功", data);
    }

    /**
     * 获得最新的n条评论
     */
    @GetMapping("latest")
    public BaseResponse latest(int number) {
        List<Comment> commentList = commentService.getLatest(number);
        return BaseResponse.success("查询成功", null);
    }

    @GetMapping("all")
    public BaseResponse getAll() {
        return BaseResponse.success("查询成功", commentService.getAll());
    }
}
