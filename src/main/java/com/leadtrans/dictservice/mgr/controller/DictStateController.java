package com.leadtrans.dictservice.mgr.controller;


import com.leadtrans.dictservice.common.vo.PageResult;
import com.leadtrans.dictservice.common.vo.ResultVO;
import com.leadtrans.dictservice.mgr.controller.vo.DictStateRespVO;
import com.leadtrans.dictservice.mgr.controller.vo.DictStatePageReqVO;
import com.leadtrans.dictservice.mgr.controller.vo.DictStateReqVO;
import com.leadtrans.dictservice.mgr.service.DictStateService;
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
@RequestMapping("/mgr/dictStates")
@Api(tags = "省或州管理接口")
public class DictStateController {

    @Autowired
    private DictStateService dictStateService;

    // ------------------------ CRUD 操作 ------------------------
    @PostMapping
    @ApiOperation(value = "创建省或州", notes = "需提供省或州详细信息")
    public ResultVO<Long> create(@RequestBody @Valid DictStateReqVO VO) {
        Long id = dictStateService.create(VO);
        return ResultVO.success(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除省或州", notes = "根据ID物理删除")
    @ApiImplicitParam(name = "id", value = "省或州ID", required = true, paramType = "path")
    public ResultVO<String> delete(@PathVariable Long id) {
        dictStateService.delete(id);
        return ResultVO.success();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "更新省或州", notes = "全字段更新")
    @ApiImplicitParam(name = "id", value = "省或州ID", required = true, paramType = "path")
    public ResultVO<String> update(@PathVariable Long id,@RequestBody @Valid DictStateReqVO VO) {
        dictStateService.update(id, VO);
        return ResultVO.success();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询省或州详情", notes = "根据ID获取详细信息")
    @ApiImplicitParam(name = "id", value = "省或州ID", required = true, paramType = "path")
    public ResultVO<DictStateRespVO> getById(@PathVariable Long id) {
        DictStateRespVO VO = dictStateService.getById(id);
        return ResultVO.success(VO);
    }

    @GetMapping
    @ApiOperation(value = "分页查询省或州", notes = "支持条件筛选")
    public ResultVO<PageResult<DictStateRespVO>> page(DictStatePageReqVO reqVO) {
        PageResult<DictStateRespVO> page = dictStateService.page(reqVO);
        return ResultVO.success(page);
    }

    // ------------------------ 其他业务操作 ------------------------
    @PatchMapping("/{id}/valid")
    @ApiOperation(value = "更新省或州状态", notes = "启用/禁用省或州")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "省或州ID", required = true, paramType = "path"),
            @ApiImplicitParam(name = "isValid", value = "状态：1-启用，0-禁用", required = true, paramType = "query")
    })
    public ResultVO<String> updateValid(
            @PathVariable Long id,
            @RequestParam String isValid) {
        dictStateService.updateValid(id, isValid);
        return ResultVO.success();
    }
}
