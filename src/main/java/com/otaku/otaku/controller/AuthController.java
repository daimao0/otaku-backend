package com.otaku.otaku.controller;

import com.otaku.otaku.common.api.CommonResult;
import com.otaku.otaku.common.utils.BCPEUtils;
import com.otaku.otaku.mapper.UserDao;
import com.otaku.otaku.model.entity.User;
import com.otaku.otaku.service.LoginService;
import com.otaku.otaku.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by echisan on 2018/6/23
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDao userDao;
    @Autowired
    private LoginService loginService;
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestBody Map<String, String> registerUser) {
        User user = new User();
        user.setUsername(registerUser.get("username"));
        user.setPassword(BCPEUtils.encode(registerUser.get("password")));
        user.setRole("ROLE_USER");
        Integer save = userDao.insert(user);
        return save.toString();
    }

/*    @PostMapping("/login")
    @ResponseBody
    @ApiOperation(value = "账号密码登录")
    public CommonResult<String> LoginUser(@ApiParam(value = "传入username,password")
                                          @RequestBody User user) {

        return loginService.loginUsePassword(user.getUsername(), user.getPassword());
    }*/

    @PostMapping("haha")
    public String haha() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "haha:" + userDetails.getUsername() + "," + userDetails.getPassword();
    }
}
