package com.leadtrans.dictservice.mgr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leadtrans.dictservice.common.enums.StatusEnum;
import com.leadtrans.dictservice.common.i18n.I18nAssert;
import com.leadtrans.dictservice.common.repository.entity.PortMappingEntity;
import com.leadtrans.dictservice.common.repository.mapper.PortMappingMapper;
import com.leadtrans.dictservice.common.utils.LambdaOrderByFactory;
import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.PortMappingPageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.PortMappingReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.PortMappingRespVO;
import com.leadtrans.dictservice.mgr.convert.PortMappingConvert;
import com.leadtrans.dictservice.mgr.service.PortMappingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class PortMappingServiceImpl implements PortMappingService {

    private final PortMappingMapper portMappingMapper;

    @Override
    public Long create(PortMappingReqVO VO) {
        Integer count = portMappingMapper.selectCount(new LambdaQueryWrapper<PortMappingEntity>().eq(PortMappingEntity::getPortCode, VO.getPortCode()).eq(PortMappingEntity::getMappingData, VO.getMappingData()));
        I18nAssert.exists(count, "portMappingReqVO.mappingData.Exists",VO.getMappingData());

        PortMappingEntity entity = PortMappingConvert.INSTANCE.toEntity(VO);
        portMappingMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public void delete(Long id) {
        I18nAssert.badRequest(id, "portMappingReqVO.id.NotNull");
        I18nAssert.notFound(portMappingMapper.selectById(id), "portMappingReqVO.id.NotFound",id.toString());
        portMappingMapper.deleteById(id);
    }

    @Override
    public void update(Long id, PortMappingReqVO VO) {
        I18nAssert.badRequest(id, "portMappingReqVO.id.NotNull");
        I18nAssert.isTrue(StatusEnum.getCodes().contains(VO.getIsValid()), "portMappingReqVO.isValid.Invalid");

        Integer count = portMappingMapper.selectCount(new LambdaQueryWrapper<PortMappingEntity>().eq(PortMappingEntity::getPortCode, VO.getPortCode()).eq(PortMappingEntity::getMappingData, VO.getMappingData()).ne(PortMappingEntity::getId,id));
        I18nAssert.exists(count, "portMappingReqVO.mappingData.Exists",VO.getMappingData());

        PortMappingEntity entity = portMappingMapper.selectById(id);

        I18nAssert.notFound(entity, "portMappingReqVO.id.NotFound",id.toString());

        PortMappingConvert.INSTANCE.updateEntity(VO,entity);

        portMappingMapper.updateById(entity);
    }

    @Override
    public PortMappingRespVO getById(Long id) {
        I18nAssert.badRequest(id, "portMappingReqVO.id.NotNull");

        PortMappingEntity entity = portMappingMapper.selectById(id);

        I18nAssert.notFound(entity, "portMappingReqVO.id.NotFound",id.toString());

        return PortMappingConvert.INSTANCE.toVO(entity);
    }


    @Override
    public PageResult<PortMappingRespVO> page(PortMappingPageReqVO reqVO) {
        I18nAssert.isTrue(Objects.isNull(reqVO.getIsValid()) || StatusEnum.getCodes().contains(reqVO.getIsValid()), "portMappingReqVO.isValid.Invalid");

        IPage page = new Page(reqVO.getPageNum(),reqVO.getPageSize());

        LambdaQueryWrapper wrapper = new LambdaQueryWrapper<PortMappingEntity>()
            .eq(StringUtils.hasText(reqVO.getIsValid()), PortMappingEntity::getIsValid,reqVO.getIsValid())
            .eq(StringUtils.hasText(reqVO.getCarrierCompanyCode()), PortMappingEntity::getCarrierCompanyCode,reqVO.getCarrierCompanyCode())
            .eq(StringUtils.hasText(reqVO.getPortCode()), PortMappingEntity::getPortCode,reqVO.getPortCode())
            .like(StringUtils.hasText(reqVO.getMappingData()), PortMappingEntity::getMappingData,reqVO.getMappingData())
            .eq(StringUtils.hasText(reqVO.getSource()), PortMappingEntity::getSource,reqVO.getSource());

        //排序
        LambdaOrderByFactory.orderBy(wrapper, PortMappingEntity.class,reqVO.getOrderBys());
        portMappingMapper.selectPage(page,wrapper);

        PageResult<PortMappingRespVO> pageResult = new PageResult(page,PortMappingConvert.INSTANCE.toVOList(page.getRecords()));
        return pageResult;
    }

    @Override
    public void updateValid(Long id, String isValid) {
        I18nAssert.badRequest(id, "portMappingReqVO.id.NotNull");
        I18nAssert.isTrue(StatusEnum.getCodes().contains(isValid), "portMappingReqVO.isValid.Invalid");

        PortMappingEntity entity = portMappingMapper.selectById(id);
        I18nAssert.notFound(entity, "portMappingReqVO.id.NotFound",id.toString());

        entity.setIsValid(isValid);
        portMappingMapper.updateById(entity);
    }
}
