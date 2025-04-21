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
@ApiModel(value = "省或州新增对象", description = "用于新增和保存省或州")
public class DictStateReqVO extends BaseReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "国家编码")
    @NotBlank
    @Size(max = 32, message = "Chinese name must be at most 32 characters")
    private String countryNumCode;

    @ApiModelProperty(value = "数字编码")
    @NotBlank
    @Size(max = 128, message = "Province/State code must be at most 128 characters")
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
