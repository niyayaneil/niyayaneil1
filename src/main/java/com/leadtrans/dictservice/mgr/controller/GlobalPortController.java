package com.leadtrans.dictservice.mgr.controller;


import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.common.vo.ResultVO;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalPortPageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalPortReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalPortRespVO;
import com.leadtrans.dictservice.mgr.service.GlobalPortService;
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
 * @since 2025-04-22
 */
@RestController
@RequestMapping("/mgr/globalPorts")
@Api(tags = "港口管理接口")
public class GlobalPortController {

    @Autowired
    private GlobalPortService globalPortService;

    // ------------------------ CRUD 操作 ------------------------
    @PostMapping
    @ApiOperation(value = "创建港口", notes = "需提供港口详细信息")
    public ResultVO<Long> create(@RequestBody @Valid GlobalPortReqVO VO) {
        Long id = globalPortService.create(VO);
        return ResultVO.success(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除港口", notes = "根据ID物理删除")
    @ApiImplicitParam(name = "id", value = "港口ID", required = true, paramType = "path")
    public ResultVO<String> delete(@PathVariable Long id) {
        globalPortService.delete(id);
        return ResultVO.success();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "更新港口", notes = "全字段更新")
    @ApiImplicitParam(name = "id", value = "港口ID", required = true, paramType = "path")
    public ResultVO<String> update(@PathVariable Long id,@RequestBody @Valid GlobalPortReqVO VO) {
        globalPortService.update(id, VO);
        return ResultVO.success();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询港口详情", notes = "根据ID获取详细信息")
    @ApiImplicitParam(name = "id", value = "港口ID", required = true, paramType = "path")
    public ResultVO<GlobalPortRespVO> getById(@PathVariable Long id) {
        GlobalPortRespVO VO = globalPortService.getById(id);
        return ResultVO.success(VO);
    }

    @GetMapping
    @ApiOperation(value = "分页查询港口", notes = "支持条件筛选")
    public ResultVO<PageResult<GlobalPortRespVO>> page(GlobalPortPageReqVO reqVO) {
        PageResult<GlobalPortRespVO> page = globalPortService.page(reqVO);
        return ResultVO.success(page);
    }

    // ------------------------ 其他业务操作 ------------------------
    @PatchMapping("/{id}/valid")
    @ApiOperation(value = "更新港口状态", notes = "启用/禁用港口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "港口ID", required = true, paramType = "path"),
            @ApiImplicitParam(name = "isValid", value = "状态：1-启用，0-禁用", required = true, paramType = "query")
    })
    public ResultVO<String> updateValid(
            @PathVariable Long id,
            @RequestParam String isValid) {
        globalPortService.updateValid(id, isValid);
        return ResultVO.success();
    }
}

