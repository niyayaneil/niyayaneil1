package com.leadtrans.dictservice.mgr.service;

import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.WarehouseRespVO;
import com.leadtrans.dictservice.mgr.controller.vo.WarehousePageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.WarehouseReqVO;

public interface WarehouseService {

    Long create(WarehouseReqVO VO);

    void delete(Long id);

    void update(Long id, WarehouseReqVO VO);

    WarehouseRespVO getById(Long id);

    PageResult<WarehouseRespVO> page(WarehousePageReqVO reqVO);

    void updateValid(Long id, Integer isValid);
}