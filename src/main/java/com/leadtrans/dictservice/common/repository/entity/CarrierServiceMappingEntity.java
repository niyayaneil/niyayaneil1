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
@TableName("CarrierServiceMapping")
public class CarrierServiceMappingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "LSM_PK", type = IdType.AUTO)
    private Long id;

    @TableField("LSM_CarrierCompanyCode")
    private String carrierCompanyCode;

    @TableField("LSM_Source")
    private String source;

    @TableField("LSM_MappingData")
    private String mappingData;

    @TableField("LSM_CarrierServiceCode")
    private String carrierServiceCode;

    @TableField(value = "LSM_SystemCreateTimeUtc",fill = FieldFill.INSERT)
    private Date systemCreateTimeUtc;

    @TableField(value = "LSM_SystemCreateUser",fill = FieldFill.INSERT)
    private String systemCreateUser;

    @TableField(value = "LSM_SystemUpdateTimeUtc",fill = FieldFill.INSERT_UPDATE)
    private Date systemUpdateTimeUtc;

    @TableField(value = "LSM_SystemUpdateUser",fill = FieldFill.INSERT_UPDATE)
    private String systemUpdateUser;

    @TableField(value = "LSM_IsValid",fill =  FieldFill.INSERT)
    private String isValid;

    @TableField(value = "LSM_Version",fill = FieldFill.INSERT_UPDATE)
    private String version;
}
