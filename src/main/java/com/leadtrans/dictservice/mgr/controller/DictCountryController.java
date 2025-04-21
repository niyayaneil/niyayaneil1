package com.leadtrans.dictservice.mgr.controller;


import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.common.vo.ResultVO;
import com.leadtrans.dictservice.mgr.controller.vo.DictCountryRespVO;
import com.leadtrans.dictservice.mgr.controller.vo.DictCountryPageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.DictCountryReqVO;
import com.leadtrans.dictservice.mgr.service.DictCountryService;
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
@RequestMapping("/mgr/dictCountries")
@Api(tags = "国家管理接口")
public class DictCountryController {

    @Autowired
    private DictCountryService dictCountryService;

    // ------------------------ CRUD 操作 ------------------------
    @PostMapping
    @ApiOperation(value = "创建国家", notes = "需提供国家详细信息")
    public ResultVO<Long> create(@RequestBody @Valid DictCountryReqVO VO) {
        Long id = dictCountryService.create(VO);
        return ResultVO.success(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除国家", notes = "根据ID物理删除")
    @ApiImplicitParam(name = "id", value = "国家ID", required = true, paramType = "path")
    public ResultVO<String> delete(@PathVariable Long id) {
        dictCountryService.delete(id);
        return ResultVO.success();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "更新国家", notes = "全字段更新")
    @ApiImplicitParam(name = "id", value = "国家ID", required = true, paramType = "path")
    public ResultVO<String> update(@PathVariable Long id,@RequestBody @Valid DictCountryReqVO VO) {
        dictCountryService.update(id, VO);
        return ResultVO.success();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询国家详情", notes = "根据ID获取详细信息")
    @ApiImplicitParam(name = "id", value = "国家ID", required = true, paramType = "path")
    public ResultVO<DictCountryRespVO> getById(@PathVariable Long id) {
        DictCountryRespVO VO = dictCountryService.getById(id);
        return ResultVO.success(VO);
    }

    @GetMapping
    @ApiOperation(value = "分页查询国家", notes = "支持条件筛选")
    public ResultVO<PageResult<DictCountryRespVO>> page(DictCountryPageReqVO reqVO) {
        PageResult<DictCountryRespVO> page = dictCountryService.page(reqVO);
        return ResultVO.success(page);
    }

    // ------------------------ 其他业务操作 ------------------------
    @PatchMapping("/{id}/valid")
    @ApiOperation(value = "更新国家状态", notes = "启用/禁用国家")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "国家ID", required = true, paramType = "path"),
            @ApiImplicitParam(name = "isValid", value = "状态：1-启用，0-禁用", required = true, paramType = "query")
    })
    public ResultVO<String> updateValid(
            @PathVariable Long id,
            @RequestParam String isValid) {
        dictCountryService.updateValid(id, isValid);
        return ResultVO.success();
    }
}
