package com.leadtrans.dictservice.mgr.service;

import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.PortMappingPageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.PortMappingReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.PortMappingRespVO;

public interface PortMappingService {

    Long create(PortMappingReqVO VO);

    void delete(Long id);

    void update(Long id, PortMappingReqVO VO);

    PortMappingRespVO getById(Long id);

    PageResult<PortMappingRespVO> page(PortMappingPageReqVO reqVO);

    void updateValid(Long id, String isValid);

}
