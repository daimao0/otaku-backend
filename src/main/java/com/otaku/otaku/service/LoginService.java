package com.otaku.otaku.service;

import com.otaku.otaku.common.api.CommonResult;
import com.otaku.otaku.model.dto.UserDto;
import com.otaku.otaku.model.entity.User;

import javax.servlet.http.HttpServletResponse;

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
    CommonResult<User> loginUsePassword(UserDto userDto, HttpServletResponse httpServletResponse);
}
