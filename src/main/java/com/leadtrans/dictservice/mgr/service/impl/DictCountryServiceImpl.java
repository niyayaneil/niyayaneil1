package com.leadtrans.dictservice.mgr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leadtrans.dictservice.common.enums.StatusEnum;
import com.leadtrans.dictservice.common.i18n.I18nAssert;
import com.leadtrans.dictservice.common.repository.entity.DictCountryEntity;
import com.leadtrans.dictservice.common.repository.mapper.DictCountryMapper;
import com.leadtrans.dictservice.common.utils.LambdaOrderByFactory;
import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.DictCountryPageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.DictCountryReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.DictCountryRespVO;
import com.leadtrans.dictservice.mgr.convert.DictCountryConvert;
import com.leadtrans.dictservice.mgr.service.DictCountryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class DictCountryServiceImpl implements DictCountryService {

    private final DictCountryMapper dictCountryMapper;

    @Override
    public Long create(DictCountryReqVO VO) {
        Integer count = dictCountryMapper.selectCount(new LambdaQueryWrapper<DictCountryEntity>().eq(DictCountryEntity::getCode, VO.getCode()));
        I18nAssert.exists(count, "dictCountryReqVO.code.Exists",VO.getCode());

        Integer count1 = dictCountryMapper.selectCount(new LambdaQueryWrapper<DictCountryEntity>().eq(DictCountryEntity::getNumCode, VO.getNumCode()));
        I18nAssert.exists(count1, "dictCountryReqVO.numCode.Exists",VO.getNumCode());

        DictCountryEntity entity = DictCountryConvert.INSTANCE.toEntity(VO);
        dictCountryMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public void delete(Long id) {
        I18nAssert.badRequest(id, "dictCountryReqVO.id.NotNull");
        I18nAssert.notFound(dictCountryMapper.selectById(id), "dictCountryReqVO.id.NotFound",id.toString());

        dictCountryMapper.deleteById(id);
    }

    @Override
    public void update(Long id, DictCountryReqVO VO) {
        I18nAssert.badRequest(id, "dictCountryReqVO.id.NotNull");
        I18nAssert.isTrue(StatusEnum.getCodes().contains(VO.getIsValid()), "dictCountryReqVO.isValid.Invalid");

        Integer count = dictCountryMapper.selectCount(new LambdaQueryWrapper<DictCountryEntity>().eq(DictCountryEntity::getCode, VO.getCode()).ne(DictCountryEntity::getId,id));
        I18nAssert.exists(count, "dictCountryReqVO.code.Exists",VO.getCode());

        Integer count1 = dictCountryMapper.selectCount(new LambdaQueryWrapper<DictCountryEntity>().eq(DictCountryEntity::getNumCode, VO.getNumCode()).ne(DictCountryEntity::getId,id));
        I18nAssert.exists(count1, "dictCountryReqVO.numCode.Exists",VO.getNumCode());

        DictCountryEntity entity = dictCountryMapper.selectById(id);
        I18nAssert.notFound(entity, "dictCountryReqVO.id.NotFound",id.toString());

        DictCountryConvert.INSTANCE.updateEntity(VO,entity);

        dictCountryMapper.updateById(entity);
    }

    @Override
    public DictCountryRespVO getById(Long id) {
        I18nAssert.badRequest(id, "dictCountryReqVO.id.NotNull");

        DictCountryEntity entity = dictCountryMapper.selectById(id);
        I18nAssert.notFound(entity, "dictCountryReqVO.id.NotFound",id.toString());

        return DictCountryConvert.INSTANCE.toVO(entity);
    }


    @Override
    public PageResult<DictCountryRespVO> page(DictCountryPageReqVO reqVO) {
        I18nAssert.isTrue(Objects.isNull(reqVO.getIsValid()) || StatusEnum.getCodes().contains(reqVO.getIsValid()), "dictCountryReqVO.isValid.Invalid");

        IPage page = new Page(reqVO.getPageNum(),reqVO.getPageSize());

        LambdaQueryWrapper wrapper = new LambdaQueryWrapper<DictCountryEntity>()
                .eq(Objects.nonNull(reqVO.getIsValid()),DictCountryEntity::getIsValid,reqVO.getIsValid());

        //排序
        LambdaOrderByFactory.orderBy(wrapper,DictCountryEntity.class,reqVO.getOrderBys());

        dictCountryMapper.selectPage(page,wrapper);

        PageResult<DictCountryRespVO> pageResult = new PageResult(page,DictCountryConvert.INSTANCE.toVOList(page.getRecords()));
        return pageResult;
    }

    @Override
    public void updateValid(Long id, String isValid) {
        I18nAssert.badRequest(id, "dictCountryReqVO.id.NotNull");
        I18nAssert.isTrue(StatusEnum.getCodes().contains(isValid), "dictCountryReqVO.isValid.Invalid");

        DictCountryEntity entity = dictCountryMapper.selectById(id);
        I18nAssert.notFound(entity, "dictCountryReqVO.id.NotFound",id.toString());

        entity.setIsValid(isValid);
        dictCountryMapper.updateById(entity);
    }

    @Override
    public List<DictCountryEntity> findByNumCodes(List<String> countryNumCodes){
        if(CollectionUtils.isEmpty(countryNumCodes)){
            return List.of();
        }
        List<DictCountryEntity> dictCountryEntities = dictCountryMapper.selectList(new LambdaQueryWrapper<DictCountryEntity>().in(DictCountryEntity::getNumCode, countryNumCodes));
        return dictCountryEntities;
    }
}
