package com.leadtrans.dictservice.mgr.controller;

import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.common.vo.ResultVO;
import com.leadtrans.dictservice.mgr.controller.vo.FclOutsideFreeTimePageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.FclOutsideFreeTimeReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.FclOutsideFreeTimeRespVO;
import com.leadtrans.dictservice.mgr.service.FclOutsideFreeTimeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/mgr/fclOutsideFreeTimes")
@Api(tags = "FCL目的港场外免用箱管理接口")
public class FclOutsideFreeTimeController {

    @Autowired
    private FclOutsideFreeTimeService fclOutsideFreeTimeService;

    // ------------------------ CRUD 操作 ------------------------
    @PostMapping
    @ApiOperation(value = "创建免用箱配置", notes = "需提供船公司、航线等信息")
    public ResultVO<Long> create(@RequestBody @Valid FclOutsideFreeTimeReqVO VO) {
        Long id = fclOutsideFreeTimeService.create(VO);
        return ResultVO.success(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除免用箱配置", notes = "根据ID物理删除")
    @ApiImplicitParam(name = "id", value = "配置ID", required = true, paramType = "path")
    public ResultVO<String> delete(@PathVariable Long id) {
        fclOutsideFreeTimeService.delete(id);
        return ResultVO.success();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "更新免用箱配置", notes = "全字段更新")
    @ApiImplicitParam(name = "id", value = "配置ID", required = true, paramType = "path")
    public ResultVO<String> update(@PathVariable Long id, @RequestBody FclOutsideFreeTimeReqVO VO) {
        fclOutsideFreeTimeService.update(id, VO);
        return ResultVO.success();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询免用箱配置详情", notes = "根据ID获取详细信息")
    @ApiImplicitParam(name = "id", value = "配置ID", required = true, paramType = "path")
    public ResultVO<FclOutsideFreeTimeRespVO> getById(@PathVariable Long id) {
        FclOutsideFreeTimeRespVO VO = fclOutsideFreeTimeService.getById(id);
        return ResultVO.success(VO);
    }

    @PostMapping("/page")
    @ApiOperation(value = "分页查询免用箱配置", notes = "支持条件筛选")
    public ResultVO<PageResult<FclOutsideFreeTimeRespVO>> page(@RequestBody FclOutsideFreeTimePageReqVO reqVO) {
        PageResult<FclOutsideFreeTimeRespVO> page = fclOutsideFreeTimeService.page(reqVO);
        return ResultVO.success(page);
    }

    // ------------------------ 其他业务操作 ------------------------
    @PatchMapping("/{id}/valid")
    @ApiOperation(value = "更新免用箱配置状态", notes = "启用/禁用配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "配置ID", required = true, paramType = "path"),
            @ApiImplicitParam(name = "isValid", value = "状态：1-启用，0-禁用", required = true, paramType = "query")
    })
    public ResultVO<String> updateValid(
            @PathVariable Long id,
            @RequestParam Integer isValid) {
        fclOutsideFreeTimeService.updateValid(id, isValid);
        return ResultVO.success();
    }
}
