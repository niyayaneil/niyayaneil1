package com.leadtrans.dictservice.mgr.convert;

import com.leadtrans.dictservice.common.repository.entity.LclWarehouseFreeTimeEntity;
import com.leadtrans.dictservice.mgr.controller.vo.LclWarehouseFreeTimeRespVO;
import com.leadtrans.dictservice.mgr.controller.vo.LclWarehouseFreeTimeReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface LclWarehouseFreeTimeConvert {
    LclWarehouseFreeTimeConvert INSTANCE = Mappers.getMapper(LclWarehouseFreeTimeConvert.class);

    LclWarehouseFreeTimeEntity toEntity(LclWarehouseFreeTimeReqVO VO);

    LclWarehouseFreeTimeRespVO toVO(LclWarehouseFreeTimeEntity entity);

    List<LclWarehouseFreeTimeRespVO> toVOList(List<LclWarehouseFreeTimeEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createUser", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateUser", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    void updateEntity(LclWarehouseFreeTimeReqVO vo, @MappingTarget LclWarehouseFreeTimeEntity entity);
}
