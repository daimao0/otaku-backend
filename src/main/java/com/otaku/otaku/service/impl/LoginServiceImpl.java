package com.otaku.otaku.service.impl;

import com.otaku.otaku.common.api.CommonResult;
import com.otaku.otaku.common.security.JwtUser;
import com.otaku.otaku.common.utils.BCPEUtils;
import com.otaku.otaku.common.utils.JwtTokenUtils;
import com.otaku.otaku.mapper.UserDao;
import com.otaku.otaku.model.dto.UserDto;
import com.otaku.otaku.model.entity.User;
import com.otaku.otaku.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

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

    private ThreadLocal<Integer> rememberMe = new ThreadLocal<>();

    @Override
    public CommonResult<User> loginUsePassword(UserDto userDto, HttpServletResponse httpServletResponse) {
        //通过前端输入获取用户
        User userCondition = new User().setUsername(userDto.getUsername());
        User user = userDao.selectOne(userCondition);
        //判断用户是否选择记住我
        rememberMe.set(userDto.getRememberMe()==null?0:userDto.getRememberMe());

        if (user!=null){
            //验证用户密码是否正确
            if (BCPEUtils.matches(userDto.getPassword(),user.getPassword())){
                //将User转化成jwtUser
                JwtUser jwtUser = new JwtUser(user);
                boolean isRemember = rememberMe.get() == 1;
                String role = "";
                Collection<? extends GrantedAuthority> authorities = jwtUser.getAuthorities();
                for (GrantedAuthority authority : authorities){
                    role = authority.getAuthority();
                }

                //生成token
                String token = JwtTokenUtils.createToken(jwtUser.getUsername(), role, isRemember);
                System.out.println("jwtUser:" + jwtUser.toString());

                httpServletResponse.setHeader("authorization", JwtTokenUtils.TOKEN_PREFIX + token);
                httpServletResponse.setHeader("Access-Control-Expose-Headers", "Authorization");
                return CommonResult.success(user,"生成token成功");
            }
        }
        return CommonResult.failed("查询用户不存在");
    }
}
