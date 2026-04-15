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
 * @since 2025-04-22
 */
@Setter
@Getter
@ApiModel(value = "港口区域新增对象", description = "用于新增和保存港口区域")
public class GlobalAreaReqVO extends BaseReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Port area code")
    @NotBlank
    @Size(max = 32, message = "Port area code must be at most 32 characters")
    private String code;

    @ApiModelProperty(value = "Port area name")
    @NotBlank
    @Size(max = 32, message = "Port area name must be at most 32 characters")
    private String name;

}
