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
@TableName("GlobalTerminal")
public class GlobalTerminalEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "LGT_PK", type = IdType.AUTO)
    private Long id;

    @TableField("LGT_Code")
    private String code;

    @TableField("LGT_NameEn")
    private String nameEn;

    @TableField("LGT_NameCn")
    private String nameCn;
    
    @TableField("LGT_PortCode")
    private String portCode;

    @TableField(value = "LGT_SystemCreateTimeUtc",fill = FieldFill.INSERT)
    private Date systemCreateTimeUtc;

    @TableField(value = "LGT_SystemCreateUser",fill = FieldFill.INSERT)
    private String systemCreateUser;

    @TableField(value = "LGT_SystemUpdateTimeUtc",fill = FieldFill.INSERT_UPDATE)
    private Date systemUpdateTimeUtc;

    @TableField(value = "LGT_SystemUpdateUser",fill = FieldFill.INSERT_UPDATE)
    private String systemUpdateUser;

    @TableField(value = "LGT_IsValid",fill =  FieldFill.INSERT)
    private String isValid;

    @TableField(value = "LGT_Version",fill = FieldFill.INSERT_UPDATE)
    private String version;

}
