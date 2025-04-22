package com.leadtrans.dictservice.mgr.controller;

import com.leadtrans.dictservice.common.vo.ResultVO;
import com.leadtrans.dictservice.mgr.controller.vo.OptionRespVO;
import com.leadtrans.dictservice.mgr.service.OptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mgr/options")
@Api(tags = "下拉选项接口")
public class OptionController {

    @Autowired
    private OptionService optionService;

    @PostMapping
    @ApiOperation(value = "查询下拉选项", notes = "[isValids,supplierTypes,countries,states,cities,globalAreas,globalPortAttris]")
    public ResultVO<Map<String, List<OptionRespVO>>> getOptions(@RequestBody Map<String, Map<String,String>> reqVO) {
        return ResultVO.success(optionService.getOptions(reqVO));
    }

}

