package com.leadtrans.dictservice.mgr.service;

import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalPortAttriMappingPageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalPortAttriMappingReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalPortAttriMappingRespVO;

public interface GlobalPortAttriMappingService {

    Long create(GlobalPortAttriMappingReqVO VO);

    void delete(Long id);

    void update(Long id, GlobalPortAttriMappingReqVO VO);

    GlobalPortAttriMappingRespVO getById(Long id);

    PageResult<GlobalPortAttriMappingRespVO> page(GlobalPortAttriMappingPageReqVO reqVO);

    void updateValid(Long id, String isValid);

}
