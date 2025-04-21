package com.leadtrans.dictservice.mgr.convert;

import com.leadtrans.dictservice.common.repository.entity.CarrierCompanyEntity;
import com.leadtrans.dictservice.mgr.controller.vo.CarrierCompanyRespVO;
import com.leadtrans.dictservice.mgr.controller.vo.CarrierCompanyReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CarrierCompanyConvert {
    CarrierCompanyConvert INSTANCE = Mappers.getMapper(CarrierCompanyConvert.class);

    CarrierCompanyEntity toEntity(CarrierCompanyReqVO VO);

    CarrierCompanyRespVO toVO(CarrierCompanyEntity entity);

    List<CarrierCompanyRespVO> toVOList(List<CarrierCompanyEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "systemCreateUser", ignore = true)
    @Mapping(target = "systemCreateTimeUtc", ignore = true)
    void updateEntity(CarrierCompanyReqVO vo, @MappingTarget CarrierCompanyEntity entity);

}
