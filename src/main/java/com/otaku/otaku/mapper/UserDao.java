package com.otaku.otaku.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.otaku.otaku.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<User> {
}