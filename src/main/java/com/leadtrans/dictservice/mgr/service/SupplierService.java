package com.leadtrans.dictservice.mgr.service;

import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.SupplierRespVO;
import com.leadtrans.dictservice.mgr.controller.vo.SupplierPageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.SupplierReqVO;

public interface SupplierService {

    Long create(SupplierReqVO VO);

    void delete(Long id);

    void update(Long id, SupplierReqVO VO);

    SupplierRespVO getById(Long id);

    PageResult<SupplierRespVO> page(SupplierPageReqVO reqVO);

    void updateValid(Long id, String isValid);
}
