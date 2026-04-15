package com.leadtrans.dictservice.mgr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leadtrans.dictservice.common.enums.StatusEnum;
import com.leadtrans.dictservice.common.i18n.I18nAssert;
import com.leadtrans.dictservice.common.repository.entity.ReadMeEntity;
import com.leadtrans.dictservice.common.repository.mapper.ReadMeMapper;
import com.leadtrans.dictservice.common.utils.LambdaOrderByFactory;
import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.ReadMePageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.ReadMeReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.ReadMeRespVO;
import com.leadtrans.dictservice.mgr.convert.ReadMeConvert;
import com.leadtrans.dictservice.mgr.service.ReadMeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReadMeServiceImpl implements ReadMeService {

    private final ReadMeMapper readMeMapper;

    @Override
    public Long create(ReadMeReqVO VO) {
        Integer count = readMeMapper.selectCount(new LambdaQueryWrapper<ReadMeEntity>().eq(ReadMeEntity::getCode, VO.getCode()));
        I18nAssert.exists(count, "readMeReqVO.code.Exists",VO.getCode());

        ReadMeEntity entity = ReadMeConvert.INSTANCE.toEntity(VO);
        readMeMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public void delete(Long id) {
        I18nAssert.badRequest(id, "readMeReqVO.id.NotNull");
        I18nAssert.notFound(readMeMapper.selectById(id), "readMeReqVO.id.NotFound",id.toString());
        readMeMapper.deleteById(id);
    }

    @Override
    public void update(Long id, ReadMeReqVO VO) {
        I18nAssert.badRequest(id, "readMeReqVO.id.NotNull");
        I18nAssert.isTrue(StatusEnum.getCodes().contains(VO.getIsValid()), "readMeReqVO.isValid.Invalid");

        Integer count = readMeMapper.selectCount(new LambdaQueryWrapper<ReadMeEntity>().eq(ReadMeEntity::getCode, VO.getCode()).ne(ReadMeEntity::getId,id));
        I18nAssert.exists(count, "readMeReqVO.code.Exists",VO.getCode());

        ReadMeEntity entity = readMeMapper.selectById(id);

        I18nAssert.notFound(entity, "readMeReqVO.id.NotFound",id.toString());

        ReadMeConvert.INSTANCE.updateEntity(VO,entity);

        readMeMapper.updateById(entity);
    }

    @Override
    public ReadMeRespVO getById(Long id) {
        I18nAssert.badRequest(id, "readMeReqVO.id.NotNull");

        ReadMeEntity entity = readMeMapper.selectById(id);

        I18nAssert.notFound(entity, "readMeReqVO.id.NotFound",id.toString());

        return ReadMeConvert.INSTANCE.toVO(entity);
    }

    @Override
    public ReadMeRespVO getByCode(String code) {
        I18nAssert.isTrue(StringUtils.hasText(code), "readMeReqVO.code.NotBlank");

        ReadMeEntity entity = readMeMapper.selectOne(new LambdaQueryWrapper<ReadMeEntity>().eq(ReadMeEntity::getCode,code));

        I18nAssert.notFound(entity, "readMeReqVO.id.NotFound",code);

        return ReadMeConvert.INSTANCE.toVO(entity);
    }


    @Override
    public PageResult<ReadMeRespVO> page(ReadMePageReqVO reqVO) {
        I18nAssert.isTrue(Objects.isNull(reqVO.getIsValid()) || StatusEnum.getCodes().contains(reqVO.getIsValid()), "readMeReqVO.isValid.Invalid");

        IPage page = new Page(reqVO.getPageNum(),reqVO.getPageSize());

        LambdaQueryWrapper wrapper = new LambdaQueryWrapper<ReadMeEntity>()
        .eq(Objects.nonNull(reqVO.getIsValid()), ReadMeEntity::getIsValid,reqVO.getIsValid());

        //排序
        LambdaOrderByFactory.orderBy(wrapper, ReadMeEntity.class,reqVO.getOrderBys());
        readMeMapper.selectPage(page,wrapper);

        PageResult<ReadMeRespVO> pageResult = new PageResult(page,ReadMeConvert.INSTANCE.toVOList(page.getRecords()));
        return pageResult;
    }

    @Override
    public void updateValid(Long id, String isValid) {
        I18nAssert.badRequest(id, "readMeReqVO.id.NotNull");
        I18nAssert.isTrue(StatusEnum.getCodes().contains(isValid), "readMeReqVO.isValid.Invalid");

        ReadMeEntity entity = readMeMapper.selectById(id);
        I18nAssert.notFound(entity, "readMeReqVO.id.NotFound",id.toString());

        entity.setIsValid(isValid);
        readMeMapper.updateById(entity);
    }
}
