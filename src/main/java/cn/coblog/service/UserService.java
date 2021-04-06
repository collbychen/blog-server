package cn.coblog.service;

import cn.coblog.domain.User;

import java.util.List;

/**
 * 用户接口
 * @author chens
 * @version 1.0.0
 * @date 2020/8/10
 */
public interface UserService {

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    Boolean save(User user);

    Boolean modifyUserById(User user);

    List<User> getAll();

    User getCurrentUser();

    Boolean updateLastTime();
}




