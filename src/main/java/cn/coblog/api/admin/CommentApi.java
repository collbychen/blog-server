package cn.coblog.api.admin;

import cn.coblog.common.base.BaseResponse;
import cn.coblog.domain.Comment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * admin评论api
 * @author chens
 * @version 1.0.0
 * @date 2020/8/29 7:30
 */
@RequestMapping("comment")
public interface CommentApi {

    @GetMapping("list")
    BaseResponse list(Integer current);

    @PostMapping("save")
    BaseResponse save(@RequestBody Comment comment, HttpServletRequest request);

//    @PutMapping
//    BaseResponse update(@RequestBody Comment comment);

    @GetMapping
    BaseResponse getComment(Long id);

    /**
     * 获得最近一周评论数和总评论数
     */
    @GetMapping("commentCount")
    BaseResponse commentCount();

    /**
     * 获得最新的n条评论
     */
    @GetMapping("latest")
    BaseResponse latest(int number);

    @GetMapping("all")
    BaseResponse getAll();

}
