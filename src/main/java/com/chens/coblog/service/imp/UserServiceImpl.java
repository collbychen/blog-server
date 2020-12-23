package com.chens.coblog.service.imp;

import com.chens.coblog.domain.User;
import com.chens.coblog.mapper.UserMapper;
import com.chens.coblog.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 用户接口实现
 * @author chens
 * @version 1.0.0
 * @date 2020/8/10
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User findUserByUsername(String username) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username",username);
        return userMapper.selectOneByExample(example);
    }

    @Override
    public User findUserByEmail(String email) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("email",email);
        return userMapper.selectOneByExample(example);
    }

    @Override
    public Boolean save(User user) {
        return userMapper.insert(user) > 0;
    }

    @Override
    public Boolean modifyUserById(User user) { return userMapper.updateByPrimaryKeySelective(user)>0; }

    @Override
    public List<User> getAll() {
        return userMapper.selectAll();
    }

    @Override
    public User getCurrentUser() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return findUserByUsername(username);
    }

    @Override
    public Boolean updateLastTime() {
        User currentUser = getCurrentUser();
        currentUser.setLastLoginTime(new Date());
        return userMapper.updateByPrimaryKeySelective(currentUser) > 0;
    }

}




