package com.leadtrans.dictservice.mgr.controller;

import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.common.vo.ResultVO;
import com.leadtrans.dictservice.mgr.controller.vo.RailCompanyPageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.RailCompanyReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.RailCompanyRespVO;
import com.leadtrans.dictservice.mgr.service.RailCompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/mgr/railCompanies")
@Api(tags = "铁路公司管理接口")
public class RailCompanyController {

    @Autowired
    private RailCompanyService railCompanyService;

    // ------------------------ CRUD 操作 ------------------------
    @PostMapping
    @ApiOperation(value = "创建铁路公司", notes = "需提供铁路公司详细信息")
    public ResultVO<Long> create(@RequestBody @Valid RailCompanyReqVO VO) {
        Long id = railCompanyService.create(VO);
        return ResultVO.success(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除铁路公司", notes = "根据ID物理删除")
    @ApiImplicitParam(name = "id", value = "铁路公司ID", required = true, paramType = "path")
    public ResultVO<String> delete(@PathVariable Long id) {
        railCompanyService.delete(id);
        return ResultVO.success();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "更新铁路公司", notes = "全字段更新")
    @ApiImplicitParam(name = "id", value = "铁路公司ID", required = true, paramType = "path")
    public ResultVO<String> update(@PathVariable Long id, @RequestBody RailCompanyReqVO VO) {
        railCompanyService.update(id, VO);
        return ResultVO.success();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询铁路公司详情", notes = "根据ID获取详细信息")
    @ApiImplicitParam(name = "id", value = "铁路公司ID", required = true, paramType = "path")
    public ResultVO<RailCompanyRespVO> getById(@PathVariable Long id) {
        RailCompanyRespVO VO = railCompanyService.getById(id);
        return ResultVO.success(VO);
    }

    @PostMapping("/page")
    @ApiOperation(value = "分页查询铁路公司", notes = "支持条件筛选")
    public ResultVO<PageResult<RailCompanyRespVO>> page(@RequestBody RailCompanyPageReqVO reqVO) {
        PageResult<RailCompanyRespVO> page = railCompanyService.page(reqVO);
        return ResultVO.success(page);
    }

    // ------------------------ 其他业务操作 ------------------------
    @PatchMapping("/{id}/valid")
    @ApiOperation(value = "更新铁路公司状态", notes = "启用/禁用铁路公司")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "铁路公司ID", required = true, paramType = "path"),
            @ApiImplicitParam(name = "isValid", value = "状态：1-启用，0-禁用", required = true, paramType = "query")
    })
    public ResultVO<String> updateValid(
            @PathVariable Long id,
            @RequestParam Integer isValid) {
        railCompanyService.updateValid(id, isValid);
        return ResultVO.success();
    }
}
