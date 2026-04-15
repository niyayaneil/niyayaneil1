package com.leadtrans.dictservice.mgr.controller.vo;

import com.leadtrans.dictservice.common.vo.PageReqVO;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

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
@ApiModel(value = "铁路公司查询参数", description = "铁路公司查询参数")
public class RailCompanyPageReqVO extends PageReqVO implements Serializable {

    private String railroadCode;

    private String railroadNameEn;

    private String railroadNameCn;

    private String partnerCarriers;

    private Integer isValid;
}
