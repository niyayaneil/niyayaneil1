package com.leadtrans.dictservice.common.repository.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Aaron
 * @since 2025-04-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("GlobalArea")
public class GlobalAreaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "LGA_PK", type = IdType.AUTO)
    private Long id;

    @TableField("LGA_Name")
    private String name;

    @TableField("LGA_Code")
    private String code;

    @TableField(value = "LGA_SystemCreateTimeUtc",fill = FieldFill.INSERT)
    private Date systemCreateTimeUtc;

    @TableField(value = "LGA_SystemCreateUser",fill = FieldFill.INSERT)
    private String systemCreateUser;

    @TableField(value = "LGA_SystemUpdateTimeUtc",fill = FieldFill.INSERT_UPDATE)
    private Date systemUpdateTimeUtc;

    @TableField(value = "LGA_SystemUpdateUser",fill = FieldFill.INSERT_UPDATE)
    private String systemUpdateUser;

    @TableField(value = "LGA_IsValid",fill =  FieldFill.INSERT)
    private String isValid;

    @TableField(value = "LGA_Version",fill = FieldFill.INSERT_UPDATE)
    private String version;
}
