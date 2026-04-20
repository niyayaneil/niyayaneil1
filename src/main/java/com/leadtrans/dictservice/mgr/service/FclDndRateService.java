package com.leadtrans.dictservice.mgr.service;

import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.FclDndRateRespVO;
import com.leadtrans.dictservice.mgr.controller.vo.FclDndRatePageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.FclDndRateReqVO;

public interface FclDndRateService {

    Long create(FclDndRateReqVO VO);

    void delete(Long id);

    void update(Long id, FclDndRateReqVO VO);

    FclDndRateRespVO getById(Long id);

    PageResult<FclDndRateRespVO> page(FclDndRatePageReqVO reqVO);

    void updateValid(Long id, Integer isValid);
}