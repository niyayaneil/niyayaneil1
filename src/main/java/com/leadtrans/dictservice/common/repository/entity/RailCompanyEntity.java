package com.leadtrans.dictservice.common.repository.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 铁路公司信息表
 * </p>
 *
 * @author Aaron
 * @since 2026-04-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("railCompany")
public class RailCompanyEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 铁路公司编码，非空，唯一标识
     */
    @TableField("railroadCode")
    private String railroadCode;

    /**
     * 铁路公司英文名称，非空
     */
    @TableField("railroadNameEn")
    private String railroadNameEn;

    /**
     * 铁路公司中文名称，非空
     */
    @TableField("railroadNameCn")
    private String railroadNameCn;

    /**
     * 合作伙伴承运商，非空
     */
    @TableField("partnerCarriers")
    private String partnerCarriers;

    /**
     * 描述，可为空
     */
    @TableField("description")
    private String description;

    /**
     * 创建时间，非空，默认当前时间
     */
    @TableField(value = "createTime", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 创建用户，可为空
     */
    @TableField(value = "createUser", fill = FieldFill.INSERT)
    private String createUser;

    /**
     * 更新时间，非空，默认当前时间且更新时自动更新
     */
    @TableField(value = "updateTime", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 更新用户，可为空
     */
    @TableField(value = "updateUser", fill = FieldFill.INSERT_UPDATE)
    private String updateUser;

    /**
     * 有效状态，非空，默认值1
     */
    @TableField(value = "isValid", fill = FieldFill.INSERT)
    private Integer isValid;

}
