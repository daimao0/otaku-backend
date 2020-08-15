package com.otaku.otaku.service.impl;

import com.otaku.otaku.common.security.JwtUser;
import com.otaku.otaku.model.entity.User;
import com.otaku.otaku.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author:daMao
 * @Date: Created in 1:16 2020/8/12
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username)  {
        User user = userService.getUserByUsername(username);
        if (user==null){
            throw new UsernameNotFoundException(username+"用户不存在");
        }
        return new JwtUser(user);
    }
}
