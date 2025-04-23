package com.leadtrans.dictservice.mgr.convert;

import com.leadtrans.dictservice.common.repository.entity.GlobalTerminalEntity;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalTerminalReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalTerminalRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GlobalTerminalConvert {
    GlobalTerminalConvert INSTANCE = Mappers.getMapper(GlobalTerminalConvert.class);

    GlobalTerminalEntity toEntity(GlobalTerminalReqVO VO);

    GlobalTerminalRespVO toVO(GlobalTerminalEntity entity);

    List<GlobalTerminalRespVO> toVOList(List<GlobalTerminalEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "systemCreateUser", ignore = true)
    @Mapping(target = "systemCreateTimeUtc", ignore = true)
    void updateEntity(GlobalTerminalReqVO vo, @MappingTarget GlobalTerminalEntity entity);

}
