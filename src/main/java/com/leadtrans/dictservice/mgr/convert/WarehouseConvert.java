package com.leadtrans.dictservice.mgr.convert;

import com.leadtrans.dictservice.common.repository.entity.WarehouseEntity;
import com.leadtrans.dictservice.mgr.controller.vo.WarehouseRespVO;
import com.leadtrans.dictservice.mgr.controller.vo.WarehouseReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface WarehouseConvert {
    WarehouseConvert INSTANCE = Mappers.getMapper(WarehouseConvert.class);

    WarehouseEntity toEntity(WarehouseReqVO VO);

    WarehouseRespVO toVO(WarehouseEntity entity);

    List<WarehouseRespVO> toVOList(List<WarehouseEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createUser", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateUser", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    void updateEntity(WarehouseReqVO vo, @MappingTarget WarehouseEntity entity);

}
