package com.leadtrans.dictservice.mgr.convert;

import com.leadtrans.dictservice.common.repository.entity.SupplierEntity;
import com.leadtrans.dictservice.mgr.controller.vo.SupplierReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.SupplierRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SupplierConvert {
    SupplierConvert INSTANCE = Mappers.getMapper(SupplierConvert.class);

    SupplierEntity toEntity(SupplierReqVO VO);

    SupplierRespVO toVO(SupplierEntity entity);

    List<SupplierRespVO> toVOList(List<SupplierEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "systemCreateUser", ignore = true)
    @Mapping(target = "systemCreateTimeUtc", ignore = true)
    void updateEntity(SupplierReqVO vo, @MappingTarget SupplierEntity entity);

}
