package com.leadtrans.dictservice.common.repository.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * FCL DND费率表
 * </p>
 *
 * @author Aaron
 * @since 2026-04-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("fcl_dnd_rate")
public class FclDndRateEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("carrierCode")
    private String carrierCode;

    @TableField("tradeLane")
    private String tradeLane;

    @TableField("chargeType")
    private String chargeType;

    @TableField("businessType")
    private String businessType;

    @TableField("portCode")
    private String portCode;

    @TableField("containerType")
    private String containerType;

    @TableField("freeDays")
    private String freeDays;

    @TableField("freeUnit")
    private String freeUnit;

    @TableField("freeDaysRule")
    private String freeDaysRule;

    @TableField("specialRule")
    private String specialRule;

    @TableField("tier1Days")
    private String tier1Days;

    @TableField("tier1Rate")
    private BigDecimal tier1Rate;

    @TableField("tier2Days")
    private String tier2Days;

    @TableField("tier2Rate")
    private BigDecimal tier2Rate;

    @TableField("tier3Days")
    private String tier3Days;

    @TableField("tier3Rate")
    private BigDecimal tier3Rate;

    @TableField("tier4Days")
    private String tier4Days;

    @TableField("tier4Rate")
    private BigDecimal tier4Rate;

    @TableField("currency")
    private String currency;

    @TableField("remarks")
    private String remarks;

    @TableField("validFrom")
    private Date validFrom;

    @TableField("validTo")
    private Date validTo;

    @TableField(value = "isValid", fill = FieldFill.INSERT)
    private Integer isValid;

    @TableField(value = "createTime", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "createUser", fill = FieldFill.INSERT)
    private String createUser;

    @TableField(value = "updateTime", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(value = "updateUser", fill = FieldFill.INSERT_UPDATE)
    private String updateUser;
}