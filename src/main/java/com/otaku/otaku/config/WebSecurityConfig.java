package com.otaku.otaku.config;

import com.otaku.otaku.common.security.exception.JWTAccessDeniedHandler;
import com.otaku.otaku.common.security.exception.JWTAuthenticationEntryPoint;
import com.otaku.otaku.common.security.filter.JWTAuthenticationFilter;
import com.otaku.otaku.common.security.filter.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * @Author:daMao
 * @Date: Created in 0:49 2020/8/12
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//保证post之前的注解可以使用
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login", "/**/*.js", "/**/*.css", "/**/*.png", "/**/*.ico",
                        "/admin/**", "/common/**", "/css/**", "/img/**", "/js/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout()
                .permitAll();

        // 禁用 Spring Security 自带的跨域处理
        http.csrf().disable()
                //响应头并没有X-Frame-Options
                .headers().frameOptions().disable();

            //添加过滤器
        http.addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                // 定制我们自己的 session 策略：调整为让 Spring Security 不创建和使用 session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //添加异常处理
                .exceptionHandling().authenticationEntryPoint(new JWTAuthenticationEntryPoint())
                .accessDeniedHandler(new JWTAccessDeniedHandler());

    }

    /**
     * 核心过滤器配置，更多使用ignoring()用来忽略对静态资源的控制
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("admin/*", "common/*", "css/*", "img/*", "js/*");
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }


/*
    //用户验证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        //基于内存来存储用户信息
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("user").password(new BCryptPasswordEncoder().encode("123")).roles("USER").and()          //设置
                .withUser("admin").password(new BCryptPasswordEncoder().encode("123")).roles("USER","ADMIN");
    }
*/

}
