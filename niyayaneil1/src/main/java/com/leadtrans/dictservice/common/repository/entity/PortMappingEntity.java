package com.leadtrans.dictservice.common.repository.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
@TableName("PortMapping")
public class PortMappingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "LPM_PK", type = IdType.AUTO)
    private Long id;

    @TableField("LPM_CarrierCompanyCode")
    private String carrierCompanyCode;

    /**
     * 外部数据
     */
    @TableField("LPM_MappingData")
    private String mappingData;

    /**
     * 标准港口
     */
    @TableField("LPM_PortCode")
    private String portCode;

    @TableField("LPM_Source")
    private String source;

    @TableField(value = "LPM_SystemCreateTimeUtc",fill = FieldFill.INSERT)
    private Date systemCreateTimeUtc;

    @TableField(value = "LPM_SystemCreateUser",fill = FieldFill.INSERT)
    private String systemCreateUser;

    @TableField(value = "LPM_SystemUpdateTimeUtc",fill = FieldFill.INSERT_UPDATE)
    private Date systemUpdateTimeUtc;

    @TableField(value = "LPM_SystemUpdateUser",fill = FieldFill.INSERT_UPDATE)
    private String systemUpdateUser;

    @TableField(value = "LPM_IsValid",fill =  FieldFill.INSERT)
    private String isValid;

    @TableField(value = "LPM_Version",fill = FieldFill.INSERT_UPDATE)
    private String version;
}
