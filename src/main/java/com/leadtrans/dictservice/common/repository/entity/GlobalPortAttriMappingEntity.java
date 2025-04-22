package com.leadtrans.dictservice.common.repository.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 港口属性映射表
 * </p>
 *
 * @author Aaron
 * @since 2025-04-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("GlobalPortAttriMapping")
public class GlobalPortAttriMappingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "LGPAM_PK", type = IdType.AUTO)
    private Long id;

    /**
     * 港口主键
     */
    @TableField("LGPAM_PK")
    private Long globalPortId;

    /**
     * 属性主键
     */
    @TableField("LGPA_PK")
    private Long globalPortAttriId;

    @TableField(value = "LGPAM_SystemCreateTimeUtc",fill = FieldFill.INSERT)
    private Date systemCreateTimeUtc;

    @TableField(value = "LGPAM_SystemCreateUser",fill = FieldFill.INSERT)
    private String systemCreateUser;

    @TableField(value = "LGPAM_SystemUpdateTimeUtc",fill = FieldFill.INSERT_UPDATE)
    private Date systemUpdateTimeUtc;

    @TableField(value = "LGPAM_SystemUpdateUser",fill = FieldFill.INSERT_UPDATE)
    private String systemUpdateUser;

    @TableField(value = "LGPAM_IsValid",fill =  FieldFill.INSERT)
    private String isValid;

    @TableField(value = "LGPAM_Version",fill = FieldFill.INSERT_UPDATE)
    private String version;

}
