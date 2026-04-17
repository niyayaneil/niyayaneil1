package com.leadtrans.dictservice.mgr.controller.vo;

import com.leadtrans.dictservice.common.vo.PageReqVO;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
@ToString
@ApiModel(value = "仓库查询参数", description = "仓库查询参数")
public class WarehousePageReqVO extends PageReqVO implements Serializable {

    private String warehouseCode;

    private String warehouseNameEn;

    private String warehouseNameCn;

    private String portRole;

    private String warehouseType;

    private String serviceType;

    private String portCode;

    private String phone;

    private Integer isValid;
}