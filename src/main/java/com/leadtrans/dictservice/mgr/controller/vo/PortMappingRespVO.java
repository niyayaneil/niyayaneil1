package com.leadtrans.dictservice.mgr.controller.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Aaron
 * @since 2025-04-23
 */
@Setter
@Getter
@ApiModel(value = "港口映射详情对象", description = "用于详细和列表查看港口映射")
public class PortMappingRespVO extends PortMappingReqVO implements Serializable {

    @ApiModelProperty(value = "记录ID")
    private Long id;

    @ApiModelProperty(value = "记录版本")
    private String version;

    @ApiModelProperty(value = "创建时间")
    private Date systemCreateTimeUtc;

    @ApiModelProperty(value = "创建人")
    private String systemCreateUser;

    @ApiModelProperty(value = "更新时间")
    private Date systemUpdateTimeUtc;

    @ApiModelProperty(value = "更新人")
    private String systemUpdateUser;

}
