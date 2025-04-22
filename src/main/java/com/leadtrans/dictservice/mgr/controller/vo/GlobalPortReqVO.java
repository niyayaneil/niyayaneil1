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
@ApiModel(value = "港口新增对象", description = "用于新增和保存港口")
public class GlobalPortReqVO extends BaseReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Standard port code")
    @NotBlank
    @Size(min = 5,max = 5, message = "Standard port code must be exactly 5 characters")
    private String code;

    @ApiModelProperty(value = "Chinese name")
    @NotBlank
    @Size(max = 64, message = "Chinese name must be at most 64 characters")
    private String nameCn;

    @ApiModelProperty(value = "English name")
    @NotBlank
    @Size(max = 64, message = "English name must be at most 64 characters")
    private String nameEn;

    @NotBlank
    private String countryCode;

    private String stateCode;

    private String cityCode;

    private Long globalAreaId;

    private String groupName;
}
