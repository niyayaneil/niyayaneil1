package com.leadtrans.dictservice.mgr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leadtrans.dictservice.common.enums.StatusEnum;
import com.leadtrans.dictservice.common.i18n.I18nAssert;
import com.leadtrans.dictservice.common.repository.entity.*;
import com.leadtrans.dictservice.common.repository.mapper.GlobalPortMapper;
import com.leadtrans.dictservice.common.utils.LambdaOrderByFactory;
import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalPortPageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalPortReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalPortRespVO;
import com.leadtrans.dictservice.mgr.convert.GlobalPortConvert;
import com.leadtrans.dictservice.mgr.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class GlobalPortServiceImpl implements GlobalPortService {
    private final GlobalPortMapper globalPortMapper;
    private final DictCountryService dictCountryService;
    private final DictStateService dictStateService;
    private final DictCityService dictCityService;
    private final GlobalAreaService globalAreaService;




    @Override
    public Long create(GlobalPortReqVO VO) {
        Integer count = globalPortMapper.selectCount(new LambdaQueryWrapper<GlobalPortEntity>().eq(GlobalPortEntity::getCode, VO.getCode()));
        I18nAssert.exists(count, "globalPortReqVO.code.Exists",VO.getCode());
        GlobalPortEntity entity = GlobalPortConvert.INSTANCE.toEntity(VO);
        globalPortMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public void delete(Long id) {
        I18nAssert.badRequest(id, "globalPortReqVO.id.NotNull");
        I18nAssert.notFound(globalPortMapper.selectById(id), "globalPortReqVO.id.NotFound",id.toString());
        globalPortMapper.deleteById(id);
    }

    @Override
    public void update(Long id, GlobalPortReqVO VO) {
        I18nAssert.badRequest(id, "globalPortReqVO.id.NotNull");
        I18nAssert.isTrue(StatusEnum.getCodes().contains(VO.getIsValid()), "globalPortReqVO.isValid.Invalid");

        Integer count = globalPortMapper.selectCount(new LambdaQueryWrapper<GlobalPortEntity>().eq(GlobalPortEntity::getCode, VO.getCode()).ne(GlobalPortEntity::getId,id));
        I18nAssert.exists(count, "globalPortReqVO.code.Exists",VO.getCode());

        GlobalPortEntity entity = globalPortMapper.selectById(id);
        I18nAssert.notFound(entity, "globalPortReqVO.id.NotFound",id.toString());

        GlobalPortConvert.INSTANCE.updateEntity(VO,entity);

        globalPortMapper.updateById(entity);
    }

    @Override
    public GlobalPortRespVO getById(Long id) {
        I18nAssert.badRequest(id, "globalPortReqVO.id.NotNull");

        GlobalPortEntity entity = globalPortMapper.selectById(id);
        I18nAssert.notFound(entity, "globalPortReqVO.id.NotFound",id.toString());
        GlobalPortRespVO GlobalPortRespVO = GlobalPortConvert.INSTANCE.toVO(entity);
        fillNames(List.of(GlobalPortRespVO));
        return GlobalPortRespVO;
    }

    @Override
    public GlobalPortRespVO getByCode(String code) {
        if(!StringUtils.hasText(code)){
            return null;
        }

        List<GlobalPortEntity> entities = globalPortMapper.selectList(new LambdaQueryWrapper<GlobalPortEntity>().eq(GlobalPortEntity::getCode,code));
        I18nAssert.notFound(entities, "globalPortReqVO.code.NotFound",code);

        List<GlobalPortRespVO> globalPortRespVOS = GlobalPortConvert.INSTANCE.toVOList(entities);
        fillNames(globalPortRespVOS);
        return globalPortRespVOS.get(0);
    }

    @Override
    public PageResult<GlobalPortRespVO> page(GlobalPortPageReqVO reqVO) {
        I18nAssert.isTrue(Objects.isNull(reqVO.getIsValid()) || StatusEnum.getCodes().contains(reqVO.getIsValid()), "globalPortReqVO.isValid.Invalid");

        IPage page = new Page(reqVO.getPageNum(),reqVO.getPageSize());

        LambdaQueryWrapper wrapper = new LambdaQueryWrapper<GlobalPortEntity>()
            .eq(Objects.nonNull(reqVO.getIsValid()),GlobalPortEntity::getIsValid,reqVO.getIsValid())
            .eq(StringUtils.hasText(reqVO.getCountryCode()),GlobalPortEntity::getCountryCode,reqVO.getCountryCode())
            .eq(StringUtils.hasText(reqVO.getStateCode()),GlobalPortEntity::getStateCode,reqVO.getStateCode())
            .eq(StringUtils.hasText(reqVO.getCityCode()),GlobalPortEntity::getCityCode,reqVO.getCityCode())
            .eq(StringUtils.hasText(reqVO.getGlobalAreaId()),GlobalPortEntity::getGlobalAreaId,reqVO.getGlobalAreaId())
            .eq(StringUtils.hasText(reqVO.getCode()),GlobalPortEntity::getCode,reqVO.getCode());

        //排序
        LambdaOrderByFactory.orderBy(wrapper, GlobalPortEntity.class,reqVO.getOrderBys());

        globalPortMapper.selectPage(page,wrapper);
        List list = GlobalPortConvert.INSTANCE.toVOList(page.getRecords());
        fillNames(list);
        PageResult<GlobalPortRespVO> pageResult = new PageResult(page,list);
        return pageResult;
    }

    @Override
    public void updateValid(Long id, String isValid) {
        I18nAssert.badRequest(id, "globalPortReqVO.id.NotNull");
        I18nAssert.isTrue(StatusEnum.getCodes().contains(isValid), "globalPortReqVO.isValid.Invalid");

        GlobalPortEntity entity = globalPortMapper.selectById(id);
        I18nAssert.notFound(entity, "globalPortReqVO.id.NotFound",id.toString());

        entity.setIsValid(isValid);
        globalPortMapper.updateById(entity);
    }

    @Override
    public List<GlobalPortEntity> selectByIds(Collection<Long> globalPortIds){
        if(CollectionUtils.isEmpty(globalPortIds)){
            return List.of();
        }
        List<GlobalPortEntity> globalPortEntities = globalPortMapper.selectBatchIds(globalPortIds);
        return globalPortEntities;
    }

    @Override
    public Map<String,GlobalPortRespVO> selectMapByCodes(Collection<String> globalPortCodes){
        if(CollectionUtils.isEmpty(globalPortCodes)){
            return Map.of();
        }
        List<GlobalPortEntity> globalPortEntities = globalPortMapper.selectList(new LambdaQueryWrapper<GlobalPortEntity>().in(GlobalPortEntity::getCode,globalPortCodes));
        if(CollectionUtils.isEmpty(globalPortEntities)){
            return Map.of();
        }
        return globalPortEntities.stream().collect(Collectors.toMap(v->v.getCode(),v->GlobalPortConvert.INSTANCE.toVO(v),(v1,v2)->v1));
    }


    private void fillNames(Collection<GlobalPortRespVO> globalPortRespVOS){
        if(CollectionUtils.isEmpty(globalPortRespVOS)){
            return;
        }
        List<String> countryCodes = globalPortRespVOS.stream().map(v -> v.getCountryCode()).distinct().collect(Collectors.toList());
        List<DictCountryEntity> dictCountryEntities = dictCountryService.findByNumCodes(countryCodes);
        Map<String, DictCountryEntity> countryEntityMap = dictCountryEntities.stream().collect(Collectors.toMap(v -> v.getNumCode(), v -> v, (v1, v2) -> v1));

        List<String> stateCodes = globalPortRespVOS.stream().map(v -> v.getStateCode()).distinct().collect(Collectors.toList());
        List<DictStateEntity> dictStateEntities = dictStateService.findByNumCodes(stateCodes);
        Map<String, DictStateEntity> stateEntityMap = dictStateEntities.stream().collect(Collectors.toMap(v -> v.getNumCode(), v -> v, (v1, v2) -> v1));

        List<String> cityCodes = globalPortRespVOS.stream().map(v -> v.getCityCode()).distinct().collect(Collectors.toList());
        List<DictCityEntity> dictCityEntities = dictCityService.findByNumCodes(cityCodes);
        Map<String, DictCityEntity> cityEntityMap = dictCityEntities.stream().collect(Collectors.toMap(v -> v.getNumCode(), v -> v, (v1, v2) -> v1));

        List<Long> globalAreaIds = globalPortRespVOS.stream().map(v -> v.getGlobalAreaId()).distinct().collect(Collectors.toList());
        List<GlobalAreaEntity> globalAreaEntities = globalAreaService.findByIds(globalAreaIds);
        Map<Long, GlobalAreaEntity> globalEntityMap = globalAreaEntities.stream().collect(Collectors.toMap(v -> v.getId(), v -> v, (v1, v2) -> v1));

        for (GlobalPortRespVO globalPortRespVO : globalPortRespVOS) {
            DictCountryEntity dictCountryEntity = countryEntityMap.get(globalPortRespVO.getCountryCode());
            if(Objects.nonNull(dictCountryEntity)){
                globalPortRespVO.setCountryNameCn(dictCountryEntity.getNameCn());
                globalPortRespVO.setCountryNameEn(dictCountryEntity.getNameEn());
            }

            DictStateEntity dictStateEntity = stateEntityMap.get(globalPortRespVO.getStateCode());
            if(Objects.nonNull(dictStateEntity)){
                globalPortRespVO.setStateNameCn(dictStateEntity.getNameCn());
                globalPortRespVO.setStateNameEn(dictStateEntity.getNameEn());
            }

            DictCityEntity dictCityEntity = cityEntityMap.get(globalPortRespVO.getCityCode());
            if(Objects.nonNull(dictCityEntity)){
                globalPortRespVO.setCityNameCn(dictCityEntity.getNameCn());
                globalPortRespVO.setCityNameEn(dictCityEntity.getNameEn());
            }

            GlobalAreaEntity dictAreaEntity = globalEntityMap.get(globalPortRespVO.getGlobalAreaId());
            if(Objects.nonNull(dictAreaEntity)){
                globalPortRespVO.setGlobalAreaCode(dictAreaEntity.getCode());
                globalPortRespVO.setGlobalAreaName(dictAreaEntity.getName());
            }

        }

    }

}
