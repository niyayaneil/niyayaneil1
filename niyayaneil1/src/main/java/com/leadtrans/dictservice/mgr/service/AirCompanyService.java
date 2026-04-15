package com.leadtrans.dictservice.mgr.service;

import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.AirCompanyRespVO;
import com.leadtrans.dictservice.mgr.controller.vo.AirCompanyPageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.AirCompanyReqVO;

public interface AirCompanyService {

    Long create(AirCompanyReqVO VO);

    void delete(Long id);

    void update(Long id, AirCompanyReqVO VO);

    AirCompanyRespVO getById(Long id);

    PageResult<AirCompanyRespVO> page(AirCompanyPageReqVO reqVO);

    void updateValid(Long id, Integer isValid);
}