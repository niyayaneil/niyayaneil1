package com.leadtrans.dictservice.mgr.controller;


import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.common.vo.ResultVO;
import com.leadtrans.dictservice.mgr.controller.vo.SupplierRespVO;
import com.leadtrans.dictservice.mgr.controller.vo.SupplierPageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.SupplierReqVO;
import com.leadtrans.dictservice.mgr.service.SupplierService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Aaron
 * @since 2025-04-15
 */
@RestController
@RequestMapping("/mgr/suppliers")
@Api(tags = "供应商管理接口")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    // ------------------------ CRUD 操作 ------------------------
    @PostMapping
    @ApiOperation(value = "创建供应商", notes = "需提供供应商详细信息")
    public ResultVO<Long> create(@RequestBody @Valid SupplierReqVO VO) {
        Long id = supplierService.create(VO);
        return ResultVO.success(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除供应商", notes = "根据ID物理删除")
    @ApiImplicitParam(name = "id", value = "供应商ID", required = true, paramType = "path")
    public ResultVO<String> delete(@PathVariable Long id) {
        supplierService.delete(id);
        return ResultVO.success();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "更新供应商", notes = "全字段更新")
    @ApiImplicitParam(name = "id", value = "供应商ID", required = true, paramType = "path")
    public ResultVO<String> update(@PathVariable Long id,@RequestBody @Valid SupplierReqVO VO) {
        supplierService.update(id, VO);
        return ResultVO.success();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询供应商详情", notes = "根据ID获取详细信息")
    @ApiImplicitParam(name = "id", value = "供应商ID", required = true, paramType = "path")
    public ResultVO<SupplierRespVO> getById(@PathVariable Long id) {
        SupplierRespVO VO = supplierService.getById(id);
        return ResultVO.success(VO);
    }

    @GetMapping
    @ApiOperation(value = "分页查询供应商", notes = "支持条件筛选")
    public ResultVO<PageResult<SupplierRespVO>> page(SupplierPageReqVO reqVO) {
        PageResult<SupplierRespVO> page = supplierService.page(reqVO);
        return ResultVO.success(page);
    }

    // ------------------------ 其他业务操作 ------------------------
    @PatchMapping("/{id}/valid")
    @ApiOperation(value = "更新供应商状态", notes = "启用/禁用供应商")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "供应商ID", required = true, paramType = "path"),
            @ApiImplicitParam(name = "isValid", value = "状态：1-启用，0-禁用", required = true, paramType = "query")
    })
    public ResultVO<String> updateValid(
            @PathVariable Long id,
            @RequestParam String isValid) {
        supplierService.updateValid(id, isValid);
        return ResultVO.success();
    }
}