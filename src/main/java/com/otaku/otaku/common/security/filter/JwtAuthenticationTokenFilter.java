package com.otaku.otaku.common.security.filter;

import com.otaku.otaku.common.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT登录授权过滤器 如果请求头携带token 验证token
 *
 * @author tujietg
 * @date 3/31/20 9:07 PM
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    private JwtTokenUtils jwtTokenUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        // 获取token
        String authToken = request.getHeader(this.tokenHeader);
        if (!StringUtils.isEmpty(authToken)) {
            // 获取username
            String username = JwtTokenUtils.getUsername(authToken);
            // username 为空 而且Security中不存在
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // 得到UserDetails
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                // 验证token是否正确
                /*if (jwtTokenUtils.validateToken(authToken, memberId)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }*/
            }
        }
        chain.doFilter(request, response);
    }


}
