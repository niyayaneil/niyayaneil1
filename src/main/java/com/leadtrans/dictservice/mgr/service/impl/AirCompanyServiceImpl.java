package com.leadtrans.dictservice.mgr.service.impl;

import com.alibaba.fastjson.JSONAware;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leadtrans.dictservice.common.enums.StatusEnum;
import com.leadtrans.dictservice.common.i18n.I18nAssert;
import com.leadtrans.dictservice.common.repository.entity.AirCompanyEntity;
import com.leadtrans.dictservice.common.repository.mapper.AirCompanyMapper;
import com.leadtrans.dictservice.common.utils.LambdaOrderByFactory;
import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.AirCompanyPageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.AirCompanyReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.AirCompanyRespVO;
import com.leadtrans.dictservice.mgr.convert.AirCompanyConvert;
import com.leadtrans.dictservice.mgr.service.AirCompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nonapi.io.github.classgraph.json.JSONUtils;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class AirCompanyServiceImpl implements AirCompanyService {

    private final AirCompanyMapper airCompanyMapper;

    @Override
    public Long create(AirCompanyReqVO VO) {
        Integer count = airCompanyMapper.selectCount(new LambdaQueryWrapper<AirCompanyEntity>().eq(AirCompanyEntity::getIataCode, VO.getIataCode()));
        I18nAssert.exists(count, "airCompanyReqVO.iataCode.Exists", VO.getIataCode());

        AirCompanyEntity entity = AirCompanyConvert.INSTANCE.toEntity(VO);
        airCompanyMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public void delete(Long id) {
        I18nAssert.badRequest(id, "airCompanyReqVO.id.NotNull");
        I18nAssert.notFound(airCompanyMapper.selectById(id), "airCompanyReqVO.id.NotFound", id.toString());
        airCompanyMapper.deleteById(id);
    }

    @Override
    public void update(Long id, AirCompanyReqVO VO) {
        I18nAssert.badRequest(id, "airCompanyReqVO.id.NotNull");
        I18nAssert.isTrue(VO.getIsValid() == 0 || VO.getIsValid() == 1, "airCompanyReqVO.isValid.Invalid");

        Integer count = airCompanyMapper.selectCount(new LambdaQueryWrapper<AirCompanyEntity>().eq(AirCompanyEntity::getIataCode, VO.getIataCode()).ne(AirCompanyEntity::getId, id));
        I18nAssert.exists(count, "airCompanyReqVO.iataCode.Exists", VO.getIataCode());

        AirCompanyEntity entity = airCompanyMapper.selectById(id);
        I18nAssert.notFound(entity, "airCompanyReqVO.id.NotFound", id.toString());

        AirCompanyConvert.INSTANCE.updateEntity(VO, entity);

        airCompanyMapper.updateById(entity);
    }

    @Override
    public AirCompanyRespVO getById(Long id) {
        I18nAssert.badRequest(id, "airCompanyReqVO.id.NotNull");

        AirCompanyEntity entity = airCompanyMapper.selectById(id);

        I18nAssert.notFound(entity, "airCompanyReqVO.id.NotFound", id.toString());

        return AirCompanyConvert.INSTANCE.toVO(entity);
    }


    @Override
    public PageResult<AirCompanyRespVO> page(AirCompanyPageReqVO reqVO) {
        I18nAssert.isTrue(Objects.isNull(reqVO.getIsValid()) || 
        (reqVO.getIsValid() == 0 || reqVO.getIsValid() == 1), "airCompanyReqVO.isValid.Invalid");

        IPage page = new Page(reqVO.getPageNum(), reqVO.getPageSize());
        LambdaQueryWrapper wrapper = new LambdaQueryWrapper<AirCompanyEntity>()
            .eq(Objects.nonNull(reqVO.getIsValid()), AirCompanyEntity::getIsValid, reqVO.getIsValid())
            .eq(StringUtils.hasText(reqVO.getIataCode()), AirCompanyEntity::getIataCode, reqVO.getIataCode())
            .eq(StringUtils.hasText(reqVO.getAirlineNameEn()), AirCompanyEntity::getAirlineNameEn, reqVO.getAirlineNameEn())
            .eq(StringUtils.hasText(reqVO.getAirlineNameCn()), AirCompanyEntity::getAirlineNameCn, reqVO.getAirlineNameCn())
            .eq(StringUtils.hasText(reqVO.getCargowiseCode()), AirCompanyEntity::getCargowiseCode, reqVO.getCargowiseCode())
            .eq(StringUtils.hasText(reqVO.getUnlocode()), AirCompanyEntity::getUnlocode, reqVO.getUnlocode());

        //排序
        LambdaOrderByFactory.orderBy(wrapper, AirCompanyEntity.class, reqVO.getOrderBys());
        airCompanyMapper.selectPage(page, wrapper);

        PageResult<AirCompanyRespVO> pageResult = new PageResult(page, AirCompanyConvert.INSTANCE.toVOList(page.getRecords()));
        return pageResult;
    }

    @Override
    public void updateValid(Long id, Integer isValid) {
        I18nAssert.badRequest(id, "airCompanyReqVO.id.NotNull");
        I18nAssert.isTrue(isValid == 0 || isValid == 1, "airCompanyReqVO.isValid.Invalid");

        AirCompanyEntity entity = airCompanyMapper.selectById(id);
        I18nAssert.notFound(entity, "airCompanyReqVO.id.NotFound", id.toString());

        entity.setIsValid(isValid);
        airCompanyMapper.updateById(entity);
    }
}