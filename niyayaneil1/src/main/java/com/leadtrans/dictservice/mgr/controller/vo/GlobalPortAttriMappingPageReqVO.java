package com.leadtrans.dictservice.mgr.controller.vo;

import com.leadtrans.dictservice.common.vo.PageReqVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

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

    @ApiModelProperty(value = "港口五字码")
    private String globalPortCode;

}
