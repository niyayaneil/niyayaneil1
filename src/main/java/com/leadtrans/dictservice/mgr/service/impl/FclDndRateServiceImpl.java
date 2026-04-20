package com.leadtrans.dictservice.mgr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leadtrans.dictservice.common.i18n.I18nAssert;
import com.leadtrans.dictservice.common.repository.entity.FclDndRateEntity;
import com.leadtrans.dictservice.common.repository.mapper.FclDndRateMapper;
import com.leadtrans.dictservice.common.utils.LambdaOrderByFactory;
import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.FclDndRatePageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.FclDndRateReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.FclDndRateRespVO;
import com.leadtrans.dictservice.mgr.convert.FclDndRateConvert;
import com.leadtrans.dictservice.mgr.service.FclDndRateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class FclDndRateServiceImpl implements FclDndRateService {

    private final FclDndRateMapper fclDndRateMapper;

    @Override
    public Long create(FclDndRateReqVO VO) {
        // 校验有效期范围
        if (VO.getValidFrom() != null && VO.getValidTo() != null 
                && VO.getValidFrom().after(VO.getValidTo())) {
            I18nAssert.badRequest(VO.getValidFrom(), "fclDndRateReqVO.validFromTo.Invalid");
        }

        FclDndRateEntity entity = FclDndRateConvert.INSTANCE.toEntity(VO);
        fclDndRateMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public void delete(Long id) {
        I18nAssert.badRequest(id, "fclDndRateReqVO.id.NotNull");
        
        FclDndRateEntity entity = fclDndRateMapper.selectById(id);
        I18nAssert.notFound(entity, "fclDndRateReqVO.id.NotFound", id);
        
        fclDndRateMapper.deleteById(id);
    }

    @Override
    public void update(Long id, FclDndRateReqVO VO) {
        I18nAssert.badRequest(id, "fclDndRateReqVO.id.NotNull");
        I18nAssert.isTrue(VO.getIsValid() == 0 || VO.getIsValid() == 1, 
            "fclDndRateReqVO.isValid.Invalid");

        // 校验有效期范围
        if (VO.getValidFrom() != null && VO.getValidTo() != null 
                && VO.getValidFrom().after(VO.getValidTo())) {
            I18nAssert.badRequest(VO.getValidFrom(), "fclDndRateReqVO.validFromTo.Invalid");
        }

        FclDndRateEntity entity = fclDndRateMapper.selectById(id);
        I18nAssert.notFound(entity, "fclDndRateReqVO.id.NotFound", id);

        FclDndRateConvert.INSTANCE.updateEntity(VO, entity);
        fclDndRateMapper.updateById(entity);
    }

    @Override
    public FclDndRateRespVO getById(Long id) {
        I18nAssert.badRequest(id, "fclDndRateReqVO.id.NotNull");

        FclDndRateEntity entity = fclDndRateMapper.selectById(id);
        I18nAssert.notFound(entity, "fclDndRateReqVO.id.NotFound", id);

        return FclDndRateConvert.INSTANCE.toVO(entity);
    }

    @Override
    public PageResult<FclDndRateRespVO> page(FclDndRatePageReqVO reqVO) {
        I18nAssert.isTrue(Objects.isNull(reqVO.getIsValid()) || 
            (reqVO.getIsValid() == 0 || reqVO.getIsValid() == 1), 
            "fclDndRateReqVO.isValid.Invalid");

        IPage page = new Page(reqVO.getPageNum(), reqVO.getPageSize());
        LambdaQueryWrapper<FclDndRateEntity> wrapper = new LambdaQueryWrapper<>();

        // 查询条件
        wrapper.eq(Objects.nonNull(reqVO.getIsValid()), 
            FclDndRateEntity::getIsValid, reqVO.getIsValid());
        wrapper.eq(StringUtils.hasText(reqVO.getCarrierCode()), 
            FclDndRateEntity::getCarrierCode, reqVO.getCarrierCode());
        wrapper.eq(StringUtils.hasText(reqVO.getTradeLane()), 
            FclDndRateEntity::getTradeLane, reqVO.getTradeLane());
        wrapper.eq(StringUtils.hasText(reqVO.getChargeType()), 
            FclDndRateEntity::getChargeType, reqVO.getChargeType());
        wrapper.eq(StringUtils.hasText(reqVO.getBusinessType()), 
            FclDndRateEntity::getBusinessType, reqVO.getBusinessType());
        
        // portCode 模糊查询（因为可能是多个逗号分隔的值）
        if (StringUtils.hasText(reqVO.getPortCode())) {
            wrapper.like(FclDndRateEntity::getPortCode, reqVO.getPortCode());
        }
        
        // containerType 模糊查询（因为可能是多个逗号分隔的值）
        if (StringUtils.hasText(reqVO.getContainerType())) {
            wrapper.like(FclDndRateEntity::getContainerType, reqVO.getContainerType());
        }

        // 有效期范围查询
        if (reqVO.getValidFrom() != null) {
            wrapper.ge(FclDndRateEntity::getValidFrom, reqVO.getValidFrom());
        }
        if (reqVO.getValidTo() != null) {
            wrapper.le(FclDndRateEntity::getValidTo, reqVO.getValidTo());
        }

        // 排序
        LambdaOrderByFactory.orderBy(wrapper, FclDndRateEntity.class, reqVO.getOrderBys());
        fclDndRateMapper.selectPage(page, wrapper);

        PageResult<FclDndRateRespVO> pageResult = new PageResult<>(
            page, 
            FclDndRateConvert.INSTANCE.toVOList(page.getRecords())
        );
        return pageResult;
    }

    @Override
    public void updateValid(Long id, Integer isValid) {
        I18nAssert.badRequest(id, "fclDndRateReqVO.id.NotNull");
        I18nAssert.isTrue(isValid == 0 || isValid == 1, 
            "fclDndRateReqVO.isValid.Invalid");

        FclDndRateEntity entity = fclDndRateMapper.selectById(id);
        I18nAssert.notFound(entity, "fclDndRateReqVO.id.NotFound", id);

        entity.setIsValid(isValid);
        fclDndRateMapper.updateById(entity);
    }
}
