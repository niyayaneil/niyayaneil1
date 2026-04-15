package com.leadtrans.dictservice.mgr.convert;

import com.leadtrans.dictservice.common.repository.entity.AirCompanyEntity;
import com.leadtrans.dictservice.mgr.controller.vo.AirCompanyRespVO;
import com.leadtrans.dictservice.mgr.controller.vo.AirCompanyReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AirCompanyConvert {
    AirCompanyConvert INSTANCE = Mappers.getMapper(AirCompanyConvert.class);

    AirCompanyEntity toEntity(AirCompanyReqVO VO);

    AirCompanyRespVO toVO(AirCompanyEntity entity);

    List<AirCompanyRespVO> toVOList(List<AirCompanyEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createUser", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateUser", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    void updateEntity(AirCompanyReqVO vo, @MappingTarget AirCompanyEntity entity);

}