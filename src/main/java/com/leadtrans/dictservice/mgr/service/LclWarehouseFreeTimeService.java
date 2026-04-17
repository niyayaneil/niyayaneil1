package com.leadtrans.dictservice.mgr.service;

import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.LclWarehouseFreeTimeRespVO;
import com.leadtrans.dictservice.mgr.controller.vo.LclWarehouseFreeTimePageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.LclWarehouseFreeTimeReqVO;

public interface LclWarehouseFreeTimeService {

    Long create(LclWarehouseFreeTimeReqVO VO);

    void delete(Long id);

    void update(Long id, LclWarehouseFreeTimeReqVO VO);

    LclWarehouseFreeTimeRespVO getById(Long id);

    PageResult<LclWarehouseFreeTimeRespVO> page(LclWarehouseFreeTimePageReqVO reqVO);

    void updateValid(Long id, Integer isValid);
}