package com.yichen.money.user.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Table("t_user")
@Data
@Accessors(chain = true)
public class User {
    @Id
    private Long id;
    private String username;
    private String password; // BCrypt 加密后
}

