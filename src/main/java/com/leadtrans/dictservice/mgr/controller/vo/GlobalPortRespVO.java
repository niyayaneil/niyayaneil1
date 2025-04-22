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
 * @since 2025-04-22
 */
@Setter
@Getter
@ApiModel(value = "港口详情对象", description = "用于详细和列表查看港口")
public class GlobalPortRespVO extends GlobalPortReqVO implements Serializable {

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

    @ApiModelProperty(value = "国家英文名称")
    private String countryNameEn;
    @ApiModelProperty(value = "国家中文名称")
    private String countryNameCn;

    @ApiModelProperty(value = "省或州中文名称")
    private String stateNameCn;
    @ApiModelProperty(value = "省或州英文名称")
    private String stateNameEn;

    @ApiModelProperty(value = "城市中文名称")
    private String cityNameCn;
    @ApiModelProperty(value = "城市英文名称")
    private String cityNameEn;

    @ApiModelProperty(value = "港口区域编码")
    private String globalAreaCode;

    @ApiModelProperty(value = "港口区域名称")
    private String globalAreaName;
}
