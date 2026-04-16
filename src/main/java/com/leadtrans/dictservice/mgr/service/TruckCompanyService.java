package com.leadtrans.dictservice.mgr.service;

import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.TruckCompanyRespVO;
import com.leadtrans.dictservice.mgr.controller.vo.TruckCompanyPageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.TruckCompanyReqVO;

public interface TruckCompanyService {

    Long create(TruckCompanyReqVO VO);

    void delete(Long id);

    void update(Long id, TruckCompanyReqVO VO);

    TruckCompanyRespVO getById(Long id);

    PageResult<TruckCompanyRespVO> page(TruckCompanyPageReqVO reqVO);

    void updateValid(Long id, Integer isValid);
}