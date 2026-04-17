package com.leadtrans.dictservice.common.repository.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 仓库信息表
 * </p>
 *
 * @author Aaron
 * @since 2026-04-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("warehouse")
public class WarehouseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("warehouseCode")
    private String warehouseCode;

    @TableField("warehouseNameEn")
    private String warehouseNameEn;

    @TableField("warehouseNameCn")
    private String warehouseNameCn;

    @TableField("portRole")
    private String portRole;

    @TableField("warehouseType")
    private String warehouseType;

    @TableField("serviceType")
    private String serviceType;

    @TableField("portCode")
    private String portCode;

    @TableField("address")
    private String address;

    @TableField("address2")
    private String address2;

    @TableField("phone")
    private String phone;

    @TableField(value = "createTime", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "createUser", fill = FieldFill.INSERT)
    private String createUser;

    @TableField(value = "updateTime", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(value = "updateUser", fill = FieldFill.INSERT_UPDATE)
    private String updateUser;

    @TableField(value = "isValid", fill = FieldFill.INSERT)
    private Integer isValid;
}