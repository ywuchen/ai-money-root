package com.ai.account.common.mybatis.model.base;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CreatedAtByBase implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableField("create_time")
    private LocalDateTime create_time;

    @TableField("create_by")
    private Long create_by;
}

