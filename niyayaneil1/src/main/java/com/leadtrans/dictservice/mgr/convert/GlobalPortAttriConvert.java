package com.leadtrans.dictservice.mgr.convert;

import com.leadtrans.dictservice.common.repository.entity.GlobalPortAttriEntity;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalPortAttriReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalPortAttriRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GlobalPortAttriConvert {
    GlobalPortAttriConvert INSTANCE = Mappers.getMapper(GlobalPortAttriConvert.class);

    GlobalPortAttriEntity toEntity(GlobalPortAttriReqVO VO);

    GlobalPortAttriRespVO toVO(GlobalPortAttriEntity entity);

    List<GlobalPortAttriRespVO> toVOList(List<GlobalPortAttriEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "systemCreateUser", ignore = true)
    @Mapping(target = "systemCreateTimeUtc", ignore = true)
    void updateEntity(GlobalPortAttriReqVO vo, @MappingTarget GlobalPortAttriEntity entity);

}
