package com.leadtrans.dictservice.mgr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leadtrans.dictservice.common.enums.StatusEnum;
import com.leadtrans.dictservice.common.i18n.I18nAssert;
import com.leadtrans.dictservice.common.repository.entity.GlobalTerminalEntity;
import com.leadtrans.dictservice.common.repository.mapper.GlobalTerminalMapper;
import com.leadtrans.dictservice.common.utils.LambdaOrderByFactory;
import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalPortRespVO;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalTerminalPageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalTerminalReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalTerminalRespVO;
import com.leadtrans.dictservice.mgr.convert.GlobalTerminalConvert;
import com.leadtrans.dictservice.mgr.service.GlobalPortService;
import com.leadtrans.dictservice.mgr.service.GlobalTerminalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class GlobalTerminalServiceImpl implements GlobalTerminalService {

    private final GlobalTerminalMapper globalTerminalMapper;
    private final GlobalPortService globalPortService;

    @Override
    public Long create(GlobalTerminalReqVO VO) {
        Integer count = globalTerminalMapper.selectCount(new LambdaQueryWrapper<GlobalTerminalEntity>().eq(GlobalTerminalEntity::getCode, VO.getCode()));
        I18nAssert.exists(count, "globalTerminalPageReqVO.code.Exists",VO.getCode());

        GlobalPortRespVO portRespVO = globalPortService.getByCode(VO.getPortCode());
        I18nAssert.notFound(portRespVO, "globalPortReqVO.code.NotFound",VO.getPortCode());

        GlobalTerminalEntity entity = GlobalTerminalConvert.INSTANCE.toEntity(VO);
        globalTerminalMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public void delete(Long id) {
        I18nAssert.badRequest(id, "globalTerminalPageReqVO.id.NotNull");
        I18nAssert.notFound(globalTerminalMapper.selectById(id), "globalTerminalPageReqVO.id.NotFound",id.toString());
        globalTerminalMapper.deleteById(id);
    }

    @Override
    public void update(Long id, GlobalTerminalReqVO VO) {
        I18nAssert.badRequest(id, "globalTerminalPageReqVO.id.NotNull");
        I18nAssert.isTrue(StatusEnum.getCodes().contains(VO.getIsValid()), "globalTerminalPageReqVO.isValid.Invalid");

        GlobalPortRespVO portRespVO = globalPortService.getByCode(VO.getPortCode());
        I18nAssert.notFound(portRespVO, "globalPortReqVO.code.NotFound",VO.getPortCode());

        Integer count = globalTerminalMapper.selectCount(new LambdaQueryWrapper<GlobalTerminalEntity>().eq(GlobalTerminalEntity::getCode, VO.getCode()).ne(GlobalTerminalEntity::getId,id));
        I18nAssert.exists(count, "globalTerminalPageReqVO.code.Exists",VO.getCode());

        GlobalTerminalEntity entity = globalTerminalMapper.selectById(id);
        I18nAssert.notFound(entity, "globalTerminalPageReqVO.id.NotFound",id.toString());

        GlobalTerminalConvert.INSTANCE.updateEntity(VO,entity);

        globalTerminalMapper.updateById(entity);
    }

    @Override
    public GlobalTerminalRespVO getById(Long id) {
        I18nAssert.badRequest(id, "globalTerminalPageReqVO.id.NotNull");

        GlobalTerminalEntity entity = globalTerminalMapper.selectById(id);

        I18nAssert.notFound(entity, "globalTerminalPageReqVO.id.NotFound",id.toString());

        GlobalTerminalRespVO globalTerminalRespVO = GlobalTerminalConvert.INSTANCE.toVO(entity);
        fillNames(List.of(globalTerminalRespVO));

        return globalTerminalRespVO;
    }

    @Override
    public PageResult<GlobalTerminalRespVO> page(GlobalTerminalPageReqVO reqVO) {

        I18nAssert.isTrue(Objects.isNull(reqVO.getIsValid()) || StatusEnum.getCodes().contains(reqVO.getIsValid()), "globalTerminalPageReqVO.isValid.Invalid");

        IPage page = new Page(reqVO.getPageNum(),reqVO.getPageSize());
        LambdaQueryWrapper wrapper = new LambdaQueryWrapper<GlobalTerminalEntity>()
            .eq(Objects.nonNull(reqVO.getIsValid()), GlobalTerminalEntity::getIsValid,reqVO.getIsValid())
            .eq(StringUtils.hasText(reqVO.getCode()),GlobalTerminalEntity::getCode,reqVO.getCode())
            .like(StringUtils.hasText(reqVO.getNameCn()),GlobalTerminalEntity::getNameCn,reqVO.getNameCn())
            .like(StringUtils.hasText(reqVO.getNameEn()),GlobalTerminalEntity::getNameEn,reqVO.getNameEn());

        //排序
        LambdaOrderByFactory.orderBy(wrapper, GlobalTerminalEntity.class,reqVO.getOrderBys());
        globalTerminalMapper.selectPage(page,wrapper);

        List<GlobalTerminalRespVO> list = GlobalTerminalConvert.INSTANCE.toVOList(page.getRecords());
        fillNames(list);

        PageResult<GlobalTerminalRespVO> pageResult = new PageResult(page,list);
        return pageResult;
    }

    @Override
    public void updateValid(Long id, String isValid) {
        I18nAssert.badRequest(id, "globalTerminalPageReqVO.id.NotNull");
        I18nAssert.isTrue(StatusEnum.getCodes().contains(isValid), "globalTerminalPageReqVO.isValid.Invalid");

        GlobalTerminalEntity entity = globalTerminalMapper.selectById(id);
        I18nAssert.notFound(entity, "globalTerminalPageReqVO.id.NotFound",id.toString());

        entity.setIsValid(isValid);
        globalTerminalMapper.updateById(entity);
    }

    private void fillNames(List<GlobalTerminalRespVO> globalTerminalRespVOS){
        if(CollectionUtils.isEmpty(globalTerminalRespVOS)){
            return;
        }
        Set<String> portCodes= globalTerminalRespVOS.stream().map(v -> v.getPortCode()).collect(Collectors.toSet());
        Map<String, GlobalPortRespVO> globalPortEntityMap = globalPortService.selectMapByCodes(portCodes);

        for (GlobalTerminalRespVO globalTerminalRespVO : globalTerminalRespVOS) {
            GlobalPortRespVO globalPortRespVO = globalPortEntityMap.get(globalTerminalRespVO.getPortCode());
            if(Objects.nonNull(globalPortRespVO)){
                globalTerminalRespVO.setPortFullName(globalPortRespVO.getPortFullName());
            }
        }
    }
}
