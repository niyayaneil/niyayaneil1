package com.leadtrans.dictservice.common.repository.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 中转港TT
 * </p>
 *
 * @author Aaron
 * @since 2025-04-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("GlobalIpiTT")
public class GlobalIpiTTEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "GITT_PK", type = IdType.AUTO)
    private Long id;

    /**
     * 起运港Code
     */
    @TableField("GITT_PolCode")
    private String polCode;

    /**
     * 目的港Code
     */
    @TableField("GITT_PodCode")
    private String podCode;

    /**
     * TT
     */
    @TableField("GITT_TT")
    private Integer tt;

    /**
     * 备注
     */
    @TableField("GITT_Remark")
    private String remark;

    @TableField(value = "GITT_SystemCreateTimeUtc",fill = FieldFill.INSERT)
    private Date systemCreateTimeUtc;

    @TableField(value = "GITT_SystemCreateUser",fill = FieldFill.INSERT)
    private String systemCreateUser;

    @TableField(value = "GITT_SystemUpdateTimeUtc",fill = FieldFill.INSERT_UPDATE)
    private Date systemUpdateTimeUtc;

    @TableField(value = "GITT_SystemUpdateUser",fill = FieldFill.INSERT_UPDATE)
    private String systemUpdateUser;

    @TableField(value = "GITT_IsValid",fill =  FieldFill.INSERT)
    private String isValid;

    @TableField(value = "GITT_Version",fill = FieldFill.INSERT_UPDATE)
    private String version;
}
