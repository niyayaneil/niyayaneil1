package com.leadtrans.dictservice.mgr.convert;

import com.leadtrans.dictservice.common.repository.entity.FclOutsideFreeTimeEntity;
import com.leadtrans.dictservice.mgr.controller.vo.FclOutsideFreeTimeRespVO;
import com.leadtrans.dictservice.mgr.controller.vo.FclOutsideFreeTimeReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface FclOutsideFreeTimeConvert {
    FclOutsideFreeTimeConvert INSTANCE = Mappers.getMapper(FclOutsideFreeTimeConvert.class);

    FclOutsideFreeTimeEntity toEntity(FclOutsideFreeTimeReqVO VO);

    FclOutsideFreeTimeRespVO toVO(FclOutsideFreeTimeEntity entity);

    List<FclOutsideFreeTimeRespVO> toVOList(List<FclOutsideFreeTimeEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createUser", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateUser", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    void updateEntity(FclOutsideFreeTimeReqVO vo, @MappingTarget FclOutsideFreeTimeEntity entity);
}