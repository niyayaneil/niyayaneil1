package com.leadtrans.dictservice.mgr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leadtrans.dictservice.common.enums.StatusEnum;
import com.leadtrans.dictservice.common.i18n.I18nAssert;
import com.leadtrans.dictservice.common.repository.entity.SupplierEntity;
import com.leadtrans.dictservice.common.repository.mapper.SupplierMapper;
import com.leadtrans.dictservice.common.utils.LambdaOrderByFactory;
import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.SupplierPageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.SupplierReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.SupplierRespVO;
import com.leadtrans.dictservice.mgr.convert.SupplierConvert;
import com.leadtrans.dictservice.mgr.service.SupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierMapper supplierMapper;

    @Override
    public Long create(SupplierReqVO VO) {
        Integer count = supplierMapper.selectCount(new LambdaQueryWrapper<SupplierEntity>().eq(SupplierEntity::getCode, VO.getCode()));
        I18nAssert.exists(count, "supplierReqVO.code.Exists",VO.getCode());

        SupplierEntity entity = SupplierConvert.INSTANCE.toEntity(VO);
        supplierMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public void delete(Long id) {
        I18nAssert.badRequest(id, "supplierReqVO.id.NotNull");
        I18nAssert.notFound(supplierMapper.selectById(id), "supplierReqVO.id.NotFound",id.toString());
        supplierMapper.deleteById(id);
    }

    @Override
    public void update(Long id, SupplierReqVO VO) {
        I18nAssert.badRequest(id, "supplierReqVO.id.NotNull");
        I18nAssert.isTrue(StatusEnum.getCodes().contains(VO.getIsValid()), "supplierReqVO.isValid.Invalid");

        Integer count = supplierMapper.selectCount(new LambdaQueryWrapper<SupplierEntity>().eq(SupplierEntity::getCode, VO.getCode()).ne(SupplierEntity::getId,id));
        I18nAssert.exists(count, "supplierReqVO.code.Exists",VO.getCode());

        SupplierEntity entity = supplierMapper.selectById(id);

        I18nAssert.notFound(entity, "supplierReqVO.id.NotFound",id.toString());

        SupplierConvert.INSTANCE.updateEntity(VO,entity);

        supplierMapper.updateById(entity);
    }

    @Override
    public SupplierRespVO getById(Long id) {
        I18nAssert.badRequest(id, "supplierReqVO.id.NotNull");

        SupplierEntity entity = supplierMapper.selectById(id);

        I18nAssert.notFound(entity, "supplierReqVO.id.NotFound",id.toString());

        return SupplierConvert.INSTANCE.toVO(entity);
    }


    @Override
    public PageResult<SupplierRespVO> page(SupplierPageReqVO reqVO) {
        I18nAssert.isTrue(Objects.isNull(reqVO.getIsValid()) || StatusEnum.getCodes().contains(reqVO.getIsValid()), "supplierReqVO.isValid.Invalid");

        IPage page = new Page(reqVO.getPageNum(),reqVO.getPageSize());

        LambdaQueryWrapper wrapper = new LambdaQueryWrapper<SupplierEntity>()
        .eq(Objects.nonNull(reqVO.getIsValid()), SupplierEntity::getIsValid,reqVO.getIsValid());

        //排序
        LambdaOrderByFactory.orderBy(wrapper, SupplierEntity.class,reqVO.getOrderBys());
        supplierMapper.selectPage(page,wrapper);

        PageResult<SupplierRespVO> pageResult = new PageResult(page,SupplierConvert.INSTANCE.toVOList(page.getRecords()));
        return pageResult;
    }

    @Override
    public void updateValid(Long id, String isValid) {
        I18nAssert.badRequest(id, "supplierReqVO.id.NotNull");
        I18nAssert.isTrue(StatusEnum.getCodes().contains(isValid), "supplierReqVO.isValid.Invalid");

        SupplierEntity entity = supplierMapper.selectById(id);
        I18nAssert.notFound(entity, "supplierReqVO.id.NotFound",id.toString());

        entity.setIsValid(isValid);
        supplierMapper.updateById(entity);
    }
}
