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
 * LCL拼箱目的港免堆期配置表
 * </p>
 *
 * @author Aaron
 * @since 2026-04-17
 */
@Setter
@Getter
@ApiModel(value = "免堆期配置新增对象", description = "用于新增和保存免堆期配置")
public class LclWarehouseFreeTimeReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "目的港仓库代码", required = true)
    @NotBlank(message = "{lclWarehouseFreeTimeReqVO.warehouseCode.not_blank}")
    private String warehouseCode;

    @ApiModelProperty(value = "目的港/地点代码", required = true)
    @NotBlank(message = "{lclWarehouseFreeTimeReqVO.portCode.not_blank}")
    private String portCode;

    @ApiModelProperty(value = "仓库运营服务商名称")
    private String serviceProvider;

    @ApiModelProperty(value = "免堆期天数")
    private Integer freeDays;

    @ApiModelProperty(value = "天数单位（WD-工作日/CD-自然日）")
    private String freeUnit;

    @ApiModelProperty(value = "有效期开始时间")
    private Date validFrom;

    @ApiModelProperty(value = "有效期结束时间")
    private Date validTo;

    @ApiModelProperty(value = "备注说明")
    private String remarks;

    @ApiModelProperty(value = "是否有效（1:有效，0:无效）")
    @NotNull(message = "{lclWarehouseFreeTimeReqVO.isValid.not_blank}")
    private Integer isValid;
}