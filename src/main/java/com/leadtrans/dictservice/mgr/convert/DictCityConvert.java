package com.leadtrans.dictservice.mgr.convert;

import com.leadtrans.dictservice.common.repository.entity.DictCityEntity;
import com.leadtrans.dictservice.mgr.controller.vo.DictCityRespVO;
import com.leadtrans.dictservice.mgr.controller.vo.DictCityReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DictCityConvert {
    DictCityConvert INSTANCE = Mappers.getMapper(DictCityConvert.class);

    DictCityEntity toEntity(DictCityReqVO VO);

    DictCityRespVO toVO(DictCityEntity entity);

    List<DictCityRespVO> toVOList(List<DictCityEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "systemCreateUser", ignore = true)
    @Mapping(target = "systemCreateTimeUtc", ignore = true)
    void updateEntity(DictCityReqVO vo, @MappingTarget DictCityEntity entity);

}
