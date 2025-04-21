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
 * @since 2025-04-15
 */
@Setter
@Getter
@ApiModel(value = "省或州查询参数", description = "省或州查询参数")
public class DictStatePageReqVO extends PageReqVO implements Serializable {
    @ApiModelProperty(value = "是否有效")
    private String isValid;

    @ApiModelProperty(value = "国家编码")
    private String countryNumCode;



}
