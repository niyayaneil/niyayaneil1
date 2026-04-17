package com.leadtrans.dictservice.mgr.controller;

import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.common.vo.ResultVO;
import com.leadtrans.dictservice.mgr.controller.vo.LclWarehouseFreeTimePageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.LclWarehouseFreeTimeReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.LclWarehouseFreeTimeRespVO;
import com.leadtrans.dictservice.mgr.service.LclWarehouseFreeTimeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/mgr/lclWarehouseFreeTimes")
@Api(tags = "LCL拼箱免堆期配置管理接口")
public class LclWarehouseFreeTimeController {

    @Autowired
    private LclWarehouseFreeTimeService lclWarehouseFreeTimeService;

    // ------------------------ CRUD 操作 ------------------------
    @PostMapping
    @ApiOperation(value = "创建免堆期配置", notes = "需提供仓库和港口信息")
    public ResultVO<Long> create(@RequestBody @Valid LclWarehouseFreeTimeReqVO VO) {
        Long id = lclWarehouseFreeTimeService.create(VO);
        return ResultVO.success(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除免堆期配置", notes = "根据ID物理删除")
    @ApiImplicitParam(name = "id", value = "配置ID", required = true, paramType = "path")
    public ResultVO<String> delete(@PathVariable Long id) {
        lclWarehouseFreeTimeService.delete(id);
        return ResultVO.success();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "更新免堆期配置", notes = "全字段更新")
    @ApiImplicitParam(name = "id", value = "配置ID", required = true, paramType = "path")
    public ResultVO<String> update(@PathVariable Long id, @RequestBody LclWarehouseFreeTimeReqVO VO) {
        lclWarehouseFreeTimeService.update(id, VO);
        return ResultVO.success();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询免堆期配置详情", notes = "根据ID获取详细信息")
    @ApiImplicitParam(name = "id", value = "配置ID", required = true, paramType = "path")
    public ResultVO<LclWarehouseFreeTimeRespVO> getById(@PathVariable Long id) {
        LclWarehouseFreeTimeRespVO VO = lclWarehouseFreeTimeService.getById(id);
        return ResultVO.success(VO);
    }

    @PostMapping("/page")
    @ApiOperation(value = "分页查询免堆期配置", notes = "支持条件筛选")
    public ResultVO<PageResult<LclWarehouseFreeTimeRespVO>> page(@RequestBody LclWarehouseFreeTimePageReqVO reqVO) {
        PageResult<LclWarehouseFreeTimeRespVO> page = lclWarehouseFreeTimeService.page(reqVO);
        return ResultVO.success(page);
    }

    // ------------------------ 其他业务操作 ------------------------
    @PatchMapping("/{id}/valid")
    @ApiOperation(value = "更新免堆期配置状态", notes = "启用/禁用配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "配置ID", required = true, paramType = "path"),
            @ApiImplicitParam(name = "isValid", value = "状态：1-启用，0-禁用", required = true, paramType = "query")
    })
    public ResultVO<String> updateValid(
            @PathVariable Long id,
            @RequestParam Integer isValid) {
        lclWarehouseFreeTimeService.updateValid(id, isValid);
        return ResultVO.success();
    }
}
