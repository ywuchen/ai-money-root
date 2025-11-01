package com.ai.account.common.mybatis.model.marker;

import com.ai.account.common.mybatis.model.base.UpdatedAtByBase;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class SoftDeleteMarker extends UpdatedAtByBase implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableField("is_deleted")
    private Integer is_deleted; // 0=未删除，1=已删除
}

