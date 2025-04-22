package com.leadtrans.dictservice.mgr.service;

import com.leadtrans.dictservice.common.repository.entity.GlobalAreaEntity;
import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalAreaPageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalAreaReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalAreaRespVO;

import java.util.List;

public interface GlobalAreaService {

    Long create(GlobalAreaReqVO VO);

    void delete(Long id);

    void update(Long id, GlobalAreaReqVO VO);

    GlobalAreaRespVO getById(Long id);

    PageResult<GlobalAreaRespVO> page(GlobalAreaPageReqVO reqVO);

    void updateValid(Long id, String isValid);

    List<GlobalAreaEntity> findByIds(List<Long> globalAreaIds);

    List<GlobalAreaEntity> findByCodes(List<String> globalAreaCodes);
}
