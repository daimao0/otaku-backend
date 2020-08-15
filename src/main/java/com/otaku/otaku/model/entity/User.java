package com.otaku.otaku.model.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author:daMao
 * @Date: Created in 1:19 2020/8/12
 */
@Data
@TableName(value = "sys_user")
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class User {
    //value与数据库主键列名一致，若实体类属性名与表主键列名一致可省略value
    @TableId(value = "id",type = IdType.AUTO)//指定自增策略
    private Long id;
    @TableField(value = "email")
    private String email;
    @TableField(value = "username")
    private String username;
    @TableField(value = "nickname")
    private String nickname;
    @TableField(value = "password")
    private String password;
    @TableField(value = "role")
    private String role;
}
