package com.otaku.otaku.service.impl;

import com.otaku.otaku.common.api.CommonResult;
import com.otaku.otaku.common.utils.BCPEUtils;
import com.otaku.otaku.mapper.UserDao;
import com.otaku.otaku.model.entity.User;
import com.otaku.otaku.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * @Author:daMao
 * @Date: Created in 22:45 2020/8/14
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserDao userDao;

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Override
    public CommonResult<String> loginUsePassword(String username, String password) {
        User userCondition = new User().setUsername(username);
        User user = userDao.selectOne(userCondition);
        if (user!=null){
            if (BCPEUtils.matches(password,user.getPassword())){
                userDetailsService.loadUserByUsername(username);
                return CommonResult.success("","查询成功");
            }
        }
        return CommonResult.failed("查询失败");
    }
}
