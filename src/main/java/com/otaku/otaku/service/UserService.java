package com.otaku.otaku.service;

import com.otaku.otaku.model.entity.User;

/**
 * @Author:daMao
 * @Date: Created in 0:47 2020/8/13
 */
public interface UserService {
    User getUserByUsername(String username);
}
