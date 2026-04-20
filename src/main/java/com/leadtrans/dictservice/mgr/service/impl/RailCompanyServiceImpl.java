package com.leadtrans.dictservice.mgr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leadtrans.dictservice.common.i18n.I18nAssert;
import com.leadtrans.dictservice.common.repository.entity.RailCompanyEntity;
import com.leadtrans.dictservice.common.repository.mapper.RailCompanyMapper;
import com.leadtrans.dictservice.common.utils.LambdaOrderByFactory;
import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.RailCompanyPageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.RailCompanyReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.RailCompanyRespVO;
import com.leadtrans.dictservice.mgr.convert.RailCompanyConvert;
import com.leadtrans.dictservice.mgr.service.RailCompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class RailCompanyServiceImpl implements RailCompanyService {

    private final RailCompanyMapper railCompanyMapper;

    @Override
    public Long create(RailCompanyReqVO VO) {
        Integer count = railCompanyMapper.selectCount(new LambdaQueryWrapper<RailCompanyEntity>().eq(RailCompanyEntity::getRailroadCode, VO.getRailroadCode()));
        I18nAssert.exists(count, "railCompanyReqVO.railroadCode.Exists", VO.getRailroadCode());

        RailCompanyEntity entity = RailCompanyConvert.INSTANCE.toEntity(VO);
        railCompanyMapper.insert(entity);
        return entity.getId().longValue();
    }

    @Override
    public void delete(Long id) {
        I18nAssert.badRequest(id, "railCompanyReqVO.id.NotNull");
        I18nAssert.notFound(railCompanyMapper.selectById(id), "railCompanyReqVO.id.NotFound", id.toString());
        railCompanyMapper.deleteById(id);
    }

    @Override
    public void update(Long id, RailCompanyReqVO VO) {
        I18nAssert.badRequest(id, "railCompanyReqVO.id.NotNull");
        I18nAssert.isTrue(VO.getIsValid() == 0 || VO.getIsValid() == 1, "railCompanyReqVO.isValid.Invalid");

        Integer count = railCompanyMapper.selectCount(new LambdaQueryWrapper<RailCompanyEntity>().eq(RailCompanyEntity::getRailroadCode, VO.getRailroadCode()).ne(RailCompanyEntity::getId, id));
        I18nAssert.exists(count, "railCompanyReqVO.railroadCode.Exists", VO.getRailroadCode());

        RailCompanyEntity entity = railCompanyMapper.selectById(id);
        I18nAssert.notFound(entity, "railCompanyReqVO.id.NotFound", id.toString());

        RailCompanyConvert.INSTANCE.updateEntity(VO, entity);

        railCompanyMapper.updateById(entity);
    }

    @Override
    public RailCompanyRespVO getById(Long id) {
        I18nAssert.badRequest(id, "railCompanyReqVO.id.NotNull");

        RailCompanyEntity entity = railCompanyMapper.selectById(id);

        I18nAssert.notFound(entity, "railCompanyReqVO.id.NotFound", id.toString());

        return RailCompanyConvert.INSTANCE.toVO(entity);
    }


    @Override
    public PageResult<RailCompanyRespVO> page(RailCompanyPageReqVO reqVO) {

        I18nAssert.isTrue(Objects.isNull(reqVO.getIsValid()) || 
        (reqVO.getIsValid() == 0 || reqVO.getIsValid() == 1), 
        "railCompanyReqVO.isValid.Invalid");

        IPage page = new Page(reqVO.getPageNum(), reqVO.getPageSize());
        LambdaQueryWrapper wrapper = new LambdaQueryWrapper<RailCompanyEntity>()
            .eq(Objects.nonNull(reqVO.getIsValid()), RailCompanyEntity::getIsValid, reqVO.getIsValid())
            .like(StringUtils.hasText(reqVO.getRailroadCode()), RailCompanyEntity::getRailroadCode, reqVO.getRailroadCode())
            .like(StringUtils.hasText(reqVO.getRailroadNameEn()), RailCompanyEntity::getRailroadNameEn, reqVO.getRailroadNameEn())
            .like(StringUtils.hasText(reqVO.getRailroadNameCn()), RailCompanyEntity::getRailroadNameCn, reqVO.getRailroadNameCn())
            .like(StringUtils.hasText(reqVO.getPartnerCarriers()), RailCompanyEntity::getPartnerCarriers, reqVO.getPartnerCarriers());

        //排序
        LambdaOrderByFactory.orderBy(wrapper, RailCompanyEntity.class, reqVO.getOrderBys());
        railCompanyMapper.selectPage(page, wrapper);

        PageResult<RailCompanyRespVO> pageResult = new PageResult(page, RailCompanyConvert.INSTANCE.toVOList(page.getRecords()));
        return pageResult;
    }

    @Override
    public void updateValid(Long id, Integer isValid) {
        I18nAssert.badRequest(id, "railCompanyReqVO.id.NotNull");
        I18nAssert.isTrue(isValid == 0 || isValid == 1, "railCompanyReqVO.isValid.Invalid");

        RailCompanyEntity entity = railCompanyMapper.selectById(id);
        I18nAssert.notFound(entity, "railCompanyReqVO.id.NotFound", id.toString());

        entity.setIsValid(isValid);
        railCompanyMapper.updateById(entity);
    }
}
