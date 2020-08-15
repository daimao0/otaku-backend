package com.otaku.otaku.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.otaku.otaku.mapper.UserDao;
import com.otaku.otaku.model.entity.User;
import com.otaku.otaku.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:daMao
 * @Date: Created in 1:33 2020/8/13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao,User> implements UserService {
    @Autowired
    UserDao userDao;
    @Override
    public User getUserByUsername(String username) {
        User userCondition = new User();
        userCondition.setUsername(username);
        User user = userDao.selectOne(userCondition);
        return user;
    }
}
