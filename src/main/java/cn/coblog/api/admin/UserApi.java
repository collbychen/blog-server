package cn.coblog.api.admin;

import cn.coblog.common.base.BaseResponse;
import cn.coblog.domain.User;
import org.springframework.web.bind.annotation.*;

/**
 * admin用户api
 * @author chens
 * @version 1.0.0
 * @date 2020/8/10
 */
@RequestMapping("user")
public interface UserApi {

    @GetMapping("all")
    BaseResponse list();

    @GetMapping("last")
    BaseResponse updateLastTime();

    @PostMapping
    BaseResponse save(@RequestBody User user);

    @PutMapping
    BaseResponse update(@RequestBody User user);

    @GetMapping
    BaseResponse info();

    @PutMapping("changePass")
    BaseResponse changePass(String oldPass, String newPass);
}
