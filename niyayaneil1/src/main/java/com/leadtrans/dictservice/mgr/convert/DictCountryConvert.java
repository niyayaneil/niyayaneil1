package com.leadtrans.dictservice.mgr.convert;

import com.leadtrans.dictservice.common.repository.entity.DictCountryEntity;
import com.leadtrans.dictservice.mgr.controller.vo.DictCountryRespVO;
import com.leadtrans.dictservice.mgr.controller.vo.DictCountryReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DictCountryConvert {
    DictCountryConvert INSTANCE = Mappers.getMapper(DictCountryConvert.class);

    DictCountryEntity toEntity(DictCountryReqVO VO);

    DictCountryRespVO toVO(DictCountryEntity entity);

    List<DictCountryRespVO> toVOList(List<DictCountryEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "systemCreateUser", ignore = true)
    @Mapping(target = "systemCreateTimeUtc", ignore = true)
    void updateEntity(DictCountryReqVO vo, @MappingTarget DictCountryEntity entity);

}
