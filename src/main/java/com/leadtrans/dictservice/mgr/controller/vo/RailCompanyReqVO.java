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
 * 铁路公司信息表
 * </p>
 *
 * @author Aaron
 * @since 2026-04-15
 */
@Setter
@Getter
@ApiModel(value = "铁路公司新增对象", description = "用于新增和保存铁路公司")
public class RailCompanyReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "铁路公司编码")
    @NotBlank(message = "{RailCompanyReqVO.railroadCode.not_blank}")
    private String railroadCode;

    @ApiModelProperty(value = "铁路公司英文名称")
    @NotBlank(message = "{RailCompanyReqVO.railroadNameEn.not_blank}")
    private String railroadNameEn;

    @ApiModelProperty(value = "铁路公司中文名称")
    @NotBlank(message = "{RailCompanyReqVO.railroadNameCn.not_blank}")
    private String railroadNameCn;

    @ApiModelProperty(value = "合作伙伴承运商")
    @NotBlank(message = "{RailCompanyReqVO.partnerCarriers.not_blank}")
    private String partnerCarriers;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "是否有效（1:有效，0:无效）")
    @NotNull(message = "{RailCompanyReqVO.isValid.not_blank}")
    private Integer isValid;
}
