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
 * @since 2025-04-23
 */
@Setter
@Getter
@ApiModel(value = "港口映射查询参数", description = "港口映射查询参数")
public class PortMappingPageReqVO extends PageReqVO implements Serializable {
    @ApiModelProperty(value = "是否有效")
    private String isValid;

    @ApiModelProperty(value = "船司编码")
    private String carrierCompanyCode;

    @ApiModelProperty(value = "外部数据")
    private String mappingData;

    @ApiModelProperty(value = "标准港口")
    private String portCode;

    @ApiModelProperty(value = "数据来源")
    private String source;

}
