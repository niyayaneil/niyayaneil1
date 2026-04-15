package com.leadtrans.dictservice.mgr.controller.vo;

import com.leadtrans.dictservice.common.vo.PageReqVO;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
@ToString
@ApiModel(value = "航空公司查询参数", description = "航空公司查询参数")
public class AirCompanyPageReqVO extends PageReqVO implements Serializable {

    private String iataCode;

    private String airlineNameEn;

    private String airlineNameCn;

    private String cargowiseCode;

    private String unlocode;

    private Integer isValid;
}