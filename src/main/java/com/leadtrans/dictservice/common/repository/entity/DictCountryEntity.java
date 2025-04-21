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
@TableName("DictCountry")
public class DictCountryEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "LDC_PK", type = IdType.AUTO)
    private Long id;

    @TableField("LDC_Code")
    private String code;

    @TableField("LDC_NumCode")
    private String numCode;

    @TableField("LDC_NameCn")
    private String nameCn;

    @TableField("LDC_NameEn")
    private String nameEn;

    @TableField("LDC_FlagUrl")
    private String flagUrl;

    @TableField(value = "LDC_SystemCreateTimeUtc",fill = FieldFill.INSERT)
    private Date systemCreateTimeUtc;

    @TableField(value = "LDC_SystemCreateUser",fill = FieldFill.INSERT)
    private String systemCreateUser;

    @TableField(value = "LDC_SystemUpdateTimeUtc",fill = FieldFill.INSERT_UPDATE)
    private Date systemUpdateTimeUtc;

    @TableField(value = "LDC_SystemUpdateUser",fill = FieldFill.INSERT_UPDATE)
    private String systemUpdateUser;

    @TableField(value = "LDC_IsValid",fill =  FieldFill.INSERT)
    private String isValid;

}
