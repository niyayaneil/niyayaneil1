package com.leadtrans.dictservice.mgr.convert;

import com.leadtrans.dictservice.common.repository.entity.RailCompanyEntity;
import com.leadtrans.dictservice.mgr.controller.vo.RailCompanyReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.RailCompanyRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 铁路公司实体转换
 *
 * @author Aaron
 * @since 2026-04-15
 */
@Mapper
public interface RailCompanyConvert {

    RailCompanyConvert INSTANCE = Mappers.getMapper(RailCompanyConvert.class);

    RailCompanyEntity toEntity(RailCompanyReqVO vo);

    RailCompanyRespVO toVO(RailCompanyEntity entity);

    List<RailCompanyRespVO> toVOList(List<RailCompanyEntity> entities);

    void updateEntity(RailCompanyReqVO vo, @MappingTarget RailCompanyEntity entity);
}
