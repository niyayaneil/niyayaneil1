package com.leadtrans.dictservice.mgr.controller.vo;

import com.leadtrans.dictservice.common.vo.PageReqVO;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
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
@ToString
@ApiModel(value = "DND费率查询参数", description = "DND费率查询参数")
public class FclDndRatePageReqVO extends PageReqVO implements Serializable {

    private String carrierCode;

    private String tradeLane;

    private String chargeType;

    private String businessType;

    private String portCode;

    private String containerType;

    private String freeDays;

    private String freeUnit;

    private Date validFrom;

    private Date validTo;

    private Integer isValid;
}