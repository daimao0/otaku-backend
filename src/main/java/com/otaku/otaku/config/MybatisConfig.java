package com.otaku.otaku.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:daMao
 * @Date: Created in 1:44 2020/8/12
 */
@Configuration
@MapperScan({"com.otaku.otaku.mapper"})
public class MybatisConfig {
}
