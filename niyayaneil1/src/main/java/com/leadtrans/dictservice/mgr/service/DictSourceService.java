package com.leadtrans.dictservice.mgr.service;

import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.DictSourcePageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.DictSourceRespVO;

public interface DictSourceService {

    PageResult<DictSourceRespVO> page(DictSourcePageReqVO reqVO);

}
