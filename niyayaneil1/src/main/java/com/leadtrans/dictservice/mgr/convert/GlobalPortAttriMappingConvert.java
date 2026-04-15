package com.leadtrans.dictservice.mgr.convert;

import com.leadtrans.dictservice.common.repository.entity.GlobalPortAttriMappingEntity;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalPortAttriMappingReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalPortAttriMappingRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GlobalPortAttriMappingConvert {
    GlobalPortAttriMappingConvert INSTANCE = Mappers.getMapper(GlobalPortAttriMappingConvert.class);

    GlobalPortAttriMappingEntity toEntity(GlobalPortAttriMappingReqVO VO);

    GlobalPortAttriMappingRespVO toVO(GlobalPortAttriMappingEntity entity);

    List<GlobalPortAttriMappingRespVO> toVOList(List<GlobalPortAttriMappingEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "systemCreateUser", ignore = true)
    @Mapping(target = "systemCreateTimeUtc", ignore = true)
    void updateEntity(GlobalPortAttriMappingReqVO vo, @MappingTarget GlobalPortAttriMappingEntity entity);

}
