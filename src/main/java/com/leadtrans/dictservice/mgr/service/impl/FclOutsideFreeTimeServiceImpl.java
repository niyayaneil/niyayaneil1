package com.leadtrans.dictservice.mgr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leadtrans.dictservice.common.i18n.I18nAssert;
import com.leadtrans.dictservice.common.repository.entity.FclOutsideFreeTimeEntity;
import com.leadtrans.dictservice.common.repository.mapper.FclOutsideFreeTimeMapper;
import com.leadtrans.dictservice.common.utils.LambdaOrderByFactory;
import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.FclOutsideFreeTimePageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.FclOutsideFreeTimeReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.FclOutsideFreeTimeRespVO;
import com.leadtrans.dictservice.mgr.convert.FclOutsideFreeTimeConvert;
import com.leadtrans.dictservice.mgr.service.FclOutsideFreeTimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class FclOutsideFreeTimeServiceImpl implements FclOutsideFreeTimeService {

    private final FclOutsideFreeTimeMapper fclOutsideFreeTimeMapper;

    @Override
    public Long create(FclOutsideFreeTimeReqVO VO) {
        // 校验条件必填：IPI业务时route必填
        if ("IPI".equals(VO.getBusinessType()) && !StringUtils.hasText(VO.getRoute())) {
            I18nAssert.badRequest(VO.getRoute(), "fclOutsideFreeTimeReqVO.route.Required");
        }

        // 校验：如果freeDays有值，freeUnit必填
        if (VO.getFreeDays() != null && !StringUtils.hasText(VO.getFreeUnit())) {
            I18nAssert.badRequest(VO.getFreeUnit(), "fclOutsideFreeTimeReqVO.freeUnit.Required");
        }

        // 校验有效期范围
        if (VO.getValidFrom() != null && VO.getValidTo() != null 
                && VO.getValidFrom().after(VO.getValidTo())) {
            I18nAssert.badRequest(VO.getValidFrom(), "fclOutsideFreeTimeReqVO.validFromTo.Invalid");
        }

        FclOutsideFreeTimeEntity entity = FclOutsideFreeTimeConvert.INSTANCE.toEntity(VO);
        fclOutsideFreeTimeMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public void delete(Long id) {
        I18nAssert.badRequest(id, "fclOutsideFreeTimeReqVO.id.NotNull");
        I18nAssert.notFound(fclOutsideFreeTimeMapper.selectById(id), 
            "fclOutsideFreeTimeReqVO.id.NotFound", id.toString());
        fclOutsideFreeTimeMapper.deleteById(id);
    }

    @Override
    public void update(Long id, FclOutsideFreeTimeReqVO VO) {
        I18nAssert.badRequest(id, "fclOutsideFreeTimeReqVO.id.NotNull");
        I18nAssert.isTrue(VO.getIsValid() == 0 || VO.getIsValid() == 1, 
            "fclOutsideFreeTimeReqVO.isValid.Invalid");

        // 校验条件必填：IPI业务时route必填
        if ("IPI".equals(VO.getBusinessType()) && !StringUtils.hasText(VO.getRoute())) {
            I18nAssert.badRequest(VO.getRoute(), "fclOutsideFreeTimeReqVO.route.Required");
        }

        // 校验：如果freeDays有值，freeUnit必填
        if (VO.getFreeDays() != null && !StringUtils.hasText(VO.getFreeUnit())) {
            I18nAssert.badRequest(VO.getFreeUnit(), "fclOutsideFreeTimeReqVO.freeUnit.Required");
        }

        // 校验有效期范围
        if (VO.getValidFrom() != null && VO.getValidTo() != null 
                && VO.getValidFrom().after(VO.getValidTo())) {
            I18nAssert.badRequest(VO.getValidFrom(), "fclOutsideFreeTimeReqVO.validFromTo.Invalid");
        }

        FclOutsideFreeTimeEntity entity = fclOutsideFreeTimeMapper.selectById(id);
        I18nAssert.notFound(entity, "fclOutsideFreeTimeReqVO.id.NotFound", id.toString());

        FclOutsideFreeTimeConvert.INSTANCE.updateEntity(VO, entity);
        fclOutsideFreeTimeMapper.updateById(entity);
    }

    @Override
    public FclOutsideFreeTimeRespVO getById(Long id) {
        I18nAssert.badRequest(id, "fclOutsideFreeTimeReqVO.id.NotNull");

        FclOutsideFreeTimeEntity entity = fclOutsideFreeTimeMapper.selectById(id);
        I18nAssert.notFound(entity, "fclOutsideFreeTimeReqVO.id.NotFound", id.toString());

        return FclOutsideFreeTimeConvert.INSTANCE.toVO(entity);
    }

    @Override
    public PageResult<FclOutsideFreeTimeRespVO> page(FclOutsideFreeTimePageReqVO reqVO) {
        I18nAssert.isTrue(Objects.isNull(reqVO.getIsValid()) || 
            (reqVO.getIsValid() == 0 || reqVO.getIsValid() == 1), 
            "fclOutsideFreeTimeReqVO.isValid.Invalid");

        IPage page = new Page(reqVO.getPageNum(), reqVO.getPageSize());
        LambdaQueryWrapper<FclOutsideFreeTimeEntity> wrapper = new LambdaQueryWrapper<>();

        // 查询条件
        wrapper.eq(Objects.nonNull(reqVO.getIsValid()), 
            FclOutsideFreeTimeEntity::getIsValid, reqVO.getIsValid());
        wrapper.eq(StringUtils.hasText(reqVO.getCarrierCode()), 
            FclOutsideFreeTimeEntity::getCarrierCode, reqVO.getCarrierCode());
        wrapper.eq(StringUtils.hasText(reqVO.getTradeLane()), 
            FclOutsideFreeTimeEntity::getTradeLane, reqVO.getTradeLane());
        wrapper.eq(StringUtils.hasText(reqVO.getContractType()), 
            FclOutsideFreeTimeEntity::getContractType, reqVO.getContractType());
        wrapper.eq(StringUtils.hasText(reqVO.getContainerType()), 
            FclOutsideFreeTimeEntity::getContainerType, reqVO.getContainerType());
        wrapper.eq(StringUtils.hasText(reqVO.getBusinessType()), 
            FclOutsideFreeTimeEntity::getBusinessType, reqVO.getBusinessType());
        wrapper.eq(StringUtils.hasText(reqVO.getRoute()), 
            FclOutsideFreeTimeEntity::getRoute, reqVO.getRoute());
        wrapper.eq(StringUtils.hasText(reqVO.getPodType()), 
            FclOutsideFreeTimeEntity::getPodType, reqVO.getPodType());
        wrapper.eq(StringUtils.hasText(reqVO.getDdType()), 
            FclOutsideFreeTimeEntity::getDdType, reqVO.getDdType());

        // 有效期范围查询
        if (reqVO.getValidFrom() != null) {
            wrapper.ge(FclOutsideFreeTimeEntity::getValidFrom, reqVO.getValidFrom());
        }
        if (reqVO.getValidTo() != null) {
            wrapper.le(FclOutsideFreeTimeEntity::getValidTo, reqVO.getValidTo());
        }

        // 排序
        LambdaOrderByFactory.orderBy(wrapper, FclOutsideFreeTimeEntity.class, reqVO.getOrderBys());
        fclOutsideFreeTimeMapper.selectPage(page, wrapper);

        PageResult<FclOutsideFreeTimeRespVO> pageResult = new PageResult<>(
            page, 
            FclOutsideFreeTimeConvert.INSTANCE.toVOList(page.getRecords())
        );
        return pageResult;
    }

    @Override
    public void updateValid(Long id, Integer isValid) {
        I18nAssert.badRequest(id, "fclOutsideFreeTimeReqVO.id.NotNull");
        I18nAssert.isTrue(isValid == 0 || isValid == 1, 
            "fclOutsideFreeTimeReqVO.isValid.Invalid");

        FclOutsideFreeTimeEntity entity = fclOutsideFreeTimeMapper.selectById(id);
        I18nAssert.notFound(entity, "fclOutsideFreeTimeReqVO.id.NotFound", id.toString());

        entity.setIsValid(isValid);
        fclOutsideFreeTimeMapper.updateById(entity);
    }
}
