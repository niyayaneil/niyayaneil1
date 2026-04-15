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
@TableName("TerminalMapping")
public class TerminalMappingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "LTM_PK", type = IdType.AUTO)
    private Long id;

    @TableField("LTM_CarrierCompanyCode")
    private String carrierCompanyCode;

    @TableField("LTM_Source")
    private String source;

    @TableField("LTM_NameEn")
    private String nameEn;

    @TableField("LTM_NameCn")
    private String nameCn;

    @TableField("LTM_TerminalCode")
    private String terminalCode;

    @TableField("LTM_PortCode")
    private String portCode;


    @TableField(value = "LTM_SystemCreateTimeUtc",fill = FieldFill.INSERT)
    private Date systemCreateTimeUtc;

    @TableField(value = "LTM_SystemCreateUser",fill = FieldFill.INSERT)
    private String systemCreateUser;

    @TableField(value = "LTM_SystemUpdateTimeUtc",fill = FieldFill.INSERT_UPDATE)
    private Date systemUpdateTimeUtc;

    @TableField(value = "LTM_SystemUpdateUser",fill = FieldFill.INSERT_UPDATE)
    private String systemUpdateUser;

    @TableField(value = "LTM_IsValid",fill =  FieldFill.INSERT)
    private String isValid;

    @TableField(value = "LTM_Version",fill = FieldFill.INSERT_UPDATE)
    private String version;

}
