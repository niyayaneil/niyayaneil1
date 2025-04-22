package com.leadtrans.dictservice.mgr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leadtrans.dictservice.common.enums.StatusEnum;
import com.leadtrans.dictservice.common.i18n.I18nAssert;
import com.leadtrans.dictservice.common.repository.entity.DictCountryEntity;
import com.leadtrans.dictservice.common.repository.entity.DictStateEntity;
import com.leadtrans.dictservice.common.repository.mapper.DictStateMapper;
import com.leadtrans.dictservice.common.utils.LambdaOrderByFactory;
import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.DictStatePageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.DictStateReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.DictStateRespVO;
import com.leadtrans.dictservice.mgr.convert.DictStateConvert;
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
public class DictStateServiceImpl implements DictStateService {
    private final DictStateMapper dictStateMapper;
    private final DictCountryService dictCountryService;

    @Override
    public Long create(DictStateReqVO VO) {
        Integer count = dictStateMapper.selectCount(new LambdaQueryWrapper<DictStateEntity>().eq(DictStateEntity::getNumCode, VO.getNumCode()));
        I18nAssert.exists(count, "dictStateReqVO.numCode.Exists",VO.getNumCode());
        DictStateEntity entity = DictStateConvert.INSTANCE.toEntity(VO);
        dictStateMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public void delete(Long id) {
        I18nAssert.badRequest(id, "dictStateReqVO.id.NotNull");
        I18nAssert.notFound(dictStateMapper.selectById(id), "dictStateReqVO.id.NotFound",id.toString());
        dictStateMapper.deleteById(id);
    }

    @Override
    public void update(Long id, DictStateReqVO VO) {
        I18nAssert.badRequest(id, "dictStateReqVO.id.NotNull");
        I18nAssert.isTrue(StatusEnum.getCodes().contains(VO.getIsValid()), "dictStateReqVO.isValid.Invalid");

        DictStateEntity entity = dictStateMapper.selectById(id);
        I18nAssert.notFound(entity, "dictStateReqVO.id.NotFound",id.toString());

        DictStateConvert.INSTANCE.updateEntity(VO,entity);

        dictStateMapper.updateById(entity);
    }

    @Override
    public DictStateRespVO getById(Long id) {
        I18nAssert.badRequest(id, "dictStateReqVO.id.NotNull");

        DictStateEntity entity = dictStateMapper.selectById(id);
        I18nAssert.notFound(entity, "dictStateReqVO.id.NotFound",id.toString());
        DictStateRespVO dictStateRespVO = DictStateConvert.INSTANCE.toVO(entity);
        fillNames(List.of(dictStateRespVO));
        return dictStateRespVO;
    }


    @Override
    public PageResult<DictStateRespVO> page(DictStatePageReqVO reqVO) {
        I18nAssert.isTrue(Objects.isNull(reqVO.getIsValid()) || StatusEnum.getCodes().contains(reqVO.getIsValid()), "dictStateReqVO.isValid.Invalid");

        IPage page = new Page(reqVO.getPageNum(),reqVO.getPageSize());

        LambdaQueryWrapper wrapper = new LambdaQueryWrapper<DictStateEntity>()
            .eq(Objects.nonNull(reqVO.getIsValid()),DictStateEntity::getIsValid,reqVO.getIsValid())
            .eq(StringUtils.hasText(reqVO.getCountryNumCode()),DictStateEntity::getCountryNumCode,reqVO.getCountryNumCode());

        //排序
        LambdaOrderByFactory.orderBy(wrapper, DictStateEntity.class,reqVO.getOrderBys());

        dictStateMapper.selectPage(page,wrapper);
        List list = DictStateConvert.INSTANCE.toVOList(page.getRecords());
        fillNames(list);
        PageResult<DictStateRespVO> pageResult = new PageResult(page,list);
        return pageResult;
    }

    @Override
    public void updateValid(Long id, String isValid) {
        I18nAssert.badRequest(id, "dictStateReqVO.id.NotNull");
        I18nAssert.isTrue(StatusEnum.getCodes().contains(isValid), "dictStateReqVO.isValid.Invalid");

        DictStateEntity entity = dictStateMapper.selectById(id);
        I18nAssert.notFound(entity, "dictStateReqVO.id.NotFound",id.toString());

        entity.setIsValid(isValid);
        dictStateMapper.updateById(entity);
    }

    @Override
    public List<DictStateEntity> findByNumCodes(List<String> stateNumCodes){
        if(CollectionUtils.isEmpty(stateNumCodes)){
            return List.of();
        }
        List<DictStateEntity> dictStateEntities = dictStateMapper.selectList(new LambdaQueryWrapper<DictStateEntity>().in(DictStateEntity::getNumCode, stateNumCodes));
        return dictStateEntities;
    }


    private void fillNames(Collection<DictStateRespVO> stateRespVOS){
        if(CollectionUtils.isEmpty(stateRespVOS)){
            return;
        }
        List<String> countryNumCodes = stateRespVOS.stream().map(v -> v.getCountryNumCode()).distinct().collect(Collectors.toList());
        List<DictCountryEntity> dictCountryEntities = dictCountryService.findByNumCodes(countryNumCodes);
        Map<String, DictCountryEntity> countryEntityMap = dictCountryEntities.stream().collect(Collectors.toMap(v -> v.getNumCode(), v -> v, (v1, v2) -> v1));
        for (DictStateRespVO stateRespVO : stateRespVOS) {
            DictCountryEntity dictCountryEntity = countryEntityMap.get(stateRespVO.getCountryNumCode());
            if(Objects.nonNull(dictCountryEntity)){
                stateRespVO.setCountryNameCn(dictCountryEntity.getNameCn());
                stateRespVO.setCountryNameEn(dictCountryEntity.getNameEn());
            }
        }
    }

}
