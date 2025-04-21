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
@TableName("DictCity")
public class DictCityEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "LD3_PK", type = IdType.AUTO)
    private Long id;

    @TableField("LD3_CountryNumCode")
    private String countryNumCode;

    @TableField("LD3_StateNumCode")
    private String stateNumCode;

    @TableField("LD3_NumCode")
    private String numCode;

    @TableField("LD3_NameCn")
    private String nameCn;

    @TableField("LD3_NameEn")
    private String nameEn;

    @TableField(value = "LD3_SystemCreateTimeUtc",fill = FieldFill.INSERT)
    private Date systemCreateTimeUtc;

    @TableField(value = "LD3_SystemCreateUser",fill = FieldFill.INSERT)
    private String systemCreateUser;

    @TableField(value = "LD3_SystemUpdateTimeUtc",fill = FieldFill.INSERT_UPDATE)
    private Date systemUpdateTimeUtc;

    @TableField(value = "LD3_SystemUpdateUser",fill = FieldFill.INSERT_UPDATE)
    private String systemUpdateUser;

    @TableField(value = "LD3_IsValid",fill =  FieldFill.INSERT)
    private String isValid;

}
