package com.leadtrans.dictservice.mgr.controller.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
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
@ApiModel(value = "港口属性新增对象", description = "用于新增和保存港口属性")
public class GlobalPortAttriMappingReqVO extends BaseReqVO implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "港口五字码")
    @NotNull
    private String globalPortCode;

    @ApiModelProperty(value = "属性值ID")
    @NotNull
    private Long globalPortAttriId;

}
