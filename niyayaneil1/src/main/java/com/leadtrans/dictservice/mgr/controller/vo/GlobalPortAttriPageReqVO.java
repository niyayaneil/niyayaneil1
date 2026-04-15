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
@ApiModel(value = "属性值查询参数", description = "属性值查询参数")
public class GlobalPortAttriPageReqVO extends PageReqVO implements Serializable {
    @ApiModelProperty(value = "是否有效")
    private String isValid;

    @ApiModelProperty(value = "属性值类型")
    private String type;

    @ApiModelProperty(value = "属性值编码")
    private String code;

    @ApiModelProperty(value = "属性值名称")
    private String name;

}
