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
@TableName("ReadMe")
public class ReadMeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "LRE_PK", type = IdType.AUTO)
    private Long id;

    @TableField("LRE_Title")
    private String title;

    @TableField("LRE_Code")
    private String code;

    @TableField("LRE_Content")
    private String content;

    @TableField("LRE_Type")
    private String type;

    @TableField(value = "LRE_SystemCreateTimeUtc",fill = FieldFill.INSERT)
    private Date systemCreateTimeUtc;

    @TableField(value = "LRE_SystemCreateUser",fill = FieldFill.INSERT)
    private String systemCreateUser;

    @TableField(value = "LRE_SystemUpdateTimeUtc",fill = FieldFill.INSERT_UPDATE)
    private Date systemUpdateTimeUtc;

    @TableField(value = "LRE_SystemUpdateUser",fill = FieldFill.INSERT_UPDATE)
    private String systemUpdateUser;

    @TableField(value = "LRE_IsValid",fill =  FieldFill.INSERT)
    private String isValid;

    @TableField(value = "LRE_Version",fill = FieldFill.INSERT_UPDATE)
    private String version;

}
