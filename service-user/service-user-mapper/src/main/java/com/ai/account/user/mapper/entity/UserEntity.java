package com.ai.account.user.mapper.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户实体，对应表 t_user
 * 列采用 lower_snake 命名
 * @author GitHub Copilot
 * @since 2025-11-01
 */
@Getter
@Setter
@ToString
@TableName("t_user")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;
    private String username;
    private String email;
    private String phone;
    private String password_hash;
    private LocalDateTime create_time;
    private LocalDateTime update_time;
}

