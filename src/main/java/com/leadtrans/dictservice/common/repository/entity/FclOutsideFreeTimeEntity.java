package com.leadtrans.dictservice.common.repository.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * FCL目的港场外免用箱表
 * </p>
 *
 * @author Aaron
 * @since 2026-04-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("fcl_outside_free_time")
public class FclOutsideFreeTimeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("customerName")
    private String customerName;

    @TableField("carrierCode")
    private String carrierCode;

    @TableField("tradeLane")
    private String tradeLane;

    @TableField("contractType")
    private String contractType;

    @TableField("containerType")
    private String containerType;

    @TableField("businessType")
    private String businessType;

    @TableField("route")
    private String route;

    @TableField("podType")
    private String podType;

    @TableField("pod")
    private String pod;

    @TableField("ddType")
    private String ddType;

    @TableField("freeDays")
    private Integer freeDays;

    @TableField("freeUnit")
    private String freeUnit;

    @TableField("validFrom")
    private Date validFrom;

    @TableField("validTo")
    private Date validTo;

    @TableField("remarks")
    private String remarks;

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