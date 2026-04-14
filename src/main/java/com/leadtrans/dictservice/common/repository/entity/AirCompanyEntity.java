package com.leadtrans.dictservice.common.repository.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 航空公司信息表
 * </p>
 *
 * @author Aaron
 * @since 2026-04-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("aircompany")
public class AirCompanyEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("iataCode")
    private String iataCode;

    @TableField("airlineNameEn")
    private String airlineNameEn;

    @TableField("airlineNameCn")
    private String airlineNameCn;

    @TableField("cargowiseCode")
    private String cargowiseCode;

    @TableField("unlocode")
    private String unlocode;

    @TableField("airCargoUrl")
    private String airCargoUrl;

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