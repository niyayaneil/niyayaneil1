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
@TableName("GlobalPort")
public class GlobalPortEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "LGP_PK", type = IdType.AUTO)
    private Long id;

    @TableField("LGP_Code")
    private String code;

    @TableField("LGP_NameEn_Lower")
    private String nameEnLower;

    @TableField("LGP_NameEn")
    private String nameEn;

    @TableField("LGP_NameCn")
    private String nameCn;

    @TableField("LGP_CountryCode")
    private String countryCode;

    @TableField("LGP_StateCode")
    private String stateCode;

    @TableField("LGP_CityCode")
    private String cityCode;

    /**
     * 区域
     */
    @TableField("LGP_LGA")
    private Long globalAreaPk;

    @TableField("LGP_GroupName")
    private String groupName;


    @TableField(value = "LGP_SystemCreateTimeUtc",fill = FieldFill.INSERT)
    private Date systemCreateTimeUtc;

    @TableField(value = "LGP_SystemCreateUser",fill = FieldFill.INSERT)
    private String systemCreateUser;

    @TableField(value = "LGP_SystemUpdateTimeUtc",fill = FieldFill.INSERT_UPDATE)
    private Date systemUpdateTimeUtc;

    @TableField(value = "LGP_SystemUpdateUser",fill = FieldFill.INSERT_UPDATE)
    private String systemUpdateUser;

    @TableField(value = "LGP_IsValid",fill =  FieldFill.INSERT)
    private String isValid;

    @TableField(value = "LGP_Version",fill = FieldFill.INSERT_UPDATE)
    private String version;
}
