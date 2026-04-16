package com.leadtrans.dictservice.mgr.controller.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 拖车公司信息表
 * </p>
 *
 * @author Aaron
 * @since 2026-04-16
 */
@Setter
@Getter
@ApiModel(value = "拖车公司新增对象", description = "用于新增和保存拖车公司")
public class TruckCompanyReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "拖车公司代码")
    @NotBlank(message = "{TruckCompanyReqVO.truckCode.not_blank}")
    private String truckCode;

    @ApiModelProperty(value = "拖车公司英文名称")
    @NotBlank(message = "{TruckCompanyReqVO.truckNameEn.not_blank}")
    private String truckNameEn;

    @ApiModelProperty(value = "拖车公司中文名称")
    @NotBlank(message = "{TruckCompanyReqVO.truckNameCn.not_blank}")
    private String truckNameCn;

    @ApiModelProperty(value = "联合国贸易运输地点代码")
    private String unlocode;

    @ApiModelProperty(value = "是否有效（1:有效，0:无效）")
    @NotNull(message = "{TruckCompanyReqVO.isValid.not_blank}")
    private Integer isValid;
}