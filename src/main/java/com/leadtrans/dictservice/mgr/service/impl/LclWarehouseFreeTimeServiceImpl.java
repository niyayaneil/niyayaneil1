package com.leadtrans.dictservice.mgr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leadtrans.dictservice.common.i18n.I18nAssert;
import com.leadtrans.dictservice.common.repository.entity.LclWarehouseFreeTimeEntity;
import com.leadtrans.dictservice.common.repository.mapper.LclWarehouseFreeTimeMapper;
import com.leadtrans.dictservice.common.utils.LambdaOrderByFactory;
import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.LclWarehouseFreeTimePageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.LclWarehouseFreeTimeReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.LclWarehouseFreeTimeRespVO;
import com.leadtrans.dictservice.mgr.convert.LclWarehouseFreeTimeConvert;
import com.leadtrans.dictservice.mgr.service.LclWarehouseFreeTimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class LclWarehouseFreeTimeServiceImpl implements LclWarehouseFreeTimeService {

    private final LclWarehouseFreeTimeMapper lclWarehouseFreeTimeMapper;

    @Override
    public Long create(LclWarehouseFreeTimeReqVO VO) {
        // 校验唯一性：warehouseCode + portCode 组合唯一
        Integer count = lclWarehouseFreeTimeMapper.selectCount(
            new LambdaQueryWrapper<LclWarehouseFreeTimeEntity>()
                .eq(LclWarehouseFreeTimeEntity::getWarehouseCode, VO.getWarehouseCode())
                .eq(LclWarehouseFreeTimeEntity::getPortCode, VO.getPortCode())
        );
        I18nAssert.exists(count, "lclWarehouseFreeTimeReqVO.warehousePort.Exists", 
            VO.getWarehouseCode() + " - " + VO.getPortCode());

        LclWarehouseFreeTimeEntity entity = LclWarehouseFreeTimeConvert.INSTANCE.toEntity(VO);
        lclWarehouseFreeTimeMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public void delete(Long id) {
        I18nAssert.badRequest(id, "lclWarehouseFreeTimeReqVO.id.NotNull");
        I18nAssert.notFound(lclWarehouseFreeTimeMapper.selectById(id), 
            "lclWarehouseFreeTimeReqVO.id.NotFound", id.toString());
        lclWarehouseFreeTimeMapper.deleteById(id);
    }

    @Override
    public void update(Long id, LclWarehouseFreeTimeReqVO VO) {
        I18nAssert.badRequest(id, "lclWarehouseFreeTimeReqVO.id.NotNull");
        I18nAssert.isTrue(VO.getIsValid() == 0 || VO.getIsValid() == 1, 
            "lclWarehouseFreeTimeReqVO.isValid.Invalid");

        // 校验唯一性（排除当前记录）
        Integer count = lclWarehouseFreeTimeMapper.selectCount(
            new LambdaQueryWrapper<LclWarehouseFreeTimeEntity>()
                .eq(LclWarehouseFreeTimeEntity::getWarehouseCode, VO.getWarehouseCode())
                .eq(LclWarehouseFreeTimeEntity::getPortCode, VO.getPortCode())
                .ne(LclWarehouseFreeTimeEntity::getId, id)
        );
        I18nAssert.exists(count, "lclWarehouseFreeTimeReqVO.warehousePort.Exists", 
            VO.getWarehouseCode() + " - " + VO.getPortCode());

        LclWarehouseFreeTimeEntity entity = lclWarehouseFreeTimeMapper.selectById(id);
        I18nAssert.notFound(entity, "lclWarehouseFreeTimeReqVO.id.NotFound", id.toString());

        LclWarehouseFreeTimeConvert.INSTANCE.updateEntity(VO, entity);
        lclWarehouseFreeTimeMapper.updateById(entity);
    }

    @Override
    public LclWarehouseFreeTimeRespVO getById(Long id) {
        I18nAssert.badRequest(id, "lclWarehouseFreeTimeReqVO.id.NotNull");

        LclWarehouseFreeTimeEntity entity = lclWarehouseFreeTimeMapper.selectById(id);
        I18nAssert.notFound(entity, "lclWarehouseFreeTimeReqVO.id.NotFound", id.toString());

        return LclWarehouseFreeTimeConvert.INSTANCE.toVO(entity);
    }

    @Override
    public PageResult<LclWarehouseFreeTimeRespVO> page(LclWarehouseFreeTimePageReqVO reqVO) {
        I18nAssert.isTrue(Objects.isNull(reqVO.getIsValid()) || 
            (reqVO.getIsValid() == 0 || reqVO.getIsValid() == 1), 
            "lclWarehouseFreeTimeReqVO.isValid.Invalid");

        IPage page = new Page(reqVO.getPageNum(), reqVO.getPageSize());
        LambdaQueryWrapper<LclWarehouseFreeTimeEntity> wrapper = new LambdaQueryWrapper<>();

        // 查询条件
        wrapper.eq(Objects.nonNull(reqVO.getIsValid()), 
            LclWarehouseFreeTimeEntity::getIsValid, reqVO.getIsValid());
        wrapper.eq(StringUtils.hasText(reqVO.getWarehouseCode()), 
            LclWarehouseFreeTimeEntity::getWarehouseCode, reqVO.getWarehouseCode());
        wrapper.eq(StringUtils.hasText(reqVO.getPortCode()), 
            LclWarehouseFreeTimeEntity::getPortCode, reqVO.getPortCode());
        wrapper.eq(StringUtils.hasText(reqVO.getServiceProvider()), 
            LclWarehouseFreeTimeEntity::getServiceProvider, reqVO.getServiceProvider());
        wrapper.eq(Objects.nonNull(reqVO.getFreeDays()), 
            LclWarehouseFreeTimeEntity::getFreeDays, reqVO.getFreeDays());
        wrapper.eq(StringUtils.hasText(reqVO.getFreeUnit()), 
            LclWarehouseFreeTimeEntity::getFreeUnit, reqVO.getFreeUnit());

        // 有效期范围查询
        if (reqVO.getValidFrom() != null) {
            wrapper.ge(LclWarehouseFreeTimeEntity::getValidFrom, reqVO.getValidFrom());
        }
        if (reqVO.getValidTo() != null) {
            wrapper.le(LclWarehouseFreeTimeEntity::getValidTo, reqVO.getValidTo());
        }

        // 排序
        LambdaOrderByFactory.orderBy(wrapper, LclWarehouseFreeTimeEntity.class, reqVO.getOrderBys());
        lclWarehouseFreeTimeMapper.selectPage(page, wrapper);

        PageResult<LclWarehouseFreeTimeRespVO> pageResult = new PageResult<>(
            page, 
            LclWarehouseFreeTimeConvert.INSTANCE.toVOList(page.getRecords())
        );
        return pageResult;
    }

    @Override
    public void updateValid(Long id, Integer isValid) {
        I18nAssert.badRequest(id, "lclWarehouseFreeTimeReqVO.id.NotNull");
        I18nAssert.isTrue(isValid == 0 || isValid == 1, 
            "lclWarehouseFreeTimeReqVO.isValid.Invalid");

        LclWarehouseFreeTimeEntity entity = lclWarehouseFreeTimeMapper.selectById(id);
        I18nAssert.notFound(entity, "lclWarehouseFreeTimeReqVO.id.NotFound", id.toString());

        entity.setIsValid(isValid);
        lclWarehouseFreeTimeMapper.updateById(entity);
    }
}