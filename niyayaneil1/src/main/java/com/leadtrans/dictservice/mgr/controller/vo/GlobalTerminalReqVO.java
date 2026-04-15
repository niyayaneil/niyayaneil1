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
 * @since 2025-04-23
 */
@Setter
@Getter
@ApiModel(value = "码头新增对象", description = "用于新增和保存码头")
public class GlobalTerminalReqVO extends BaseReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "码头编码")
    @NotBlank
    @Size(max = 25, message = "Terminal code must be at most 25 characters")
    private String code;

    @ApiModelProperty(value = "码头中文名")
    @NotBlank
    @Size(max = 64, message = "Chinese name must be at most 64 characters")
    private String nameCn;

    @ApiModelProperty(value = "码头英文名")
    @NotBlank
    @Size(max = 64, message = "English name must be at most 64 characters")
    private String nameEn;

    @ApiModelProperty(value = "港口五字码")
    @NotBlank
    private String portCode;

}
