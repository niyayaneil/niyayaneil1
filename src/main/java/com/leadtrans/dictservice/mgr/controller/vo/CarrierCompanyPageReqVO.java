package com.leadtrans.dictservice.mgr.controller.vo;

import com.leadtrans.dictservice.common.vo.PageReqVO;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Aaron
 * @since 2025-04-15
 */
@Setter
@Getter
@ApiModel(value = "船司查询参数", description = "船司查询参数")
public class CarrierCompanyPageReqVO extends PageReqVO implements Serializable {

    private String name;

    private String code;

    private String cargowise9Code;

}
