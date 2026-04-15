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
@TableName("InlandPointTransport")
public class InlandPointTransportEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "LIP_PK", type = IdType.AUTO)
    private Long id;

    @TableField("LIP_CountryNumCode")
    private String countryNumCode;

    @TableField("LIP_StartPort")
    private String startPort;

    @TableField("LIP_EndPort")
    private String endPort;

    @TableField("LIP_TT")
    private String tt;

    @TableField("LIP_Charge")
    private Float charge;

    @TableField(value = "LIP_SystemCreateTimeUtc",fill = FieldFill.INSERT)
    private Date systemCreateTimeUtc;

    @TableField(value = "LIP_SystemCreateUser",fill = FieldFill.INSERT)
    private String systemCreateUser;

    @TableField(value = "LIP_SystemUpdateTimeUtc",fill = FieldFill.INSERT_UPDATE)
    private Date systemUpdateTimeUtc;

    @TableField(value = "LIP_SystemUpdateUser",fill = FieldFill.INSERT_UPDATE)
    private String systemUpdateUser;

    @TableField(value = "LIP_IsValid",fill =  FieldFill.INSERT)
    private String isValid;

    @TableField(value = "LIP_Version",fill = FieldFill.INSERT_UPDATE)
    private String version;


}
