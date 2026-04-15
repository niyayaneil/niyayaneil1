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
 * @since 2025-04-15
 */
@Setter
@Getter
@ApiModel(value = "城市详情对象", description = "用于详细和列表查看城市")
public class DictCityRespVO extends DictCityReqVO implements Serializable {

    @ApiModelProperty(value = "记录ID")
    private Long id;

    @ApiModelProperty(value = "记录版本")
    private String version;

    @ApiModelProperty(value = "是否可用：1可用，0不可用")
    private String isValid;

    @ApiModelProperty(value = "创建时间")
    private Date systemCreateTimeUtc;

    @ApiModelProperty(value = "创建人")
    private String systemCreateUser;

    @ApiModelProperty(value = "更新时间")
    private Date systemUpdateTimeUtc;

    @ApiModelProperty(value = "更新人")
    private String systemUpdateUser;


    @ApiModelProperty(value = "国家中文名称")
    private String countryNameCn;
    @ApiModelProperty(value = "国家英文名称")
    private String countryNameEn;

    @ApiModelProperty(value = "省或州中文名称")
    private String stateNameCn;
    @ApiModelProperty(value = "省或州英文名称")
    private String stateNameEn;
}
