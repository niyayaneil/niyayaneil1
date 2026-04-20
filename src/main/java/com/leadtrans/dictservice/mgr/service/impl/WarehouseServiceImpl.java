package com.leadtrans.dictservice.mgr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leadtrans.dictservice.common.enums.StatusEnum;
import com.leadtrans.dictservice.common.i18n.I18nAssert;
import com.leadtrans.dictservice.common.repository.entity.WarehouseEntity;
import com.leadtrans.dictservice.common.repository.mapper.WarehouseMapper;
import com.leadtrans.dictservice.common.utils.LambdaOrderByFactory;
import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.WarehousePageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.WarehouseReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.WarehouseRespVO;
import com.leadtrans.dictservice.mgr.convert.WarehouseConvert;
import com.leadtrans.dictservice.mgr.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseMapper warehouseMapper;

    @Override
    public Long create(WarehouseReqVO VO) {
        Integer count = warehouseMapper.selectCount(new LambdaQueryWrapper<WarehouseEntity>().eq(WarehouseEntity::getWarehouseCode, VO.getWarehouseCode()));
        I18nAssert.exists(count, "warehouseReqVO.warehouseCode.Exists", VO.getWarehouseCode());

        WarehouseEntity entity = WarehouseConvert.INSTANCE.toEntity(VO);
        warehouseMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public void delete(Long id) {
        I18nAssert.badRequest(id, "warehouseReqVO.id.NotNull");
        I18nAssert.notFound(warehouseMapper.selectById(id), "warehouseReqVO.id.NotFound", id.toString());
        warehouseMapper.deleteById(id);
    }

    @Override
    public void update(Long id, WarehouseReqVO VO) {
        I18nAssert.badRequest(id, "warehouseReqVO.id.NotNull");
        I18nAssert.isTrue(VO.getIsValid() == 0 || VO.getIsValid() == 1, "warehouseReqVO.isValid.Invalid");

        Integer count = warehouseMapper.selectCount(new LambdaQueryWrapper<WarehouseEntity>().eq(WarehouseEntity::getWarehouseCode, VO.getWarehouseCode()).ne(WarehouseEntity::getId, id));
        I18nAssert.exists(count, "warehouseReqVO.warehouseCode.Exists", VO.getWarehouseCode());

        WarehouseEntity entity = warehouseMapper.selectById(id);
        I18nAssert.notFound(entity, "warehouseReqVO.id.NotFound", id.toString());

        WarehouseConvert.INSTANCE.updateEntity(VO, entity);

        warehouseMapper.updateById(entity);
    }

    @Override
    public WarehouseRespVO getById(Long id) {
        I18nAssert.badRequest(id, "warehouseReqVO.id.NotNull");

        WarehouseEntity entity = warehouseMapper.selectById(id);

        I18nAssert.notFound(entity, "warehouseReqVO.id.NotFound", id.toString());

        return WarehouseConvert.INSTANCE.toVO(entity);
    }

    @Override
    public PageResult<WarehouseRespVO> page(WarehousePageReqVO reqVO) {
        I18nAssert.isTrue(Objects.isNull(reqVO.getIsValid()) || 
        (reqVO.getIsValid() == 0 || reqVO.getIsValid() == 1), "warehouseReqVO.isValid.Invalid");

        IPage page = new Page(reqVO.getPageNum(), reqVO.getPageSize());
        LambdaQueryWrapper wrapper = new LambdaQueryWrapper<WarehouseEntity>()
            .eq(Objects.nonNull(reqVO.getIsValid()), WarehouseEntity::getIsValid, reqVO.getIsValid())
            .like(StringUtils.hasText(reqVO.getWarehouseCode()), WarehouseEntity::getWarehouseCode, reqVO.getWarehouseCode())
            .like(StringUtils.hasText(reqVO.getWarehouseNameEn()), WarehouseEntity::getWarehouseNameEn, reqVO.getWarehouseNameEn())
            .like(StringUtils.hasText(reqVO.getWarehouseNameCn()), WarehouseEntity::getWarehouseNameCn, reqVO.getWarehouseNameCn())
            .like(StringUtils.hasText(reqVO.getPortRole()), WarehouseEntity::getPortRole, reqVO.getPortRole())
            .like(StringUtils.hasText(reqVO.getWarehouseType()), WarehouseEntity::getWarehouseType, reqVO.getWarehouseType())
            .like(StringUtils.hasText(reqVO.getServiceType()), WarehouseEntity::getServiceType, reqVO.getServiceType())
            .like(StringUtils.hasText(reqVO.getPortCode()), WarehouseEntity::getPortCode, reqVO.getPortCode())
            .like(StringUtils.hasText(reqVO.getPhone()), WarehouseEntity::getPhone, reqVO.getPhone());

        //排序
        LambdaOrderByFactory.orderBy(wrapper, WarehouseEntity.class, reqVO.getOrderBys());
        warehouseMapper.selectPage(page, wrapper);

        PageResult<WarehouseRespVO> pageResult = new PageResult(page, WarehouseConvert.INSTANCE.toVOList(page.getRecords()));
        return pageResult;
    }

    @Override
    public void updateValid(Long id, Integer isValid) {
        I18nAssert.badRequest(id, "warehouseReqVO.id.NotNull");
        I18nAssert.isTrue(isValid == 0 || isValid == 1, "warehouseReqVO.isValid.Invalid");

        WarehouseEntity entity = warehouseMapper.selectById(id);
        I18nAssert.notFound(entity, "warehouseReqVO.id.NotFound", id.toString());

        entity.setIsValid(isValid);
        warehouseMapper.updateById(entity);
    }
}