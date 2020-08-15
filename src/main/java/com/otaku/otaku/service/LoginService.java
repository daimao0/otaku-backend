package com.otaku.otaku.service;

import com.otaku.otaku.common.api.CommonResult;

/**
 * 用户登录相关
 * @Author:daMao
 * @Date: Created in 0:44 2020/8/13
 */
public interface LoginService {
    /**
     *账号密码登录
     *
     */
    CommonResult<String> loginUsePassword(String username, String password);
}
