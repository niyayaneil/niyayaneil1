package com.leadtrans.dictservice.mgr.convert;

import com.leadtrans.dictservice.common.repository.entity.ReadMeEntity;
import com.leadtrans.dictservice.mgr.controller.vo.ReadMeReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.ReadMeRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ReadMeConvert {
    ReadMeConvert INSTANCE = Mappers.getMapper(ReadMeConvert.class);

    ReadMeEntity toEntity(ReadMeReqVO VO);

    ReadMeRespVO toVO(ReadMeEntity entity);

    List<ReadMeRespVO> toVOList(List<ReadMeEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "systemCreateUser", ignore = true)
    @Mapping(target = "systemCreateTimeUtc", ignore = true)
    void updateEntity(ReadMeReqVO vo, @MappingTarget ReadMeEntity entity);

}
