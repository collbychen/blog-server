package com.chens.coblog.api;


import com.chens.coblog.common.base.BaseResponse;
import com.chens.coblog.domain.User;
import com.chens.coblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 用户管理
 * @author chens
 * @version 1.0.0
 * @date 2020/8/10
 */
@RestController
@RequestMapping("api/user")
public class ApiUserController {

    @Autowired
    private UserService userService;
    @Autowired
    @Lazy(true)
    private PasswordEncoder passwordEncoder;

    @GetMapping("all")
    public BaseResponse list() {
        return BaseResponse.success("查询成功", userService.getAll());
    }

    @GetMapping("last")
    public BaseResponse updateLastTime() {
        return BaseResponse.success("查询成功", userService.updateLastTime());
    }

    /**
     * 添加用户
     */
    @PostMapping
    public BaseResponse save(@RequestBody User user) throws Exception {
        if (null != userService.findUserByUsername(user.getUsername())) {
            return BaseResponse.fail("用户名已被注册");
        }
        if (null != userService.findUserByEmail(user.getEmail())) {
            return BaseResponse.fail("邮箱已被注册");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setLoginFailNum(0);
        user.setCreateTime(new Date());
        boolean flag = userService.save(user);
        if (!flag) {
            return BaseResponse.fail("注册失败");
        }
        return BaseResponse.success("注册成功");
    }

    @PutMapping
    public BaseResponse update(@RequestBody User user) {
        //密码不可通过此接口更新，将其置空
        user.setPassword(null);
        return userService.modifyUserById(user) ? BaseResponse.success("修改成功") : BaseResponse.success("修改失败");
    }

    @GetMapping
    public BaseResponse info() {
        User currentUser = userService.getCurrentUser();
        return BaseResponse.success("获取成功", currentUser);
    }

    @PutMapping("changePass")
    public BaseResponse changePass(String oldPass, String newPass) {
        User currentUser = userService.getCurrentUser();
        boolean checkPass = passwordEncoder.matches(oldPass, currentUser.getPassword());
        if (!checkPass) {
            return BaseResponse.fail("原密码输入错误");
        }
        currentUser.setPassword(passwordEncoder.encode(newPass));
        userService.modifyUserById(currentUser);
        //退出登录
        SecurityContextHolder.getContext().setAuthentication(null);
        return BaseResponse.success("修改密码成功");
    }

}
