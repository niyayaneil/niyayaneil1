package com.leadtrans.dictservice.mgr.service.impl;

import com.alibaba.fastjson.JSON;
import com.leadtrans.dictservice.common.enums.StatusEnum;
import com.leadtrans.dictservice.common.enums.SupplierTypeEnum;
import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.*;
import com.leadtrans.dictservice.mgr.service.DictCityService;
import com.leadtrans.dictservice.mgr.service.DictCountryService;
import com.leadtrans.dictservice.mgr.service.DictStateService;
import com.leadtrans.dictservice.mgr.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OptionServiceImpl implements OptionService {

    @Autowired
    private DictCountryService dictCountryService;
    @Autowired
    private DictStateService dictStateService;
    @Autowired
    private DictCityService dictCityService;

    @Override
    public Map<String, List<OptionRespVO>> getOptions(Map<String, Map<String,String>> reqVO) {
        if (CollectionUtils.isEmpty(reqVO)) {
            return Collections.emptyMap();
        }

        return reqVO.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> builders.containsKey(entry.getKey())?builders.get(entry.getKey()).build(entry.getValue()):Collections.emptyList(),
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
    }

    private interface OptionBuilder {
        List<OptionRespVO> build(Map<String,String> parameters);
    }
    private final Map<String,OptionBuilder> builders = new HashMap<>();
    public OptionServiceImpl(){ registerBuilders(); }
    private void registerBuilders() {
        builders.put("isValids", parameters ->
                Arrays.stream(StatusEnum.values())
                        .map(e -> new OptionRespVO<>(e.getCode(), e.getName()))
                        .collect(Collectors.toList())
        );
        builders.put("supplierTypes", parameters ->
                Arrays.stream(SupplierTypeEnum.values())
                        .map(e -> new OptionRespVO<>(e.getCode(), e.getName()))
                        .collect(Collectors.toList())
        );
        builders.put("countries", parameters ->{
            DictCountryPageReqVO pageReqVO = JSON.parseObject(JSON.toJSONString(parameters), DictCountryPageReqVO.class);
            pageReqVO.setPageNum(1);
            pageReqVO.setPageSize(1000000);
            PageResult<DictCountryRespVO> page = dictCountryService.page(pageReqVO);
            return page.getList().stream().map(e -> new OptionRespVO<>(e.getNumCode(), e.getNameEn())).collect(Collectors.toList());
        });
        builders.put("states", parameters ->{
                DictStatePageReqVO pageReqVO = JSON.parseObject(JSON.toJSONString(parameters), DictStatePageReqVO.class);
                pageReqVO.setPageNum(1);
                pageReqVO.setPageSize(1000000);
                PageResult<DictStateRespVO> page = dictStateService.page(pageReqVO);
                return page.getList().stream().map(e -> new OptionRespVO<>(e.getNumCode(), e.getNameEn())).collect(Collectors.toList());
         });
        builders.put("cities", parameters -> {
            DictCityPageReqVO pageReqVO = JSON.parseObject(JSON.toJSONString(parameters), DictCityPageReqVO.class);
            pageReqVO.setPageNum(1);
            pageReqVO.setPageSize(1000000);
            PageResult<DictCityRespVO> page = dictCityService.page(pageReqVO);
            return page.getList().stream().map(e -> new OptionRespVO<>(e.getNumCode(), e.getNameEn())).collect(Collectors.toList());
        });
    }

}
