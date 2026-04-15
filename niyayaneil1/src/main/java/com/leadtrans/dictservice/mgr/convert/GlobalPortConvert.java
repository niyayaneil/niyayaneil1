package com.leadtrans.dictservice.mgr.convert;

import com.leadtrans.dictservice.common.repository.entity.GlobalPortEntity;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalPortReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalPortRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GlobalPortConvert {
    GlobalPortConvert INSTANCE = Mappers.getMapper(GlobalPortConvert.class);

    GlobalPortEntity toEntity(GlobalPortReqVO VO);

    GlobalPortRespVO toVO(GlobalPortEntity entity);

    List<GlobalPortRespVO> toVOList(List<GlobalPortEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "systemCreateUser", ignore = true)
    @Mapping(target = "systemCreateTimeUtc", ignore = true)
    void updateEntity(GlobalPortReqVO vo, @MappingTarget GlobalPortEntity entity);

}
