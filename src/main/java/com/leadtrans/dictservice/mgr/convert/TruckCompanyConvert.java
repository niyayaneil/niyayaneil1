package com.leadtrans.dictservice.mgr.convert;

import com.leadtrans.dictservice.common.repository.entity.TruckCompanyEntity;
import com.leadtrans.dictservice.mgr.controller.vo.TruckCompanyRespVO;
import com.leadtrans.dictservice.mgr.controller.vo.TruckCompanyReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TruckCompanyConvert {
    TruckCompanyConvert INSTANCE = Mappers.getMapper(TruckCompanyConvert.class);

    TruckCompanyEntity toEntity(TruckCompanyReqVO VO);

    TruckCompanyRespVO toVO(TruckCompanyEntity entity);

    List<TruckCompanyRespVO> toVOList(List<TruckCompanyEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createUser", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateUser", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    void updateEntity(TruckCompanyReqVO vo, @MappingTarget TruckCompanyEntity entity);

}