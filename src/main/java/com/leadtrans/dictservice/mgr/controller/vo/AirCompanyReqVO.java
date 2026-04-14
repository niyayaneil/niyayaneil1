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
 * 航空公司信息表
 * </p>
 *
 * @author Aaron
 * @since 2026-04-14
 */
@Setter
@Getter
@ApiModel(value = "航空公司新增对象", description = "用于新增和保存航空公司")
public class AirCompanyReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "航空公司IATA二字码")
    @NotBlank(message = "{AirCompanyReqVO.iataCode.not_blank}")
    private String iataCode;

    @ApiModelProperty(value = "航空公司英文名称")
    @NotBlank(message = "{AirCompanyReqVO.airlineNameEn.not_blank}")
    private String airlineNameEn;

    @ApiModelProperty(value = "航空公司中文名称")
    @NotBlank(message = "{AirCompanyReqVO.airlineNameCn.not_blank}")
    private String airlineNameCn;

    @ApiModelProperty(value = "Cargowise系统代码")
    private String cargowiseCode;

    @ApiModelProperty(value = "联合国贸易运输地点代码")
    private String unlocode;

    @ApiModelProperty(value = "货运查询网址")
    private String airCargoUrl;

    @ApiModelProperty(value = "是否有效（1:有效，0:无效）")
    @NotNull(message = "{AirCompanyReqVO.isValid.not_blank}")
    private Integer isValid;
}