package com.leadtrans.dictservice.mgr.convert;

import com.leadtrans.dictservice.common.repository.entity.PortMappingEntity;
import com.leadtrans.dictservice.mgr.controller.vo.PortMappingReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.PortMappingRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PortMappingConvert {
    PortMappingConvert INSTANCE = Mappers.getMapper(PortMappingConvert.class);

    PortMappingEntity toEntity(PortMappingReqVO VO);

    PortMappingRespVO toVO(PortMappingEntity entity);

    List<PortMappingRespVO> toVOList(List<PortMappingEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "systemCreateUser", ignore = true)
    @Mapping(target = "systemCreateTimeUtc", ignore = true)
    void updateEntity(PortMappingReqVO vo, @MappingTarget PortMappingEntity entity);

}
