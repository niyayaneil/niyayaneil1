package com.leadtrans.dictservice.mgr.service;

import com.leadtrans.dictservice.common.repository.entity.GlobalPortAttriEntity;
import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalAreaRespVO;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalPortAttriPageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalPortAttriReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalPortAttriRespVO;

import java.util.List;

public interface GlobalPortAttriService {

    Long create(GlobalPortAttriReqVO VO);

    void delete(Long id);

    void update(Long id, GlobalPortAttriReqVO VO);

    GlobalPortAttriRespVO getById(Long id);

    PageResult<GlobalPortAttriRespVO> page(GlobalPortAttriPageReqVO reqVO);

    void updateValid(Long id, String isValid);

}
