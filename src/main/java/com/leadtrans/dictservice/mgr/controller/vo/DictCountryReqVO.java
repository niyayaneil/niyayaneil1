package com.leadtrans.dictservice.mgr.controller.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

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
@ApiModel(value = "国家新增对象", description = "用于新增和保存国家")
public class DictCountryReqVO extends BaseReqVO implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "记录ID")
    private Long id;

    @ApiModelProperty(value = "国家编码")
    private String code;

    @ApiModelProperty(value = "数字编码")
    private String numCode;

    @ApiModelProperty(value = "中文名称")
    private String nameCn;

    @ApiModelProperty(value = "英文名称")
    private String nameEn;

    @ApiModelProperty(value = "国旗地址")
    private String flagUrl;
}
