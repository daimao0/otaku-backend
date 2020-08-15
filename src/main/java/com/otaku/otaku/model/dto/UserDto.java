package com.otaku.otaku.model.dto;

import lombok.Data;

/**
 * @Author:daMao
 * @Date: Created in 22:14 2020/8/14
 */
@Data
public class UserDto {
    private String username;
    private String password;
    private Integer rememberMe;

}
