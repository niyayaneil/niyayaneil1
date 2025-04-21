package com.leadtrans.dictservice.mgr.controller.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
@ApiModel(value = "船司新增对象", description = "用于新增和保存船司")
public class CarrierCompanyReqVO extends BaseReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Carrier Name")
    @NotBlank
    @Size(max = 128, message = "Carrier name must be at most 128 characters")
    private String name;

    @ApiModelProperty(value = "Carrier Code")
    @NotBlank
    @Size(max = 12, message = "Carrier code must be at most 12 characters")
    private String code;

    @ApiModelProperty(value = "CargoWise Code")
    @NotBlank
    @Size(max = 9, message = "Carrier code must be at most 9 characters")
    private String cargowise9Code;

    @ApiModelProperty(value = "Web Url")
    @NotBlank
    @Size(max = 256, message = "Website URL must be at most 9 characters")
    private String webUrl;

    @ApiModelProperty(value = "LOGO")
    private String logoUrl;

    @ApiModelProperty(value = "Sort")
    @NotNull
    private Integer sort;

}
