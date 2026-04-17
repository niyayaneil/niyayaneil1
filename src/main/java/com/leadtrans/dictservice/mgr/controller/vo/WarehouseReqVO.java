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
 * 仓库信息表
 * </p>
 *
 * @author Aaron
 * @since 2026-04-17
 */
@Setter
@Getter
@ApiModel(value = "仓库新增对象", description = "用于新增和保存仓库")
public class WarehouseReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "仓库代码")
    @NotBlank(message = "{WarehouseReqVO.warehouseCode.not_blank}")
    private String warehouseCode;

    @ApiModelProperty(value = "仓库英文名称")
    @NotBlank(message = "{WarehouseReqVO.warehouseNameEn.not_blank}")
    private String warehouseNameEn;

    @ApiModelProperty(value = "仓库中文名称")
    private String warehouseNameCn;

    @ApiModelProperty(value = "港口角色：POL-起运港，POD-目的港")
    @NotBlank(message = "{WarehouseReqVO.portRole.not_blank}")
    private String portRole;

    @ApiModelProperty(value = "仓库类型：CFS-集装箱货运站，LCL-拼箱仓库，AIR-空运货站")
    @NotBlank(message = "{WarehouseReqVO.warehouseType.not_blank}")
    private String warehouseType;

    @ApiModelProperty(value = "服务支持：LCL/FCL/LCL,AIR等")
    @NotBlank(message = "{WarehouseReqVO.serviceType.not_blank}")
    private String serviceType;

    @ApiModelProperty(value = "港口代码")
    private String portCode;

    @ApiModelProperty(value = "详细地址")
    private String address;

    @ApiModelProperty(value = "地址补充")
    private String address2;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "是否有效（1:有效，0:无效）")
    @NotNull(message = "{WarehouseReqVO.isValid.not_blank}")
    private Integer isValid;
}