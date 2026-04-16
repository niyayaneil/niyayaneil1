package com.leadtrans.dictservice.common.repository.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 拖车公司信息表
 * </p>
 *
 * @author Aaron
 * @since 2026-04-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("truckCompany")
public class TruckCompanyEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("truckCode")
    private String truckCode;

    @TableField("truckNameEn")
    private String truckNameEn;

    @TableField("truckNameCn")
    private String truckNameCn;

    @TableField("unlocode")
    private String unlocode;

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