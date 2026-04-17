package com.leadtrans.dictservice.common.repository.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * LCL拼箱目的港免堆期配置表
 * </p>
 *
 * @author Aaron
 * @since 2026-04-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("lcl_warehouse_free_time")
public class LclWarehouseFreeTimeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("warehouseCode")
    private String warehouseCode;

    @TableField("portCode")
    private String portCode;

    @TableField("serviceProvider")
    private String serviceProvider;

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