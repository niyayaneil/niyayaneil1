package com.leadtrans.dictservice.mgr.service;

import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.FclOutsideFreeTimeRespVO;
import com.leadtrans.dictservice.mgr.controller.vo.FclOutsideFreeTimePageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.FclOutsideFreeTimeReqVO;

public interface FclOutsideFreeTimeService {

    Long create(FclOutsideFreeTimeReqVO VO);

    void delete(Long id);

    void update(Long id, FclOutsideFreeTimeReqVO VO);

    FclOutsideFreeTimeRespVO getById(Long id);

    PageResult<FclOutsideFreeTimeRespVO> page(FclOutsideFreeTimePageReqVO reqVO);

    void updateValid(Long id, Integer isValid);
}
