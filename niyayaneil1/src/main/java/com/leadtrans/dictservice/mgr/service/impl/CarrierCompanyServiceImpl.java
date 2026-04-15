package com.leadtrans.dictservice.mgr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leadtrans.dictservice.common.enums.StatusEnum;
import com.leadtrans.dictservice.common.i18n.I18nAssert;
import com.leadtrans.dictservice.common.repository.entity.CarrierCompanyEntity;
import com.leadtrans.dictservice.common.repository.mapper.CarrierCompanyMapper;
import com.leadtrans.dictservice.common.utils.LambdaOrderByFactory;
import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.CarrierCompanyPageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.CarrierCompanyReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.CarrierCompanyRespVO;
import com.leadtrans.dictservice.mgr.convert.CarrierCompanyConvert;
import com.leadtrans.dictservice.mgr.service.CarrierCompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarrierCompanyServiceImpl implements CarrierCompanyService {

    private final CarrierCompanyMapper carrierCompanyMapper;

    @Override
    public Long create(CarrierCompanyReqVO VO) {
        Integer count = carrierCompanyMapper.selectCount(new LambdaQueryWrapper<CarrierCompanyEntity>().eq(CarrierCompanyEntity::getCode, VO.getCode()));
        I18nAssert.exists(count, "carrierCompanyReqVO.code.Exists",VO.getCode());

        CarrierCompanyEntity entity = CarrierCompanyConvert.INSTANCE.toEntity(VO);
        carrierCompanyMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public void delete(Long id) {
        I18nAssert.badRequest(id, "carrierCompanyReqVO.id.NotNull");
        I18nAssert.notFound(carrierCompanyMapper.selectById(id), "carrierCompanyReqVO.id.NotFound",id.toString());
        carrierCompanyMapper.deleteById(id);
    }

    @Override
    public void update(Long id, CarrierCompanyReqVO VO) {
        I18nAssert.badRequest(id, "carrierCompanyReqVO.id.NotNull");
        I18nAssert.isTrue(StatusEnum.getCodes().contains(VO.getIsValid()), "carrierCompanyReqVO.isValid.Invalid");

        Integer count = carrierCompanyMapper.selectCount(new LambdaQueryWrapper<CarrierCompanyEntity>().eq(CarrierCompanyEntity::getCode, VO.getCode()).ne(CarrierCompanyEntity::getId,id));
        I18nAssert.exists(count, "carrierCompanyReqVO.code.Exists",VO.getCode());

        CarrierCompanyEntity entity = carrierCompanyMapper.selectById(id);
        I18nAssert.notFound(entity, "carrierCompanyReqVO.id.NotFound",id.toString());

        CarrierCompanyConvert.INSTANCE.updateEntity(VO,entity);

        carrierCompanyMapper.updateById(entity);
    }

    @Override
    public CarrierCompanyRespVO getById(Long id) {
        I18nAssert.badRequest(id, "carrierCompanyReqVO.id.NotNull");

        CarrierCompanyEntity entity = carrierCompanyMapper.selectById(id);

        I18nAssert.notFound(entity, "carrierCompanyReqVO.id.NotFound",id.toString());

        return CarrierCompanyConvert.INSTANCE.toVO(entity);
    }


    @Override
    public PageResult<CarrierCompanyRespVO> page(CarrierCompanyPageReqVO reqVO) {

        I18nAssert.isTrue(Objects.isNull(reqVO.getIsValid()) || StatusEnum.getCodes().contains(reqVO.getIsValid()), "carrierCompanyReqVO.isValid.Invalid");

        IPage page = new Page(reqVO.getPageNum(),reqVO.getPageSize());
        LambdaQueryWrapper wrapper = new LambdaQueryWrapper<CarrierCompanyEntity>()
            .eq(Objects.nonNull(reqVO.getIsValid()), CarrierCompanyEntity::getIsValid,reqVO.getIsValid())
            .eq(StringUtils.hasText(reqVO.getCode()),CarrierCompanyEntity::getCode,reqVO.getCode())
            .eq(StringUtils.hasText(reqVO.getName()),CarrierCompanyEntity::getName,reqVO.getName())
            .eq(StringUtils.hasText(reqVO.getCargowise9Code()),CarrierCompanyEntity::getCargowise9Code,reqVO.getCargowise9Code());

        //排序
        LambdaOrderByFactory.orderBy(wrapper, CarrierCompanyEntity.class,reqVO.getOrderBys());
        carrierCompanyMapper.selectPage(page,wrapper);

        PageResult<CarrierCompanyRespVO> pageResult = new PageResult(page,CarrierCompanyConvert.INSTANCE.toVOList(page.getRecords()));
        return pageResult;
    }

    @Override
    public void updateValid(Long id, String isValid) {
        I18nAssert.badRequest(id, "carrierCompanyReqVO.id.NotNull");
        I18nAssert.isTrue(StatusEnum.getCodes().contains(isValid), "carrierCompanyReqVO.isValid.Invalid");

        CarrierCompanyEntity entity = carrierCompanyMapper.selectById(id);
        I18nAssert.notFound(entity, "carrierCompanyReqVO.id.NotFound",id.toString());

        entity.setIsValid(isValid);
        carrierCompanyMapper.updateById(entity);
    }
}
