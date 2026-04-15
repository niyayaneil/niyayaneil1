package com.leadtrans.dictservice.common.repository.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 港口属性表
 * </p>
 *
 * @author Aaron
 * @since 2025-04-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("GlobalPortAttri")
public class GlobalPortAttriEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "LGPA_PK", type = IdType.AUTO)
    private Long id;

    /**
     * 属性类型: GEO/PRIMARY
     */
    @TableField("LGPA_Type")
    private String type;

    /**
     * 属性名称
     */
    @TableField("LGPA_Name")
    private String name;

    /**
     * 属性编码
     */
    @TableField("LGPA_Code")
    private String code;

    @TableField(value = "LGPA_SystemCreateTimeUtc",fill = FieldFill.INSERT)
    private Date systemCreateTimeUtc;

    @TableField(value = "LGPA_SystemCreateUser",fill = FieldFill.INSERT)
    private String systemCreateUser;

    @TableField(value = "LGPA_SystemUpdateTimeUtc",fill = FieldFill.INSERT_UPDATE)
    private Date systemUpdateTimeUtc;

    @TableField(value = "LGPA_SystemUpdateUser",fill = FieldFill.INSERT_UPDATE)
    private String systemUpdateUser;

    @TableField(value = "LGPA_IsValid",fill =  FieldFill.INSERT)
    private String isValid;

    @TableField(value = "LGPA_Version",fill = FieldFill.INSERT_UPDATE)
    private String version;
}
