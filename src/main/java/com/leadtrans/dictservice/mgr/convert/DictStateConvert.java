package com.leadtrans.dictservice.mgr.convert;

import com.leadtrans.dictservice.common.repository.entity.DictStateEntity;
import com.leadtrans.dictservice.mgr.controller.vo.DictStateReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.DictStateRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DictStateConvert {
    DictStateConvert INSTANCE = Mappers.getMapper(DictStateConvert.class);

    DictStateEntity toEntity(DictStateReqVO VO);

    DictStateRespVO toVO(DictStateEntity entity);

    List<DictStateRespVO> toVOList(List<DictStateEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "systemCreateUser", ignore = true)
    @Mapping(target = "systemCreateTimeUtc", ignore = true)
    void updateEntity(DictStateReqVO vo, @MappingTarget DictStateEntity entity);

}
