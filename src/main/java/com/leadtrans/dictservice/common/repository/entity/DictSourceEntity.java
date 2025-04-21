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
@TableName("DictSource")
public class DictSourceEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "LSO_PK", type = IdType.AUTO)
    private Long id;

    @TableField("LSO_Name")
    private String name;

    @TableField("LSO_Code")
    private String code;

    @TableField(value = "LSO_SystemCreateTimeUtc",fill = FieldFill.INSERT)
    private Date systemCreateTimeUtc;

    @TableField(value = "LSO_SystemCreateUser",fill = FieldFill.INSERT)
    private String systemCreateUser;

    @TableField(value = "LSO_SystemUpdateTimeUtc",fill = FieldFill.INSERT_UPDATE)
    private Date systemUpdateTimeUtc;

    @TableField(value = "LSO_SystemUpdateUser",fill = FieldFill.INSERT_UPDATE)
    private String systemUpdateUser;

    @TableField(value = "LSO_Version",fill = FieldFill.INSERT_UPDATE)
    private String version;

}
