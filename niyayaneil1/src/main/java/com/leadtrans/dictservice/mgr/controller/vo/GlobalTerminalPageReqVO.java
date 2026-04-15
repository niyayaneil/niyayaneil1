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
@ApiModel(value = "码头查询参数", description = "码头查询参数")
public class GlobalTerminalPageReqVO extends PageReqVO implements Serializable {
    @ApiModelProperty(value = "是否有效")
    private String isValid;

    @ApiModelProperty(value = "码头编码")
    private String code;

    @ApiModelProperty(value = "码头中文名")
    private String nameCn;

    @ApiModelProperty(value = "码头英文名")
    private String nameEn;

    @ApiModelProperty(value = "港口编码")
    private String portCode;

}
