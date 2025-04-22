package com.leadtrans.dictservice.mgr.controller;

import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.common.vo.ResultVO;
import com.leadtrans.dictservice.mgr.controller.vo.CarrierCompanyPageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.CarrierCompanyReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.CarrierCompanyRespVO;
import com.leadtrans.dictservice.mgr.service.CarrierCompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/mgr/carrierCompanies")
@Api(tags = "船司管理接口")
public class CarrierCompanyController {

    @Autowired
    private CarrierCompanyService carrierCompanyService;

    // ------------------------ CRUD 操作 ------------------------
    @PostMapping
    @ApiOperation(value = "创建船司", notes = "需提供船司详细信息")
    public ResultVO<Long> create(@RequestBody @Valid CarrierCompanyReqVO VO) {
        Long id = carrierCompanyService.create(VO);
        return ResultVO.success(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除船司", notes = "根据ID物理删除")
    @ApiImplicitParam(name = "id", value = "船司ID", required = true, paramType = "path")
    public ResultVO<String> delete(@PathVariable Long id) {
        carrierCompanyService.delete(id);
        return ResultVO.success();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "更新船司", notes = "全字段更新")
    @ApiImplicitParam(name = "id", value = "船司ID", required = true, paramType = "path")
    public ResultVO<String> update(@PathVariable Long id,@RequestBody CarrierCompanyReqVO VO) {
        carrierCompanyService.update(id, VO);
        return ResultVO.success();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询船司详情", notes = "根据ID获取详细信息")
    @ApiImplicitParam(name = "id", value = "船司ID", required = true, paramType = "path")
    public ResultVO<CarrierCompanyRespVO> getById(@PathVariable Long id) {
        CarrierCompanyRespVO VO = carrierCompanyService.getById(id);
        return ResultVO.success(VO);
    }

    @GetMapping
    @ApiOperation(value = "分页查询船司", notes = "支持条件筛选")
    public ResultVO<PageResult<CarrierCompanyRespVO>> page(CarrierCompanyPageReqVO reqVO) {
        PageResult<CarrierCompanyRespVO> page = carrierCompanyService.page(reqVO);
        return ResultVO.success(page);
    }

    // ------------------------ 其他业务操作 ------------------------
    @PatchMapping("/{id}/valid")
    @ApiOperation(value = "更新船司状态", notes = "启用/禁用船司")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "船司ID", required = true, paramType = "path"),
            @ApiImplicitParam(name = "isValid", value = "状态：1-启用，0-禁用", required = true, paramType = "query")
    })
    public ResultVO<String> updateValid(
            @PathVariable Long id,
            @RequestParam String isValid) {
        carrierCompanyService.updateValid(id, isValid);
        return ResultVO.success();
    }
}

