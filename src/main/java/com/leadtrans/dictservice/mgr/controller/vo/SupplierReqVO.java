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
@ApiModel(value = "供应商新增对象", description = "用于新增和保存供应商")
public class SupplierReqVO extends BaseReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Chinese name")
    @NotBlank
    @Size(max = 32, message = "Chinese name must be at most 32 characters")
    private String nameCn;

    @ApiModelProperty(value = "English name")
    @NotBlank
    @Size(max = 32, message = "English name must be at most 32 characters")
    private String nameEn;

    @ApiModelProperty(value = "Vendor code")
    @NotBlank
    @Size(max = 12, message = "Vendor code must be at most 12 characters")
    private String code;

    @ApiModelProperty(value = "Type")
    @NotBlank
    private String type;

}
