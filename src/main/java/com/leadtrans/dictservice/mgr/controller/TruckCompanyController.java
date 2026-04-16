package com.leadtrans.dictservice.mgr.controller;

import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.common.vo.ResultVO;
import com.leadtrans.dictservice.mgr.controller.vo.TruckCompanyPageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.TruckCompanyReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.TruckCompanyRespVO;
import com.leadtrans.dictservice.mgr.service.TruckCompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/mgr/truckCompanies")
@Api(tags = "拖车公司管理接口")
public class TruckCompanyController {

    @Autowired
    private TruckCompanyService truckCompanyService;

    // ------------------------ CRUD 操作 ------------------------
    @PostMapping
    @ApiOperation(value = "创建拖车公司", notes = "需提供拖车公司详细信息")
    public ResultVO<Long> create(@RequestBody @Valid TruckCompanyReqVO VO) {
        Long id = truckCompanyService.create(VO);
        return ResultVO.success(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除拖车公司", notes = "根据ID物理删除")
    @ApiImplicitParam(name = "id", value = "拖车公司ID", required = true, paramType = "path")
    public ResultVO<String> delete(@PathVariable Long id) {
        truckCompanyService.delete(id);
        return ResultVO.success();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "更新拖车公司", notes = "全字段更新")
    @ApiImplicitParam(name = "id", value = "拖车公司ID", required = true, paramType = "path")
    public ResultVO<String> update(@PathVariable Long id, @RequestBody TruckCompanyReqVO VO) {
        truckCompanyService.update(id, VO);
        return ResultVO.success();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询拖车公司详情", notes = "根据ID获取详细信息")
    @ApiImplicitParam(name = "id", value = "拖车公司ID", required = true, paramType = "path")
    public ResultVO<TruckCompanyRespVO> getById(@PathVariable Long id) {
        TruckCompanyRespVO VO = truckCompanyService.getById(id);
        return ResultVO.success(VO);
    }

    @PostMapping("/page")
    @ApiOperation(value = "分页查询拖车公司", notes = "支持条件筛选")
    public ResultVO<PageResult<TruckCompanyRespVO>> page(@RequestBody TruckCompanyPageReqVO reqVO) {
        System.out.println(reqVO.toString());
        PageResult<TruckCompanyRespVO> page = truckCompanyService.page(reqVO);
        return ResultVO.success(page);
    }

    // ------------------------ 其他业务操作 ------------------------
    @PatchMapping("/{id}/valid")
    @ApiOperation(value = "更新拖车公司状态", notes = "启用/禁用拖车公司")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "拖车公司ID", required = true, paramType = "path"),
            @ApiImplicitParam(name = "isValid", value = "状态：1-启用，0-禁用", required = true, paramType = "query")
    })
    public ResultVO<String> updateValid(
            @PathVariable Long id,
            @RequestParam Integer isValid) {
        truckCompanyService.updateValid(id, isValid);
        return ResultVO.success();
    }
}