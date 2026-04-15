package com.leadtrans.dictservice.mgr.controller;


import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.common.vo.ResultVO;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalTerminalPageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalTerminalReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.GlobalTerminalRespVO;
import com.leadtrans.dictservice.mgr.service.GlobalTerminalService;
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
@RequestMapping("/mgr/globalTerminals")
@Api(tags = "码头管理接口")
public class GlobalTerminalController {

    @Autowired
    private GlobalTerminalService globalTerminalService;

    // ------------------------ CRUD 操作 ------------------------
    @PostMapping
    @ApiOperation(value = "创建码头", notes = "需提供码头详细信息")
    public ResultVO<Long> create(@RequestBody @Valid GlobalTerminalReqVO VO) {
        Long id = globalTerminalService.create(VO);
        return ResultVO.success(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除码头", notes = "根据ID物理删除")
    @ApiImplicitParam(name = "id", value = "码头ID", required = true, paramType = "path")
    public ResultVO<String> delete(@PathVariable Long id) {
        globalTerminalService.delete(id);
        return ResultVO.success();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "更新码头", notes = "全字段更新")
    @ApiImplicitParam(name = "id", value = "码头ID", required = true, paramType = "path")
    public ResultVO<String> update(@PathVariable Long id,@RequestBody @Valid GlobalTerminalReqVO VO) {
        globalTerminalService.update(id, VO);
        return ResultVO.success();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询码头详情", notes = "根据ID获取详细信息")
    @ApiImplicitParam(name = "id", value = "码头ID", required = true, paramType = "path")
    public ResultVO<GlobalTerminalRespVO> getById(@PathVariable Long id) {
        GlobalTerminalRespVO VO = globalTerminalService.getById(id);
        return ResultVO.success(VO);
    }

    @GetMapping
    @ApiOperation(value = "分页查询码头", notes = "支持条件筛选")
    public ResultVO<PageResult<GlobalTerminalRespVO>> page(GlobalTerminalPageReqVO reqVO) {
        PageResult<GlobalTerminalRespVO> page = globalTerminalService.page(reqVO);
        return ResultVO.success(page);
    }

    // ------------------------ 其他业务操作 ------------------------
    @PatchMapping("/{id}/valid")
    @ApiOperation(value = "更新码头状态", notes = "启用/禁用码头")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "码头ID", required = true, paramType = "path"),
            @ApiImplicitParam(name = "isValid", value = "状态：1-启用，0-禁用", required = true, paramType = "query")
    })
    public ResultVO<String> updateValid(
            @PathVariable Long id,
            @RequestParam String isValid) {
        globalTerminalService.updateValid(id, isValid);
        return ResultVO.success();
    }
}

