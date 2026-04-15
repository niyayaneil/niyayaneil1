package com.leadtrans.dictservice.mgr.service;

import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.RailCompanyRespVO;
import com.leadtrans.dictservice.mgr.controller.vo.RailCompanyPageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.RailCompanyReqVO;

public interface RailCompanyService {

    Long create(RailCompanyReqVO VO);

    void delete(Long id);

    void update(Long id, RailCompanyReqVO VO);

    RailCompanyRespVO getById(Long id);

    PageResult<RailCompanyRespVO> page(RailCompanyPageReqVO reqVO);

    void updateValid(Long id, Integer isValid);
}
