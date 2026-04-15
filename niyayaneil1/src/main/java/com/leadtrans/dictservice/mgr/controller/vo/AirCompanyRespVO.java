package com.leadtrans.dictservice.mgr.controller.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 航空公司信息表
 * </p>
 *
 * @author Aaron
 * @since 2026-04-14
 */
@Setter
@Getter
@ApiModel(value = "航空公司详情对象", description = "用于详细和列表查看航空公司")
public class AirCompanyRespVO extends AirCompanyReqVO implements Serializable {

    @ApiModelProperty(value = "记录ID")
    private Long id;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "创建人")
    private String createUser;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateUser;
}