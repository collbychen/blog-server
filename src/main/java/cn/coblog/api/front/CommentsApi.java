package cn.coblog.api.front;

import cn.coblog.common.base.BaseResponse;
import cn.coblog.domain.Comment;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 前台评论api
 * @author chens
 * @version 1.0.0
 * @date 2020/8/12
 */
@RequestMapping("comments")
public interface CommentsApi {

    @GetMapping("{id}")
    BaseResponse info(@PathVariable(value = "id") Long id);

    @PostMapping
    BaseResponse commit(HttpServletRequest request, @RequestBody Comment comment);

}
