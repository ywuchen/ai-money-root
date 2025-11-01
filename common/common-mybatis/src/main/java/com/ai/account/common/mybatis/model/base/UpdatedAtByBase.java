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
public class UpdatedAtByBase extends CreatedAtByBase implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableField("update_time")
    private LocalDateTime update_time;

    @TableField("update_by")
    private Long update_by;
}

