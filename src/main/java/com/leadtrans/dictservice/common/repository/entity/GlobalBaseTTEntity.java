package com.leadtrans.dictservice.common.repository.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 基本港TT
 * </p>
 *
 * @author Aaron
 * @since 2025-04-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("GlobalBaseTT")
public class GlobalBaseTTEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "GBTT_PK", type = IdType.AUTO)
    private Long id;

    /**
     * 船司
     */
    @TableField("GBTT_Carrier")
    private String carrier;

    /**
     * 航线
     */
    @TableField("GBTT_SvcCode")
    private String svcCode;

    /**
     * 起运港Code
     */
    @TableField("GBTT_PolCode")
    private String polCode;

    /**
     * 目的港Code
     */
    @TableField("GBTT_PodCode")
    private String podCode;

    /**
     * 中转港Code
     */
    @TableField("GBTT_RouteCode")
    private String routeCode;

    /**
     * ServiceCode
     */
    @TableField("GBTT_ServiceCode")
    private String serviceCode;

    /**
     * ETD
     */
    @TableField("GBTT_Etd")
    private String etd;

    /**
     * TT
     */
    @TableField("GBTT_TT")
    private Integer tt;

    /**
     * 备注
     */
    @TableField("GBTT_Remark")
    private String remark;

    @TableField(value = "GBTT_SystemCreateTimeUtc",fill = FieldFill.INSERT)
    private Date systemCreateTimeUtc;

    @TableField(value = "GBTT_SystemCreateUser",fill = FieldFill.INSERT)
    private String systemCreateUser;

    @TableField(value = "GBTT_SystemUpdateTimeUtc",fill = FieldFill.INSERT_UPDATE)
    private Date systemUpdateTimeUtc;

    @TableField(value = "GBTT_SystemUpdateUser",fill = FieldFill.INSERT_UPDATE)
    private String systemUpdateUser;

    @TableField(value = "GBTT_IsValid",fill =  FieldFill.INSERT)
    private String isValid;

    @TableField(value = "GBTT_Version",fill = FieldFill.INSERT_UPDATE)
    private String version;
}
