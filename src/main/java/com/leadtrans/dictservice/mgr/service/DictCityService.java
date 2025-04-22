package com.leadtrans.dictservice.mgr.service;

import com.leadtrans.dictservice.common.repository.entity.DictCityEntity;
import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.DictCityRespVO;
import com.leadtrans.dictservice.mgr.controller.vo.DictCityPageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.DictCityReqVO;

import java.util.List;

public interface DictCityService {

    Long create(DictCityReqVO VO);

    void delete(Long id);

    void update(Long id, DictCityReqVO VO);

    DictCityRespVO getById(Long id);

    PageResult<DictCityRespVO> page(DictCityPageReqVO reqVO);

    void updateValid(Long id, String isValid);

    List<DictCityEntity> findByNumCodes(List<String> cityCodes);
}
