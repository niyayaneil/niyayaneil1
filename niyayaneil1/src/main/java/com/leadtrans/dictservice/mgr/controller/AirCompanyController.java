package com.leadtrans.dictservice.mgr.controller;

import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.common.vo.ResultVO;
import com.leadtrans.dictservice.mgr.controller.vo.AirCompanyPageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.AirCompanyReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.AirCompanyRespVO;
import com.leadtrans.dictservice.mgr.service.AirCompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/mgr/airCompanies")
@Api(tags = "航空公司管理接口")
public class AirCompanyController {

    @Autowired
    private AirCompanyService airCompanyService;

    // ------------------------ CRUD 操作 ------------------------
    @PostMapping
    @ApiOperation(value = "创建航空公司", notes = "需提供航空公司详细信息")
    public ResultVO<Long> create(@RequestBody @Valid AirCompanyReqVO VO) {
        Long id = airCompanyService.create(VO);
        return ResultVO.success(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除航空公司", notes = "根据ID物理删除")
    @ApiImplicitParam(name = "id", value = "航空公司ID", required = true, paramType = "path")
    public ResultVO<String> delete(@PathVariable Long id) {
        airCompanyService.delete(id);
        return ResultVO.success();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "更新航空公司", notes = "全字段更新")
    @ApiImplicitParam(name = "id", value = "航空公司ID", required = true, paramType = "path")
    public ResultVO<String> update(@PathVariable Long id, @RequestBody AirCompanyReqVO VO) {
        airCompanyService.update(id, VO);
        return ResultVO.success();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询航空公司详情", notes = "根据ID获取详细信息")
    @ApiImplicitParam(name = "id", value = "航空公司ID", required = true, paramType = "path")
    public ResultVO<AirCompanyRespVO> getById(@PathVariable Long id) {
        AirCompanyRespVO VO = airCompanyService.getById(id);
        return ResultVO.success(VO);
    }

    @PostMapping("/page")
    @ApiOperation(value = "分页查询航空公司", notes = "支持条件筛选")
    public ResultVO<PageResult<AirCompanyRespVO>> page(@RequestBody AirCompanyPageReqVO reqVO) {
        PageResult<AirCompanyRespVO> page = airCompanyService.page(reqVO);
        return ResultVO.success(page);
    }

    // ------------------------ 其他业务操作 ------------------------
    @PatchMapping("/{id}/valid")
    @ApiOperation(value = "更新航空公司状态", notes = "启用/禁用航空公司")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "航空公司ID", required = true, paramType = "path"),
            @ApiImplicitParam(name = "isValid", value = "状态：1-启用，0-禁用", required = true, paramType = "query")
    })
    public ResultVO<String> updateValid(
            @PathVariable Long id,
            @RequestParam Integer isValid) {
        airCompanyService.updateValid(id, isValid);
        return ResultVO.success();
    }
}