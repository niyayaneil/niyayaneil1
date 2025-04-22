package com.leadtrans.dictservice.mgr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leadtrans.dictservice.common.enums.StatusEnum;
import com.leadtrans.dictservice.common.i18n.I18nAssert;
import com.leadtrans.dictservice.common.repository.entity.DictCityEntity;
import com.leadtrans.dictservice.common.repository.entity.DictCountryEntity;
import com.leadtrans.dictservice.common.repository.entity.DictStateEntity;
import com.leadtrans.dictservice.common.repository.mapper.DictCityMapper;
import com.leadtrans.dictservice.common.utils.LambdaOrderByFactory;
import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.DictCityPageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.DictCityReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.DictCityRespVO;
import com.leadtrans.dictservice.mgr.convert.DictCityConvert;
import com.leadtrans.dictservice.mgr.service.DictCityService;
import com.leadtrans.dictservice.mgr.service.DictCountryService;
import com.leadtrans.dictservice.mgr.service.DictStateService;
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
public class DictCityServiceImpl implements DictCityService {
    private final DictCityMapper dictCityMapper;
    private final DictCountryService dictCountryService;
    private final DictStateService dictStateService;

    @Override
    public Long create(DictCityReqVO VO) {
        Integer count = dictCityMapper.selectCount(new LambdaQueryWrapper<DictCityEntity>().eq(DictCityEntity::getNumCode, VO.getNumCode()));
        I18nAssert.exists(count, "dictCityReqVO.numCode.Exists",VO.getNumCode());
        DictCityEntity entity = DictCityConvert.INSTANCE.toEntity(VO);
        dictCityMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public void delete(Long id) {
        I18nAssert.badRequest(id, "dictCityReqVO.id.NotNull");
        I18nAssert.notFound(dictCityMapper.selectById(id), "dictCityReqVO.id.NotFound",id.toString());

        dictCityMapper.deleteById(id);
    }

    @Override
    public void update(Long id, DictCityReqVO VO) {
        I18nAssert.badRequest(id, "dictCityReqVO.id.NotNull");
        I18nAssert.isTrue(StatusEnum.getCodes().contains(VO.getIsValid()), "dictCityReqVO.isValid.Invalid");

        DictCityEntity entity = dictCityMapper.selectById(id);

        I18nAssert.notFound(entity, "dictCityReqVO.id.NotFound",id.toString());

        DictCityConvert.INSTANCE.updateEntity(VO,entity);

        dictCityMapper.updateById(entity);
    }

    @Override
    public DictCityRespVO getById(Long id) {
        I18nAssert.badRequest(id, "dictCityReqVO.id.NotNull");

        DictCityEntity entity = dictCityMapper.selectById(id);
        I18nAssert.notFound(entity, "dictCityReqVO.id.NotFound",id.toString());

        DictCityRespVO cityRespVO = DictCityConvert.INSTANCE.toVO(entity);
        fillNames(List.of(cityRespVO));

        return cityRespVO;
    }


    @Override
    public PageResult<DictCityRespVO> page(DictCityPageReqVO reqVO) {
        I18nAssert.isTrue(Objects.isNull(reqVO.getIsValid()) || StatusEnum.getCodes().contains(reqVO.getIsValid()), "dictCityReqVO.isValid.Invalid");

        IPage page = new Page(reqVO.getPageNum(),reqVO.getPageSize());

        LambdaQueryWrapper wrapper = new LambdaQueryWrapper<DictCityEntity>()
            .eq(Objects.nonNull(reqVO.getIsValid()),DictCityEntity::getIsValid,reqVO.getIsValid())
            .eq(StringUtils.hasText(reqVO.getCountryNumCode()),DictCityEntity::getCountryNumCode,reqVO.getCountryNumCode())
            .eq(StringUtils.hasText(reqVO.getStateNumCode()),DictCityEntity::getStateNumCode,reqVO.getStateNumCode());

        //排序
        LambdaOrderByFactory.orderBy(wrapper, DictCityEntity.class,reqVO.getOrderBys());

        dictCityMapper.selectPage(page,wrapper);
        List list = DictCityConvert.INSTANCE.toVOList(page.getRecords());
        fillNames(list);
        PageResult<DictCityRespVO> pageResult = new PageResult(page,list);
        return pageResult;
    }

    @Override
    public void updateValid(Long id, String isValid) {
        I18nAssert.badRequest(id, "dictCityReqVO.id.NotNull");
        I18nAssert.isTrue(StatusEnum.getCodes().contains(isValid), "dictCityReqVO.isValid.Invalid");

        DictCityEntity entity = dictCityMapper.selectById(id);
        I18nAssert.notFound(entity, "dictCityReqVO.id.NotFound",id.toString());

        entity.setIsValid(isValid);
        dictCityMapper.updateById(entity);
    }


    private void fillNames(Collection<DictCityRespVO> cityRespVOS){
        if(CollectionUtils.isEmpty(cityRespVOS)){
            return;
        }
        List<String> countryNumCodes = cityRespVOS.stream().map(v -> v.getCountryNumCode()).distinct().collect(Collectors.toList());
        List<DictCountryEntity> dictCountryEntities = dictCountryService.findByNumCodes(countryNumCodes);
        Map<String, DictCountryEntity> countryEntityMap = dictCountryEntities.stream().collect(Collectors.toMap(v -> v.getNumCode(), v -> v, (v1, v2) -> v1));

        List<String> stateNumCodes = cityRespVOS.stream().map(v -> v.getStateNumCode()).distinct().collect(Collectors.toList());
        List<DictStateEntity> dictStateEntities = dictStateService.findByNumCodes(stateNumCodes);
        Map<String, DictStateEntity> stateEntityMap = dictStateEntities.stream().collect(Collectors.toMap(v -> v.getNumCode(), v -> v, (v1, v2) -> v1));

        for (DictCityRespVO cityRespVO : cityRespVOS) {
            DictCountryEntity dictCountryEntity = countryEntityMap.get(cityRespVO.getCountryNumCode());
            if(Objects.nonNull(dictCountryEntity)){
                cityRespVO.setCountryNameCn(dictCountryEntity.getNameCn());
                cityRespVO.setCountryNameEn(dictCountryEntity.getNameEn());
            }

            DictStateEntity  dictStateEntity = stateEntityMap.get(cityRespVO.getStateNumCode());
            if(Objects.nonNull(dictStateEntity)){
                cityRespVO.setStateNameCn(dictStateEntity.getNameCn());
                cityRespVO.setStateNameEn(dictStateEntity.getNameEn());
            }
        }
    }
}
