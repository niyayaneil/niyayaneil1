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
 * FCL目的港场外免用箱表
 * </p>
 *
 * @author Aaron
 * @since 2026-04-20
 */
@Setter
@Getter
@ToString
@ApiModel(value = "免用箱配置查询参数", description = "免用箱配置查询参数")
public class FclOutsideFreeTimePageReqVO extends PageReqVO implements Serializable {

    private String customerName;

    private String carrierCode;

    private String tradeLane;

    private String contractType;

    private String containerType;

    private String businessType;

    private String route;

    private String podType;

    private String pod;

    private String ddType;

    private Integer freeDays;

    private String freeUnit;

    private Date validFrom;

    private Date validTo;

    private String remarks;

    private Integer isValid;
}