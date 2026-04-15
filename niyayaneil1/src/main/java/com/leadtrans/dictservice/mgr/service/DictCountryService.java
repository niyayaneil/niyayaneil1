package com.leadtrans.dictservice.mgr.service;

import com.leadtrans.dictservice.common.repository.entity.DictCountryEntity;
import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.DictCountryPageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.DictCountryReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.DictCountryRespVO;

import java.util.List;

public interface DictCountryService {

    Long create(DictCountryReqVO VO);

    void delete(Long id);

    void update(Long id, DictCountryReqVO VO);

    DictCountryRespVO getById(Long id);

    PageResult<DictCountryRespVO> page(DictCountryPageReqVO reqVO);

    void updateValid(Long id, String isValid);

    List<DictCountryEntity> findByNumCodes(List<String> countryNumCodes);
}
