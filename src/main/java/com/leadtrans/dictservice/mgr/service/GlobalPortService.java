package com.leadtrans.dictservice.mgr.service;

import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalPortPageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalPortReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalPortRespVO;

public interface GlobalPortService {

    Long create(GlobalPortReqVO VO);

    void delete(Long id);

    void update(Long id, GlobalPortReqVO VO);

    GlobalPortRespVO getById(Long id);

    PageResult<GlobalPortRespVO> page(GlobalPortPageReqVO reqVO);

    void updateValid(Long id, String isValid);
}
