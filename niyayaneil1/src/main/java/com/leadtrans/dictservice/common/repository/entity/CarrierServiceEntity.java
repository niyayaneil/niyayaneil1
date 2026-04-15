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
@TableName("CarrierService")
public class CarrierServiceEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "LCS_PK", type = IdType.AUTO)
    private Long id;

    @TableField("LCS_CarrierCompanyCode")
    private String carrierCompanyCode;

    @TableField("LCS_StartPortCode")
    private String startPortCode;

    @TableField("LCS_EndPortCode")
    private String endPortCode;

    @TableField("LCS_Code")
    private String code;

    @TableField(value = "LCS_SystemCreateTimeUtc",fill = FieldFill.INSERT)
    private Date systemCreateTimeUtc;

    @TableField(value = "LCS_SystemCreateUser",fill = FieldFill.INSERT)
    private String systemCreateUser;

    @TableField(value = "LCS_SystemUpdateTimeUtc",fill = FieldFill.INSERT_UPDATE)
    private Date systemUpdateTimeUtc;

    @TableField(value = "LCS_SystemUpdateUser",fill = FieldFill.INSERT_UPDATE)
    private String systemUpdateUser;

    @TableField(value = "LCS_IsValid",fill =  FieldFill.INSERT)
    private String isValid;

    @TableField(value = "LCS_Version",fill = FieldFill.INSERT_UPDATE)
    private String version;

}
