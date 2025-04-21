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

@Slf4j
@Service
@RequiredArgsConstructor
public class CarrierCompanyServiceImpl implements CarrierCompanyService {

    private final CarrierCompanyMapper carrierCompanyMapper;

    @Override
    public Long create(CarrierCompanyReqVO VO) {
        Integer count = carrierCompanyMapper.selectCount(new LambdaQueryWrapper<CarrierCompanyEntity>().eq(CarrierCompanyEntity::getCode, VO.getCode()));
        I18nAssert.exists(count, "entity.exists","CarrierCompany","code",VO.getCode());

        CarrierCompanyEntity entity = CarrierCompanyConvert.INSTANCE.toEntity(VO);
        carrierCompanyMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public void delete(Long id) {
        I18nAssert.badRequest(id, "id.null");
        I18nAssert.notFound(carrierCompanyMapper.selectById(id), "entity.null");
        carrierCompanyMapper.deleteById(id);
    }

    @Override
    public void update(Long id, CarrierCompanyReqVO VO) {
        I18nAssert.badRequest(id, "id.null");

        CarrierCompanyEntity entity = carrierCompanyMapper.selectById(id);

        I18nAssert.notFound(entity, "entity.null");

        CarrierCompanyConvert.INSTANCE.updateEntity(VO,entity);

        carrierCompanyMapper.updateById(entity);
    }

    @Override
    public CarrierCompanyRespVO getById(Long id) {
        I18nAssert.badRequest(id, "id.null");

        CarrierCompanyEntity entity = carrierCompanyMapper.selectById(id);

        I18nAssert.badRequest(entity, "entity.null");

        return CarrierCompanyConvert.INSTANCE.toVO(entity);
    }


    @Override
    public PageResult<CarrierCompanyRespVO> page(CarrierCompanyPageReqVO reqVO) {
        IPage page = new Page(reqVO.getPageNum(),reqVO.getPageSize());

        LambdaQueryWrapper wrapper = new LambdaQueryWrapper<CarrierCompanyEntity>()
            .eq(CarrierCompanyEntity::getIsValid, StatusEnum.valid.getCode())
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
        I18nAssert.badRequest(id, "id.null");
        I18nAssert.badRequest(isValid, "isValid.null");
        I18nAssert.isTrue(StatusEnum.getCodes().contains(isValid), "isValid.error");

        CarrierCompanyEntity entity = carrierCompanyMapper.selectById(id);
        I18nAssert.notFound(entity, "entity.null");

        entity.setIsValid(isValid);
        carrierCompanyMapper.updateById(entity);
    }
}
