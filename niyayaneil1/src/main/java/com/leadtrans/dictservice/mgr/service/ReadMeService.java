package com.leadtrans.dictservice.mgr.service;

import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.ReadMePageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.ReadMeReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.ReadMeRespVO;

public interface ReadMeService {

    Long create(ReadMeReqVO VO);

    void delete(Long id);

    void update(Long id, ReadMeReqVO VO);

    ReadMeRespVO getById(Long id);

    ReadMeRespVO getByCode(String code);

    PageResult<ReadMeRespVO> page(ReadMePageReqVO reqVO);

    void updateValid(Long id, String isValid);
}
