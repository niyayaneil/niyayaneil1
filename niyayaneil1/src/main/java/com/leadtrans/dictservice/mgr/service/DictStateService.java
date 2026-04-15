package com.leadtrans.dictservice.mgr.service;

import com.leadtrans.dictservice.common.repository.entity.DictStateEntity;
import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.DictStatePageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.DictStateReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.DictStateRespVO;

import java.util.List;

public interface DictStateService {

    Long create(DictStateReqVO VO);

    void delete(Long id);

    void update(Long id, DictStateReqVO VO);

    DictStateRespVO getById(Long id);

    PageResult<DictStateRespVO> page(DictStatePageReqVO reqVO);

    void updateValid(Long id, String isValid);

    List<DictStateEntity> findByNumCodes(List<String> stateNumCodes);
}
