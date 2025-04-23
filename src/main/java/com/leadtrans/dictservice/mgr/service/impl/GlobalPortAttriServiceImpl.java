package com.leadtrans.dictservice.mgr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leadtrans.dictservice.common.enums.StatusEnum;
import com.leadtrans.dictservice.common.i18n.I18nAssert;
import com.leadtrans.dictservice.common.repository.entity.GlobalPortAttriEntity;
import com.leadtrans.dictservice.common.repository.mapper.GlobalPortAttriMapper;
import com.leadtrans.dictservice.common.utils.LambdaOrderByFactory;
import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalPortAttriPageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalPortAttriReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalPortAttriRespVO;
import com.leadtrans.dictservice.mgr.convert.GlobalPortAttriConvert;
import com.leadtrans.dictservice.mgr.service.GlobalPortAttriService;
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
public class GlobalPortAttriServiceImpl implements GlobalPortAttriService {
    private final GlobalPortAttriMapper globalPortAttriMapper;

    @Override
    public Long create(GlobalPortAttriReqVO VO) {
        Integer count = globalPortAttriMapper.selectCount(new LambdaQueryWrapper<GlobalPortAttriEntity>().eq(GlobalPortAttriEntity::getCode, VO.getCode()));
        I18nAssert.exists(count, "globalPortAttriReqVO.code.Exists",VO.getCode());
        GlobalPortAttriEntity entity = GlobalPortAttriConvert.INSTANCE.toEntity(VO);
        globalPortAttriMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public void delete(Long id) {
        I18nAssert.badRequest(id, "globalPortAttriReqVO.id.NotNull");
        I18nAssert.notFound(globalPortAttriMapper.selectById(id), "globalPortAttriReqVO.id.NotFound",id.toString());
        globalPortAttriMapper.deleteById(id);
    }

    @Override
    public void update(Long id, GlobalPortAttriReqVO VO) {
        I18nAssert.badRequest(id, "globalPortAttriReqVO.id.NotNull");
        I18nAssert.isTrue(StatusEnum.getCodes().contains(VO.getIsValid()), "globalPortAttriReqVO.isValid.Invalid");

        Integer count = globalPortAttriMapper.selectCount(new LambdaQueryWrapper<GlobalPortAttriEntity>().eq(GlobalPortAttriEntity::getCode, VO.getCode()).ne(GlobalPortAttriEntity::getId,id));
        I18nAssert.exists(count, "globalPortAttriReqVO.code.Exists",VO.getCode());

        GlobalPortAttriEntity entity = globalPortAttriMapper.selectById(id);
        I18nAssert.notFound(entity, "globalPortAttriReqVO.id.NotFound",id.toString());

        GlobalPortAttriConvert.INSTANCE.updateEntity(VO,entity);

        globalPortAttriMapper.updateById(entity);
    }

    @Override
    public GlobalPortAttriRespVO getById(Long id) {
        I18nAssert.badRequest(id, "globalPortAttriReqVO.id.NotNull");

        GlobalPortAttriEntity entity = globalPortAttriMapper.selectById(id);
        I18nAssert.notFound(entity, "globalPortAttriReqVO.id.NotFound",id.toString());
        GlobalPortAttriRespVO GlobalPortAttriRespVO = GlobalPortAttriConvert.INSTANCE.toVO(entity);
        return GlobalPortAttriRespVO;
    }


    @Override
    public PageResult<GlobalPortAttriRespVO> page(GlobalPortAttriPageReqVO reqVO) {
        I18nAssert.isTrue(Objects.isNull(reqVO.getIsValid()) || StatusEnum.getCodes().contains(reqVO.getIsValid()), "globalPortAttriReqVO.isValid.Invalid");

        IPage page = new Page(reqVO.getPageNum(),reqVO.getPageSize());

        LambdaQueryWrapper wrapper = new LambdaQueryWrapper<GlobalPortAttriEntity>()
            .eq(Objects.nonNull(reqVO.getIsValid()),GlobalPortAttriEntity::getIsValid,reqVO.getIsValid())
            .eq(StringUtils.hasText(reqVO.getCode()),GlobalPortAttriEntity::getCode,reqVO.getCode())
            .eq(StringUtils.hasText(reqVO.getName()),GlobalPortAttriEntity::getName,reqVO.getName());

        //排序
        LambdaOrderByFactory.orderBy(wrapper, GlobalPortAttriEntity.class,reqVO.getOrderBys());

        globalPortAttriMapper.selectPage(page,wrapper);
        List list = GlobalPortAttriConvert.INSTANCE.toVOList(page.getRecords());
        PageResult<GlobalPortAttriRespVO> pageResult = new PageResult(page,list);
        return pageResult;
    }

    @Override
    public void updateValid(Long id, String isValid) {
        I18nAssert.badRequest(id, "globalPortAttriReqVO.id.NotNull");
        I18nAssert.isTrue(StatusEnum.getCodes().contains(isValid), "globalPortAttriReqVO.isValid.Invalid");

        GlobalPortAttriEntity entity = globalPortAttriMapper.selectById(id);
        I18nAssert.notFound(entity, "globalPortAttriReqVO.id.NotFound",id.toString());

        entity.setIsValid(isValid);
        globalPortAttriMapper.updateById(entity);
    }

    @Override
    public List<GlobalPortAttriEntity> selectByIds(List<Long> globalPortAttriIds){
        if(CollectionUtils.isEmpty(globalPortAttriIds)){
            return List.of();
        }
        List<GlobalPortAttriEntity> globalPortAttriEntities = globalPortAttriMapper.selectBatchIds(globalPortAttriIds);

        return globalPortAttriEntities;
    }
}
