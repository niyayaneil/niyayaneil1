package com.leadtrans.dictservice.mgr.service;

import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.CarrierCompanyRespVO;
import com.leadtrans.dictservice.mgr.controller.vo.CarrierCompanyPageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.CarrierCompanyReqVO;

public interface CarrierCompanyService {

    Long create(CarrierCompanyReqVO VO);

    void delete(Long id);

    void update(Long id, CarrierCompanyReqVO VO);

    CarrierCompanyRespVO getById(Long id);

    PageResult<CarrierCompanyRespVO> page(CarrierCompanyPageReqVO reqVO);

    void updateValid(Long id, String isValid);
}
