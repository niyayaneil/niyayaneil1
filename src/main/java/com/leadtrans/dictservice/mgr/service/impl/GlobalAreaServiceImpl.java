package com.leadtrans.dictservice.mgr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leadtrans.dictservice.common.enums.StatusEnum;
import com.leadtrans.dictservice.common.i18n.I18nAssert;
import com.leadtrans.dictservice.common.repository.entity.GlobalAreaEntity;
import com.leadtrans.dictservice.common.repository.mapper.GlobalAreaMapper;
import com.leadtrans.dictservice.common.utils.LambdaOrderByFactory;
import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalAreaPageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalAreaReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalAreaRespVO;
import com.leadtrans.dictservice.mgr.convert.GlobalAreaConvert;
import com.leadtrans.dictservice.mgr.service.GlobalAreaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class GlobalAreaServiceImpl implements GlobalAreaService {
    private final GlobalAreaMapper globalAreaMapper;

    @Override
    public Long create(GlobalAreaReqVO VO) {
        Integer count = globalAreaMapper.selectCount(new LambdaQueryWrapper<GlobalAreaEntity>().eq(GlobalAreaEntity::getCode, VO.getCode()));
        I18nAssert.exists(count, "globalAreaReqVO.code.Exists",VO.getCode());
        GlobalAreaEntity entity = GlobalAreaConvert.INSTANCE.toEntity(VO);
        globalAreaMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public void delete(Long id) {
        I18nAssert.badRequest(id, "globalAreaReqVO.id.NotNull");
        I18nAssert.notFound(globalAreaMapper.selectById(id), "globalAreaReqVO.id.NotFound",id.toString());
        globalAreaMapper.deleteById(id);
    }

    @Override
    public void update(Long id, GlobalAreaReqVO VO) {
        I18nAssert.badRequest(id, "globalAreaReqVO.id.NotNull");
        I18nAssert.isTrue(StatusEnum.getCodes().contains(VO.getIsValid()), "globalAreaReqVO.isValid.Invalid");

        Integer count = globalAreaMapper.selectCount(new LambdaQueryWrapper<GlobalAreaEntity>().eq(GlobalAreaEntity::getCode, VO.getCode()).ne(GlobalAreaEntity::getId,id));
        I18nAssert.exists(count, "globalAreaReqVO.code.Exists",VO.getCode());

        GlobalAreaEntity entity = globalAreaMapper.selectById(id);
        I18nAssert.notFound(entity, "globalAreaReqVO.id.NotFound",id.toString());

        GlobalAreaConvert.INSTANCE.updateEntity(VO,entity);

        globalAreaMapper.updateById(entity);
    }

    @Override
    public GlobalAreaRespVO getById(Long id) {
        I18nAssert.badRequest(id, "globalAreaReqVO.id.NotNull");

        GlobalAreaEntity entity = globalAreaMapper.selectById(id);
        I18nAssert.notFound(entity, "globalAreaReqVO.id.NotFound",id.toString());
        GlobalAreaRespVO GlobalAreaRespVO = GlobalAreaConvert.INSTANCE.toVO(entity);
        return GlobalAreaRespVO;
    }


    @Override
    public PageResult<GlobalAreaRespVO> page(GlobalAreaPageReqVO reqVO) {
        I18nAssert.isTrue(Objects.isNull(reqVO.getIsValid()) || StatusEnum.getCodes().contains(reqVO.getIsValid()), "globalAreaReqVO.isValid.Invalid");

        IPage page = new Page(reqVO.getPageNum(),reqVO.getPageSize());

        LambdaQueryWrapper wrapper = new LambdaQueryWrapper<GlobalAreaEntity>()
            .eq(Objects.nonNull(reqVO.getIsValid()),GlobalAreaEntity::getIsValid,reqVO.getIsValid())
            .eq(StringUtils.hasText(reqVO.getCode()),GlobalAreaEntity::getCode,reqVO.getCode())
            .eq(StringUtils.hasText(reqVO.getName()),GlobalAreaEntity::getName,reqVO.getName());

        //排序
        LambdaOrderByFactory.orderBy(wrapper, GlobalAreaEntity.class,reqVO.getOrderBys());

        globalAreaMapper.selectPage(page,wrapper);
        List list = GlobalAreaConvert.INSTANCE.toVOList(page.getRecords());
        PageResult<GlobalAreaRespVO> pageResult = new PageResult(page,list);
        return pageResult;
    }

    @Override
    public void updateValid(Long id, String isValid) {
        I18nAssert.badRequest(id, "globalAreaReqVO.id.NotNull");
        I18nAssert.isTrue(StatusEnum.getCodes().contains(isValid), "globalAreaReqVO.isValid.Invalid");

        GlobalAreaEntity entity = globalAreaMapper.selectById(id);
        I18nAssert.notFound(entity, "globalAreaReqVO.id.NotFound",id.toString());

        entity.setIsValid(isValid);
        globalAreaMapper.updateById(entity);
    }

    @Override
    public List<GlobalAreaEntity> findByIds(List<Long> globalAreaIds){
        if(CollectionUtils.isEmpty(globalAreaIds)){
            return List.of();
        }
        List<GlobalAreaEntity> globalAreaEntities = globalAreaMapper.selectList(new LambdaQueryWrapper<GlobalAreaEntity>().in(GlobalAreaEntity::getId, globalAreaIds));
        return globalAreaEntities;
    }

    @Override
    public List<GlobalAreaEntity> findByCodes(List<String> globalAreaCodes){
        if(CollectionUtils.isEmpty(globalAreaCodes)){
            return List.of();
        }
        List<GlobalAreaEntity> globalAreaEntities = globalAreaMapper.selectList(new LambdaQueryWrapper<GlobalAreaEntity>().in(GlobalAreaEntity::getCode, globalAreaCodes));
        return globalAreaEntities;
    }

}
