package com.leadtrans.dictservice.mgr.service;

import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalTerminalPageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalTerminalReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalTerminalRespVO;

public interface GlobalTerminalService {

    Long create(GlobalTerminalReqVO VO);

    void delete(Long id);

    void update(Long id, GlobalTerminalReqVO VO);

    GlobalTerminalRespVO getById(Long id);

    PageResult<GlobalTerminalRespVO> page(GlobalTerminalPageReqVO reqVO);

    void updateValid(Long id, String isValid);

}
