package com.leadtrans.dictservice.mgr.convert;

import com.leadtrans.dictservice.common.repository.entity.FclDndRateEntity;
import com.leadtrans.dictservice.mgr.controller.vo.FclDndRateRespVO;
import com.leadtrans.dictservice.mgr.controller.vo.FclDndRateReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface FclDndRateConvert {
    FclDndRateConvert INSTANCE = Mappers.getMapper(FclDndRateConvert.class);

    FclDndRateEntity toEntity(FclDndRateReqVO VO);

    FclDndRateRespVO toVO(FclDndRateEntity entity);

    List<FclDndRateRespVO> toVOList(List<FclDndRateEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createUser", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateUser", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    void updateEntity(FclDndRateReqVO vo, @MappingTarget FclDndRateEntity entity);
}