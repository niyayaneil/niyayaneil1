package com.leadtrans.dictservice.mgr.convert;

import com.leadtrans.dictservice.common.repository.entity.DictSourceEntity;
import com.leadtrans.dictservice.mgr.controller.vo.DictSourceRespVO;
import com.leadtrans.dictservice.mgr.controller.vo.DictSourceReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DictSourceConvert {
    DictSourceConvert INSTANCE = Mappers.getMapper(DictSourceConvert.class);

    DictSourceEntity toEntity(DictSourceReqVO VO);

    DictSourceRespVO toVO(DictSourceEntity entity);

    List<DictSourceRespVO> toVOList(List<DictSourceEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "systemCreateUser", ignore = true)
    @Mapping(target = "systemCreateTimeUtc", ignore = true)
    void updateEntity(DictSourceReqVO vo, @MappingTarget DictSourceEntity entity);

}
