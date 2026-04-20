package com.leadtrans.dictservice.mgr.controller.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * FCL目的港场外免用箱表
 * </p>
 *
 * @author Aaron
 * @since 2026-04-20
 */
@Setter
@Getter
@ApiModel(value = "免用箱配置新增对象", description = "用于新增和保存免用箱配置")
public class FclOutsideFreeTimeReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "客户名称")
    private String customerName;

    @ApiModelProperty(value = "船公司代码", required = true)
    @NotBlank(message = "{fclOutsideFreeTimeReqVO.carrierCode.not_blank}")
    private String carrierCode;

    @ApiModelProperty(value = "贸易航线", required = true)
    @NotBlank(message = "{fclOutsideFreeTimeReqVO.tradeLane.not_blank}")
    private String tradeLane;

    @ApiModelProperty(value = "合约类型", required = true)
    @NotBlank(message = "{fclOutsideFreeTimeReqVO.contractType.not_blank}")
    private String contractType;

    @ApiModelProperty(value = "箱型", required = true)
    @NotBlank(message = "{fclOutsideFreeTimeReqVO.containerType.not_blank}")
    private String containerType;

    @ApiModelProperty(value = "业务类型", required = true)
    @NotBlank(message = "{fclOutsideFreeTimeReqVO.businessType.not_blank}")
    private String businessType;

    @ApiModelProperty(value = "中转港区域")
    private String route;

    @ApiModelProperty(value = "范围类型", required = true)
    @NotBlank(message = "{fclOutsideFreeTimeReqVO.podType.not_blank}")
    private String podType;

    @ApiModelProperty(value = "范围值")
    private String pod;

    @ApiModelProperty(value = "免箱期类型", required = true)
    @NotBlank(message = "{fclOutsideFreeTimeReqVO.ddType.not_blank}")
    private String ddType;

    @ApiModelProperty(value = "免箱天数")
    private Integer freeDays;

    @ApiModelProperty(value = "天数单位")
    private String freeUnit;

    @ApiModelProperty(value = "生效日期")
    private Date validFrom;

    @ApiModelProperty(value = "失效日期")
    private Date validTo;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "是否有效", required = true)
    @NotNull(message = "{fclOutsideFreeTimeReqVO.isValid.not_blank}")
    private Integer isValid;
}
