package com.leadtrans.dictservice.mgr.controller.vo;

import com.leadtrans.dictservice.common.vo.PageReqVO;
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
@ApiModel(value = "港口属性查询参数", description = "港口属性查询参数")
public class GlobalPortAttriMappingPageReqVO extends PageReqVO implements Serializable {
    @ApiModelProperty(value = "是否有效")
    private String isValid;

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "港口五字码")
    @NotNull
    private String globalPortCode;

    @ApiModelProperty(value = "属性值类型")
    @NotNull
    private String portAttriType;

    @ApiModelProperty(value = "属性值ID")
    @NotNull
    private Long portAttriId;

}
