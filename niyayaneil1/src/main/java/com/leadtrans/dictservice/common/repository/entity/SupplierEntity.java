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
@TableName("Supplier")
public class SupplierEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "LSR_PK", type = IdType.AUTO)
    private Long id;

    @TableField("LSR_Name_Cn")
    private String nameCn;

    @TableField("LSR_Name_En")
    private String nameEn;

    @TableField("LSR_Code")
    private String code;

    /**
     * 供应商类型中文
     */
    @TableField("LSR_Type")
    private String type;

    @TableField(value = "LSR_SystemCreateTimeUtc",fill = FieldFill.INSERT)
    private Date systemCreateTimeUtc;

    @TableField(value = "LSR_SystemCreateUser",fill = FieldFill.INSERT)
    private String systemCreateUser;

    @TableField(value = "LSR_SystemUpdateTimeUtc",fill = FieldFill.INSERT_UPDATE)
    private Date systemUpdateTimeUtc;

    @TableField(value = "LSR_SystemUpdateUser",fill = FieldFill.INSERT_UPDATE)
    private String systemUpdateUser;

    @TableField(value = "LSR_IsValid",fill =  FieldFill.INSERT)
    private String isValid;

    @TableField(value = "LSR_Version",fill = FieldFill.INSERT_UPDATE)
    private String version;


}
