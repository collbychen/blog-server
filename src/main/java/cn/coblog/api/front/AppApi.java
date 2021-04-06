package cn.coblog.api.front;

import cn.coblog.common.base.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * 前台应用api
 * @author chens
 * @version 1.0.0
 * @date 2020/8/12
 */
@RequestMapping("app")
public interface AppApi {

    @GetMapping("global")
    BaseResponse global();

    @GetMapping("github")
    BaseResponse sendCode();

    @GetMapping("userInfo")
    BaseResponse getInfo(@RequestParam(value = "token") String token);

    @GetMapping("callback")
    String callBack(HttpServletRequest request);

    /**
     * 搜索页分页
     * @param wd  关键字
     * @param page 需要加载的页码
     */
    @GetMapping("search")
    BaseResponse search(String wd, Integer page);
}
