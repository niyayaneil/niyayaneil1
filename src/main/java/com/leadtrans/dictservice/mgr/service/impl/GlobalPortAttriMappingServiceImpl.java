package com.leadtrans.dictservice.mgr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leadtrans.dictservice.common.enums.StatusEnum;
import com.leadtrans.dictservice.common.i18n.I18nAssert;
import com.leadtrans.dictservice.common.repository.entity.GlobalPortAttriEntity;
import com.leadtrans.dictservice.common.repository.entity.GlobalPortAttriMappingEntity;
import com.leadtrans.dictservice.common.repository.mapper.GlobalPortAttriMappingMapper;
import com.leadtrans.dictservice.common.utils.LambdaOrderByFactory;
import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalPortAttriMappingPageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalPortAttriMappingReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalPortAttriMappingRespVO;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalPortRespVO;
import com.leadtrans.dictservice.mgr.convert.GlobalPortAttriMappingConvert;
import com.leadtrans.dictservice.mgr.service.GlobalPortAttriMappingService;
import com.leadtrans.dictservice.mgr.service.GlobalPortService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class GlobalPortAttriMappingServiceImpl implements GlobalPortAttriMappingService {
    private final GlobalPortAttriMappingMapper globalPortAttriMappingMapper;
    private final GlobalPortService globalPortService;

    @Override
    public Long create(GlobalPortAttriMappingReqVO VO) {
        I18nAssert.isTrue(StatusEnum.getCodes().contains(VO.getIsValid()), "globalPortAttriMappingReqVO.isValid.Invalid");
        I18nAssert.isTrue(StringUtils.hasText(VO.getGlobalPortCode()), "globalPortAttriMappingReqVO.globalPortCode.NotNull");
        I18nAssert.badRequest(VO.getGlobalPortAttriId(), "globalPortAttriMappingReqVO.globalPortAttriId.NotNull");

        GlobalPortRespVO globalPortRespVO = globalPortService.getByCode(VO.getGlobalPortCode());
        I18nAssert.notFound(globalPortRespVO, "globalPortReqVO.code.NotFound",VO.getGlobalPortCode());

        Integer count = globalPortAttriMappingMapper.selectCount(new LambdaQueryWrapper<GlobalPortAttriMappingEntity>().eq(GlobalPortAttriMappingEntity::getGlobalPortId, VO.getGlobalPortAttriId()).eq(GlobalPortAttriMappingEntity::getGlobalPortId, globalPortRespVO.getId()));
        I18nAssert.exists(count, "globalPortAttriMappingReqVO.globalPortAttriId.Exists",VO.getGlobalPortCode());
        GlobalPortAttriMappingEntity entity = GlobalPortAttriMappingConvert.INSTANCE.toEntity(VO);
        globalPortAttriMappingMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public void delete(Long id) {
        I18nAssert.badRequest(id, "globalPortAttriMappingReqVO.id.NotNull");
        I18nAssert.notFound(globalPortAttriMappingMapper.selectById(id), "globalPortAttriMappingReqVO.id.NotFound",id.toString());
        globalPortAttriMappingMapper.deleteById(id);
    }

    @Override
    public void update(Long id, GlobalPortAttriMappingReqVO VO) {
        I18nAssert.isTrue(StatusEnum.getCodes().contains(VO.getIsValid()), "globalPortAttriMappingReqVO.isValid.Invalid");
        I18nAssert.isTrue(StringUtils.hasText(VO.getGlobalPortCode()), "globalPortAttriMappingReqVO.globalPortCode.NotNull");
        I18nAssert.badRequest(VO.getGlobalPortAttriId(), "globalPortAttriMappingReqVO.globalPortAttriId.NotNull");

        GlobalPortRespVO globalPortRespVO = globalPortService.getByCode(VO.getGlobalPortCode());
        I18nAssert.notFound(globalPortRespVO, "globalPortReqVO.code.NotFound",VO.getGlobalPortCode());

        Integer count = globalPortAttriMappingMapper.selectCount(new LambdaQueryWrapper<GlobalPortAttriMappingEntity>().eq(GlobalPortAttriMappingEntity::getGlobalPortId, VO.getGlobalPortAttriId()).eq(GlobalPortAttriMappingEntity::getGlobalPortId, globalPortRespVO.getId()).ne(GlobalPortAttriMappingEntity::getId,id));
        I18nAssert.exists(count, "globalPortAttriMappingReqVO.globalPortAttriId.Exists",VO.getGlobalPortCode());

        GlobalPortAttriMappingEntity entity = globalPortAttriMappingMapper.selectById(id);
        I18nAssert.notFound(entity, "globalPortAttriMappingReqVO.id.NotFound",id.toString());

        GlobalPortAttriMappingConvert.INSTANCE.updateEntity(VO,entity);

        globalPortAttriMappingMapper.updateById(entity);
    }

    @Override
    public GlobalPortAttriMappingRespVO getById(Long id) {
        I18nAssert.badRequest(id, "globalPortAttriMappingReqVO.id.NotNull");

        GlobalPortAttriMappingEntity entity = globalPortAttriMappingMapper.selectById(id);
        I18nAssert.notFound(entity, "globalPortAttriMappingReqVO.id.NotFound",id.toString());
        GlobalPortAttriMappingRespVO globalPortAttriRespVO = GlobalPortAttriMappingConvert.INSTANCE.toVO(entity);
        return globalPortAttriRespVO;
    }


    @Override
    public PageResult<GlobalPortAttriMappingRespVO> page(GlobalPortAttriMappingPageReqVO reqVO) {
        I18nAssert.isTrue(Objects.isNull(reqVO.getIsValid()) || StatusEnum.getCodes().contains(reqVO.getIsValid()), "globalPortAttriMappingReqVO.isValid.Invalid");
        GlobalPortRespVO globalPortRespVO = globalPortService.getByCode(reqVO.getGlobalPortCode());

        IPage page = new Page(reqVO.getPageNum(),reqVO.getPageSize());

        LambdaQueryWrapper<GlobalPortAttriMappingEntity> wrapper = new LambdaQueryWrapper<GlobalPortAttriMappingEntity>()
            .eq(Objects.nonNull(reqVO.getIsValid()),GlobalPortAttriMappingEntity::getIsValid,reqVO.getIsValid())
            .eq(Objects.nonNull(reqVO.getPortAttriId()),GlobalPortAttriMappingEntity::getGlobalPortAttriId,reqVO.getPortAttriId());

        if(Objects.nonNull(globalPortRespVO)){
            wrapper.eq(Objects.nonNull(globalPortRespVO),GlobalPortAttriMappingEntity::getGlobalPortId,globalPortRespVO.getId());
        }

        //排序
        LambdaOrderByFactory.orderBy(wrapper, GlobalPortAttriMappingEntity.class,reqVO.getOrderBys());

        globalPortAttriMappingMapper.selectPage(page,wrapper);
        List list = GlobalPortAttriMappingConvert.INSTANCE.toVOList(page.getRecords());
        PageResult<GlobalPortAttriMappingRespVO> pageResult = new PageResult(page,list);
        return pageResult;
    }

    @Override
    public void updateValid(Long id, String isValid) {
        I18nAssert.badRequest(id, "globalPortAttriMappingReqVO.id.NotNull");
        I18nAssert.isTrue(StatusEnum.getCodes().contains(isValid), "globalPortAttriMappingReqVO.isValid.Invalid");

        GlobalPortAttriMappingEntity entity = globalPortAttriMappingMapper.selectById(id);
        I18nAssert.notFound(entity, "globalPortAttriMappingReqVO.id.NotFound",id.toString());

        entity.setIsValid(isValid);
        globalPortAttriMappingMapper.updateById(entity);
    }


}
