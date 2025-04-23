package com.leadtrans.dictservice.mgr.controller;


import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.common.vo.ResultVO;
import com.leadtrans.dictservice.mgr.controller.vo.PortMappingPageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.PortMappingReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.PortMappingRespVO;
import com.leadtrans.dictservice.mgr.service.PortMappingService;
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
 * @since 2025-04-23
 */
@RestController
@RequestMapping("/mgr/portMappings")
@Api(tags = "港口映射管理接口")
public class PortMappingController {

    @Autowired
    private PortMappingService portMappingService;

    // ------------------------ CRUD 操作 ------------------------
    @PostMapping
    @ApiOperation(value = "创建港口映射", notes = "需提供港口映射详细信息")
    public ResultVO<Long> create(@RequestBody @Valid PortMappingReqVO VO) {
        Long id = portMappingService.create(VO);
        return ResultVO.success(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除港口映射", notes = "根据ID物理删除")
    @ApiImplicitParam(name = "id", value = "港口映射ID", required = true, paramType = "path")
    public ResultVO<String> delete(@PathVariable Long id) {
        portMappingService.delete(id);
        return ResultVO.success();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "更新港口映射", notes = "全字段更新")
    @ApiImplicitParam(name = "id", value = "港口映射ID", required = true, paramType = "path")
    public ResultVO<String> update(@PathVariable Long id,@RequestBody @Valid PortMappingReqVO VO) {
        portMappingService.update(id, VO);
        return ResultVO.success();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询港口映射详情", notes = "根据ID获取详细信息")
    @ApiImplicitParam(name = "id", value = "港口映射ID", required = true, paramType = "path")
    public ResultVO<PortMappingRespVO> getById(@PathVariable Long id) {
        PortMappingRespVO VO = portMappingService.getById(id);
        return ResultVO.success(VO);
    }

    @GetMapping
    @ApiOperation(value = "分页查询港口映射", notes = "支持条件筛选")
    public ResultVO<PageResult<PortMappingRespVO>> page(PortMappingPageReqVO reqVO) {
        PageResult<PortMappingRespVO> page = portMappingService.page(reqVO);
        return ResultVO.success(page);
    }

    // ------------------------ 其他业务操作 ------------------------
    @PatchMapping("/{id}/valid")
    @ApiOperation(value = "更新港口映射状态", notes = "启用/禁用港口映射")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "港口映射ID", required = true, paramType = "path"),
            @ApiImplicitParam(name = "isValid", value = "状态：1-启用，0-禁用", required = true, paramType = "query")
    })
    public ResultVO<String> updateValid(
            @PathVariable Long id,
            @RequestParam String isValid) {
        portMappingService.updateValid(id, isValid);
        return ResultVO.success();
    }
}

