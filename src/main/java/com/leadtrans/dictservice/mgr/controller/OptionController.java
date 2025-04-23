package com.leadtrans.dictservice.mgr.controller;

import com.leadtrans.dictservice.common.vo.ResultVO;
import com.leadtrans.dictservice.mgr.controller.vo.OptionRespVO;
import com.leadtrans.dictservice.mgr.service.OptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mgr/options")
@Api(tags = "下拉选项接口")
public class OptionController {

    @Autowired
    private OptionService optionService;

    @PostMapping
    @ApiOperation(value = "查询下拉选项", notes = "传入已注册下拉选项key和选项查询参数，查询下拉选项列表")
    public ResultVO<Map<String, List<OptionRespVO>>> getOptions(@RequestBody Map<String, Map<String,String>> reqVO) {
        return ResultVO.success(optionService.getOptions(reqVO));
    }

    @GetMapping("/registers")
    @ApiOperation(value = "已注册下拉选项", notes = "查询已经注册的下拉选项")
    public ResultVO<Collection<String>> getRegisters() {
        return ResultVO.success(optionService.getRegisters());
    }

}

