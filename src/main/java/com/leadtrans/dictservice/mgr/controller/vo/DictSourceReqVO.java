package com.leadtrans.dictservice.mgr.controller.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
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
@ApiModel(value = "Source新增对象", description = "用于新增和保存Source")
public class DictSourceReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "名称")
    @NotBlank(message = "{DictSourceReqVO.name.not_blank}")
    private String name;

    @ApiModelProperty(value = "编码")
    @NotBlank(message = "{DictSourceReqVO.code.not_blank}")
    private String code;

}
