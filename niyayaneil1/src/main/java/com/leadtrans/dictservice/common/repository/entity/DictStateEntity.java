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
@TableName("DictState")
public class DictStateEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "LDS_PK", type = IdType.AUTO)
    private Long id;

    @TableField("LDS_CountryNumCode")
    private String countryNumCode;

    @TableField("LDS_NumCode")
    private String numCode;

    @TableField("LDS_NameCn")
    private String nameCn;

    @TableField("LDS_NameEn")
    private String nameEn;

    @TableField(value = "LDS_SystemCreateTimeUtc",fill = FieldFill.INSERT)
    private Date systemCreateTimeUtc;

    @TableField(value = "LDS_SystemCreateUser",fill = FieldFill.INSERT)
    private String systemCreateUser;

    @TableField(value = "LDS_SystemUpdateTimeUtc",fill = FieldFill.INSERT_UPDATE)
    private Date systemUpdateTimeUtc;

    @TableField(value = "LDS_SystemUpdateUser",fill = FieldFill.INSERT_UPDATE)
    private String systemUpdateUser;

    @TableField(value = "LDS_IsValid",fill =  FieldFill.INSERT)
    private String isValid;

}
