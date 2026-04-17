package com.leadtrans.dictservice.mgr.controller;

import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.common.vo.ResultVO;
import com.leadtrans.dictservice.mgr.controller.vo.WarehousePageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.WarehouseReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.WarehouseRespVO;
import com.leadtrans.dictservice.mgr.service.WarehouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/mgr/warehouses")
@Api(tags = "仓库管理接口")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    // ------------------------ CRUD 操作 ------------------------
    @PostMapping
    @ApiOperation(value = "创建仓库", notes = "需提供仓库详细信息")
    public ResultVO<Long> create(@RequestBody @Valid WarehouseReqVO VO) {
        Long id = warehouseService.create(VO);
        return ResultVO.success(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除仓库", notes = "根据ID物理删除")
    @ApiImplicitParam(name = "id", value = "仓库ID", required = true, paramType = "path")
    public ResultVO<String> delete(@PathVariable Long id) {
        warehouseService.delete(id);
        return ResultVO.success();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "更新仓库", notes = "全字段更新")
    @ApiImplicitParam(name = "id", value = "仓库ID", required = true, paramType = "path")
    public ResultVO<String> update(@PathVariable Long id, @RequestBody WarehouseReqVO VO) {
        warehouseService.update(id, VO);
        return ResultVO.success();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询仓库详情", notes = "根据ID获取详细信息")
    @ApiImplicitParam(name = "id", value = "仓库ID", required = true, paramType = "path")
    public ResultVO<WarehouseRespVO> getById(@PathVariable Long id) {
        WarehouseRespVO VO = warehouseService.getById(id);
        return ResultVO.success(VO);
    }

    @PostMapping("/page")
    @ApiOperation(value = "分页查询仓库", notes = "支持条件筛选")
    public ResultVO<PageResult<WarehouseRespVO>> page(@RequestBody WarehousePageReqVO reqVO) {
        System.out.println(reqVO.toString());
        PageResult<WarehouseRespVO> page = warehouseService.page(reqVO);
        return ResultVO.success(page);
    }

    // ------------------------ 其他业务操作 ------------------------
    @PatchMapping("/{id}/valid")
    @ApiOperation(value = "更新仓库状态", notes = "启用/禁用仓库")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "仓库ID", required = true, paramType = "path"),
            @ApiImplicitParam(name = "isValid", value = "状态：1-启用，0-禁用", required = true, paramType = "query")
    })
    public ResultVO<String> updateValid(
            @PathVariable Long id,
            @RequestParam Integer isValid) {
        warehouseService.updateValid(id, isValid);
        return ResultVO.success();
    }
}