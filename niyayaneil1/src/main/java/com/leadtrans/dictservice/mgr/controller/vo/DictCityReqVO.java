package com.leadtrans.dictservice.mgr.controller.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
@ApiModel(value = "城市新增对象", description = "用于新增和保存城市")
public class DictCityReqVO extends BaseReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "国家编码")
    @NotBlank
    private String countryNumCode;

    @ApiModelProperty(value = "省或州编码")
    @NotBlank
    private String stateNumCode;

    @ApiModelProperty(value = "城市编码")
    @NotBlank
    @Size(max = 128, message = "City code must be at most 128 characters")
    private String numCode;

    @ApiModelProperty(value = "中文名称")
    @NotBlank
    @Size(max = 128, message = "Chinese name must be at most 32 characters")
    private String nameCn;

    @ApiModelProperty(value = "英文名称")
    @NotBlank
    @Size(max = 128, message = "Chinese name must be at most 32 characters")
    private String nameEn;
}
