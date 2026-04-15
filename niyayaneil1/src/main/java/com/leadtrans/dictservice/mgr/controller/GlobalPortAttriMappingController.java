package com.leadtrans.dictservice.mgr.controller;


import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.common.vo.ResultVO;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalPortAttriMappingPageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalPortAttriMappingReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalPortAttriMappingRespVO;
import com.leadtrans.dictservice.mgr.service.GlobalPortAttriMappingService;
import com.leadtrans.dictservice.mgr.service.GlobalPortAttriService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 港口属性映射表 前端控制器
 * </p>
 *
 * @author Aaron
 * @since 2025-04-22
 */
@RestController
@RequestMapping("/mgr/globalPortAttriMappings")
@Api(tags = "港口属性管理接口")
public class GlobalPortAttriMappingController {

    @Autowired
    private GlobalPortAttriMappingService globalPortAttriMappingService;

    // ------------------------ CRUD 操作 ------------------------
    @PostMapping
    @ApiOperation(value = "创建港口属性", notes = "需提供港口属性详细信息")
    public ResultVO<Long> create(@RequestBody @Valid GlobalPortAttriMappingReqVO VO) {
        Long id = globalPortAttriMappingService.create(VO);
        return ResultVO.success(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除港口属性", notes = "根据ID物理删除")
    @ApiImplicitParam(name = "id", value = "港口属性ID", required = true, paramType = "path")
    public ResultVO<String> delete(@PathVariable Long id) {
        globalPortAttriMappingService.delete(id);
        return ResultVO.success();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "更新港口属性", notes = "全字段更新")
    @ApiImplicitParam(name = "id", value = "港口属性ID", required = true, paramType = "path")
    public ResultVO<String> update(@PathVariable Long id,@RequestBody @Valid GlobalPortAttriMappingReqVO VO) {
        globalPortAttriMappingService.update(id, VO);
        return ResultVO.success();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询港口属性详情", notes = "根据ID获取详细信息")
    @ApiImplicitParam(name = "id", value = "港口属性ID", required = true, paramType = "path")
    public ResultVO<GlobalPortAttriMappingRespVO> getById(@PathVariable Long id) {
        GlobalPortAttriMappingRespVO VO = globalPortAttriMappingService.getById(id);
        return ResultVO.success(VO);
    }

    @GetMapping
    @ApiOperation(value = "分页查询港口属性", notes = "支持条件筛选")
    public ResultVO<PageResult<GlobalPortAttriMappingRespVO>> page(GlobalPortAttriMappingPageReqVO reqVO) {
        PageResult<GlobalPortAttriMappingRespVO> page = globalPortAttriMappingService.page(reqVO);
        return ResultVO.success(page);
    }

    // ------------------------ 其他业务操作 ------------------------
    @PatchMapping("/{id}/valid")
    @ApiOperation(value = "更新港口属性状态", notes = "启用/禁用港口属性")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "港口属性ID", required = true, paramType = "path"),
            @ApiImplicitParam(name = "isValid", value = "状态：1-启用，0-禁用", required = true, paramType = "query")
    })
    public ResultVO<String> updateValid(
            @PathVariable Long id,
            @RequestParam String isValid) {
        globalPortAttriMappingService.updateValid(id, isValid);
        return ResultVO.success();
    }
}

