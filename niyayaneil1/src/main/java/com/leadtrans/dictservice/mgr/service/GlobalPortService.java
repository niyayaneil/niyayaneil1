package com.leadtrans.dictservice.mgr.service;

import com.leadtrans.dictservice.common.repository.entity.GlobalPortEntity;
import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalPortPageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalPortReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalPortRespVO;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface GlobalPortService {

    Long create(GlobalPortReqVO VO);

    void delete(Long id);

    void update(Long id, GlobalPortReqVO VO);

    GlobalPortRespVO getById(Long id);

    GlobalPortRespVO getByCode(String code);

    PageResult<GlobalPortRespVO> page(GlobalPortPageReqVO reqVO);

    void updateValid(Long id, String isValid);

    List<GlobalPortEntity> selectByIds(Collection<Long> globalPortIds);

    Map<String,GlobalPortRespVO> selectMapByCodes(Collection<String> globalPortCodes);
}
