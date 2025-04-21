package com.leadtrans.dictservice.mgr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leadtrans.dictservice.common.repository.entity.DictSourceEntity;
import com.leadtrans.dictservice.common.repository.mapper.DictSourceMapper;
import com.leadtrans.dictservice.common.utils.LambdaOrderByFactory;
import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.DictSourcePageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.DictSourceRespVO;
import com.leadtrans.dictservice.mgr.convert.DictSourceConvert;
import com.leadtrans.dictservice.mgr.service.DictSourceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DictSourceServiceImpl implements DictSourceService {

    private final DictSourceMapper dictSourceMapper;

    @Override
    public PageResult<DictSourceRespVO> page(DictSourcePageReqVO reqVO) {
        IPage page = new Page(reqVO.getPageNum(),reqVO.getPageSize());

        LambdaQueryWrapper wrapper = new LambdaQueryWrapper<DictSourceEntity>();
        //排序
        LambdaOrderByFactory.orderBy(wrapper, DictSourceEntity.class,reqVO.getOrderBys());

        dictSourceMapper.selectPage(page,wrapper);

        PageResult<DictSourceRespVO> pageResult = new PageResult(page,DictSourceConvert.INSTANCE.toVOList(page.getRecords()));
        return pageResult;
    }

}
