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
@ApiModel(value = "港口映射新增对象", description = "用于新增和保存港口映射")
public class PortMappingReqVO extends BaseReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "船司编码")
    @NotBlank
    @Size(max = 12, message = "Carrier must be at most 12 characters")
    private String carrierCompanyCode;

    @ApiModelProperty(value = "外部数据")
    @NotBlank
    @Size(max = 1024, message = "External mapping data must be at most 1024 characters")
    private String mappingData;

    @ApiModelProperty(value = "标准港口")
    @NotBlank
    @Size(min = 5,max = 5, message = "Standard port code must be exactly 5 characters")
    private String portCode;

    @ApiModelProperty(value = "数据来源")
    @NotBlank
    @Size(max = 12, message = "External data source must be exactly 12 characters")
    private String source;
}
