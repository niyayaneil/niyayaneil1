package com.leadtrans.dictservice.mgr.controller.vo;

import com.leadtrans.dictservice.common.vo.PageReqVO;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
@ToString
@ApiModel(value = "拖车公司查询参数", description = "拖车公司查询参数")
public class TruckCompanyPageReqVO extends PageReqVO implements Serializable {

    private String truckCode;

    private String truckNameEn;

    private String truckNameCn;

    private String unlocode;

    private Integer isValid;
}