package com.leadtrans.dictservice.mgr.controller.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * FCL DND费率表
 * </p>
 *
 * @author Aaron
 * @since 2026-04-20
 */
@Setter
@Getter
@ApiModel(value = "DND费率新增对象", description = "用于新增和保存DND费率")
public class FclDndRateReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "船司代码", required = true)
    @NotBlank(message = "{fclDndRateReqVO.carrierCode.not_blank}")
    private String carrierCode;

    @ApiModelProperty(value = "航线/国家")
    private String tradeLane;

    @ApiModelProperty(value = "费用类型")
    private String chargeType;

    @ApiModelProperty(value = "业务类型")
    private String businessType;

    @ApiModelProperty(value = "港口代码（多个用逗号分隔）")
    private String portCode;

    @ApiModelProperty(value = "箱型（多个用逗号分隔）")
    private String containerType;

    @ApiModelProperty(value = "免费天数")
    private String freeDays;

    @ApiModelProperty(value = "免费单位")
    private String freeUnit;

    @ApiModelProperty(value = "免费期规则")
    private String freeDaysRule;

    @ApiModelProperty(value = "特殊规则")
    private String specialRule;

    @ApiModelProperty(value = "第一阶梯天数")
    private String tier1Days;

    @ApiModelProperty(value = "第一阶梯费率")
    private BigDecimal tier1Rate;

    @ApiModelProperty(value = "第二阶梯天数")
    private String tier2Days;

    @ApiModelProperty(value = "第二阶梯费率")
    private BigDecimal tier2Rate;

    @ApiModelProperty(value = "第三阶梯天数")
    private String tier3Days;

    @ApiModelProperty(value = "第三阶梯费率")
    private BigDecimal tier3Rate;

    @ApiModelProperty(value = "第四阶梯天数")
    private String tier4Days;

    @ApiModelProperty(value = "第四阶梯费率")
    private BigDecimal tier4Rate;

    @ApiModelProperty(value = "币种")
    private String currency;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "有效期开始时间")
    private Date validFrom;

    @ApiModelProperty(value = "有效期结束时间")
    private Date validTo;

    @ApiModelProperty(value = "是否有效", required = true)
    @NotNull(message = "{fclDndRateReqVO.isValid.not_blank}")
    private Integer isValid;
}
