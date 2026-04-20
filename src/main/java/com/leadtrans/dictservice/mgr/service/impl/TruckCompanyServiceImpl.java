package com.leadtrans.dictservice.mgr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leadtrans.dictservice.common.enums.StatusEnum;
import com.leadtrans.dictservice.common.i18n.I18nAssert;
import com.leadtrans.dictservice.common.repository.entity.TruckCompanyEntity;
import com.leadtrans.dictservice.common.repository.mapper.TruckCompanyMapper;
import com.leadtrans.dictservice.common.utils.LambdaOrderByFactory;
import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.TruckCompanyPageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.TruckCompanyReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.TruckCompanyRespVO;
import com.leadtrans.dictservice.mgr.convert.TruckCompanyConvert;
import com.leadtrans.dictservice.mgr.service.TruckCompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class TruckCompanyServiceImpl implements TruckCompanyService {

    private final TruckCompanyMapper truckCompanyMapper;

    @Override
    public Long create(TruckCompanyReqVO VO) {
        Integer count = truckCompanyMapper.selectCount(new LambdaQueryWrapper<TruckCompanyEntity>().eq(TruckCompanyEntity::getTruckCode, VO.getTruckCode()));
        I18nAssert.exists(count, "truckCompanyReqVO.truckCode.Exists", VO.getTruckCode());

        TruckCompanyEntity entity = TruckCompanyConvert.INSTANCE.toEntity(VO);
        truckCompanyMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public void delete(Long id) {
        I18nAssert.badRequest(id, "truckCompanyReqVO.id.NotNull");
        I18nAssert.notFound(truckCompanyMapper.selectById(id), "truckCompanyReqVO.id.NotFound", id.toString());
        truckCompanyMapper.deleteById(id);
    }

    @Override
    public void update(Long id, TruckCompanyReqVO VO) {
        I18nAssert.badRequest(id, "truckCompanyReqVO.id.NotNull");
        I18nAssert.isTrue(VO.getIsValid() == 0 || VO.getIsValid() == 1, "truckCompanyReqVO.isValid.Invalid");

        Integer count = truckCompanyMapper.selectCount(new LambdaQueryWrapper<TruckCompanyEntity>().eq(TruckCompanyEntity::getTruckCode, VO.getTruckCode()).ne(TruckCompanyEntity::getId, id));
        I18nAssert.exists(count, "truckCompanyReqVO.truckCode.Exists", VO.getTruckCode());

        TruckCompanyEntity entity = truckCompanyMapper.selectById(id);
        I18nAssert.notFound(entity, "truckCompanyReqVO.id.NotFound", id.toString());

        TruckCompanyConvert.INSTANCE.updateEntity(VO, entity);

        truckCompanyMapper.updateById(entity);
    }

    @Override
    public TruckCompanyRespVO getById(Long id) {
        I18nAssert.badRequest(id, "truckCompanyReqVO.id.NotNull");

        TruckCompanyEntity entity = truckCompanyMapper.selectById(id);

        I18nAssert.notFound(entity, "truckCompanyReqVO.id.NotFound", id.toString());

        return TruckCompanyConvert.INSTANCE.toVO(entity);
    }

    @Override
    public PageResult<TruckCompanyRespVO> page(TruckCompanyPageReqVO reqVO) {
        I18nAssert.isTrue(Objects.isNull(reqVO.getIsValid()) || 
        (reqVO.getIsValid() == 0 || reqVO.getIsValid() == 1), "truckCompanyReqVO.isValid.Invalid");

        IPage page = new Page(reqVO.getPageNum(), reqVO.getPageSize());
        LambdaQueryWrapper wrapper = new LambdaQueryWrapper<TruckCompanyEntity>()
            .eq(Objects.nonNull(reqVO.getIsValid()), TruckCompanyEntity::getIsValid, reqVO.getIsValid())
            .like(StringUtils.hasText(reqVO.getTruckCode()), TruckCompanyEntity::getTruckCode, reqVO.getTruckCode())
            .like(StringUtils.hasText(reqVO.getTruckNameEn()), TruckCompanyEntity::getTruckNameEn, reqVO.getTruckNameEn())
            .like(StringUtils.hasText(reqVO.getTruckNameCn()), TruckCompanyEntity::getTruckNameCn, reqVO.getTruckNameCn())
            .like(StringUtils.hasText(reqVO.getUnlocode()), TruckCompanyEntity::getUnlocode, reqVO.getUnlocode());

        //排序
        LambdaOrderByFactory.orderBy(wrapper, TruckCompanyEntity.class, reqVO.getOrderBys());
        truckCompanyMapper.selectPage(page, wrapper);

        PageResult<TruckCompanyRespVO> pageResult = new PageResult(page, TruckCompanyConvert.INSTANCE.toVOList(page.getRecords()));
        return pageResult;
    }

    @Override
    public void updateValid(Long id, Integer isValid) {
        I18nAssert.badRequest(id, "truckCompanyReqVO.id.NotNull");
        I18nAssert.isTrue(isValid == 0 || isValid == 1, "truckCompanyReqVO.isValid.Invalid");

        TruckCompanyEntity entity = truckCompanyMapper.selectById(id);
        I18nAssert.notFound(entity, "truckCompanyReqVO.id.NotFound", id.toString());

        entity.setIsValid(isValid);
        truckCompanyMapper.updateById(entity);
    }
}