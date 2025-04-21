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
@TableName("CarrierCompany")
public class CarrierCompanyEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "LCC_PK", type = IdType.AUTO)
    private Long id;

    @TableField("LCC_Name")
    private String name;

    @TableField("LCC_Code")
    private String code;

    @TableField("LCC_Cargowise9Code")
    private String cargowise9Code;

    @TableField("LCC_WebUrl")
    private String webUrl;

    @TableField("LCC_LogoUrl")
    private String logoUrl;

    @TableField("LCC_Sort")
    private Integer sort;

    @TableField(value = "LCC_SystemCreateTimeUtc",fill = FieldFill.INSERT)
    private Date systemCreateTimeUtc;

    @TableField(value = "LCC_SystemCreateUser",fill = FieldFill.INSERT)
    private String systemCreateUser;

    @TableField(value = "LCC_SystemUpdateTimeUtc",fill = FieldFill.INSERT_UPDATE)
    private Date systemUpdateTimeUtc;

    @TableField(value = "LCC_SystemUpdateUser",fill = FieldFill.INSERT_UPDATE)
    private String systemUpdateUser;

    @TableField(value = "LCC_IsValid",fill =  FieldFill.INSERT)
    private String isValid;

    @TableField(value = "LCC_Version",fill = FieldFill.INSERT_UPDATE)
    private String version;
}
