package com.otaku.otaku.controller;

import com.otaku.otaku.common.api.CommonResult;
import com.otaku.otaku.model.entity.User;
import com.otaku.otaku.service.LoginService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;
/*    @PostMapping("/login")
    @ApiOperation(value = "账号密码登录")
    public CommonResult<String> loginUsePassword (@ApiParam(value = "传入username,password")
                                                 @RequestBody User user){
        return  loginService.loginUsePassword(user.getUsername(),user.getPassword());
    }*/

    @PostMapping("haha")
    public String haha(){
        UserDetails userDetails = (UserDetails) org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "haha:"+userDetails.getUsername()+","+userDetails.getPassword();
    }
}