package com.leadtrans.dictservice.mgr.service.impl;

import com.alibaba.fastjson.JSON;
import com.leadtrans.dictservice.common.enums.StatusEnum;
import com.leadtrans.dictservice.common.enums.SupplierTypeEnum;
import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.mgr.controller.vo.*;
import com.leadtrans.dictservice.mgr.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.Collator;
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
    @Autowired
    private GlobalAreaService globalAreaService;
    @Autowired
    private GlobalPortAttriService globalPortAttriService;
    @Autowired
    private CarrierCompanyService carrierCompanyService;
    @Autowired
    private DictSourceService dictSourceService;
    @Autowired
    private GlobalPortService globalPortService;

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


    @Override
    public Collection<String> getRegisters() {
        return builders.keySet();
    }

    public interface OptionBuilder {
        List<OptionRespVO> build(Map<String,String> parameters);
    }
    private final Map<String,OptionBuilder> builders = new HashMap<>();
    public OptionServiceImpl(){ registerBuilders(); }
    private void registerBuilders() {
        builders.put("isValids", parameters ->
                Arrays.stream(StatusEnum.values())
                        .map(e -> new OptionRespVO<>(e.getCode(), e.getName(), e.getName()))
                        .collect(Collectors.toList())
        );
        builders.put("supplierTypes", parameters ->
                Arrays.stream(SupplierTypeEnum.values())
                        .map(e -> new OptionRespVO<>(e.getCode(), e.getName(), e.getName()))
                        .collect(Collectors.toList())
        );
        builders.put("countries", parameters ->{
            DictCountryPageReqVO pageReqVO = JSON.parseObject(JSON.toJSONString(parameters), DictCountryPageReqVO.class);
            pageReqVO.setPageNum(1);
            pageReqVO.setPageSize(1000000);
            PageResult<DictCountryRespVO> page = dictCountryService.page(pageReqVO);
            Collator collator = Collator.getInstance(Locale.CHINA);
            return page.getList().stream().map(e -> new OptionRespVO<>(e.getNumCode(), e.getNameEn(), String.join(" ", StringUtils.hasText(e.getNameCn()) ? e.getNameCn() : "", e.getNameEn(), e.getNumCode(), e.getCode()).trim())).sorted((v1,v2)->collator.compare(v1.getLabel(),v2.getLabel())).collect(Collectors.toList());
        });
        builders.put("states", parameters ->{
                DictStatePageReqVO pageReqVO = JSON.parseObject(JSON.toJSONString(parameters), DictStatePageReqVO.class);
                pageReqVO.setPageNum(1);
                pageReqVO.setPageSize(1000000);
                PageResult<DictStateRespVO> page = dictStateService.page(pageReqVO);
            Collator collator = Collator.getInstance(Locale.CHINA);
                return page.getList().stream().map(e -> new OptionRespVO<>(e.getNumCode(), e.getNameEn(), String.join(" ", StringUtils.hasText(e.getNameCn())?e.getNameCn():"",e.getNameEn(),e.getNumCode()).trim())).sorted((v1,v2)->collator.compare(v1.getLabel(),v2.getLabel())).collect(Collectors.toList());
         });
        builders.put("cities", parameters -> {
            DictCityPageReqVO pageReqVO = JSON.parseObject(JSON.toJSONString(parameters), DictCityPageReqVO.class);
            pageReqVO.setPageNum(1);
            pageReqVO.setPageSize(1000000);
            PageResult<DictCityRespVO> page = dictCityService.page(pageReqVO);
            Collator collator = Collator.getInstance(Locale.CHINA);
            return page.getList().stream().map(e -> new OptionRespVO<>(e.getNumCode(), e.getNameEn(), String.join(" ",StringUtils.hasText(e.getNameCn())?e.getNameCn():"",e.getNameEn(),e.getNumCode()).trim())).sorted((v1,v2)->collator.compare(v1.getLabel(),v2.getLabel())).collect(Collectors.toList());
        });

        builders.put("globalAreas", parameters -> {
            GlobalAreaPageReqVO pageReqVO = JSON.parseObject(JSON.toJSONString(parameters), GlobalAreaPageReqVO.class);
            pageReqVO.setPageNum(1);
            pageReqVO.setPageSize(1000000);
            PageResult<GlobalAreaRespVO> page = globalAreaService.page(pageReqVO);
            return page.getList().stream().map(e -> new OptionRespVO<>(e.getId(), e.getName(), e.getName())).collect(Collectors.toList());
        });

        builders.put("globalPortAttris", parameters -> {
            GlobalPortAttriPageReqVO pageReqVO = JSON.parseObject(JSON.toJSONString(parameters), GlobalPortAttriPageReqVO.class);
            pageReqVO.setPageNum(1);
            pageReqVO.setPageSize(1000000);
            PageResult<GlobalPortAttriRespVO> page = globalPortAttriService.page(pageReqVO);
            return page.getList().stream().map(e -> new OptionRespVO<>(e.getId(), e.getName(), e.getName())).collect(Collectors.toList());
        });

        builders.put("carriers", parameters -> {
            CarrierCompanyPageReqVO pageReqVO = JSON.parseObject(JSON.toJSONString(parameters), CarrierCompanyPageReqVO.class);
            pageReqVO.setPageNum(1);
            pageReqVO.setPageSize(1000000);
            PageResult<CarrierCompanyRespVO> page = carrierCompanyService.page(pageReqVO);
            return page.getList().stream().map(e -> new OptionRespVO<>(e.getCode(), e.getName(), e.getName())).collect(Collectors.toList());
        });

        builders.put("sources", parameters -> {
            DictSourcePageReqVO pageReqVO = JSON.parseObject(JSON.toJSONString(parameters), DictSourcePageReqVO.class);
            pageReqVO.setPageNum(1);
            pageReqVO.setPageSize(1000000);
            PageResult<DictSourceRespVO> page = dictSourceService.page(pageReqVO);
            return page.getList().stream().map(e -> new OptionRespVO<>(e.getCode(), e.getName(), e.getName())).collect(Collectors.toList());
        });

        builders.put("globalPorts", parameters -> {
            GlobalPortPageReqVO pageReqVO = JSON.parseObject(JSON.toJSONString(parameters),GlobalPortPageReqVO.class);
            pageReqVO.setPageNum(1);
            pageReqVO.setPageSize(1000000);
            PageResult<GlobalPortRespVO> page = globalPortService.page(pageReqVO);
            return page.getList().stream().map(e -> new OptionRespVO<>(e.getCode(), e.getNameEn(), e.getPortFullName())).collect(Collectors.toList());
        });

    }

}
